package org.example.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.javalin.Javalin;
import io.javalin.plugin.json.JavalinJackson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * @author andreiserov
 */
public class AppConfig {

    private static final Logger LOG = LoggerFactory.getLogger(AppConfig.class);


    private static final String PORT_FROM_ENV = System.getenv("PORT");
    private static final int PORT = (PORT_FROM_ENV != null) ? Integer.parseInt(PORT_FROM_ENV) : 7000;

    private static final ObjectMapper mapper = new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)
        .registerModule(new JavaTimeModule());

    private static final Javalin APP =  Javalin.create(config -> {
            config.enableDevLogging();
            config.jsonMapper(new JavalinJackson(mapper));

            config.requestLogger((ctx, ms) -> {
                LOG.info("Incoming request body is: {}.", ctx.body());
                LOG.info("Request processed in {} ms.", ms);
            });
        })
        .exception(Exception.class, (e, ctx) -> LOG.error(e.getMessage(), e));

    public static Javalin setup() {
        Objects.requireNonNull(APP.jettyServer()).setServerPort(PORT);
        Router.register(APP);

        APP.before(ctx -> ctx.attribute("ctx", ctx));

        return APP;
    }

}
