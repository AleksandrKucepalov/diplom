package main.api.request;

import lombok.Data;
import main.api.response.view.UserForPost;

import java.util.ArrayList;

@Data
public class PostRequest {
    private long timestamp;
    private boolean active;
    private String title;
    private String text;
    private ArrayList<String> tags = new ArrayList<>();
}
