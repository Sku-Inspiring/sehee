package org.zerock.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//servlet-context.xml에서 어노테이션 인식하여 컨트롤러로 등록
@Controller
@Log4j
public class SampleController {
    //value값에 맞는 url이 들어왔을 때 해당 메소드를 실행
    @RequestMapping(value = "/")
    public String doA(){
        return "index";
    }
}
