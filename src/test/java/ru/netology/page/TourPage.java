package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;
import ru.netology.data.Database;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TourPage {
    private SelenideElement heading = $("h2").shouldHave(exactText("Путешествие дня"));
    private SelenideElement buyButton = $("button").shouldHave(exactText("Купить"));
    private SelenideElement creditButton = $("button").shouldHave(exactText("Купить в кредит"));
    private SelenideElement buyHeader = $("h3").shouldHave(exactText("Оплата по карте"));
    private SelenideElement creditHeader = $("h3").shouldHave(exactText("Кредит по данным карты"));
    private SelenideElement cardNumberField = $(byText("Номер карты")).parent().$(".input__control");
    private SelenideElement monthField = $(byText("Месяц")).parent().$(".input__control");
    private SelenideElement yearField = $(byText("Год")).parent().$(".input__control");
    private SelenideElement ownerField = $(byText("Владелец")).parent().$(".input__control");
    private SelenideElement cvcField = $(byText("CVC/CVV")).parent().$(".input__control");
    private SelenideElement continueButton = $$("button").find(exactText("Продолжить"));
    private SelenideElement notificationOK = $(".notification_status_ok");
    private SelenideElement notificationError = $(".notification_status_error");
    private SelenideElement inputInvalid = $(".input__sub");

    public TourPage(){
        heading.shouldBe(visible);
    }

    public void fillPayment(DataHelper.Card card){
        buyButton.click();
        buyHeader.shouldBe(visible);
        cardNumberField.setValue(card.getNumber());
        monthField.setValue(card.getMonth());
        yearField.setValue(card.getYear());
        ownerField.setValue(card.getHolder());
        cvcField.setValue(card.getCvc());
        continueButton.click();
    }

    public void fillCredit(DataHelper.Card card){
        creditButton.click();
        creditHeader.shouldBe(visible);
        cardNumberField.setValue(card.getNumber());
        monthField.setValue(card.getMonth());
        yearField.setValue(card.getYear());
        ownerField.setValue(card.getHolder());
        cvcField.setValue(card.getCvc());
        continueButton.click();
    }

    public void notificationOkIsVisible() {
        notificationOK.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void notificationErrorIsVisible() {
        notificationError.shouldBe(visible, Duration.ofSeconds(15));
    }

    public boolean inputInvalidIsVisible() {
        return inputInvalid.isDisplayed();
    }
}
