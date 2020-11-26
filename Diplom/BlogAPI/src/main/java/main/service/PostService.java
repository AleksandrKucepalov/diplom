package main.service;

import main.api.response.PostResponse;
import main.api.response.SettingsResponse;
import main.api.response.view.PostForPostResponse;
import main.api.response.view.UserForPost;
import main.model.Post;
import main.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class PostService{
    @Autowired
    private PostRepository postRepository;

    public PostResponse getPost(int offset, int limit, String mode) {
        int countPost = postRepository.getCount();
        int page = offset/limit ;
        Pageable sorted;

        switch (mode) {
            case ("recent"):
                sorted = PageRequest.of(page, limit, Sort.by("time").descending());
                break;
            case ("popular"):
                //countComment
                sorted = PageRequest.of(page, limit, Sort.by("viewCount").descending());
                break;
            case ("best"):
                //likeCount;
                sorted = PageRequest.of(page, limit, Sort.by("viewCount").ascending());
                break;
            default:
                sorted = PageRequest.of(page, limit, Sort.by("time").ascending());
                break;
        }

        //получаем все посту по условиям
        Iterable<Post> iterable = postRepository.findByPost(sorted);

        ArrayList<PostForPostResponse> jsonArrayPosts = new ArrayList<>();
        //
        for (Post post : iterable) {
            PostForPostResponse jsonPost = new PostForPostResponse();
            jsonPost.setId(post.getId());
            jsonPost.setTimestamp((long) post.getTime().getTime() / 1000);
            //получаем автора поста
            UserForPost jsonUser = new UserForPost();
            jsonUser.setId(post.getUserAuthor().getId());
            jsonUser.setName(post.getUserAuthor().getName());

            jsonPost.setUser(jsonUser);
            jsonPost.setTitle(post.getTitle());
            jsonPost.setAnnounce(post.getText() + post.getId());
            jsonPost.setLikeCount(post.getCountLike());
            jsonPost.setDislikeCount(post.getCountDisLike());
            jsonPost.setCommentCount(post.getPostCommentList().size());
            jsonPost.setViewCount(post.getViewCount());
            //добавляем обьек в массив
            jsonArrayPosts.add(jsonPost);
        }

        //основной json для ответа
        PostResponse postResponse = new PostResponse();
        postResponse.setCount(countPost);
        postResponse.setPosts(jsonArrayPosts);

        return postResponse;
    }

    /*
    public int ComparatorJSONObject(main.api.response.Post a, main.api.response.Post b, String KEY_MODE, String type) {
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
    }*/
}
