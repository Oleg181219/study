package diplom.blog.repo;
import diplom.blog.model.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TagsRepository extends JpaRepository<Tags, Long> {
    List<Tags> findAll();
}
