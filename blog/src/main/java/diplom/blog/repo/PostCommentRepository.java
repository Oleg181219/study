package diplom.blog.repo;

import diplom.blog.model.PostComment;
import org.springframework.data.repository.CrudRepository;

public interface PostCommentRepository extends CrudRepository<PostComment, Long> {
}
