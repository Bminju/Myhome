package com.mincoder.myhome.controller;

import com.mincoder.myhome.model.Board;
import com.mincoder.myhome.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    //list 조회
    @GetMapping("/list")
    public String list(Model model) {
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) long id) {
        if("id" == null) {
            model.addAttribute("board", new Board());
        } else {
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }

        model.addAttribute("board", new Board());
        return "board/form";
    }

    @PostMapping("/form")
    public String greetingSubmit(@ModelAttribute Board board) {
        boardRepository.save(board);
        return "redirect:/board/list";
    }
}
