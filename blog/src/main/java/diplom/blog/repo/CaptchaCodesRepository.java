package diplom.blog.repo;

import diplom.blog.model.CaptchaCodes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaptchaCodesRepository extends JpaRepository<CaptchaCodes, Integer> {
}
