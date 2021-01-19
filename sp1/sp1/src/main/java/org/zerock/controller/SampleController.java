package org.zerock.controller;//package org.zerock.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/sample/*")
@Log4j
public class SampleController {

    @GetMapping("/doA")
    public void doA(){
        log.info("dhjksajd");
    }

}