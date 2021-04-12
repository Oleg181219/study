package diplom.blog.service;

import diplom.blog.api.response.CalendarResponse;
import diplom.blog.api.response.PostResponse;
import diplom.blog.base.SearchEntry;
import diplom.blog.model.*;
import diplom.blog.repo.*;
import diplom.blog.model.DtoModel.PostDto;
import diplom.blog.model.DtoModel.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;


@Component
public class PostService {
    private final PostRepository postRepository;
    private final TagToPostRepository tagToPostRepository;
    private final PostCommentRepository postCommentRepository;
    private final PostVotesRepository postVotesRepository;
    private final TagsRepository tagsRepository;
    SimpleDateFormat formaterPostDate = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat formaterYear = new SimpleDateFormat("yyyy");


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
    public PostResponse getPost(int offset, int limit, String mode) {
        ArrayList<PostDto> postsList = new ArrayList<>();
        PostResponse postResponse = new PostResponse();
        Page<Post> allPosts;
        int countPosts = 0;


        switch (mode) {
            case "popular":
                allPosts = postRepository.findPostsOrderByPostComments(PageRequest.of(offset / limit, limit));
                countPosts = Math.toIntExact(allPosts.getTotalElements());
                for (Post allPost : allPosts) {

                    PostDto newRespPost = addNewRespPosts(allPost);
                    if (!postsList.contains(newRespPost)) {
                        postsList.add(newRespPost);
                    }
                }
                break;
            case "best":
                allPosts = postRepository.findPostsOrderByLikeCount(PageRequest.of(offset / limit, limit));
                countPosts = Math.toIntExact(allPosts.getTotalElements());
                for (Post allPost : allPosts) {

                    PostDto newRespPost = addNewRespPosts(allPost);
                    if (!postsList.contains(newRespPost)) {
                        postsList.add(newRespPost);
                    }
                }
                break;
            case "early":
                allPosts = postRepository.findPostsOrderByTimeIincrease(PageRequest.of(offset / limit, limit));
                countPosts = Math.toIntExact(allPosts.getTotalElements());
                for (Post allPost : allPosts) {

                    PostDto newRespPost = addNewRespPosts(allPost);
                    if (!postsList.contains(newRespPost)) {
                        postsList.add(newRespPost);
                    }
                }
                break;
            default:
                allPosts = postRepository.findPostsOrderByTimeDesc(PageRequest.of(offset / limit, limit));
                countPosts = Math.toIntExact(allPosts.getTotalElements());
                for (Post allPost : allPosts) {

                    PostDto newRespPost = addNewRespPosts(allPost);
                    if (!postsList.contains(newRespPost)) {
                        postsList.add(newRespPost);
                    }
                }
                break;
        }
        postResponse.setCount(countPosts);
        postResponse.setPosts(postsList);

        if (postsList.size() == 0) {
            postResponse.setCount(0);
            postResponse.setPosts(postsList);
            return postResponse;
        }
        return postResponse;
    }


    //=================================================================================
    public PostResponse getPostsSearch(int offset, int limit, String query) {
        ArrayList<PostDto> postsList = new ArrayList<>();
        PostResponse postResponse = new PostResponse();
        int countPosts = 0;

        Page<Post> allPosts = postRepository.findAllText(PageRequest.of(offset / limit, limit),  query);
        System.out.println("finded - "+allPosts.getTotalElements());
        countPosts = Math.toIntExact(allPosts.getTotalElements());

        for (Post post : allPosts) {
            postsList.add(addNewRespPosts(post));
            }

        postResponse.setCount(countPosts);
        postResponse.setPosts(postsList);
        return postResponse;
    }
//=================================================================================

    public CalendarResponse getCalendar() {
        CalendarResponse calendarResponse = new CalendarResponse();
        ArrayList<String> years = new ArrayList<>();
        HashMap<String, Integer> posts = new HashMap<>();
        List<Post> allPosts = postRepository.findAllByCalendar();

        for (Post post : allPosts) {
            if ((post.getIsActive() == 1)
                    && (post.getModerationStatus().toString().equals("ACCEPTED"))
                    && (post.getTime().getTime() <= System.currentTimeMillis())) {
                Date date = post.getTime();
                String year = formaterYear.format(date);
                String datePost = formaterPostDate.format(date);
                if (!years.contains(year)) {
                    years.add(year);
                }
                if (!posts.containsKey(datePost)) {
                    posts.put(datePost, 1);
                } else {
                    posts.replace(datePost, (posts.get(datePost) + 1));
                }
            }
        }

        calendarResponse.setYears(years);
        calendarResponse.setPosts(posts);
        return calendarResponse;
    }

    //=================================================================================
    public PostResponse getPostSearchByDate(int offset, int limit, String date) {
        PostResponse postResponse = new PostResponse();

        Page<Post> allPosts = postRepository.findPostsByDate(PageRequest.of(offset / limit, limit), date);
        ArrayList<PostDto> postsList = new ArrayList<>();

        for (Post allPost : allPosts) {
            PostDto newRespPost = addNewRespPosts(allPost);
            Date dateFromList = new Date(allPost.getTime().getTime());
            String dateFromListString = formaterPostDate.format(dateFromList);

            if (!postsList.contains(newRespPost) && (dateFromListString.equals(date))) {
                postsList.add(newRespPost);
            }
        }
        if (postsList.isEmpty()) {
            postResponse.setCount(0);
            postResponse.setPosts(postsList);
            return postResponse;
        }
        postResponse.setCount(postsList.size());
        postResponse.setPosts(postsList);
        return postResponse;
    }

    //=================================================================================
    private PostDto addNewRespPosts(Post post) {
        List<PostVotes> postVotes = postVotesRepository.findAll();
        List<PostComment> postComments = postCommentRepository.findAll();
        PostDto postDto = new PostDto();
        int likes = 0;
        int dislikes = 0;
        int postCommentCount = 0;

        if ((post.getIsActive() == 1)
                && (post.getModerationStatus().toString().equals("ACCEPTED"))
                && (post.getTime().getTime() <= System.currentTimeMillis())) {
//          posts: id
            postDto.setId(post.getId());
//          posts: timestamp
            postDto.setTimestamp(post.getTime().getTime() / 1000);
//          posts: user
            UserDto userDto = new UserDto();
            userDto.setId((long) post.getUser().getId());
            userDto.setName(post.getUser().getName());
            postDto.setUser(userDto);
//          posts: title
            postDto.setTitle(post.getTitle());
//          posts: announce
            if (post.getText().length() > 150) {
                String temp = (post.getText()).substring(0, 150);
                postDto.setAnnounce((temp.substring(0, temp.lastIndexOf(" ")) + "..."));
            } else {
                postDto.setAnnounce(((post.getText()).substring(0, post.getText().length()) + "..."));
            }
            for (PostVotes postVote : postVotes) {
                if (post.getId().equals(postVote.getPost().getId())) {
                    if (postVote.getValue() == -1) {
                        dislikes = dislikes + 1;
                    } else if (postVote.getValue() == 1) {
                        likes = likes + 1;
                    }
                }
            }
//          posts: likeCount
            postDto.setLikeCount(likes);
//          posts: dislikeCount
            postDto.setDislikeCount(dislikes);
//          posts: commentCount
            for (PostComment postComment : postComments) {
                if (postComment.getUser().getId() == post.getId()) {
                    postCommentCount = postCommentCount + 1;
                }
            }
            postDto.setCommentCount(postCommentCount);

//          posts: viewCount
            postDto.setViewCount(post.getViewCount());


        }
        return postDto;
    }

}
