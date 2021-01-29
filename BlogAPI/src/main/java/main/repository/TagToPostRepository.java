package main.repository;

import main.model.Post;
import main.model.Tag;
import main.model.TagToPost;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface TagToPostRepository extends CrudRepository<TagToPost, Integer> {

    @Modifying
    @Transactional
    @Query("delete from TagToPost  where postId=?1 and tagId=?2")
    void deleteTagToPost(long postId,long tagId);
}
