import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriver driver=new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
		/*driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.cssSelector("div button")).click();
		System.out.println(driver.findElement(By.cssSelector("div[id='finish'] h4")).getText());
		System.out.println("implicit wait completed");*/
		
		/*WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(7));
		driver.findElement(By.cssSelector("div button")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='finish'] h4")));
		System.out.println(driver.findElement(By.cssSelector("div[id='finish'] h4")).getText());
		System.out.println("explicit wait completed");*/
		
		
		Wait<WebDriver> fwait=new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class);
		
		driver.findElement(By.cssSelector("div button")).click();
		
		WebElement test=fwait.until(new Function<WebDriver,WebElement>(){
			public WebElement apply(WebDriver driver) {
				if(driver.findElement(By.cssSelector("div[id='finish'] h4")).isDisplayed())
					return driver.findElement(By.cssSelector("div[id='finish'] h4"));
				else 
					return null;
			}
		});
		System.out.println("fluent wait test completed");
	}

}
