package com.qa.TDL.selenium;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class seleniumUiTests {
	
	private static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/newdrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1366, 768));
    }
    
    

    @Test
    public void UitestHome() throws InterruptedException {
        driver.get("http://localhost:8905/index.html");
        Thread.sleep(1500);
        assertThat("To Do List").isEqualTo(driver.getTitle());
    }
    
    
    @Test
    public void UitestCreate() throws InterruptedException {
        driver.get("http://localhost:8905/index.html");
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"createBtn\"]")).click();
        Thread.sleep(1500);
        assertThat("Create A Task").isEqualTo(driver.getTitle());
    }
    
    
    @Test
    public void UitestCreateTask() throws InterruptedException {
    	driver.get("http://localhost:8905/index.html");
    	Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"createBtn\"]")).click();
        Thread.sleep(1500);
//        create 
        WebElement createBar = driver.findElement(By.id("inputName"));
        String createTerm = "Shopping";
        createBar.sendKeys(createTerm);
        createBar.submit();
        
//       back
        driver.findElement(By.xpath("//*[@id=\"updateBtn\"]")).click();
        Thread.sleep(1500);
//        is it there
        assertThat(driver.findElement(By.xpath("/html/body/table/tbody/tr/td[2]")).isDisplayed());
    }
    
    
    
    @Test
    public void UitestCreateTaskItem() throws InterruptedException {
    	driver.get("http://localhost:8905/index.html");
    	Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"createBtn\"]")).click();
        Thread.sleep(1500);
//        create
        WebElement createBar = driver.findElement(By.id("inputName"));
        String createTerm = "Shopping";
        createBar.sendKeys(createTerm);
        createBar.submit();
        
//		 back 
        driver.findElement(By.xpath("//*[@id=\"updateBtn\"]")).click();  
        Thread.sleep(1500);
        // click add/update
        driver.findElement(By.xpath("/html/body/table/tbody/tr/td[3]/a")).click();
        Thread.sleep(1500);
   //     click add
        driver.findElement(By.xpath("//*[@id=\"createButton\"]")).click();
        Thread.sleep(1500);
        // check right page 
        assertThat("Create A Task Item").isEqualTo(driver.getTitle());
    }
    
    
    
    @Test
    public void UitestDeleteTask() throws InterruptedException {
    	driver.get("http://localhost:8905/index.html");
    	Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"createBtn\"]")).click();
        Thread.sleep(1500);
//        create Task
        WebElement createBar = driver.findElement(By.id("inputName"));
        String createTerm = "Shopping";
        createBar.sendKeys(createTerm);
        createBar.submit();
        
//        Navigate back
        driver.findElement(By.xpath("//*[@id=\"updateBtn\"]")).click();
        Thread.sleep(1500);
//        click delete
        driver.findElement(By.xpath("/html/body/table/tbody/tr/td[4]/a/html/body/table/tbody/tr/td[4]/a")).click();
        Thread.sleep(2000);
//        check
        assertFalse(driver.findElement(By.xpath("/html/body/table")).isDisplayed());       
    }
    
    
    @AfterClass
    public static void tearDown() {
        driver.close();
    }
    

}
