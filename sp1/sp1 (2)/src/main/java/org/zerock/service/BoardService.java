package org.zerock.service;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import java.util.List;

public interface BoardService {

    //재고교환 페이지 생성
    public void register(BoardVO board);

    // 재고교환 페이지 조회
    public BoardVO get(Long bno);

    // 재고교환 페이지 수정
    public boolean modify(BoardVO board);

    // 재고교환 페이지 삭제
    public boolean remove(Long bno);

    // 재고교환 페이지 전체조회(페이징처리)
    public List<BoardVO> getList(Criteria cri);

    // board 테이블 전체 컬럼 수 세기
    public int getTotal(Criteria cri);
}
