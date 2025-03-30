package svm.sibmirsoft.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddCustomerPage extends BasePage {
    private final By firstNameInput = By.xpath("//input[@placeholder='First Name']");
    private final By lastNameInput = By.xpath("//input[@placeholder='Last Name']");
    private final By postCodeInput = By.xpath("//input[@placeholder='Post Code']");
    private final By addCustomerSubmit = By.xpath("//button[@type='submit']");

    public AddCustomerPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввести имя клиента: {firstName}")
    public void setFirstName(String firstName) {
        this.inputText(this.firstNameInput, firstName);
    }

    @Step("Ввести фамилию клиента: {lastName}")
    public void setLastName(String lastName) {
        this.inputText(this.lastNameInput, lastName);
    }

    @Step("Ввести почтовый индекс: {postCode}")
    public void setPostCode(String postCode) {
        this.inputText(this.postCodeInput, postCode);
    }

    @Step("Нажать кнопку 'Добавить клиента'")
    public void clickAddCustomerSubmit() {
        this.click(this.addCustomerSubmit);
    }

    @Step("Добавить нового клиента (Имя: {firstName}, Фамилия: {lastName}, Индекс: {postCode})")
    public void addCustomer(String firstName, String lastName, String postCode) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setPostCode(postCode);
        this.clickAddCustomerSubmit();
    }

    @Step("Получить значение поля 'Имя'")
    public String getFirstNameValue() {
        return this.getFieldValue(this.firstNameInput);
    }

    @Step("Получить значение поля 'Фамилия'")
    public String getLastNameValue() {
        return this.getFieldValue(this.lastNameInput);
    }

    @Step("Получить значение поля 'Почтовый индекс'")
    public String getPostCodeValue() {
        return this.getFieldValue(this.postCodeInput);
    }
}