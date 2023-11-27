package opencart;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.collections.Lists;

public class Shopping2 {

	SoftAssert assertion = new SoftAssert();
	private RemoteWebDriver driver;

	@BeforeTest
	public void preTestConfig() throws IOException, InterruptedException {
		
		TestSuite.browserSetup();

		// Set the remote debugging port to the one you used when starting the browser
		int remoteDebuggingPort = 9222;

		// finding the os
		String os = System.getProperty("os.name");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--window-size=1440, 900");

		// Set the path to the ChromeDriver executable

		if (os.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
			options.setBinary("./src/test/resources/chrome-win64/chrome.exe");
		}

		else {
			System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriverchromedriver");

			options.setBinary("./src/test/resources/chromedriver/chrome-linux64/chrome");
		}

		driver = new ChromeDriver(options);
		/*
		 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 * driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
		 * driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		 */
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
