package ru.yandex;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverSettings {
    public static WebDriver driver;

    @BeforeAll
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver_win32_78.0.3904.70.exe");
        driver = new ChromeDriver();
        System.out.println("Запуск теста");
    }

    @AfterAll
    public static void close(){
        System.out.println("Тест окончен");
        driver.quit();
    }


}
