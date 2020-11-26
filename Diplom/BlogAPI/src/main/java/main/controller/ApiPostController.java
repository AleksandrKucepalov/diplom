package main.controller;

import main.api.request.*;
import main.api.response.PostIdResponse;
import main.api.response.ResultErrorResponse;
import main.api.response.ResultResponse;
import main.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiPostController {

    //9
    @PostMapping("/post")
    public ResponseEntity postAdd(@RequestBody PostRequest postRequest) {

        return new ResponseEntity<>(new ResultResponse(), HttpStatus.OK);
    }

    //10
    @PostMapping("/image")
    public ResponseEntity image(@RequestParam String image) {

        return new ResponseEntity<>(new ResultErrorResponse(), HttpStatus.BAD_REQUEST);
    }

    //12
    @PostMapping("/comment")
    public ResponseEntity comment(@RequestBody CommentRequest commentRequest) {

        return new ResponseEntity<>(new ResultErrorResponse(), HttpStatus.BAD_REQUEST);
    }

    //14
    @PostMapping("/moderation")
    public ResponseEntity moderation(@RequestBody ModerationRequest moderationRequest) {

        return new ResponseEntity<>(new ResultResponse(), HttpStatus.OK);
    }

    //20
    @PostMapping("/profile/my")
    public ResponseEntity profileMy() {

        return new ResponseEntity<>(new ResultResponse(), HttpStatus.OK);
    }

    //24
    @PostMapping("/post/like")
    public ResponseEntity like(@RequestBody LikeAndDislikeRequest likeRequest) {

        return new ResponseEntity<>(new ResultResponse(), HttpStatus.OK);
    }

    //25
    @PostMapping("/post/dislike")
    public ResponseEntity dislike(@RequestBody LikeAndDislikeRequest dislikeRequest) {

        return new ResponseEntity<>(new ResultResponse(), HttpStatus.OK);
    }
}
