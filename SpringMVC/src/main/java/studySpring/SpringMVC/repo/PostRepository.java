package studySpring.SpringMVC.repo;
import org.springframework.data.repository.CrudRepository;
import studySpring.SpringMVC.models.Post;

public interface PostRepository extends CrudRepository<Post, Long>{

}
