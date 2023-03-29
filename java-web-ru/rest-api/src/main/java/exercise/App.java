package exercise;

import exercise.controllers.UserController;
import io.javalin.Javalin;
import io.javalin.core.validation.ValidationException;

import static io.javalin.apibuilder.ApiBuilder.crud;

public final class App {

    private static int getPort() {
        String port = System.getenv().getOrDefault("PORT", "8000");
        return Integer.valueOf(port);
    }

    private static void addRoutes(Javalin app) {

        app.get("/api/v1", ctx -> ctx.result("REST API"));

        // BEGIN
        app.routes(() -> crud("/api/v1/users/{user-id}", new UserController()));
        // END
    }

    public static Javalin getApp() {

        Javalin app = Javalin.create(config -> {
            config.enableDevLogging();
        });

        // Устанавливаем, что при возникновении ошибок валидации
        // будет отправлен JSON с ошибками валидации
        // и установлен код ответа 422
        app.exception(ValidationException.class, (e, ctx) -> {
            ctx.json(e.getErrors()).status(422);
        });

        addRoutes(app);

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(getPort());
    }
}
