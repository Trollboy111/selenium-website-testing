package ScanTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;
import java.time.Duration;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import Drivers.Drivers;

class ScanTests {
	private static WebDriver driver;
	private static WebDriverWait wait;

	@BeforeAll
	public static void setUp() {
		driver = Drivers.startChrome();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		//The following is done to remove the news letter once and not do it again because it slows down the tests
		driver.get("https://www.scanmalta.com/shop/");

		List<WebElement> newsletterModal = driver.findElements(By.cssSelector(".cdz-newsletter-modal"));
		if (!newsletterModal.isEmpty()) {
			Drivers.waitToClick(driver, driver.findElement(By.cssSelector(".cdz-newsletter-modal .action-close")));
		}
	}

	@AfterAll
	public static void tearDown() {
		driver.quit();
	}
	
	@BeforeEach
	public void openHomePage() {
		driver.get("https://www.scanmalta.com/shop/");
	}

	@Test
	public void mostExpensiveMouse() {
		WebElement searchBox = driver.findElement(By.id("search"));
		searchBox.sendKeys("mouse");
		Drivers.waitToClick(driver, driver.findElement(By.cssSelector(".action > .mdl-button__ripple-container")));

		WebElement sorter = wait.until(elementToBeClickable(By.id("sorter")));
		sorter.sendKeys("price");

		try {
			wait.until(invisibilityOfElementLocated(By.cssSelector("div.loader")));
		} catch (TimeoutException e) {
		}

		WebElement directionOption = driver.findElement(By.cssSelector(".toolbar-sorter > .action"));
		String directionClass = directionOption.getAttribute("class");
		if (directionClass.contains("sort-asc")) {
			directionOption.click();
			try {
				wait.until(invisibilityOfElementLocated(By.cssSelector("div.loader")));
			} catch (TimeoutException e) {
			}
		}

		WebElement firstPriceElement = driver.findElement(By.className("price"));
		String priceText = firstPriceElement.getText().replaceAll("[^0-9.]", "");
		double price = Double.parseDouble(priceText);
		assertTrue(price > 100, "Expected price greater than 100 as sorted by price descending");
	}

	@Test
	public void leastExpensiveMouse() {
		WebElement searchBox = driver.findElement(By.id("search"));
		searchBox.sendKeys("mouse");
		Drivers.waitToClick(driver, driver.findElement(By.cssSelector(".action > .mdl-button__ripple-container")));

		WebElement sorter = wait.until(elementToBeClickable(By.id("sorter")));
		sorter.sendKeys("price");

		try {
			wait.until(invisibilityOfElementLocated(By.cssSelector("div.loader")));
		} catch (TimeoutException e) {
		}

		WebElement directionOption = driver.findElement(By.cssSelector(".toolbar-sorter > .action"));
		String directionClass = directionOption.getAttribute("class");
		if (directionClass.contains("sort-desc")) {
			directionOption.click();
			try {
				wait.until(invisibilityOfElementLocated(By.cssSelector("div.loader")));
			} catch (TimeoutException e) {
			}
		}

		WebElement firstPriceElement = driver.findElement(By.className("price"));
		String priceText = firstPriceElement.getText().replaceAll("[^0-9.]", "");
		double price = Double.parseDouble(priceText);
		assertTrue(price < 10, "Expected price smaller than 10 as sorted by price ascending");
	}

	@Test
	public void firstAlphabeticalKeyboard() {
		WebElement searchBox = driver.findElement(By.id("search"));
		searchBox.sendKeys("keyboard");
		Drivers.waitToClick(driver, driver.findElement(By.cssSelector(".action > .mdl-button__ripple-container")));

		WebElement sorter = wait.until(elementToBeClickable(By.id("sorter")));
		sorter.sendKeys("name");

		try {
			wait.until(invisibilityOfElementLocated(By.cssSelector("div.loader")));
		} catch (TimeoutException e) {
		}

		WebElement directionOption = driver.findElement(By.cssSelector(".toolbar-sorter > .action"));
		String directionClass = directionOption.getAttribute("class");
		if (directionClass.contains("sort-desc")) {
			directionOption.click();
			try {
				wait.until(invisibilityOfElementLocated(By.cssSelector("div.loader")));
			} catch (TimeoutException e) {
			}
		}

		WebElement firstProductLinkElement = driver.findElement(By.cssSelector(".search:nth-child(1) .item:nth-child(1) .product-item-link"));
		String firstProductTitle = firstProductLinkElement.getText().toLowerCase();

		WebElement secondProductLinkElement = driver
				.findElement(By.cssSelector(".search:nth-child(1) .item:nth-child(2) .product-item-link"));
		String secondProductTitle = secondProductLinkElement.getText().toLowerCase();
		
		assertTrue(firstProductTitle.compareTo(secondProductTitle) < 0,
				"Expected less than 0 as the second title comes before the first title when sorting by name descending");
	}

