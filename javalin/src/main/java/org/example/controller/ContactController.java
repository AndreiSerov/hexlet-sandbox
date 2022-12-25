package org.example.controller;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpCode;
import io.javalin.http.NotFoundResponse;
import org.example.domain.Contact;

import java.util.Optional;

/**
 * @author andreiserov
 */
public class ContactController {

    public static Handler update = ctx -> {
        final Long id = getId(ctx);
        final Contact request = ctx.bodyAsClass(Contact.class);
        final Contact contact = Contact.find.byId(request.getId());

        if (contact != null) {
            request.save();
        } else {
            ctx.status(HttpCode.NOT_FOUND);
        }
    };

    public static void getAll(Context ctx) {
        final var contacts = Contact.find.all();
        ctx.json(contacts);
    }

    public static void getUserById(Context ctx) {
        final Contact obj = Contact.find.byId(getId(ctx));
        if (obj != null) {
            ctx.json(obj);
        } else {
            ctx.status(HttpCode.NOT_FOUND);
        }
    }

    public static Handler create = ctx -> {
        final Contact request = ctx.bodyAsClass(Contact.class);
        request.save();
        ctx.status(HttpCode.CREATED);
    };

    private static Long getId(Context ctx) {
        return Optional.ofNullable(ctx.pathParamAsClass("id", Long.class).getOrDefault(null))
            .orElseThrow(NotFoundResponse::new);
    }
}
