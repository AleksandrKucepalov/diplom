package main.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LikeAndDislikeRequest {

    @JsonProperty("post_id")
    private long postId;
}
