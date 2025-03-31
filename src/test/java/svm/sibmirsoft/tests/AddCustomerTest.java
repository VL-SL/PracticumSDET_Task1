package svm.sibmirsoft.tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import svm.sibmirsoft.helpers.DataGenerator;
import svm.sibmirsoft.pages.AddCustomerPage;

@Epic("Тесты банковской системы")
@Feature("Добавление клиентов")
public class AddCustomerTest extends BaseTest {
    @Test(testName = "Добавление клиента с автоматически сгенерированными данными")
    @Description("Тест добавления клиента с сгенерированными данными")
    @Story("Пользователь добавляет нового клиента в систему")
    public void testAddCustomerWithGeneratedData() {
        Allure.getLifecycle().updateTestCase(testResult ->
                testResult.setName("Тест: добавление клиента с автоматически сгенерированными данными"));
        driver.get(BASE_URL + "/addCust");

        SoftAssert softAssert = new SoftAssert();
        AddCustomerPage addCustomerPage = new AddCustomerPage(driver);

        String firstName = DataGenerator.numbersToWord(DataGenerator.splitPostCode(DataGenerator.generate10DigitNumber()));
        addCustomerPage.addCustomer(firstName,
                DataGenerator.shuffleWord(firstName),
                DataGenerator.generate10DigitNumber()
        );

        String alertText = driver.switchTo().alert().getText();
        softAssert.assertTrue(alertText.contains("Customer added successfully"), "Неверный алерт");
        driver.switchTo().alert().accept();
        softAssert.assertAll();
    }
}