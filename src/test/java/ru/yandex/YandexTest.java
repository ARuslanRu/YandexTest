package ru.yandex;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexTest extends WebDriverSettings {

    @Test
    public void openYandex()
    {
        driver.get("https://yandex.ru/");

        WebDriverWait wait = new WebDriverWait(driver ,10);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-id=\"market\"]")));

        WebElement market = driver.findElement(By.xpath("//a[@data-id='market']"));
        market.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"header-search\"]")));

        WebElement headerSearch = driver.findElement(By.xpath("//input[@id=\"header-search\"]"));

        headerSearch.sendKeys("ноутбуки");
        WebElement searchButton = driver.findElement(By.xpath("//button[@role=\"button\"]"));
        searchButton.click();



        String str = "Hi";
        Assertions.assertTrue(str.equals("Hi"));
    }

}
