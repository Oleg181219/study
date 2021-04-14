package diplom.blog.service;

import diplom.blog.api.response.CalendarResponse;
import diplom.blog.api.response.PostResponse;
import diplom.blog.model.*;
import diplom.blog.model.DtoModel.*;
import diplom.blog.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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

    SimpleDateFormat formaterPostDate = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat formaterYear = new SimpleDateFormat("yyyy");

    //получение Post по формату прописанному в API
    public PostResponse getPost(int offset, int limit, String mode) {
        ArrayList<PostDTO> postsList = new ArrayList<>();
        PostResponse postResponse = new PostResponse();
        Page<Post> allPosts;
        int countPosts = 0;

        switch (mode) {
            case "popular":
                allPosts = postRepository.findPostsOrderByPostComments(PageRequest.of(offset / limit, limit));
                countPosts = Math.toIntExact(allPosts.getTotalElements());
                for (Post allPost : allPosts) {

                    PostDTO newRespPost = addNewRespPosts(allPost);
                    if (!postsList.contains(newRespPost)) {
                        postsList.add(newRespPost);
                    }
                }
                break;
            case "best":
                allPosts = postRepository.findPostsOrderByLikeCount(PageRequest.of(offset / limit, limit));
                countPosts = Math.toIntExact(allPosts.getTotalElements());
                for (Post allPost : allPosts) {

                    PostDTO newRespPost = addNewRespPosts(allPost);
                    if (!postsList.contains(newRespPost)) {
                        postsList.add(newRespPost);
                    }
                }
                break;
            case "early":
                allPosts = postRepository.findPostsOrderByTimeIincrease(PageRequest.of(offset / limit, limit));
                countPosts = Math.toIntExact(allPosts.getTotalElements());
                for (Post allPost : allPosts) {

                    PostDTO newRespPost = addNewRespPosts(allPost);
                    if (!postsList.contains(newRespPost)) {
                        postsList.add(newRespPost);
                    }
                }
                break;
            default:
                allPosts = postRepository.findPostsOrderByTimeDesc(PageRequest.of(offset / limit, limit));
                countPosts = Math.toIntExact(allPosts.getTotalElements());
                for (Post allPost : allPosts) {

                    PostDTO newRespPost = addNewRespPosts(allPost);
                    if (!postsList.contains(newRespPost)) {
                        postsList.add(newRespPost);
                    }
                }
                break;
        }
        postResponse.setCount(countPosts);
        postResponse.setPosts(postsList);
        return postResponse;
    }


    //=================================================================================
    public PostResponse getPostsSearch(int offset, int limit, String query) {
        ArrayList<PostDTO> postsList = new ArrayList<>();
        PostResponse postResponse = new PostResponse();
        int countPosts = 0;

        Page<Post> allPosts = postRepository.findAllText(PageRequest.of(offset / limit, limit), query);
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
        ArrayList<PostDTO> postsList = new ArrayList<>();
        Page<Post> allPosts = postRepository.findPostsByDate(PageRequest.of(offset / limit, limit), date);

        for (Post allPost : allPosts) {
            PostDTO newRespPost = addNewRespPosts(allPost);
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


    public PostResponse getPostSearchByTag(int offset, int limit, String tag) {
        ArrayList<PostDTO> postsList = new ArrayList<>();
        PostResponse postResponse = new PostResponse();
        int countPosts = 0;

        Page<Post> allPosts = postRepository.findPostByTag(PageRequest.of(offset / limit, limit), tag);
        countPosts = Math.toIntExact(allPosts.getTotalElements());

        for (Post post : allPosts) {
            postsList.add(addNewRespPosts(post));
        }
        postResponse.setCount(countPosts);
        postResponse.setPosts(postsList);
        return postResponse;
    }

    public PostByIdDTO findById(long id) {

        List<Post> posts = postRepository.findById(id);
        PostByIdDTO postByIdDTO = new PostByIdDTO();
        UserDTO userDTO = new UserDTO();
        ArrayList<String> tagPostByIdDTO = new ArrayList<>();
        ArrayList<CommentDTO> comments = new ArrayList<>();

        postByIdDTO.setId(posts.get(0).getId());
        postByIdDTO.setTimestamp(posts.get(0).getTime().getTime() / 1000);
        postByIdDTO.setActive(posts.get(0).getIsActive() != 0);

        userDTO.setId((long) posts.get(0).getUser().getId());
        userDTO.setName(posts.get(0).getUser().getName());

        postByIdDTO.setUser(userDTO);
        postByIdDTO.setTitle(posts.get(0).getTitle());
        postByIdDTO.setText(posts.get(0).getText());
        postByIdDTO.setLikeCount(posts.get(0).getPostVotes().stream().filter(a -> a.getValue() == 1).count());
        postByIdDTO.setDislikeCount(posts.get(0).getPostVotes().stream().filter(a -> a.getValue() != 1).count());
        postByIdDTO.setViewCount(posts.get(0).getViewCount());

        posts.get(0).getPostComments().forEach(entry -> {
            UserCommentDTO userCommentDTO = new UserCommentDTO();
            CommentDTO commentDTO = new CommentDTO();
//           create UserCommentDTO
            userCommentDTO.setId(entry.getUser().getId());
            userCommentDTO.setName(entry.getUser().getName());
            userCommentDTO.setPhoto(entry.getUser().getPhoto());
//==============================================================
            commentDTO.setId(entry.getId());
            commentDTO.setTimestamp(entry.getTime().getTime()/1000);
            commentDTO.setText(entry.getText());
            commentDTO.setUser(userCommentDTO);
            comments.add(commentDTO);
        });
        postByIdDTO.setComments(comments);

        posts.get(0).getTags().forEach(entry -> tagPostByIdDTO.add(entry.getName()));

        postByIdDTO.setTags(tagPostByIdDTO);
        return postByIdDTO;
    }

    //=================================================================================
    private PostDTO addNewRespPosts(Post post) {
        PostDTO postDto = new PostDTO();

        if ((post.getIsActive() == 1)
                && (post.getModerationStatus().toString().equals("ACCEPTED"))
                && (post.getTime().getTime() <= System.currentTimeMillis())) {
            postDto.setId(post.getId());
            postDto.setTimestamp(post.getTime().getTime() / 1000);
            UserDTO userDto = new UserDTO();
            userDto.setId((long) post.getUser().getId());
            userDto.setName(post.getUser().getName());
            postDto.setUser(userDto);
            postDto.setTitle(post.getTitle());

            if (post.getText().length() > 150) {
                String temp = (post.getText()).substring(0, 150);
                postDto.setAnnounce((temp.substring(0, temp.lastIndexOf(" ")) + "..."));
            } else {
                postDto.setAnnounce(((post.getText()) + "..."));
            }

            postDto.setLikeCount(post.getPostVotes().stream().filter(a -> a.getValue() == 1).count());
            postDto.setDislikeCount(post.getPostVotes().stream().filter(a -> a.getValue() != 1).count());
            postDto.setCommentCount(post.getPostComments().size());
            postDto.setViewCount(post.getViewCount());
        }
        return postDto;
    }

}
