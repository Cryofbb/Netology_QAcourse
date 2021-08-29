package ru.netology.data;

import com.github.javafaker.CreditCardType;
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
        var faker = new Faker(new Locale("usa"));
        return new Card("4444 4444 4444 4441", getMonth(1), getYear(1), faker.name().fullName(), faker.random().nextInt(100,999).toString());
    }

    public static Card getDeclinedCard() {
        var faker = new Faker(new Locale("usa"));
        return new Card("4444 4444 4444 4442", getMonth(1), getYear(1), faker.name().fullName(), faker.random().nextInt(100,999).toString());
    }

    public static Card getFakeCard() {
        var faker = new Faker(new Locale("usa"));
        return new Card(faker.finance().creditCard(CreditCardType.MASTERCARD), getMonth(1), getYear(1), faker.name().fullName(), faker.random().nextInt(100,999).toString());
    }

    public static Card getInvalidHolderCard() {
        var faker = new Faker(new Locale("ru"));
        return new Card("4444 4444 4444 4441", getMonth(1), getYear(1), faker.internet().password(6,20,true,true), faker.random().nextInt(100,999).toString());
    }

    public static Card getPastYearCard() {
        var faker = new Faker(new Locale("usa"));
        return new Card("4444 4444 4444 4441", getMonth(0), getPastYear(), faker.name().fullName(), faker.random().nextInt(100,999).toString());
    }

    public static Card getPastMonthCard() {
        var faker = new Faker(new Locale("usa"));
        return new Card("4444 4444 4444 4441", getPastMonth(), getYear(0), faker.name().fullName(), faker.random().nextInt(100,999).toString());
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
    public static Card getShortCard() {
        var faker = new Faker(new Locale("usa"));
        return new Card(faker.finance().creditCard(CreditCardType.DINERS_CLUB), getMonth(1), getYear(1), faker.name().fullName(), faker.random().nextInt(100,999).toString());
    }

    public static Card getSpecificCard(String card, String cardHolder, String month, String year, String cvc) {
        return new Card(card, month, year, cardHolder, cvc);
    }
}
