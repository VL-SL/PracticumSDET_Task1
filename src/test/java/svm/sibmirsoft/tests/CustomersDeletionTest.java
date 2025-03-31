package svm.sibmirsoft.tests;

import java.util.Comparator;
import java.util.List;

import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import svm.sibmirsoft.pages.CustomersPage;

@Epic("Тесты банковской системы")
@Feature("Управление клиентами")
public class CustomersDeletionTest extends BaseTest {
    private CustomersPage customersPage;

    @BeforeMethod
    @Description("Подготовка теста: переход на страницу клиентов")
    @Step("Подготовка тестового окружения")
    public void setUpPages() {
        getDriver().get(BASE_URL + "/list");

        SoftAssert softAssert = new SoftAssert();
        customersPage = new CustomersPage(getDriver());

        List<String> originalNames = customersPage.getAllFirstNames();
        softAssert.assertFalse(originalNames.isEmpty(), "Нет клиентов для тестирования удаления");
        softAssert.assertAll();
    }

    @Test(threadPoolSize = 3, invocationCount = 1,  timeOut = 30000)
    @Description("Тест удаления клиента со средней длиной имени")
    @Story("Пользователь удаляет клиента из системы")
    public void testDeleteCustomerByAverageNameLength() {
        Allure.getLifecycle().updateTestCase(testResult ->
                testResult.setName("Тест: удаление клиента с именем средней длины"));
        SoftAssert softAssert = new SoftAssert();

        List<String> allNames = customersPage.getAllFirstNames();
        List<String> allAccounts = customersPage.getAllAccountNumbers();

        softAssert.assertFalse(allNames.isEmpty(), "Нет клиентов для тестирования удаления");

        String targetName = findNameClosestToAverageLength(allNames);
        String targetAccount = allAccounts.get(allNames.indexOf(targetName));

        customersPage.searchCustomer(targetName)
                .deleteFirstFoundCustomer()
                .searchCustomer("");

        softAssert.assertFalse(
                customersPage.getAllAccountNumbers().contains(targetAccount),
                "Аккаунт " + targetAccount + " все еще отображается после удаления"
        );

        softAssert.assertAll();
    }

    private String findNameClosestToAverageLength(List<String> names) {
        double averageLength = names.stream()
                .mapToInt(String::length)
                .average()
                .orElseThrow(() -> new IllegalStateException("Список имен пуст"));

        return names.stream()
                .min(Comparator.comparingDouble(name -> Math.abs(name.length() - averageLength)))
                .orElseThrow(() -> new IllegalStateException("Не удалось найти подходящее имя"));
    }
}