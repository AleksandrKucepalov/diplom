package main.repository;

import main.model.PostVote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostVoteRepository extends CrudRepository<PostVote, Integer> {

    @Query(nativeQuery = true, value =
            "select e.* from post_votes e " +
                    "where e.value = ?1 and   " +
                    " post_id = ?2 and user_id = ?3")
    PostVote findByValueAndPostID(int value, long postId, long userId);

}
