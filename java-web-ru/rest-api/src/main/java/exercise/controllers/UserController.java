package exercise.controllers;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.ebean.DB;
import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.List;

public class UserController implements CrudHandler {

    public void getAll(Context ctx) {
        // BEGIN
        List<User> users = new QUser()
                .orderBy().id.asc()
                .findList();

        String json = DB.json().toJson(users);
        ctx.json(json);
        // END
    }

    public void getOne(Context ctx, String id) {

        // BEGIN
        User user = new QUser()
                .id.equalTo(Integer.parseInt(id))
                .findOne();

        String json = DB.json().toJson(user);
        ctx.json(json);
        // END
    }

    public void create(Context ctx) {

        // BEGIN

        User user = ctx.bodyValidator(User.class)
                .check(it -> !it.getFirstName().isEmpty(), "Имя не должно быть пустым")
                .check(it -> !it.getLastName().isEmpty(), "Фамилия не должна быть пустой")
                .check(it -> EmailValidator.getInstance().isValid(it.getEmail()), "Email должен быть валидным")
                .check(it -> it.getPassword().length() >= 4 && StringUtils.isNumeric(it.getPassword()),
                        "Пароль должен быть не короче 4 символов и содержать только цифры")
                .get();

        user.save();
        // END
    }

    public void update(Context ctx, String id) {
        // BEGIN
        User user = DB.json().toBean(User.class, ctx.body());
        user.setId(id);
        user.update();
        // END
    }

    public void delete(Context ctx, String id) {
        // BEGIN
        new QUser().id.equalTo(Integer.parseInt(id)).delete();
        // END
    }
}
