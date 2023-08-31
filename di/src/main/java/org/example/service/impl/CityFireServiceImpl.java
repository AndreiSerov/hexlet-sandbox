package org.example.service.impl;

import org.example.configuration.IUserInput;
import org.example.service.FireService;
import org.example.service.PoliceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityFireServiceImpl implements FireService {

    private static List<String> words = List.of("fire", "boom");

    @Autowired
    private IUserInput input;

    // field injection
    @Autowired
    private PoliceService policeService;

    public boolean isFire(String s) {
        return words.contains(s.toLowerCase());
    }

    public void call() {
        System.out.println("What is your address?");
        final String address = input.read();
        System.out.println("City Fireman comes to your address: " + address);
    }



    /**
     * CGLIB and JDKProxy
     * Жизненный цикл бина /
     * Scope бинов /
     *
     * Сколько ваш сервисов держит запросов ?
     *
     */
}