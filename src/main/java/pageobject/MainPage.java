package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait driverWait;
    //Локатор кнопки "Заказать", которая сверху
    private final By makeOrderFirstButtonLocator = By.xpath(".//button[@class='Button_Button__ra12g']");
    //Локатор кнопки "Заказать", которая внизу
    private final By makeOrderSecondButtonLocator = By.xpath(".//button[@class='Button_Button__ra12g Button_UltraBig__UU3Lp']");
    //Локатор кнопок раздела "Вопросы о важном"
    private final By questionElementButtonLocator = By.xpath(".//div[contains(@id,'accordion__heading')]");
    //Локатор текста ответов на вопросы в разделе "Вопросы о важном"
    private final By answerElementTextLocator = By.xpath(".//div[contains(@id,'accordion__panel')]/p");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.driverWait = new WebDriverWait(driver, 3);
    }

    //Открытие главной страницы ресурса
    public void openPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
    //Нажатие на первую кнопку совершения заказа
    public void clickMakeOrderFirstButton() {
        driver.findElement(makeOrderFirstButtonLocator).click();
    }
    //Нажатие на вторую кнопку совершения заказа
    public void clickMakeOrderSecondButton() {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(makeOrderFirstButtonLocator));
        WebElement element = driver.findElements(makeOrderFirstButtonLocator).get(0);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",element);
        element.click();
    }
    //Получение текста вопроса раздела "Вопросы о важном" по его порядковому номеру
    public String getTextOfQuestionByIndex(int indexOfFaq) {
        return driver.findElements(questionElementButtonLocator).get(indexOfFaq).getText();
    }
    //Получение текста ответа раздела "Вопросы о важном" по его порядковому номеру
    public String getTextOfAnswerByIndex(int indexOfFaq) {
        return driver.findElements(answerElementTextLocator).get(indexOfFaq).getText();
    }

    //Открытие вопроса в разделе "Вопросы о важном" по его номеру
    public void openQuestionByIndex(int indexOfFaq) {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(questionElementButtonLocator));
        WebElement element = driver.findElements(questionElementButtonLocator).get(indexOfFaq);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",element);
        element.click();

    }

}
