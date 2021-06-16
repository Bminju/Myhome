package com.mincoder.myhome.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity   //DB연동을 위한 model class임을 알려줌
@Data
public class User {

    @Id  //id가 pk임을 알려줌
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private Boolean enabled;

    @ManyToMany  // 양방향Mapping JPA 문법
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
             )
    private List<Role> roles;
}
