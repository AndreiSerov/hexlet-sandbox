package org.example.config;

import io.javalin.Javalin;
import org.example.domain.User;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;

/**
 * @author andreiserov
 */
public class Router {

    public static void register(Javalin app) {
        app.post("/users", ctx -> {
            final User user = ctx.bodyAsClass(User.class);
            user.save();
            ctx.json(user);
        });
    }
}