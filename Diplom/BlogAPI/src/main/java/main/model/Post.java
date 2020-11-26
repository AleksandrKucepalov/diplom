package main.model;

import lombok.Data;
import main.model.Enum.ModerationStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;//поста
    @Column(name = "is_active", nullable = false, columnDefinition = "tinyint")
    private int isActive ;// скрыта или активна публикация: 0 или 1
    @Enumerated(EnumType.STRING)
    @Column(name = "moderation_status", nullable = false,columnDefinition = "enum('NEW', 'ACCEPTED','DECLINED')")
    private ModerationStatus moderationStatus = ModerationStatus.NEW;//статус модерации, по умолчанию значение "NEW".
    //@Column(name = "moderator_id")
    //private Integer moderatorId;//ID пользователя-модератора, принявшего решение, или NULL
    @ManyToOne
    @JoinColumn(name="moderator_id")
    private User userModerator;

    //@Column(name = "user_id", nullable = false)
    //private int userId;// автор поста
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User userAuthor;

    @Column(nullable = false)
    private Date time ;// дата и время публикации поста
    @Column(nullable = false)
    private String  title;// заголовок поста
    @Column(nullable = false, columnDefinition = "text")
    private String  text;//текст поста
    @Column(name = "view_count", nullable = false)
    private int viewCount = 0;//количество просмотров поста

    //список тегов
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "tag2post",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    private Set<Tag> tagSet;

    //список лайков/дизлайков
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostVote> postVoteList;

    //коментарии поста
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostComment> postCommentList;

    public Post() {
    }

    public int getCountLike(){
        List<PostVote> postVoteLike = new ArrayList<>();
        for(PostVote postVote : postVoteList){
            if(postVote.getValue()==1){
                postVoteLike.add(postVote);
            }
        }
        return postVoteLike.size();
    }

    public int getCountDisLike(){
        List<PostVote> postVoteDisLike = new ArrayList<>();
        for(PostVote postVote : postVoteList){
            if(postVote.getValue()==1){
                postVoteDisLike.add(postVote);
            }
        }
        return postVoteDisLike.size();
    }

}
