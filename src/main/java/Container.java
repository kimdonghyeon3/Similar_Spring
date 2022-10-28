import article.controller.ArticleController;

public class Container {
    public static ArticleController getArticleController() {
        return new ArticleController();
    }
}
