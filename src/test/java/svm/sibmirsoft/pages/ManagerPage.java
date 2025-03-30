package svm.sibmirsoft.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ManagerPage extends BasePage {
    private final By addCustomerButton = By.xpath("//button[@ng-click='addCust()']");
    private final By customersButton = By.xpath("//button[@ng-click='showCust()']");

    public ManagerPage(WebDriver driver) {
        super(driver);
    }

    public AddCustomerPage clickAddCustomerButton() {
        click(addCustomerButton);
        return new AddCustomerPage(driver);
    }

    public CustomersPage clickCustomersButton() {
        click(customersButton);
        return new CustomersPage(driver);
    }
}