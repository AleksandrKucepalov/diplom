package main.model;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {

    @Query("select e from Post e where e.isActive = 1 and moderationStatus = 'ACCEPTED' and time < :date")
    List<Post> findPostWithActiveAndStatusACCEPTED(@Param("date") Date date);
}
