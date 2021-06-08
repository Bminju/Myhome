package com.mincoder.myhome.repository;

import com.mincoder.myhome.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByTitle(String title); //인터페이스만 정의를 하면 구현을 알아서 됨
    List<Board> findByTitleOrContent(String title, String content);
}
