package diplom.blog.api.response;

import diplom.blog.model.DtoModel.TagDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class TagResponse {

    private ArrayList<TagDto> tags;

    public ArrayList<TagDto> getTags() {
        return tags;
    }

    public void setTags(ArrayList<TagDto> tags) {
        this.tags = tags;
    }
}
