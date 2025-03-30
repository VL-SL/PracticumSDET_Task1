package svm.sibmirsoft.tests;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import svm.sibmirsoft.pages.CustomersPage;
import svm.sibmirsoft.pages.ManagerPage;

public class CustomersTest extends BaseTest {
    private ManagerPage managerPage;
    private CustomersPage customersPage;

    public CustomersTest() {
    }

    @BeforeMethod
    public void setUpPages() {
        this.managerPage = new ManagerPage(this.driver);
        this.customersPage = this.managerPage.clickCustomersButton();
    }

    @Test(
            priority = 1
    )
    public void testFirstNameSorting() {
        SoftAssert softAssert = new SoftAssert();
        List<String> originalNames = this.customersPage.getAllFirstNames();
        softAssert.assertFalse(originalNames.isEmpty(), "Список клиентов пуст");
        this.customersPage.clickFirstNameHeader();
        List<String> uiSortedDesc = this.customersPage.getAllFirstNames();
        List<String> programmaticallySortedDesc = originalNames.stream().sorted(Collections.reverseOrder()).toList();
        softAssert.assertEquals(uiSortedDesc, programmaticallySortedDesc, "Сортировка по убыванию работает некорректно");
        this.customersPage.clickFirstNameHeader();
        List<String> uiSortedAsc = this.customersPage.getAllFirstNames();
        List<String> programmaticallySortedAsc = originalNames.stream().sorted().toList();
        softAssert.assertEquals(uiSortedAsc, programmaticallySortedAsc, "Сортировка по возрастанию работает некорректно");
        softAssert.assertAll();
    }

    @Test(
            priority = 2
    )
    public void testDeleteCustomerByAverageNameLength() {
        SoftAssert softAssert = new SoftAssert();
        List<String> allNames = this.customersPage.getAllFirstNames();
        List<String> allAccounts = this.customersPage.getAllAccountNumbers();
        softAssert.assertFalse(allNames.isEmpty(), "Список клиентов пуст");
        softAssert.assertEquals(allNames.size(), allAccounts.size(), "Количество имен не совпадает с количеством аккаунтов");
        String targetName = this.findNameToAverageLength(allNames);
        int targetIndex = allNames.indexOf(targetName);
        String targetAccount = (String)allAccounts.get(targetIndex);
        this.customersPage.searchCustomer(targetName);
        List<String> foundAccounts = this.customersPage.getAllAccountNumbers();
        this.customersPage.deleteFirstFoundCustomer();
        List<String> remainingAccounts = this.customersPage.getAllAccountNumbers();
        softAssert.assertFalse(remainingAccounts.contains(targetAccount), "Аккаунт " + targetAccount + " все еще отображается после удаления");
        softAssert.assertAll();
    }

    private String findNameToAverageLength(List<String> names) {
        double averageLength = names.stream().mapToInt(String::length).average().orElseThrow(() -> new IllegalStateException("Список имен пуст"));
        return (String)names.stream().min(Comparator.comparingDouble((name) -> Math.abs((double)name.length() - averageLength))).orElseThrow(() -> new IllegalStateException("Не удалось найти подходящее имя"));
    }
}