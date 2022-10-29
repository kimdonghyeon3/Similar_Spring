package com.ll.exam.article.repository;


import com.ll.exam.annotation.Autowired;
import com.ll.exam.annotation.Repository;
import com.ll.exam.article.dto.ArticleDto;
import com.ll.exam.db.MyDb;
import com.ll.exam.db.SecSql;

import java.util.List;

@Repository
public class ArticleRepository {
    @Autowired
    private MyDb myDb;

    public List<ArticleDto> getArticles() {
        SecSql sql = myDb.genSecSql();
        sql
                .append("SELECT *")
                .append("FROM article")
                .append("ORDER BY id DESC");
        return sql.selectRows(ArticleDto.class);
    }
}
