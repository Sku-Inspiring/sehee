package org.zerock.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import java.util.List;

public interface BoardMapper {

    // 재고교환 페이지 전체조회
    public List<BoardVO> getList();

    // 재고교환 페이지 전체조회(페이징처리)
    public List<BoardVO> getListWithPaging(Criteria cri);

    // 재고교환 페이지 생성
    public void insert(BoardVO board);

    // 재고교환 페이지 생성 (PK)
    public void insertSelectKey(BoardVO board);

    // 재고교환 페이지 조회
    public BoardVO read(Long bno);

    // 재고교환 페이지 삭제
    public int delete(Long bno);

    // 재고교환 페이지 수정
    public int update(BoardVO board);

    // board 테이블 전체 컬럼 수 세기
    public int getTotalCount(Criteria cri);

    //2개 이상의 파라미터를 받을 떄는 @Param어노테이션 사용
    public void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);
}
