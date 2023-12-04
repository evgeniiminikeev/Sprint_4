package pageobject;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private final String customerName;
    private final String customerSurname;
    private final String customerAddress;
    private final String customerMetroStation;
    private final String customerPhoneNumber;
    private final String customerDeliveryDate;
    private final String commentForDelivery;
    private final boolean expectedResult;

    public OrderTest(String customerName, String customerSurname,
                     String customerAddress, String customerMetroStation,
                     String customerPhoneNumber, String customerDeliveryDate,
                     String commentForDelivery, boolean expectedResult) {
        this.customerName = customerName;
        this.customerSurname = customerSurname;
        this.customerAddress = customerAddress;
        this.customerMetroStation = customerMetroStation;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerDeliveryDate = customerDeliveryDate;
        this.commentForDelivery = commentForDelivery;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"ИмяПокупателя", "ФамилияПокупателя", "АдресПокупателя один", "Бульвар Рокоссовского", "89898989898", "01.01.2024", "Коментарий для доставки тест один", true},
                {"ДВА", "ДВА", "АДРЕС ДВА", "Пятницкое шоссе", "89123456789", "10.02.2024", "ТЕСТ ДВА", true},
        };
    }


    @Before
    public void firefoxSetup(){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }


/*
    @Before
    public void chromeSetup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
*/
    @Test
    public void shouldMakeSuccessfulOrder() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.clickMakeOrderFirstButton();

        OrderPage orderPage = new OrderPage(driver);

        Assert.assertEquals(expectedResult, orderPage.makeOrder(customerName,customerSurname,
                                                                customerAddress,customerMetroStation,
                                                                customerPhoneNumber,customerDeliveryDate,
                                                                commentForDelivery));

    }

    @Test
    public void shouldWorkSecondOrderButton() {
        String expectedOrderPage = "https://qa-scooter.praktikum-services.ru/order";

        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.clickMakeOrderSecondButton();

        Assert.assertEquals(expectedOrderPage, driver.getCurrentUrl());

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
