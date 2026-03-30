import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class StreamSort {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriver driver=new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//th[1]/span[2]")).click();
		List<WebElement> items=driver.findElements(By.xpath("//tr/td[1]"));
		
			List<String> itemNames=items.stream()
			.map(x->x.getText())
			.collect(Collectors.toList());
			
			System.out.println("itemNames"+"\n"+itemNames.toString());

			List<String> Sorteditems=items.stream()
					.map(x->x.getText())
					.sorted()
					.collect(Collectors.toList());
			
		System.out.println("Sorteditems"+"\n"+itemNames.toString());
		
		Assert.assertEquals(itemNames, Sorteditems);
		List<String> priceOfItem;
		do {
		List<WebElement> rows=driver.findElements(By.xpath("//tr/td[1]"));
		 priceOfItem =rows.stream()
		.filter(x-> x.getText().contains("Rice"))
		.map(x->getPrice(x))
		.collect(Collectors.toList());
		if(priceOfItem.size()<1) {
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("[aria-label='Next']")).click();
		}
	}while(priceOfItem.size()<1);
		System.out.print(priceOfItem);

		driver.close();
	}

	private static String getPrice(WebElement x) {
		// TODO Auto-generated method stub
		String price=x.findElement(By.xpath("following-sibling::td[1]")).getText();
		return price;
	}

}
