package main.api.response;

import lombok.Data;
import main.api.response.view.ErrorForResultError;
import main.api.response.view.ErrorForResultPasswordResponse;

@Data
public class ResultPasswordResponse {
    private boolean result;
    private ErrorForResultPasswordResponse errors;
}
