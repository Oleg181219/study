package diplom.blog.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewPostResponse {

    private boolean result;

    private Map<String, String> errors;

    public NewPostResponse(boolean result, Map<String, String> errors) {
        this.result = result;
        this.errors = errors;
    }

    public NewPostResponse() {
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
