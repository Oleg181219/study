package diplom.blog.repo;
import diplom.blog.model.TagToPost;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TagToPostRepository extends CrudRepository<TagToPost, Integer> {
    List<TagToPost> findAll();
}
