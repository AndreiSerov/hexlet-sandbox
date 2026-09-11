package org.example.service.impl;

import org.springframework.stereotype.Service;

/**
 * @author andreiserov
 */
@Service
public class HelloService {
    public void hello(String input) {
        System.out.println("Hello, " + input);
    }
}
