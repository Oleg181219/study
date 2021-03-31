package diplom.blog.repo;

import diplom.blog.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Integer> {
    List<User> findAll();
}
