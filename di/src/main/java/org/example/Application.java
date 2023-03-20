package org.example;

import org.example.configuration.UserInput;
import org.example.service.FireService;
import org.example.service.PoliceService;
import org.example.service.impl.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.lang.annotation.Inherited;

/**
 * @author andreiserov
 */
@Component
public class Application {

    private final UserInput input;

    private final HelloService helloService;
    private final PoliceService policeService;
    private final FireService fireService;

    public Application(UserInput input, HelloService helloService, PoliceService policeService, FireService fireService) {
        this.input = input;
        this.helloService = helloService;
        this.policeService = policeService;
        this.fireService = fireService;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("org.example");
        context.refresh();

        var application = context.getBean(Application.class);
        application.run();
    }

    private void run() {
        while (true) {
            final String s = input.read();
            if (s.startsWith("exit")) { break; }

            if (policeService.isPolice(s)) {
                policeService.call();
            } else if (fireService.isFire(s)) {
                fireService.call();
            } else {
                helloService.hello(s);
            }
        }
    }
}


@Component
@Inherited
@ComponentScan(
    basePackages = "org.example"
)
@interface SpringApplication {
}
