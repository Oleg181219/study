package diplom.blog.repo;
import diplom.blog.model.TagToPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TagToPostRepository extends JpaRepository<TagToPost, Integer> {
    List<TagToPost> findAll();
}
