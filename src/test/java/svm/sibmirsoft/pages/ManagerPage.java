package svm.sibmirsoft.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ManagerPage extends BasePage {
    private final By addCustomerButton = By.xpath("//button[contains(text(),'Add Customer')]");
    private final By customersButton = By.xpath("//button[contains(text(),'Customers')]");

    public ManagerPage(WebDriver driver) {
        super(driver);
    }

    public AddCustomerPage clickAddCustomerButton() {
        this.click(this.addCustomerButton);
        return new AddCustomerPage(this.driver);
    }

    public CustomersPage clickCustomersButton() {
        this.click(this.customersButton);
        return new CustomersPage(this.driver);
    }
}