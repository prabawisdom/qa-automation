import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.zillion.qa.ZadoReports;
import com.zillion.qa.commands.Navigate;
import com.zillion.qa.config.AndroidSetup;
import com.zillion.qa.config.ChromeBrowser;
import com.zillion.qa.config.FirefoxBrowser;
import com.zillion.qa.config.IEBrowser;
import com.zillion.qa.config.IOSSetup;
import com.zillion.qa.config.SafariBrowser;
import com.zillion.qa.datadriver.CaseStep;
import com.zillion.qa.datadriver.TestCaseRunner;
import com.zillion.qa.enums.LogAs;
import com.zillion.qa.listeners.ConfigurationListener;
import com.zillion.qa.listeners.MethodListener;
import com.zillion.qa.listeners.ZadoReportsListener;
import com.zillion.qa.reports.CaptureScreen;
import com.zillion.qa.reports.CaptureScreen.ScreenshotOf;
import com.zillion.qa.util.ExcelUtils;
import com.zillion.qa.utils.Directory;
import com.zillion.qa.utils.TestParameters;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

@Listeners({ ConfigurationListener.class, ZadoReportsListener.class,
	MethodListener.class })
public class TestNGClass {
   private static final AtomicInteger testCaseCount = new AtomicInteger(1);
   public static String tcModuleName;
	Logger log = Logger.getLogger(TestNGClass.class.getName());
	@BeforeTest 
	@DataProvider(name = "data", parallel = true)
	public static Iterator<Object[]> testCaseProvider() {
		List<Object[]> data = new ArrayList<Object[]>();
		ExcelUtils utils = new ExcelUtils();
		Collection<File> testCaseList = utils.readTestCaseFiles(Directory.testCasePath);
		Iterator<File> testCaseItr = testCaseList.iterator();

		while (testCaseItr.hasNext()) {
			String[] browser = Directory.browser.split(",");
			File tcFileName = testCaseItr.next();
			for (int i = 0; i < browser.length; i++) {
				TestParameters params = new TestParameters();
				params.setBrowserName(browser[i]);
				params.setTestCaseFileName(tcFileName);
				params.setTestCaseName(FilenameUtils.getBaseName(tcFileName
						.getName()) + "_" + browser[i]);
				String parentmodule=tcFileName.getParent();
			    String[] parentpath=parentmodule.split("testcases");
			    params.setModuleName(parentpath[1]);
			    tcModuleName=parentpath[1];
				params.setOrSheetFileName(new File(Directory.ORSheetPath));
				data.add(new Object[] { params });
			}
		}
		return data.iterator();
	}

