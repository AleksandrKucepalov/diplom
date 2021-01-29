package main.api.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MyRequest {
    private  String name;
    private String email;
    private String password;
    private MultipartFile photo;
    private Integer removePhoto;
}
