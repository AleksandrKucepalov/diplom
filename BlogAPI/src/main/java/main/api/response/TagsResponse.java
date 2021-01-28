package main.api.response;

import lombok.Data;
import main.api.response.view.TagForTagsResponse;

import java.util.ArrayList;

@Data
public class TagsResponse {

    private ArrayList<TagForTagsResponse> tags;
}
