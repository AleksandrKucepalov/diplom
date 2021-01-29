package main.controller;



import main.api.request.*;
import main.api.response.ResultResponse;
import main.service.AuthService;
import main.service.CommentService;
import main.service.PostService;
import main.service.VoteService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiPostController {

    private final VoteService voteService;
    private final PostService postService;
    private final CommentService commentService;
    private final AuthService authService;

    public ApiPostController(VoteService voteService, PostService postService, CommentService commentService, AuthService authService) {

        this.voteService = voteService;
        this.postService = postService;
        this.commentService = commentService;
        this.authService = authService;
    }

    //9
    @PostMapping("/post")
    public ResponseEntity postAdd(Principal principal, @RequestBody PostRequest postRequest) {

        if (principal == null) {
            return new ResponseEntity<>(new ResultResponse(), HttpStatus.OK);
        } else {
            String email = principal.getName();
            return new ResponseEntity<>(postService.addPostId(email, postRequest), HttpStatus.OK);
        }
    }


    //10
    @PostMapping("/image")
    public ResponseEntity image(@RequestParam("image") MultipartFile file)   {

        try {
           String path = postService.addImage(file);
            return new ResponseEntity<>(path, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            ResultResponse response = new ResultResponse();
            Map<String,Object> errors = new HashMap<>();
            errors.put("image","Размер файла больше 5 МБ");
            response.setErrors(errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }


    }



    //12
    @PostMapping("/comment")
    public ResponseEntity comment(Principal principal, @RequestBody CommentRequest commentRequest) {

        if (principal == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            String email = principal.getName();
            return commentService.addComment(commentRequest, email);
        }
    }

    //14
    @PostMapping("/moderation")
    // @PreAuthorize("hasAuthority('user:moderate','user:write')")
    public ResponseEntity moderation(Principal principal, @RequestBody ModerationRequest moderationRequest) {

        if (principal == null) {
            return new ResponseEntity<>(new ResultResponse(), HttpStatus.OK);
        } else {
            String email = principal.getName();
            return new ResponseEntity<>(postService.addModeratePost(email, moderationRequest), HttpStatus.OK);
        }
    }

    //20
    @PostMapping("/profile/my")
    public ResponseEntity profileMy(Principal principal, @RequestBody MyRequest myRequest) {
        if (principal == null) {
            return new ResponseEntity<>(new ResultResponse(), HttpStatus.OK);
        } else {
            String email = principal.getName();
            return new ResponseEntity<>(authService.getMyResponse( myRequest,email), HttpStatus.OK);
        }
    }

    //24
    @PostMapping("/post/like")
    public ResponseEntity like(Principal principal, @RequestBody VoteRequest likeRequest) {
        if (principal == null) {
            return new ResponseEntity<>(new ResultResponse(), HttpStatus.OK);
        } else {
            String email = principal.getName();
            return new ResponseEntity<>(voteService.setVote(likeRequest.getPostId(), 1, email), HttpStatus.OK);
        }
    }


    //25
    @PostMapping("/post/dislike")
    public ResponseEntity dislike(Principal principal, @RequestBody VoteRequest dislikeRequest) {
        if (principal == null) {
            return new ResponseEntity<>(new ResultResponse(), HttpStatus.OK);
        } else {
            String email = principal.getName();
            return new ResponseEntity<>(voteService.setVote(dislikeRequest.getPostId(), -1, email), HttpStatus.OK);
        }
    }
}
