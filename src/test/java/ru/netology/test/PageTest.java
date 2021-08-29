package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.Database;
import ru.netology.page.TourPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

public class PageTest {

    @BeforeEach
    public void openPage() {
        open(System.getProperty("connection.url"));
    }
    @AfterEach
    public void cleanDB(){
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
    @DisplayName("DECLINED credit, should decline")
    void shouldDeclineCredit() {
        var tourPage = new TourPage();
        tourPage.fillCredit(DataHelper.getDeclinedCard());
//        tourPage.notificationErrorIsVisible();
        tourPage.notificationOkIsVisible();
        assertEquals("DECLINED", Database.creditStatus());
    }

    @Test
    @DisplayName("DECLINED payment, should decline")
    void shouldDeclinePayment() {
        var tourPage = new TourPage();
        tourPage.fillPayment(DataHelper.getDeclinedCard());
//        tourPage.notificationErrorIsVisible();
        tourPage.notificationOkIsVisible();
        assertEquals("DECLINED", Database.paymentStatus());
    }
    @Test
    @DisplayName("Fake card payment, should decline")
    void shouldDeclineFakeCardPayment() {}
    @Test
    @DisplayName("Fake card credit, should decline")
    void shouldDeclineFakeCardCredit() {}
    @Test
    @DisplayName("Short card payment, should decline")
    void shouldDeclineShortCardPayment() {}
    @Test
    @DisplayName("Short card credit, should decline")
    void shouldDeclineShortCardCredit() {}
    @Test
    @DisplayName("Expired card payment, should decline")
    void shouldDeclineExpiredCardPayment() {}
    @Test
    @DisplayName("Expired card credit, should decline")
    void shouldDeclineExpiredCardCredit() {}
    @Test
    @DisplayName("Expired month card payment, should decline")
    void shouldDeclineExpiredMonthCardPayment() {}
    @Test
    @DisplayName("Expired month card credit, should decline")
    void shouldDeclineExpiredMonthCardCredit() {}
    @Test
    @DisplayName("Zero month card payment, should decline")
    void shouldDeclineZeroMonthCardPayment() {}
    @Test
    @DisplayName("Zero month card credit, should decline")
    void shouldDeclineZeroMonthCardCredit() {}
    @Test
    @DisplayName("Expired year card payment, should decline")
    void shouldDeclineExpiredYearCardPayment() {}
    @Test
    @DisplayName("Expired year card credit, should decline")
    void shouldDeclineExpiredYearCardCredit() {}
    @Test
    @DisplayName("Zero year card payment, should decline")
    void shouldDeclineZeroYearCardPayment() {}
    @Test
    @DisplayName("Zero year card credit, should decline")
    void shouldDeclineZeroYearCardCredit() {}
    @Test
    @DisplayName("Zero CVC card payment, should decline")
    void shouldDeclineZeroCVCCardPayment() {}
    @Test
    @DisplayName("Zero CVC card credit, should decline")
    void shouldDeclineZeroCVCCardCredit() {}
    @Test
    @DisplayName("Short CVC card payment, should decline")
    void shouldDeclineShortCVCCardPayment() {}
    @Test
    @DisplayName("Short CVC card credit, should decline")
    void shouldDeclineShortCVCCardCredit() {}
    @Test
    @DisplayName("Symbol cardholder card payment, should decline")
    void shouldDeclineZeroCardHolderPayment() {}
    @Test
    @DisplayName("Symbol cardholder card credit, should decline")
    void shouldDeclineZeroMonthCardHolderCredit() {}
}