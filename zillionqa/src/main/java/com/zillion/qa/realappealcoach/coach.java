package com.zillion.qa.realappealcoach;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;

import java.awt.event.InputEvent;










import com.zillion.qa.commands.ElementActions;
import com.zillion.qa.commands.Manipulation;
import com.zillion.qa.commands.Navigate;
import com.zillion.qa.utils.Directory;
import com.zillion.qa.utils.Platform;

public class coach extends Manipulation implements OR
{

	/**
	 * Name :     Vinothkumar.M
	 * Created Date:   25/Apr/2016
	 * Description :Create a common method for Login into Real Appeal Coach Login
	 * Ticket ID :
	 * Required Inputs : URL, Username and Password
	 *
	 */
	public static void realAppealCoachLogin(WebDriver driver)
	{
		Navigate.get(driver, Directory.RA_Provider_Url);
		Navigate.maximize(driver);
		wait(driver, "5");
		WebElement Palogin= driver.findElement(By.xpath(RA_COACH_USERNAME));
		waitForElement(driver,RA_COACH_USERNAME);
		sendKeys(Palogin, Directory.RA_Coach_Username1);
		WebElement Papassword= driver.findElement(By.xpath(RA_COACH_PASSWORD));
		sendKeys(Papassword, Directory.RA_Coach_Pasword1);
		WebElement loginbutton= driver.findElement(By.xpath(RA_COACH_LOGIN));
		click(loginbutton);
		wait(driver, "4");
		try{
			//Navigate.waitTime(driver, "20");
			//wait(driver, "5");
			WebElement Palogopresent= driver.findElement(By.xpath(RA_COACH_REAL_APPEAL_LOGO));
			//waitForElement(driver,RA_COACH_REAL_APPEAL_LOGO);
			verifyElementIsPresent(driver, Palogopresent);
		}
		catch(Exception e) {

			WebElement userSessionPopup = driver.findElement(By.xpath(PROVIDER_USER_SESSION_POPUP));
			click(userSessionPopup);
			WebElement Palogopresent= driver.findElement(By.xpath(RA_COACH_REAL_APPEAL_LOGO));
			waitForElement(driver,RA_COACH_REAL_APPEAL_LOGO);
			verifyElementIsPresent(driver, Palogopresent);
		}
		wait(driver, "5");
	}

	/**
	 * Name :    Vinothkumar.M
	 * Created Date:   25/Apr/2016
	 * Description :Create a common method for Real Appeal Coach Logout
	 * Ticket ID :
	 * Required Inputs :
	 *
	 */
	public static void realAppealCoachLogout(WebDriver driver)
	{
		waitForElement(driver,RA_COACH_SIGNOUT_LINK);
		jsClickByXPath(driver,RA_COACH_SIGNOUT_LINK);
		wait(driver, "1");
		waitForElement(driver,RA_COACH_SIGNOUT_BUTTON);
		jsClickByXPath(driver,RA_COACH_SIGNOUT_BUTTON);
		Navigate.waitTime(driver, "20");
		waitForElement(driver,RA_COACH_SIGNOUT_VERIFY);
		Navigate.waitTime(driver, "20");
	}

	/**
	 * Name :     Abinaya.P
	 * Created Date:   25/04/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve available hours from coach table
	 * Ticket ID :
	 * Required Inputs :  No Inputs Required
	 * @throws ParseException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String availablehours="";
	public  static String retrieveCoachAvailableHrs(WebDriver driver,String inputData) throws ParseException, ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select SCH_AVAILABLE_HRS_WEEKLY from Provider where email like '"+inputData+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			availablehours= rs.getString("SCH_AVAILABLE_HRS_WEEKLY");
			System.out.println("Available hours "+availablehours+" is retrieved from the Table");
		}
		return availablehours;
	}

	/**
	 * Name :   Vinothkumar.M
	 * Created Date: 4/May/2016
	 * Modified Date:
	 * Description : To Launch Real Appeal Provider URL
	 * Ticket ID :
	 * Required Inputs :
	 */

	public static void launchRAProviderUrl(WebDriver driver)
	{
		try {

			driver.navigate().to(Directory.RA_Provider_Url);
			wait(driver, "2");
		}  catch (Exception e1) 
		{
			com.zillion.qa.commands.Manipulation.browserURLSecurityException(driver);
		}
	}

	/**
	 * Name :Abinaya.P
	 * Created Date: 05/May/2016
	 * Modified Date:
	 * Description : Create a common method to find the Login assigned coach of the member
	 * Ticket ID :
	 * Required Inputs :  Provider URL,Email ID from reference step, Password
	 * @throws ParseException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static void assignedCoachOfMemberLogin(WebDriver driver,String mailId)
	{
		Navigate.get(driver, Directory.RA_Provider_Url);
		wait(driver, "5");
		Navigate.maximize(driver);
		WebElement coach_username= driver.findElement(By.xpath(OR.RA_COACH_USERNAME));
		verifyElementIsPresent(driver, coach_username);
		WebElement coach_password= driver.findElement(By.xpath(OR.RA_COACH_PASSWORD));
		verifyElementIsPresent(driver, coach_password);
		WebElement Coacheslogin_button= driver.findElement(By.xpath(OR.RA_COACH_LOGIN));
		verifyElementIsPresent(driver, Coacheslogin_button);
		sendKeys(coach_username,mailId);
		try
		{
			wait(driver, "2");
			sendKeys(coach_password,"Healthfleet2015");
			System.out.print("Password is Healthfleet2015");
			wait(driver, "2");
			jsClickByXPath(driver, RA_COACH_LOGIN);
			wait(driver, "2");
			waitForElement(driver, RA_COACH_SIGNOUT_LINK);
		}
		catch(Exception e2)
		{
			try
			{
				wait(driver, "2");
				WebElement coach_password1= driver.findElement(By.xpath(OR.RA_COACH_PASSWORD));
				clear( coach_password1 );
				sendKeys(coach_password,"Zillion2016");
				wait(driver, "2");
				jsClickByXPath(driver, RA_COACH_LOGIN);
				wait(driver, "2");
				waitForElement(driver, RA_COACH_SIGNOUT_LINK);
			}
			catch(Exception e)
			{
				try
				{
					wait(driver, "2");
					WebElement coach_password2= driver.findElement(By.xpath(OR.RA_COACH_PASSWORD));
					clear( coach_password2 );
					sendKeys(coach_password,"Healthfleet2015");
					wait(driver, "2");
					jsClickByXPath(driver, RA_COACH_LOGIN);
					wait(driver, "3");
					waitForElement(driver, RA_COACH_SIGNOUT_LINK);
				}
				catch(Exception e1)
				{
					wait(driver, "2");
					WebElement coach_password3= driver.findElement(By.xpath(OR.RA_COACH_PASSWORD));
					clear( coach_password3 );
					sendKeys(coach_password,"Zadotesting1");
					wait(driver, "2");
					jsClickByXPath(driver, RA_COACH_LOGIN);
					wait(driver, "3");
					waitForElement(driver, RA_COACH_SIGNOUT_LINK);
				}
			}
		}
		Navigate.waitTime(driver, "20");
		System.out.println("Coaches Logged in successfully");
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   05/MAY/16
	 * Modified Date:
	 * Description :   Create a common method to append 'attend now' to url in RA coach portal
	 * Ticket ID :
	 * Required Inputs :
	 *
	 */
	public static void appendTextToRACoachURL(WebDriver driver)
	{
		Navigate.get(driver, Directory.RACoachAppendURL);
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   10/MAY/16
	 * Modified Date:
	 * Description : Create a common method to verify the mail of the coach once the customization session is completed
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */

	public static void verifyCoachMailCustomizationSession(WebDriver driver, String mailid)
	{
		if(mailid.contains("yopmail.com"))
		{
			Navigate.get(driver, Directory.yopmailurl);
			Navigate.maximize(driver);
			WebElement yopEmailTextbox= driver.findElement(By.xpath(OR.YOP_EMAIL_TEXTBOX));
			verifyElementIsPresent(driver, yopEmailTextbox);
			clear(yopEmailTextbox);
			sendKeys(yopEmailTextbox, mailid);
			WebElement checkInbox= driver.findElement(By.xpath(OR.YOP_EMAIL_CHECK_INBOX));
			verifyElementIsPresent(driver, checkInbox);
			jsClickByXPath(driver, OR.YOP_EMAIL_CHECK_INBOX);
			wait(driver, "5");
			WebElement iframe= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME));
			Navigate.switchToFrame(driver, iframe);
			WebElement generatedLink= driver.findElement(By.xpath(OR.RA_COACH_CUSTOMIZATION_MAIL));
			verifyElementIsPresent(driver, generatedLink);
			com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.RA_COACH_CUSTOMIZATION_MAIL);
			wait(driver, "5");

