package main.api.response;

import lombok.Data;
import main.api.response.view.PostForPostResponse;

import java.util.ArrayList;

@Data
public class PostResponse {

    private int count;
    private ArrayList<PostForPostResponse> posts = new ArrayList<>();
}
