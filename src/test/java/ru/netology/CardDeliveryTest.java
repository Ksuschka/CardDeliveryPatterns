package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.DataGenerator.generateDate;

public class CardDeliveryTest {

    @BeforeEach
    public void openBrowser() {
        open("http://localhost:9999");
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";
    }

    @Test
    public void shouldApplicationForm() {

        $("[data-test-id= 'city'] input").setValue(DataGenerator.generateCity("ru"));
        $("[data-test-id= 'date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id= 'date'] input").setValue(DataGenerator.generateDate(4));
        $("[data-test-id= 'name'] input").setValue(DataGenerator.generateName("ru"));
        $("[data-test-id= 'phone'] input").setValue(DataGenerator.generatePhone("ru"));
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(withText("Запланировать")).click();
        $(".notification_visible")
                .shouldBe(exist, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'] .notification__title")
                .shouldHave(text("Успешно!"), Duration.ofSeconds(15));
        $("[data-test-id='success-notification'] .notification__content")
                .shouldHave(text("Встреча успешно запланирована на " + DataGenerator.generateDate(4)), Duration.ofSeconds(15));

    }

    @Test
    public void shouldDateRescheduling(){
        $("[data-test-id= 'city'] input").setValue(DataGenerator.generateCity("ru"));
        $("[data-test-id= 'date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id= 'date'] input").setValue(DataGenerator.generateDate(4));
        $("[data-test-id= 'name'] input").setValue(DataGenerator.generateName("ru"));
        $("[data-test-id= 'phone'] input").setValue(DataGenerator.generatePhone("ru"));
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(withText("Запланировать")).click();
        $(".notification_visible")
                .shouldBe(exist, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'] .notification__title")
                .shouldHave(text("Успешно!"), Duration.ofSeconds(15));
        $("[data-test-id='success-notification'] .notification__content")
                .shouldHave(text("Встреча успешно запланирована на " + DataGenerator.generateDate(4)), Duration.ofSeconds(15));
        $("[data-test-id= 'date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id= 'date'] input").setValue(DataGenerator.generateDate(5));
        $$("button").find(Condition.exactText("Запланировать")).click();
        $(withText("Перепланировать?"))
                .shouldBe(Condition.visible, Duration.ofSeconds(15));
        $$("button").find(Condition.exactText("Перепланировать")).click();
        $("[data-test-id='success-notification'] .notification__content")
                .shouldHave(text("Встреча успешно запланирована на " + DataGenerator.generateDate(5)), Duration.ofSeconds(15));

    }

    @Test
    public void shouldNameLetterE() {
        $("[data-test-id= 'city'] input").setValue(DataGenerator.generateCity("ru"));
        $("[data-test-id= 'date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id= 'date'] input").setValue(DataGenerator.generateDate(4));
        $("[data-test-id= 'name'] input").setValue(DataGenerator.generateNameLetterE("ru"));
        $("[data-test-id= 'phone'] input").setValue(DataGenerator.generatePhone("ru"));
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(withText("Запланировать")).click();
        $(".notification_visible")
                .shouldBe(exist, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'] .notification__title")
                .shouldHave(text("Успешно!"), Duration.ofSeconds(15));
        $("[data-test-id='success-notification'] .notification__content")
                .shouldHave(text("Встреча успешно запланирована на " + DataGenerator.generateDate(4)), Duration.ofSeconds(15));

    }

    @Test
    public void shouldApplicationFormShortPhone() {
        $("[data-test-id= 'city'] input").setValue(DataGenerator.generateCity("ru"));
        $("[data-test-id= 'date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id= 'date'] input").setValue(DataGenerator.generateDate(4));
        $("[data-test-id= 'name'] input").setValue(DataGenerator.generateName("ru"));
        $("[data-test-id= 'phone'] input").setValue(DataGenerator.generateShortPhone("ru"));
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(withText("Запланировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub")
                .shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    public void shouldApplicationFalsePhone() {
        $("[data-test-id= 'city'] input").setValue(DataGenerator.generateCity("ru"));
        $("[data-test-id= 'date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id= 'date'] input").setValue(DataGenerator.generateDate(4));
        $("[data-test-id= 'name'] input").setValue(DataGenerator.generateName("ru"));
        $("[data-test-id= 'phone'] input").setValue(DataGenerator.generateFalsePhone("ru"));
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(withText("Запланировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub")
                .shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

}
