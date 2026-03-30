import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Windows {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriver driver=new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/loginpagePractise/#");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.findElement(By.cssSelector(".blinkingText")).click();
		Set<String> totalWindows=driver.getWindowHandles();
		Iterator<String> it=totalWindows.iterator();
		String parentWindow=it.next();
		String childWindow=it.next();
		driver.switchTo().window(childWindow);
		String highlightedText=driver.findElement(By.cssSelector("div[class='col-md-8'] p:nth-child(2)")).getText();
		System.out.println(highlightedText);
		String emailId=highlightedText.split(" ")[4];
		driver.switchTo().window(parentWindow);
		driver.findElement(By.id("username")).sendKeys(emailId);
		driver.close();
	}

}
