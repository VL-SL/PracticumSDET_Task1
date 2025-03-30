package svm.sibmirsoft.tests;

import java.util.Collections;
import java.util.List;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import svm.sibmirsoft.pages.CustomersPage;
import svm.sibmirsoft.pages.ManagerPage;

public class CustomersSortingTest extends BaseTest {
    private CustomersPage customersPage;

    @BeforeMethod
    public void setUpPages() {
        ManagerPage managerPage = new ManagerPage(driver);
        customersPage = managerPage.clickCustomersButton();

        // Проверяем, что есть клиенты для тестирования
        SoftAssert softAssert = new SoftAssert();
        List<String> originalNames = customersPage.getAllFirstNames();
        softAssert.assertFalse(originalNames.isEmpty(), "Нет клиентов для тестирования сортировки");
        softAssert.assertAll();
    }

    @Test
    public void testFirstNameSorting() {
        SoftAssert softAssert = new SoftAssert();
        List<String> originalNames = customersPage.getAllFirstNames();

        customersPage.clickFirstNameHeader();
        List<String> uiSortedDesc = customersPage.getAllFirstNames();
        List<String> programmaticallySortedDesc = originalNames.stream()
                .sorted(Collections.reverseOrder())
                .toList();
        softAssert.assertEquals(uiSortedDesc, programmaticallySortedDesc,
                "Сортировка по убыванию работает некорректно");

        customersPage.clickFirstNameHeader();
        List<String> uiSortedAsc = customersPage.getAllFirstNames();
        List<String> programmaticallySortedAsc = originalNames.stream()
                .sorted()
                .toList();
        softAssert.assertEquals(uiSortedAsc, programmaticallySortedAsc,
                "Сортировка по возрастанию работает некорректно");

        softAssert.assertAll();
    }
}