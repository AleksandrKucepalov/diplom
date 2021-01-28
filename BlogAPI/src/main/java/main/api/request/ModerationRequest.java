package main.api.request;

import lombok.Data;

@Data
public class ModerationRequest {
    private long post_id;
    private String decision;
}
