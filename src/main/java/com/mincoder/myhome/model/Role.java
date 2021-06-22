package com.mincoder.myhome.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity   //DB연동을 위한 model class임을 알려줌
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id  //id가 pk임을 알려줌
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
