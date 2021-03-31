package diplom.blog.repo;

import diplom.blog.model.PostVotes;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostVotesRepository extends CrudRepository<PostVotes,Long> {
    List<PostVotes> findAll();
}
