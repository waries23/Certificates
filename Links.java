import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Links {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriver driver=new ChromeDriver();
		driver.get("https://www.qaplayground.com/practice/links");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		Actions act=new Actions(driver);
		System.out.println(driver.findElements(By.xpath("//a")).size());
		WebElement box=driver.findElement(By.xpath("//div/section[6]"));
		System.out.println(box.findElements(By.tagName("a")).size());
		WebElement exeBox=driver.findElement(By.xpath("//div/section[2]"));
		System.out.println(exeBox.findElements(By.tagName("a")).size());
		for(int i=0;i<exeBox.findElements(By.tagName("a")).size();i++) {
			String clicks=Keys.chord(Keys.CONTROL,Keys.ENTER);
			exeBox.findElements(By.tagName("a")).get(i).sendKeys(clicks);
			//act.keyDown(Keys.CONTROL).moveToElement(exeBox.findElements(By.tagName("a")).get(i)).click().build().perform();
			Thread.sleep(2000);
		}
		Set<String> windows=driver.getWindowHandles();
		Iterator<String> it=windows.iterator();
		int size=windows.size();
		String[] numberOfHandles=new String[size];
		int j=0;
		while(it.hasNext()) {
			driver.switchTo().window(it.next());
			Thread.sleep(2000);
			System.out.println(driver.getTitle());
			numberOfHandles[j]=driver.getTitle();
			j++;
			
		}
		System.out.println(Arrays.toString(numberOfHandles));
		driver.quit();
	}

}
