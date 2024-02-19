package com.study.board2.service;

import com.study.board2.entity.Board;
import com.study.board2.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;
    //글 작성 처리
    public void boardWrite(Board board){
        boardRepository.save(board);
    }

    //게시글 리스트 처리
    public List<Board> boardList(){
        return boardRepository.findAll();
    }

    //게시글 상세페이지 처리
    public Board boardView(Integer iboard){
        return boardRepository.findById(iboard).get();
    }

    //특정 게시글 삭제
    public void boardDelete(Integer iboard){ boardRepository.deleteById(iboard);}

}
