package main.model;

import lombok.Data;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "is_moderator", nullable = false)
    private int isModerator; //является ли пользователь модератором (может ли править глобальные настройки сайта и модерировать посты)
    @Column(name = "reg_time", nullable = false)
    private Date regTime; //дата и время регистрации пользователя
    @Column(nullable = false)
    private String  name; //имя пользователя
    @Column(nullable = false)
    private String email; //e-mail пользователя
    @Column(nullable = false)
    private String password ; //хэш пароля пользователя
    private String code; // код для восстановления пароля, может быть NULL
    @Column(columnDefinition = "text")
    private String photo; //фотография (ссылка на файл), может быть NULL

    // посты пользователя
    @OneToMany(mappedBy = "userAuthor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> postList;
    // посты что он модерировал
    @OneToMany(mappedBy = "userModerator")
    private List<Post> postListModerator;
    //коментарии что он писал
    @OneToMany(mappedBy = "userAuthor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostComment> postCommentList;
    //коментарии что он писал
    /*не пойму как правильно каскодирование настроить
    например я удаляю пользователя, что делать с его постами? удалять? тогда cascade = CascadeType.ALL нужно использовать
    А что делать с модераторам? если его удалили, то в посте как понимаю нужно null проставить. какая анотация за это отвчает?
    CascadeType.REFRESH?
    Если вообще ничего не указать будет ошибка?
     */
//список лайков/дизлайков
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostVote> postVoteList;

    public User() {

    }
}
