package org.zerock.service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.BoardMapper;

import java.util.List;

@Log4j
//비지니스 계층임을 표시하기 위해 사용
@Service
//인터페이스 내용 자동으로 주입(BoardMapper인터페이스를 주입받는 생성자가 만들어짐)
//@AllArgsConstructor
public class BoardServiceImpl implements BoardService{

    @Setter(onMethod=@__({@Autowired}))
    private BoardMapper mapper;

    @Override
    public void register(BoardVO board){
        log.info("register....." + board);
        mapper.insertSelectKey(board);
    }

    @Override
    public BoardVO get(Long bno) {
        log.info("get....." + bno);
        return mapper.read(bno);
    }
    @Override
    public boolean modify(BoardVO board) {
        log.info("modify....." + board);
        return mapper.update(board) == 1;
    }
    @Override
    public boolean remove(Long bno) {
        log.info("remove....." + bno);
        return mapper.delete(bno) == 1;
    }
    @Override
    public List<BoardVO> getList() {
        log.info("getlist.....");
        return mapper.getList();
    }
}