	@Test(dataProvider = "data", enabled = true)
	public void launchapp(TestParameters params) throws Exception {
		WebDriver driver = null;
		AndroidDriver<?> adriver = null;
		IOSDriver<?> idriver = null;
		ExcelUtils utils = new ExcelUtils();
		
		try {
			if (params.getBrowserName().equals("chrome")) {
				driver = new ChromeBrowser().getDriver();
			} else if (params.getBrowserName().equals("firefox")) {
				driver =  new FirefoxBrowser().getDriver();
			} else if (params.getBrowserName().equals("ie")) {
				driver = new IEBrowser().getDriver();
			} else if (params.getBrowserName().equals("safari")) {
				driver = new SafariBrowser().getDriver();
			}else if(params.getBrowserName().equals("android")) {
				adriver = AndroidSetup.getDriver();
			} else if(params.getBrowserName().equalsIgnoreCase("IOS")) {
				idriver = IOSSetup.getDriver();
			}

			final int iTest = TestNGClass.testCaseCount.getAndIncrement();
			System.out.println("Executing Testcase "+iTest+" :"+params.getTestCaseFileName());

			if (Directory.browser.equalsIgnoreCase("android")){
				log.info("before set driver Thread -----"+Thread.currentThread().getId() +"------------driver------------"+adriver);
				ZadoReports.setWebDriver(adriver);
				log.info("after set driver Thread -----"+Thread.currentThread().getId() +"------------driver------------"+adriver);
			} else if (params.getBrowserName().equalsIgnoreCase("IOS")) {
				log.info("before set driver Thread -----"+Thread.currentThread().getId() +"------------driver------------"+idriver);
				ZadoReports.setWebDriver(idriver);
				log.info("after set driver Thread -----"+Thread.currentThread().getId() +"------------driver------------"+idriver);
			} else {
				log.info("before set driver Thread -----"+Thread.currentThread().getId() +"------------driver------------"+driver);
				ZadoReports.setWebDriver(driver);
				log.info("after set driver Thread -----"+Thread.currentThread().getId() +"------------driver------------"+driver);
			}

			try {
				List<CaseStep> steps = utils.readTestCase(
						params.getTestCaseFileName(),
						params.getOrSheetFileName());
				TestCaseRunner.exectuteTestCase(adriver,idriver,driver, steps);
				} catch (NoSuchElementException e) {
					if(Directory.browser.equalsIgnoreCase("android")){
						ZadoReports.add("Failed to find Element", LogAs.FAILED,
								new CaptureScreen(ScreenshotOf.BROWSER_PAGE));


						log.info("Thread @ first close-----"+Thread.currentThread().getId() +"------------driver------------"+adriver);		
						//				driver.quit();
						throw e; 
					}
					else if(Directory.browser.equalsIgnoreCase("IOS")){
						ZadoReports.add("Failed to find Element", LogAs.FAILED,
								new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

						log.info("Thread @ first close-----"+Thread.currentThread().getId() +"------------driver------------"+idriver);		
						//				driver.quit();
						throw e; 
					}
					else {
						ZadoReports.add("Failed to find Element", LogAs.FAILED,
								new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

						log.info("Thread @ first close-----"+Thread.currentThread().getId() +"------------driver------------"+driver);		

						throw e;
				}
				}
			
			catch (TimeoutException e) {
				if(Directory.browser.equalsIgnoreCase("android")){
					ZadoReports.add("Timeout Exception", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.BROWSER_PAGE));


					log.info("Thread @ first close-----"+Thread.currentThread().getId() +"------------driver------------"+adriver);		
					//				driver.quit();
					throw e; 
				}
				else if(Directory.browser.equalsIgnoreCase("IOS")){
					ZadoReports.add("Timeout Exception", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

					log.info("Thread @ first close-----"+Thread.currentThread().getId() +"------------driver------------"+idriver);		
					//				driver.quit();
					throw e; 
				}
				else {
					ZadoReports.add("Timeout Exception", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

					log.info("Thread @ first close-----"+Thread.currentThread().getId() +"------------driver------------"+driver);		

					throw e;
			}
			}
			catch (NullPointerException e){
				if(Directory.browser.equalsIgnoreCase("android")){
					ZadoReports.add("NullPointerException", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					
					log.info("Thread @ first close-----"+Thread.currentThread().getId() +"------------driver------------"+adriver);

					throw e;
				}
				else if(Directory.browser.equalsIgnoreCase("IOS")){
					ZadoReports.add("NullPointerException", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					log.info("Thread @ first close-----"+Thread.currentThread().getId() +"------------driver------------"+idriver);
					throw e;
				}
				else {
					ZadoReports.add("NullPointerException", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

					log.info("Thread @ first close-----"+Thread.currentThread().getId() +"------------driver------------"+driver);

					throw e; }
			}	
			catch (Exception e){
				if(Directory.browser.equalsIgnoreCase("android")){
					ZadoReports.add("Exception", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

					log.info("Thread @ first close-----"+Thread.currentThread().getId() +"------------driver------------"+adriver);
					throw e;
				}
				else if(Directory.browser.equalsIgnoreCase("IOS")){
					ZadoReports.add("Exception", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

					log.info("Thread @ first close-----"+Thread.currentThread().getId() +"------------driver------------"+idriver);
					throw e;
				}
				else {
					ZadoReports.add("Exception", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

					log.info("Thread @ first close-----"+Thread.currentThread().getId() +"------------driver------------"+driver);
					throw e;
				}
			}	
			finally
			{

				if(Directory.browser.equalsIgnoreCase("android")){
					try
					{
						adriver.quit();
					}
					catch(Exception e)
					{
						
					}
					
					log.info("Thread @ close-----"+Thread.currentThread().getId() +"------------driver------------"+adriver);}
				else if(Directory.browser.equalsIgnoreCase("IOS")){
					idriver.quit();
					log.info("Thread @ close-----"+Thread.currentThread().getId() +"------------driver------------"+idriver);}
				else {
					Navigate.quit(driver);
					log.info("Thread @ close-----"+Thread.currentThread().getId() +"------------driver------------"+driver);
				}
			}
		} catch (InvalidFormatException e)
		{
			e.printStackTrace();
		} catch (IOException e) 
		{			
			e.printStackTrace();
		}
	}
}