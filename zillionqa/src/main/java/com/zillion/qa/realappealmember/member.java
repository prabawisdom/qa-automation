package com.zillion.qa.realappealmember;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import bsh.ParseException;

import com.zillion.qa.commands.ElementActions;
import com.zillion.qa.commands.Manipulation;
import com.zillion.qa.commands.Navigate;
import com.zillion.qa.utils.Directory;

public class member extends Manipulation implements OR {

	/**
	 * Name :      Abinaya.P
	 * Created Date:   22/Apr/16
	 * Modified Date:
	 * Description :   Create a common method to RealAppeal Member URL
	 * Ticket ID :
	 * Required Inputs :  Username and Password
	 *
	 */

	public static String realAppealLaunchMemberURL(WebDriver driver) 
	{
		try 
		{
			driver.navigate().to(Directory.RA_Member_Url);
			Manipulation.wait(driver, "4");
		} 
		catch (Exception e1) 
		{
			com.zillion.qa.commands.Manipulation.browserURLSecurityException(driver);
		}
		String memberUrl= Directory.RA_Member_Url;
		System.out.println("retrieved url from file: "+memberUrl);
		return memberUrl;

	}

	/**
	 * Name :      Abinaya.P
	 * Created Date:   22/Apr/16
	 * Modified Date:
	 * Description :   Create a common method to pick date and time from timeslot
	 * Ticket ID :
	 * Required Inputs :  Username and Password
	 *
	 */
	public static void scheduleTimeslotRAMember(WebDriver driver, String time1)
	{
		WebElement date = driver.findElement( By.xpath(OR.RA_MEMBER_DATE ) );
		Manipulation.click(date);
		WebElement time = driver.findElement( By.xpath("//a[text()='"+time1+"']" ) );
		Manipulation.click(time);
	}

	/**
	 * Name :      Abinaya.P
	 * Created Date:   22/Apr/16
	 * Modified Date:
	 * Description :   Create a common method to verify Member pagination enabled or disabled
	 * Ticket ID :
	 * Required Inputs :  Username and Password
	 *
	 */
	public static void realAppealMemberVerifyPaginationEnableOrDisabled(WebDriver driver)
	{
		String totalPageCount = driver.findElement(By.xpath(OR.RA_MEMBER_INBOX_MESSAGE_COUNT)).getText();
		System.out.println("Total Message count in the Inbox is: "+totalPageCount);
		int totalPageCount1 = Integer.parseInt(totalPageCount);
		if (totalPageCount1<=10)
		{
			WebElement nextPageNavigatorDisabled = driver.findElement( By.xpath(OR.RA_MEMBER_INBOX_NEXT_PAGENAVIGATOR_DISABLED_BUTTON ) );
			verifyElementIsPresent(driver, nextPageNavigatorDisabled);
			System.out.println("Total page count is less than 10");
		}
		else if (totalPageCount1>10)
		{
			WebElement nextPageNavigatorEnabled = driver.findElement( By.xpath(OR.RA_MEMBER_INBOX_NEXT_PAGENAVIGATOR_ENABLED_BUTTON ) );
			verifyElementIsPresent(driver, nextPageNavigatorEnabled);
			System.out.println("Total page count is greater than 10");
		}
	}

	/**
	 * Name :   Vinothkumar.M
	 * Created Date: 4/May/2016
	 * Modified Date:
	 * Description :Real Appeal Member url
	 * Ticket ID :
	 * Required Inputs :
	 *
	 */

	public static void launchRAMemberUrl(WebDriver driver)
	{
		try{
			driver.navigate().to(Directory.RA_Member_Url);
			wait(driver, "2");
		}  catch (Exception e1) {

			com.zillion.qa.commands.Manipulation.browserURLSecurityException(driver);

		}
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   04/MAY/16
	 * Modified Date:
	 * Description :   Create a common method to append 'attend now' to url in RA member portal
	 * Ticket ID :
	 * Required Inputs :
	 *
	 */
	public static void appendTextToRAMemberURL(WebDriver driver)
	{
		Navigate.get(driver, Directory.RAMemberAppendURL);
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   05/MAY/16
	 * Modified Date:
	 * Description :   Create a common method to find member which is available to schedule a session from database
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String retreieveMemberToScheduleSessionFromDB(WebDriver driver, String sessiontype, String status) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select * from CALENDAR_EVENT C where  session_type_id = '"+sessiontype+"' and session_status = '"+status+"' ORDER BY C.START_DT_TIME DESC");
		System.out.println("query executed");
		String accountid="";
		if(rs.next())
		{
			accountid= rs.getString("ACCOUNT_ID");
			System.out.println(accountid+" Account id is retrieved from the Table ");
		}
		return accountid;
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   10/MAY/16
	 * Modified Date:
	 * Description : Create a common method to verify the mail of the member once the enrollment is completed
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void verifyMemberMailEnrollment(WebDriver driver, String mailid)
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
			WebElement generatedLink= driver.findElement(By.xpath(OR.RA_MEMBER_ENROLLMENT_MAIL));
			verifyElementIsPresent(driver, generatedLink);
			com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.RA_MEMBER_ENROLLMENT_MAIL);
			wait(driver, "5");

			if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
			jsClickByXPath(driver, OR.RA_MEMBER_ENROLLMENT_MAIL);
			wait(driver, "3");
			waitForElement(driver, OR.RA_MEMBER_ENROLLMENT_MAIL_CONTENT);
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
			com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.RA_MEMBER_ENROLLMENT_MAIL);
			wait(driver, "5");

