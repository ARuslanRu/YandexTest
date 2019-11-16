package ru.yandex;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public MainPage(WebDriver driver)
    {
        this.driver = driver;
        wait = new WebDriverWait(driver,10);
    }

    @FindBy(xpath = "//a[@data-id=\"market\"]")
    private WebElement marketLink;

    public void open()
    {
        driver.get("https://yandex.ru/");
    }

    public void goToMarket()
    {
        marketLink.click();
    }

}
