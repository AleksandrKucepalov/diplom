package main.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@Table(name="global_settings")
public class GlobalSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;//id настройки
    @NonNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GlobalSettingCode code;//системное имя настройки
    @NonNull
    @Column(nullable = false)
    private String name ;//название настройки
    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GlobalSettingValue value ;//значение настройки
}
