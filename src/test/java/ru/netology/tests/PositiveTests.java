package ru.netology.tests;

import org.junit.jupiter.api.Test;
import ru.netology.helpers.DataHelper;
import ru.netology.helpers.SQLHelper;
import ru.netology.pages.InitialPage;

import static com.codeborne.selenide.Selenide.open;

public class PositiveTests {

    @Test
    void shouldSuccessfullyPayForTourWithCard() {
        open("http://localhost:8080");
        var initialPage = new InitialPage();
        var cardDetailsPage = initialPage.shouldOpenCardDetails();
        var cardInfo = DataHelper.getApprovedCardInfo();
        cardDetailsPage.fillInCardDetails(cardInfo);
        cardDetailsPage.okNotificationVisible();
        cardDetailsPage.errorNotificationNotVisible();
        SQLHelper.returnStatusOfTransactionMysql().equals("APPROVED");
    }

    @Test
    void shouldDeclineCardPaymentFromDeclinedCard() {
        open("http://localhost:8080");
        var initialPage = new InitialPage();
        var cardDetailsPage = initialPage.shouldOpenCardDetails();
        var cardInfo = DataHelper.getDeclinedCardInfo();
        cardDetailsPage.fillInCardDetails(cardInfo);
        cardDetailsPage.errorNotificationVisible();
        SQLHelper.returnStatusOfTransactionMysql().equals("DECLINED");
    }

    @Test
    void shouldSuccessfullyPayForTourWithCredit() {
        open("http://localhost:8080");
        var initialPage = new InitialPage();
        var creditDetailsPage = initialPage.shouldOpenCreditDetails();
        var cardInfo = DataHelper.getApprovedCardInfo();
        creditDetailsPage.fillInCardDetails(cardInfo);
        creditDetailsPage.okNotificationVisible();
        creditDetailsPage.errorNotificationNotVisible();
        SQLHelper.returnStatusOfTransactionMysql().equals("APPROVED");
    }

    @Test
    void shouldDeclineCreditPaymentFromDeclinedCard() {
        open("http://localhost:8080");
        var initialPage = new InitialPage();
        var creditDetailsPage = initialPage.shouldOpenCardDetails();
        var cardInfo = DataHelper.getDeclinedCardInfo();
        creditDetailsPage.fillInCardDetails(cardInfo);
        creditDetailsPage.errorNotificationVisible();
        SQLHelper.returnStatusOfTransactionMysql().equals("DECLINED");
    }
}