			if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
			jsClickByXPath(driver, OR.RA_MEMBER_ENROLLMENT_MAIL);
			wait(driver, "3");
		}
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   11/MAY/16
	 * Modified Date:
	 * Description : Create a common method to login into real apeal member account
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void realAppealMemberEnterPassword(WebDriver driver)
	{
		WebElement member_password= driver.findElement(By.xpath(OR.RA_MEMBER_LANDINGPAGE_PASSWORD));
		try
		{
			sendKeys(member_password, "Password1");
			wait(driver, "3");
			jsClickByXPath(driver, OR.RA_MEMBER_LOGIN_BUTTON);
			wait(driver, "3");
			waitForElement(driver, OR.RA_MEMBER_LOGOUT_BUTTON_TOP);
			wait(driver, "10");
		}
		catch(Exception e)
		{
			WebElement member_password1= driver.findElement(By.xpath(OR.RA_MEMBER_LANDINGPAGE_PASSWORD));
			clear(member_password1);
			sendKeys(member_password1, "Zillion2016");
			wait(driver, "2");
			jsClickByXPath(driver, OR.RA_MEMBER_LOGIN_BUTTON);
			wait(driver, "3");
			waitForElement(driver, OR.RA_MEMBER_LOGOUT_BUTTON_TOP);
			wait(driver, "10");
		}
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   12/MAY/16
	 * Modified Date:
	 * Description : Create a common method to validate DB query whether roll up and allocation job runs
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void dbValidateRollUpJob(WebDriver driver) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select * from QRTZ_TRIGGERS");
		System.out.println("query executed");
		String accountid="";
		if(rs.next())
		{
			accountid= rs.getString("ACCOUNT_ID");
			System.out.println(accountid+" Account id is retrieved from the Table ");
		}
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   12/MAY/16
	 * Modified Date:
	 * Description : Create a common method to verify the session status and roll up status of the member
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void dbValidateSessionAndRollupStatus(WebDriver driver, String mailid) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select SESSION_STATUS, ROLLUP_STATUS, START_DT_TIME from CALENDAR_EVENT WHERE ACCOUNT_ID IN (SELECT ID FROM ACCOUNT WHERE EMAIL LIKE '"+mailid+"')");
		System.out.println("query executed");
		String session_status="";
		String rollup_status="";
		if(rs.next())
		{
			session_status= rs.getString("SESSION_STATUS");
			System.out.println(session_status+"  ");
			rollup_status= rs.getString("ROLLUP_STATUS");
			System.out.println(rollup_status+"  ");
		}
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   12/MAY/16
	 * Modified Date:
	 * Description : Create a common method to verify
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void dbValidateAccountAchievement(WebDriver driver, String mailid) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select * from ACCOUNT_INS_ACHIEVEMENT WHERE ACCOUNT_ID IN (SELECT ID FROM ACCOUNT WHERE EMAIL LIKE '"+mailid+"');");
		System.out.println("query executed");
		String session_status="";
		String rollup_status="";
		if(rs.next())
		{
			session_status= rs.getString("SESSION_STATUS");
			System.out.println(session_status+"  ");
			rollup_status= rs.getString("ROLLUP_STATUS");
			System.out.println(rollup_status+"  ");
		}
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   13/MAY/16
	 * Modified Date:
	 * Description : Create a common method to verify the onboarding status of the member
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String validateMemberOnboardingStatus(WebDriver driver, String mailid) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("Select onboarding_status from summary_account_todate where account_id=(select id from account where email='"+mailid+"')");
		System.out.println("query executed");
		String onboarding_status="";
		if(rs.next())
		{
			onboarding_status= rs.getString("onboarding_status");
			System.out.println(onboarding_status+" :onboarding_status ");
		}
		return onboarding_status;
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   13/MAY/16
	 * Modified Date:
	 * Description : Create a common method to logout from real appeal member
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void realAppealMemberlogout(WebDriver driver)
	{

		WebElement member_LogoutBtn= driver.findElement(By.xpath(OR.RA_MEMBER_LOGOUT_BUTTON));
		verifyElementIsPresent(driver, member_LogoutBtn);
		click(member_LogoutBtn);
		wait(driver, "4");
		waitForElement(driver, OR.RA_MEMBER_SIGNED_OUT_TEXT);
		WebElement member_SignedOutText= driver.findElement(By.xpath(OR.RA_MEMBER_SIGNED_OUT_TEXT));
		verifyElementIsPresent(driver, member_SignedOutText);
	}

	/**
	 * Name : P.Abinaya
	 * Created Date: 18/May/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve Coach program id for any session
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	// Retrieve Coach Hostname for 1on1 Session
	// Substituting the Program ID in the query will give the Coach Hostname  for 1on1 session
	public static String retrieveHostNameWithProgramID(WebDriver driver, String accountProgramID, String sessionType, String session_Type_Id) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("SELECT EVT.ID, EVT.HOST_ID as Host_id, EVT.HOST_NAME FROM ACCOUNT_PROGRAM_SESSION_DETAIL PSD, CALENDAR_EVENT EVT WHERE EVT.ID=PSD.CALENDAR_EVENT_ID AND ACCOUNT_PROGRAM_ID='"+accountProgramID+"'AND PSD.SESSION_TYPE='"+sessionType+"' and PSD.SESSION_TYPE_ID='"+session_Type_Id+"'");
		System.out.println("query executed");
		String coachWithHostName1on1session="";
		while(rs.next())
		{
			coachWithHostName1on1session= rs.getString("Host_id");
			System.out.println("1on1 Session Coach Host Id "+coachWithHostName1on1session+" is retrieved from the Table");
		}
		return coachWithHostName1on1session;
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   19/May/16
	 * Modified Date:
	 * Description :   Create a common method to retrieve account id of the member with the onboarding status and MP_Name
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String retrieveAcntIdWithOnboardingStatusAndMpName(WebDriver driver,String onboarding_status,String mp_name) throws ClassNotFoundException, SQLException
	{
		String memberid ="";
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
		ResultSet rs = stat.executeQuery("Select ACCOUNT_ID from SUMMARY_ACCOUNT_TODATE SMRY, ACCOUNT ACNT where SMRY.ACCOUNT_ID=ACNT.ID AND ONBOARDING_STATUS= '"+onboarding_status+"' and MP_Name='"+mp_name+"' AND ACNT.STATUS='Active' ORDER BY ACCOUNT_ID DESC");
		System.out.println("query executed");
		if(rs.next())
		{
			memberid = rs.getString("account_id");
		}
		System.out.println(memberid);
		return memberid;
	}

	/**
	 * Name : P.Abinaya
	 * Created Date: 19/May/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve nick name of the coach using host id
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String retrieveHostNickNameUsingHostId(WebDriver driver, String hostid) throws ClassNotFoundException, SQLException
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
		//ResultSet rs = stat.executeQuery("SELECT EVT.HOST_NICKNAME, EVT.HOST_ID, EVT.HOST_NAME FROM ACCOUNT_PROGRAM_SESSION_DETAIL PSD, CALENDAR_EVENT EVT WHERE EVT.ID=PSD.CALENDAR_EVENT_ID AND EVT.HOST_ID='"+hostid+"'");
		ResultSet rs = stat.executeQuery("SELECT NICKNAME FROM PROVIDER WHERE ID='"+hostid+"'");
		System.out.println("query executed");
		String hostnickname1="";
		if(rs.next())
		{
			hostnickname1= rs.getString("NICKNAME");
			System.out.println("1on1 Session Coach Host Nick Name "+hostnickname1+" is retrieved from the Table");
		}
		return hostnickname1;
	}

	/**
	 * Name : P.Abinaya
	 * Created Date: 19/May/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve classroom id for the member using member id
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String retrieveClassroomId(WebDriver driver, String id) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select classroom_id  from classroom_account_signup where account_id ='"+id+"'");
		System.out.println("query executed");
		String classroom_id="";
		if(rs.next())
		{
			classroom_id= rs.getString("classroom_id");
			System.out.println("Classroom id "+classroom_id+" is retrieved from the Table");
		}
		return classroom_id;
	}

	/**
	 * Name : P.Abinaya
	 * Created Date: 19/May/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve approved status of the member using classroom id
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String retrieveIsApproved(WebDriver driver, String classroomid) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select is_approved, status  from classroom where ID='"+classroomid+"'");
		System.out.println("query executed");
		String is_approved="";
		String status1="";
		if(rs.next())
		{
			is_approved= rs.getString("is_approved");
			System.out.println("Approved flag "+is_approved+" is retrieved from the Table");
			status1= rs.getString("status");
			System.out.println("Status "+status1+" is retrieved from the Table");
		}
		return is_approved+","+status1;
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   26/MAY/16
	 * Modified Date:
	 * Description : Create a common method to verify the mail of the member once the 101 session is scheduled
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void verifyMemberMail1on1Session(WebDriver driver, String mailid)
	{
		wait(driver, "3");
		String Parent_Window = driver.getWindowHandle();
		Navigate.newTab(driver);
		if(mailid.contains("yopmail.com"))
		{
			Navigate.get(driver, Directory.yopmailurl);
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
				WebElement generatedLink= driver.findElement(By.xpath(OR.RA_MEMBER_1on1_MAIL));
				verifyElementIsPresent(driver, generatedLink);
				com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.RA_MEMBER_1on1_MAIL);
				wait(driver, "5");
				if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
				{
					driver.get("javascript:document.getElementById('overridelink').click();");
					wait(driver, "5");
				}
				jsClickByXPath(driver, OR.RA_MEMBER_1on1_MAIL);
				wait(driver, "3");
				Navigate.switchToDefaultFrame(driver);
				WebElement iframe2= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME2));
				Navigate.switchToFrame(driver, iframe2);
				waitForElement(driver, OR.ORBERA_MEMBER_1on1_CONTENT_SCHEDULE_UPCOMING);
			}
			catch(Exception e)
			{
				WebElement generatedLink= driver.findElement(By.xpath(OR.RA_MEMBER_1on1_MAIL_SCHEDULED));
				verifyElementIsPresent(driver, generatedLink);
				com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.RA_MEMBER_1on1_MAIL_SCHEDULED);
				wait(driver, "5");
				if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
				{
					driver.get("javascript:document.getElementById('overridelink').click();");
					wait(driver, "5");
				}
				jsClickByXPath(driver, OR.RA_MEMBER_1on1_MAIL_SCHEDULED);
				wait(driver, "3");
				Navigate.switchToDefaultFrame(driver);
				WebElement iframe2= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME2));
				Navigate.switchToFrame(driver, iframe2);
				waitForElement(driver, OR.RA_MEMBER_1on1_CONTENT_SCHEDULED);
			}
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
			try
			{
				WebElement generatedLink= driver.findElement(By.xpath(OR.RA_MEMBER_1on1_MAIL));
				verifyElementIsPresent(driver, generatedLink);
				com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.RA_MEMBER_1on1_MAIL);
				wait(driver, "5");

				if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
				{
					driver.get("javascript:document.getElementById('overridelink').click();");
					wait(driver, "5");
				}
				jsClickByXPath(driver, OR.RA_MEMBER_1on1_MAIL);
				wait(driver, "3");
				waitForElement(driver, OR.RA_MEMBER_1on1_CONTENT);
			}
			catch(Exception e) {

				wait(driver, "15");
				WebElement generatedLink= driver.findElement(By.xpath(OR.ORBERA_COACH_1on1_MAIL_GR));
				String grSubject = ElementActions.getText(generatedLink);
				System.out.println("Subject line is" +grSubject);
				if(grSubject.contains("Your 1-on-1 Session Confirmation")) {
					com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_COACH_1on1_MAIL_GR);
					wait(driver, "15");
					if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
					{
						driver.get("javascript:document.getElementById('overridelink').click();");
						wait(driver, "5");
					}
					jsClickByXPath(driver, OR.ORBERA_COACH_1on1_MAIL_GR);
					wait(driver, "3");
					jsClickByXPath(driver, OR.RA_MEMBER_1on1_CONTENT_SCHEDULED);
					wait(driver, "3");
					waitForElement(driver, OR.RA_MEMBER_1on1_CONTENT_SCHEDULED);
				}
			}
		}
		Navigate.closeTab(driver);
		driver.switchTo().window(Parent_Window);
	}


	/**
	 * Name :     ABINAYA.P
	 * Created Date:   26/MAY/16
	 * Modified Date:
	 * Description : Create a common method to delete the mails in the yop mail
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void deleteYopMails(WebDriver driver)
	{
		wait(driver, "3");
		WebElement iframe= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME1));
		Navigate.switchToFrame(driver, iframe);
		waitForElement(driver, OR.YOP_EMAIL_DELETE);
		WebElement yopemaildelete= driver.findElement(By.xpath(OR.YOP_EMAIL_DELETE));
		clickAndHold(driver, yopemaildelete);
		jsClickByXPath(driver, OR.YOP_EMAIL_EMPTY_MAIL);
		wait(driver, "20");
		Navigate.switchToDefaultFrame(driver);
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   26/MAY/16
	 * Modified Date:
	 * Description : Create a common method to verify the mail of the member once the 101 session is cancelled
	 * Ticket ID :
	 * Required Inputs :
	 * @throws Exception 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void verifyMemberMail1on1SessionCancellation(WebDriver driver, String mailid) throws Exception
	{
		wait(driver, "3");
		String Parent_Window = driver.getWindowHandle();
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
			WebElement generatedLink= driver.findElement(By.xpath(OR.RA_MEMBER_1on1_MAIL_CANCELLATION));
			verifyElementIsPresent(driver, generatedLink);
			com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.RA_MEMBER_1on1_MAIL_CANCELLATION);
			wait(driver, "5");

			if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
			jsClickByXPath(driver, OR.RA_MEMBER_1on1_MAIL_CANCELLATION);
			wait(driver, "3");
			Navigate.switchToDefaultFrame(driver);
			WebElement iframe2= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME2));
			Navigate.switchToFrame(driver, iframe2);
			waitForElement(driver, OR.RA_MEMBER_1on1_CONTENT_CANCELLATION);
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
			com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.RA_MEMBER_1on1_MAIL_CANCELLATION);
			wait(driver, "5");

			if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
			jsClickByXPath(driver, OR.RA_MEMBER_1on1_MAIL_CANCELLATION);
			wait(driver, "3");
			waitForElement(driver, OR.RA_MEMBER_1on1_CONTENT_CANCELLATION);
		}
		Navigate.closeTab(driver);
		driver.switchTo().window(Parent_Window);
	}


	/**
	 * Name :     ABINAYA.P
	 * Created Date:   28/MAY/16
	 * Modified Date:
	 * Description : Create a common method to verify confirm message for schedule/reschedule 1 on 1 session
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void verifyConfirmMessage1on1Session(WebDriver driver)
	{
		try
		{
			waitForElement(driver, RA_10N1SESSION_RECONFIRM_SUCCESS_MSG);
			jsClickByXPath(driver, RA_10N1SESSION_RECONFIRM_SUCCESS_MSG);
		}
		catch(Exception e)
		{
			waitForElement(driver, RA_10N1SESSION_CONFIRM_SUCCESS_MSG);
			jsClickByXPath(driver, RA_10N1SESSION_CONFIRM_SUCCESS_MSG);
		}
	}

	/**
	 * Name : P.Abinaya
	 * Created Date: 06/Jun/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve member/coach credentials for customization session
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String retrieveMembersForCustomizationSession(WebDriver driver, String session_id, String session_status ) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("SELECT ACNT.EMAIL Member_Email, PROV.EMAIL Coach_Email FROM SUMMARY_ACCOUNT_TODATE SMRY, PROVIDER PROV,ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID AND SMRY.ACCOUNT_ID=ACNT.ID AND ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ID IN(SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_STATUS='"+session_status+"' AND SESSION_TYPE_ID='"+session_id+"')) AND ACNT.EMAIL not like '%api%' order by ACNT.CREATED_DT Desc");
		System.out.println("query executed");
		String member_mail="";
		String coach_mail="";

		if(rs.next())
		{
			member_mail = rs.getString("Member_Email");
			coach_mail= rs.getString("Coach_Email");
			System.out.println("Customization session-member "+member_mail+" is retrieved from the Table");
			System.out.println("Customization session-coach "+coach_mail+" is retrieved from the Table");
		}
		return member_mail+","+coach_mail;
	}

	/**
	 * Name : P.Abinaya
	 * Created Date: 06/Jun/2016
	 * Modified Date:
	 * Description :   Create a common method to update next firetime in the QRTZ Table
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String updateQRTZTableForClaims(WebDriver driver, String nextfiretime) throws ClassNotFoundException, SQLException
	{
		Connection con =null;
		Statement stmt =null;
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url,user,pass);
		System.out.println("Oracle Connection is established successfully");
		String query="UPDATE qrtz_triggers set next_fire_time = '"+nextfiretime+"' where Job_name IN ('rollupJob')";
		String query1="UPDATE qrtz_triggers set next_fire_time = '"+nextfiretime+"' where Job_name IN ('achievementClaimJob')";
		stmt = con.createStatement();
		System.out.println("Statement is created");
		int rs=stmt.executeUpdate(query);
		int rs1=stmt.executeUpdate(query1);
		int no_of_rows=rs+rs1;
		System.out.println("Update query is executed");
		System.out.println(no_of_rows+" row updated");
		stmt.close();
		con.close();
		return rs+" row updated";
	}

	/**
	 * Name : P.Abinaya
	 * Created Date: 06/Jun/2016
	 * Modified Date:
	 * Description :   Create a common method to validate the Roll up status and Session status for the cliams
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String rollup_prcs_dt_sch_cust=null;
	public static String validateRollupAndSessionStatusForClaims(WebDriver driver) throws ClassNotFoundException, Exception
	{
		Connection conn =null;
		Statement stat =null;
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.Reports_Path+"test.properties");
		urlsProperties.load(input);
		String member=urlsProperties.getProperty("RAmember1").trim();
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);
		stat=conn.createStatement();
		System.out.println("Connection established");
		ResultSet rs = stat.executeQuery("select SESSION_STATUS,END_DT_TIME, ROLLUP_STATUS from CALENDAR_EVENT WHERE ACCOUNT_ID IN (SELECT ID FROM ACCOUNT WHERE EMAIL LIKE '"+member+"')");
		System.out.println("query executed34");
		String session_status="";
		String rollup_status="";
		String result="";
		if(rs.next())
		{
			session_status = rs.getString("SESSION_STATUS");
			rollup_status= rs.getString("ROLLUP_STATUS");
			rollup_prcs_dt_sch_cust= rs.getString("END_DT_TIME");
			System.out.println("Session status: "+session_status+" of the cliams is retrieved from the Table");
			System.out.println("Rollup status: "+rollup_status+" of the claims is retrieved from the Table");
		}
		result=session_status+","+rollup_status;
		System.out.println("result"+result);
		stat.close();
		conn.close();
		return result;
	}

	/**
	 * Name : P.Abinaya
	 * Created Date: 06/Jun/2016
	 * Modified Date:
	 * Description :   Create a common method to validate the cliams achievements
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String validateClaimsAchievements(WebDriver driver) throws ClassNotFoundException, SQLException
	{
		Connection conn =null;
		Statement stat =null;
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);
		stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select ins_rule_id from ACCOUNT_INS_ACHIEVEMENT WHERE ACCOUNT_ID ='"+claimsmemberid+"'");
		System.out.println("Achivements query executed");
		String ins_rule_id1="";
		if(rs.next())
		{
			ins_rule_id1 = rs.getString("ins_rule_id");
			System.out.println("Ins_rule_id1: "+ins_rule_id1+" of the claims is retrieved from the Table");
		}
		stat.close();
		conn.close();
		return ins_rule_id1;
	}

	/**
	 * Name:Suresh.V
	 * Created Date:06.06.2016
	 * @return
	 */
	public static String customizationDateAndTimeVerify(WebDriver driver, String Schedule)
	{
		String customizationScheduleDateTime= driver.findElement(By.xpath(OR.SCHEDULE_CUSTOMIZATION_TIME_AND_DATE)).getText();
		System.out.println(customizationScheduleDateTime);
		/*
		String[] splitcustomizationScheduleDateTime = customizationScheduleDateTime.split("is");
		String part1 = splitcustomizationScheduleDateTime[0];
		System.out.println(part1);//Your Customization Session
		String part2 = splitcustomizationScheduleDateTime[1];
		System.out.println(part2);//on Tuesday, June 07 1:00AM IST
		String[] part3 = part2.split("on");
		String part4 = part3[1];
		System.out.println(part4);//Tuesday, June 07 1:00AM IST
		String[] part5 = part4.split("EDT");
		String part6 = part5[0];
		System.out.println(part6);//Tuesday, June 07 1:00AM
		String[] part7 = part6.split(" ");
		System.out.println(part7+"part7");
		String part8 = part7[1];//Tuesday,
		String part9 = part7[2]; //June
		String part10 = part7[3]; //08
		String part11 = part7[4];//1:00AM
		System.out.println(part8);
		System.out.println(part9);
		System.out.println(part10);
		 */
		String[] msg1=customizationScheduleDateTime.split(" ");
		String seceduledDateTime=msg1[5]+" "+msg1[6]+" "+msg1[7]+" " + msg1[8];

		System.out.println(seceduledDateTime+"seceduledDateTime");
		return seceduledDateTime;
	}

	/**
	 * Name:Suresh.V
	 * Created Date:06.06.2016
	 */
	public static String customizationDateAndTimeVerifyAfterSchedule(WebDriver driver, String Scheduled)
	{
		String customizationAfterScheduleDateTime= driver.findElement(By.xpath(OR.TIME_AND_DATE_AFTER_SCHEDULE_CUSTOMIZATION_)).getText();
		System.out.println(customizationAfterScheduleDateTime);//Tuesday, June 07
		//01:00 am
		String[] customizationAfterScheduledDateTime = customizationAfterScheduleDateTime.split(" ");
		String part0 = customizationAfterScheduledDateTime[0]; //Tuesday,
		String part1 = customizationAfterScheduledDateTime[1]; //June
		String part2 = customizationAfterScheduledDateTime[2]; //07
		String part3 = customizationAfterScheduledDateTime[3];
		System.out.println("part0:"+part0);//Wednesday
		System.out.println("part1:"+part1);//June
		System.out.println("part2:"+part2);//08 01:00
		String[] part4=part2.split("\\n");
		System.out.println("part4[0]:"+part4[0]);
		System.out.println("part4[1]:"+part4[1]);
		String part5=part4[0]+" "+part4[1];
		String part6=part4[1].substring(0,1);

		if(part6.equals("0"))
		{
			part5=part4[0]+" "+part4[1].substring(1);
		}
		System.out.println("part6:"+part6);
		System.out.println("part5:"+part5);
		System.out.println("part3:"+part3);//am r pm
		String timeFormatLowerCase=new String(part3);
		String timeFormatUpperCase=timeFormatLowerCase.toUpperCase();//AM
		System.out.println(timeFormatUpperCase);
		String AfterScheduledDateTime=part0+" "+part1+" "+part5+timeFormatUpperCase;
		System.out.println(AfterScheduledDateTime);

		return AfterScheduledDateTime;
	}

	/**
	 * Name : P.Abinaya
	 * Created Date: 06/Jun/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve next firetime in the QRTZ Table
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String retrieveNextFireTimeQRTZTable(WebDriver driver) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("Select Next_Fire_Time from qrtz_triggers where Job_name = 'rollupJob'");
		System.out.println("query executed");
		String nextfiretime1="";
		if(rs.next())
		{
			nextfiretime1= rs.getString("Next_Fire_Time");
			System.out.println(nextfiretime1+" Next Fire Time is retrieved from the Table ");
		}
		return nextfiretime1;
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   13/MAY/16
	 * Modified Date:
	 * Description : Create a common method to logout from real appeal member using top logout button
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void realAppealMemberlogoutUsingTopLogoutButton(WebDriver driver)
	{
		WebElement member_LogoutBtn= driver.findElement(By.xpath(OR.RA_MEMBER_LOGOUT_BUTTON_TOP));
		verifyElementIsPresent(driver, member_LogoutBtn);
		click(member_LogoutBtn);
		wait(driver, "4");
		waitForElement(driver, OR.RA_MEMBER_SIGNED_OUT_TEXT);
		WebElement member_SignedOutText= driver.findElement(By.xpath(OR.RA_MEMBER_SIGNED_OUT_TEXT));
		verifyElementIsPresent(driver, member_SignedOutText);
	}

	/**
	 * Name : P.Abinaya
	 * Created Date: 19/May/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve session status using accountid
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String retrieveSessionStatusUsingAcntId(WebDriver driver, String acntid,String sessiontype ) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("SELECT SESSION_STATUS FROM CALENDAR_EVENT WHERE  ACCOUNT_ID='"+acntid+"' AND SESSION_TYPE='"+sessiontype+"'");
		System.out.println("query executed");
		String session_status="";
		if(rs.next())
		{
			session_status= rs.getString("SESSION_STATUS");
			System.out.println("SESSION_STATUS: "+session_status+" is retrieved from the Table");
		}
		return session_status;
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   25/JUN/16
	 * Modified Date:
	 * Description : Create a common method to enter insurance details in RA Enrollment
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void realAppealInsuranceDetails(WebDriver driver)
	{
		Manipulation.wait(driver, "5");
		waitForElement(driver, OR.RA_MEMBER_INSURANCEPROVIDER);
		WebElement insurance_provider= driver.findElement(By.xpath(OR.RA_MEMBER_INSURANCEPROVIDER));
		WebElement member_id= driver.findElement(By.xpath(OR.RA_MEMBER_MEMBERID));
		WebElement groupnumber= driver.findElement(By.xpath(OR.RA_MEMBER_GROUPNUMBER));
		clearAndType(insurance_provider, Directory.RA_Enrollment_Insurance_Provider);
		WebElement autosuggest= driver.findElement(By.xpath(OR.RA_INSURANCE_AUTO_SUGGEST));
		Manipulation.wait(driver, "2");
		waitForElement(driver, OR.RA_INSURANCE_AUTO_SUGGEST);
		actionClick(driver, autosuggest);
		clearAndType(member_id, Directory.RA_Enrollment_Member_Id);
		clearAndType(groupnumber, Directory.RA_Enrollment_Group_Member);
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   07/JUL/16
	 * Modified Date:
	 * Description : Create a common method to enter first name dynamic to multi environment
	 * @return
	 */
	public static Object enterRAFirstName(WebDriver driver)
	{
		WebElement  firstname = driver.findElement(By.xpath(OR.RA_MEMBER_FIRSTNAME));
		return clearAndType(firstname, Directory.RA_Enrollment_First_Name);
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   07/JUL/16
	 * Modified Date:
	 * Description : Create a common method to enter last name dynamic to multi environment
	 * @return
	 */
	public static Object enterRALastName(WebDriver driver)
	{
		WebElement  lastname = driver.findElement(By.xpath(OR.RA_MEMBER_LASTNAME));
		return clearAndType(lastname, Directory.RA_Enrollment_Last_Name);
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   07/JUL/16
	 * Modified Date:
	 * Description : Create a common method to enter day of DOB dynamic to multi environment
	 */
	public static void enterRADOBDay(WebDriver driver)
	{
		WebElement  day = driver.findElement(By.xpath(OR.RA_MEMBER_DOBDAY));
		selectByVisibletext(day, Directory.RA_Enrollment_Day);
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   07/JUL/16
	 * Modified Date:
	 * Description : Create a common method to enter month of DOB dynamic to multi environment
	 */
	public static void enterRADOBMonth(WebDriver driver)
	{
		WebElement  month = driver.findElement(By.xpath(OR.RA_MEMBER_DOBMONTH));
		selectByVisibletext(month, Directory.RA_Enrollment_Month);
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   07/JUL/16
	 * Modified Date:
	 * Description : Create a common method to enter year of DOB dynamic to multi environment
	 */
	public static void enterRADOBYear(WebDriver driver)
	{
		WebElement  year = driver.findElement(By.xpath(OR.RA_MEMBER_DOBYEAR));
		selectByVisibletext(year, Directory.RA_Enrollment_Year);
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   07/JUL/16
	 * Modified Date:
	 * Description : Create a common method to enter first name dynamic to multi environment
	 */
	public static String enterRAFirstNameOnInsurancePopUp(WebDriver driver)
	{
		wait(driver, "5");
		String fname1="";
		try{

			WebElement  firstname = driver.findElement(By.xpath(OR.RA_FIRSTNAME_AFTER_INSURANCE));
			sendKeys(firstname, Directory.RA_Enrollment_First_Name);
			WebElement  fname = driver.findElement(By.xpath(OR.RA_MEMBER_SIGNUP_INSURANCE_PROVIDER_PAGE_PHI_POPUP_FIRSTNAME_FIELD));
			fname1=fname.getText();

		}
		catch(Exception e){
			System.out.println("PHI popup not displayed");

		}

		return fname1;

	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   07/JUL/16
	 * Modified Date:
	 * Description : Create a common method to enter last name dynamic to multi environment
	 */
	public static String enterRALastNameOnInsurancePopUp(WebDriver driver)
	{
		String lname1="";
		try{
			WebElement  lname = driver.findElement(By.xpath(OR.RA_MEMBER_SIGNUP_INSURANCE_PROVIDER_PAGE_PHI_POPUP_LASTNAME_FIELD));
			lname1=lname.getText();
			WebElement  lastname = driver.findElement(By.xpath(OR.RA_LASTNAME_AFTER_INSURANCE));
			sendKeys(lastname, Directory.RA_Enrollment_Last_Name);
			WebElement  insuranceAccept = driver.findElement(By.xpath(OR.RA_MEMBER_SETTINGS_PHI_POPUP_ACCEPT_BUTTON));
			Manipulation.click(insuranceAccept);
			wait(driver, "10");

			//WebElement  nextStepButton = driver.findElement(By.xpath(OR.RA_MEMBER_STEP1_NEXTBUTTON_ENABLED));
			//Manipulation.click(nextStepButton);

		}
		catch(Exception e){
			System.out.println("PHI popup not displayed");

		}
		return lname1;

	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   07/JUL/16
	 * Modified Date:
	 * Description : Create a common method to store member credential after Enrollment
	 */
	static String RAmember;
	public static void storeMemberDuringEnrollment(WebDriver driver, String data)
	{
		RAmember=data;
		try {
			Properties properties = new Properties();
			properties.setProperty("RAmember1", RAmember);
			File file = new File(Directory.uploadFilePath+"test.properties");
			FileOutputStream fileOut = new FileOutputStream(file);
			properties.store(fileOut, "This is to store member for claims");
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(RAmember+":Member from Member enrollment");
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   06/JULY/16
	 * Modified Date:
	 * Description : Create a common method to split timestamp using colon(:)
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String splitUsingColon(WebDriver driver,String text)
	{
		String[] a=text.split(":");
		return a[1].trim();
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   06/JULY/16
	 * Modified Date:
	 * Description : Create a common method to enter RA member for customization session over claims workflow
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String enterRAMemberUsername(WebDriver driver)
	{
		WebElement  username = driver.findElement(By.xpath(OR.RA_MEMBER_LANDINGPAGE_USERNAME));
		sendKeys(username, RAmember);
		System.out.println("Member on customization session"+RAmember);
		return RAmember ;
	}

	/**
	 * Name : P.Abinaya
	 * Created Date: 14/Jul/2016
	 * Modified Date:
	 * Description : Retrieve member id with the member name for claims
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String claimsmemberid="";
	public static String getMemberIdForClaims(WebDriver driver) throws ClassNotFoundException, SQLException, IOException
	{

		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.Reports_Path+"test.properties");
		urlsProperties.load(input);
		String member=urlsProperties.getProperty("RAmember1").trim();
		Connection conn =null;
		Statement stat =null;
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);
		stat=conn.createStatement();
		System.out.print("RAmember: "+RAmember);
		ResultSet rs = stat.executeQuery("select id from account where email='"+member+"'");
		System.out.println("Member id query executed");
		String ins_rule_id1="";
		if(rs.next())
		{
			claimsmemberid = rs.getString("id");
			System.out.println("Member id: "+claimsmemberid+" must be retrieved");
		}
		stat.close();
		conn.close();

		return ins_rule_id1;
	}

	/**
	 * Suresh V
	 * @param driver
	 */
	public static void dashboardCaloriesComparision(WebDriver driver)
	{
		String  breakFastCalories = driver.findElement(By.xpath(OR.RA_BREAKFAST_CALORIES)).getText();
		String  lunchCalories = driver.findElement(By.xpath(OR.RA_LUNCH_CALORIES)).getText();
		String  dinnerCalories = driver.findElement(By.xpath(OR.RA_DINNER_CALORIES)).getText();
		String  snackCalories = driver.findElement(By.xpath(OR.RA_SNACK_CALORIES)).getText();

		String[] caloriesBreakfast = breakFastCalories.split("CAL");
		String one= caloriesBreakfast[0];
		String countBF=one.trim();
		System.out.println("BreakFast Calories="+countBF);
		int count1= Integer.parseInt(countBF);
		System.out.println("BreakFast Calories="+count1);

		String[] caloriesLunch = lunchCalories.split("CAL");
		String two = caloriesLunch[0];
		String countLunch=two.trim();
		int count2= Integer.parseInt(countLunch);
		System.out.println("Lunch Calories="+countLunch);
		System.out.println("Lunch Calories="+count2);

		String[] caloriesDinner = dinnerCalories.split("CAL");
		String three= caloriesDinner[0];
		String countDinner=three.trim();
		int count3= Integer.parseInt(countDinner);
		System.out.println("Dinner Calories="+countDinner);
		System.out.println("Dinner Calories="+count3);

		String[] caloriesSnack = snackCalories.split("CAL");
		String four= caloriesSnack[0];
		String countSnack=four.trim();
		System.out.println("Snack Calories="+countSnack);
		int count4= Integer.parseInt(countSnack);
		System.out.println("Snack Calories="+count4);

		int additionOf4Calories=count1+count2+count3+count4;
		System.out.println("Addition of 4 Calories="+additionOf4Calories);

		String  caloriesCount = driver.findElement(By.xpath(OR.RA_TOTAL_CALORIES)).getText();
		String[] caloriesTotalCount = caloriesCount.split("cal");
		String count = caloriesTotalCount[0];
		String totalCount=count.trim();

		System.out.println("Total Calories="+totalCount);
		int counttotal= Integer.parseInt(totalCount);
		System.out.println("Grand total of Calories="+counttotal);

		if(additionOf4Calories==(counttotal))
		{
			System.out.println("Calories counts are matched");
		}
		else
		{
			System.out.println("Calories counts are not matched");

		}}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   26/MAY/16
	 * Modified Date:
	 * Description : Create a common method to verify member Email ID from the Database
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public  static String dBRealAppealMember(WebDriver driver,String member_Status) throws ParseException, ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select A.EMAIL as EMAIL1, SATD.ASSIGNED_PROVIDER_NAME, SATD.ONBOARDING_STATUS, A.ID from ACCOUNT A, SUMMARY_ACCOUNT_TODATE SATD where A.ID= SATD.ACCOUNT_ID AND SATD.MAST_PROGRAM_ID= '02' AND SATD.ONBOARDING_STATUS= '"+member_Status+"' and SATD.MP_Name='RA - Real Appeal' AND A.EMAIL NOT LIKE '%api%' AND A.EMAIL NOT LIKE '%info%' AND SATD.PROGRAM_INTERVAL_NUMBER <'52' and SATD.PROGRAM_INTERVAL_NUMBER > '15' and A.STATUS IN  ('Active') and A.EMAIL NOT LIKE 'QAmember%default@yopmail.com' ORDER BY SATD.CREATED_DT DESC");
		System.out.println("query executed");
		String mailID="";
		while(rs.next())
		{
			mailID = rs.getString("EMAIL1");
			System.out.println("DBEnrollmentstatus="+mailID);
		}
		return mailID;
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   26/MAY/16
	 * Modified Date:
	 * Description : Create a common method to verify the mail of the member once the 101 session is scheduled
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void verifyMemberMail1on1Session2(WebDriver driver, String mailid)
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
			WebElement checkInbox= driver.findElement(By.xpath(OR.YOP_EMAIL_CHECK_INBOX));
			verifyElementIsPresent(driver, checkInbox);
			jsClickByXPath(driver, OR.YOP_EMAIL_CHECK_INBOX);
			wait(driver, "5");
			Navigate.switchToDefaultFrame(driver);
			WebElement iframe= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME1));
			Navigate.switchToFrame(driver, iframe);
			System.out.println("sdfdf");
			try
			{
				wait(driver, "10");
				System.out.println("sdfdf2");

				com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_MEMBER_1on1_MAIL_SCHEDULE);
				waitForElement(driver, OR.ORBERA_MEMBER_1on1_MAIL_SCHEDULE);
				System.out.println("sdfdf3");
				wait(driver, "5");
				if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
				{
					driver.get("javascript:document.getElementById('overridelink').click();");
					wait(driver, "5");
				}
				jsClickByXPath(driver, OR.ORBERA_MEMBER_1on1_MAIL_SCHEDULE);
				System.out.println("sdfdf4");
				wait(driver, "3");
				Navigate.switchToDefaultFrame(driver);
				System.out.println("sdfdf5");
				WebElement iframe2= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME2));
				Navigate.switchToFrame(driver, iframe2);
				System.out.println("sdfdf6");
				waitForElement(driver, OR.ORBERA_MEMBER_1on1_CONTENT_SCHEDULE);
				System.out.println("sdfdf7");
			}
			catch(Exception e)
			{
				Navigate.switchToDefaultFrame(driver);
				WebElement iframe2= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME2));
				Navigate.switchToFrame(driver, iframe2);
				waitForElement(driver, OR.ORBERA_MEMBER_1on1_CONTENT_SCHEDULE);
			}
			Navigate.switchToDefaultFrame(driver);
			waitForElement(driver, YOPMAIL_DELETE);
			jsClickByXPath(driver, YOPMAIL_DELETE);
			wait(driver, "3");


		}
		else if(mailid.contains("guerrillamail"))
		{
			Navigate.get(driver, Directory.Guerrillamailurl);
			for (String Second_Window : driver.getWindowHandles())
			{
				driver.switchTo().window(Second_Window);
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
				com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_MEMBER_1on1_MAIL_SCHEDULE);
				wait(driver, "5");

				if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
				{
					driver.get("javascript:document.getElementById('overridelink').click();");
					wait(driver, "5");
				}
				jsClickByXPath(driver, OR.ORBERA_MEMBER_1on1_MAIL_SCHEDULE);
				wait(driver, "3");
				waitForElement(driver, OR.ORBERA_MEMBER_1on1_CONTENT_SCHEDULE);
			}
		}

		Navigate.closeTab(driver);
		driver.switchTo().window(First_Window);
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   10/AUG/16
	 * Modified Date:
	 * Description : Create a common method to store member credential after Enrollment for the claims workflow (Member did not qualify)
	 */
	static String RAmemberMemberDidNotQualify;
	public static void storeMemberDuringEnrollmentMemberDidNotQualify(WebDriver driver, String data)
	{
		RAmemberMemberDidNotQualify=data;
		try {
			Properties properties = new Properties();
			properties.setProperty("RAmember1", RAmemberMemberDidNotQualify);
			File file = new File(Directory.uploadFilePath+"RAmemberMemberDidNotQualify.properties");
			FileOutputStream fileOut = new FileOutputStream(file);
			properties.store(fileOut, "This is to store member for claims (member did not qualify)");
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(RAmember+":Member from Member enrollment for claims (member did not qualify)");
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   1/AUG/16
	 * Modified Date:
	 * Description : Create a common method to enter RA member for customization session over claims workflow
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String enterRAMemberUsernameMemberDidNotQuaify(WebDriver driver)
	{
		//RAmemberMemberDidNotQualify="Wed20160810050759AMqa@guerrillamail.com";
		WebElement  username = driver.findElement(By.xpath(OR.RA_MEMBER_LANDINGPAGE_USERNAME));
		sendKeys(username, RAmemberMemberDidNotQualify);
		System.out.println("Member on customization session claims (Member did not qualify) "+RAmemberMemberDidNotQualify);
		return RAmemberMemberDidNotQualify ;
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   12/AUG/16
	 * Modified Date:
	 * Description : Create a common method to store member credential after Enrollment for the claims workflow (BMI Out of range)
	 */
	static String RAmemberBMIOutOfRange;
	public static void storeMemberDuringEnrollmentBMIOutOfRange(WebDriver driver, String data)
	{
		RAmemberBMIOutOfRange=data;
		try {
			Properties properties = new Properties();
			properties.setProperty("RAmember1", RAmemberBMIOutOfRange);
			File file = new File(Directory.uploadFilePath+"RAmemberBMIOutOfRange.properties");
			FileOutputStream fileOut = new FileOutputStream(file);
			properties.store(fileOut, "This is to store member for claims (BMI Out Of Range)");
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(RAmember+":Member from Member enrollment for claims (BMI Out Of Range)");
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   12/AUG/16
	 * Modified Date:
	 * Description : Create a common method to enter RA member for customization session over claims workflow for BMI out of range
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String enterRAMemberUsernameBMIOutOfRange(WebDriver driver)
	{
		//RAmemberMemberDidNotQualify="Wed20160810050759AMqa@guerrillamail.com";
		WebElement  username = driver.findElement(By.xpath(OR.RA_MEMBER_LANDINGPAGE_USERNAME));
		sendKeys(username, RAmemberBMIOutOfRange);
		System.out.println("Member on customization session claims (BMI Out of range) "+RAmemberBMIOutOfRange);
		return RAmemberBMIOutOfRange ;
	}

	/**
	 * Name : P.Abinaya
	 * Created Date: 12/Aug/2016
	 * Modified Date:
	 * Description : Retrieve member id with the member name for claims BMI Out of range
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String claimsmemberidBMIOutRange="";
	public static String getMemberIdForClaimsBMIOutOfRange(WebDriver driver) throws ClassNotFoundException, SQLException, IOException
	{
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.uploadFilePath+"RAmemberBMIOutOfRange.properties");
		urlsProperties.load(input);
		String member=urlsProperties.getProperty("RAmember1").trim();
		Connection conn =null;
		Statement stat =null;
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);
		stat=conn.createStatement();
		System.out.print("RAmember: "+member);
		ResultSet rs = stat.executeQuery("select id from account where email='"+member+"'");
		System.out.println("Member id query executed");
		//String ins_rule_id1="";
		if(rs.next())
		{
			claimsmemberidBMIOutRange = rs.getString("id");
			System.out.println("Member id: "+claimsmemberidBMIOutRange+" must be retrieved");
		}
		stat.close();
		conn.close();

		return claimsmemberidBMIOutRange;
	}

	/**
	 * Name : P.Abinaya
	 * Created Date: 12/Aug/2016
	 * Modified Date:
	 * Description :   Create a common method to validate the cliams achievements for BMI Out of range workflow
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String validateClaimsAchievementsBMIOutOfRange(WebDriver driver) throws ClassNotFoundException, SQLException
	{
		Connection conn =null;
		Statement stat =null;
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);
		stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select ins_rule_id from ACCOUNT_INS_ACHIEVEMENT WHERE ACCOUNT_ID ='"+claimsmemberidBMIOutRange+"'");
		System.out.println("Achivements query executed");
		String ins_rule_id1="";
		if(rs.next())
		{
			ins_rule_id1 = rs.getString("ins_rule_id");
			System.out.println("Ins_rule_id1: "+ins_rule_id1+" of the claims is retrieved from the Table");
		}
		else
		{
			ins_rule_id1 = "No Claims is achieved";
		}
		stat.close();
		conn.close();
		return ins_rule_id1;
	}

	/**
	 * Name : P.Abinaya
	 * Created Date: 12/Aug/2016
	 * Modified Date:
	 * Description :   Create a common method to validate the Roll up status and Session status for the cliams(BMI Out of range)
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String validateRollupAndSessionStatusForClaimsBMIOutOfRange(WebDriver driver) throws ClassNotFoundException, Exception
	{
		Connection conn =null;
		Statement stat =null;
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.uploadFilePath+"RAmemberBMIOutOfRange.properties");
		urlsProperties.load(input);
		String member=urlsProperties.getProperty("RAmember1").trim();
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);
		stat=conn.createStatement();
		System.out.println("Connection established");
		ResultSet rs = stat.executeQuery("select SESSION_STATUS, ROLLUP_STATUS from CALENDAR_EVENT WHERE ACCOUNT_ID IN (SELECT ID FROM ACCOUNT WHERE EMAIL LIKE '"+member+"')");
		System.out.println("query executed");
		String session_status="";
		String rollup_status="";
		String result="";
		if(rs.next())
		{
			session_status = rs.getString("SESSION_STATUS");
			rollup_status= rs.getString("ROLLUP_STATUS");
			System.out.println("Session status: "+session_status+" of the cliams is retrieved from the Table");
			System.out.println("Rollup status: "+rollup_status+" of the claims is retrieved from the Table");
		}
		if(!rollup_status.equals("ROLLUP CLOSED"))
		{
			wait(driver, "240");
		}
		result=session_status+","+rollup_status;
		System.out.println("result"+result);
		stat.close();
		conn.close();
		return result;
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   12/AUG/16
	 * Modified Date:
	 * Description : Create a common method to verify the mail of the Orbera member once the 101 session is cancelled
	 * Ticket ID :
	 * Required Inputs :
	 * @throws Throwable 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void verifyOrberaMemberMail1on1SessionCancellation(WebDriver driver, String mailid)
	{
		wait(driver, "3");
		String Parent_Window = driver.getWindowHandle();
		Navigate.newTab(driver);
		if(mailid.contains("yopmail.com"))
		{
			Navigate.get(driver, Directory.yopmailurl);
			for (String Second_Window : driver.getWindowHandles())
			{
				driver.switchTo().window(Second_Window);
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
				WebElement generatedLink= driver.findElement(By.xpath(OR.ORBERA_MEMBER_1on1_MAIL_CANCELLATION));
				verifyElementIsPresent(driver, generatedLink);
				com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_MEMBER_1on1_MAIL_CANCELLATION);
				wait(driver, "5");
				if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
				{
					driver.get("javascript:document.getElementById('overridelink').click();");
					wait(driver, "5");
				}
				jsClickByXPath(driver, OR.ORBERA_MEMBER_1on1_MAIL_CANCELLATION);
				wait(driver, "3");
				Navigate.switchToDefaultFrame(driver);
				WebElement iframe2= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME2));
				Navigate.switchToFrame(driver, iframe2);
				waitForElement(driver, OR.ORBERA_MEMBER_1on1_MAIL_CONTENT_CANCELLATION);
				Navigate.switchToDefaultFrame(driver);
				waitForElement(driver, YOPMAIL_DELETE);
				jsClickByXPath(driver, YOPMAIL_DELETE);
				wait(driver, "3");
			}
			Navigate.closeTab(driver);
			driver.switchTo().window(Parent_Window);
		}
		else if(mailid.contains("guerrillamail"))
		{
			Navigate.get(driver, Directory.Guerrillamailurl);
			for (String Second_Window : driver.getWindowHandles())
			{
				driver.switchTo().window(Second_Window);
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
				com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_MEMBER_1on1_MAIL_CANCELLATION);
				wait(driver, "5");

				if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
				{
					driver.get("javascript:document.getElementById('overridelink').click();");
					wait(driver, "5");
				}
				jsClickByXPath(driver, OR.ORBERA_MEMBER_1on1_MAIL_CANCELLATION);
				wait(driver, "3");
				waitForElement(driver, OR.RA_MEMBER_1on1_CONTENT_CANCELLATION);
			}
			Navigate.closeTab(driver);
			driver.switchTo().window(Parent_Window);
		}
	}
	/**
	 * Name : P.Abinaya
	 * Created Date: 16/Aug/2016
	 * Modified Date:
	 * Description : Retrieve member id with the member name for claims (Member did not qualify)
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String claimsmemberdidnotqualify="";
	public static String getMemberIdForClaimsDidNotQualify(WebDriver driver) throws ClassNotFoundException, SQLException, IOException
	{
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.uploadFilePath+"RAmemberMemberDidNotQualify.properties");
		urlsProperties.load(input);
		String member=urlsProperties.getProperty("RAmember1").trim();
		Connection conn =null;
		Statement stat =null;
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);
		stat=conn.createStatement();
		System.out.print("RAmember: "+member);
		ResultSet rs = stat.executeQuery("select id from account where email='"+member+"'");
		System.out.println("Member id query executed");
		if(rs.next())
		{
			claimsmemberdidnotqualify = rs.getString("id");
			System.out.println("Member id: "+claimsmemberdidnotqualify+" must be retrieved");
		}
		stat.close();
		conn.close();

		return claimsmemberdidnotqualify;
	}

	/**
	 * Name : P.Abinaya
	 * Created Date: 16/Aug/2016
	 * Modified Date:
	 * Description :   Create a common method to validate the Roll up status and Session status for the cliams(Member did not qualify)
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String validateRollupAndSessionStatusForClaimsDidNotQualify(WebDriver driver) throws ClassNotFoundException, Exception
	{
		Connection conn =null;
		Statement stat =null;
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.uploadFilePath+"RAmemberMemberDidNotQualify.properties");
		urlsProperties.load(input);
		String member=urlsProperties.getProperty("RAmember1").trim();
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);
		stat=conn.createStatement();
		System.out.println("Connection established");
		ResultSet rs = stat.executeQuery("select SESSION_STATUS, ROLLUP_STATUS from CALENDAR_EVENT WHERE ACCOUNT_ID IN (SELECT ID FROM ACCOUNT WHERE EMAIL LIKE '"+member+"')");
		System.out.println("query executed");
		String session_status="";
		String rollup_status="";
		String result="";
		if(rs.next())
		{
			session_status = rs.getString("SESSION_STATUS");
			rollup_status= rs.getString("ROLLUP_STATUS");
			System.out.println("Session status: "+session_status+" of the cliams is retrieved from the Table");
			System.out.println("Rollup status: "+rollup_status+" of the claims is retrieved from the Table");
		}
		if(!rollup_status.equals("ROLLUP CLOSED"))
		{
			wait(driver, "240");
		}
		result=session_status+","+rollup_status;
		System.out.println("result"+result);
		stat.close();
		conn.close();
		return result;
	}

	/**
	 * Name : P.Abinaya
	 * Created Date: 16/Aug/2016
	 * Modified Date:
	 * Description :   Create a common method to validate the cliams achievements for Member did not qualify workflow
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String validateClaimsAchievementsDidNotQualify(WebDriver driver) throws ClassNotFoundException, SQLException
	{
		Connection conn =null;
		Statement stat =null;
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);
		stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select ins_rule_id from ACCOUNT_INS_ACHIEVEMENT WHERE ACCOUNT_ID ='"+claimsmemberdidnotqualify+"'");
		System.out.println("Achivements query executed");
		String ins_rule_id1="";
		if(rs.next())
		{
			ins_rule_id1 = rs.getString("ins_rule_id");
			System.out.println("Ins_rule_id1: "+ins_rule_id1+" of the claims is retrieved from the Table");
		}
		else
		{
			ins_rule_id1 = "No Claims is achieved";
		}
		stat.close();
		conn.close();
		return ins_rule_id1;
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   13/AUG/16
	 * Modified Date:
	 * Description : Create a common method to store member credential after Enrollment for the claims workflow (opts out of the program)
	 */
	static String RAmemberOpsOutOfPgm;
	public static void storeMemberDuringEnrollmentMemberOpsOutOfPgm(WebDriver driver, String data)
	{
		RAmemberOpsOutOfPgm=data;
		try {
			Properties properties = new Properties();
			properties.setProperty("RAmember1", RAmemberOpsOutOfPgm);
			File file = new File(Directory.uploadFilePath+"\\RAMemberOpsOutOfPgm.properties");
			FileOutputStream fileOut = new FileOutputStream(file);
			properties.store(fileOut, "This is to store member for claims (member ops out of the program)");
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(RAmemberOpsOutOfPgm+":Member from Member enrollment for claims (member ops out of the program)");
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   17/AUG/16
	 * Modified Date:
	 * Description : Create a common method to enter RA member for customization session over claims workflow(Member ops out of the program)
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String enterRAMemberUsernameMemberOpsOutOfPgm(WebDriver driver)
	{
		WebElement  username = driver.findElement(By.xpath(OR.RA_MEMBER_LANDINGPAGE_USERNAME));
		sendKeys(username, RAmemberOpsOutOfPgm);
		System.out.println("Member on customization session claims (Member Ops out of the pgm) "+RAmemberOpsOutOfPgm);
		return RAmemberOpsOutOfPgm ;
	}

	/**
	 * Name : P.Abinaya
	 * Created Date: 17/Jul/2016
	 * Modified Date:
	 * Description : Retrieve member id with the member name for claims(Member Opt out of the program)
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String claimsmemberidOptOutOfPgm="";
	public static String getMemberIdForClaimsOptOutOfPgm(WebDriver driver) throws ClassNotFoundException, SQLException, IOException
	{
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.uploadFilePath+"//RAMemberOpsOutOfPgm.properties");
		urlsProperties.load(input);
		String member=urlsProperties.getProperty("RAmember1").trim();
		Connection conn =null;
		Statement stat =null;
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);
		stat=conn.createStatement();
		System.out.print("RAmember: "+RAmember);
		ResultSet rs = stat.executeQuery("select id from account where email='"+member+"'");
		System.out.println("Member id query executed");
		if(rs.next())
		{
			claimsmemberidOptOutOfPgm = rs.getString("id");
			System.out.println("Member id: "+claimsmemberidOptOutOfPgm+" must be retrieved");
		}
		stat.close();
		conn.close();
		return claimsmemberidOptOutOfPgm;
	}

	/**
	 * Name : P.Abinaya
	 * Created Date: 17/Aug/2016
	 * Modified Date:
	 * Description :   Create a common method to validate the cliams achievements (Member opt out of the program)
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String validateClaimsAchievementsOptOutOfPgm(WebDriver driver) throws ClassNotFoundException, SQLException
	{
		Connection conn =null;
		Statement stat =null;
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);
		stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select ins_rule_id from ACCOUNT_INS_ACHIEVEMENT WHERE ACCOUNT_ID ='"+claimsmemberidOptOutOfPgm+"'");
		System.out.println("Achivements query executed");
		String ins_rule_id1="";

		if(rs.next())
		{
			ins_rule_id1 = rs.getString("ins_rule_id");
			System.out.println("Ins_rule_id1: "+ins_rule_id1+" of the claims is retrieved from the Table");
		}
		stat.close();
		conn.close();
		return ins_rule_id1;
	}

	/**
	 * Name :     LEENA P.
	 * Created Date:   16/AUG/16
	 * Modified Date:
	 * Description : Create a common method to verify the email of the member once the lecture session is missed
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void verifyMemberMailReminderLectureSession(WebDriver driver, String mailid)
	{
		wait(driver, "5");
		String Parent_Window = driver.getWindowHandle();
		Navigate.newTab(driver);
		if(mailid.contains("yopmail.com"))
		{
			Navigate.get(driver, Directory.yopmailurl);
			for (String Second_Window : driver.getWindowHandles())
			{
				driver.switchTo().window(Second_Window);
				Navigate.maximize(driver);
				WebElement yopEmailTextbox= driver.findElement(By.xpath(OR.YOP_EMAIL_TEXTBOX));
				verifyElementIsPresent(driver, yopEmailTextbox);
				clear(yopEmailTextbox);
				sendKeys(yopEmailTextbox, mailid);
				WebElement checkInbox= driver.findElement(By.xpath(OR.YOP_EMAIL_CHECK_INBOX));
				verifyElementIsPresent(driver, checkInbox);
				jsClickByXPath(driver, OR.YOP_EMAIL_CHECK_INBOX);
				wait(driver, "5");
				Navigate.switchToDefaultFrame(driver);
				WebElement iframe= driver.findElement(By.id(OR.YOPMAIL_IFRAME1));
				Navigate.switchToFrame(driver, iframe);

				wait(driver, "10");
				System.out.println("sdfdf2");
				WebElement generatedLink= driver.findElement(By.xpath(OR.ORBERA_MEMBER_LECTURE_MAIL_REMINDER));
				waitForElement(driver, OR.ORBERA_MEMBER_LECTURE_MAIL_REMINDER);
				wait(driver, "5");
				if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
				{
					driver.get("javascript:document.getElementById('overridelink').click();");
					wait(driver, "5");
				}
				actionClick(driver, generatedLink);
				wait(driver, "3");
				Navigate.switchToDefaultFrame(driver);
				WebElement iframe2= driver.findElement(By.id(OR.YOPMAIL_IFRAME2));
				Navigate.switchToFrame(driver, iframe2);
				waitForElement(driver, OR.ORBERA_MEMBER_LECTURE_CONTENT_REMINDER);
				wait(driver, "3");
				Navigate.switchToDefaultFrame(driver);
			}
			Navigate.closeTab(driver);
			driver.switchTo().window(Parent_Window);
		}
		else if(mailid.contains("guerrillamail"))
		{
			Navigate.get(driver, Directory.Guerrillamailurl);
			for (String Second_Window : driver.getWindowHandles())
			{
				driver.switchTo().window(Second_Window);
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
				com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_MEMBER_LECTURE_MAIL_REMINDER);
				wait(driver, "5");

				if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
				{
					driver.get("javascript:document.getElementById('overridelink').click();");
					wait(driver, "5");
				}
				jsClickByXPath(driver, OR.ORBERA_MEMBER_LECTURE_MAIL_REMINDER);
				wait(driver, "3");
				waitForElement(driver, OR.ORBERA_MEMBER_LECTURE_CONTENT_REMINDER);
			}
			Navigate.closeTab(driver);
			driver.switchTo().window(Parent_Window);
		}
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   19/AUG/16
	 * Modified Date:
	 * Description : Create a common method to store member credential after Enrollment for claims(BeFit Positive scenario)
	 */
	static String RAmemberBeFitPositive;
	public static void storeMemberOnEnrollmentBeFitPositive(WebDriver driver, String data)
	{
		RAmemberBeFitPositive=data;
		try {
			Properties properties = new Properties();
			properties.setProperty("RAmember1", RAmemberBeFitPositive);
			File file = new File(Directory.uploadFilePath+"//RAmemberBeFitPositive.properties");
			FileOutputStream fileOut = new FileOutputStream(file);
			properties.store(fileOut, "This is to store member for claims");
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(RAmemberBeFitPositive+":Member from Member enrollment");
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   19/AUG/16
	 * Modified Date:
	 * Description : Create a common method to enter RA member for customization session over claims workflow (Be Fit Positive scenario)
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String enterRAMemberUsernameBeFitPostive(WebDriver driver)
	{
		WebElement  username = driver.findElement(By.xpath(OR.RA_MEMBER_LANDINGPAGE_USERNAME));
		sendKeys(username, RAmemberBeFitPositive);
		System.out.println("Member on customization session"+RAmemberBeFitPositive);
		return RAmemberBeFitPositive ;
	}

	/**
	 * Name : P.Abinaya
	 * Created Date: 19/Aug/2016
	 * Modified Date:
	 * Description : Retrieve member id with the member name for claims (Be Fit Positive)
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String claimsmemberidBeFitPositive="";
	public static String getMemberIdForClaimsBeFitPositive(WebDriver driver) throws ClassNotFoundException, SQLException, IOException
	{
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.uploadFilePath+"//RAmemberBeFitPositive.properties");
		urlsProperties.load(input);
		String member=urlsProperties.getProperty("RAmember1").trim();
		Connection conn =null;
		Statement stat =null;
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);
		stat=conn.createStatement();
		System.out.print("RAmember: "+member);
		ResultSet rs = stat.executeQuery("select id from account where email='"+member+"'");
		System.out.println("Member id query executed");
		if(rs.next())
		{
			claimsmemberidBeFitPositive = rs.getString("id");
			System.out.println("Member id: "+claimsmemberidBeFitPositive+" must be retrieved");
		}
		stat.close();
		conn.close();
		return claimsmemberidBeFitPositive;
	}

	/**
	 * Name : P.Abinaya
	 * Created Date: 19/Aug/2016
	 * Modified Date:
	 * Description :   Create a common method to validate the cliams achievements (BeFit Program)
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String validateClaimsAchievementsBeFitPositive(WebDriver driver) throws ClassNotFoundException, SQLException
	{
		Connection conn =null;
		Statement stat =null;
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);
		stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select ins_rule_id from ACCOUNT_INS_ACHIEVEMENT WHERE ACCOUNT_ID ='"+claimsmemberid+"'");
		System.out.println("Achivements query executed");
		String ins_rule_id1="";
		if(rs.next())
		{
			ins_rule_id1 = rs.getString("ins_rule_id");
			System.out.println("Ins_rule_id1: "+ins_rule_id1+" of the claims is retrieved from the Table");
		}
		stat.close();
		conn.close();
		return ins_rule_id1;
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   22/AUG/16
	 * Modified Date:
	 * Description : Create a common method to store member credential after Enrollment for claims(BeFit Negative scenario)
	 */
	static String RAmemberBeFitNegative;
	public static void storeMemberOnEnrollmentBeFitNegative(WebDriver driver, String data)
	{
		RAmemberBeFitNegative=data;
		try {
			Properties properties = new Properties();
			properties.setProperty("RAmember1", RAmemberBeFitNegative);
			File file = new File(Directory.uploadFilePath+"//RAmemberBeFitNegative.properties");
			FileOutputStream fileOut = new FileOutputStream(file);
			properties.store(fileOut, "This is to store member for claims");
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(RAmemberBeFitNegative+":Member from Member enrollment");
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   22/AUG/16
	 * Modified Date:
	 * Description : Create a common method to enter RA member for customization session over claims workflow (Be Fit Negative scenario)
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String enterRAMemberUsernameBeFitNegative(WebDriver driver)
	{
		WebElement  username = driver.findElement(By.xpath(OR.RA_MEMBER_LANDINGPAGE_USERNAME));
		sendKeys(username, RAmemberBeFitNegative);
		System.out.println("Member on customization session"+RAmemberBeFitNegative);
		return RAmemberBeFitNegative ;
	}

	/**
	 * Name : P.Abinaya
	 * Created Date: 22/Aug/2016
	 * Modified Date:
	 * Description : Retrieve member id with the member name for claims (BeFit Negative Scenario)
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String claimsmemberbefitnegative="";
	public static String getMemberIdForClaimsBeFitNegative(WebDriver driver) throws ClassNotFoundException, SQLException, IOException
	{
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.uploadFilePath+"//RAmemberBeFitNegative.properties");
		urlsProperties.load(input);
		String member=urlsProperties.getProperty("RAmember1").trim();
		Connection conn =null;
		Statement stat =null;
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);
		stat=conn.createStatement();
		System.out.print("RAmember: "+member);
		ResultSet rs = stat.executeQuery("select id from account where email='"+member+"'");
		System.out.println("Member id query executed");
		if(rs.next())
		{
			claimsmemberbefitnegative = rs.getString("id");
			System.out.println("Member id: "+claimsmemberbefitnegative+" must be retrieved");
		}
		stat.close();
		conn.close();
		return claimsmemberbefitnegative;
	}

	/**
	 * Name : P.Abinaya
	 * Created Date: 22/Aug/2016
	 * Modified Date:
	 * Description :   Create a common method to validate the Roll up status and Session status for the cliams(Be Fit Negative Scenario)
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String validateRollupAndSessionStatusForClaimsBeFitNegative(WebDriver driver) throws ClassNotFoundException, Exception
	{
		Connection conn =null;
		Statement stat =null;
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.uploadFilePath+"//RAmemberBeFitNegative.properties");
		urlsProperties.load(input);
		String member=urlsProperties.getProperty("RAmember1").trim();
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);
		stat=conn.createStatement();
		System.out.println("Connection established");
		ResultSet rs = stat.executeQuery("select SESSION_STATUS, ROLLUP_STATUS from CALENDAR_EVENT WHERE ACCOUNT_ID IN (SELECT ID FROM ACCOUNT WHERE EMAIL LIKE '"+member+"')");
		System.out.println("query executed");
		String session_status="";
		String rollup_status="";
		String result="";
		if(rs.next())
		{
			session_status = rs.getString("SESSION_STATUS");
			rollup_status= rs.getString("ROLLUP_STATUS");
			System.out.println("Session status: "+session_status+" of the cliams is retrieved from the Table");
			System.out.println("Rollup status: "+rollup_status+" of the claims is retrieved from the Table");
		}
		if(!rollup_status.equals("ROLLUP CLOSED"))
		{
			wait(driver, "240");
		}
		result=session_status+","+rollup_status;
		System.out.println("result"+result);
		stat.close();
		conn.close();
		return result;
	}

	/**
	 * Name : VIGNESHRAJ.M
	 * Created Date: 22/Aug/2016
	 * Modified Date:
	 * Description :   Create a common method to validate data specific client in PHI popup
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void verifyPHIDataForSpecificClient(WebDriver driver)
	{
		WebElement verifyClientNameHeader= driver.findElement(By.xpath("//span[contains(text(),'"+Directory.RA_Enrollment_Last_Name+"')]"));
		verifyElementIsPresent(driver, verifyClientNameHeader);
	}

	/**
	 * Name :    Vinothkumar.M
	 * Created Date:   23/AUG/16
	 * Modified Date:
	 * Description : Create a common method to Client2 insurance details in RA Enrollment
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void realAppealClient2InsuranceDetails(WebDriver driver)
	{
		Manipulation.wait(driver, "5");
		waitForElement(driver, OR.RA_MEMBER_INSURANCEPROVIDER);
		WebElement insurance_provider= driver.findElement(By.xpath(OR.RA_MEMBER_INSURANCEPROVIDER));
		WebElement member_id= driver.findElement(By.xpath(OR.RA_MEMBER_MEMBERID));
		WebElement groupnumber= driver.findElement(By.xpath(OR.RA_MEMBER_GROUPNUMBER));
		clearAndType(insurance_provider, Directory.Client2_RA_Enrollment_Insurance_Provider);
		WebElement autosuggest= driver.findElement(By.xpath(OR.RA_INSURANCE_AUTO_SUGGEST));
		Manipulation.wait(driver, "2");
		waitForElement(driver, OR.RA_INSURANCE_AUTO_SUGGEST);
		actionClick(driver, autosuggest);
		clearAndType(member_id, Directory.Client2_RA_Enrollment_Member_Id);
		clearAndType(groupnumber, Directory.Client2_RA_Enrollment_Group_Number);
	}

	/**
	 * Name :     Vinothkumar.M
	 * Created Date:   23/AUG/16
	 * Modified Date:
	 * Description : Create a common method to enter last name dynamic to multi environment
	 */
	public static void client2EnterRALastName(WebDriver driver)
	{
		WebElement  lastname = driver.findElement(By.xpath(OR.RA_MEMBER_LASTNAME));
		sendKeys(lastname, Directory.Client2_RA_Enrollment_Last_Name);
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   23/AUG/16
	 * Modified Date:
	 * Description :   Create a common method to find member which is available to reschedule a session from database
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String getMemberForRescheduleCust(WebDriver driver) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select A.EMAIL as mail, CE.SESSION_STATUS from CALENDAR_EVENT CE, ACCOUNT A WHERE A.ID= CE.ACCOUNT_ID AND CE.SESSION_STATUS = 'Missed' and CE.SESSION_TYPE_ID = '13' and START_DT_TIME>SYSDATE + INTERVAL '-52' DAY ORDER BY CE.START_DT desc");
		System.out.println("query executed");
		String mail="";
		int i=0;
		while(rs.next())
		{
			if(i==3)
			{
				mail= rs.getString("mail");
				System.out.println(mail+" Mail is retrieved from the Table ");
			}
			i++;
		}
		return mail;
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   23/AUG/16
	 * Modified Date:
	 * Description : Create a common method to store member credential for the claims(Reschedule-Customization)
	 */
	public static void storeMemberClaimsRescheduleCust(WebDriver driver, String data)
	{
		String memberreschedule=data;
		try
		{
			Properties properties = new Properties();
			properties.setProperty("RAmember1", memberreschedule);
			File file = new File(Directory.uploadFilePath+"//RAmemberRescheduleCust.properties");
			FileOutputStream fileOut = new FileOutputStream(file);
			properties.store(fileOut, "This is to store member for claims");
			fileOut.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		System.out.println(memberreschedule+":Member from Member Reschedule Customization session");
	}

	/**
	 * Name : P.Abinaya
	 * Created Date: 23/Aug/2016
	 * Modified Date:
	 * Description : Retrieve member id with the member name for claims (Reschedule-Customization)
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String claimsmemberidreschedulecust="";
	public static String getMemberIdForClaimsRescheduleCust(WebDriver driver) throws ClassNotFoundException, SQLException, IOException
	{
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.uploadFilePath+"//RAmemberRescheduleCust.properties");
		urlsProperties.load(input);
		String member=urlsProperties.getProperty("RAmember1").trim();
		Connection conn =null;
		Statement stat =null;
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);
		stat=conn.createStatement();
		System.out.print("RAmember: "+member);
		ResultSet rs = stat.executeQuery("select id from account where email='"+member+"'");
		System.out.println("Member id query executed");
		if(rs.next())
		{
			claimsmemberidreschedulecust = rs.getString("id");
			System.out.println("Member id: "+claimsmemberidreschedulecust+" must be retrieved");
		}
		stat.close();
		conn.close();
		return claimsmemberidreschedulecust;
	}

	/**
	 * Name : P.Abinaya
	 * Created Date: 23/Aug/2016
	 * Modified Date:
	 * Description :   Create a common method to validate the cliams achievements (BeFit Program)
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String validateClaimsAchievementsRescheduleCust(WebDriver driver) throws ClassNotFoundException, SQLException
	{
		Connection conn =null;
		Statement stat =null;
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);
		stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select ins_rule_id from ACCOUNT_INS_ACHIEVEMENT WHERE ACCOUNT_ID ='"+claimsmemberidreschedulecust+"'");
		System.out.println("Achivements query executed");
		String ins_rule_id1="";
		if(rs.next())
		{
			ins_rule_id1 = rs.getString("ins_rule_id");
			System.out.println("Ins_rule_id1: "+ins_rule_id1+" of the claims is retrieved from the Table");
		}
		stat.close();
		conn.close();
		return ins_rule_id1;
	}

	/**
	 * Name :     LEENA P.
	 * Created Date:   16/AUG/16
	 * Modified Date:
	 * Description : Create a common method to verify the email of the member once the 1on1 session is missed
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void verifyMemberMailMissedSession(WebDriver driver, String mailid)
	{
		wait(driver, "5");
		String Parent_Window = driver.getWindowHandle();
		Navigate.newTab(driver);
		if(mailid.contains("yopmail.com"))
		{
			Navigate.get(driver, Directory.yopmailurl);
			for (String Second_Window : driver.getWindowHandles())
			{
				driver.switchTo().window(Second_Window);
				Navigate.maximize(driver);
				WebElement yopEmailTextbox= driver.findElement(By.xpath(OR.YOP_EMAIL_TEXTBOX));
				verifyElementIsPresent(driver, yopEmailTextbox);
				clear(yopEmailTextbox);
				sendKeys(yopEmailTextbox, mailid);
				WebElement checkInbox= driver.findElement(By.xpath(OR.YOP_EMAIL_CHECK_INBOX));
				verifyElementIsPresent(driver, checkInbox);
				jsClickByXPath(driver, OR.YOP_EMAIL_CHECK_INBOX);
				wait(driver, "5");
				Navigate.switchToDefaultFrame(driver);
				WebElement iframe= driver.findElement(By.id(OR.YOPMAIL_IFRAME1));
				Navigate.switchToFrame(driver, iframe);
				wait(driver, "10");
				System.out.println("sdfdf2");
				WebElement generatedLink= driver.findElement(By.xpath(OR.ORBERA_MEMBER_1on1_MAIL_MISSED));
				//com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_MEMBER_1on1_MAIL_SCHEDULE);
				waitForElement(driver, OR.ORBERA_MEMBER_1on1_MAIL_MISSED);
				wait(driver, "5");

				if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
				{
					driver.get("javascript:document.getElementById('overridelink').click();");
					wait(driver, "5");
				}
				actionClick(driver, generatedLink);
				wait(driver, "3");
				Navigate.switchToDefaultFrame(driver);
				WebElement iframe2= driver.findElement(By.id(OR.YOPMAIL_IFRAME2));
				Navigate.switchToFrame(driver, iframe2);
				waitForElement(driver, OR.ORBERA_MEMBER_CONTENT_MISSED);
				waitForElement(driver, OR.ORBERA_MEMBER_1on1_CONTENT_MISSED);
				Navigate.switchToDefaultFrame(driver);
			}
			Navigate.closeTab(driver);
			driver.switchTo().window(Parent_Window);
		}
		else if(mailid.contains("guerrillamail"))
		{
			Navigate.get(driver, Directory.Guerrillamailurl);
			for (String Second_Window : driver.getWindowHandles())
			{
				driver.switchTo().window(Second_Window);
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
				com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_MEMBER_1on1_MAIL_MISSED);
				wait(driver, "5");

				if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
				{
					driver.get("javascript:document.getElementById('overridelink').click();");
					wait(driver, "5");
				}
				jsClickByXPath(driver, OR.ORBERA_MEMBER_1on1_MAIL_MISSED);
				wait(driver, "3");
				waitForElement(driver, OR.ORBERA_MEMBER_CONTENT_MISSED);
				waitForElement(driver, OR.ORBERA_MEMBER_1on1_CONTENT_MISSED);
			}
			Navigate.closeTab(driver);
			driver.switchTo().window(Parent_Window);
		}
	}

	/**
	 * Name :     LEENA P.
	 * Created Date:   16/AUG/16
	 * Modified Date:
	 * Description : Create a common method to verify the email of the member once the lecture session is missed
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void verifyMemberMailMissedLectureSession(WebDriver driver, String mailid)
	{
		wait(driver, "5");
		String Parent_Window = driver.getWindowHandle();
		Navigate.newTab(driver);
		if(mailid.contains("yopmail.com"))
		{
			Navigate.get(driver, Directory.yopmailurl);
			for (String Second_Window : driver.getWindowHandles())
			{
				driver.switchTo().window(Second_Window);
				Navigate.maximize(driver);
				WebElement yopEmailTextbox= driver.findElement(By.xpath(OR.YOP_EMAIL_TEXTBOX));
				verifyElementIsPresent(driver, yopEmailTextbox);
				clear(yopEmailTextbox);
				sendKeys(yopEmailTextbox, mailid);
				WebElement checkInbox= driver.findElement(By.xpath(OR.YOP_EMAIL_CHECK_INBOX));
				verifyElementIsPresent(driver, checkInbox);
				jsClickByXPath(driver, OR.YOP_EMAIL_CHECK_INBOX);
				wait(driver, "5");
				Navigate.switchToDefaultFrame(driver);
				WebElement iframe= driver.findElement(By.id(OR.YOPMAIL_IFRAME1));
				Navigate.switchToFrame(driver, iframe);
				wait(driver, "10");
				System.out.println("sdfdf2");
				WebElement generatedLink= driver.findElement(By.xpath(OR.ORBERA_MEMBER_LECTURE_MAIL_MISSED));
				waitForElement(driver, OR.ORBERA_MEMBER_LECTURE_MAIL_MISSED);
				wait(driver, "5");
				if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
				{
					driver.get("javascript:document.getElementById('overridelink').click();");
					wait(driver, "5");
				}
				actionClick(driver, generatedLink);
				wait(driver, "3");
				Navigate.switchToDefaultFrame(driver);
				WebElement iframe2= driver.findElement(By.id(OR.YOPMAIL_IFRAME2));
				Navigate.switchToFrame(driver, iframe2);
				waitForElement(driver, OR.ORBERA_MEMBER_CONTENT_MISSED);
				waitForElement(driver, OR.ORBERA_MEMBER_LECTURE_CONTENT_MISSED);
				Navigate.switchToDefaultFrame(driver);
			}
			Navigate.closeTab(driver);
			driver.switchTo().window(Parent_Window);
		}
		else if(mailid.contains("guerrillamail"))
		{
			Navigate.get(driver, Directory.Guerrillamailurl);
			for (String Second_Window : driver.getWindowHandles())
			{
				driver.switchTo().window(Second_Window);
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
				com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_MEMBER_LECTURE_MAIL_MISSED);
				wait(driver, "5");

				if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
				{
					driver.get("javascript:document.getElementById('overridelink').click();");
					wait(driver, "5");
				}
				jsClickByXPath(driver, OR.ORBERA_MEMBER_LECTURE_MAIL_MISSED);
				wait(driver, "3");
				waitForElement(driver, OR.ORBERA_MEMBER_CONTENT_MISSED);
				waitForElement(driver, OR.ORBERA_MEMBER_LECTURE_CONTENT_MISSED);
			}
			Navigate.closeTab(driver);
			driver.switchTo().window(Parent_Window);
		}
	}

	/**
	 * Name : Leena P.
	 * Created Date:   23/AUG/16
	 * Modified Date:
	 * Description : Create a common method to verify Completed session for a member
	 */
	public static void nextpagenavigatorcheckcompletedlecture(WebDriver driver)
	{
		try
		{
			WebElement  sessionStatus = driver.findElement(By.xpath(OR.ORBERA_MEMBER_FROM_HFOPS_SESSION_STATUS_LECTURE));
			verifyElementIsPresent(driver, sessionStatus);
		}
		catch(Exception e)
		{
			Manipulation.waitForElement(driver, OR.ORBERA_MEMBER_FROM_HFOPS_SESSION_STATUS_PAGE_NAVIGATOR);
			jsClickByXPath(driver,OR.ORBERA_MEMBER_FROM_HFOPS_SESSION_STATUS_PAGE_NAVIGATOR);
			WebElement  sessionStatus = driver.findElement(By.xpath(OR.ORBERA_MEMBER_FROM_HFOPS_SESSION_STATUS_LECTURE));
			verifyElementIsPresent(driver, sessionStatus);
		}
	}

	/**
	 * Name : Leena P.
	 * Created Date:   23/AUG/16
	 * Modified Date:
	 * Description : Create a common method to verify Completed session for a member
	 */
	public static void nextpagenavigatorcheckmissed(WebDriver driver)
	{
		try
		{
			WebElement  sessionStatus = driver.findElement(By.xpath(OR.ORBERA_MEMBER_FROM_HFOPS_SESSION_STATUS_1on1));
			verifyElementIsPresent(driver, sessionStatus);
		}
		catch(Exception e)
		{
			WebElement  sessionStatus = driver.findElement(By.xpath(OR.ORBERA_MEMBER_FROM_HFOPS_SESSION_STATUS_1on1));
			jsClickByXPath(driver,OR.ORBERA_MEMBER_FROM_HFOPS_SESSION_STATUS_PAGE_NAVIGATOR);
			verifyElementIsPresent(driver, sessionStatus);
		}
	}

	/**
	 * Name :     LEENA P.
	 * Created Date:   24/AUG/16
	 * Modified Date:
	 * Description : Create a common method to verify the email of the member once the 1on1 session is missed
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void verifyMemberMailReminder1on1Session(WebDriver driver, String mailid)
	{
		wait(driver, "5");
		String Parent_Window = driver.getWindowHandle();
		Navigate.newTab(driver);
		if(mailid.contains("yopmail.com"))
		{
			Navigate.get(driver, Directory.yopmailurl);
			for (String Second_Window : driver.getWindowHandles())
			{
				driver.switchTo().window(Second_Window);
				Navigate.maximize(driver);
				WebElement yopEmailTextbox= driver.findElement(By.xpath(OR.YOP_EMAIL_TEXTBOX));
				verifyElementIsPresent(driver, yopEmailTextbox);
				clear(yopEmailTextbox);
				sendKeys(yopEmailTextbox, mailid);
				WebElement checkInbox= driver.findElement(By.xpath(OR.YOP_EMAIL_CHECK_INBOX));
				verifyElementIsPresent(driver, checkInbox);
				jsClickByXPath(driver, OR.YOP_EMAIL_CHECK_INBOX);
				wait(driver, "5");
				Navigate.switchToDefaultFrame(driver);
				WebElement iframe= driver.findElement(By.id(OR.YOPMAIL_IFRAME1));
				Navigate.switchToFrame(driver, iframe);
				wait(driver, "10");
				System.out.println("sdfdf2");
				WebElement generatedLink= driver.findElement(By.xpath(OR.ORBERA_MEMBER_1on1_MAIL_REMINDER));
				waitForElement(driver, OR.ORBERA_MEMBER_1on1_MAIL_REMINDER);
				wait(driver, "5");

				if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
				{
					driver.get("javascript:document.getElementById('overridelink').click();");
					wait(driver, "5");
				}
				actionClick(driver, generatedLink);
				wait(driver, "3");
				Navigate.switchToDefaultFrame(driver);
				WebElement iframe2= driver.findElement(By.id(OR.YOPMAIL_IFRAME2));
				Navigate.switchToFrame(driver, iframe2);
				waitForElement(driver, OR.ORBERA_MEMBER_1on1_CONTENT_REMINDER);
				Navigate.switchToDefaultFrame(driver);
			}
			Navigate.closeTab(driver);
			driver.switchTo().window(Parent_Window);
		}
		else if(mailid.contains("guerrillamail"))
		{
			Navigate.get(driver, Directory.Guerrillamailurl);
			for (String Second_Window : driver.getWindowHandles())
			{
				driver.switchTo().window(Second_Window);
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
				com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_MEMBER_1on1_MAIL_REMINDER);
				wait(driver, "5");

				if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
				{
					driver.get("javascript:document.getElementById('overridelink').click();");
					wait(driver, "5");
				}
				jsClickByXPath(driver, OR.ORBERA_MEMBER_1on1_MAIL_REMINDER);
				wait(driver, "3");
				waitForElement(driver, OR.ORBERA_MEMBER_1on1_CONTENT_REMINDER);
			}
			Navigate.closeTab(driver);
			driver.switchTo().window(Parent_Window);
		}
	}

	/**
	 * Name :     Vinothkumar.M
	 * Created Date:   25/Aug/16
	 * Modified Date:
	 * Description : Create a common method to enter first name dynamic to multi environment
	 */
	public static void client2EnterRAFirstName(WebDriver driver)
	{
		WebElement  firstname = driver.findElement(By.xpath(OR.RA_MEMBER_FIRSTNAME));
		sendKeys(firstname, Directory.Client2_RA_Enrollment_First_Name);
	}

	/**
	 * Name :    VIGNESHRAJ.M
	 * Created Date:   23/AUG/16
	 * Modified Date:
	 * Description :   Create a common method to find member different Onboarding status
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String retreieveEmailForDifferentOnboardingStatusandOrgIDClient1(WebDriver driver, String status) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("SELECT ACNT.ID, ACNT.EMAIL FROM SUMMARY_ACCOUNT_TODATE SMRY, ACCOUNT ACNT WHERE ACNT.ID= SMRY.ACCOUNT_ID AND ACNT.Status='Active' AND ACNT.EMAIL NOT LIKE '%api%' AND ACNT.EMAIL NOT LIKE '%QAmember%-default@yopmail.com%' AND SMRY.ONBOARDING_STATUS= '"+status+"'and ACNT.ORGANIZATION_ID= '"+Directory.Client1_RA_Organization_ID+"'and SMRY.SESSION_DATE_UTC<SYSDATE ORDER BY SMRY.SESSION_DATE_UTC DESC");
		System.out.println("query executed");
		String memberEmailID="";
		if(rs.next())
		{
			memberEmailID= rs.getString("EMAIL");
			System.out.println(" Member EmailID is retrieved from the Table: "+memberEmailID);
		}
		return memberEmailID;
	}

	/**
	 * Name :     Vinothkumar.M
	 * Created Date:   23/AUG/16
	 * Modified Date:
	 * Description : Create a common method to enter last name dynamic to multi environment
	 */
	public static void realAppealClient1SettingsEnterRALastName(WebDriver driver)
	{
		WebElement  lastname = driver.findElement(By.xpath(OR.RA_MEMBER_SETTINGS_CONTACT_LAST_NAME_TEXTBOX));
		clearAndType(lastname, Directory.RA_Enrollment_Last_Name);

	}

	/**
	 * Name :    Vinothkumar.M
	 * Created Date:   25/AUG/16
	 * Modified Date:
	 * Description : Create a common method to Primary Health insurance details in Settings page
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void realAppealClient1SettingsPrimaryInsuranceDetails(WebDriver driver)
	{
		Manipulation.wait(driver, "5");
		waitForElement(driver, OR.RA_MEMBER_SETTINGS_INSURANCEPROVIDER);
		WebElement insurance_provider= driver.findElement(By.xpath(OR.RA_MEMBER_SETTINGS_INSURANCEPROVIDER));
		WebElement member_id= driver.findElement(By.xpath(OR.RA_MEMBER_SETTINGS_MEMBERID));
		WebElement groupnumber= driver.findElement(By.xpath(OR.RA_MEMBER_SETTINGS_GROUPNUMBER));
		clearAndType(insurance_provider, Directory.RA_Enrollment_Insurance_Provider);
		WebElement autosuggest= driver.findElement(By.xpath(OR.RA_MEMBER_SETTINGS_INSURANCE_AUTO_SUGGEST));
		Manipulation.wait(driver, "2");
		waitForElement(driver, OR.RA_MEMBER_SETTINGS_INSURANCE_AUTO_SUGGEST);
		actionClick(driver, autosuggest);
		//jsClickByXPath(driver, OR.RA_INSURANCE_AUTO_SUGGEST);
		clearAndType(member_id, Directory.RA_Enrollment_Member_Id);
		clearAndType(groupnumber, Directory.RA_Enrollment_Group_Member);
	}

	/**
	 * Name :     Vinothkumar.M
	 * Created Date:   23/AUG/16
	 * Modified Date:
	 * Description : Create a common method to enter last name dynamic to multi environment
	 */
	public static void realAppealClient1SettingsEnterRAFirstName(WebDriver driver)
	{
		WebElement  firstname = driver.findElement(By.xpath(OR.RA_MEMBER_SETTINGS_CONTACT_FIRST_NAME_TEXTBOX));
		clearAndType(firstname, Directory.RA_Enrollment_First_Name);

	}

	/**
	 * Name :     LEENA P.
	 * Created Date:   16/AUG/16
	 * Modified Date:
	 * Description : Create a common method to verify the email of the member once the lecture session is signed up
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void verifyMemberMailSignUpLectureSession(WebDriver driver, String mailid)
	{
		wait(driver, "5");
		String Parent_Window = driver.getWindowHandle();
		Navigate.newTab(driver);
		if(mailid.contains("yopmail.com"))
		{
			Navigate.get(driver, Directory.yopmailurl);
			for (String Second_Window : driver.getWindowHandles())
			{
				driver.switchTo().window(Second_Window);
				Navigate.maximize(driver);
				WebElement yopEmailTextbox= driver.findElement(By.xpath(OR.YOP_EMAIL_TEXTBOX));
				verifyElementIsPresent(driver, yopEmailTextbox);
				clear(yopEmailTextbox);
				sendKeys(yopEmailTextbox, mailid);
				WebElement checkInbox= driver.findElement(By.xpath(OR.YOP_EMAIL_CHECK_INBOX));
				verifyElementIsPresent(driver, checkInbox);
				jsClickByXPath(driver, OR.YOP_EMAIL_CHECK_INBOX);
				wait(driver, "5");
				Navigate.switchToDefaultFrame(driver);
				WebElement iframe= driver.findElement(By.id(OR.YOPMAIL_IFRAME1));
				Navigate.switchToFrame(driver, iframe);
				wait(driver, "10");
				System.out.println("sdfdf2");
				WebElement generatedLink= driver.findElement(By.xpath(OR.ORBERA_MEMBER_LECTURE_MAIL_SIGNUP_CONFIRMATION));
				waitForElement(driver, OR.ORBERA_MEMBER_LECTURE_MAIL_SIGNUP_CONFIRMATION);
				wait(driver, "5");
				if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
				{
					driver.get("javascript:document.getElementById('overridelink').click();");
					wait(driver, "5");
				}
				actionClick(driver, generatedLink);
				wait(driver, "3");
				Navigate.switchToDefaultFrame(driver);
				WebElement iframe2= driver.findElement(By.id(OR.YOPMAIL_IFRAME2));
				Navigate.switchToFrame(driver, iframe2);
				waitForElement(driver, OR.ORBERA_MEMBER_LECTURE_MAIL_SIGNUP_CONFIRMATION_CONTENT);
				Navigate.switchToDefaultFrame(driver);
			}
			WebElement iframe= driver.findElement(By.id(OR.YOPMAIL_IFRAME1));
			Navigate.switchToFrame(driver, iframe);
			com.zillion.qa.coaches.Classes.deleteYopmailMsg(driver);
			Navigate.switchToDefaultFrame(driver);
			Navigate.closeTab(driver);
			driver.switchTo().window(Parent_Window);
		}
		else if(mailid.contains("guerrillamail"))
		{
			Navigate.get(driver, Directory.Guerrillamailurl);
			for (String Second_Window : driver.getWindowHandles())
			{
				driver.switchTo().window(Second_Window);
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
				com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_MEMBER_LECTURE_MAIL_SIGNUP_CONFIRMATION);
				wait(driver, "5");

				if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
				{
					driver.get("javascript:document.getElementById('overridelink').click();");
					wait(driver, "5");
				}
				jsClickByXPath(driver, OR.ORBERA_MEMBER_LECTURE_MAIL_SIGNUP_CONFIRMATION);
				wait(driver, "3");
				waitForElement(driver, OR.ORBERA_MEMBER_LECTURE_MAIL_SIGNUP_CONFIRMATION_CONTENT);
			}
			Navigate.closeTab(driver);
			driver.switchTo().window(Parent_Window);
		}
	}

	/**
	 * Name :  Vinothkumar.M
	 * Created Date:   31/AUG/16
	 * Modified Date:
	 * Description : Create a common method to enter day of DOB dynamic to multi environment
	 */
	public static void client2EnterRADOBDay(WebDriver driver)
	{
		WebElement  day = driver.findElement(By.xpath(OR.RA_MEMBER_DOBDAY));
		selectByVisibletext(day, Directory.Client2_RA_Enrollment_Day);
	}

	/**
	 * Name :  Vinothkumar.M
	 * Created Date:   31/AUG/16
	 * Modified Date:
	 * Description : Create a common method to enter month of DOB dynamic to multi environment
	 */
	public static void client2EnterRADOBMonth(WebDriver driver)
	{
		WebElement  month = driver.findElement(By.xpath(OR.RA_MEMBER_DOBMONTH));
		selectByVisibletext(month, Directory.Client2_RA_Enrollment_Month);
	}

	/**
	 * Name :     Vinothkumar.M
	 * Created Date:  31/AUG/16
	 * Modified Date:
	 * Description : Create a common method to enter year of DOB dynamic to multi environment
	 */
	public static void client2EnterRADOBYear(WebDriver driver)
	{
		WebElement  year = driver.findElement(By.xpath(OR.RA_MEMBER_DOBYEAR));
		selectByVisibletext(year, Directory.Client2_RA_Enrollment_Year);
	}

	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   19/Aug/16
	 * Modified Date:
	 * Description :   Create a common method to Set Max enrollments Limit
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String submitMaxEnrollmentsAllowedForOrganization(WebDriver driver, String testData) throws ClassNotFoundException, SQLException
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
		String organizationID= Directory.Client1_RA_Organization_ID;
		System.out.println("Organization id is" +organizationID);
		//Statement stat=conn.createStatement();
		PreparedStatement ps = conn.prepareStatement("update ORG_INS_SETTING set MAX_ENROLLMENTS_ALLOWED="+testData+" WHERE ORGANIZATION_ID = '"+organizationID+"'");
		ps.executeUpdate();
		ps.close();
		System.out.println("query executed");
		String maxEnrollmentAllowed="";
		/*if(rs.next())
		{
			maxEnrollmentAllowed= rs.getString("MAX_ENROLLMENTS_ALLOWED");
			System.out.println(" Maximum enrollments Allowed is retrieved from the Table "+maxEnrollmentAllowed);
		}*/
		return testData;
	}

	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   19/Aug/16
	 * Modified Date:
	 * Description :   Create a common method to find Organization ID from organization Name
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String retreieveMaxEnrollMentAllowedForOrganization(WebDriver driver) throws ClassNotFoundException, SQLException
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
		String organizationName= Directory.RA_Enrollment_Organization_Name;
		String maxEnrollmentAllowed="";
		ResultSet rs = stat.executeQuery("select * from org_ins_setting where organization_id=(select ID from organization where organization_name='"+organizationName+"')");
		System.out.println("query executed");
		if(rs.next())
		{
			maxEnrollmentAllowed= rs.getString("MAX_ENROLLMENTS_ALLOWED");
			System.out.println(" Maximum enrollments Allowed is retrieved from the Table "+maxEnrollmentAllowed);
		}
		return maxEnrollmentAllowed;
	}

	/**
	 * Name : P.Abinaya
	 * Created Date: 17/Aug/2016
	 * Modified Date:
	 * Description :   Create a common method to validate the Roll up status and Session status for the cliams(Member opt out of the program)
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String rollup_prcs_dt_ops_out_pgm=null;
	public static String validateRollupAndSessionStatusForClaimsOptOutOfPgm(WebDriver driver) throws ClassNotFoundException, Exception
	{
		Connection conn =null;
		Statement stat =null;
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.uploadFilePath+"//RAMemberOpsOutOfPgm.properties");
		urlsProperties.load(input);
		String member=urlsProperties.getProperty("RAmember1").trim();
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);
		stat=conn.createStatement();
		System.out.println("Connection established");
		ResultSet rs = stat.executeQuery("select SESSION_STATUS,END_DT_TIME, ROLLUP_STATUS from CALENDAR_EVENT WHERE ACCOUNT_ID IN (SELECT ID FROM ACCOUNT WHERE EMAIL LIKE '"+member+"')");
		System.out.println("query executed");
		String session_status="";
		String rollup_status="";
		String result="";
		if(rs.next())
		{
			session_status = rs.getString("SESSION_STATUS");
			rollup_status= rs.getString("ROLLUP_STATUS");
			rollup_prcs_dt_ops_out_pgm= rs.getString("END_DT_TIME");
			System.out.println("Session status: "+session_status+" of the cliams is retrieved from the Table");
			System.out.println("Rollup status: "+rollup_status+" of the claims is retrieved from the Table");
		}
		result=session_status+","+rollup_status;
		System.out.println("result"+result);
		stat.close();
		conn.close();
		return result;
	}

	/**
	 * Name : P.Abinaya
	 * Created Date: 19/Aug/2016
	 * Modified Date:
	 * Description :   Create a common method to validate the Roll up status and Session status for the cliams (BeFit Program)
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String rollup_prcs_dt_befit_positive=null;
	public static String validateRollupForClaimsBeFitPositive(WebDriver driver) throws ClassNotFoundException, Exception
	{
		Connection conn =null;
		Statement stat =null;
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.uploadFilePath+"//RAmemberBeFitPositive.properties");
		urlsProperties.load(input);
		String member=urlsProperties.getProperty("RAmember1").trim();
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);
		stat=conn.createStatement();
		System.out.println("Connection established");
		ResultSet rs = stat.executeQuery("select SESSION_STATUS,END_DT_TIME, ROLLUP_STATUS from CALENDAR_EVENT WHERE ACCOUNT_ID IN (SELECT ID FROM ACCOUNT WHERE EMAIL LIKE '"+member+"')");
		System.out.println("query executed");
		String session_status="";
		String rollup_status="";
		String result="";
		if(rs.next())
		{
			session_status = rs.getString("SESSION_STATUS");
			rollup_status= rs.getString("ROLLUP_STATUS");
			rollup_prcs_dt_befit_positive= rs.getString("END_DT_TIME");
			System.out.println("Session status: "+session_status+" of the cliams is retrieved from the Table");
			System.out.println("Rollup status: "+rollup_status+" of the claims is retrieved from the Table");
		}
		result=session_status+","+rollup_status;
		System.out.println("result"+result);
		stat.close();
		conn.close();
		return result;
	}

	/**
	 * Name : P.Abinaya
	 * Created Date: 22/Aug/2016
	 * Modified Date:
	 * Description :   Create a common method to validate the claims achievements (Be Fit Negative Scenario) workflow
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String validateClaimsAchievementsBeFitNegative(WebDriver driver) throws ClassNotFoundException, SQLException
	{
		Connection conn =null;
		Statement stat =null;
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);
		stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select ins_rule_id from ACCOUNT_INS_ACHIEVEMENT WHERE ACCOUNT_ID in (select id from account where email like '"+claimsmemberbefitnegative+"')");
		System.out.println("Achivements query executed");
		String ins_rule_id1="";
		if(rs.next())
		{
			ins_rule_id1 = rs.getString("ins_rule_id");
			System.out.println("Ins_rule_id1: "+ins_rule_id1+" of the claims is retrieved from the Table");
		}
		else
		{
			ins_rule_id1 = "No Claims is achieved";
		}
		stat.close();
		conn.close();
		return ins_rule_id1;
	}

	/**
	 * Name : P.Abinaya
	 * Created Date: 23/Aug/2016
	 * Modified Date:
	 * Description :   Create a common method to validate the Roll up status and Session status for the cliams (Reschedule-Customization)
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String rollup_prcs_dt_resc_cust=null;
	public static String validateRollupForClaimsRescheduleCust(WebDriver driver) throws ClassNotFoundException, Exception
	{
		Connection conn =null;
		Statement stat =null;
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.uploadFilePath+"//RAmemberRescheduleCust.properties");
		urlsProperties.load(input);
		String member=urlsProperties.getProperty("RAmember1").trim();
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);
		stat=conn.createStatement();
		System.out.println("Connection established");
		ResultSet rs = stat.executeQuery("select SESSION_STATUS,END_DT_TIME, ROLLUP_STATUS from CALENDAR_EVENT WHERE ACCOUNT_ID IN (SELECT ID FROM ACCOUNT WHERE EMAIL LIKE '"+member+"')");
		System.out.println("query executed");
		String session_status="";
		String rollup_status="";
		String result="";
		if(rs.next())
		{
			session_status = rs.getString("SESSION_STATUS");
			rollup_status= rs.getString("ROLLUP_STATUS");
			rollup_prcs_dt_resc_cust= rs.getString("END_DT_TIME");
			System.out.println("Session status: "+session_status+" of the cliams is retrieved from the Table");
			System.out.println("Rollup status: "+rollup_status+" of the claims is retrieved from the Table");
		}
		result=session_status+","+rollup_status;
		System.out.println("result"+result);
		stat.close();
		conn.close();

		return result;
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   26/AUG/16
	 * Modified Date:
	 * Description : Create a common method to get RA member for customization session over claims workflow (Be Fit Positive scenario)
	 * Ticket ID :
	 * Required Inputs :
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String getMemFromPropertyBeFitPositive(WebDriver driver) throws IOException
	{
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.uploadFilePath+"//RAmemberBeFitPositive.properties");
		urlsProperties.load(input);
		String member=urlsProperties.getProperty("RAmember1").trim();
		return member;
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   25/AUG/16
	 * Modified Date:
	 * Description : Create a common method to validate achievements code in HFOPS portal for the workflow (Claims-)
	 * @throws SQLException
	 *@throws ClassNotFoundException
	 * @throws IOException
	 */
	public static String checkAchievementsFromHFOPSRABefitByBothActive(WebDriver driver) throws ClassNotFoundException, SQLException, IOException
	{
		System.out.print("rollup_prcs_dt_befit_positive:"+rollup_prcs_dt_befit_positive);
		String[] date=rollup_prcs_dt_befit_positive.split("\\s+");
		System.out.print("date[0]"+date[0]);
		WebElement achievments_code= driver.findElement(By.xpath("//tbody//tr//td[4][contains(text(),'"+date[0]+"')]/preceding-sibling::td[3]"));
		String achievments_code_text =achievments_code.getText();
		return achievments_code_text;
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   29/AUG/16
	 * Modified Date:
	 * Description : Create a common method to get RA member for rescheduling the customization session over claims workflow
	 * Ticket ID :
	 * Required Inputs :
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String getMemFromPropertyReschedule(WebDriver driver) throws IOException
	{
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.uploadFilePath+"//RAmemberRescheduleCust.properties");
		urlsProperties.load(input);
		String member=urlsProperties.getProperty("RAmember1").trim();
		return member;
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   29/AUG/16
	 * Modified Date:
	 * Description : Create a common method to validate achievements code in HFOPS portal for the workflow (Reschedule-Claims)
	 * @throws SQLException
	 *@throws ClassNotFoundException
	 * @throws IOException
	 */
	public static String checkAchievementsFromHFOPSReschCust(WebDriver driver) throws ClassNotFoundException, SQLException, IOException
	{
		System.out.print("rollup_prcs_dt_resc_cust:"+rollup_prcs_dt_resc_cust);
		String[] date=rollup_prcs_dt_resc_cust.split("\\s+");
		System.out.print("date[0]"+date[0]);
		WebElement achievments_code= driver.findElement(By.xpath("//tbody//tr//td[4][contains(text(),'"+date[0]+"')]/preceding-sibling::td[3]"));
		String achievments_code_text =achievments_code.getText();
		return achievments_code_text;
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   29/AUG/16
	 * Modified Date:
	 * Description : Create a common method to get RA member for customization session over claims workflow (Member opt out of the program)
	 * Ticket ID :
	 * Required Inputs :
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String getMemFromPropertyOptOfPgm(WebDriver driver) throws IOException
	{
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.uploadFilePath+"//RAMemberOpsOutOfPgm.properties");
		urlsProperties.load(input);
		String member=urlsProperties.getProperty("RAmember1").trim();
		return member;
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   25/AUG/16
	 * Modified Date:
	 * Description : Create a common method to validate achievements code in HFOPS portal for the workflow (Claims-)
	 * @throws SQLException
	 *@throws ClassNotFoundException
	 * @throws IOException
	 */
	public static String checkAchievementsFromHFOPSOptOutOfPgm(WebDriver driver) throws ClassNotFoundException, SQLException, IOException
	{
		System.out.print("rollup_prcs_dt_ops_out_pgm:"+rollup_prcs_dt_ops_out_pgm);
		String[] date=rollup_prcs_dt_ops_out_pgm.split("\\s+");
		System.out.print("date[0]"+date[0]);
		WebElement achievments_code= driver.findElement(By.xpath("//tbody//tr//td[4][contains(text(),'"+date[0]+"')]/preceding-sibling::td[3]"));
		String achievments_code_text =achievments_code.getText();
		return achievments_code_text;
	}

	/**
	 * Name :    VIGNESHRAJ.M
	 * Created Date:   05/MAY/16
	 * Modified Date:
	 * Description :   Create a common method to retrieve Allow Max enrollment is true
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String retrieveAllowMaximumEnrollmentToBeTrue(WebDriver driver) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("Select PROP_Key,Prop_Value from ORG_SETTING where organization_id='"+Directory.Client1_RA_Organization_ID+"' and PROP_Key='ALLOW_MULTIPLE_ENROLLMENTS'");
		System.out.println("query executed");
		String Prop_value="";
		if(rs.next())
		{
			Prop_value= rs.getString("PROP_VALUE");
			System.out.println("Allow Maximum enrollment status :"+Prop_value);
		}
		return Prop_value;
	}

	/**
	 * Name :    VIGNESHRAJ.M
	 * Created Date:  31/AUG/16
	 * Modified Date:
	 * Description : Create a common method to enter client name in Program admin
	 */
	public static String programAdminMemberSearchTextboxOrganizationName(WebDriver driver)
	{
		WebElement  searchTextbox = driver.findElement(By.xpath(OR.RA_PA_MEMBER_TAB_SEARCH_TEXTBOX));
		String clientName= Directory.RA_Enrollment_Organization_Name;
		sendKeys(searchTextbox, clientName);
		return clientName;
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   29/AUG/16
	 * Modified Date:
	 * Description : Create a common method to get RA member for scheduling the customization session over claims workflow
	 * Ticket ID :
	 * Required Inputs :
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String getMemFromPropertyScheduleCust(WebDriver driver) throws IOException
	{
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.uploadFilePath+"//test.properties");
		urlsProperties.load(input);
		String member=urlsProperties.getProperty("RAmember1").trim();
		return member;
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   29/AUG/16
	 * Modified Date:
	 * Description : Create a common method to validate achievements code in HFOPS portal for the workflow (Reschedule-Claims)
	 * @throws SQLException
	 *@throws ClassNotFoundException
	 * @throws IOException
	 */
	public static String checkAchievementsFromHFOPSSchCust(WebDriver driver) throws ClassNotFoundException, SQLException, IOException
	{
		String[] date=rollup_prcs_dt_sch_cust.split("\\s+");
		System.out.print("date[0]"+date[0]);
		WebElement achievments_code= driver.findElement(By.xpath("//tbody//tr//td[4][contains(text(),'"+date[0]+"')]/preceding-sibling::td[3]"));
		String achievments_code_text =achievments_code.getText();
		return achievments_code_text;
	}

	/**
	 * Name :    Vinoth.M
	 * Created Date:   8/SEP/16
	 * Modified Date:
	 * Description :   Create a common method to find member Program completed Onboarding status
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String retrieveEmailForProgramCompletedStatusandOrgIDClient1(WebDriver driver) throws ClassNotFoundException, SQLException
	{
		/*String[] testData1 = testData.split(",");
		String inputOnboardingStatus=testData1[0];
		String inputSessiontype=testData1[1]; */
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
		//ResultSet rs = stat.executeQuery("SELECT ACNT.ID, ACNT.EMAIL FROM SUMMARY_ACCOUNT_TODATE SMRY, ACCOUNT ACNT WHERE ACNT.ID= SMRY.ACCOUNT_ID AND SMRY.ONBOARDING_STATUS= '"+inputOnboardingStatus+"' and ACNT.ORGANIZATION_ID= '"+Directory.Client1_RA_Organization_ID+"'and ACNT.STATUS='Active'and SMRY.SESSION_DATE_UTC<SYSDATE AND SMRY.ACCOUNT_PROGRAM_ID IN (SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_TYPE_ID='"+inputSessiontype+"') ORDER BY SMRY.ROLLUP_PROCESS_DT DESC");

		//ResultSet rs = stat.executeQuery("select ACNT.EMAIL, SAT.PROGRAM_INTERVAL_NUMBER, SAT. ONBOARDING_STATUS from SUMMARY_ACCOUNT_TODATE SAT, ACCOUNT ACNT WHERE ACNT.ID= SAT.ACCOUNT_ID AND SAT.PROGRAM_INTERVAL_NUMBER= '52' AND SAT.ONBOARDING_STATUS= 'PROGRAM COMPLETED' AND ACNT.EMAIL NOT LIKE '%api%' AND ACNT.EMAIL  not like '%QAmember%-default@yopmail.com%' and ACNT.STATUS IN ('Active')");

		ResultSet rs = stat.executeQuery("select ACNT.EMAIL, SAT.PROGRAM_INTERVAL_NUMBER, SAT. ONBOARDING_STATUS from SUMMARY_ACCOUNT_TODATE SAT, ACCOUNT ACNT WHERE ACNT.ID= SAT.ACCOUNT_ID AND SAT.PROGRAM_INTERVAL_NUMBER= '52' AND SAT.ONBOARDING_STATUS= 'PROGRAM COMPLETED' AND ACNT.EMAIL NOT LIKE '%api%' and ACNT.EMAIL NOT LIKE 'QAmember%default@yopmail.com' and ACNT.STATUS IN ('Active')");

		System.out.println("query executed");
		String memberEmailID="";
		if(rs.next())
		{
			memberEmailID= rs.getString("EMAIL");
			System.out.println(" Member EmailID is retrieved from the Table: "+memberEmailID);
		} 
		return memberEmailID;
	}

	/**
	 * Name : Vinothkumar.M
	 * Created Date: 9/Aug/2016
	 * Modified Date:
	 * Description : Create a common method to Select Insurance Provider name dynamic to multi environment
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public  static String selectInsuranceProviderName(WebDriver driver)
	{
		jsClickByXPath(driver,OR.RA_MEMBER_INSURANCE_PROVIDER_CLICK_HERE_LINK);
		Manipulation.wait(driver, "2");
		try
		{
			WebElement  insuranceProviderHumana = driver.findElement(By.xpath(OR.RA_MEMBER_INSURANCE_PROVIDER_LIST_HUMANA));
			verifyElementIsPresent(driver, insuranceProviderHumana);
			String humana = insuranceProviderHumana.getText();
			jsClickByXPath(driver,OR.RA_MEMBER_INSURANCE_PROVIDER_LIST_HUMANA);
			Manipulation.wait(driver, "2");
			return humana;
		}
		catch(Exception e)
		{
			WebElement  insuranceProviderCenturyLinkCarrier = driver.findElement(By.xpath(OR.RA_MEMBER_INSURANCE_PROVIDER_LIST_CENTURY_LINK_CARRIER));
			verifyElementIsPresent(driver, insuranceProviderCenturyLinkCarrier);
			String centuryLinkCarrier = insuranceProviderCenturyLinkCarrier.getText();
			jsClickByXPath(driver,OR.RA_MEMBER_INSURANCE_PROVIDER_LIST_CENTURY_LINK_CARRIER);
			Manipulation.wait(driver, "2");
			return centuryLinkCarrier;
		}
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   26/AUG/16
	 * Modified Date:
	 * Description : Create a common method to get RA member for customization session over claims workflow (Member is in firt interval)
	 * Ticket ID :
	 * Required Inputs :
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String getMemFromPropertyMemberIsInFirstInt(WebDriver driver) throws IOException
	{
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.uploadFilePath+"//GroupMemberIsInFirstInterval.properties");
		urlsProperties.load(input);
		String member=urlsProperties.getProperty("RAmember1").trim();
		return member;
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   25/AUG/16
	 * Modified Date:
	 * Description : Create a common method to validate claims (Member is in first interval)
	 * @throws SQLException
	 *@throws ClassNotFoundException
	 * @throws IOException
	 */
	public static String rollup_prcs_dt_mem_isin_first=null;
	public static void checkClaimsMemberIsInFirstInt(WebDriver driver, String aten_rsn1,String weight_status1,String current_overall_status1,String current_overall_status_rsn1) throws ClassNotFoundException, SQLException, IOException
	{
		String mailid=null;
		String date=null;
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.uploadFilePath+"//GroupMemberIsInFirstInterval.properties");
		urlsProperties.load(input);
		mailid=urlsProperties.getProperty("MEMBER_ID").trim();
		date=urlsProperties.getProperty("CREATED_DATE").trim();
		String[] date1=date.split("\\s+");
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
		ResultSet rs = stat.executeQuery("SELECT AIA.INS_RULE_ID as ins_rule_id, SAI.PROGRAM_INTERVAL_START_DT as start_dt, SAI.PROGRAM_INTERVAL_END_DT as end_dt, SAI.ROLLUP_PROCESS_DT as rollup_prcs_dt, SAI.ATTEND_THIS_INTVL_STATUS_REASN as aten_rsn, SAI.WEIGHT_THIS_INTVL_STATUS as weight_status, SAI.CURRENT_OVERALL_STATUS as current_overall_status, SAI.CURRENT_OVERALL_STATUS_REASON as current_overall_status_rsn FROM ACCOUNT_INS_ACHIEVEMENT AIA, SUMMARY_ACCOUNT_INTERVAL SAI WHERE AIA.SUMMARY_ACCOUNT_INTERVAL_ID= SAI.ID AND AIA.ACCOUNT_ID in(select id from account where email like '"+mailid+"') and SAI.PROGRAM_INTERVAL_START_DT like '"+date1[0]+"%' ORDER BY SAI.PROGRAM_INTERVAL_START_DT");
		System.out.println("query executed");
		String start_dt=null;
		String end_dt=null;
		String aten_rsn=null;
		String weight_status=null;
		String current_overall_status=null;
		String current_overall_status_rsn=null;
		while(rs.next())
		{
			start_dt= rs.getString("start_dt");
			end_dt= rs.getString("end_dt");
			rollup_prcs_dt_mem_isin_first= rs.getString("rollup_prcs_dt");
			aten_rsn= rs.getString("aten_rsn");
			weight_status= rs.getString("weight_status");
			current_overall_status= rs.getString("current_overall_status");
			current_overall_status_rsn= rs.getString("current_overall_status_rsn");
		}
		if(start_dt!=null)
		{
			System.out.println("Start date is not null");
		}
		else
		{
			System.out.println("Start date is null");
			Assert.fail();
		}
		if(end_dt!=null)
		{
			System.out.println("End date is not null");
		}
		else
		{
			System.out.println("End date is null");
			Assert.fail();
		}
		if(rollup_prcs_dt_mem_isin_first!=null)
		{
			System.out.println("Rollup process date is not null");
		}
		else
		{
			System.out.println("Rollup process date is null");
			Assert.fail();
		}
		if(aten_rsn.equals(aten_rsn1))
		{
			System.out.println("Attend this interval reason matches with the input");
		}
		else
		{
			System.out.println("Attend this interval reason not matches with the input");
			Assert.fail();
		}
		if(weight_status.equals(weight_status1) )
		{
			System.out.println("Weight This Interval Status matches with the input");
		}
		else
		{
			System.out.println("Weight This Interval Status not matches with the input");
			Assert.fail();
		}
		if(current_overall_status.equals(current_overall_status1) )
		{
			System.out.println("Current overall Status matches with the input");
		}
		else
		{
			System.out.println("Current overall Status not matches with the input");
			Assert.fail();
		}
		if(current_overall_status_rsn.equals(current_overall_status_rsn1) )
		{
			System.out.println("Current overall Status reason matches with the input");
		}
		else
		{
			System.out.println("Current overall Status reason not matches with the input");
			Assert.fail();
		}
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   25/AUG/16
	 * Modified Date:
	 * Description : Create a common method to validate achievements code in HFOPS portal for the workflow (Claims-Member is in first interval)
	 * @throws SQLException
	 *@throws ClassNotFoundException
	 * @throws IOException
	 */
	public static String checkAchievementsFromHFOPSMemberIsInFirstInt(WebDriver driver) throws ClassNotFoundException, SQLException, IOException
	{
		String[] date=rollup_prcs_dt_mem_isin_first.split("\\s+");
		System.out.print("date[0]"+date[0]);
		WebElement achievments_code= driver.findElement(By.xpath("//tbody//tr//td[4][contains(text(),'"+date[0]+"')]/preceding-sibling::td[3]"));
		String achievments_code_text =achievments_code.getText();
		return achievments_code_text;
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   25/AUG/16
	 * Modified Date:
	 * Description : Create a common method to validate claims (Member is in first interval)
	 * @throws SQLException
	 *@throws ClassNotFoundException
	 * @throws IOException
	 */
	public static String rollup_prcs_dt_msd_grp_attend_1on1;
	public static void checkClaimsMissedGrpAttended1on1(WebDriver driver, String aten_rsn1,String weight_status1,String current_overall_status1,String current_overall_status_rsn1) throws ClassNotFoundException, SQLException, IOException
	{
		String mailid=null;
		String date=null;
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.uploadFilePath+"//ReportsMissedGroupAnd1on1Session.properties");
		urlsProperties.load(input);
		mailid=urlsProperties.getProperty("MEMBER_ID").trim();
		date=urlsProperties.getProperty("SMRY.CREATED_DATE").trim();
		String[] date1=date.split("\\s+");
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
		ResultSet rs = stat.executeQuery("SELECT AIA.INS_RULE_ID as ins_rule_id, SAI.PROGRAM_INTERVAL_START_DT as start_dt, SAI.PROGRAM_INTERVAL_END_DT as end_dt, SAI.ROLLUP_PROCESS_DT as rollup_prcs_dt, SAI.ATTEND_THIS_INTVL_STATUS_REASN as aten_rsn, SAI.WEIGHT_THIS_INTVL_STATUS as weight_status, SAI.CURRENT_OVERALL_STATUS as current_overall_status, SAI.CURRENT_OVERALL_STATUS_REASON as current_overall_status_rsn FROM ACCOUNT_INS_ACHIEVEMENT AIA, SUMMARY_ACCOUNT_INTERVAL SAI WHERE AIA.SUMMARY_ACCOUNT_INTERVAL_ID= SAI.ID AND AIA.ACCOUNT_ID in(select id from account where email like '"+mailid+"') and SAI.PROGRAM_INTERVAL_START_DT like '"+date1[0]+"%' ORDER BY SAI.PROGRAM_INTERVAL_START_DT");
		System.out.println("query executed");
		String start_dt=null;
		String end_dt=null;
		String aten_rsn=null;
		String weight_status=null;
		String current_overall_status=null;
		String current_overall_status_rsn=null;
		while(rs.next())
		{
			start_dt= rs.getString("start_dt");
			end_dt= rs.getString("end_dt");
			rollup_prcs_dt_msd_grp_attend_1on1= rs.getString("rollup_prcs_dt");
			aten_rsn= rs.getString("aten_rsn");
			weight_status= rs.getString("weight_status");
			current_overall_status= rs.getString("current_overall_status");
			current_overall_status_rsn= rs.getString("current_overall_status_rsn");
		}
		System.out.println("rollup_prcs_dt_msd_grp_attend_1on1"+rollup_prcs_dt_msd_grp_attend_1on1);
		if(start_dt!=null)
		{
			System.out.println("Start date is not null");
		}
		else
		{
			System.out.println("Start date is null");
			Assert.fail();
		}
		if(end_dt!=null)
		{
			System.out.println("End date is not null");
		}
		else
		{
			System.out.println("End date is null");
			Assert.fail();
		}
		if(rollup_prcs_dt_msd_grp_attend_1on1!=null)
		{
			System.out.println("Rollup process date is not null");
		}
		else
		{
			System.out.println("Rollup process date is null");
			Assert.fail();
		}
		if(aten_rsn.equals(aten_rsn1))
		{
			System.out.println("Attend this interval reason matches with the input");
		}
		else
		{
			System.out.println("Attend this interval reason not matches with the input");
			Assert.fail();
		}
		if(weight_status.equals(weight_status1))
		{
			System.out.println("Weight This Interval Status matches with the input");
		}
		else
		{
			System.out.println("Weight This Interval Status not matches with the input");
			Assert.fail();
		}
		if(current_overall_status.equals(current_overall_status1))
		{
			System.out.println("Current overall Status matches with the input");
		}
		else
		{
			System.out.println("Current overall Status not matches with the input");
			Assert.fail();
		}
		if(current_overall_status_rsn.equals(current_overall_status_rsn1))
		{
			System.out.println("Current overall Status reason matches with the input");
		}
		else
		{
			System.out.println("Current overall Status reason not matches with the input");
			Assert.fail();
		}
	}

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   26/AUG/16
	 * Modified Date:
	 * Description : Create a common method to get RA member for customization sesson over claims workflow (Missed Group and Attended 1on1 session)
	 * Ticket ID :
	 * Required Inputs :
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String getMemFromPropertyMissedGrpAttended1on1(WebDriver driver) throws IOException
	{
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.uploadFilePath+"//ReportsMissedGroupAnd1on1Session.properties");
		urlsProperties.load(input);
		String member=urlsProperties.getProperty("MEMBER_ID").trim();
		return member;
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   25/AUG/16
	 * Modified Date:
	 * Description : Create a common method to validate achievements code in HFOPS portal for the workflow (Claims-Missed Group and attended 1on1 session)
	 * @throws SQLException
	 *@throws ClassNotFoundException
	 * @throws IOException
	 * @throws java.text.ParseException
	 */
	public static String checkAchievementsFromHFOPSMissedGrpAttended1on1(WebDriver driver) throws ClassNotFoundException, SQLException, IOException, java.text.ParseException
	{
		String[] date=rollup_prcs_dt_msd_grp_attend_1on1.split(" ");
		WebElement achievments_code= driver.findElement(By.xpath("//tbody//tr//td[4][contains(text(),'"+date[0]+"')]/preceding-sibling::td[3]"));
		String achievments_code_text =achievments_code.getText();
		return achievments_code_text;
	}

	/**
	 * Name :  VigneshRaj.M
	 * Created Date:   12/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter day of DOB UseCase-1 dynamic to multi environment
	 */
	public static void useCaseOneEnterRADOBDay(WebDriver driver)
	{
		WebElement  day = driver.findElement(By.xpath(OR.RA_MEMBER_DOBDAY));
		selectByVisibletext(day, Directory.UseCaseOne_RA_Enrollment_Day);
	}

	/**
	 * Name :  VigneshRaj.M
	 * Created Date:  12/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter month of DOB UseCase-1 dynamic to multi environment
	 */
	public static void useCaseOneEnterRADOBMonth(WebDriver driver)
	{
		WebElement  month = driver.findElement(By.xpath(OR.RA_MEMBER_DOBMONTH));
		selectByVisibletext(month, Directory.UseCaseOne_RA_Enrollment_Month);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  12/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter year of DOB UseCase-1 dynamic to multi environment
	 */
	public static void useCaseOneEnterRADOBYear(WebDriver driver)
	{
		WebElement  year = driver.findElement(By.xpath(OR.RA_MEMBER_DOBYEAR));
		selectByVisibletext(year, Directory.UseCaseOne_RA_Enrollment_Year);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  12/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter last name dynamic UseCase-1 to multi environment
	 */
	public static void realAppealUseCaseOneEnterRAFirstName(WebDriver driver)
	{
		WebElement  firstname = driver.findElement(By.xpath(OR.RA_MEMBER_FIRSTNAME));
		clearAndType(firstname, Directory.UseCaseOne_RA_Enrollment_First_Name);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  12/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter last name dynamic UseCase-1 to multi environment
	 */
	public static void realAppealUseCaseOneEnterRALastName(WebDriver driver)
	{
		WebElement  lastname = driver.findElement(By.xpath(OR.RA_MEMBER_LASTNAME));
		clearAndType(lastname, Directory.UseCaseOne_RA_Enrollment_Last_Name);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  12/SEP/16
	 * Modified Date:
	 * Description : Create a common method to type Member ID UseCase-1 in RA Enrollment
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void realAppealCustomMemberID(WebDriver driver)
	{
		Manipulation.wait(driver, "2");
		WebElement member_id= driver.findElement(By.xpath(OR.RA_MEMBER_MEMBERID));
		clearAndType(member_id, Directory.UseCaseOne_RA_Enrollment_Member_Id);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  12/SEP/16
	 * Modified Date:
	 * Description : Create a common method to type Member URL UseCase-1 in RA Enrollment
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String realAppealUseCaseOneMemberURL(WebDriver driver)
	{
		try 
		{
			Navigate.get(driver, Directory.RA_UseCaseOneCustomEnroll_Member_Url);
			Manipulation.wait(driver, "2");
			com.zillion.qa.commands.Manipulation.browserURLSecurityException(driver);
		} 
		catch (Exception e1) 
		{
			com.zillion.qa.commands.Manipulation.browserURLSecurityException(driver);
		}
		String memberUrl= Directory.RA_UseCaseOneCustomEnroll_Member_Url;
		return memberUrl;
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  12/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter first name dynamic UseCase-1 to multi environment
	 */
	public static void enterUseCaseOneRAFirstNameOnInsurancePopUp(WebDriver driver)
	{
		WebElement  firstname = driver.findElement(By.xpath(OR.RA_FIRSTNAME_AFTER_INSURANCE));
		sendKeys(firstname, Directory.UseCaseOne_RA_Enrollment_First_Name);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  12/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter last name dynamic UseCase-1 to multi environment
	 */
	public static void enterUseCaseOneRALastNameOnInsurancePopUp(WebDriver driver)
	{
		WebElement  lastname = driver.findElement(By.xpath(OR.RA_LASTNAME_AFTER_INSURANCE));
		sendKeys(lastname, Directory.UseCaseOne_RA_Enrollment_Last_Name);
	}

	/**
	 * Name :  VigneshRaj.M
	 * Created Date:   12/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter day of DOB UseCase-2 dynamic to multi environment
	 */
	public static void useCaseTwoEnterRADOBDay(WebDriver driver)
	{
		WebElement  day = driver.findElement(By.xpath(OR.RA_MEMBER_DOBDAY));
		selectByVisibletext(day, Directory.UseCaseTwo_RA_Enrollment_Day);
	}

	/**
	 * Name :  VigneshRaj.M
	 * Created Date:  12/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter month of DOB UseCase-2 dynamic to multi environment
	 */
	public static void useCaseTwoEnterRADOBMonth(WebDriver driver)
	{
		WebElement  month = driver.findElement(By.xpath(OR.RA_MEMBER_DOBMONTH));
		selectByVisibletext(month, Directory.UseCaseTwo_RA_Enrollment_Month);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  12/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter year of DOB UseCase-2 dynamic to multi environment
	 */
	public static void useCaseTwoEnterRADOBYear(WebDriver driver)
	{
		WebElement  year = driver.findElement(By.xpath(OR.RA_MEMBER_DOBYEAR));
		selectByVisibletext(year, Directory.UseCaseTwo_RA_Enrollment_Year);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  12/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter last name dynamic UseCase-2 to multi environment
	 */
	public static void realAppealUseCaseTwoEnterRAFirstName(WebDriver driver)
	{
		WebElement  firstname = driver.findElement(By.xpath(OR.RA_MEMBER_FIRSTNAME));
		clearAndType(firstname, Directory.UseCaseTwo_RA_Enrollment_First_Name);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  12/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter last name dynamic UseCase-2 to multi environment
	 */
	public static void realAppealUseCaseTwoEnterRALastName(WebDriver driver)
	{
		WebElement  lastname = driver.findElement(By.xpath(OR.RA_MEMBER_LASTNAME));
		clearAndType(lastname, Directory.UseCaseTwo_RA_Enrollment_Last_Name);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  12/SEP/16
	 * Modified Date:
	 * Description : Create a common method to type Member ID UseCase-2 in RA Enrollment
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void realAppealUseCaseTwoCustomMemberID(WebDriver driver)
	{
		Manipulation.wait(driver, "2");
		WebElement member_id= driver.findElement(By.xpath(OR.RA_MEMBER_MEMBERID));
		clearAndType(member_id, Directory.UseCaseTwo_RA_Enrollment_Member_Id);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  12/SEP/16
	 * Modified Date:
	 * Description : Create a common method to type Member URL UseCase-2 in RA Enrollment
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String realAppealUseCaseTwoMemberURL(WebDriver driver)
	{
		Navigate.get(driver, Directory.RA_UseCaseTwoCustomEnroll_Member_Url);
		String memberUrl= Directory.RA_UseCaseTwoCustomEnroll_Member_Url;
		return memberUrl;
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  12/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter first name dynamic UseCase-2 to multi environment
	 */
	public static void enterUseCaseTwoRAFirstNameOnInsurancePopUp(WebDriver driver)
	{
		WebElement  firstname = driver.findElement(By.xpath(OR.RA_FIRSTNAME_AFTER_INSURANCE));
		sendKeys(firstname, Directory.UseCaseTwo_RA_Enrollment_First_Name);
	}
	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  12/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter last name dynamic UseCase-2 to multi environment
	 */
	public static void enterUseCaseTwoRALastNameOnInsurancePopUp(WebDriver driver)
	{
		WebElement  lastname = driver.findElement(By.xpath(OR.RA_LASTNAME_AFTER_INSURANCE));
		sendKeys(lastname, Directory.UseCaseTwo_RA_Enrollment_Last_Name);
	}
	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  12/SEP/16
	 * Modified Date:
	 * Description : Create a common method to type Group number UseCase-2 in RA Enrollment
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void realAppealUseCaseTwoCustomGroupNumber(WebDriver driver)
	{
		Manipulation.wait(driver, "2");
		WebElement member_id= driver.findElement(By.xpath(OR.RA_MEMBER_GROUPNUMBER));
		clearAndType(member_id, Directory.UseCaseTwo_RA_Enrollment_Group_Number);
	}

	/**
	 * Name :  VigneshRaj.M
	 * Created Date:   13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter day of DOB UseCase-3 dynamic to multi environment
	 */
	public static void useCaseThreeEnterRADOBDay(WebDriver driver)
	{
		WebElement  day = driver.findElement(By.xpath(OR.RA_MEMBER_DOBDAY));
		selectByVisibletext(day, Directory.UseCaseThree_RA_Enrollment_Day);
	}

	/**
	 * Name :  VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter month of DOB UseCase-3 dynamic to multi environment
	 */
	public static void useCaseThreeEnterRADOBMonth(WebDriver driver)
	{
		WebElement  month = driver.findElement(By.xpath(OR.RA_MEMBER_DOBMONTH));
		selectByVisibletext(month, Directory.UseCaseThree_RA_Enrollment_Month);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter year of DOB UseCase-3 dynamic to multi environment
	 */
	public static void useCaseThreeEnterRADOBYear(WebDriver driver)
	{
		WebElement  year = driver.findElement(By.xpath(OR.RA_MEMBER_DOBYEAR));
		selectByVisibletext(year, Directory.UseCaseThree_RA_Enrollment_Year);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter last name dynamic UseCase-3 to multi environment
	 */
	public static void realAppealUseCaseThreeEnterRAFirstName(WebDriver driver)
	{
		WebElement  firstname = driver.findElement(By.xpath(OR.RA_MEMBER_FIRSTNAME));
		clearAndType(firstname, Directory.UseCaseThree_RA_Enrollment_First_Name);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter last name dynamic UseCase-3 to multi environment
	 */
	public static void realAppealUseCaseThreeEnterRALastName(WebDriver driver)
	{
		WebElement  lastname = driver.findElement(By.xpath(OR.RA_MEMBER_LASTNAME));
		clearAndType(lastname, Directory.UseCaseThree_RA_Enrollment_Last_Name);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to type Member ID UseCase-3 in RA Enrollment
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void realAppealUseCaseThreeCustomMemberID(WebDriver driver)
	{
		Manipulation.wait(driver, "2");
		WebElement member_id= driver.findElement(By.xpath(OR.RA_MEMBER_MEMBERID));
		clearAndType(member_id, Directory.UseCaseThree_RA_Enrollment_Member_Id);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to type Member URL UseCase-3 in RA Enrollment
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String realAppealUseCaseThreeMemberURL(WebDriver driver)
	{
		Navigate.get(driver, Directory.RA_UseCaseThreeCustomEnroll_Member_Url);
		String memberUrl= Directory.RA_UseCaseThreeCustomEnroll_Member_Url;
		return memberUrl;

	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter first name dynamic UseCase-3 to multi environment
	 */
	public static void enterUseCaseThreeRAFirstNameOnInsurancePopUp(WebDriver driver)
	{
		WebElement  firstname = driver.findElement(By.xpath(OR.RA_FIRSTNAME_AFTER_INSURANCE));
		sendKeys(firstname, Directory.UseCaseThree_RA_Enrollment_First_Name);
	}
	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter last name dynamic UseCase-3 to multi environment
	 */
	public static void enterUseCaseThreeRALastNameOnInsurancePopUp(WebDriver driver)
	{
		WebElement  lastname = driver.findElement(By.xpath(OR.RA_LASTNAME_AFTER_INSURANCE));
		sendKeys(lastname, Directory.UseCaseThree_RA_Enrollment_Last_Name);
	}
	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to type Group number UseCase-3 in RA Enrollment
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void realAppealUseCaseThreeCustomGroupNumber(WebDriver driver)
	{
		Manipulation.wait(driver, "2");
		WebElement member_id= driver.findElement(By.xpath(OR.RA_MEMBER_GROUPNUMBER));
		clearAndType(member_id, Directory.UseCaseThree_RA_Enrollment_Group_Number);
	}

	/**
	 * Name :  VigneshRaj.M
	 * Created Date:   13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter day of DOB UseCase-4 UnitedHeath dynamic to multi environment
	 */
	public static void useCaseFourUnitedHeathEnterRADOBDay(WebDriver driver)
	{
		WebElement  day = driver.findElement(By.xpath(OR.RA_MEMBER_DOBDAY));
		selectByVisibletext(day, Directory.UseCaseFourUnitedHeath_RA_Enrollment_Day);
	}

	/**
	 * Name :  VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter month of DOB UseCase-4 UnitedHeath dynamic to multi environment
	 */
	public static void useCaseFourUnitedHeathEnterRADOBMonth(WebDriver driver)
	{
		WebElement  month = driver.findElement(By.xpath(OR.RA_MEMBER_DOBMONTH));
		selectByVisibletext(month, Directory.UseCaseFourUnitedHeath_RA_Enrollment_Month);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter year of DOB UseCase-4 UnitedHeath dynamic to multi environment
	 */
	public static void useCaseFourUnitedHeathEnterRADOBYear(WebDriver driver)
	{
		WebElement  year = driver.findElement(By.xpath(OR.RA_MEMBER_DOBYEAR));
		selectByVisibletext(year, Directory.UseCaseFourUnitedHeath_RA_Enrollment_Year);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter last name dynamic UseCase-4 UnitedHeath to multi environment
	 */
	public static void realAppealUseCaseFourUnitedHeathEnterRAFirstName(WebDriver driver)
	{
		WebElement  firstname = driver.findElement(By.xpath(OR.RA_MEMBER_FIRSTNAME));
		clearAndType(firstname, Directory.UseCaseFourUnitedHeath_RA_Enrollment_First_Name);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter last name dynamic UseCase-4 UnitedHeath to multi environment
	 */
	public static void realAppealUseCaseFourUnitedHeathEnterRALastName(WebDriver driver)
	{
		WebElement  lastname = driver.findElement(By.xpath(OR.RA_MEMBER_LASTNAME));
		clearAndType(lastname, Directory.UseCaseFourUnitedHeath_RA_Enrollment_Last_Name);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to type Member ID UseCase-4 UnitedHeath in RA Enrollment
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void realAppealUseCaseFourUnitedHeathCustomMemberID(WebDriver driver)
	{
		Manipulation.wait(driver, "2");
		WebElement member_id= driver.findElement(By.xpath(OR.RA_MEMBER_MEMBERID));
		clearAndType(member_id, Directory.UseCaseFourUnitedHeath_RA_Enrollment_Member_Id);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to type Member URL UseCase-4 UnitedHeath in RA Enrollment
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String realAppealUseCaseUnitedHeathFourMemberURL(WebDriver driver)
	{

		try 
		{
			driver.navigate().to(Directory.RA_UseCaseFourUnitedHeathCustomEnroll_Member_Url);
			Manipulation.wait(driver, "2");
			com.zillion.qa.commands.Manipulation.browserURLSecurityException(driver);
		} 
		catch (Exception e1) 
		{
			com.zillion.qa.commands.Manipulation.browserURLSecurityException(driver);
		}
		String memberUrl= Directory.RA_UseCaseFourUnitedHeathCustomEnroll_Member_Url;
		return memberUrl;
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter first name dynamic UseCase-4 UnitedHeath to multi environment
	 */
	public static void enterUseCaseFourUnitedHeathRAFirstNameOnInsurancePopUp(WebDriver driver)
	{
		WebElement  firstname = driver.findElement(By.xpath(OR.RA_FIRSTNAME_AFTER_INSURANCE));
		sendKeys(firstname, Directory.UseCaseFourUnitedHeath_RA_Enrollment_First_Name);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter last name dynamic UseCase-4 UnitedHeath to multi environment
	 */
	public static void enterUseCaseFourUnitedHeathRALastNameOnInsurancePopUp(WebDriver driver)
	{
		WebElement  lastname = driver.findElement(By.xpath(OR.RA_LASTNAME_AFTER_INSURANCE));
		sendKeys(lastname, Directory.UseCaseFourUnitedHeath_RA_Enrollment_Last_Name);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to type Group number UseCase-4 UnitedHeath in RA Enrollment
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void realAppealUseCaseUnitedHeathFourCustomGroupNumber(WebDriver driver)
	{
		Manipulation.wait(driver, "2");
		WebElement member_id= driver.findElement(By.xpath(OR.RA_MEMBER_GROUPNUMBER));
		clearAndType(member_id, Directory.UseCaseFourUnitedHeath_RA_Enrollment_Group_Number);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:   13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter insurance details UseCase-4 UnitedHeath in RA Enrollment
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void realAppealUseCaseFourUnitedHeathInsuranceCarrierSelect(WebDriver driver)
	{
		Manipulation.wait(driver, "2");
		WebElement insurance_provider= driver.findElement(By.xpath(OR.RA_MEMBER_INSURANCEPROVIDER));
		clearAndType(insurance_provider, Directory.UseCaseFourUnitedHeath_RA_Enrollment_Insurance_Provider);
		WebElement autosuggest= driver.findElement(By.xpath(OR.RA_INSURANCE_AUTO_SUGGEST));
		Manipulation.wait(driver, "2");
		waitForElement(driver, OR.RA_INSURANCE_AUTO_SUGGEST);
		actionClick(driver, autosuggest);
	}

	/**
	 * Name :  VigneshRaj.M
	 * Created Date:   14/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter day of DOB UseCase-4 Client-2 dynamic to multi environment
	 */
	public static void useCaseFourClient2EnterRADOBDay(WebDriver driver)
	{
		WebElement  day = driver.findElement(By.xpath(OR.RA_MEMBER_DOBDAY));
		selectByVisibletext(day, Directory.UseCaseFourClient2_RA_Enrollment_Day);
	}

	/**
	 * Name :  VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter month of DOB UseCase-4 Client2 dynamic to multi environment
	 */
	public static void useCaseFourClient2EnterRADOBMonth(WebDriver driver)
	{
		WebElement  month = driver.findElement(By.xpath(OR.RA_MEMBER_DOBMONTH));
		selectByVisibletext(month, Directory.UseCaseFourClient2_RA_Enrollment_Month);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter year of DOB UseCase-4 Client2 dynamic to multi environment
	 */
	public static void useCaseFourClient2EnterRADOBYear(WebDriver driver)
	{
		WebElement  year = driver.findElement(By.xpath(OR.RA_MEMBER_DOBYEAR));
		selectByVisibletext(year, Directory.UseCaseFourClient2_RA_Enrollment_Year);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter last name dynamic UseCase-4 Client2 to multi environment
	 */
	public static void realAppealUseCaseFourClient2EnterRAFirstName(WebDriver driver)
	{
		WebElement  firstname = driver.findElement(By.xpath(OR.RA_MEMBER_FIRSTNAME));
		clearAndType(firstname, Directory.UseCaseFourClient2_RA_Enrollment_First_Name);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter last name dynamic UseCase-4 Client2 to multi environment
	 */
	public static void realAppealUseCaseFourClient2EnterRALastName(WebDriver driver)
	{
		WebElement  lastname = driver.findElement(By.xpath(OR.RA_MEMBER_LASTNAME));
		clearAndType(lastname, Directory.UseCaseFourClient2_RA_Enrollment_Last_Name);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to type Member ID UseCase-4 Client2 in RA Enrollment
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void realAppealUseCaseFourClient2CustomMemberID(WebDriver driver)
	{
		Manipulation.wait(driver, "2");
		WebElement member_id= driver.findElement(By.xpath(OR.RA_MEMBER_MEMBERID));
		clearAndType(member_id, Directory.UseCaseFourClient2_RA_Enrollment_Member_Id);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to type Member URL UseCase-4 Client2 in RA Enrollment
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String realAppealUseCaseClient2FourMemberURL(WebDriver driver)
	{
		try 
		{
			driver.navigate().to(Directory.RA_UseCaseFourClient2CustomEnroll_Member_Url);
			Manipulation.wait(driver, "2");
			com.zillion.qa.commands.Manipulation.browserURLSecurityException(driver);
		} 
		catch (Exception e1) 
		{
			com.zillion.qa.commands.Manipulation.browserURLSecurityException(driver);
		}
		String memberUrl= Directory.RA_UseCaseFourClient2CustomEnroll_Member_Url;
		return memberUrl;
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter first name dynamic UseCase-4 Client2 to multi environment
	 */
	public static void enterUseCaseFourClient2RAFirstNameOnInsurancePopUp(WebDriver driver)
	{
		WebElement  firstname = driver.findElement(By.xpath(OR.RA_FIRSTNAME_AFTER_INSURANCE));
		sendKeys(firstname, Directory.UseCaseFourClient2_RA_Enrollment_First_Name);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter last name dynamic UseCase-4 Client2 to multi environment
	 */
	public static void enterUseCaseFourClient2RALastNameOnInsurancePopUp(WebDriver driver)
	{
		WebElement  lastname = driver.findElement(By.xpath(OR.RA_LASTNAME_AFTER_INSURANCE));
		sendKeys(lastname, Directory.UseCaseFourClient2_RA_Enrollment_Last_Name);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to type Group number UseCase-4 Client2 in RA Enrollment
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void realAppealUseCaseClient2FourCustomGroupNumber(WebDriver driver)
	{
		Manipulation.wait(driver, "2");
		WebElement member_id= driver.findElement(By.xpath(OR.RA_MEMBER_GROUPNUMBER));
		clearAndType(member_id, Directory.UseCaseFourClient2_RA_Enrollment_Group_Number);

	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:   13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter insurance details UseCase-4 Client2 in RA Enrollment
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void realAppealUseCaseFourClient2InsuranceCarrierSelect(WebDriver driver)
	{
		Manipulation.wait(driver, "2");
		WebElement insurance_provider= driver.findElement(By.xpath(OR.RA_MEMBER_INSURANCEPROVIDER));
		clearAndType(insurance_provider, Directory.UseCaseFourClient2_RA_Enrollment_Insurance_Provider);
		WebElement autosuggest= driver.findElement(By.xpath(OR.RA_INSURANCE_AUTO_SUGGEST));
		Manipulation.wait(driver, "2");
		waitForElement(driver, OR.RA_INSURANCE_AUTO_SUGGEST);
		actionClick(driver, autosuggest);
	}

	/**
	 * Name :  VigneshRaj.M
	 * Created Date:   14/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter day of DOB UseCase-4 Client-3 dynamic to multi environment
	 */
	public static void useCaseFourClient3EnterRADOBDay(WebDriver driver)
	{
		WebElement  day = driver.findElement(By.xpath(OR.RA_MEMBER_DOBDAY));
		selectByVisibletext(day, Directory.UseCaseFourClient3_RA_Enrollment_Day);
	}

	/**
	 * Name :  VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter month of DOB UseCase-4 Client3 dynamic to multi environment
	 */
	public static void useCaseFourClient3EnterRADOBMonth(WebDriver driver)
	{
		WebElement  month = driver.findElement(By.xpath(OR.RA_MEMBER_DOBMONTH));
		selectByVisibletext(month, Directory.UseCaseFourClient3_RA_Enrollment_Month);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter year of DOB UseCase-4 Client3 dynamic to multi environment
	 */
	public static void useCaseFourClient3EnterRADOBYear(WebDriver driver)
	{
		WebElement  year = driver.findElement(By.xpath(OR.RA_MEMBER_DOBYEAR));
		selectByVisibletext(year, Directory.UseCaseFourClient3_RA_Enrollment_Year);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter last name dynamic UseCase-4 Client3 to multi environment
	 */
	public static void realAppealUseCaseFourClient3EnterRAFirstName(WebDriver driver)
	{
		WebElement  firstname = driver.findElement(By.xpath(OR.RA_MEMBER_FIRSTNAME));
		clearAndType(firstname, Directory.UseCaseFourClient3_RA_Enrollment_First_Name);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter last name dynamic UseCase-4 Client3 to multi environment
	 */
	public static void realAppealUseCaseFourClient3EnterRALastName(WebDriver driver)
	{
		WebElement  lastname = driver.findElement(By.xpath(OR.RA_MEMBER_LASTNAME));
		clearAndType(lastname, Directory.UseCaseFourClient3_RA_Enrollment_Last_Name);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to type Member ID UseCase-4 Client3 in RA Enrollment
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void realAppealUseCaseFourClient3CustomMemberID(WebDriver driver)
	{
		Manipulation.wait(driver, "2");
		WebElement member_id= driver.findElement(By.xpath(OR.RA_MEMBER_MEMBERID));
		clearAndType(member_id, Directory.UseCaseFourClient3_RA_Enrollment_Member_Id);

	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to type Member URL UseCase-4 Client3 in RA Enrollment
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String realAppealUseCaseClient3FourMemberURL(WebDriver driver)
	{
		try 
		{
			driver.navigate().to(Directory.RA_UseCaseFourClient3CustomEnroll_Member_Url);
			Manipulation.wait(driver, "2");
			com.zillion.qa.commands.Manipulation.browserURLSecurityException(driver);
		} 
		catch (Exception e1) 
		{
			com.zillion.qa.commands.Manipulation.browserURLSecurityException(driver);
		}
		String memberUrl= Directory.RA_UseCaseFourClient3CustomEnroll_Member_Url;
		return memberUrl;
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter first name dynamic UseCase-4 Client3 to multi environment
	 */
	public static void enterUseCaseFourClient3RAFirstNameOnInsurancePopUp(WebDriver driver)
	{
		WebElement  firstname = driver.findElement(By.xpath(OR.RA_FIRSTNAME_AFTER_INSURANCE));
		sendKeys(firstname, Directory.UseCaseFourClient3_RA_Enrollment_First_Name);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter last name dynamic UseCase-4 Client3 to multi environment
	 */
	public static void enterUseCaseFourClient3RALastNameOnInsurancePopUp(WebDriver driver)
	{
		WebElement  lastname = driver.findElement(By.xpath(OR.RA_LASTNAME_AFTER_INSURANCE));
		sendKeys(lastname, Directory.UseCaseFourClient3_RA_Enrollment_Last_Name);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:  13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to type Group number UseCase-4 Client3 in RA Enrollment
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void realAppealUseCaseClient3FourCustomGroupNumber(WebDriver driver)
	{
		Manipulation.wait(driver, "2");
		WebElement member_id= driver.findElement(By.xpath(OR.RA_MEMBER_GROUPNUMBER));
		clearAndType(member_id, Directory.UseCaseFourClient3_RA_Enrollment_Group_Number);
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:   13/SEP/16
	 * Modified Date:
	 * Description : Create a common method to enter insurance details UseCase-4 Client2 in RA Enrollment
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void realAppealUseCaseFourClient3InsuranceCarrierSelect(WebDriver driver)
	{
		Manipulation.wait(driver, "2");
		WebElement insurance_provider= driver.findElement(By.xpath(OR.RA_MEMBER_INSURANCEPROVIDER));
		clearAndType(insurance_provider, Directory.UseCaseFourClient3_RA_Enrollment_Insurance_Provider);
		WebElement autosuggest= driver.findElement(By.xpath(OR.RA_INSURANCE_AUTO_SUGGEST));
		Manipulation.wait(driver, "2");
		waitForElement(driver, OR.RA_INSURANCE_AUTO_SUGGEST);
		actionClick(driver, autosuggest);
	}

	/**
	 * Created by Suresh V
	 */
	public static String realAppealMemberTrackerAddcaloriesValues(WebDriver driver)
	{
		int count=driver.findElements(By.xpath("//table//following::tbody[@id='Trackers_Exercise_Container']/tr[*]/td[4]")).size();
		System.out.println(count);

		int total=0;
		String totalcaloriesvalue=null;
		for (int i=1;i<=count;i++)
		{

			String calories=driver.findElement(By.xpath("//table//following::tbody[@id='Trackers_Exercise_Container']/tr["+i+"]/td[4]")).getText();

			int caloriesvalue = Integer.parseInt(calories);
			total=total+caloriesvalue;
			System.out.println(caloriesvalue);
			System.out.println("Total Calories:"+total);
			int totalcalories=(total/7);
			System.out.println("Total Calories value:"+totalcalories);
			totalcaloriesvalue = Integer.toString(totalcalories);

		}
		return totalcaloriesvalue;

	}

	/**
	 * Created by Suresh V
	 * This method for capture target value in member-Tracker-food subtab
	 * Usecase name:RA_Tracker_MyPlanNutritionPlanning
	 * @param driver
	 * @return
	 */
	public static int memberTrackerCompareDailyCalorie(WebDriver driver,String targetValue)
	{
		String target=driver.findElement(By.xpath("//div[@id='progress_update']/*[local-name()='svg']/*[local-name()='g']/*[local-name()='text'][@class='targetlabel']")).getText();
		System.out.println(target);
		String[] foodTabTarget = target.split("TARGET:");
		String getFoodTabTarget= foodTabTarget[1];
		String actualFoodTabTarget=getFoodTabTarget.trim();
		System.out.println("Target="+actualFoodTabTarget);
		int TargetValue = Integer.parseInt(actualFoodTabTarget);
		return TargetValue;
	}

	/**
	 * Created by Suresh V
	 * This common method for get starting weight and add more than 110Lbs in member-my plan tab
	 * RA_Tracker_MyPlanNavigatingToTheMyPlanTab
	 * @param driver
	 */
	public static String memberStartingWeight(WebDriver driver)
	{
		int targetInput=110;
		String startingWeight=driver.findElement(By.xpath("//div[contains(text(),'Starting Weight')]/following-sibling::div[contains(text(),'')]")).getText();
		System.out.println("Starting Weight="+startingWeight);
		float convertStringtoFloat=Float.valueOf(startingWeight);
		float targetWeight=convertStringtoFloat+targetInput;
		String inputTargetWeight=null;
		inputTargetWeight=Float.toString(targetWeight);
		return inputTargetWeight;
	}

	/**
	 * Name :      VigneshRaj.M
	 * Created Date:   20/SEP/16
	 * Modified Date:
	 * Description : Create a common method to delete the saved targets
	 * Ticket ID :
	 *
	 */
	public static void deleteAllSavedExercise(WebDriver driver)
	{
		try
		{
			wait(driver, "2");
			int deletesavedExercises1 = driver.findElements(By.xpath("//tbody[@id='Trackers_Exercise_Container']/tr[*]/td[2]/i")).size();
			System.out.println(deletesavedExercises1);
			for(int i=1; i<=deletesavedExercises1;i++)
			{
				wait(driver, "2");
				WebElement deletesavedExercises2 = driver.findElement(By.xpath("//tbody[@id='Trackers_Exercise_Container']/tr/td[2]/i"));
				click(deletesavedExercises2);
				wait(driver, "2");
			}
		}
		catch(Exception e)
		{
		}

	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   24/OCT/16
	 * Modified Date:
	 * Description : Create a common method to retrieve member which has member request
	 * @throws SQLException
	 *@throws ClassNotFoundException
	 * @throws IOException
	 */
	public static String pickmemberRequest(WebDriver driver) throws ClassNotFoundException, SQLException, IOException
	{
		String name=null;
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
		ResultSet rs = stat.executeQuery("SELECT SUPPLIED_NAME, SUPPLIED_EMAIL, REASON FROM ACCOUNT_CASE WHERE TYPE= 'MEMBER REQUEST' AND STATUS= 'Open' order by created_dt desc");
		System.out.println("query executed");
		while(rs.next())
		{
			name= rs.getString("SUPPLIED_NAME");
		}
		return name;
	}

	/**
	 * Name : P.Abinaya
	 * Created Date: 04/Nov/2016
	 * Description : Retrieve mail id of the member which is active
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String getActiveRAMember(WebDriver driver) throws ClassNotFoundException, SQLException, IOException
	{
		Connection conn =null;
		Statement stat =null;
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);
		stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select A.EMAIL from ACCOUNT A, SUMMARY_ACCOUNT_TODATE SATD where A.ID= SATD.ACCOUNT_ID AND SATD.MAST_PROGRAM_ID= '02' AND SATD.ONBOARDING_STATUS= 'MEMBER ONBOARDED' AND A.Status='Active' AND A.EMAIL not like '%QAmember%-default@yopmail.com%'  AND A.EMAIL not LIKE '%api%' ORDER BY SATD.CREATED_DT");
		System.out.println(" query executed");
		String email="";
		if(rs.next())
		{
			email = rs.getString("EMAIL");
		}
		stat.close();
		conn.close();
		return email;
	}

	/**
	 * Name :     Abinaya
	 * Created Date:   31/Mar/16
	 * Description :   Create a common method to format DOB from the member profile of the HFOPS
	 * Testcase sheet:SystemUser_Member_INThePAMemberProfileChangeTheDOBAndCheckIfTheDOBIsReflectedInThePACoachMemberDetailsAndInThePAProfileUnderPersonalInfo
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String OpsAdmin_DOB;
	public static String Member_DOB;
	public static String memberProfileDOB(WebDriver driver) throws java.text.ParseException
	{
		String dob = driver.findElement(By.xpath(OR.RA_DOB)).getText();
		Navigate.waitTime(driver, "10");
		SimpleDateFormat sm = new SimpleDateFormat("MMM dd, yyyy");
		//String date = sm.format(dob);
		Date convertedDate =sm.parse(dob);
		System.out.println(convertedDate+"convertedDate");
		SimpleDateFormat sm1 = new SimpleDateFormat("MM/dd/yyyy");
		Member_DOB= sm1.format(convertedDate);
		System.out.println(Member_DOB);
		return Member_DOB;
	}

	/**
	 * Name :     Suresh.V
	 * Created Date:   31/Mar/16
	 * Modified Date:
	 * Description :   Create a common method to format DOB of the member profile
	 * Ticket ID :
	 * Testcase sheet:
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String opsAdminDOB(WebDriver driver)
	{
		WebElement profile_day = driver.findElement(By.xpath(OR.MEMBER_PROFILE_DAY));
		WebElement profile_month = driver.findElement(By.xpath(OR. MEMBER_PROFILE_MONTH));
		WebElement profile_year = driver.findElement(By.xpath(OR. MEMBER_PROFILE_YEAR));
		WebElement profile_save_button = driver.findElement(By.xpath(OR.MEMBER_PROFILE_DOB_SAVE));
		WebElement profile_day_select = driver.findElement(By.xpath(OR.MEMBER_PROFILE_DAY1));
		WebElement profile_month_select = driver.findElement(By.xpath(OR. MEMBER_PROFILE_MONTH1));
		WebElement profile_year_select = driver.findElement(By.xpath(OR. MEMBER_PROFILE_YEAR1));
		click(profile_day);
		click(profile_day_select);
		String day=profile_day_select.getText();
		Navigate.waitTime(driver, "10");
		click(profile_month);
		click(profile_month_select);
		String month=profile_month_select.getText();
		Navigate.waitTime(driver, "10");
		click(profile_year);
		click(profile_year_select);
		String year=profile_year_select.getText();
		Navigate.waitTime(driver, "10");
		wait(driver, "3");
		click(profile_save_button);
		Navigate.waitTime(driver, "10");
		String OpsAdmin_DOB = month+"/"+day+"/"+year;
		System.out.println(OpsAdmin_DOB);
		return OpsAdmin_DOB;
	}

	/**
	 * Name :     Suresh.v
	 * Created Date:   31/Mar/16
	 * Modified Date:
	 * Description :   Create a common method to compare both the formatted DOB from the member profile
	 * Ticket ID :
	 * Testcase sheet:
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String compareDOB(WebDriver driver)
	{
		if(OpsAdmin_DOB.equals(Member_DOB))
		{
			return "OpsAdmin edited DOB and Member profile DOB are matched";
		}
		else
		{
			return "OpsAdmin edited DOB and Member profile DOB are not matched";
		}
	}

	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:   21/NOV/16
	 * Modified Date:
	 * Description :   Create a common method to scheduled OneOnOne StartAndEndtime
	 * Ticket ID :
	 * Required Inputs :  Mail
	 *
	 */
	public static String realAppealScheduledOneOnOneStartTime(WebDriver driver,String Starttime)
	{
		String[] Starttime1=Starttime.split(" ");
		String Starttime2=Starttime1[7];
		System.out.println("Print2: "+Starttime2);
		String Starttime3=Starttime2.toLowerCase().trim();
		System.out.println("Print2: "+Starttime3);
		String Sessiontime21= Starttime3.substring(0, 4).trim();
		String Sessiontime22= Starttime3.substring(4, 6).trim();
		String Sessiontime23=Sessiontime21+" "+Sessiontime22;
		return Sessiontime23;
	}

	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:   21/NOV/16
	 * Modified Date:
	 * Description : Create a common method to Cancel session For Scheduled OneOnOne session
	 * Ticket ID :
	 * Required Inputs :  Mail
	 *
	 */
	public static String cancelSessionForScheduledOneOnOne(WebDriver driver,String Starttime5)
	{
		String ActualTime ="";
		String[] Starttime6=Starttime5.split(",");
		String FullStarttime=Starttime6[2];
		System.out.println("Full start time"+FullStarttime);

		String ActualTimeinEDT[] = FullStarttime.trim().split(" ");
		System.out.println("start time without EDT: "+ActualTimeinEDT[0]);
		ActualTime = ActualTimeinEDT[0].substring(0, (ActualTimeinEDT[0].length()-2));


		/*
		String[] Starttime2=Starttime5.split(" ");
		String Starttime3=Starttime2[7];
		String Starttime4=Starttime3.toLowerCase().trim();
		String Sessiontime21= Starttime4.substring(0, 2);
		String Sessiontime22= Starttime4.substring(2, 6);
		String Sessiontime23=Sessiontime21+" "+Sessiontime22;
		System.out.println("Starttime: "+Sessiontime23);

		if(!Sessiontime21.equalsIgnoreCase("10")&&!Sessiontime21.equalsIgnoreCase("11")&&!Sessiontime21.equalsIgnoreCase("12"))
		{
			String Sessiontime24= Sessiontime21.substring(0).replace("0", "");
			System.out.println("1: "+Sessiontime24);
			String Sessiontime25= Starttime4.substring(2, 4);
			System.out.println("2: "+Sessiontime25);
			String Sessiontime26= Starttime4.substring(4, 6);
			String combinedString= Sessiontime24+Sessiontime25+" "+Sessiontime26;
			System.out.println("3: "+combinedString);
			WebElement cancelSessionButton = driver.findElement(By.xpath(OR.CANCEL_SESSION_FOR_SCHEDULED_ONE_ON_ONE_START_OR+combinedString+CANCEL_SESSION_FOR_SCHEDULED_ONE_ON_ONE_END_OR));
			verifyElementIsPresent(driver, cancelSessionButton);
			jsClickByXPath(driver, OR.CANCEL_SESSION_FOR_SCHEDULED_ONE_ON_ONE_START_OR+combinedString+CANCEL_SESSION_FOR_SCHEDULED_ONE_ON_ONE_END_OR);
			wait(driver, "15");
			return combinedString;
		}
		else
		{*/
		WebElement cancelSessionButton = driver.findElement(By.xpath(OR.CANCEL_SESSION_FOR_SCHEDULED_ONE_ON_ONE_START_OR+ActualTime+CANCEL_SESSION_FOR_SCHEDULED_ONE_ON_ONE_END_OR));
		verifyElementIsPresent(driver, cancelSessionButton);
		jsClickByXPath(driver, OR.CANCEL_SESSION_FOR_SCHEDULED_ONE_ON_ONE_START_OR+ActualTime+CANCEL_SESSION_FOR_SCHEDULED_ONE_ON_ONE_END_OR);
		wait(driver, "15");
		return ActualTime;
		//}
	}

	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:   21/NOV/16
	 * Modified Date:
	 * Description :   Create a common method to scheduled OneOnOne Starttime
	 * Ticket ID :
	 * Required Inputs :  Mail
	 *
	 */
	public static String raCoachScheduledOneOnOneStartTime(WebDriver driver,String Starttime2)
	{
		String[] Starttime3=Starttime2.split(" ");
		String Sessiontime4=Starttime3[1];
		String Sessiontime5=Starttime3[2];
		System.out.println("Print2: "+Sessiontime4);
		String Sessiontime6=Sessiontime4+" "+Sessiontime5;
		return Sessiontime6;
	}

	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:   21/NOV/16
	 * Modified Date:
	 * Description :   Create a common method to scheduled OneOnOne Endtime
	 * Ticket ID :
	 * Required Inputs :  Mail
	 *
	 */
	public static String raCoachScheduledOneOnOneEndTime(WebDriver driver,String Starttime4)
	{
		String[] Starttime5=Starttime4.split(" ");
		String Sessiontime6=Starttime5[1];
		String Sessiontime7=Starttime5[2];
		System.out.println("Print2: "+Sessiontime6);
		String Sessiontime8=Sessiontime6+" "+Sessiontime7;
		return Sessiontime8;
	}

	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:   11/NOV/16
	 * Modified Date:
	 * Description : Create a common method to Reschedule option
	 * Ticket ID :
	 * Required Inputs :  Mail
	 *
	 */
	public static String realappealSessionConflictsScheduledOneOnOneRescheduleOption(WebDriver driver,String ScheduleTime5)
	{
		String[] ScheduleTime6=ScheduleTime5.split(" ");
		String ScheduleTime7=ScheduleTime6[7];
		System.out.println("Print2: "+ScheduleTime7);
		String ScheduleTime8=ScheduleTime7.toLowerCase().trim();
		WebElement rescheduleOption = driver.findElement(By.xpath(OR.SESSION_CONFLICTS_RESCHEDULE_OPTION_START_OR+ScheduleTime8+SESSION_CONFLICTS_RESCHEDULE_OPTION_END_OR));
		verifyElementIsPresent(driver, rescheduleOption);
		jsClickByXPath(driver, OR.SESSION_CONFLICTS_RESCHEDULE_OPTION_START_OR+ScheduleTime8+SESSION_CONFLICTS_RESCHEDULE_OPTION_END_OR);
		wait(driver, "5");
		return ScheduleTime8;
	}

	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:   11/NOV/16
	 * Modified Date:
	 * Description : Create a common method to Cancel OneOnOne session
	 * Ticket ID :
	 * Required Inputs :  Mail
	 *
	 */
	public static String realappealSessionConflictsScheduledOneOnOneCancelOption(WebDriver driver,String ScheduleTime6)
	{
		String[] ScheduleTime7=ScheduleTime6.split(" ");
		String ScheduleTime8=ScheduleTime7[7];
		System.out.println("Print2: "+ScheduleTime8);
		String ScheduleTime9=ScheduleTime8.toLowerCase().trim();
		WebElement cancelOption = driver.findElement(By.xpath(OR.SESSION_CONFLICTS_CANCEL_OPTION_START_OR+ScheduleTime9+SESSION_CONFLICTS_CANCEL_OPTION_END_OR));
		verifyElementIsPresent(driver, cancelOption);
		jsClickByXPath(driver, OR.SESSION_CONFLICTS_CANCEL_OPTION_START_OR+ScheduleTime9+SESSION_CONFLICTS_CANCEL_OPTION_END_OR);
		wait(driver, "5");
		return ScheduleTime9;
	}

	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:   21/NOV/16
	 * Modified Date:
	 * Description :   Create a common method to scheduled Group session Starttime
	 * Ticket ID :
	 * Required Inputs :  Mail
	 *
	 */
	public static String raCoachScheduledGroupSessionStartAndEndTime(WebDriver driver,String Starttime3)
	{
		String[] Starttime4=Starttime3.split(" ");
		String Sessiontime6=Starttime4[1];
		System.out.println("Print2: "+Sessiontime6);
		String Sessiontime21= Sessiontime6.substring(0, 2).trim();
		String Sessiontime22= Sessiontime6.substring(2, 6);
		String Sessiontime23=Sessiontime21+" "+Sessiontime22;
		System.out.println("Starttime: "+Sessiontime21);

		if(!Sessiontime21.equalsIgnoreCase("10")&&!Sessiontime21.equalsIgnoreCase("11")&&!Sessiontime21.equalsIgnoreCase("12"))
		{
			String Sessiontime24= Sessiontime21.substring(0).replace("0", "");
			System.out.println("1: "+Sessiontime24);
			String Sessiontime25= Sessiontime6.substring(2, 4);
			System.out.println("2: "+Sessiontime25);

			String Sessiontime26= Sessiontime6.substring(4, 6);
			String combinedString= Sessiontime24+Sessiontime25+" "+Sessiontime26;
			System.out.println("3: "+combinedString);
			return combinedString;
		}
		else if(Sessiontime21.equalsIgnoreCase("10")||Sessiontime21.equalsIgnoreCase("11")||Sessiontime21.equalsIgnoreCase("12"))
		{
			String Sessiontime26= Sessiontime6.substring(0, 2);
			String Sessiontime27= Sessiontime6.substring(2, 5);
			String Sessiontime28= Sessiontime6.substring(5, 7);
			String Sessiontime29=Sessiontime26+Sessiontime27+" "+Sessiontime28;
			System.out.println("44: "+Sessiontime29);
			return Sessiontime29;
		}
		return Sessiontime23;
	}

	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:   23/NOV/16
	 * Modified Date:
	 * Description : Create a common method to AssignSubtituteCoach For Scheduled Group session
	 * Ticket ID :
	 * Required Inputs :  Mail
	 *
	 */
	public static String assignSubtituteCoachForScheduledGroupSession(WebDriver driver,String ScheduleTime8)
	{
		String[] ScheduleTime9=ScheduleTime8.split(" ");
		String ScheduleTime10=ScheduleTime9[1];
		System.out.println("Print2: "+ScheduleTime10);
		String ScheduleTime11= ScheduleTime10.substring(0, 2);
		String ScheduleTime12= ScheduleTime10.substring(2, 6);

		if(ScheduleTime11!="10"&&ScheduleTime11!="11"&&ScheduleTime11!="12")
		{
			String ScheduleTime13= ScheduleTime11.substring(0).replace("0", "");
			String combinedString= ScheduleTime13+ScheduleTime12;
			WebElement substituteCoachButton = driver.findElement(By.xpath(OR.ASSIGNED_COACH_SUBSTITUTE_FOR_SCHEDULED_GROUP_START_OR+combinedString+ASSIGNED_COACH_SUBSTITUTE_FOR_SCHEDULED_GROUP_END_OR));
			verifyElementIsPresent(driver, substituteCoachButton);
			jsClickByXPath(driver, OR.ASSIGNED_COACH_SUBSTITUTE_FOR_SCHEDULED_GROUP_START_OR+combinedString+ASSIGNED_COACH_SUBSTITUTE_FOR_SCHEDULED_GROUP_END_OR);
			wait(driver, "5");
			return combinedString;
		}
		WebElement substituteCoachButton = driver.findElement(By.xpath(OR.ASSIGNED_COACH_SUBSTITUTE_FOR_SCHEDULED_GROUP_START_OR+ScheduleTime10+ASSIGNED_COACH_SUBSTITUTE_FOR_SCHEDULED_GROUP_END_OR));
		verifyElementIsPresent(driver, substituteCoachButton);
		jsClickByXPath(driver, OR.ASSIGNED_COACH_SUBSTITUTE_FOR_SCHEDULED_GROUP_START_OR+ScheduleTime10+ASSIGNED_COACH_SUBSTITUTE_FOR_SCHEDULED_GROUP_END_OR);
		wait(driver, "5");
		return ScheduleTime10;
	}

	/**
	 * Name : Vinothkumar.M
	 * Created Date:   24/Nov/2016
	 * Modified Date:
	 * Description :   Common method to validate schedule/change for the 1on1 session
	 * Required Inputs :
	 * @return
	 *
	 */
	public static String realappealVerifyScheduleOrChange(WebDriver driver, String mail)
	{
		wait(driver, "5");
		try
		{
			waitForElement(driver, RA_MEMBERLIVESESSION_SCHEDULE_BUTTON);
			jsClickByXPath(driver, RA_MEMBERLIVESESSION_SCHEDULE_BUTTON);
			wait(driver, "6");
		}
		catch(Exception e)
		{
			try
			{
				waitForElement(driver, MEMBER_SESSION_CHANGE_BUTTON);
				jsClickByXPath(driver, MEMBER_SESSION_CHANGE_BUTTON);
				wait(driver, "6");
				waitForElement(driver, MEMBER_CANCEL_SESSION_BUTTON);
				jsClickByXPath(driver, MEMBER_CANCEL_SESSION_BUTTON);
				wait(driver, "3");
				waitForElement(driver, MEMBER_SESSION_POPUP_OK);
				jsClickByXPath(driver, MEMBER_SESSION_POPUP_OK);
				wait(driver, "6");
				waitForElement( driver, OR.REAL_APPEAL_MEMBER_DASHBOARD_TAB );
				jsClickByXPath(driver, REAL_APPEAL_MEMBER_DASHBOARD_TAB);
				wait(driver, "6");
				waitForElement(driver, RA_MEMBERLIVESESSION_SCHEDULE_BUTTON);
				jsClickByXPath(driver, RA_MEMBERLIVESESSION_SCHEDULE_BUTTON);
				wait(driver, "6");
			}
			catch(Exception e1)
			{
				waitForElement(driver, RA_MEMBER_DASHBOARD_RESCHDULE_BUTTON);
				jsClickByXPath(driver, RA_MEMBER_DASHBOARD_RESCHDULE_BUTTON);
				wait(driver, "5");
			}
		}
		waitForElement(driver, RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_AVAILABLE_BUTTON);
		WebElement timeselectionSelectTime = driver.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_AVAILABLE_BUTTON_AVAILABLE_TIME));
		verifyElementIsPresent(driver, timeselectionSelectTime);
		jsClickByXPath(driver, RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_AVAILABLE_BUTTON_AVAILABLE_TIME);
		wait( driver, "2" );
		String scheduledTime=driver.findElement(By.xpath("//h4[@id='selected1on1Class']")).getText();
		WebElement confirmButton = driver.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_CONFIRM_BUTTON));
		verifyElementIsPresent(driver, confirmButton);
		click(confirmButton);
		wait( driver, "7" );
		return scheduledTime;
	}

	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:   30/NOV/16
	 * Modified Date:
	 * Description :   Create a common method to Reschedule one on one session
	 * Ticket ID :
	 * Required Inputs :  Mail
	 *
	 */
	public static String raProgramAdminScheduledOneOnOneRescheduleOption(WebDriver driver,String Starttime6)
	{
		String[] Starttime4=Starttime6.split(" ");
		String Sessiontime6=Starttime4[1];
		System.out.println("Print9: "+Sessiontime6);
		String Sessiontime21= Sessiontime6.substring(0, 2).trim();
		System.out.println("SessionStarttime1: "+Sessiontime21);
		String Sessiontime22= Sessiontime6.substring(2, 5);
		System.out.println("SessionStarttime2: "+Sessiontime22);
		String Sessiontime23=Starttime4[2];
		System.out.println("SessionStarttime3: "+Sessiontime23);
		String Sessiontime24=Sessiontime21+Sessiontime22+Sessiontime23;
		System.out.println("Sessiontime24: "+Sessiontime24);
		WebElement rescheduleOption = driver.findElement(By.xpath(OR.SESSION_CONFLICTS_RESCHEDULE_OPTION_START_OR+Sessiontime24+SESSION_CONFLICTS_RESCHEDULE_OPTION_END_OR));
		verifyElementIsPresent(driver, rescheduleOption);
		jsClickByXPath(driver, OR.SESSION_CONFLICTS_RESCHEDULE_OPTION_START_OR+Sessiontime24+SESSION_CONFLICTS_RESCHEDULE_OPTION_END_OR);
		wait(driver, "5");
		return Sessiontime24;
	}

	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:   30/NOV/16
	 * Modified Date:
	 * Description :   Create a common method to Cancel One on One session
	 * Ticket ID :
	 * Required Inputs :  Mail
	 *
	 */
	public static String raProgramAdminScheduledOneOnOneCancelOption(WebDriver driver,String Starttime8)
	{
		String[] Starttime4=Starttime8.split(" ");
		String Sessiontime6=Starttime4[1];
		System.out.println("Print9: "+Sessiontime6);
		String Sessiontime21= Sessiontime6.substring(0, 2).trim();
		System.out.println("SessionStarttime1: "+Sessiontime21);
		String Sessiontime22= Sessiontime6.substring(2, 5);
		System.out.println("SessionStarttime2: "+Sessiontime22);
		String Sessiontime23=Starttime4[2];
		System.out.println("SessionStarttime3: "+Sessiontime23);
		String Sessiontime24=Sessiontime21+Sessiontime22+Sessiontime23;
		System.out.println("Sessiontime24: "+Sessiontime24);
		WebElement cancelOption = driver.findElement(By.xpath(OR.SESSION_CONFLICTS_CANCEL_OPTION_START_OR+Sessiontime24+SESSION_CONFLICTS_CANCEL_OPTION_END_OR));
		verifyElementIsPresent(driver, cancelOption);
		jsClickByXPath(driver, OR.SESSION_CONFLICTS_CANCEL_OPTION_START_OR+Sessiontime24+SESSION_CONFLICTS_CANCEL_OPTION_END_OR);
		wait(driver, "5");
		return Sessiontime24;
	}

	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   29/DEC/16
	 * Modified Date:
	 * Description :   Create a common method to CLose the support Matrix Popup
	 * Ticket ID :
	 * Required Inputs :
	 *
	 */
	public static String browserSupportMatrixRA(WebDriver driver1)
	{
		driver1.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try
		{	
			Manipulation.waitForElement(driver1, OR.MEMBER_BROWSER_SUPPORT_MATRIX_OK_BUTTON);

			WebElement okPopupButton = driver1.findElement(By.xpath(OR.MEMBER_BROWSER_SUPPORT_MATRIX_OK_BUTTON));
			verifyElementIsPresent(driver1, okPopupButton);
			WebElement closePopup = driver1.findElement(By.xpath(OR.MEMBER_BROWSER_SUPPORT_MATRIX_CLOSE_BUTTON));
			verifyElementIsPresent(driver1, closePopup);
			click(okPopupButton);
			wait(driver1, "3");
		}
		catch(Exception e) {
			System.out.println("Browser compatibility matrix message not displayed");
		}

		driver1.manage().timeouts().implicitlyWait(67, TimeUnit.SECONDS);
		//com.zillion.qa.realappealmember.member.termsAndConditionPopupHandle(driver1);
		return ElementWait;
	}

	/**
	 * Name :     Bharath Kumar.M
	 * Created Date:   27/JAN/17
	 * Modified Date:
	 * Description :   Create a common method to get username2 from config file and Enter
	 * Ticket ID :
	 * Required Inputs :
	 *
	 */
	public static String retrieveMemConfigUserName2(WebDriver driver)
	{
		String username2= Directory.RA_Member_username3;
		System.out.println(username2);
		return username2;
	}

	/**
	 * Name :     VigneshRaj.M
	 * Created Date:   30/JAN/17
	 * Modified Date:
	 * Description :   Create a common method to Tracker Server Temporarily Unavailable
	 * Ticket ID :
	 * Required Inputs :
	 *
	 */

	public static String handleTrackerServerUnavailable(WebDriver driver1)
	{
		try
		{
			jsClickByXPath(driver1, OR.RA_MEMBER_TRACKER_SERVER_OK_BUTTON);
			wait(driver1, "3");
		}
		catch (Exception e)
		{

		}
		return ElementWait;
	}

	/**
	 * Name :    Bharath Kumar
	 * Created Date:   03/FEB/17
	 * Modified Date:
	 * Description : Create a common method to retrieve Active member from RealAppeal Program
	 * @throws SQLException
	 *@throws ClassNotFoundException
	 * @throws IOException
	 */

	public static String realAppealActiveInactiveMember(WebDriver driver, String Status) throws ClassNotFoundException, SQLException, IOException
	{
		String Email=null;
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
		ResultSet rs = stat.executeQuery("select A.EMAIL as Email, SATD.MP_Name as ProgramName  from ACCOUNT A ,SUMMARY_ACCOUNT_TODATE SATD where A.ID= SATD.ACCOUNT_ID AND SATD.MAST_PROGRAM_ID='"+Directory.Mast_ProgramId+"' AND  A.EMAIL NOT LIKE '%mem%' AND A.EMAIL NOT LIKE '%api%' AND A.EMAIL NOT LIKE '%guerrillamail%' AND A.Status='"+Status+"'");
		System.out.println("query executed");
		if(rs.next())
		{
			Email= rs.getString("Email");
			System.out.println("Retrieved RealApeal Active member is"+Email);
		}
		return Email;
	}


	/**
	 * Name :    Bharath Kumar
	 * Created Date:   09/FEB/17
	 * Modified Date:
	 * Description : Create a common method to retrieve Active member from RealAppeal Program
	 * @throws SQLException
	 *@throws ClassNotFoundException
	 * @throws IOException
	 */
	public static String getTrackerExerciseFavValue(WebDriver driver, String ref1) {
		String str1 = driver.findElement(
				By.xpath("//table[@id='Trackers_Exercise_Favorites_Container']/tbody//tr//td[contains(text(),'" + ref1
						+ "')]"))
						.getText();

		String[] str2 = str1.split("\\s");
		String str3 = str2[0].trim();
		return str3;
	}


	/**
	 * Name :    Bharath Kumar
	 * Created Date:   09/FEB/17
	 * Modified Date:
	 * Description : Create a common method to retrieve Active member from RealAppeal Program
	 * @throws SQLException
	 *@throws ClassNotFoundException
	 * @throws IOException
	 */
	public static String getTrackerExerciseFavTimeValue(WebDriver driver, String ref1) {
		String str1 = driver.findElement(By.xpath("//table[@id='Trackers_Exercise_Favorites_Container']/tbody/tr/td[contains(text(),'" + ref1
				+ "')]/following::td"))
				.getText();
		String[] str2 = str1.split("\\s");
		String str3 = str2[0].trim();
		return str3;
	}

	/**
	 * Name :    Bharath Kumar
	 * Created Date:   13/FEB/17
	 * Modified Date:
	 * Description : Filter members with Client by reading the prop file 
	 */
	public static void realAppealfilterMemWithClient(WebElement webElement)
	{
		Manipulation.sendKeys(webElement, Directory.RA_Member_Client);
	}

	/**
	 * Name :     Bharath Kumar.M
	 * Created Date:   16/FEB/17
	 * Modified Date:
	 * Description :   Create a common method to Navigate Monday from current day
	 * Ticket ID :
	 * Required Inputs :
	 *
	 */
	public static void navigateToMonday(WebDriver driver, String inputdata)
	{
		DateFormat df = new SimpleDateFormat("E");
		Date dateobj = new Date();
		String CurrentDay = df.format(dateobj);
		System.out.println(CurrentDay);	

		switch (inputdata) {

		case "Exercise":
			WebElement PreviousDayArrow = driver.findElement(By.xpath(OR.MEMBER_TRACKERS_EXERCISE_PREVIOUS_DAY_ARROW));
			verifyElementIsPresent(driver, PreviousDayArrow);
			wait(driver, "3");
			if(CurrentDay.equals("Sun"))
			{
				System.out.println("Click 6 times");
				for(int i=0; i<6;i++ )
				{
					click(PreviousDayArrow);
					wait(driver, "1");
				}
			}
			if(CurrentDay.equals("Mon"))
			{
				System.out.println("Click 7 times");
				for(int i=0; i<7;i++ )
				{
					click(PreviousDayArrow);
					wait(driver, "1");
				}
			}
			if(CurrentDay.equals("Tue"))
			{
				System.out.println("Click 8 times");
				for(int i=0; i<8;i++ )
				{
					click(PreviousDayArrow);
					wait(driver, "1");
				}
			}
			if(CurrentDay.equals("Wed"))
			{
				System.out.println("Click 9 times");
				for(int i=0; i<9;i++ )
				{
					click(PreviousDayArrow);
					wait(driver, "1");
				}
			}
			if(CurrentDay.equals("Thu"))
			{
				System.out.println("Click 10 times");
				for(int i=0; i<10;i++ )
				{
					click(PreviousDayArrow);
					wait(driver, "1");
				}
			}
			if(CurrentDay.equals("Fri"))
			{
				System.out.println("Click 11 times");	
				for(int i=0; i<11;i++ )
				{
					click(PreviousDayArrow);
					wait(driver, "1");
				}
			}
			if(CurrentDay.equals("Sat"))
			{
				System.out.println("Click 12 times");	
				for(int i=0; i<12;i++ )
				{
					click(PreviousDayArrow);
					wait(driver, "1");
				}
			}
			break;


		case "Food":
			WebElement PreviousDayArrowFood = driver.findElement(By.xpath(OR.MEMBER_TRACKERS_FOODE_PREVIOUS_DAY_ARROW));
			verifyElementIsPresent(driver, PreviousDayArrowFood);
			wait(driver, "3");

			switch(CurrentDay) {
			case "Sun":

				System.out.println("Click 6 times");
				for(int i=0; i<6;i++ )
				{
					click(PreviousDayArrowFood);
					wait(driver, "1");
				}
				break;

			case "Mon":

				System.out.println("Click 7 times");
				for(int i=0; i<7;i++ )
				{
					click(PreviousDayArrowFood);
					wait(driver, "1");
				}
				break;

			case "Tue":

				System.out.println("Click 8 times");
				for(int i=0; i<8;i++ )
				{
					click(PreviousDayArrowFood);
					wait(driver, "1");
				}
				break;

			case "Wed":

				System.out.println("Click 9 times");
				for(int i=0; i<9;i++ )
				{
					click(PreviousDayArrowFood);
					wait(driver, "1");
				}
				break;

			case "Thu":

				System.out.println("Click 10 times");
				for(int i=0; i<10;i++ )
				{
					click(PreviousDayArrowFood);
					wait(driver, "1");
				}
				break;

			case "Fri":

				System.out.println("Click 11 times");
				for(int i=0; i<11;i++ )
				{
					click(PreviousDayArrowFood);
					wait(driver, "1");
				}
				break;

			case "Sat":

				System.out.println("Click 12 times");
				for(int i=0; i<12;i++ )
				{
					click(PreviousDayArrowFood);
					wait(driver, "1");
				}
				break;

			}

			break;
		}
	}


	/**
	 * Name :     Vinothkumar.M
	 * Created Date:   17/FEB/17
	 * Modified Date:
	 * Description :   Create a common method to check Email Agree button
	 * Ticket ID :
	 * Required Inputs :
	 *
	 */

	public static String preferenceAgreenDisagreeButton(WebDriver driver)
	{
		try
		{
			jsClickByXPath(driver, OR.ACCOUNT_SETTINGS_EDIT_BUTTON_WITH_PREFERENCES_HEADER);
			wait(driver, "3");
			jsClickByXPath(driver, OR.ACCOUNT_SETTINGS_PREFERENCES_SMS_I_AGREE_RADIO_BUTTON);
			wait(driver, "3");
			jsClickByXPath(driver, OR.ACCOUNT_SETTINGS_SAVE_BUTTON_IN_PREFERENCES);
			wait(driver, "3");
		}
		catch (Exception e)
		{

		}
		return ElementWait;
	}

	/**
	 * Name :   Vinothkumar.M
	 * Created Date:   17/FEB/2017
	 * Modified Date:
	 * Description :   Common method to validate schedule/change for the Customization session
	 * Required Inputs :
	 */
	public static void verifyScheduleOrChange(WebDriver driver)
	{
		wait(driver, "5");
		try
		{
			waitForElement(driver, MEMBER_SESSION_SCHEDULE);
			jsClickByXPath(driver, MEMBER_SESSION_SCHEDULE);
			wait(driver, "6");
			jsClickByXPath(driver, TIME_SELECTION_RESCHEDULE);
			wait(driver, "3");
			jsClickByXPath(driver, TIME_SELECTION_RESCHEDULE_TIME);
			wait(driver, "3");
			jsClickByXPath(driver, MEMBER_SESSION_CONFIRM);
			wait(driver, "5");
			waitForElement(driver, RESCHEDULE_SUCCESS_MESSAGE);
			jsClickByXPath(driver, MEMBER_SESSION_CHANGE);
			wait(driver, "6");
			jsClickByXPath(driver, MEMBER_CANCEL_SESSION);
			wait(driver, "3");
			waitForElement(driver, MEMBER_SESSION_POPUP_OK);
			jsClickByXPath(driver, MEMBER_SESSION_POPUP_OK);
			wait(driver, "6");

		}
		catch(Exception e)
		{
			jsClickByXPath(driver, MEMBER_SESSION_CHANGE);
			wait(driver, "6");
			jsClickByXPath(driver, MEMBER_CANCEL_SESSION);
			wait(driver, "3");
			waitForElement(driver, MEMBER_SESSION_POPUP_OK);
			jsClickByXPath(driver, MEMBER_SESSION_POPUP_OK);
			wait(driver, "6");

		}
	}
	/**
	 * Name :     Bharath.M
	 * Created Date:   20/FEB/17
	 * Modified Date:
	 * Description :   Create a common method to Handle PHI popup
	 * Ticket ID :
	 * Required Inputs :
	 *
	 */
	public static String handlePHIPopup(WebDriver driver)
	{
		try
		{
			waitForElement(driver, OR.RA_ACCEPT_AFTER_INSURANCE);
			WebElement Afer_Insurance_Button = driver.findElement(By.xpath(OR.RA_ACCEPT_AFTER_INSURANCE));
			verifyElementIsPresent(driver, Afer_Insurance_Button);
			wait(driver, "2");
			String CurrentURL=getCurrentURL(driver);
			System.out.println("Current url is"+CurrentURL);
			System.out.println("Before If");
			if(CurrentURL.contains(Directory.RA_UseCaseOneCustomEnroll_Member_Url))
			{	System.out.println("Inside one");
			enterUseCaseOneRAFirstNameOnInsurancePopUp(driver);
			enterUseCaseOneRALastNameOnInsurancePopUp(driver);
			wait(driver, "1");
			click(Afer_Insurance_Button);
			wait(driver, "2");
			}
			if(CurrentURL.contains(Directory.RA_UseCaseTwoCustomEnroll_Member_Url))
			{
				System.out.println("Inside two");
				enterUseCaseTwoRAFirstNameOnInsurancePopUp(driver);
				System.out.println("Entered 1st name");
				enterUseCaseTwoRALastNameOnInsurancePopUp(driver);
				wait(driver, "1");
				click(Afer_Insurance_Button);
				wait(driver, "2");
			}
			if(CurrentURL.contains(Directory.RA_UseCaseThreeCustomEnroll_Member_Url))
			{
				System.out.println("Inside three");
				enterUseCaseThreeRAFirstNameOnInsurancePopUp(driver);
				enterUseCaseThreeRALastNameOnInsurancePopUp(driver);
				wait(driver, "1");
				click(Afer_Insurance_Button);
				wait(driver, "2");
			}
		}
		catch (Exception e)
		{
			System.out.println("PHI Pop up is not enabled carry on with the next step");
		}
		return ElementWait;
	}

	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:   20/FEB/17
	 * Modified Date:
	 * Description : Create a common method to To find RA members who have missed C/S and can reschedule it
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static String retrieveMissedRACustomizationMemberEmail(WebDriver driver,String testData) throws ParseException, ClassNotFoundException, SQLException
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
		//ResultSet rs = stat.executeQuery("select A.EMAIL, CE.SESSION_STATUS from CALENDAR_EVENT CE, ACCOUNT A WHERE A.ID= CE.ACCOUNT_ID AND CE.SESSION_STATUS = 'Missed' and CE.SESSION_TYPE_ID = '13' and A.STATUS IN ('Active') AND START_DT_TIME<SYSDATE ORDER BY CE.START_DT desc");
		ResultSet rs = stat.executeQuery("SELECT A.EMAIL FROM ACCOUNT A, ACCOUNT_PROGRAM AP, ACCOUNT_PROGRAM_SESSION_DETAIL PSD WHERE A.ID= AP.ACCOUNT_ID AND AP.ID= PSD.ACCOUNT_PROGRAM_ID AND PSD.SESSION_TYPE_ID='"+inputSessionType+"' and PSD.SESSION_STATUS= '"+inputSessionStatus+"' AND A.EMAIL NOT LIKE '%api%' and A.EMAIL NOT LIKE '%QAmember%default@yopmail.com%' and A.STATUS IN ('Active') ORDER BY A.CREATED_DT desc");
		System.out.println("query executed");
		String retrievedMemberEmail="";
		if(rs.next())
		{
			retrievedMemberEmail = rs.getString("EMAIL");
			System.out.println("Missed Customization Member Email is "+retrievedMemberEmail);
		}
		return retrievedMemberEmail;
	}

	/**
	 * Name :Vinothkumar.M
	 * Created Date:20/FEB/2017
	 * Modified Date:
	 * Description :   Create a common method to retrieve Available Member Email ID with Account ID
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static String retrieveAvailableMemberEmailIdWithAccountID(WebDriver driver,String availableMemberEmailIDWithAccountID) throws ParseException, ClassNotFoundException, SQLException
	{
		String availableMemberEmailIDWithAccountID1="";
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
		ResultSet rs = stat.executeQuery("SELECT ID, NAME, EMAIL, ORGANIZATION_ID, STATUS FROM ACCOUNT WHERE EMAIL LIKE'"+availableMemberEmailIDWithAccountID+"'");
		System.out.println("query executed");
		if(rs.next())
		{
			availableMemberEmailIDWithAccountID1  = rs.getString("ID");

		}
		System.out.println("Available Member Account ID "+availableMemberEmailIDWithAccountID1+" is retrieved from the Table");
		return availableMemberEmailIDWithAccountID1;
	}
	/**
	 * Name :     Bharath Kumar. M
	 * Created Date:   23/Feb/17
	 * Modified Date:
	 * Description : Create a common method to verify the QA Security Link email
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void verifyQaSecurityEmail(WebDriver driver, String mailid)
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
			WebElement iframe= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME1));
			Navigate.switchToFrame(driver, iframe);
			WebElement generatedLink= driver.findElement(By.xpath(OR.YOPMAIL_SECURITY_QA_EMAIL));
			verifyElementIsPresent(driver, generatedLink);
			com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.YOPMAIL_SECURITY_QA_EMAIL);
			wait(driver, "5");

			if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
			jsClickByXPath(driver, OR.YOPMAIL_SECURITY_QA_EMAIL);
			wait(driver, "3");
			Navigate.switchToDefaultFrame(driver);
			WebElement iframe2= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME2));
			Navigate.switchToFrame(driver, iframe2);
			waitForElement(driver, OR.YOPMAIL_SECURITY_QA_EMAIL_CONTENT);
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
			com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.GUERRILLA_MAIL_SECURITY_EMAIL);
			wait(driver, "5");
			WebElement QAsecurityEmail= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_SECURITY_EMAIL));
			verifyElementIsPresent(driver, QAsecurityEmail);
			if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
		}
	}
	/**
	 * Name :     Vinothkumar.M
	 * Created Date:   7/MAR/2017
	 * Description :Create a common method for Login into MDAC Jira
	 * Ticket ID :
	 * Required Inputs : URL, Username and Password
	 *
	 */
	public static void mdacJiraLogin(WebDriver driver)
	{
		Navigate.get(driver, Directory.MDAC_Jira_Url);
		Navigate.maximize(driver);
		wait(driver, "5");
		try{
			WebElement userName= driver.findElement(By.xpath(MDAC_JIRA_USERNAME));
			waitForElement(driver,MDAC_JIRA_USERNAME);
			sendKeys(userName, Directory.MDAC_Jira_Username);
			WebElement nextButton= driver.findElement(By.xpath(MDAC_JIRA_NEXT_BUTTON));
			click(nextButton);
			wait(driver, "5");
			WebElement password= driver.findElement(By.xpath(MDAC_JIRA_PASSWORD));
			sendKeys(password, Directory.MDAC_Jira_Password);
			click(nextButton);
			wait(driver, "10");
		}
		catch(Exception e) {
			System.out.println("Username field not found, admin is already logged in");
		}

	}	

	/**
	 * Name :     Vinothkumar.M
	 * Created Date:   7/MAR/2017
	 * Description :Create a common method for Logout into MDAC Jira
	 * Ticket ID :
	 * Required Inputs : URL, Username and Password
	 *
	 */
	public static void mdacJiraLogout(WebDriver driver)
	{
		wait(driver, "5");
		WebElement mdacJiraUserProfileImage= driver.findElement(By.xpath(OR.MDAC_JIRA_USER_PROFILE_IMAGE));
		wait(driver, "5");
		verifyElementIsPresent(driver, mdacJiraUserProfileImage);
		click(mdacJiraUserProfileImage);
		WebElement logOutLink= driver.findElement(By.xpath(OR.MDAC_JIRA_LOGOUT_LINK));
		click(logOutLink);
		wait(driver, "10");

	}	
	/**
	 * Name :     Bharath Kumar. M
	 * Created Date:   07/Mar/17
	 * Modified Date:
	 * Description : Created a common method to Retrieve Ideal member from DataBase
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static Object retrieveIdealMemberFromDb(WebDriver driver) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select first_name,last_name,email,status,created_dt from account where Status='Idle' and EMAIL NOT LIKE '%guerrillamail.com%' and EMAIL NOT LIKE '%api%' AND EMAIL NOT LIKE '%info%'");
		System.out.println("query executed");
		String retrieveIdealMember="";
		while(rs.next())
		{
			retrieveIdealMember  = rs.getString("EMAIL");

		}
		System.out.println("Retrieved Ideal member from DB is "+retrieveIdealMember);
		return retrieveIdealMember;
	}

	/**
	 * Name :     Bharath Kumar. M
	 * Created Date:   07/Mar/17
	 * Modified Date:
	 * Description : Created a common method to Retrieve Security answer for particular member
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */

	public static Object securityAnswerForParticularMember(WebDriver driver,String Email) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select first_name,last_name,EMAIL,status,created_dt,Security_Answer from account where  EMAIL='"+Email+"'");
		System.out.println("query executed");
		String SecurityAnswer="";
		if(rs.next())
		{
			SecurityAnswer  = rs.getString("Security_Answer");

		}
		System.out.println("Retrieved Security Answer for Ideal member "+Email+" from DB is "+SecurityAnswer);
		return SecurityAnswer;

	}

	/**
	 * Name :     VinothKumar. M
	 * Created Date:   08/Mar/17
	 * Modified Date:
	 * Description : Created a common method to Split the Recepient Member name
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String realAppealRecepientMemberName(WebDriver driver,String RecepientName)
	{

		String[] Starttime1=RecepientName.split(":");
		String[] split=Starttime1[1].split(",");
		System.out.println(split[0]);
		System.out.println(split[1]);
		String Name=split[1]+""+split[0];
		System.out.println("Member name is" +Name);
		return Name;
	}	


	/**
	 * Name :     Bharath Kumar. M
	 * Created Date:   08/Mar/17
	 * Modified Date:
	 * Description : Created a common method to update last login for particular member
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static Object updatingLastLoginDate(WebDriver driver, String member_Id2) throws ClassNotFoundException, SQLException
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
		stat.executeQuery("UPDATE SUMMARY_ACCOUNT_TODATE SET LOGIN_DT_UTC=sysdate-42 WHERE ACCOUNT_ID='"+member_Id2+"'");
		System.out.println("Update query executed successfully");
		stat.executeQuery("COMMIT");
		System.out.println("Commit has been done successfully");
		System.out.println("query executed");
		String Message="Row has been upated with the fetched member id and commited";
		System.out.println("Retrieved Member ID is "+Message);
		return Message;
	}
	/**
	 * Name :     Bharath Kumar. M
	 * Created Date:   08/Mar/17
	 * Modified Date:
	 * Description : Created a common method to Get system hour and minute
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static Object hourAndMinute(WebDriver driver)
	{

		Date date = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH");
		SimpleDateFormat sdf = new SimpleDateFormat("mm");
		String Minutes = sdf.format(date);
		String Hour = sdf1.format(date);
		System.out.println(Hour);
		System.out.println(Minutes);
		int i=Integer.parseInt(Minutes);
		i=i+2;
		System.out.println("added +2 "+i );
		String HourAndMinute = Hour+","+i;
		System.out.println("Hour,Minutes ="+HourAndMinute );
		return HourAndMinute;
	}
	/**
	 * Name :     Bharath Kumar. M
	 * Created Date:   08/Mar/17
	 * Modified Date:
	 * Description : Created a common method to Enter hour in Epoch
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static Object enterHourInEpoch(WebDriver driver, String hour) 
	{
		WebElement epochHour = driver.findElement( By.xpath(OR.EPOCH_HOUR ) );
		Manipulation.clearAndType(epochHour, hour);
		String Message="System 'Hour' Time Entered in Epoch";
		return Message;

	}
	/**
	 * Name :     Bharath Kumar. M
	 * Created Date:   08/Mar/17
	 * Modified Date:
	 * Description : Created a common method to Enter hour in Epoch
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static Object enterMinuteInEpoch(WebDriver driver, String Minute) 
	{
		WebElement epochMinute = driver.findElement( By.xpath(OR.EPOCH_MINUTE ) );
		Manipulation.clearAndType(epochMinute, Minute);
		String Message="System 'Minute' Time Entered in Epoch";
		return Message;

	}
	/**
	 * Name :     Bharath Kumar. M
	 * Created Date:   08/Mar/17
	 * Modified Date:
	 * Description : Created a common method to update quartz table to change active member to Inactive
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static Object updateQRTZTableForIdle(WebDriver driver, String idle1) throws ClassNotFoundException, SQLException
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
		stat.executeQuery("update QRTZ_TRIGGERS set NEXT_FIRE_TIME = '"+idle1+"' where trigger_name = 'idleMemberIdentificationJobTrigger'");
		System.out.println("Update query executed successfully");
		stat.executeQuery("COMMIT");
		System.out.println("Commit has been done successfully");
		System.out.println("query executed");
		String Message="Quartz Table has been updated";
		return Message;
	}


	/**
	 * Name         :   Leena P.
	 * Created Date :   10/Mar/17
	 * Modified Date:   
	 * Description  :  Guerrilla Mail RA Member enrollment completion mail
	 * 
	 */
	public static void guerrillaMailWelcomeToRALink(WebDriver driver, String Email)
	{
		Navigate.get(driver, Directory.Guerrillamailurl);
		Navigate.maximize(driver);
		wait(driver, "5");
		WebElement Guerrillamaildropdown= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_DROPDOWN));
		selectByValue(Guerrillamaildropdown, "guerrillamail.com");
		WebElement GuerrillaMailEditButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_EDIT_BUTTON));
		click(GuerrillaMailEditButton);
		waitForElement(driver, GUERRILLA_MAIL_TEXTBOX);
		WebElement GuerrillaMailTextbox= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_TEXTBOX));
		clearAndType(GuerrillaMailTextbox, Email);
		WebElement GuerrillaMailSetButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_SET_BUTTON));
		click(GuerrillaMailSetButton);
		wait(driver, "5");

		WebElement generatedLink= driver.findElement(By.xpath(OR.ORBERA_COACH_1on1_MAIL_GR));
		String grSubject = ElementActions.getText(generatedLink);
		System.out.println("Subject line is" +grSubject);

		if(grSubject.contains("Welcome to Real Appeal")) {
			System.out.println("Welcome to Real Appeal email verified");
		}
		else {
			System.out.println("Welcome to Real Appeal email not verified");
			Assert.fail();
		}


		/*	try{
			WebElement GuerrillaMailWelcomeLink= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_WELCOME_TO_RA_LINK));
			verifyElementIsPresent(driver, GuerrillaMailWelcomeLink);
		}
		catch(Exception e)
		{
			//driver.navigate().refresh();
			wait( driver, "10" );
			WebElement GuerrillaMailWelcomeLink= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_WELCOME_TO_RA_LINK));
			verifyElementIsPresent(driver, GuerrillaMailWelcomeLink);
		} */

	}
	/**
	 * Name :     Bharath Kumar. M
	 * Created Date:   13/Mar/17
	 * Modified Date:
	 * Description : Common Method to retrieve a member who missed 1 on 1 session
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static Object retrieveMemeberMissed1on1Session(WebDriver driver) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select A.EMAIL, CE.SESSION_STATUS  from CALENDAR_EVENT CE, ACCOUNT A WHERE A.ID= CE.ACCOUNT_ID AND CE.SESSION_STATUS = 'Missed' AND EMAIL NOT LIKE '%guerrillamail%' AND EMAIL NOT LIKE '%api%' AND EMAIL NOT LIKE '%info%'and CE.SESSION_TYPE_ID = '02' and START_DT_TIME<SYSDATE ORDER BY CE.START_DT desc");
		System.out.println("query executed");
		String Email="";
		if(rs.next())
		{
			Email  = rs.getString("EMAIL");

		}
		System.out.println("Retrieved Member Email id who misssed 1on1 session: "+Email);
		return Email;
	}
	/**
	 * Name :     Bharath Kumar. M
	 * Created Date:   13/Mar/17
	 * Modified Date:
	 * Description : Common Method to retrieve a member who missed 1 on 1 session
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String MemberFirstNameFromDB(WebDriver driver, String email1) throws ClassNotFoundException, SQLException 
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
		ResultSet rs = stat.executeQuery("select email,First_name,Last_name from account where email='"+email1+"'");
		System.out.println("query executed");
		String Fname="";
		if(rs.next())
		{
			Fname  = rs.getString("FIRST_NAME");

		}
		System.out.println("Retrieved Member First name from DB: "+Fname);
		return Fname;
	}
	/**
	 * Name :     Bharath Kumar. M
	 * Created Date:   13/Mar/17
	 * Modified Date:
	 * Description : Common Method to retrieve a member who missed 1 on 1 session
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static Object retrieveMemberDeclinedStatus(WebDriver driver, String Status) throws ClassNotFoundException, SQLException 
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
		ResultSet rs = stat.executeQuery("select A.EMAIL ,  SATD.ONBOARDING_STATUS, A.ID from ACCOUNT A, SUMMARY_ACCOUNT_TODATE SATD where A.ID= SATD.ACCOUNT_ID AND  SATD.ONBOARDING_STATUS= '"+Status+"' and A.STATUS='Inactive' AND A.EMAIL NOT LIKE '%api%' AND A.EMAIL NOT LIKE '%info%'");
		System.out.println("query executed");
		String Declinedmember="";
		if(rs.next())
		{
			Declinedmember  = rs.getString("EMAIL");

		}
		System.out.println("Retrieved Member In Dclined Status: "+Declinedmember);
		return Declinedmember;
	}
	/**
	 * Name :     Bharath Kumar.M
	 * Created Date:   17/Mar/17
	 * Modified Date:
	 * Description : Create a common method to store newly customized member member 
	 */
	static String RAcustomizedMember;
	public static void storeNewCustomizationMember(WebDriver driver, String data)
	{
		RAcustomizedMember=data;
		try {
			Properties properties = new Properties();
			properties.setProperty("RAmember1", RAcustomizedMember);
			File file = new File(Directory.uploadFilePath+"//RAcustomizedMember.properties");
			FileOutputStream fileOut = new FileOutputStream(file);
			properties.store(fileOut, "This is to store member for newly customized member");
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(RAcustomizedMember+":Member from Member enrollment");
	}
	/**
	 * Name :     Bharath Kumar. M
	 * Created Date:   07/APR/17
	 * Modified Date:
	 * Description : Create a common method to retrieve the stored customization member
	 * @throws IOException 
	 */
	public static Object retrieveStoredCustomizatoinMember(WebDriver driver) throws IOException
	{
		String mailid=null;
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.uploadFilePath+"//RAcustomizedMember.properties");
		urlsProperties.load(input);
		mailid=urlsProperties.getProperty("RAmember1").trim();
		return mailid;
	}
	/**
	 * Name :     Bharath Kumar. M
	 * Created Date:   13/Mar/17
	 * Modified Date:
	 * Description : Common Method to retrieve a member who missed 1 on 1 session
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static Object retrieveMemeberMissedGroupSession(WebDriver driver) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select ACNT.EMAIL, CE.START_DT_TIME FROM CALENDAR_EVENT CE , CALENDAR_EVENT_ATTENDEE CEA, ACCOUNT ACNT WHERE CE.ID= CEA.EVENT_ID AND ACNT.ID= CEA.ACCOUNT_ID AND CE.SESSION_TYPE_ID= '01' AND CE.SESSION_STATUS= 'Missed' AND CE.START_DT<SYSDATE AND ACNT.EMAIL not like '%info%' AND ACNT.EMAIL not like '%api%' ORDER BY CE. START_DT DESC");
		System.out.println("query executed");
		String Email="";
		if(rs.next())
		{
			Email  = rs.getString("EMAIL");

		}
		System.out.println("Retrieved Member Email id who misssed Group session: "+Email);
		return Email;
	}

	/**
	 * Name :     Bharath Kumar. M
	 * Created Date:   07/Mar/17
	 * Modified Date:
	 * Description : Created a common method to Retrieve Ideal member from DataBase on boarding status
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static Object retrieveIdealMemberFromDbOnboardingStatus(WebDriver driver) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select A.EMAIL , A.STATUS, SATD.ONBOARDING_STATUS, A.ID, SATD.PROGRAM_INTERVAL_NUMBER from ACCOUNT A, SUMMARY_ACCOUNT_TODATE SATD where A.ID= SATD.ACCOUNT_ID AND SATD.ONBOARDING_STATUS= 'MEMBER ONBOARDED'  AND SATD.PROGRAM_INTERVAL_NUMBER< '52' and A.STATUS='Idle' AND A.EMAIL NOT LIKE '%api%' AND A.EMAIL NOT LIKE '%info%' AND A.EMAIL not like '%QAmember%-default@yopmail.com%'");
		System.out.println("query executed");
		String retrieveIdealMember="";
		if(rs.next())
		{
			retrieveIdealMember  = rs.getString("EMAIL");

		}
		System.out.println("Retrieved Ideal member from DB is "+retrieveIdealMember);
		return retrieveIdealMember;
	}



	/**
	 * Name :      Leena P.
	 * Created Date:   24/Mar/17
	 * Modified Date:
	 * Description : Create a common method to delete the saved food entries
	 * Ticket ID :
	 *
	 */
	public static void deleteAllSavedFood(WebDriver driver)
	{
		try
		{
			wait(driver, "2");
			int deletesavedFood = driver.findElements(By.xpath("//tbody//i[contains(@id,'trackersFoodRemoveDailyFoods')]")).size();
			System.out.println( "Total foods are: " +deletesavedFood);
			for(int i=1; i<=deletesavedFood;i++)
			{
				wait(driver, "2");
				WebElement deletesavedFood1 = driver.findElement(By.xpath("//tbody//i[contains(@id,'trackersFoodRemoveDailyFoods')]"));
				click(deletesavedFood1);
				wait(driver, "2");
			}
		}
		catch(Exception e)
		{
		}

	}




	/**
	 * Name :     Bharath kumar.M
	 * Created Date:   24/Mar/17
	 * Modified Date:  31/Mar/17
	 * Description :   Create a common method to Handle terms and condition and PHI pop ups 
	 * Ticket ID :
	 * Required Inputs :
	 *
	 */
	public static void termsAndConditionPopupHandle(WebDriver driver1)
	{	
		driver1.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

		try
		{
			Manipulation.waitForElement(driver1, OR.YES_I_AGREE_BUTTON);
			WebElement agree_buton = driver1.findElement(By.xpath(OR.YES_I_AGREE_BUTTON));
			verifyElementIsPresent(driver1, agree_buton);
			click(agree_buton);
			wait(driver1, "4");
		}
		catch(Exception e)
		{
			System.out.println("Terms and Conditions popup not displayed or handled");
		}

		try
		{		
			Manipulation.waitForElement(driver1, OR.RA_FIRSTNAME_AFTER_INSURANCE);

			sendKeys(driver1.findElement(By.xpath(OR.RA_FIRSTNAME_AFTER_INSURANCE)), Directory.RA_Enrollment_First_Name);
			WebElement  lastname = driver1.findElement(By.xpath(OR.RA_LASTNAME_AFTER_INSURANCE));
			sendKeys(lastname, Directory.RA_Enrollment_Last_Name);
			wait(driver1, "4");
			WebElement Afer_Insurance_Button = driver1.findElement(By.xpath(OR.RA_ACCEPT_AFTER_INSURANCE));
			click(Afer_Insurance_Button);
		}
		catch(Exception e)
		{
			System.out.println("PHI popup not displayed or handled");
		}

		driver1.manage().timeouts().implicitlyWait(67, TimeUnit.SECONDS);

	}


	/**
	 * Name :     Bharath kumar.M
	 * Created Date:   24/Mar/17
	 * Modified Date:
	 * Description :   Create a common method to Handle terms and condition pop up by clicking I agree
	 * Ticket ID :
	 * Required Inputs :
	 *
	 */
	public static String termsAndConditionPopupHandleDisagreeButton(WebDriver driver1)
	{
		try
		{
			waitForElement(driver1, OR.TERMS_CONDITION_HEADER);
			WebElement disagree_button = driver1.findElement(By.xpath(OR.DISAGREE_BUTTON));
			verifyElementIsPresent(driver1, disagree_button);
			click(disagree_button);
			wait(driver1, "4");
		}
		catch (Exception e)
		{

		}
		return ElementWait;
	}

	/**
	 * Name :     Leena P.
	 * Created Date:   28/MAR/17
	 * Modified Date:
	 * Description :   Create a common method to get username4 from config file and Enter
	 * Ticket ID :
	 * Required Inputs :
	 *
	 */
	public static String retrieveMemConfigUserNamePwdReset(WebDriver driver)
	{
		String username4= Directory.RA_Member_username4;
		System.out.println(username4);
		return username4;
	}

	/**
	 * Name :     Bharath kumar. M
	 * Created Date:   30/MAR/17
	 * Modified Date:
	 * Description :   Create a common method to get username5 from config file and Enter
	 * Ticket ID :
	 * Required Inputs :
	 *
	 */
	public static Object retrieveMemConfigUserNameEmailChange(WebDriver driver)
	{

		String username5= Directory.RA_Member_username5;
		System.out.println(username5);
		return username5;
	}

	/**
	 * Name :     Bharath Kumar. M
	 * Created Date:   30/Mar/17
	 * Modified Date:
	 * Description : Create a common method to verify the email change Notification
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void verifyMemberEmailChangeNotification(WebDriver driver, String mailid)
	{
		wait(driver, "5");
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
			if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
			WebElement iframe2= driver.findElement(By.id(OR.YOPMAIL_IFRAME2));
			Navigate.switchToFrame(driver, iframe2);
			WebElement Notificationemail= driver.findElement(By.xpath(OR.NOTIFICATION_EMAIL_YOP));
			verifyElementIsPresent(driver, Notificationemail);


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
			WebElement	 GuerrillaMailSetButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_SET_BUTTON));
			click(GuerrillaMailSetButton);
			wait(driver, "5");
			com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.NOTIFICATION_EMAIL_GUERRILLA);
			wait(driver, "5");

			if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
			WebElement Notificationemail= driver.findElement(By.xpath(OR.NOTIFICATION_EMAIL_GUERRILLA));
			verifyElementIsPresent(driver, Notificationemail);
		}
	}
	/**
	 * Name :     Bharath Kumar. M
	 * Created Date:   31/Mar/17
	 * Modified Date:
	 * Description : Create a common method to verify the Password change Notification
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void verifyMemberPasswordChangeNotification(WebDriver driver, String mailid)
	{
		wait(driver, "5");
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
			if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
			WebElement iframe2= driver.findElement(By.id(OR.YOPMAIL_IFRAME2));
			Navigate.switchToFrame(driver, iframe2);
			WebElement Notificationemail= driver.findElement(By.xpath(OR.YOPMAIL_PASSWORD_CHANGE_NOTIFICATION));
			verifyElementIsPresent(driver, Notificationemail);


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
			WebElement	 GuerrillaMailSetButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_SET_BUTTON));
			click(GuerrillaMailSetButton);
			wait(driver, "5");
			com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.GUERRILLA_PASSWORD_CHANGE_NOTIFICATION);
			wait(driver, "5");

			if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
			WebElement Notificationemail= driver.findElement(By.xpath(OR.GUERRILLA_PASSWORD_CHANGE_NOTIFICATION));
			verifyElementIsPresent(driver, Notificationemail);
		}
	}
	/**
	 * Name :     Bharath Kumar. M
	 * Created Date:   01/Apr/17
	 * Modified Date:
	 * Description : Create a common method to verify the Notification mail Coach sends to member
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String verifyNotificationMailCoachOrProgramAdminSendsToMemberOrClass(WebDriver driver, String mailid) throws ClassNotFoundException, SQLException
	{
		wait(driver, "5");
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
			if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
			Navigate.switchToFrame(driver, "ifmail");			
			wait(driver,"3");
			WebElement Notificationemail= driver.findElement(By.xpath(OR.YOPMAIL_SUBJECT_NEW_MESSAGE));
			verifyElementIsPresent(driver, Notificationemail);
			String YopMailNewMessage=Notificationemail.getText();
			WebElement YopMailMemberName= driver.findElement(By.xpath(OR.YOPMAIL_MEMBER_NAME));
			String YopMail_MemberName = YopMailMemberName.getText();
			String[] Email = YopMail_MemberName.split(" ");
			System.out.println(Email[0]);
			System.out.println(Email[1]);
			String EmailFirstName = Email[1].replaceAll(",", "");
			System.out.println("After Replace" +EmailFirstName);
			String MemberFirstNameFromDB=com.zillion.qa.realappealmember.member.MemberFirstNameFromDB(driver,mailid);
			WebElement YopMailMemberMailContentLine1= driver.findElement(By.xpath(YOPMAIL_MEMBER_NEW_MESSAGE_PLEASE_DO_NOT));
			String YopMailMemberMailContentLine1Text=YopMailMemberMailContentLine1.getText();
			WebElement YopMailMemberMailContentLine2= driver.findElement(By.xpath(OR.YOPMAIL_MEMBER_NEW_MESSAGE_HAVING));
			String YopMailMemberMailContentLine2Text=YopMailMemberMailContentLine2.getText();
			WebElement YopMailMemberMailContentLine3= driver.findElement(By.xpath(OR.YOPMAIL_MEMBER_NEW_MESSAGE_FOR_ANY));
			String YopMailMemberMailContentLine3Text=YopMailMemberMailContentLine3.getText();
			String YopMailFirstName=Manipulation.condtionMatch(EmailFirstName,MemberFirstNameFromDB);
			System.out.println("Yopmail First Name: "+YopMailFirstName);
			String YopMailMessageContentLine1 = Manipulation.condtionMatch(YopMailMemberMailContentLine1Text, "(Please do not reply to this email.)");
			System.out.println("Yopmail Message Content Line1: "+YopMailMessageContentLine1);
			String YopMailMessageContentLine3 = Manipulation.condtionMatch(YopMailMemberMailContentLine3Text, "For any other questions, please contact your Real Appeal Coach in the message center.");
			System.out.println("Yopmail Message Content Line3: "+YopMailMessageContentLine3);
			if(Directory.zado_environment.equalsIgnoreCase("ConfigurationFile-Kulfi"))
			{
				WebElement YopMailMemberNewMessageBody= driver.findElement(By.xpath(OR.YOPMAIL_MEMBER_NEW_MESSAGE_MAIL_BODY));
				String YopMailMemberNewMessageMailBody = YopMailMemberNewMessageBody.getText();
				String YopMailMessageBody = Manipulation.condtionMatch(YopMailMemberNewMessageMailBody, "There's a new message waiting for you from RA - Real Appeal. Please visit https://vra-kulfi.zillion.com to view the message.");
				System.out.println("Yopmail New Message Body: "+YopMailMessageBody);
				String YopMailMessageContentLine2 = Manipulation.condtionMatch(YopMailMemberMailContentLine2Text, "Having trouble logging in? Email us at kulfi@healthfleet.com or call us at 1-844-344-REAL (7325).");
				System.out.println("Yopmail Message Content Line2: "+YopMailMessageContentLine2);
				WebElement YopMailMemberMailContentLine4= driver.findElement(By.xpath(OR.YOPMAIL_MEMBER_NEW_MESSAGE_SUPPORT_LINK));
				String YopMailMemberMailContentLine4Text=YopMailMemberMailContentLine4.getText();
				String YopMailMessageContentLine4 = Manipulation.condtionMatch(YopMailMemberMailContentLine4Text, "https://vra-kulfi.zillion.com");
				System.out.println("Yopmail Message Content Line4: "+YopMailMessageContentLine4);
			}
			else if(Directory.zado_environment.equalsIgnoreCase("ConfigurationFile-Test"))
			{
				WebElement YopMailMemberNewMessageBody= driver.findElement(By.xpath(OR.YOPMAIL_MEMBER_NEW_MESSAGE_MAIL_BODY));
				String YopMailMemberNewMessageMailBody = YopMailMemberNewMessageBody.getText();
				String YopMailMessageBody = Manipulation.condtionMatch(YopMailMemberNewMessageMailBody, "There's a new message waiting for you from RA - Real Appeal. Please visit https://testmemfe.zillion.com to view the message.");
				System.out.println("Yopmail New Message Body: "+YopMailMessageBody);
				String YopMailMessageContentLine2 = Manipulation.condtionMatch(YopMailMemberMailContentLine2Text, "Having trouble logging in? Email us at systest@healthfleet.com or call us at 1-844-344-REAL (7325).");
				System.out.println("Yopmail Message Content Line2: "+YopMailMessageContentLine2);
				WebElement yopMailMemberNewMessageLine4Test = driver.findElement(By.xpath(OR.YOPMAIL_MEMBER_NEW_MESSAGE_SUPPORT_LINK_TEST));
				String yopMailMemberNewMessageSupportLinkTest=yopMailMemberNewMessageLine4Test.getText();
				String yopMailMemberNewMessageSupportLinkTestLine4= Manipulation.condtionMatch(yopMailMemberNewMessageSupportLinkTest, "https://testmemfe.zillion.com");
				System.out.println("Yopmail Message Content Line4: "+yopMailMemberNewMessageSupportLinkTestLine4);
			}
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
			WebElement	 GuerrillaMailSetButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_SET_BUTTON));
			click(GuerrillaMailSetButton);
			wait(driver, "5");
			com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.GUERRILLA_SUBJECT_NEW_MESSAGE);
			wait(driver, "5");

			if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
			WebElement Notificationemail= driver.findElement(By.xpath(OR.GUERRILLA_SUBJECT_NEW_MESSAGE));
			verifyElementIsPresent(driver, Notificationemail);
			String GuerrillaMailNewMessage=Notificationemail.getText();
			click(Notificationemail);
			WebElement GUERRILLAMAILMemberName= driver.findElement(By.xpath(OR.GUERRILLAMAIL_MEMBER_NAME));
			String GUERRILLAMAIL_MemberName = GUERRILLAMAILMemberName.getText();
			String[] GuerrillaEmail = GUERRILLAMAIL_MemberName.split(" ");
			System.out.println(GuerrillaEmail[0]);
			System.out.println(GuerrillaEmail[1]);
			String GuerrillaEmailFirstName = GuerrillaEmail[1].replaceAll(",", "");
			System.out.println("After Replace" +GuerrillaEmailFirstName);
			String MemberFirstNameFromDB=com.zillion.qa.realappealmember.member.MemberFirstNameFromDB(driver,mailid);
			WebElement GUERRILLAMAILMemberMailContentLine1= driver.findElement(By.xpath(OR.GUERRILLAMAIL_MEMBER_NEW_MESSAGE_PLEASE_DO_NOT));
			String GUERRILLAMAILMemberMailContentLine1Text=GUERRILLAMAILMemberMailContentLine1.getText();
			WebElement GUERRILLAMAILMemberMailContentLine2= driver.findElement(By.xpath(OR.GUERRILLAMAIL_MEMBER_NEW_MESSAGE_HAVING));
			String GUERRILLAMAILMemberMailContentLine2Text=GUERRILLAMAILMemberMailContentLine2.getText();
			WebElement GUERRILLAMAILMemberMailContentLine3= driver.findElement(By.xpath(OR.GUERRILLAMAIL_MEMBER_NEW_MESSAGE_FOR_ANY));
			String GUERRILLAMAILMemberMailContentLine3Text=GUERRILLAMAILMemberMailContentLine3.getText();
			String YopMailFirstName=Manipulation.condtionMatch(GuerrillaEmailFirstName,MemberFirstNameFromDB);
			System.out.println("Yopmail First Name: "+YopMailFirstName);
			String GUERRILLAMAILMessageContentLine1 = Manipulation.condtionMatch(GUERRILLAMAILMemberMailContentLine1Text, "(Please do not reply to this email.)");
			System.out.println("GUERRILLAMAIL Message Content Line1: "+GUERRILLAMAILMessageContentLine1);
			String GUERRILLAMAILMessageContentLine3 = Manipulation.condtionMatch(GUERRILLAMAILMemberMailContentLine3Text, "For any other questions, please contact your Real Appeal Coach in the message center.");
			System.out.println("GUERRILLAMAIL Message Content Line3: "+GUERRILLAMAILMessageContentLine3);
			if(Directory.zado_environment.equalsIgnoreCase("ConfigurationFile-Kulfi"))
			{
				WebElement GUERRILLAMAILMemberNewMessageBody= driver.findElement(By.xpath(OR.GUERRILLAMAIL_MEMBER_NEW_MESSAGE_MAIL_BODY));
				String GUERRILLAMAILMemberNewMessageMailBody = GUERRILLAMAILMemberNewMessageBody.getText();
				String GUERRILLAMAILMessageBody = Manipulation.condtionMatch(GUERRILLAMAILMemberNewMessageMailBody, "There's a new message waiting for you from RA - Real Appeal. Please visit https://vra-kulfi.zillion.com to view the message.");
				System.out.println("GUERRILLAMAIL New Message Body: "+GUERRILLAMAILMessageBody);
				String GUERRILLAMAILMessageContentLine2 = Manipulation.condtionMatch(GUERRILLAMAILMemberMailContentLine2Text, "Having trouble logging in? Email us at kulfi@healthfleet.com or call us at 1-844-344-REAL (7325).");
				System.out.println("GUERRILLAMAIL Message Content Line2: "+GUERRILLAMAILMessageContentLine2);
				WebElement GUERRILLAMAILMemberMailContentLine4= driver.findElement(By.xpath(OR.GUERRILLAMAIL_MEMBER_NEW_MESSAGE_SUPPORT_LINK));
				String GUERRILLAMAILMemberMailContentLine4Text=GUERRILLAMAILMemberMailContentLine4.getText();
				String GUERRILLAMAILMessageContentLine4 = Manipulation.condtionMatch(GUERRILLAMAILMemberMailContentLine4Text, "https://vra-kulfi.zillion.com");
				System.out.println("GUERRILLAMAIL Message Content Line4: "+GUERRILLAMAILMessageContentLine4);
			}
			else if(Directory.zado_environment.equalsIgnoreCase("ConfigurationFile-Test"))
			{
				WebElement GUERRILLAMAILMemberNewMessageBody= driver.findElement(By.xpath(OR.GUERRILLAMAIL_MEMBER_NEW_MESSAGE_MAIL_BODY));
				String GUERRILLAMAILMemberNewMessageMailBody = GUERRILLAMAILMemberNewMessageBody.getText();
				String GUERRILLAMAILMessageBody = Manipulation.condtionMatch(GUERRILLAMAILMemberNewMessageMailBody, "There's a new message waiting for you from RA - Real Appeal. Please visit https://testmemfe.zillion.com to view the message.");
				System.out.println("GUERRILLAMAIL New Message Body: "+GUERRILLAMAILMessageBody);
				String GUERRILLAMAILMessageContentLine2 = Manipulation.condtionMatch(GUERRILLAMAILMemberMailContentLine2Text, "Having trouble logging in? Email us at systest@healthfleet.com or call us at 1-844-344-REAL (7325).");
				System.out.println("GUERRILLAMAIL Message Content Line2: "+GUERRILLAMAILMessageContentLine2);
				WebElement GUERRILLAMAILMemberMailContentLine4Test= driver.findElement(By.xpath(OR.GUERRILLAMAIL_MEMBER_NEW_MESSAGE_SUPPORT_LINK_TEST));
				String GUERRILLAMAILMemberMailContentLine4TestText=GUERRILLAMAILMemberMailContentLine4Test.getText();
				String GUERRILLAMAILMessageContentLine4Test = Manipulation.condtionMatch(GUERRILLAMAILMemberMailContentLine4TestText, "https://testmemfe.zillion.com");
				System.out.println("GUERRILLAMAIL Message Content Line4: "+GUERRILLAMAILMessageContentLine4Test);
			}
		}
		return "Notification Email Verified";
	}

	/**
	 * Name :     LEENA P.
	 * Created Date:   03/APR/17
	 * Modified Date:
	 * Description : Create a common method to enter last name dynamic to multi environment
	 */
	public static void declinePHIPopup(WebDriver driver)
	{
		try{
			WebElement  lastname = driver.findElement(By.xpath(OR.RA_LASTNAME_AFTER_INSURANCE));
			WebElement  insuranceDecline = driver.findElement(By.xpath(OR.RA_MEMBER_PHI_DECLINE_BUTTON));
			Manipulation.click(insuranceDecline);
			wait(driver, "10");
			WebElement  nextStepButton = driver.findElement(By.xpath(OR.RA_MEMBER_STEP1_NEXTBUTTON_ENABLED));
			Manipulation.click(nextStepButton);

		}
		catch(Exception e){
			System.out.println("PHI popup not displayed");
		}
	}

	/**
	 * Name :     Bharath kumar. M
	 * Created Date:   05/APR/17
	 * Modified Date:
	 * Description : Create a common method to enter Client 2 RA Firstname in insurance popup if pop up shows
	 */
	public static void enterClient2RAFirstNameOnInsurancePopUp(WebDriver driver) 
	{
		try{
			WebElement  firstname = driver.findElement(By.xpath(OR.RA_FIRSTNAME_AFTER_INSURANCE));
			sendKeys(firstname, Directory.Client2_RA_Enrollment_First_Name);
			wait(driver, "2");
		}
		catch(Exception e){
			System.out.println("PHI popup not displayed");
		}

	}
	/**
	 * Name :     Bharath kumar. M
	 * Created Date:   05/APR/17
	 * Modified Date:
	 * Description : Create a common method to enter Client 2 RA Lastname in insurance popup if pop up shows
	 */

	public static void enterClient2RALastNameOnInsurancePopUp(WebDriver driver)
	{
		try{
			WebElement  lastname = driver.findElement(By.xpath(OR.RA_LASTNAME_AFTER_INSURANCE));
			sendKeys(lastname, Directory.Client2_RA_Enrollment_Last_Name);
			WebElement  insuranceAccept = driver.findElement(By.xpath(OR.RA_MEMBER_SETTINGS_PHI_POPUP_ACCEPT_BUTTON));
			Manipulation.click(insuranceAccept);
			wait(driver, "10");
			//WebElement  nextStepButton = driver.findElement(By.xpath(OR.RA_MEMBER_STEP1_NEXTBUTTON_ENABLED));
			//Manipulation.click(nextStepButton);

		}
		catch(Exception e){
			System.out.println("PHI popup not displayed");
		}

	}

	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:   05/APR/16
	 * Modified Date:
	 * Description : Create a common method to AssignSubtituteCoach For Scheduled Makeup session
	 * Ticket ID :
	 * Required Inputs :
	 */
	public static String assignSubtituteCoachForScheduledMakeupSession(WebDriver driver,String Starttime5)
	{
		String[] Starttime2=Starttime5.split(" ");
		String Starttime3=Starttime2[7];
		String Starttime4=Starttime3.toLowerCase().trim();
		String Sessiontime21= Starttime4.substring(0, 2);
		String Sessiontime22= Starttime4.substring(2, 6);
		String Sessiontime23=Sessiontime21+" "+Sessiontime22;
		System.out.println("Starttime: "+Sessiontime23);
		if(!Sessiontime21.equalsIgnoreCase("10")&&!Sessiontime21.equalsIgnoreCase("11")&&!Sessiontime21.equalsIgnoreCase("12"))
		{
			String Sessiontime24= Sessiontime21.substring(0).replace("0", "");
			System.out.println("1: "+Sessiontime24);
			String Sessiontime25= Starttime4.substring(2, 4);
			System.out.println("2: "+Sessiontime25);
			String Sessiontime26= Starttime4.substring(4, 6);
			String combinedString= Sessiontime24+Sessiontime25+" "+Sessiontime26;
			System.out.println("3: "+combinedString);
			WebElement substituteCoachButton = driver.findElement(By.xpath(OR.ASSIGNED_COACH_SUBSTITUTE_FOR_SCHEDULED_MAKEUP_SESSION_START_OR+combinedString+ASSIGNED_COACH_SUBSTITUTE_FOR_SCHEDULED_MAKEUP_SESSION_END_OR));
			verifyElementIsPresent(driver, substituteCoachButton);
			jsClickByXPath(driver, OR.ASSIGNED_COACH_SUBSTITUTE_FOR_SCHEDULED_MAKEUP_SESSION_START_OR+combinedString+ASSIGNED_COACH_SUBSTITUTE_FOR_SCHEDULED_MAKEUP_SESSION_END_OR);
			wait(driver, "5");
			return combinedString;
		}
		WebElement substituteCoachButton = driver.findElement(By.xpath(OR.ASSIGNED_COACH_SUBSTITUTE_FOR_SCHEDULED_MAKEUP_SESSION_START_OR+Sessiontime23+ASSIGNED_COACH_SUBSTITUTE_FOR_SCHEDULED_MAKEUP_SESSION_END_OR));
		verifyElementIsPresent(driver, substituteCoachButton);
		jsClickByXPath(driver, OR.ASSIGNED_COACH_SUBSTITUTE_FOR_SCHEDULED_MAKEUP_SESSION_START_OR+Sessiontime23+ASSIGNED_COACH_SUBSTITUTE_FOR_SCHEDULED_MAKEUP_SESSION_END_OR);
		wait(driver, "5");
		return Sessiontime23;
	}


	/**
	 * Name :     ABINAYA.P
	 * Created Date:   26/MAY/16
	 * Modified Date:
	 * Description : Create a common method to verify the mail of the member once the 101 session is cancelled
	 * Ticket ID :
	 * Required Inputs :
	 * @throws Exception 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void verifyMemberMailCSSessionCancellation(WebDriver driver, String mailid) throws Exception
	{
		wait(driver, "3");
		String Parent_Window = driver.getWindowHandle();
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
			WebElement generatedLink= driver.findElement(By.xpath(OR.RA_MEMBER_CS_MAIL_CANCELLATION));
			verifyElementIsPresent(driver, generatedLink);
			com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.RA_MEMBER_CS_MAIL_CANCELLATION);
			wait(driver, "5");

			if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
			jsClickByXPath(driver, OR.RA_MEMBER_CS_MAIL_CANCELLATION);
			wait(driver, "3");
			Navigate.switchToDefaultFrame(driver);
			WebElement iframe2= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME2));
			Navigate.switchToFrame(driver, iframe2);
			waitForElement(driver, OR.RA_MEMBER_1on1_CONTENT_CANCELLATION);
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
			WebElement generatedLink= driver.findElement(By.xpath(OR.ORBERA_COACH_1on1_MAIL_GR));
			String grSubject = ElementActions.getText(generatedLink);
			System.out.println("Subject line is" +grSubject);
			if(grSubject.contains("Customization Session Cancellation")) {
				com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_COACH_1on1_MAIL_GR);
				wait(driver, "15");
				if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
				{
					driver.get("javascript:document.getElementById('overridelink').click();");
					wait(driver, "5");
				}
				jsClickByXPath(driver, OR.ORBERA_COACH_1on1_MAIL_GR);
				wait(driver, "3");
				waitForElement(driver, OR.RA_MEMBER_1on1_CONTENT_CANCELLATION);
			}
		}
		Navigate.closeTab(driver);
		driver.switchTo().window(Parent_Window);
	}

	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:   05/APR/16
	 * Modified Date:
	 * Description : Create a common method to AssignSubtituteCoach should not be substitute For Scheduled Makeup session
	 * Ticket ID :
	 * Required Inputs :
	 */
	public static String verifyAssignedCoachShouldNotBeAllowedToSubstituteForScheduledMakeupSession(WebDriver driver,String Starttime6)
	{
		String[] Starttime2=Starttime6.split(" ");
		String Starttime3=Starttime2[7];
		String Starttime4=Starttime3.toLowerCase().trim();
		String Sessiontime21= Starttime4.substring(0, 2);
		String Sessiontime22= Starttime4.substring(2, 6);
		String Sessiontime23=Sessiontime21+" "+Sessiontime22;
		System.out.println("Starttime: "+Sessiontime23);
		if(Sessiontime21!="10"&&Sessiontime21!="11"&&Sessiontime21!="12")
		{
			String Sessiontime24= Sessiontime21.substring(0).replace("0", "");
			System.out.println("1: "+Sessiontime24);
			String Sessiontime25= Starttime4.substring(2, 4);
			System.out.println("2: "+Sessiontime25);
			String Sessiontime26= Starttime4.substring(4, 6);
			String combinedString= Sessiontime24+Sessiontime25+" "+Sessiontime26;
			System.out.println("3: "+combinedString);
			WebElement substituteCoachButton = driver.findElement(By.xpath(OR.ASSIGNED_COACH_SHOULD_NOT_BE_SUBSTITUTE_FOR_SCHEDULED_MAKEUP_SESSION_START_OR+combinedString+ASSIGNED_COACH_SHOULD_NOT_BE_SUBSTITUTE_FOR_SCHEDULED_MAKEUP_SESSION_END_OR));
			verifyElementIsPresent(driver, substituteCoachButton);
			return combinedString;
		}
		WebElement substituteCoachButton = driver.findElement(By.xpath(OR.ASSIGNED_COACH_SHOULD_NOT_BE_SUBSTITUTE_FOR_SCHEDULED_MAKEUP_SESSION_START_OR+Sessiontime23+ASSIGNED_COACH_SUBSTITUTE_FOR_SCHEDULED_MAKEUP_SESSION_END_OR));
		verifyElementIsPresent(driver, substituteCoachButton);
		return Sessiontime23;
	}

	/**
	 * Name :   Bharath Kumar
	 * Created Date:   06/APR/17
	 * Modified Date:
	 * Description : Created the common method to get Idle detection value 
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static String idleDetection(WebDriver driver) throws ClassNotFoundException, SQLException 
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
		ResultSet rs = stat.executeQuery("SELECT IS_IDLE_DETECTION_REQUIRED FROM MP_A_MASTER_PROGRAM WHERE ORGANIZATION_ID='02' AND ID='02'");
		System.out.println("query executed");
		String idlestatus="";
		if(rs.next())
		{
			idlestatus= rs.getString("IS_IDLE_DETECTION_REQUIRED");
			System.out.println(" Member EmailID is retrieved from the Table: "+idlestatus);
		}
		return idlestatus;

	}

	/**
	 * Name :   Bharath Kumar
	 * Created Date:   06/APR/17
	 * Modified Date:
	 * Description : Created the common method to turn off the Idle Detection
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static void turnOffIdleDetection(WebDriver driver) throws ClassNotFoundException, SQLException 
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
		stat.executeQuery("UPDATE MP_A_MASTER_PROGRAM SET IS_IDLE_DETECTION_REQUIRED='0' WHERE ORGANIZATION_ID='02' AND ID='02'");
		stat.executeQuery("commit");
		System.out.println("query executed");


	}
	/**
	 * Name :   Bharath Kumar
	 * Created Date:   06/APR/17
	 * Modified Date:
	 * Description : Created the common method to turn off the Idle Detection
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static void turnOnIdleDetection(WebDriver driver) throws ClassNotFoundException, SQLException 
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
		stat.executeQuery("UPDATE MP_A_MASTER_PROGRAM SET IS_IDLE_DETECTION_REQUIRED='1' WHERE ORGANIZATION_ID='02' AND ID='02'");
		stat.executeQuery("commit");
		System.out.println("query executed");


	}
	/**
	 * Name :   Bharath Kumar
	 * Created Date:   06/APR/17
	 * Modified Date:
	 * Description : Created the common method to get active member with id and email
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */

	public static String toGetActiveMemberid(WebDriver driver, String email) throws ClassNotFoundException, SQLException 	
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
		ResultSet rs = stat.executeQuery("select Id from account where email='"+email+"'");		System.out.println("query executed");
		String Id="";
		if(rs.next())
		{
			Id= rs.getString("ID");
			System.out.println(" Member ID is retrieved from the Table: "+Id);
		}
		return Id;

	}
	/**
	 * Name :   Bharath Kumar
	 * Created Date:   06/APR/17
	 * Modified Date:
	 * Description : Created the common method to get Last login date for the member
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static String toFindLastLoginDate(WebDriver driver, String id) throws ClassNotFoundException, SQLException 
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
		ResultSet rs = stat.executeQuery("SELECT LOGIN_DT_UTC,MAST_PROGRAM_ID,CLASSROOM_ID,CLASSROOM_NAME FROM SUMMARY_ACCOUNT_TODATE WHERE ACCOUNT_ID='"+id+"'");
		System.out.println("query executed");
		String LastLogin="";
		if(rs.next())
		{
			LastLogin= rs.getString("LOGIN_DT_UTC");
			System.out.println("Members Last Login Date:"+LastLogin);
		}
		return LastLogin;
	}
	/**
	 * Name :   Bharath Kumar
	 * Created Date:   06/APR/17
	 * Modified Date:
	 * Description : Created the common method to get Last login date for the member
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static void ToChangeLoginDatebydaysBehind(WebDriver driver, String id, String days) throws ClassNotFoundException, SQLException 
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
		stat.executeQuery("UPDATE SUMMARY_ACCOUNT_TODATE SET LOGIN_DT_UTC=sysdate-"+days+" WHERE ACCOUNT_ID='"+id+"'");
		System.out.println("Update query executed successfully");
		stat.executeQuery("COMMIT");
		System.out.println("Commit has been done successfully");
		System.out.println("query executed");
	}
	/**
	 * Name :     Bharath Kumar.M
	 * Created Date:   10/APR/17
	 * Modified Date:  17/APR/17
	 * Description : Create a common method to store member who switched from Active to Idle 
	 */
	static String IdleMember;
	public static void storeIdleMember(WebDriver driver, String data)
	{
		IdleMember=data;
		try {
			Properties properties = new Properties();
			properties.setProperty("FirstNotification", IdleMember);
			File file = new File(Directory.uploadFilePath+"//IdleMember.properties");
			FileOutputStream fileOut = new FileOutputStream(file);
			properties.store(fileOut, "This is to store member who is received First Notification Mail");
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(IdleMember+":Idlel member who's received First Notification Mail");
	}

	/**
	 * Name :    LEENA P.
	 * Created Date:   10/APR/17
	 * Modified Date:
	 * Description :   Create a common method to find member who had accepted PHI and now client has disabled PHI
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String memberAcceptedorDeclinedPHIandClientDisabledPHI(WebDriver driver, String inputData) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select A.EMAIL from ACCOUNT_DOCUMENT_AGREEMENT ADA, ACCOUNT_PROGRAM_MEMBERSHIP APM, ACCOUNT A, SUMMARY_ACCOUNT_TODATE SMRY  WHERE APM.ID= ADA.ACCOUNT_PROGRAM_MEMBERSHIP_ID AND APM.ACCOUNT_ID = A.ID AND APM.ACCOUNT_ID = SMRY.ACCOUNT_ID AND  SMRY.ONBOARDING_STATUS='MEMBER ONBOARDED' AND A.STATUS='Active' and ADA.IS_ACCEPTED= '"+inputData+"' AND  A.EMAIL NOT LIKE '%api%' AND A.EMAIL NOT LIKE '%QAmember%-default@yopmail.com%' and ORGANIZATION_ID= '"+Directory.Client2_RA_Organization_ID+"'");
		System.out.println("query executed");
		String memberEmailID="";
		if(rs.next())
		{
			memberEmailID= rs.getString("EMAIL");
			System.out.println(" Member EmailID is retrieved from the Table: "+memberEmailID);
		}
		return memberEmailID;
	}

	/**
	 * Name :     Vinothkumar. M
	 * Created Date:   12/APR/17
	 * Modified Date:
	 * Description : Create a common method to verify the Makeup Session Confirmation Email
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void verifyMakeupSessionConfirmationEmail(WebDriver driver, String mailid)
	{
		wait(driver, "5");
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
			if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
			WebElement iframe1= driver.findElement(By.id(OR.YOPMAIL_IFRAME2));
			Navigate.switchToFrame(driver, iframe1);
			WebElement Notificationemail= driver.findElement(By.xpath(OR.RA_MEMBER_MAKEUP_SESSION_CONFIRMATION_EMAIL_YOP));
			verifyElementIsPresent(driver, Notificationemail);
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
			WebElement	 GuerrillaMailSetButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_SET_BUTTON));
			click(GuerrillaMailSetButton);
			wait(driver, "5");
			com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.RA_MEMBER_MAKEUP_SESSION_CONFIRMATION_EMAIL_GUERRILLA);
			wait(driver, "5");

			if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
			WebElement Notificationemail= driver.findElement(By.xpath(OR.RA_MEMBER_MAKEUP_SESSION_CONFIRMATION_EMAIL_GUERRILLA));
			verifyElementIsPresent(driver, Notificationemail);
			click(Notificationemail);
			wait(driver, "5");
			WebElement confirmationEmailText= driver.findElement(By.xpath(OR.RA_MEMBER_MAKEUP_SESSION_CONFIRMATION_EMAIL_YOP));
			waitForElement(driver, RA_MEMBER_MAKEUP_SESSION_CONFIRMATION_EMAIL_YOP);
			verifyElementIsPresent(driver, confirmationEmailText);
		}
	}
	/**
	 * Name :     Bharath Kumar. M
	 * Created Date:   11/April/17
	 * Modified Date:
	 * Description : Created a common method to Retrieve Ideal member from DataBase on boarding status
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static Object retrieveIdealMemberFromDbOnboardingStatusProgramInterval(WebDriver driver,String ID) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select A.EMAIL , A.STATUS, SATD.ONBOARDING_STATUS, A.ID, SATD.PROGRAM_INTERVAL_NUMBER from ACCOUNT A, SUMMARY_ACCOUNT_TODATE SATD where A.ID= SATD.ACCOUNT_ID  AND A.EMAIL='"+ID+"'");
		System.out.println("query executed");
		String retrieveIdealMember="";
		if(rs.next())
		{
			retrieveIdealMember  = rs.getString("PROGRAM_INTERVAL_NUMBER");

		}
		System.out.println("Retrieved Ideal member from DB is "+retrieveIdealMember);
		return retrieveIdealMember;
	}
	/**
	 * Name :     Bharath Kumar. M
	 * Created Date:   11/April/17
	 * Modified Date:
	 * Description : Create a common method to verify the idle notification mail for coach
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void verifyIdleNotificationCoachEmail(WebDriver driver, String mailid)
	{
		String IdleNotificationCoachEmailBody;
		wait(driver, "5");
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
			if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
			WebElement iframe2= driver.findElement(By.id(OR.YOPMAIL_IFRAME2));
			Navigate.switchToFrame(driver, iframe2);
			WebElement Notificationemail= driver.findElement(By.xpath(OR.IDLE_NOTIFICATION_COACH_EMAIL_YOP));
			verifyElementIsPresent(driver, Notificationemail);
			IdleNotificationCoachEmailBody = Notificationemail.getText();
			System.out.println("IdleNotificationCoachEmailBody:"+IdleNotificationCoachEmailBody);
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
			WebElement	 GuerrillaMailSetButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_SET_BUTTON));
			click(GuerrillaMailSetButton);
			wait(driver, "5");
			com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.IDLE_NOTIFICATION_COACH_EMAIL_GUERRILLA);
			wait(driver, "5");

			if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
			WebElement Notificationemail= driver.findElement(By.xpath(OR.IDLE_NOTIFICATION_COACH_EMAIL_GUERRILLA));
			verifyElementIsPresent(driver, Notificationemail);
			click(Notificationemail);
			WebElement NotificationemailBody= driver.findElement(By.xpath(OR.IDLE_NOTIFICATION_COACH_EMAIL_BODY_GUERRILLA));
			verifyElementIsPresent(driver, NotificationemailBody);
			IdleNotificationCoachEmailBody = NotificationemailBody.getText();
			System.out.println("IdleNotificationCoachEmailBody:"+IdleNotificationCoachEmailBody);
		}
	}

	/**
	 * Name :     Bharath Kumar. M
	 * Created Date:   11/April/17
	 * Modified Date:
	 * Description : Create a common method to verify the idle notification mail for coach
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void verifyIdleNotificationMemberEmail(WebDriver driver, String mailid)
	{
		String IdleNotificationCoachEmailBody;
		wait(driver, "5");
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
			if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
			WebElement iframe2= driver.findElement(By.id(OR.YOPMAIL_IFRAME2));
			Navigate.switchToFrame(driver, iframe2);
			WebElement Notificationemail= driver.findElement(By.xpath(OR.IDLE_NOTIFICATION_MEMBER_EMAIL_YOP));
			verifyElementIsPresent(driver, Notificationemail);
			IdleNotificationCoachEmailBody = Notificationemail.getText();
			System.out.println("IdleNotificationCoachEmailBody:"+IdleNotificationCoachEmailBody);
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
			WebElement	 GuerrillaMailSetButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_SET_BUTTON));
			click(GuerrillaMailSetButton);
			wait(driver, "5");
			com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.IDLE_NOTIFICATION_MEMBER_EMAIL_GUERRILLA);
			wait(driver, "5");

			if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
			WebElement Notificationemail= driver.findElement(By.xpath(OR.IDLE_NOTIFICATION_MEMBER_EMAIL_GUERRILLA));
			verifyElementIsPresent(driver, Notificationemail);
			click(Notificationemail);
			WebElement NotificationemailBody= driver.findElement(By.xpath(OR.IDLE_NOTIFICATION_MEMBER_EMAIL_BODY_GUERRILLA));
			verifyElementIsPresent(driver, NotificationemailBody);
			IdleNotificationCoachEmailBody = NotificationemailBody.getText();
			System.out.println("IdleNotificationCoachEmailBody:"+IdleNotificationCoachEmailBody);
		}
	}
	/**
	 * Name :   Bharath Kumar
	 * Created Date:   06/APR/17
	 * Modified Date:
	 * Description : Created the common method to get active member with id and email
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static String toGetActiveMemberemail(WebDriver driver) throws ClassNotFoundException, SQLException 
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
		ResultSet rs = stat.executeQuery("select A.ID, A.EMAIL from ACCOUNT A, SUMMARY_ACCOUNT_TODATE SATD, ACCOUNT_IDENTITY_MAP AM where A.ID= SATD.ACCOUNT_ID AND AM.ACCOUNT_ID = A.ID AND SATD.MAST_PROGRAM_ID= '02' AND SATD.ONBOARDING_STATUS= 'MEMBER ONBOARDED' AND A.Status='Active' AND A.EMAIL not LIKE '%api%' AND A.EMAIL not like '%QAmember%-default@yopmail.com%' AND AM.ID IS NOT NULL ORDER  BY SATD.CREATED_DT");
		System.out.println("query executed");
		String Email="";
		if(rs.next())
		{
			Email= rs.getString("EMAIL");
			System.out.println(" Member EmailID is retrieved from the Table: "+Email);
		}
		return Email;

	}
	/**
	 * Name :     Bharath Kumar. M
	 * Created Date:   12/April/17
	 * Modified Date:
	 * Description : Created a common method to Retrieve Active member from DataBase on boarding status
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static Object retrieveActiveMemberFromDbOnboardingStatus(WebDriver driver) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select A.EMAIL , A.STATUS, SATD.ONBOARDING_STATUS, A.ID, SATD.PROGRAM_INTERVAL_NUMBER from ACCOUNT A, SUMMARY_ACCOUNT_TODATE SATD where A.ID= SATD.ACCOUNT_ID AND SATD.ONBOARDING_STATUS= 'MEMBER ONBOARDED'  AND SATD.PROGRAM_INTERVAL_NUMBER< '52' and A.STATUS='Active' AND A.organization_id='02' AND A.EMAIL NOT LIKE '%api%' AND A.EMAIL NOT LIKE '%info%' AND A.EMAIL not like '%QAmember%-default@yopmail.com%'");
		System.out.println("query executed");
		String retrieveIdealMember="";
		if(rs.next())
		{
			retrieveIdealMember  = rs.getString("EMAIL");

		}
		System.out.println("Retrieved Ideal member from DB is "+retrieveIdealMember);
		return retrieveIdealMember;
	}
	/**
	 * Name :     Bharath Kumar. M
	 * Created Date:   12/April/17
	 * Modified Date:
	 * Description : Created a common method to Retrieve Active member from DataBase on boarding status
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static Object retrieveActiveMemberFromDbOnboardingStatusProgramInterval(WebDriver driver) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select A.EMAIL , A.STATUS, SATD.ONBOARDING_STATUS, A.ID, SATD.PROGRAM_INTERVAL_NUMBER from ACCOUNT A, SUMMARY_ACCOUNT_TODATE SATD where A.ID= SATD.ACCOUNT_ID AND SATD.ONBOARDING_STATUS= 'MEMBER ONBOARDED'  AND SATD.PROGRAM_INTERVAL_NUMBER< '52' and A.STATUS='Active' AND A.EMAIL NOT LIKE '%api%' AND A.EMAIL NOT LIKE '%info%' AND A.EMAIL not like '%QAmember%-default@yopmail.com%'");
		System.out.println("query executed");
		String retrieveIdealMember="";
		if(rs.next())
		{
			retrieveIdealMember  = rs.getString("PROGRAM_INTERVAL_NUMBER");

		}
		System.out.println("Retrieved Ideal member from DB is "+retrieveIdealMember);
		return retrieveIdealMember;
	}

	/**
	 * Name :    LEENA P.
	 * Created Date:   12/APR/17
	 * Modified Date:
	 * Description :   Create a common method to find program completed member for Client 2
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String retrieveMemOrgIDClient2(WebDriver driver, String status) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("SELECT ACNT.ID, ACNT.EMAIL FROM SUMMARY_ACCOUNT_TODATE SMRY, ACCOUNT ACNT WHERE ACNT.ID= SMRY.ACCOUNT_ID AND ACNT.Status='Active' AND ACNT.EMAIL NOT LIKE '%api%' AND ACNT.EMAIL NOT LIKE '%QAmember%-default@yopmail.com%' AND SMRY.ONBOARDING_STATUS= '"+status+"' and ACNT.ORGANIZATION_ID= '"+Directory.Client2_RA_Organization_ID+"'and SMRY.SESSION_DATE_UTC<SYSDATE ORDER BY SMRY.SESSION_DATE_UTC DESC");
		System.out.println("query executed");
		String memberEmailID="";
		if(rs.next())
		{
			memberEmailID= rs.getString("EMAIL");
			System.out.println(" Member EmailID is retrieved from the Table: "+memberEmailID);
		}
		return memberEmailID;
	}

	/**
	 * Name :     Bharath Kumar. M
	 * Created Date:   17/April/17
	 * Modified Date:
	 * Description : Create a common method to verify the idle member first notification mail
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void verifyIdleMemberFirstAndSecondNotificationEmail(WebDriver driver, String mailid)
	{
		String IdleNotificationCoachEmailBody;
		wait(driver, "5");
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
			if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
			WebElement iframe2= driver.findElement(By.id(OR.YOPMAIL_IFRAME2));
			Navigate.switchToFrame(driver, iframe2);
			WebElement Notificationemail= driver.findElement(By.xpath(OR.IDLE_MEMBER_NOT_LOGGED_IN_NOTIFICATION_EMAIL_YOP));
			verifyElementIsPresent(driver, Notificationemail);
			IdleNotificationCoachEmailBody = Notificationemail.getText();
			System.out.println("IdleNotificationCoachEmailBody:"+IdleNotificationCoachEmailBody);
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
			WebElement	 GuerrillaMailSetButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_SET_BUTTON));
			click(GuerrillaMailSetButton);
			wait(driver, "5");
			com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.IDLE_EMAIL_NOTIFICATION_GUERRILLA);
			wait(driver, "5");

			if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
		}
	}
	/**
	 * Name :     Bharath Kumar. M
	 * Created Date:   17/April/17
	 * Modified Date:
	 * Description : Create a common method to retrieve the stored idle member from Property file
	 * @throws IOException 
	 */
	public static String retrieveStoredFirstIdleMember(WebDriver driver) throws IOException
	{
		String mailid=null;
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.uploadFilePath+"//IdleMember.properties");
		urlsProperties.load(input);
		mailid=urlsProperties.getProperty("FirstNotification").trim();
		return mailid;
	}
	/**
	 * Name :     Bharath Kumar.M
	 * Created Date:   17/APR/17
	 * Modified Date:  
	 * Description : Create a common method to store member who haven't logged in for 40 days
	 */
	static String IdleMember1;
	public static void storeIdleMember2(WebDriver driver, String data)
	{
		IdleMember1=data;
		try {
			Properties properties = new Properties();
			properties.setProperty("SecondNotification", IdleMember1);
			File file = new File(Directory.uploadFilePath+"//IdleMember1.properties");
			FileOutputStream fileOut = new FileOutputStream(file);
			properties.store(fileOut, "This is to store member who is received Second Notification Mail");
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(IdleMember+":Idlel member who's received Second Notification Mail");
	}
	/**
	 * Name :     Bharath Kumar. M
	 * Created Date:   17/April/17
	 * Modified Date:
	 * Description : Create a common method to retrieve the stored idle member1 from Property file
	 * @throws IOException 
	 */
	public static String retrieveStoredSecondIdleMember(WebDriver driver) throws IOException
	{
		String mailid=null;
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.uploadFilePath+"//IdleMember1.properties");
		urlsProperties.load(input);
		mailid=urlsProperties.getProperty("SecondNotification").trim();
		return mailid;
	}

	/**
	 * Name :     Bharath Kumar. M
	 * Created Date:   27/April/17
	 * Modified Date:
	 * Description : Create a common method to verify the PHI Consent Agreement
	 * @throws IOException 
	 */
	public static void phiConsentDetailsOfMemberIdleStatus(WebDriver driver) throws IOException
	{
		try{
			WebElement PHIConsentDetailsAgrees= driver.findElement(By.xpath(OR.IDLE_MEMBER_PHI_CONSENT_DETAILS_AGREES));
			verifyElementIsPresent(driver, PHIConsentDetailsAgrees);
		}
		catch(Exception e) {
			WebElement PHIConsentDetailsDeclines= driver.findElement(By.xpath(OR.IDLE_MEMBER_PHI_CONSENT_DETAILS_DECLINES));
			verifyElementIsPresent(driver, PHIConsentDetailsDeclines);
			wait(driver,"3");
		}
	}
	/**
	 * Name :     Bharath Kumar.M
	 * Created Date:   27/April/17
	 * Modified Date:
	 * Description :   Created a common method to find the member status
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String toGetMemberStatus(WebDriver driver, String mememail) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select STATUS from Account where email='"+mememail+"'");
		System.out.println("query executed");
		String status="";
		if(rs.next())
		{
			status= rs.getString("STATUS");
			System.out.println(" Account status retrieved from the Db is " +status);
		}
		return status;
	}



	/**
	 * Name :    LEENA P.
	 * Created Date:   26/APR/17
	 * Modified Date:
	 * Description : Create a common method to verify member Email ID from the Database
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public  static String dBMember(WebDriver driver,String member_Status, String mp_name) throws ParseException, ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select A.EMAIL as EMAIL1 from ACCOUNT A, SUMMARY_ACCOUNT_TODATE SATD where A.ID= SATD.ACCOUNT_ID AND SATD.MAST_PROGRAM_ID= '"+Directory.Mast_ProgramId+"' AND SATD.ONBOARDING_STATUS= '"+member_Status+"' and SATD.MP_Name='"+mp_name+"' AND A.EMAIL NOT LIKE '%api%' AND A.EMAIL NOT LIKE '%info%' and A.STATUS IN  ('Active') and A.EMAIL NOT LIKE 'QAmember%default@yopmail.com' ORDER BY SATD.CREATED_DT DESC");
		System.out.println("query executed");
		String mailID="";
		if(rs.next())
		{
			mailID = rs.getString("EMAIL1");
			System.out.println("DBEnrollmentstatus="+mailID);
		}
		return mailID;
	}

	/**
	 * Name :     Vinothkumar. M
	 * Created Date:   12/APR/17
	 * Modified Date:
	 * Description : Create a common method to verify RA Coach mail 1 on 1 session
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void verifyRACoachMail1on1Session(WebDriver driver, String mailid)
	{
		wait(driver, "5");
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
			if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
			WebElement iframe1= driver.findElement(By.id(OR.YOPMAIL_IFRAME2));
			Navigate.switchToFrame(driver, iframe1);
			WebElement Notificationemail= driver.findElement(By.xpath(OR.RA_COACH_ONE_ON_ONE_SESSION_CONFIRMATION_EMAIL_YOP));
			verifyElementIsPresent(driver, Notificationemail);
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
			WebElement	 GuerrillaMailSetButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_SET_BUTTON));
			click(GuerrillaMailSetButton);
			wait(driver, "5");
			com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.RA_COACH_ONE_ON_ONE_SESSION_CONFIRMATION_EMAIL_GUERRILLA);
			wait(driver, "5");

			if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
			WebElement Notificationemail= driver.findElement(By.xpath(OR.RA_COACH_ONE_ON_ONE_SESSION_CONFIRMATION_EMAIL_GUERRILLA));
			verifyElementIsPresent(driver, Notificationemail);
			click(Notificationemail);
			wait(driver, "5");
			//			WebElement confirmationEmailText= driver.findElement(By.xpath(OR.RA_MEMBER_MAKEUP_SESSION_CONFIRMATION_EMAIL_YOP));
			//			waitForElement(driver, RA_MEMBER_MAKEUP_SESSION_CONFIRMATION_EMAIL_YOP);
			//			verifyElementIsPresent(driver, confirmationEmailText);
		}
		Navigate.closeTab(driver);
		driver.switchTo().window(First_Window);
	}

	/**
	 * Name :     Vinothkumar. M
	 * Created Date:   12/APR/17
	 * Modified Date:
	 * Description : Create a common method to verify RA Coach mail 1 on 1 session
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void verifyRAMemberMail1on1Session(WebDriver driver, String mailid)
	{
		wait(driver, "5");
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
			if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
			WebElement iframe1= driver.findElement(By.id(OR.YOPMAIL_IFRAME2));
			Navigate.switchToFrame(driver, iframe1);
			WebElement Notificationemail= driver.findElement(By.xpath(OR.RA_MEMBER_ONE_ON_ONE_SESSION_CONFIRMATION_EMAIL_YOP));
			verifyElementIsPresent(driver, Notificationemail);
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
			WebElement	 GuerrillaMailSetButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_SET_BUTTON));
			click(GuerrillaMailSetButton);
			wait(driver, "5");
			com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.RA_MEMBER_ONE_ON_ONE_SESSION_CONFIRMATION_EMAIL_GUERRILLA);
			wait(driver, "5");

			if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
			WebElement Notificationemail= driver.findElement(By.xpath(OR.RA_MEMBER_ONE_ON_ONE_SESSION_CONFIRMATION_EMAIL_GUERRILLA));
			verifyElementIsPresent(driver, Notificationemail);
			click(Notificationemail);
			wait(driver, "5");
		}
		Navigate.closeTab(driver);
		driver.switchTo().window(First_Window);
	}

	/**
	 * Name : LEENA P.
	 * Created Date:   08/MAY/17
	 * Modified Date:
	 * Description : Create a common method for RA member login
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static String raMemberLoginUser(WebDriver driver, String Username)
	{
		if(Username.equals("1"))
		{
			com.zillion.qa.realappealmember.member.launchRAMemberUrl(driver);
			wait(driver, "5");
			WebElement  loginLink = driver.findElement(By.xpath(OR.RA_MEMBER_LOGIN_LINK));
			click(loginLink);
			WebElement  username = driver.findElement(By.xpath(OR.RA_MEMBER_LANDINGPAGE_USERNAME));
			sendKeys(username, Directory.RA_Member_Username1);
			WebElement  password = driver.findElement(By.xpath(OR.RA_MEMBER_LOGIN_PASSWORD));
			sendKeys(password, Directory.RA_Member_Password1);
			WebElement  loginButton = driver.findElement(By.xpath(OR.RA_MEMBER_LOGIN_BUTTON));
			click(loginButton);
			return  Directory.RA_Member_Username1;
		}
		else if(Username.equals("2")){
			com.zillion.qa.realappealmember.member.launchRAMemberUrl(driver);
			wait(driver, "5");
			WebElement  loginLink = driver.findElement(By.xpath(OR.RA_MEMBER_LOGIN_LINK));
			click(loginLink);
			WebElement  username = driver.findElement(By.xpath(OR.RA_MEMBER_LANDINGPAGE_USERNAME));
			sendKeys(username, Directory.RA_Member_username2);
			WebElement  password = driver.findElement(By.xpath(OR.RA_MEMBER_LOGIN_PASSWORD));
			sendKeys(password, Directory.RA_Member_Password1);
			WebElement  loginButton = driver.findElement(By.xpath(OR.RA_MEMBER_LOGIN_BUTTON));
			click(loginButton);
			return  Directory.RA_Member_username2;
		}else {
			return null;
		}




	}
	/**
	 * Name : Bharath kumar.M
	 * Created Date:11/May/2017
	 * Modified Date:
	 * Description :   Created a common method to verify Notification email contents for different environments
	 * Required Inputs :
	 */
	public static void verifyNotificationEmailMessageContents(WebDriver driver)
	{
		if(Directory.zado_environment.equalsIgnoreCase("ConfigurationFile-Kulfi"))
		{
			WebElement yopMailMemberNewMessageLine1 = driver.findElement(By.xpath(OR.YOPMAIL_MEMBER_NEW_MESSAGE_PLEASE_DO_NOT));
			String yopMailMemberNewMessagePleaseDoNotHave=yopMailMemberNewMessageLine1.getText();
			WebElement yopMailMemberNewMessageLine2 = driver.findElement(By.xpath(OR.YOPMAIL_MEMBER_NEW_MESSAGE_HAVING));
			String yopMailMemberNewMessageHaving=yopMailMemberNewMessageLine2.getText();
			WebElement yopMailMemberNewMessageLine3 = driver.findElement(By.xpath(OR.YOPMAIL_MEMBER_NEW_MESSAGE_FOR_ANY));
			String yopMailMemberNewMessageForAny=yopMailMemberNewMessageLine3.getText();
			WebElement yopMailMemberNewMessageLine4 = driver.findElement(By.xpath(OR.YOPMAIL_MEMBER_NEW_MESSAGE_SUPPORT_LINK));
			String yopMailMemberNewMessageSupportLink=yopMailMemberNewMessageLine4.getText();
			Manipulation.condtionMatch(yopMailMemberNewMessagePleaseDoNotHave, "(Please do not reply to this email.)");
			Manipulation.condtionMatch(yopMailMemberNewMessageHaving, "Having trouble logging in? Email us at kulfi@healthfleet.com or call us at 1-844-344-REAL (7325).");
			Manipulation.condtionMatch(yopMailMemberNewMessageForAny, "For any other questions, please contact your Real Appeal Coach in the message center.");	
			Manipulation.condtionMatch(yopMailMemberNewMessageSupportLink, "https://vra-kulfi.zillion.com");
		}
		else if(Directory.zado_environment.equalsIgnoreCase("ConfigurationFile-Test"))
		{
			WebElement yopMailMemberNewMessageLine1 = driver.findElement(By.xpath(OR.YOPMAIL_MEMBER_NEW_MESSAGE_PLEASE_DO_NOT));
			String yopMailMemberNewMessagePleaseDoNotHave=yopMailMemberNewMessageLine1.getText();
			WebElement yopMailMemberNewMessageLine2 = driver.findElement(By.xpath(OR.YOPMAIL_MEMBER_NEW_MESSAGE_HAVING));
			String yopMailMemberNewMessageHaving=yopMailMemberNewMessageLine2.getText();
			WebElement yopMailMemberNewMessageLine3 = driver.findElement(By.xpath(OR.YOPMAIL_MEMBER_NEW_MESSAGE_FOR_ANY));
			String yopMailMemberNewMessageForAny=yopMailMemberNewMessageLine3.getText();
			WebElement yopMailMemberNewMessageLine4 = driver.findElement(By.xpath(OR.YOPMAIL_MEMBER_NEW_MESSAGE_SUPPORT_LINK_TEST));
			String yopMailMemberNewMessageSupportLinkTest=yopMailMemberNewMessageLine4.getText();
			Manipulation.condtionMatch(yopMailMemberNewMessagePleaseDoNotHave, "(Please do not reply to this email.)");
			Manipulation.condtionMatch(yopMailMemberNewMessageHaving, "Having trouble logging in? Email us at systest@healthfleet.com or call us at 1-844-344-REAL (7325).");
			Manipulation.condtionMatch(yopMailMemberNewMessageForAny, "For any other questions, please contact your Real Appeal Coach in the message center.");	
			Manipulation.condtionMatch(yopMailMemberNewMessageSupportLinkTest, "https://testmemfe.zillion.com");
		}
	}
	/**
	 * Name : Bharath kumar.M
	 * Created Date:15/May/2017
	 * Modified Date:
	 * Description :   Created a common method to verify Notification email contents for different environments
	 * Required Inputs :
	 */
	public static void verifyNotificationEmailMessage(WebDriver driver,String mailid)
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
			if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
			WebElement iframe2= driver.findElement(By.id(OR.YOPMAIL_IFRAME2));
			Navigate.switchToFrame(driver, iframe2);
			if(Directory.zado_environment.equalsIgnoreCase("ConfigurationFile-Kulfi"))
			{
				WebElement yopMailMemberNewMessageLine1 = driver.findElement(By.xpath(OR.YOPMAIL_MEMBER_NEW_MESSAGE_PLEASE_DO_NOT));
				String yopMailMemberNewMessagePleaseDoNotHave=yopMailMemberNewMessageLine1.getText();
				WebElement yopMailMemberNewMessageLine2 = driver.findElement(By.xpath(OR.YOPMAIL_MEMBER_NEW_MESSAGE_HAVING));
				String yopMailMemberNewMessageHaving=yopMailMemberNewMessageLine2.getText();
				WebElement yopMailMemberNewMessageLine3 = driver.findElement(By.xpath(OR.YOPMAIL_MEMBER_NEW_MESSAGE_FOR_ANY));
				String yopMailMemberNewMessageForAny=yopMailMemberNewMessageLine3.getText();
				WebElement yopMailMemberNewMessageLine4 = driver.findElement(By.xpath(OR.YOPMAIL_MEMBER_NEW_MESSAGE_SUPPORT_LINK));
				String yopMailMemberNewMessageSupportLink=yopMailMemberNewMessageLine4.getText();
				Manipulation.condtionMatch(yopMailMemberNewMessagePleaseDoNotHave, "(Please do not reply to this email.)");
				Manipulation.condtionMatch(yopMailMemberNewMessageHaving, "Having trouble logging in? Email us at kulfi@healthfleet.com or call us at 1-844-344-REAL (7325).");
				Manipulation.condtionMatch(yopMailMemberNewMessageForAny, "For any other questions, please contact your Real Appeal Coach in the message center.");	
				Manipulation.condtionMatch(yopMailMemberNewMessageSupportLink, "https://vra-kulfi.zillion.com");
			}
			else if(Directory.zado_environment.equalsIgnoreCase("ConfigurationFile-Test"))
			{
				WebElement yopMailMemberNewMessageLine1 = driver.findElement(By.xpath(OR.YOPMAIL_MEMBER_NEW_MESSAGE_PLEASE_DO_NOT));
				String yopMailMemberNewMessagePleaseDoNotHave=yopMailMemberNewMessageLine1.getText();
				WebElement yopMailMemberNewMessageLine2 = driver.findElement(By.xpath(OR.YOPMAIL_MEMBER_NEW_MESSAGE_HAVING));
				String yopMailMemberNewMessageHaving=yopMailMemberNewMessageLine2.getText();
				WebElement yopMailMemberNewMessageLine3 = driver.findElement(By.xpath(OR.YOPMAIL_MEMBER_NEW_MESSAGE_FOR_ANY));
				String yopMailMemberNewMessageForAny=yopMailMemberNewMessageLine3.getText();
				WebElement yopMailMemberNewMessageLine4 = driver.findElement(By.xpath(OR.YOPMAIL_MEMBER_NEW_MESSAGE_SUPPORT_LINK_TEST));
				String yopMailMemberNewMessageSupportLinkTest=yopMailMemberNewMessageLine4.getText();
				Manipulation.condtionMatch(yopMailMemberNewMessagePleaseDoNotHave, "(Please do not reply to this email.)");
				Manipulation.condtionMatch(yopMailMemberNewMessageHaving, "Having trouble logging in? Email us at systest@healthfleet.com or call us at 1-844-344-REAL (7325).");
				Manipulation.condtionMatch(yopMailMemberNewMessageForAny, "For any other questions, please contact your Real Appeal Coach in the message center.");	
				Manipulation.condtionMatch(yopMailMemberNewMessageSupportLinkTest, "https://testmemfe.zillion.com");
			}
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
			WebElement	 GuerrillaMailSetButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_SET_BUTTON));
			click(GuerrillaMailSetButton);
			wait(driver, "5");
			com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.IDLE_EMAIL_NOTIFICATION_GUERRILLA);
			wait(driver, "5");

			if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
			WebElement Notificationemail= driver.findElement(By.xpath(OR.IDLE_EMAIL_NOTIFICATION_GUERRILLA));
			verifyElementIsPresent(driver, Notificationemail);
			click(Notificationemail);
			wait(driver, "5");
			WebElement GUERRILLAMAILMemberMailContentLine1= driver.findElement(By.xpath(OR.GUERRILLAMAIL_MEMBER_NEW_MESSAGE_PLEASE_DO_NOT));
			String GUERRILLAMAILMemberMailContentLine1Text=GUERRILLAMAILMemberMailContentLine1.getText();
			WebElement GUERRILLAMAILMemberMailContentLine2= driver.findElement(By.xpath(OR.GUERRILLAMAIL_MEMBER_NEW_MESSAGE_HAVING));
			String GUERRILLAMAILMemberMailContentLine2Text=GUERRILLAMAILMemberMailContentLine2.getText();
			WebElement GUERRILLAMAILMemberMailContentLine3= driver.findElement(By.xpath(OR.GUERRILLAMAIL_MEMBER_NEW_MESSAGE_FOR_ANY));
			String GUERRILLAMAILMemberMailContentLine3Text=GUERRILLAMAILMemberMailContentLine3.getText();
			String GUERRILLAMAILMessageContentLine1 = Manipulation.condtionMatch(GUERRILLAMAILMemberMailContentLine1Text, "(Please do not reply to this email.)");
			System.out.println("GUERRILLAMAIL Message Content Line1: "+GUERRILLAMAILMessageContentLine1);
			String GUERRILLAMAILMessageContentLine3 = Manipulation.condtionMatch(GUERRILLAMAILMemberMailContentLine3Text, "For any other questions, please contact your Real Appeal Coach in the message center.");
			System.out.println("GUERRILLAMAIL Message Content Line3: "+GUERRILLAMAILMessageContentLine3);
			if(Directory.zado_environment.equalsIgnoreCase("ConfigurationFile-Kulfi"))
			{
				WebElement GUERRILLAMAILMemberNewMessageBody= driver.findElement(By.xpath(OR.GUERRILLAMAIL_MEMBER_NEW_MESSAGE_MAIL_BODY));
				String GUERRILLAMAILMemberNewMessageMailBody = GUERRILLAMAILMemberNewMessageBody.getText();
				String GUERRILLAMAILMessageBody = Manipulation.condtionMatch(GUERRILLAMAILMemberNewMessageMailBody, "There's a new message waiting for you from RA - Real Appeal. Please visit https://vra-kulfi.zillion.com to view the message.");
				System.out.println("GUERRILLAMAIL New Message Body: "+GUERRILLAMAILMessageBody);
				String GUERRILLAMAILMessageContentLine2 = Manipulation.condtionMatch(GUERRILLAMAILMemberMailContentLine2Text, "Having trouble logging in? Email us at kulfi@healthfleet.com or call us at 1-844-344-REAL (7325).");
				System.out.println("GUERRILLAMAIL Message Content Line2: "+GUERRILLAMAILMessageContentLine2);
				WebElement GUERRILLAMAILMemberMailContentLine4= driver.findElement(By.xpath(OR.GUERRILLAMAIL_MEMBER_NEW_MESSAGE_SUPPORT_LINK));
				String GUERRILLAMAILMemberMailContentLine4Text=GUERRILLAMAILMemberMailContentLine4.getText();
				String GUERRILLAMAILMessageContentLine4 = Manipulation.condtionMatch(GUERRILLAMAILMemberMailContentLine4Text, "https://vra-kulfi.zillion.com");
				System.out.println("GUERRILLAMAIL Message Content Line4: "+GUERRILLAMAILMessageContentLine4);
			}
			else if(Directory.zado_environment.equalsIgnoreCase("ConfigurationFile-Test"))
			{
				WebElement GUERRILLAMAILMemberNewMessageBody= driver.findElement(By.xpath(OR.GUERRILLAMAIL_MEMBER_NEW_MESSAGE_MAIL_BODY));
				String GUERRILLAMAILMemberNewMessageMailBody = GUERRILLAMAILMemberNewMessageBody.getText();
				String GUERRILLAMAILMessageBody = Manipulation.condtionMatch(GUERRILLAMAILMemberNewMessageMailBody, "There's a new message waiting for you from RA - Real Appeal. Please visit https://testmemfe.zillion.com to view the message.");
				System.out.println("GUERRILLAMAIL New Message Body: "+GUERRILLAMAILMessageBody);
				String GUERRILLAMAILMessageContentLine2 = Manipulation.condtionMatch(GUERRILLAMAILMemberMailContentLine2Text, "Having trouble logging in? Email us at systest@healthfleet.com or call us at 1-844-344-REAL (7325).");
				System.out.println("GUERRILLAMAIL Message Content Line2: "+GUERRILLAMAILMessageContentLine2);
				WebElement GUERRILLAMAILMemberMailContentLine4Test= driver.findElement(By.xpath(OR.GUERRILLAMAIL_MEMBER_NEW_MESSAGE_SUPPORT_LINK_TEST));
				String GUERRILLAMAILMemberMailContentLine4TestText=GUERRILLAMAILMemberMailContentLine4Test.getText();
				String GUERRILLAMAILMessageContentLine4Test = Manipulation.condtionMatch(GUERRILLAMAILMemberMailContentLine4TestText, "https://testmemfe.zillion.com");
				System.out.println("GUERRILLAMAIL Message Content Line4: "+GUERRILLAMAILMessageContentLine4Test);
			}
		}
	}

}
