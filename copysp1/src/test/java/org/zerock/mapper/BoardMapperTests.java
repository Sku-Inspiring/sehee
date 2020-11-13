package org.zerock.mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {

    @Setter(onMethod=@__({@Autowired}))
    private BoardMapper mapper;

    @Test
    public void testGetList(){
        mapper.getList().forEach(board -> log.info(board));
    }

    @Test
    public void testInsert() {
        BoardVO board = new BoardVO();
        board.setTitle("새로 작성하는 글");
        board.setContent("새로 작성하는 a내용");
        board.setWriter("newnew");

        mapper.insert(board);

        log.info(board);
    }
    @Test
    public void testInsertSelectKey() {
        BoardVO board = new BoardVO();
        board.setTitle("새로 작성하는 글 select key");
        board.setContent("새로 작성하는 내용 select key");
        board.setWriter("newnew");

        mapper.insertSelectKey(board);
        log.info(board);
    }

    @Test
    public void testRead() {
        //PK가 4인 데이터 조회
        BoardVO board = mapper.read(4L);
        log.info(board);
    }

    @Test
    public void testDelete() {
        //PK가 3인 데이터 삭제
        log.info("Delete" + mapper.delete(3L));
    }
    @Test
    public void testUpdate() {
        BoardVO board = new BoardVO();
        //PK가 5인 데이터 수정하기
        board.setBno(5L);
        board.setTitle("after update");
        board.setContent("popopopopo update");
        board.setWriter("user99");
        int count = mapper.update(board);
        log.info("count");
    }
}
