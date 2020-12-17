package main.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import main.api.response.view.UserForResponse;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class _ResultLoginResponse {
    private boolean result;
    private UserForResponse user;
}
