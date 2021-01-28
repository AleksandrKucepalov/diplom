package main.service;


import main.api.response.TagsResponse;
import main.api.response.view.TagForTagsResponse;
import main.model.Tag;
import main.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public TagsResponse getTags(String query) {

        List<Tag> tagList = tagRepository.findByQuery(query);
        TagsResponse jsonTag = new TagsResponse();

        if (tagList.size() > 0) {

            Map<String, Integer> countTagsMap = new HashMap<>();
            for (Tag tag : tagList) {
                String nameTag = tag.getName();
                int count = tagRepository.countTag(nameTag);
                countTagsMap.put(nameTag, count);
            }
            //int sumCount = 0;
            int maxCount = -1;
            Set<String> setTags = countTagsMap.keySet();
            for (String tag : setTags) {
                // sumCount+= countTags.get(tag);
                if (countTagsMap.get(tag) > maxCount) {
                    maxCount = countTagsMap.get(tag);
                }
            }

            ArrayList<TagForTagsResponse> jsonArrayTags = new ArrayList<>();
            for (String tag : setTags) {
                TagForTagsResponse jsontag = new TagForTagsResponse();
                jsontag.setName(tag);
                jsontag.setWeight((double) countTagsMap.get(tag) / maxCount);
                jsonArrayTags.add(jsontag);
            }
            jsonTag.setTags(jsonArrayTags);
        }
        return jsonTag;
    }
}
