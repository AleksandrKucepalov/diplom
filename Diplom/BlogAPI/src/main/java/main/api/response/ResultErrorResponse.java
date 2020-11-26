package main.api.response;

import lombok.Data;
import main.api.response.view.ErrorForResultError;

@Data
public class ResultErrorResponse {
    private boolean result;
    private ErrorForResultError errors;

}
