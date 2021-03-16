package diplom.blog.repo;

import diplom.blog.model.CaptchaCodes;
import org.springframework.data.repository.CrudRepository;

public interface CaptchaCodesRepository extends CrudRepository<CaptchaCodes, Integer> {
}
