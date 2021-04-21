package diplom.blog.repo;

import diplom.blog.model.Post;
import diplom.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findAll();


    List<User> findByEmail(String email);
}
