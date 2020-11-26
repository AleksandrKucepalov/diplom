package main.controller;

import main.api.request.PostRequest;
import main.api.request.SettingsRequest;
import main.api.response.*;
import main.service.PostService;
import main.service.SettingsService;
import main.service.TagService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiGeneralController {


    private final InitResponse initResponse;
    private final SettingsService settingsService;
    private final PostService postService;
    private final TagService tagService;

    public ApiGeneralController(InitResponse initResponse, SettingsService settingsService, PostService postService, TagService tagService) {
        this.initResponse = initResponse;
        this.settingsService = settingsService;
        this.postService = postService;
        this.tagService = tagService;
    }

    //1
    @GetMapping("/init")
    public ResponseEntity<InitResponse> init() {

        return new ResponseEntity<>(initResponse, HttpStatus.OK);
    }

    //27
    @GetMapping("/settings")
    public ResponseEntity<SettingsResponse> settings() {

        return new ResponseEntity<>(settingsService.getGlobalSettings(), HttpStatus.OK);
    }

    //28
    @PutMapping("/settings")
    public void settingsPut(@RequestBody SettingsRequest settingsRequest) {

    }

    //2
    @GetMapping("/post")
    public ResponseEntity<PostResponse> post(@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                             @RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
                                             @RequestParam(value = "mode", required = false, defaultValue = "recent") String mode) {

        return new ResponseEntity<>(postService.getPost(offset, limit, mode), HttpStatus.OK);
    }

    //3
    @GetMapping("/post/search")
    public ResponseEntity<PostResponse> postSearch(@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                                   @RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
                                                   @RequestParam(value = "query", required = false, defaultValue = "") String query) {

        return new ResponseEntity<>(new PostResponse(), HttpStatus.OK);
    }

    //4
    @GetMapping("/post/byDate")
    public ResponseEntity<PostResponse> postByDate(@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                                   @RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
                                                   @RequestParam(value = "date", required = false, defaultValue = "") String date) {

        return new ResponseEntity<>(new PostResponse(), HttpStatus.OK);
    }

    //5
    @GetMapping("/post/byTag")
    public ResponseEntity<PostResponse> postByTag(@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                                  @RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
                                                  @RequestParam(value = "tag", required = false, defaultValue = "") String tag) {

        return new ResponseEntity<>(new PostResponse(), HttpStatus.OK);
    }

    //6
    @GetMapping("/post/moderation")
    public ResponseEntity<PostResponse> postModeration(@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                                       @RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
                                                       @RequestParam(value = "status", required = false, defaultValue = "new") String status) {

        return new ResponseEntity<>(new PostResponse(), HttpStatus.OK);
    }

    //7
    @GetMapping("/post/my")
    public ResponseEntity<PostResponse> postMy(@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                               @RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
                                               @RequestParam(value = "status", required = false, defaultValue = "inactive") String status) {

        return new ResponseEntity<>(new PostResponse(), HttpStatus.OK);
    }

    //8
    @GetMapping("/post/{id}")
    public ResponseEntity<PostIdResponse> postId(@PathVariable int id) {

        return new ResponseEntity<>(new PostIdResponse(), HttpStatus.OK);
    }

    //11
    @PutMapping("/post/{id}")
    public ResponseEntity postUpdate(@RequestBody PostRequest postRequest) {

        return new ResponseEntity<>(new ResultResponse(), HttpStatus.OK);
    }

    //13
    @GetMapping("/tag")
    public ResponseEntity<TagsResponse> tag() {

        return new ResponseEntity<>(tagService.getTags(), HttpStatus.OK);
    }

    //14.1
    @GetMapping("/calendar")
    public ResponseEntity<CalendarResponse> calendar(@RequestParam(value = "year", required = false, defaultValue = "0") int year) {

        return new ResponseEntity<>(new CalendarResponse(), HttpStatus.OK);
    }

    //22
    @GetMapping("/statistics/my")
    public ResponseEntity<StatisticsResponse> statisticsMy() {

        return new ResponseEntity<>(new StatisticsResponse(), HttpStatus.OK);
    }

    //23
    @GetMapping("/statistics/all")
    public ResponseEntity<StatisticsResponse> statisticsAll() {

        return new ResponseEntity<>(new StatisticsResponse(), HttpStatus.OK);
    }


}
