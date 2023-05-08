package ru.netology.pages;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.helpers.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class CardDetailsPage {

    private SelenideElement cardNumber = $x("//*[@placeholder='0000 0000 0000 0000']");
    private SelenideElement month = $x("//*[@placeholder='08']");
    private SelenideElement year = $x("//*[@placeholder='22']");
    private SelenideElement owner = $x("(//*[@class='input__control'])[4]");
    private SelenideElement cvc = $x("//*[@placeholder='999']");
    private SelenideElement paymentHeading = $x("//*[contains(text(), 'Оплата по карте')]");
    //private SelenideElement creditHeading = $x("//*[contains(text(), 'Кредит по данным карты')]");

    private SelenideElement okNotification = $x("//*[contains(@class,'notification_status_ok')]");
    private SelenideElement errorNotification = $x("//*[contains(@class,'notification_status_error')]");
    private SelenideElement button = $x("(//*[contains(@class,'button__text')])[3]");

    private SelenideElement cardNumberWrongFormatNotification = $x("(//*[contains(text(), 'Неверный формат')])[1]");
    private SelenideElement monthWrongFormatNotification = $x("(//*[contains(text(), 'Неверный формат')])[2]");
    private SelenideElement yearWrongFormatNotification = $x("(//*[contains(text(), 'Неверный формат')])[3]");
    private SelenideElement cvcWrongFormatNotification = $x("(//*[contains(text(), 'Неверный формат')])[4]");
    private SelenideElement fillInNameNotification = $x("//*[contains(text(), 'Поле')]");

    public CardDetailsPage() {
        paymentHeading.shouldBe(Condition.visible);
    }


    public void fillInCardDetails(DataHelper.CardInfo info) {
        cardNumber.setValue(info.getCardNumber());
        month.setValue(info.getMonth());
        year.setValue(info.getYear());
        owner.setValue(info.getOwner());
        cvc.setValue(info.getCvc());
        button.click();
    }

    public void leaveFieldsBlank() {
        cardNumber.setValue("");
        month.setValue("");
        year.setValue("");
        owner.setValue("");
        cvc.setValue("");
        button.click();
    }

    public void okNotificationVisible() {
        okNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void errorNotificationVisible() {
        errorNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void errorNotificationNotVisible() { errorNotification.shouldNotBe(Condition.visible, Duration.ofSeconds(15)); }


    public void allWrongNotificationsVisible() {
        cardNumberWrongFormatNotification.shouldBe(Condition.visible);
        monthWrongFormatNotification.shouldBe(Condition.visible);
        yearWrongFormatNotification.shouldBe(Condition.visible);
        fillInNameNotification.shouldBe(Condition.visible);
        cvcWrongFormatNotification.shouldBe(Condition.visible);
    }



}
