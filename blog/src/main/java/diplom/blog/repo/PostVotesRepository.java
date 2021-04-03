package diplom.blog.repo;

import diplom.blog.model.PostVotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostVotesRepository extends JpaRepository<PostVotes,Long> {
    List<PostVotes> findAll();
}
