package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepsTest {

    private static final String REPOSYTORY = "alatalin/demoqa-tests-alatalin";
    private static final int ISSUE = 1;

    @Test
    public void testLambdaStep(){
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });

        step("Ищем репозиторий " + REPOSYTORY, () -> {
            $(".header-search-button").click();
            $("#query-builder-test").setValue(REPOSYTORY).pressEnter();
        });

        step("Кликаем по ссылке репозитория " + REPOSYTORY, () -> {
            $(linkText(REPOSYTORY)).click();
        });

        step("Открываем tab Issues", () -> {
            $("#issues-tab").click();
        });

        step("Проверяем наличие Issue с номером " + ISSUE, () -> {
            $(withText("#" + ISSUE)).should(Condition.exist);
        });
    }

    @Test
    public void testAnnotatedStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSYTORY);
        steps.clickOnRepositoryLink(REPOSYTORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(1);
    }
}
