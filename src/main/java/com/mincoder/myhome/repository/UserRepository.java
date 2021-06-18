package com.mincoder.myhome.repository;

import com.mincoder.myhome.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<Board, Long> {
}
