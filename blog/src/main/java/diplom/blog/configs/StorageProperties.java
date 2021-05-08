package diplom.blog.configs;

import org.springframework.stereotype.Component;

@Component
public class StorageProperties {

    private String location = "src/main/java/diplom/blog/upload";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
