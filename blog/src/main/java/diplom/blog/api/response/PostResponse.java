package diplom.blog.api.response;

import diplom.blog.model.DtoModel.PostDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PostResponse {

    private int count;

    private ArrayList<PostDTO> posts;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<PostDTO> posts) {
        this.posts = posts;
    }
}
