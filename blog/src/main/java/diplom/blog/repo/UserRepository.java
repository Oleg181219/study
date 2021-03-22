package diplom.blog.repo;

import diplom.blog.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
}
