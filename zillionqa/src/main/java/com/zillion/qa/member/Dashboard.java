package com.zillion.qa.member;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.zillion.qa.commands.ElementActions;
import com.zillion.qa.commands.Manipulation;
import com.zillion.qa.commands.Navigate;
import com.zillion.qa.utils.Directory;
import com.zillion.qa.utils.Platform;
public class Dashboard extends Manipulation implements OR
{
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   29/Dec/15
	 * Modified Date:   12/Jan/16
	 * Description :   Create a common method for Login into Member user in framework
	 * Ticket ID :
	 * Required Inputs :  Username and Password
	 */
	public static void memberLogin(WebDriver driver) {
		wait(driver, "1");
		Navigate.get(driver, Directory.Memberurl);
		Navigate.maximize(driver);
		wait(driver, "10");
		try
		{
			Navigate.waitTime(driver, "30");
			waitForElement(driver, OR.MEMBER_YOURMAIL);
		}
		catch(Exception e)
		{
			Navigate.refreshPage(driver);
			waitForElement(driver, OR.MEMBER_YOURMAIL);
			try
			{
				Navigate.waitTime(driver, "30");
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
			catch(Exception e1)
			{
				Navigate.refreshPage(driver);
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
		}
		WebElement member_username = driver.findElement(By.xpath(OR.MEMBER_YOURMAIL));
		actionType(driver,member_username, Directory.Memberusername1);
		System.out.println("Username is typed");
		//member_username.sendKeys(Keys.TAB);
		WebElement member_password = driver.findElement(By.xpath(OR.MEMBER_YOURPASSWORD));
		jsClickByXPath(driver, OR.MEMBER_YOURPASSWORD);
		actionType(driver,member_password,Directory.Memberpassword2);
		System.out.println("Password is typed");
		waitForElement(driver, OR.MEMBER_LOGIN_BUTTON);
		jsClickByXPath(driver, OR.MEMBER_LOGIN_BUTTON);
		System.out.println("Login button is clicked");
		Navigate.waitTime(driver, "20");
		waitForElement(driver, OR.MEMBER_DASHBOARD);
		waitForAjax(driver);
		wait(driver, "10");
		System.out.println("Member logged in successfully");
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   14/Dec/15
	 * Modified Date:
	 * Description :   Create a common method for Logout from Member user in framework
	 * Ticket ID :
	 * Required Inputs :
	 *
	 */


	public static void memberLogout(WebDriver driver)
	{
		WebElement member_profile = driver.findElement(By.xpath(OR.MEMBER_PROFILE));
		click(member_profile);
		waitForElement(driver, OR.MEMBER_LOGOUT);
		WebElement member_logout= driver.findElement(By.xpath(OR.MEMBER_LOGOUT));
		click(member_logout);
		wait(driver, "5");
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   14/Dec/15
	 * Modified Date:   12/Jan/16
	 * Description :   Create a common method to view content from Library in Member user
	 * Ticket ID :
	 * Required Inputs :
	 *
	 */
	public static void viewContentFromArticle(WebDriver driver)
	{
		int no_widgets=driver.findElements(By.xpath("//div[@class='content-holder col-xs-12 col-sm-6 col-md-4 col-xl-3 ng-scope'][*]")).size();
		WebElement view;
		for(int i=1;i<=no_widgets;i++)
		{
			wait(driver, "1");
			String view1=null;
			try
			{

				view=driver.findElement(By.xpath("//div[@class='content-holder col-xs-12 col-sm-6 col-md-4 col-xl-3 ng-scope']["+i+"]//div[@class='content-holder-footer']//button"));
				System.out.println(i);
			}
			catch(Exception e)
			{
				System.out.println(i);

				view=driver.findElement(By.xpath("//div[@class='content-holder col-xs-12 col-sm-6 col-md-4 col-xl-3 ng-scope']["+i+"]//div[@class='content-holder-footer']//a"));
			}
			view1=view.getText();

			if (view1.equalsIgnoreCase("VIEW RECIPE ")||view1.equalsIgnoreCase("VIEW CONTRACT ")||view1.equalsIgnoreCase("VIEW ACTIVITY "))
			{
				wait(driver, "1");
				jsClickByXPath(driver, "//div[@class='content-holder col-xs-12 col-sm-6 col-md-4 col-xl-3 ng-scope']["+i+"]//div[@class='content-holder-footer']//button");
				System.out.println("Recipe is clicked");
				waitForElement(driver, OR.MEMBER_LIBRARY_CONTENT);
				waitForAjax(driver);
				System.out.println("waited");
				WebElement articlesummary=driver.findElement(By.xpath(OR.MEMBER_LIBRARY_CONTENT));
				verifyElementIsPresent(driver, articlesummary);

				System.out.println("Content is verified");
				WebElement articleclose=driver.findElement(By.xpath(OR.MEMBER_ARTICLE_CLOSE));
				click(articleclose);
				System.out.println("Content is closed");
			}
			if (view1.equalsIgnoreCase("VIEW ARTICLE "))
			{
				wait(driver, "1");
				jsClickByXPath(driver, "//div[@class='content-holder col-xs-12 col-sm-6 col-md-4 col-xl-3 ng-scope']["+i+"]//div[@class='content-holder-footer']//a");
				System.out.println("Article is clicked");
				wait(driver, "2");
				waitForElement(driver, OR.MEMBER_LIBRARY_ARTICLE_CONTENT);
				WebElement article=driver.findElement(By.xpath(OR.MEMBER_LIBRARY_ARTICLE_CONTENT));
				verifyElementIsPresent(driver, article);
				System.out.println("Content is verified");
				Navigate.goBack(driver);
				waitForElement(driver,OR.MEMBER_LIBRARY_TAB );
				jsClickByXPath(driver,OR.MEMBER_LIBRARY_TAB);
				waitForElement(driver, MEMBER_LIBRARY_ALLCONTENT);
				WebElement allcontent=driver.findElement(By.xpath(MEMBER_LIBRARY_ALLCONTENT));
				click(allcontent);
				waitForElement(driver, MEMBER_LIBRARY_ALLCONTENT);
			}

			if (view1.equalsIgnoreCase("WATCH VIDEO "))
			{
				wait(driver, "1");
				jsClickByXPath(driver, "//div[@class='content-holder col-xs-12 col-sm-6 col-md-4 col-xl-3 ng-scope']["+i+"]//a[@class='btn btn-lg btn-primary-reverse text-sm btn-block ng-binding ng-scope']");
				waitForElement(driver, MEMBER_LIBRARY_VIDEO_PAUSE);
				WebElement article=driver.findElement(By.xpath(MEMBER_LIBRARY_VIDEO_PAUSE));
				verifyElementIsPresent(driver, article);
				WebElement articleclose=driver.findElement(By.xpath(OR.MEMBER_ARTICLE_CLOSE));
				click(articleclose);
			}
		}
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   22/Dec/15
	 * Modified Date:
	 * Description :   Create a common method to enter a weight in log weight in Member user
	 * Ticket ID :
	 * Required Inputs :
	 *
	 */
	public static void dashboardEnterWeight(WebDriver driver,String weight)
	{
		waitForElement(driver, "//span[text()='Log Weight']");
		WebElement logweighttextbox=driver.findElement(By.xpath("//input[@id='dashboardEnterWeight']"));
		WebElement logweightenter=driver.findElement(By.xpath("//*[@id='dashboardStatsTemp']//span[text()='Log Weight']"));
		clearAndType(logweighttextbox, weight);
		Navigate.pageDown(driver);
		Navigate.keyboardPageDown(driver);
		mouseOverAndClick(driver, logweightenter);
		wait(driver,"3");


	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   22/Dec/15
	 * Modified Date:   29/Apr/15
	 * Description :   Create a common method to upload photos in Member user
	 * Ticket ID :
	 * Required Inputs :
	 * @throws IOException
	 * @throws AWTException
	 * @throws FindFailed
	 *
	 */
	public static void uploadPhotos(WebDriver driver, String filepath) throws InterruptedException, AWTException, FindFailed
	{
		String OSName=Platform.OS.toUpperCase();
		if(OSName.contains("WINDOWS")){
			wait(driver, "5");
			waitForElement(driver, MEMBER_TRACKER_UPLOADPHOTO);
			WebElement uploadphoto=driver.findElement(By.xpath(MEMBER_TRACKER_UPLOADPHOTO));
			actionClick(driver,uploadphoto);
			System.out.println("Clicked upload photo on Tracker");
			wait(driver, "2");
			String filepath1=Directory.uploadFilePath+filepath;
			File fileName = new File(filepath1);
			String absoultePath = fileName.getAbsoluteFile().toString();
			System.out.println(absoultePath);
			wait(driver, "2");
			fileUploadRobot(driver,absoultePath);
			waitForAjax(driver);
			System.out.println("Uploaded the Image");
			wait(driver, "8");
			try{
				waitForElement(driver, MEMBER_UPLOAD_SAVE);
				jsClickByXPath(driver,MEMBER_PHOTO_ORIENTATION);
				jsClickByXPath(driver,MEMBER_UPLOAD_SAVE);
				wait(driver, "3");
			}
			catch(NoSuchElementException e ) {
				Robot robot = new Robot();
				wait(driver, "1");
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				wait(driver, "2");
				waitForElement(driver, MEMBER_UPLOAD_SAVE);
				jsClickByXPath(driver,MEMBER_UPLOAD_SAVE);
				wait(driver, "3");
			}
			System.out.println("Clicked the save button");
			wait(driver, "8");
		}
		else if(OSName.contains("MAC"))
		{
			wait(driver,"8");
			waitForElement(driver, MEMBER_TRACKER_UPLOADPHOTO);
			WebElement uploadphoto=driver.findElement(By.xpath(MEMBER_TRACKER_UPLOADPHOTO));
			actionClick(driver,uploadphoto);
			wait(driver,"5");
			System.out.println("Clicked upload photo on Tracker");
			Navigate.waitTime(driver, "5");
			String filepath1=Directory.uploadFilePath+filepath;
			File fileName = new File(filepath1);
			String absoultePath = fileName.getAbsoluteFile().toString();
			System.out.println(absoultePath);
			StringSelection stringSelection= new StringSelection(filepath1);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_META);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyPress(KeyEvent.VK_META);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyPress(KeyEvent.VK_META);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_META);
			robot.keyRelease(KeyEvent.VK_TAB);
			robot.delay(500);
			robot.keyPress(KeyEvent.VK_META);
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_G);
			robot.keyRelease(KeyEvent.VK_META);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			robot.keyRelease(KeyEvent.VK_G);
			wait(driver, "2");
			robot.keyPress(KeyEvent.VK_META);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_META);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			wait(driver, "2");
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			wait(driver, "2");
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			wait(driver, "2");
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			wait(driver, "2");
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			wait(driver, "2");
			System.out.println("Uploaded the Image");
			Navigate.waitTime(driver, "5");
			wait(driver, "5");
			waitForAjax(driver);
			System.out.println("Uploaded the Image");
			wait(driver, "8");
			try
			{
				waitForElement(driver, MEMBER_UPLOAD_SAVE1);
				jsClickByXPath(driver,MEMBER_PHOTO_ORIENTATION);
				jsClickByXPath(driver,MEMBER_UPLOAD_SAVE1);
				wait(driver, "3");
			}
			catch(NoSuchElementException e1)
			{
				Robot robot1 = new Robot();
				wait(driver, "1");
				robot1.keyPress(KeyEvent.VK_ENTER);
				robot1.keyRelease(KeyEvent.VK_ENTER);
				wait(driver, "2");
				waitForElement(driver, MEMBER_UPLOAD_SAVE1);
				jsClickByXPath(driver,MEMBER_UPLOAD_SAVE1);
				wait(driver, "3");
			}
			System.out.println("Clicked the save button");
			wait(driver, "8");
		}
	}
	public static void uploadPhotos2(WebDriver driver, String filepath) throws InterruptedException, AWTException
	{
		String OSName=Platform.OS.toUpperCase();
		if(OSName.contains("WINDOWS")){
			Navigate.waitTime(driver, "10");
			waitForElement(driver, MEMBER_TRACKER_UPLOADPHOTO);
			WebElement uploadphoto=driver.findElement(By.xpath(MEMBER_TRACKER_UPLOADPHOTO));
			uploadphoto.click();
			System.out.println("Clicked upload photo on Tracker");
			wait(driver, "5");
			String filepath1=Directory.uploadFilePath+filepath;
			File fileName = new File(filepath1);
			String absoultePath = fileName.getAbsoluteFile().toString();
			System.out.println(absoultePath);
			wait(driver, "5");
			fileUploadRobot(driver,absoultePath);
			waitForAjax(driver);
			System.out.println("Uploaded the Image");
			Navigate.waitTime(driver, "5");
			wait(driver, "5");
			try{
				waitForElement(driver, MEMBER_UPLOAD_SAVE);
				jsClickByXPath(driver,MEMBER_PHOTO_ORIENTATION);
				jsClickByXPath(driver,MEMBER_UPLOAD_SAVE);
				wait(driver, "5");
			}
			catch(NoSuchElementException e ) {
				Robot robot = new Robot();
				wait(driver, "5");
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				wait(driver, "5");
				waitForElement(driver, MEMBER_UPLOAD_SAVE);
				jsClickByXPath(driver,MEMBER_UPLOAD_SAVE);
				wait(driver, "5");
			}
			System.out.println("Clicked the save button");
			Navigate.waitTime(driver, "30");
			wait(driver, "5");
		}
		else if(OSName.contains("MAC"))
		{
			wait(driver,"5");
			Navigate.waitTime(driver, "10");
			waitForElement(driver, MEMBER_TRACKER_UPLOADPHOTO);
			WebElement uploadphoto=driver.findElement(By.xpath(MEMBER_TRACKER_UPLOADPHOTO));
			actionClick(driver,uploadphoto);
			wait(driver,"5");
			System.out.println("Clicked upload photo on Tracker");
			Navigate.waitTime(driver, "5");
			String filepath1=Directory.uploadFilePath+filepath;
			File fileName = new File(filepath1);
			String absoultePath = fileName.getAbsoluteFile().toString();
			System.out.println(absoultePath);
			StringSelection stringSelection= new StringSelection(filepath1);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
			Robot robot = new Robot();
			//Open Goto window
			robot.keyPress(KeyEvent.VK_META);
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_G);
			robot.keyRelease(KeyEvent.VK_META);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			robot.keyRelease(KeyEvent.VK_G);
			robot.keyPress(KeyEvent.VK_META);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_META);
			robot.keyRelease(KeyEvent.VK_V);
			//Press Enter key to close the Goto window and Upload window
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			wait(driver, "5");
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			wait(driver, "5");
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			wait(driver, "5");
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			wait(driver, "5");
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			// fileUploadRobot(driver,absoultePath);
			waitForAjax(driver);
			System.out.println("Uploaded the Image");
			Navigate.waitTime(driver, "5");
			wait(driver, "5");
			try
			{
				waitForElement(driver, MEMBER_UPLOAD_SAVE1);
				jsClickByXPath(driver,MEMBER_PHOTO_ORIENTATION);
				jsClickByXPath(driver,MEMBER_UPLOAD_SAVE1);
				wait(driver, "5");
			}
			catch(NoSuchElementException e1)
			{
				Robot robot1 = new Robot();
				wait(driver, "5");
				robot1.keyPress(KeyEvent.VK_ENTER);
				robot1.keyRelease(KeyEvent.VK_ENTER);
				wait(driver, "5");
				waitForElement(driver, MEMBER_UPLOAD_SAVE1);
				jsClickByXPath(driver,MEMBER_UPLOAD_SAVE1);
				wait(driver, "5");
			}
			System.out.println("Clicked the save button");
			Navigate.waitTime(driver, "30");
			wait(driver, "5");
		}
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   14/Dec/15
	 * Modified Date:   24/Feb/2016
	 * Description :Guerrilla mail reload
	 * Ticket ID :
	 * Required Inputs :
	 */
	public static void guerrillaMail(WebDriver driver,String normalXpath)
	{
		try{
			if(driver.findElement( By.xpath( normalXpath ) ).isDisplayed())
			{
				waitForElement(driver, normalXpath);
			}
			else
			{
				driver.navigate().refresh();
				wait( driver, "3" );
				if(driver.findElement( By.xpath( normalXpath ) ).isDisplayed())
				{
					waitForElement(driver, normalXpath);
				}
				else
				{
					driver.navigate().refresh();
					wait( driver, "3" );
					waitForElement(driver, normalXpath);
				}
			}
		}
		catch(Exception e)
		{
			driver.navigate().refresh();
			wait( driver, "3" );
			waitForElement(driver, normalXpath);
		}
	}
	public static void deletePhotosBeforeUpload(WebDriver driver) throws IOException
	{
		try
		{
			WebElement image=driver.findElement(By.xpath(MEMBER_PHOTO1));
			clickAndHold(driver, image);
			jsClickByXPath(driver, "//span[text()='Delete Photo']");
			System.out.println("photo is deleted");
			waitForAjax(driver);
			wait(driver, "2");
			WebElement image1=driver.findElement(By.xpath(MEMBER_PHOTO1));
			clickAndHold(driver, image1);
			jsClickByXPath(driver, "//span[text()='Delete Photo']");
			System.out.println("photo is deleted");
			waitForAjax(driver);
			wait(driver, "2");
			WebElement image3=driver.findElement(By.xpath(MEMBER_PHOTO1));
			clickAndHold(driver, image3);
			jsClickByXPath(driver, "//span[text()='Delete Photo']");
			System.out.println("photo is deleted");
			waitForAjax(driver);
			wait(driver, "2");

		}
		catch(Exception e)
		{

		}
	}

	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   05/02/2015
	 * Modified Date:
	 * Description :   Create a common method for Login after Lecture creation
	 * Ticket ID :
	 * Required Inputs :  Username and Password
	 *
	 */

	public static void lectureMemberLogin(WebDriver driver) {
		wait(driver, "1");
		Navigate.get(driver, Directory.Memberurl);
		Navigate.maximize(driver);
		wait(driver, "10");
		try
		{
			Navigate.waitTime(driver, "30");
			waitForElement(driver, OR.MEMBER_YOURMAIL);
		}
		catch(Exception e)
		{
			Navigate.refreshPage(driver);
			waitForElement(driver, OR.MEMBER_YOURMAIL);
			try
			{
				Navigate.waitTime(driver, "30");
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
			catch(Exception e1)
			{
				Navigate.refreshPage(driver);
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
		}
		WebElement member_username = driver.findElement(By.xpath(OR.MEMBER_YOURMAIL));
		actionType(driver,member_username, Directory.Lecturememberusername);
		System.out.println("Username is typed");
		//member_username.sendKeys(Keys.TAB);
		WebElement member_password = driver.findElement(By.xpath(OR.MEMBER_YOURPASSWORD));
		jsClickByXPath(driver, OR.MEMBER_YOURPASSWORD);
		actionType(driver,member_password,Directory.Lecturememberpassword);
		System.out.println("Password is typed");
		waitForElement(driver, OR.MEMBER_LOGIN_BUTTON);
		jsClickByXPath(driver, OR.MEMBER_LOGIN_BUTTON);
		System.out.println("Login button is clicked");
		Navigate.waitTime(driver, "20");
		waitForElement(driver, OR.MEMBER_DASHBOARD);
		waitForAjax(driver);
		wait(driver, "10");
		System.out.println("Member logged in successfully");
	}
	/**
	 * Name :     Abinaya.P
	 * Created Date:   16/02/2016
	 * Modified Date:
	 * Description :   Create a common method to schedule/Reschedule/Cancel Session from member portal
	 * Testcase Sheet:
	 * Ticket ID :
	 * Required Inputs :
	 * @throws AWTException
	 *
	 */

	public static void scheduleOrRescheduleOrCancelSession(WebDriver driver, String inputData) throws AWTException {
		String[] inputData1 = inputData.split(",");
		String gmailUsernamedata=inputData1[0];
		String gmailPassworddata=inputData1[1];
		String yahooUsernamedata=inputData1[2];
		String yahooPassworddata=inputData1[3];
		try
		{
			wait(driver, "5");
			waitForElement(driver, MEMBER_SCHEDULE_1ON1SESSION);
			jsClickByXPath(driver, MEMBER_SCHEDULE_1ON1SESSION);
			wait(driver, "5");
		}
		catch(Exception e)
		{
			wait(driver, "5");
			waitForElement(driver,MEMBER_SESSIONIN_NEXT_INTERVAL);

		}
		Navigate.pageUp(driver);
		/*waitForElement(driver, MEMBER_SCHEDULE_SESSIONDATE);
		jsClickByXPath(driver, MEMBER_SCHEDULE_SESSIONDATE);
		jsClickByXPath(driver, MEMBER_SCHEDULE_SESSIONTIME);
		 */
		com.zillion.qa.member.Dashboard.dateSession(driver);
		waitForElement(driver, MEMBER_SESSION_CONFIRM);
		jsClickByXPath(driver, MEMBER_SESSION_CONFIRM);
		wait(driver, "5");
		waitForElement(driver, MEMBER_SESSION_SUCCESS_MSG);
		WebElement  memberAddCalender= driver.findElement(By.xpath(OR.MEMBER_ADDTO_CALENDER));
		waitForElement(driver, MEMBER_ADDTO_CALENDER);
		jsClickByXPath(driver, MEMBER_ADDTO_CALENDER);
		WebElement  googleCalender= driver.findElement(By.xpath(OR.MEMBER_GOOGLE_CALENDER));
		WebElement  yahooCalender= driver.findElement(By.xpath(OR.MEMBER_YAHOO_CALENDER));
		WebElement  icalCalender= driver.findElement(By.xpath(OR.MEMBER_ICAL_CALENDER));
		WebElement  outlookCalender= driver.findElement(By.xpath(OR.MEMBER_OUTLOOK_CALENDER));
		verifyElementIsPresent(driver, googleCalender);
		verifyElementIsPresent(driver, yahooCalender);
		verifyElementIsPresent(driver, icalCalender);
		verifyElementIsPresent(driver, outlookCalender);
		getWindow(driver, googleCalender);
		waitForElement(driver, GMAIL_USERNAME);
		WebElement gmailUsername = driver.findElement(By.xpath(OR.GMAIL_USERNAME));
		actionType(driver,gmailUsername ,gmailUsernamedata);
		jsClickByXPath(driver, GMAIL_STAY_SIGIN);
		jsClickByXPath(driver, GMAIL_NEXT);
		wait(driver, "2");
		waitForElement(driver, GMAIL_PASSWORD);
		WebElement gmailPassword = driver.findElement(By.xpath(OR.GMAIL_PASSWORD));
		actionType(driver,gmailPassword , gmailPassworddata);
		jsClickByXPath(driver, GMAIL_SIGNIN);
		waitForElement(driver, GMAIL_SAVE_CALENDER);
		jsClickByXPath(driver, GMAIL_SAVE_CALENDER);
		waitForElement(driver, GMAIL_CALENDER_CONFIRM_MSG);
		jsClickByXPath(driver, GMAIL_SETTING);
		jsClickByXPath(driver, GMAIL_LOGOUT);
		wait(driver, "2");
		Navigate.close(driver);
		switchWindow(driver);
		wait(driver, "5");
		waitForElement(driver, MEMBER_ADDTO_CALENDER);
		mouseOver(driver, memberAddCalender);
		jsClickByXPath(driver, MEMBER_ADDTO_CALENDER);
		getWindow(driver, yahooCalender);
		try
		{
			waitForElement(driver, YAHOO_USERNAME);
		}
		catch(Exception e)
		{
			Navigate.refreshPage(driver);
			waitForElement(driver, YAHOO_USERNAME);
			try
			{
				waitForElement(driver, YAHOO_USERNAME);
			}
			catch(Exception e1)
			{
				Navigate.refreshPage(driver);
				waitForElement(driver, YAHOO_USERNAME);
				try
				{
					waitForElement(driver, YAHOO_USERNAME);
				}
				catch(Exception e2)
				{
					Navigate.refreshPage(driver);
					waitForElement(driver, YAHOO_USERNAME);
				}
			}
		}
		WebElement yahooUsername = driver.findElement(By.xpath(OR.YAHOO_USERNAME));
		Manipulation.sendKeys(yahooUsername , yahooUsernamedata);
		wait(driver, "3");
		WebElement yahoo_staysign = driver.findElement(By.xpath(OR.YAHOO_STAY_SIGIN));
		Manipulation.click(yahoo_staysign);
		jsClickByXPath(driver, YAHOO_SIGNIN);
		wait(driver, "3");
		WebElement yahooPassword = driver.findElement(By.xpath(OR.YAHOO_PASSWORD));
		actionType(driver,yahooPassword , yahooPassworddata);
		jsClickByXPath(driver, YAHOO_SIGNIN);
		waitForElement(driver, YAHOO_SAVE_CALENDER);
		jsClickByXPath(driver, YAHOO_SAVE_CALENDER);
		wait(driver, "2");
		//jsClickByXPath(driver, YAHOO_SETTING);
		WebElement yahooSettings = driver.findElement(By.xpath(OR.YAHOO_SETTING));
		mouseOver(driver, yahooSettings);
		wait(driver, "1");
		jsClickByXPath(driver, YAHOO_LOGOUT);
		wait(driver, "2");
		Navigate.close(driver);
		switchWindow(driver);
		wait(driver, "2");
		waitForElement(driver, MEMBER_ADDTO_CALENDER);
		jsClickByXPath(driver, MEMBER_ADDTO_CALENDER);
		jsClickByXPath(driver, MEMBER_ICAL_CALENDER);
		wait(driver, "4");
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		wait(driver, "2");
		waitForElement(driver, MEMBER_ADDTO_CALENDER);
		jsClickByXPath(driver, MEMBER_ADDTO_CALENDER);
		jsClickByXPath(driver, MEMBER_OUTLOOK_CALENDER);
		wait(driver, "4");
		robot.keyPress(KeyEvent.VK_ENTER);
		wait(driver, "2");
		waitForElement(driver, MEMBER_DASHBOARD);
		jsClickByXPath(driver, MEMBER_DASHBOARD);
		wait(driver, "2");
		waitForElement(driver, MEMBER_SCHEDULED_10N1SESSION_);
		inVisibilityElement(driver,MEMBER_SCHEDULE_1ON1SESSION);
		jsClickByXPath(driver, CHANGE_SESSION);
		wait(driver, "4");
		waitForElement(driver, CHANGE_SCHEDULED_TIME);
		jsClickByXPath(driver, CHANGE_SCHEDULED_TIME);
		wait(driver, "3");
		/*
		waitForElement(driver, MEMBER_SCHEDULE_SESSIONDATE);
		jsClickByXPath(driver, MEMBER_SCHEDULE_SESSIONDATE);
		jsClickByXPath(driver, MEMBER_RESCHEDULE_SESSIONTIME);
		 */
		com.zillion.qa.member.Dashboard.dateSession(driver);
		waitForElement(driver, MEMBER_SESSION_CONFIRM);
		jsClickByXPath(driver, MEMBER_SESSION_CONFIRM);
		waitForElement(driver, MEMBER_SESSION_SUCCESS_MSG);
		waitForElement(driver, MEMBER_ADDTO_CALENDER);
		jsClickByXPath(driver, MEMBER_ADDTO_CALENDER);
		WebElement  googleCalender1= driver.findElement(By.xpath(OR.MEMBER_GOOGLE_CALENDER));
		WebElement  yahooCalender1= driver.findElement(By.xpath(OR.MEMBER_YAHOO_CALENDER));
		getWindow(driver, googleCalender1);
		waitForElement(driver, GMAIL_USERNAME);
		WebElement gmailUsername1 = driver.findElement(By.xpath(OR.GMAIL_USERNAME));
		actionType(driver,gmailUsername1 , gmailUsernamedata);
		jsClickByXPath(driver, GMAIL_STAY_SIGIN);
		jsClickByXPath(driver, GMAIL_NEXT);
		wait(driver, "2");
		waitForElement(driver, GMAIL_PASSWORD);
		WebElement gmailPassword1 = driver.findElement(By.xpath(OR.GMAIL_PASSWORD));
		actionType(driver,gmailPassword1 , gmailPassworddata);
		jsClickByXPath(driver, GMAIL_SIGNIN);
		waitForElement(driver, GMAIL_SAVE_CALENDER);
		jsClickByXPath(driver, GMAIL_SAVE_CALENDER);
		waitForElement(driver, GMAIL_CALENDER_CONFIRM_MSG);
		jsClickByXPath(driver, GMAIL_SETTING);
		jsClickByXPath(driver, GMAIL_LOGOUT);
		wait(driver, "2");
		Navigate.close(driver);
		switchWindow(driver);
		wait(driver, "5");
		waitForElement(driver, MEMBER_ADDTO_CALENDER);
		jsClickByXPath(driver, MEMBER_ADDTO_CALENDER);
		getWindow(driver, yahooCalender1);
		try
		{
			waitForElement(driver, YAHOO_USERNAME);
		}
		catch(Exception e)
		{
			Navigate.refreshPage(driver);
			waitForElement(driver, YAHOO_USERNAME);
			try
			{
				waitForElement(driver, YAHOO_USERNAME);
			}
			catch(Exception e1)
			{
				Navigate.refreshPage(driver);
				waitForElement(driver, YAHOO_USERNAME);
				try
				{
					waitForElement(driver, YAHOO_USERNAME);
				}
				catch(Exception e3)
				{
					Navigate.refreshPage(driver);
					waitForElement(driver, YAHOO_USERNAME);
				}
			}
		}

		WebElement yahooUsername1 = driver.findElement(By.xpath(OR.YAHOO_USERNAME));
		Manipulation.sendKeys(yahooUsername1 , yahooUsernamedata);
		wait(driver, "3");
		WebElement yahoo_staysign1 = driver.findElement(By.xpath(OR.YAHOO_STAY_SIGIN));
		Manipulation.click(yahoo_staysign1);
		jsClickByXPath(driver, YAHOO_SIGNIN);
		wait(driver, "3");
		WebElement yahooPassword1 = driver.findElement(By.xpath(OR.YAHOO_PASSWORD));
		actionType(driver,yahooPassword1 , yahooPassworddata);
		jsClickByXPath(driver, YAHOO_SIGNIN);
		waitForElement(driver, YAHOO_SAVE_CALENDER);
		jsClickByXPath(driver, YAHOO_SAVE_CALENDER);
		wait(driver, "2");
		//jsClickByXPath(driver, YAHOO_SETTING);
		mouseOver(driver, yahooSettings);
		jsClickByXPath(driver, YAHOO_LOGOUT);
		wait(driver, "2");
		Navigate.close(driver);
		switchWindow(driver);
		wait(driver, "2");
		waitForElement(driver, MEMBER_ADDTO_CALENDER);
		jsClickByXPath(driver, MEMBER_ADDTO_CALENDER);
		jsClickByXPath(driver, MEMBER_ICAL_CALENDER);
		wait(driver, "4");
		robot.keyPress(KeyEvent.VK_ENTER);
		wait(driver, "2");
		waitForElement(driver, MEMBER_ADDTO_CALENDER);
		jsClickByXPath(driver, MEMBER_ADDTO_CALENDER);
		jsClickByXPath(driver, MEMBER_OUTLOOK_CALENDER);
		wait(driver, "4");
		robot.keyPress(KeyEvent.VK_ENTER);
		wait(driver, "2");
		jsClickByXPath(driver, MEMBER_DASHBOARD);
		wait(driver, "2");
		waitForElement(driver, MEMBER_SCHEDULED_10N1SESSION_);
		jsClickByXPath(driver, CHANGE_SESSION);
		wait(driver, "4");
		waitForElement(driver, MEMBER_CANCEL_SESSION);
		jsClickByXPath(driver, MEMBER_CANCEL_SESSION);
		waitForElement(driver, MEMBER_SESSION_POPUP_CANCEL);
		jsClickByXPath(driver, MEMBER_SESSION_POPUP_CANCEL);
		jsClickByXPath(driver, MEMBER_CANCEL_SESSION);
		waitForElement(driver, MEMBER_SESSION_POPUP_CLOSE);
		jsClickByXPath(driver, MEMBER_SESSION_POPUP_CLOSE);
		jsClickByXPath(driver, MEMBER_CANCEL_SESSION);
		waitForElement(driver, MEMBER_SESSION_POPUP_OK);
		jsClickByXPath(driver, MEMBER_SESSION_POPUP_OK);
		waitForElement(driver, SESSION_CANCEL_MSG);


	}
	/**
	 * Name :     Abinaya.P
	 * Created Date:   16/02/2016
	 * Modified Date:
	 * Description :   Create a common method to verify view sessions from member portal
	 * Ticket ID :
	 * Required Inputs :
	 *
	 */

	public static void verifyViewSessions(WebDriver driver) {
		wait(driver, "3");
		int noOfSessions=driver.findElements(By.xpath("//div[@id='lectureSessionModal']//div//div[@class='panel-box-list-inline panel-box-bottom-border ng-scope'][*]")).size();
		for(int i=1;i<=noOfSessions;i++)
		{
			waitForElement(driver, "//div[@id='lectureSessionModal']//div//div[@class='panel-box-list-inline panel-box-bottom-border ng-scope']["+i+"]");
			verifyElementIsPresent(driver, driver.findElement(By.xpath("//div[@id='lectureSessionModal']//div//div[@class='panel-box-list-inline panel-box-bottom-border ng-scope']["+i+"]")));
		}
	}



	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   26/02/2015
	 * Modified Date:
	 * Description :   Create a common method for Login Session widget display
	 * Ticket ID :     187186
	 * Required Inputs :  Username and Password
	 *
	 */

	public static void sessionMemberLogin(WebDriver driver) {
		wait(driver, "1");
		Navigate.get(driver, Directory.Memberurl);
		Navigate.maximize(driver);
		wait(driver, "10");
		try
		{
			Navigate.waitTime(driver, "30");
			waitForElement(driver, OR.MEMBER_YOURMAIL);
		}
		catch(Exception e)
		{
			Navigate.refreshPage(driver);
			waitForElement(driver, OR.MEMBER_YOURMAIL);
			try
			{
				Navigate.waitTime(driver, "30");
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
			catch(Exception e1)
			{
				Navigate.refreshPage(driver);
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
		}
		WebElement member_username = driver.findElement(By.xpath(OR.MEMBER_YOURMAIL));
		actionType(driver,member_username, Directory.Sessionmemberusername);
		System.out.println("Username is typed");
		//member_username.sendKeys(Keys.TAB);
		WebElement member_password = driver.findElement(By.xpath(OR.MEMBER_YOURPASSWORD));
		jsClickByXPath(driver, OR.MEMBER_YOURPASSWORD);
		actionType(driver,member_password,Directory.Sessionmemberpassword);
		System.out.println("Password is typed");
		waitForElement(driver, OR.MEMBER_LOGIN_BUTTON);
		jsClickByXPath(driver, OR.MEMBER_LOGIN_BUTTON);
		System.out.println("Login button is clicked");
		Navigate.waitTime(driver, "20");
		waitForElement(driver, OR.MEMBER_DASHBOARD);
		waitForAjax(driver);
		wait(driver, "10");
		System.out.println("Member logged in successfully");
	}


	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   03/02/2016
	 * Modified Date:
	 * Description :   Create a common method to VERIFY CURRENT DATE
	 * Required Inputs :
	 *
	 */
	public static String changePlacementDateFuture(WebDriver driver)
	{
		/*String currentdate = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
		String currentmonth = new SimpleDateFormat("MM").format(Calendar.getInstance().getTime());
		String currentyear = new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
		String currentdate2= currentdate.substring(0).replace("0", "");
		if(currentmonth!="10"&&currentmonth!="11"&&currentmonth!="12")
		{
			String currentmonth2= currentmonth.substring(0).replace("0", "");
		}
		System.out.println("currentdate: "+currentdate2);
		System.out.println("currentmonth: "+currentmonth);
		int currentdate3 = Integer.parseInt(currentdate2);
		int currentdate4= currentdate3+1;
		String currentdate5 = Integer.toString(currentdate4);*/
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		String currentdate5 = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
		String currentmonth = Integer.toString(cal.get(Calendar.MONTH)+1);
		String currentyear = Integer.toString(cal.get(Calendar.YEAR));
		WebElement placement_day = driver.findElement(By.xpath(OR.PLACEMENT_APPOINTMENT_DAY));
		WebElement placement_month = driver.findElement(By.xpath(OR.PLACEMENT_APPOINTMENT_MONTH));
		WebElement placement_year = driver.findElement(By.xpath(OR.PLACEMENT_APPOINTMENT_YEAR));
		WebElement placement_submit_button = driver.findElement(By.xpath(OR.PLACEMENT_APPOINTMENT_SUBMIT_BUTTON));
		selectByVisibletext(placement_day, currentdate5);
		Navigate.waitTime(driver, "5");
		selectByVisibletext(placement_month, currentmonth);
		Navigate.waitTime(driver, "5");
		selectByVisibletext(placement_year, currentyear);
		Navigate.waitTime(driver, "5");
		click(placement_submit_button);
		return currentdate5;
	}


	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   03/02/2016
	 * Modified Date:
	 * Description :   Create a common method to VERIFY CURRENT DATE
	 * Required Inputs :
	 *
	 */
	public static String memberSignUpLectureSession(WebDriver driver)
	{


		//Member-1-LectureSignUp
		wait(driver, "1");
		Navigate.get(driver, Directory.Memberurl);
		Navigate.maximize(driver);
		wait(driver, "4");
		try
		{
			Navigate.waitTime(driver, "30");
			waitForElement(driver, OR.MEMBER_YOURMAIL);
		}
		catch(Exception e)
		{
			Navigate.refreshPage(driver);
			waitForElement(driver, OR.MEMBER_YOURMAIL);
			try
			{
				Navigate.waitTime(driver, "30");
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
			catch(Exception e1)
			{
				Navigate.refreshPage(driver);
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
		}
		WebElement member_username = driver.findElement(By.xpath(OR.MEMBER_YOURMAIL));
		actionType(driver,member_username, Directory.Memberusername2);
		System.out.println("Username is typed");
		//member_username.sendKeys(Keys.TAB);
		WebElement member_password = driver.findElement(By.xpath(OR.MEMBER_YOURPASSWORD));
		jsClickByXPath(driver, OR.MEMBER_YOURPASSWORD);
		actionType(driver,member_password,"Password1");
		System.out.println("Password is typed");
		waitForElement(driver, OR.MEMBER_LOGIN_BUTTON);
		jsClickByXPath(driver, OR.MEMBER_LOGIN_BUTTON);
		System.out.println("Login button is clicked");
		Navigate.waitTime(driver, "20");
		waitForElement(driver, OR.MEMBER_DASHBOARD);
		waitForAjax(driver);
		wait(driver, "5");
		System.out.println("Member logged in successfully");
		WebElement viewSessionButton = driver.findElement(By.xpath(OR.VIEW_SESSION_BUTTON));
		verifyElementIsPresent(driver, viewSessionButton);
		click(viewSessionButton);
		com.zillion.qa.opsadmin.Providers.memberVerifyLectureSession(driver);
		jsClickByXPath(driver, OR.PLACEMENT_APPOINTMENT_CLOSE_BUTTON);
		Navigate.refreshPage(driver);
		WebElement created_Lecture_session = driver.findElement(By.xpath(OR.PLACEMENT_APPOINTMENT_CREATED_LECTURE_SESSION));
		waitForElement(driver, OR.PLACEMENT_APPOINTMENT_CREATED_LECTURE_SESSION);
		verifyElementIsPresent(driver, created_Lecture_session);
		com.zillion.qa.member.Dashboard.memberLogout(driver);
		return ElementWait;


		/*
		//Member-2-LectureSignUp
		wait(driver, "1");
		Navigate.get(driver, Directory.Memberurl);
		Navigate.maximize(driver);
		wait(driver, "2");
		try
		{
			Navigate.waitTime(driver, "30");
			waitForElement(driver, OR.MEMBER_YOURMAIL);
		}
		catch(Exception e)
		{
			Navigate.refreshPage(driver);
			waitForElement(driver, OR.MEMBER_YOURMAIL);
			try
			{
				Navigate.waitTime(driver, "30");
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
			catch(Exception e1)
			{
				Navigate.refreshPage(driver);
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
		}
		//WebElement member_username = driver.findElement(By.xpath(OR.MEMBER_YOURMAIL));
		actionType(driver,member_username, "member.kulfi6.1@yopmail.com");
		System.out.println("Username is typed");
		//member_username.sendKeys(Keys.TAB);
		//WebElement member_password = driver.findElement(By.xpath(OR.MEMBER_YOURPASSWORD));
		jsClickByXPath(driver, OR.MEMBER_YOURPASSWORD);
		actionType(driver,member_password,"Password1");
		System.out.println("Password is typed");
		waitForElement(driver, OR.MEMBER_LOGIN_BUTTON);
		jsClickByXPath(driver, OR.MEMBER_LOGIN_BUTTON);
		System.out.println("Login button is clicked");
		Navigate.waitTime(driver, "20");
		waitForElement(driver, OR.MEMBER_DASHBOARD);
		waitForAjax(driver);
		wait(driver, "5");
		System.out.println("Member logged in successfully");
		verifyElementIsPresent(driver, viewSessionButton);
		click(viewSessionButton);
		com.zillion.qa.opsadmin.Providers.memberVerifyLectureSession(driver);
		jsClickByXPath(driver, OR.PLACEMENT_APPOINTMENT_CLOSE_BUTTON);
		Navigate.refreshPage(driver);
		//WebElement created_Lecture_session = driver.findElement(By.xpath(OR.PLACEMENT_APPOINTMENT_CREATED_LECTURE_SESSION));
		waitForElement(driver, OR.PLACEMENT_APPOINTMENT_CREATED_LECTURE_SESSION);
		verifyElementIsPresent(driver, created_Lecture_session);
		com.zillion.qa.member.Dashboard.memberLogout(driver);


		//Member-3-LectureSignUp
		wait(driver, "1");
		Navigate.get(driver, Directory.Memberurl);
		Navigate.maximize(driver);
		wait(driver, "4");
		try
		{
			Navigate.waitTime(driver, "30");
			waitForElement(driver, OR.MEMBER_YOURMAIL);
		}
		catch(Exception e)
		{
			Navigate.refreshPage(driver);
			waitForElement(driver, OR.MEMBER_YOURMAIL);
			try
			{
				Navigate.waitTime(driver, "30");
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
			catch(Exception e1)
			{
				Navigate.refreshPage(driver);
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
		}
		//WebElement member_username = driver.findElement(By.xpath(OR.MEMBER_YOURMAIL));
		actionType(driver,member_username, "member.kulfi2.1@yopmail.com");
		System.out.println("Username is typed");
		//member_username.sendKeys(Keys.TAB);
		//WebElement member_password = driver.findElement(By.xpath(OR.MEMBER_YOURPASSWORD));
		jsClickByXPath(driver, OR.MEMBER_YOURPASSWORD);
		actionType(driver,member_password,"Password1");
		System.out.println("Password is typed");
		waitForElement(driver, OR.MEMBER_LOGIN_BUTTON);
		jsClickByXPath(driver, OR.MEMBER_LOGIN_BUTTON);
		System.out.println("Login button is clicked");
		Navigate.waitTime(driver, "20");
		waitForElement(driver, OR.MEMBER_DASHBOARD);
		waitForAjax(driver);
		wait(driver, "5");
		System.out.println("Member logged in successfully");
		verifyElementIsPresent(driver, viewSessionButton);
		click(viewSessionButton);
		com.zillion.qa.opsadmin.Providers.memberVerifyLectureSession(driver);
		jsClickByXPath(driver, OR.PLACEMENT_APPOINTMENT_CLOSE_BUTTON);
		Navigate.refreshPage(driver);
		//WebElement created_Lecture_session = driver.findElement(By.xpath(OR.PLACEMENT_APPOINTMENT_CREATED_LECTURE_SESSION));
		waitForElement(driver, OR.PLACEMENT_APPOINTMENT_CREATED_LECTURE_SESSION);
		verifyElementIsPresent(driver, created_Lecture_session);
		com.zillion.qa.member.Dashboard.memberLogout(driver);



		//Member-4-LectureSignUp
		wait(driver, "1");
		Navigate.get(driver, Directory.Memberurl);
		Navigate.maximize(driver);
		wait(driver, "4");
		try
		{
			Navigate.waitTime(driver, "30");
			waitForElement(driver, OR.MEMBER_YOURMAIL);
		}
		catch(Exception e)
		{
			Navigate.refreshPage(driver);
			waitForElement(driver, OR.MEMBER_YOURMAIL);
			try
			{
				Navigate.waitTime(driver, "30");
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
			catch(Exception e1)
			{
				Navigate.refreshPage(driver);
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
		}
		//WebElement member_username = driver.findElement(By.xpath(OR.MEMBER_YOURMAIL));
		actionType(driver,member_username, "member.kulfi4@yopmail.com");
		System.out.println("Username is typed");
		//member_username.sendKeys(Keys.TAB);
		//WebElement member_password = driver.findElement(By.xpath(OR.MEMBER_YOURPASSWORD));
		jsClickByXPath(driver, OR.MEMBER_YOURPASSWORD);
		actionType(driver,member_password,"Password1");
		System.out.println("Password is typed");
		waitForElement(driver, OR.MEMBER_LOGIN_BUTTON);
		jsClickByXPath(driver, OR.MEMBER_LOGIN_BUTTON);
		System.out.println("Login button is clicked");
		Navigate.waitTime(driver, "20");
		waitForElement(driver, OR.MEMBER_DASHBOARD);
		waitForAjax(driver);
		wait(driver, "5");
		System.out.println("Member logged in successfully");
		verifyElementIsPresent(driver, viewSessionButton);
		click(viewSessionButton);
		com.zillion.qa.opsadmin.Providers.memberVerifyLectureSession(driver);
		jsClickByXPath(driver, OR.PLACEMENT_APPOINTMENT_CLOSE_BUTTON);
		Navigate.refreshPage(driver);
		//WebElement created_Lecture_session = driver.findElement(By.xpath(OR.PLACEMENT_APPOINTMENT_CREATED_LECTURE_SESSION));
		waitForElement(driver, OR.PLACEMENT_APPOINTMENT_CREATED_LECTURE_SESSION);
		verifyElementIsPresent(driver, created_Lecture_session);
		com.zillion.qa.member.Dashboard.memberLogout(driver);


		//Member-5-LectureSignUp
		wait(driver, "1");
		Navigate.get(driver, Directory.Memberurl);
		Navigate.maximize(driver);
		wait(driver, "4");
		try
		{
			Navigate.waitTime(driver, "30");
			waitForElement(driver, OR.MEMBER_YOURMAIL);
		}
		catch(Exception e)
		{
			Navigate.refreshPage(driver);
			waitForElement(driver, OR.MEMBER_YOURMAIL);
			try
			{
				Navigate.waitTime(driver, "30");
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
			catch(Exception e1)
			{
				Navigate.refreshPage(driver);
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
		}
		//WebElement member_username = driver.findElement(By.xpath(OR.MEMBER_YOURMAIL));
		actionType(driver,member_username, "member.kulfi2@yopmail.com");
		System.out.println("Username is typed");
		//member_username.sendKeys(Keys.TAB);
		//WebElement member_password = driver.findElement(By.xpath(OR.MEMBER_YOURPASSWORD));
		jsClickByXPath(driver, OR.MEMBER_YOURPASSWORD);
		actionType(driver,member_password,"Password1");
		System.out.println("Password is typed");
		waitForElement(driver, OR.MEMBER_LOGIN_BUTTON);
		jsClickByXPath(driver, OR.MEMBER_LOGIN_BUTTON);
		System.out.println("Login button is clicked");
		Navigate.waitTime(driver, "20");
		waitForElement(driver, OR.MEMBER_DASHBOARD);
		waitForAjax(driver);
		wait(driver, "5");
		System.out.println("Member logged in successfully");
		verifyElementIsPresent(driver, viewSessionButton);
		click(viewSessionButton);
		com.zillion.qa.opsadmin.Providers.memberVerifyLectureSession(driver);
		jsClickByXPath(driver, OR.PLACEMENT_APPOINTMENT_CLOSE_BUTTON);
		Navigate.refreshPage(driver);
		//WebElement created_Lecture_session = driver.findElement(By.xpath(OR.PLACEMENT_APPOINTMENT_CREATED_LECTURE_SESSION));
		waitForElement(driver, OR.PLACEMENT_APPOINTMENT_CREATED_LECTURE_SESSION);
		verifyElementIsPresent(driver, created_Lecture_session);
		com.zillion.qa.member.Dashboard.memberLogout(driver);

		//Member-6-LectureSignUp
		wait(driver, "1");
		Navigate.get(driver, Directory.Memberurl);
		Navigate.maximize(driver);
		wait(driver, "4");
		try
		{
			Navigate.waitTime(driver, "30");
			waitForElement(driver, OR.MEMBER_YOURMAIL);
		}
		catch(Exception e)
		{
			Navigate.refreshPage(driver);
			waitForElement(driver, OR.MEMBER_YOURMAIL);
			try
			{
				Navigate.waitTime(driver, "30");
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
			catch(Exception e1)
			{
				Navigate.refreshPage(driver);
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
		}
		//WebElement member_username = driver.findElement(By.xpath(OR.MEMBER_YOURMAIL));
		actionType(driver,member_username, "member.kulfi-7@yopmail.com");
		System.out.println("Username is typed");
		//member_username.sendKeys(Keys.TAB);
		//WebElement member_password = driver.findElement(By.xpath(OR.MEMBER_YOURPASSWORD));
		jsClickByXPath(driver, OR.MEMBER_YOURPASSWORD);
		actionType(driver,member_password,"Password1");
		System.out.println("Password is typed");
		waitForElement(driver, OR.MEMBER_LOGIN_BUTTON);
		jsClickByXPath(driver, OR.MEMBER_LOGIN_BUTTON);
		System.out.println("Login button is clicked");
		Navigate.waitTime(driver, "20");
		waitForElement(driver, OR.MEMBER_DASHBOARD);
		waitForAjax(driver);
		wait(driver, "5");
		System.out.println("Member logged in successfully");
		verifyElementIsPresent(driver, viewSessionButton);
		click(viewSessionButton);
		com.zillion.qa.opsadmin.Providers.memberVerifyLectureSession(driver);
		jsClickByXPath(driver, OR.PLACEMENT_APPOINTMENT_CLOSE_BUTTON);
		Navigate.refreshPage(driver);
		//WebElement created_Lecture_session = driver.findElement(By.xpath(OR.PLACEMENT_APPOINTMENT_CREATED_LECTURE_SESSION));
		waitForElement(driver, OR.PLACEMENT_APPOINTMENT_CREATED_LECTURE_SESSION);
		verifyElementIsPresent(driver, created_Lecture_session);
		com.zillion.qa.member.Dashboard.memberLogout(driver);


		//Member-7-LectureSignUp
		wait(driver, "1");
		Navigate.get(driver, Directory.Memberurl);
		Navigate.maximize(driver);
		wait(driver, "4");
		try
		{
			Navigate.waitTime(driver, "30");
			waitForElement(driver, OR.MEMBER_YOURMAIL);
		}
		catch(Exception e)
		{
			Navigate.refreshPage(driver);
			waitForElement(driver, OR.MEMBER_YOURMAIL);
			try
			{
				Navigate.waitTime(driver, "30");
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
			catch(Exception e1)
			{
				Navigate.refreshPage(driver);
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
		}
		//WebElement member_username = driver.findElement(By.xpath(OR.MEMBER_YOURMAIL));
		actionType(driver,member_username, "member.kulfi6@yopmail.com");
		System.out.println("Username is typed");
		//member_username.sendKeys(Keys.TAB);
		//WebElement member_password = driver.findElement(By.xpath(OR.MEMBER_YOURPASSWORD));
		jsClickByXPath(driver, OR.MEMBER_YOURPASSWORD);
		actionType(driver,member_password,"Password1");
		System.out.println("Password is typed");
		waitForElement(driver, OR.MEMBER_LOGIN_BUTTON);
		jsClickByXPath(driver, OR.MEMBER_LOGIN_BUTTON);
		System.out.println("Login button is clicked");
		Navigate.waitTime(driver, "20");
		waitForElement(driver, OR.MEMBER_DASHBOARD);
		waitForAjax(driver);
		wait(driver, "5");
		System.out.println("Member logged in successfully");
		verifyElementIsPresent(driver, viewSessionButton);
		click(viewSessionButton);
		com.zillion.qa.opsadmin.Providers.memberVerifyLectureSession(driver);
		jsClickByXPath(driver, OR.PLACEMENT_APPOINTMENT_CLOSE_BUTTON);
		Navigate.refreshPage(driver);
		//WebElement created_Lecture_session = driver.findElement(By.xpath(OR.PLACEMENT_APPOINTMENT_CREATED_LECTURE_SESSION));
		waitForElement(driver, OR.PLACEMENT_APPOINTMENT_CREATED_LECTURE_SESSION);
		verifyElementIsPresent(driver, created_Lecture_session);
		com.zillion.qa.member.Dashboard.memberLogout(driver);


		//Member-8-LectureSignUp
		wait(driver, "1");
		Navigate.get(driver, Directory.Memberurl);
		Navigate.maximize(driver);
		wait(driver, "4");
		try
		{
			Navigate.waitTime(driver, "30");
			waitForElement(driver, OR.MEMBER_YOURMAIL);
		}
		catch(Exception e)
		{
			Navigate.refreshPage(driver);
			waitForElement(driver, OR.MEMBER_YOURMAIL);
			try
			{
				Navigate.waitTime(driver, "30");
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
			catch(Exception e1)
			{
				Navigate.refreshPage(driver);
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
		}
		//WebElement member_username = driver.findElement(By.xpath(OR.MEMBER_YOURMAIL));
		actionType(driver,member_username, "member.kulfi5@yopmail.com");
		System.out.println("Username is typed");
		//member_username.sendKeys(Keys.TAB);
		//WebElement member_password = driver.findElement(By.xpath(OR.MEMBER_YOURPASSWORD));
		jsClickByXPath(driver, OR.MEMBER_YOURPASSWORD);
		actionType(driver,member_password,"Password1");
		System.out.println("Password is typed");
		waitForElement(driver, OR.MEMBER_LOGIN_BUTTON);
		jsClickByXPath(driver, OR.MEMBER_LOGIN_BUTTON);
		System.out.println("Login button is clicked");
		Navigate.waitTime(driver, "20");
		waitForElement(driver, OR.MEMBER_DASHBOARD);
		waitForAjax(driver);
		wait(driver, "5");
		System.out.println("Member logged in successfully");
		verifyElementIsPresent(driver, viewSessionButton);
		click(viewSessionButton);
		com.zillion.qa.opsadmin.Providers.memberVerifyLectureSession(driver);
		jsClickByXPath(driver, OR.PLACEMENT_APPOINTMENT_CLOSE_BUTTON);
		Navigate.refreshPage(driver);
		//WebElement created_Lecture_session = driver.findElement(By.xpath(OR.PLACEMENT_APPOINTMENT_CREATED_LECTURE_SESSION));
		waitForElement(driver, OR.PLACEMENT_APPOINTMENT_CREATED_LECTURE_SESSION);
		verifyElementIsPresent(driver, created_Lecture_session);
		com.zillion.qa.member.Dashboard.memberLogout(driver);


		//Member-9-LectureSignUp
		wait(driver, "1");
		Navigate.get(driver, Directory.Memberurl);
		Navigate.maximize(driver);
		wait(driver, "4");
		try
		{
			Navigate.waitTime(driver, "30");
			waitForElement(driver, OR.MEMBER_YOURMAIL);
		}
		catch(Exception e)
		{
			Navigate.refreshPage(driver);
			waitForElement(driver, OR.MEMBER_YOURMAIL);
			try
			{
				Navigate.waitTime(driver, "30");
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
			catch(Exception e1)
			{
				Navigate.refreshPage(driver);
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
		}
		//WebElement member_username = driver.findElement(By.xpath(OR.MEMBER_YOURMAIL));
		actionType(driver,member_username, "member.kulfi4.1@yopmail.com");
		System.out.println("Username is typed");
		//member_username.sendKeys(Keys.TAB);
		//WebElement member_password = driver.findElement(By.xpath(OR.MEMBER_YOURPASSWORD));
		jsClickByXPath(driver, OR.MEMBER_YOURPASSWORD);
		actionType(driver,member_password,"Password1");
		System.out.println("Password is typed");
		waitForElement(driver, OR.MEMBER_LOGIN_BUTTON);
		jsClickByXPath(driver, OR.MEMBER_LOGIN_BUTTON);
		System.out.println("Login button is clicked");
		Navigate.waitTime(driver, "20");
		waitForElement(driver, OR.MEMBER_DASHBOARD);
		waitForAjax(driver);
		wait(driver, "5");
		System.out.println("Member logged in successfully");
		verifyElementIsPresent(driver, viewSessionButton);
		click(viewSessionButton);
		com.zillion.qa.opsadmin.Providers.memberVerifyLectureSession(driver);
		jsClickByXPath(driver, OR.PLACEMENT_APPOINTMENT_CLOSE_BUTTON);
		Navigate.refreshPage(driver);
		//WebElement created_Lecture_session = driver.findElement(By.xpath(OR.PLACEMENT_APPOINTMENT_CREATED_LECTURE_SESSION));
		waitForElement(driver, OR.PLACEMENT_APPOINTMENT_CREATED_LECTURE_SESSION);
		verifyElementIsPresent(driver, created_Lecture_session);
		com.zillion.qa.member.Dashboard.memberLogout(driver);

		//Member-10-LectureSignUp
		wait(driver, "1");
		Navigate.get(driver, Directory.Memberurl);
		Navigate.maximize(driver);
		wait(driver, "4");
		try
		{
			Navigate.waitTime(driver, "30");
			waitForElement(driver, OR.MEMBER_YOURMAIL);
		}
		catch(Exception e)
		{
			Navigate.refreshPage(driver);
			waitForElement(driver, OR.MEMBER_YOURMAIL);
			try
			{
				Navigate.waitTime(driver, "30");
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
			catch(Exception e1)
			{
				Navigate.refreshPage(driver);
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
		}
		//WebElement member_username = driver.findElement(By.xpath(OR.MEMBER_YOURMAIL));
		actionType(driver,member_username, "member.kulfi-4@yopmail.com");
		System.out.println("Username is typed");
		//member_username.sendKeys(Keys.TAB);
		//WebElement member_password = driver.findElement(By.xpath(OR.MEMBER_YOURPASSWORD));
		jsClickByXPath(driver, OR.MEMBER_YOURPASSWORD);
		actionType(driver,member_password,"Password1");
		System.out.println("Password is typed");
		waitForElement(driver, OR.MEMBER_LOGIN_BUTTON);
		jsClickByXPath(driver, OR.MEMBER_LOGIN_BUTTON);
		System.out.println("Login button is clicked");
		Navigate.waitTime(driver, "20");
		waitForElement(driver, OR.MEMBER_DASHBOARD);
		waitForAjax(driver);
		wait(driver, "5");
		System.out.println("Member logged in successfully");
		verifyElementIsPresent(driver, viewSessionButton);
		click(viewSessionButton);
		com.zillion.qa.opsadmin.Providers.memberVerifyLectureSession(driver);
		jsClickByXPath(driver, OR.PLACEMENT_APPOINTMENT_CLOSE_BUTTON);
		Navigate.refreshPage(driver);
		//WebElement created_Lecture_session = driver.findElement(By.xpath(OR.PLACEMENT_APPOINTMENT_CREATED_LECTURE_SESSION));
		waitForElement(driver, OR.PLACEMENT_APPOINTMENT_CREATED_LECTURE_SESSION);
		verifyElementIsPresent(driver, created_Lecture_session);
		com.zillion.qa.member.Dashboard.memberLogout(driver);

		//Member-11-LectureSignUp
		wait(driver, "1");
		Navigate.get(driver, Directory.Memberurl);
		Navigate.maximize(driver);
		wait(driver, "4");
		try
		{
			Navigate.waitTime(driver, "30");
			waitForElement(driver, OR.MEMBER_YOURMAIL);
		}
		catch(Exception e)
		{
			Navigate.refreshPage(driver);
			waitForElement(driver, OR.MEMBER_YOURMAIL);
			try
			{
				Navigate.waitTime(driver, "30");
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
			catch(Exception e1)
			{
				Navigate.refreshPage(driver);
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
		}
		//WebElement member_username = driver.findElement(By.xpath(OR.MEMBER_YOURMAIL));
		actionType(driver,member_username, "member.kulfi-2.1@yopmail.com");
		System.out.println("Username is typed");
		//member_username.sendKeys(Keys.TAB);
		//WebElement member_password = driver.findElement(By.xpath(OR.MEMBER_YOURPASSWORD));
		jsClickByXPath(driver, OR.MEMBER_YOURPASSWORD);
		actionType(driver,member_password,"Password1");
		System.out.println("Password is typed");
		waitForElement(driver, OR.MEMBER_LOGIN_BUTTON);
		jsClickByXPath(driver, OR.MEMBER_LOGIN_BUTTON);
		System.out.println("Login button is clicked");
		Navigate.waitTime(driver, "20");
		waitForElement(driver, OR.MEMBER_DASHBOARD);
		waitForAjax(driver);
		wait(driver, "5");
		System.out.println("Member logged in successfully");
		verifyElementIsPresent(driver, viewSessionButton);
		click(viewSessionButton);
		com.zillion.qa.opsadmin.Providers.memberVerifyLectureSession(driver);
		jsClickByXPath(driver, OR.PLACEMENT_APPOINTMENT_CLOSE_BUTTON);
		Navigate.refreshPage(driver);
		//WebElement created_Lecture_session = driver.findElement(By.xpath(OR.PLACEMENT_APPOINTMENT_CREATED_LECTURE_SESSION));
		waitForElement(driver, OR.PLACEMENT_APPOINTMENT_CREATED_LECTURE_SESSION);
		verifyElementIsPresent(driver, created_Lecture_session);
		com.zillion.qa.member.Dashboard.memberLogout(driver);

		//Member-12-LectureSignUp
		wait(driver, "1");
		Navigate.get(driver, Directory.Memberurl);
		Navigate.maximize(driver);
		wait(driver, "4");
		try
		{
			Navigate.waitTime(driver, "30");
			waitForElement(driver, OR.MEMBER_YOURMAIL);
		}
		catch(Exception e)
		{
			Navigate.refreshPage(driver);
			waitForElement(driver, OR.MEMBER_YOURMAIL);
			try
			{
				Navigate.waitTime(driver, "30");
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
			catch(Exception e1)
			{
				Navigate.refreshPage(driver);
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
		}
		//WebElement member_username = driver.findElement(By.xpath(OR.MEMBER_YOURMAIL));
		actionType(driver,member_username, "member.kulfi-11@yopmail.com");
		System.out.println("Username is typed");
		//member_username.sendKeys(Keys.TAB);
		//WebElement member_password = driver.findElement(By.xpath(OR.MEMBER_YOURPASSWORD));
		jsClickByXPath(driver, OR.MEMBER_YOURPASSWORD);
		actionType(driver,member_password,"Password1");
		System.out.println("Password is typed");
		waitForElement(driver, OR.MEMBER_LOGIN_BUTTON);
		jsClickByXPath(driver, OR.MEMBER_LOGIN_BUTTON);
		System.out.println("Login button is clicked");
		Navigate.waitTime(driver, "20");
		waitForElement(driver, OR.MEMBER_DASHBOARD);
		waitForAjax(driver);
		wait(driver, "5");
		System.out.println("Member logged in successfully");
		verifyElementIsPresent(driver, viewSessionButton);
		click(viewSessionButton);
		com.zillion.qa.opsadmin.Providers.memberVerifyLectureSession(driver);
		jsClickByXPath(driver, OR.PLACEMENT_APPOINTMENT_CLOSE_BUTTON);
		Navigate.refreshPage(driver);
		//WebElement created_Lecture_session = driver.findElement(By.xpath(OR.PLACEMENT_APPOINTMENT_CREATED_LECTURE_SESSION));
		waitForElement(driver, OR.PLACEMENT_APPOINTMENT_CREATED_LECTURE_SESSION);
		verifyElementIsPresent(driver, created_Lecture_session);
		com.zillion.qa.member.Dashboard.memberLogout(driver);
		return ElementWait;

		 */
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   29/FEB/16
	 * Modified Date:
	 * Description :   Create a common method to schedule 1 on 1 session in member portal
	 * Ticket ID :
	 * Required Inputs :  Username and Password
	 *
	 */

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   29/FEB/16
	 * Modified Date:
	 * Description :   Create a common method to schedule 1 on 1 session in member portal
	 * Ticket ID :
	 * Required Inputs :  Username and Password
	 *
	 */
	public static String Sessiontime="";
	public static void dateSession(WebDriver driver)
	{
		//	try
		//		{
		String CurrentDateOR = "//div[@id='timeSelection']/div	/div/div/div/span";
		WebElement CurrentDate = driver.findElement(By.xpath(CurrentDateOR));
		String date2 = CurrentDate.getText();
		String CurrentDateEvents = "//span[text()='"+date2+"']/parent::div/following-sibling::div/button[@ng-disabled='false']";
		System.out.println("Label Name : "+CurrentDate);
		WebElement Currentevents = driver.findElement(By.xpath(CurrentDateEvents));
		click( Currentevents );
		wait(driver, "3");
		WebElement CurrenteventsDrop = driver.findElement(By.xpath("//ul[@class='dropdown-menu schedule-default-menu']/li/a"));
		Sessiontime=CurrenteventsDrop.getText();
		wait(driver, "2");
		actionClick( driver, CurrenteventsDrop );
		wait(driver, "5");
	}
	//		catch(Exception e)
	//		{
	//			String CurrentDateOR = "//div[@id='timeSelection']/div[2]/div/div/div/span//following::div[5]/following::div/div/span";
	//			WebElement CurrentDate = driver.findElement(By.xpath(CurrentDateOR));
	//			String date2 = CurrentDate.getText();
	//			String CurrentDateEvents = "//span[text()='"+date2+"']/parent::div/following-sibling::div/button[@ng-disabled='false']";
	//			System.out.println("Label Name : "+CurrentDate);
	//			WebElement Currentevents = driver.findElement(By.xpath(CurrentDateEvents));
	//			click( Currentevents );
	//			wait(driver, "3");
	//			WebElement CurrenteventsDrop = driver.findElement(By.xpath("//ul[@class='dropdown-menu schedule-default-menu']/li/a"));
	//			actionClick( driver, CurrenteventsDrop );
	//			wait(driver, "5");
	//		}
	//	}




	/**
	 * Name :     ABINAYA.P
	 * Created Date:   29/FEB/16
	 * Modified Date:
	 * Description :   Create a common method to append url in member portal
	 * Ticket ID :
	 * Required Inputs :  Username and Password
	 *
	 */

	public static void appendTextToURL(WebDriver driver)
	{
		Navigate.get(driver, Directory.AppendURL);

	}

	/**
	 * Name :     VIGNESHRAJ
	 * Created Date:   29/FEB/16
	 * Modified Date:
	 * Description :   Create a common method to append url in Coach portal
	 * Ticket ID :
	 * Required Inputs :  Username and Password
	 *
	 */

	public static void coachAppendTextToURL(WebDriver driver)
	{
		String currentURL= getCurrentURL(driver);
		Navigate.get(driver, currentURL+"?attendnow");

	}

	/**
=======	public static void coachAppendTextToURL(WebDriver driver)
	{
		Navigate.getHttpUrl(driver, Directory.CoachAppendURL);

	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   10/Mar/16
	 * Modified Date:
	 * Description :   Create a common method to retrieve program id from account program table
	 * Ticket ID :
	 * Testcase Sheet:
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String programid1 = "";
	public static String retrieveProgramId1OfTheMember(WebDriver driver,String accountId) throws ClassNotFoundException, SQLException
	{
		String port = Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("SELECT ID, MAST_PROGRAM_ID, TO_CHAR(START_DT_TIME, 'YYYY-MM-DD HH24:MI') START_DT_TIME, TO_CHAR(START_DT, 'YYYY-MM-DD HH24:MI') START_DT,  TO_CHAR(END_DT_TIME, 'YYYY-MM-DD HH24:MI') END_DT_TIME, TO_CHAR(END_DT, 'YYYY-MM-DD HH24:MI') END_DT, STATUS FROM ACCOUNT_PROGRAM WHERE ACCOUNT_ID='"+accountId+"'");
		System.out.println("query executed");
		if(rs.next())
		{
			programid1 = rs.getString("MAST_PROGRAM_ID");
			System.out.println(programid1+" : programid1");
		}
		else
		{
			System.out.println("No results found from the sql execution");
		}

		return programid1;
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   10/Mar/16
	 * Modified Date:
	 * Description :   Create a common method to retrieve program id from account program membership table
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String programid2 = "";
	public static String retrieveProgramId2OfTheMember(WebDriver driver,String accountId) throws ClassNotFoundException, SQLException
	{
		String port = "1521";
		String database_name= "Kulfi";
		String user = "PROMETHEUS_DB_SORBET";
		String pass = "PrOm_SorBeT_DB";
		String hostname ="kulfi.c2nmjbfxq71p.us-east-1.rds.amazonaws.com";
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("SELECT ID, MAST_PROGRAM_ID, TO_CHAR(START_DT_TIME, 'YYYY-MM-DD HH24:MI') START_DT_TIME, TO_CHAR(START_DT, 'YYYY-MM-DD HH24:MI') START_DT, TO_CHAR(END_DT_TIME, 'YYYY-MM-DD HH24:MI') END_DT_TIME, TO_CHAR(END_DT, 'YYYY-MM-DD HH24:MI') END_DT FROM ACCOUNT_PROGRAM_MEMBERSHIP WHERE ACCOUNT_ID='"+accountId+"'");
		System.out.println("query executed");
		if(rs.next())
		{
			programid2 = rs.getString("MAST_PROGRAM_ID");
			System.out.println(programid2+" : programid2");
		}
		else
		{
			System.out.println("No results found from the sql execution");
		}

		return programid2;
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   10/Mar/16
	 * Modified Date:
	 * Description :   Create a common method to update startdate and enddate in account program table
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */

	public static void updateDateinAccountProgram(WebDriver driver,String accountId,String pgmId) throws ClassNotFoundException, SQLException
	{
		String port = "1521";
		String database_name= "Kulfi";
		String user = "PROMETHEUS_DB_SORBET";
		String pass = "PrOm_SorBeT_DB";
		String hostname ="kulfi.c2nmjbfxq71p.us-east-1.rds.amazonaws.com";
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("UPDATE ACCOUNT_PROGRAM SET START_DT_TIME=START_DT_TIME + INTERVAL '-1' DAY, START_DT=START_DT + INTERVAL '-1' DAY, END_DT_TIME=END_DT_TIME + INTERVAL '-1' DAY, END_DT=END_DT + INTERVAL '-1' DAY WHERE ACCOUNT_ID='"+accountId+"' AND MAST_PROGRAM_ID='"+pgmId+"'");
		System.out.println("query executed");
		if(rs.next())
		{
			programid2 = rs.getString("MAST_PROGRAM_ID");
			System.out.println(programid2+" : programid2");
		}
		else
		{
			System.out.println("No results found from the sql execution");
		}
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   10/Mar/16
	 * Modified Date:
	 * Description :   Create a common method to update startdate and enddate in account program membershiptable
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */

	public static void updateDateinAccountProgramMembership(WebDriver driver,String accountId,String pgmId) throws ClassNotFoundException, SQLException
	{
		String port = "1521";
		String database_name= "Kulfi";
		String user = "PROMETHEUS_DB_SORBET";
		String pass = "PrOm_SorBeT_DB";
		String hostname ="kulfi.c2nmjbfxq71p.us-east-1.rds.amazonaws.com";
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("UPDATE ACCOUNT_PROGRAM_MEMBERSHIP SET START_DT_TIME=START_DT_TIME + INTERVAL '-1' DAY, START_DT=START_DT + INTERVAL '-1' DAY, END_DT_TIME=END_DT_TIME + INTERVAL '-1' DAY, END_DT=END_DT + INTERVAL '-1' DAY WHERE ACCOUNT_ID='"+accountId+"' AND MAST_PROGRAM_ID='"+pgmId+"'");
		System.out.println("query executed");
		if(rs.next())
		{
			programid2 = rs.getString("MAST_PROGRAM_ID");
			System.out.println(programid2+" : programid2");
		}
		else
		{
			System.out.println("No results found from the sql execution");
		}
	}
	/**
	 * Name :    Suresh V
	 * Created Date:   17/Mar/16
	 */
	public static void launchMemberUrl(WebDriver driver)
	{
		try
		{
			Navigate.navigateUrl(driver, Directory.Memberurl);
			Manipulation.wait(driver, "2");
		}
		catch(Exception e)
		{
			com.zillion.qa.commands.Manipulation.browserURLSecurityException(driver);
		}

	}
	/**
	 * Name :    Suresh V
	 * Created Date:   18/Mar/16
	 */
	public static void getHttpUrlMember(WebDriver driver)
	{
		driver.get(Directory.Memberurl);
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   22/Mar/16
	 * Modified Date:
	 * Description :   Create a common method to delete all the mails in guerrilla
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void deleteAllMails(WebDriver driver)
	{
		int SelectAllCheckbox = driver.findElements(By.xpath("//tbody[@id='email_list']/tr[*]/td/input[@type='checkbox']")).size();
		for(int i=1; i<=SelectAllCheckbox ;i++)
		{
			WebElement SelectAllCheckbox1 = driver.findElement(By.xpath("//tbody[@id='email_list']/tr["+i+"]/td/input[@type='checkbox']"));
			click(SelectAllCheckbox1);
			wait(driver, "2");
		}
		wait(driver, "2");
		WebElement Delete = driver.findElement(By.xpath("//input[@id='del_button']"));
		click(Delete);
		wait(driver, "3");
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   22/Mar/16
	 * Modified Date:
	 * Description :   Create a common method to get entries from content library
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String retrieveCotentCount(WebDriver driver)
	{
		String[] total= new String[2];
		try
		{
			String text=driver.findElement(By.xpath("//*[@id='libraryToolbarRow']/div/div/div[1]/span")).getText();
			total=text.split("entries in");
		}
		catch(Exception e)
		{
			total[0]="0";
		}
		return total[0];
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   22/Mar/16
	 * Modified Date:
	 * Description :   Create a common method to count number of counts in content library
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */

	public static String calculateDisplayedContentsCount(WebDriver driver)
	{
		String total="";
		try
		{
			int count=driver.findElements(By.xpath("//div[@class='content-holder col-xs-12 col-sm-6 col-md-4 col-xl-3 ng-scope'][*]")).size();
			total=""+count;
		}
		catch(Exception e)
		{
			total="0";
		}
		return total;
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   15/Apr/16
	 * Modified Date:
	 * Description :   Create a common method to retrieve timeslot to execute API automation using jenkins
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String timeslot="";
	public static String jenkinDbTimeslot(WebDriver driver ,String time1) throws Exception
	{
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd ");
		final Calendar cal = Calendar.getInstance();
		System.out.println(cal.getTime());
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
		String date= dateFormat.format(cal.getTime());
		System.out.println(date);
		String time=time1.replaceAll(",", ":");
		System.out.println("time"+time);
		String port = Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("SELECT PROV.ID, PROV.EMAIL,REPLACE(TO_CHAR(START_DT_TIME,'YYYY-MM-DD HH24:MI:SS'),' ','T')|| '.000Z' TZSLOT_CUST,HOST_ID,SESSION_STATUS, EVENT_TYPE,START_DT_TIME, END_DT_TIME FROM CALENDAR_EVENT EVT, PROVIDER PROV WHERE EVT.HOST_ID=PROV.ID AND EVT.HOST_ID IN (SELECT PROVIDER_ID FROM MP_PROVIDER_APPROVED WHERE SESSION_TYPE_ID IN ('13')) AND ((START_DT_TIME<=TO_DATE('"+date+time+"','YYYY-MM-DD hh24:mi:ss') AND END_DT_TIME>TO_DATE('"+date+time+"','YYYY-MM-DD hh24:mi:ss') AND EVENT_TYPE='Available')) AND EVT.HOST_ID NOT IN (SELECT HOST_ID FROM CALENDAR_EVENT EVT2 WHERE (EVT2.START_DT_TIME<=TO_DATE('"+date+time+"','YYYY-MM-DD hh24:mi:ss') AND EVT2.END_DT_TIME>=TO_DATE('"+date+time+"','YYYY-MM-DD hh24:mi:ss') AND EVT2.EVENT_TYPE IN ('Session','Unavailable')))");
		System.out.println("query executed");
		while(rs.next())
		{
			timeslot=rs.getString("TZSLOT_CUST");
			System.out.println("Timeslot "+timeslot);
		}
		return (timeslot);
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   15/Apr/16
	 * Modified Date:
	 * Description :   Create a common method to manipulate startdate to execute API automation using jenkins
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String jenkinStartdate(WebDriver driver)
	{
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
		final Calendar cal = Calendar.getInstance();
		System.out.println(cal.getTime());
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
		String date= dateFormat.format(cal.getTime());
		System.out.println(date);
		return date;

	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   14/Dec/15
	 * Modified Date:   24/Feb/2016
	 * Description :Guerrilla mail reload
	 * Ticket ID :
	 * Required Inputs :
	 *
	 */
	public static void reloadTilMailGetsLoaded(WebDriver driver,String normalXpath)
	{
		try{
			if(driver.findElement( By.xpath( normalXpath ) ).isDisplayed())
			{
				waitForElement(driver, normalXpath);
			}
			else
			{
				driver.navigate().refresh();
				wait( driver, "3" );
				if(driver.findElement( By.xpath( normalXpath ) ).isDisplayed())
				{
					waitForElement(driver, normalXpath);
				}
				else
				{
					driver.navigate().refresh();
					wait( driver, "3" );
					waitForElement(driver, normalXpath);
				}
			}
		}
		catch(Exception e)
		{
			driver.navigate().refresh();
			wait( driver, "3" );
			waitForElement(driver, normalXpath);
		}
	}

	/**
	 * Name :   Vinothkumar.M
	 * Created Date: 3/May/2016
	 * Modified Date:
	 * Description :Guerrilla mail url
	 * Ticket ID :
	 * Required Inputs :
	 *
	 */
	public static void launchGuerrillamailUrl(WebDriver driver)
	{

		driver.navigate().to(Directory.Guerrillamailurl);
	}


	/**
	 * Name :   VigneshRaj.M
	 * Created Date: 3/May/2016
	 * Modified Date:
	 * Description : Session Widget display
	 * Ticket ID :
	 * Required Inputs :
	 *
	 */

	public static void sessionWidgetDisplay(WebDriver driver)
	{
		try
		{
			WebElement viewSessionText = driver.findElement(By.xpath(OR.SESSION_WIDGET_VIEW_SESSION_BUTTON_TEXT));
			WebElement viewSessionButton = driver.findElement(By.xpath(OR.SESSION_WIDGET_VIEW_SESSION_BUTTON));
			verifyElementIsPresent(driver, viewSessionText);
			verifyElementIsPresent(driver, viewSessionButton);

		}
		catch(Exception e)
		{
			WebElement scheduleButtonAvailable = driver.findElement(By.xpath(OR.SCHEDULE_BUTTON_AVAILABLE));
			verifyElementIsPresent(driver, scheduleButtonAvailable);
			WebElement viewSessionText = driver.findElement(By.xpath(OR.SESSION_WIDGET_VIEW_SESSION_BUTTON_TEXT));
			WebElement viewSessionButton = driver.findElement(By.xpath(OR.SESSION_WIDGET_VIEW_SESSION_BUTTON));
			WebElement oneOnOneYOurNextSessionText = driver.findElement(By.xpath(OR.SESSION_WIDGET_1ON1_YOUR_NEXT_SESSION));
			verifyElementIsPresent(driver, viewSessionText);
			verifyElementIsPresent(driver, viewSessionButton);
			verifyElementIsPresent(driver, oneOnOneYOurNextSessionText);
		}
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   10/Mar/16
	 * Modified Date:
	 * Description :   Create a common method to retrieve program id from account program table
	 * Ticket ID :
	 * Testcase Sheet:
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */

	public static String getMemberWeightUsingAcntMeasuremntTable(WebDriver driver,String accountId) throws ClassNotFoundException, SQLException
	{
		String port = Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select * from ACCOUNT_MEASUREMENT where account_id = '"+accountId+"'");
		System.out.println("query executed");
		System.out.println("accountId: "+accountId);
		String weight1="";
		while(rs.next())
		{
			weight1 = rs.getString("weight");
			weight1=weight1+".0";
			System.out.println("Weight:"+weight1+" is retrieved from the table");

		}


		return weight1;
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   07/JUN/16
	 * Modified Date:
	 * Description : Create a common method to Login for Orbera member with retrieve Email and Password
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ParseException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */

	public static String orberaMemberLoginUsingDB(WebDriver driver,String mail) throws ClassNotFoundException, ParseException, SQLException
	{
		String returnObj = "";
		if(mail.equals(","))
		{
			returnObj = "No Data in the Table";

		}
		else
		{
			wait(driver, "5");
			Navigate.get(driver, Directory.Memberurl);
			wait(driver, "5");
			Navigate.maximize(driver);
			if(Platform.BROWSER_NAME.equals("Unknown"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
			}
			Navigate.maximize(driver);
			wait(driver, "1");
			Navigate.get(driver, Directory.Memberurl);
			Navigate.maximize(driver);
			wait(driver, "10");
			try
			{
				Navigate.waitTime(driver, "30");
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
			catch(Exception e)
			{
				Navigate.refreshPage(driver);
				waitForElement(driver, OR.MEMBER_YOURMAIL);
				try
				{
					Navigate.waitTime(driver, "30");
					waitForElement(driver, OR.MEMBER_YOURMAIL);
				}
				catch(Exception e1)
				{
					Navigate.refreshPage(driver);
					waitForElement(driver, OR.MEMBER_YOURMAIL);
				}
			}
			WebElement member_username = driver.findElement(By.xpath(OR.MEMBER_YOURMAIL));
			actionType(driver,member_username, mail);
			System.out.println("Username is typed");
			//member_username.sendKeys(Keys.TAB);
			WebElement member_password = driver.findElement(By.xpath(OR.MEMBER_YOURPASSWORD));
			jsClickByXPath(driver, OR.MEMBER_YOURPASSWORD);
			actionType(driver,member_password,Directory.Memberpassword2);
			System.out.println("Password is typed");
			waitForElement(driver, OR.MEMBER_LOGIN_BUTTON);
			jsClickByXPath(driver, OR.MEMBER_LOGIN_BUTTON);
			System.out.println("Login button is clicked");
			Navigate.waitTime(driver, "20");
			waitForElement(driver, OR.MEMBER_DASHBOARD);
			waitForAjax(driver);
			wait(driver, "10");
			System.out.println("Member logged in successfully");
			returnObj="";
		}
		return returnObj;
	}
	/**
	 * Name :    Abinaya.P
	 * Created Date:   08/JUN/16
	 * Modified Date:
	 * Description : Create a common method to retrieve orbera member which has 1 on 1 schedule button
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ParseException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */

	public static String getOrberaMemberFor1on1Session(WebDriver driver) throws ClassNotFoundException, ParseException, SQLException
	{
		String port = Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("SELECT ACNT.EMAIL Member_Email, PROV.EMAIL Coach_Email FROM SUMMARY_ACCOUNT_TODATE SMRY, PROVIDER PROV, ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID AND SMRY.ACCOUNT_ID=ACNT.ID AND ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ID IN (SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_STATUS!='Completed' AND SESSION_TYPE_ID='30100AA52BEAEE0EE1' AND MP_INTERVAL_NUMBER > (SELECT MAX(MP_INTERVAL_NUMBER) FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_TYPE_ID='30100AA52BEAEE0EE1' AND SCHEDULED_DT<SYSDATE AND SCHEDULED_DT>SYSDATE + INTERVAL '-1' DAY))) AND (SUMMARY_EXTENSION LIKE '%medicalProcedureMilestone1Answer%YES, I HAD IT%' AND ACNT.EMAIL not like '%api%')");
		System.out.println("query executed");
		String member_mail="";
		while(rs.next())
		{
			member_mail = rs.getString("Member_Email");
			System.out.println("Member mail:"+member_mail+" is retrieved from the table");

		}
		return member_mail;
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   24/Dec/15
	 * Modified Date:    30/Dec/15
	 * Description :   Create a common method to delete photos in Member user
	 * Ticket ID :
	 * Required Inputs :
	 * @throws IOException
	 * @throws AWTException
	 *
	 */
	public static void deletePhotos(WebDriver driver) throws IOException
	{
		//Navigate.refreshPage(driver);
		wait(driver, "2");
		waitForElement(driver, MEMBER_DASHBOARD);
		jsClickByXPath(driver,MEMBER_DASHBOARD);
		wait(driver, "2");
		System.out.println("Clicked on Dashboard");
		waitForAjax(driver);
		waitForElement(driver, MEMBER_DASH_UPLOADPHOTO);
		jsClickByXPath(driver, MEMBER_DASH_UPLOADPHOTO);
		System.out.println("Clicked on Upload Food");
		wait(driver, "2");
		waitForAjax(driver);
		WebElement image=driver.findElement(By.xpath(MEMBER_PHOTO1));
		clickAndHold(driver, image);
		jsClickByXPath(driver, "//button[contains(.,'Delete Photo')]");
		System.out.println("photo is deleted");
		waitForAjax(driver);
		wait(driver, "2");

	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   15/Apr/16
	 * Modified Date:
	 * Description :   Create a common method to retrieve timeslot to execute API automation using jenkins
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String timeslot1="";
	public static String jenkinDbTimeslotForClass(WebDriver driver ,String time1) throws Exception
	{
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
		final Calendar cal = Calendar.getInstance();
		System.out.println(cal.getTime());
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
		String date= dateFormat.format(cal.getTime());
		System.out.println(date);
		String time=time1.replaceAll(",", ":");
		System.out.println("time"+time);
		String port = Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("SELECT PROV.ID, PROV.EMAIL, CAST( START_DT_TIME AS TIMESTAMP WITH TIME ZONE) AT TIME ZONE 'AMERICA/LOS_ANGELES' START_DT_LA, CAST( END_DT_TIME AS TIMESTAMP WITH TIME ZONE) AT TIME ZONE 'AMERICA/LOS_ANGELES' END_DT_LA, HOST_ID, SESSION_STATUS, EVENT_TYPE ,START_DT_TIME, END_DT_TIME FROM CALENDAR_EVENT EVT, PROVIDER PROV WHERE EVT.HOST_ID=PROV.ID AND EVT.HOST_ID IN (SELECT PROVIDER_ID FROM MP_PROVIDER_APPROVED WHERE SESSION_TYPE_ID IN ('01','05')) AND (( START_DT_TIME<=TO_DATE('"+date+time+"','YYYY-MM-DD hh24:mi:ss') AND END_DT_TIME>TO_DATE('"+date+time+"','YYYY-MM-DD hh24:mi:ss') AND EVENT_TYPE='Available' ) OR ( START_DT_TIME<=TO_DATE('"+date+time+"','YYYY-MM-DD hh24:mi:ss') + INTERVAL '7' DAY AND END_DT_TIME>TO_DATE('"+date+time+"','YYYY-MM-DD hh24:mi:ss') + INTERVAL '7' DAY AND EVENT_TYPE='Available')) AND EVT.HOST_ID NOT IN ( SELECT HOST_ID FROM CALENDAR_EVENT EVT2 WHERE ( EVT2.START_DT_TIME<=TO_DATE('"+date+time+"','YYYY-MM-DD hh24:mi:ss') AND EVT2.END_DT_TIME>=TO_DATE('"+date+time+"','YYYY-MM-DD hh24:mi:ss') AND EVT2.EVENT_TYPE IN ('Session','Unavailable')) OR ( EVT2.START_DT_TIME<=TO_DATE('"+date+time+"','YYYY-MM-DD hh24:mi:ss') + INTERVAL '7' DAY AND EVT2.END_DT_TIME>=TO_DATE('"+date+time+"','YYYY-MM-DD hh24:mi:ss') + INTERVAL '7' DAYAND EVT2.EVENT_TYPE IN ('Session','Unavailable')))");
		System.out.println("query executed");
		while(rs.next())
		{
			timeslot1=rs.getString("START_DT_LA");
			System.out.println("Timeslot "+timeslot1);
		}
		return (timeslot1);
	}
	/**
	 * Name :     Abinaya.P
	 * Created Date:   16/02/2016
	 * Modified Date:
	 * Description :   Create a common method to verify calender add for schedule/Reschedule/Cancel Session from member portal
	 * Testcase Sheet:
	 * Ticket ID :
	 * Required Inputs :
	 * @throws AWTException
	 * @throws FindFailed
	 *
	 */

	public static void verifyAddCalenderForSchedule(WebDriver driver, String inputData) throws AWTException, FindFailed {
		String[] inputData1 = inputData.split(",");
		String gmailUsernamedata=inputData1[0];
		String gmailPassworddata=inputData1[1];
		String yahooUsernamedata=inputData1[2];
		String yahooPassworddata=inputData1[3];
		wait(driver, "5");
		WebElement  memberAddCalender= driver.findElement(By.xpath(OR.MEMBER_ADDTO_CALENDER));
		waitForElement(driver, MEMBER_ADDTO_CALENDER);
		jsClickByXPath(driver, MEMBER_ADDTO_CALENDER);
		WebElement  googleCalender= driver.findElement(By.xpath(OR.MEMBER_GOOGLE_CALENDER));
		WebElement  yahooCalender= driver.findElement(By.xpath(OR.MEMBER_YAHOO_CALENDER));
		WebElement  icalCalender= driver.findElement(By.xpath(OR.MEMBER_ICAL_CALENDER));
		WebElement  outlookCalender= driver.findElement(By.xpath(OR.MEMBER_OUTLOOK_CALENDER));
		verifyElementIsPresent(driver, googleCalender);
		verifyElementIsPresent(driver, yahooCalender);
		verifyElementIsPresent(driver, icalCalender);
		verifyElementIsPresent(driver, outlookCalender);
		getWindow(driver, googleCalender);
		waitForElement(driver, GMAIL_USERNAME);
		WebElement gmailUsername = driver.findElement(By.xpath(OR.GMAIL_USERNAME));
		actionType(driver,gmailUsername ,gmailUsernamedata);
		jsClickByXPath(driver, GMAIL_STAY_SIGIN);
		jsClickByXPath(driver, GMAIL_NEXT);
		wait(driver, "2");
		waitForElement(driver, GMAIL_PASSWORD);
		WebElement gmailPassword = driver.findElement(By.xpath(OR.GMAIL_PASSWORD));
		actionType(driver,gmailPassword , gmailPassworddata);
		jsClickByXPath(driver, GMAIL_SIGNIN);
		waitForElement(driver, GMAIL_SAVE_CALENDER);
		jsClickByXPath(driver, GMAIL_SAVE_CALENDER);
		waitForElement(driver, GMAIL_CALENDER_CONFIRM_MSG);
		jsClickByXPath(driver, GMAIL_SETTING);
		jsClickByXPath(driver, GMAIL_LOGOUT);
		wait(driver, "2");
		Navigate.close(driver);
		switchWindow(driver);
		wait(driver, "5");
		waitForElement(driver, MEMBER_ADDTO_CALENDER);
		mouseOver(driver, memberAddCalender);
		jsClickByXPath(driver, MEMBER_ADDTO_CALENDER);
		getWindow(driver, yahooCalender);
		try
		{
			waitForElement(driver, YAHOO_USERNAME);
		}
		catch(Exception e)
		{
			Navigate.refreshPage(driver);
			waitForElement(driver, YAHOO_USERNAME);
			try
			{
				waitForElement(driver, YAHOO_USERNAME);
			}
			catch(Exception e1)
			{
				Navigate.refreshPage(driver);
				waitForElement(driver, YAHOO_USERNAME);

			}
		}
		WebElement yahooUsername = driver.findElement(By.xpath(OR.YAHOO_USERNAME));
		Manipulation.sendKeys(yahooUsername , yahooUsernamedata);
		wait(driver, "3");
		WebElement yahoo_staysign = driver.findElement(By.xpath(OR.YAHOO_STAY_SIGIN));
		Manipulation.click(yahoo_staysign);
		jsClickByXPath(driver, YAHOO_SIGNIN);
		wait(driver, "3");
		WebElement yahooPassword = driver.findElement(By.xpath(OR.YAHOO_PASSWORD));
		actionType(driver,yahooPassword , yahooPassworddata);
		jsClickByXPath(driver, YAHOO_SIGNIN);
		waitForElement(driver, YAHOO_SAVE_CALENDER);
		jsClickByXPath(driver, YAHOO_SAVE_CALENDER);
		wait(driver, "2");
		WebElement yahooSettings = driver.findElement(By.xpath(OR.YAHOO_SETTING));
		mouseOver(driver, yahooSettings);
		wait(driver, "2");
		Navigate.close(driver);
		wait(driver, "1");
		switchWindow(driver);
		wait(driver, "2");
		String OSName=Platform.OS.toUpperCase();
		if(OSName.contains("WINDOWS"))
		{
			waitForElement(driver, MEMBER_ADDTO_CALENDER);
			jsClickByXPath(driver, MEMBER_ADDTO_CALENDER);
			jsClickByXPath(driver, MEMBER_ICAL_CALENDER);
			wait(driver, "4");
			Screen s=new Screen();
			try
			{
				Pattern image = new Pattern(Directory.uploadFilePath+"Cancel4.png");
				wait(driver, "5");
				s.wait(image, 15);
				//s.click(image);
				//wait(driver, "2");
				s.doubleClick(image);
				wait(driver, "15");
			}
			catch(Exception e)
			{
				Pattern image = new Pattern(Directory.uploadFilePath+"Cancel4.png");
				wait(driver, "15");
				// s.wait(image, 15);
				s.doubleClick(image);
				wait(driver, "15");
			}
			wait(driver, "30");
			waitForElement(driver, MEMBER_ADDTO_CALENDER);
			jsClickByXPath(driver, MEMBER_ADDTO_CALENDER);
			jsClickByXPath(driver, MEMBER_OUTLOOK_CALENDER);
			wait(driver, "15");
			try
			{
				Pattern image = new Pattern(Directory.uploadFilePath+"Cancel4.png");
				wait(driver, "5");
				//s.wait(image, 15);
				s.doubleClick(image);
				wait(driver, "15");
			}
			catch(Exception e)
			{
				Pattern image = new Pattern(Directory.uploadFilePath+"Cancel4.png");
				wait(driver, "15");
				//s.wait(image, 15);
				s.click(image);
				wait(driver, "2");
				s.click(image);
				wait(driver, "15");
			}
		}
	}


	/**
	 * Name :     LEENA P.
	 * Created Date:   July/21/2016
	 * Modified Date:
	 * Description :   Common method for member sign up for lecture session
	 * Required Inputs :
	 *
	 */
	public static void memberSignUpLectureSessionForCoachConfirmation(WebDriver driver, String lecturename, String mailid)
	{


		//Member-1-LectureSignUp
		wait(driver, "1");
		Navigate.get(driver, Directory.Memberurl);
		Navigate.maximize(driver);
		wait(driver, "10");
		try
		{
			wait(driver, "10");
			waitForElement(driver, OR.MEMBER_YOURMAIL);
		}
		catch(Exception e)
		{
			Navigate.refreshPage(driver);
			waitForElement(driver, OR.MEMBER_YOURMAIL);
			try
			{
				Navigate.waitTime(driver, "30");
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
			catch(Exception e1)
			{
				Navigate.refreshPage(driver);
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
		}
		WebElement member_username = driver.findElement(By.xpath(OR.MEMBER_YOURMAIL));
		actionType(driver,member_username, mailid);
		System.out.println("Username is typed");
		WebElement member_password = driver.findElement(By.xpath(OR.MEMBER_YOURPASSWORD));
		jsClickByXPath(driver, OR.MEMBER_YOURPASSWORD);
		actionType(driver,member_password,"Password1");
		System.out.println("Password is typed");
		waitForElement(driver, OR.MEMBER_LOGIN_BUTTON);
		jsClickByXPath(driver, OR.MEMBER_LOGIN_BUTTON);
		System.out.println("Login button is clicked");
		Navigate.waitTime(driver, "20");
		waitForElement(driver, OR.MEMBER_DASHBOARD);
		waitForAjax(driver);
		wait(driver, "5");
		System.out.println("Member logged in successfully");
		waitForElement(driver, VIEW_SESSION_BUTTON);
		jsClickByXPath(driver, VIEW_SESSION_BUTTON);
		wait(driver, "5");
		String lecturesignup  = "//div[text()='"+lecturename+"']//parent::span/parent::div/parent::div/following-sibling::div//button[contains(text(),'Sign up')]";
		try
		{
			System.out.println("inside try block ");

			verifyElementIsPresent(driver, driver.findElement(By.xpath("//div[contains(text(),'"+lecturename+"')]")));
			WebElement signUpButton= driver.findElement(By.xpath(lecturesignup));
			click(signUpButton);
		}
		catch (Exception e)
		{
			List<WebElement> countPages = driver.findElements(By.id(OR.MEMBER_UPCOMING_SESSIONS_PAGES));
			int noofPages = countPages.size();
			System.out.println("no of pages is " +noofPages);
			for (int i=0; i<noofPages-1; i++)
			{
				try
				{
					verifyElementIsPresent(driver, driver.findElement(By.xpath("//div[text()='"+lecturename+"']")));
					WebElement signUpButton= driver.findElement(By.xpath(lecturesignup));
					click(signUpButton);
					break;
				}
				catch(Exception e1)
				{
					Manipulation.waitForElement(driver, OR.MEMBER_VIEW_SESSION_NEXT_PAGE_NAVIGATOR);
					WebElement nextPageNavigator= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_NEXT_PAGE_NAVIGATOR));
					click(nextPageNavigator);
					System.out.println("clicked on next page");
					Manipulation.wait(driver, "5");
				}
			}
		}
		verifyElementIsPresent(driver, driver.findElement(By.xpath("//div[text()='"+lecturename+"']")));
		WebElement signUpButton= driver.findElement(By.xpath(lecturesignup));
		click(signUpButton);
		Manipulation.wait(driver, "5");
		WebElement successfullPopUpText= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_SUCCESSFULL_POPUP_TEXT));
		verifyElementIsPresent(driver, successfullPopUpText);
		System.out.println("Popup text verified");
		Manipulation.wait(driver, "10");
		WebElement successfullPopUpOkButton= driver.findElement(By.xpath(OR.AVAILABLE_SESSIONS_CLOSE_BUTTON));
		verifyElementIsPresent(driver, successfullPopUpOkButton);
		click(successfullPopUpOkButton);
	}
	/**
	 * Name :     Abinaya.P
	 * Created Date:   14/sep/2016
	 * Modified Date:
	 * Description :   Common method to validate schedule/change for the 1on1 session
	 * Required Inputs :
	 */
	public static String verifyScheduleOrChange(WebDriver driver, String mail)
	{
		wait(driver, "5");
		try
		{
			waitForElement(driver, MEMBER_SESSION_SCHEDULE);
			jsClickByXPath(driver, MEMBER_SESSION_SCHEDULE);
			wait(driver, "6");
		}
		catch(Exception e)
		{
			try
			{
				waitForElement(driver, MEMBER_SESSION_CHANGE);
				jsClickByXPath(driver, MEMBER_SESSION_CHANGE);
				wait(driver, "6");
				waitForElement(driver, MEMBER_CANCEL_SESSION);
				jsClickByXPath(driver, MEMBER_CANCEL_SESSION);
				wait(driver, "3");
				waitForElement(driver, MEMBER_SESSION_POPUP_OK);
				jsClickByXPath(driver, MEMBER_SESSION_POPUP_OK);
				waitForElement(driver, SESSION_CANCEL_MSG);
				wait(driver, "6");
				waitForElement(driver, MEMBER_DASHBOARD);
				jsClickByXPath(driver, MEMBER_DASHBOARD);
				wait(driver, "6");
				waitForElement(driver, MEMBER_SESSION_SCHEDULE);
				jsClickByXPath(driver, MEMBER_SESSION_SCHEDULE);
				wait(driver, "6");

			}
			catch(Exception e1)
			{
				waitForElement(driver, MEMBER_SESSION_RESCHEDULE);
				jsClickByXPath(driver, MEMBER_SESSION_RESCHEDULE);
				wait(driver, "6");
			}
		}
		com.zillion.qa.member.Dashboard.dateSession1(driver,mail);
		waitForElement(driver, MEMBER_SESSION_CONFIRM);
		jsClickByXPath(driver, MEMBER_SESSION_CONFIRM);
		wait(driver, "5");
		waitForElement(driver, MEMBER_SESSION_SUCCESS_MSG);
		return mail;
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   13/OCT/16
	 * Modified Date:
	 * Description :   Create a common method to schedule 1 on 1 session in member portal
	 * Ticket ID :
	 * Required Inputs :  Mail
	 */
	public static String dateSession1(WebDriver driver,String mail)
	{
		String availableTime="";
		try
		{
			String CurrentDateOR = "//div[@id='timeSelection']/div[2]/div/div/div/span";
			WebElement CurrentDate = driver.findElement(By.xpath(CurrentDateOR));
			String date2 = CurrentDate.getText();
			String CurrentDateEvents = "//span[text()='"+date2+"']/parent::div/following-sibling::div/button[@ng-disabled='false']";
			System.out.println("Label Name : "+CurrentDate);
			WebElement Currentevents = driver.findElement(By.xpath(CurrentDateEvents));
			click( Currentevents );
			wait(driver, "3");
			WebElement CurrenteventsDrop = driver.findElement(By.xpath("//ul[@class='dropdown-menu schedule-default-menu']/li/a"));
			availableTime = driver.findElement(By.xpath("//ul[@class='dropdown-menu schedule-default-menu']/li/a")).getText();
			System.out.println("Available Time :"+availableTime);
			actionClick( driver, CurrenteventsDrop );
			wait(driver, "5");
		}
		catch(Exception e)
		{
			try
			{
				String CurrentDateOR = "//div[@id='timeSelection']/div[2]/div/div/div/span//following::div[5]/following::div/div/span";
				WebElement CurrentDate = driver.findElement(By.xpath(CurrentDateOR));
				String date2 = CurrentDate.getText();
				String CurrentDateEvents = "//span[text()='"+date2+"']/parent::div/following-sibling::div/button[@ng-disabled='false']";
				System.out.println("Label Name : "+CurrentDate);
				WebElement Currentevents = driver.findElement(By.xpath(CurrentDateEvents));
				click( Currentevents );
				wait(driver, "3");
				WebElement CurrenteventsDrop = driver.findElement(By.xpath("//ul[@class='dropdown-menu schedule-default-menu']/li/a"));
				actionClick( driver, CurrenteventsDrop );
				wait(driver, "5");
			}
			catch (Exception e1)
			{
				Navigate.newTab(driver);
				com.zillion.qa.opsadmin.Dashboard.opsAdminLogin(driver);
				wait(driver, "3");
				waitForElement(driver, OPS_ADMIN_MEMBERS_TAB);
				jsClickByXPath(driver, OPS_ADMIN_MEMBERS_TAB);
				WebElement patient_drpdown = driver.findElement(By.xpath(PATIENT_SORT_DROPDOWN));
				selectByVisibletext(patient_drpdown, "Email");
				jsClickByXPath(driver, PATIENT_SEARCH_TEXTBOX);
				WebElement patient_srch_txtbx = driver.findElement(By.xpath(PATIENT_SORT_DROPDOWN));
				Manipulation.sendKeys(patient_srch_txtbx, mail);
				jsClickByXPath(driver, PATIENT_SEARCH_SUBMIT_BUTTON);
				wait(driver, "5");
				WebElement coach_name = driver.findElement(By.xpath(OPSADMIN_MEMBER_TABLE_COACH_NAME));
				String coach_name1=ElementActions.getText(coach_name);
				jsClickByXPath(driver, OPS_ADMIN_PROVIDERS_HEADER_TAB);
				wait(driver, "2");
				waitForElement(driver, PROGRAM_ADMIN_PROVIDER_TAB_SEARCH_DROPDOWN);
				WebElement prvd_drpdown = driver.findElement(By.xpath(PROGRAM_ADMIN_PROVIDER_TAB_SEARCH_DROPDOWN));
				selectByVisibletext(prvd_drpdown, "Name");
				wait(driver, "2");
				waitForElement(driver, PROGRAM_ADMIN_PROVIDER_TAB_SEARCH_TEXTBOX);
				WebElement prvd_srch = driver.findElement(By.xpath(PROGRAM_ADMIN_PROVIDER_TAB_SEARCH_TEXTBOX));
				Manipulation.sendKeys(prvd_srch, "coach_name1");
				jsClickByXPath(driver, PROGRAM_ADMIN_PROVIDER_TAB_SEARCH_TEXTBOX_SUBMIT_BUTTON);
				wait(driver, "5");
				com.zillion.qa.opsadmin.Providers.clickOnAppropriateProvider(driver,coach_name1);
				wait(driver, "3");
				waitForElement(driver, COACHES_GEAR_BUTTON_SCHEDULE_ADD_BUTTON);
				jsClickByXPath(driver, COACHES_GEAR_BUTTON_SCHEDULE_ADD_BUTTON);
				waitForElement(driver, COACHES_SCHEDULE_CALENDER_ADD_AVAILABLITY_OPTION);
				jsClickByXPath(driver, COACHES_SCHEDULE_CALENDER_ADD_AVAILABLITY_OPTION);
				wait(driver, "3");
				waitForElement(driver, COACHES_SCHEDULE_CALENDER_SCHEDULE_REPEATS_BUTTON);
				jsClickByXPath(driver, COACHES_SCHEDULE_CALENDER_SCHEDULE_REPEATS_BUTTON);
				wait(driver, "3");
				com.zillion.qa.opsadmin.Classes.nextDateCoachUnavailabeTimeSlot(driver);
				wait(driver, "1");
				WebElement schedule = driver.findElement(By.xpath(COACHES_SCHEDULE_CALENDER_SCHEDULE_AVA_FOR_TEXT));
				Manipulation.doubleClick(driver, schedule);
				jsClickByXPath(driver, COACHES_SCHEDULE_CALENDER_SCHEDULE_START_TIME_TEXTBOX);
				WebElement start_time = driver.findElement(By.xpath(COACHES_SCHEDULE_CALENDER_SCHEDULE_START_TIME_TEXTBOX));
				Manipulation.clear(start_time);
				Manipulation.sendKeys(start_time, "12:00 am");
				WebElement end_time = driver.findElement(By.xpath(COACHES_SCHEDULE_CALENDER_SCHEDULE_END_TIME_TEXTBOX));
				Manipulation.clear(end_time);
				Manipulation.sendKeys(end_time, "11:00 pm");
				jsClickByXPath(driver,LECTURE_SESSION_MONDAY_CHECKBOX );
				jsClickByXPath(driver,LECTURE_SESSION_TUESDAY_CHECKBOX );
				jsClickByXPath(driver,LECTURE_SESSION_WEDNESDAY_CHECKBOX );
				jsClickByXPath(driver,LECTURE_SESSION_THURSDAY_CHECKBOX );
				jsClickByXPath(driver,LECTURE_SESSION_FRIDAY_CHECKBOX);
				jsClickByXPath(driver, COACHES_SCHEDULE_CALENDER_SCHEDULE_NEVER_CHECKBOX);
				jsClickByXPath(driver, COACHES_SCHEDULE_CALENDER_SCHEDULE_SAVE_BUTTON);
				wait(driver, "3");
				waitForElement(driver, COACHES_SCHEDULE_CALENDER_SCHEDULE_POPUP_TEXT);
				jsClickByXPath(driver, COACHES_SCHEDULE_CALENDER_SCHEDULE_POPUP_OK_BUTTON);
				wait(driver, "2");
				com.zillion.qa.opsadmin.Dashboard.opsAdminLogout(driver);
				Navigate.closeTab(driver);
			}
		}
		return availableTime;
	}
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   16/OCT/16
	 * Modified Date:
	 * Description :   Create a common method to Select the future date time slot
	 * Ticket ID :
	 * Required Inputs :  Mail
	 */
	public static void selectFuturedateSession(WebDriver driver)
	{
		try
		{
			String CurrentDateOR = "//div[@id='timeSelection']/div/div/div[2]/div/span";
			WebElement CurrentDate = driver.findElement(By.xpath(CurrentDateOR));
			String date2 = CurrentDate.getText();
			String CurrentDateEvents = "//span[text()='"+date2+"']/parent::div/following-sibling::div/button[@ng-disabled='false']";
			System.out.println("Label Name : "+CurrentDate);
			WebElement Currentevents = driver.findElement(By.xpath(CurrentDateEvents));
			click( Currentevents );
			wait(driver, "3");
			WebElement CurrenteventsDrop = driver.findElement(By.xpath("//div[@id='timeSelection']/div/div/div[2]/div//following::ul/li/a"));
			actionClick( driver, CurrenteventsDrop );
			wait(driver, "5");
		}
		catch(Exception e)
		{
			String CurrentDateOR = "//div[@id='timeSelection']/div[2]/div/div/div/span//following::div[5]/following::div/div/span";
			WebElement CurrentDate = driver.findElement(By.xpath(CurrentDateOR));
			String date2 = CurrentDate.getText();
			String CurrentDateEvents = "//span[text()='"+date2+"']/parent::div/following-sibling::div/button[@ng-disabled='false']";
			System.out.println("Label Name : "+CurrentDate);
			WebElement Currentevents = driver.findElement(By.xpath(CurrentDateEvents));
			click( Currentevents );
			wait(driver, "3");
			WebElement CurrenteventsDrop = driver.findElement(By.xpath("//div[@id='timeSelection']/div/div/div[2]/div/following::ul[@class='dropdown-menu schedule-default-menu']/li/a"));
			actionClick( driver, CurrenteventsDrop );
			wait(driver, "5");
		}
	}
	public static void oneononeScheduleIntheCalendar(WebDriver driver)
	{
		System.out.println("SessionTime : "+Sessiontime);
		String[] Sessiontime1=Sessiontime.split(":");
		String Sessiontime2=Sessiontime1[0];
		System.out.println("SessionTime2 : "+Sessiontime2);
		String Sessiontime3=Sessiontime1[1];
		System.out.println("SessionTime3 : "+Sessiontime3);
		String Sessiontime4= Sessiontime3.substring(0, 2).trim();
		System.out.println("SessionTime4 : "+Sessiontime4);
		String Sessiontime5= Sessiontime3.substring(2, 4).trim();
		System.out.println("SessionTime5 : "+Sessiontime5);
		String Sessiontime6=Sessiontime5.toLowerCase();
		String scheduledtime=Sessiontime2+":"+Sessiontime4+" "+Sessiontime6;
		wait(driver, "3");
		System.out.println("Consolidate SessionTime : "+scheduledtime);
		WebElement schedule_calendar = driver.findElement(By.xpath("//span[contains(text(),'"+scheduledtime+"')]//following::div[contains(text(),'1on1')]"));
		System.out.println("SessionT: "+schedule_calendar);
		wait(driver, "5");
		click(schedule_calendar);
	}
	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:   11/NOV/16
	 * Modified Date:
	 * Description :   Create a common method to scheduled OneOnOne StartAndEndtime
	 * Ticket ID :
	 * Required Inputs :  Mail
	 */
	public static String scheduledOneOnOneStartAndEnd(WebDriver driver,String StartAndEndTime)
	{
		String[] StartAndEndTime1=StartAndEndTime.split(" ");
		String Sessiontime2=StartAndEndTime1[3];
		String Sessiontime3=StartAndEndTime1[5];
		String Sessiontime21= Sessiontime2.substring(0, 5).trim();
		String Sessiontime22= Sessiontime2.substring(5, 7).trim();
		String Sessiontime23=Sessiontime21+" "+Sessiontime22;
		System.out.println("Print1: "+Sessiontime23);
		String Sessiontime31= Sessiontime3.substring(0, 5).trim();
		String Sessiontime32= Sessiontime3.substring(5, 7).trim();
		String Sessiontime33=Sessiontime31+" "+Sessiontime32;
		System.out.println("Print2: "+Sessiontime33);
		return Sessiontime2+","+Sessiontime23+","+Sessiontime33;
	}
	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:   11/NOV/16
	 * Modified Date:
	 * Description : Create a common method to AssignSubtituteCoach For Scheduled OneOnOne session
	 * Ticket ID :
	 * Required Inputs :  Mail
	 */
	public static String assignSubtituteCoachForScheduledOneOnOne(WebDriver driver,String ScheduleTime)
	{
		String[] ScheduleTime1 = ScheduleTime.split(",");
		String ScheduleTime2= ScheduleTime1[1];
		System.out.println("ScheduleTime2: "+ScheduleTime2);
		String Sessiontime3= ScheduleTime2.substring(0, 2);
		String Sessiontime4= ScheduleTime2.substring(2, 8);
		System.out.println("Sessiontime4: "+Sessiontime4);
		if(Sessiontime3!="10"&&Sessiontime3!="11"&&Sessiontime3!="12")
		{
			String Sessiontime5= Sessiontime3.substring(0).replace("0", "");
			String combinedString= Sessiontime5+Sessiontime4;
			WebElement substituteCoachButton = driver.findElement(By.xpath(OR.ASSIGNED_COACH_SUBSTITUTE_FOR_SCHEDULED_ONE_ON_ONE_START_OR+combinedString+ASSIGNED_COACH_SUBSTITUTE_FOR_SCHEDULED_ONE_ON_ONE_END_OR));
			verifyElementIsPresent(driver, substituteCoachButton);
			jsClickByXPath(driver, OR.ASSIGNED_COACH_SUBSTITUTE_FOR_SCHEDULED_ONE_ON_ONE_START_OR+combinedString+ASSIGNED_COACH_SUBSTITUTE_FOR_SCHEDULED_ONE_ON_ONE_END_OR);
			wait(driver, "5");
			return ScheduleTime2;
		}
		WebElement substituteCoachButton = driver.findElement(By.xpath(OR.ASSIGNED_COACH_SUBSTITUTE_FOR_SCHEDULED_ONE_ON_ONE_START_OR+ScheduleTime2+ASSIGNED_COACH_SUBSTITUTE_FOR_SCHEDULED_ONE_ON_ONE_END_OR));
		verifyElementIsPresent(driver, substituteCoachButton);
		jsClickByXPath(driver, OR.ASSIGNED_COACH_SUBSTITUTE_FOR_SCHEDULED_ONE_ON_ONE_START_OR+ScheduleTime2+ASSIGNED_COACH_SUBSTITUTE_FOR_SCHEDULED_ONE_ON_ONE_END_OR);
		wait(driver, "5");
		return ScheduleTime2;
	}
	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:   11/NOV/16
	 * Modified Date:
	 * Description :   Create a common method to Scheduled time as unavailable
	 * Ticket ID :
	 * Required Inputs :  Mail
	 */
	public static String typeScheduledTimeAsUnavailable(WebDriver driver,String ScheduleTime2)
	{
		String[] ScheduleTime1 = ScheduleTime2.split(",");
		String ScheduleTime21= ScheduleTime1[1];
		String ScheduleTime31= ScheduleTime1[2];
		WebElement startTimeTextbox= driver.findElement(By.xpath(OR.COACHES_SCHEDULE_CALENDER_SCHEDULE_START_TIME_TEXTBOX));
		actionClick(driver,startTimeTextbox);
		clear( startTimeTextbox );
		sendKeys(startTimeTextbox,ScheduleTime21);
		WebElement endTimeTextbox= driver.findElement(By.xpath(OR.COACHES_SCHEDULE_CALENDER_SCHEDULE_END_TIME_TEXTBOX));
		actionClick(driver,endTimeTextbox);
		clear( endTimeTextbox );
		sendKeys(endTimeTextbox,ScheduleTime31);
		return ScheduleTime21+","+ScheduleTime31;
	}
	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:   11/NOV/16
	 * Modified Date:
	 * Description : Create a common method to AssignSubtituteCoach For Scheduled OneOnOne session
	 * Ticket ID :
	 * Required Inputs :  Mail
	 */
	public static String sessionConflictsScheduledOneOnOneRescheduleOption(WebDriver driver,String ScheduleTime3)
	{
		String[] ScheduleTime1 = ScheduleTime3.split(",");
		String ScheduleTime2= ScheduleTime1[0];
		WebElement rescheduleOption = driver.findElement(By.xpath(OR.SESSION_CONFLICTS_RESCHEDULE_OPTION_START_OR+ScheduleTime2+SESSION_CONFLICTS_RESCHEDULE_OPTION_END_OR));
		verifyElementIsPresent(driver, rescheduleOption);
		jsClickByXPath(driver, OR.SESSION_CONFLICTS_RESCHEDULE_OPTION_START_OR+ScheduleTime2+SESSION_CONFLICTS_RESCHEDULE_OPTION_END_OR);
		wait(driver, "5");
		return ScheduleTime2;
	}
	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:   11/NOV/16
	 * Modified Date:
	 * Description : Create a common method to Cancel OneOnOne session
	 * Ticket ID :
	 * Required Inputs :  Mail
	 */
	public static String sessionConflictsScheduledOneOnOneCancelOption(WebDriver driver,String ScheduleTime4)
	{
		String[] ScheduleTime1 = ScheduleTime4.split(",");
		String ScheduleTime2= ScheduleTime1[0];
		WebElement cancelOption = driver.findElement(By.xpath(OR.SESSION_CONFLICTS_CANCEL_OPTION_START_OR+ScheduleTime2+SESSION_CONFLICTS_CANCEL_OPTION_END_OR));
		verifyElementIsPresent(driver, cancelOption);
		jsClickByXPath(driver, OR.SESSION_CONFLICTS_CANCEL_OPTION_START_OR+ScheduleTime2+SESSION_CONFLICTS_CANCEL_OPTION_END_OR);
		wait(driver, "5");
		return ScheduleTime2;
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   10/Mar/16
	 * Modified Date:
	 * Description :   Create a common method to retrieve member which is active and has program name as 'Post-placement for 52 weeks'
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException	 */
	public static String mailid = "";
	public static String getorberamember(WebDriver driver) throws ClassNotFoundException, SQLException
	{
		String port = Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select AC.email as mail from account AC, account_program AP where AC.id=AP.account_id and AP.name like 'Orbera Program: Post-placement for 52 weeks' and AC.STATUS='Active'");
		System.out.println("query executed");
		if(rs.next())
		{
			mailid = rs.getString("mail");
			System.out.println(mailid+" : mailid");
		}
		else
		{
			System.out.println("No results found from the sql execution");
		}

		return mailid;
	}
	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:   11/NOV/16
	 * Modified Date:
	 * Description :   Create a common method to scheduled OneOnOne StartAndEndtime
	 * Ticket ID :
	 * Required Inputs :  Mail
	 *
	 */
	public static String PracticePAscheduledOneOnOneStartAndEnd(WebDriver driver,String StartAndEndTime1)
	{
		String[] StartAndEndTime2=StartAndEndTime1.split(" ");
		String Sessiontime2=StartAndEndTime2[0];
		String Sessiontime3=StartAndEndTime2[2];
		System.out.println("Print1: "+Sessiontime2);
		System.out.println("Print1: "+Sessiontime3);
		String Sessiontime21= Sessiontime2.substring(0, 4).trim();
		String Sessiontime22= Sessiontime2.substring(4, 6).trim();
		String Sessiontime23=Sessiontime21+" "+Sessiontime22;
		System.out.println("Print1: "+Sessiontime23);
		String Sessiontime31= Sessiontime3.substring(0, 4).trim();
		String Sessiontime32= Sessiontime3.substring(4, 6).trim();
		String Sessiontime33=Sessiontime31+" "+Sessiontime32;
		System.out.println("Print2: "+Sessiontime33);
		return Sessiontime2+","+Sessiontime23+","+Sessiontime33;
	}
	public static void kidneyMemberLogin(WebDriver driver) {
		wait(driver, "1");
		Navigate.get(driver, Directory.Memberurl);
		Navigate.maximize(driver);
		wait(driver, "10");
		try
		{
			Navigate.waitTime(driver, "30");
			waitForElement(driver, OR.MEMBER_YOURMAIL);
		}
		catch(Exception e)
		{
			Navigate.refreshPage(driver);
			waitForElement(driver, OR.MEMBER_YOURMAIL);
			try
			{
				Navigate.waitTime(driver, "30");
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
			catch(Exception e1)
			{
				Navigate.refreshPage(driver);
				waitForElement(driver, OR.MEMBER_YOURMAIL);
			}
		}
		WebElement member_username = driver.findElement(By.xpath(OR.MEMBER_YOURMAIL));
		actionType(driver,member_username, Directory.KidneyMemberusername1);
		System.out.println("Username is typed");
		WebElement member_password = driver.findElement(By.xpath(OR.MEMBER_YOURPASSWORD));
		jsClickByXPath(driver, OR.MEMBER_YOURPASSWORD);
		actionType(driver,member_password,Directory.Memberpassword2);
		System.out.println("Password is typed");
		waitForElement(driver, OR.MEMBER_LOGIN_BUTTON);
		jsClickByXPath(driver, OR.MEMBER_LOGIN_BUTTON);
		System.out.println("Login button is clicked");
		Navigate.waitTime(driver, "20");
		waitForElement(driver, OR.MEMBER_DASHBOARD);
		waitForAjax(driver);
		wait(driver, "10");
		System.out.println("Member logged in successfully");
	}
	/**
	 * Name :    VinothKumar.M
	 * Created Date:   20/Dec/16
	 * Modified Date:
	 * Description :   Create a common method for Logout from Member user in framework
	 * Ticket ID :
	 * Required Inputs :
	 */
	public static void kidneyMemberLogout(WebDriver driver)
	{
		WebElement member_profile = driver.findElement(By.xpath(OR.MEMBER_PROFILE));
		click(member_profile);
		waitForElement(driver, OR.MEMBER_LOGOUT);
		WebElement member_logout= driver.findElement(By.xpath(OR.MEMBER_LOGOUT));
		click(member_logout);
	}
	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:   27/Dec/16
	 * Modified Date:
	 * Description :   Create a common method to scheduled OneOnOne Endtime
	 * Ticket ID :
	 * Required Inputs :  Mail
	 */
	public static String PracticePAscheduledOneOnOneStartTime(WebDriver driver,String StartTime1)
	{
		String[] StartTime2=StartTime1.split(" ");
		String Sessiontime2=StartTime2[0];
		String Sessiontime3=StartTime2[2];
		System.out.println("Print1: "+Sessiontime2);
		System.out.println("Print1: "+Sessiontime3);
		String Sessiontime21= Sessiontime2.substring(0, 2).trim();
		String Starttime="";
		if(!Sessiontime21.equalsIgnoreCase("10")&&!Sessiontime21.equalsIgnoreCase("11")&&!Sessiontime21.equalsIgnoreCase("12"))
		{
			String Sessiontime24= Sessiontime21.substring(0).replace("0", "");
			System.out.println("1: "+Sessiontime24);
			String Sessiontime25= Sessiontime2.substring(2, 4);
			System.out.println("2: "+Sessiontime25);
			String Sessiontime26= Sessiontime2.substring(4, 6);
			String combinedString= Sessiontime24+Sessiontime25+" "+Sessiontime26;
			System.out.println("3: "+combinedString);
			return  Sessiontime2+","+combinedString;
		}
		else if(Sessiontime21.equalsIgnoreCase("10")||Sessiontime21.equalsIgnoreCase("11")||Sessiontime21.equalsIgnoreCase("12"))
		{
			String Sessiontime26= Sessiontime2.substring(0, 2);
			String Sessiontime27= Sessiontime2.substring(2, 5);
			String Sessiontime28= Sessiontime2.substring(5, 7);
			String Sessiontime29=Sessiontime26+Sessiontime27+" "+Sessiontime28;
			System.out.println("Starttime: "+Sessiontime29);
			return  Sessiontime2+","+Sessiontime29;
		}
		return Starttime;
	}
	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:   27/Dec/16
	 * Modified Date:
	 * Description :   Create a common method to scheduled OneOnOne Endtime
	 * Ticket ID :
	 * Required Inputs :  Mail
	 */
	public static String PracticePAscheduledOneOnOneEndTime(WebDriver driver,String EndTime1)
	{
		String[] EndTime2=EndTime1.split(" ");
		String Sessiontime2=EndTime2[0];
		String Sessiontime3=EndTime2[2];
		System.out.println("Print1: "+Sessiontime2);
		System.out.println("Print1: "+Sessiontime3);
		String Sessiontime21= Sessiontime3.substring(0, 2).trim();
		String Endtime="";
		if(!Sessiontime21.equalsIgnoreCase("10")&&!Sessiontime21.equalsIgnoreCase("11")&&!Sessiontime21.equalsIgnoreCase("12"))
		{
			String Sessiontime24= Sessiontime21.substring(0).replace("0", "");
			System.out.println("1: "+Sessiontime24);
			String Sessiontime25= Sessiontime3.substring(2, 4);
			System.out.println("2: "+Sessiontime25);
			String Sessiontime26= Sessiontime3.substring(4, 6);
			String combinedString= Sessiontime24+Sessiontime25+" "+Sessiontime26;
			System.out.println("3: "+combinedString);
			return Sessiontime3+","+combinedString;
		}
		else if(Sessiontime21.equalsIgnoreCase("10")||Sessiontime21.equalsIgnoreCase("11")||Sessiontime21.equalsIgnoreCase("12"))
		{
			String Sessiontime26= Sessiontime3.substring(0, 2);
			String Sessiontime27= Sessiontime3.substring(2, 5);
			String Sessiontime28= Sessiontime3.substring(5, 7);
			String Sessiontime29=Sessiontime26+Sessiontime27+" "+Sessiontime28;
			System.out.println("Endtime: "+Sessiontime29);
			return Sessiontime3+","+Sessiontime29;
		}
		return Endtime;
	}
	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:   27/Dec/16
	 * Modified Date:
	 * Description :   Create a common method to Scheduled time as unavailable Start time
	 * Ticket ID :
	 * Required Inputs :  Mail
	 */
	public static String typeScheduledTimeAsUnavailableStartTime(WebDriver driver,String ScheduleTime2)
	{
		String[] ScheduleTime1 = ScheduleTime2.split(",");
		String ScheduleTime21= ScheduleTime1[1];
		WebElement startTimeTextbox= driver.findElement(By.xpath(OR.COACHES_SCHEDULE_CALENDER_SCHEDULE_START_TIME_TEXTBOX));
		actionClick(driver,startTimeTextbox);
		clear( startTimeTextbox );
		sendKeys(startTimeTextbox,ScheduleTime21);
		return ScheduleTime21;
	}
	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:   27/Dec/16
	 * Modified Date:
	 * Description :   Create a common method to Scheduled time as unavailable End time
	 * Ticket ID :
	 * Required Inputs :  Mail
	 */
	public static String typeScheduledTimeAsUnavailableEndTime(WebDriver driver,String ScheduleTime3)
	{
		String[] ScheduleTime1 = ScheduleTime3.split(",");
		String ScheduleTime21= ScheduleTime1[1];
		WebElement endTimeTextbox= driver.findElement(By.xpath(OR.COACHES_SCHEDULE_CALENDER_SCHEDULE_END_TIME_TEXTBOX));
		actionClick(driver,endTimeTextbox);
		clear( endTimeTextbox );
		sendKeys(endTimeTextbox,ScheduleTime21);
		return ScheduleTime21;
	}
	/**
	 * Name :     Suresh V
	 * Created Date:   05-Jan-2017
	 * Modified Date:
	 */
	public static void verifyScheduleOrCancel(WebDriver driver, String mail)
	{
		wait(driver, "5");
		try
		{
			waitForElement(driver, MEMBER_SESSION_SCHEDULE);
			jsClickByXPath(driver, MEMBER_SESSION_SCHEDULE);
			wait(driver, "6");
		}
		catch(Exception e)
		{
			try
			{
				waitForElement(driver, MEMBER_SESSION_CHANGE);
				jsClickByXPath(driver, MEMBER_SESSION_CHANGE);
				wait(driver, "6");
				waitForElement(driver, MEMBER_CANCEL_SESSION);
				jsClickByXPath(driver, MEMBER_CANCEL_SESSION);
				wait(driver, "3");
				waitForElement(driver, MEMBER_SESSION_POPUP_OK);
				jsClickByXPath(driver, MEMBER_SESSION_POPUP_OK);
				waitForElement(driver, SESSION_CANCEL_MSG);
				wait(driver, "6");
				waitForElement(driver, MEMBER_DASHBOARD);
				jsClickByXPath(driver, MEMBER_DASHBOARD);
				wait(driver, "6");
				waitForElement(driver, MEMBER_SESSION_SCHEDULE);
				jsClickByXPath(driver, MEMBER_SESSION_SCHEDULE);
				wait(driver, "6");
			}
			catch(Exception e1)
			{
				waitForElement(driver, MEMBER_SESSION_RESCHEDULE);
				jsClickByXPath(driver, MEMBER_SESSION_RESCHEDULE);
				wait(driver, "6");
			}
		}
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   13/OCT/16
	 * Modified Date:
	 * Description :   Create a common method to schedule 1 on 1 session in member portal
	 * Ticket ID :
	 * Required Inputs :  Mail
	 */
	public static String availableSessionTime(WebDriver driver)
	{
		String availableTime="";
		waitForElement(driver, MEMBER_SESSION_SCHEDULE);
		jsClickByXPath(driver, MEMBER_SESSION_SCHEDULE);
		wait(driver, "6");
		String CurrentDateOR = "//div[@id='timeSelection']/div[2]/div/div/div/span";
		WebElement CurrentDate = driver.findElement(By.xpath(CurrentDateOR));
		String date2 = CurrentDate.getText();
		String CurrentDateEvents = "//span[text()='"+date2+"']/parent::div/following-sibling::div/button[@ng-disabled='false']";
		System.out.println("Label Name : "+CurrentDate);
		WebElement Currentevents = driver.findElement(By.xpath(CurrentDateEvents));
		click( Currentevents );
		wait(driver, "3");
		WebElement CurrenteventsDrop = driver.findElement(By.xpath("//ul[@class='dropdown-menu schedule-default-menu']/li/a"));
		availableTime = driver.findElement(By.xpath("//ul[@class='dropdown-menu schedule-default-menu']/li/a")).getText();
		System.out.println("Available Time :"+availableTime);
		actionClick( driver, CurrenteventsDrop );
		wait(driver, "5");
		waitForElement(driver, MEMBER_SESSION_CONFIRM);
		jsClickByXPath(driver, MEMBER_SESSION_CONFIRM);
		wait(driver, "5");
		waitForElement(driver, MEMBER_SESSION_SUCCESS_MSG);
		String[] availableTime1= availableTime.split(":");
		String availableTime2= availableTime1[1].substring(0, 2);
		String availableTime3= availableTime1[1].substring(2, 4).toLowerCase();
		String availableTime4= availableTime1[0]+":"+availableTime2+" "+availableTime3;
		return availableTime4;
	}
}