package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    private final WebDriver driver;
    private final WebDriverWait driverWait;


    //Локатор поля именя покупателя в форме "Для кого самокат"
    private final By customerNameLocator = By.xpath(".//input[@placeholder='* Имя']");
    //Локатор поля фамилии покупателя в форме "Для кого самокат"
    private final By customerSurnameLocator = By.xpath(".//input[@placeholder='* Фамилия']");
    //Локатор поля адреса покупателя в форме "Для кого самокат"
    private final By customerAddressLocator = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Локатор поля станции метро покупателя в форме "Для кого самокат"
    private final By customerMetroStationLocator = By.xpath(".//input[@placeholder='* Станция метро']");
    //Локатор поля номера телефона покупателя в форме "Для кого самокат"
    private final By customerPhoneNumberLocator = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");


    //Локатор поля даты доставки в форме "Про аренду"
    private final By deliveryDateLocator = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Локатор поля срока аренды в форме "Про аренду"
    private final By deliveryTimeLocator = By.className("Dropdown-placeholder");
    //Локатор опции одного дня аренды в выпадающем списке выбора срока аренды в форме "Про аренду"
    private final By deliveryTimeOneDayLocator = By.xpath(".//div[@class='Dropdown-option' and text()='сутки']");
    //Локатор чекбокса черного цвета самоката в форме "Про аренду"
    private final By scooterBlackColourCheckboxLocator = By.id("black");
    //Локатор поля комментария для курьера в форме "Про аренду"
    private final By commentForDeliveryLocator = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    //Локатор сообщения об успешно совершенном заказе
    private final By orderIsSuccessfulMessageLocator = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']");


    //Локатор кнопки Далее в форме "Для кого самокат"
    private final By nextButtonLocator = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Далее']");
    private final By makeOrderButtonLocator = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");
    private final By accecptOrderButtonLocator = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");
    //Локатор кнопки "Посмотреть статус" должен быть виден, после нажатия кнопки подтверждения заказа "Да"
    private final By orderStatusButtonLocator = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Посмотреть статус']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.driverWait = new WebDriverWait(driver, 3);
    }

    //Указываем имя покупателя в форме "Для кого самокат"
    private void setCustomerName(String customerName) {
        driver.findElement(customerNameLocator).sendKeys(customerName);
    }
    //Указываем фамилию покупателя в форме "Для кого самокат"
    private void setCustomerSurname(String customerSurname) {
        driver.findElement(customerSurnameLocator).sendKeys(customerSurname);
    }
    //Указываем адрес доставки покупателя в форме "Для кого самокат"
    private void setCustomerAddress(String customerAddress) {
        driver.findElement(customerAddressLocator).sendKeys(customerAddress);
    }
    //Указываем станцию метро покупателя в форме "Для кого самокат"
    private void setCustomerMetroStation(String customerMetroStation) {
        driver.findElement(customerMetroStationLocator).click();
        driver.findElement(customerMetroStationLocator).sendKeys(customerMetroStation);
        driver.findElement(customerMetroStationLocator).sendKeys(Keys.DOWN);
        driver.findElement(customerMetroStationLocator).sendKeys(Keys.ENTER);
    }
    //Указываем телефон покупателя в форме "Для кого самокат"
    private void setCustomerPhoneNumber(String customerPhoneNumber) {
        driver.findElement(customerPhoneNumberLocator).sendKeys(customerPhoneNumber);
    }

    //Указываем дату доставки в форме "Про аренду"
    private void setDeliveryDate(String deliveryDate) {
        driver.findElement(deliveryDateLocator).click();
        driver.findElement(deliveryDateLocator).sendKeys(deliveryDate);
        driver.findElement(deliveryDateLocator).sendKeys(Keys.DOWN);
        driver.findElement(deliveryDateLocator).sendKeys(Keys.ENTER);
    }
    //Указываем срок аренды (один день) в форме "Про аренду"
    private void setDeliveryTime() {
        driver.findElement(deliveryTimeLocator).click();
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(deliveryTimeOneDayLocator));
        driver.findElement(deliveryTimeOneDayLocator).click();
    }
    //Указываем черный цвет самоката (нажимаем на чек-бокс) в форме "Про аренду"
    private void setScooterBlackColour() {
        driver.findElement(scooterBlackColourCheckboxLocator).click();
    }
    //Указываем комментарий для доставки в форме "Про аренду"
    private void setCommentForDelivery(String commentForDelivery) {
        driver.findElement(commentForDeliveryLocator).sendKeys(commentForDelivery);
    }


    //Нажимаем кнопку "Далее" в форме "Для кого самокат"
    private void clickNextButton() {
        driver.findElement(nextButtonLocator).click();
    }
    //Нажимае кнопку "Заказать" в форме "Про аренду"
    private void clickMakeOrderButton() {
        driver.findElement(makeOrderButtonLocator).click();
    }
    //Нажимае кнопку "Да" в форме "Хотите оформить заказ" для подтверждения заказа
    private void clickAccecptOrderButton() {
        driver.findElement(accecptOrderButtonLocator).click();
    }


    //Заполняем данные в форме "Для кого самокат и нажимаем кнопку Далее"
    private void loginCustomerData(String customerName, String customerSurname, String customerAddress, String customerMetroStation, String customerPhoneNumber) {
        setCustomerName(customerName);
        setCustomerSurname(customerSurname);
        setCustomerAddress(customerAddress);
        setCustomerMetroStation(customerMetroStation);
        setCustomerPhoneNumber(customerPhoneNumber);
        clickNextButton();
    }
    //Заполняем данные в форме "Про аренду", нажимаем кнопку Заказать и затем нажимаем кнопку Да
    private void loginDeliveryData(String deliveryDate, String commentForDelivery){
        setDeliveryDate(deliveryDate);
        setDeliveryTime();
        setScooterBlackColour();
        setCommentForDelivery(commentForDelivery);
        clickMakeOrderButton();
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(accecptOrderButtonLocator));
        clickAccecptOrderButton();
    }

    //Проверяем что оказались на странице с сообщение об успешном заказе
    public boolean isOrderSuccessful() {
        return driver.findElement(orderIsSuccessfulMessageLocator).isDisplayed();
    }

    //Проходим полный цикл заказа самоката
    public boolean makeOrder(String customerName, String customerSurname, String customerAddress, String customerMetroStation, String customerPhoneNumber,
                          String deliveryDate, String commentForDelivery) {
        loginCustomerData(customerName, customerSurname, customerAddress, customerMetroStation, customerPhoneNumber);
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(deliveryDateLocator));
        loginDeliveryData(deliveryDate, commentForDelivery);
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(orderIsSuccessfulMessageLocator));
        return isOrderSuccessful();
    }


}
