package org.zerock.mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import java.util.List;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { org.zerock.config.RootConfig.class})
@Log4j
public class ReplyMapperTests {
    private Long[] bnoArr = {86L, 85L, 84L, 83L, 82L};
    @Setter(onMethod = @__({@Autowired}))
    private ReplyMapper mapper;

    @Test
    public void testMapper() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            ReplyVO vo = new ReplyVO();
            vo.setBno(bnoArr[i % 5]);
            vo.setReply("댓글 test" + i);
            vo.setReplyer("replyer" + i);

            mapper.insert(vo);
        });
    }
    @Test
    public void testRead() {
        Long targetBno = 1L;
        ReplyVO vo = mapper.read(targetBno);
        log.info(vo);
    }

    @Test
    public void testList(){
        Criteria cri = new Criteria();
        List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);
        replies.forEach(reply -> log.info(reply));
    }

    @Test
    public void testDelete(){
        Long targetBno = 1L;
        mapper.delete(targetBno);
    }
    @Test
    public void testUpdate(){
        Long targetBno = 10L;
        ReplyVO vo = mapper.read(targetBno);
        vo.setReply("Update Reply");
        int count = mapper.update(vo);
        log.info("Update Count: "+ count);
    }

    }


