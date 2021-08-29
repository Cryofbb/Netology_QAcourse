package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class Card {
        private String number;
        private String month;
        private String year;
        private String holder;
        private String cvc;
    }

    public static Card getValidCard() {
        return new Card("4444 4444 4444 4441", getMonth(1), getYear(1), new Faker(new Locale("usa")).name().fullName(), "123");
    }

    public static Card getDeclinedCard() {
        return new Card("4444 4444 4444 4442", getMonth(1), getYear(1), new Faker(new Locale("usa")).name().fullName(), "123");
    }

    public static Card getFakeCard() {
        var faker = new Faker(new Locale("usa"));
        return new Card(faker.finance().creditCard(), getMonth(1), getYear(1), faker.name().fullName(), "123");
    }

    public static Card getInvalidHolderCard() {
        return new Card("4444 4444 4444 4441", getMonth(1), getYear(1), new Faker(new Locale("ru")).name().fullName(), "123");
    }

    public static Card getInvalidYearCard() {
        return new Card("4444 4444 4444 4441", getMonth(1), getYear(10), new Faker(new Locale("ru")).name().fullName(), "123");
    }

    public static Card getPastYearCard() {
        return new Card("4444 4444 4444 4441", getMonth(1), getPastYear(), new Faker(new Locale("ru")).name().fullName(), "123");
    }

    public static Card getPastMonthCard() {
        return new Card("4444 4444 4444 4441", getPastMonth(), getYear(3), new Faker(new Locale("ru")).name().fullName(), "123");
    }

    public static String getMonth(int month) {
        return LocalDate.now().plusMonths(month).format(DateTimeFormatter.ofPattern(("MM")));
    }

    public static String getYear(int year) {
        return LocalDate.now().plusYears(year).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getPastYear(){
        return LocalDate.now().minusYears(5).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getPastMonth(){
        return LocalDate.now().minusMonths(5).format(DateTimeFormatter.ofPattern("MM"));
    }
}
