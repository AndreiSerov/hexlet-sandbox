package org.example;

import io.javalin.Javalin;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.example.config.AppConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author andreiserov
 */
class AppTest {

    private static Javalin app;
    private static String baseUrl;

    @BeforeAll
    public static void beforeAll() {
        app = AppConfig.setup().start(0);

        int port = app.port();
        baseUrl = "http://localhost:" + port;
    }

    @AfterAll
    public static void afterAll() {
        app.stop();
    }

    @Test
    void testIndex() {
        var response = Unirest.get(baseUrl + "/").asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }


    @SuppressWarnings("rawtypes")
    private HttpResponse postUrl(String baseUrl, String stubUrl) {
        return Unirest
            .post(baseUrl)
            .field("url", stubUrl)
            .asEmpty();
    }
}
