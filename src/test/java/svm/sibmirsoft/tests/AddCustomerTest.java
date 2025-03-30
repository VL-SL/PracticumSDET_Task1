package svm.sibmirsoft.tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import svm.sibmirsoft.helpers.DataGenerator;
import svm.sibmirsoft.pages.AddCustomerPage;
import svm.sibmirsoft.pages.ManagerPage;
import java.util.List;

@Epic("Операции банковского менеджера")
@Feature("Управление клиентами")
@Owner("Тестировщик")
public class AddCustomerTest extends BaseTest {

    @Test
    @Description("Тест добавления клиента с автоматически сгенерированными данными")
    @Story("Добавление нового клиента")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "Документация", url = "https://example.com/docs")
    public void testAddCustomerWithGeneratedData() {
        SoftAssert softAssert = new SoftAssert();

        Allure.step("Открыть страницу добавления клиента", () -> {
            ManagerPage managerPage = new ManagerPage(this.driver);
            AddCustomerPage addCustomerPage = managerPage.clickAddCustomerButton();

            Allure.step("Генерация тестовых данных", () -> {
                String postCode = DataGenerator.generate10DigitNumber();
                List<Integer> numbers = DataGenerator.splitPostCode(postCode);
                String firstName = DataGenerator.numbersToWord(numbers);
                String lastName = DataGenerator.shuffleWord(firstName);

                Allure.step("Заполнение формы клиента", () -> {
                    addCustomerPage.setFirstName(firstName);
                    addCustomerPage.setLastName(lastName);
                    addCustomerPage.setPostCode(postCode);
                });

                Allure.step("Проверка введенных данных", () -> {
                    softAssert.assertEquals(addCustomerPage.getFirstNameValue(), firstName,
                            "Введенное имя не соответствует отображаемому");
                    softAssert.assertEquals(addCustomerPage.getLastNameValue(), lastName,
                            "Введенная фамилия не соответствует отображаемой");
                    softAssert.assertEquals(addCustomerPage.getPostCodeValue(), postCode,
                            "Введенный почтовый индекс не соответствует отображаемому");
                });

                Allure.step("Отправка формы", () -> {
                    addCustomerPage.clickAddCustomerSubmit();
                });

                Allure.step("Проверка алерта", () -> {
                    String alertText = this.driver.switchTo().alert().getText();
                    softAssert.assertTrue(alertText.contains("Customer added successfully"),
                            "Не отобразилось сообщение об успешном добавлении клиента");
                    this.driver.switchTo().alert().accept();
                });
            });
        });

        softAssert.assertAll();
    }
}