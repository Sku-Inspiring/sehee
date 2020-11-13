package org.zerock.service;

import org.zerock.domain.BoardVO;

import java.util.List;

public interface BoardService {
    public void register(BoardVO board);

    //특정 목록 조회
    public BoardVO get(Long bno);

    public boolean modify(BoardVO board);

    public boolean remove(Long bno);

    //전체 목록 조회
    public List<BoardVO> getList();
}
