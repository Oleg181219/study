package diplom.blog.repo;

import diplom.blog.model.PostComment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostCommentRepository extends CrudRepository<PostComment, Long> {
    List<PostComment> findAllByIdAfter(Long id);
}
