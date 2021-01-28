package main.api.response;

import lombok.Data;
import main.api.response.view.CommentForPostId;
import main.api.response.view.UserForResponse;

import java.util.ArrayList;

@Data
public class PostIdResponse {

    private long id;
    private long timestamp;
    private boolean active;
    private UserForResponse user;
    private String title;
    private String text;
    private int likeCount;
    private int dislikeCount;
    private int viewCount;
    private ArrayList<CommentForPostId> comments = new ArrayList<>();
    private ArrayList<String> tags = new ArrayList<>();
}
