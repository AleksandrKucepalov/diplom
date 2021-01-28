package main.api.response;

import lombok.Data;
import main.api.response.view._ErrorForResultPasswordResponse;

@Data
public class _ResultPasswordResponse {
    private boolean result;
    private _ErrorForResultPasswordResponse errors;
}
