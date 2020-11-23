package main.controller;

import main.api.response.InitResponse;
import main.model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Comparator;
import java.util.Date;

@RestController
public class ApiGeneralController {

    @Autowired
    private PostRepository postRepository;



    private final InitResponse initResponse;

    public ApiGeneralController(InitResponse initResponse) {
        this.initResponse = initResponse;
    }


    @GetMapping("/api/init")
    public ResponseEntity<InitResponse> init() {
/*
        JSONObject jsonInit = new JSONObject();
        jsonInit.put("title", "DevPub");
        jsonInit.put("subtitle", "Рассказы разработчиков");
        jsonInit.put("phone", "+7 952 863-28-62");
        jsonInit.put("email", "a-l-e-x-007@yandex.ru");
        jsonInit.put("copyright", "Куцепалов Александр");
        jsonInit.put("copyrightFrom", "2020");
 */
        return new ResponseEntity<>(initResponse, HttpStatus.OK);
    }

    @GetMapping("/api/post")
    public ResponseEntity<JSONObject> post(@RequestParam(value = "offset",required = false, defaultValue = "0") int offset,
                                           @RequestParam(value = "limit",required = false, defaultValue = "10") int limit,
                                           @RequestParam(value = "mode",required = false, defaultValue = "recent") String mode) {

        //получаем все посту по условиям
        Iterable<Post> iterable = postRepository.findPostWithActiveAndStatusACCEPTED(new Date());

        JSONArray jsonArrayPosts = new JSONArray();
        int countPost = 0;
        for (Post post : iterable) {
            JSONObject jsonPost = new JSONObject();
            jsonPost.put("id", post.getId());
            jsonPost.put("timestamp", (long) post.getTime().getTime() / 1000 - (int) (Math.random() * 1000));
            //получаем автора поста
            JSONObject jsonUser = new JSONObject();
            jsonUser.put("id", post.getUserAuthor().getId());
            jsonUser.put("name", post.getUserAuthor().getName());

            jsonPost.put("user", jsonUser);
            jsonPost.put("title", post.getTitle());
            jsonPost.put("announce", post.getText() + post.getId());
            jsonPost.put("likeCount", post.getCountLike());
            jsonPost.put("dislikeCount", post.getCountDisLike());
            jsonPost.put("commentCount", post.getPostCommentList().size());
            jsonPost.put("viewCount", post.getViewCount());
            //добавляем обьек в массив
            jsonArrayPosts.add(jsonPost);
            countPost++;
        }

        //сортируем в зависимости от параметра mode
        //как вынести компоратор?
        //почему то если я обьявляю Long, то не работает сортировка по коментариям и лайкам(почему, не пойму)
        //если обьявляю Integer, то посты не сортируются(тут понятно почему)
        //поэтому компоратор сделал для каждого параметра
        String KEY_MODE;
        switch (mode) {
            case ("recent"):
                KEY_MODE = "timestamp";
                jsonArrayPosts.sort((Comparator<JSONObject>) (a, b) -> ComparatorJSONObject(a, b, KEY_MODE, "Long"));
                break;
            case ("popular"):
                KEY_MODE = "commentCount";
                jsonArrayPosts.sort((Comparator<JSONObject>) (a, b) -> ComparatorJSONObject(a, b, KEY_MODE, "Integer"));
                break;
            case ("best"):
                KEY_MODE = "likeCount";
                jsonArrayPosts.sort((Comparator<JSONObject>) (a, b) -> ComparatorJSONObject(a, b, KEY_MODE, "Integer"));
                break;
            default:
                KEY_MODE = "timestamp";
                jsonArrayPosts.sort((Comparator<JSONObject>) (a, b) -> ComparatorJSONObject(a, b, KEY_MODE, "LongDesc"));
                break;
        }

        //массив для показа
        JSONArray jsonArrayPostsPokaz = new JSONArray();
        if (jsonArrayPosts != null) {
            int lenArray = jsonArrayPosts.size();
            int len = 0;
            if (offset + limit > lenArray) {
                len = lenArray;
            } else {
                len = offset + limit;
            }
            for (int i = offset; i < len; i++) {
                jsonArrayPostsPokaz.add(jsonArrayPosts.get(i));
            }
        }

        //основной json для ответа
        JSONObject jsonPostsAnswer = new JSONObject();
        jsonPostsAnswer.put("count", countPost);
        jsonPostsAnswer.put("posts", jsonArrayPostsPokaz);

        return new ResponseEntity<>(jsonPostsAnswer, HttpStatus.OK);
    }

    @GetMapping("/api/tag")
    public ResponseEntity<JSONObject> tag() {

        JSONObject jsonTag = new JSONObject();
        JSONArray jsonArrayTags = new JSONArray();

        JSONObject jsontag1 = new JSONObject();
        jsontag1.put("name", "PHP");
        jsontag1.put("weight", 1);
        jsonArrayTags.add(jsontag1);

        JSONObject jsontag2 = new JSONObject();
        jsontag2.put("name", "Python");
        jsontag2.put("weight", 0.33);
        jsonArrayTags.add(jsontag2);

        jsonTag.put("tags", jsonArrayTags);

        return new ResponseEntity<>(jsonTag, HttpStatus.OK);
    }

    public int ComparatorJSONObject(JSONObject a, JSONObject b, String KEY_MODE, String type) {
        String KEY_NAME = KEY_MODE;
        if (type.equals("Integer")) {
            Integer valA = (Integer) a.get(KEY_NAME);
            Integer valB = (Integer) b.get(KEY_NAME);
            return (valB.compareTo(valA));
        } else if (type.equals("IntegerDesc")) {
            Integer valA = (Integer) a.get(KEY_NAME);
            Integer valB = (Integer) b.get(KEY_NAME);
            return -(valB.compareTo(valA));
        } else if (type.equals("LongDesc")) {
            Long valA = (Long) a.get(KEY_NAME);
            Long valB = (Long) b.get(KEY_NAME);
            return -(valB.compareTo(valA));
        } else {
            Long valA = (Long) a.get(KEY_NAME);
            Long valB = (Long) b.get(KEY_NAME);
            return (valB.compareTo(valA));
        }
    }
}
