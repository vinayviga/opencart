package opencart;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.collections.Lists;

public class Shopping {

	SoftAssert assertion = new SoftAssert();
	private RemoteWebDriver driver;

	 @Parameters("browser")
	@BeforeTest
	public void preTestConfig(String browser) throws IOException, InterruptedException {
		
		 //for a non devops setup, you can run the below method. The implementation is there in Shopping2 class
		//TestSuite.browserSetup();

		// Set the remote debugging port to the one you used when starting the browser
		int remoteDebuggingPort = 9222;
		
		
		URL url = new URL("http://localhost:4444/wd/hub");

		if(browser.equals("firefox"))
			driver = new RemoteWebDriver(url,new FirefoxOptions());
		else if(browser.equals("chrome"))
			driver = new RemoteWebDriver(url,new ChromeOptions());
		else if(browser.equals("edge"))
			driver = new RemoteWebDriver(url,new EdgeOptions());
		
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.opencart.com/");

	}

	@Test
	public void siteNavigationTestHomepage() {
		// checking the homepage
		assertion.assertTrue(driver.findElement(By.xpath("//img[contains(@title,\"OpenCart -\")]")).isDisplayed());
		assertion.assertAll();

	}

	@Test
	public void siteNavigationTestFeatures() {
		// checking the Features page
		driver.findElement(By.xpath("(//a[text()='Features'])[1]")).click();
		assertion.assertTrue(driver.findElement(By.xpath("//h1[text()='OpenCart Features']")).isDisplayed());
		assertion.assertAll();
	}

	@Test
	public void siteNavigationTestDemo() {
		// checking the Demo page
		driver.findElement(By.xpath("(//a[text()='Demo'])[1]")).click();
		assertion.assertTrue(driver.findElement(By.xpath("//h1[text()='OpenCart Demonstration']")).isDisplayed());
		assertion.assertAll();
	}

	@Test
	public void siteNavigationTestMarketplace() {
		// checking the Marketplace page
		driver.findElement(By.xpath("(//a[text()='Marketplace'])[1]")).click();
		assertion.assertTrue(
				driver.findElement(By.xpath("//h1[text()='Welcome to OpenCart Extension Store']")).isDisplayed());

	}

	@Test
	public void siteNavigationTestBlog() {
		// checking the Blog page
		driver.findElement(By.xpath("//a[text()='Blog']")).click();
		assertion.assertTrue(
				driver.findElement(By.xpath("//h1[text()='Welcome to OpenCart Ecommerce Blog']")).isDisplayed());
		assertion.assertAll();
	}

	@Test
	public void siteNavigationTestDownload() {
		// checking the Download page
		driver.findElement(By.xpath("(//a[text()='Download'])[1]")).click();
		assertion.assertTrue(driver.findElement(By.xpath("//h3[text()='Other download options']")).isDisplayed());
		assertion.assertAll();
	}

	@AfterClass
	public void postTestConfig() {
		driver.quit();
		System.out.println("test done");
	}

}
