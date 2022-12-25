package org.example.config;

import io.javalin.Javalin;
import org.example.controller.ContactController;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

/**
 * @author andreiserov
 */
public class Router {

    public static void register(Javalin app) {
        app.routes(() -> path("contacts", () -> {
            get(ContactController::getAll);
            post(ContactController.create);
            path("{id}", () -> {
                get(ContactController::getUserById);
                post(ContactController.update);
            });

        }));
    }
}