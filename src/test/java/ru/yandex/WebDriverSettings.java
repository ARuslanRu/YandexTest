package ru.yandex;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverSettings {
    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeAll
    public static void beforeAll() {
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver_win32_78.0.3904.70.exe");
        //Перед запуском  тестового метода вывести сообщение «Запуск теста»
        System.out.println("Запуск теста");
    }

    @BeforeEach
    public void beforeEach() {
        driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver ,10);
    }

    @AfterEach
    public void afterEach() {
        driver.quit();
    }

    @AfterAll
    public static void afterAll() {
        //При окончании теста вывести сообщение «Тест окончен»
        System.out.println("Тест окончен");
    }
}
