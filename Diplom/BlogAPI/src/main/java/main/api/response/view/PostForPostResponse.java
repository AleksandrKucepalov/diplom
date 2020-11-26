package main.api.response.view;

import lombok.Data;

@Data
public class PostForPostResponse {

    private long id;
    private long timestamp;
    private UserForPost user;
    private String title;
    private String announce;
    private int likeCount;
    private int dislikeCount;
    private int commentCount;
    private int viewCount;
}
