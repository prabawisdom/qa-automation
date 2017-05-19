package com.zillion.qa.member;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.zillion.qa.ZadoReports;
import com.zillion.qa.commands.Manipulation;
import com.zillion.qa.commands.Navigate;
import com.zillion.qa.enums.LogAs;
import com.zillion.qa.reports.CaptureScreen;
import com.zillion.qa.reports.CaptureScreen.ScreenshotOf;
import com.zillion.qa.utils.Directory;
import com.zillion.qa.utils.Platform;
public class liveSessionSubCommonMethods extends Manipulation implements OR
{
	/**
	 * Name :VinothKumar.M
	 * Created Date:10/Mar/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve Member account ID
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String accountId="";
	// Retrieve account ID from the DB with the Member Username
	// Member Username is given as input from the Spreadsheet
	// With the help of the accont Id we can retrieve Program ID
	public  static String retrieveMemberAccountId(WebDriver driver, String testData) throws ParseException, ClassNotFoundException, SQLException
	{
		System.out.println("testData"+testData);
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
		ResultSet rs = stat.executeQuery("SELECT ID, NAME, EMAIL, ORGANIZATION_ID, STATUS FROM ACCOUNT WHERE EMAIL LIKE'"+member_mail1+"'");
		System.out.println("query executed");
		String Accountid1="";
		while(rs.next())
		{
			Accountid1= rs.getString("ID");
			System.out.println("Member Account id "+Accountid1+" is retrieved from the Table");
		}
		return Accountid1;
	}
	/**
	 * Name :VinothKumar.M
	 * Created Date:10/Mar/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve Member account ID
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// Retrieve account ID from the DB with the Member Username
	// Member Username is given as input from the Spreadsheet
	// With the help of the accont Id we can retrieve Program ID
	public  static String retrieveLectureSessionMemberAccountId(WebDriver driver, String testData) throws ParseException, ClassNotFoundException, SQLException
	{
		String[] testData1 = testData.split(",");
		String inputUsername=testData1[1];
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
		while(rs.next())
		{
			accountId= rs.getString("ID");
			System.out.println("Member Account id "+accountId+" is retrieved from the Table");
		}
		return accountId;
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
	public static String accountProgramID ="";
	public static String retrieveAccountProgramID(WebDriver driver,String pgmid) throws ClassNotFoundException, SQLException
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
		System.out.println(accountId);
		ResultSet rs = stat.executeQuery("SELECT ID, MAST_PROGRAM_ID, START_DT_TIME, END_DT_TIME FROM ACCOUNT_PROGRAM WHERE ACCOUNT_ID='"+pgmid+"' AND IS_ACTIVE=1");
		System.out.println("query executed");
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
	public static String retrieve1on1SessionHostNameWithProgramID(WebDriver driver, String accountProgramID) throws ClassNotFoundException, SQLException
	{
		String coachWithHostName1on1session ="";
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("SELECT EVT.ID, EVT.HOST_ID, EVT.HOST_NAME FROM ACCOUNT_PROGRAM_SESSION_DETAIL PSD, CALENDAR_EVENT EVT WHERE EVT.ID=PSD.CALENDAR_EVENT_ID AND ACCOUNT_PROGRAM_ID='"+accountProgramID+"' AND PSD.SESSION_STATUS='Scheduled' AND PSD.SESSION_TYPE_ID='30100AA52BEAEE0EE1'");
		System.out.println("query executed");
		while(rs.next())
		{
			coachWithHostName1on1session= rs.getString("HOST_ID");
			System.out.println("1on1 Session Coach HostID "+coachWithHostName1on1session+" is retrieved from the Table");
		}
		return coachWithHostName1on1session;
	}
	public static String retrieveHostnameofCoach(WebDriver driver) throws ClassNotFoundException, SQLException
	{
		String coachWithHostName1on1session ="";
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
		while(rs.next())
		{
			coachWithHostName1on1session= rs.getString("HOST_NAME");
		}
		System.out.println(" 1on1 Session Coach Host Name "+coachWithHostName1on1session+" is retrieved from the Table");
		return coachWithHostName1on1session;
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
	// Retrieve Coach Hostname for Lecture Session
	// Substituting the Program ID in the query will give the Coach Hostname  for Lecture session
	public static String coachWithHostNameLecturesession ="";
	public static String retrieveLectureSessionHostNameWithProgramID(WebDriver driver) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("SELECT EVT.ID, EVT.HOST_ID, EVT.HOST_NAME FROM ACCOUNT_PROGRAM_SESSION_DETAIL PSD, CALENDAR_EVENT EVT WHERE EVT.ID=PSD.CALENDAR_EVENT_ID AND ACCOUNT_PROGRAM_ID='"+accountProgramID+"' AND PSD.SESSION_STATUS='Scheduled' AND PSD.SESSION_TYPE_ID='30100AA52BEAEE0EE3'");
		System.out.println("query executed");
		while(rs.next())
		{
			coachWithHostNameLecturesession= rs.getString("HOST_NAME");
			System.out.println("Lecture Session Coach Host Name "+coachWithHostNameLecturesession+" is retrieved from the Table");
		}
		return coachWithHostNameLecturesession;
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
	public static String retrive1on1sessionCoachEmail ="";
	public static String retrive1on1SessionEmailWithCoachHostId(WebDriver driver,String coachWithHostName1on1session) throws ClassNotFoundException, SQLException
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
		while(rs.next())
		{
			retrive1on1sessionCoachEmail= rs.getString("email");
			System.out.println("1on1 Coach Email  "+retrive1on1sessionCoachEmail+" is retrieved from the Table");
		}
		return retrive1on1sessionCoachEmail;
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
	//Retrieve Coach Email for Lecture Session
	// Substituting the Coach Hostname in the query will give coach Email for Lecture Session
	public static String retriveLectureSessionCoachEmail ="";
	public static String retriveLectureSessionEmailWithCoachHostName(WebDriver driver) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select email from provider where NAME = '"+coachWithHostNameLecturesession+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			retriveLectureSessionCoachEmail= rs.getString("EMAIL");
			System.out.println("Coach Email "+retriveLectureSessionCoachEmail+" is retrieved from the Table");
		}
		return retriveLectureSessionCoachEmail;
	}
	/**
	 * Name :Vigneshraj
	 * Created Date: 11/Mar/2016
	 * Modified Date:
	 * Description : Create a common method to Live Session 1on1 Coach Login
	 * Required Inputs :  No Inputs Required
	 * @throws ParseException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static void oneOnOneLiveSessionCoachLogin(WebDriver driver1)
	{
		wait(driver1, "5");
		Navigate.get(driver1, Directory.Coachesurl);
		wait(driver1, "5");
		Navigate.maximize(driver1);
		WebElement Coacheslogin_logo= driver1.findElement(By.xpath(OR.COACHES_LOGIN_PAGE_LOGO_REF));
		waitForElement( driver1, OR.COACHES_LOGIN_PAGE_LOGO_REF );
		verifyElementIsPresent(driver1, Coacheslogin_logo);
		WebElement coach_username= driver1.findElement(By.xpath(OR.COACHES_USERNAME_1));
		verifyElementIsPresent(driver1, coach_username);
		WebElement coach_password= driver1.findElement(By.xpath(OR.COACHES_PASSWORD));
		verifyElementIsPresent(driver1, coach_password);
		WebElement Coacheslogin_button= driver1.findElement(By.xpath(OR.COACHES_LOGIN_BUTTON));
		verifyElementIsPresent(driver1, Coacheslogin_button);
		sendKeys(coach_username,coach_mail1);
		try
		{
			wait(driver1, "2");
			sendKeys(coach_password,"Healthfleet2015");
			wait(driver1, "2");
			click(Coacheslogin_button);
			wait(driver1, "2");
			try
			{
				wait(driver1, "2");
				clear( coach_password );
				sendKeys(coach_password,"Zillion2016");
				wait(driver1, "2");
				click(Coacheslogin_button);
				wait(driver1, "2");
				try
				{
					wait(driver1, "2");
					clear( coach_password );
					sendKeys(coach_password,"Healthfleet2016");
					wait(driver1, "2");
					click(Coacheslogin_button);
					wait(driver1, "3");
				}
				catch(Exception e)
				{
					System.out.println("Password are in matched");
				}
			}
			catch(Exception e)
			{
				System.out.println("Password not in matched");
			}
		}
		catch(Exception e)
		{
			System.out.println("Password are in matched");
		}
		Navigate.waitTime(driver1, "20");
		System.out.println("Coaches Logged in successfully");
	}
	/**
	 * Name :Vigneshraj
	 * Created Date: 11/Mar/2016
	 * Modified Date:
	 * Description : Create a common method to Live Session 1on1 Coach Login
	 * Ticket ID :
	 * Required Inputs :  No Inputs Required
	 * @throws ParseException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static void lectureLiveSessionCoachLogin(WebDriver driver1)
	{
		Navigate.get(driver1, Directory.Coachesurl);
		Navigate.maximize(driver1);
		String username= retriveLectureSessionCoachEmail;
		WebElement Coacheslogin_logo= driver1.findElement(By.xpath(OR.COACHES_LOGIN_PAGE_LOGO_REF));
		waitForElement( driver1, OR.COACHES_LOGIN_PAGE_LOGO_REF );
		verifyElementIsPresent(driver1, Coacheslogin_logo);
		WebElement coach_username= driver1.findElement(By.xpath(OR.COACHES_USERNAME_1));
		verifyElementIsPresent(driver1, coach_username);
		WebElement coach_password= driver1.findElement(By.xpath(OR.COACHES_PASSWORD));
		verifyElementIsPresent(driver1, coach_password);
		WebElement Coacheslogin_button= driver1.findElement(By.xpath(OR.COACHES_LOGIN_BUTTON));
		verifyElementIsPresent(driver1, Coacheslogin_button);
		sendKeys(coach_username,username);
		sendKeys(coach_password,"Healthfleet2015");
		click(Coacheslogin_button);
		wait(driver1, "20");
		waitForElement(driver1, OR.COACHES_CLASSES_TAB);
		WebElement Classes_Tab = driver1.findElement(By.xpath(OR.COACHES_CLASSES_TAB));
		click(Classes_Tab);
		wait(driver1, "5");
		WebElement Dashboard_Tab = driver1.findElement(By.xpath(OR.COACHES_LOGIN_DASHBOARD_REF));
		click(Dashboard_Tab);
		wait(driver1, "10");
	}
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   03/02/2016
	 * Modified Date:
	 * Description :   Create a common method to Timeslot Search for Lecture Live session
	 * Ticket ID :
	 * Required Inputs :
	 */
	// Method will verify the  created available date and time slot search
	public static String liveSessionLectureSessionTimeSlotSearch(WebDriver driver)
	{
		WebElement nextPageNavigator= driver.findElement(By.xpath(OR.NEXT_PAGE_NAVIGATOR));
		verifyElementIsPresent(driver, nextPageNavigator);
		click(nextPageNavigator);
		wait(driver, "14");
		try
		{
			waitForElement(driver, FRIDAY_HEADER);
			waitForElement(driver, FRIDAY_EVENING_BUTTON);
			jsClickByXPath(driver, FRIDAY_EVENING_BUTTON);
			wait(driver, "4");
		}
		catch(Exception e)
		{
			verifyElementIsPresent(driver, nextPageNavigator);
			click(nextPageNavigator);
			wait(driver, "4");
			WebElement fridayHeader= driver.findElement(By.xpath(OR.FRIDAY_HEADER));
			WebElement fridayEveningButton= driver.findElement(By.xpath(OR.FRIDAY_EVENING_BUTTON));
			verifyElementIsPresent(driver, fridayHeader);
			verifyElementIsPresent(driver, fridayEveningButton);
			click(fridayEveningButton);
		}
		return "Timeslot should be searchedin the view session";
	}
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   03/02/2016
	 * Modified Date:
	 * Description :   Create a common method to VERIFY CURRENT DATE
	 * Required Inputs :
	 */
	// Method will sign up the created Lecture session
	public static String liveSessionMemberLoginForLectureSession(WebDriver driver,String testData)
	{
		//Member-1-LectureSignUp
		String[] testData1 = testData.split(",");
		String inputUsername=testData1[1];
		String inputPassword=testData1[2];
		wait(driver, "8");
		Navigate.get(driver, Directory.Memberurl);
		Navigate.maximize(driver);
		wait(driver, "20");
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
		sendKeys(member_username, inputUsername);
		System.out.println("Username is typed");
		WebElement member_password = driver.findElement(By.xpath(OR.MEMBER_YOURPASSWORD));
		jsClickByXPath(driver, OR.MEMBER_YOURPASSWORD);
		sendKeys(member_password,inputPassword);
		System.out.println("Password is typed");
		waitForElement(driver, OR.MEMBER_LOGIN_BUTTON);
		jsClickByXPath(driver, OR.MEMBER_LOGIN_BUTTON);
		System.out.println("Login button is clicked");
		wait( driver, "10" );
		return inputUsername;
	}
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   03/02/2016
	 * Modified Date:
	 * Description :   Create a common method to Member verify lecture session
	 * Ticket ID :
	 * Required Inputs :
	 */
	// Method will verify the created Lecture session by clicking View session for Sign Up
	public static String liveSessionMemberVerifyLectureSession(WebDriver driver)
	{
		try
		{
			WebElement saturdayText= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_OF_FRIDAY_TEXT));
			WebElement lectureSessionTiming= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_OF_LECTURE_SESSION_TIMING));
			verifyElementIsPresent(driver, saturdayText);
			verifyElementIsPresent(driver, lectureSessionTiming);
			WebElement signUpButton= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_FOLLOWING_SIGNUP));
			verifyElementIsPresent(driver, signUpButton);
			click(signUpButton);
			Navigate.waitTime(driver, "5");
			WebElement successfullPopUpText= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_SUCCESSFULL_POPUP_TEXT));
			WebElement successfullPopUpOkButton= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_SUCCESSFULL_POPUP_OK_BUTTON));
			verifyElementIsPresent(driver, successfullPopUpText);
			verifyElementIsPresent(driver, successfullPopUpOkButton);
			click(successfullPopUpOkButton);
		}
		catch(Exception e)
		{
			WebElement nextPageNavigator= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_NEXT_PAGE_NAVIGATOR));
			verifyElementIsPresent(driver, nextPageNavigator);
			click(nextPageNavigator);
			Navigate.waitTime(driver, "5");
		}
		try
		{
			WebElement saturdayText= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_OF_FRIDAY_TEXT));
			WebElement lectureSessionTiming= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_OF_LECTURE_SESSION_TIMING));
			verifyElementIsPresent(driver, saturdayText);
			verifyElementIsPresent(driver, lectureSessionTiming);
			WebElement signUpButton= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_FOLLOWING_SIGNUP));
			verifyElementIsPresent(driver, signUpButton);
			click(signUpButton);
			Navigate.waitTime(driver, "5");
			WebElement successfullPopUpText= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_SUCCESSFULL_POPUP_TEXT));
			WebElement successfullPopUpOkButton= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_SUCCESSFULL_POPUP_OK_BUTTON));
			verifyElementIsPresent(driver, successfullPopUpText);
			verifyElementIsPresent(driver, successfullPopUpOkButton);
			click(successfullPopUpOkButton);
		}
		catch(Exception e)
		{
			WebElement nextPageNavigator= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_NEXT_PAGE_NAVIGATOR));
			verifyElementIsPresent(driver, nextPageNavigator);
			click(nextPageNavigator);
			Navigate.waitTime(driver, "5");
		}
		try
		{
			WebElement saturdayText= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_OF_FRIDAY_TEXT));
			WebElement lectureSessionTiming= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_OF_LECTURE_SESSION_TIMING));
			verifyElementIsPresent(driver, saturdayText);
			verifyElementIsPresent(driver, lectureSessionTiming);
			WebElement signUpButton= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_FOLLOWING_SIGNUP));
			verifyElementIsPresent(driver, signUpButton);
			click(signUpButton);
			Navigate.waitTime(driver, "5");
			WebElement successfullPopUpText= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_SUCCESSFULL_POPUP_TEXT));
			WebElement successfullPopUpOkButton= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_SUCCESSFULL_POPUP_OK_BUTTON));
			verifyElementIsPresent(driver, successfullPopUpText);
			verifyElementIsPresent(driver, successfullPopUpOkButton);
			click(successfullPopUpOkButton);
		}
		catch(Exception e)
		{
			WebElement nextPageNavigator= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_NEXT_PAGE_NAVIGATOR));
			verifyElementIsPresent(driver, nextPageNavigator);
			click(nextPageNavigator);
			Navigate.waitTime(driver, "5");
		}
		try
		{
			WebElement saturdayText= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_OF_FRIDAY_TEXT));
			WebElement lectureSessionTiming= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_OF_LECTURE_SESSION_TIMING));
			verifyElementIsPresent(driver, saturdayText);
			verifyElementIsPresent(driver, lectureSessionTiming);
			WebElement signUpButton= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_FOLLOWING_SIGNUP));
			verifyElementIsPresent(driver, signUpButton);
			click(signUpButton);
			Navigate.waitTime(driver, "5");
			WebElement successfullPopUpText= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_SUCCESSFULL_POPUP_TEXT));
			WebElement successfullPopUpOkButton= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_SUCCESSFULL_POPUP_OK_BUTTON));
			verifyElementIsPresent(driver, successfullPopUpText);
			verifyElementIsPresent(driver, successfullPopUpOkButton);
			click(successfullPopUpOkButton);
		}
		catch(Exception e)
		{
			WebElement nextPageNavigator= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_NEXT_PAGE_NAVIGATOR));
			verifyElementIsPresent(driver, nextPageNavigator);
			click(nextPageNavigator);
			Navigate.waitTime(driver, "5");
		}
		try
		{
			WebElement saturdayText= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_OF_FRIDAY_TEXT));
			WebElement lectureSessionTiming= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_OF_LECTURE_SESSION_TIMING));
			verifyElementIsPresent(driver, saturdayText);
			verifyElementIsPresent(driver, lectureSessionTiming);
			WebElement signUpButton= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_FOLLOWING_SIGNUP));
			verifyElementIsPresent(driver, signUpButton);
			click(signUpButton);
			Navigate.waitTime(driver, "5");
			WebElement successfullPopUpText= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_SUCCESSFULL_POPUP_TEXT));
			WebElement successfullPopUpOkButton= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_SUCCESSFULL_POPUP_OK_BUTTON));
			verifyElementIsPresent(driver, successfullPopUpText);
			verifyElementIsPresent(driver, successfullPopUpOkButton);
			click(successfullPopUpOkButton);
		}
		catch(Exception e)
		{
			WebElement nextPageNavigator= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_NEXT_PAGE_NAVIGATOR));
			verifyElementIsPresent(driver, nextPageNavigator);
			click(nextPageNavigator);
			Navigate.waitTime(driver, "5");
		}
		try
		{
			WebElement saturdayText= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_OF_FRIDAY_TEXT));
			WebElement lectureSessionTiming= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_OF_LECTURE_SESSION_TIMING));
			verifyElementIsPresent(driver, saturdayText);
			verifyElementIsPresent(driver, lectureSessionTiming);
			WebElement signUpButton= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_FOLLOWING_SIGNUP));
			verifyElementIsPresent(driver, signUpButton);
			click(signUpButton);
			Navigate.waitTime(driver, "5");
			WebElement successfullPopUpText= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_SUCCESSFULL_POPUP_TEXT));
			WebElement successfullPopUpOkButton= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_SUCCESSFULL_POPUP_OK_BUTTON));
			verifyElementIsPresent(driver, successfullPopUpText);
			verifyElementIsPresent(driver, successfullPopUpOkButton);
			click(successfullPopUpOkButton);
		}
		catch(Exception e)
		{
			WebElement nextPageNavigator= driver.findElement(By.xpath(OR.LIVESESSION_MEMBER_VIEW_SESSION_NEXT_PAGE_NAVIGATOR));
			verifyElementIsPresent(driver, nextPageNavigator);
			click(nextPageNavigator);
			Navigate.waitTime(driver, "5");
		}
		return "Lecture session is verified and Signed up from Member";
	}
	/**
	 * Name :     VINOTHKUMAR
	 * Created Date:   22/03/2016
	 * Modified Date:
	 * Description :   Create a common method to Browser minimize
	 * Ticket ID :
	 * Required Inputs :
	 */
	public static String browserMinimize(WebDriver driver1) throws AWTException
	{
		final String osName = Platform.OS.toUpperCase();
		if (osName.contains("WINDOWS")) 
		{
			Manipulation.wait(driver1, "1");
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_SPACE);
			robot.keyRelease(KeyEvent.VK_SPACE);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_N);
			robot.keyRelease(KeyEvent.VK_N);
		}
		else if (osName.contains("MAC")) 
		{
			Robot robot = new Robot();
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.keyPress(KeyEvent.VK_META);
			robot.keyPress(KeyEvent.VK_M);
			robot.keyRelease(KeyEvent.VK_META);
			robot.keyRelease(KeyEvent.VK_M);
			Manipulation.wait(driver1, "2");
		}
		return ElementWait;
	}

	/**
	 * Name :     VINOTHKUMAR
	 * Created Date:   22/03/2016
	 * Modified Date:
	 * Description :   Create a common method to Allow the Plugin and Remember
	 * Ticket ID :
	 * Required Inputs :
	 * @throws FindFailed
	 * @throws AWTException
	 */
	// Method will Allow the plugin if there is Allow and Remember Else it will skip the execution
	public static void allowPlugins(WebDriver driver) throws FindFailed, AWTException
	{
		Robot rb = new Robot();
		try
		{
			{
				rb.keyPress(KeyEvent.VK_ALT);
				rb.keyPress(KeyEvent.VK_A);
				rb.keyRelease(KeyEvent.VK_ALT);
				rb.keyRelease(KeyEvent.VK_A);
				wait( driver, "2" );
				rb.keyPress(KeyEvent.VK_ALT);
				rb.keyPress(KeyEvent.VK_R);
				rb.keyRelease(KeyEvent.VK_ALT);
				rb.keyRelease(KeyEvent.VK_R);
				wait( driver, "5" );
			}
		}
		catch(Exception e)
		{
			Screen Allow=new Screen();
			Pattern image = new Pattern(Directory.uploadFilePath+"ChromeAllow.PNG");
			Allow.wait(image, 15);
			Allow.click(image);
		}
		try
		{ 
			//mac
			Screen Allow=new Screen();
			Pattern image = new Pattern(Directory.uploadFilePath+"MacChromeAllowPopup.PNG");
			Allow.wait(image, 15);
			Allow.click(image);
		}
		catch(Exception e)
		{
			{
				rb.keyPress(KeyEvent.VK_CONTROL);
				rb.keyPress(KeyEvent.VK_A);
				rb.keyRelease(KeyEvent.VK_CONTROL);
				rb.keyRelease(KeyEvent.VK_A);
				wait( driver, "4" );
				rb.keyPress(KeyEvent.VK_CONTROL);
				rb.keyPress(KeyEvent.VK_R);
				rb.keyRelease(KeyEvent.VK_CONTROL);
				rb.keyRelease(KeyEvent.VK_R);
				wait( driver, "5" );
			}
		}
	}
	/**
	 * Name : VinothKumar.M
	 * Created Date: 10/Mar/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve 1on1 Session Attendance with Session ID
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String getSessionID=null;
	public static String getCurrentURL(WebDriver driver1)
	{
		String getCurrentURL= driver1.getCurrentUrl();
		String getCurrentURL1= getCurrentURL.substring(getCurrentURL.indexOf("=") + 1);
		String[] getCurrentURL2=getCurrentURL1.split("&");
		getSessionID=  getCurrentURL2[0];
		System.out.println("Session ID from the URL: "+getSessionID);
		return getSessionID;
	}
	/**
	 * Name : VinothKumar.M
	 * Created Date: 10/Mar/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve 1on1 Session Attendance with Session ID
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String retrive1on1SessionAttendanceStatusWithSessionID(WebDriver driver) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select account.email, ce.ATTENDANCE_STATUS, ce.ATTENDANCE_PRTCPNT_ARRIVAL, ce.ATTENDANCE_PRTCPNT_DEPARTURE, ce.ATTENDANCE_PRTCPNT_MINUTES, ce.SESSION_ACTUAL_START_DATETIME, ce.SESSION_ACTUAL_END_DATETIME, ce.SESSION_ACTUAL_MINUTES, ce.SESSION_ACTUAL_START_DATETIME, ce.SESSION_ACTUAL_END_DATETIME, ce.SESSION_ACTUAL_MINUTES from calendar_event ce, account where ce.id='"+getSessionID+"' and ce.account_id ='"+accountId+"' and ce.account_id = account.id order by account.email desc");
		System.out.println("query executed");
		while(rs.next())
		{
			String retrieve1on1SessionAttendanceParticipantArrival= rs.getString("ATTENDANCE_PRTCPNT_ARRIVAL");
			String retrieve1on1SessionAttendanceStartDateTime= rs.getString("SESSION_ACTUAL_START_DATETIME");
			String retrieve1on1SessionAttendanceEndDateTime= rs.getString("SESSION_ACTUAL_END_DATETIME");
			String retrieve1on1SessionAttendanceActualMinutes= rs.getString("SESSION_ACTUAL_MINUTES");
			System.out.println("retrieve1on1SessionAttendanceActualMinutes :"+retrieve1on1SessionAttendanceActualMinutes);
			String currenttime = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			System.out.println("Current Date :"+currenttime);
			String retrieve1on1SessionAttendanceParticipantArrival1[]= retrieve1on1SessionAttendanceParticipantArrival.split( " " );
			String retrieve1on1SessionAttendanceParticipantArrival2= retrieve1on1SessionAttendanceParticipantArrival1[0];
			if(retrieve1on1SessionAttendanceParticipantArrival2.equalsIgnoreCase( currenttime ))
			{
				System.out.println("Current Time and Participant Arrival time are matched: "+retrieve1on1SessionAttendanceParticipantArrival2);
			}
			String retrieve1on1SessionAttendanceStartDateTime1[]= retrieve1on1SessionAttendanceStartDateTime.split( " " );
			String retrieve1on1SessionAttendanceStartDateTime2= retrieve1on1SessionAttendanceStartDateTime1[0];
			if(retrieve1on1SessionAttendanceStartDateTime2.equalsIgnoreCase( currenttime ))
			{
				System.out.println("Current Time and Participant Start Date time are matched: "+retrieve1on1SessionAttendanceStartDateTime2);
			}
			String retrieve1on1SessionAttendanceEndDateTime1[]= retrieve1on1SessionAttendanceEndDateTime.split( " " );
			String retrieve1on1SessionAttendanceEndDateTime2= retrieve1on1SessionAttendanceEndDateTime1[0];
			if(retrieve1on1SessionAttendanceEndDateTime2.equalsIgnoreCase( currenttime ))
			{
				System.out.println("Current Time and Participant End Date time are matched :"+retrieve1on1SessionAttendanceEndDateTime2);
			}
			int SessionActualMinutes= Integer.parseInt( retrieve1on1SessionAttendanceActualMinutes );
			if (SessionActualMinutes>=1)
			{
				System.out.println("Session Actual Minutes is greater than 1 :"+SessionActualMinutes);
			}
		}
		return ElementWait;
	}
	/**
	 * Name : VinothKumar.M
	 * Created Date: 10/Mar/2016
	 * Modified Date:
	 * Description : Create a common method to retrieve lecture session Attendance with Session ID
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String retriveLectureSessionAttendanceStatusWithSessionID(WebDriver driver) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select account.email, cae.ATTENDANCE_STATUS, cae.ATTENDANCE_PRTCPNT_ARRIVAL, cae.ATTENDANCE_PRTCPNT_DEPARTURE, cae.ATTENDANCE_PRTCPNT_MINUTES, cae.SESSION_ACTUAL_START_DATETIME, cae.SESSION_ACTUAL_END_DATETIME, cae.SESSION_ACTUAL_MINUTES from calendar_event ce, calendar_event_attendee cae, account where cae.event_id='"+getSessionID+"' and cae.account_id = '"+accountId+"' and ce.id = cae.event_id order by account.email desc");
		System.out.println("query executed");
		while(rs.next())
		{
			String retrieve1on1SessionAttendanceParticipantArrival= rs.getString("ATTENDANCE_PRTCPNT_ARRIVAL");
			String retrieve1on1SessionAttendanceStartDateTime= rs.getString("SESSION_ACTUAL_START_DATETIME");
			String retrieve1on1SessionAttendanceEndDateTime= rs.getString("SESSION_ACTUAL_END_DATETIME");
			String retrieve1on1SessionAttendanceActualMinutes= rs.getString("SESSION_ACTUAL_MINUTES");
			System.out.println("retrieve1on1SessionAttendanceActualMinutes :"+retrieve1on1SessionAttendanceActualMinutes);
			String currenttime = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			System.out.println("Current Date :"+currenttime);
			String retrieve1on1SessionAttendanceParticipantArrival1[]= retrieve1on1SessionAttendanceParticipantArrival.split( " " );
			String retrieve1on1SessionAttendanceParticipantArrival2= retrieve1on1SessionAttendanceParticipantArrival1[0];
			if(retrieve1on1SessionAttendanceParticipantArrival2.equalsIgnoreCase( currenttime ))
			{
				System.out.println("Current Time and Participant Arrival time are matched: "+retrieve1on1SessionAttendanceParticipantArrival2);
			}
			String retrieve1on1SessionAttendanceStartDateTime1[]= retrieve1on1SessionAttendanceStartDateTime.split( " " );
			String retrieve1on1SessionAttendanceStartDateTime2= retrieve1on1SessionAttendanceStartDateTime1[0];
			if(retrieve1on1SessionAttendanceStartDateTime2.equalsIgnoreCase( currenttime ))
			{
				System.out.println("Current Time and Participant Start Date time are matched: "+retrieve1on1SessionAttendanceStartDateTime2);
			}
			String retrieve1on1SessionAttendanceEndDateTime1[]= retrieve1on1SessionAttendanceEndDateTime.split( " " );
			String retrieve1on1SessionAttendanceEndDateTime2= retrieve1on1SessionAttendanceEndDateTime1[0];
			if(retrieve1on1SessionAttendanceEndDateTime2.equalsIgnoreCase( currenttime ))
			{
				System.out.println("Current Time and Participant End Date time are matched :"+retrieve1on1SessionAttendanceEndDateTime2);
			}
			int SessionActualMinutes= Integer.parseInt( retrieve1on1SessionAttendanceActualMinutes );
			if (SessionActualMinutes>=1)
			{
				System.out.println("Session Actual Minutes is greater than 1 :"+SessionActualMinutes);
			}
		}
		return ElementWait;
	}
	/**
	 * Name : Vigneshraj.M
	 * Created Date: 29/Mar/2016
	 * Modified Date:
	 * Description :   Create a common method to Member Gear Settings
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String memberGearSettings(WebDriver driver)
	{
		WebElement memberSettingsGearIcon = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_BUTTON));
		verifyElementIsPresent(driver, memberSettingsGearIcon);
		jsClickByXPath(driver, OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_BUTTON);
		wait(driver, "3" );
		WebElement memberSettingsTitle = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_TITLE));
		WebElement memberSettingsCloseButton = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_CLOSE_BUTTON));
		WebElement memberSettingsVideoOption = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_VIDEO_OPTION));
		WebElement memberSettingsAudioOption = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_AUDIO_OPTION));
		WebElement memberSettingsCameraLabel = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_CAMERA_LABEL));
		WebElement memberSettingsCameraDropdown = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_CAMERA_DROPDOWN));
		verifyElementIsPresent(driver, memberSettingsTitle);
		verifyElementIsPresent(driver, memberSettingsCloseButton);
		verifyElementIsPresent(driver, memberSettingsVideoOption);
		verifyElementIsPresent(driver, memberSettingsAudioOption);
		verifyElementIsPresent(driver, memberSettingsCameraLabel);
		verifyElementIsPresent(driver, memberSettingsCameraDropdown);
		click(memberSettingsCameraDropdown);
		wait(driver, "5" );
		String OSName=Platform.OS.toUpperCase();
		if(OSName.contains("WINDOWS")){
			{
				waitForElement(driver, OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_CAMERA_OPTION);
				WebElement memberSettingsCameraOption = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_CAMERA_OPTION));
				verifyElementIsPresent(driver, memberSettingsCameraOption);
			}
		}
		else if(OSName.contains("MAC"))
		{
			waitForElement(driver, OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_MAC_CAMERA_OPTION);
			WebElement memberSettingsMacCameraOption = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_MAC_CAMERA_OPTION));
			verifyElementIsPresent(driver, memberSettingsMacCameraOption);
		}
		jsClickByXPath(driver, OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_AUDIO_OPTION);
		wait(driver, "5" );
		WebElement memberSettingsMicrophoneLabel = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_MICROPHONE_LABEL));
		WebElement memberSettingsSpeakersLabel = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_SPEAKERS_LABEL));
		WebElement memberSettingsMicDropdown = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_MIC_DROPDOWN));
		WebElement memberSettingsSpeakerDropdown = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_SPEAKER_DROPDOWN));
		WebElement memberSettingsPlayTestSoundButton = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_PLAY_TEST_SOUND_BUTTON));
		WebElement memberSettingsMicrophoneActivityLabel = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_MICROPHONE_ACTIVITY_LABEL));
		WebElement memberSettingsMicEnableCheckbox = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_MIC_ENABLE_CHECKBOX));
		verifyElementIsPresent(driver, memberSettingsMicrophoneLabel);
		verifyElementIsPresent(driver, memberSettingsSpeakersLabel);
		verifyElementIsPresent(driver, memberSettingsMicrophoneActivityLabel);
		verifyElementIsPresent(driver, memberSettingsMicEnableCheckbox);
		verifyElementIsPresent(driver, memberSettingsMicDropdown);
		verifyElementIsPresent(driver, memberSettingsSpeakerDropdown);
		verifyElementIsPresent(driver, memberSettingsPlayTestSoundButton);
		jsClickByXPath(driver, OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_CLOSE_BUTTON);
		wait(driver, "3" );
		return ElementWait;
	}
	/**
	 * Name : Vinothkumar.M
	 * Created Date: 29/Mar/2016
	 * Modified Date:
	 * Description :   Create a common method to Coach Gear Settings
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String coachGearSettings(WebDriver driver1)
	{
		WebElement memberSettingsGearIcon = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_BUTTON));
		verifyElementIsPresent(driver1, memberSettingsGearIcon);
		jsClickByXPath(driver1, OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_BUTTON);
		wait(driver1, "2" );
		WebElement CoachSettingsTitle = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_COACH_SETTINGS_TITLE));
		WebElement CoachSettingsCloseButton = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_COACH_SETTINGS_CLOSE_BUTTON));
		WebElement memberSettingsVideoOption = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_VIDEO_OPTION));
		WebElement memberSettingsAudioOption = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_AUDIO_OPTION));
		WebElement memberSettingsCameraLabel = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_CAMERA_LABEL));
		WebElement memberSettingsCameraDropdown = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_CAMERA_DROPDOWN));
		verifyElementIsPresent(driver1, CoachSettingsTitle);
		verifyElementIsPresent(driver1, CoachSettingsCloseButton);
		verifyElementIsPresent(driver1, memberSettingsVideoOption);
		verifyElementIsPresent(driver1, memberSettingsAudioOption);
		verifyElementIsPresent(driver1, memberSettingsCameraLabel);
		verifyElementIsPresent(driver1, memberSettingsCameraDropdown);
		click(memberSettingsCameraDropdown);
		wait(driver1, "5" );
		String OSName=Platform.OS.toUpperCase();
		if(OSName.contains("WINDOWS")){
			{
				waitForElement(driver1, OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_CAMERA_OPTION);
				WebElement memberSettingsCameraOption = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_CAMERA_OPTION));
				verifyElementIsPresent(driver1, memberSettingsCameraOption);
			}
		}
		else if(OSName.contains("MAC"))
		{
			waitForElement(driver1, OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_MAC_CAMERA_OPTION);
			WebElement memberSettingsMacCameraOption = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_MAC_CAMERA_OPTION));
			verifyElementIsPresent(driver1, memberSettingsMacCameraOption);
		}
		jsClickByXPath(driver1, OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_AUDIO_OPTION);
		wait(driver1, "2" );
		WebElement memberSettingsMicrophoneLabel = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_MICROPHONE_LABEL));
		WebElement memberSettingsSpeakersLabel = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_SPEAKERS_LABEL));
		WebElement memberSettingsMicDropdown = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_MIC_DROPDOWN));
		WebElement memberSettingsSpeakerDropdown = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_SPEAKER_DROPDOWN));
		WebElement memberSettingsPlayTestSoundButton = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_PLAY_TEST_SOUND_BUTTON));
		WebElement memberSettingsMicrophoneActivityLabel = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_MICROPHONE_ACTIVITY_LABEL));
		WebElement memberSettingsMicEnableCheckbox = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_MIC_ENABLE_CHECKBOX));
		verifyElementIsPresent(driver1, memberSettingsMicrophoneLabel);
		verifyElementIsPresent(driver1, memberSettingsSpeakersLabel);
		verifyElementIsPresent(driver1, memberSettingsMicrophoneActivityLabel);
		verifyElementIsPresent(driver1, memberSettingsMicEnableCheckbox);
		verifyElementIsPresent(driver1, memberSettingsMicDropdown);
		verifyElementIsPresent(driver1, memberSettingsSpeakerDropdown);
		verifyElementIsPresent(driver1, memberSettingsPlayTestSoundButton);
		jsClickByXPath(driver1, OR.LIVESESSION_1ON1_SESSION_COACH_SETTINGS_CLOSE_BUTTON);
		wait(driver1, "3" );
		return ElementWait;
	}
	/**
	 * Name : VIGNESHRAJ.M
	 * Created Date: 30/Mar/2016
	 * Modified Date:
	 * Description :   Create a common method to Rating stars from DB match with given stars.
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static String sessionratingStars(WebDriver driver) throws ParseException, ClassNotFoundException, SQLException
	{
		String providerRating="";
		int givenProviderRating= 5;
		String currentdate = new SimpleDateFormat("dd-MMM-YY").format(Calendar.getInstance().getTime());
		String currentdate1 = currentdate.toUpperCase() ;
		System.out.println("Current Date :"+currentdate1);
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("SELECT NOTES_LOGGED_DT, ACCOUNT_ID, PROVIDER_ID, EVENT_ID, SESSION_TYPE, PROVIDER_RATING, CONTENT FROM ACCOUNTS_PROVIDER_NOTE WHERE ACCOUNT_ID='"+accountId+"' and NOTES_LOGGED_DT like '"+currentdate1+" % %' ");
		while(rs.next())
		{
			providerRating= rs.getString("PROVIDER_RATING");
		}
		System.out.println("Rating stars from DB :"+providerRating);
		int providerRating1 = Integer.parseInt( providerRating );
		if(givenProviderRating==providerRating1)
		{
			System.out.println("Given provider rating stars is matched with DB stars: "+providerRating1);
		}
		return providerRating;
	}
	/**
	 * Name : VIGNESHRAJ.M
	 * Created Date: 30/Mar/2016
	 * Modified Date:
	 * Description :   Create a common method to Rating Notes from DB match with given stars.
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static String ratingNotesCoachSide(WebDriver driver, String testData) throws ParseException, ClassNotFoundException, SQLException
	{
		String[] testData1 = testData.split(",");
		String inputCoachSessionCommentTextbox=testData1[8];
		String coachRatingNotes="";
		String givenRatingNotesCoachSide= inputCoachSessionCommentTextbox;
		String currentdate = new SimpleDateFormat("dd-MMM-YY").format(Calendar.getInstance().getTime());
		String currentdate1 = currentdate.toUpperCase() ;
		System.out.println("Current Date :"+currentdate1);
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select DT, NOTE_TYPE, CONTENT, EVENT_ID, ACCOUNT_ID, CATEGORY from Account_NOTE where EVENT_ID = '"+getSessionID+"' and DT like '"+currentdate1+" % %' ");
		while(rs.next())
		{
			coachRatingNotes= rs.getString("CONTENT");
		}
		System.out.println("Coach Rating content from DB :"+coachRatingNotes);
		if(givenRatingNotesCoachSide.equalsIgnoreCase( coachRatingNotes ))
		{
			System.out.println("Session rating notes from session as provided by coach matches with DB: "+coachRatingNotes);
		}
		return coachRatingNotes;
	}
	/**
	 * Name : VIGNESHRAJ.M
	 * Created Date: 30/Mar/2016
	 * Modified Date:
	 * Description :   Create a common method to Rating notes from DB match with given stars.
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static String ratingNotesMemberSide(WebDriver driver, String testData) throws ParseException, ClassNotFoundException, SQLException
	{
		String[] testData1 = testData.split(",");
		String inputMemberCoachRatingTextbox=testData1[9];
		String memberRatingNotes="";
		String givenRatingNotesMemberSide= inputMemberCoachRatingTextbox;
		String currentdate = new SimpleDateFormat("dd-MMM-YY").format(Calendar.getInstance().getTime());
		String currentdate1 = currentdate.toUpperCase() ;
		System.out.println("Current Date :"+currentdate1);
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select Notes_logged_DT, Note_type, EVENT_ID, ACCOUNT_ID, SESSION_TYPE, INTERVAL_NUMBER, CONTENT from ACCOUNTS_PROVIDER_NOTE where account_id = '3DA03589E11B7D80E055000000000001' and EVENT_ID ='"+getSessionID+"' and Notes_logged_DT like '"+currentdate1+" % %' ");
		while(rs.next())
		{
			memberRatingNotes= rs.getString("CONTENT");
		}
		System.out.println("Member Rating content from DB :"+memberRatingNotes);

		if(givenRatingNotesMemberSide.equalsIgnoreCase( memberRatingNotes ))
		{
			System.out.println("Session rating notes from session as provided by member matches with DB: "+memberRatingNotes);
		}
		return memberRatingNotes;
	}
	/**
	 * Name : VIGNESHRAJ.M
	 * Created Date: 4/April/2016
	 * Modified Date:
	 * Description : Common method to 1on1 Live Session Coach Mic Enable/Disable from spread sheet in Place [7]
	 * Ticket ID :
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String coachOneOnOneSessionMicEnableAndDisable(WebDriver driver1,String testData)
	{
		String[] testData1 = testData.split(",");
		String inputCoachMic=testData1[4];
		if ("CoachMicEnable".equalsIgnoreCase(inputCoachMic) )
		{
			try
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_MIC_OFF);
				WebElement coachMicOn = driver1.findElement(By.xpath(OR.COACH_SESSION_MIC_ON));
				verifyElementIsPresent(driver1, coachMicOn);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_MIC_ON);
				jsClickByXPath(driver1, OR.COACH_SESSION_MIC_OFF);
				WebElement coachMicOn = driver1.findElement(By.xpath(OR.COACH_SESSION_MIC_ON));
				verifyElementIsPresent(driver1, coachMicOn);
			}
		}
		else if ( "CoachMicDisable".equalsIgnoreCase(inputCoachMic) )
		{
			try
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_MIC_ON);
				WebElement coachMicOff = driver1.findElement(By.xpath(OR.COACH_SESSION_MIC_OFF));
				verifyElementIsPresent(driver1, coachMicOff);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_MIC_OFF);
				jsClickByXPath(driver1, OR.COACH_SESSION_MIC_ON);
				WebElement coachMicOff = driver1.findElement(By.xpath(OR.COACH_SESSION_MIC_OFF));
				verifyElementIsPresent(driver1, coachMicOff);
			}
		}
		return ElementWait;
	}
	/**
	 * Name : VIGNESHRAJ.M
	 * Created Date: 4/April/2016
	 * Modified Date:
	 * Description :   Common method to 1on1 LSive ession Coach Video Enable/Disable from spread sheet in Place [8]
	 * Ticket ID :
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String coachOneOnOneSessionVideoEnableAndDisable(WebDriver driver1,String testData)
	{
		String[] testData1 = testData.split(",");
		String inputCoachVideo=testData1[5];
		if ("CoachVideoEnable".equalsIgnoreCase(inputCoachVideo) )
		{
			try
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_VIDEO_OFF);
				wait(driver1, "2" );
				WebElement coachVideoOn = driver1.findElement(By.xpath(OR.COACH_SESSION_VIDEO_ON));
				verifyElementIsPresent(driver1, coachVideoOn);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_VIDEO_ON);
				jsClickByXPath(driver1, OR.COACH_SESSION_VIDEO_OFF);
				WebElement coachVideoOn = driver1.findElement(By.xpath(OR.COACH_SESSION_VIDEO_ON));
				verifyElementIsPresent(driver1, coachVideoOn);
			}
		}
		else if ( "CoachVideoDisable".equalsIgnoreCase(inputCoachVideo) )
		{
			try
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_VIDEO_ON);
				wait(driver1, "2" );
				WebElement coachVideoOff = driver1.findElement(By.xpath(OR.COACH_SESSION_VIDEO_OFF));
				verifyElementIsPresent(driver1, coachVideoOff);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_VIDEO_OFF);
				jsClickByXPath(driver1, OR.COACH_SESSION_VIDEO_ON);
				WebElement coachVideoOff = driver1.findElement(By.xpath(OR.COACH_SESSION_VIDEO_OFF));
				verifyElementIsPresent(driver1, coachVideoOff);
			}
		}
		return ElementWait;
	}
	/**
	 * Name : VIGNESHRAJ.M
	 * Created Date: 4/April/2016
	 * Modified Date:
	 * Description :   Common method to 1on1 Live Session Member Mic Enable/Disable from spread sheet in Place [4]
	 * Ticket ID :
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String memberOneonOneSessionMicEnableAndDisable(WebDriver driver,String testData)
	{
		String[] testData1 = testData.split(",");
		String inputMemberMic=testData1[1];
		if ("MemberMicEnable".equalsIgnoreCase(inputMemberMic) )
		{
			try
			{
				jsClickByXPath(driver, OR.MEMBER_SESSION_MIC_OFF);
				WebElement memberMicOn = driver.findElement(By.xpath(OR.MEMBER_SESSION_MIC_ON));
				verifyElementIsPresent(driver, memberMicOn);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver, OR.MEMBER_SESSION_MIC_ON);
				jsClickByXPath(driver, OR.MEMBER_SESSION_MIC_OFF);
				WebElement memberMicOn = driver.findElement(By.xpath(OR.MEMBER_SESSION_MIC_ON));
				verifyElementIsPresent(driver, memberMicOn);
			}
		}
		else if ( "MemberMicDisable".equalsIgnoreCase(inputMemberMic) )
		{
			try
			{
				jsClickByXPath(driver, OR.MEMBER_SESSION_MIC_ON);
				WebElement memberMicOff = driver.findElement(By.xpath(OR.MEMBER_SESSION_MIC_OFF));
				verifyElementIsPresent(driver, memberMicOff);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver, OR.MEMBER_SESSION_MIC_OFF);
				jsClickByXPath(driver, OR.MEMBER_SESSION_MIC_ON);
				WebElement memberMicOff = driver.findElement(By.xpath(OR.MEMBER_SESSION_MIC_OFF));
				verifyElementIsPresent(driver, memberMicOff);
			}
		}
		return ElementWait;
	}
	/**
	 * Name : VINOTHKUMAR.M
	 * Created Date: 4/April/2016
	 * Modified Date:
	 * Description :   Common method to 1on1 Live Session Video Enable/Disable from spread sheet in Place [5]
	 * Ticket ID :
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String memberOneonOneSessionVideoEnableAndDisable(WebDriver driver,String testData)
	{
		String[] testData1 = testData.split(",");
		String inputMemberVideo=testData1[2];
		if ("MemberVideoEnable".equalsIgnoreCase(inputMemberVideo) )
		{
			try
			{
				jsClickByXPath(driver, OR.MEMBER_SESSION_VIDEO_OFF);
				WebElement memberVideoOn = driver.findElement(By.xpath(OR.MEMBER_SESSION_VIDEO_ON));
				verifyElementIsPresent(driver, memberVideoOn);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver, OR.MEMBER_SESSION_VIDEO_ON);
				jsClickByXPath(driver, OR.MEMBER_SESSION_VIDEO_OFF);
				WebElement memberVideoOn = driver.findElement(By.xpath(OR.MEMBER_SESSION_VIDEO_ON));
				verifyElementIsPresent(driver, memberVideoOn);
			}
		}
		else if ( "MemberVideoDisable".equalsIgnoreCase(inputMemberVideo) )
		{
			try
			{
				jsClickByXPath(driver, OR.MEMBER_SESSION_VIDEO_ON);
				WebElement memberVideoOff = driver.findElement(By.xpath(OR.MEMBER_SESSION_VIDEO_OFF));
				verifyElementIsPresent(driver, memberVideoOff);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver, OR.MEMBER_SESSION_VIDEO_OFF);
				jsClickByXPath(driver, OR.MEMBER_SESSION_VIDEO_ON);
				WebElement memberVideoOff = driver.findElement(By.xpath(OR.MEMBER_SESSION_VIDEO_OFF));
				verifyElementIsPresent(driver, memberVideoOff);
			}
		}
		return ElementWait;
	}
	/**
	 * Name : VINOTHKUMAR.M
	 * Created Date: 4/April/2016
	 * Modified Date:
	 * Description :   Common method to Lecture Live Session Coach Mic Enable/Disable from spread sheet in Place [11]
	 * Ticket ID :
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String coachLectureSessionMicEnableAndDisable(WebDriver driver1,String testData)
	{
		String[] testData1 = testData.split(",");
		String inputCoachMic=testData1[6];
		if ("CoachMicEnable".equalsIgnoreCase(inputCoachMic) )
		{
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_MIC_OFF);
				wait(driver1,"5");
				WebElement coachMicOn = driver1.findElement(By.xpath(OR.COACH_SESSION_MIC_ON));
				verifyElementIsPresent(driver1, coachMicOn);
			}
		}
		else if ( "CoachMicDisable".equalsIgnoreCase(inputCoachMic) )
		{
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_MIC_ON);
				wait(driver1,"5");
				WebElement coachMicOff = driver1.findElement(By.xpath(OR.COACH_SESSION_MIC_OFF));
				verifyElementIsPresent(driver1, coachMicOff);
			}
		}
		return ElementWait;
	}
	/**
	 * Name : VINOTHKUMAR.M
	 * Created Date: 4/April/2016
	 * Modified Date:
	 * Description :   Common method to Lecture Live Session Coach Video Enable/Disable from spread sheet in Place [12]
	 * Ticket ID :
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String coachLectureSessionVideoEnableAndDisable(WebDriver driver1,String testData)
	{
		String[] testData1 = testData.split(",");
		String inputCoachVideo=testData1[7];
		if ("CoachVideoEnable".equalsIgnoreCase(inputCoachVideo) )
		{
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_VIDEO_OFF);
				wait(driver1,"5");
				WebElement coachVideoOn = driver1.findElement(By.xpath(OR.COACH_SESSION_VIDEO_ON));
				verifyElementIsPresent(driver1, coachVideoOn);
			}
		}
		else if ( "CoachVideoDisable".equalsIgnoreCase(inputCoachVideo) )
		{
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_VIDEO_ON);
				wait(driver1,"5");
				WebElement coachVideoOff = driver1.findElement(By.xpath(OR.COACH_SESSION_VIDEO_OFF));
				verifyElementIsPresent(driver1, coachVideoOff);
			}
		}
		return ElementWait;
	}
	/**
	 * Name : VIGNESHRAJ.M
	 * Created Date: 4/April/2016
	 * Modified Date:
	 * Description :   Common method to Lecture Live Session Coach Member Tile Mic Enable/Disable from spread sheet in Place [15]
	 * Ticket ID :
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String lectureSessionCoachMemberTileMicEnable(WebDriver driver1,String testData)
	{
		String[] testData1 = testData.split(",");
		String inputCoachMemberTileMic=testData1[10];
		if ("CoachMemberTileMicEnable".equalsIgnoreCase(inputCoachMemberTileMic) )
		{
			{
				jsClickByXPath(driver1, OR.COACH_LECTURE_SESSION_COACH_TILE_MEMBER_MIC_DISABLED);
				wait(driver1,"5");
				WebElement coachTileMemberMicEnabled = driver1.findElement(By.xpath(OR.COACH_LECTURE_SESSION_COACH_TILE_MEMBER_MIC_ENABLED));
				verifyElementIsPresent(driver1, coachTileMemberMicEnabled);
			}
		}
		else if ( "CoachMemberTileMicDisable".equalsIgnoreCase(inputCoachMemberTileMic) )
		{
			{
				jsClickByXPath(driver1, OR.COACH_LECTURE_SESSION_COACH_TILE_MEMBER_MIC_ENABLED);
				wait(driver1,"5");
				WebElement coachTileMemberMicDisabled = driver1.findElement(By.xpath(OR.COACH_LECTURE_SESSION_COACH_TILE_MEMBER_MIC_DISABLED));
				verifyElementIsPresent(driver1, coachTileMemberMicDisabled);
			}
		}
		return ElementWait;
	}
	/**
	 * Name : VINOTHKUMAR.M
	 * Created Date: 4/April/2016
	 * Modified Date:
	 * Description :   Common method to Lecture Live Session Coach Member Mute/Unmute button from spread sheet in Place [16]
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String lectureSessionCoachMuteMembersButton(WebDriver driver1,String testData)
	{
		String[] testData1 = testData.split(",");
		String inputMuteMemberButton=testData1[11];
		if ("MuteMembersButton".equalsIgnoreCase(inputMuteMemberButton) )
		{
			try
			{
				jsClickByXPath(driver1, OR.COACH_LECTURE_SESSION_UNMUTE_MEMBERS_BUTTON);
				wait(driver1,"5");
				WebElement muteMembersButton = driver1.findElement(By.xpath(OR.COACH_LECTURE_SESSION_MUTE_MEMBERS_BUTTON));
				verifyElementIsPresent(driver1, muteMembersButton);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver1, OR.COACH_LECTURE_SESSION_MUTE_MEMBERS_BUTTON);
				wait(driver1, "5");
				jsClickByXPath(driver1, OR.COACH_LECTURE_SESSION_UNMUTE_MEMBERS_BUTTON);
				WebElement muteMembersButton = driver1.findElement(By.xpath(OR.COACH_LECTURE_SESSION_MUTE_MEMBERS_BUTTON));
				verifyElementIsPresent(driver1, muteMembersButton);
			}
		}
		else if ( "UnmuteMembersButton".equalsIgnoreCase(inputMuteMemberButton) )
		{
			try
			{
				jsClickByXPath(driver1, OR.COACH_LECTURE_SESSION_MUTE_MEMBERS_BUTTON);
				WebElement  unmuteMembers= driver1.findElement(By.xpath(OR.COACH_LECTURE_SESSION_UNMUTE_MEMBERS_BUTTON));
				verifyElementIsPresent(driver1, unmuteMembers);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver1, OR.COACH_LECTURE_SESSION_UNMUTE_MEMBERS_BUTTON);
				wait(driver1,"5");
				jsClickByXPath(driver1, OR.COACH_LECTURE_SESSION_MUTE_MEMBERS_BUTTON);
				WebElement  unmuteMembers= driver1.findElement(By.xpath(OR.COACH_LECTURE_SESSION_UNMUTE_MEMBERS_BUTTON));
				verifyElementIsPresent(driver1, unmuteMembers);
			}
		}
		return ElementWait;
	}
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   16/02/2016
	 * Modified Date:
	 * Description :   Create a common method to Verify Created Lecture Session
	 * Ticket ID :
	 * Required Inputs :
	 * @throws AWTException
	 */
	public static void cancelCreatedLectureLiveSession(WebDriver driver)
	{
		try
		{
			waitForElement(driver, OR.CREATED_LECTURE_SESSION_AREA);
			jsClickByXPath(driver, OR.CREATED_LECTURE_SESSION_AREA);
			WebElement assignSubstituteOption=driver.findElement(By.xpath(OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_SCHEDULE_ASSIGN_SUBSTITUTE_OPTION));
			waitForElement(driver, OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_SCHEDULE_CANCEL_SCHEDULE_OPTION);
			verifyElementIsPresent(driver,assignSubstituteOption);
			jsClickByXPath(driver, OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_SCHEDULE_CANCEL_SCHEDULE_OPTION);
			wait(driver, "5");
			waitForElement(driver, OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_CANCEL_SESSION_TITLE);
			WebElement lectureTitleLabel=driver.findElement(By.xpath(OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_LECTURE_TITLE_LABEL));
			WebElement startTimeLabel=driver.findElement(By.xpath(OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_START_TIME_LABEL));
			WebElement cancelSessionButton=driver.findElement(By.xpath(OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_CANCEL_SESSION_BUTTON));
			WebElement backToScheduleButton=driver.findElement(By.xpath(OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_BACK_TO_SCHEDULE));
			waitForElement(driver, OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_CANCEL_SESSION_TITLE);
			verifyElementIsPresent(driver,lectureTitleLabel);
			verifyElementIsPresent(driver,startTimeLabel);
			verifyElementIsPresent(driver,cancelSessionButton);
			verifyElementIsPresent(driver,backToScheduleButton);
			jsClickByXPath(driver, OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_CANCEL_SESSION_BUTTON);
			wait(driver, "5");
			WebElement popUpSuccessOkButton=driver.findElement(By.xpath(OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_SCHEDULE_CANCEL_POPUP_OK_BUTTON));
			verifyElementIsPresent(driver,popUpSuccessOkButton);
			jsClickByXPath(driver, OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_SCHEDULE_CANCEL_POPUP_OK_BUTTON);
			wait(driver, "5");
			waitForElement(driver, OR.SCHEDULE_PAGE_NAVIGATOR_RIGHT);
			jsClickByXPath(driver, OR.SCHEDULE_PAGE_NAVIGATOR_RIGHT);
			Navigate.pageDown(driver);
			Navigate.pageDown(driver);
		}
		catch(Exception e)
		{
			System.out.println("Previously there is no created Lecture session ");
		}
	}
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   4/APRIL/2016
	 * Modified Date:
	 * Description :   Create a common method to CompareAccountIDWithBrowserToolBarIcon
	 * Ticket ID :
	 * Required Inputs :
	 * @throws AWTException
	 */
	public static void compareAccountIDWithBrowserToolBarIcon(WebDriver driver1)
	{
		String BrowserToolBarAccountID = driver1.findElement( By.xpath( OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_MEMBER_ID )).getText();
		if(BrowserToolBarAccountID.equalsIgnoreCase( accountId ))
		{
			System.out.println("Member account ID is match with Browser Toolbar account ID"+BrowserToolBarAccountID);
		}
	}
	/**
	 * Name :VinothKumar.M
	 * Created Date:10/Mar/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve Available Member account ID
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String availableMemberAccountID="";
	public  static String retrieveAvailableMemberAccountId(WebDriver driver) throws ParseException, ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("SELECT ID, START_DT_TIME, END_DT_TIME, HOST_ID, ACCOUNT_ID,SESSION_TYPE FROM CALENDAR_EVENT WHERE START_DT_TIME>SYSDATE AND EVENT_TYPE='Session' AND SESSION_TYPE='1on1' ORDER BY 2 ASC");
		System.out.println("query executed");
		while(rs.next())
		{
			availableMemberAccountID= rs.getString("ACCOUNT_ID");
		}
		System.out.println("Available Member Account id "+availableMemberAccountID+" is retrieved from the Table");
		return availableMemberAccountID;
	}
	/**
	 * Name :Vigneshraj.M
	 * Created Date:10/Mar/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve Available Member Email ID with Account ID
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static String retrieveAvailableMemberEmailIdWithAccountID(WebDriver driver,String availableMemberAccountID) throws ParseException, ClassNotFoundException, SQLException
	{
		String availableMemberEmailIDWithAccountID="";
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
		ResultSet rs = stat.executeQuery("select email from ACCOUNT where id = '"+availableMemberAccountID+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			availableMemberEmailIDWithAccountID  = rs.getString("EMAIL");

		}
		System.out.println("Available Member Email ID "+availableMemberEmailIDWithAccountID+" is retrieved from the Table");
		return availableMemberEmailIDWithAccountID;
	}
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   29/Dec/15
	 * Modified Date:   12/Jan/16
	 * Description :   Create a common method for Live Session Member Login into Member user in framework
	 * Ticket ID :
	 * Required Inputs :  Username and Password
	 */
	public static void oneOnOneliveSessionMemberLogin(WebDriver driver)
	{
		wait(driver, "1");
		Navigate.get(driver, Directory.Memberurl);
		Navigate.maximize(driver);
		wait(driver, "15");
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
		jsClickByXPath(driver, OR.MEMBER_YOURMAIL);
		sendKeys(member_username,member_mail1);
		wait(driver, "2");
		System.out.println("Username is typed");
		WebElement member_password = driver.findElement(By.xpath(OR.MEMBER_YOURPASSWORD));
		jsClickByXPath(driver, OR.MEMBER_YOURPASSWORD);
		sendKeys(member_password,"Password1");
		wait(driver, "2");
		System.out.println("Password is typed");
		waitForElement(driver, OR.MEMBER_LOGIN_BUTTON);
		WebElement memberLoginButton = driver.findElement(By.xpath(OR.MEMBER_LOGIN_BUTTON));
		click(memberLoginButton);
		System.out.println("Login button is clicked");
		Navigate.waitTime(driver, "20");
		waitForElement(driver, OR.MEMBER_DASHBOARD);
		waitForAjax(driver);
		wait(driver, "15");
		System.out.println("Member logged in successfully");
	}
	/**
	 * Name : P.Abinaya
	 * Created Date: 05/May/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve Coach program id for customization session
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// Retrieve Coach Hostname for 1on1 Session
	// Substituting the Program ID in the query will give the Coach Hostname  for 1on1 session
	public static String CoachId="";
	public static String retrieveHostIDForCustomizationSessionWithProgramID(WebDriver driver, String accountProgramID) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("SELECT EVT.ID, EVT.HOST_ID, EVT.HOST_NAME FROM ACCOUNT_PROGRAM_SESSION_DETAIL PSD, CALENDAR_EVENT EVT WHERE EVT.ID=PSD.CALENDAR_EVENT_ID AND ACCOUNT_PROGRAM_ID='"+accountProgramID+"'AND PSD.SESSION_TYPE='Customization Session'");
		System.out.println("query executed");
		while(rs.next())
		{
			CoachId= rs.getString("HOST_ID");
			System.out.println("Customization Session Coach Host ID "+CoachId+" is retrieved from the Table");
		}
		return CoachId;
	}
	/**
	 * Name : P.Abinaya
	 * Created Date: 05/May/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve Coach program id for all session
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// Retrieve Coach Hostname for 1on1 Session
	// Substituting the Program ID in the query will give the Coach Hostname  for 1on1 session
	public static String retrieveHostIDForAllSessionWithProgramID(WebDriver driver, String accountProgramID) throws ClassNotFoundException, SQLException
	{
		String coachWithHostName1on1session ="";
		String coachID1on1session="";
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("SELECT EVT.ID, EVT.HOST_ID, EVT.HOST_NAME FROM ACCOUNT_PROGRAM_SESSION_DETAIL PSD, CALENDAR_EVENT EVT WHERE EVT.ID=PSD.CALENDAR_EVENT_ID AND ACCOUNT_PROGRAM_ID='"+accountProgramID+"'AND HOST_ID IS NOT NULL");
		System.out.println("query executed");
		if(rs.next())
		{
			coachWithHostName1on1session= rs.getString("HOST_ID");
			System.out.println("1on1 Session Coach Host ID "+coachWithHostName1on1session+" is retrieved from the Table");

			coachID1on1session= rs.getString("ID");
			System.out.println("1on1 Session Coach  ID "+coachID1on1session+" is retrieved from the Table");
		}
		return coachWithHostName1on1session;

	}
	/**
	 * Name :VinothKumar.M
	 * Created Date:12/May/2016
	 * Modified Date:
	 * Description :   Create a common method for capture image
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String fridayBluearea(WebDriver driver) throws FindFailed
	{
		Screen s=new Screen();
		Pattern image = new Pattern("C:/workspace/zillion_automation/Zillion/testcases/UploadFiles/Capture.png");
		s.wait(image, 15);
		s.click(image);
		return ElementWait;
	}
	/**
	 * Name :Suresh
	 * Created Date: 10/June/2016
	 * Modified Date:
	 * Description :   Create a common method for Verifying DB for Member onboarding status Classroom Not started
	 */
	public static String fridayBluearea1(WebDriver driver) throws FindFailed
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0, -250)", "");
		wait(driver, "2");
		Screen s=new Screen();
		Pattern image = new Pattern("C:/workspace/zillion_automation/Zillion/testcases/UploadFiles/Capture2.png");
		s.wait(image, 15);
		s.click(image);
		return ElementWait;
	}
	/**
	 * Name :Suresh
	 * Created Date: 10/May/2016
	 * Modified Date:
	 */
	public static String  fridayUnavailablearea(WebDriver driver) throws FindFailed
	{
		Screen s=new Screen();
		Pattern image = new Pattern("C:/workspace/zillion_automation/Zillion/testcases/UploadFiles/Available.png");
		s.click(image);
		wait(driver, "6");
		return ElementWait;
	}
	/**
	 * Name :Vigneshraj.M
	 * Created Date:11/Apr/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve the orbera member to schedule 1 on 1 session from member
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String member_mail1="";
	public static String getOrberaMember1on1SessionFromMember(WebDriver driver) throws ClassNotFoundException, SQLException
	{
		String port = Directory.Oracle_Port;
		String database_name=Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("SELECT ACNT.EMAIL Member_Email, PROV.EMAIL Coach_Email FROM SUMMARY_ACCOUNT_TODATE SMRY,PROVIDER PROV, ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID AND SMRY.ACCOUNT_ID=ACNT.ID AND ACCOUNT_ID IN(SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ID IN (SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_TYPE='1on1' AND SESSION_STATUS IN ('Unscheduled') AND PROGRAM_INTERVAL_START_DT < SYSDATE AND PROGRAM_INTERVAL_END_DT>SYSDATE AND ACCOUNT_PROGRAM_ID IN (SELECT ID FROM ACCOUNT_PROGRAM WHERE ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM SUMMARY_ACCOUNT_TODATE WHERE SUMMARY_EXTENSION LIKE '%medicalProcedureMilestone1Answer%YES, I HAD IT%') AND ACNT.EMAIL not like '%QAmember%-default@yopmail.com%' AND MAST_PROGRAM_ID='30100AA52BEA' AND IS_ACTIVE=1)))ORDER BY SMRY.CREATED_DT ASC");
		System.out.println("query executed");
		while(rs.next())
		{
			member_mail1 = rs.getString("MEMBER_EMAIL");
			coach_mail1 = rs.getString("COACH_EMAIL");
		}
		System.out.println("Member Mail id is "+member_mail1);
		return member_mail1;
	}
	public static String coach_mail1="";
	public static String getOrberaMemberMissed1on1SessionFromMember(WebDriver driver) throws ClassNotFoundException, SQLException
	{
		String port = Directory.Oracle_Port;
		String database_name=Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("SELECT ACNT.EMAIL Member_Email, PROV.EMAIL Coach_Email FROM SUMMARY_ACCOUNT_TODATE SMRY,PROVIDER PROV, ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID AND SMRY.ACCOUNT_ID=ACNT.ID AND ACCOUNT_ID IN(SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ID IN (SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_TYPE='1on1' AND SESSION_STATUS IN ('Missed') AND PROGRAM_INTERVAL_START_DT < SYSDATE AND PROGRAM_INTERVAL_END_DT>SYSDATE AND ACCOUNT_PROGRAM_ID IN (SELECT ID FROM ACCOUNT_PROGRAM WHERE ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM SUMMARY_ACCOUNT_TODATE WHERE SUMMARY_EXTENSION LIKE '%medicalProcedureMilestone1Answer%YES, I HAD IT%') AND MAST_PROGRAM_ID='30100AA52BEA' AND IS_ACTIVE=1)))ORDER BY SMRY.CREATED_DT DESC");
		System.out.println("query executed");
		String member_mail1="";
		while(rs.next())
		{
			member_mail1 = rs.getString("Member_Email");
			coach_mail1 = rs.getString("Coach_Email");
		}
		return member_mail1;
	}
	/**
	 * Name :Leena P.
	 * Created Date:17/Aug/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve the orbera member who missed the lecture session
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String getOrberaMemberMissedLectureSessionFromMember(WebDriver driver) throws ClassNotFoundException, SQLException
	{
		String port = Directory.Oracle_Port;
		String database_name=Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select ACNT.EMAIL, CE.START_DT_TIME FROM CALENDAR_EVENT CE , CALENDAR_EVENT_ATTENDEE CEA, ACCOUNT ACNT WHERE CE.ID= CEA.EVENT_ID AND ACNT.ID= CEA.ACCOUNT_ID AND CE.SESSION_TYPE_ID= '30100AA52BEAEE0EE3' AND CE.SESSION_STATUS= 'Missed' AND CE.START_DT<SYSDATE AND ACNT.EMAIL not like '%api%' ORDER BY CE. START_DT ASC");
		System.out.println("query executed");
		String member_mail1="";
		while(rs.next())
		{
			member_mail1 = rs.getString("EMAIL");
		}
		return member_mail1;
	}
	/**
	 * Name :Leena P.
	 * Created Date:17/Aug/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve the orbera member who missed the lecture session
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String getOrberaMemberReminderLectureSessionFromMember(WebDriver driver) throws ClassNotFoundException, SQLException
	{

		String port = Directory.Oracle_Port;
		String database_name=Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select ACNT.EMAIL Member_Email, CE.START_DT_TIME FROM CALENDAR_EVENT CE , CALENDAR_EVENT_ATTENDEE CEA, ACCOUNT ACNT WHERE CE.ID= CEA.EVENT_ID AND ACNT.ID= CEA.ACCOUNT_ID AND CE.SESSION_TYPE_ID= '30100AA52BEAEE0EE3' AND CE.SESSION_STATUS= 'Completed' AND CE.START_DT<SYSDATE AND ACNT.EMAIL not like '%api%' ORDER BY CE. START_DT ASC");
		System.out.println("query executed");
		String member_mail1="";
		while(rs.next())
		{
			member_mail1 = rs.getString("Member_Email");

		}
		return member_mail1;
	}
	/**
	 * Name :Vinothkumar.M
	 * Created Date:11/Apr/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve the orbera coach to attend 1 on 1 session from coach
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	static String sessiontime1=null;
	public  static void orberaMemberSessionTime(WebDriver driver) throws ParseException
	{
		WebElement memberSessionTime = driver.findElement(By.xpath(OR.ORBERA_MEMBER_SESSION_TIME));
		String sessionsTime=memberSessionTime.getText();
		SimpleDateFormat ft = new SimpleDateFormat("EEE hhh:mm a");
		java.util.Date t=ft.parse(sessionsTime);
		ft.applyPattern("hh:mma");
		sessiontime1=ft.format(t).toLowerCase().trim();
		System.out.print(ft.format(t));
		System.out.print("Time"+sessiontime1);
	}
	/**
	 * Name : Vinothkumar.M
	 * Created Date: 21/Sep/2016
	 * Modified Date:
	 * Description :   Common method to attend the Orbera 1on1 Live session
	 * Ticket ID :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static void orberaCoachUpcomingSessionTime(WebDriver driver1) throws ParseException
	{
		WebElement coachUpcomingSessionTime = driver1.findElement(By.xpath("//tr//td[text()='"+sessiontime1+"']/following::td[text()='1on1']/following-sibling::td/following-sibling::td/following-sibling::td/div/a"));
		verifyElementIsPresent(driver1, coachUpcomingSessionTime);
		click(coachUpcomingSessionTime);
		wait( driver1, "20" );
	}
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   03/02/2016
	 * Modified Date:
	 * Description :   Create a common method to Member verify lecture session
	 * Ticket ID :
	 * Required Inputs :
	 */
	// Method will verify the created Lecture session by clicking View session for Sign Up
	public static String memberVerifyLectureSession(WebDriver driver, String verifyMemberSignUp1)
	{
		verifyMemberSignUp1= verifyMemberSignUp1.trim();
		int totalPaginationCount = driver.findElements(By.xpath("//ul[@class='pagination']/li[*]")).size();
		int actualCount= totalPaginationCount-3;
		String actualCount1= Integer.toString(actualCount);
		System.out.println("totalPaginationCount: "+totalPaginationCount);
		System.out.println("actualCount: "+actualCount);
		for(int i=4;i<totalPaginationCount;i++)
		{
			try
			{
				WebElement saturdayText= driver.findElement(By.xpath(OR.VIEW_SESSION_OF_TITLE_START+verifyMemberSignUp1+OR_END));
				WebElement lectureSessionTiming= driver.findElement(By.xpath(OR.VIEW_SESSION_OF_LECTURE_SESSION_TIMING_START+verifyMemberSignUp1+VIEW_SESSION_OF_LECTURE_SESSION_TIMING_END));
				verifyElementIsPresent(driver, saturdayText);
				verifyElementIsPresent(driver, lectureSessionTiming);
				WebElement signUpButton= driver.findElement(By.xpath(OR.VIEW_SESSION_FOLLOWING_SIGNUP_START+verifyMemberSignUp1+VIEW_SESSION_FOLLOWING_SIGNUP_END));
				verifyElementIsPresent(driver, signUpButton);
				click(signUpButton);
				Navigate.waitTime(driver, "5");
				break;
			}
			catch(Exception e)
			{
				WebElement nextPageNavigator= driver.findElement(By.xpath("//ul[@class='pagination']/li["+i+"]//span[@id='lecturePagingData']"));
				verifyElementIsPresent(driver, nextPageNavigator);
				click(nextPageNavigator);
				Navigate.waitTime(driver, "5");
				System.out.println("No of pagination: "+i);
			}
		}
		return actualCount1;
	}
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   03/02/2016
	 * Modified Date:
	 * Description :   Create a common method to Member verify lecture session
	 * Ticket ID :
	 * Required Inputs :
	 */
	// Method will verify the created Lecture session by clicking View session for Sign Up
	public static String wellNessCorpMemberVerifyLectureSession(WebDriver driver)
	{
		int totalPaginationCount = driver.findElements(By.xpath("//ul[@class='pagination']/li[*]")).size();
		int actualCount= totalPaginationCount-3;
		String actualCount1= Integer.toString(actualCount);
		System.out.println("totalPaginationCount: "+totalPaginationCount);
		System.out.println("actualCount: "+actualCount);
		for(int i=4;i<totalPaginationCount;i++)
		{
			try
			{
				WebElement saturdayText= driver.findElement(By.xpath(OR.WELLNESSCORP_VIEW_SESSION_OF_TITLE));
				WebElement lectureSessionTiming= driver.findElement(By.xpath(OR.WELLNESSCORP_VIEW_SESSION_OF_LECTURE_SESSION_TIMING));
				verifyElementIsPresent(driver, saturdayText);
				verifyElementIsPresent(driver, lectureSessionTiming);
				WebElement signUpButton= driver.findElement(By.xpath(OR.WELLNESSCORP_VIEW_SESSION_FOLLOWING_SIGNUP));
				verifyElementIsPresent(driver, signUpButton);
				click(signUpButton);
				Navigate.waitTime(driver, "5");
				break;
			}
			catch(Exception e)
			{
				WebElement nextPageNavigator= driver.findElement(By.xpath("//ul[@class='pagination']/li["+i+"]//span[@id='lecturePagingData']"));
				verifyElementIsPresent(driver, nextPageNavigator);
				click(nextPageNavigator);
				Navigate.waitTime(driver, "5");
				System.out.println("No of pagination: "+i);
			}
		}
		return actualCount1;
	}
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   03/02/2016
	 * Modified Date:
	 * Description :   Create a common method to VERIFY CURRENT DATE
	 * Required Inputs :
	 */
	// Method will sign up the created Lecture session
	public static String wellnessCorpLiveSessionMemberSignUpLectureSession(WebDriver driver,String testData,String verifyMemberSignUp3)
	{
		//Member-1-LectureSignUp
		String[] testData1 = testData.split(",");
		String inputUsername=testData1[1];
		String inputPassword=testData1[2];
		wait(driver, "8");
		Navigate.get(driver, Directory.Memberurl);
		Navigate.maximize(driver);
		wait(driver, "20");
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
		sendKeys(member_username, inputUsername);
		System.out.println("Username is typed");
		WebElement member_password = driver.findElement(By.xpath(OR.MEMBER_YOURPASSWORD));
		jsClickByXPath(driver, OR.MEMBER_YOURPASSWORD);
		sendKeys(member_password,inputPassword);
		System.out.println("Password is typed");
		waitForElement(driver, OR.MEMBER_LOGIN_BUTTON);
		jsClickByXPath(driver, OR.MEMBER_LOGIN_BUTTON);
		System.out.println("Login button is clicked");
		wait(driver, "15");
		waitForElement(driver, OR.VIEW_SESSION_BUTTON);
		waitForAjax(driver);
		wait(driver, "5");
		System.out.println("Member logged in successfully");
		WebElement viewSessionButton = driver.findElement(By.xpath(OR.VIEW_SESSION_BUTTON));
		verifyElementIsPresent(driver, viewSessionButton);
		click(viewSessionButton);
		com.zillion.qa.member.liveSessionSubCommonMethods.wellnessCorpLiveSessionmemberVerifyLectureSession(driver, verifyMemberSignUp3);
		jsClickByXPath(driver, OR.PLACEMENT_APPOINTMENT_CLOSE_BUTTON);
		Navigate.refreshPage(driver);
		wait( driver, "6" );
		return "";
	}
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   03/02/2016
	 * Modified Date:
	 * Description :   Create a common method to Timeslot Search for Lecture Live session
	 * Ticket ID :
	 * Required Inputs :
	 */
	// Method will verify the  created available date and time slot search
	public static String lectureSessionTimeSlotSearch(WebDriver driver)
	{
		WebElement nextPageNavigator= driver.findElement(By.xpath(OR.NEXT_PAGE_NAVIGATOR));
		verifyElementIsPresent(driver, nextPageNavigator);
		click(nextPageNavigator);
		wait(driver, "4");
		try
		{
			WebElement fridayHeader= driver.findElement(By.xpath(OR.FRIDAY_HEADER));
			WebElement fridayEveningButton= driver.findElement(By.xpath(OR.FRIDAY_EVENING_BUTTON));
			verifyElementIsPresent(driver, fridayHeader);
			verifyElementIsPresent(driver, fridayEveningButton);
			click(fridayEveningButton);
		}
		catch(Exception e)
		{
			WebElement previousPageNavigator= driver.findElement(By.xpath(OR.PREVIOUS_PAGE_NAVIGATOR));
			verifyElementIsPresent(driver, previousPageNavigator);
			click(previousPageNavigator);
			wait(driver, "4");
			WebElement fridayHeader= driver.findElement(By.xpath(OR.FRIDAY_HEADER));
			WebElement fridayEveningButton= driver.findElement(By.xpath(OR.FRIDAY_EVENING_BUTTON));
			verifyElementIsPresent(driver, fridayHeader);
			verifyElementIsPresent(driver, fridayEveningButton);
			click(fridayEveningButton);
		}
		return "Timeslot should be searchedin the view session";
	}
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   03/02/2016
	 * Modified Date:
	 * Description :   Create a common method to Timeslot Search for Lecture Live session
	 * Ticket ID :
	 * Required Inputs :
	 */
	// Method will verify the  created available date and time slot search
	public static String verifyCreatedLectureSessionForLiveSession(WebDriver driver,String lectureLiveSession321)
	{
		try
		{
			WebElement createdLectureSession= driver.findElement(By.xpath("//div[contains(text(),'"+lectureLiveSession321+"')]"));
			verifyElementIsPresent(driver, createdLectureSession);
			return lectureLiveSession321;
		}
		catch(Exception e)
		{
			WebElement pageNavigator= driver.findElement(By.xpath(OR.COACHES_GEAR_BUTTON_SCHEDULE_PAGE_NAVIGATOR_RIGHT));
			verifyElementIsPresent(driver, pageNavigator);
			click(pageNavigator);
			wait(driver,"3");
			Navigate.pageDown(driver);
			Navigate.pageDown(driver);
			WebElement createdLectureSession= driver.findElement(By.xpath("//div[contains(text(),'"+lectureLiveSession321+"')]"));
			verifyElementIsPresent(driver, createdLectureSession);
		}
		return ElementWait;
	}
	/**
	 * Name :     LEENA P.
	 * Created Date:   Oct/18//2016
	 * Modified Date:
	 * Description :   Create a common method to Verify Repeats button
	 * Ticket ID :
	 * Required Inputs :
	 * @throws AWTException
	 */
	public static void checkRepeatsButton(WebDriver driver)
	{
		try{
			WebElement fridayCheckBox = driver.findElement(By.xpath(OR.LECTURE_SESSION_FRIDAY_CHECKBOX));
			verifyElementIsPresent(driver, fridayCheckBox);
		}
		catch(Exception e) {
			driver.findElement(By.xpath(OR.COACHES_SCHEDULE_CALENDER_SCHEDULE_REPEATS_BUTTON)).click();
		}
	}
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   03/02/2016
	 * Modified Date:
	 * Description :   Create a common method to Member verify lecture session
	 * Ticket ID :
	 * Required Inputs :
	 */
	// Method will verify the created Lecture session by clicking View session for Sign Up
	public static String wellnessCorpLiveSessionmemberVerifyLectureSession(WebDriver driver, String verifyMemberSignUp3)
	{
		verifyMemberSignUp3= verifyMemberSignUp3.trim();
		int totalPaginationCount = driver.findElements(By.xpath("//ul[@class='pagination']/li[*]")).size();
		int actualCount= totalPaginationCount-3;
		String actualCount1= Integer.toString(actualCount);
		System.out.println("totalPaginationCount: "+totalPaginationCount);
		System.out.println("actualCount: "+actualCount);
		for(int i=4;i<totalPaginationCount;i++)
		{
			try
			{
				WebElement saturdayText= driver.findElement(By.xpath(OR.VIEW_SESSION_OF_TITLE_START+verifyMemberSignUp3+OR_END));
				WebElement lectureSessionTiming= driver.findElement(By.xpath(OR.VIEW_SESSION_OF_LECTURE_SESSION_TIMING_START+verifyMemberSignUp3+VIEW_SESSION_OF_LECTURE_SESSION_TIMING_END));
				verifyElementIsPresent(driver, saturdayText);
				verifyElementIsPresent(driver, lectureSessionTiming);
				WebElement signUpButton= driver.findElement(By.xpath(OR.VIEW_SESSION_FOLLOWING_SIGNUP_START+verifyMemberSignUp3+VIEW_SESSION_FOLLOWING_SIGNUP_END));
				verifyElementIsPresent(driver, signUpButton);
				click(signUpButton);
				Navigate.waitTime(driver, "5");
				break;
			}
			catch(Exception e)
			{
				WebElement nextPageNavigator= driver.findElement(By.xpath("//ul[@class='pagination']/li["+i+"]//span[@id='lecturePagingData']"));
				verifyElementIsPresent(driver, nextPageNavigator);
				click(nextPageNavigator);
				Navigate.waitTime(driver, "5");
				System.out.println("No of pagination: "+i);
			}
		}
		return actualCount1;
	}
	public static void selectNextDateTimeSlot(WebDriver driver)
	{
		{
			String CurrentDateOR = "//div[@id='timeSelection']/div/div/div[2]/div/span";
			WebElement CurrentDate = driver.findElement(By.xpath(CurrentDateOR));
			String date2 = CurrentDate.getText();
			String CurrentDateEvents = "//span[text()='"+date2+"']/parent::div/following-sibling::div/button/div[text()='Evening']";
			System.out.println("Label Name : "+CurrentDate);
			WebElement Currentevents = driver.findElement(By.xpath(CurrentDateEvents));
			click( Currentevents );
		}
	}
	public static String getCoachfor1on1(WebDriver driver)
	{
		return coach_mail1;
	}
	public static void select1on1ForMember(WebDriver driver, String inputData)
	{
		try{
			String oneonOnecancelLink1 = "//div[contains(text(),'1on1 for "+inputData+"')]";
			WebElement oneonOnecancelLink = driver.findElement(By.xpath(oneonOnecancelLink1));
			click(oneonOnecancelLink);
		}
		catch(Exception e) {
			WebElement pageNavigator= driver.findElement(By.xpath(OR.COACHES_GEAR_BUTTON_SCHEDULE_PAGE_NAVIGATOR_RIGHT));
			verifyElementIsPresent(driver, pageNavigator);
			click(pageNavigator);
			wait(driver,"3");
			String oneonOnecancelLink1 = "//div[contains(text(),'1on1 for "+inputData+"')]";
			WebElement oneonOnecancelLink = driver.findElement(By.xpath(oneonOnecancelLink1));
			click(oneonOnecancelLink);
		}
	}
	public static String getOrberaCoach1on1SessionFromMember(WebDriver driver) throws ClassNotFoundException, SQLException
	{
		String port = Directory.Oracle_Port;
		String database_name=Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("SELECT ACNT.EMAIL Member_Email, PROV.EMAIL Coach_Email FROM SUMMARY_ACCOUNT_TODATE SMRY,PROVIDER PROV, ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID AND SMRY.ACCOUNT_ID=ACNT.ID AND ACCOUNT_ID IN(SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ID IN (SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_TYPE='1on1' AND SESSION_STATUS IN ('Missed') AND PROGRAM_INTERVAL_START_DT < SYSDATE AND PROGRAM_INTERVAL_END_DT>SYSDATE AND ACCOUNT_PROGRAM_ID IN (SELECT ID FROM ACCOUNT_PROGRAM WHERE ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM SUMMARY_ACCOUNT_TODATE WHERE SUMMARY_EXTENSION LIKE '%medicalProcedureMilestone1Answer%YES, I HAD IT%') AND MAST_PROGRAM_ID='30100AA52BEA' AND IS_ACTIVE=1)))ORDER BY SMRY.CREATED_DT DESC");
		System.out.println("query executed");
		while(rs.next())
		{
			coach_mail1 = rs.getString("COACH_EMAIL");
		}
		System.out.println("Coach Mail id is "+coach_mail1);
		return coach_mail1;
	}
	/**
	 * Name :     VINOTHKUMAR
	 * Created Date:   22/03/2016
	 * Modified Date:
	 * Description :   Create a common method to Allow the Plugin and Remember
	 * Ticket ID :
	 * Required Inputs :
	 * @throws FindFailed
	 * @throws AWTException
	 */
	// Method will Allow the plugin if there is Allow and Remember Else it will skip the execution
	public static void allowPluginsForSameBrowser(WebDriver driver) throws FindFailed, AWTException
	{
		{
			String OSName=Platform.OS.toUpperCase();
			if(OSName.contains("MAC"))
			{
				Screen Allow=new Screen();
				wait( driver, "8" );
				Pattern image = new Pattern(Directory.uploadFilePath+"AllowFireFoxMac.PNG");
				Allow.wait(image, 15);
				Allow.click(image);
				wait( driver, "3" );
				Robot rb = new Robot();
				wait( driver, "8" );
				rb.keyPress(KeyEvent.VK_CONTROL);
				rb.keyPress(KeyEvent.VK_A);
				rb.keyRelease(KeyEvent.VK_CONTROL);
				rb.keyRelease(KeyEvent.VK_A);
				rb.keyPress(KeyEvent.VK_CONTROL);
				rb.keyPress(KeyEvent.VK_R);
				rb.keyRelease(KeyEvent.VK_CONTROL);
				rb.keyRelease(KeyEvent.VK_R);
			}
			else if (OSName.contains("WINDOWS"))
			{
				Robot rb = new Robot();
				rb.keyPress(KeyEvent.VK_ALT);
				rb.keyPress(KeyEvent.VK_A);
				rb.keyRelease(KeyEvent.VK_ALT);
				rb.keyRelease(KeyEvent.VK_A);
				wait( driver, "4" );
				rb.keyPress(KeyEvent.VK_ALT);
				rb.keyPress(KeyEvent.VK_R);
				rb.keyRelease(KeyEvent.VK_ALT);
				rb.keyRelease(KeyEvent.VK_R);
			}
		}
	}
	/**
	 * Name :     VINOTHKUMAR
	 * Created Date:   22/03/2016
	 * Modified Date:
	 * Description :   Create a common method to Allow the Plugin and Remember
	 * Ticket ID :
	 * Required Inputs :
	 * @throws FindFailed
	 * @throws AWTException
	 */
	// Method will Allow the plugin if there is Allow and Remember Else it will skip the execution
	public static void orberFeaturesSupportAlert(WebDriver driver) throws FindFailed, AWTException
	{
		try
		{
			WebElement supportText = driver.findElement(By.xpath(OR.MEMBER_CERTAIN_FEATURES_IN_THE_ORBERA_NOT_SUPPORTED_BY_YOUR_BROWSER_WARNING_MSG));
			verifyElementIsPresent(driver, supportText);
			WebElement supportPopupClose = driver.findElement(By.xpath(OR.MEMBER_CERTAIN_FEATURES_IN_THE_ORBERA_CLOSE_BUTTON));
			verifyElementIsPresent(driver, supportPopupClose);
			jsClickByXPath(driver, OR.MEMBER_CERTAIN_FEATURES_IN_THE_ORBERA_CLOSE_BUTTON);
			wait(driver, "2");
			WebElement attendNowButton = driver.findElement(By.xpath(OR.LECTURE_SESSION_MEMBER_ATTEND_NOW_BUTTON));
			verifyElementIsPresent(driver, attendNowButton);
			jsClickByXPath(driver, OR.LECTURE_SESSION_MEMBER_ATTEND_NOW_BUTTON);
			wait(driver, "10");
		}
		catch (Exception e)
		{

		}
	}
	/**
	 * Name : VIGNESHRAJ.M
	 * Created Date: 4/April/2016
	 * Modified Date:
	 * Description :   Common method to Lecture Live Session Coach Member Tile Mic Enable/Disable from spread sheet in Place [15]
	 * Ticket ID :
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String lectureSessionCoachMemberTileMicEnableSameBrowser(WebDriver driver1,String testData)
	{
		if ("CoachMemberTileMicEnable".equalsIgnoreCase(testData) )
		{
			{
				WebElement coachTileMemberMicDisabled = driver1.findElement(By.xpath(OR.COACH_LECTURE_SESSION_COACH_TILE_MEMBER_MIC_DISABLED));
				verifyElementIsPresent(driver1, coachTileMemberMicDisabled);
				jsClickByXPath(driver1, OR.COACH_LECTURE_SESSION_COACH_TILE_MEMBER_MIC_DISABLED);
				wait(driver1,"3");
				WebElement coachTileMemberMicEnabled = driver1.findElement(By.xpath(OR.COACH_LECTURE_SESSION_COACH_TILE_MEMBER_MIC_ENABLED));
				verifyElementIsPresent(driver1, coachTileMemberMicEnabled);
			}
		}
		else if ( "CoachMemberTileMicDisable".equalsIgnoreCase(testData) )
		{
			{
				jsClickByXPath(driver1, OR.COACH_LECTURE_SESSION_COACH_TILE_MEMBER_MIC_ENABLED);
				wait(driver1,"5");
				WebElement coachTileMemberMicDisabled = driver1.findElement(By.xpath(OR.COACH_LECTURE_SESSION_COACH_TILE_MEMBER_MIC_DISABLED));
				verifyElementIsPresent(driver1, coachTileMemberMicDisabled);
			}
		}
		return ElementWait;
	}
	/**
	 * Name : VIGNESHRAJ.M
	 * Created Date: 4/April/2016
	 * Modified Date:
	 * Description :   Common method to Lecture Live Session Coach Member Tile Mic Enable/Disable from spread sheet in Place [15]
	 * Ticket ID :
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String verifyMemberTileEnableOrDisable(WebDriver driver,String testData)
	{
		// Verify Coach Member tile Mic Enable/Disable
		if ("CoachMemberTileMicDisable".equalsIgnoreCase(testData))
		{
			WebElement memberMicDisabled = driver.findElement(By.xpath(OR.LECTURE_SESSION_MEMBER_SESSION_MIC_DISABLED));
			verifyElementIsPresent(driver, memberMicDisabled);
		}
		else if ( "CoachMemberTileMicEnable".equalsIgnoreCase(testData) )
		{
			WebElement micEnabled = driver.findElement(By.xpath(OR.MEMBER_SESSION_MIC_ON));
			verifyElementIsPresent(driver, micEnabled);
		}
		return testData;
	}
	/**
	 * Name : VIGNESHRAJ.M
	 * Created Date: 4/April/2016
	 * Modified Date:
	 * Description :   Common method to Lecture Live Session Coach Member Tile Mic Enable/Disable from spread sheet in Place [15]
	 * Ticket ID :
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String screenShotForMemberLiveSession(WebDriver driver)
	{
		ZadoReports.add("TakeScreenShot","Screen shot for the Member Screen share","", "Screen shot took successfully for Member screen share",Objects.toString("", ""),LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));
		return ElementWait;
	}
	/**
	 * Name : VIGNESHRAJ.M
	 * Created Date: 4/April/2016
	 * Modified Date:
	 * Description :   Common method to Lecture Live Session Coach Member Tile Mic Enable/Disable from spread sheet in Place [15]
	 * Ticket ID :
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String memberTileBrowser(WebDriver driver)
	{
		String browser= Directory.browser;
		if ("firefox".equalsIgnoreCase(browser) )
		{
			WebElement firefoxBrowserIcon = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_FIREFOX_BROWSER_ICON));
			verifyElementIsPresent(driver, firefoxBrowserIcon);
			jsClickByXPath(driver, OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_FIREFOX_BROWSER_ICON);
			wait( driver, "3" );
		}
		else if ( "ie".equalsIgnoreCase(browser) )
		{
			WebElement ieBrowserIcon = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_IE_BROWSER_ICON));
			verifyElementIsPresent(driver, ieBrowserIcon);
			jsClickByXPath(driver, OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_IE_BROWSER_ICON);
			wait( driver, "3" );
		}
		else if ( "chrome".equalsIgnoreCase(browser) )
		{
			WebElement chromeBrowserIcon = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_CHROME_BROWSER_ICON));
			verifyElementIsPresent(driver, chromeBrowserIcon);
			jsClickByXPath(driver, OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_CHROME_BROWSER_ICON);
			wait( driver, "3" );
		}
		return browser;
	}
	/**
	 * Name :Bharath.M
	 * Created Date:11/Jan/2017
	 * Modified Date:
	 * Description :   Create a common method to retrieve Available Member Email ID(Modified querry)
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public  static String retrieveAvailableMemberEmailId(WebDriver driver) throws ParseException, ClassNotFoundException, SQLException
	{
		String availableMemberEmailIDWithAccountID="";
		String availableMemberEmailID="";
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
		ResultSet rs = stat.executeQuery("select A.EMAIL, SATD.ASSIGNED_PROVIDER_NAME, SATD.ONBOARDING_STATUS, A.ID from ACCOUNT A, SUMMARY_ACCOUNT_TODATE SATD where A.ID= SATD.ACCOUNT_ID AND SATD.MAST_PROGRAM_ID= '02' AND SATD.ONBOARDING_STATUS= 'CLASSROOM NOT ALLOCATED' and SATD.MP_Name='RA - Real Appeal' AND A.EMAIL NOT LIKE '%api%' AND  A.EMAIL NOT LIKE 'QAmember%default@yopmail.com' and SATD.PROGRAM_INTERVAL_NUMBER <'52' ORDER BY SATD.CREATED_DT DESC");
		System.out.println("query executed");
		while(rs.next())
		{
			availableMemberEmailID  = rs.getString("EMAIL");
		}
		System.out.println("Available Member Email ID "+availableMemberEmailIDWithAccountID+" is retrieved from the Table");
		return availableMemberEmailID;
	}
	/**
	 * Name :Bharath.M
	 * Created Date:13/Jan/2017
	 * Modified Date:
	 * Description :   Create a common method to retrieve Available Member Email ID with ID(Modified querry by jaspreet)
	 * Required Inputs :
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public  static String retrieveAvailableMemberEmailIdWithID(WebDriver driver,String inputdata,String Status1,String Environment1) throws ParseException, ClassNotFoundException, SQLException
	{
		String availableMemberEmailID="";
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
		ResultSet rs = stat.executeQuery("select A.EMAIL as MEMBER_EMAIL, SATD.ASSIGNED_PROVIDER_NAME, SATD.ONBOARDING_STATUS, A.ID as MEMBER_ID from ACCOUNT A, SUMMARY_ACCOUNT_TODATE SATD where A.ID= SATD.ACCOUNT_ID AND SATD.MAST_PROGRAM_ID= '02' AND SATD.ONBOARDING_STATUS= '"+Status1+"' and SATD.MP_Name='"+Environment1+"' AND A.EMAIL NOT LIKE '%api%' AND A.EMAIL not like '%QAmember%-default@yopmail.com%' AND A.Status='Active' AND A.EMAIL NOT LIKE '%info%' AND SATD.PROGRAM_INTERVAL_NUMBER <'52' ORDER BY SATD.CREATED_DT DESC");
		System.out.println("query executed");
		System.out.println("input"+inputdata);
		if(inputdata.equalsIgnoreCase("ID"))
		{
			if(rs.next())
			{
				availableMemberEmailID  = rs.getString("MEMBER_ID");
				System.out.println("Available Member  ID "+availableMemberEmailID+" is retrieved from the Table");
			}
		}
		else if(inputdata.equalsIgnoreCase("EMAIL"))
		{
			if(rs.next())
			{
				availableMemberEmailID  = rs.getString("MEMBER_EMAIL");
				System.out.println("Available Member Email "+availableMemberEmailID+" is retrieved from the Table");
			}
		}
		return availableMemberEmailID;
	}
	/**
	 * Name :VIGNESHRAJ.M
	 * Created Date:23/Jan/2017
	 * Modified Date:
	 * Description :   Create a common method to handle the Orbera Removal Popup
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static void orberaRemovalHandlePopup(WebDriver driver)
	{
		try
		{
			WebElement removalPopupText = driver.findElement(By.xpath(OR.MEMBER_ORBERA_REMOVAL_POPUP_TEXT));
			verifyElementIsPresent(driver, removalPopupText);
			WebElement removalPopupButton = driver.findElement(By.xpath(OR.MEMBER_ORBERA_REMOVAL_YES_I_HAD_BUTTON));
			verifyElementIsPresent(driver, removalPopupButton);
			jsClickByXPath(driver, OR.MEMBER_ORBERA_REMOVAL_YES_I_HAD_BUTTON);
			wait(driver, "3");
		}
		catch (Exception e)
		{

		}
	}
	/**
	 * Name :Bharath.M
	 * Created Date:24/Jan/2017
	 * Modified Date:
	 * Description :   Create a common method to retrieve Available Member in both Environment
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static String retrieveAvailableMemberEmailIdWithIDinBoth(WebDriver driver) throws ParseException, ClassNotFoundException, SQLException
	{
		String availableMemberEmailIDInKulfiTest="";
		String availableMemberEmailIDWithID="";
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
		ResultSet rs = stat.executeQuery("select A.EMAIL As Email from ACCOUNT A, SUMMARY_ACCOUNT_TODATE SATD where A.ID= SATD.ACCOUNT_ID AND SATD.MAST_PROGRAM_ID= '02'AND SATD.ONBOARDING_STATUS= 'MEMBER ONBOARDED' AND A.Status='Active' AND A.EMAIL not LIKE '%api%' ORDER BY SATD.CREATED_DT");
		System.out.println("query executed");
		if(rs.next())
		{
			availableMemberEmailIDInKulfiTest  = rs.getString("Email");
			System.out.println("Available Member Email "+availableMemberEmailIDWithID+" is retrieved from the Table");
		}
		return availableMemberEmailIDInKulfiTest;
	}

	/**
	 * Name :VIGNESHRAJ.M
	 * Created Date:23/Jan/2017
	 * Modified Date:
	 * Description :   Create a common method to sort program wise
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static void sortProgramWise(WebDriver driver, String testData)
	{
		waitForElement(driver, OR.HFOPS_CLASSES_ALLSESSION_PROGRAM_LINK_NAME);
		for (int i=0; i<=5;i++)
		{
			waitForElement(driver, OR.HFOPS_CLASSES_ALLSESSION_PROGRAM_LINK_NAME);
			String programName = driver.findElement(By.xpath(OR.HFOPS_CLASSES_ALLSESSION_PROGRAM_LINK_NAME)).getText();

			if(testData!=programName)
			{
				WebElement programDropdown = driver.findElement(By.xpath(OR.HFOPS_CLASSES_ALLSESSION_PROGRAM_SORT_ARROW));
				click(programDropdown);
				wait(driver, "3");
				waitForElement(driver, OR.HFOPS_CLASSES_ALLSESSION_PROGRAM_LINK_NAME);
			}
		}

	}


	public  static String retrieveMemberAccountIdFromEmail(WebDriver driver, String testData) throws ParseException, ClassNotFoundException, SQLException
	{
		System.out.println("testData"+testData);
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
		ResultSet rs = stat.executeQuery("SELECT ID, NAME, EMAIL, ORGANIZATION_ID, STATUS FROM ACCOUNT WHERE EMAIL LIKE'"+testData+"'");
		System.out.println("query executed");
		String Accountid1="";
		while(rs.next())
		{
			Accountid1= rs.getString("ID");
			System.out.println("Member Account id "+Accountid1+" is retrieved from the Table");
		}
		return Accountid1;
	}

	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   14/Mar/17
	 * Modified Date: 
	 * Description :   Create a common method for Login into Member for LiveSession
	 * Ticket ID :
	 * Required Inputs :  Username and Password
	 */
	public static void orberaMemberLoginForDifferentBrowser(WebDriver driver, String memberEmail1) {
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
		actionType(driver,member_username, memberEmail1);
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
	 * Name :     VIGNESHRAJ
	 * Created Date:   14/03/2017
	 * Modified Date:   
	 * Description :   Create a common method to Open Robot Default
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws FindFailed 
	 * @throws AWTException 
	 *
	 */
	public static void openRobotDefault(WebDriver driver1) throws FindFailed, AWTException 
	{
		Robot rb = new Robot();
		wait( driver1, "4" );
		rb.keyPress(KeyEvent.VK_A);
		rb.keyRelease(KeyEvent.VK_A);
	}

	/**
	 * Name : VigneshRaj.M    
	 * Created Date: 30/May/2016  
	 * Modified Date:     
	 * Description : Create a common method to Member Append URL to force attend the session
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public  static void orberaMemberOneOnOneSessionAppendURL(WebDriver driver)
	{
		String getCurrentURL= driver.getCurrentUrl();
		String appendCurrentURL= getCurrentURL+"?attendnow";
		System.out.println("Append URL to force attend the 1on1 session: "+appendCurrentURL);
		Navigate.get( driver, appendCurrentURL );
		wait( driver, "5" );
	}

	/**
	 * Name :     VIGNESHRAJ
	 * Created Date:   24/02/2017
	 * Modified Date:   
	 * Description :   Create a common method to Allow the Plugin and Remember
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws FindFailed 
	 * @throws AWTException 
	 *
	 */
	// Method will Allow the plugin if there is Allow and Remember Else it will skip the execution
	public static void allowPluginDifferentBrowser(WebDriver driver) throws FindFailed, AWTException 
	{
		String OSName=Platform.OS.toUpperCase();
		if(OSName.contains("WINDOWS"))
		{
			Screen Allow=new Screen(); 
			wait( driver, "8" );
			Pattern image = new Pattern(Directory.uploadFilePath+"GroupSessionZLiveAllowForChrome.PNG");
			Allow.wait(image, 15);
			Allow.click(image);
			wait( driver, "3" );
		}
		else if(OSName.contains("MAC"))
		{
			Screen Allow=new Screen(); 
			wait( driver, "8" );
			Pattern image = new Pattern(Directory.uploadFilePath+"GroupSessionZLiveAllowForChrome.PNG");
			Allow.wait(image, 15);
			Allow.click(image);
			wait( driver, "3" );
		}
	}

	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   14/12/2015
	 * Modified Date:  6/1/2016
	 * Description :   Create a common method for Login Coaches URL for User-1
	 * Ticket ID :    ZA-41
	 * Required Inputs :  No Inputs Required
	 * Purpose :   Logout from the CMS platform
	 */
	public  static void orberaCoacheLoginDifferentBrowser(WebDriver driver1, String coachEmail1)
	{
		Navigate.get(driver1, Directory.Coachesurl);
		Navigate.maximize(driver1);
		wait(driver1, "7");
		WebElement Coacheslogin_logo= driver1.findElement(By.xpath(OR.COACHES_LOGIN_PAGE_LOGO_REF));
		verifyElementIsPresent(driver1, Coacheslogin_logo);
		WebElement coach_username= driver1.findElement(By.xpath(OR.COACHES_USERNAME_1));
		verifyElementIsPresent(driver1, coach_username);
		WebElement coach_password= driver1.findElement(By.xpath(OR.COACHES_PASSWORD));
		verifyElementIsPresent(driver1, coach_password);
		WebElement Coacheslogin_button= driver1.findElement(By.xpath(OR.COACHES_LOGIN_BUTTON));
		verifyElementIsPresent(driver1, Coacheslogin_button);
		click(Coacheslogin_button);
		WebElement coachUsernameRequired= driver1.findElement(By.xpath(OR.COACHES_LOGIN_USERNAME_REQUIRED));
		WebElement coachPasswordRequired= driver1.findElement(By.xpath(OR.COACHES_LOGIN_PASSWORD_REQUIRED));
		verifyElementIsPresent(driver1, coachUsernameRequired);
		verifyElementIsPresent(driver1, coachPasswordRequired);
		sendKeys(coach_username,coachEmail1);
		sendKeys(coach_password, Directory.Coachespassword1);
		click(Coacheslogin_button);
		wait(driver1, "5");
		try {
			//Navigate.waitTime(driver, "20");
			WebElement opslogopresent= driver1.findElement(By.xpath(OR.RA_PROGRAM_ADMIN_REAL_APPEAL_LOGO));
			waitForElement(driver1, OR.RA_PROGRAM_ADMIN_REAL_APPEAL_LOGO);
			verifyElementIsPresent(driver1, opslogopresent);
		}
		catch(Exception e) {

			WebElement userSessionPopup = driver1.findElement(By.xpath(PROVIDER_USER_SESSION_POPUP));
			click(userSessionPopup);
			WebElement opslogopresent= driver1.findElement(By.xpath(OR.RA_PROGRAM_ADMIN_REAL_APPEAL_LOGO));
			waitForElement(driver1, OR.RA_PROGRAM_ADMIN_REAL_APPEAL_LOGO);
			verifyElementIsPresent(driver1, opslogopresent);
		}
		wait(driver1, "5");

		System.out.println("Coaches Logged in successfully");
	}

	/**
	 * Name : VigneshRaj.M    
	 * Created Date: 30/May/2016  
	 * Modified Date:     
	 * Description : Create a common method to Member Append URL to force attend the session
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public  static void orberaCoachSessionAppendURL(WebDriver driver1)
	{
		String getCurrentURL= driver1.getCurrentUrl();
		String appendCurrentURL= getCurrentURL+"?attendnow";
		System.out.println("Append URL to force attend the 1on1 session: "+appendCurrentURL);
		Navigate.get( driver1, appendCurrentURL );
		wait( driver1, "5" );
	}

	/**
	 * Name :     VINOTHKUMAR
	 * Created Date:   22/03/2016
	 * Modified Date:
	 * Description :   Create a common method to Allow the Plugin and Remember
	 * Ticket ID :
	 * Required Inputs :
	 * @throws FindFailed
	 * @throws AWTException
	 */
	// Method will Allow the plugin if there is Allow and Remember Else it will skip the execution
	public static void allowPluginsForDifferentBrowser(WebDriver driver1) throws FindFailed, AWTException
	{
		{
			String OSName=Platform.OS.toUpperCase();
			if(OSName.contains("MAC"))
			{
				Screen Allow=new Screen();
				wait( driver1, "8" );
				Pattern image = new Pattern(Directory.uploadFilePath+"AllowFireFoxMac.PNG");
				Allow.wait(image, 15);
				Allow.click(image);
				wait( driver1, "3" );
				Robot rb = new Robot();
				wait( driver1, "8" );
				rb.keyPress(KeyEvent.VK_CONTROL);
				rb.keyPress(KeyEvent.VK_A);
				rb.keyRelease(KeyEvent.VK_CONTROL);
				rb.keyRelease(KeyEvent.VK_A);
				rb.keyPress(KeyEvent.VK_CONTROL);
				rb.keyPress(KeyEvent.VK_R);
				rb.keyRelease(KeyEvent.VK_CONTROL);
				rb.keyRelease(KeyEvent.VK_R);
			}
			else if (OSName.contains("WINDOWS"))
			{
				Robot rb = new Robot();
				rb.keyPress(KeyEvent.VK_ALT);
				rb.keyPress(KeyEvent.VK_A);
				rb.keyRelease(KeyEvent.VK_ALT);
				rb.keyRelease(KeyEvent.VK_A);
				wait( driver1, "4" );
				rb.keyPress(KeyEvent.VK_ALT);
				rb.keyPress(KeyEvent.VK_R);
				rb.keyRelease(KeyEvent.VK_ALT);
				rb.keyRelease(KeyEvent.VK_R);
			}
		}
	}

	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   14/12/2015
	 * Modified Date:
	 * Description :Coaches Logout
	 * Purpose :   Logout from the CMS platform
	 */
	public  static void coachesLogoutDifferentBrowser(WebDriver driver1)
	{
		wait(driver1, "5");
		Navigate.waitTime(driver1, "20");
		waitForElement(driver1, OR.COACHES_SIGNOUT_LINK_BUTTON);
		WebElement Signout_link_settings = driver1.findElement(By.xpath(OR.COACHES_SIGNOUT_LINK_BUTTON));
		actionClick(driver1, Signout_link_settings);
		waitForElement(driver1, COACHES_SIGNOUT_BUTTON);
		jsClickByXPath(driver1, COACHES_SIGNOUT_BUTTON);
		wait(driver1, "5");
		Navigate.waitTime(driver1, "20");
		waitForElement(driver1, COACHES_SIGNOUT_YOU_HAVE_SIGNED_OUT_TEXT_REF);
	}

	/**
	 * Name : VIGNESHRAJ.M 
	 * Created Date: 30/Jan/2017  
	 * Modified Date:     
	 * Description :   Common method to Change AddLive To ZLive
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String orberaChangeAddLiveToZLive(WebDriver driver, String LiveType,String testData)
	{
		if (LiveType.equalsIgnoreCase("ADDLIVE"))
		{
			if (testData.equalsIgnoreCase("Group"))
			{
				WebElement addLiveVideoButton= driver.findElement(By.xpath("//tbody[@id='sessionClasses_tableBody']/tr/td[5][contains(text(),'"+testData+"')]/following-sibling::td[4][contains(text(),'Scheduled')]/following-sibling::td[5]/div[contains(text(),'ADDLIVE')]/parent::td/following-sibling::td/span[3]/following-sibling::a[3]/following-sibling::span[2]/a/span"));
				click(addLiveVideoButton);
				wait(driver, "3" );
				waitForElement(driver, OR.ORBERA_SESSION_CHANGE_LIVE_DROPDOWN);
				WebElement changeLiveDropdown= driver.findElement(By.xpath(OR.ORBERA_SESSION_CHANGE_LIVE_DROPDOWN));
				selectByVisibletext(changeLiveDropdown, "ZLIVE");
				wait(driver, "5" );
				WebElement saveButton= driver.findElement(By.xpath(OR.ORBERA_SESSION_CHANGE_SAVE_BUTTON));
				click(saveButton);
				wait(driver, "3" );
			}
			else if (testData.equalsIgnoreCase("1on1"))
			{
				WebElement addLiveVideoButton= driver.findElement(By.xpath("//tbody[@id='sessionClasses_tableBody']//tr/td[3]/a/span[contains(text(),'Orbera Program: Post-placement for 52 weeks')]/parent::a/parent::td/following-sibling::td[6][contains(text(),'Scheduled')]/following-sibling::td[5]/div[contains(text(),'ADDLIVE')]/parent::td/following-sibling::td/span[3]/following-sibling::a[3]/following-sibling::span[4]/a/span"));
				click(addLiveVideoButton);
				wait(driver, "3" );
				waitForElement(driver, OR.ORBERA_SESSION_CHANGE_LIVE_DROPDOWN);
				WebElement changeLiveDropdown= driver.findElement(By.xpath(OR.ORBERA_SESSION_CHANGE_LIVE_DROPDOWN));
				selectByVisibletext(changeLiveDropdown, "ZLIVE");
				wait(driver, "2" );
				WebElement saveButton= driver.findElement(By.xpath(OR.ORBERA_SESSION_CHANGE_SAVE_BUTTON));
				click(saveButton);
				wait(driver, "2" );
			}
		}
		else if (LiveType.equalsIgnoreCase("ZLIVE"))
		{

		}
		return LiveType;
	}

	/**
	 * Name : VIGNESHRAJ.M 
	 * Created Date: 16/Mar/2017  
	 * Modified Date:     
	 * Description :   Common method to Change AddLive To ZLive
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String changeAddLiveOrZLiveLectureLiveSession(WebDriver driver,String testData)
	{
		if (testData.equalsIgnoreCase("ADDLIVE"))
		{
			WebElement addLiveVideoButton= driver.findElement(By.xpath(OR.ORBERA_SESSION_CAMERA_BUTTON));
			click(addLiveVideoButton);
			wait(driver, "3" );
			waitForElement(driver, OR.ORBERA_SESSION_CHANGE_LIVE_DROPDOWN);
			WebElement changeLiveDropdown= driver.findElement(By.xpath(OR.ORBERA_SESSION_CHANGE_LIVE_DROPDOWN));
			selectByVisibletext(changeLiveDropdown, "ADDLIVE");
			wait(driver, "5" );
			WebElement saveButton= driver.findElement(By.xpath(OR.ORBERA_SESSION_CHANGE_SAVE_BUTTON));
			click(saveButton);
			wait(driver, "3" );
		}

		else if (testData.equalsIgnoreCase("ZLIVE"))
		{	
			WebElement addLiveVideoButton= driver.findElement(By.xpath(OR.ORBERA_SESSION_CAMERA_BUTTON));
			click(addLiveVideoButton);
			wait(driver, "3" );
			waitForElement(driver, OR.ORBERA_SESSION_CHANGE_LIVE_DROPDOWN);
			WebElement changeLiveDropdown= driver.findElement(By.xpath(OR.ORBERA_SESSION_CHANGE_LIVE_DROPDOWN));
			selectByVisibletext(changeLiveDropdown, "ZLIVE");
			wait(driver, "5" );
			WebElement saveButton= driver.findElement(By.xpath(OR.ORBERA_SESSION_CHANGE_SAVE_BUTTON));
			click(saveButton);
			wait(driver, "3" );
		}
		return testData;
	}

	/**
	 * Name : VIGNESHRAJ.M 
	 * Created Date: 30/Jan/2017  
	 * Modified Date:     
	 * Description :   Common method to Change AddLive To ZLive
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws AWTException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static void getWindowToScreenShareAndInstallDifferentBrowser(WebDriver driver1) throws FindFailed, AWTException
	{
		String your_title = "Zillion Live Screen Sharing :: Add-ons for Forefox";
		//String currentWindow = driver1.getWindowHandle(); 
		WebElement screenShareButton= driver1.findElement(By.xpath(OR.COACH_LECTURE_SESSION_SHARE_SCREEN_BUTTON));
		click(screenShareButton);
		wait(driver1, "15");
		ArrayList<String> tabs = new ArrayList<String> (driver1.getWindowHandles());
		driver1.switchTo().window(tabs.get(1));
		System.out.println("Title "+your_title);
		WebElement AddPlugin= driver1.findElement(By.xpath(OR.LECTURE_SESSION_SCREEN_SHARING_ADD_BROWSER_PLUGIN));
		click(AddPlugin);
		wait(driver1, "3");
		Robot rb = new Robot();
		wait( driver1, "4" );
		rb.keyPress(KeyEvent.VK_CONTROL); 
		rb.keyPress(KeyEvent.VK_I);
		rb.keyRelease(KeyEvent.VK_CONTROL); 
		rb.keyRelease(KeyEvent.VK_I);
		wait(driver1,"3");
		/*String Main_Window1 = driver1.getWindowHandle();
		WebElement screenShareButton= driver1.findElement(By.xpath(OR.COACH_LECTURE_SESSION_SHARE_SCREEN_BUTTON));
		click(screenShareButton);
		ArrayList<String> allWindows = new ArrayList<String>(driver1.getWindowHandles());
		allWindows.remove(Main_Window1);
		driver1.switchTo().window(allWindows.get(0));
		wait(driver1, "15");

		WebElement AddPlugin= driver1.findElement(By.xpath(OR.LECTURE_SESSION_SCREEN_SHARING_ADD_BROWSER_PLUGIN));
		click(AddPlugin);
		wait(driver1, "3");
		Robot rb = new Robot();
		wait( driver1, "4" );
		rb.keyPress(KeyEvent.VK_CONTROL); 
		rb.keyPress(KeyEvent.VK_I);
		rb.keyRelease(KeyEvent.VK_CONTROL); 
		rb.keyRelease(KeyEvent.VK_I);
		wait(driver1,"3");
		System.out.println("tutorial page closed");
		driver1.switchTo().window(allWindows.get(0)).close();
		driver1.switchTo().window(Main_Window1);*/
	}
	
	/**
	 * Name : VIGNESHRAJ.M
	 * Created Date: 27/Mar/2017  
	 * Modified Date:     
	 * Description :   Common method to Get Member session time
	 * Ticket ID :     
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	
	public  static String memberOneOnOneSessionTime(WebDriver driver1) throws ParseException
	{
		String sessiontime1=null;
		WebElement memberSessionTime = driver1.findElement(By.xpath(OR.ORBERA_MEMBER_SESSION_TIME));
		String sessionsTime=memberSessionTime.getText();
		SimpleDateFormat ft = new SimpleDateFormat("EEE hhh:mm a");
		java.util.Date t=ft.parse(sessionsTime);
		ft.applyPattern("hh:mma");
		sessiontime1=ft.format(t).toLowerCase().trim();
		System.out.print(ft.format(t));
		System.out.print("Time"+sessiontime1);
		return sessiontime1;
	}
	
	/**
	 * Name : VIGNESHRAJ.M 
	 * Created Date: 27/Mar/2017  
	 * Modified Date:     
	 * Description :   Common method to attend the OneOnOne Live session 
	 * Ticket ID :     
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public  static void attendMemberOneOnOneSessionTime(WebDriver driver, String time22) throws ParseException
	{
		WebElement coachUpcomingSessionTime = driver.findElement(By.xpath("//div[@id='mentorUpcomingSessions']//tbody//tr/td[2][contains(text(),'"+time22+"')]/following-sibling::td[3][contains(text(),'1on1')]/following-sibling::td[4]/div/button[contains(text(),'Attend Now')]"));
		verifyElementIsPresent(driver, coachUpcomingSessionTime); 
		click(coachUpcomingSessionTime);
		wait( driver, "4" );
	}
	
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   14/12/2015
	 * Modified Date:  6/1/2016
	 * Description :   Create a common method for Login Coaches URL for User-1
	 * Ticket ID :    ZA-41
	 * Required Inputs :  No Inputs Required
	 * Purpose :   Logout from the CMS platform
	 */
	public  static void orberaPALoginDifferentBrowser(WebDriver driver1, String coachEmail1)
	{
		Navigate.get(driver1, Directory.Providerurl);
		Navigate.maximize(driver1);
		wait(driver1, "7");
		WebElement Coacheslogin_logo= driver1.findElement(By.xpath(OR.COACHES_LOGIN_PAGE_LOGO_REF));
		verifyElementIsPresent(driver1, Coacheslogin_logo);
		WebElement coach_username= driver1.findElement(By.xpath(OR.COACHES_USERNAME_1));
		verifyElementIsPresent(driver1, coach_username);
		WebElement coach_password= driver1.findElement(By.xpath(OR.COACHES_PASSWORD));
		verifyElementIsPresent(driver1, coach_password);
		WebElement Coacheslogin_button= driver1.findElement(By.xpath(OR.COACHES_LOGIN_BUTTON));
		verifyElementIsPresent(driver1, Coacheslogin_button);
		click(Coacheslogin_button);
		WebElement coachUsernameRequired= driver1.findElement(By.xpath(OR.COACHES_LOGIN_USERNAME_REQUIRED));
		WebElement coachPasswordRequired= driver1.findElement(By.xpath(OR.COACHES_LOGIN_PASSWORD_REQUIRED));
		verifyElementIsPresent(driver1, coachUsernameRequired);
		verifyElementIsPresent(driver1, coachPasswordRequired);
		sendKeys(coach_username,coachEmail1);
		sendKeys(coach_password, Directory.Coachespassword1);
		click(Coacheslogin_button);
		wait(driver1, "5");
		try {
			//Navigate.waitTime(driver, "20");
			WebElement opslogopresent= driver1.findElement(By.xpath(OR.RA_PROGRAM_ADMIN_REAL_APPEAL_LOGO));
			waitForElement(driver1, OR.RA_PROGRAM_ADMIN_REAL_APPEAL_LOGO);
			verifyElementIsPresent(driver1, opslogopresent);
		}
		catch(Exception e) {

			WebElement userSessionPopup = driver1.findElement(By.xpath(PROVIDER_USER_SESSION_POPUP));
			click(userSessionPopup);
			WebElement opslogopresent= driver1.findElement(By.xpath(OR.RA_PROGRAM_ADMIN_REAL_APPEAL_LOGO));
			waitForElement(driver1, OR.RA_PROGRAM_ADMIN_REAL_APPEAL_LOGO);
			verifyElementIsPresent(driver1, opslogopresent);
		}
		wait(driver1, "5");

		System.out.println("Coaches Logged in successfully");
	}
}