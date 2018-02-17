package Test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class FirstTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void dragAndDropElement() {

        driver.get("http://demoqa.com/droppable/");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement element = driver.findElement(By.xpath("//div[@id='draggableview']"));
        WebElement target = driver.findElement(By.xpath("//div[@id='droppableview']"));

        Actions builder = new Actions(driver);
        builder.dragAndDrop(element, target).build().perform();

        assertTrue("Document has not been dropped.", target.getText().equals("Dropped!"));
        assertThat(target.getText()).isEqualToIgnoringCase("Dropped!");
    }

    @Test
    public void pickADate() {

        driver.get("http://demoqa.com/datepicker/");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement field = driver.findElement(By.xpath("//input[@id='datepicker1']"));
        field.click();
        //WebElement day = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='ui-state-default' and (normalize-space(text())='18')]")));
        WebElement day = driver.findElement(By.xpath("//a[@class='ui-state-default' and (normalize-space(text())='18')]"));
        day.click();

        assertEquals("Date 18.02.2018", field.getAttribute("value"), "February 18, 2018");

    }

    @After
    public void tearDown() {
        driver.close();
    }
}




