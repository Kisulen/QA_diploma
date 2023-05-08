package ru.netology.tests;

import org.junit.jupiter.api.Test;
import ru.netology.helpers.DataHelper;
import ru.netology.helpers.SQLHelper;
import ru.netology.pages.InitialPage;

import static com.codeborne.selenide.Selenide.open;

public class NegativeTests {
    @Test
    void shouldFailWithRandomCard() {
        open("http://localhost:8080");
        var initialPage = new InitialPage();
        var cardDetailsPage = initialPage.shouldOpenCardDetails();
        var randomInfo = DataHelper.getRandomCardInfo();
        cardDetailsPage.fillInCardDetails(randomInfo);
        cardDetailsPage.errorNotificationVisible();
        SQLHelper.returnStatusOfTransactionMysql().equals("DECLINED");
    }

    @Test
    void shouldFailWithRandomCredit() {
        open("http://localhost:8080");
        var initialPage = new InitialPage();
        var creditDetailsPage = initialPage.shouldOpenCardDetails();
        var randomInfo = DataHelper.getRandomCardInfo();
        creditDetailsPage.fillInCardDetails(randomInfo);
        creditDetailsPage.errorNotificationVisible();
        SQLHelper.returnStatusOfTransactionMysql().equals("DECLINED");
    }

    @Test
    void shouldFailIfAllFieldsEmptyCard() {
        open("http://localhost:8080");
        var initialPage = new InitialPage();
        var cardDetailsPage = initialPage.shouldOpenCardDetails();
        cardDetailsPage.leaveFieldsBlank();
        cardDetailsPage.allWrongNotificationsVisible();
        SQLHelper.returnStatusOfTransactionMysql().equals("DECLINED");
    }

    @Test
    void shouldFailIfAllFieldsEmptyCredit() {
        open("http://localhost:8080");
        var initialPage = new InitialPage();
        var creditDetailsPage = initialPage.shouldOpenCardDetails();
        creditDetailsPage.leaveFieldsBlank();
        creditDetailsPage.allWrongNotificationsVisible();
        SQLHelper.returnStatusOfTransactionMysql().equals("DECLINED");
    }

}
