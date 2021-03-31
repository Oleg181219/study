package diplom.blog.api.response;

import diplom.blog.response_model.RespTags;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class TagResponse {

    private ArrayList<RespTags> tags;

    public ArrayList<RespTags> getTags() {
        return tags;
    }

    public void setTags(ArrayList<RespTags> tags) {
        this.tags = tags;
    }
}
