package main.model;

import lombok.Data;
import main.model.Enum.GlobalSettingCode;
import main.model.Enum.GlobalSettingValue;

import javax.persistence.*;

@Entity
@Data
@Table(name="global_settings")
public class GlobalSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;//id настройки
    @Column(nullable = false)
    private String code;//системное имя настройки
    @Column(nullable = false)
    private String name ;//название настройки
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GlobalSettingValue value ;//значение настройки
}
