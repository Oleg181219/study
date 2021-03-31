package diplom.blog.repo;

import diplom.blog.model.GlobalSettings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GlobalSettingsRepository extends CrudRepository<GlobalSettings, Long> {

    List<GlobalSettings> findByCode(String code);
}
