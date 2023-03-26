package exercise.controllers;

import exercise.domain.Article;
import exercise.domain.Category;
import exercise.domain.query.QArticle;
import exercise.domain.query.QCategory;
import io.ebean.PagedList;
import io.javalin.http.Handler;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public final class ArticleController {

    public static Handler listArticles = ctx -> {
        int page = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        int rowsPerPage = 10;
        int offset = (page - 1) * rowsPerPage;

        PagedList<Article> pagedArticles = new QArticle()
                .setFirstRow(offset)
                .setMaxRows(rowsPerPage)
                .orderBy()
                .id.asc()
                .findPagedList();

        List<Article> articles = pagedArticles.getList();

        ctx.attribute("articles", articles);
        ctx.attribute("page", page);
        ctx.render("articles/index.html");
    };

    public static Handler newArticle = ctx -> {
        List<Category> categories = new QCategory().findList();
        ctx.attribute("categories", categories);
        ctx.render("articles/new.html");
    };

    public static Handler createArticle = ctx -> {
        String title = ctx.formParam("title");
        String body = ctx.formParam("body");
        Long categoryId = ctx.formParamAsClass("categoryId", Long.class).getOrDefault(null);

        Category category = new QCategory()
                .id.equalTo(categoryId)
                .findOne();

        Article article = new Article(title, body, category);
        article.save();

        ctx.sessionAttribute("flash", "Статья успешно создана");
        ctx.redirect("/articles");
    };

    public static Handler showArticle = ctx -> {
        long id = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);

        Article article = new QArticle()
                .id.equalTo(id)
                .findOne();

        ctx.attribute("article", article);
        ctx.render("articles/show.html");
    };

    public static Handler editArticle = ctx -> {
        // BEGIN
        final Long id = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);

        if (id == null) {
            ctx.res.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        final List<Category> categories = new QCategory().findList();
        final Article article = new QArticle().id.equalTo(id).findOne();

        ctx.attribute("categories", categories);
        ctx.attribute("article", article);
        ctx.render("articles/edit.html");
        // END
    };

    public static Handler updateArticle = ctx -> {
        // BEGIN
        final Long id = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);
        final String title = ctx.formParam("title");
        final String body = ctx.formParam("body");
        final String categoryId = ctx.formParam("categoryId");

        if (id == null) {
            ctx.res.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        new QArticle().id.equalTo(id)
                .asUpdate()
                .set("title", title)
                .set("body", body)
                .set("category", categoryId)
                .update();

        ctx.sessionAttribute("flash", "Статья успешно создана");
        ctx.redirect("/articles");
        // END
    };

    public static Handler deleteArticle = ctx -> {
        // BEGIN
        final Long id = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);

        if (id == null) {
            ctx.res.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        Article article = new QArticle().id.equalTo(id).findOne();

        if (article == null) {
            ctx.res.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

        ctx.attribute("article", article);
        ctx.render("articles/delete.html");
        // END
    };

    public static Handler destroyArticle = ctx -> {
        // BEGIN
        final Long id = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);

        if (id == null) {
            ctx.res.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        new QArticle().id.equalTo(id).delete();

        ctx.sessionAttribute("flash", "Статья успешно удалена");
        ctx.redirect("/articles");
        // END
    };
}
