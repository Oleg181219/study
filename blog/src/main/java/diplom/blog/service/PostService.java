package diplom.blog.service;

import diplom.blog.api.response.PostResponse;
import diplom.blog.api.response.TagResponse;
import diplom.blog.model.*;
import diplom.blog.repo.*;
import diplom.blog.response_model.RespPosts;
import diplom.blog.response_model.RespTags;
import diplom.blog.response_model.RespUser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PostService {
    public List<Post> allPosts;
    List<User> userList;
    List<PostComment> postComments;
    List<PostVotes> postVotes;

    List<Tags> allTags;
    List<TagToPost> allTagToPost;

    public List<Post> getAllPosts() {
        return allPosts;
    }

    @Bean
    public CommandLineRunner getTag(TagsRepository repository) {
        return (args) -> allTags = repository.findAll();
    }

    @Bean
    public CommandLineRunner getTagToPost(TagToPostRepository repository) {
        return (args) -> allTagToPost = repository.findAll();
    }

    //получение в List<Post> всех Post
    @Bean
    public CommandLineRunner getPost(PostRepository repository) {
        return (args) -> allPosts = repository.findAllByIdAfter(0L);
    }

    //получение List<User> всех User
    @Bean
    public CommandLineRunner getUser(UserRepository repository) {
        return (args) -> userList = repository.findAll();
    }

    // Получение в List<PostComment> всех PostComment
    @Bean
    public CommandLineRunner getPostComments(PostCommentRepository repository) {
        return (args) -> postComments = repository.findAllByIdAfter(0L);
    }

    //Получение в List<PostVotes> все PostVotes
    @Bean
    public CommandLineRunner getPostVotes(PostVotesRepository repository) {
        return (args) -> postVotes = repository.findAll();
    }

    //получение Post по формату прописанному в API
    public PostResponse getPost() {

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
        System.out.println(respTags.size());
        tagResponse.setTags(respTags);
        return tagResponse;
    }

}
