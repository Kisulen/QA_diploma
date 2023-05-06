package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class InitialPage {

    private SelenideElement buyWithCard = $x("//span[text()='Купить']");
    private SelenideElement buyUsingCredit = $x("//span[text()='Купить в кредит']");

    public CardDetailsPage shouldOpenCardDetails() {
        buyWithCard.click();
        return new CardDetailsPage();
    }

    public CreditDetailsPage shouldOpenCreditDetails() {
        buyUsingCredit.click();
        return new CreditDetailsPage();
    }
}
