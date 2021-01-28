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


    @Query(value = "select count(*) from  Post_Votes p " +
            "inner join (select id from posts e " +
            "where e.is_active = 1 and moderation_Status = 'ACCEPTED' " +
            "and time <= now() and  user_id = :idUser) as e " +
            " on e.id = p.post_id " +
            " where  p.value = 1", nativeQuery = true)
    int countByLike(long idUser);

    @Query(value = "select count(*) from  Post_Votes p " +
            "inner join (select id from posts e " +
            "where e.is_active = 1 and moderation_Status = 'ACCEPTED' " +
            "and time <= now()) as e " +
            " on e.id = p.post_id " +
            " where  p.value = 1", nativeQuery = true)
    int countByLikeAll();
    @Query(value = "select count(*) from  Post_Votes p " +
            "inner join (select id from posts e " +
            "where e.is_active = 1 and moderation_Status = 'ACCEPTED' " +
            "and time <= now() and  user_id = :idUser) as e " +
            " on e.id = p.post_id " +
            " where  p.value = -1", nativeQuery = true)
    int countByDisLike(long idUser);

    @Query(value = "select count(*) from  Post_Votes p " +
            "inner join (select id from posts e " +
            "where e.is_active = 1 and moderation_Status = 'ACCEPTED' " +
            "and time <= now()) as e " +
            " on e.id = p.post_id " +
            " where  p.value = -1", nativeQuery = true)
    int countByDisLikeAll();
}
