package ru.netology.rest;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.rest.DataGenerator.*;

public class ClassTestPaters {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        //var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);

        $("[data-test-id='city'] input").setValue(generateCity("ru"));
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(" ");
        $("[data-test-id='date'] input").setValue(firstMeetingDate);
        $("[data-test-id='name'] input").setValue(generateName("ru"));
        $("[data-test-id='phone'] input").setValue("+79200000000"); //generatePhone("ru"));
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + firstMeetingDate), Duration.ofSeconds(15)).shouldBe(visible);

        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(" ");
        $("[data-test-id='date'] input").setValue(secondMeetingDate);
        System.out.println(secondMeetingDate);
        //$(byText("Перепланировать")).click();
        //$(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        //$(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + secondMeetingDate), Duration.ofSeconds(15)).shouldBe(visible);
    }
}
