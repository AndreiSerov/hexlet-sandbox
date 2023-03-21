package org.example.service.impl;

import org.example.configuration.IUserInput;
import org.example.configuration.ScannerInput;
import org.example.service.FireService;
import org.example.service.PoliceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author andreiserov
 */
@Service
public class PoliceServiceImpl implements PoliceService {
    private static List<String> words = List.of("police", "criminal");

    private final IUserInput input;

    private FireService fireService;

    // constructor injection
    public PoliceServiceImpl(@ScannerInput IUserInput input) {
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

    // setter injection
    @Autowired
    public void setFireService(FireService fireService) {
        this.fireService = fireService;
    }
}
