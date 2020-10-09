package com.qa.TDL.Selenium;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumUiTests {
	
	 private static WebDriver driver;

	    @BeforeClass
	    public static void Uisetup() {
	        System.setProperty("webdriver.chrome.driver", "src/test/java/newDrivers/chromedriver.exe");
	        driver = new ChromeDriver();
	        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	        driver.manage().window().setSize(new Dimension(1366, 768));

	    }

	    @Test
	    public void UitestMain() throws InterruptedException {
	        driver.get("http://localhost:8905/index.html");
	        Thread.sleep(1000);
	        assertThat("To Do List").isEqualTo(driver.getTitle());
	    }
	    
	    
	    @Test
	    public void UitestCreate() throws InterruptedException {
	        driver.get("http://localhost:8905/index.html");
	        Thread.sleep(1000);
//	        navigate to create Task page
	        driver.findElement(By.xpath("//*[@id=\"createBtn\"]")).click();
	        Thread.sleep(1000);
	        assertThat("Create A Task").isEqualTo(driver.getTitle());
	    }
	    
	    
	    @Test
	    
	    public void UitestCreateTask() throws InterruptedException {
	        driver.get("http://localhost:8905/index.html");
	        
//	        Click Create
	        driver.findElement(By.xpath("//*[@id=\"createBtn\"]")).click();
	        Thread.sleep(1000);
//	        create a Task
	        WebElement input = driver.findElement(By.id("inputName"));
	        String taskInput = "Shopping";
	        input.sendKeys(taskInput);
	        input.submit();
	        
//	        Click Create
	        driver.findElement(By.xpath("//*[@id=\"updateBtn\"]")).click();
	        Thread.sleep(1000);
//	        Is Task There
	        assertThat(driver.findElement(By.xpath("/html/body/table")).isDisplayed());
	    }
	        
	    
	    @Test
	    public void UitestCreateTaskItem() throws InterruptedException {
	    	 driver.get("http://localhost:8905/index.html");
	    	 Thread.sleep(1000);
//		        Click Create
		        driver.findElement(By.xpath("//*[@id=\"createBtn\"]")).click();
		        Thread.sleep(1000);
//		        create a Task
		        WebElement input = driver.findElement(By.id("inputName"));
		        String taskInput = "Shopping";
		        input.sendKeys(taskInput);
		        input.submit();
		        
//		        Click Create
		        driver.findElement(By.xpath("//*[@id=\"updateBtn\"]")).click();
		        
//		       Click Add/update
		 //       driver.findElement(By.xpath("/html/body/table/tbody/tr/td[3]/a"]")).click();
	        
//	        click the ADD button
	        driver.findElement(By.xpath("//*[@id=\"createButton\"]")).click();
	        Thread.sleep(1000);
	        assertThat("Create A Task Item").isEqualTo(driver.getTitle());        
	    }
	    

	    
	    
	    @Test
	    public void testDeleteTask() throws InterruptedException {
	    	
	    	 driver.get("http://localhost:8905/index.html");
	    	 Thread.sleep(1000);
//		        Click Create
		        driver.findElement(By.xpath("//*[@id=\"createBtn\"]")).click();
		        Thread.sleep(1000);
//		        create a Task
		        WebElement input = driver.findElement(By.id("inputName"));
		        String taskInput = "Shopping";
		        input.sendKeys(taskInput);
		        input.submit();
		        
//		        Click Create
		        driver.findElement(By.xpath("//*[@id=\"updateBtn\"]")).click();
		        Thread.sleep(1000);
//	        click the delete button
	        driver.findElement(By.xpath("/html/body/table/tbody/tr/td[4]/a")).click();
	        Thread.sleep(1500);
//	        is the task still there?
	        assertFalse(driver.findElement(By.xpath("/html/body/table")).isDisplayed());       
	    }
	    
	    
	    @AfterClass
	    public static void tearDown() {
	        driver.close();
	    }
	    

}