	@Test
	public void lastAlphabeticalKeyboard() {
		WebElement searchBox = driver.findElement(By.id("search"));
		searchBox.sendKeys("keyboard");
		Drivers.waitToClick(driver, driver.findElement(By.cssSelector(".action > .mdl-button__ripple-container")));

		WebElement sorter = wait.until(elementToBeClickable(By.id("sorter")));
		sorter.sendKeys("name");

		try {
			wait.until(invisibilityOfElementLocated(By.cssSelector("div.loader")));
		} catch (TimeoutException e) {
		}

		WebElement directionOption = driver.findElement(By.cssSelector(".toolbar-sorter > .action"));
		String directionClass = directionOption.getAttribute("class");
		if (directionClass.contains("sort-asc")) {
			directionOption.click();
			try {
				wait.until(invisibilityOfElementLocated(By.cssSelector("div.loader")));
			} catch (TimeoutException e) {
			}
		}

		WebElement firstProductLinkElement = driver
				.findElement(By.cssSelector(".search:nth-child(1) .item:nth-child(1) .product-item-link"));
		String firstProductTitle = firstProductLinkElement.getText().toLowerCase();

		WebElement secondProductLinkElement = driver
				.findElement(By.cssSelector(".search:nth-child(1) .item:nth-child(2) .product-item-link"));
		String secondProductTitle = secondProductLinkElement.getText().toLowerCase();
		assertTrue(firstProductTitle.compareTo(secondProductTitle) > 0,
				"Expected greater than 0 as the second title does not come before the first title when sorting by name descending");
	}

	@Test
	public void gamingHeadphones() {
		WebElement searchBox = driver.findElement(By.id("search"));
		searchBox.sendKeys("headphones");
		Drivers.waitToClick(driver, driver.findElement(By.cssSelector(".action > .mdl-button__ripple-container")));

		Drivers.waitToClick(driver, driver.findElement(By.cssSelector(".cat .item:nth-child(3) > a")));

		try {
			wait.until(invisibilityOfElementLocated(By.cssSelector("div.loader")));
		} catch (TimeoutException e) {
		}

		WebElement firstProductLinkElement = driver
				.findElement(By.cssSelector(".search:nth-child(1) .item:nth-child(1) .product-item-link"));
		String firstProductTitle = firstProductLinkElement.getText().toLowerCase();

		assertTrue(firstProductTitle.contains("gaming"), "Expected contains the word gaming");
	}

	@Test
	public void wirelessHeadphones() {
		WebElement searchBox = driver.findElement(By.id("search"));
		searchBox.sendKeys("headphones");
		Drivers.waitToClick(driver, driver.findElement(By.cssSelector(".action > .mdl-button__ripple-container")));

		Drivers.waitToClick(driver, driver.findElement(By.cssSelector(".headphones_wireless .item:nth-child(2) > a")));

		try {
			wait.until(invisibilityOfElementLocated(By.cssSelector("div.loader")));
		} catch (TimeoutException e) {
		}

		WebElement firstProductLinkElement = driver
				.findElement(By.cssSelector(".search:nth-child(1) .item:nth-child(1) .product-item-link"));
		String firstProductTitle = firstProductLinkElement.getText().toLowerCase();

		assertTrue(firstProductTitle.contains("bluetooth"), "Expected contains the word bluetooth");
	}
	
