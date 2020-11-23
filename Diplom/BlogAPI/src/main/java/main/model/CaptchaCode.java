package main.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name="captcha_codes")
public class CaptchaCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull
    @Column(nullable = false)
    private Date time;//дата и время генерации кода капчи
    @NonNull
    @Column(nullable = false, columnDefinition = "tinytext")
    private String code;// код, отображаемый на картинкке капчи
    @NonNull
    @Column(name = "secret_code",nullable = false, columnDefinition = "tinytext")
    private String secretCode;//код, передаваемый в параметре
}
