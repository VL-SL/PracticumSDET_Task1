package svm.sibmirsoft.tests;

import java.util.Comparator;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import svm.sibmirsoft.pages.CustomersPage;
import svm.sibmirsoft.pages.ManagerPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

@Epic("Тесты банковской системы")
@Feature("Управление клиентами")
public class CustomersDeletionTest extends BaseTest {
    private CustomersPage customersPage;
    private String deletedCustomerName;
    private String deletedCustomerAccount;

    @BeforeMethod
    @Description("Подготовка теста: переход на страницу клиентов")
    public void setUpPages() {
        ManagerPage managerPage = new ManagerPage(driver);
        customersPage = managerPage.clickCustomersButton();

        SoftAssert softAssert = new SoftAssert();
        List<String> allNames = customersPage.getAllFirstNames();
        softAssert.assertFalse(allNames.isEmpty(), "Нет клиентов для тестирования удаления");
        softAssert.assertAll();
    }

    @Test
    @Description("Тест удаления клиента со средней длиной имени")
    @Story("Пользователь удаляет клиента из системы")
    public void testDeleteCustomerByAverageNameLength() {
        SoftAssert softAssert = new SoftAssert();
        List<String> allNames = customersPage.getAllFirstNames();
        List<String> allAccounts = customersPage.getAllAccountNumbers();

        String targetName = findNameToAverageLength(allNames);
        int targetIndex = allNames.indexOf(targetName);
        deletedCustomerName = targetName;
        deletedCustomerAccount = allAccounts.get(targetIndex);

        customersPage.searchCustomer(targetName)
                .deleteFirstFoundCustomer();

        customersPage.searchCustomer("");
        List<String> remainingAccounts = customersPage.getAllAccountNumbers();
        softAssert.assertFalse(remainingAccounts.contains(deletedCustomerAccount),
                "Аккаунт " + deletedCustomerAccount + " все еще отображается после удаления");

        softAssert.assertAll();
    }

    private String findNameToAverageLength(List<String> names) {
        double averageLength = names.stream().mapToInt(String::length).average().orElseThrow(()
                -> new IllegalStateException("Список имен пуст"));
        return (String) names.stream().min(Comparator.comparingDouble((name)
                -> Math.abs((double) name.length() - averageLength))).orElseThrow(()
                -> new IllegalStateException("Не удалось найти подходящее имя"));
    }
}