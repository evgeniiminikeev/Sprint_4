package page_object;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Parameterized.class)
public class ShouldOpenAnswersInFaq {
    private WebDriver driver;
    private final int indexOfFaq;
    private final String expectedQuestion;
    private final String expectedAnswer;

    public ShouldOpenAnswersInFaq(int indexOfFaq, String expectedQuestion, String expectedAnswer) {
        this.indexOfFaq = indexOfFaq;
        this.expectedQuestion = expectedQuestion;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {0, "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }


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
    public void checkOpeningQuestions() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.openQuestionByIndex(indexOfFaq);
        Assert.assertEquals(expectedQuestion, mainPage.getTextOfQuestionByIndex(indexOfFaq));
        Assert.assertEquals(expectedAnswer, mainPage.getTextOfAnswerByIndex(indexOfFaq));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
