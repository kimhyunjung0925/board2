package com.study.board2.Controller;

import com.study.board2.Board2Application;
import com.study.board2.entity.Board;
import com.study.board2.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.GetExchange;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write")
    public String boardWriteForm() {
        return "boardWrite";
    }

    @PostMapping("/board/writePro")
    public String boardWritePro(Board board) {
        boardService.boardWrite(board);
        return "";
    }

    @GetMapping("/board/list")
    public String boardList(Model model){
        model.addAttribute("list",boardService.boardList());
        return "boardList";
    }

    @GetMapping("/board/view")
    public String boardView(Model model, Integer iboard){
        model.addAttribute("board", boardService.boardView(iboard));
        return "boardView";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Integer iboard){
        boardService.boardDelete(iboard);
        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{iboard}")
    //PathVariable 쓰면 쿼리스트링이 아니라 깔끔하게 /위에 iboard만 넣어서 작동
    public String boardModify(@PathVariable("iboard") Integer iboard, Model model){
        model.addAttribute("board", boardService.boardView(iboard));
        return "boardModify";
    }

    @PostMapping("/board/update/{iboard}")
    public String boardUpdate(@PathVariable("iboard") Integer iboard, Board board) {

        Board boardTemp  = boardService.boardView(iboard);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        boardService.boardWrite(boardTemp);

        return "redirect:/board/list";
    }
}
