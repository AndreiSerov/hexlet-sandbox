package org.example.field_example;

import java.security.DrbgParameters;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static java.security.DrbgParameters.Capability.PR_AND_RESEED;

/**
 * @author andreiserov
 */
public class User {

    private String name;
    private String password;
    private int salt;

    public KaliaField kaliaField;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.salt = calculateHash();
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getSalt() {
        return salt;
    }

    private void setKaliaField() {
        System.out.println("setKaliaField is called");
        kaliaField = new KaliaField();
    }

    private void setKaliaField(String s) {
        System.out.println("setKaliaField is called with argument: " + s );
    }
    private int calculateHash() {
        try {
            return SecureRandom
                .getInstance(
                    "DRBG",
                    DrbgParameters.instantiation(256, PR_AND_RESEED, null)
                )
                .nextInt();
        } catch (NoSuchAlgorithmException e) {
            return 1234124;
        }
    }
}


class KaliaField {

    @Override
    public String toString() {
        return "KaliaField";
    }
}