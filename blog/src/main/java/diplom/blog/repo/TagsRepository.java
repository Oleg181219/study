package diplom.blog.repo;
import diplom.blog.model.Tags;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TagsRepository extends CrudRepository<Tags, Long> {
    List<Tags> findAll();
}
