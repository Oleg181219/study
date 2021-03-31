package diplom.blog.api.response;

import diplom.blog.response_model.RespPosts;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PostResponse {

    private int count;

    private ArrayList<RespPosts> posts;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<RespPosts> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<RespPosts> posts) {
        this.posts = posts;
    }
}
