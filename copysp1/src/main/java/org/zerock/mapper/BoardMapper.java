package org.zerock.mapper;

import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;

import java.util.List;

public interface BoardMapper {
    //@Select("select * from tbl_board where bno > 0")
    public List<BoardVO> getList();

    //PK 몰라도 insert 할 수 있는 경우
    public void insert(BoardVO board);

    //PK을 알아야 insert 할 수 있는 경우
    public void insertSelectKey(BoardVO board);

    //pk로 데이터 읽기
    public BoardVO read(Long bno);

    //데이터 삭제
    public int delete(Long bno);

    //데이터 수정
    public int update(BoardVO board);
}
