package main.repository;

import main.model.Post;
import main.model.Tag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TagRepository extends CrudRepository<Tag, Integer> {

    Tag findByName(String name);

    @Query(value = "select e.*  from tags e " +
            "inner join tag2post tp on e.id = tp.tag_id " +
            "where e.name like %?1% ", nativeQuery = true)
    List<Tag> findByQuery(String query);

    @Query(value = "select  count(*) from tags e " +
            "inner join tag2post tp on e.id = tp.tag_id " +
            "where e.name = ?1 ", nativeQuery = true)
    int countTag(String name);
}
