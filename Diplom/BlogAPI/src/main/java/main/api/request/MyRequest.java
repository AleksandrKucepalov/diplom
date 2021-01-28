package main.api.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MyRequest {
    private  String name;
    private String email;
    private String password;
    private String photo;
    private int removePhoto;
}
