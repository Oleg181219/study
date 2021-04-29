package diplom.blog.service;

import diplom.blog.api.response.CalendarResponse;
import diplom.blog.api.response.PostResponse;
import diplom.blog.model.DtoModel.*;
import diplom.blog.model.Post;
import diplom.blog.repo.PostRepository;
import diplom.blog.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Component
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository
            , UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    SimpleDateFormat formaterPostDate = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat formaterYear = new SimpleDateFormat("yyyy");

    public PostResponse allPost(int offset, int limit, String mode) {
        ArrayList<PostDTO> postsList = new ArrayList<>();
        PostResponse postResponse = new PostResponse();
        Page<Post> allPosts;
        int countPosts = 0;
        switch (mode) {
            case "popular":
                allPosts = postRepository.findPostsOrderByPostComments(PageRequest.of(offset / limit, limit));
                countPosts = Math.toIntExact(allPosts.getTotalElements());
                for (Post allPost : allPosts) {

                    PostDTO newRespPost = createNewResponsePosts(allPost);
                    if (!postsList.contains(newRespPost)) {
                        postsList.add(newRespPost);
                    }
                }
                break;
            case "best":
                allPosts = postRepository.findPostsOrderByLikeCount(PageRequest.of(offset / limit, limit));
                countPosts = Math.toIntExact(allPosts.getTotalElements());
                for (Post allPost : allPosts) {

                    PostDTO newRespPost = createNewResponsePosts(allPost);
                    if (!postsList.contains(newRespPost)) {
                        postsList.add(newRespPost);
                    }
                }
                break;
            case "early":
                allPosts = postRepository.findPostsOrderByTimeIincrease(PageRequest.of(offset / limit, limit));
                countPosts = Math.toIntExact(allPosts.getTotalElements());
                for (Post allPost : allPosts) {

                    PostDTO newRespPost = createNewResponsePosts(allPost);
                    if (!postsList.contains(newRespPost)) {
                        postsList.add(newRespPost);
                    }
                }
                break;
            default:
                allPosts = postRepository.findPostsOrderByTimeDesc(PageRequest.of(offset / limit, limit));
                countPosts = Math.toIntExact(allPosts.getTotalElements());
                for (Post allPost : allPosts) {

                    PostDTO newRespPost = createNewResponsePosts(allPost);
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
    public PostResponse postsSearch(int offset, int limit, String query) {
        ArrayList<PostDTO> postsList = new ArrayList<>();
        PostResponse postResponse = new PostResponse();
        int countPosts = 0;

        Page<Post> allPosts = postRepository.findAllText(PageRequest.of(offset / limit, limit), query);
        countPosts = Math.toIntExact(allPosts.getTotalElements());

        for (Post post : allPosts) {
            postsList.add(createNewResponsePosts(post));
        }

        postResponse.setCount(countPosts);
        postResponse.setPosts(postsList);
        return postResponse;
    }
//=================================================================================

    public CalendarResponse calendar() {
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
    public PostResponse postsSearchByDate(int offset, int limit, String date) {
        PostResponse postResponse = new PostResponse();
        ArrayList<PostDTO> postsList = new ArrayList<>();
        Page<Post> allPosts = postRepository.findPostsByDate(PageRequest.of(offset / limit, limit), date);

        for (Post allPost : allPosts) {
            PostDTO newRespPost = createNewResponsePosts(allPost);
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
    public PostResponse postsSearchByTag(int offset, int limit, String tag) {
        ArrayList<PostDTO> postsList = new ArrayList<>();
        PostResponse postResponse = new PostResponse();
        int countPosts = 0;

        Page<Post> allPosts = postRepository.findPostByTag(PageRequest.of(offset / limit, limit), tag);
        countPosts = Math.toIntExact(allPosts.getTotalElements());

        for (Post post : allPosts) {
            postsList.add(createNewResponsePosts(post));
        }
        postResponse.setCount(countPosts);
        postResponse.setPosts(postsList);
        return postResponse;
    }

    //=================================================================================
    public PostByIdDTO findPostById(long id, String principalEmail)  {

        List<Post> post = postRepository.findById(id);
        if (post.isEmpty()){
           return new
        }
        PostByIdDTO postByIdDTO = new PostByIdDTO();
        UserDTO userDTO = new UserDTO();
        ArrayList<String> tagPostByIdDTO = new ArrayList<>();
        ArrayList<CommentDTO> comments = new ArrayList<>();

        postByIdDTO.setId(post.get(0).getId());// id
        postByIdDTO.setTimestamp(post.get(0).getTime().getTime() / 1000);//timestamp
        postByIdDTO.setActive(post.get(0).getIsActive() != 0);// isActive
        userDTO.setId((long) post.get(0).getUser().getId());//user id
        userDTO.setName(post.get(0).getUser().getName());//username
        postByIdDTO.setUser(userDTO);//user
        postByIdDTO.setTitle(post.get(0).getTitle());//title
        postByIdDTO.setText(post.get(0).getText());//text
        postByIdDTO.setLikeCount(post.get(0).getPostVotes().stream().filter(a -> a.getValue() == 1).count());//likes
        postByIdDTO.setDislikeCount(post.get(0).getPostVotes().stream().filter(a -> a.getValue() != 1).count());//dislikes
        int viewCount = post.get(0).getViewCount();
        if (principalEmail.equals("empty")) {
            viewCount++;
        } else {
            if (!principalEmail.equals(post.get(0).getUser().getEmail()) ||
                    userRepository.findByEmail(principalEmail).getIsModerator() != 1) {
                post.get(0).setViewCount(viewCount + 1);
                postRepository.save(post.get(0));
            }
        }
            postByIdDTO.setViewCount(viewCount);
        post.get(0).getPostComments().forEach(entry -> {//postComment start
                UserCommentDTO userCommentDTO = new UserCommentDTO();
                CommentDTO commentDTO = new CommentDTO();
                userCommentDTO.setId(entry.getUser().getId());
                userCommentDTO.setName(entry.getUser().getName());
                userCommentDTO.setPhoto(entry.getUser().getPhoto());
                commentDTO.setId(entry.getId());
                commentDTO.setTimestamp(entry.getTime().getTime() / 1000);
                commentDTO.setText(entry.getText());
                commentDTO.setUser(userCommentDTO);
                comments.add(commentDTO);
            });//postComment end
            postByIdDTO.setComments(comments);//PostComment
        post.get(0).getTags().forEach(entry -> tagPostByIdDTO.add(entry.getName()));
            postByIdDTO.setTags(tagPostByIdDTO);//tags
            return postByIdDTO;
        }

        //=================================================================================
        private PostDTO createNewResponsePosts(Post post){
            PostDTO postDto = new PostDTO();
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
            return postDto;
        }

    }
