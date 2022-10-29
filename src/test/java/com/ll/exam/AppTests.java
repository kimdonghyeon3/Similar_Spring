package com.ll.exam;

import com.ll.exam.spring.Container;
import com.ll.exam.spring.ControllerManager;
import com.ll.exam.spring.RouteInfo;
import com.ll.exam.util.Util;
import com.ll.exam.article.controller.ArticleController;
import com.ll.exam.article.repository.ArticleRepository;
import com.ll.exam.article.service.ArticleService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTests {
    @Test
    public void ioc__articleController() {
        ArticleController articleController = Container.getObj(ArticleController.class);

        assertThat(articleController).isNotNull();
    }

    @Test
    public void ioc__articleController_singleton() {
        ArticleController articleController1 = Container.getObj(ArticleController.class);
        ArticleController articleController2 = Container.getObj(ArticleController.class);

        assertThat(articleController1).isEqualTo(articleController2);
    }

    @Test
    public void ioc__Controller_Scan() {
        List<String> names = Container.getControllerNames();

        assertThat(names).contains("home");
        assertThat(names).contains("article");
    }

    @Test
    public void ioc__articleService() {
        ArticleService articleService = Container.getObj(ArticleService.class);

        assertThat(articleService).isNotNull();
    }

    @Test
    public void ioc__articleService__singleton() {
        ArticleService articleService1 = Container.getObj(ArticleService.class);
        ArticleService articleService2 = Container.getObj(ArticleService.class);

        assertThat(articleService2).isEqualTo(articleService1);
    }


    @Test
    public void articleController_autowired_articleService() {
        ArticleController articleController = Container.getObj(ArticleController.class);

        ArticleService articleService = Util.reflection.getFieldValue(articleController, "articleService", null);

        assertThat(articleService).isNotNull();
    }

    @Test
    public void articleService_autowired_articleRepository() {
        ArticleService articleService = Container.getObj(ArticleService.class);

        ArticleRepository articleRepository = Util.reflection.getFieldValue(articleService, "articleRepository", null);

        assertThat(articleRepository).isNotNull();
    }

    @Test
    public void controllerManager__GetMappings_scan() {
        ControllerManager.init(); // 클래스를 강제로 로딩되게 하려는 목적
    }

    @Test
    public void controllerManager__GetMapping_Count() {
        Map<String, RouteInfo> routeInfos = ControllerManager.getRouteInfosForTest();

        assertThat(routeInfos.size()).isEqualTo(4);
    }

}
