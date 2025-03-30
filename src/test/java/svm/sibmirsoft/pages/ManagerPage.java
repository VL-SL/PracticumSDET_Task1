package svm.sibmirsoft.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ManagerPage extends BasePage {
    private final By addCustomerButton = By.xpath("//button[contains(text(),'Add Customer')]");
    private final By customersButton = By.xpath("//button[contains(text(),'Customers')]");

    public ManagerPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажать кнопку 'Add Customer' для перехода на страницу добавления клиента")
    public AddCustomerPage clickAddCustomerButton() {
        this.click(this.addCustomerButton);
        return new AddCustomerPage(this.driver);
    }

    @Step("Нажать кнопку 'Customers' для перехода к списку клиентов")
    public CustomersPage clickCustomersButton() {
        this.click(this.customersButton);
        return new CustomersPage(this.driver);
    }
}