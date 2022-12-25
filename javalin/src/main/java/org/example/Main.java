package org.example;

import io.javalin.Javalin;
import org.example.config.AppConfig;

public class Main {
    public static void main(String[] args) {
        getApp().start();
    }

    public static Javalin getApp() {
        return AppConfig.setup();
    }
}