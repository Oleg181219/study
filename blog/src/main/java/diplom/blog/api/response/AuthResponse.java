package diplom.blog.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthResponse {

    private Boolean result;

    private HashMap<String, String> errors;

    public AuthResponse(Boolean result, HashMap<String, String> errors) {
        this.result = result;
        this.errors = errors;
    }

    public AuthResponse() {
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public HashMap<String, String> getErrors() {
        return errors;
    }

    public void setErrors(HashMap<String, String> errors) {
        this.errors = errors;
    }
}
