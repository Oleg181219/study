package diplom.blog.api.response;

import org.springframework.stereotype.Component;

@Component
public class LogoutResponse {
    private Boolean result;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
