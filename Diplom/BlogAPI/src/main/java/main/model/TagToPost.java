package main.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@Table(name="tag2post")
public class TagToPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;//id связи
    @NonNull
    @Column(name = "post_id", nullable = false)
    private int postId;//id поста
    @NonNull
    @Column(name = "tag_id", nullable = false)
    private int tagId;// id тега
}
