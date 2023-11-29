package page_object;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class shouldMakeSuccessfulOrder {
    private WebDriver driver;

/*
    @Before
    public void firefoxSetup(){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }
*/


    @Before
    public void chromeSetup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void checkMakingOrder() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.clickMakeOrderButton();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.makeOrder("ИмяПокупателя", "ФамилияПокупателя", "АдресПокупателя тест",
                "Бульвар Рокоссовского", "89898989898", "01.01.2024", "Коментарий для доставки тест");

        Assert.assertTrue(orderPage.isOrderSuccessful());

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
