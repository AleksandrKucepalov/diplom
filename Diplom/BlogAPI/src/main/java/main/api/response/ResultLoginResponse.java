package main.api.response;

import lombok.Data;
import main.api.response.view.UserForResultLoginResponse;

@Data
public class ResultLoginResponse {
    private boolean result;
    private UserForResultLoginResponse user;
}
