package main.controller;

import main.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaulController {

   @Autowired
   private PostRepository postRepository;

    public DefaulController() {
    }

    @RequestMapping({"/"})
    public String index(Model model) {
        /*
        Post post = new Post();
        post.setIsActive(1);
        post.setModeratorId(1);
        post.setText("Текст");
        post.setTitle("Первый пост");
        post.setTime(new Date());
        postRepository.save(post);
        post = new Post();
        post.setIsActive(1);
        post.setModeratorId(1);
        post.setText("Текст");
        post.setTitle("Второй пост");
        post.setTime(new Date());
        post.setModerationStatus(ModerationStatus.ACCEPTED);
        postRepository.save(post);
        */
        return "index";

    }
}
