package svm.sibmirsoft.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class BasePage {
    protected final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected WebElement findElement(By locator) {
        return this.driver.findElement(locator);
    }

    protected void scrollToElement(By locator) {
        WebElement element = this.findElement(locator);
        Actions actions = new Actions(this.driver);
        actions.moveToElement(element).perform();
    }

    protected void click(By locator) {
        this.scrollToElement(locator);
        this.findElement(locator).click();
    }

    protected void inputText(By locator, String text) {
        this.scrollToElement(locator);
        this.findElement(locator).sendKeys(new CharSequence[]{text});
    }

    protected List<String> getTextsFromElements(By locator) {
        return this.driver.findElements(locator).stream().map(WebElement::getText).toList();
    }
}