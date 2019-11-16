package ru.yandex;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMarketPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public MainMarketPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,10);
    }

    @FindBy(xpath = "//input[@id=\"header-search\"]")
    private WebElement searchField;

    @FindBy(xpath = "//button[@role=\"button\"]")
    private WebElement searchButton;

    @FindBy(xpath = "//input[@id=\"glpricefrom\"]")
    private WebElement priceFromField;

    @FindBy(xpath = "//input[@id=\"glpriceto\"]")
    private WebElement priceToField;

    @FindBy(xpath = "//div[@class=\"price\"]")
    private List<WebElement> prices;

    private By filterApplyLocator = By.xpath("//div[@class=\"preloadable__preloader preloadable__preloader_visibility_visible preloadable__paranja\"]");
    private By searchApplyLocator = By.xpath("//div[contains(@id,\"product-\")]");

    //Ввод текста для поиска
    public void setSearchFieldText(String searchText) {
        searchField.sendKeys(searchText);
    }

    //Очистка поля поиска
    public void clearSearchField() {
        searchField.clear();
    }

    //Клик по кнопке Найти
    public void clikSearchButton(){
        searchButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchApplyLocator));
    }

    //возврат текста из поля для поиска
    public String getSaerhFieldText() {
        return searchField.getAttribute("value");
    }

    //Установка фильтра цены
    public void setPriceFilter(Double priceFrom ,Double priceTo) {
        priceFromField.clear();
        priceToField.clear();
        priceFromField.sendKeys(priceFrom.toString());
        priceToField.sendKeys(priceTo.toString());
    }

    //Установка чекбоксов в фильтре
    public void selectChekBoxes(String[] chekBoxNames) {
        for (String name : chekBoxNames) {
            By chekBoxLocator = By.xpath("//input[contains(@name,\"" + name + "\")]/..");
            driver.findElement(chekBoxLocator).click();
        }
        waitFilterApply();
    }

    //Проверка чекбоксов в фильтре
    public Boolean[] isSelectedChekBoxes(String[] chekBoxNames) {
        Boolean[] results = new Boolean[chekBoxNames.length];

        for (int i = 0; i <chekBoxNames.length ; i++) {
            By chekBoxLocator = By.xpath("//input[contains(@name,\"" + chekBoxNames[i] + "\")]");
            results[i] = driver.findElement(chekBoxLocator).isSelected();
        }
        return results;
    }

    //Ожидание применения фильтра
    public void waitFilterApply(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(filterApplyLocator));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(filterApplyLocator));
    }

    //Получение цен из карточек товаров
    public ArrayList<Integer> getPrices() {
        ArrayList<Integer> intPrices = new ArrayList<Integer>();

        for (WebElement element : prices) {
            String text = element.getText().replaceAll(" ", "");

            Matcher matcher = Pattern.compile("[0-9]+").matcher(text);

            while (matcher.find()) {
                intPrices.add(Integer.parseInt(matcher.group()));
            }
        }
        return intPrices;
    }

    //Переход по ссылке из карточки товара
    public void clikOnProductCardTitle(String title) {
        driver.findElement(By.xpath("//div[@class=\"n-snippet-card2__title\"]/a[contains(text(),\"" + title + "\")]")).click();
    }
}
