package com.mincoder.myhome.repository;


import com.mincoder.myhome.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
    User findByUsername(String username);
    //DB table username에 유니크 키 제약조건이 있어서 반드시 하나의 데이터 혹은 null을 받아 올 것임

}
