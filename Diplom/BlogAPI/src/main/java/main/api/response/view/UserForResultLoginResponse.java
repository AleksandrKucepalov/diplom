package main.api.response.view;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class UserForResultLoginResponse {

    private long id;
    private String  name;
    private String photo;
    private String email;
    private boolean moderation;
    private int moderationCount;
    private boolean settings ;

}
