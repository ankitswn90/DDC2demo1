package MyDemo_01.MyDemo_01;

import org.testng.annotations.Test;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class NewTest11 {
	WebDriver driver;

     @Test (priority=1)
     public void login() throws InterruptedException {
     System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver.exe");
     driver = new ChromeDriver();


	 driver.get("http://10.232.237.143:443/TestMeApp");
     Thread.sleep(5000);
     driver.manage().window().maximize();
     WebElement signin = driver.findElement(By.linkText("SignIn"));


	 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);


	 signin.click();
     WebElement uname = driver.findElement(By.name("userName"));
     WebElement pass = driver.findElement(By.name("password"));
     WebElement login = driver.findElement(By.name("Login"));
     uname.sendKeys("ank456");
     pass.sendKeys("ank456");
     login.click();

  }


	  @Test (priority=2)
      public void testcart() {
      WebElement search= driver.findElement(By.xpath("//*[@id=\"menu3\"]/li[2]/a/span"));
      Actions act1= new Actions(driver);
      act1.moveToElement(search).build().perform();
      WebElement electronics= driver.findElement(By.xpath("//*[@id=\"menu3\"]/li[2]/ul/li[1]/a/span"));


	 Actions act2= new Actions(driver);
     act2.moveToElement(electronics).build().perform();
     act2.doubleClick().build().perform();
     WebElement head= driver.findElement(By.xpath("//*[@id=\"submenuul11290\"]/li[1]/a/span"));
     Actions act3= new Actions(driver);
     act3.moveToElement(head).build().perform();
     act3.click(head).build().perform();
     Set <String> wind_name= driver.getWindowHandles();

     int int_window=wind_name.size();
     System.out.print(int_window);
     for(String s:wind_name) {
     driver.switchTo().window(s);
     System.out.println(driver.getWindowHandle());


 }


     WebElement cart= driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div/div[2]/center/a"));   
     
    cart.click();
    WebElement clickcart=driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div/a[2]"));
    clickcart.click();


}

      @Test (priority=3)
      public void payment() {
      WebElement checkout= driver.findElement(By.xpath("//*[@id=\"cart\"]/tfoot/tr[2]/td[5]/a"));   
      checkout.click();  
      WebElement proceedtopay= driver.findElement(By.xpath("/html/body/b/div/div/div[1]/div/div[2]/div[3]/div/form[2]/input"));   
      proceedtopay.click();  

 }

	}