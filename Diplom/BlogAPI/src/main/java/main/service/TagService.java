package main.service;

import main.api.response.TagsResponse;
import main.api.response.view.TagForTagsResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TagService {
    public TagsResponse getTags(){

        TagsResponse jsonTag = new TagsResponse();
        ArrayList<TagForTagsResponse> jsonArrayTags = new ArrayList<>();

        TagForTagsResponse jsontag1 = new TagForTagsResponse();
        jsontag1.setName("PHP");
        jsontag1.setWeight(1.0);
        jsonArrayTags.add(jsontag1);

        TagForTagsResponse jsontag2 = new TagForTagsResponse();
        jsontag2.setName("Python");
        jsontag2.setWeight(0.33);
        jsonArrayTags.add(jsontag2);

        jsonTag.setTags(jsonArrayTags);

        return jsonTag;

    }
}
