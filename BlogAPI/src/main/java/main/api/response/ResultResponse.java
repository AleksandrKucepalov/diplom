package main.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import main.api.response.view.UserForResponse;

import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResultResponse {

    private boolean result;
    private Map<String,Object> errors;
    private UserForResponse user;
}
