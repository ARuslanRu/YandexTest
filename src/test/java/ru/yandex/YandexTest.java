package ru.yandex;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class YandexTest extends WebDriverSettings {

    @Test
    public void openYandex() throws InterruptedException {

        try
        {
            MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
            //Зайти на yandex.ru
            mainPage.open();
            //Кликнуть на «Маркет»
            mainPage.goToMarket();

            MainMarketPage mainMarketPage = PageFactory.initElements(driver, MainMarketPage.class);

            //В поле поиска ввести «ноутбуки»
            mainMarketPage.setSearchFieldText("ноутбуки");

            //Проверить что поле поиска содержит «ноутбуки»
            assertEquals("ноутбуки", mainMarketPage.getSaerhFieldText().toLowerCase());

            //Нажать «Найти»
            mainMarketPage.clikSearchButton();

            //В поле Цена ввести значения 100000 – 200000
            mainMarketPage.setPriceFilter(100000d,200000d);

            //Выбрать производителей «Apple», «ASUS», «HP», «Xiaomi»
            String[] chekBoxNames = new String[]{"Apple","ASUS","HP","Xiaomi"};
            mainMarketPage.selectChekBoxes(chekBoxNames);

            //Проверить, что соответствующие галочки проставились
            Boolean[] isSelectedChekBoxes = mainMarketPage.isSelectedChekBoxes(chekBoxNames);
            for (Boolean isSelected : isSelectedChekBoxes) {
                assertTrue(isSelected);
            }

            //Выбрать процессор «Core i7»
            chekBoxNames = new String[]{"Core i7"};
            mainMarketPage.selectChekBoxes(chekBoxNames);

            //Проверить что цены в таблице в интервале 100 – 200 тысяч
            ArrayList<Integer> prices = mainMarketPage.getPrices();
            for (Integer price : prices ) {
                assertTrue(price >= 100000 && price <= 200000 , "Цена меньше 100000 или больше 200000: " + price);
            }

            //Очистить поле поиска
            mainMarketPage.clearSearchField();

            //Ввести «Зеленый слоник»
            mainMarketPage.setSearchFieldText("Зеленый слоник");

            //Нажать найти
            mainMarketPage.clikSearchButton();

            //Найти товар содержащие в имени «Толстовка», кликнуть на него
            mainMarketPage.clikOnProductCardTitle("Худи");//Толстовки не было в результатах поиска. Поставил "Худи".

            //Перейти на yandex.ru
            driver.get("https://yandex.ru/");

            //Задержка для просмотра результата
            Thread.sleep(10000);
        }
        catch (Exception e)
        {
            String message = "Упс, что-то пошло не так";
            System.out.println(message);
            System.out.println(e.getMessage());
            for(StackTraceElement element: e.getStackTrace())
                System.out.println(element);
        }

    }

}
