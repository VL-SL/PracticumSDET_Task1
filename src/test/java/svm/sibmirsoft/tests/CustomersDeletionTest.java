package svm.sibmirsoft.tests;

import java.util.Comparator;
import java.util.List;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import svm.sibmirsoft.pages.CustomersPage;

public class CustomersDeletionTest extends BaseTest {
    private CustomersPage customersPage;

    @BeforeMethod
    public void setUpPages() {
        driver.get(BASE_URL + "/list");

        SoftAssert softAssert = new SoftAssert();
        customersPage = new CustomersPage(driver);

        List<String> originalNames = customersPage.getAllFirstNames();
        softAssert.assertFalse(originalNames.isEmpty(), "Нет клиентов для тестирования удаления");
        softAssert.assertAll();
    }

    @Test
    public void testDeleteCustomerByAverageNameLength() {
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