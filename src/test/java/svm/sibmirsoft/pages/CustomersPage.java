package svm.sibmirsoft.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CustomersPage extends BasePage {
    private final By firstNameHeader = By.xpath("//a[@ng-click=\"sortType = 'fName'; sortReverse = !sortReverse\"]");
    private final By firstNameCells = By.xpath("//table//tbody//tr/td[1]");
    private final By searchCustomerInput = By.xpath("//input[@placeholder='Search Customer']");
    private final By customerRows = By.xpath("//table[@class='table table-bordered table-striped']//tbody//tr");
    private final By accountNumberCells = By.xpath("//table//tbody//tr/td[4]");
    private final By deleteButtons = By.xpath("//button[contains(text(),'Delete')]");

    public CustomersPage(WebDriver driver) {
        super(driver);
    }

    public void clickFirstNameHeader() {
        this.click(this.firstNameHeader);
    }

    public List<String> getAllFirstNames() {
        return this.getTextsFromElements(this.firstNameCells);
    }

    public void searchCustomer(String name) {
        this.inputText(this.searchCustomerInput, name);
    }

    public void deleteFirstFoundCustomer() {
        this.click(this.deleteButtons);
    }

    public String getFirstFoundAccountNumber() {
        return (String)this.getTextsFromElements(this.accountNumberCells).get(0);
    }

    public int getVisibleCustomersCount() {
        return this.findElements(this.customerRows).size();
    }

    public List<String> getAllAccountNumbers() {
        return this.getTextsFromElements(this.accountNumberCells);
    }
}