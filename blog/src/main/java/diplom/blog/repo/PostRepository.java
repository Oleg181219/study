package diplom.blog.repo;

import diplom.blog.model.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface PostRepository extends CrudRepository<Post,Long> {
    List<Post> findAllByIdAfter(Long id);
}
