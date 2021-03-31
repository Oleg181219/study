package diplom.blog.service;

import diplom.blog.api.response.SettingsResponse;
import diplom.blog.model.GlobalSettings;
import diplom.blog.repo.GlobalSettingsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Service
public class SettingsService {
    List<GlobalSettings> multiuserMode;
    List<GlobalSettings> postPremoderation;
    List<GlobalSettings> statisticsIsPublic;

    @Bean
    public CommandLineRunner getGlobalSettings(GlobalSettingsRepository repository){
        return (args) -> {
            multiuserMode = repository.findByCode("MULTIUSER_MODE");
            postPremoderation = repository.findByCode("POST_PREMODERATION");
            statisticsIsPublic = repository.findByCode("STATISTICS_IS_PUBLIC");

        };
    }

    public SettingsResponse getGlobalSettings() {
        SettingsResponse settingsResponse = new SettingsResponse();

        if(multiuserMode.get(0).getValue().equals("YES")){
            settingsResponse.setMultyuserMode(true);
        } else if (multiuserMode.get(0).getValue().equals("NO")){
            settingsResponse.setMultyuserMode(false);
        }

        if(postPremoderation.get(0).getValue().equals("YES")){
            settingsResponse.setPostPremoderation(true);
        } else if (postPremoderation.get(0).getValue().equals("NO")){
            settingsResponse.setPostPremoderation(false);
        }

        if(statisticsIsPublic.get(0).getValue().equals("YES")){
            settingsResponse.setStatisticsIsPublic(true);
        } else if (statisticsIsPublic.get(0).getValue().equals("NO")){
            settingsResponse.setStatisticsIsPublic(false);
        }

        return settingsResponse;
    }

}
