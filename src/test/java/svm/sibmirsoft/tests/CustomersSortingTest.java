package svm.sibmirsoft.tests;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import svm.sibmirsoft.pages.CustomersPage;

public class CustomersSortingTest extends BaseTest {
    private CustomersPage customersPage;

    @BeforeMethod
    public void setUpPages() {
        driver.get(BASE_URL + "/list");

        SoftAssert softAssert = new SoftAssert();
        customersPage = new CustomersPage(driver);

        List<String> originalNames = customersPage.getAllFirstNames();
        softAssert.assertFalse(originalNames.isEmpty(), "Нет клиентов для тестирования сортировки");
        softAssert.assertAll();
    }

    @Test
    public void testFirstNameSorting() {
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