package org.example.service.impl;

import org.example.configuration.UserInput;
import org.example.service.FireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * @author andreiserov
 */
@Service
public class FireServiceImpl implements FireService {

    private static List<String> words = List.of("fire", "boom");

    private final UserInput input;

    public FireServiceImpl(UserInput input) {
        this.input = input;
    }

    public boolean isFire(String s) {
        return words.contains(s.toLowerCase());
    }

    public void call() {
        System.out.println("What is your address?");
        final String address = input.read();
        System.out.println("Fireman comes to your address: " + address);
    }
}
