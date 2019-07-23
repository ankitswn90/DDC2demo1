package MyDemo_01.MyDemo_01;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITest;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterTest;


public class extentreportdemo1 {
	ExtentTest test;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	  WebDriver driver;
//  @Test(dataProvider="dp")
//  
//  public void login(String n,String s) throws InterruptedException {
//	  test=extent.createTest("TC-01","Title validation");
//	
//	  System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver.exe");
//	  driver=(WebDriver) new ChromeDriver();
//	  driver.get("http://10.232.237.143:443/TestMeApp");
//	  
//	  WebElement regstr=   driver.findElement(By.xpath("//a[contains(text(),'SignIn')]"));
//      
//		regstr.click();
//	  driver.findElement(By.cssSelector("input#userName")).sendKeys(n);
//		driver.findElement(By.cssSelector("input#password")).sendKeys(s);
//		
//		driver.findElement(By.cssSelector("input[type='submit']")).click();
//	  String E_title="Home";
//	  String A_title=driver.getTitle();
	  
	  
  
  
//  @DataProvider
//  public Object[][] dp() {
//    return new Object[][] {
//      new Object[] { "ank456", "ank456" },
//     
//    };
//  }
	  
  @Test
  public void readFromExcel() throws IOException {
	  
	  File scr=new File("C:\\Users\\training_d2.03.07\\Desktop\\new.xlsx");
	  FileInputStream fis=new FileInputStream(scr);
	  XSSFWorkbook wb=new XSSFWorkbook(fis);
	  XSSFSheet sheet1=wb.getSheetAt(0);
	  
	  int rowCount = sheet1.getLastRowNum();
	  System.out.println("Total no of rows is : "+rowCount);
	  
	  for(int i=0;i<=rowCount;i++)
	  {
		  String Data=sheet1.getRow(i).getCell(0).getStringCellValue();
		  System.out.println("Test Data from excel sheet is :" +Data);
		  
		  String Data1=sheet1.getRow(i).getCell(1).getStringCellValue();
		  System.out.println("Test Data from excel sheet is :" +Data1);
	  }
	  		
	  wb.close();		
	  
  }
  
  
  @AfterMethod
 
	  public void getResult(ITestResult result) throws IOException {
		  //WebDriver driver = null;
		  if(result.getStatus()==ITestResult.FAILURE)
		  {
			  test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+"FAILED",ExtentColor.RED));
			  
			  TakesScreenshot snapshot=(TakesScreenshot)driver;
			  File src= snapshot.getScreenshotAs(OutputType.FILE);
			  String Path=System.getProperty("user.dir") + "/test-output/screens/"+result.getName()+" .png";
			  FileUtils.copyFile(src, new File(Path));
			  test.addScreenCaptureFromPath(Path,result.getName());
			  test.fail(result.getThrowable());
		  }
		  
		  else if(result.getStatus()==ITestResult.SUCCESS) {
			  test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+"PASSED", ExtentColor.GREEN));
		  }
		  
		  else {
			  test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+"SKIPPED", ExtentColor.ORANGE));
			  test.skip(result.getThrowable());
		  }
		  
  }
	  
  

  @BeforeTest
  public void startReport() {
	 
	  htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/testReport.html");
	  extent =new ExtentReports();
	  extent.attachReporter(htmlReporter);
	  //extent.setSystemInfo("os",os);
	 // extent.setSystemInfo("Browser",browser);
	 // htmlReporter.config().setChartVisibilityOnOpen(true);
	  htmlReporter.config().setDocumentTitle("Extent Report Demo");
	  htmlReporter.config().setReportName("Test Report");
	//  htmlReporter.config().setTestViewChartLocation(ChartLocation)
	  htmlReporter.config().setTheme(Theme.STANDARD);
	 htmlReporter.config().setTimeStampFormat("EEEE,MMMM dd,yyyy, hh:mm a '('zzz')'");
  }

  @AfterTest
  public void afterTest() {
	  extent.flush();
	  
  }

  
  
  
  
  
}
