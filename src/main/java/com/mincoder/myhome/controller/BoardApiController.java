package com.mincoder.myhome.controller;


import com.mincoder.myhome.model.Board;
import com.mincoder.myhome.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RestController
@RequestMapping("/api")
class BoardApiController {

    @Autowired
    private BoardRepository repository;


    //제목 검색 추가,
    @GetMapping("/boards")   //required=true가 기본 값. true면 param 전달 안됨. title 값을 받는 것.
    //title 값을 받으면 검색을 하고 ,전달되지 않았다면 return
    List<Board> all(@RequestParam(required = false, defaultValue = "") String title,
        @RequestParam(required = false, defaultValue = "") String content) {
        if(StringUtils.isEmpty(title) && StringUtils.isEmpty(content)) {
            return repository.findAll();  //전체 데이터 조회
        }else{
            return repository.findByTitleOrContent(title, content);
        }
    }

    @PostMapping("/boards")  //post 요청으로 받는 parameter가 requestbody
    Board newBoard(@RequestBody Board newBoard) {
        return repository.save(newBoard);
    }

    @GetMapping("/boards/{id}")
    Board one(@PathVariable Long id) {
        return repository.findById(id)
                .orElse(null);
    }

    @PostMapping("/boards/{id}")
    Board replaceBoard(@RequestBody Board newBoard, @PathVariable Long id) {

        return repository.findById(id)
                .map(board -> {
                    board.setTitle(newBoard.getTitle());
                    board.setContent(newBoard.getContent());
                    return repository.save(board);
                })
                .orElseGet(() -> {
                    newBoard.setId(id);
                    return repository.save(newBoard);
                });
    }

    @DeleteMapping("/board/{id}")
    void deleteBoard(@PathVariable Long id) {
        repository.deleteById(id);
    }


}