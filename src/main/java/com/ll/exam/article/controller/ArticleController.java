package com.ll.exam.article.controller;

import com.ll.exam.annotation.Controller;
import com.ll.exam.annotation.GetMapping;
import com.ll.exam.article.service.ArticleService;

@Controller
public class ArticleController {

    private ArticleService articleService;

    @GetMapping("usr/com.ll.exam.article/list")
    public void showList(){

    }
}
