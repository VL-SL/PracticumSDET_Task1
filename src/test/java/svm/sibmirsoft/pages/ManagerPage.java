package svm.sibmirsoft.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ManagerPage extends BasePage {
    private final By addCustomerButton = By.xpath("//button[@ng-click='addCust()']");
    private final By customersButton = By.xpath("//button[@ng-click='showCust()']");

    public ManagerPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажатие кнопки 'Add Customer'")
    public AddCustomerPage clickAddCustomerButton() {
        click(addCustomerButton);
        return new AddCustomerPage(driver);
    }

    @Step("Нажатие кнопки 'Customers'")
    public CustomersPage clickCustomersButton() {
        click(customersButton);
        return new CustomersPage(driver);
    }
}