import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Cholpon {

    public class ActivityFunctionalityFromOrbitzCom {
        WebDriver driver;
        //
        @BeforeMethod
        public void firstStep(){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.get("http://orbitz.com");
        }
        @Test
        public void FirstCountry()throws InterruptedException{
            WebElement activity = driver.findElement(By.id("primary-header-activity"));
            activity.click();
            WebElement country =  driver.findElement(By.name("location"));
            country.sendKeys("Tokyo,Japan");
            driver.findElement(By.id("activity-tabs")).click();
            Thread.sleep(2000);
            WebElement fromDate = driver.findElement(By.xpath("//input[@id='activity-start-alp']"));
            // driver.findElement(By.xpath("(//button[@data-day='22'])[1]")).click();
            fromDate.sendKeys("07/22/2019");
            WebElement endDate =  driver.findElement(By.xpath("//input[@id='activity-end-alp']"));
            endDate.click();
            driver.findElement(By.xpath("//button[@data-day='2']")).click();
            //  driver.findElement(By.xpath("(//button[@data-day='14'])[2]")).click();
//   WebElement startFrom = driver.findElement(By.id("activity-start-alp"));
//   driver.findElement(By.xpath("//button[@data-day='21']")).click();
            driver.findElement(By.xpath("(//button[@type='submit'])[11]")).click();
            WebElement result = driver.findElement(By.xpath("//h1[@id='titleHeading']"));
            String resultCheck = "things to do";
            if(result.getText().contains(resultCheck)){
                System.out.println("Activity search verification passed");
            }else{
                System.out.println("Activity search verification failed");
            }
        }
        @Test
        public void negativeTestingForActivityFunctionality(){
            driver.findElement(By.id("primary-header-activity")).click();
            WebElement destinationButton = driver.findElement(By.name("location"));
            destinationButton.sendKeys("634sjbhbxncnbmbxcd  \\");
            WebElement fromDate = driver.findElement(By.xpath("//input[@id='activity-start-alp']"));
            fromDate.sendKeys("hgsdfhgd64576545.....");
            WebElement endDate =  driver.findElement(By.xpath("//input[@id='activity-end-alp']"));
            endDate.click();
            endDate.sendKeys("???456345hjbDSSDSFSDF__cbc");
            driver.findElement(By.xpath("(//button[@type='submit'])[11]")).click();
            WebElement negativeResultMainTitle = driver.findElement(By.xpath("//*[@id=\"gcw-activities-form-alp\"]/div[2]/h5"));
            Assert.assertTrue(negativeResultMainTitle.isDisplayed());
        }
        @Test
        public void checkMessages(){
            List<WebElement> invalidDate1 = driver.findElements(By.xpath("//a[@class='error-link'][1]"));
            for (WebElement w: invalidDate1) {
                Assert.assertTrue(w.isDisplayed());
            }
            Assert.assertTrue(driver.getTitle().contains("Vacation"));
        }
        @AfterMethod
        public void finish(){
            driver.close();
        }
    }
}
