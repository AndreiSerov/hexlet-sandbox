package org.example.service.impl;

import org.example.configuration.IUserInput;
import org.example.service.FireService;
import org.example.service.PoliceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author andreiserov
 */
@CountryFire
@Service
public class CountryFireService implements FireService {

    private static List<String> words = List.of("fire", "boom");

    @Autowired
    private IUserInput input;


    public boolean isFire(String s) {
        return words.contains(s.toLowerCase());
    }

    @Override
    public void call() {
        System.out.println("What is your address?");
        final String address = input.read();
        System.out.println("CountryFireService comes to your address: " + address);
    }
}
