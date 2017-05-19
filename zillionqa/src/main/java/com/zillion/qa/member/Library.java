package com.zillion.qa.member;
import java.awt.AWTException;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;
import org.testng.Assert;
import com.zillion.qa.commands.Manipulation;
import com.zillion.qa.commands.Navigate;
import com.zillion.qa.member.OR;
import com.zillion.qa.utils.Platform;
public class Library extends Manipulation implements OR
/**
 * Name         :   Suresh.V
 * Created Date :   16/Feb/16
 * Modified Date:   
 * Required Inputs: Username and password.
 */
{
	public static void memberContentLibrarySearchAndVerify(WebDriver driver) throws InterruptedException, FindFailed
	{
		WebElement lifeStyleSubMenu=driver.findElement(By.xpath(OR.MEMBER_LIFESTYLE));
		click(lifeStyleSubMenu);
		wait(driver, "10");
		String entriesCountInLifeStylePage = driver.findElement(By.xpath("//div[@id='librarySinglePageTemplate']//div[@class='input-group pull-right input-group-with-btn']//input[@type='text' and @placeholder='Search for...']")).getText();
		System.out.println(entriesCountInLifeStylePage);
		jsClickByXPath(driver, OR.MEMBER_NUTRITION);
		wait(driver, "10");
		String entriesCountnutritionSubMenuPage = driver.findElement(By.xpath("//div[@id='librarySinglePageTemplate']//div[@id='libraryToolbarRow']//div/span")).getText();
		System.out.println(entriesCountnutritionSubMenuPage);
		jsClickByXPath(driver, OR.GETTING_FIT_SUB_MENU);
		wait(driver, "10");
		String entriesCountGettingFitPagePage = driver.findElement(By.xpath("//div[@id='librarySinglePageTemplate']//div[@id='libraryToolbarRow']//div/span")).getText();
		System.out.println(entriesCountGettingFitPagePage);
		jsClickByXPath(driver, OR.MEMBER_RECIPES);
		wait(driver, "10");
		String entriesCountInRecipesPage = driver.findElement(By.xpath("//div[@id='librarySinglePageTemplate']//div[@id='libraryToolbarRow']//div/span")).getText();
		System.out.println(entriesCountInRecipesPage);
		Navigate.waitTime(driver, "10");
		waitForElement(driver, OR.CONTENT_LIBRARY_PAGE_SEARCH_TEXTBOX1);
		jsClickByXPath(driver,OR.CONTENT_LIBRARY_PAGE_SEARCH_TEXTBOX1 );
		WebElement contentLibrarySearchTextbox=driver.findElement(By.xpath(OR.CONTENT_LIBRARY_PAGE_SEARCH_TEXTBOX1));
		clearAndType(contentLibrarySearchTextbox,"Lifestyle");
		WebElement contentLibrarySearchButton=driver.findElement(By.xpath(OR.CONTENT_LIBRARY_PAGE_SEARCH_BUTTON));
		click(contentLibrarySearchButton);
		String entriesCount2InLifeStylePage = driver.findElement(By.xpath("//div[@id='librarySinglePageTemplate']//div[@id='libraryToolbarRow']//div/span")).getText();
		System.out.println(entriesCount2InLifeStylePage);
	}
	/**
	 * Name: SURESH V
	 * Created Date: 29/Feb/2016
	 */
	public static void pdfFileHandling(WebDriver driver)
	{
		WebElement gettingFitSubMenu=driver.findElement(By.xpath(OR.GETTINGFIT_SUBMENU));
		WebElement gettingFitViewArticleButton=driver.findElement(By.xpath(OR.GETTINGFIT_VIEW_ARTICLE_BUTTON));
		click(gettingFitSubMenu);
		wait(driver, "5");
		click(gettingFitViewArticleButton);
		if (Platform.BROWSER_NAME.equalsIgnoreCase("UnKnown"))
		{
			Navigate.dismissAlert(driver);
		}
		else
		{
			Navigate.closeTab(driver);
		}
	}
	public static void pagedown1(WebDriver driver) throws AWTException

	{
		((JavascriptExecutor)driver).executeScript("scroll(0,4600)");
	}
	/**
	 * Suresh V
	 * @param driver
	 */
	public static void viewArticle(WebDriver driver) 

	{
		WebElement viewarticle=driver.findElement(By.xpath(GETTINGFIT_VIEW_ARTICLE_LINK)); 
		verifyElementIsPresent(driver, viewarticle);
		wait(driver, "2");
		try
		{
			Main_Window1 = driver.getWindowHandle();
			click(viewarticle);
			wait(driver, "2"); 
			ArrayList<String> allWindows=new ArrayList<String>(driver.getWindowHandles());
			String windowname=driver.switchTo().window(allWindows.get(1)).getTitle();
			if(windowname.contains("pdf")){
				System.out.println("pdf was opened");
			}
			else {
				System.out.println("pdf was not opened");
				Assert.fail();
			}
			driver.switchTo().window(allWindows.get(1)).close();
			System.out.println("pdf closed");
			driver.switchTo().window(allWindows.get(0));
		}
		catch(Exception e)
		{
			System.out.println("Inside View Article catch block");
			getWindow(driver, viewarticle);
			wait(driver, "5" );
			driver.close();
			driver.switchTo().window(Main_Window);
		}
	}
	/**
	 * Leena P.
	 * @param driver
	 */
	public static void vspnViewEducationalFormats(WebDriver driver) 
	{
		WebElement viewTutorial=driver.findElement(By.xpath(VSPN_TUTORIAL_LINK)); 
		verifyElementIsPresent(driver, viewTutorial);
		wait(driver, "2");
		WebElement viewSummary=driver.findElement(By.xpath(VSPN_SUMMARY_LINK)); 
		verifyElementIsPresent(driver, viewSummary);
		wait(driver, "2");
		click(viewTutorial);
		wait(driver, "2"); 
		ArrayList<String> allWindows=new ArrayList<String>(driver.getWindowHandles());
		String windowname=driver.switchTo().window(allWindows.get(1)).getTitle();
		System.out.println("window name is " +windowname.toLowerCase());
		if(windowname.toLowerCase().contains("plain")){
			System.out.println("tutorial was opened");
		}
		else {
			System.out.println("tutorial was not opened");
			Assert.fail();
			driver.switchTo().window(allWindows.get(1)).close();
		}
		driver.switchTo().window(allWindows.get(1)).close();
		System.out.println("tutorial page closed");
		driver.switchTo().window(allWindows.get(0));
		click(viewSummary);
		wait(driver, "2"); 
		ArrayList<String> allWindows1=new ArrayList<String>(driver.getWindowHandles());
		String windowname1=driver.switchTo().window(allWindows1.get(1)).getTitle();
		if(windowname1.contains("pdf")){
			System.out.println("pdf was opened");
		}
		else {
			System.out.println("pdf was not opened");
			Assert.fail();
			driver.switchTo().window(allWindows1.get(1)).close();
		}
		driver.switchTo().window(allWindows1.get(1)).close();
		System.out.println("pdf closed");
		driver.switchTo().window(allWindows1.get(0));
	}
}