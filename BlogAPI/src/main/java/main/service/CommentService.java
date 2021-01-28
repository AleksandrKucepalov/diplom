package main.service;


import main.api.request.CommentRequest;
import main.api.response.ResultResponse;
import main.model.Post;
import main.model.PostComment;
import main.repository.PostCommentRepository;
import main.repository.PostRepository;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CommentService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostCommentRepository postCommentRepository;

    public ResponseEntity addComment(CommentRequest commentRequest, String email) {

        ResultResponse resultResponse = new ResultResponse();
        if (commentRequest.getText().length() < 3) {
            Map<String, Object> errors = new HashMap<>();
            errors.put("text", "Текст комментария не задан или слишком короткий.");
            resultResponse.setErrors(errors);
            return new ResponseEntity<>(resultResponse, HttpStatus.OK);
        } else {
            try {
                main.model.User userTek = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user " + email + " not found"));
                Post post = postRepository.findById(commentRequest.getPostId());
                if (post == null) {
                    new UsernameNotFoundException("post " + commentRequest.getPostId() + " not found");
                }
                PostComment newPostComment = new PostComment();
                if (commentRequest.getParentId() != null) {
                    PostComment postComment = postCommentRepository.findById(commentRequest.getParentId());
                    if (postComment == null) {
                        new UsernameNotFoundException("Comment " + commentRequest.getParentId() + " not found");
                    } else {
                        newPostComment.setPostComment(postComment);
                    }
                }
                newPostComment.setPost(post);
                newPostComment.setText(commentRequest.getText());
                newPostComment.setTime(new Date());
                newPostComment.setUserAuthor(userTek);

                postCommentRepository.save(newPostComment);

                resultResponse.setResult(true);
                return new ResponseEntity<>(resultResponse, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }
}
