package com.mincoder.myhome.controller;


import com.mincoder.myhome.model.Board;
import com.mincoder.myhome.model.User;
import com.mincoder.myhome.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j  //log 찍는데 필요
class UserApiController {

    @Autowired
    private UserRepository repository;


    //제목 검색 추가,
    @GetMapping("/users")
    List<User> all() {
        List<User> users = repository.findAll();
        log.debug("getBoards().size() 호출전"); //디버그
        log.debug("getBoards().size() : {}", users.get(0).getBoards().size());

        log.debug("getBoards().size() 호출후");
        return users;
    }

    @PostMapping("/Users")  //post 요청으로 받는 parameter가 requestbody
    User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    @GetMapping("/Users/{id}")
    User one(@PathVariable Long id) {
        return repository.findById(id)
                .orElse(null);
    }

    @PostMapping("/Users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

        return repository.findById(id)
                .map(user -> {
 //                   User.setTitle(newUser.getTitle());
 //                   User.setContent(newUser.getContent());
                    //user.setBoards(newUser.getBoards());
                    user.getBoards().clear();  //기존 데이터 삭제
                    user.getBoards().addAll(newUser.getBoards()); // 받은 데이터로 데이터 변경
                    for(Board board : user.getBoards()) {
                        board.setUser(user);
                    }
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    @DeleteMapping("/User/{id}")
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }


}