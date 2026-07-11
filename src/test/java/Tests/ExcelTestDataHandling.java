package Tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Utils.TestDataProvider;

public class ExcelTestDataHandling {

	WebDriver wd;
	WebDriverWait wait;

	@BeforeMethod
	public void setup() {
		wd = new ChromeDriver();
		wait = new WebDriverWait(wd, Duration.ofSeconds(20L));

		wd.manage().window().maximize();
		wd.get("https://www.saucedemo.com/");
	}

	@Test(dataProvider = "loginTestData", dataProviderClass = TestDataProvider.class)
	public void loginIntoApplication(String username, String password) {

		WebElement userNameElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("user-name")));
		WebElement passwordNameElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
		WebElement loginButtonElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-button")));

		userNameElement.sendKeys(username);
		passwordNameElement.sendKeys(password);
		loginButtonElement.click();

	}
	
	@AfterMethod
	public void tearDown() {
		wd.quit();
	}

}
