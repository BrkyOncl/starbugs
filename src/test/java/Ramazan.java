import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Ramazan {
    Faker faker = new Faker();
    WebDriver driver;
    WebElement goingTo;
    WebElement checkIn;
    WebElement checkOutBox;
    WebElement checkOut;
    WebElement room;
    WebElement adults;
    WebElement children;
    WebElement childAge;
​
    @BeforeMethod
    public void setUp(){
​
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://orbitz.com");
​
​
    }
​
    @AfterMethod
    public void closeBrowser(){
​
        driver.close();
​
    }
​
    @Test (priority = 1)
​
    public void hotelList()throws InterruptedException {
​
        System.out.println("#1: HOTEL SEARCH VERIFICATION TEST IS RUNNING");
​
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//button[@data-section-id='#section-hotel-tab-hp']")).click();
​
        goingTo = driver.findElement(By.xpath("//input[@id='hotel-destination-hp-hotel']"));
        goingTo.sendKeys(faker.address().cityName());
​
​
        checkIn = driver.findElement(By.xpath("//input[@id='hotel-checkin-hp-hotel']"));
        Thread.sleep(3000);
        checkIn.sendKeys("11/20/2019");
​
        checkOutBox = driver.findElement(By.id("hotel-checkout-hp-hotel"));
        checkOutBox.click();
        Thread.sleep(3000);
​
        checkOut = driver.findElement(By.xpath("//button[@data-day='30']"));
        checkOut.click();
​
        room = driver.findElement(By.xpath("//select[@name='rooms']"));
        Select roomDropDown = new Select(room);
        roomDropDown.selectByIndex(0);
​
        adults = driver.findElement(By.xpath("//select[@id='hotel-1-adults-hp-hotel']"));
        Select adultsDropDown = new Select(adults);
        adultsDropDown.selectByIndex(0);
​
        children = driver.findElement(By.xpath("//select[@id='hotel-1-children-hp-hotel']"));
        Select childrenDropDown = new Select(children);
        childrenDropDown.selectByIndex(1);
​
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        childAge = driver.findElement(By.xpath("//select[@id='hotel-1-age-select-1-hp-hotel']"));
        Select childAgeDropDown = new Select(childAge);
        childAgeDropDown.selectByValue("6");
​
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//label[@class='col search-btn-col'])[2]")).click();
        Thread.sleep(3000);
​
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains("Hotel-Search"));
​
​
    }
​
    @Test (priority = 2)\
​
    public void hotelAndFlight() throws InterruptedException {
​
        System.out.println("#2: HOTEL + FLIGHT SEARCH VERIFICATION TEST IS RUNNING");
​
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//button[@data-section-id='#section-hotel-tab-hp']")).click();
​
        goingTo = driver.findElement(By.xpath("//input[@id='hotel-destination-hp-hotel']"));
        goingTo.sendKeys(faker.address().cityName());
​
​
        checkIn = driver.findElement(By.xpath("//input[@id='hotel-checkin-hp-hotel']"));
        Thread.sleep(3000);
        checkIn.sendKeys("11/20/2019");
​
        checkOutBox = driver.findElement(By.id("hotel-checkout-hp-hotel"));
        checkOutBox.click();
        Thread.sleep(3000);
​
        checkOut = driver.findElement(By.xpath("//button[@data-day='30']"));
        checkOut.click();
​
        room = driver.findElement(By.xpath("//select[@name='rooms']"));
        Select roomDropDown = new Select(room);
        roomDropDown.selectByIndex(0);
​
        adults = driver.findElement(By.xpath("//select[@id='hotel-1-adults-hp-hotel']"));
        Select adultsDropDown = new Select(adults);
        adultsDropDown.selectByIndex(0);
​
​
        children = driver.findElement(By.xpath("//select[@id='hotel-1-children-hp-hotel']"));
        Select childrenDropDown = new Select(children);
        childrenDropDown.selectByIndex(1);
​
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        childAge = driver.findElement(By.xpath("//select[@id='hotel-1-age-select-1-hp-hotel']"));
        Select childAgeDropDown = new Select(childAge);
        childAgeDropDown.selectByValue("6");
​
        driver.findElement(By.id("hotel-add-flight-checkbox-hp-hotel")).click();
​
​
        String actualText = driver.findElement(By.xpath("//label[@for='hotel-flight-origin-hp-hotel']/*[1]")).getText();
        String expectedText = "Flying from";
        Assert.assertTrue(actualText.equals(expectedText), "Text over flight search box does not match!");
​
    }
​
    @Test (priority = 3)
    public void errorTest() throws InterruptedException{
​
        System.out.println("#3: ERROR MESSAGE VERIFICATION TEST IS RUNNING");
​
​
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//button[@data-section-id='#section-hotel-tab-hp']")).click();
​
        checkIn = driver.findElement(By.xpath("//input[@id='hotel-checkin-hp-hotel']"));
        checkIn.sendKeys("11/20/2019");
​
        checkOut = driver.findElement(By.xpath("//input[@id='hotel-checkout-hp-hotel']"));
        checkOut.sendKeys("11/10/2020");
​
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        room = driver.findElement(By.xpath("//select[@name='rooms']"));
        Select roomDropDown2 = new Select(room);
        roomDropDown2.selectByIndex(0);
​
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        adults = driver.findElement(By.xpath("//select[@id='hotel-1-adults-hp-hotel']"));
        Select adultsDropDown2 = new Select(adults);
        adultsDropDown2.selectByIndex(0);
​
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        children = driver.findElement(By.xpath("//select[@id='hotel-1-children-hp-hotel']"));
        Select childrenDropDown2 = new Select(children);
        childrenDropDown2.selectByIndex(1);
​
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        childAge = driver.findElement(By.xpath("//select[@id='hotel-1-age-select-1-hp-hotel']"));
        Select childAgeDropDown2 = new Select(childAge);
        childAgeDropDown2.selectByValue("6");
​
        driver.findElement(By.xpath("(//label[@class='col search-btn-col'])[2]")).click();
​
        WebElement errorLocater = driver.findElement(By.xpath("//h5[@class='alert-title no-outline']"));
        String errorMessage = errorLocater.getText();
        Assert.assertEquals(errorMessage,"Please correct the errors below.", "Error message does not match!");
​
​
    }

}