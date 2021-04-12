package diplom.blog.repo;

import diplom.blog.model.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagsRepository extends JpaRepository<Tags, Long> {
    @Query(
            "SELECT t " +
                    "FROM Tags t " +
                    "LEFT JOIN TagToPost tp ON tp.tagId = t.id " +
                    "LEFT JOIN Post p ON p.id = tp.postId " +
                    "WHERE tp.id > 0"
    )
    List<Tags> findAll();
}
