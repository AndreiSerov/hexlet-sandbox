package org.example.service.impl;

import org.example.configuration.UserInput;
import org.example.service.PoliceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * @author andreiserov
 */
@Service
public class PoliceServiceImpl implements PoliceService {
    private static List<String> words = List.of("police", "criminal");

    private final UserInput input;

    public PoliceServiceImpl(UserInput input) {
        this.input = input;
    }

    public boolean isPolice(String input) {
        return words.contains(input);
    }

    public void call() {
        System.out.println("What is your address?");
        final String address = input.read();
        System.out.println("Police comes to your address: " + address);
    }
}
