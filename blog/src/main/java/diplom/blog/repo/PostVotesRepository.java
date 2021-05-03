package diplom.blog.repo;

import diplom.blog.model.PostVotes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostVotesRepository extends JpaRepository<PostVotes,Long> {
    List<PostVotes> findAll();
}
