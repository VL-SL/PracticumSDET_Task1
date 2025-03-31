package svm.sibmirsoft.tests;

import java.util.Comparator;
import java.util.List;

import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import svm.sibmirsoft.pages.CustomersPage;

@Epic("Тесты банковской системы")
@Feature("Сортировка клиентов")
public class CustomersSortingTest extends BaseTest {
    private CustomersPage customersPage;

    @BeforeMethod
    @Description("Подготовка теста: переход на страницу клиентов")
    @Step("Подготовка тестового окружения")
    public void setUpPages() {
        getDriver().get(BASE_URL + "/list");

        SoftAssert softAssert = new SoftAssert();
        customersPage = new CustomersPage(getDriver());

        List<String> originalNames = customersPage.getAllFirstNames();
        softAssert.assertFalse(originalNames.isEmpty(), "Нет клиентов для тестирования сортировки");
        softAssert.assertAll();
    }

    @Test(threadPoolSize = 3, invocationCount = 1,  timeOut = 30000)
    @Description("Тест сортировки клиентов по имени")
    @Story("Пользователь сортирует клиентов по имени")
    public void testFirstNameSorting() {
        Allure.getLifecycle().updateTestCase(testResult ->
                testResult.setName("Тест: сортировка по имени"));
        SoftAssert softAssert = new SoftAssert();

        List<String> allFirstNames = customersPage
                .clickFirstNameHeader()
                .getAllFirstNames();

        softAssert.assertEquals(
                allFirstNames,
                allFirstNames.stream().sorted(Comparator.reverseOrder()).toList(),
                "Сортировка по убыванию работает некорректно"
        );

        customersPage.clickFirstNameHeader();

        softAssert.assertEquals(
                customersPage.getAllFirstNames(),
                allFirstNames.stream().sorted().toList(),
                "Сортировка по возрастанию работает некорректно"
        );

        softAssert.assertAll();
    }
}