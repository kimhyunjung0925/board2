package com.study.board2.service;

import com.study.board2.entity.Board;
import com.study.board2.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    //글 작성 처리, 글 수정처리
    public void boardWrite(Board board, MultipartFile file) throws Exception{
// 파일 처리 로직
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
        if (file != null && !file.isEmpty()) {
            //파일경로지정
//            String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

            //파일 이름 랜덤 생성
            UUID uuid = UUID.randomUUID();

            //랜덤파일이릉 + 들어온파일이름
            String fileName = uuid + "_" + file.getOriginalFilename();

            //파일 껍데기 생성자
            File saveFile = new File(projectPath, fileName);
            file.transferTo(saveFile);

            board.setFilename(fileName);
            board.setFilepath("/files/" + fileName);

        }

        boardRepository.save(board);
    }

    //게시글 리스트 처리 (페이징)
    //pageable 처리때문에 리턴값 오류 List<Board>에서 Page<Board>로 설정해줘야함
    public Page<Board> boardList(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    //게시글 검색 처리
    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable){
        return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }

    //게시글 상세페이지 처리
    public Board boardView(Integer iboard){
        return boardRepository.findById(iboard).get();
    }

    //특정 게시글 삭제
    public void boardDelete(Integer iboard){ boardRepository.deleteById(iboard);}

}
