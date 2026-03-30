import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.openqa.selenium.support.locators.RelativeLocator.*;

public class RelativeLocators {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		WebDriver driver=new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/angularpractice/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		
		WebElement nameBox=driver.findElement(By.id("exampleInputPassword1"));
		String text=driver.findElement(with(By.tagName("label")).above(nameBox)).getText();
		System.out.println("1."+text);
		
		WebElement dob=driver.findElement(By.cssSelector("[for='dateofBirth']"));
		driver.findElement(with(By.tagName("input")).below(dob)).click();
		
		WebElement leftof=driver.findElement(By.className("form-check-label"));
		driver.findElement(with(By.tagName("input")).toLeftOf(leftof)).click();
		
		WebElement rightof=driver.findElement(By.id("inlineRadio1"));
		System.out.println("2."+driver.findElement(with(By.tagName("label")).toRightOf(rightof)).getText());
		
		driver.switchTo().newWindow(WindowType.WINDOW);
		Set<String> windows=driver.getWindowHandles();
		Iterator<String> it=windows.iterator();
		String parentID=it.next();
		String childId=it.next();
		driver.switchTo().window(childId);
		driver.get("https://courses.rahulshettyacademy.com/l/products?sortKey=recommended&sortDirection=asc&page=1");
		JavascriptExecutor js=(JavascriptExecutor)driver;
		List<WebElement> courses=driver.findElements(By.xpath("//h2[contains(@class,'tracking-tight')]"));
		List<WebElement> neededCourse=courses.stream()
				//.map(x->x.getText())
				.filter(x->x.getText().contains("Selenium Webdriver with Java"))
				.collect(Collectors.toList());
		System.out.println(neededCourse.size());
		System.out.println(neededCourse.get(0).getText());
		String cname=neededCourse.get(0).getText();
		js.executeScript("arguments[0].scrollIntoView({block:'center'});",neededCourse.get(0));
		File courseSS=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(courseSS, new File("C://Users//warie//TestScreenshots//courseSS.png"));
		driver.switchTo().window(parentID);
		WebElement namefield=driver.findElement(By.xpath("//div[@class='form-group']//input[@name='name']"));
		namefield.sendKeys(cname);
		File nameSS=namefield.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(nameSS, new File("C://Users//warie//TestScreenshots//namefield.png"));
		//driver.close();
	}

}
