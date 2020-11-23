package main.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name="tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name; //текст тэга

    @ManyToMany(mappedBy = "tagSet")
    private Set<Post> postSet;

    public Tag() {

    }
}
