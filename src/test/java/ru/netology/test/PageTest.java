package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.Database;
import ru.netology.page.TourPage;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

public class PageTest {

    @BeforeEach
    public void openPage() {
        open(System.getProperty("connection.url"));
    }

    @AfterEach
    public void cleanDB() {
        Database.cleanBD();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @Disabled
    @DisplayName("APPROVED credit, should pass")
    void shouldWorkCredit() {
        var tourPage = new TourPage();
        tourPage.fillCredit(DataHelper.getValidCard());
        tourPage.notificationOkIsVisible();
        assertEquals("APPROVED", Database.creditStatus());
    }

    @Test
    @Disabled
    @DisplayName("APPROVED payment, should pass")
    void shouldWorkPayment() {
        var tourPage = new TourPage();
        tourPage.fillPayment(DataHelper.getValidCard());
        tourPage.notificationOkIsVisible();
        assertEquals("APPROVED", Database.paymentStatus());
    }

    @Test
    @Disabled
    @DisplayName("DECLINED credit, should decline")
    void shouldDeclineCredit() {
        var tourPage = new TourPage();
        tourPage.fillCredit(DataHelper.getDeclinedCard());
//        tourPage.notificationErrorIsVisible();
        tourPage.notificationOkIsVisible();
        assertEquals("DECLINED", Database.creditStatus());
    }

    @Test
    @Disabled
    @DisplayName("DECLINED payment, should decline")
    void shouldDeclinePayment() {
        var tourPage = new TourPage();
        tourPage.fillPayment(DataHelper.getDeclinedCard());
//        tourPage.notificationErrorIsVisible();
        tourPage.notificationOkIsVisible();
        assertEquals("DECLINED", Database.paymentStatus());
    }

    @Test
    @Disabled
    @DisplayName("Fake card payment, should decline")
    void shouldDeclineFakeCardPayment() {
        var tourPage = new TourPage();
        tourPage.fillPayment(DataHelper.getFakeCard());
        tourPage.notificationErrorIsVisible();
        assertEquals("0", Database.countRecords());
    }

    @Test
    @Disabled
    @DisplayName("Fake card credit, should decline")
    void shouldDeclineFakeCardCredit() {
        var tourPage = new TourPage();
        tourPage.fillCredit(DataHelper.getFakeCard());
        tourPage.notificationErrorIsVisible();
        assertEquals("0", Database.countRecords());
    }

    @Test
    @Disabled
    @DisplayName("Short card payment, should decline")
    void shouldDeclineShortCardPayment() {
        var tourPage = new TourPage();
        tourPage.fillPayment(DataHelper.getShortCard());
        assertTrue(tourPage.inputInvalidIsVisible());
    }

    @Test
    @Disabled
    @DisplayName("Short card credit, should decline")
    void shouldDeclineShortCardCredit() {
        var tourPage = new TourPage();
        tourPage.fillCredit(DataHelper.getShortCard());
        assertTrue(tourPage.inputInvalidIsVisible());
    }

    @Test
    @Disabled
    @DisplayName("Expired card payment, should decline")
    void shouldDeclineExpiredCardPayment() {
        var tourPage = new TourPage();
        tourPage.fillPayment(DataHelper.getPastYearCard());
        assertTrue(tourPage.inputInvalidIsVisible());
    }

    @Test
    @Disabled
    @DisplayName("Expired card credit, should decline")
    void shouldDeclineExpiredCardCredit() {
        var tourPage = new TourPage();
        tourPage.fillCredit(DataHelper.getPastYearCard());
        assertTrue(tourPage.inputInvalidIsVisible());
    }

    @Test
    @Disabled
    @DisplayName("Expired month payment, should decline")
    void shouldDeclineExpiredMonthCardPayment() {
        var tourPage = new TourPage();
        tourPage.fillPayment(DataHelper.getPastMonthCard());
        assertTrue(tourPage.inputInvalidIsVisible());
    }

    @Test
    @Disabled
    @DisplayName("Expired month credit, should decline")
    void shouldDeclineExpiredMonthCardCredit() {
        var tourPage = new TourPage();
        tourPage.fillCredit(DataHelper.getPastMonthCard());
        assertTrue(tourPage.inputInvalidIsVisible());
    }

    @Test
    @Disabled
    @DisplayName("Zero month payment, should decline")
    void shouldDeclineZeroMonthCardPayment() {
        var tourPage = new TourPage();
        tourPage.fillCredit(DataHelper.getSpecificCard("4444 4444 4444 4441", new Faker().name().fullName(), "00", DataHelper.getYear(1),"123"));
        assertTrue(tourPage.inputInvalidIsVisible());
    }

    @Test
    @Disabled
    @DisplayName("Zero month credit, should decline")
    void shouldDeclineZeroMonthCardCredit() {
        var tourPage = new TourPage();
        tourPage.fillPayment(DataHelper.getSpecificCard("4444 4444 4444 4441", new Faker().name().fullName(), "00", DataHelper.getYear(1),"123"));
        assertTrue(tourPage.inputInvalidIsVisible());
    }
//
//    @Test
//    @DisplayName("Expired year payment, should decline")
//    void shouldDeclineExpiredYearCardPayment() {
//        var tourPage = new TourPage();
//    }
//
//    @Test
//    @DisplayName("Expired year credit, should decline")
//    void shouldDeclineExpiredYearCardCredit() {
//        var tourPage = new TourPage();
//    }
//
//    @Test
//    @DisplayName("Zero year payment, should decline")
//    void shouldDeclineZeroYearCardPayment() {
//        var tourPage = new TourPage();
//    }
//
//    @Test
//    @DisplayName("Zero year credit, should decline")
//    void shouldDeclineZeroYearCardCredit() {
//        var tourPage = new TourPage();
//    }
//
//    @Test
//    @DisplayName("Zero CVC payment, should decline")
//    void shouldDeclineZeroCVCCardPayment() {
//        var tourPage = new TourPage();
//    }
//
//    @Test
//    @DisplayName("Zero CVC credit, should decline")
//    void shouldDeclineZeroCVCCardCredit() {
//        var tourPage = new TourPage();
//    }
//
//    @Test
//    @DisplayName("Short CVC payment, should decline")
//    void shouldDeclineShortCVCCardPayment() {
//        var tourPage = new TourPage();
//    }
//
//    @Test
//    @DisplayName("Short CVC credit, should decline")
//    void shouldDeclineShortCVCCardCredit() {
//        var tourPage = new TourPage();
//    }
//
//    @Test
//    @DisplayName("Symbol cardholder payment, should decline")
//    void shouldDeclineSymbolCardHolderPayment() {
//        var tourPage = new TourPage();
//    }
//
//    @Test
//    @DisplayName("Symbol cardholder credit, should decline")
//    void shouldDeclineSymbolCardHolderCredit() {
//        var tourPage = new TourPage();
//    }
//
    @Test
    @Disabled
    @DisplayName("Empty card number payment, should decline")
    void shouldDeclineEmptyCardNumberPayment() {
        var tourPage = new TourPage();
        var faker = new Faker(new Locale("usa"));
        tourPage.fillPayment(DataHelper.getSpecificCard("4444 4444 4444 4441", "", DataHelper.getMonth(1), DataHelper.getYear(1),faker.random().nextInt(100,999).toString()));
        assertTrue(tourPage.inputInvalidIsVisible());
    }

    @Test
    @Disabled
    @DisplayName("Empty card number credit, should decline")
    void shouldDeclineEmptyCardNumberCredit() {
        var tourPage = new TourPage();
        var faker = new Faker(new Locale("usa"));
        tourPage.fillPayment(DataHelper.getSpecificCard("4444 4444 4444 4441", "", DataHelper.getMonth(1), DataHelper.getYear(1),faker.random().nextInt(100,999).toString()));
        assertTrue(tourPage.inputInvalidIsVisible());
    }

    @Test
    @Disabled
    @DisplayName("Empty month payment, should decline")
    void shouldDeclineEmptyMonthPayment() {
        var tourPage = new TourPage();
        var faker = new Faker(new Locale("usa"));
        tourPage.fillPayment(DataHelper.getSpecificCard("4444 4444 4444 4441", faker.name().fullName(), "", DataHelper.getYear(1), faker.random().nextInt(100,999).toString()));
        assertTrue(tourPage.inputInvalidIsVisible());
    }

    @Test
    @Disabled
    @DisplayName("Empty month credit, should decline")
    void shouldDeclineEmptyMonthCredit() {
        var tourPage = new TourPage();
        var faker = new Faker(new Locale("usa"));
        tourPage.fillCredit(DataHelper.getSpecificCard("4444 4444 4444 4441", faker.name().fullName(), "", DataHelper.getYear(1),faker.random().nextInt(100,999).toString()));
        assertTrue(tourPage.inputInvalidIsVisible());

    }

    @Test
    @Disabled
    @DisplayName("Empty year payment, should decline")
    void shouldDeclineEmptyYearPayment() {
        var tourPage = new TourPage();
        var faker = new Faker(new Locale("usa"));
        tourPage.fillPayment(DataHelper.getSpecificCard("4444 4444 4444 4441", faker.name().fullName(), DataHelper.getMonth(1), "", faker.random().nextInt(100,999).toString()));
        assertTrue(tourPage.inputInvalidIsVisible());
    }

    @Test
    @Disabled
    @DisplayName("Empty year credit, should decline")
    void shouldDeclineEmptyYearCredit() {
        var tourPage = new TourPage();
        var faker = new Faker(new Locale("usa"));
        tourPage.fillCredit(DataHelper.getSpecificCard("4444 4444 4444 4441", faker.name().fullName(), DataHelper.getMonth(1), "",faker.random().nextInt(100,999).toString()));
        assertTrue(tourPage.inputInvalidIsVisible());
    }

    @Test
    @Disabled
    @DisplayName("Empty cardholder payment, should decline")
    void shouldDeclineEmptyCardHolderPayment() {
        var tourPage = new TourPage();
        tourPage.fillPayment(DataHelper.getSpecificCard("4444 4444 4444 4441", "", DataHelper.getMonth(1), DataHelper.getYear(1),new Faker().random().nextInt(100,999).toString()));
        assertTrue(tourPage.inputInvalidIsVisible());
    }

    @Test
    @Disabled
    @DisplayName("Empty cardholder credit, should decline")
    void shouldDeclineEmptyCardHolderCredit() {
        var tourPage = new TourPage();
        tourPage.fillCredit(DataHelper.getSpecificCard("4444 4444 4444 4441", "", DataHelper.getMonth(1), DataHelper.getYear(1), new Faker().random().nextInt(100,999).toString()));
        assertTrue(tourPage.inputInvalidIsVisible());
    }

    @Test
    @Disabled
    @DisplayName("Empty CVC payment, should decline")
    void shouldDeclineEmptyCVCPayment() {
        var tourPage = new TourPage();
        tourPage.fillPayment(DataHelper.getSpecificCard("4444 4444 4444 4441", new Faker().name().fullName(), DataHelper.getMonth(1), DataHelper.getYear(1),""));
        assertTrue(tourPage.inputInvalidIsVisible());
    }

    @Test
    @Disabled
    @DisplayName("Empty CVC credit, should decline")
    void shouldDeclineEmptyCVCCredit() {
        var tourPage = new TourPage();
        tourPage.fillCredit(DataHelper.getSpecificCard("4444 4444 4444 4441", new Faker().name().fullName(), DataHelper.getMonth(1), DataHelper.getYear(1),""));
        assertTrue(tourPage.inputInvalidIsVisible());
    }
}