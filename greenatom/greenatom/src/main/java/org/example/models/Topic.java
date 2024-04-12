package org.example.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


//В бд у нас одна сущность с топиками и одна с сообщениями, но дублируется в коде для удобства работы
//Предложенная в примере API реализация бд порождает слишком сильное дублирование данных, и на большом количестве данных может быть критическим
@Data
@Entity(name = "topic")
public class Topic {

    @Id
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "created")
    private String created;
}
