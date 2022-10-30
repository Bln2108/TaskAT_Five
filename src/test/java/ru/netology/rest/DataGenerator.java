package ru.netology.rest;

import com.github.javafaker.Faker;
import lombok.Value;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    private static Faker faker;

    @BeforeEach
    void setup() {
        faker = new Faker(new Locale("ru"));
    }

    public static String generateDate(int shift) {
        String date = LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }

    public static String generateCity(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.address().cityName();
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String name = faker.name().fullName();
        return name;
    }

    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

    public static class Registration {
        private Registration() {
        }
 /*
        public static UserInfo generateUser(String locale) {
            Faker faker = new Faker(new Locale(locale));
           return new UserInfo(
                    faker.address().cityName(),
                    faker.name().fullName(),
                    faker.phoneNumber().phoneNumber()
                    );
        }*/
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}