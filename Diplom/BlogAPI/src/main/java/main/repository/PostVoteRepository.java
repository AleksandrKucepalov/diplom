package main.repository;

import main.model.PostVote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostVoteRepository extends CrudRepository<PostVote,Integer> {

}
