package testNGPackA;

import java.util.concurrent.TimeUnit;
//import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SelectDropDownTest 
{
	WebDriver driver;
	
	@BeforeMethod
	public void startSetUp()
	{
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		//driver.manage().window().maximize();
		//driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com");
      }
	
	@Test
	public void HandlingDropDown() throws InterruptedException 
	{
		driver.findElement(By.name("firstname")).sendKeys("$34^dsd*");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Nagalla");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("krishnagalla@yahoo.com");
		//Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).sendKeys("krishnagalla@yahoo.com");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[contains(@id,'password_step_input')]")).sendKeys("gallakr1shn@");
		Thread.sleep(1000);
		
// Instead of using selectors we can use 'select' class for dropdown But tagname should be'select' in DOM
		
		//select the third dropdown using "select by index"
		//Select index = new Select(driver.findElement(By.id("month")));//OR
		Select index = new Select(driver.findElement(By.cssSelector("select#month")));
		index.selectByIndex(9);
		Thread.sleep(1000);
		
		//select the second dropdown using "select by visible text" // best option
		//Select selectByVisibleTxt = new Select(driver.findElement(By.id("day"))); //OR
		Select selectByVisibleTxt = new Select(driver.findElement(By.cssSelector("select#day")));
		selectByVisibleTxt.selectByVisibleText("12");
		Thread.sleep(1000);
		
		// select the first operator using "select by value"
		//Select selectByValu = new Select(driver.findElement(By.id("year")));//OR
		Select selectByValu = new Select(driver.findElement(By.cssSelector("select#year")));
		selectByValu.selectByValue("1976");
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//label[text()='Female']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@name='websubmit']")).click();
		Thread.sleep(1000);
		
		
		String actualTitle = driver.getTitle();
		System.out.println(" actual title is : " +actualTitle);
		String expectedTitle = "something else";
		
		WebElement ERROR_MESSAGE = driver.findElement(By.id("reg_error_inner"));
		By ERROR_MESSAGE_LOCATOR = By.id("reg_error_inner");
		//Using explicit wait
		
		try
		{
			new WebDriverWait(driver , 10).until(ExpectedConditions.visibilityOf(ERROR_MESSAGE));
			//OR
			//new WebDriverWait(driver , 10).until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE_LOCATOR));
			System.out.println(" using Explicit Wait of 10 secs of error message");
		}catch (TimeoutException e) 
		{
			System.out.println(" tried to see error message for special chars firstname using Explicit Wait of 10 secs ");
		}
		//OR
//		try{
//			WebDriverWait wait = new WebDriverWait(driver , 10);//we created wait
//			wait.until(ExpectedConditions.titleIs(expectedTitle));
//		}catch (TimeoutException e) {
//		System.out.println(" tried waiting for title change that means next page , using Explicit Wait of 10 seconds ");
//		}
//		
		// Using assertion
		//Assert.assertEquals(" ** Next page not loaded  ** " , expectedTitle , actualTitle);
	}
//	@AfterMethod
//	public void closeAll() {
//		driver.close();
//		driver.quit();
//	}
}
