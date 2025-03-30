package svm.sibmirsoft.tests;

import java.util.List;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import svm.sibmirsoft.helpers.DataGenerator;
import svm.sibmirsoft.pages.AddCustomerPage;
import svm.sibmirsoft.pages.ManagerPage;

public class AddCustomerTest extends BaseTest {
    public AddCustomerTest() {
    }

    @Test
    public void testAddCustomerWithGeneratedData() {
        SoftAssert softAssert = new SoftAssert();
        ManagerPage managerPage = new ManagerPage(this.driver);
        AddCustomerPage addCustomerPage = managerPage.clickAddCustomerButton();
        String postCode = DataGenerator.generate10DigitNumber();
        List<Integer> numbers = DataGenerator.splitPostCode(postCode);
        String firstName = DataGenerator.numbersToWord(numbers);
        String lastName = DataGenerator.shuffleWord(firstName);
        addCustomerPage.setFirstName(firstName);
        addCustomerPage.setLastName(lastName);
        addCustomerPage.setPostCode(postCode);
        softAssert.assertEquals(addCustomerPage.getFirstNameValue(), firstName, "Несоответствие значения имени");
        softAssert.assertEquals(addCustomerPage.getLastNameValue(), lastName, "Несоответствие значения фамилии");
        softAssert.assertEquals(addCustomerPage.getPostCodeValue(), postCode, "Несоответствие значения почтового индекса");
        addCustomerPage.clickAddCustomerSubmit();
        String alertText = this.driver.switchTo().alert().getText();
        softAssert.assertTrue(alertText.contains("Customer added successfully"), "Неверный алерт");
        this.driver.switchTo().alert().accept();
        softAssert.assertAll();
    }
}