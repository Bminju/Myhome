package com.mincoder.myhome.service;

import com.mincoder.myhome.model.Board;
import com.mincoder.myhome.model.User;
import com.mincoder.myhome.repository.BoardRepository;
import com.mincoder.myhome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    public Board save(String username, Board board) {
     User user = userRepository.findByUsername(username); //user 클래스 받아 옴
     board.setUser(user); //board 안에 user 넣어 줌
     return boardRepository.save(board); // 저장된 객체를 리턴
    }
}
