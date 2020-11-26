package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);

    }
  /*  @Autowired
    private PostRepository postRepository;

    @Bean
    InitializingBean sendDatabase() {
        return () -> {
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

            Post post = new Post(1,ModerationStatus.ACCEPTED,1,1,new Date(),"Post","text",(int) (Math.random() * 100));
            postRepository.save(post);
        };
    }*/
}