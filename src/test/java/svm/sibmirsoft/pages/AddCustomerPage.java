package svm.sibmirsoft.pages;

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

    public void setFirstName(String firstName) {
        this.inputText(this.firstNameInput, firstName);
    }

    public void setLastName(String lastName) {
        this.inputText(this.lastNameInput, lastName);
    }

    public void setPostCode(String postCode) {
        this.inputText(this.postCodeInput, postCode);
    }

    public void clickAddCustomerSubmit() {
        this.click(this.addCustomerSubmit);
    }

    public void addCustomer(String firstName, String lastName, String postCode) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setPostCode(postCode);
        this.clickAddCustomerSubmit();
    }

    public String getFirstNameValue() {
        return this.getFieldValue(this.firstNameInput);
    }

    public String getLastNameValue() {
        return this.getFieldValue(this.lastNameInput);
    }

    public String getPostCodeValue() {
        return this.getFieldValue(this.postCodeInput);
    }
}