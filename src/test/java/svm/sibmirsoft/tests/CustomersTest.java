package svm.sibmirsoft.tests;

import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import svm.sibmirsoft.pages.CustomersPage;
import svm.sibmirsoft.pages.ManagerPage;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Epic("Операции банковского менеджера")
@Feature("Управление клиентами")
@Owner("Тестировщик")
public class CustomersTest extends BaseTest {
    private ManagerPage managerPage;
    private CustomersPage customersPage;

    @BeforeMethod
    @Description("Инициализация страниц перед каждым тестом")
    public void setUpPages() {
        Allure.step("Открытие страницы менеджера", () -> {
            this.managerPage = new ManagerPage(this.driver);
            this.customersPage = this.managerPage.clickCustomersButton();
        });
    }

    @Test(priority = 1)
    @Description("Тест сортировки клиентов по имени")
    @Story("Сортировка списка клиентов")
    @Severity(SeverityLevel.NORMAL)
    public void testFirstNameSorting() {
        SoftAssert softAssert = new SoftAssert();

        Allure.step("Получение списка имен клиентов", () -> {
            List<String> originalNames = this.customersPage.getAllFirstNames();
            softAssert.assertFalse(originalNames.isEmpty(), "Список клиентов пуст");

            Allure.step("Проверка сортировки по убыванию", () -> {
                this.customersPage.clickFirstNameHeader();
                List<String> uiSortedDesc = this.customersPage.getAllFirstNames();
                List<String> programmaticallySortedDesc = originalNames.stream()
                        .sorted(Collections.reverseOrder())
                        .toList();
                softAssert.assertEquals(uiSortedDesc, programmaticallySortedDesc,
                        "Сортировка по убыванию работает некорректно");
            });

            Allure.step("Проверка сортировки по возрастанию", () -> {
                this.customersPage.clickFirstNameHeader();
                List<String> uiSortedAsc = this.customersPage.getAllFirstNames();
                List<String> programmaticallySortedAsc = originalNames.stream()
                        .sorted()
                        .toList();
                softAssert.assertEquals(uiSortedAsc, programmaticallySortedAsc,
                        "Сортировка по возрастанию работает некорректно");
            });
        });

        softAssert.assertAll();
    }

    @Test(priority = 2)
    @Description("Тест удаления клиента со средней длиной имени")
    @Story("Удаление клиента из системы")
    @Severity(SeverityLevel.CRITICAL)
    public void testDeleteCustomerByAverageNameLength() {
        SoftAssert softAssert = new SoftAssert();

        Allure.step("Получение списка клиентов", () -> {
            List<String> allNames = this.customersPage.getAllFirstNames();
            List<String> allAccounts = this.customersPage.getAllAccountNumbers();

            softAssert.assertFalse(allNames.isEmpty(), "Список клиентов пуст");
            softAssert.assertEquals(allNames.size(), allAccounts.size(),
                    "Количество имен не совпадает с количеством аккаунтов");

            Allure.step("Поиск клиента со средней длиной имени", () -> {
                String targetName = this.findNameToAverageLength(allNames);
                int targetIndex = allNames.indexOf(targetName);
                String targetAccount = allAccounts.get(targetIndex);

                Allure.step("Удаление выбранного клиента", () -> {
                    this.customersPage.searchCustomer(targetName);
                    this.customersPage.deleteFirstFoundCustomer();

                    Allure.step("Проверка удаления клиента", () -> {
                        List<String> remainingAccounts = this.customersPage.getAllAccountNumbers();
                        softAssert.assertFalse(remainingAccounts.contains(targetAccount),
                                "Аккаунт " + targetAccount + " все еще отображается после удаления");
                    });
                });
            });
        });

        softAssert.assertAll();
    }

    @Step("Нахождение имени с длиной, наиболее близкой к средней")
    private String findNameToAverageLength(List<String> names) {
        double averageLength = names.stream()
                .mapToInt(String::length)
                .average()
                .orElseThrow(() -> new IllegalStateException("Список имен пуст"));

        return names.stream()
                .min(Comparator.comparingDouble(name -> Math.abs(name.length() - averageLength)))
                .orElseThrow(() -> new IllegalStateException("Не удалось найти подходящее имя"));
    }
}