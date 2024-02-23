package com.study.board2.Controller;

import com.study.board2.entity.Board;
import com.study.board2.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/write")
    public String boardWriteForm() {

        return "/board/Write";
    }

    @PostMapping("/writePro")
    public String boardWritePro(Board board, Model model, MultipartFile file) throws Exception {
        boardService.boardWrite(board, file);

        model.addAttribute("message","글작성이 완료되었습니다.");
        model.addAttribute("searchUrl","/board/list");
        return "message";
    }

    @GetMapping("/list")
    public String boardList(Model model,
                            @PageableDefault(page=0,size = 15,sort = "iboard", direction = Sort.Direction.DESC) Pageable pageable,
                            String searchKeyword){
        Page<Board> list = null;

        //검색하는 단어가 없을 때(기본)
        if(searchKeyword == null){
            list = boardService.boardList(pageable);
        //검색하는 단어가 있을 때(검색시)
        } else {
            list = boardService.boardSearchList(searchKeyword,pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1); //둘중 큰거 반환
        int endPage = Math.min(nowPage + 5, list.getTotalPages());
        int pre = pageable.previousOrFirst().getPageNumber();
        int next = pageable.next().getPageNumber();

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        model.addAttribute("pre", pre);
        model.addAttribute("next", next);

        return "board/List";
    }

    @GetMapping("/view")
    public String boardView(Model model, Integer iboard){
        model.addAttribute("board", boardService.boardView(iboard));

        return "board/View";

    }

    @GetMapping("/delete")
    public String boardDelete(Integer iboard){
        boardService.boardDelete(iboard);
        return "redirect:/board/list";
    }

    @GetMapping("/modify/{iboard}")
    //PathVariable 쓰면 쿼리스트링이 아니라 깔끔하게 /위에 iboard만 넣어서 작동
    public String boardModify(@PathVariable("iboard") Integer iboard, Model model){
        model.addAttribute("board", boardService.boardView(iboard));
        return "board/Modify";
    }

    @PostMapping("/update/{iboard}")
    public String boardUpdate(@PathVariable("iboard") Integer iboard, Board board, Model model, MultipartFile file) throws Exception {

        Board boardTemp  = boardService.boardView(iboard);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        boardService.boardWrite(boardTemp,file);

        model.addAttribute("message","글수정이 완료되었습니다.");
        model.addAttribute("searchUrl","/board/list");

        return "message";
    }
}
