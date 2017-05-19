package com.zillion.qa.coaches;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.zillion.qa.commands.ElementActions;
import com.zillion.qa.commands.Manipulation;
import com.zillion.qa.commands.Navigate;
import com.zillion.qa.utils.Directory;
public class Classes extends Manipulation implements OR
{
	/**
	 * Name :     Abinaya.P
	 * Created Date:   01/02/2016
	 * Modified Date:
	 * Description :   Create a common method to validate upcoming sessions
	 * Ticket ID :
	 * Required Inputs :  No Inputs Required
	 * @throws ParseException
	 */
	public  static void validateUpcomingSessions(WebDriver driver) throws ParseException
	{
		int size=driver.findElements(By.xpath("//tbody[@id='sessionClasses_tableBody']//tr[*]")).size();
		if(size>2)
		{
			for(int i=1;i<size;i++)
			{
				String sessiondate3="";
				String sessiondate=driver.findElement(By.xpath("//tbody[@id='sessionClasses_tableBody']//tr["+i+"]//td[@id='sessionClasses_tdDate"+(i-1)+"']")).getText();
				if(sessiondate.contains("th"))
				{
					String[] sessiondate1=sessiondate.split("th");
					sessiondate3=sessiondate1[0]+sessiondate1[1];
				}
				else if (sessiondate.contains("st"))
				{
					String[] sessiondate1=sessiondate.split("st");
					sessiondate3=sessiondate1[0]+sessiondate1[1];
				}
				else if(sessiondate.contains("rd"))
				{
					String[] sessiondate1=sessiondate.split("rd");
					sessiondate3=sessiondate1[0]+sessiondate1[1];
				}
				else if(sessiondate.contains("nd"))
				{
					String[] sessiondate1=sessiondate.split("nd");
					sessiondate3=sessiondate1[0]+sessiondate1[1];
				}
				DateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
				Date sessiondate4 = dateFormat.parse(sessiondate3);
				Calendar cal = Calendar.getInstance();
				String systemdate=dateFormat.format(cal.getTime());
				Date systemdate1 = dateFormat.parse(systemdate);
				if(sessiondate4.compareTo(systemdate1)>0)
				{
					System.out.println("Upcoming sessions date is verified accross current date");
				}
				else
				{
					Assert.fail();
				}
			}
		}
		else
		{
			WebElement coach_no_session= driver.findElement(By.xpath(OR.COACH_NOSESSION_MSG));
			System.out.println(coach_no_session.getText());
		}
	}
	/**
	 * Name :     Abinaya.P
	 * Created Date:   01/02/2016
	 * Modified Date:
	 * Description :   Create a common method to validate upcoming sessions
	 * Ticket ID :
	 * Required Inputs :  No Inputs Required
	 * @throws ParseException
	 */
	public  static void validatePreviousSessions(WebDriver driver) throws ParseException
	{
		int size=driver.findElements(By.xpath("//tbody[@id='sessionClasses_tableBody']//tr[*]")).size();
		if(size>2)
		{
			for(int i=1;i<size;i++)
			{
				String sessiondate3="";
				String sessiondate=driver.findElement(By.xpath("//tbody[@id='sessionClasses_tableBody']//tr["+i+"]//td[@id='sessionClasses_tdDate"+(i-1)+"']")).getText();
				if(sessiondate.contains("th"))
				{
					String[] sessiondate1=sessiondate.split("th");
					sessiondate3=sessiondate1[0]+sessiondate1[1];
				}
				else if (sessiondate.contains("st"))
				{
					String[] sessiondate1=sessiondate.split("st");
					sessiondate3=sessiondate1[0]+sessiondate1[1];
				}
				else if(sessiondate.contains("rd"))
				{
					String[] sessiondate1=sessiondate.split("rd");
					sessiondate3=sessiondate1[0]+sessiondate1[1];
				}
				else if(sessiondate.contains("nd"))
				{
					String[] sessiondate1=sessiondate.split("nd");
					sessiondate3=sessiondate1[0]+sessiondate1[1];
				}
				DateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
				Date sessiondate4 = dateFormat.parse(sessiondate3);
				Calendar cal = Calendar.getInstance();
				String systemdate=dateFormat.format(cal.getTime());
				Date systemdate1 = dateFormat.parse(systemdate);
				if(sessiondate4.compareTo(systemdate1)<0)
				{
					System.out.println("Previous sessions date is verified accross current date");
				}
				else
				{
					Assert.fail();
				}
			}
		}
		else
		{
			WebElement coach_no_session= driver.findElement(By.xpath(OR.COACH_NOSESSION_MSG));
			System.out.println(coach_no_session.getText());
		}
	}
	/**
	 * Name :     Abinaya.P
	 * Created Date:   05/02/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve provider id from provider table
	 * Ticket ID :
	 * Required Inputs :  No Inputs Required
	 * @throws ParseException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String providerId="";
	public  static String retrieveProviderId(WebDriver driver,String inputData) throws ParseException, ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select * from Provider where email like '"+inputData+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			providerId= rs.getString("ID");
			System.out.println("Provider id "+providerId+" is retrieved from the Table");
		}
		return providerId;
	}
	/**
	 * Name :     Abinaya.P
	 * Created Date:   05/02/2016
	 * Modified Date:
	 * Description :   Create a common method to verify the approval status for 1 on 1 session in coach portal
	 * Ticket ID :
	 * Required Inputs :  No Inputs Required
	 * @throws ParseException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static int coachApprovedStatus1on1Session(WebDriver driver) throws ParseException, ClassNotFoundException, SQLException
	{
		int approvedStatus = 0;
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
		ResultSet rs = stat.executeQuery("select IS_APPROVED from mp_provider_approved where session_type_id= '30100AA52BEAEE0EE1'and provider_id= '"+providerId+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			approvedStatus= rs.getInt("IS_APPROVED");
			System.out.println(approvedStatus);
		}
		if(approvedStatus==1)
		{
			System.out.println("Verified the approved status of 1 on 1 session from coach portal ");
		}
		else
		{
			System.out.println("Verified the unapproved status of 1 on 1 session from coach portal");
			Assert.fail();
		}
		return approvedStatus;
	}
	/**
	 * Name :     Abinaya.P
	 * Created Date:   06/02/2016
	 * Modified Date:
	 * Description :   Create a common method to verify the un approval status for 1 on 1 session in coach portal
	 * Ticket ID :
	 * Required Inputs :  No Inputs Required
	 * @throws ParseException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static int coachUnapprovedStatus1on1Session(WebDriver driver) throws ParseException, ClassNotFoundException, SQLException
	{
		int approvedStatus = 0;
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
		ResultSet rs = stat.executeQuery("select IS_APPROVED from mp_provider_approved where session_type_id= '30100AA52BEAEE0EE1'and provider_id= '"+providerId+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			approvedStatus= rs.getInt("IS_APPROVED");
			System.out.println(approvedStatus);
		}
		if(approvedStatus==0)
		{
			System.out.println("Verified the unapproved status  of 1 on 1 session from coach portal");
		}
		else
		{
			System.out.println("Verified the approved status  of 1 on 1 session from coach portal");
			Assert.fail();
		}
		return approvedStatus;
	}
	/**
	 * Name :     Abinaya.P
	 * Created Date:   05/02/2016
	 * Modified Date:
	 * Description :   Create a common method to verify the approval status for Lecture session in coach portal
	 * Ticket ID :
	 * Required Inputs :  No Inputs Required
	 * @throws ParseException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static int coachApprovedStatusLectureSession(WebDriver driver) throws ParseException, ClassNotFoundException, SQLException
	{
		int approvedStatus = 0;
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
		ResultSet rs = stat.executeQuery("select IS_APPROVED from mp_provider_approved where session_type_id= '30100AA52BEAEE0EE3'and provider_id= '"+providerId+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			approvedStatus= rs.getInt("IS_APPROVED");
			System.out.println(approvedStatus);
		}
		if(approvedStatus==1)
		{
			System.out.println("Verified the approved status of Lecture session from coach portal");
		}
		else
		{
			System.out.println("Verified the unapproved status of Lecture session from coach portal");
			Assert.fail();
		}
		return approvedStatus;
	}
	/**
	 * Name :     Abinaya.P
	 * Created Date:   05/02/2016
	 * Modified Date:
	 * Description :   Create a common method to verify the approval status for Lecture session in coach portal
	 * Ticket ID :
	 * Required Inputs :  No Inputs Required
	 * @throws ParseException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static int coachUnapprovedStatusLectureSession(WebDriver driver) throws ParseException, ClassNotFoundException, SQLException
	{
		int approvedStatus = 0;
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
		ResultSet rs = stat.executeQuery("select IS_APPROVED from mp_provider_approved where session_type_id= '30100AA52BEAEE0EE3'and provider_id= '"+providerId+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			approvedStatus= rs.getInt("IS_APPROVED");
			System.out.println(approvedStatus);
		}
		if(approvedStatus==0)
		{
			System.out.println("Verified the approved status of Lecture session from coach portal");
		}
		else
		{
			System.out.println("Verified the unapproved status of Lecture session from coach portal");
			Assert.fail();
		}
		return approvedStatus;
	}
	/**
	 * Name :     Abinaya.P
	 * Created Date:   05/02/2016
	 * Modified Date:
	 * Description :   Create a common method to verify the approved status of the coach
	 * Ticket ID :
	 * Required Inputs :  No Inputs Required
	 * @throws ParseException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static int verifyApprovedStatusOfSession (WebDriver driver,String providerId1) throws ParseException, ClassNotFoundException, SQLException
	{
		int approvedStatus = 0;
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
		ResultSet rs = stat.executeQuery("select IS_APPROVED from mp_provider_approved where session_type_id= '30100AA52BEAEE0EE1'and provider_id= '"+providerId1+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			approvedStatus= rs.getInt("IS_APPROVED");
			System.out.println(approvedStatus);
		}
		return approvedStatus;
	}
	/**
	 * Name :     LEENA P.
	 * Created Date:   11/JULY/16
	 * Modified Date:
	 * Description : Create a common method to verify the mail of the coach once the 1on1 session is scheduled/rescheduled
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void verifyorberaCoachMail1on1Session(WebDriver driver, String mailid)
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
				try
				{
					WebElement generatedLink= driver.findElement(By.xpath(OR.ORBERA_COACH_1on1_MAIL_SCHEDULED));
					verifyElementIsPresent(driver, generatedLink);
					com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_COACH_1on1_MAIL_SCHEDULED);
					wait(driver, "5");
					if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
					{
						driver.get("javascript:document.getElementById('overridelink').click();");
						wait(driver, "5");
					}
					jsClickByXPath(driver, OR.ORBERA_COACH_1on1_MAIL_SCHEDULED);
					wait(driver, "3");
					Navigate.switchToDefaultFrame(driver);
					WebElement iframe2= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME2));
					Navigate.switchToFrame(driver, iframe2);
					waitForElement(driver, OR.ORBERA_COACH_1on1_CONTENT_SCHEDULED);
				}
				catch(Exception e)
				{
					WebElement generatedLink= driver.findElement(By.xpath(OR.ORBERA_COACH_1on1_MAIL));
					verifyElementIsPresent(driver, generatedLink);
					com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_COACH_1on1_MAIL);
					wait(driver, "5");
					if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
					{
						driver.get("javascript:document.getElementById('overridelink').click();");
						wait(driver, "5");
					}
					jsClickByXPath(driver, OR.ORBERA_COACH_1on1_MAIL);
					wait(driver, "3");
					Navigate.switchToDefaultFrame(driver);
					WebElement iframe2= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME2));
					Navigate.switchToFrame(driver, iframe2);
					waitForElement(driver, OR.ORBERA_COACH_1on1_CONTENT);
				}
				Navigate.switchToDefaultFrame(driver);
				WebElement iframe1= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME1));
				System.out.println("switched to frame" +iframe1);
				Navigate.switchToFrame(driver, iframe);
				wait(driver, "3");
				deleteYopmailMsg(driver);
				Navigate.switchToDefaultFrame(driver);
				Navigate.closeTab(driver);
				driver.switchTo().window(First_Window);
			}
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
				wait(driver, "15");
				try
				{
				}
				catch(Exception e) {
					wait(driver, "15");
				}
				WebElement generatedLink= driver.findElement(By.xpath(OR.ORBERA_COACH_1on1_MAIL_GR));
				String grSubject = ElementActions.getText(generatedLink);
				System.out.println("Subject line is" +grSubject);
				if(grSubject.contains("Session Scheduled: 1-on-1")) {
					com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_COACH_1on1_MAIL_GR);
					wait(driver, "15");
					if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
					{
						driver.get("javascript:document.getElementById('overridelink').click();");
						wait(driver, "5");
					}
					jsClickByXPath(driver, OR.ORBERA_COACH_1on1_MAIL_GR);
					wait(driver, "3");
					waitForElement(driver, OR.ORBERA_COACH_1on1_CONTENT_SCHEDULED);
				}
				else if(grSubject.contains("1-on-1 Session Rescheduled")) {
					com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_COACH_1on1_MAIL_GR);
					wait(driver, "15");
					if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
					{
						driver.get("javascript:document.getElementById('overridelink').click();");
						wait(driver, "5");
					}
					jsClickByXPath(driver, OR.ORBERA_COACH_1on1_MAIL_GR);
					wait(driver, "3");
					waitForElement(driver, OR.ORBERA_COACH_1on1_CONTENT);
				}
				else
				{
					Assert.fail();
				}
				wait(driver, "3");
				WebElement grInboxButton = driver.findElement(By.xpath(OR.GUERRILLA_MAIL_INBOX_BUTTON));
				Manipulation.click(grInboxButton);
				wait(driver, "3");
				WebElement grEmailCheckBox = driver.findElement(By.xpath(OR.GUERRILLA_MAIL_EMAIL_CHECKBOX));
				Manipulation.click(grEmailCheckBox);
				wait(driver, "3");
				WebElement grDelButton = driver.findElement(By.xpath(OR.GUERRILLA_MAIL_DELETE_BUTTON));
				Manipulation.click(grDelButton);
				wait(driver, "3");
				Navigate.closeTab(driver);
				driver.switchTo().window(First_Window);
			}
		}
	}
	/**
	 * Name :     LEENA P.
	 * Created Date:   11/JULY/16
	 * Modified Date:
	 * Description : Create a common method to verify the mail of the coach once the 101 session is cancelled
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void verifyorberaCoachMail1on1SessionCancellation(WebDriver driver, String mailid)
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
				WebElement generatedLink= driver.findElement(By.xpath(OR.ORBERA_COACH_1on1_CANCELlATION_MAIL));
				verifyElementIsPresent(driver, generatedLink);
				com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_COACH_1on1_CANCELlATION_MAIL);
				wait(driver, "5");
				if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
				{
					driver.get("javascript:document.getElementById('overridelink').click();");
					wait(driver, "5");
				}
				jsClickByXPath(driver, OR.ORBERA_COACH_1on1_CANCELlATION_MAIL);
				wait(driver, "3");
				Navigate.switchToDefaultFrame(driver);
				WebElement iframe2= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME2));
				Navigate.switchToFrame(driver, iframe2);
				waitForElement(driver, OR.ORBERA_COACH_1on1_CANCELLATION_CONTENT);
			}
			Navigate.switchToDefaultFrame(driver);
			WebElement iframe= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME1));
			Navigate.switchToFrame(driver, iframe);
			deleteYopmailMsg(driver);
			Navigate.switchToDefaultFrame(driver);
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
				wait(driver, "15");
				WebElement generatedLink = driver.findElement(By.xpath(OR.ORBERA_COACH_1on1_MAIL_GR));
				String grSubject = ElementActions.getText(generatedLink);
				System.out.println("Cancellation message is" +grSubject);
				if(grSubject.contains("Session Canceled")) {
					com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_COACH_1on1_MAIL_GR);
					wait(driver, "5");

					if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
					{
						driver.get("javascript:document.getElementById('overridelink').click();");
						wait(driver, "5");
					}
					jsClickByXPath(driver, OR.ORBERA_COACH_1on1_MAIL_GR);
					wait(driver, "3");
					waitForElement(driver, OR.ORBERA_COACH_1on1_CANCELLATION_CONTENT);
				}
				else
				{
					Assert.fail();
				}
			}
			Navigate.closeTab(driver);
			driver.switchTo().window(First_Window);
		}
	}
	/**
	 * Name :     LEENA P.
	 * Created Date:   11/JULY/16
	 * Modified Date:
	 * Description : Create a common method to verify the mail of the coach once the 101 session is scheduled
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void verifyorberaCoachMailLectureSession(WebDriver driver, String mailid)
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
				WebElement generatedLink= driver.findElement(By.xpath(OR.ORBERA_COACH_LECTURE_MAIL));
				verifyElementIsPresent(driver, generatedLink);
				com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_COACH_LECTURE_MAIL);
				wait(driver, "5");
				if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
				{
					driver.get("javascript:document.getElementById('overridelink').click();");
					wait(driver, "5");
				}
				jsClickByXPath(driver, OR.ORBERA_COACH_LECTURE_MAIL);
				wait(driver, "3");
				Navigate.switchToDefaultFrame(driver);
				WebElement iframe2= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME2));
				Navigate.switchToFrame(driver, iframe2);
				waitForElement(driver, OR.ORBERA_COACH_LECTURE_CONTENT);
				Navigate.switchToDefaultFrame(driver);
			}
			WebElement iframe= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME1));
			Navigate.switchToFrame(driver, iframe);
			deleteYopmailMsg(driver);
			Navigate.switchToDefaultFrame(driver);
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
				try
				{
				}
				catch(Exception e) {
					wait(driver, "15");
				}
				WebElement generatedLink= driver.findElement(By.xpath(OR.ORBERA_COACH_1on1_MAIL_GR));
				String grSubject = ElementActions.getText(generatedLink);
				System.out.println("Subject line is" +grSubject);
				if(grSubject.contains("Lecture Session Confirmation")) {
					com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_COACH_1on1_MAIL_GR);
					wait(driver, "15");
					if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
					{
						driver.get("javascript:document.getElementById('overridelink').click();");
						wait(driver, "5");
					}
					jsClickByXPath(driver, OR.ORBERA_COACH_1on1_MAIL_GR);
					wait(driver, "5");
					waitForElement(driver, OR.ORBERA_COACH_LECTURE_CONTENT);
				}
				else
				{
					Assert.fail();
				}
			}
			wait(driver, "3");
			WebElement grInboxButton = driver.findElement(By.xpath(OR.GUERRILLA_MAIL_INBOX_BUTTON));
			Manipulation.click(grInboxButton);
			wait(driver, "3");
			WebElement grEmailCheckBox = driver.findElement(By.xpath(OR.GUERRILLA_MAIL_EMAIL_CHECKBOX));
			Manipulation.click(grEmailCheckBox);
			wait(driver, "3");
			WebElement grDelButton = driver.findElement(By.xpath(OR.GUERRILLA_MAIL_DELETE_BUTTON));
			Manipulation.click(grDelButton);
			wait(driver, "3");
			Navigate.closeTab(driver);
			driver.switchTo().window(First_Window);
		}
	}
	/**
	 * Name :     LEENA P.
	 * Created Date:   11/JULY/16
	 * Modified Date:
	 * Description : Create a common method to verify the mail of the coach once the 101 session is scheduled
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void verifyorberaSubstituteCoachEMailConfirmation(WebDriver driver, String mailid)
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
				wait(driver, "5");
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
				try
				{
					WebElement generatedLink= driver.findElement(By.xpath(OR.ORBERA_COACH_SUBSTITUTE_MAIL));
					verifyElementIsPresent(driver, generatedLink);
					com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_COACH_SUBSTITUTE_MAIL);
					wait(driver, "5");
					if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
					{
						driver.get("javascript:document.getElementById('overridelink').click();");
						wait(driver, "5");
					}
					jsClickByXPath(driver, OR.ORBERA_COACH_SUBSTITUTE_MAIL);
					wait(driver, "3");
					Navigate.switchToDefaultFrame(driver);
					WebElement iframe2= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME2));
					Navigate.switchToFrame(driver, iframe2);
					waitForElement(driver, OR.ORBERA_COACH_SUBSTITUTE_EMAIL_CONTENT);
				}
				catch(Exception e)
				{
				}
				Navigate.switchToDefaultFrame(driver);
			}
			WebElement iframe= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME1));
			Navigate.switchToFrame(driver, iframe);
			Navigate.switchToDefaultFrame(driver);
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
				try
				{
					WebElement generatedLink= driver.findElement(By.xpath(OR.ORBERA_COACH_SUBSTITUTE_MAIL));
					verifyElementIsPresent(driver, generatedLink);
					com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_COACH_SUBSTITUTE_MAIL);
					wait(driver, "5");
					if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
					{
						driver.get("javascript:document.getElementById('overridelink').click();");
						wait(driver, "5");
					}
					jsClickByXPath(driver, OR.ORBERA_COACH_SUBSTITUTE_MAIL);
					wait(driver, "3");
					waitForElement(driver, OR.ORBERA_COACH_SUBSTITUTE_EMAIL_CONTENT);
				}
				catch(Exception e)
				{
				}
			}
			Navigate.closeTab(driver);
			driver.switchTo().window(First_Window);
		}
	}
	/**
	 * Name :     LEENA P.
	 * Created Date:   14/SEP/16
	 * Modified Date:
	 * Description : Create a common method to delete 'this' message from yopmail
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void deleteYopmailMsg(WebDriver driver)
	{
		waitForElement(driver, DELETE_ALL_BUTTON);
		WebElement yopmailSelectDelete = driver.findElement(By.xpath(OR.DELETE_ALL_BUTTON));
		Manipulation.mouseOver(driver, yopmailSelectDelete);
		waitForElement(driver, DELETE_ALL_BUTTON);
		Manipulation.jsClickByXPath(driver, OR.DELETE_ALL_BUTTON);
		wait(driver, "3");
		Manipulation.jsClickByXPath(driver, OR.YOPMAIL_SELECT_FIRST_MESSAGE);
		wait(driver, "3");
		System.out.println("deleted this msg");
	}
	/**
	 * Name :     Abinaya.P
	 * Created Date:   06/02/2016
	 * Modified Date:
	 * Description :   Create a common method to verify the un approval status for 1 on 1 session in coach portal
	 * Ticket ID :
	 * Required Inputs :  No Inputs Required
	 * @throws ParseException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static int coachUnapprovedStatus1on1SessionByPA(WebDriver driver) throws ParseException, ClassNotFoundException, SQLException
	{
		int approvedStatus = 0;
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
		ResultSet rs = stat.executeQuery("select IS_APPROVED from mp_provider_approved where session_type_id= '39448FC1B9923573E053A5121FAC4CA5'and provider_id= '"+providerId+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			approvedStatus= rs.getInt("IS_APPROVED");
			System.out.println(approvedStatus);
		}
		if(approvedStatus==0)
		{
			System.out.println("Verified the unapproved status  of 1 on 1 session from coach portal");
		}
		else
		{
			System.out.println("Verified the approved status  of 1 on 1 session from coach portal");
			Assert.fail();
		}
		return approvedStatus;
	}
	/**
	 * Name :     Abinaya.P
	 * Created Date:   05/02/2016
	 * Modified Date:
	 * Description :   Create a common method to verify the approval status for 1 on 1 session in coach portal
	 * Ticket ID :
	 * Required Inputs :  No Inputs Required
	 * @throws ParseException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static int coachApprovedStatus1on1SessionByPA(WebDriver driver) throws ParseException, ClassNotFoundException, SQLException
	{

		int approvedStatus = 0;
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
		ResultSet rs = stat.executeQuery("select IS_APPROVED from mp_provider_approved where session_type_id= '39448FC1B9923573E053A5121FAC4CA5'and provider_id= '"+providerId+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			approvedStatus= rs.getInt("IS_APPROVED");
			System.out.println(approvedStatus);
		}
		if(approvedStatus==1)
		{
			System.out.println("Verified the approved status of 1 on 1 session from coach portal ");
		}
		else
		{
			System.out.println("Verified the unapproved status of 1 on 1 session from coach portal");
			Assert.fail();
		}
		return approvedStatus;
	}
	/**
	 * Name :     ABINAYA
	 * Created Date:   22/NOV/16
	 * Modified Date:
	 * Description : Create a common method to verify the mail of the coach once the 101 session is scheduled
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void verifyorberaPAMailLectureSession(WebDriver driver, String mailid)
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
				WebElement generatedLink= driver.findElement(By.xpath(OR.ORBERA_PA_LECTURE_MAIL));
				verifyElementIsPresent(driver, generatedLink);
				com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_PA_LECTURE_MAIL);
				wait(driver, "5");
				if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
				{
					driver.get("javascript:document.getElementById('overridelink').click();");
					wait(driver, "5");
				}
				jsClickByXPath(driver, OR.ORBERA_PA_LECTURE_MAIL);
				wait(driver, "3");
				Navigate.switchToDefaultFrame(driver);
				WebElement iframe2= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME2));
				Navigate.switchToFrame(driver, iframe2);
				waitForElement(driver, OR.ORBERA_PA_LECTURE_CONTENT);
				Navigate.switchToDefaultFrame(driver);
			}
			WebElement iframe= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME1));
			Navigate.switchToFrame(driver, iframe);
			deleteYopmailMsg(driver);
			Navigate.switchToDefaultFrame(driver);
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
				try
				{
				}
				catch(Exception e) {
					wait(driver, "15");
				}
				WebElement generatedLink= driver.findElement(By.xpath(OR.ORBERA_COACH_1on1_MAIL_GR));
				String grSubject = ElementActions.getText(generatedLink);
				System.out.println("Subject line is" +grSubject);
				if(grSubject.contains("Lecture Session Confirmation")) {
					com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_COACH_1on1_MAIL_GR);
					wait(driver, "15");
					if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
					{
						driver.get("javascript:document.getElementById('overridelink').click();");
						wait(driver, "5");
					}
					jsClickByXPath(driver, OR.ORBERA_COACH_1on1_MAIL_GR);
					wait(driver, "3");
					waitForElement(driver, OR.ORBERA_COACH_LECTURE_CONTENT);
				}
				else {
					Assert.fail();
				}
			}
			wait(driver, "3");
			WebElement grInboxButton = driver.findElement(By.xpath(OR.GUERRILLA_MAIL_INBOX_BUTTON));
			Manipulation.click(grInboxButton);
			wait(driver, "3");
			WebElement grEmailCheckBox = driver.findElement(By.xpath(OR.GUERRILLA_MAIL_EMAIL_CHECKBOX));
			Manipulation.click(grEmailCheckBox);
			wait(driver, "3");
			WebElement grDelButton = driver.findElement(By.xpath(OR.GUERRILLA_MAIL_DELETE_BUTTON));
			Manipulation.click(grDelButton);
			wait(driver, "3");
			Navigate.closeTab(driver);
			driver.switchTo().window(First_Window);
		}
	}
}