package main.service;

import main.api.request.ModerationRequest;
import main.api.request.PostRequest;
import main.api.response.*;
import main.api.response.view.CommentForPostId;
import main.api.response.view.PostForPostResponse;
import main.api.response.view.UserForResponse;
import main.model.*;
import main.model.Enum.GlobalSettingValue;
import main.model.Enum.ModerationStatus;
import main.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostVoteRepository postVoteRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private TagToPostRepository tagToPostRepository;
    @Autowired
    private GlobalSettingRepository globalSettingRepository;


    public PostResponse getPost(int offset, int limit, String mode) {

        int countPost = postRepository.getCount();
        int page = offset / limit;
        Pageable sorted;
        Iterable<Post> iterable;

        switch (mode) {
            case ("recent"):
                sorted = PageRequest.of(page, limit, Sort.by("time").descending());
                iterable = postRepository.findByPost(sorted);
                break;
            case ("popular"):
                //countComment
                sorted = PageRequest.of(page, limit);
                iterable = postRepository.findByPostSortComment(sorted);
                break;
            case ("best"):
                //likeCount;
                sorted = PageRequest.of(page, limit);
                iterable = postRepository.findByPostSortLike(sorted);
                break;
            default:
                sorted = PageRequest.of(page, limit, Sort.by("time").ascending());
                iterable = postRepository.findByPost(sorted);
                break;
        }

        return getPostResponse(iterable, countPost);
    }

    public PostResponse getPostQuery(int offset, int limit, String query) {

        int countPost = postRepository.getCountQuery(query);
        int page = offset / limit;
        Pageable sorted;
        sorted = PageRequest.of(page, limit, Sort.by("time").ascending());

        Iterable<Post> iterable;
        iterable = postRepository.findByPostQuery(sorted, query);

        return getPostResponse(iterable, countPost);
    }

    public PostResponse getPostDate(int offset, int limit, String date) {

        int countPost = postRepository.getCountDate(date);
        int page = offset / limit;
        Pageable sorted;
        sorted = PageRequest.of(page, limit, Sort.by("time").ascending());
        Iterable<Post> iterable;
        iterable = postRepository.findByPostDate(sorted, date);

        return getPostResponse(iterable, countPost);
    }

    public PostResponse getPostTag(int offset, int limit, String tag) {
        int countPost = postRepository.getCountTag(tag);
        int page = offset / limit;
        Pageable sorted;
        sorted = PageRequest.of(page, limit, Sort.by("time").ascending());
        Iterable<Post> iterable;
        iterable = postRepository.findByPostTag(sorted, tag);

        return getPostResponse(iterable, countPost);
    }

    public PostResponse getPostModeration(int offset, int limit, String status, String email) {

        main.model.User userTek = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user " + email + " not found"));
        Long moderationId = userTek.getId();
        int countPost;
        int page = offset / limit;
        Pageable sorted;
        sorted = PageRequest.of(page, limit, Sort.by("time").ascending());
        Iterable<Post> iterable;

        switch (status) {
            case ("new"):
                //новые
                iterable = postRepository.findByPostModerationNew(sorted, status);
                countPost = postRepository.getCountModerationNew(status);
                break;
            default:
                //принятые
                //отклонненые
                iterable = postRepository.findByPostModeration(sorted, status, moderationId);
                countPost = postRepository.getCountModeration(status, moderationId);
                break;

        }

        return getPostResponse(iterable, countPost);
    }

    public PostResponse getPostMy(int offset, int limit, String status, String email) {
        main.model.User userTek = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user " + email + " not found"));
        Long userId = userTek.getId();
        int page = offset / limit;
        Pageable sorted;
        sorted = PageRequest.of(page, limit, Sort.by("time").ascending());

        Iterable<Post> iterable;
        int countPost;
        switch (status) {
            case ("pending"):
                //активные, но не промодерированные
                iterable = postRepository.findByPostMy(sorted, 1, "NEW", userId);
                countPost = postRepository.getCountMy(1, "NEW", userId);
                break;
            case ("declined"):
                //отклоненные
                iterable = postRepository.findByPostMy(sorted, 1, "DECLINED", userId);
                countPost = postRepository.getCountMy(1, "DECLINED", userId);
                break;
            case ("published"):
                //опубликованные
                iterable = postRepository.findByPostMy(sorted, 1, "ACCEPTED", userId);
                countPost = postRepository.getCountMy(1, "ACCEPTED", userId);
                break;
            default:
                //скрытые
                iterable = postRepository.findByPostMy(sorted, 0, "", userId);
                countPost = postRepository.getCountMy(1, "", userId);
                break;
        }
        return getPostResponse(iterable, countPost);
    }

    public PostResponse getPostID(int offset, int limit, String status, long userId) {
        int countPost = postRepository.getCountModeration(status, userId);
        int page = offset / limit;
        Pageable sorted;
        sorted = PageRequest.of(page, limit, Sort.by("time").ascending());

        Iterable<Post> iterable;
        switch (status) {
            case ("pending"):
                //активные, но не промодерированные
                iterable = postRepository.findByPostMy(sorted, 1, "NEW", userId);
                break;
            case ("declined"):
                //отклоненные
                iterable = postRepository.findByPostMy(sorted, 1, "DECLINED", userId);
                break;
            case ("published"):
                //опубликованные
                iterable = postRepository.findByPostMy(sorted, 1, "ACCEPTED", userId);
                break;
            default:
                //скрытые
                iterable = postRepository.findByPostMy(sorted, 0, "", userId);
                break;

        }
        return getPostResponse(iterable, countPost);
    }

    public PostIdResponse getPostId(long id, String email) {

        //получаем пост
        Post post = postRepository.findById(id);
        if (post == null) {
            return null;
        } else {
            PostIdResponse jsonPost = new PostIdResponse();
            jsonPost.setId(post.getId());
            jsonPost.setTimestamp(post.getTimeSec());
            //получаем автора поста
            UserForResponse jsonUser = new UserForResponse();
            jsonUser.setId(post.getUserAuthor().getId());
            jsonUser.setName(post.getUserAuthor().getName());

            jsonPost.setUser(jsonUser);
            jsonPost.setTitle(post.getTitle());
            jsonPost.setText(post.getText());
            jsonPost.setLikeCount(post.getCountLike());
            jsonPost.setDislikeCount(post.getCountDisLike());
            jsonPost.setViewCount(post.getViewCount());

            //получаем комментарии
            List<PostComment> listComment = post.getPostCommentList();
            ArrayList<CommentForPostId> comments = new ArrayList<>();
            for (PostComment postComment : listComment) {
                CommentForPostId commentForPostId = new CommentForPostId();
                commentForPostId.setId(postComment.getId());
                commentForPostId.setTimestamp(postComment.getTimeSec());
                commentForPostId.setText(postComment.getText());

                UserForResponse userForComment = new UserForResponse();
                userForComment.setId(postComment.getUserAuthor().getId());
                userForComment.setName(postComment.getUserAuthor().getName());
                userForComment.setPhoto(postComment.getUserAuthor().getPhoto());

                commentForPostId.setUser(userForComment);

                comments.add(commentForPostId);
            }
            //добавляем комментарии
            jsonPost.setComments(comments);
            //получаем теги
            List<Tag> tagSet = post.getTagList();
            ArrayList<String> tags = new ArrayList<>();
            for (Tag tag : tagSet) {
                tags.add(tag.getName());
            }
            jsonPost.setTags(tags);

            if (email != null) {
                main.model.User userTek = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user " + email + " not found"));

                if (userTek.getIsModerator() == 0 && userTek.getId() != post.getUserAuthor().getId()) {
                    int viewCount = post.getViewCount();
                    viewCount++;
                    post.setViewCount(viewCount);
                    postRepository.save(post);
                }
            } else {
                int viewCount = post.getViewCount();
                viewCount++;
                post.setViewCount(viewCount);
                postRepository.save(post);
            }
            return jsonPost;
        }
    }

    public ResultResponse putPostId(long id, String email, PostRequest postRequest) {

        //получаем пост
        Post post = postRepository.findById(id);
        if (post == null) {
            return new ResultResponse();
        } else {
            ResultResponse resultResponse = getResultResponse(postRequest);
            if (resultResponse.isResult()) {
                savePost(post, email, postRequest, false);
            }
            return resultResponse;
        }
    }


    public ResultResponse addPostId(String email, PostRequest postRequest) {

        ResultResponse resultResponse = getResultResponse(postRequest);
        if (resultResponse.isResult()) {
            //создаем пост
            Post post = new Post();
            savePost(post, email, postRequest, true);
        }
        return resultResponse;
    }

    public ResultResponse addModeratePost(String email, ModerationRequest moderationRequest) {
        try {
            main.model.User userTek = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user " + email + " not found"));
            Post post = postRepository.findById(moderationRequest.getPost_id());
            if (post == null) {
                return new ResultResponse();
            } else {
                String status;
                if (moderationRequest.getDecision() == "accepte") {
                    status = "ACCEPTED";
                } else {
                    status = "DECLINED";
                }

                post.setModerationStatus(ModerationStatus.valueOf(status));
                post.setUserModerator(userTek);
                postRepository.save(post);
                ResultResponse resultResponse = new ResultResponse();
                resultResponse.setResult(true);
                return resultResponse;
            }
        } catch (Exception e) {
            return new ResultResponse();
        }
    }


    public CalendarResponse getCalendar(int year) {

        if (year == 0) {
            year = getCurrentYear();
        }
        ArrayList<Integer> yearList = postRepository.findByYear();
        List<DateCount> dateCountList = postRepository.findByCountInDate();
        CalendarResponse calendarResponse = new CalendarResponse();
        calendarResponse.setYears(yearList);
        Map<String, Integer> posts = new HashMap<>();
        for (DateCount dateCount : dateCountList) {
            posts.put(dateCount.getDate().toString(), dateCount.getCount());
        }
        calendarResponse.setPosts(posts);
        return calendarResponse;
    }

    public StatisticsResponse getStatisticsMy(String email) {

        try {
            main.model.User userTek = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user " + email + " not found"));
            long idUser = userTek.getId();
            StatisticsResponse statisticsResponse = new StatisticsResponse();
            statisticsResponse.setPostsCount(postRepository.countByPost(idUser));
            statisticsResponse.setViewsCount(postRepository.countByView(idUser));
            statisticsResponse.setFirstPublication(postRepository.firstByPublication(idUser).getTime() / 1000);
            statisticsResponse.setLikesCount(postVoteRepository.countByLike(idUser));
            statisticsResponse.setDislikesCount(postVoteRepository.countByDisLike(idUser));

            return statisticsResponse;
        } catch (Exception e) {
            return new StatisticsResponse();
        }
    }

    public String addImage(MultipartFile file) throws Exception {

        InputStream in = file.getInputStream();
        String path1 = getHash(2);
        String path2 = getHash(2);
        String path3 = getHash(2);
        String path = "upload/" + path1 + "/" + path2 + "/" + path3;
        Path dir = Files.createDirectories(Paths.get(path));
        FileOutputStream f = new FileOutputStream(String.valueOf(dir.resolve(file.getOriginalFilename())));
        int ch = 0;
        while ((ch = in.read()) != -1) {
            f.write(ch);
        }
        f.flush();
        f.close();
        System.out.println(path);
        return path;

    }

    public StatisticsResponse getStatisticsAll(String email) {

        try {
            main.model.User userTek = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user " + email + " not found"));
            StatisticsResponse statisticsResponse = new StatisticsResponse();
            statisticsResponse.setPostsCount(postRepository.countByPostAll());
            statisticsResponse.setViewsCount(postRepository.countByViewAll());
            statisticsResponse.setFirstPublication(postRepository.firstByPublicationAll().getTime() / 1000);
            statisticsResponse.setLikesCount(postVoteRepository.countByLikeAll());
            statisticsResponse.setDislikesCount(postVoteRepository.countByDisLikeAll());

            boolean flagStatisticsIsPublic = (globalSettingRepository.findAllByCode("STATISTICS_IS_PUBLIC").getValue().equals(GlobalSettingValue.YES)) ? true : false;
            if (flagStatisticsIsPublic) {
                return statisticsResponse;
            } else {
                if (userTek.getIsModerator() == 1) {
                    return statisticsResponse;
                } else {
                    return new StatisticsResponse();
                }
            }

        } catch (Exception e) {
            return new StatisticsResponse();
        }
    }

    //----------------------------------------------------------------------------------------------
    //вспомогательные методы
    public String getHash(int length) {
        String symbols = "abcdefghijklmnopqrstuvwxyz1234567890";
        String random = new Random().ints(length, 0, symbols.length())
                .mapToObj(symbols::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
        return random;
    }

    private ResultResponse getResultResponse(PostRequest postRequest) {

        ResultResponse resultResponse = new ResultResponse();
        boolean flagError = false;
        Map<String, Object> errors = new HashMap<>();
        if (postRequest.getTitle().length() < 3) {
            errors.put("title", "Заголовок не установлен. Минимальная длина 3 символа.");
            flagError = true;
        }
        if (postRequest.getText().length() < 30) {
            errors.put("text", "Текст побликации слишком короткий. Минимальная длина 30 символов.");
            flagError = true;
        }
        if (flagError) {
            resultResponse.setErrors(errors);
            return resultResponse;
        }
        resultResponse.setResult(true);
        return resultResponse;
    }

    private PostResponse getPostResponse(Iterable<Post> iterable, int countPost) {

        ArrayList<PostForPostResponse> jsonArrayPosts = new ArrayList<>();
        //
        for (Post post : iterable) {
            PostForPostResponse jsonPost = new PostForPostResponse();
            jsonPost.setId(post.getId());
            jsonPost.setTimestamp(post.getTimeSec());
            //получаем автора поста
            UserForResponse jsonUser = new UserForResponse();
            jsonUser.setId(post.getUserAuthor().getId());
            jsonUser.setName(post.getUserAuthor().getName());

            jsonPost.setUser(jsonUser);
            jsonPost.setTitle(post.getTitle());
            jsonPost.setAnnounce(textForAnnounce(post.getText()));
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

    private String textForAnnounce(String text) {

        String textOut = "";
        textOut = text.replaceAll("<.*?>", " ").replaceAll("&.*?;", "");
        int length = textOut.length();
        if (length < 150) {
            textOut = textOut.substring(0, length).trim();
        } else {
            textOut = textOut.substring(0, 150).trim() + "...";
        }
        return textOut;
    }

    private void savePost(Post post, String email, PostRequest postRequest, boolean flagNew) {

        Date dateTek = new Date();
        Date datePost = new Date(postRequest.getTimestamp() * 1000);
        if (dateTek.compareTo(datePost) > 0) {
            datePost = dateTek;

        }
        post.setTime(datePost);
        post.setIsActive(postRequest.getActive());
        post.setTitle(postRequest.getTitle());
        post.setText(postRequest.getText());
        main.model.User userTek = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user " + email + " not found"));
        post.setUserAuthor(userTek);

        boolean postPremoderation = (globalSettingRepository.findAllByCode("POST_PREMODERATION").getValue().equals(GlobalSettingValue.YES)) ? true : false;

        if (!postPremoderation) {
            post.setModerationStatus(ModerationStatus.ACCEPTED);
        } else {
            if (userTek.getIsModerator() == 0 && !flagNew) {
                post.setModerationStatus(ModerationStatus.NEW);
            } else {
                if (flagNew) {
                    post.setModerationStatus(ModerationStatus.NEW);
                }
            }
        }

        List<Tag> tagPostInList = new ArrayList<>();
        for (Tag tag : post.getTagList()) {
            tagPostInList.add(tag);
        }

        for (String tagName : postRequest.getTags()) {

            Tag tag = tagRepository.findByName(tagName);
            if (tag == null) {
                Tag newTag = new Tag();
                newTag.setName(tagName);
                tagRepository.save(newTag);

                TagToPost tagToPost = new TagToPost();
                tagToPost.setPostId(post.getId());
                tagToPost.setTagId(newTag.getId());
                tagToPostRepository.save(tagToPost);

            } else {
                if (!tagPostInList.contains(tag)) {
                    TagToPost tagToPost = new TagToPost();
                    tagToPost.setPostId(post.getId());
                    tagToPost.setTagId(tag.getId());
                    tagToPostRepository.save(tagToPost);

                } else {
                    tagPostInList.remove(tag);
                }
            }
        }
        for (Tag tag : tagPostInList) {
            tagToPostRepository.deleteTagToPost(post.getId(), tag.getId());
        }
        postRepository.save(post);
    }

    public static int getCurrentYear() {

        java.util.Calendar calendar = java.util.Calendar.getInstance(java.util.TimeZone.getDefault(), java.util.Locale.getDefault());
        calendar.setTime(new java.util.Date());
        return calendar.get(java.util.Calendar.YEAR);
    }
}
