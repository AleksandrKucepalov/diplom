package main.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CommentRequest {
    @JsonProperty("parent_id")
    private Long parentId;
    @JsonProperty("post_id")
    private long postId;
    private String text;
}
