package org.example.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

@Component
public class UserInput {

    @Autowired
    private Scanner sc;

    private final BufferedReader reader;

    public UserInput(BufferedReader reader) {
        this.reader = reader;
    }

    public String read() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}