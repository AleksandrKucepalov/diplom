package main.api.response.view;

import lombok.Data;

@Data
public class CommentForPostId {
    private long id;
    private long timestamp;
    private String text;
    private UserForComment user;
}
