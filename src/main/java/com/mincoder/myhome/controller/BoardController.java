package com.mincoder.myhome.controller;

import com.mincoder.myhome.model.Board;
import com.mincoder.myhome.repository.BoardRepository;
import com.mincoder.myhome.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardValidator boardValidator;

    //list 조회
    @GetMapping("/list")
    public String list(Model model, Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);
        //getPageable -> page 정보를 사용할 수 있음. getTotalElements -> 전체 데이터 건수, getTotalPages -> 총 page수
        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4); //4건씩 보이기
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id) {
        if(id == null) {
            model.addAttribute("board", new Board());
        } else {
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }
        return "board/form";
    }

    @PostMapping("/form")   //@Valid 유효성 검사 어노테이션
    public String greetingSubmit(@Valid Board board, BindingResult bindingResult)  {
        boardValidator.validate(board, bindingResult);
        if (bindingResult.hasErrors()) {
            return "board/form";
        }

        boardRepository.save(board);
        return "redirect:/board/list";
    }
}
