package com.ll.exam.article.controller;


import com.ll.exam.Rq;
import com.ll.exam.annotation.Autowired;
import com.ll.exam.annotation.Controller;
import com.ll.exam.annotation.GetMapping;
import com.ll.exam.article.service.ArticleService;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("usr/article/list/{broadCode}")
    public void showList(Rq rq){
        rq.println("게시물 리스트");
    }

    @GetMapping("/usr/article/detail/{broadCode}/{id}")
    public void showDetail(Rq rq){
        rq.println("게시물 상세페이지");
    }

    @GetMapping("/usr/article/modify/{broadCode}/{id}")
    public void showModify(Rq rq){
        rq.println("게시물 수정페이지");

        long id = rq.getLongPathValueByIndex(1,-1);
    }
}
