package main.controller;

import main.api.request.*;
import main.api.response.ResultResponse;
import main.repository.CommentService;
import main.service.PostService;
import main.service.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class ApiPostController {

    private final VoteService voteService;
    private final PostService postService;
    private final CommentService commentService;

    public ApiPostController(VoteService voteService, PostService postService, CommentService commentService) {

        this.voteService = voteService;
        this.postService = postService;
        this.commentService = commentService;
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
    public ResponseEntity image(@RequestParam String image) {

        return new ResponseEntity<>(new ResultResponse(), HttpStatus.BAD_REQUEST);
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
    public ResponseEntity profileMy() {

        return new ResponseEntity<>(new ResultResponse(), HttpStatus.OK);
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
