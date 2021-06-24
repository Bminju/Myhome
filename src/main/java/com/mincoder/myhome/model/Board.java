package com.mincoder.myhome.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity   //DB연동을 위한 model class임을 알려줌
@Data
public class Board {
    @Id  //id가 pk임을 알려줌
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min=2, max=30, message = "제목은 2자이상 30자 이하입니다.")
    private String title;
    private String content;

    @ManyToOne  //어떤 컬럼을 참조할 것인지 JoinColumn으로 설정
    @JoinColumn(name = "user_id")  //소유하는 쪽 Many에 해당. 외래키를 가지고 있음.
    @JsonIgnore
    private User user;

}
