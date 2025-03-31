package svm.sibmirsoft.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddCustomerPage extends BasePage {
    private final By firstNameInput = By.xpath("//input[@ng-model='fName']");
    private final By lastNameInput = By.xpath("//input[@ng-model='lName']");
    private final By postCodeInput = By.xpath("//input[@ng-model='postCd']");
    private final By addCustomerSubmit = By.xpath("//button[@type='submit']");

    public AddCustomerPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввод имени: {firstName}")
    public AddCustomerPage setFirstName(String firstName) {
        inputText(firstNameInput, firstName);
        return this;
    }

    @Step("Ввод фамилии: {lastName}")
    public AddCustomerPage setLastName(String lastName) {
        inputText(lastNameInput, lastName);
        return this;
    }

    @Step("Ввод почтового индекса: {postCode}")
    public AddCustomerPage setPostCode(String postCode) {
        inputText(postCodeInput, postCode);
        return this;
    }

    @Step("Нажатие кнопки добавления клиента")
    public AddCustomerPage clickAddCustomerSubmit() {
        click(addCustomerSubmit);
        return this;
    }

    @Step("Добавление клиента с общими данными")
    public void addCustomer(String firstName, String lastName, String postCode) {
        setFirstName(firstName)
                .setLastName(lastName)
                .setPostCode(postCode)
                .clickAddCustomerSubmit();
    }
}