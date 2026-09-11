package org.example;

import org.example.configuration.IUserInput;
import org.example.service.FireService;
import org.example.service.PoliceService;
import org.example.service.impl.CountryFire;
import org.example.service.impl.HelloService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author andreiserov
 */
@SpringApplication
public class Application {

    private final IUserInput input;

    // add image with container
    private final HelloService helloService;
    private final PoliceService policeService;
    private final @Qualifier("country") FireService fireService;

    public Application(IUserInput input,
                       HelloService helloService,
                       PoliceService policeService,
                       @CountryFire FireService fireService
    ) {
        this.input = input;
        this.helloService = helloService;
        this.policeService = policeService;
        this.fireService = fireService;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class); // IoC - container
        var application = context.getBean(Application.class);

        application.run();
    }

    private void run() {
        while (true) {
            final String s = input.read();
            if (s.startsWith("exit")) {
                break;
            }

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

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
@ComponentScan(
    basePackages = "org.example"
)
@interface SpringApplication {
}

