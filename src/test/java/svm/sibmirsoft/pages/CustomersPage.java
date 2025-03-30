package svm.sibmirsoft.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.List;

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

    @Step("Нажать на заголовок столбца 'First Name' для сортировки")
    public void clickFirstNameHeader() {
        this.click(this.firstNameHeader);
    }

    @Step("Получить список всех имен клиентов")
    public List<String> getAllFirstNames() {
        return this.getTextsFromElements(this.firstNameCells);
    }

    @Step("Поиск клиента по имени: {name}")
    public void searchCustomer(String name) {
        this.inputText(this.searchCustomerInput, name);
    }

    @Step("Удалить первого найденного клиента")
    public void deleteFirstFoundCustomer() {
        this.click(this.deleteButtons);
    }

    @Step("Получить список всех номеров счетов")
    public List<String> getAllAccountNumbers() {
        return this.getTextsFromElements(this.accountNumberCells);
    }
}