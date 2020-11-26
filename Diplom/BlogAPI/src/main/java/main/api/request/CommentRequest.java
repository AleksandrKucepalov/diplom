package main.api.request;

import lombok.Data;

@Data
public class CommentRequest {
    private long parent_id;
    private long post_id;
    private String text;
}
