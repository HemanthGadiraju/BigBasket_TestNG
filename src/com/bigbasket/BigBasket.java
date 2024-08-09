package com.bigbasket;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

@Test
public class BigBasket {
	
	static WebDriver driver;
	
	@Test(priority =1)
	public static void browser_launch() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.bigbasket.com/");
		String url = driver.getCurrentUrl();
		System.out.println(url);
	}
	@Test(priority =2)
	public static void search_product() throws AWTException {
		WebElement e1 = driver.findElement(By.xpath("(//input[@class='flex-1 w-full leading-md lg:text-sm xl:text-md placeholder-silverSurfer-700'])[2]"));
		e1.sendKeys("oranges"); 	//passing values
		Actions ac = new Actions(driver); //mouse hover to double click
		ac.doubleClick(e1).perform();
	
		Robot r = new Robot();		//virtual keyboard
		r.keyPress(KeyEvent.VK_DOWN);
	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement e2 = driver.findElement(By.xpath("(//span[@class='Label-sc-15v1nk5-0 QuickSearch___StyledLabel2-sc-rtz2vl-7 gJxZPQ gFDEBU'])[1]"));
		e2.click();		
		driver.findElement(By.xpath("//button[@class='Button-sc-1dr2sn8-0 CTA___StyledButton-sc-yj3ixq-5 kYQsWi bYACar']")).click(); // add to cart
		driver.findElement(By.xpath("(//button[@class='Button-sc-1dr2sn8-0 PdCartCTA___StyledButton2-sc-mq73zq-3 kYQsWi bwKrKT group false group false'])[1]")).click();  //increment
	}
	
	@Test(priority =3)
	public static void close() throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement e4 = driver.findElement(By.xpath("(//span[@class='Header___StyledMotionSpan-sc-19kl9m3-12 fwiste'])")); //cart clicked to verify
		e4.click();
		Thread.sleep(4000);
		driver.findElement(By.id("multiform")).sendKeys("9441830316"); // phone no.
		Thread.sleep(10000);
		WebElement e5 = driver.findElement(By.xpath("//button[text()='Continue']"));
		boolean enabled = e5.isEnabled();
		System.out.println(enabled+ "Enabled");
		e5.click();
		Thread.sleep(3000);
		driver.close();
		}
	}