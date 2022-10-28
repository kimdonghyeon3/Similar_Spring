import com.ll.exam.Container;
import com.ll.exam.article.controller.ArticleController;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTests {
    @Test
    public void ioc__articleController() {
        ArticleController articleController = Container.getArticleController();

        assertThat(articleController).isNotNull();
    }

    @Test
    public void ioc__articleController_singleton() {
        ArticleController articleController1 = Container.getArticleController();
        ArticleController articleController2 = Container.getArticleController();

        assertThat(articleController1).isEqualTo(articleController2);
    }

    @Test
    public void ioc__Controller_Scan() {
        List<String> names = Container.getControllerNames();

        assertThat(names).contains("home");
        assertThat(names).contains("article");
    }

}
