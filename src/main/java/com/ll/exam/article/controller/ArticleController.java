package com.ll.exam.article.controller;


import com.ll.exam.util.Rq;
import com.ll.exam.annotation.Autowired;
import com.ll.exam.annotation.Controller;
import com.ll.exam.annotation.GetMapping;
import com.ll.exam.article.service.ArticleService;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/usr/article/list/{broadCode}")
    public void showList(Rq rq){
        rq.println("게시물 리스트");
    }

    @GetMapping("/usr/article/detail/{boardCode}/{id}")
    public void showDetail(Rq rq){
        rq.println("게시물 상세페이지<br>");

        String boardCode = rq.getParam("boardCode", ""); // 곧 기능 구현
        long id = rq.getLongParam("id", -1); // 곧 기능 구현

        rq.println("%s 게시판, %d번 게시물".formatted(boardCode, id));
    }

    @GetMapping("/usr/article/modify/{boardCode}/{id}")
    public void showModify(Rq rq){
        rq.println("게시물 수정페이지<br>");

        String boardCode = rq.getParam("boardCode", ""); // 곧 기능 구현
        long id = rq.getLongParam("id", -1); // 곧 기능 구현

        rq.println("%s 게시판, %d번 게시물".formatted(boardCode, id));
    }
}
