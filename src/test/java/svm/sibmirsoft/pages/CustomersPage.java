package svm.sibmirsoft.pages;

import java.util.List;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CustomersPage extends BasePage {
    private final By firstNameHeader = By.xpath("//a[@ng-click=\"sortType = 'fName'; sortReverse = !sortReverse\"]");
    private final By firstNameCells = By.xpath("//tr[@ng-repeat='cust in Customers | orderBy:sortType:sortReverse " +
            "| filter:searchCustomer']/td[1]");
    private final By searchCustomerInput = By.xpath("//input[@ng-model='searchCustomer']");;
    private final By accountNumberCells = By.xpath("//td[.//*[@ng-repeat='account in cust.accountNo']]");
    private final By deleteButtons = By.xpath("//button[@ng-click='deleteCust(cust)']");

    public CustomersPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажатие на заголовок столбца 'First Name' для сортировки")
    public CustomersPage clickFirstNameHeader() {
        click(firstNameHeader);
        return this;
    }

    @Step("Получение списка всех имен клиентов")
    public List<String> getAllFirstNames() {
        return getTextsFromElements(firstNameCells);
    }

    @Step("Поиск клиента по имени: {name}")
    public CustomersPage searchCustomer(String name) {
        inputText(searchCustomerInput, name);
        return this;
    }

    @Step("Удаление первого найденного клиента")
    public CustomersPage deleteFirstFoundCustomer() {
        click(deleteButtons);
        return this;
    }

    @Step("Получение номеров счетов")
    public List<String> getAllAccountNumbers() {
        return getTextsFromElements(accountNumberCells);
    }
}