package org.zerock.service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.config.RootConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class})
@Log4j
public class SampleServiceTests {
    @Setter(onMethod = @__({@Autowired}))
    private SampleService service;

    @Test
    public void testClass(){
        log.info(service);
        log.info(service.getClass().getName());
    }

    @Test
    public void testAdd() throws Exception{
        log.info(service.doAdd("123" , "456"));
    }

    //파라미터의 값을 잘못 넘겨줘서 예외 발생
    @Test
    public void testAddError() throws Exception{
        log.info(service.doAdd("123", "ABC"));
    }
}
