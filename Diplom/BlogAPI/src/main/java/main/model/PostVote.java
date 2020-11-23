package main.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name="post_votes")
public class PostVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;//id лайка/дизлайка
    //@Column(name = "user_id", nullable = false)
    //private int userId;// тот, кто поставил лайк / дизлайк
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    //@Column(name = "post_id", nullable = false)
    //private int postId;//пост, которому поставлен лайк / дизлайк
    @ManyToOne
    @JoinColumn(name="post_id", nullable=false)
    private Post post;

    @Column(nullable = false)
    private Date time;//дата и время лайка / дизлайка
    @Column(nullable = false, columnDefinition = "tinyint")
    private int value;//лайк или дизлайк: 1 или -1

    public PostVote() {
    }
}
