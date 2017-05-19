package com.zillion.qa.realappealmember;

import java.awt.AWTException;
import java.awt.Robot;
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
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;
import com.zillion.qa.commands.Manipulation;
import com.zillion.qa.commands.Navigate;
import com.zillion.qa.utils.Directory;
import com.zillion.qa.utils.Platform;

public class RAOneOnOneLiveSessionSubCommonMethods extends Manipulation implements OR
{

	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:   24/MAY/16
	 * Modified Date:   
	 * Description : Create a common method to retrieve Member who is available Schedule button for RA 1on1 live session
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public  static String retrieveAvailableScheduleMember(WebDriver driver,String testData) throws ParseException, ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("SELECT ACNT.EMAIL Member_Email, PROV.EMAIL Coach_Email FROM SUMMARY_ACCOUNT_TODATE SMRY, PROVIDER PROV, ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID AND SMRY.ACCOUNT_ID=ACNT.ID AND SMRY.ONBOARDING_STATUS='MEMBER ONBOARDED' AND ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ACCOUNT_PROGRAM.START_DT_TIME>SYSDATE + INTERVAL '-50' DAY AND ID IN (SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_STATUS in ('"+inputSessionStatus+"') AND PROGRAM_INTERVAL_START_DT<SYSDATE AND PROGRAM_INTERVAL_END_DT>SYSDATE AND SESSION_TYPE_ID='"+inputSessionType+"')) AND ACNT.EMAIL not like '%api%' AND ACNT.EMAIL not like '%QAmember%-default@yopmail.com%' AND PROV.EMAIL not like '%api%' AND ACNT.STATUS='Active' ORDER BY SMRY.SESSION_DATE_UTC ASC");
		System.out.println("query executed");
		String retrievedscheduleAvailableMemberEmail="";
		String retrievedscheduleAvailableMemberAssignedCoachEmail="";
		String result="";
		if(rs.next())
		{
			retrievedscheduleAvailableMemberEmail= rs.getString("MEMBER_EMAIL"); 
			retrievedscheduleAvailableMemberAssignedCoachEmail = rs.getString("COACH_EMAIL");
			System.out.println("Available Member Email is "+retrievedscheduleAvailableMemberEmail);
			System.out.println("Available Member Email Assigned coach Email is "+retrievedscheduleAvailableMemberAssignedCoachEmail);
			result=retrievedscheduleAvailableMemberEmail+","+retrievedscheduleAvailableMemberAssignedCoachEmail;
		}
		else
		{
			result=""; 
		}
		System.out.println("result "+result);
		return result;  
	}

	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   24/MAY/16
	 * Modified Date:   
	 * Description : Create a common method to Login for RA 1on1 Livesession with retrieve Email and Password
	 * Ticket ID :     
	 * Required Inputs :  Member URL, Username and Password
	 * @throws ParseException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String realAppealOneOnOneLiveSessionMemberLogin(WebDriver driver,String result) throws ClassNotFoundException, ParseException, SQLException 
	{
		String memberEmail[]=result.split(",");
		String retrievedscheduleAvailableMemberEmail=memberEmail[0]; 
		wait(driver, "5");
		System.out.println("Retrieved member is: " +retrievedscheduleAvailableMemberEmail);
		Navigate.get(driver, Directory.RA_Member_Url);
		wait(driver, "5");
		Navigate.maximize(driver);
		wait(driver, "5");
		waitForElement( driver, OR.RA_MEMBER_BEFORE_LOGIN_BUTTON );
		WebElement member_LoginButton= driver.findElement(By.xpath(OR.RA_MEMBER_BEFORE_LOGIN_BUTTON));
		verifyElementIsPresent(driver, member_LoginButton);
		click(member_LoginButton);
		WebElement memberUsername= driver.findElement(By.xpath(OR.RA_MEMBER_USERNAME_TEXTBOX));
		verifyElementIsPresent(driver, memberUsername);
		WebElement memberPassword= driver.findElement(By.xpath(OR.RA_MEMBER_PASSWORD_TEXTBOX));
		verifyElementIsPresent(driver, memberPassword);
		WebElement memberLoginButton= driver.findElement(By.xpath(OR.RA_MEMBERLIVESESSION_LOGIN_BUTTON));
		verifyElementIsPresent(driver, memberLoginButton);
		sendKeys(memberUsername,retrievedscheduleAvailableMemberEmail);
		try
		{
			wait(driver, "2");
			sendKeys(memberPassword,"Password1");
			wait(driver, "2");
			click(memberLoginButton);
			wait(driver, "2");
			try
			{
				wait(driver, "2");
				clear( memberPassword );
				sendKeys(memberPassword,"Password2");
				wait(driver, "2");
				click(memberLoginButton);
				wait(driver, "2");
				try
				{
					wait(driver, "2");
					clear( memberPassword );
					sendKeys(memberPassword,"Zillion2016");
					wait(driver, "2");
					click(memberLoginButton);
					wait(driver, "3");
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
		Navigate.waitTime(driver, "20");
		System.out.println("Member Logged in successfully");
		return retrievedscheduleAvailableMemberEmail;
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
	 *
	 */
	public static String realAppealmemberGearSettings(WebDriver driver)
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

		if(OSName.contains("WINDOWS"))
		{
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
		WebElement memberSettingsMicrophoneActivityLabel = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_MICROPHONE_ACTIVITY_LABEL));
		WebElement memberSettingsMicEnableCheckbox = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_MIC_ENABLE_CHECKBOX));
		verifyElementIsPresent(driver, memberSettingsMicrophoneLabel);
		verifyElementIsPresent(driver, memberSettingsSpeakersLabel);
		verifyElementIsPresent(driver, memberSettingsMicrophoneActivityLabel);
		verifyElementIsPresent(driver, memberSettingsMicEnableCheckbox);
		verifyElementIsPresent(driver, memberSettingsMicDropdown);
		verifyElementIsPresent(driver, memberSettingsSpeakerDropdown);
		jsClickByXPath(driver, OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_CLOSE_BUTTON);
		wait(driver, "3" );
		return ElementWait;
	}

	/**
	 * Name :Vigneshraj     
	 * Created Date: 11/Mar/2016  
	 * Modified Date:  
	 * Description : Create a common method to RA Live Session 1on1 Coach Login
	 * Ticket ID :   
	 * Required Inputs :  Email from the DB 
	 * @throws ParseException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public  static void raOneOnOneLiveSessionCoachLogin(WebDriver driver1,String result) throws ClassNotFoundException, ParseException, SQLException
	{
		String memberCoach[]=result.split(",");
		String retrievedscheduleAvailableCoachEmail=memberCoach[1]; 
		wait(driver1, "3");
		Navigate.get(driver1, Directory.RA_Provider_Url);
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
		sendKeys(coach_username,retrievedscheduleAvailableCoachEmail);
		try
		{
			wait(driver1, "2");
			sendKeys(coach_password,"Healthfleet2015");
			wait(driver1, "2");
			click(Coacheslogin_button);
			wait(driver1, "2");
			try{
				WebElement userSessionPopup = driver1.findElement(By.xpath(PROVIDER_USER_SESSION_POPUP));
				click(userSessionPopup);
				System.out.println("Coaches Logged in successfully");
			}
				catch(Exception e) {
				System.out.println("Coaches Logged in successfully");
			}
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
		
	}

	/**
	 * Name : VinothKumar.M    
	 * Created Date: 26/May/2016  
	 * Modified Date:     
	 * Description : Create a common method to retrieve RA 1on1 Session Attendance with Session ID
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 * @throws ParseException 
	 *
	 */
	public static String retrive1on1SessionAttendanceStatusWithSessionID(WebDriver driver,String result) throws ClassNotFoundException, SQLException, ParseException  

	{
		String memberEmail[]=result.split(",");
		String retrievedscheduleAvailableMemberEmail=memberEmail[0]; 
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select attendance_host_arrival, attendance_host_departure, attendance_host_minutes, attendance_prtcpnt_arrival, attendance_prtcpnt_departure, attendance_prtcpnt_minutes, session_status, session_actual_start_datetime from calendar_event where account_id=(select ID from account where email ='"+retrievedscheduleAvailableMemberEmail+"') and session_type='1on1' and calendar_event.SESSION_STATUS='Completed'");
		System.out.println("query executed");
		while(rs.next())
		{
			String retrieve1on1SessionAttendanceHostArrival= rs.getString("ATTENDANCE_HOST_ARRIVAL");
			String retrieve1on1SessionAttendanceHostDeparture= rs.getString("ATTENDANCE_HOST_DEPARTURE");
			String retrieve1on1SessionAttendanceParticipantArrival= rs.getString("ATTENDANCE_PRTCPNT_ARRIVAL");
			String retrieve1on1SessionAttendanceParticipantDeparture= rs.getString("ATTENDANCE_PRTCPNT_DEPARTURE");
			String retrieve1on1SessionAttendanceParticipantMinutes= rs.getString("ATTENDANCE_PRTCPNT_MINUTES");
			String retrieve1on1SessionSessionStatus= rs.getString("SESSION_STATUS");
			String retrieve1on1SessionSessionActualStartDateTime= rs.getString("SESSION_ACTUAL_START_DATETIME");
			System.out.println("retrieve1on1SessionAttendanceParticipantMinutes :"+retrieve1on1SessionAttendanceParticipantMinutes);
			String currenttime = new SimpleDateFormat("dd-MMM-yy").format(Calendar.getInstance().getTime());
			String currenttime1 = currenttime.toUpperCase();
			System.out.println("Current Date :"+currenttime1);

			// Retrieve Attendance Host Arrival time
			String retrieve1on1SessionAttendanceHostArrival1[]= retrieve1on1SessionAttendanceHostArrival.split( " " );
			String retrieve1on1SessionAttendanceHostArrival2= retrieve1on1SessionAttendanceHostArrival1[0];
			if(retrieve1on1SessionAttendanceHostArrival2.equalsIgnoreCase( currenttime1 ))
			{
				System.out.println("Current Time and Host Arrival time are matched: "+retrieve1on1SessionAttendanceHostArrival2);
			}

			// Retrieve Attendance Actual Start time
			String retrieve1on1SessionSessionActualStartDateTime1[]= retrieve1on1SessionSessionActualStartDateTime.split( " " );
			String retrieve1on1SessionSessionActualStartDateTime2= retrieve1on1SessionSessionActualStartDateTime1[0];
			if(retrieve1on1SessionSessionActualStartDateTime2.equalsIgnoreCase( currenttime1 ))
			{
				System.out.println("Current Time and Participant Start Date time are matched: "+retrieve1on1SessionSessionActualStartDateTime2);
			}

			// Retrieve Attendance Host departure time
			String retrieve1on1SessionAttendanceHostDeparture1[]= retrieve1on1SessionAttendanceHostDeparture.split( " " );
			String retrieve1on1SessionAttendanceHostDeparture2= retrieve1on1SessionAttendanceHostDeparture1[0];
			if(retrieve1on1SessionAttendanceHostDeparture2.equalsIgnoreCase( currenttime1 ))
			{
				System.out.println("Current Time and Host Departure time are matched: "+retrieve1on1SessionAttendanceHostDeparture2);
			}

			// Retrieve Attendance Participant Arrival time
			String retrieve1on1SessionAttendanceParticipantArrival1[]= retrieve1on1SessionAttendanceParticipantArrival.split( " " );
			String retrieve1on1SessionAttendanceParticipantArrival2= retrieve1on1SessionAttendanceParticipantArrival1[0];
			if(retrieve1on1SessionAttendanceParticipantArrival2.equalsIgnoreCase( currenttime1 ))
			{
				System.out.println("Current Time and Participant Arrival time are matched: "+retrieve1on1SessionAttendanceParticipantArrival2);
			}

			// Retrieve Attendance Participant Departure time
			String retrieve1on1SessionAttendanceParticipantDeparture1[]= retrieve1on1SessionAttendanceParticipantDeparture.split( " " );
			String retrieve1on1SessionAttendanceParticipantDeparture2= retrieve1on1SessionAttendanceParticipantDeparture1[0];
			if(retrieve1on1SessionAttendanceParticipantDeparture2.equalsIgnoreCase( currenttime1 ))
			{
				System.out.println("Current Time and Participant Departure time are matched: "+retrieve1on1SessionAttendanceParticipantDeparture2);
			}

			// Retrieve Attendance Host Minutes
			int SessionParticipantMinutes= Integer.parseInt( retrieve1on1SessionAttendanceParticipantMinutes );
			if (SessionParticipantMinutes>=1)
			{
				System.out.println("Session Participant Minutes is greater than 1 :"+SessionParticipantMinutes);
			}

			// Retrieve Session status

			String completedStatus= "Completed";
			if (completedStatus.equalsIgnoreCase( retrieve1on1SessionSessionStatus ))

			{
				System.out.println("Session status is completed :"+retrieve1on1SessionSessionStatus);
			}
		}
		return ElementWait;
	}

	/**
	 * Name : Vinothkumar.M    
	 * Created Date: 30/May/2016  
	 * Modified Date:     
	 * Description : Create a common method to Retrieve Member account ID
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String accountId=null;

	// Retrieve account ID from the DB with the Member Username
	// Member Username is given as input from the Spreadsheet
	// With the help of the accont Id we can retrieve Program ID
	public  static String retrieveMemberAccountId(WebDriver driver,String result) throws ParseException, ClassNotFoundException, SQLException
	{
		String memberEmail[]=result.split(",");
		String retrievedscheduleAvailableMemberEmail=memberEmail[0]; 
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
		ResultSet rs = stat.executeQuery("SELECT ID, NAME, EMAIL, ORGANIZATION_ID, STATUS FROM ACCOUNT WHERE EMAIL LIKE'"+retrievedscheduleAvailableMemberEmail+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			accountId= rs.getString("ID"); 
			System.out.println("Member Account id "+accountId+" is retrieved from the Table");
		}
		return accountId;
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
	public  static void raMemberOneOnOneSessionAppendURL(WebDriver driver)
	{
		String getCurrentURL= driver.getCurrentUrl();
		String appendCurrentURL= getCurrentURL+"?attendnow";
		System.out.println("Append URL to force attend the 1on1 session: "+appendCurrentURL);
		Navigate.get( driver, appendCurrentURL );
		wait( driver, "5" );
	}

	/**
	 * Name : VigneshRaj.M    
	 * Created Date: 30/May/2016  
	 * Modified Date:     
	 * Description : Create a common method to Plugin Alert accept pop up
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public  static void pluginAlertAccept(WebDriver driver)
	{
		try
		{
			Navigate.alertAccept( driver ); 
		}
		catch(Exception e)
		{

		}
	}

	/**
	 * Name : VigneshRaj.M    
	 * Created Date: 30/May/2016  
	 * Modified Date:     
	 * Description : Create a common method to Coach append URL
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public  static void raCoachOneOnOneSessionAppendURL(WebDriver driver)
	{
		String getCoachCurrentURL= driver.getCurrentUrl();
		String appendCoachCurrentURL= getCoachCurrentURL+"?attendnow";
		Navigate.get( driver, appendCoachCurrentURL );
	}

	/**
	 * Name : VIGNESHRAJ.M    
	 * Created Date: 31/MAY/2016  
	 * Modified Date:     
	 * Description : Common method to 1on1 Live Session Coach Mic Enable/Disable from spread sheet in Place
	 * Ticket ID :     
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String coachRAOneOnOneSessionMicEnableAndDisable(WebDriver driver,String testData)
	{
		String[] testData1 = testData.split(",");
		String inputCoachMic=testData1[0];

		if ("CoachMicEnable".equalsIgnoreCase(inputCoachMic) ) 
		{ 
			try
			{
				jsClickByXPath(driver, OR.COACH_SESSION_MIC_OFF);
				WebElement coachMicOn = driver.findElement(By.xpath(OR.COACH_SESSION_MIC_ON));
				verifyElementIsPresent(driver, coachMicOn);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver, OR.COACH_SESSION_MIC_ON);
				jsClickByXPath(driver, OR.COACH_SESSION_MIC_OFF);
				WebElement coachMicOn = driver.findElement(By.xpath(OR.COACH_SESSION_MIC_ON));
				verifyElementIsPresent(driver, coachMicOn);
			}
		}

		else if ( "CoachMicDisable".equalsIgnoreCase(inputCoachMic) )
		{
			try
			{
				jsClickByXPath(driver, OR.COACH_SESSION_MIC_ON);
				WebElement coachMicOff = driver.findElement(By.xpath(OR.COACH_SESSION_MIC_OFF));
				verifyElementIsPresent(driver, coachMicOff);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver, OR.COACH_SESSION_MIC_OFF);
				jsClickByXPath(driver, OR.COACH_SESSION_MIC_ON);
				WebElement coachMicOff = driver.findElement(By.xpath(OR.COACH_SESSION_MIC_OFF));
				verifyElementIsPresent(driver, coachMicOff);
			}
		}
		return testData;
	}

	/**
	 * Name : VINOTHKUMAR.M    
	 * Created Date: 31/MAY/2016  
	 * Modified Date:     
	 * Description :   Common method to 1on1 Live session Coach Video Enable/Disable from spread sheet in Place
	 * Ticket ID :     
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String coachRAOneOnOneSessionVideoEnableAndDisable(WebDriver driver,String testData)
	{
		String[] testData1 = testData.split(",");
		String inputCoachVideo=testData1[0];
		if ("CoachVideoEnable".equalsIgnoreCase(inputCoachVideo) ) 
		{ 
			try
			{
				jsClickByXPath(driver, OR.COACH_SESSION_VIDEO_OFF);
				WebElement coachVideoOn = driver.findElement(By.xpath(OR.COACH_SESSION_VIDEO_ON));
				verifyElementIsPresent(driver, coachVideoOn);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver, OR.COACH_SESSION_VIDEO_ON);
				jsClickByXPath(driver, OR.COACH_SESSION_VIDEO_OFF);
				WebElement coachVideoOn = driver.findElement(By.xpath(OR.COACH_SESSION_VIDEO_ON));
				verifyElementIsPresent(driver, coachVideoOn);
			}

		}

		else if ( "CoachVideoDisable".equalsIgnoreCase(inputCoachVideo) )
		{
			try
			{
				jsClickByXPath(driver, OR.COACH_SESSION_VIDEO_ON);
				WebElement coachVideoOff = driver.findElement(By.xpath(OR.COACH_SESSION_VIDEO_OFF));
				verifyElementIsPresent(driver, coachVideoOff);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver, OR.COACH_SESSION_VIDEO_OFF);
				jsClickByXPath(driver, OR.COACH_SESSION_VIDEO_ON);
				WebElement coachVideoOff = driver.findElement(By.xpath(OR.COACH_SESSION_VIDEO_OFF));
				verifyElementIsPresent(driver, coachVideoOff);
			}
		}
		return testData;
	}

	/**
	 * Name : VINOTHKUMAR.M    
	 * Created Date: 31/MAY/2016  
	 * Modified Date:     
	 * Description :   Common method to 1on1 Live Session Member Mic Enable/Disable from spread sheet in Place
	 * Ticket ID :     
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String memberRAOneonOneSessionMicEnableAndDisable(WebDriver driver,String testData)
	{
		String[] testData1 = testData.split(",");
		String inputMemberMic=testData1[0];
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
		return testData;
	}

	/**
	 * Name : VINOTHKUMAR.M  
	 * Created Date: 31/MAY/2016  
	 * Modified Date:     
	 * Description :   Common method to 1on1 Live Session Video Enable/Disable from spread sheet in Place [5]
	 * Ticket ID :     
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String memberRAOneonOneSessionVideoEnableAndDisable(WebDriver driver,String testData)
	{
		String[] testData1 = testData.split(",");
		String inputMemberVideo=testData1[0];
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
		return testData;
	}

	/**
	 * Name : VINOTHKUMAR.M  
	 * Created Date: 31/MAY/2016  
	 * Modified Date:     
	 * Description :   Common method to coach verify Member Mic
	 * Ticket ID :     
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String coachRAOneonOneSessionVerifyMemberMic(WebDriver driver,String testData)
	{
		String[] testData1 = testData.split(",");
		String inputMemberMic=testData1[0];

		if ("MemberMicDisable".equalsIgnoreCase(inputMemberMic))  
		{
			WebElement micDisabled = driver.findElement(By.xpath(OR.COACH_SESSION_MIC_DISABLED));
			verifyElementIsPresent(driver, micDisabled);
		}
		else if ( "MemberMicEnable".equalsIgnoreCase(inputMemberMic) )
		{
			WebElement micEnabled = driver.findElement(By.xpath(OR.COACH_SESSION_MIC_ENABLED));
			verifyElementIsPresent(driver, micEnabled);
		}
		return testData;
	}

	/**
	 * Name : VIGNESHRAJ.M  
	 * Created Date: 31/MAY/2016  
	 * Modified Date:     
	 * Description :   Common method to coach verify Member Mic
	 * Ticket ID :     
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String coachRAOneonOneSessionVerifyMemberVideo(WebDriver driver,String testData)
	{
		String[] testData1 = testData.split(",");
		String inputMemberVideo=testData1[0];

		if ("MemberVideoEnable".equalsIgnoreCase(inputMemberVideo) )  
		{
			WebElement cameraEnabled = driver.findElement(By.xpath(OR.COACH_SESSION_CAMERA_ENABLED));
			verifyElementIsPresent(driver, cameraEnabled);
		}
		else if ( "MemberVideoDisable".equalsIgnoreCase(inputMemberVideo) )
		{
			WebElement cameraDisabled = driver.findElement(By.xpath(OR.COACH_SESSION_CAMERA_DISABLED));
			verifyElementIsPresent(driver, cameraDisabled);
		}
		return testData;
	}

	/**
	 * Name : VIGNESHRAJ.M  
	 * Created Date: 31/MAY/2016  
	 * Modified Date:     
	 * Description :   Common method to Check Browser Icon Coach Toolbar compare with Input member Browser
	 * Ticket ID :     
	 * Required Inputs :
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String browserIconToolbar(WebDriver driver)
	{
		if (Directory.browser.equalsIgnoreCase( "firefox" ))  
		{
			WebElement firefoxBrowserIcon = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_FIREFOX_BROWSER_ICON));
			verifyElementIsPresent(driver, firefoxBrowserIcon);
			jsClickByXPath(driver, OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_FIREFOX_BROWSER_ICON);
			wait( driver, "3" );
		}
		else if (Directory.browser.equalsIgnoreCase( "ie" ))
		{
			WebElement ieBrowserIcon = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_IE_BROWSER_ICON));
			verifyElementIsPresent(driver, ieBrowserIcon);
			jsClickByXPath(driver, OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_IE_BROWSER_ICON);
			wait( driver, "3" );
		}
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
	 *
	 */
	public static String raCoachGearSettingsForExcel(WebDriver driver)
	{
		WebElement memberSettingsGearIcon = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_BUTTON));
		verifyElementIsPresent(driver, memberSettingsGearIcon);
		jsClickByXPath(driver, OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_BUTTON);
		wait(driver, "2" );
		WebElement CoachSettingsCloseButton = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_COACH_SETTINGS_CLOSE_BUTTON));
		WebElement memberSettingsVideoOption = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_VIDEO_OPTION));
		WebElement memberSettingsAudioOption = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_AUDIO_OPTION));
		WebElement memberSettingsCameraLabel = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_CAMERA_LABEL));
		WebElement memberSettingsCameraDropdown = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_CAMERA_DROPDOWN));
		WebElement memberSettingsCameraOption = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_CAMERA_OPTION));
		WebElement memberSettingsCheckConnectivityOption = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_CHECK_CONNECTIVITY_BUTTON));
		verifyElementIsPresent(driver, CoachSettingsCloseButton);
		verifyElementIsPresent(driver, memberSettingsVideoOption);
		verifyElementIsPresent(driver, memberSettingsAudioOption);
		verifyElementIsPresent(driver, memberSettingsCameraLabel);
		verifyElementIsPresent(driver, memberSettingsCameraDropdown);
		click(memberSettingsCameraDropdown);
		wait(driver, "1" );
		verifyElementIsPresent(driver, memberSettingsCameraOption);
		verifyElementIsPresent(driver, memberSettingsCheckConnectivityOption);
		jsClickByXPath(driver, OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_AUDIO_OPTION);
		wait(driver, "2" );
		WebElement memberSettingsMicrophoneLabel = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_MICROPHONE_LABEL));
		WebElement memberSettingsSpeakersVolumeLabel = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_SPEAKERS_VOLUME_LABEL));
		WebElement memberSettingsSpeakersLabel = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_SPEAKERS_LABEL));
		WebElement memberSettingsMicDropdown = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_MIC_DROPDOWN));
		WebElement memberSettingsSpeakerDropdown = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_SPEAKER_DROPDOWN));
		WebElement memberSettingsSlider = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_SLIDER));
		WebElement memberSettingsPlayTestSoundButton = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_PLAY_TEST_SOUND_BUTTON));
		WebElement memberSettingsMicrophoneVolumeLabel = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_MICROPHONE_VOLUME_LABEL));
		WebElement memberSettingsMicrophoneActivityLabel = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_MICROPHONE_ACTIVITY_LABEL));
		WebElement memberSettingsMicEnableCheckbox = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_MIC_ENABLE_CHECKBOX));
		verifyElementIsPresent(driver, memberSettingsMicrophoneLabel);
		verifyElementIsPresent(driver, memberSettingsSpeakersLabel);
		verifyElementIsPresent(driver, memberSettingsSpeakersVolumeLabel);
		verifyElementIsPresent(driver, memberSettingsMicrophoneVolumeLabel);
		verifyElementIsPresent(driver, memberSettingsMicrophoneActivityLabel);
		verifyElementIsPresent(driver, memberSettingsMicEnableCheckbox);
		verifyElementIsPresent(driver, memberSettingsMicDropdown);
		verifyElementIsPresent(driver, memberSettingsSpeakerDropdown);
		verifyElementIsPresent(driver, memberSettingsSlider);
		verifyElementIsPresent(driver, memberSettingsPlayTestSoundButton);
		jsClickByXPath(driver, OR.LIVESESSION_1ON1_SESSION_COACH_SETTINGS_CLOSE_BUTTON);
		wait(driver, "3" );
		return ElementWait;
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
	 *
	 */
	public static String getSessionID=null;
	public static String getCurrentURL(WebDriver driver)
	{
		String getCurrentURL= driver.getCurrentUrl();
		String getCurrentURL1= getCurrentURL.substring(getCurrentURL.indexOf("=") + 1);     
		String[] getCurrentURL2=getCurrentURL1.split("&");
		getSessionID=  getCurrentURL2[0];
		System.out.println("Session ID from the URL: "+getSessionID);
		return getSessionID;
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
	 *
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
	 * Description :   Create a common method to Rating Notes from DB match with given notes.
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
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
		ResultSet rs = stat.executeQuery("select DT, NOTE_TYPE, CONTENT, EVENT_ID, ACCOUNT_ID, CATEGORY from Account_NOTE where EVENT_ID = '"+getSessionID+"' and DT like '"+currentdate1+" % %'");
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
	 * Description :   Create a common method to Rating notes from DB match with given notes.
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
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
		ResultSet rs = stat.executeQuery("select Notes_logged_DT, Note_type, EVENT_ID, ACCOUNT_ID, SESSION_TYPE, INTERVAL_NUMBER, CONTENT from ACCOUNTS_PROVIDER_NOTE where account_id = '"+accountId+"' and EVENT_ID ='"+getSessionID+"' and Notes_logged_DT like '"+currentdate1+" % %' ");
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
	 * Name :     VINOTHKUMAR.M
	 * Created Date:  9/JUNE/16
	 * Modified Date:   
	 * Description : Create a common method to retrieve Member for RA Group live session
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public  static String retrieveAvailableGroupSessionScheduleMember(WebDriver driver,String testData) throws ParseException, ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("SELECT ACNT.EMAIL Member_Email, PROV.EMAIL Coach_Email FROM SUMMARY_ACCOUNT_TODATE SMRY, PROVIDER PROV, ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID AND SMRY.ACCOUNT_ID=ACNT.ID AND SMRY.ONBOARDING_STATUS='MEMBER ONBOARDED' AND ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ACCOUNT_PROGRAM.START_DT_TIME>SYSDATE + INTERVAL '-50' DAY AND ID IN (SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_STATUS!=('"+inputSessionStatus+"') AND PROGRAM_INTERVAL_START_DT<SYSDATE AND PROGRAM_INTERVAL_END_DT>SYSDATE AND SESSION_TYPE_ID='"+inputSessionType+"'))AND ACNT.EMAIL not like '%api%' AND PROV.EMAIL not like '%api%' AND ACNT.EMAIL not like '%QAmember%-default@yopmail.com%' and ACNT.STATUS IN ('Active') ORDER BY SMRY.SESSION_DATE_UTC DESC");
		System.out.println("query executed");
		String retrievedscheduleAvailableMemberEmail="";
		String retrievedscheduleAvailableMemberAssignedCoachEmail="";
		String result="";
		if(rs.next())
		{
			retrievedscheduleAvailableMemberEmail= rs.getString("MEMBER_EMAIL"); 
			retrievedscheduleAvailableMemberAssignedCoachEmail = rs.getString("COACH_EMAIL");
			System.out.println("Available Member Email is "+retrievedscheduleAvailableMemberEmail);
			System.out.println("Available Member Email Assigned coach Email is "+retrievedscheduleAvailableMemberAssignedCoachEmail);
			result=retrievedscheduleAvailableMemberEmail+","+retrievedscheduleAvailableMemberAssignedCoachEmail;
		}
		else
		{
			result=","; 
		}
		System.out.println("result "+result);
		return result;  
	}

	/**
	 * Name :     Vinothkumar.M
	 * Created Date:   9/JUNE/16
	 * Modified Date:   
	 * Description : Create a common method to Login for RA Group Livesession with retrieve Email and Password
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ParseException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String realAppealGroupLiveSessionMemberLogin(WebDriver driver) throws ClassNotFoundException, ParseException, SQLException 
	{
		wait(driver, "5");
		Navigate.get(driver, Directory.RA_Member_Url);
		wait(driver, "5");
		waitForElement( driver, OR.RA_MEMBER_BEFORE_LOGIN_BUTTON );
		WebElement member_LoginButton= driver.findElement(By.xpath(OR.RA_MEMBER_BEFORE_LOGIN_BUTTON));
		verifyElementIsPresent(driver, member_LoginButton);
		click(member_LoginButton);
		WebElement memberUsername= driver.findElement(By.xpath(OR.RA_MEMBER_USERNAME_TEXTBOX));
		verifyElementIsPresent(driver, memberUsername);
		WebElement memberPassword= driver.findElement(By.xpath(OR.RA_MEMBER_PASSWORD_TEXTBOX));
		verifyElementIsPresent(driver, memberPassword);
		WebElement memberLoginButton= driver.findElement(By.xpath(OR.RA_MEMBERLIVESESSION_LOGIN_BUTTON));
		verifyElementIsPresent(driver, memberLoginButton);
		sendKeys(memberUsername,Member_Email_ID);
		try
		{
			wait(driver, "2");
			sendKeys(memberPassword,"Password1");
			wait(driver, "2");
			click(memberLoginButton);
			wait(driver, "2");
			try
			{
				wait(driver, "2");
				clear( memberPassword );
				sendKeys(memberPassword,"Password2");
				wait(driver, "2");
				click(memberLoginButton);
				wait(driver, "2");
				try
				{
					wait(driver, "2");
					clear( memberPassword );
					sendKeys(memberPassword,"Zillion2016");
					wait(driver, "2");
					click(memberLoginButton);
					wait(driver, "3");
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
		Navigate.waitTime(driver, "20");
		System.out.println("Member Logged in successfully");
		return ElementWait;
	}

	/**
	 * Name :Vinothkumar.M    
	 * Created Date: 11/Mar/2016  
	 * Modified Date:  
	 * Description : Create a common method to RA Group Live Session Coach Login
	 * Ticket ID :   
	 * Required Inputs :  Email from the DB 
	 * @throws ParseException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public  static void raAppealGroupLiveSessionCoachLogin(WebDriver driver1,String result) throws ClassNotFoundException, ParseException, SQLException
	{
		String memberCoach[]=result.split(",");
		String retrievedscheduleAvailableCoachEmail=memberCoach[1]; 
		wait(driver1, "3");
		Navigate.get(driver1, Directory.RA_Provider_Url);
		wait(driver1, "5");
//		driver1.get("javascript:document.getElementById('overridelink').click();"); 
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
		sendKeys(coach_username,retrievedscheduleAvailableCoachEmail);
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
	 * Name : Vinothkumar.M    
	 * Created Date: 9/June/2016  
	 * Modified Date:     
	 * Description : Create a common method to Member Append URL to force attend the session
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public  static void raMemberGroupSessionAppendURL(WebDriver driver1)
	{
		String getCurrentURL= driver1.getCurrentUrl();
		String appendCurrentURL= getCurrentURL+"?attendnow";
		System.out.println("Append URL to force attend the Group session: "+appendCurrentURL);
		Navigate.get( driver1, appendCurrentURL );
		wait( driver1, "5" );
	}

	/**
	 * Name : Vinothkumar.M    
	 * Created Date: 13/June/2016  
	 * Modified Date:     
	 * Description : Create a common method to retrieve available member
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String Member_Email_ID=null;
	public static String classesRetrieveAvailableMember(WebDriver driver1)
	{
		WebElement CLASSESTAB= driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_CLASSES_TAB));
		click(CLASSESTAB);
		wait(driver1, "20");
		WebElement programSelectionDropdown=driver1.findElement(By.xpath(OR.RA_COACH_CLASSES_TAB_PROGRAM_DROPDOWN));
		selectByValue(programSelectionDropdown, "RA - Real Appeal");
		WebElement goButton =driver1.findElement(By.xpath(OR.RA_COACH_CLASSES_TAB_GO_BUTTON));
		click(goButton);
		wait(driver1, "5");
		WebElement startDateFilter =driver1.findElement(By.xpath(OR.RA_COACH_CLASSES_TAB_START_DATE_FILTER));
		click(startDateFilter);
		wait(driver1, "5");
		WebElement coachClassesTabChevronLink= driver1.findElement(By.xpath(OR.COACH_CLASSES_TAB_CHEVRON_LINK));
		click(coachClassesTabChevronLink);
		wait(driver1, "10");
		WebElement coachClassesTabMemberName= driver1.findElement(By.xpath(OR.COACH_CLASSES_TAB_MEMBER_NAME));
		click(coachClassesTabMemberName);
		wait(driver1, "10");
		WebElement Email_ID= driver1.findElement(By.xpath(COACH_CLASSES_TAB_MEMBER_PROFILE_EMAIL));
		Member_Email_ID=Email_ID.getAttribute("value");
		System.out.println("email"+Member_Email_ID);
		return Member_Email_ID;
	}

	/**
	 * Name : Vinothkumar.M 
	 * Created Date: 13/June/2016  
	 * Modified Date:     
	 * Description :   Common method to Group Live Session Coach Member Tile Mic Enable/Disable from spread sheet 
	 * Ticket ID :     
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String raGroupSessionCoachMemberTileMicEnable(WebDriver driver1,String testData)
	{
		String[] testData1 = testData.split(",");
		String inputCoachMemberTileMic=testData1[10];

		if ("CoachMemberTileMicEnable".equalsIgnoreCase(inputCoachMemberTileMic) ) 
		{
			jsClickByXPath(driver1, OR.COACH_RA_GROUP_SESSION_COACH_TILE_MEMBER_MIC_DISABLED);  
			WebElement coachTileMemberMicEnabled = driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_COACH_TILE_MEMBER_MIC_ENABLED));
			verifyElementIsPresent(driver1, coachTileMemberMicEnabled);
		}
		else if ( "CoachMemberTileMicDisable".equalsIgnoreCase(inputCoachMemberTileMic) )
		{
			jsClickByXPath(driver1, OR.COACH_RA_GROUP_SESSION_COACH_TILE_MEMBER_MIC_ENABLED);
			WebElement coachTileMemberMicDisabled = driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_COACH_TILE_MEMBER_MIC_DISABLED));
			verifyElementIsPresent(driver1, coachTileMemberMicDisabled);
		}
		return testData;
	}
	/**
	 * Name : Vinothkumar.M 
	 * Created Date: 14/June/2016  
	 * Modified Date:     
	 * Description :   Common method to Group Live Session Member Raise hand Enable/Disable from spread sheet
	 * Ticket ID :     
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String raGroupSessionMemberRaiseHand(WebDriver driver,String testData)
	{
		String[] testData1 = testData.split(",");
		String inputMemberRaiseHand=testData1[11];

		if ("MemberRaiseHandEnable".equalsIgnoreCase(inputMemberRaiseHand) ) 
		{
			jsClickByXPath(driver, OR.MEMBER_RA_GROUP_SESSION_RAISE_HAND);
			WebElement memberLowerHand = driver.findElement(By.xpath(OR.MEMBER_RA_GROUP_SESSION_LOWER_HAND));
			verifyElementIsPresent(driver, memberLowerHand);
		}
		else if ( "MemberRaiseHandDisable".equalsIgnoreCase(inputMemberRaiseHand) )
		{
			jsClickByXPath(driver, OR.MEMBER_RA_GROUP_SESSION_RAISE_HAND);
			jsClickByXPath(driver, OR.MEMBER_RA_GROUP_SESSION_LOWER_HAND);
			WebElement memberRaiseHand = driver.findElement(By.xpath(OR.MEMBER_RA_GROUP_SESSION_RAISE_HAND));
			verifyElementIsPresent(driver, memberRaiseHand);
		}

		return ElementWait;

	}
	/**
	 * Name : Vinothkumar.M 
	 * Created Date: 14/June/2016  
	 * Modified Date:     
	 * Description :   Common method to Group session quiz link
	 * Ticket ID :     
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static void raGroupSessionQuizLink(WebDriver driver)
	{ 
		jsClickByXPath(driver,  OR.COACH_RA_GROUP_SESSION_CONTENT_BUTTON);
		wait(driver, "5" );
		WebElement QuizLink= driver.findElement(By.xpath(COACH_CONTENT_QUIZ_ARTICLES));
		verifyElementIsPresent(driver, QuizLink);
		getWindow1(driver, QuizLink);
		wait( driver, "8" );
		WebElement Quiz_Question = driver.findElement(By.xpath(OR.COACH_CONTENT_QUIZ_ARTICLES_QUESTION));
		verifyElementIsPresent(driver,  Quiz_Question);
		WebElement Quiz_Answer_option2 = driver.findElement(By.xpath(OR.COACH_CONTENT_QUIZ_ARTICLES_ANSWER_OPTION2));
		verifyElementIsPresent(driver,  Quiz_Answer_option2);
		wait( driver, "2" );
		click(Quiz_Answer_option2);
		WebElement Quiz_Answer_Submit = driver.findElement(By.xpath(OR.COACH_CONTENT_QUIZ_ARTICLES_ANSWER_SUBMIT_BUTTON));
		click(Quiz_Answer_Submit);
		wait( driver, "3" );
		WebElement Quiz_score_header = driver.findElement(By.xpath(OR.COACH_CONTENT_QUIZ_ARTICLES_QUIZ_SCORE));
		verifyElementIsPresent(driver,  Quiz_score_header);
		wait( driver, "2" );
		WebElement Quiz_wrong_answer = driver.findElement(By.xpath(OR.COACH_CONTENT_QUIZ_ARTICLES_QUIZ_WRONG_ANSWER));
		verifyElementIsPresent(driver,  Quiz_wrong_answer);
		driver.close();
		driver.switchTo().window(Main_Window); 
		try{Thread.sleep(1000);}
		catch(InterruptedException e){e.printStackTrace();}
		wait( driver, "2" );
		getWindow1(driver, QuizLink);
		wait( driver, "4" );
		WebElement Quiz_Answer_option1 = driver.findElement(By.xpath(OR.COACH_CONTENT_QUIZ_ARTICLES_ANSWER_OPTION1));
		verifyElementIsPresent(driver,  Quiz_Answer_option1);
		click(Quiz_Answer_option1);
		jsClickByXPath(driver, OR.COACH_CONTENT_QUIZ_ARTICLES_ANSWER_SUBMIT_BUTTON);
		wait( driver, "4" );
		WebElement Quiz_Right_answer = driver.findElement(By.xpath(OR.COACH_CONTENT_QUIZ_ARTICLES_QUIZ_RIGHT_ANSWER));
		verifyElementIsPresent(driver,  Quiz_Right_answer);
		driver.close();
		driver.switchTo().window(Main_Window); 
		try{Thread.sleep(1000);}
		catch(InterruptedException e){e.printStackTrace();}
		wait( driver, "2" );
	}

	/**
	 * Name : Vinothkumar.M 
	 * Created Date: 14/June/2016  
	 * Modified Date:     
	 * Description :   Common method to Group session Contract link
	 * Ticket ID :     
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static void raGroupSessionContractLink(WebDriver driver1)
	{ 
		WebElement ContractLink= driver1.findElement(By.xpath(COACH_CONTENT_CONTRACT_ARTICLES_LINK));
		verifyElementIsPresent(driver1, ContractLink);
		getWindow1(driver1, ContractLink);
		wait( driver1, "2" );
		WebElement Contract_main_header = driver1.findElement(By.xpath(OR.COACH_CONTENT_CONTRACT_ARTICLES_COMMITMENT_CONTRACT_MAIN_HEADER));
		verifyElementIsPresent(driver1,  Contract_main_header);
		WebElement Contract_header = driver1.findElement(By.xpath(OR.COACH_CONTENT_CONTRACT_ARTICLES_HEADER));
		verifyElementIsPresent(driver1,  Contract_header);
		WebElement Contract_sub_header = driver1.findElement(By.xpath(OR.COACH_CONTENT_CONTRACT_ARTICLES_SUB_HEADER));
		verifyElementIsPresent(driver1,  Contract_sub_header);
		wait( driver1, "2" );
		WebElement RA_shopping_list = driver1.findElement(By.xpath(OR.COACH_CONTENT_CONTRACT_ARTICLES_REAL_APPEAL_SHOPPING_LIST_LABEL));
		verifyElementIsPresent(driver1,  RA_shopping_list);
		WebElement Nutrition_Facts_label = driver1.findElement(By.xpath(OR.COACH_CONTENT_CONTRACT_ARTICLES_NUTRITION_FACTS_LABEL));
		verifyElementIsPresent(driver1,  Nutrition_Facts_label);
		WebElement ingredient_lists_label = driver1.findElement(By.xpath(OR.COACH_CONTENT_CONTRACT_ARTICLES_EXAMINE_THE_INGREDIENT_LISTS_LABEL));
		verifyElementIsPresent(driver1,  ingredient_lists_label);
		WebElement my_other_healthy_habit = driver1.findElement(By.xpath(OR.COACH_CONTENT_CONTRACT_ARTICLES_CONTINUE_MY_OTHER_HEALTHY_HEALTHY_HABITS_LABEL));
		verifyElementIsPresent(driver1,  my_other_healthy_habit );
		wait( driver1, "2" );
		jsClickByXPath(driver1, OR.COACH_CONTENT_CONTRACT_ARTICLES_I_COMMIT_BUTTON);
		wait( driver1, "2" );
		WebElement Error_msg = driver1.findElement(By.xpath(OR.COACH_CONTENT_CONTRACT_ARTICLES_QUESTION_IS_REQUIRED_ERROR_MSG));
		verifyElementIsPresent(driver1,  Error_msg );
		wait( driver1, "2" );
		wait(driver1, "2");
		int SelectAllCheckbox = driver1.findElements(By.xpath("//div[@class='sg-question-set']/div[*]//span//label/strong")).size();
		for(int i=1; i<=SelectAllCheckbox ;i++)
		{
			WebElement SelectAllCheckbox1 = driver1.findElement(By.xpath("//div[@class='sg-question-set']/div["+i+"]//span//label/strong"));
			click(SelectAllCheckbox1);
			wait(driver1, "2"); 
		}
		jsClickByXPath(driver1, OR.COACH_CONTENT_CONTRACT_ARTICLES_I_COMMIT_BUTTON);
		wait(driver1, "3");
		WebElement success_msg = driver1.findElement(By.xpath(OR.COACH_CONTENT_CONTRACT_ARTICLES_SUCCESS_MSG));
		verifyElementIsPresent(driver1,  success_msg );
		WebElement Signup_commited_contract = driver1.findElement(By.xpath(OR.COACH_CONTENT_CONTRACT_ARTICLES_SIGNED_UP_FOR_YOUR_COMMITMENT_CONTRACT_MSG));
		verifyElementIsPresent(driver1,  Signup_commited_contract  );
		driver1.close();
		driver1.switchTo().window(Main_Window); 
		try{Thread.sleep(1000);}
		catch(InterruptedException e){e.printStackTrace();}
		wait( driver1, "2" );
	}
	/**
	 * Name : Vinothkumar.M 
	 * Created Date: 14/June/2016  
	 * Modified Date:     
	 * Description :   Common method to Group session Guide link
	 * Ticket ID :     
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public  static void raGroupSessionGuideLink(WebDriver driver1) throws AWTException
	{
		WebElement guideLink= driver1.findElement(By.xpath(OR.COACH_CONTENT_SESSION_GUIDE_ARTICLES_LINK));
		verifyElementIsPresent(driver1, guideLink);
		getWindow1(driver1, guideLink);
		wait(driver1, "10" );
		WebElement guideFullScreen= driver1.findElement(By.xpath(COACH_SESSION_GUIDE_FULLSCREEN));
		WebElement guideAutomaticZoom= driver1.findElement(By.xpath(COACH_SESSION_GUIDE_AUTOMATICZOOM));
		WebElement guidePagination =driver1.findElement(By.xpath(COACH_SESSION_GUIDE_PAGINATION));
		WebElement guidePrint =driver1.findElement(By.xpath(COACH_SESSION_GUIDE_PRINT));
		WebElement guideDownload =driver1.findElement(By.xpath(COACH_SESSION_GUIDE_DOWNLOAD));
		WebElement guideToggleSlideBar =driver1.findElement(By.xpath(COACH_SESSION_GUIDE_TOGGLE_SLIDEBAR));
		verifyElementIsPresent(driver1, guideFullScreen);
		verifyElementIsPresent(driver1, guideAutomaticZoom);
		verifyElementIsPresent(driver1, guidePagination);
		verifyElementIsPresent(driver1, guidePrint);
		verifyElementIsPresent(driver1, guideDownload);
		verifyElementIsPresent(driver1, guideToggleSlideBar);
		jsClickByXPath(driver1, OR.COACH_SESSION_PAGNUMBER_INPUT);
		wait( driver1, "3" );
		Manipulation.jsClickByXPath(driver1, OR.COACH_SESSION_PAGNUMBER_INPUT);
		wait( driver1, "3" );
		jsClickByXPath(driver1, OR.COACH_SESSION_GUIDE_TOGGLE_SLIDEBAR);
		wait( driver1, "5" );
		jsClickByXPath(driver1, OR.COACH_SESSION_GUIDE_TOGGLE_SLIDEBAR);
		wait( driver1, "5" );
		jsClickByXPath(driver1, OR.COACH_SESSION_GUIDE_FULLSCREEN);
		wait( driver1, "3" );
		/*Actions actionObject1 = new Actions(driver1);
		actionObject1.sendKeys(Keys.ESCAPE).build().perform();*/
		Robot esc= new Robot();
		esc.keyPress(KeyEvent.VK_ESCAPE);
		esc.keyRelease(KeyEvent.VK_ESCAPE);
		wait( driver1, "3" );
		jsClickByXPath(driver1, OR.COACH_SESSION_GUIDE_SECONDARY_TOOLBAR);
		wait( driver1, "3" );
		jsClickByXPath(driver1, OR.COACH_SESSION_GUIDE_ROTATE_CLOCKWISE);
		wait( driver1, "3" );
		jsClickByXPath(driver1, OR.COACH_SESSION_GUIDE_SECONDARY_TOOLBAR);
		wait( driver1, "3" );
		jsClickByXPath(driver1, OR.COACH_SESSION_GUIDE_ROTATE_ANTICLOCKWISE);
		wait( driver1, "3" );
		jsClickByXPath(driver1, OR.COACH_SESSION_GUIDE_SECONDARY_TOOLBAR);
		wait( driver1, "3" );
		jsClickByXPath(driver1, OR.COACH_SESSION_GUIDE_DOCUMENT_PROPERTIES);
		wait( driver1, "3" );
		jsClickByXPath(driver1, OR.COACH_SESSION_GUIDE_DOCUMENT_PROPERTIES_POPUP_CLOSE);
		driver1.close();
		driver1.switchTo().window(Main_Window); 
		try{Thread.sleep(1000);}
		catch(InterruptedException e){e.printStackTrace();}
		wait( driver1, "2" );
	}

	/**
	 * Name : Vinothkumar.M 
	 * Created Date: 14/June/2016  
	 * Modified Date:     
	 * Description :   Common method to Group session Video link
	 * Ticket ID :     
	 * Required Inputs :
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public  static void raGroupSessionVideoLink(WebDriver driver1) throws AWTException
	{
		wait( driver1, "2" );      
		jsClickByXPath(driver1, OR.COACH_RA_GROUP_SESSION_VIDEO_BUTTON);   
		wait( driver1, "2" );  
		WebElement videoLink= driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_VIDEO));
		verifyElementIsPresent(driver1, videoLink);
		click(videoLink);
		wait(driver1, "5" );
		WebElement play_button= driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_PLAY_VIDEO_BUTTON));
		verifyElementIsPresent(driver1, play_button);
		WebElement Pause_button= driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_PAUSE_BUTTON));
		verifyElementIsPresent(driver1, Pause_button);
		WebElement Backward_btn= driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_JUMP_BACK_THIRTY_SECONDS_BUTTON));
		verifyElementIsPresent(driver1, Backward_btn);
		WebElement forward_btn= driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_JUMP_FORWARD_THIRTY_SECOND_BUTTON));
		verifyElementIsPresent(driver1, forward_btn);
		WebElement Refresh_btn= driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_RESTART_FROM_THE_BEGINNING_BUTTON));
		verifyElementIsPresent(driver1, Refresh_btn);
		WebElement Eject_btn= driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_EJECT_VIDEO_BUTTON));
		verifyElementIsPresent(driver1, Eject_btn);
		wait( driver1, "5" );
		click(play_button);
		wait(driver1, "10" );
		click(Pause_button);
		wait(driver1, "10" );
		click( forward_btn);
		wait(driver1, "10" );
		click( Backward_btn);
		wait(driver1, "10" );
		click( Refresh_btn);
		wait(driver1, "10" );
		click( Eject_btn);
		wait(driver1, "10" );
	}

	/**
	 * Name : Vinothkumar.M 
	 * Created Date: 14/June/2016  
	 * Modified Date:     
	 * Description :   Common method to Group session script link
	 * Ticket ID :     
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public  static void raGroupSessionScriptLink(WebDriver driver1) throws AWTException
	{
		wait( driver1, "2" ); 
		WebElement Script_btn= driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_SCRIPT_BUTTON));
		verifyElementIsPresent(driver1, Script_btn);
		jsClickByXPath(driver1, OR.COACH_RA_GROUP_SESSION_SCRIPT_BUTTON);  
		wait(driver1, "5" );
		WebElement Script_pdf_link= driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_SCRIPT_LINK));
		verifyElementIsPresent(driver1, Script_pdf_link);
		getWindow(driver1, Script_pdf_link);
		wait(driver1, "5" );
		WebElement guideFullScreen= driver1.findElement(By.xpath(COACH_SESSION_GUIDE_FULLSCREEN));
		WebElement guideAutomaticZoom= driver1.findElement(By.xpath(COACH_SESSION_GUIDE_AUTOMATICZOOM));
		WebElement guidePagination =driver1.findElement(By.xpath(COACH_SESSION_GUIDE_PAGINATION));
		WebElement guidePrint =driver1.findElement(By.xpath(COACH_SESSION_GUIDE_PRINT));
		WebElement guideDownload =driver1.findElement(By.xpath(COACH_SESSION_GUIDE_DOWNLOAD));
		WebElement guideToggleSlideBar =driver1.findElement(By.xpath(COACH_SESSION_GUIDE_TOGGLE_SLIDEBAR));
		verifyElementIsPresent(driver1, guideFullScreen);
		verifyElementIsPresent(driver1, guideAutomaticZoom);
		verifyElementIsPresent(driver1, guidePagination);
		verifyElementIsPresent(driver1, guidePrint);
		verifyElementIsPresent(driver1, guideDownload);
		verifyElementIsPresent(driver1, guideToggleSlideBar);
		jsClickByXPath(driver1, OR.COACH_SESSION_PAGNUMBER_INPUT);
		wait( driver1, "3" );
		Manipulation.jsClickByXPath(driver1, OR.COACH_SESSION_PAGNUMBER_INPUT);
		wait( driver1, "3" );
		jsClickByXPath(driver1, OR.COACH_SESSION_GUIDE_TOGGLE_SLIDEBAR);
		wait( driver1, "5" );
		jsClickByXPath(driver1, OR.COACH_SESSION_GUIDE_TOGGLE_SLIDEBAR);
		wait( driver1, "5" );
		jsClickByXPath(driver1, OR.COACH_SESSION_GUIDE_FULLSCREEN);
		wait( driver1, "3" );
/*		Actions actionObject1 = new Actions(driver1);
		actionObject1.sendKeys(Keys.ESCAPE).build().perform();*/
		Robot esc= new Robot();
		esc.keyPress(KeyEvent.VK_ESCAPE);
		esc.keyRelease(KeyEvent.VK_ESCAPE);
		wait( driver1, "3" );
		jsClickByXPath(driver1, OR.COACH_SESSION_GUIDE_SECONDARY_TOOLBAR);
		wait( driver1, "3" );
		jsClickByXPath(driver1, OR.COACH_SESSION_GUIDE_ROTATE_CLOCKWISE);
		wait( driver1, "3" );
		jsClickByXPath(driver1, OR.COACH_SESSION_GUIDE_SECONDARY_TOOLBAR);
		wait( driver1, "3" );
		jsClickByXPath(driver1, OR.COACH_SESSION_GUIDE_ROTATE_ANTICLOCKWISE);
		wait( driver1, "3" );
		jsClickByXPath(driver1, OR.COACH_SESSION_GUIDE_SECONDARY_TOOLBAR);
		wait( driver1, "3" );
		jsClickByXPath(driver1, OR.COACH_SESSION_GUIDE_DOCUMENT_PROPERTIES);
		wait( driver1, "3" );
		jsClickByXPath(driver1, OR.COACH_SESSION_GUIDE_DOCUMENT_PROPERTIES_POPUP_CLOSE);
		driver1.close();
		driver1.switchTo().window(Main_Window); 
		try{Thread.sleep(1000);}
		catch(InterruptedException e){e.printStackTrace();}
		wait( driver1, "2" );
	}

	/**
	 * Name : Vinothkumar.M 
	 * Created Date: 14/June/2016  
	 * Modified Date:     
	 * Description :   Common method to RealAppeal Member Account ID for GroupSession
	 * Ticket ID :     
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	// Retrieve account ID from the DB with the Member Username
	// Member Username is given as input from the Spreadsheet
	// With the help of the accont Id we can retrieve Program ID
	public  static String raGroupSessionRetrieveMemberAccountId(WebDriver driver) throws ParseException, ClassNotFoundException, SQLException
	{
		String memberEmail[]=Member_Email_ID.split(",");
		String retrievedMemberEmail=memberEmail[0]; 
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
		ResultSet rs = stat.executeQuery("SELECT ID, NAME, EMAIL, ORGANIZATION_ID, STATUS FROM ACCOUNT WHERE EMAIL LIKE'"+retrievedMemberEmail+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			accountId= rs.getString("ID"); 
			System.out.println("Member Account id "+accountId+" is retrieved from the Table");
		}
		return accountId;
	}

	/**
	 * Name : Vinothkumar.M 
	 * Created Date: 21/June/2016  
	 * Modified Date:     
	 * Description :   Common method to Get Member session time
	 * Ticket ID :     
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	static String sessiontime1=null;
	public  static String raMemberSessionTime(WebDriver driver1) throws ParseException
	{
		WebElement memberSessionTime = driver1.findElement(By.xpath(OR.RA_MEMBER_SESSION_TIME));
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
	 * Name : Vinothkumar.M 
	 * Created Date: 21/June/2016  
	 * Modified Date:     
	 * Description :   Common method to attend the RA Group Live session 
	 * Ticket ID :     
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public  static void raCoachUpcomingSessionTime(WebDriver driver1) throws ParseException
	{

		WebElement coachUpcomingSessionTime = driver1.findElement(By.xpath("//tr//td[text()='"+sessiontime1+"']/following::td[text()='Group']/following-sibling::td//following-sibling::td//following-sibling::td/following-sibling::td/div/a"));
		verifyElementIsPresent(driver1, coachUpcomingSessionTime); 
		click(coachUpcomingSessionTime);
		wait( driver1, "20" );
	}

	/**
	 * Name : Vinothkumar.M 
	 * Created Date: 21/June/2016  
	 * Modified Date:     
	 * Description : Common method to Allow system Plugins.Click Allow and Allow&Remember
	 * Ticket ID :     
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String allowPlugins(WebDriver driver) throws FindFailed, AWTException
	{
		Screen s=new Screen();
		Pattern image = new Pattern("C:/workspace/zillion_automation/Zillion/testcases/UploadFiles/Allownow.png");
		s.click(image);
		wait( driver, "3" );
		Robot rb = new Robot();
		rb.keyPress(KeyEvent.VK_ALT); 
		rb.keyPress(KeyEvent.VK_R);
		rb.keyPress(KeyEvent.VK_ALT); 
		rb.keyPress(KeyEvent.VK_R);
		wait( driver, "2" );
		return ElementWait;
	}

	/**
	 * Name : Vinothkumar.M 
	 * Created Date: 21/June/2016  
	 * Modified Date:     
	 * Description :   Common method to RA Group Live Session Coach Member Mute/Unmute button 
	 * Ticket ID :     
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String raGroupSessionCoachMuteMembersButton(WebDriver driver1,String testData)
	{
		String[] testData1 = testData.split(",");
		String inputMuteMemberButton=testData1[2];

		if ("CoachMembersMutebutton".equalsIgnoreCase(inputMuteMemberButton) ) 
		{
			jsClickByXPath(driver1, OR.COACH_RA_GROUP_SESSION_COACH_TILE_MEMBER_MIC_DISABLED);  
			WebElement coachTileMemberMicEnabled = driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_COACH_TILE_MEMBER_MIC_ENABLED));
			verifyElementIsPresent(driver1, coachTileMemberMicEnabled);
			jsClickByXPath(driver1, OR.COACH_RA_GROUP_SESSION_MUTE_MEMBERS_BUTTON); 
			WebElement coachTileMemberMicDisabled = driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_COACH_TILE_MEMBER_MIC_DISABLED));
			verifyElementIsPresent(driver1, coachTileMemberMicDisabled);
		}
		else if ( "CoachMembersUnMutebutton".equalsIgnoreCase(inputMuteMemberButton) )
		{
			jsClickByXPath(driver1, OR.COACH_RA_GROUP_SESSION_COACH_TILE_MEMBER_MIC_ENABLED);
			jsClickByXPath(driver1, OR.COACH_RA_GROUP_SESSION_COACH_TILE_MEMBER_MIC_DISABLED);  
			WebElement coachTileMemberMicDisabled = driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_COACH_TILE_MEMBER_MIC_DISABLED));
			verifyElementIsPresent(driver1, coachTileMemberMicDisabled);
		}
		return testData;
	}

	/**
	 * Name : Vinothkumar.M 
	 * Created Date: 21/June/2016  
	 * Modified Date:     
	 * Description :   Common method to Login as Member with reference step for LiveSession
	 * Ticket ID :     
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String retrieveMember(WebDriver driver, String result)
	{
		String memberEmail[]=result.split(",");
		String retrievedscheduleAvailableEmail=memberEmail[0]; 
		wait(driver, "3");
		System.out.print("Member:"+retrievedscheduleAvailableEmail);
		WebElement memberUsername= driver.findElement(By.xpath(OR.RA_MEMBER_USERNAME_TEXTBOX));
		verifyElementIsPresent(driver, memberUsername);
		sendKeys(memberUsername,retrievedscheduleAvailableEmail);
		WebElement memberPassword= driver.findElement(By.xpath(OR.RA_MEMBER_PASSWORD_TEXTBOX));
		verifyElementIsPresent(driver, memberPassword);
		WebElement memberLoginButton= driver.findElement(By.xpath(OR.RA_MEMBERLIVESESSION_LOGIN_BUTTON));
		verifyElementIsPresent(driver, memberLoginButton);
		try
		{

			wait(driver, "2");
			sendKeys(memberPassword,"Password1");
			wait(driver, "2");
			click(memberLoginButton);
			wait(driver, "2");
			try
			{
				wait(driver, "2");
				clear( memberPassword );
				sendKeys(memberPassword,"Password2");
				wait(driver, "2");
				click(memberLoginButton);
				wait(driver, "2");
				try
				{
					wait(driver, "2");
					clear( memberPassword );
					sendKeys(memberPassword,"Zillion2016");
					wait(driver, "2");
					click(memberLoginButton);
					wait(driver, "3");
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
		Navigate.waitTime(driver, "20");
		System.out.println("Member Logged in successfully");
		return retrievedscheduleAvailableEmail;
	}

	/**
	 * Name : VigneshRaj.M 
	 * Created Date: 03/Jan/2017
	 * Modified Date:     
	 * Description :   Common method to RA 1on1 Enter Weight durint attending 1on1 Session
	 * Ticket ID :     
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static void raEnterWeightDuringLiveSession(WebDriver driver1)
	{
		try
		{
			waitForElement(driver1, OR.RA_LIVE_SESSION_BEFORE_ATTENDING_ENTER_WEIGHT_TEXTBOX);
			WebElement weightTextBox= driver1.findElement(By.xpath(OR.RA_LIVE_SESSION_BEFORE_ATTENDING_ENTER_WEIGHT_TEXTBOX));
			sendKeys(weightTextBox, "150");
			WebElement weightSubmitButton= driver1.findElement(By.xpath(OR.RA_LIVE_SESSION_BEFORE_ATTENDING_ENTER_WEIGHT_SUBMIT_BUTTON));
			click(weightSubmitButton);
		}
		catch (Exception e)
		{

		}
	}

	/**
	 * Name :Vinothkumar.M    
	 * Created Date: 11/Mar/2016  
	 * Modified Date:  
	 * Description : Create a common method to RA Group Live Session Coach Login for Same Browser
	 * Ticket ID :   
	 * Required Inputs :  Email from the DB 
	 * @throws ParseException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public  static String raAppealGroupLiveSessionCoachLoginSameBrowser(WebDriver driver,String classCoach) throws ClassNotFoundException, ParseException, SQLException
	{
		wait(driver, "3");
		Navigate.get(driver, Directory.RA_Provider_Url);
		wait(driver, "5");
		Navigate.maximize(driver);
		WebElement Coacheslogin_logo= driver.findElement(By.xpath(OR.COACHES_LOGIN_PAGE_LOGO_REF));
		waitForElement( driver, OR.COACHES_LOGIN_PAGE_LOGO_REF );
		verifyElementIsPresent(driver, Coacheslogin_logo);
		WebElement coach_username= driver.findElement(By.xpath(OR.COACHES_USERNAME_1));
		verifyElementIsPresent(driver, coach_username);
		WebElement coach_password= driver.findElement(By.xpath(OR.COACHES_PASSWORD));
		verifyElementIsPresent(driver, coach_password);
		WebElement Coacheslogin_button= driver.findElement(By.xpath(OR.COACHES_LOGIN_BUTTON));
		verifyElementIsPresent(driver, Coacheslogin_button);
		sendKeys(coach_username,classCoach);
		try
		{
			wait(driver, "2");
			sendKeys(coach_password,"Healthfleet2015");
			wait(driver, "2");
			click(Coacheslogin_button);
			wait(driver, "2");
			try
			{
				wait(driver, "2");
				clear( coach_password );
				sendKeys(coach_password,"Zillion2016");
				wait(driver, "2");
				click(Coacheslogin_button);
				wait(driver, "2");
				try
				{
					wait(driver, "2");
					clear( coach_password );
					sendKeys(coach_password,"Healthfleet2016");
					wait(driver, "2");
					click(Coacheslogin_button);
					wait(driver, "3");
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
		Navigate.waitTime(driver, "20");
		System.out.println("Coaches Logged in successfully");
		return classCoach;
	}

	/**
	 * Name :     Vinothkumar.M
	 * Created Date:   9/JUNE/16
	 * Modified Date:   
	 * Description : Create a common method to Login for RA Group Livesession with retrieve Email and Password
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ParseException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String realAppealGroupLiveSessionMemberLoginSameBrowser(WebDriver driver, String testData) throws ClassNotFoundException, ParseException, SQLException 
	{
		wait(driver, "5");
		Navigate.get(driver, Directory.RA_Member_Url);
		wait(driver, "5");
		waitForElement( driver, OR.RA_MEMBER_BEFORE_LOGIN_BUTTON );
		WebElement member_LoginButton= driver.findElement(By.xpath(OR.RA_MEMBER_BEFORE_LOGIN_BUTTON));
		verifyElementIsPresent(driver, member_LoginButton);
		click(member_LoginButton);
		WebElement memberUsername= driver.findElement(By.xpath(OR.RA_MEMBER_USERNAME_TEXTBOX));
		verifyElementIsPresent(driver, memberUsername);
		WebElement memberPassword= driver.findElement(By.xpath(OR.RA_MEMBER_PASSWORD_TEXTBOX));
		verifyElementIsPresent(driver, memberPassword);
		WebElement memberLoginButton= driver.findElement(By.xpath(OR.RA_MEMBERLIVESESSION_LOGIN_BUTTON));
		verifyElementIsPresent(driver, memberLoginButton);
		sendKeys(memberUsername,testData);
		try
		{
			wait(driver, "2");
			sendKeys(memberPassword,"Password1");
			wait(driver, "2");
			click(memberLoginButton);
			wait(driver, "2");
			try
			{
				wait(driver, "2");
				clear( memberPassword );
				sendKeys(memberPassword,"Password2");
				wait(driver, "2");
				click(memberLoginButton);
				wait(driver, "2");
				try
				{
					wait(driver, "2");
					clear( memberPassword );
					sendKeys(memberPassword,"Zillion2016");
					wait(driver, "2");
					click(memberLoginButton);
					wait(driver, "3");

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
		Navigate.waitTime(driver, "20");
		System.out.println("Member Logged in successfully");
		return testData;
	}

	/**
	 * Name : Vinothkumar.M 
	 * Created Date: 21/June/2016  
	 * Modified Date:     
	 * Description :   Common method to attend the RA Group Live session 
	 * Ticket ID :     
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public  static void raCoachUpcomingSessionTimeSameBrowser(WebDriver driver, String time22) throws ParseException
	{
		WebElement coachUpcomingSessionTime = driver.findElement(By.xpath("//tr//td[text()='"+time22+"']/following::td[text()='Group']/following-sibling::td//following-sibling::td//following-sibling::td/following-sibling::td/div/a"));
		verifyElementIsPresent(driver, coachUpcomingSessionTime); 
		click(coachUpcomingSessionTime);
		wait( driver, "20" );
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
	 *
	 */
	// Method will Allow the plugin if there is Allow and Remember Else it will skip the execution
	public static void shareSelectedDevice(WebDriver driver) throws FindFailed, AWTException 
	{
		String OSName=Platform.OS.toUpperCase();
		if(OSName.contains("WINDOWS"))
		{
			Screen Allow=new Screen(); 
			wait( driver, "8" );
			Pattern image = new Pattern(Directory.uploadFilePath+"SendButton.PNG");
			Allow.wait(image, 15);
			Allow.click(image);
			wait( driver, "3" );
		}
		else if(OSName.contains("MAC"))
		{
			Robot rb = new Robot();
			wait( driver, "4" );
			rb.keyPress(KeyEvent.VK_CONTROL); 
			rb.keyPress(KeyEvent.VK_S);
			rb.keyRelease(KeyEvent.VK_CONTROL); 
			rb.keyRelease(KeyEvent.VK_S);
			wait( driver, "8" ); 
			rb.keyPress(KeyEvent.VK_S);
			rb.keyRelease(KeyEvent.VK_S);
		}
	}

	/**
	 * Name : Vinothkumar.M 
	 * Created Date: 05/Jan/2017  
	 * Modified Date:     
	 * Description :   Common method to Group Live Session Member Raise hand Enable/Disable from spread sheet
	 * Ticket ID :     
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String raGroupSessionMemberRaiseHandSameBrowser(WebDriver driver,String testData)
	{
		if ("MemberRaiseHandEnable".equalsIgnoreCase(testData) ) 
		{
			jsClickByXPath(driver, OR.MEMBER_RA_GROUP_SESSION_RAISE_HAND);
			WebElement memberLowerHand = driver.findElement(By.xpath(OR.MEMBER_RA_GROUP_SESSION_LOWER_HAND));
			verifyElementIsPresent(driver, memberLowerHand);
		}
		else if ( "MemberRaiseHandDisable".equalsIgnoreCase(testData) )
		{
			jsClickByXPath(driver, OR.MEMBER_RA_GROUP_SESSION_RAISE_HAND);
			wait(driver, "2");
			jsClickByXPath(driver, OR.MEMBER_RA_GROUP_SESSION_LOWER_HAND);
			WebElement memberRaiseHand = driver.findElement(By.xpath(OR.MEMBER_RA_GROUP_SESSION_RAISE_HAND));
			verifyElementIsPresent(driver, memberRaiseHand);
		}
		return testData;
	}

	/**
	 * Name : Vinothkumar.M 
	 * Created Date: 05/Jan/2017  
	 * Modified Date:     
	 * Description :   Common method to Group Live Session Member Raise hand Enable/Disable from spread sheet
	 * Ticket ID :     
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String verifyGroupSessionMemberRaiseHandSameBrowser(WebDriver driver,String testData)
	{
		if ("MemberRaiseHandEnable".equalsIgnoreCase(testData))  
		{
			WebElement memberLowerHand = driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_LOWER_HAND_SYMBOL));
			verifyElementIsPresent(driver, memberLowerHand);
			WebElement lowerAllHandsButton = driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_LOWER_ALL_HANDS_BUTTON));
			verifyElementIsPresent(driver, lowerAllHandsButton);
		}
		else if ( "MemberRaiseHandDisable".equalsIgnoreCase(testData) )
		{
			WebElement memberLowerHand = driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_LOWER_HAND_SYMBOL));
			verifyElementIsNotPresent(driver, memberLowerHand);
			WebElement lowerAllHandsButton = driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_LOWER_ALL_HANDS_BUTTON));
			verifyElementIsNotPresent(driver, lowerAllHandsButton);
		}
		return testData;
	}

	/**
	 * Name : Vinothkumar.M 
	 * Created Date: 13/June/2016  
	 * Modified Date:     
	 * Description :   Common method to Group Live Session Coach Member Tile Mic Enable/Disable from spread sheet 
	 * Ticket ID :     
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String raGroupSessionCoachMemberTileMicEnableSameBrowser(WebDriver driver,String testData)
	{
		if ("CoachMemberTileMicEnable".equalsIgnoreCase(testData) ) 
		{ 
			{
				jsClickByXPath(driver, OR.COACH_RA_GROUP_SESSION_COACH_TILE_MEMBER_MIC_DISABLED);  
				wait(driver, "2");
				WebElement coachTileMemberMicEnabled = driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_COACH_TILE_MEMBER_MIC_ENABLED));
				verifyElementIsPresent(driver, coachTileMemberMicEnabled);
			}
		}
		else if ( "CoachMemberTileMicDisable".equalsIgnoreCase(testData) )
		{
			{
				jsClickByXPath(driver, OR.COACH_RA_GROUP_SESSION_COACH_TILE_MEMBER_MIC_ENABLED);
				WebElement coachTileMemberMicDisabled = driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_COACH_TILE_MEMBER_MIC_DISABLED));
				verifyElementIsPresent(driver, coachTileMemberMicDisabled);
			}
		}
		return testData;
	}

	/**
	 * Name : VIGNESHRAJ.M 
	 * Created Date: 06/Jan/2017  
	 * Modified Date:     
	 * Description :   Common method to Lecture Live Session Coach Member Tile Mic Enable/Disable from spread sheet in Place [15]
	 * Ticket ID :     
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String raVerifyMemberTileEnableOrDisableSameBrowser(WebDriver driver,String testData)
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
	 * Name : Vinothkumar.M 
	 * Created Date: 10/Jan/2017  
	 * Modified Date:     
	 * Description :   Common method to vspnViewEducationalFormatsPdfDocument
	 * Ticket ID :     
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public  static void vspnViewEducationalFormatsPdfDocument(WebDriver driver) throws AWTException
	{
		WebElement viewTutorial=driver.findElement(By.xpath(VSPN_TUTORIAL_LINK)); 
		verifyElementIsPresent(driver, viewTutorial);
		wait(driver, "2");
		WebElement viewSummary=driver.findElement(By.xpath(VSPN_SUMMARY_LINK)); 
		verifyElementIsPresent(driver, viewSummary);
		wait(driver, "2");

		//String Main_Window2 = driver.getWindowHandle();
		click(viewTutorial);
		wait(driver, "2"); 

		ArrayList<String> allWindows=new ArrayList<String>(driver.getWindowHandles());
		String windowname=driver.switchTo().window(allWindows.get(1)).getTitle();
		System.out.println("window name is " +windowname);
		String osName = Platform.OS.toUpperCase();
		if(osName=="WINDOWS")
		{
			if(windowname.contains("X_PLAIN.com")){
				System.out.println("tutorial was opened");
			}
			System.out.println("tutorial was opened");
		}
		else if (osName=="MAC")
		{
			if(windowname.contains("X-Plain Tutorial"))
			{
				System.out.println("tutorial was opened");
			}
		}

		else {
			System.out.println("tutorial was not opened");
			//			Assert.fail();
		}
		wait(driver, "10" );
		driver.switchTo().window(allWindows.get(1)).close();
		System.out.println("tutorial page closed");
		driver.switchTo().window(allWindows.get(0));

		//Main_Window2 = driver.getWindowHandle();
		click(viewSummary);
		wait(driver, "2"); 
		ArrayList<String> allWindows1=new ArrayList<String>(driver.getWindowHandles());
		String windowname1=driver.switchTo().window(allWindows1.get(1)).getTitle();
		if(windowname1.contains("pdf"))
		{
			System.out.println("pdf was opened");
		}
		else 
		{
			System.out.println("pdf was not opened");
			Assert.fail();
		}
		wait(driver, "10" );
		WebElement guideFullScreen= driver.findElement(By.xpath(COACH_SESSION_GUIDE_FULLSCREEN));
		WebElement guideAutomaticZoom= driver.findElement(By.xpath(COACH_SESSION_GUIDE_AUTOMATICZOOM));
		WebElement guidePagination =driver.findElement(By.xpath(COACH_SESSION_GUIDE_PAGINATION));
		WebElement guidePrint =driver.findElement(By.xpath(COACH_SESSION_GUIDE_PRINT));
		WebElement guideDownload =driver.findElement(By.xpath(COACH_SESSION_GUIDE_DOWNLOAD));
		WebElement guideToggleSlideBar =driver.findElement(By.xpath(COACH_SESSION_GUIDE_TOGGLE_SLIDEBAR));
		verifyElementIsPresent(driver, guideFullScreen);
		verifyElementIsPresent(driver, guideAutomaticZoom);
		verifyElementIsPresent(driver, guidePagination);
		verifyElementIsPresent(driver, guidePrint);
		verifyElementIsPresent(driver, guideDownload);
		verifyElementIsPresent(driver, guideToggleSlideBar);
		jsClickByXPath(driver, OR.COACH_SESSION_PAGNUMBER_INPUT);
		wait( driver, "3" );
		Manipulation.jsClickByXPath(driver, OR.COACH_SESSION_PAGNUMBER_INPUT);
		wait( driver, "3" );
		jsClickByXPath(driver, OR.COACH_SESSION_GUIDE_TOGGLE_SLIDEBAR);
		wait( driver, "5" );
		jsClickByXPath(driver, OR.COACH_SESSION_GUIDE_TOGGLE_SLIDEBAR);
		wait( driver, "5" );
		jsClickByXPath(driver, OR.COACH_SESSION_GUIDE_FULLSCREEN);
		wait( driver, "3" );
		Actions actionObject1 = new Actions(driver);
		actionObject1.sendKeys(Keys.ESCAPE).build().perform();
		wait( driver, "3" );
		jsClickByXPath(driver, OR.COACH_SESSION_GUIDE_SECONDARY_TOOLBAR);
		wait( driver, "3" );
		jsClickByXPath(driver, OR.COACH_SESSION_GUIDE_ROTATE_CLOCKWISE);
		wait( driver, "3" );
		jsClickByXPath(driver, OR.COACH_SESSION_GUIDE_SECONDARY_TOOLBAR);
		wait( driver, "3" );
		jsClickByXPath(driver, OR.COACH_SESSION_GUIDE_ROTATE_ANTICLOCKWISE);
		wait( driver, "3" );
		jsClickByXPath(driver, OR.COACH_SESSION_GUIDE_SECONDARY_TOOLBAR);
		wait( driver, "3" );
		jsClickByXPath(driver, OR.COACH_SESSION_GUIDE_DOCUMENT_PROPERTIES);
		wait( driver, "3" );
		jsClickByXPath(driver, OR.COACH_SESSION_GUIDE_DOCUMENT_PROPERTIES_POPUP_CLOSE);
		driver.close();
		driver.switchTo().window(Main_Window); 
		try{Thread.sleep(1000);}
		catch(InterruptedException e){e.printStackTrace();}
		wait( driver, "2" );
	}

	/**
	 * Name : VIGNESHRAJ.M 
	 * Created Date: 25/Jan/2017  
	 * Modified Date:     
	 * Description :   Common method to Validate the GroupSession Video
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static void raVerifyGroupSessionVideoValidation(WebDriver driver1)
	{
		try
		{
			jsClickByXPath(driver1, OR.COACH_RA_GROUP_SESSION_VIDEO_BUTTON);   
			wait( driver1, "2" );  
			WebElement videoLink= driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_VIDEO));
			verifyElementIsPresent(driver1, videoLink);
			click(videoLink);
			wait(driver1, "10" );
			WebElement play_button= driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_PLAY_VIDEO_BUTTON));
			verifyElementIsPresent(driver1, play_button);
			WebElement Pause_button= driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_PAUSE_BUTTON));
			verifyElementIsPresent(driver1, Pause_button);
			WebElement Backward_btn= driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_JUMP_BACK_THIRTY_SECONDS_BUTTON));
			verifyElementIsPresent(driver1, Backward_btn);
			WebElement forward_btn= driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_JUMP_FORWARD_THIRTY_SECOND_BUTTON));
			verifyElementIsPresent(driver1, forward_btn);
			WebElement Refresh_btn= driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_RESTART_FROM_THE_BEGINNING_BUTTON));
			verifyElementIsPresent(driver1, Refresh_btn);
			WebElement Eject_btn= driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_EJECT_VIDEO_BUTTON));
			verifyElementIsPresent(driver1, Eject_btn);
			wait( driver1, "5" );
			click(play_button);
			wait( driver1, "5" );
			click(Pause_button);
			wait(driver1, "6" );
			click( forward_btn);
			wait(driver1, "6" );
			click( Backward_btn);
			wait(driver1, "6" );
			click( Refresh_btn);
			wait(driver1, "6" );
			click( Eject_btn);
			wait(driver1, "3" );
		}
		catch (Exception e)
		{

		}
	}

	/**
	 * Name : VIGNESHRAJ.M 
	 * Created Date: 25/Jan/2017  
	 * Modified Date:     
	 * Description :   Common method to Validate the GroupSession Video
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static void coachGroupAppendUrlForSession6(WebDriver driver1)
	{
		String getCoachURL= driver1.getCurrentUrl();
		String appendCoachURL= getCoachURL+"&intervalNumber=6";
		Navigate.get( driver1, appendCoachURL );
		wait(driver1, "5" );
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
	public static String changeAddLiveToZLive(WebDriver driver, String LiveType,String testData)
	{
		if (LiveType.equalsIgnoreCase("ADDLIVE"))
		{
			if (testData.equalsIgnoreCase("Group"))
			{
			WebElement addLiveVideoButton= driver.findElement(By.xpath("//tbody[@id='sessionClasses_tableBody']/tr/td[5][contains(text(),'"+testData+"')]/following-sibling::td[4][contains(text(),'Scheduled')]/following-sibling::td[5]/div[contains(text(),'ADDLIVE')]/parent::td/following-sibling::td/span[3]/following-sibling::a[3]/following-sibling::span[2]/a/span"));
			click(addLiveVideoButton);
			wait(driver, "3" );
			waitForElement(driver, OR.RA_GROUP_SESSION_CHANGE_LIVE_DROPDOWN);
			WebElement changeLiveDropdown= driver.findElement(By.xpath(OR.RA_GROUP_SESSION_CHANGE_LIVE_DROPDOWN));
			selectByVisibletext(changeLiveDropdown, "ZLIVE");
			wait(driver, "5" );
			WebElement saveButton= driver.findElement(By.xpath(OR.RA_GROUP_SESSION_CHANGE_SAVE_BUTTON));
			click(saveButton);
			wait(driver, "3" );
			}
			else if (testData.equalsIgnoreCase("1on1"))
			{
				WebElement addLiveVideoButton= driver.findElement(By.xpath("//tbody[@id='sessionClasses_tableBody']/tr/td[5][contains(text(),'"+testData+"')]/following-sibling::td[4][contains(text(),'Scheduled')]/following-sibling::td[5]/div[contains(text(),'ADDLIVE')]/parent::td/following-sibling::td/span[3]/following-sibling::a[3]/following-sibling::span[3]/a/span"));
				click(addLiveVideoButton);
				wait(driver, "3" );
				waitForElement(driver, OR.RA_GROUP_SESSION_CHANGE_LIVE_DROPDOWN);
				WebElement changeLiveDropdown= driver.findElement(By.xpath(OR.RA_GROUP_SESSION_CHANGE_LIVE_DROPDOWN));
				selectByVisibletext(changeLiveDropdown, "ZLIVE");
				wait(driver, "2" );
				WebElement saveButton= driver.findElement(By.xpath(OR.RA_GROUP_SESSION_CHANGE_SAVE_BUTTON));
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
	 * Name :     VIGNESHRAJ
	 * Created Date:   31/01/2017
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
	 * Name :     VIGNESHRAJ
	 * Created Date:   31/01/2017
	 * Modified Date:   
	 * Description :   Create a common method to ScreenShare Install
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws FindFailed 
	 * @throws AWTException 
	 *
	 */
	public static void screenSharingInstallAddOn(WebDriver driver) throws FindFailed, AWTException 
	{
		Robot rb = new Robot();
		wait( driver, "4" );
		rb.keyPress(KeyEvent.VK_CONTROL); 
		rb.keyPress(KeyEvent.VK_I);
		rb.keyRelease(KeyEvent.VK_CONTROL); 
		rb.keyRelease(KeyEvent.VK_I);
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
	public static void getWindowToScreenShareAndInstall(WebDriver driver) throws FindFailed, AWTException
	{
		String parentWindow= driver.getWindowHandle();
		WebElement screenShareButton= driver.findElement(By.xpath(OR.COACH_LECTURE_SESSION_SHARE_SCREEN_BUTTON));
		click(screenShareButton);
		wait(driver, "15");
		ArrayList<String> allWindows=new ArrayList<String>(driver.getWindowHandles());
		allWindows.remove(parentWindow);
		driver.switchTo().window(allWindows.get(0));
		String titleName= driver.getTitle();
		System.out.println("Title Name : "+titleName);
		if (titleName.contains("Zillion Live Screen Sharing"))
		{
			WebElement AddPlugin= driver.findElement(By.xpath(OR.GROUP_SESSION_SCREEN_SHARING_ADD_BROWSER_PLUGIN));
			click(AddPlugin);
			wait(driver, "3");
			Robot rb = new Robot();
			wait( driver, "4" );
			rb.keyPress(KeyEvent.VK_CONTROL); 
			rb.keyPress(KeyEvent.VK_I);
			rb.keyRelease(KeyEvent.VK_CONTROL); 
			rb.keyRelease(KeyEvent.VK_I);
			wait(driver,"3");
			driver.switchTo().window(allWindows.get(0)).close();
			System.out.println("tutorial page closed");
			driver.switchTo().window(parentWindow);

		}
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
	public static void selectScreenForGroupSessionZLive(WebDriver driver) throws FindFailed, AWTException
	{
		Robot rb = new Robot();
		wait( driver, "4" );
		rb.keyPress(KeyEvent.VK_CONTROL); 
		rb.keyPress(KeyEvent.VK_W);
		rb.keyRelease(KeyEvent.VK_CONTROL); 
		rb.keyRelease(KeyEvent.VK_W);
		wait( driver, "3" );
		rb.keyPress(KeyEvent.VK_L);
		rb.keyRelease(KeyEvent.VK_L);
		wait( driver, "3" );
		rb.keyPress(KeyEvent.VK_CONTROL); 
		rb.keyPress(KeyEvent.VK_S);
		rb.keyRelease(KeyEvent.VK_CONTROL); 
		rb.keyRelease(KeyEvent.VK_S);
		wait( driver, "8" ); 
		rb.keyPress(KeyEvent.VK_S);
		rb.keyRelease(KeyEvent.VK_S);
	}
	
	/**
	 * Name :VIGNESHRAJ.M  
	 * Created Date: 23/FEB/2017  
	 * Modified Date:  
	 * Description : Create a common method to RA Group Live Session Coach Login
	 * Ticket ID :   
	 * Required Inputs :  Email from the DB 
	 * @throws ParseException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public  static void coachLoginWithReferenceStep(WebDriver driver,String coachEmail1) throws ClassNotFoundException, ParseException, SQLException
	{
		wait(driver, "3");
		Navigate.get(driver, Directory.RA_Provider_Url);
		wait(driver, "5");
		driver.get("javascript:document.getElementById('overridelink').click();"); 
		Navigate.maximize(driver);
		WebElement Coacheslogin_logo= driver.findElement(By.xpath(OR.COACHES_LOGIN_PAGE_LOGO_REF));
		waitForElement( driver, OR.COACHES_LOGIN_PAGE_LOGO_REF );
		verifyElementIsPresent(driver, Coacheslogin_logo);
		WebElement coach_username= driver.findElement(By.xpath(OR.COACHES_USERNAME_1));
		verifyElementIsPresent(driver, coach_username);
		WebElement coach_password= driver.findElement(By.xpath(OR.COACHES_PASSWORD));
		verifyElementIsPresent(driver, coach_password);
		WebElement Coacheslogin_button= driver.findElement(By.xpath(OR.COACHES_LOGIN_BUTTON));
		verifyElementIsPresent(driver, Coacheslogin_button);
		sendKeys(coach_username,coachEmail1);
		try
		{
			wait(driver, "2");
			sendKeys(coach_password,"Healthfleet2015");
			wait(driver, "2");
			click(Coacheslogin_button);
			try{
				//Navigate.waitTime(driver, "20");
				WebElement Palogopresent= driver.findElement(By.xpath(RA_PROGRAM_ADMIN_REAL_APPEAL_LOGO));
				waitForElement(driver,RA_PROGRAM_ADMIN_REAL_APPEAL_LOGO);
				verifyElementIsPresent(driver, Palogopresent);
			}
			catch(Exception e) {
				WebElement userSessionPopup = driver.findElement(By.xpath(PROVIDER_USER_SESSION_POPUP));
				click(userSessionPopup);
				WebElement Palogopresent= driver.findElement(By.xpath(RA_PROGRAM_ADMIN_REAL_APPEAL_LOGO));
				waitForElement(driver,RA_PROGRAM_ADMIN_REAL_APPEAL_LOGO);
				verifyElementIsPresent(driver, Palogopresent);
			}
			wait(driver, "2");
			try
			{
				wait(driver, "2");
				clear( coach_password );
				sendKeys(coach_password,"Zillion2016");
				wait(driver, "2");
				click(Coacheslogin_button);
				wait(driver, "2");
				try
				{
					wait(driver, "2");
					clear( coach_password );
					sendKeys(coach_password,"Healthfleet2016");
					wait(driver, "2");
					click(Coacheslogin_button);
					wait(driver, "3");
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
		Navigate.waitTime(driver, "20");
		System.out.println("Coaches Logged in successfully");
	}
	
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   9/JUNE/16
	 * Modified Date:   
	 * Description : Create a common method to Login for RA Group Livesession ZLiveSession Different browser
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ParseException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String raGroupZLiveSessionMemberLoginDifferentBrowser(WebDriver driver1, String memberEmail1) throws ClassNotFoundException, ParseException, SQLException 
	{
		wait(driver1, "5");
		Navigate.get(driver1, Directory.RA_Member_Url);
		wait(driver1, "5");
		waitForElement( driver1, OR.RA_MEMBER_BEFORE_LOGIN_BUTTON );
		WebElement member_LoginButton= driver1.findElement(By.xpath(OR.RA_MEMBER_BEFORE_LOGIN_BUTTON));
		verifyElementIsPresent(driver1, member_LoginButton);
		click(member_LoginButton);
		WebElement memberUsername= driver1.findElement(By.xpath(OR.RA_MEMBER_USERNAME_TEXTBOX));
		verifyElementIsPresent(driver1, memberUsername);
		WebElement memberPassword= driver1.findElement(By.xpath(OR.RA_MEMBER_PASSWORD_TEXTBOX));
		verifyElementIsPresent(driver1, memberPassword);
		WebElement memberLoginButton= driver1.findElement(By.xpath(OR.RA_MEMBERLIVESESSION_LOGIN_BUTTON));
		verifyElementIsPresent(driver1, memberLoginButton);
		sendKeys(memberUsername,memberEmail1);
		try
		{
			wait(driver1, "2");
			sendKeys(memberPassword,"Password1");
			wait(driver1, "2");
			click(memberLoginButton);
			wait(driver1, "2");
			try
			{
				wait(driver1, "2");
				clear( memberPassword );
				sendKeys(memberPassword,"Password2");
				wait(driver1, "2");
				click(memberLoginButton);
				wait(driver1, "2");
				try
				{
					wait(driver1, "2");
					clear( memberPassword );
					sendKeys(memberPassword,"Zillion2016");
					wait(driver1, "2");
					click(memberLoginButton);
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
		System.out.println("Member Logged in successfully");
		return memberEmail1;
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
	public static void allowGroupZLivePlugin(WebDriver driver1) throws FindFailed, AWTException 
	{
		String OSName=Platform.OS.toUpperCase();
		if(OSName.contains("WINDOWS"))
		{
			Screen Allow=new Screen(); 
			wait( driver1, "8" );
			Pattern image = new Pattern(Directory.uploadFilePath+"GroupSessionZLiveAllowForChrome.PNG");
			Allow.wait(image, 15);
			Allow.click(image);
			wait( driver1, "3" );
		}
		else if(OSName.contains("MAC"))
		{
			Screen Allow=new Screen(); 
			wait( driver1, "8" );
			Pattern image = new Pattern(Directory.uploadFilePath+"GroupSessionZLiveAllowForChrome.PNG");
			Allow.wait(image, 15);
			Allow.click(image);
			wait( driver1, "3" );
		}
	}
	
	/**
	 * Name : VIGNESHRAJ.M 
	 * Created Date: 24/FEB/2017  
	 * Modified Date:     
	 * Description :   Common method to attend the RA Group Live session 
	 * Ticket ID :     
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public  static void raCoachUpcomingSessionTimeDifferentBrowser(WebDriver driver) throws ParseException
	{
		WebElement coachUpcomingSessionTime = driver.findElement(By.xpath("//tr//td[text()='"+sessiontime1+"']/following::td[text()='Group']/following-sibling::td//following-sibling::td//following-sibling::td/following-sibling::td/div/a"));
		verifyElementIsPresent(driver, coachUpcomingSessionTime); 
		click(coachUpcomingSessionTime);
		wait( driver, "10" );
	}
	
	/**
	 * Name : VIGNESHRAJ.M
	 * Created Date: 27/FEB/2017
	 * Modified Date:
	 * Description :   Create a common method to Coach Gear Settings
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String groupZLiveSessionCoachGearSettings(WebDriver driver)
	{
		WebElement memberSettingsGearIcon = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_BUTTON));
		verifyElementIsPresent(driver, memberSettingsGearIcon);
		jsClickByXPath(driver, OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_BUTTON);
		wait(driver, "2" );
		WebElement CoachSettingsTitle = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_COACH_SETTINGS_TITLE));
		WebElement CoachSettingsCloseButton = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_COACH_SETTINGS_CLOSE_BUTTON));
		WebElement memberSettingsVideoOption = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_VIDEO_OPTION));
		WebElement memberSettingsAudioOption = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_AUDIO_OPTION));
		WebElement memberSettingsCameraLabel = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_CAMERA_LABEL));
		WebElement memberSettingsCameraDropdown = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_CAMERA_DROPDOWN));
		verifyElementIsPresent(driver, CoachSettingsTitle);
		verifyElementIsPresent(driver, CoachSettingsCloseButton);
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
		wait(driver, "2" );
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
		jsClickByXPath(driver, OR.LIVESESSION_1ON1_SESSION_COACH_SETTINGS_CLOSE_BUTTON);
		wait(driver, "3" );
		return ElementWait;
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
	public static void getWindowToScreenShareAndInstallDifferentBrowser(WebDriver driver) throws FindFailed, AWTException
	{
		String parentWindow= driver.getWindowHandle();
		Robot rb = new Robot();
		WebElement screenShareButton= driver.findElement(By.xpath(OR.COACH_LECTURE_SESSION_SHARE_SCREEN_BUTTON));
		click(screenShareButton);
		wait(driver, "15");
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		String titleName= driver.getTitle();
		System.out.println("Title Name : "+titleName);
//		if (titleName.contains("Zillion Live Screen Sharing"))
//		{
			WebElement AddPlugin= driver.findElement(By.xpath(OR.GROUP_SESSION_SCREEN_SHARING_ADD_BROWSER_PLUGIN));
			click(AddPlugin);
			wait(driver, "3");
			wait( driver, "4" );
			rb.keyPress(KeyEvent.VK_CONTROL); 
			rb.keyPress(KeyEvent.VK_I);
			rb.keyRelease(KeyEvent.VK_CONTROL); 
			rb.keyRelease(KeyEvent.VK_I);
			wait(driver,"3");
			driver.switchTo().window(tabs.get(1)).close();
			System.out.println("tutorial page closed");
			driver.switchTo().window(parentWindow);

//		}
	}
	
	/**
	 * Name : Vinoth Kumar
	 * Created Date: 28/Feb/2017  
	 * Modified Date:     
	 * Description :   Common method to get time in Upcoming session for Customization
	 * Ticket ID :     
	 * Required Inputs : 
	 *
	 */
	static String customizationSessionTime=null;
	public static String getTimeInUpcomingSessionsSecForCustomization(WebDriver driver1) {
		WebElement memberSessionTime = driver1.findElement(By.xpath("//tr//td/following::td[text()='Customization']/preceding::td[3]"));
		String sessionsTime = memberSessionTime.getText();
		String str1 = null;
		str1 = sessionsTime.substring(1, 5);
		String str2 = sessionsTime.substring(5);
        String str3 = sessionsTime.substring(0, 2);
        if(str3 == "10" && str3 == "11" && str3 == "12") {
	        str1 = sessionsTime.substring(0, 5);
        }
		String customizationSessionTime = str1 + " " + str2;
		return customizationSessionTime;
	}
	
	/**
	 * Name : Vinoth Kumar
	 * Created Date: 28/Feb/2017  
	 * Modified Date:     
	 * Description :   Common method to click on Customization session time
	 * Ticket ID :     
	 * Required Inputs :
	 *
	 */
	public  static void clickOnCustomizationsSessionTime(WebDriver driver1, String time) {
		WebElement customizationSessionTimeEle = driver1.findElement(By.xpath("//div[contains(@data-start, '" + time + "')]/following-sibling::div[contains(text(),'Customization Session')]"));
		verifyElementIsPresent(driver1, customizationSessionTimeEle); 
		click(customizationSessionTimeEle);
		wait( driver1, "3" );
	}	
	
	
	/**
	 * Name :     LEENA P.
	 * Created Date:  28/FEB/17
	 * Modified Date:   
	 * Description : Create a common method to retrieve Member for RA 1on1  session
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public  static String retrieveAvailable1on1SessionScheduleMember(WebDriver driver) throws ParseException, ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("SELECT ACNT.EMAIL Member_Email, PROV.EMAIL Coach_Email FROM SUMMARY_ACCOUNT_TODATE SMRY, PROVIDER PROV, ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID AND SMRY.ACCOUNT_ID=ACNT.ID AND SMRY.ONBOARDING_STATUS='MEMBER ONBOARDED' AND ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ACCOUNT_PROGRAM.START_DT_TIME>SYSDATE + INTERVAL '-50' DAY AND ID IN (SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_STATUS IN ('Unscheduled') AND PROGRAM_INTERVAL_START_DT<SYSDATE AND PROGRAM_INTERVAL_END_DT>SYSDATE AND SESSION_TYPE_ID='02')) AND ACNT.EMAIL not like '%api%' AND PROV.EMAIL not like '%api%' AND ACNT.STATUS='Active' ORDER BY SMRY.SESSION_DATE_UTC DESC");
		//ResultSet rs = stat.executeQuery("SELECT ACNT.EMAIL Member_Email, PROV.EMAIL Coach_Email FROM SUMMARY_ACCOUNT_TODATE SMRY, PROVIDER PROV, ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID AND SMRY.ACCOUNT_ID=ACNT.ID AND SMRY.ONBOARDING_STATUS='MEMBER ONBOARDED' AND ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ACCOUNT_PROGRAM.START_DT_TIME>SYSDATE + INTERVAL '-50' DAY AND ID IN (SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_STATUS!=('"+inputSessionStatus+"') AND PROGRAM_INTERVAL_START_DT<SYSDATE AND PROGRAM_INTERVAL_END_DT>SYSDATE AND SESSION_TYPE_ID='"+inputSessionType+"'))AND ACNT.EMAIL not like '%api%' AND PROV.EMAIL not like '%api%' ORDER BY SMRY.SESSION_DATE_UTC ASC");
		System.out.println("query executed");
		String retrievedscheduleAvailableMemberEmail="";
		String retrievedscheduleAvailableMemberAssignedCoachEmail="";
		String result="";
		if(rs.next())
		{
			retrievedscheduleAvailableMemberEmail= rs.getString("MEMBER_EMAIL"); 
			retrievedscheduleAvailableMemberAssignedCoachEmail = rs.getString("COACH_EMAIL");
			System.out.println("Available Member Email is "+retrievedscheduleAvailableMemberEmail);
			System.out.println("Available Member Email Assigned coach Email is "+retrievedscheduleAvailableMemberAssignedCoachEmail);
			result=retrievedscheduleAvailableMemberEmail+","+retrievedscheduleAvailableMemberAssignedCoachEmail;
		}
		else
		{
			result=","; 
		}
		System.out.println("result "+result);
		return result;  
	}
	
	/**
	 * Name : VigneshRaj.M 
	 * Created Date: 03/Jan/2017
	 * Modified Date:     
	 * Description :   Common method to RA 1on1 Enter Weight durint attending 1on1 Session
	 * Ticket ID :     
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static void raEnterWeightDuringLiveSessionDifferentBrowser(WebDriver driver)
	{
		try
		{
			waitForElement(driver, OR.RA_LIVE_SESSION_BEFORE_ATTENDING_ENTER_WEIGHT_TEXTBOX);
			WebElement weightTextBox= driver.findElement(By.xpath(OR.RA_LIVE_SESSION_BEFORE_ATTENDING_ENTER_WEIGHT_TEXTBOX));
			sendKeys(weightTextBox, "150");
			WebElement weightSubmitButton= driver.findElement(By.xpath(OR.RA_LIVE_SESSION_BEFORE_ATTENDING_ENTER_WEIGHT_SUBMIT_BUTTON));
			click(weightSubmitButton);
		}
		catch (Exception e)
		{

		}
	}
	
	/**
	 * Name :VIGNESHRAJ.M  
	 * Created Date: 23/FEB/2017  
	 * Modified Date:  
	 * Description : Create a common method to RA Group Live Session Coach Login
	 * Ticket ID :   
	 * Required Inputs :  Email from the DB 
	 * @throws ParseException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public  static void coachLoginWithReferenceStepDifferentBrowser(WebDriver driver1,String coachEmail1) throws ClassNotFoundException, ParseException, SQLException
	{
		wait(driver1, "3");
		Navigate.get(driver1, Directory.RA_Provider_Url);
		wait(driver1, "5");
		driver1.get("javascript:document.getElementById('overridelink').click();"); 
		Navigate.maximize(driver1);
		waitForElement( driver1, OR.COACHES_LOGIN_PAGE_LOGO_REF );
		WebElement Coacheslogin_logo= driver1.findElement(By.xpath(OR.COACHES_LOGIN_PAGE_LOGO_REF));
		verifyElementIsPresent(driver1, Coacheslogin_logo);
		WebElement coach_username= driver1.findElement(By.xpath(OR.COACHES_USERNAME_1));
		verifyElementIsPresent(driver1, coach_username);
		WebElement coach_password= driver1.findElement(By.xpath(OR.COACHES_PASSWORD));
		verifyElementIsPresent(driver1, coach_password);
		WebElement Coacheslogin_button= driver1.findElement(By.xpath(OR.COACHES_LOGIN_BUTTON));
		verifyElementIsPresent(driver1, Coacheslogin_button);
		sendKeys(coach_username,coachEmail1);
		try
		{
			wait(driver1, "2");
			sendKeys(coach_password,"Healthfleet2015");
			wait(driver1, "2");
			click(Coacheslogin_button);
			try{
				//Navigate.waitTime(driver, "20");
				WebElement Palogopresent= driver1.findElement(By.xpath(RA_PROGRAM_ADMIN_REAL_APPEAL_LOGO));
				waitForElement(driver1,RA_PROGRAM_ADMIN_REAL_APPEAL_LOGO);
				verifyElementIsPresent(driver1, Palogopresent);
			}
			catch(Exception e) {
				WebElement userSessionPopup = driver1.findElement(By.xpath(PROVIDER_USER_SESSION_POPUP));
				click(userSessionPopup);
				WebElement Palogopresent= driver1.findElement(By.xpath(RA_PROGRAM_ADMIN_REAL_APPEAL_LOGO));
				waitForElement(driver1,RA_PROGRAM_ADMIN_REAL_APPEAL_LOGO);
				verifyElementIsPresent(driver1, Palogopresent);
			}
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
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   9/JUNE/16
	 * Modified Date:   
	 * Description : Create a common method to Login for RA Group Livesession ZLiveSession Different browser
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ParseException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String raGroupZLiveSessionMemberLogin(WebDriver driver, String memberEmail1) throws ClassNotFoundException, ParseException, SQLException 
	{
		wait(driver, "5");
		Navigate.get(driver, Directory.RA_Member_Url);
		wait(driver, "5");
		waitForElement( driver, OR.RA_MEMBER_BEFORE_LOGIN_BUTTON );
		WebElement member_LoginButton= driver.findElement(By.xpath(OR.RA_MEMBER_BEFORE_LOGIN_BUTTON));
		verifyElementIsPresent(driver, member_LoginButton);
		click(member_LoginButton);
		WebElement memberUsername= driver.findElement(By.xpath(OR.RA_MEMBER_USERNAME_TEXTBOX));
		verifyElementIsPresent(driver, memberUsername);
		WebElement memberPassword= driver.findElement(By.xpath(OR.RA_MEMBER_PASSWORD_TEXTBOX));
		verifyElementIsPresent(driver, memberPassword);
		WebElement memberLoginButton= driver.findElement(By.xpath(OR.RA_MEMBERLIVESESSION_LOGIN_BUTTON));
		verifyElementIsPresent(driver, memberLoginButton);
		sendKeys(memberUsername,memberEmail1);
		try
		{
			wait(driver, "2");
			sendKeys(memberPassword,"Password1");
			wait(driver, "2");
			click(memberLoginButton);
			wait(driver, "2");
			try
			{
				wait(driver, "2");
				clear( memberPassword );
				sendKeys(memberPassword,"Password2");
				wait(driver, "2");
				click(memberLoginButton);
				wait(driver, "2");
				try
				{
					wait(driver, "2");
					clear( memberPassword );
					sendKeys(memberPassword,"Zillion2016");
					wait(driver, "2");
					click(memberLoginButton);
					wait(driver, "3");

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
		Navigate.waitTime(driver, "20");
		System.out.println("Member Logged in successfully");
		return memberEmail1;
	}
	
	/**
	 * Name : VigneshRaj.M    
	 * Created Date: 30/May/2016  
	 * Modified Date:     
	 * Description : Create a common method to Coach append URL
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public  static void raCoachOneOnOneSessionAppendURLDifferentBrowser(WebDriver driver1)
	{
		String getCoachCurrentURL= driver1.getCurrentUrl();
		String appendCoachCurrentURL= getCoachCurrentURL+"?attendnow";
		Navigate.get( driver1, appendCoachCurrentURL );
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
	public static void allowGroupZLivePluginDifferentBrowser(WebDriver driver) throws FindFailed, AWTException 
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
	 * Name :     VINOTHKUMAR
	 * Created Date:   22/03/2016
	 * Modified Date:   
	 * Description :   Create a common method to Allow the Plugin and Remember
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws FindFailed 
	 * @throws AWTException 
	 *
	 */
	// Method will Allow the plugin if there is Allow and Remember Else it will skip the execution
	public static void shareSelectedDeviceDifferentBrowser(WebDriver driver1) throws FindFailed, AWTException 
	{
		String OSName=Platform.OS.toUpperCase();
		if(OSName.contains("WINDOWS"))
		{
			Screen Allow=new Screen(); 
			wait( driver1, "8" );
			Pattern image = new Pattern(Directory.uploadFilePath+"SendButton.PNG");
			Allow.wait(image, 15);
			Allow.click(image);
			wait( driver1, "3" );
		}
		else if(OSName.contains("MAC"))
		{
			Robot rb = new Robot();
			wait( driver1, "4" );
			rb.keyPress(KeyEvent.VK_CONTROL); 
			rb.keyPress(KeyEvent.VK_S);
			rb.keyRelease(KeyEvent.VK_CONTROL); 
			rb.keyRelease(KeyEvent.VK_S);
			wait( driver1, "8" ); 
			rb.keyPress(KeyEvent.VK_S);
			rb.keyRelease(KeyEvent.VK_S);
		}
	}
	
	
	/**
	 * Name :    Vinothkumar.M
	 * Created Date:   25/Apr/2016
	 * Description :Create a common method for Real Appeal Coach Logout
	 * Ticket ID :
	 * Required Inputs :
	 *
	 */
	public static void realAppealCoachLogout(WebDriver driver1)
	{
		waitForElement(driver1,RA_COACH_SIGNOUT_LINK);
		jsClickByXPath(driver1,RA_COACH_SIGNOUT_LINK);
		wait(driver1, "1");
		waitForElement(driver1,RA_COACH_SIGNOUT_BUTTON);
		jsClickByXPath(driver1,RA_COACH_SIGNOUT_BUTTON);
		Navigate.waitTime(driver1, "20");
		waitForElement(driver1,RA_COACH_SIGNOUT_VERIFY);
		Navigate.waitTime(driver1, "20");
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
	 * Name :     BHARATH KUMAR.M
	 * Created Date:   15/MAR/17
	 * Modified Date:   
	 * Description : Create a common method to retrieve Member who is available Schedule button for RA 1on1 live session except .info,api,guerrilamail
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public  static String retrieveAvailableScheduleMemberMessage(WebDriver driver,String testData) throws ParseException, ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("SELECT ACNT.EMAIL Member_Email, PROV.EMAIL Coach_Email FROM SUMMARY_ACCOUNT_TODATE SMRY, PROVIDER PROV, ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID AND SMRY.ACCOUNT_ID=ACNT.ID AND SMRY.ONBOARDING_STATUS='MEMBER ONBOARDED' AND ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ACCOUNT_PROGRAM.START_DT_TIME>SYSDATE + INTERVAL '-50' DAY AND ID IN (SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_STATUS in ('"+inputSessionStatus+"') AND PROGRAM_INTERVAL_START_DT<SYSDATE AND PROGRAM_INTERVAL_END_DT>SYSDATE AND SESSION_TYPE_ID='"+inputSessionType+"')) AND ACNT.EMAIL not like '%api%' AND ACNT.EMAIL not like '%QAmember%-default@yopmail.com%' AND PROV.EMAIL not like '%api%' AND ACNT.STATUS='Active' ORDER BY SMRY.SESSION_DATE_UTC DESC");
		System.out.println("query executed");
		String retrievedscheduleAvailableMemberEmail="";
		String retrievedscheduleAvailableMemberAssignedCoachEmail="";
		String result="";
		if(rs.next())
		{
			retrievedscheduleAvailableMemberEmail= rs.getString("MEMBER_EMAIL"); 
			retrievedscheduleAvailableMemberAssignedCoachEmail = rs.getString("COACH_EMAIL");
			System.out.println("Available Member Email is "+retrievedscheduleAvailableMemberEmail);
			System.out.println("Available Member Email Assigned coach Email is "+retrievedscheduleAvailableMemberAssignedCoachEmail);
			result=retrievedscheduleAvailableMemberEmail+","+retrievedscheduleAvailableMemberAssignedCoachEmail;
		}
		else
		{
			result=""; 
		}
		System.out.println("result "+result);
		return result;  
	}
	
	/**
	 * Name : Vinothkumar.M 
	 * Created Date: 14/June/2016  
	 * Modified Date:     
	 * Description :   Common method to Group session quiz link
	 * Ticket ID :     
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static void raGroupSessionQuizLinkDifferentBrowser(WebDriver driver)
	{ 
		jsClickByXPath(driver,  OR.COACH_RA_GROUP_SESSION_CONTENT_BUTTON);
		wait(driver, "5" );
		WebElement QuizLink= driver.findElement(By.xpath(COACH_CONTENT_QUIZ_ARTICLES));
		verifyElementIsPresent(driver, QuizLink);
		String defaultWindow = driver.getWindowHandle();
		try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
		wait(driver, "3");
		click(QuizLink);
		wait(driver, "3");
		ArrayList<String> tabs2=new ArrayList<String>(driver.getWindowHandles());
		wait(driver, "3");
		driver.switchTo().window(tabs2.get(1));
		wait( driver, "4" );
		WebElement Quiz_Answer_option1 = driver.findElement(By.xpath(OR.COACH_CONTENT_QUIZ_ARTICLES_ANSWER_OPTION1));
		verifyElementIsPresent(driver,  Quiz_Answer_option1);
		click(Quiz_Answer_option1);
		jsClickByXPath(driver, OR.COACH_CONTENT_QUIZ_ARTICLES_ANSWER_SUBMIT_BUTTON);
		wait( driver, "4" );
		WebElement Quiz_Right_answer = driver.findElement(By.xpath(OR.COACH_CONTENT_QUIZ_ARTICLES_QUIZ_RIGHT_ANSWER));
		verifyElementIsPresent(driver,  Quiz_Right_answer);
		driver.close();
		driver.switchTo().window(defaultWindow); 
		try{Thread.sleep(1000);}
		catch(InterruptedException e){e.printStackTrace();}
		wait( driver, "2" );
	}
	
	/**
	 * Name : Vinothkumar.M 
	 * Created Date: 14/June/2016  
	 * Modified Date:     
	 * Description :   Common method to Group session Contract link
	 * Ticket ID :     
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static void raGroupSessionContractLinkDifferentBrowser(WebDriver driver)
	{ 
		WebElement ContractLink= driver.findElement(By.xpath(COACH_CONTENT_CONTRACT_ARTICLES_LINK));
		verifyElementIsPresent(driver, ContractLink);
		String defaultWindow = driver.getWindowHandle();
		try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
		click(ContractLink);
		wait(driver, "3");
		ArrayList<String> tabs2=new ArrayList<String>(driver.getWindowHandles());
		wait(driver, "3");
		driver.switchTo().window(tabs2.get(1));
		wait( driver, "4" );
		WebElement Contract_main_header = driver.findElement(By.xpath(OR.COACH_CONTENT_CONTRACT_ARTICLES_COMMITMENT_CONTRACT_MAIN_HEADER));
		verifyElementIsPresent(driver,  Contract_main_header);
		WebElement Contract_header = driver.findElement(By.xpath(OR.COACH_CONTENT_CONTRACT_ARTICLES_HEADER));
		verifyElementIsPresent(driver,  Contract_header);
		WebElement Contract_sub_header = driver.findElement(By.xpath(OR.COACH_CONTENT_CONTRACT_ARTICLES_SUB_HEADER));
		verifyElementIsPresent(driver,  Contract_sub_header);
		wait( driver, "2" );
		WebElement RA_shopping_list = driver.findElement(By.xpath(OR.COACH_CONTENT_CONTRACT_ARTICLES_REAL_APPEAL_SHOPPING_LIST_LABEL));
		verifyElementIsPresent(driver,  RA_shopping_list);
		WebElement Nutrition_Facts_label = driver.findElement(By.xpath(OR.COACH_CONTENT_CONTRACT_ARTICLES_NUTRITION_FACTS_LABEL));
		verifyElementIsPresent(driver,  Nutrition_Facts_label);
		WebElement ingredient_lists_label = driver.findElement(By.xpath(OR.COACH_CONTENT_CONTRACT_ARTICLES_EXAMINE_THE_INGREDIENT_LISTS_LABEL));
		verifyElementIsPresent(driver,  ingredient_lists_label);
		WebElement my_other_healthy_habit = driver.findElement(By.xpath(OR.COACH_CONTENT_CONTRACT_ARTICLES_CONTINUE_MY_OTHER_HEALTHY_HEALTHY_HABITS_LABEL));
		verifyElementIsPresent(driver,  my_other_healthy_habit );
		wait( driver, "2" );
		jsClickByXPath(driver, OR.COACH_CONTENT_CONTRACT_ARTICLES_I_COMMIT_BUTTON);
		wait( driver, "2" );
		WebElement Error_msg = driver.findElement(By.xpath(OR.COACH_CONTENT_CONTRACT_ARTICLES_QUESTION_IS_REQUIRED_ERROR_MSG));
		verifyElementIsPresent(driver,  Error_msg );
		wait( driver, "2" );
		wait(driver, "2");
		int SelectAllCheckbox = driver.findElements(By.xpath("//div[@class='sg-question-set']/div[*]//span//label/strong")).size();
		for(int i=1; i<=SelectAllCheckbox ;i++)
		{
			WebElement SelectAllCheckbox1 = driver.findElement(By.xpath("//div[@class='sg-question-set']/div["+i+"]//span//label/strong"));
			click(SelectAllCheckbox1);
			wait(driver, "2"); 
		}
		jsClickByXPath(driver, OR.COACH_CONTENT_CONTRACT_ARTICLES_I_COMMIT_BUTTON);
		wait(driver, "3");
		WebElement success_msg = driver.findElement(By.xpath(OR.COACH_CONTENT_CONTRACT_ARTICLES_SUCCESS_MSG));
		verifyElementIsPresent(driver,  success_msg );
		WebElement Signup_commited_contract = driver.findElement(By.xpath(OR.COACH_CONTENT_CONTRACT_ARTICLES_SIGNED_UP_FOR_YOUR_COMMITMENT_CONTRACT_MSG));
		verifyElementIsPresent(driver,  Signup_commited_contract  );
		driver.close();
		driver.switchTo().window(defaultWindow); 
		try{Thread.sleep(1000);}
		catch(InterruptedException e){e.printStackTrace();}
		wait( driver, "2" );
	}
	
	/**
	 * Name : Vinothkumar.M 
	 * Created Date: 14/June/2016  
	 * Modified Date:     
	 * Description :   Common method to Group session Guide link
	 * Ticket ID :     
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public  static void raGroupSessionGuideLinkDifferentBrowser(WebDriver driver) throws AWTException
	{
		WebElement guideLink= driver.findElement(By.xpath(OR.COACH_CONTENT_SESSION_GUIDE_ARTICLES_LINK));
		verifyElementIsPresent(driver, guideLink);
		String defaultWindow = driver.getWindowHandle();
		try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
		click(guideLink);
		wait(driver, "3");
		ArrayList<String> tabs2=new ArrayList<String>(driver.getWindowHandles());
		wait(driver, "3");
		driver.switchTo().window(tabs2.get(1));
		wait( driver, "4" );
		WebElement guideFullScreen= driver.findElement(By.xpath(COACH_SESSION_GUIDE_FULLSCREEN));
		WebElement guideAutomaticZoom= driver.findElement(By.xpath(COACH_SESSION_GUIDE_AUTOMATICZOOM));
		WebElement guidePagination =driver.findElement(By.xpath(COACH_SESSION_GUIDE_PAGINATION));
		WebElement guidePrint =driver.findElement(By.xpath(COACH_SESSION_GUIDE_PRINT));
		WebElement guideDownload =driver.findElement(By.xpath(COACH_SESSION_GUIDE_DOWNLOAD));
		WebElement guideToggleSlideBar =driver.findElement(By.xpath(COACH_SESSION_GUIDE_TOGGLE_SLIDEBAR));
		verifyElementIsPresent(driver, guideFullScreen);
		verifyElementIsPresent(driver, guideAutomaticZoom);
		verifyElementIsPresent(driver, guidePagination);
		verifyElementIsPresent(driver, guidePrint);
		verifyElementIsPresent(driver, guideDownload);
		verifyElementIsPresent(driver, guideToggleSlideBar);
		jsClickByXPath(driver, OR.COACH_SESSION_PAGNUMBER_INPUT);
		wait( driver, "3" );
		Manipulation.jsClickByXPath(driver, OR.COACH_SESSION_PAGNUMBER_INPUT);
		wait( driver, "3" );
		jsClickByXPath(driver, OR.COACH_SESSION_GUIDE_TOGGLE_SLIDEBAR);
		wait( driver, "5" );
		jsClickByXPath(driver, OR.COACH_SESSION_GUIDE_TOGGLE_SLIDEBAR);
		wait( driver, "5" );
		jsClickByXPath(driver, OR.COACH_SESSION_GUIDE_FULLSCREEN);
		wait( driver, "3" );
		driver.close();
		driver.switchTo().window(defaultWindow); 
		try{Thread.sleep(1000);}
		catch(InterruptedException e){e.printStackTrace();}
		wait( driver, "2" );
	}
	
	/**
	 * Name : Vinothkumar.M 
	 * Created Date: 14/June/2016  
	 * Modified Date:     
	 * Description :   Common method to Group session script link
	 * Ticket ID :     
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public  static void raGroupSessionScriptLinkDifferentBrowser(WebDriver driver) throws AWTException
	{
		wait( driver, "2" ); 
		WebElement Script_btn= driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_SCRIPT_BUTTON));
		verifyElementIsPresent(driver, Script_btn);
		jsClickByXPath(driver, OR.COACH_RA_GROUP_SESSION_SCRIPT_BUTTON);  
		wait(driver, "5" );
		WebElement Script_pdf_link= driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_SCRIPT_LINK));
		verifyElementIsPresent(driver, Script_pdf_link);
		String defaultWindow = driver.getWindowHandle();
		try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
		click(Script_pdf_link);
		wait(driver, "3");
		ArrayList<String> tabs2=new ArrayList<String>(driver.getWindowHandles());
		wait(driver, "3");
		driver.switchTo().window(tabs2.get(1));
		wait( driver, "4" );
		WebElement guideFullScreen= driver.findElement(By.xpath(COACH_SESSION_GUIDE_FULLSCREEN));
		WebElement guideAutomaticZoom= driver.findElement(By.xpath(COACH_SESSION_GUIDE_AUTOMATICZOOM));
		WebElement guidePagination =driver.findElement(By.xpath(COACH_SESSION_GUIDE_PAGINATION));
		WebElement guidePrint =driver.findElement(By.xpath(COACH_SESSION_GUIDE_PRINT));
		WebElement guideDownload =driver.findElement(By.xpath(COACH_SESSION_GUIDE_DOWNLOAD));
		WebElement guideToggleSlideBar =driver.findElement(By.xpath(COACH_SESSION_GUIDE_TOGGLE_SLIDEBAR));
		verifyElementIsPresent(driver, guideFullScreen);
		verifyElementIsPresent(driver, guideAutomaticZoom);
		verifyElementIsPresent(driver, guidePagination);
		verifyElementIsPresent(driver, guidePrint);
		verifyElementIsPresent(driver, guideDownload);
		verifyElementIsPresent(driver, guideToggleSlideBar);
		jsClickByXPath(driver, OR.COACH_SESSION_PAGNUMBER_INPUT);
		wait( driver, "3" );
		Manipulation.jsClickByXPath(driver, OR.COACH_SESSION_PAGNUMBER_INPUT);
		wait( driver, "3" );
		jsClickByXPath(driver, OR.COACH_SESSION_GUIDE_TOGGLE_SLIDEBAR);
		wait( driver, "5" );
		jsClickByXPath(driver, OR.COACH_SESSION_GUIDE_TOGGLE_SLIDEBAR);
		wait( driver, "5" );
		jsClickByXPath(driver, OR.COACH_SESSION_GUIDE_FULLSCREEN);
		wait( driver, "3" );
		driver.close();
		driver.switchTo().window(defaultWindow); 
		try{Thread.sleep(1000);}
		catch(InterruptedException e){e.printStackTrace();}
		wait( driver, "2" );

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
	public static void allowPluginsForDifferentBrowserSupport(WebDriver driver) throws FindFailed, AWTException
	{
		String OSName=Platform.OS.toUpperCase();
		String browser=Platform.BROWSER_NAME.toUpperCase();
		if(OSName.contains("MAC"))
		{
			if (browser.contains("FIREFOX"))
			{
				Robot rb = new Robot();
				wait( driver, "4" );
				rb.keyPress(KeyEvent.VK_CONTROL); 
				rb.keyPress(KeyEvent.VK_S);
				rb.keyRelease(KeyEvent.VK_CONTROL); 
				rb.keyRelease(KeyEvent.VK_S);
				wait( driver, "8" ); 
				rb.keyPress(KeyEvent.VK_S);
				rb.keyRelease(KeyEvent.VK_S);
			}
			else if (browser.contains("CHROME"))
			{
				Screen Allow=new Screen(); 
				wait( driver, "8" );
				Pattern image = new Pattern(Directory.uploadFilePath+"GroupSessionZLiveAllowForChrome.PNG");
				Allow.wait(image, 15);
				Allow.click(image);
				wait( driver, "3" );			
			}
		}
		
	}
	
	/**
	 * Name : VigneshRaj.M 
	 * Created Date: 03/Jan/2017
	 * Modified Date:     
	 * Description :   Common method to RA 1on1 Enter Weight durint attending 1on1 Session
	 * Ticket ID :     
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static void selectExerciseAmountDuringLiveSession(WebDriver driver1)
	{
		try
		{
			waitForElement(driver1, OR.PREVENT_T2_SELECT_EXERCISE_AMOUNT_DROPDOWN);
			WebElement selectExcerciseAmountDropdown= driver1.findElement(By.xpath(OR.PREVENT_T2_SELECT_EXERCISE_AMOUNT_DROPDOWN));
			selectByValue(selectExcerciseAmountDropdown, "Moderate");
			WebElement weightSubmitButton= driver1.findElement(By.xpath(OR.RA_LIVE_SESSION_BEFORE_ATTENDING_ENTER_WEIGHT_SUBMIT_BUTTON));
			click(weightSubmitButton);
		}
		catch (Exception e)
		{

		}
	}
}

