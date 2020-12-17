package main.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="post_comments")
public class PostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;//id комментария
    //@Column(name = "parent_id")
    //private Integer parentId;//комментарий, на который оставлен этот комментарий (может быть NULL, если комментарий оставлен просто к посту)
    @ManyToOne
    @JoinColumn(name="parent_id")
    private PostComment postComment;
    //@Column(name = "post_id", nullable = false)
    //private int postId;// пост, к которому написан комментарий
    @ManyToOne
    @JoinColumn(name="post_id", nullable=false)
    private Post post;
    //@Column(name = "user_id", nullable = false)
    //private int userId;//  автор комментария
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User userAuthor;
    @Column(nullable = false)
    private Date time;// дата и время комментария
    @Column(nullable = false, columnDefinition = "text")
    private String text;//  текст комментария

    //коментарии комментария
    @OneToMany(mappedBy = "postComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostComment> postCommentList;

    public PostComment() {
    }
    public long getTimeSec(){
        return getTime().getTime() / 1000;
    }
}
