package testNGPackA;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NewBankAndCashTest 
{
	WebDriver driver;
	
	@BeforeMethod
	public void startOff()
	{
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		//driver.manage().window().maximize();
		//driver.manage().deleteAllCookies();
		driver.get("http://techfios.com/test/billing/?ng=admin/");
		driver.findElement(By.id("username")).sendKeys("techfiosdemo@gmail.com");
		driver.findElement(By.name("password")).sendKeys("abc123");
		//driver.findElement(By.xpath("//button[@name='login' and @type='submit']")).click();
		driver.findElement(By.cssSelector("button[type='submit']")).click();
	}

	@Test
	public void addNewBankAcTest() throws InterruptedException
	{
		By BANK_CASH_LOCATOR = By.xpath("//ul[@id='side-menu']/descendant::span[text()='Bank & Cash']");
		//also can use long waylocator //span[text()='Dashboard']/ancestor::li/following-sibling::li/descendant::span[text()='Bank & Cash']
		By NEW_ACCOUNT_LOCATOR = By.linkText("New Account");
		By ACCOUNT_TITLE_LOCATOR = By.id("account");
		System.out.println(" 1) Title is : " + driver.getTitle());
		//new WebDriverWait(driver , 10).until(ExpectedConditions.titleContains("Dashboard")); //OR
		waitForDriver(driver,10,BANK_CASH_LOCATOR);
		
		driver.findElement(BANK_CASH_LOCATOR).click();
		Thread.sleep(1000);
		driver.findElement(NEW_ACCOUNT_LOCATOR).click();
		waitForDriver(driver,10,ACCOUNT_TITLE_LOCATOR);
		Random rand = new Random();
		int randNum = rand.nextInt(999);
		driver.findElement(ACCOUNT_TITLE_LOCATOR).sendKeys("Acct"+randNum);
		System.out.println("Generated random id is Acct"+randNum);
		// OR simply you can write like below
		//driver.findElement(ACCOUNT_TITLE_LOCATOR).sendKeys("Acct"+ new Random().nextInt(999));
		driver.findElement(By.cssSelector("input[id='description']")).sendKeys("Account created");
		driver.findElement(By.cssSelector("input[id='balance']")).sendKeys("1000");
		driver.findElement(By.xpath("//button[@type='submit'][@class='btn btn-primary']")).click();
		
	}
	
	
	
	
	public void waitForDriver(WebDriver driver , int time , By locator)
	{
		new WebDriverWait(driver , time).until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
//	@AfterMethod
//	public void closeEverything() throws InterruptedException
//	{
//		Thread.sleep(3000);
//		driver.close();
//		driver.quit();
//	}
}
