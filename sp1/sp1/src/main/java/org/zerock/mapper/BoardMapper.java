// mapper interface로 메소드를 선언하고 SQL에 대한 처리는 xml파일에서 이루어짐

package org.zerock.mapper;

import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import java.util.List;

public interface BoardMapper {


    public List<BoardVO> getList();

    public List<BoardVO> getListWithPaging(Criteria cri);

    public void insert(BoardVO board);

    public void insertSelectKey(BoardVO board);

    public BoardVO read(Long bno);

    public int delete(Long bno);

    public int update(BoardVO board);
}
