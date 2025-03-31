package svm.sibmirsoft.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CustomersPage extends BasePage {
    private final By firstNameHeader = By.cssSelector("a[ng-click*='fName']");;
    private final By firstNameCells = By.xpath("//tr[contains(@ng-repeat, 'cust in Customers')]/td[1]");
    private final By searchCustomerInput = By.xpath("//input[@ng-model='searchCustomer']");;
    private final By accountNumberCells = By.xpath("//td[.//*[@ng-repeat='account in cust.accountNo']]");
    private final By deleteButtons = By.xpath("//button[@ng-click='deleteCust(cust)']");

    public CustomersPage(WebDriver driver) {
        super(driver);
    }

    public CustomersPage clickFirstNameHeader() {
        click(firstNameHeader);
        return this;
    }

    public List<String> getAllFirstNames() {
        return getTextsFromElements(firstNameCells);
    }

    public CustomersPage searchCustomer(String name) {
        inputText(searchCustomerInput, name);
        return this;
    }

    public CustomersPage deleteFirstFoundCustomer() {
        click(deleteButtons);
        return this;
    }

    public List<String> getAllAccountNumbers() {
        return getTextsFromElements(accountNumberCells);
    }
}