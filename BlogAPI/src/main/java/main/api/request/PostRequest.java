package main.api.request;

import lombok.Data;

import java.util.ArrayList;

@Data
public class PostRequest {
    private long timestamp;
    private int active;
    private String title;
    private String text;
    private ArrayList<String> tags = new ArrayList<>();

}
