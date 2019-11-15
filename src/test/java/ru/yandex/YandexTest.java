package ru.yandex;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class YandexTest extends WebDriverSettings {

    @Test
    public void openYandex() throws InterruptedException {
        //Заходим на Яндекс
        driver.get("https://yandex.ru/");
        //Ожидаем появления ссылки на маркет
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-id=\"market\"]")));
        //Находим элемент ссылки на маркет и кликаем на него
        driver.findElement(By.xpath("//a[@data-id='market']")).click();
        //Ждем пока не появится поле поиска
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"header-search\"]")));
        //Находим поле поиска и вводим текст
        String searchText = "ноутбуки";
        WebElement headerSearch = driver.findElement(By.xpath("//input[@id=\"header-search\"]"));
        headerSearch.sendKeys(searchText);
        //Проверяем что поле посика содержит введенный текст
        assertEquals(searchText.toLowerCase(),headerSearch.getAttribute("value").toLowerCase());
        //Находим кнопку "Найти" и кликаем по ней
        driver.findElement(By.xpath("//button[@role=\"button\"]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"search-prepack\"]//legend[.=\"Цена, ₽\"]")));
        //В поле Цена ввести значения 100000 – 200000
        driver.findElement(By.xpath("//input[@id=\"glpricefrom\"]")).sendKeys("100000");
        driver.findElement(By.xpath("//input[@id=\"glpriceto\"]")).sendKeys("200000");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"search-prepack\"]//legend[.=\"Производитель\"]")));
        //Выбрать производителей «Apple», «ASUS», «HP», «Xiaomi»
        driver.findElement(By.xpath("//input[contains(@name,\"Apple\")]/..")).click();
        driver.findElement(By.xpath("//input[contains(@name,\"ASUS\")]/..")).click();
        driver.findElement(By.xpath("//input[contains(@name,\"HP\")]/..")).click();
        driver.findElement(By.xpath("//input[contains(@name,\"Xiaomi\")]/..")).click();

        //Проверить, что соответствующие галочки проставились
        assertTrue(driver.findElement(By.xpath("//input[contains(@name,\"Apple\")]")).isSelected());
        assertTrue(driver.findElement(By.xpath("//input[contains(@name,\"ASUS\")]")).isSelected());
        assertTrue(driver.findElement(By.xpath("//input[contains(@name,\"HP\")]")).isSelected());
        assertTrue(driver.findElement(By.xpath("//input[contains(@name,\"Xiaomi\")]")).isSelected());

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"search-prepack\"]//legend[.=\"Процессор\"]")));
        //Выбрать процессор «Core i7»
        driver.findElement(By.xpath("//input[contains(@name,\"Core i7\")]/..")).click();

        //TODO: Надо как то понять что фильтр применился или дождаться пока он применится
        //Пробуем подождать элемента который скрывает найденное
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"preloadable__preloader preloadable__preloader_visibility_visible preloadable__paranja\"]")));
        //Теперь ждем когда этот элемент пропадет
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class=\"preloadable__preloader preloadable__preloader_visibility_visible preloadable__paranja\"]")));

        //Вытаскиваем все элементы с ценами
        List<WebElement> elementsPrice = driver.findElements(By.xpath("//div[@class=\"price\"]"));

        for (WebElement element : elementsPrice)
        {
            //Временно для проверки вывожу всецены в консоль
            System.out.println(element.getText());
        }

        //Задержка для того что бы посмотреть страничку пока не закрылась, потом убрать
        Thread.sleep(10000);

    }

}