	@Test
	public void discountedWebcam() {
		WebElement searchBox = driver.findElement(By.id("search"));
		searchBox.sendKeys("webcam");
		Drivers.waitToClick(driver, driver.findElement(By.cssSelector(".action > .mdl-button__ripple-container")));

		Drivers.waitToClick(driver, driver.findElement(By.cssSelector(".cat .item:nth-child(1) > a")));

		try {
			wait.until(invisibilityOfElementLocated(By.cssSelector("div.loader")));
		} catch (TimeoutException e) {
		}

		WebElement priceBox = driver.findElement(By.cssSelector(".price-box.price-final_price[data-role='priceBox']"));

		WebElement specialPriceElement = priceBox.findElement(By.cssSelector(".special-price .price"));
		String discountedText = specialPriceElement.getText().replaceAll("[^0-9.]", "");
		double discountedPrice = Double.parseDouble(discountedText);

		WebElement originalPriceElement = priceBox.findElement(By.cssSelector(".old-price .price"));
		String originalText = originalPriceElement.getText().replaceAll("[^0-9.]", "");
		double originalPrice = Double.parseDouble(originalText);

		assertTrue(discountedPrice < originalPrice, "Expected original price to be greater than discounted price");
	}

	@Test
	public void usbWebcam() {
	    WebElement searchBox = driver.findElement(By.id("search"));
	    searchBox.sendKeys("webcam");
	    Drivers.waitToClick(driver, driver.findElement(By.cssSelector(".action > .mdl-button__ripple-container")));
	    
	    Drivers.waitToClick(driver, driver.findElement(By.id("filter-power_source-1")));

	    Drivers.waitToClick(driver,
	            driver.findElement(By.cssSelector(".search:nth-child(1) .item:nth-child(1) .product-item-link")));

	    Drivers.scrollIntoView(driver, driver.findElement(By.cssSelector("tr:nth-child(6) > .data")));

	    String connector = driver.findElement(By.cssSelector("tr:nth-child(6) > .data")).getText().toLowerCase();
	    assertTrue(connector.contains("usb"), "Expected contains the word usb");
	}

	@Test
	public void MotherboardValidRange() {
		WebElement searchBox = driver.findElement(By.id("search"));
		searchBox.sendKeys("motherboard");
		Drivers.waitToClick(driver, driver.findElement(By.cssSelector(".action > .mdl-button__ripple-container")));
		
		try {
			wait.until(invisibilityOfElementLocated(By.cssSelector("div.loader")));
		} catch (TimeoutException e) {
		}
		
		WebElement minPriceBox = driver.findElement(By.name("min_price"));
		minPriceBox.clear();
		minPriceBox.sendKeys("200");
		
		WebElement maxPriceBox = driver.findElement(By.name("max_price"));
		maxPriceBox.clear();
		maxPriceBox.sendKeys("300");
		
		driver.findElement(By.cssSelector(".btn")).click();

		try {
			wait.until(invisibilityOfElementLocated(By.cssSelector("div.loader")));
		} catch (TimeoutException e) {
		}

		String priceText = driver.findElement(By.className("price")).getText().replaceAll("[^0-9.]", "");
		double price = Double.parseDouble(priceText);
		assertTrue(price>199 && price < 301, "Expected price between 200 and 300");
	}

	@Test
	public void MotherboardInvalidRange() {
		WebElement searchBox = driver.findElement(By.id("search"));
		searchBox.sendKeys("motherboard");
		Drivers.waitToClick(driver, driver.findElement(By.cssSelector(".action > .mdl-button__ripple-container")));
		
		try {
			wait.until(invisibilityOfElementLocated(By.cssSelector("div.loader")));
		} catch (TimeoutException e) {
		}
		
		WebElement minPriceBox = driver.findElement(By.name("min_price"));
		minPriceBox.clear();
		minPriceBox.sendKeys("0");
		
		WebElement maxPriceBox = driver.findElement(By.name("max_price"));
		maxPriceBox.clear();
		maxPriceBox.sendKeys("1");
		
		driver.findElement(By.cssSelector(".btn")).click();

		try {
			wait.until(invisibilityOfElementLocated(By.cssSelector("div.loader")));
		} catch (TimeoutException e) {
		}

		String resultsMessage = driver.findElement(By.cssSelector(".message > div")).getText();
		assertTrue("Your search returned no results.".equals(resultsMessage), "Expected no results message");
	}

}
