package main.repository;

import main.model.DateCount;
import main.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {
    //2
    @Query("select e from Post e where e.isActive = 1 and moderationStatus = 'ACCEPTED' and time <= now()")
    List<Post> findByPost(Pageable page);

    @Query("select count(*) from Post e where e.isActive = 1 and moderationStatus = 'ACCEPTED' and time <= now()")
    int getCount();

    @Query(value = "select e.* from Posts e  " +
            "left join (select Post_id  postId, count(*) countLike from Post_Votes p " +
            "where p.value = 1  group by Post_id) p " +
            "on e.id = p.postId " +
            "where e.is_Active = 1 and moderation_Status = 'ACCEPTED' and time <= now() " +
            "order by countLike desc", nativeQuery = true)
    List<Post> findByPostSortLike(Pageable page);

    @Query(value = "select e.* from Posts e  " +
            "left join (select Post_id  postId, count(*) countComments from Post_comments p group by Post_id) p " +
            "on e.id = p.postId " +
            "where e.is_Active = 1 and moderation_Status = 'ACCEPTED' and time <= now() " +
            "order by countComments desc", nativeQuery = true)
    List<Post> findByPostSortComment(Pageable page);

    //3
    @Query("select e from Post e where e.isActive = 1 and moderationStatus = 'ACCEPTED' and time <= now() and (text like %?1% or title like %?1%)")
    List<Post> findByPostQuery(Pageable page, String query);

    @Query("select count(*) from Post e where e.isActive = 1 and moderationStatus = 'ACCEPTED' and time <= now() and (text like %?1%  or title like %?1%)")
    int getCountQuery(String query);

    //4
    @Query("select e from Post e where  e.isActive = 1 and moderationStatus = 'ACCEPTED' and cast(time as date) = cast(?1 as date)")
    List<Post> findByPostDate(Pageable page, String date);

    @Query("select count(*) from Post e where  e.isActive = 1 and moderationStatus = 'ACCEPTED' and cast(time as date)= cast(?1 as date)")
    int getCountDate(String date);

    //5
    @Query(value = "select e.* from posts e " +
            "inner join tag2post tp on e.id = tp.post_id " +
            "inner join tags t on t.id = tp.tag_id " +
            "where e.is_Active = 1 and moderation_Status = 'ACCEPTED' and time <= now() " +
            "and lower(t.name) = lower(?1)", nativeQuery = true)
    List<Post> findByPostTag(Pageable page, String tag);

    @Query(value = "select count(*) from posts e " +
            "inner join tag2post tp on e.id = tp.post_id " +
            "inner join tags t on t.id = tp.tag_id " +
            "where e.is_Active = 1 and moderation_Status = 'ACCEPTED' and time <= now() " +
            "and lower(t.name) = lower(?1)", nativeQuery = true)
    int getCountTag(String tag);

    //6
    @Query(nativeQuery = true, value =
            "select e.* from posts e " +
                    "where e.is_Active = 1 and lower(moderation_status) = lower(?1) " +
                    "and moderator_id = ?2")
    List<Post> findByPostModeration(Pageable page, String status, Long moderationId);

    @Query(nativeQuery = true, value =
            "select count(*) from posts e " +
                    "where e.is_Active = 1 and lower(moderation_status) = lower(?1) " +
                    "and moderator_id = ?2")
    int getCountModeration(String status, Long moderationId);

    @Query(nativeQuery = true, value =
            "select e.* from posts e " +
                    "where e.is_Active = 1 and lower(moderation_status) = lower(?1) " +
                    "and moderator_id is null")
    List<Post> findByPostModerationNew(Pageable page, String status);

    @Query(nativeQuery = true, value =
            "select count(*) from posts e " +
                    "where e.is_Active = 1 and lower(moderation_status) = lower(?1) " +
                    "and moderator_id is null")
    int getCountModerationNew(String status);

    //7
    @Query(nativeQuery = true, value =
            "select e.* from posts e " +
                    "where e.is_Active = ?1 and lower(moderation_status) = lower(?2) " +
                    "and user_id = ?3")
    List<Post> findByPostMy(Pageable page, int isActive, String status, long userId);

    @Query(nativeQuery = true, value =
            "select count(*) from posts e " +
                    "where e.is_Active = ?1 and lower(moderation_status) = lower(?2) " +
                    "and user_id = ?3")
    int getCountMy(int isActive, String status, long userId);

    @Query("select e from Post e where  e.isActive = 1 and e.moderationStatus = 'ACCEPTED' and  e.id = :id")
    Post findById(long id);

    @Query(nativeQuery = true, value =
            "select distinct YEAR(time)   from posts e where  e.isActive = 1 and e.moderationStatus = 'ACCEPTED' order by YEAR(time)")
    ArrayList<Integer> findByYear();

    @Query(nativeQuery = true, value =
            "select  cast(time as date) date, count(*) count  from posts e where  e.isActive = 1 and e.moderationStatus = 'ACCEPTED' " +
                    "group by cast(time as date)")
    List<DateCount> findByCountInDate();


    @Query(value = "select count(*)  from posts e " +
            "where e.is_active = 1 and moderation_Status = 'ACCEPTED' " +
            "and time <= now() and  user_id = :idUser", nativeQuery = true)
    int countByPost(long idUser);

    @Query(value = "select count(*)  from posts e " +
            "where e.is_active = 1 and moderation_Status = 'ACCEPTED' " +
            "and time <= now()", nativeQuery = true)
    int countByPostAll();

    @Query(value = "select sum(view_count)  from posts e " +
            "where e.is_active = 1 and moderation_Status = 'ACCEPTED' " +
            "and time <= now() and  user_id = :idUser", nativeQuery = true)
    int countByView(long idUser);

    @Query(value = "select sum(view_count)  from posts e " +
            "where e.is_active = 1 and moderation_Status = 'ACCEPTED' " +
            "and time <= now()", nativeQuery = true)
    int countByViewAll();

    @Query(value = "select min(time)  from posts e " +
            "where e.is_active = 1 and moderation_Status = 'ACCEPTED' " +
            "and time <= now() and  user_id = :idUser", nativeQuery = true)
    Date firstByPublication(long idUser);

    @Query(value = "select min(time)  from posts e " +
            "where e.is_active = 1 and moderation_Status = 'ACCEPTED' " +
            "and time <= now()", nativeQuery = true)
    Date firstByPublicationAll();
}
