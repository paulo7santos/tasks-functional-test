package br.sp.psantos.Functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {
	
	/*
	 * @Test public void environmentTest() { WebDriver driver = new ChromeDriver();
	 * driver.navigate().to("http://www.google.com"); }
	 */
	
	public WebDriver applicationAccess() {
		System.setProperty("webdriver.chrome.driver","/usr/bin/chromedriver/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://192.168.0.16:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	
	
	@Test
	public void shouldSaveTaskWithSuccess() {
		
		WebDriver driver = applicationAccess();
		try {
			
			//click on Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//write the description
			driver.findElement(By.id("task")).sendKeys("Test via Selenium");
			
			//write the date
			driver.findElement(By.id("dueDate")).sendKeys("15/12/2020");
			
			//click on Save
			driver.findElement(By.id("saveButton")).click();
			
			//success message validate
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", message);
			
			//close the browser
			driver.quit();
		} finally {
			//close the browser
			driver.quit();
		}
		
	}
	
	@Test
	public void shouldNotSaveTaskWithoutDate() {
		
		WebDriver driver = applicationAccess();
		try {
			
			//click on Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//write the description
			driver.findElement(By.id("task")).sendKeys("Test via Selenium");
			
			
			//click on Save
			driver.findElement(By.id("saveButton")).click();
			
			//success message validate
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", message);
			
			//close the browser
			driver.quit();
		} finally {
			//close the browser
			driver.quit();
		}
		
	}
	
	@Test
	public void shouldNotSaveTaskWithPastDate() {
		
		WebDriver driver = applicationAccess();
		try {
			
			//click on Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//write the description
			driver.findElement(By.id("task")).sendKeys("Test via Selenium");
			
			//write the date
			driver.findElement(By.id("dueDate")).sendKeys("15/12/2010");
			
			//click on Save
			driver.findElement(By.id("saveButton")).click();
			
			//success message validate
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", message);
			
			//close the browser
			driver.quit();
		} finally {
			//close the browser
			driver.quit();
		}
		
	}
}
