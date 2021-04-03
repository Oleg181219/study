package diplom.blog.service;

import diplom.blog.api.response.PostResponse;
import diplom.blog.api.response.TagResponse;
import diplom.blog.model.*;
import diplom.blog.repo.*;
import diplom.blog.response_model.RespPosts;
import diplom.blog.response_model.RespTags;
import diplom.blog.response_model.RespUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PostService {
    private final PostRepository postRepository;
    private final TagToPostRepository tagToPostRepository;
    private final PostCommentRepository postCommentRepository;
    private final PostVotesRepository postVotesRepository;
    private final TagsRepository tagsRepository;

    @Autowired
    public PostService(PostRepository postRepository
            , TagToPostRepository tagToPostRepository
            , PostCommentRepository postCommentRepository
            , PostVotesRepository postVotesRepository
            , TagsRepository tagsRepository) {
        this.postRepository = postRepository;
        this.tagToPostRepository = tagToPostRepository;
        this.tagsRepository = tagsRepository;
        this.postCommentRepository = postCommentRepository;
        this.postVotesRepository = postVotesRepository;
    }

    //получение Post по формату прописанному в API
    public PostResponse getPost() {
        List<Post> allPosts = postRepository.findAll();
        List<PostVotes> postVotes = postVotesRepository.findAll();
        List<PostComment> postComments = postCommentRepository.findAll();

        PostResponse postResponse = new PostResponse();
        ArrayList<RespPosts> postsList = new ArrayList<>();

//          count
        postResponse.setCount(allPosts.size());
        for (Post allPost : allPosts) {
            RespPosts respPosts = new RespPosts();

//          posts: id
            respPosts.setId(allPost.getId());

//          posts: timestamp
            respPosts.setTimestamp(System.currentTimeMillis() / 1000);

//          posts: user
            RespUser respUser = new RespUser();
            respUser.setId((long) allPost.getUser().getId());
            respUser.setName(allPost.getUser().getName());
            respPosts.setUser(respUser);

//          posts: title
            respPosts.setTitle(allPost.getTitle());

//          posts: announce
            if (allPost.getText().length() > 150) {
                String temp = (allPost.getText()).substring(0, 150);
                respPosts.setAnnounce((temp.substring(0, temp.lastIndexOf(" ")) + "..."));
            } else {
                respPosts.setAnnounce(((allPost.getText()).substring(0, allPost.getText().length()) + "..."));
            }
            int likes = 0;
            int dislikes = 0;
            for (PostVotes postVote : postVotes) {
                if (allPost.getId() == postVote.getPost().getId()) {
                    if (postVote.getValue() == -1) {
                        dislikes = dislikes + 1;
                    } else if (postVote.getValue() == 1) {
                        likes = likes + 1;
                    }
                }
            }
//          posts: likeCount
            respPosts.setLikeCount(likes);
//          posts: dislikeCount
            respPosts.setDislikeCount(dislikes);
//          posts: commentCount
            int postCommentCount = 0;

            for (PostComment postComment : postComments) {
                if (postComment.getUser().getId() == allPost.getId()) {
                    postCommentCount = postCommentCount + 1;
                }
            }
            respPosts.setCommentCount(postCommentCount);

//          posts: viewCount
            respPosts.setViewCount(allPost.getViewCount());
            postsList.add(respPosts);
        }
        postResponse.setPosts(postsList);
        return postResponse;
    }

    //=====================================================================
    public TagResponse getTags() {
        List<TagToPost> allTagToPost = tagToPostRepository.findAll();
        List<Tags> allTags = tagsRepository.findAll();
        List<Post> allPosts = postRepository.findAll();
        TagResponse tagResponse = new TagResponse();

        ArrayList<RespTags> respTags = new ArrayList<>();
        HashMap<String, Integer> respTagsList = new HashMap<>();

        for (TagToPost tagToPost : allTagToPost) {
            if (respTagsList.containsKey(allTags.get(Math.toIntExact(tagToPost.getTagId())).getName())) {
                respTagsList.put((allTags.get(Math.toIntExact(tagToPost.getTagId())).getName())
                        , (respTagsList.get((allTags.get(Math.toIntExact(tagToPost.getTagId())).getName())) + 1));
            } else {
                respTagsList.put((allTags.get(Math.toIntExact(tagToPost.getTagId())).getName()), 1);
            }

        }
//      находим самый популярный тэг
        int countOfMaxPopularTag = respTagsList.entrySet()
                .stream()
                .max(Comparator.comparingInt(entry -> entry.getValue()))
                .get().getValue();


        double dWeightMax = (1 / ((double) countOfMaxPopularTag / (double) allPosts.size()));


        respTagsList.entrySet().forEach(entry -> {
            RespTags respTag = new RespTags();
            respTag.setName(entry.getKey());
            respTag.setWeight(entry.getValue() / (double)allPosts.size() * dWeightMax );
            respTags.add(respTag);

        });
        tagResponse.setTags(respTags);
        return tagResponse;
    }

}
