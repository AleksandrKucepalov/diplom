package main.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@Table(name="tag2post")
public class TagToPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;//id связи
    @Column(name = "post_id", nullable = false)
    private long postId;//id поста
    @Column(name = "tag_id", nullable = false)
    private long tagId;// id тега

    public TagToPost() {
    }
}
