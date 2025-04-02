package svm.sibmirsoft.tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import svm.sibmirsoft.helpers.DataGenerator;
import svm.sibmirsoft.pages.AddCustomerPage;

@Epic("Тесты банковской системы")
@Feature("Добавление клиентов")
public class AddCustomerTest extends BaseTest {
    @Test(description = "Тест: добавление клиента с автоматически сгенерированными данными",threadPoolSize = 3, invocationCount = 1,  timeOut = 30000)
    @Description("Тест добавления клиента с сгенерированными данными")
    @Story("Пользователь добавляет нового клиента в систему")
    public void testAddCustomerWithGeneratedData() {
        getDriver().get(BASE_URL + "/addCust");

        SoftAssert softAssert = new SoftAssert();
        AddCustomerPage addCustomerPage = new AddCustomerPage(getDriver());

        String firstName = DataGenerator.numbersToWord(DataGenerator.splitPostCode(DataGenerator.generate10DigitNumber()));
        addCustomerPage.addCustomer(firstName,
                DataGenerator.shuffleWord(firstName),
                DataGenerator.generate10DigitNumber()
        );

        String alertText = getDriver().switchTo().alert().getText();
        softAssert.assertTrue(alertText.contains("Customer added successfully"), "Неверный алерт");
        getDriver().switchTo().alert().accept();
        softAssert.assertAll();
    }
}