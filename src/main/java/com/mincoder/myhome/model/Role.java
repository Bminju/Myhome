package com.mincoder.myhome.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity   //DB연동을 위한 model class임을 알려줌
@Data
public class Role {

    @Id  //id가 pk임을 알려줌
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
