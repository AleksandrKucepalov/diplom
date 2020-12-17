package main.api.response.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserForResponse {
    private  long id;
    private  String name;
    private  String photo;
    private String email;
    private boolean moderation;
    private int moderationCount;
    private boolean settings ;
}
