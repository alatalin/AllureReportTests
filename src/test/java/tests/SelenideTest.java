package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;


public class SelenideTest extends TestBase{

    @Test
    public void testIssueSearch(){
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");

        $(".header-search-button").click();
        $("#query-builder-test").setValue("alatalin/demoqa-tests-alatalin").pressEnter();

        $(linkText("alatalin/demoqa-tests-alatalin")).click();
        $("#issues-tab").click();
        $(withText("#1")).should(Condition.exist);

    }
}
