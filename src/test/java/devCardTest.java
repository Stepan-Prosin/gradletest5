import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class devCardTest {
    private String genDate(int newDays, String patern) {
        return LocalDate.now().plusDays(newDays).format(DateTimeFormatter.ofPattern(patern));
    }
    @Test
    void cardDevTest() {

        open("http://localhost:9999");
        $("[data-test-id='city'] input").sendKeys("Москва");
        String date = genDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").sendKeys("Сидров Андрей");
        $("[data-test-id='phone'] input").sendKeys("+78524325555");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + date));

    }
}

