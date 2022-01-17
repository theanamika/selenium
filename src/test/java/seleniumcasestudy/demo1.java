package seleniumcasestudy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import io.github.bonigarcia.wdm.WebDriverManager;

public class demo1 {
   public WebDriver driver;
   
  @ BeforeTest
   public void browser()
   {
   WebDriverManager.chromedriver().setup();
   
   driver =new ChromeDriver();
   driver.navigate().to("https://www.edureka.co/");
   }
  @Test (priority=0)
  public void apple() throws IOException, InterruptedException
  {
	  System.out.println("zero method");
	  //driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	  Thread.sleep(4000);
	
	
	  File lodget = new File("C:\\Users\\Moolya\\Desktop\\book1.xlsx");
	  FileInputStream fis = new FileInputStream(lodget);
	  
	  XSSFWorkbook book1 =new XSSFWorkbook(fis);
	  XSSFSheet sh = book1.getSheetAt(0);
	  
	  String Email = sh.getRow(0).getCell(0).getStringCellValue();
	  String password = sh.getRow(0).getCell(1).getStringCellValue();
	  Thread.sleep(4000);
	  
	  driver.findElement(By.xpath("//*[@data-button-name='Login']")).click();
	  //driver.findElement(By.linkText("Log In")).click();
	  Thread.sleep(3000);
	   driver.findElement(By.xpath("//*[@id='si_popup_email']")).sendKeys(Email);
	   driver.findElement(By.xpath("//*[@id='si_popup_passwd']")).sendKeys(password);
	   
	   Thread.sleep(3000);
	   driver.findElement(By.xpath("//*[@class='clik_btn_log btn-block']")).click();
  }
  @Test(priority=1,dependsOnMethods = {"apple"})
  public void bat() throws InterruptedException
  
  {
	 System.out.println("first method");
	  Thread.sleep(2000);
	  driver.manage().window().maximize();
	   driver.findElement(By.xpath("//*[@data-button-name='Courses']")).click();
	
	   JavascriptExecutor js = (JavascriptExecutor)driver;
	  
	   WebElement ele1 = driver.findElement(By.xpath("//*[@title='DevOps Engineer Masters Program']"));
	   Thread.sleep(3000);
	   js.executeScript("arguments[0].scrollIntoView(true);" ,ele1);
	   
	   
	
	   
	   new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title='Selenium Certification Training Course']"))).click();
		
     
       js.executeScript("window.scrollBy(0,200)");
       new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'#certification')]"))).click();
       Thread.sleep(4000);
       driver.findElement(By.xpath("//*[@class='watch_course_text get_a_glimpse_course_preview']")).click();
       Thread.sleep(6000);
       driver.navigate().back();
       
	 // js.executeScript("window.scrollBy(0,-900)");
	 // Thread.sleep(4000);

    
  }
      
    @AfterTest
    public void logout() throws InterruptedException
    {
    	   JavascriptExecutor js = (JavascriptExecutor)driver;
    	  js.executeScript("window.scrollBy(0,-900)");
    	  Thread.sleep(4000);

    	driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
    	Actions act =new Actions(driver);
    	
    	WebElement ele2 =driver.findElement(By.xpath("//*[@class='img30']"));
    	ele2.click();
    	Thread.sleep(4000);
    	 driver.findElement(By.xpath("//*[@data-button-name='Logout']")).sendKeys(Keys.ENTER);
    	 driver.close();
    }
	  
}
