package Drivers;

import java.io.File;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Drivers {
	private static final String baseDir = "drivers"+File.separatorChar;
	public static final String CHROME  = baseDir+"chromedriver.exe";
	
	public static WebDriver startChrome() {
		System.setProperty("webdriver.chrome.driver", Drivers.CHROME);
		return new ChromeDriver();
	}

	public static void waitToClick(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement elementFound = wait.until(ExpectedConditions.elementToBeClickable(element));
		elementFound.click();
	}
	
	public static void waitToClick(WebDriver driver, By by) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
		element.click();
	}
	
	public static void scrollIntoView(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", element); 
	}
}
