package diplom.blog.repo;

import diplom.blog.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


    @Query("SELECT p " +
            "FROM Post p " +
            "LEFT JOIN User u ON u.id = p.user.id " +
            "LEFT JOIN PostComment pc ON p.id = pc.post.id " +
            "LEFT JOIN PostVotes pvl ON (p.id = pc.post.id and pvl.value = 1) " +
            "WHERE (p.isActive = 1 AND p.moderationStatus = 'ACCEPTED' AND p.time <= CURRENT_TIME)" +
            "GROUP BY p.id ORDER BY p.time desc ")
    Page<Post> findPostsOrderByTimeDesc(Pageable pageable);

    @Query("SELECT p " +
            "FROM Post p " +
            "LEFT JOIN User u ON u.id = p.user.id " +
            "LEFT JOIN PostComment pc ON p.id = pc.post.id " +
            "LEFT JOIN PostVotes pvl ON p.id = pc.post.id  " +
            "WHERE (p.isActive = 1 AND p.moderationStatus = 'ACCEPTED' AND p.time <= CURRENT_TIME)" +
            "GROUP BY p.id ORDER BY p.postComments.size desc ")
    Page<Post> findPostsOrderByPostComments(Pageable pageable);

    @Query("SELECT p " +
            "FROM Post p " +
            "LEFT JOIN User u ON u.id = p.user.id " +
            "LEFT JOIN PostComment pc ON p.id = pc.post.id " +
            "LEFT JOIN PostVotes pvl ON (p.id = pc.post.id and pvl.value = 1) " +
            "WHERE (p.isActive = 1 AND p.moderationStatus = 'ACCEPTED' AND p.time <= CURRENT_TIME)" +
            "GROUP BY p.id ORDER BY p.time  ")
    Page<Post> findPostsOrderByTimeIincrease(Pageable pageable);

    @Query("SELECT p " +
            "FROM Post p " +
            "LEFT JOIN User u ON u.id = p.user.id " +
            "LEFT JOIN PostComment pc ON p.id = pc.post.id " +
            "LEFT JOIN PostVotes pvl ON (p.id = pc.post.id and pvl.value = 1) " +
            "WHERE (p.isActive = 1 AND p.moderationStatus = 'ACCEPTED' AND p.time <= CURRENT_TIME)" +
            "GROUP BY p.id ORDER BY p.postVotes.size desc ")
    Page<Post> findPostsOrderByLikeCount(Pageable pageable);
}
