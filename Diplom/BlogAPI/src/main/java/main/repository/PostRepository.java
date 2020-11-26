package main.repository;

import main.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {

    @Query("select e from Post e where e.isActive = 1 and moderationStatus = 'ACCEPTED' and time <= now()")
    List<Post> findByPost(Pageable page);

    @Query("select count(*) from Post e where e.isActive = 1 and moderationStatus = 'ACCEPTED' and time <= now()")
    int getCount();


}
