package ru.yandex;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainMarketPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public MainMarketPage(WebDriver driver)
    {
        this.driver = driver;
        wait = new WebDriverWait(driver,10);
    }

    @FindBy(xpath = "//input[@id=\"header-search\"]")
    private WebElement searchField;

    @FindBy(xpath = "//input[@id=\"glpricefrom\"]")
    private WebElement priceFromField;

    @FindBy(xpath = "//input[@id=\"glpriceto\"]")
    private WebElement priceToField;

    @FindBy(xpath = "//input[contains(@name,\"Apple\")]/..")
    WebElement chekBoxApple;

    @FindBy(xpath = "//input[contains(@name,\"ASUS\")]/..")
    WebElement chekBoxASUS;

    @FindBy(xpath = "//input[contains(@name,\"HP\")]/..")
    WebElement chekBoxHP;

    @FindBy(xpath = "//input[contains(@name,\"Xiaomi\")]/..")
    WebElement chekBoxXiaomi;

    public void search(String searchText)
    {
        searchField.sendKeys(searchText + Keys.ENTER);
    }

    public void setPriceFilter(Double priceFrom ,Double priceTo)
    {
        priceFromField.clear();
        priceToField.clear();
        priceFromField.sendKeys(priceFrom.toString());
        priceToField.sendKeys(priceTo.toString());
    }

    public void selectChekBoxApple()
    {
        chekBoxApple.click();
    }
    public void selectChekBoxASUS()
    {
        chekBoxApple.click();
    }
    public void selectChekBoxHP()
    {
        chekBoxApple.click();
    }
    public void selectChekBoxXiaomi()
    {
        chekBoxApple.click();
    }
}
