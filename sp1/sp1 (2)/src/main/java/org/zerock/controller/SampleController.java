package org.zerock.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zerock.domain.SampleVO;
import org.zerock.domain.Ticket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/sample")
@Log4j
public class SampleController {

    @GetMapping(value = "/getText" ,produces={MediaType.TEXT_PLAIN_VALUE})
    public String getText() {
        log.info("MIME TYPE: " + MediaType.TEXT_PLAIN_VALUE);
        return "안녕하세요";
    }

    @GetMapping(value="/getsample", produces={MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE })
    public SampleVO getSample() {
        return new SampleVO(112, "권", "세희");
    }

    // 여러 데이터를 한번에 전송하기 위해 리스트 객체로 만들기
    @GetMapping(value="/getList")
    public List<SampleVO> getList() {
        return IntStream.range(1,10).mapToObj(i -> new SampleVO(i, i + "First", i + "Last")).collect(Collectors.toList());
    }

    // Map으로
    @GetMapping(value="/getMap")
    public Map<String, SampleVO> getMap() {
        Map<String, SampleVO> map = new HashMap<>();
        map.put("First", new SampleVO(111,"권","세히"));
        return map;
    }

    // ResponseEntity
    @GetMapping(value = "/check", params={"height", "weight"})
    public ResponseEntity<SampleVO> check(Double height, Double weight){
        SampleVO vo = new SampleVO(0, "" + height, "" + weight);

        ResponseEntity<SampleVO> result = null;

        if(height < 150){
            result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
        }else{
            result = ResponseEntity.status(HttpStatus.OK).body(vo);
        }
        return result;
    }

    // pathVariable 사용하기
    @GetMapping("/product/{cat}/{pid}")
    public String[] getPath(@PathVariable("cat") String cat, @PathVariable("pid") Integer pid){
        return new String[] {"category: " + cat, "productid: " + pid };
    }

    // @RequestBody
    @PostMapping("/ticket")
    public Ticket convert(@RequestBody Ticket ticket){
        log.info("convert.......ticket" + ticket);
        return ticket;
    }
}

