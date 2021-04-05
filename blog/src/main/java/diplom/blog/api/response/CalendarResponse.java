package diplom.blog.api.response;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@Component
public class CalendarResponse {

    ArrayList<String> years;

    HashMap<String, Integer> posts;

    public ArrayList<String> getYears() {
        return years;
    }

    public void setYears(ArrayList<String> years) {
        this.years = years;
    }

    public HashMap<String, Integer> getPosts() {
        return posts;
    }

    public void setPosts(HashMap<String, Integer> posts) {
        this.posts = posts;
    }
}
