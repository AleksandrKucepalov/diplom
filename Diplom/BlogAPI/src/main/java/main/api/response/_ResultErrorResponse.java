package main.api.response;

import lombok.Data;
import main.api.response.view._ErrorForResultError;

@Data
public class _ResultErrorResponse {
    private boolean result;
    private _ErrorForResultError errors;

}
