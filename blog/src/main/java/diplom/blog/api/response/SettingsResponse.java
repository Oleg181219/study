package diplom.blog.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SettingsResponse {

    @JsonProperty("MULTIUSER_MODE")
    private boolean multyuserMode;

    @JsonProperty("POST_PREMODERATION")
    private boolean post_Premoderation;

    @JsonProperty("STATISTICS_IS_PUBLIC")
    private boolean statisticsIsPublic;






    public boolean isMultyuserMode() {
        return multyuserMode;
    }

    public void setMultyuserMode(boolean multyuserMode) {
        this.multyuserMode = multyuserMode;
    }

    public boolean isPost_Premoderation() {
        return post_Premoderation;
    }

    public void setPost_Premoderation(boolean post_Premoderation) {
        this.post_Premoderation = post_Premoderation;
    }

    public boolean isStatisticsIsPublic() {
        return statisticsIsPublic;
    }

    public void setStatisticsIsPublic(boolean statisticsIsPublic) {
        this.statisticsIsPublic = statisticsIsPublic;
    }
}

