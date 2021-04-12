package diplom.blog.api.response;

import diplom.blog.model.DtoModel.PostDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PostResponse {

    private int count;

    private ArrayList<PostDto> posts;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<PostDto> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<PostDto> posts) {
        this.posts = posts;
    }
}
