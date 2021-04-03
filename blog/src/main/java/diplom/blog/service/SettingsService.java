package diplom.blog.service;

import diplom.blog.api.response.SettingsResponse;
import diplom.blog.model.GlobalSettings;
import diplom.blog.repo.GlobalSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingsService {
    private final GlobalSettingsRepository settingsRepository;

    SettingsResponse settingsResponse = new SettingsResponse();

    @Autowired
    public SettingsService(GlobalSettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    public SettingsResponse getGlobalSettings() {
        List<GlobalSettings> gs = settingsRepository.findAll();

        for (int i = 0; i < gs.size(); i++) {
                System.out.println(gs.get(i).getValue());
            }
            if (gs.get(0).getValue().equals("YES")) {
                settingsResponse.setMultyuserMode(true);
            } else if (gs.get(0).getValue().equals("NO")) {
                settingsResponse.setMultyuserMode(false);
            }
            if (gs.get(1).getValue().equals("YES")) {
                settingsResponse.setPostPremoderation(true);
            } else if (gs.get(1).getValue().equals("NO")) {
                settingsResponse.setPostPremoderation(false);
            }
            if (gs.get(2).getValue().equals("YES")) {
                settingsResponse.setStatisticsIsPublic(true);
            } else if (gs.get(2).getValue().equals("NO")) {
                settingsResponse.setStatisticsIsPublic(false);
            }
        return settingsResponse;
    }
}
