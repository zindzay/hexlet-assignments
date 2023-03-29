package exercise;

import exercise.domain.User;
import io.ebean.DB;
import io.ebean.Database;
import io.javalin.Javalin;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    // BEGIN
    @BeforeAll
    static void beforeAll() {
        app = App.getApp();
        app.start(0);
        int port = app.port();
        baseUrl = "http://localhost:" + port;
    }

    @AfterAll
    public static void afterAll() {
        app.stop();
    }
    // END

    // Между тестами база данных очищается
    // Благодаря этому тесты не влияют друг на друга
    @BeforeEach
    void beforeEach() {
        Database db = DB.getDefault();
        db.truncate("user");
        User existingUser = new User("Wendell", "Legros", "a@a.com", "123456");
        existingUser.save();
    }

    @Test
    void testUsers() {

        // Выполняем GET запрос на адрес http://localhost:port/users
        HttpResponse<String> response = Unirest
                .get(baseUrl + "/users")
                .asString();
        // Получаем тело ответа
        String content = response.getBody();

        // Проверяем код ответа
        assertThat(response.getStatus()).isEqualTo(200);
        // Проверяем, что страница содержит определенный текст
        assertThat(response.getBody()).contains("Wendell Legros");
    }

    @Test
    void testNewUser() {

        HttpResponse<String> response = Unirest
                .get(baseUrl + "/users/new")
                .asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    // BEGIN
    @Test
    void testCreateUser() {
        String firstName = "Aleksandr";
        String lastName = "Vasiliev";
        String email = "aleks@yandex.ru";
        String password = "123456";
        HttpResponse<?> responsePost = Unirest
                .post(baseUrl + "/users")
                .field("firstName", firstName)
                .field("lastName", lastName)
                .field("email", email)
                .field("password", password)
                .asEmpty();

        assertThat(responsePost.getStatus()).isEqualTo(302);

        HttpResponse<String> responseGet = Unirest
                .get(baseUrl + "/users")
                .asString();

        String content = responseGet.getBody();
        assertThat(content)
                .contains("Aleksandr Vasiliev")
                .contains("aleks@yandex.ru");
    }

    @Test
    void testCreateUserError() {
        String firstName = "";
        String lastName = "";
        String email = "jon";
        String password = "123";
        HttpResponse<String> responsePost = Unirest
                .post(baseUrl + "/users")
                .field("firstName", firstName)
                .field("lastName", lastName)
                .field("email", email)
                .field("password", password)
                .asString();

        assertThat(responsePost.getStatus()).isEqualTo(422);
        assertThat(responsePost.getBody())
                .contains("jon")
                .contains("123")
                .contains("Имя не должно быть пустым")
                .contains("Фамилия не должна быть пустой")
                .contains("Должно быть валидным email")
                .contains("Пароль должен содержать не менее 4 символов");

        HttpResponse<String> responseGet = Unirest
                .get(baseUrl + "/users")
                .asString();

        String content = responseGet.getBody();
        assertThat(content).doesNotContain("jon");
    }
    // END
}
