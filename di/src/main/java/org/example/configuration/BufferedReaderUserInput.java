package org.example.configuration;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;

@Primary
@Component
public class BufferedReaderUserInput implements IUserInput {

    private final BufferedReader reader;

    public BufferedReaderUserInput(BufferedReader reader) {
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