package diplom.blog.service;

import diplom.blog.api.response.SettingsResponse;
import org.springframework.stereotype.Service;

@Service
public class SettingsService {

    public SettingsResponse getGlobalSettings(){
        SettingsResponse settingsResponse = new SettingsResponse();
        settingsResponse.setMultyuserMode(true);
        settingsResponse.setPost_Premoderation(true);
        return settingsResponse;
    }

}
