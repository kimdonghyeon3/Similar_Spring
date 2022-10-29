package com.ll.exam;

import com.ll.exam.annotation.Controller;
import com.ll.exam.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/usr/home/main")
    public void showMain(Rq rq) {
        rq.println("메인 페이지");
    }
}
