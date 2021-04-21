package diplom.blog.repo;

import diplom.blog.model.CaptchaCodes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface CaptchaCodesRepository extends JpaRepository<CaptchaCodes, Integer> {

    @Modifying
    void deleteAllByTimeBefore(Date date);

    CaptchaCodes findBySecretCode(String secretCode);

}
