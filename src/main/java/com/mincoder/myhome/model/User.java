package com.mincoder.myhome.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
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

    @JsonIgnore
    @ManyToMany  // 양방향Mapping JPA 문법
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    //One에 해당.
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)  //양방향 매핑 board에서 사용한 변수명을 써줌
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)  //양방향 매핑 board에서 사용한 변수명을 써줌
    private List<Board> boards = new ArrayList<>();

}
