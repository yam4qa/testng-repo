package testNGPackA;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NewProductAndServicesTest 
{
	WebDriver driver;
	@BeforeMethod
	public void startOff()
	{
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		// driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://techfios.com/test/billing/?ng=admin/");
	}
	
	@Test
	public void userShouldBeAbleToAddDeposite() throws InterruptedException
	{
		By DASHBOARD_LOCATOR = By.xpath("//h2[contains(text() , 'Dashboar')]");
		By PRODUCT_SERVICES_MEMU_LOCATOR = By.xpath("//ul[@id='side-menu']/descendant::span[text()='Products & Services']");
		By NEW_PRODUCT_LOCATOR = By.linkText("New Product");
		By PRODUCTLIST_LOCATOR = By.linkText("Products");
		By PRODCUT_HEADING_LOCATOR = By.xpath("//h2[contains(text() , 'Products & Services' )]");
		By PRODUCT_NAME_LOCATOR = By.cssSelector("input[id='name']");
		
		driver.findElement(By.xpath("//input[contains(@class,'form-') and @name='username'] ")).sendKeys("techfiosdemo@gmail.com");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@placeholder='Password']")).sendKeys("abc123");
		Thread.sleep(1000);
		System.out.println( "Present Title in login method : "+ driver.getTitle());
		driver.findElement(By.xpath("//*[contains(@class,'btn-success')]")).click();
		Thread.sleep(2000);
		
		//new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(DASHBOARD_LOCATOR));
		//OR
		String expectedTitle = "Dashboard- TechFios Test Application - Billing";
		Assert.assertEquals(driver.getTitle(), expectedTitle, "Titles are not matched , so didn't come to Dashboard page");
		Thread.sleep(1000);
		driver.findElement(PRODUCT_SERVICES_MEMU_LOCATOR).click();
		waitForElement(driver , 5 , NEW_PRODUCT_LOCATOR);
		driver.findElement(NEW_PRODUCT_LOCATOR).click();
		Thread.sleep(1000);
		
		waitForDriver(driver,10,PRODCUT_HEADING_LOCATOR);
		Random rand = new Random();
		String randName = "TFP"+rand.nextInt(999);
		System.out.println("Generated random name for product is "+randName);
		// OR simply you can write like below
		//driver.findElement(PRODUCT_NAME_LOCATOR).sendKeys("TFP"+ new Random().nextInt(999));
		driver.findElement(PRODUCT_NAME_LOCATOR).sendKeys(randName);
		driver.findElement(By.cssSelector("input[id='sales_price']")).sendKeys("$76");
		driver.findElement(By.cssSelector("textarea[id='description']")).sendKeys("New Product "+randName);
		driver.findElement(By.cssSelector("button[id='submit']")).click();
		
		// displaying the product list and searching for the created product and then deleting
		driver.findElement(PRODUCTLIST_LOCATOR).click();
		waitForDriver(driver,10,PRODCUT_HEADING_LOCATOR);
		List<WebElement> ProductElementList = driver.findElements(By.xpath("//table/descendant::td/descendant::a"));
		Assert.assertTrue(isElementTextMatch(randName, ProductElementList), "Deposit unsucessfull!");
		
	}
	
	public boolean isElementTextMatch(String expectedDescrption ,List<WebElement> elementList ) throws InterruptedException
	{
		for (int i=0 ; i< elementList.size() ; i++)
		{
			System.out.println("element " + i +" value is : " +elementList.get(i).getText());
			if(elementList.get(i).getText().equalsIgnoreCase(expectedDescrption))
			{
				System.out.println("td - " + (i+1) + " value " + elementList.get(i).getText() + " matched");
				elementList.get(i+2).click();
				new WebDriverWait(driver , 30).until(ExpectedConditions.alertIsPresent());
				driver.switchTo().alert().accept();
				return true;
			}
		}
		return false;
	}
	
	public void waitForDriver(WebDriver driver , int time , By locator)
	{
		new WebDriverWait(driver , time).until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitForElement(WebDriver driver , int time , By locator)
	{
		new WebDriverWait(driver,time).until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
//	@AfterMethod
//	public void closeAll() {
//		driver.close();
//		driver.quit();
//	}
}
