package com.ll.exam.article.controller;


import com.ll.exam.annotation.Autowired;
import com.ll.exam.annotation.Controller;
import com.ll.exam.annotation.GetMapping;
import com.ll.exam.article.service.ArticleService;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("usr/article/list")
    public void showList(){

    }
    public ArticleService getArticleServiceForTest() {
        return articleService;
    }
}
