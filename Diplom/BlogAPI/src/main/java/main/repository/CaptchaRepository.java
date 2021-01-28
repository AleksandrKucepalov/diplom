package main.repository;

import lombok.Data;
import main.model.CaptchaCode;
import main.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface CaptchaRepository extends CrudRepository<CaptchaCode,Integer> {

    int countByCodeAndAndSecretCode(String code, String secretCode);

    @Modifying
    @Transactional
    @Query("delete from CaptchaCode  where timestampdiff(minute,time,:date) > 60")
    void deleteCaptcha60min(Date date);

}