			if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
			jsClickByXPath(driver, OR.RA_COACH_CUSTOMIZATION_MAIL);
			wait(driver, "3");
			waitForElement(driver, OR.RA_COACH_CUSTOMIZATION_MAIL_CONTENT);
		}
		else if(mailid.contains("guerrillamail"))
		{
			Navigate.get(driver, Directory.Guerrillamailurl);
			Navigate.maximize(driver);
			wait(driver, "2");
			WebElement Guerrillamaildropdown= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_DROPDOWN));
			selectByValue(Guerrillamaildropdown, "guerrillamail.com");
			WebElement GuerrillaMailEditButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_EDIT_BUTTON));
			click(GuerrillaMailEditButton);
			waitForElement(driver, GUERRILLA_MAIL_TEXTBOX);
			WebElement GuerrillaMailTextbox= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_TEXTBOX));
			clearAndType(GuerrillaMailTextbox, mailid);
			WebElement GuerrillaMailSetButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_SET_BUTTON));
			click(GuerrillaMailSetButton);
			wait(driver, "5");
			com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.GUERRILLAMAIL_RA_COACH_CUSTOMIZATION_MAIL);
			wait(driver, "5");

			if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
			jsClickByXPath(driver, OR.GUERRILLAMAIL_RA_COACH_CUSTOMIZATION_MAIL);
			wait(driver, "3");
			waitForElement(driver, OR.RA_COACH_CUSTOMIZATION_MAIL_CONTENT);
		}
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   10/MAY/16
	 * Modified Date:
	 * Description : Create a common method to verify the mail of the coach once the 1on1 session is scheduled
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void verifyCoachMail1on1Session(WebDriver driver, String mailid)
	{
		String First_Window = driver.getWindowHandle();
		Navigate.newTab(driver);
		if(mailid.contains("yopmail.com"))
		{

			Navigate.get(driver, Directory.yopmailurl);
			/*for (String Child_Window : driver.getWindowHandles())
			{*/
			Navigate.maximize(driver);

			System.out.println("Navigated to yopmail");
			WebElement yopEmailTextbox= driver.findElement(By.xpath(OR.YOP_EMAIL_TEXTBOX));
			verifyElementIsPresent(driver, yopEmailTextbox);
			clear(yopEmailTextbox);
			sendKeys(yopEmailTextbox, mailid);
			wait(driver, "3");
			WebElement checkInbox= driver.findElement(By.xpath(OR.YOP_EMAIL_CHECK_INBOX));
			verifyElementIsPresent(driver, checkInbox);
			//click(checkInbox);
			jsClickByXPath(driver, OR.YOP_EMAIL_CHECK_INBOX);
			wait(driver, "5");
			WebElement iframe= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME1));
			Navigate.switchToFrame(driver, iframe);
			try
			{
				WebElement generatedLink= driver.findElement(By.xpath(OR.RA_COACH_1on1_MAIL));
				verifyElementIsPresent(driver, generatedLink);
				com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.RA_COACH_1on1_MAIL);
				wait(driver, "5");

				if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
				{
					driver.get("javascript:document.getElementById('overridelink').click();");
					wait(driver, "5");
				}				

				jsClickByXPath(driver, OR.RA_COACH_1on1_MAIL);
				wait(driver, "3");
				Navigate.switchToDefaultFrame(driver);
				WebElement iframe2= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME2));
				Navigate.switchToFrame(driver, iframe2);
				waitForElement(driver, OR.RA_COACH_1on1_CONTENT);
				Navigate.switchToDefaultFrame(driver);
			}
			catch(Exception e)
			{
				WebElement generatedLink= driver.findElement(By.xpath(OR.YOPMAIL_RA_COACH_1on1_MAIL_SCHEDULED));
				verifyElementIsPresent(driver, generatedLink);
				com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.YOPMAIL_RA_COACH_1on1_MAIL_SCHEDULED);
				wait(driver, "5");

				if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
				{
					driver.get("javascript:document.getElementById('overridelink').click();");
					wait(driver, "5");
				}
				jsClickByXPath(driver, OR.YOPMAIL_RA_COACH_1on1_MAIL_SCHEDULED);
				wait(driver, "3");
				Navigate.switchToDefaultFrame(driver);
				WebElement iframe2= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME2));
				Navigate.switchToFrame(driver, iframe2);
				waitForElement(driver, OR.YOPMAIL_RA_COACH_1on1_CONTENT_SCHEDULED);
				Navigate.switchToDefaultFrame(driver);
			}


		}
		//}
		else if(mailid.contains("guerrillamail"))
		{
			//Navigate.newTab(driver);
			Navigate.get(driver, Directory.Guerrillamailurl);
			wait(driver, "5");
			WebElement Guerrillamaildropdown= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_DROPDOWN));
			selectByValue(Guerrillamaildropdown, "guerrillamail.com");
			WebElement GuerrillaMailEditButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_EDIT_BUTTON));
			click(GuerrillaMailEditButton);
			waitForElement(driver, GUERRILLA_MAIL_TEXTBOX);
			WebElement GuerrillaMailTextbox= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_TEXTBOX));
			clearAndType(GuerrillaMailTextbox, mailid);
			WebElement GuerrillaMailSetButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_SET_BUTTON));
			click(GuerrillaMailSetButton);
			wait(driver, "5");
			WebElement generatedLink= driver.findElement(By.xpath(OR.ORBERA_COACH_1on1_MAIL_GR));
			String grSubject = ElementActions.getText(generatedLink);
			System.out.println("Subject line is" +grSubject);

			if(grSubject.contains("1-on-1 Session Rescheduled")) {
				com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_COACH_1on1_MAIL_GR);
				wait(driver, "15");
				if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
				{
					driver.get("javascript:document.getElementById('overridelink').click();");
					wait(driver, "5");
				}
				jsClickByXPath(driver, OR.ORBERA_COACH_1on1_MAIL_GR);
				wait(driver, "3");
				wait(driver, "3");
				waitForElement(driver, OR.RA_COACH_1on1_CONTENT);
			}

			else if(grSubject.contains("Session Scheduled: 1-on-1")) {
				com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_COACH_1on1_MAIL_GR);
				wait(driver, "15");
				if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
				{
					driver.get("javascript:document.getElementById('overridelink').click();");
					wait(driver, "5");
				}
				jsClickByXPath(driver, OR.ORBERA_COACH_1on1_MAIL_GR);
				wait(driver, "3");
				wait(driver, "3");
				waitForElement(driver, OR.YOPMAIL_RA_COACH_1on1_CONTENT_SCHEDULED);

			}
		}
		Navigate.closeTab(driver);
		driver.switchTo().window(First_Window);

	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   26/MAY/16
	 * Modified Date:
	 * Description : Create a common method to verify the mail of the coach once the 1on1 session is cancelled
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void verifyCoachMail1on1SessionCancellation(WebDriver driver, String mailid)
	{
		String First_Window = driver.getWindowHandle();
		Navigate.newTab(driver);
		if(mailid.contains("yopmail.com"))
		{
			Navigate.get(driver, Directory.yopmailurl);
			Navigate.maximize(driver);
			WebElement yopEmailTextbox= driver.findElement(By.xpath(OR.YOP_EMAIL_TEXTBOX));
			verifyElementIsPresent(driver, yopEmailTextbox);
			clear(yopEmailTextbox);
			sendKeys(yopEmailTextbox, mailid);
			WebElement checkInbox= driver.findElement(By.xpath(OR.YOP_EMAIL_CHECK_INBOX));
			verifyElementIsPresent(driver, checkInbox);
			jsClickByXPath(driver, OR.YOP_EMAIL_CHECK_INBOX);
			wait(driver, "5");
			WebElement iframe= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME1));
			Navigate.switchToFrame(driver, iframe);
			WebElement generatedLink= driver.findElement(By.xpath(OR.RA_COACH_1on1_CANCELlATION_MAIL));
			verifyElementIsPresent(driver, generatedLink);
			com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.RA_COACH_1on1_CANCELlATION_MAIL);
			wait(driver, "5");

			if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
			jsClickByXPath(driver, OR.RA_COACH_1on1_CANCELlATION_MAIL);
			wait(driver, "3");
			Navigate.switchToDefaultFrame(driver);
			WebElement iframe2= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME2));
			Navigate.switchToFrame(driver, iframe2);
			waitForElement(driver, OR.RA_COACH_1on1_CANCELLATION_CONTENT);
			Navigate.switchToDefaultFrame(driver);
		}
		else if(mailid.contains("guerrillamail"))
		{
			Navigate.get(driver, Directory.Guerrillamailurl);
			Navigate.maximize(driver);
			wait(driver, "2");
			WebElement Guerrillamaildropdown= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_DROPDOWN));
			selectByValue(Guerrillamaildropdown, "guerrillamail.com");
			WebElement GuerrillaMailEditButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_EDIT_BUTTON));
			click(GuerrillaMailEditButton);
			waitForElement(driver, GUERRILLA_MAIL_TEXTBOX);
			WebElement GuerrillaMailTextbox= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_TEXTBOX));
			clearAndType(GuerrillaMailTextbox, mailid);
			WebElement GuerrillaMailSetButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_SET_BUTTON));
			click(GuerrillaMailSetButton);
			wait(driver, "5");

			WebElement generatedLink= driver.findElement(By.xpath(OR.ORBERA_COACH_1on1_MAIL_GR));
			String grSubject = ElementActions.getText(generatedLink);
			System.out.println("Subject line is" +grSubject);
			if(grSubject.contains("Session Canceled")) {
				com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_COACH_1on1_MAIL_GR);
				wait(driver, "15");
				if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
				{
					driver.get("javascript:document.getElementById('overridelink').click();");
					wait(driver, "5");
				}
				jsClickByXPath(driver, OR.ORBERA_COACH_1on1_MAIL_GR);
				wait(driver, "3");
				waitForElement(driver, OR.RA_COACH_1on1_CANCELLATION_CONTENT);
			}
			else {
				System.out.println("Cancellation email not received");
			}

		}
		Navigate.closeTab(driver);
		driver.switchTo().window(First_Window);

	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   10/MAY/16
	 * Modified Date:
	 * Description : Create a common method to verify the mail of the coach once the enrollment is completed
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void verifyCoachMailEnrollment(WebDriver driver, String mailid)
	{
		if(mailid.contains("yopmail.com"))
		{
			Navigate.get(driver, Directory.yopmailurl);
			Navigate.maximize(driver);
			WebElement yopEmailTextbox= driver.findElement(By.xpath(OR.YOP_EMAIL_TEXTBOX));
			verifyElementIsPresent(driver, yopEmailTextbox);
			clear(yopEmailTextbox);
			sendKeys(yopEmailTextbox, mailid);
			WebElement checkInbox= driver.findElement(By.xpath(OR.YOP_EMAIL_CHECK_INBOX));
			verifyElementIsPresent(driver, checkInbox);
			jsClickByXPath(driver, OR.YOP_EMAIL_CHECK_INBOX);
			wait(driver, "5");
			WebElement iframe= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME));
			Navigate.switchToFrame(driver, iframe);
			WebElement generatedLink= driver.findElement(By.xpath(OR.RA_COACH_ENROLLMENT_MAIL));
			verifyElementIsPresent(driver, generatedLink);
			com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.RA_COACH_ENROLLMENT_MAIL);
			wait(driver, "5");

			if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
			jsClickByXPath(driver, OR.RA_COACH_ENROLLMENT_MAIL);
			wait(driver, "3");
			Navigate.switchToDefaultFrame(driver);
			WebElement iframe1= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME1));
			Navigate.switchToFrame(driver, iframe1);
			waitForElement(driver, OR.RA_COACH_ENROLLMENT_MAIL_CONTENT);
			Navigate.switchToDefaultFrame(driver);
			wait(driver, "3");
		}
		else if(mailid.contains("guerrillamail"))
		{
			Navigate.get(driver, Directory.Guerrillamailurl);
			Navigate.maximize(driver);
			wait(driver, "2");
			WebElement Guerrillamaildropdown= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_DROPDOWN));
			selectByValue(Guerrillamaildropdown, "guerrillamail.com");
			WebElement GuerrillaMailEditButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_EDIT_BUTTON));
			click(GuerrillaMailEditButton);
			waitForElement(driver, GUERRILLA_MAIL_TEXTBOX);
			WebElement GuerrillaMailTextbox= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_TEXTBOX));
			clearAndType(GuerrillaMailTextbox, mailid);
			WebElement GuerrillaMailSetButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_SET_BUTTON));
			click(GuerrillaMailSetButton);
			wait(driver, "5");
			com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.RA_COACH_ENROLLMENT_MAIL_GUERILLA);
			wait(driver, "5");

			if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
			jsClickByXPath(driver, OR.RA_COACH_ENROLLMENT_MAIL_GUERILLA);
			wait(driver, "3");
			waitForElement(driver, OR.RA_COACH_ENROLLMENT_MAIL_CONTENT);
		}
	}

	/**
	 * Name :Suresh.V
	 * Created Date: 31/May/2016
	 * Modified Date:
	 * Description :   Create a common method for Verifying Top 5 Upcoming Sessions in the coach Dashboard
	 **/
	public static void verifyCoachTop5UpcomingSessions(WebDriver driver)
	{
		wait(driver, "2");
		int count = driver.findElements(By.xpath("//*[@id='mentorUpcomingSessions']/grid/div/table/tbody/tr[*]")).size();
		System.out.println("Upcoming sessions count :"+count);

		if(count<=5)
		{
			System.out.println(" Upcoming sessions has below 5" );
		}
		else
		{
			Assert.fail();
		}

	}

	/**
	 * Name :Suresh.V
	 * Created Date: 31/May/2016
	 * Modified Date:
	 * Description :   Create a common method for Verifying Coach's upcoming session where Past sessions are not available
	 * @throws ParseException
	 **/
	public static void verifyCoachPastUpcomingSessionsAreNotAvailable(WebDriver driver) throws ParseException
	{
		String sessiontime=null;
		String UpcomingSessiondate=null;
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String mydate=dateFormat.format(date);
		System.out.println("Current date :"+mydate);
		int countDate=driver.findElements(By.xpath("//*[@id='mentorUpcomingSessions']/grid/div/table/tbody/tr[*]")).size();
		System.out.println("Count value" +countDate);
		String[] sessiondate1 = new String[countDate];

		for (int i=0;i<5;i++)
		{
			String sessiontdate=driver.findElement(By.xpath("//*[@id='mentorUpcomingSessions']/grid/div/table/tbody//tr["+(i+1)+"]/td[1]")).getText();
			sessiondate1[i]=sessiontdate;
			String sessiondate2 = sessiondate1[i].replace("st", "");
			String sessiontdate3 = sessiondate2.replace("rd", "");
			String sessiondate4 = sessiontdate3.replace("th", "");
			String sessiondate5 = sessiondate4.replace("nd", "");
			String[] Sessiondate6=sessiondate5.split(",");
			String Sessiondate7=Sessiondate6[1];
			System.out.println("Current date :"+Sessiondate7);
			SimpleDateFormat ft = new SimpleDateFormat(" MMMMM dd yyyy");
			java.util.Date t=ft.parse(Sessiondate7);
			ft.applyPattern("MM-dd-yyyy");
			UpcomingSessiondate=ft.format(t);
			System.out.println("Current date :"+UpcomingSessiondate);
		}
		Calendar cal = Calendar.getInstance();
		DateFormat sdf = new SimpleDateFormat("hh:mma");
		String currentTime1=sdf.format(cal.getTime());
		System.out.println( currentTime1 );
		int countTime=driver.findElements(By.xpath("//*[@id='mentorUpcomingSessions']/grid/div/table/tbody/tr[*]/td[2]")).size();
		System.out.println("Count value" +countTime);
		String[] sessiontime2 = new String[countTime];

		for (int j=0;j<5;j++)
		{
			sessiontime=driver.findElement(By.xpath("//*[@id='mentorUpcomingSessions']/grid/div/table/tbody//tr["+(j+1)+"]/td[2]")).getText();
			sessiontime2[j]=sessiontime;
			System.out.println("Current time :"+sessiontime2[j]);
		}

		//How to check if date1 is equal to date2

		if(UpcomingSessiondate.equals(mydate))
		{
			System.out.println(UpcomingSessiondate + " and " +mydate + " are equal to each other");
		}

		//How to check if date1 is greater than date2

		if (sessiontime.toLowerCase().compareTo(currentTime1) > 0)
		{
			System.out.println(sessiontime + " is greater than " +currentTime1 );
		}

	}

	/**
	 * Name :Name :Suresh.V
	 * Created Date: 02/April/2016
	 * Modified Date:
	 * Description :   Create a common method for verifying the coach upcoming session View and Attend now button
	 **/
	public static String verifyingcoachupcomingsessionViewAndAttendnowButton(WebDriver driver)
	{
		try
		{
			WebElement AttendNow_Btn=driver.findElement(By.xpath("//span[text()='Upcoming Sessions']//following::tbody/tr/td[9]/div/a[contains(text(),'Attend Now')]"));
			verifyElementIsPresent(driver, AttendNow_Btn);
			return  "Attend now button is verified";
		}
		catch(Exception e)
		{
			WebElement View_Btn=driver.findElement(By.xpath("//span[text()='Upcoming Sessions']//following::tbody/tr/td[9]/div/a[contains(text(),'View')]"));
			verifyElementIsPresent(driver, View_Btn);
			return  "View button is verified";
		}
	}

	/**
	 * Name :Name :Suresh.V
	 * Created Date: 02/April/2016
	 * Modified Date:
	 * Description :   Create a common method for Coach Upcoming Session in Ascending order
	 * @throws ParseException
	 **/
	public static String verifyingcoachupcomingsessionsareAscendingOrders(WebDriver driver) throws ParseException
	{
		int countDate=driver.findElements(By.xpath("//*[@id='mentorUpcomingSessions']/grid/div/table/tbody/tr[*]")).size();
		System.out.println("Count value" +countDate);
		String[] sessiondate1 = new String[countDate];
		String[] sessiondate = new String[countDate];
		for (int i=0;i<5;i++)
		{
			String sessiontdate=driver.findElement(By.xpath("//*[@id='mentorUpcomingSessions']/grid/div/table/tbody//tr["+(i+1)+"]/td[1]")).getText();
			sessiondate1[i]=sessiontdate;
			String sessiondate2 = sessiondate1[i].replace("st", "");
			String sessiontdate3 = sessiondate2.replace("rd", "");
			String sessiondate4 = sessiontdate3.replace("th", "");
			String sessiondate5 = sessiondate4.replace("nd", "");
			String[] Sessiondate6=sessiondate5.split(",");
			String Sessiondate7=Sessiondate6[1];
			System.out.println("Current date :"+Sessiondate7);
			SimpleDateFormat ft = new SimpleDateFormat(" MMMMM dd yyyy");
			System.out.println("Current date :"+sessiondate5);
			java.util.Date t=ft.parse(Sessiondate7);
			ft.applyPattern("MM-dd-yyyy");
			String UpcomingSessiondate=ft.format(t);
			sessiondate[i]=UpcomingSessiondate;
			System.out.println("Current date :"+UpcomingSessiondate);
		}
		String[] sort=sessiondate;
		Arrays.sort(sort);
		int i=0;
		for (i=0; i< sort.length;i++)
		{
			System.out.println("sorted in ascending order::"+sort[i]);
		}
		if(Arrays.equals(sort,sessiondate))
		{
			return "Upcoming sessions are ascending order";
		}
		else
		{
			return "Upcoming sessions are not ascending order";
		}
	}

	/**
	 * Name :Name :Suresh.V
	 * Created Date: 02/April/2016
	 * Modified Date:
	 * Description :   Create a common method for Upcoming session Alert to accept
	 * @throws ParseException
	 **/
	public  static  void upcomingSessionAlertaccept(WebDriver driver) throws ClassNotFoundException, SQLException
	{
		try
		{
			Alert alert = driver.switchTo().alert();
			alert.accept();
			WebElement DASHBOARD=driver.findElement(By.xpath(RA_COACH_DASHBOARD));
			click(DASHBOARD);
			wait(driver, "5");
		}
		catch(Exception e)
		{
			WebElement DASHBOARD=driver.findElement(By.xpath(RA_COACH_DASHBOARD));
			click(DASHBOARD);
			wait(driver, "5");
		}
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   20/Jun/16
	 * Modified Date:
	 * Description : Create a common method to verify the mail of the coach once the member is moved to another class
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void verifyCoachMail(WebDriver driver, String mailid)
	{
		String First_Window = driver.getWindowHandle();
		Navigate.newTab(driver);
		if(mailid.contains("yopmail.com"))
		{
			Navigate.get(driver, Directory.yopmailurl);
			for (String Child_Window : driver.getWindowHandles())
			{
				driver.switchTo().window(Child_Window);
				Navigate.maximize(driver);
				WebElement yopEmailTextbox= driver.findElement(By.xpath(OR.YOP_EMAIL_TEXTBOX));
				verifyElementIsPresent(driver, yopEmailTextbox);
				clear(yopEmailTextbox);
				sendKeys(yopEmailTextbox, mailid);
				WebElement checkInbox= driver.findElement(By.xpath(OR.YOP_EMAIL_CHECK_INBOX));
				verifyElementIsPresent(driver, checkInbox);
				jsClickByXPath(driver, OR.YOP_EMAIL_CHECK_INBOX);
				wait(driver, "5");
				WebElement iframe= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME1));
				Navigate.switchToFrame(driver, iframe);
				WebElement generatedLink= driver.findElement(By.xpath(OR.RA_COACH_1on1_CANCELlATION_MAIL));
				verifyElementIsPresent(driver, generatedLink);
				com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.RA_COACH_1on1_CANCELlATION_MAIL);
				wait(driver, "5");

				if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
				{
					driver.get("javascript:document.getElementById('overridelink').click();");
					wait(driver, "5");
				}
				jsClickByXPath(driver, OR.RA_COACH_1on1_CANCELlATION_MAIL);
				wait(driver, "3");
				Navigate.switchToDefaultFrame(driver);
				WebElement iframe2= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME2));
				Navigate.switchToFrame(driver, iframe2);
				waitForElement(driver, OR.RA_COACH_1on1_CANCELLATION_CONTENT);
				Navigate.switchToDefaultFrame(driver);
			}
			Navigate.closeTab(driver);
			driver.switchTo().window(First_Window);
		}
		else if(mailid.contains("guerrillamail"))
		{
			Navigate.get(driver, Directory.Guerrillamailurl);
			for (String Child_Window : driver.getWindowHandles())
			{
				driver.switchTo().window(Child_Window);
				Navigate.maximize(driver);
				wait(driver, "2");
				WebElement Guerrillamaildropdown= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_DROPDOWN));
				selectByValue(Guerrillamaildropdown, "guerrillamail.com");
				WebElement GuerrillaMailEditButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_EDIT_BUTTON));
				click(GuerrillaMailEditButton);
				waitForElement(driver, GUERRILLA_MAIL_TEXTBOX);
				WebElement GuerrillaMailTextbox= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_TEXTBOX));
				clearAndType(GuerrillaMailTextbox, mailid);
				WebElement GuerrillaMailSetButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_SET_BUTTON));
				click(GuerrillaMailSetButton);
				wait(driver, "5");
				com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.RA_COACH_1on1_CANCELlATION_MAIL);
				wait(driver, "5");

				if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
				{
					driver.get("javascript:document.getElementById('overridelink').click();");
					wait(driver, "5");
				}
				jsClickByXPath(driver, OR.RA_COACH_1on1_CANCELlATION_MAIL);
				wait(driver, "3");
				waitForElement(driver, OR.RA_COACH_1on1_CANCELLATION_CONTENT);
			}
			Navigate.closeTab(driver);
			driver.switchTo().window(First_Window);
		}
	}

	/**
	 * Name :VinothKumar.M
	 * Created Date:10/Mar/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve Account Program ID
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// Retrieve Program ID with the help of Account ID
	// Substituting the account ID in the Query will give Program ID
	public static String realAppealRetrieveAccountProgramID(WebDriver driver, String accountIDReff1234) throws ClassNotFoundException, SQLException
	{
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		Statement stat=conn.createStatement();
		System.out.println("Connection success");
		ResultSet rs = stat.executeQuery("SELECT ID, MAST_PROGRAM_ID, START_DT_TIME, END_DT_TIME FROM ACCOUNT_PROGRAM WHERE ACCOUNT_ID='"+accountIDReff1234+"' AND IS_ACTIVE=1");
		System.out.println("RealAppealRetrieveAccountProgramID query executed");
		String accountProgramID="";
		while(rs.next())
		{
			accountProgramID= rs.getString("ID");
			System.out.println("Account Program id "+accountProgramID+" is retrieved from the Table");
		}
		return accountProgramID;
	}

	/**
	 * Name : VinothKumar.M
	 * Created Date: 10/Mar/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve Coach program id
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// Retrieve Coach Hostname for 1on1 Session
	// Substituting the Program ID in the query will give the Coach Hostname  for 1on1 session
	public static String realAppealRetrieve1on1SessionHostNameWithProgramID(WebDriver driver, String accountProgramID) throws ClassNotFoundException, SQLException
	{
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("SELECT EVT.ID, EVT.HOST_ID, EVT.HOST_NAME FROM ACCOUNT_PROGRAM_SESSION_DETAIL PSD, CALENDAR_EVENT EVT WHERE EVT.ID=PSD.CALENDAR_EVENT_ID AND ACCOUNT_PROGRAM_ID='"+accountProgramID+"'");
		System.out.println("query executed");
		String coachWithHostName1on1session="";
		while(rs.next())
		{
			coachWithHostName1on1session= rs.getString("HOST_ID");
			System.out.println("Session Coach HostID "+coachWithHostName1on1session+" is retrieved from the Table");
		}
		return coachWithHostName1on1session;
	}

	/**
	 * Name : VinothKumar.M
	 * Created Date: 10/Mar/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve Coach Email
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	//Retrieve Coach Email for 1on1 Session
	// Substituting the Coach Hostname in the query will give coach Email for 1on1 Session
	public static String realAppealRetrive1on1SessionEmailWithCoachHostId(WebDriver driver,String coachWithHostName1on1session) throws ClassNotFoundException, SQLException
	{
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select email from provider where id = '"+coachWithHostName1on1session+"'");
		System.out.println("query executed");
		String retrive1on1sessionCoachEmail ="";
		while(rs.next())
		{
			retrive1on1sessionCoachEmail= rs.getString("EMAIL");
			System.out.println("Coach Email  "+retrive1on1sessionCoachEmail+" is retrieved from the Table");
		}
		return retrive1on1sessionCoachEmail;
	}

	/**
	 * Name :VinothKumar.M
	 * Created Date:10/Mar/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve Member account ID
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	// Retrieve account ID from the DB with the Member Username
	// Member Username is given as input from the Spreadsheet
	// With the help of the accont Id we can retrieve Program ID
	public  static String realAppealRetrieveMemberAccountId(WebDriver driver, String inputUsername) throws ParseException, ClassNotFoundException, SQLException
	{
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("SELECT ID, NAME, EMAIL, ORGANIZATION_ID, STATUS FROM ACCOUNT WHERE EMAIL LIKE'"+inputUsername+"'");
		System.out.println("query executed");
		String accountId="";
		while(rs.next())
		{
			accountId= rs.getString("ID");
			System.out.println("Member Account id "+accountId+" is retrieved from the Table");
		}
		return accountId;
	}

	/**
	 * Name :Abinaya.P
	 * Created Date: 05/May/2016
	 * Modified Date:
	 * Description : Create a common method to find the assigned coach of the member using reference step
	 * Ticket ID :
	 * Required Inputs :  Provider URL, Username and Password
	 * @throws ParseException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static void coachLoginFromRetrievedAssignedCoachFromRefStep(WebDriver driver,String mailId)
	{
		String consolidatedResult[]= mailId.split(",");
		String coachEmail= consolidatedResult[1];
		wait(driver, "5");
		Navigate.get(driver, Directory.RA_Provider_Url);
		wait(driver, "5");
		Navigate.maximize(driver);
		WebElement coach_username= driver.findElement(By.xpath(OR.RA_COACH_USERNAME));
		verifyElementIsPresent(driver, coach_username);
		WebElement coach_password= driver.findElement(By.xpath(OR.RA_COACH_PASSWORD));
		verifyElementIsPresent(driver, coach_password);
		WebElement Coacheslogin_button= driver.findElement(By.xpath(OR.RA_COACH_LOGIN));
		verifyElementIsPresent(driver, Coacheslogin_button);
		sendKeys(coach_username,coachEmail);
		try
		{
			wait(driver, "2");
			sendKeys(coach_password,"Healthfleet2015");
			System.out.print("Password is Healthfleet2015");
			wait(driver, "2");
			jsClickByXPath(driver, RA_COACH_LOGIN);
			wait(driver, "2");
			waitForElement(driver, RA_COACH_SIGNOUT_LINK);
		}
		catch(Exception e2)
		{
			try
			{
				wait(driver, "2");
				WebElement coach_password1= driver.findElement(By.xpath(OR.RA_COACH_PASSWORD));
				clear( coach_password1 );
				sendKeys(coach_password,"Zillion2016");
				wait(driver, "2");
				jsClickByXPath(driver, RA_COACH_LOGIN);
				wait(driver, "2");
				waitForElement(driver, RA_COACH_SIGNOUT_LINK);
			}
			catch(Exception e)
			{
				try
				{
					wait(driver, "2");
					WebElement coach_password2= driver.findElement(By.xpath(OR.RA_COACH_PASSWORD));
					clear( coach_password2 );
					sendKeys(coach_password,"Healthfleet2016");
					wait(driver, "2");
					jsClickByXPath(driver, RA_COACH_LOGIN);
					wait(driver, "3");
					waitForElement(driver, RA_COACH_SIGNOUT_LINK);
				}
				catch(Exception e1)
				{
					wait(driver, "2");
					WebElement coach_password3= driver.findElement(By.xpath(OR.RA_COACH_PASSWORD));
					clear( coach_password3 );
					sendKeys(coach_password,"Zadotesting1");
					wait(driver, "2");
					jsClickByXPath(driver, RA_COACH_LOGIN);
					wait(driver, "3");
					waitForElement(driver, RA_COACH_SIGNOUT_LINK);
				}
			}
		}
		Navigate.waitTime(driver, "20");
		System.out.println("Coaches Logged in successfully");
	}

	/**
	 * Name :Name :Suresh.V
	 * Created Date: 10/JUNE/2016
	 * Modified Date:
	 * Description :   Create a common method for retrieve available coach for GroupSession
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 **/
	public  static  String retrievegroupsessionAvailableCoachEmailID(WebDriver driver) throws ClassNotFoundException, SQLException
	{
		String DBCoachEmailID=null;
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		//ResultSet rs = stat.executeQuery("SELECT SESSION_DATE_UTC, ACNT.EMAIL Member_Email, PROV.EMAIL Coach_Email FROM SUMMARY_ACCOUNT_TODATE SMRY, PROVIDER PROV, ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID  AND SMRY.ACCOUNT_ID=ACNT.ID AND SMRY.ONBOARDING_STATUS='MEMBER ONBOARDED'  AND ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ID IN (SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_STATUS='Scheduled' AND SESSION_TYPE_ID='01' AND MP_INTERVAL_NUMBER > (SELECT MAX(MP_INTERVAL_NUMBER) FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_TYPE_ID='01' AND  SCHEDULED_DT>SYSDATE + INTERVAL '-10' DAY)))AND ACNT.EMAIL not like '%api%' AND PROV.EMAIL not like '%api%' ORDER BY SESSION_DATE_UTC asc");
		ResultSet rs = stat.executeQuery("SELECT ACNT.EMAIL Member_Email, PROV.EMAIL Coach_Email FROM SUMMARY_ACCOUNT_TODATE SMRY, PROVIDER PROV, ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID AND SMRY.ACCOUNT_ID=ACNT.ID AND SMRY.ONBOARDING_STATUS='MEMBER ONBOARDED' AND ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ACCOUNT_PROGRAM.START_DT_TIME>SYSDATE + INTERVAL '-52' DAY AND ID IN (SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_STATUS!='Completed' AND SESSION_TYPE_ID='02' AND MP_INTERVAL_NUMBER > (SELECT MAX(MP_INTERVAL_NUMBER) FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_TYPE_ID='02' AND SCHEDULED_DT<SYSDATE AND SCHEDULED_DT>SYSDATE + INTERVAL '-2' DAY)))AND ACNT.EMAIL not like '%api%' AND PROV.EMAIL not like '%api%' ORDER BY SMRY.SESSION_DATE_UTC DESC");
		System.out.println("query executed");
		while(rs.next())
		{
			DBCoachEmailID = rs.getString("Coach_Email");
			System.out.println("DBSessionDate :"+   DBCoachEmailID );
		}
		return  DBCoachEmailID;
	}

	/**
	 * Name :     Vinothkumar.M
	 * Created Date:   25/Apr/2016
	 * Description :Create a common method for Login into Real Appeal Coach Login Using input method
	 * Ticket ID :
	 * Required Inputs : Provider URL, Username and Password
	 */
	public static void realAppealCoachLoginUsingInput(WebDriver driver,String mail)
	{
		Navigate.get(driver, Directory.RA_Provider_Url);
		Navigate.maximize(driver);
		wait(driver, "5");
		WebElement Palogin= driver.findElement(By.xpath(RA_COACH_USERNAME));
		waitForElement(driver,RA_COACH_USERNAME);
		sendKeys(Palogin, mail);
		WebElement Papassword= driver.findElement(By.xpath(RA_COACH_PASSWORD));
		sendKeys(Papassword, Directory.RA_Coach_Pasword1);
		WebElement loginbutton= driver.findElement(By.xpath(RA_COACH_LOGIN));
		click(loginbutton);
		wait(driver, "4");
		Navigate.waitTime(driver, "20");
		WebElement Palogopresent= driver.findElement(By.xpath(RA_COACH_REAL_APPEAL_LOGO));
		waitForElement(driver,RA_COACH_REAL_APPEAL_LOGO);
		verifyElementIsPresent(driver, Palogopresent);
		wait(driver, "5");
	}

	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:   24/Jan/17
	 * Modified Date:
	 * Description : Create a common method to retrieve Member who is available Schedule button for RA 1on1 live session
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static String retrieveRAAvailableMemberEmail(WebDriver driver,String testData) throws ParseException, ClassNotFoundException, SQLException
	{
		String[] testData1 = testData.split(",");
		String inputSessionType=testData1[0];
		String inputSessionStatus=testData1[1];
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		//ResultSet rs = stat.executeQuery("SELECT ACNT.EMAIL Member_Email, PROV.EMAIL Coach_Email FROM SUMMARY_ACCOUNT_TODATE SMRY, PROVIDER PROV, ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID AND SMRY.ACCOUNT_ID=ACNT.ID AND SMRY.ONBOARDING_STATUS='MEMBER ONBOARDED' AND ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ACCOUNT_PROGRAM.START_DT_TIME>SYSDATE + INTERVAL '-50' DAY AND ID IN (SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_STATUS in ('"+inputSessionStatus+"') AND PROGRAM_INTERVAL_START_DT<SYSDATE AND PROGRAM_INTERVAL_END_DT>SYSDATE AND SESSION_TYPE_ID='"+inputSessionType+"')) AND ACNT.EMAIL not like '%api%' AND PROV.EMAIL not like '%api%' ORDER BY SMRY.SESSION_DATE_UTC DESC");
		ResultSet rs = stat.executeQuery("SELECT ACNT.EMAIL Member_Email, PROV.EMAIL Coach_Email FROM SUMMARY_ACCOUNT_TODATE SMRY, PROVIDER PROV, ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID AND SMRY.ACCOUNT_ID=ACNT.ID AND SMRY.ONBOARDING_STATUS='MEMBER ONBOARDED' AND ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ACCOUNT_PROGRAM.START_DT_TIME>SYSDATE + INTERVAL '-50' DAY AND ID IN (SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_STATUS IN ('Unscheduled') AND PROGRAM_INTERVAL_START_DT<SYSDATE AND PROGRAM_INTERVAL_END_DT>SYSDATE AND SESSION_TYPE_ID='02'))AND ACNT.EMAIL not like '%api%' and ACNT.EMAIL not like '%QAmember%-default@yopmail.com%' AND PROV.EMAIL not like '%api%' AND ACNT.STATUS='Active' ORDER BY SMRY.SESSION_DATE_UTC DESC");
		System.out.println("query executed");
		String retrievedscheduleAvailableMemberEmail="";
		if(rs.next())
		{
			retrievedscheduleAvailableMemberEmail= rs.getString("MEMBER_EMAIL");
			System.out.println("Available Member Email is "+retrievedscheduleAvailableMemberEmail);
		}
		return retrievedscheduleAvailableMemberEmail;
	}

	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:   24/Jan/17
	 * Modified Date:
	 * Description : Create a common method to retrieve Member who is available Schedule button for RA 1on1 live session
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static String retrieveAssignedRACoachEmail(WebDriver driver,String testData) throws ParseException, ClassNotFoundException, SQLException
	{
		//String[] testData1 = testData.split(",");
		//String inputSessionType=testData1[0];
		//String inputSessionStatus=testData1[1];
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		//ResultSet rs = stat.executeQuery("SELECT ACNT.EMAIL Member_Email, PROV.EMAIL Coach_Email FROM SUMMARY_ACCOUNT_TODATE SMRY, PROVIDER PROV, ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID AND SMRY.ACCOUNT_ID=ACNT.ID AND SMRY.ONBOARDING_STATUS='MEMBER ONBOARDED' AND ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ACCOUNT_PROGRAM.START_DT_TIME>SYSDATE + INTERVAL '-50' DAY AND ID IN (SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_STATUS in ('"+inputSessionStatus+"') AND PROGRAM_INTERVAL_START_DT<SYSDATE AND PROGRAM_INTERVAL_END_DT>SYSDATE AND SESSION_TYPE_ID='"+inputSessionType+"')) AND ACNT.EMAIL not like '%api%' AND PROV.EMAIL not like '%api%' ORDER BY SMRY.SESSION_DATE_UTC DESC");
		ResultSet rs = stat.executeQuery("SELECT ACNT.EMAIL Member_Email, PROV.EMAIL Coach_Email FROM SUMMARY_ACCOUNT_TODATE SMRY, PROVIDER PROV, ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID AND SMRY.ACCOUNT_ID=ACNT.ID AND SMRY.ONBOARDING_STATUS='MEMBER ONBOARDED' AND ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ACCOUNT_PROGRAM.START_DT_TIME>SYSDATE + INTERVAL '-50' DAY AND ID IN (SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_STATUS IN ('Unscheduled') AND PROGRAM_INTERVAL_START_DT<SYSDATE AND PROGRAM_INTERVAL_END_DT>SYSDATE AND SESSION_TYPE_ID='02'))AND ACNT.EMAIL='"+testData+"' AND PROV.EMAIL not like '%api%' AND ACNT.STATUS='Active' ORDER BY SMRY.SESSION_DATE_UTC DESC");
		System.out.println("query executed");
		String retrievedscheduleAvailableMemberAssignedCoachEmail="";
		if(rs.next())
		{
			retrievedscheduleAvailableMemberAssignedCoachEmail = rs.getString("COACH_EMAIL");
			System.out.println("Available Member Email Assigned coach Email is "+retrievedscheduleAvailableMemberAssignedCoachEmail);
		}
		return retrievedscheduleAvailableMemberAssignedCoachEmail;
	}

	/**
	 * Name :     Leena P.
	 * Created Date:   08/Mar/2017
	 * Description :Check for user session popup
	 * Ticket ID :
	 * Required Inputs : URL, Username and Password
	 *
	 */
	public static void verifyUserSession(WebDriver driver)
	{
		try{

			WebElement userSessionPopup = driver.findElement(By.xpath(PROVIDER_USER_SESSION_POPUP));
			click(userSessionPopup);
		}
		catch(Exception e){
			System.out.println("Provider logged in successfully");
		}

	}
	/**
	 * Name :   Vinothkumar.M
	 * Created Date:   09/Mar/2017
	 * Description : Created common method for Provider Set Profile Picture
	 * Ticket ID :
	 * Required Inputs : URL, Username and Password
	 *
	 */
	public static void providerChangeProfilePic(WebDriver driver, String filepath) throws InterruptedException, AWTException
	{
		String OSName=Platform.OS.toUpperCase();
		if(OSName.contains("WINDOWS")){
			Navigate.waitTime(driver, "10");
			waitForElement(driver, PROVIDER_CHANGE_PIC);
			WebElement uploadphoto=driver.findElement(By.xpath(PROVIDER_CHANGE_PIC));
			uploadphoto.click();
			System.out.println("Clicked upload photo on Provider change pic");
			wait(driver, "5");
			String filepath1=Directory.uploadFilePath+filepath;
			File fileName = new File(filepath1);
			String absoultePath = fileName.getAbsoluteFile().toString();
			System.out.println(absoultePath);
			wait(driver, "5");
			coachFileuploadrobot(driver,absoultePath);
			wait(driver, "2");
			System.out.println("Entered mas");
			wait(driver, "4");
			try
			{
				waitForElement(driver, MEMBER_UPLOAD_SAVE1);
				jsClickByXPath(driver,MEMBER_UPLOAD_SAVE1);
				wait(driver, "5");
			}
			catch(NoSuchElementException e1)
			{
				Robot robot2 = new Robot();
				wait(driver, "5");
				robot2.keyPress(KeyEvent.VK_ENTER);
				robot2.keyRelease(KeyEvent.VK_ENTER);
				wait(driver, "5");
				waitForElement(driver, MEMBER_UPLOAD_SAVE1);
				jsClickByXPath(driver,MEMBER_UPLOAD_SAVE1);
				wait(driver, "5");
			}
		}
		else if(OSName.contains("MAC"))
		{
			wait(driver,"5");
			Navigate.waitTime(driver, "10");
			waitForElement(driver, PROVIDER_CHANGE_PIC);
			WebElement uploadphoto=driver.findElement(By.xpath(PROVIDER_CHANGE_PIC));
			actionClick(driver,uploadphoto);
			wait(driver,"5");
			System.out.println("Clicked upload photo on Provider change pic");
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
			// fileUploadRobot(driver,absoultePath);
			waitForAjax(driver);
			System.out.println("Uploaded the Image");
			Navigate.waitTime(driver, "5");
			wait(driver, "5");
			try
			{
				waitForElement(driver, MEMBER_UPLOAD_SAVE1);
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
	 * Name :     VINOTHKUMAR.M
	 * Created Date:   10/MAR/17
	 * Modified Date:
	 * Description : Create a common method to Member to coach via MDAC - DB records
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static String retrieveMDACProcessStatus(WebDriver driver) throws ParseException, ClassNotFoundException, SQLException
	{
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select * from MESSAGE_MDAC order by CREATED_DT desc");
		System.out.println("query executed");
		String mdacprocessStatus="";
		if(rs.next())
		{
			mdacprocessStatus = rs.getString("PROCESS_STATUS");
			System.out.println("Retrieve MDAC Process Status "+mdacprocessStatus);
		}
		return mdacprocessStatus;
	}
	
	/**
	   * Name :     VinothKumar. M
	   * Created Date:   14/Mar/17
	   * Modified Date:
	   * Description : Created a common method to Split the MDAC Response Text
	   * Ticket ID :
	   * Required Inputs :
	   * @throws ClassNotFoundException
	   * @throws SQLException
	   *
	   */
	  public static String realAppealCoachMDACResponseText(WebDriver driver,String ResponseText)
	  {

	   String[] Text=ResponseText.split("\\s");
	   String MDACResponseText=Text[2];
	   System.out.println("MDAC Response Text " +MDACResponseText);
	   return MDACResponseText;
	  }
		
	public static void clickUnavailabilityRedArea(WebDriver driver, WebElement webElement, int x,int y)
	{
		String OSName=Platform.OS.toUpperCase();
		  if(OSName.contains("WINDOWS"))
		  {
			  try {
				   System.out.println("Browser is " + Directory.browser);
				   Robot robot = new Robot();
				   robot.mouseMove(x,y);
				   wait(driver, "3");
				   robot.mousePress(InputEvent.BUTTON1_MASK);
				   wait(driver, "1");
				   robot.mouseRelease(InputEvent.BUTTON1_MASK);
				   wait(driver, "1");
				 
				  } catch (Exception e) {
				  // log.info("Could not click " + e.getStackTrace());
				  }
		  }
		  else if(OSName.contains("MAC"))
		  {
				if((Directory.browser).equalsIgnoreCase("chrome"))
				{
				Navigate.pageDown(driver);
				Robot robot;
				try {
					robot = new Robot();
					System.out.println("eeeentereed");
					robot.mousePress(InputEvent.BUTTON1_MASK);
					robot.mouseRelease(InputEvent.BUTTON1_MASK);
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Screen screen = new Screen();		
				Pattern logo = new Pattern(Directory.uploadFilePath+"Capture7.JPG");
				wait(driver, "5");
				
				try {
					screen.wait(logo, 7000);
					screen.click(logo);
					screen.wait(logo, 7000);
					WebElement removeUnavailability = driver.findElement(By.xpath("//a[text()='Edit / Remove Unavailability']"));
					}
				
				 catch (FindFailed e2) {
					 
				 }catch(Exception e)
				{
					 System.out.println("Could not find red area when scrolled down");
					 try {
						    Navigate.pageUp(driver);   
						    screen.wait(logo, 7000);
							screen.click(logo);
							screen.wait(logo, 7000);
							WebElement removeUnavailability = driver.findElement(By.xpath("//a[text()='Edit / Remove Unavailability']"));
						}
						catch (FindFailed e3) {
							 System.out.println("Could not find red area when scrolled up");
						 
					 }
				}
					 	
				}
				
				
			}
				 else if(((Directory.browser).equalsIgnoreCase("firefox")))
				{
					Navigate.pageDown(driver);
					Robot robot;
				
					Screen screen = new Screen();		
					Pattern logo = new Pattern(Directory.uploadFilePath+"Capture7.JPG");
					wait(driver, "5");
					
					try {
						screen.wait(logo, 7000);
						screen.click(logo);
						screen.wait(logo, 7000);}
					catch(Exception e4)
					{
						
					}
				}
		     }		
		  

	
	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:   30/MAR/17
	 * Modified Date:
	 * Description : Create a common method to retrieve Member whose group session has just started and is in progress
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static String retrieveMemberWhoseGroupSessionIsInProgress(WebDriver driver,String testData) throws ParseException, ClassNotFoundException, SQLException
	{
		String[] testData1 = testData.split(",");
		String inputSessionStatus=testData1[0];
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select ACNT.EMAIL, CE.START_DT_TIME FROM CALENDAR_EVENT CE , CALENDAR_EVENT_ATTENDEE CEA, ACCOUNT ACNT WHERE CE.ID= CEA.EVENT_ID AND ACNT.ID= CEA.ACCOUNT_ID AND CE.SESSION_TYPE_ID= '01' AND CE.SESSION_STATUS= '"+inputSessionStatus+"' AND CE.START_DT<SYSDATE+ '10' AND ACNT.EMAIL not like '%api%' ORDER BY CE. START_DT DESC");
		System.out.println("query executed");
		String retrieveMemberEmailWhoseGroupSessionIsInProgress="";
		if(rs.next())
		{
			retrieveMemberEmailWhoseGroupSessionIsInProgress = rs.getString("EMAIL");
			System.out.println("Available Member Email"+retrieveMemberEmailWhoseGroupSessionIsInProgress);
		}
		return retrieveMemberEmailWhoseGroupSessionIsInProgress;
	}
	
	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:  04/APR/17
	 * Modified Date:
	 * Description : Create a common method to retrieve Member who has missed group session and can schedule makeup session
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static String retrieveMemberWhoseHasMissedGroupSession(WebDriver driver,String testData) throws ParseException, ClassNotFoundException, SQLException
	{
		String[] testData1 = testData.split(",");
		String inputSessionStatus=testData1[0];
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select ACNT.EMAIL, CE.START_DT_TIME FROM CALENDAR_EVENT CE , CALENDAR_EVENT_ATTENDEE CEA, ACCOUNT ACNT WHERE CE.ID= CEA.EVENT_ID AND ACNT.ID= CEA.ACCOUNT_ID AND CE.SESSION_TYPE_ID IN ('01','02') AND CE.SESSION_STATUS= '"+inputSessionStatus+"' AND CE.START_DT<SYSDATE  and ACNT.STATUS IN ('Active') AND ACNT.EMAIL  not like '%QAmember%-default@yopmail.com%' AND ACNT.EMAIL not like '%api%' ORDER BY CE. START_DT DESC");
		System.out.println("query executed");
		String retrieveMemberEmailWhoseHasMissedGroupSession="";
		if(rs.next())
		{
			retrieveMemberEmailWhoseHasMissedGroupSession = rs.getString("EMAIL");
			System.out.println("Available Member Email "+retrieveMemberEmailWhoseHasMissedGroupSession);
		}
		return retrieveMemberEmailWhoseHasMissedGroupSession;
	}
	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:   10/APR/17
	 * Modified Date:
	 * Description : Create a common method to Maximum no of participants for a makeup session
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static String retrieveMaximumMakeUpAttendees(WebDriver driver) throws ParseException, ClassNotFoundException, SQLException
	{
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select MAXIMUM_MAKEUP_ATTENDEES from mp_a_master_program where Program_name='RA - Real Appeal' and id='02'");
		System.out.println("query executed");
		String maximumMakeupAttendees="";
		int inputDefaultMakeupAttendees= 15;
		while(rs.next())
		{
			maximumMakeupAttendees = rs.getString("MAXIMUM_MAKEUP_ATTENDEES");
		}
		System.out.println("Retrieve Maximum no participants for a makeup session: "+maximumMakeupAttendees);
		int maximumMakeupAttendees1 = Integer.parseInt( maximumMakeupAttendees );
		if(inputDefaultMakeupAttendees!=maximumMakeupAttendees1)
		{
			System.out.println("Default Makeup attendees value is not matched with DB Maximum Makeup Attendees:"+maximumMakeupAttendees1);
		}
		return maximumMakeupAttendees;
		
	}
	
	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:   10/APR/17
	 * Modified Date:
	 * Description : Create a common method to check if the makeup session is configured for RA-Real Appeal Group session
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static String checkIfTheMakeUpSessionIsConfiguredForRAGroupSession(WebDriver driver) throws ParseException, ClassNotFoundException, SQLException
	{
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select * from mp_session_type where MAST_PROGRAM_ID='02' and PARTICIPATION_MODE='Group'");
		System.out.println("query executed");
		String isAllowMakeup="";
		String isAllowMemberMakeup="";
		String isAllowProviderMakeup="";
		int inputDefaultisAllowMakeup= 1;
		int inputDefaultisAllowMemberMakeup= 1;
		int inputDefaultisAllowProviderMakeup= 0;
		while(rs.next())
		{
			isAllowMakeup = rs.getString("IS_ALLOW_MAKEUP");
			isAllowMemberMakeup = rs.getString("IS_ALLOW_MEMBER_MAKEUP");
			isAllowProviderMakeup = rs.getString("IS_ALLOW_PROVIDER_MAKEUP");
		}
		int isAllowMakeup1 = Integer.parseInt( isAllowMakeup );
		if(inputDefaultisAllowMakeup==isAllowMakeup1)
		{
			System.out.println("The value of 'IS_ALLOW_MAKEUP' column should be '1' by default for group session: "+isAllowMakeup1);
		}
		
		int isAllowMemberMakeup1 = Integer.parseInt( isAllowMemberMakeup );
		if(inputDefaultisAllowMemberMakeup==isAllowMemberMakeup1)
		{
			System.out.println("The value of 'IS_ALLOW_MEMBER_MAKEUP' column should be '1' by default for group session: "+isAllowMemberMakeup1);
		}
		int isAllowProviderMakeup1 = Integer.parseInt( isAllowProviderMakeup );
		if(inputDefaultisAllowProviderMakeup==isAllowProviderMakeup1)
		{
			System.out.println(" The value of 'IS_ALLOW_PROVIDER_MAKEUP' column should be '0' by default for group session since the provider should not be allowed to schedule makeup for member: "+isAllowProviderMakeup1);
		}
		return isAllowMakeup;
		
	} 
	
	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:   10/APR/17
	 * Modified Date:
	 * Description : Create a common method to Check if the makeup is not configured for any session type other than RA-Real Appeal group
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static String checkIfTheMakeUpSessionIsNotConfiguredForAnySessionOtherThanRAGroupSession(WebDriver driver) throws ParseException, ClassNotFoundException, SQLException
	{
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select  is_allow_makeup, is_allow_member_makeup,  mast_program_id, abbreviated_name from mp_session_type where abbreviated_name !='Group'");
		System.out.println("query executed");
		String isAllowMakeup="";
		String isAllowMemberMakeup="";
		int inputDefaultisAllowMakeup= 0;
		int inputDefaultisAllowMemberMakeup= 0;
		
		while(rs.next())
		{
			isAllowMakeup = rs.getString("IS_ALLOW_MAKEUP");
			isAllowMemberMakeup = rs.getString("IS_ALLOW_MEMBER_MAKEUP");
		}
		int isAllowMakeup1 = Integer.parseInt( isAllowMakeup );
		if(inputDefaultisAllowMakeup==isAllowMakeup1)
		{
			System.out.println("For all session types other than RA-Group, the value of 'IS_ALLOW_MAKEUP' should be 0: "+isAllowMakeup1);
		}
		
		int isAllowMemberMakeup1 = Integer.parseInt( isAllowMemberMakeup );
		if(inputDefaultisAllowMemberMakeup==isAllowMemberMakeup1)
		{
			System.out.println("For all session types other than RA-Group, the value of 'IS_ALLOW_MEMBER_MAKEUP' should be 0: "+isAllowMemberMakeup1);
		}
		return isAllowMakeup;
		
	}
}

