package com.mincoder.myhome.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity   //DB연동을 위한 model class임을 알려줌
@Data
public class Board {
    @Id  //id가 pk임을 알려줌
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
}
