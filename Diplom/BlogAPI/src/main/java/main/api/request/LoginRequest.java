package main.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {

    @JsonProperty("e_mail")
    private String email;
    private String password;
}
