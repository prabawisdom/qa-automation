package com.zillion.qa.realappealmember;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileDeleteStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;

import com.zillion.qa.ZadoReports;
import com.zillion.qa.commands.ElementActions;
import com.zillion.qa.commands.Manipulation;
import com.zillion.qa.commands.Navigate;
import com.zillion.qa.config.ChromeBrowser;
import com.zillion.qa.config.FirefoxBrowser;
import com.zillion.qa.config.IEBrowser;
import com.zillion.qa.config.SafariBrowser;
import com.zillion.qa.enums.LogAs;
import com.zillion.qa.reports.CaptureScreen;
import com.zillion.qa.reports.CaptureScreen.ScreenshotOf;
import com.zillion.qa.utils.Directory;
import com.zillion.qa.utils.Platform;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Objects;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.io.BufferedReader;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class Claims extends Manipulation implements OR {

	/**
	 * Name :    Abinaya.P
	 * Created Date:   08/JUN/16
	 * Modified Date:   
	 * Description : Create a common method to unzip the API Automation report in order to retrieve the member credentials
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ParseException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static void unzipDir(WebDriver driver) throws ClassNotFoundException, ParseException, SQLException 
	{
		File First = null ; 
		try
		{
			String down = "C:/Users/Administrator/Downloads/Test1_report_2016_06_Tu_06_35.zip";
			File dir = new File(down);
			File[] files = dir.listFiles();
			if (files == null || files.length == 0) 
			{      
				System.out.println("File : "+files);      
			}
			File lastModifiedFile = files[0];
			for (int i = 1; i < files.length; i++) {
				if (lastModifiedFile.lastModified() < files[i].lastModified()) {
					lastModifiedFile = files[i];            
					First = lastModifiedFile;  
					System.out.println("Path: "+First);
				}
			}     

			// Specify file to decompress
			String inFileName = First.getPath();

			// Specify destination where file will be unzipped
			String destinationDirectory = "C:/Test";
			File sourceZipFile = new File(inFileName);
			File unzipDestinationDirectory = new File(destinationDirectory);

			// Open Zip file for reading
			ZipFile zipFile = new ZipFile(sourceZipFile, ZipFile.OPEN_READ);

			// Create an enumeration of the entries in the zip file
			Enumeration<?> zipFileEntries = zipFile.entries();

			// Process each entry
			while (zipFileEntries.hasMoreElements())
			{
				// grab a zip file entry
				ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
				String currentEntry = entry.getName();
				System.out.println("Extracting: " + entry);
				File destFile =new File(unzipDestinationDirectory, currentEntry);

				// grab file's parent directory structure
				File destinationParent = destFile.getParentFile();

				// create the parent directory structure if needed
				destinationParent.mkdirs();
				// extract file if not a directory
				if (!entry.isDirectory())
				{
					BufferedInputStream is =new BufferedInputStream(zipFile.getInputStream(entry));
					int currentByte;
					// establish buffer for writing file
					byte data[] = new byte[1024];

					// write the current file to disk
					FileOutputStream fos = new FileOutputStream(destFile);
					BufferedOutputStream dest =new BufferedOutputStream(fos, 1024);

					// read and write until last byte is encountered
					while ((currentByte = is.read(data, 0, 1024)) != -1)
					{
						dest.write(data, 0, currentByte);
					}
					dest.flush();
					dest.close();
					is.close();
				}
			}
			zipFile.close();
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	} 

	/**
	 * Name :    Abinaya.P
	 * Created Date:   08/JUN/16
	 * Modified Date:   
	 * Description : Create a common method to retrieve the member credentials from API Automation report
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ParseException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 * @throws IOException 
	 */
	private static BufferedReader br;
	public static String getMemberFromApiAutomation(WebDriver driver) throws ClassNotFoundException, ParseException, SQLException, IOException 
	{
		String result = null;
		br = new BufferedReader(new FileReader("C://Users//Administrator//Downloads//Members//Test//V1Results//Test1//V1Results//CreateMemberResults.csv"));
		String line;
		while ((line = br.readLine()) != null) 
		{
			// use comma as separator
			String[] cols = line.split(",");

			System.out.println("User Name" + cols[2]);
			result= cols[2];
		}
		return result;
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   08/JUN/16
	 * Modified Date:   
	 * Description : Create a common method to retrieve the member credentials from API Automation report
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ParseException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public static void deletePreviousAPIAutomation(WebDriver driver) throws ClassNotFoundException, ParseException, SQLException, IOException 
	{
		String down = "C:/Test";
		File fina = new File(down);
		for (File file : fina.listFiles())
		{
			try 
			{
				FileDeleteStrategy.FORCE.delete(file);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			System.out.println("Deleted");
		}
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   08/JUN/16
	 * Modified Date:   
	 * Description : Create a common method to pick up the current date minute
	 * Ticket ID :     
	 * Required Inputs :
	 *
	 */
	public static void retrievecurrentMin(WebDriver driver) 
	{
		DateFormat df = new SimpleDateFormat("MMMMM dd");
		Date dateobj = new Date();	
		String currentDate1=df.format(dateobj);
		System.out.println("time:"+currentDate1);
	}

	/**
	 * Name : P.Abinaya    
	 * Created Date: 09/Jun/2016  
	 * Modified Date:     
	 * Description :   Create a common method to retrieve member who has completed customization session and roll up status is closed
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String retreiveMemberWithSessionStatusRollupStatus(WebDriver driver, String sessionstatus, String rollupstatus, String sessiontypeid) throws ClassNotFoundException, SQLException  
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
		ResultSet rs = stat.executeQuery("SELECT ACCOUNT_ID FROM CALENDAR_EVENT where SESSION_STATUS= 'Completed' and ROLLUP_STATUS= 'ROLLUP CLOSED'and session_type_id='13' order by START_DT_TIME");
		System.out.println("query executed");
		String account_id="";
		if(rs.next())
		{
			account_id = rs.getString("ACCOUNT_ID"); 
			System.out.println("Accountid: "+account_id+" of the cliams is retrieved from the Table");
		}
		return account_id;
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   14/JUN/16
	 * Modified Date:   
	 * Description : Create a common method to download the zip file
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ParseException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 * @throws IOException 
	 *
	 */
	public static void downloadFiles(WebDriver driver) 
	{ 
		try 
		{
			Screen screen = new Screen();  
			try 
			{    
				Pattern Save = new Pattern("C:/workspace/zillion_automation/Zillion/testcases/UploadFiles/Save.png");
				screen.wait(Save, 1);
				screen.click(Save);
			} 
			catch(Exception e) 
			{
				Pattern SaveAs = new Pattern("C:/workspace/zillion_automation/Zillion/testcases/UploadFiles/SaveAs.png");
				screen.wait(SaveAs, 1);
				screen.click(SaveAs);
			}
			Pattern Ok = new Pattern("C:/workspace/zillion_automation/Zillion/testcases/UploadFiles/OK.png");
			screen.wait(Ok, 1);
			screen.click(Ok);
		}
		catch(Exception e) 
		{   
		}
	}

	/**
	 * Name : P.Abinaya    
	 * Created Date: 21/Jul/2016  
	 * Modified Date:     
	 * Description :   Create a common method to retrieve gender of the member
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String retrieveGenderOfTheMember(WebDriver driver,String membermailid) throws ClassNotFoundException, SQLException  
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
		ResultSet rs = stat.executeQuery("select gender from account where email ='"+membermailid+"'");
		System.out.println("query executed");
		String gender="";
		if(rs.next())
		{
			gender = rs.getString("membermailid"); 
			System.out.println("Retrieve gender of the member "+gender);
		}
		return gender;
	}

	/**
	 * Name : P.Abinaya    
	 * Created Date: 21/Jul/2016  
	 * Modified Date:     
	 * Description :   Create a common method to read member credentials from property file for claims with the scneario:group and 1on1 session in same interval
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 * @throws IOException 
	 *
	 */
	public static String readMemberForGroup1Claims(WebDriver driver) throws ClassNotFoundException, SQLException, IOException  
	{
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.Reports_Path+"GroupAnd1on1SessionInSameIntervalMember.properties");
		urlsProperties.load(input);
		String member=urlsProperties.getProperty("RAmember1").trim();
		return member;
	}

	/**
	 * Name : P.Abinaya    
	 * Created Date: 21/Jul/2016  
	 * Modified Date:     
	 * Description :   Create a common method to retrieve 
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String checkSessionStatusFor1on1Session(WebDriver driver,String membermailid) throws ClassNotFoundException, SQLException  
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar cal = Calendar.getInstance();
		String date=dateFormat.format(cal.getTime());
		System.out.println(date+":Date");
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select session_status from CALENDAR_EVENT WHERE ACCOUNT_ID IN (SELECT ID FROM ACCOUNT WHERE EMAIL LIKE '"+membermailid+"') and END_DT_TIME_SESSION_LEN like '"+date+"%'");
		System.out.println("query executed");
		String session_status="";
		if(rs.next())
		{
			session_status = rs.getString("session_status"); 
			System.out.println("Retrieve Session status of the member "+session_status);
		}
		return session_status;
	}

	/**
	 * Name : P.Abinaya    
	 * Created Date: 21/Jul/2016  
	 * Modified Date:     
	 * Description :   Create a common method to retrieve checkSession status for group session
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String checkSessionStatusForGroup(WebDriver driver,String membermailid) throws ClassNotFoundException, SQLException  
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar cal = Calendar.getInstance();
		String date=dateFormat.format(cal.getTime());
		System.out.println(date+":Date");
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select * from CALENDAR_EVENT_ATTENDEE WHERE ACCOUNT_ID IN (SELECT ID FROM ACCOUNT WHERE EMAIL LIKE '"+membermailid+"') AND SESSION_ACTUAL_END_DATETIME like '"+date+"%'");
		System.out.println("query executed");
		String session_status="";
		if(rs.next())
		{
			session_status = rs.getString("session_status"); 
			System.out.println("Retrieve Session status of the member "+session_status);
		}
		return session_status;
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   22/JUL/16
	 * Modified Date:   
	 * Description : Create a common method to retrieve member id from database
	 * Ticket ID :     
	 * Required Inputs :Table name-Membername and created date 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	static String GroupSession1memberid ="";
	static String GroupSession1CreatedDate="";
	static String GroupSession1coachEmail ="";
	public static String retrieveMemberID(WebDriver driver,String MemberName,String CDate,String CoachName) throws ClassNotFoundException, SQLException 
	{ 
		Connection conn =null;
		Statement stat=null;
		String port = Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		String CurrentDate = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
		String Month=new SimpleDateFormat("MMM").format(Calendar.getInstance().getTime());
		String CurrentMonth=Month.toUpperCase();
		String Currentyear=new SimpleDateFormat("yy").format(Calendar.getInstance().getTime());
		String date=CurrentDate+"-"+CurrentMonth+"-"+Currentyear;
		String Currentdate=date.trim();
		System.out.println(Currentdate);
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("SELECT SMRY.CREATED_DT,PROGRAM_INTERVAL_NUMBER,SESSION_DATE_UTC, ACNT.EMAIL Member_Email, PROV.EMAIL Coach_Email FROM SUMMARY_ACCOUNT_TODATE SMRY,PROVIDER PROV, ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID AND SMRY.ACCOUNT_ID=ACNT.ID AND SMRY.ONBOARDING_STATUS='MEMBER ONBOARDED' AND ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ID IN(SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_STATUS='Scheduled' AND SESSION_TYPE_ID='01' AND MP_INTERVAL_NUMBER > (SELECT MAX(MP_INTERVAL_NUMBER) FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_TYPE_ID='01' AND SCHEDULED_DT<SYSDATE AND SCHEDULED_DT>SYSDATE + INTERVAL '-1' DAY)))AND ACNT.EMAIL not like '%api%'AND PROV.EMAIL not like '%api%'and SMRY.CREATED_DT not like '"+Currentdate+"' and PROGRAM_INTERVAL_NUMBER >1 ORDER BY SESSION_DATE_UTC DESC");
		System.out.println("query executed");
		if(rs.next())
		{
			GroupSession1memberid = rs.getString(MemberName);
			GroupSession1coachEmail = rs.getString(CoachName);
			GroupSession1CreatedDate = rs.getString(CDate);
			System.out.println("MemberEmail ID: "+GroupSession1memberid);
			System.out.println("CoachEmail ID: "+GroupSession1coachEmail);
			System.out.println("Created Date: "+GroupSession1CreatedDate);
		}
		return GroupSession1memberid;
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
	public  static void retrieveCoachIdandLogin(WebDriver driver)
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
		sendKeys(coach_username,GroupSession1coachEmail);
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
	public static String retrieveMemberIdandLogin(WebDriver driver1) throws ClassNotFoundException, ParseException, SQLException 
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
		sendKeys(memberUsername,GroupSession1memberid);
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
		return GroupSession1memberid;
	}


	/**
	 * Name :    Abinaya.P
	 * Created Date:   22/JUL/16
	 * Modified Date:   
	 * Description : Create a common method to retrieve coach id from database
	 * Ticket ID :     
	 * Required Inputs :coach id table name
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String retrieveCoachID(WebDriver driver,String coach) throws ClassNotFoundException, SQLException 
	{ 
		Connection conn =null;
		Statement stat=null;
		String coachid ="";
		String port = Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		String CurrentDate = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
		String Month=new SimpleDateFormat("MMM").format(Calendar.getInstance().getTime());
		String CurrentMonth=Month.toUpperCase();
		String Currentyear=new SimpleDateFormat("yy").format(Calendar.getInstance().getTime());
		String date=CurrentDate+"-"+CurrentMonth+"-"+Currentyear;
		String Currentdate=date.trim();
		System.out.println(Currentdate);
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("SELECT SMRY.CREATED_DT,PROGRAM_INTERVAL_NUMBER,SESSION_DATE_UTC, ACNT.EMAIL Member_Email, PROV.EMAIL Coach_Email FROM SUMMARY_ACCOUNT_TODATE SMRY,PROVIDER PROV, ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID AND SMRY.ACCOUNT_ID=ACNT.ID AND SMRY.ONBOARDING_STATUS='MEMBER ONBOARDED' AND ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ID IN(SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_STATUS='Scheduled' AND SESSION_TYPE_ID='01' AND MP_INTERVAL_NUMBER > (SELECT MAX(MP_INTERVAL_NUMBER) FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_TYPE_ID='01' AND SCHEDULED_DT<SYSDATE AND SCHEDULED_DT>SYSDATE + INTERVAL '-1' DAY)))AND ACNT.EMAIL not like '%api%'AND PROV.EMAIL not like '%api%'and SMRY.CREATED_DT not like '"+Currentdate+"' and PROGRAM_INTERVAL_NUMBER >1 ORDER BY SESSION_DATE_UTC DESC");
		System.out.println("query executed");
		System.out.println("mailId"+coach);
		if(rs.next())
		{
			coachid = rs.getString(coach);
			System.out.println(coachid);
		}
		return coachid;
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   22/JUL/16
	 * Modified Date:   
	 * Description : Create a common method to get current member session time
	 * Ticket ID :     
	 * Required Inputs :No input
	 * @throws ParseException
	 *
	 */
	static String sessiontime1=null;
	static String GroupAttendNow=null;
	static String SessionTime=null;
	public  static String raMemberSessionTime(WebDriver driver) throws ParseException
	{
		WebElement memberSessionTime = driver.findElement(By.xpath(OR.RA_MEMBER_SESSION_TIME));
		String sessionsTime=memberSessionTime.getText();
		String[]Time=sessionsTime.split("\\s");
		GroupAttendNow=Time[0];
		SessionTime=Time[1].concat(Time[2]);
		SimpleDateFormat ft = new SimpleDateFormat("EEE hhh:mm a");
		java.util.Date t=ft.parse(sessionsTime);
		ft.applyPattern("hh:mma");
		sessiontime1=ft.format(t).toLowerCase().trim();
		System.out.print(ft.format(t));
		System.out.print("Time"+SessionTime);
		return GroupAttendNow+","+SessionTime;
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   22/JUL/16
	 * Modified Date:   
	 * Description : Create a common method to attend the session 
	 * Ticket ID :     
	 * Required Inputs :No input
	 * @throws ParseException
	 *
	 */
	public  static void attendnow(WebDriver driver)
	{
		wait( driver, "20" );
		String attendnoeor ="//tr//td[contains(text(),'"+GroupAttendNow+"')]/following::td[contains(text(),'"+SessionTime+"')]/following-sibling::td[7]/div/button";
		System.out.println("GroupAttendNow"+GroupAttendNow);
		System.out.println("SessionTime"+SessionTime);
		waitForElement(driver, attendnoeor);
		jsClickByXPath(driver, attendnoeor);
		wait( driver, "20" );
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   25/JUL/16
	 * Modified Date:   
	 * Description : Create a common method to retrieve program interval number 
	 * Ticket ID :     
	 * Required Inputs :Member id
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	static String GroupSession1Programinterval ="";
	public static String retrieveprograminterval(WebDriver driver,String ID) throws ClassNotFoundException, SQLException 
	{ 
		Connection conn =null;
		Statement stat=null;
		String port = Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		System.out.println("ID"+ID);
		stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select PROGRAM_INTERVAL_NUMBER  FROM SUMMARY_ACCOUNT_TODATE SMRY,PROVIDER PROV, ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID AND SMRY.ACCOUNT_ID=ACNT.ID AND ACNT.EMAIL='"+ID+"'");			
		System.out.println("query executed");
		if(rs.next())
		{
			GroupSession1Programinterval = rs.getString("PROGRAM_INTERVAL_NUMBER");
			System.out.println("Program Interval number is "+GroupSession1Programinterval);
		}
		return GroupSession1Programinterval;
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   25/JUL/16
	 * Modified Date:   
	 * Description : Create a common method to reduce weight in tracker  
	 * Ticket ID :     
	 * Required Inputs :Program interval number
	 *
	 */
	static String GroupSession1Reduceweight="";
	public static String Weightloss(WebDriver driver,String ProgramInterval)
	{
		String Currentweight="";
		double Reducedweight;
		//		Navigate.refreshPage(driver);
		WebElement MemberTracker = driver.findElement(By.xpath(RA_MEMBER_TRACKERS_TAB));
		Manipulation.click(MemberTracker);
		Manipulation.wait(driver, "5");
		WebElement WeightTab = driver.findElement(By.xpath(RA_MEMBER_WEIGHT_SUB_TAB));
		Manipulation.verifyElementIsPresent(driver, WeightTab);
		Manipulation.click(WeightTab);
		Manipulation.wait(driver, "2");
		WebElement TrackWeight = driver.findElement(By.xpath(RA_MEMBER_TRACK_WEIGHT_HEADER_TEXT));
		Manipulation.verifyElementIsPresent(driver, TrackWeight);
		Manipulation.waitForElement(driver, RA_MEMBER_CURRENT_WEIGHT);
		WebElement CurrentWeight = driver.findElement(By.xpath(RA_MEMBER_CURRENT_WEIGHT));
		Currentweight=ElementActions.getText(CurrentWeight);
		System.out.println("Current weight value"+Currentweight);
		double Currentweight1 = Double.parseDouble(Currentweight);
		double Interval = Double.parseDouble(ProgramInterval);
		Reducedweight=Currentweight1-(Interval*0.4);
		DecimalFormat df1 = new DecimalFormat("0.0");
		df1.setMaximumFractionDigits(1);
		String weightreduced = df1.format(Reducedweight);
		System.out.println("Reduce weight value"+weightreduced);
		String WeightReduced = String.valueOf(weightreduced);
		Manipulation.waitForElement(driver, RA_MEMBER_WEIGHT_REDUCE_TEXTBOX);
		WebElement ReduceWeight= driver.findElement(By.xpath(RA_MEMBER_WEIGHT_REDUCE_TEXTBOX));
		Manipulation.clearAndType(ReduceWeight,WeightReduced);
		Manipulation.wait(driver, "1");
		WebElement SaveButton= driver.findElement(By.id(RA_MEMBER_TRACKER_WEIGHT_SAVE_BUTTON));
		Manipulation.click(SaveButton);
		Manipulation.wait(driver, "7");
		WebElement CurrentWeight1 = driver.findElement(By.xpath(RA_MEMBER_CURRENT_WEIGHT));
		GroupSession1Reduceweight= ElementActions.getText(CurrentWeight1);
		if(GroupSession1Reduceweight.equalsIgnoreCase(WeightReduced))
		{
			return "CurrentWeight is reduced from"+Currentweight+"to"+GroupSession1Reduceweight;
		}
		else
		{
			return "CurrentWeight"+Currentweight+"is not reduced";
		}
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   26/JUL/16
	 * Modified Date:   
	 * Description : Create a common method to append url
	 * Ticket ID :     
	 * Required Input:Url append
	 *
	 */
	public  static String AppendURL(WebDriver driver,String append)
	{
		String getCurrentURL= driver.getCurrentUrl();
		String appendCurrentURL= getCurrentURL+append;
		System.out.println("Append URL to force attend the Group session: "+appendCurrentURL);
		Navigate.get( driver, appendCurrentURL );
		wait( driver, "10" );
		return appendCurrentURL;
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   28/JUL/16
	 * Modified Date:   
	 * Description : Create a common method to validate calender event attendee 
	 * Ticket ID :     
	 * Required Inputs :ATTENDANCE_PRTCPNT_MINUTES,ATTENDANCE_STATUS,SESSION_ACTUAL_MINUTES and ROLLUP_STATUS values 
	 * @throws SQLException
	 *@throws IOException
	 *@throws ParseException
	 *@throws ClassNotFoundException
	 */
	static String memberid,createdDate;
	public static String validateCalenderEventAttendeeForGrp1Claims(WebDriver driver,String inputAmint,String Attend_status,String inputSmin, String status ) throws ClassNotFoundException, SQLException, IOException, ParseException 
	{ 
		String Event="",Arrival="",Departure="",AMinutes="",AStatus="",Smin="",RollUpstatus="";
		String port = Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.Reports_Path+"GroupAnd1on1SessionInSameIntervalMember.properties");
		urlsProperties.load(input);
		memberid=urlsProperties.getProperty("MEMBER_ID").trim();
		System.out.println(memberid);
		String date=urlsProperties.getProperty("SMRY.CREATED_DATE").trim();
		String[] Date=date.split("\\s");
		String CDate=Date[0];
		DateFormat df1 = new SimpleDateFormat("yyy-MM-dd"); // for parsing input
		DateFormat df2 = new SimpleDateFormat("dd-MMM-yy");  // for formatting output
		Date d = df1.parse(CDate);
		String CreatedDate = df2.format(d);
		String[] format=CreatedDate.split("-");
		String UpperCaseMonth=format[1].toUpperCase();
		String concat=format[0]+"-"+UpperCaseMonth+"-"+format[2];		
		createdDate=concat.concat("%");
		System.out.println(createdDate);
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("SELECT EVENT_START_DT, ATTENDANCE_PRTCPNT_ARRIVAL, ATTENDANCE_PRTCPNT_DEPARTURE, ATTENDANCE_PRTCPNT_MINUTES, ATTENDANCE_STATUS, SESSION_ACTUAL_MINUTES, ROLLUP_STATUS FROM CALENDAR_EVENT_ATTENDEE WHERE ACCOUNT_ID IN (SELECT ID FROM ACCOUNT WHERE EMAIL LIKE '"+memberid+"') and EVENT_START_DT Like '"+createdDate+"' order by EVENT_START_DT");
		System.out.println("query executed");
		if(rs.next())
		{
			Event = rs.getString("EVENT_START_DT");
			Arrival = rs.getString("ATTENDANCE_PRTCPNT_ARRIVAL");
			Departure=rs.getString("ATTENDANCE_PRTCPNT_DEPARTURE");
			AMinutes = rs.getString("ATTENDANCE_PRTCPNT_MINUTES");
			AStatus = rs.getString("ATTENDANCE_STATUS");
			Smin = rs.getString("SESSION_ACTUAL_MINUTES");
			RollUpstatus = rs.getString("ROLLUP_STATUS");
			System.out.println("Validated successfully"+Event+","+Arrival+","+","+Departure+","+AMinutes+","+AStatus+","+Smin+","+RollUpstatus);
		}
		if(Arrival!=null&&Departure!=null&&AMinutes.equalsIgnoreCase(inputAmint)&&AStatus.equalsIgnoreCase(Attend_status)&&Smin.equalsIgnoreCase(inputSmin)&&RollUpstatus.equalsIgnoreCase(status))
		{
			return "Validated successfully"+Event+","+Arrival+","+","+Departure+","+AMinutes+","+AStatus+","+Smin+","+RollUpstatus;
		}
		else
		{
			return"Validation Failed";
		}
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   28/JUL/16
	 * Modified Date:   
	 * Description : Create a common method to validate summary  
	 * Ticket ID :     
	 * Required Inputs :ATTEND_THIS_INTVL_STATUS_REASN,ATTEND_THIS_INTVL_STATUS,WEIGHT_THIS_INTVL_STATUS,CURRENT_OVERALL_STATUS and CURRENT_OVERALL_STATUS_REASON  values 
	 * @throws SQLException
	 *@throws IOException
	 *@throws ParseException
	 *@throws ClassNotFoundException
	 */
	static String ProgramInterval;
	public static String validateSummaryForGrp1Claims(WebDriver driver,String attendstatusreason,String attendstatus,String weightstatus,String Currentstatus,String CurrentOverallstatus) throws ClassNotFoundException, SQLException, IOException 
	{ 
		String Inumber="",Istart="",Iend="",Areason="",Astatus="",Wenroll="",Winterval="",WintervalStatus="",CStatus="",CStatusReason="";
		String port = Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.Reports_Path+"GroupAnd1on1SessionInSameIntervalMember.properties");
		urlsProperties.load(input);
		ProgramInterval=urlsProperties.getProperty("Program_Interval_Number").trim();
		String ReducedWeightvalue=urlsProperties.getProperty("Reduced_weight").trim();
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("SELECT PROGRAM_INTERVAL_NUMBER,PROGRAM_INTERVAL_START_DT, PROGRAM_INTERVAL_END_DT, ATTEND_THIS_INTVL_STATUS_REASN, ATTEND_THIS_INTVL_STATUS, WEIGHT_ENROLL, WEIGHT_THIS_INTVL, WEIGHT_THIS_INTVL_STATUS, CURRENT_OVERALL_STATUS, CURRENT_OVERALL_STATUS_REASON FROM SUMMARY_ACCOUNT_INTERVAL WHERE ACCOUNT_ID IN (SELECT ID FROM ACCOUNT WHERE EMAIL LIKE '"+memberid+"') and PROGRAM_INTERVAL_NUMBER='"+ProgramInterval+"' order by PROGRAM_INTERVAL_NUMBER");
		System.out.println("query executed");
		if(rs.next())
		{
			Inumber = rs.getString("PROGRAM_INTERVAL_NUMBER");
			Istart = rs.getString("PROGRAM_INTERVAL_START_DT");
			Iend=rs.getString("PROGRAM_INTERVAL_END_DT");
			Areason = rs.getString("ATTEND_THIS_INTVL_STATUS_REASN");
			Astatus = rs.getString("ATTEND_THIS_INTVL_STATUS");
			Wenroll = rs.getString("WEIGHT_ENROLL");
			Winterval = rs.getString("WEIGHT_THIS_INTVL");
			WintervalStatus = rs.getString("WEIGHT_THIS_INTVL_STATUS");
			CStatus = rs.getString("CURRENT_OVERALL_STATUS");
			CStatusReason = rs.getString("CURRENT_OVERALL_STATUS_REASON");
		}
		if(Inumber.equalsIgnoreCase(ProgramInterval)&&Istart!=null&&Iend!=null&&Areason.equalsIgnoreCase(attendstatusreason)&&Astatus.equalsIgnoreCase(attendstatus)&&Wenroll!=null&&Winterval.equalsIgnoreCase(ReducedWeightvalue)&&WintervalStatus.equalsIgnoreCase(weightstatus)&&CStatus.equalsIgnoreCase(Currentstatus)&&CStatusReason.equalsIgnoreCase(CurrentOverallstatus))
		{
			return "Validated successfullly"+Inumber+","+Istart+","+","+Iend+","+Areason+","+Astatus+","+Wenroll+","+Winterval+","+WintervalStatus+","+CStatus+","+CStatusReason;
		}
		else
		{
			return"Validation failed";
		}
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   28/JUL/16
	 * Modified Date:   
	 * Description : Create a common method to validate achievements  
	 * Ticket ID :     
	 * Required Inputs :No inputs
	 * @throws SQLException
	 *@throws ClassNotFoundException
	 */
	public static String validateAchievementsGrp1Claims(WebDriver driver) throws ClassNotFoundException, SQLException 
	{ 
		String LoggedDate="",InsID="";
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
		ResultSet rs = stat.executeQuery("SELECT LOGGED_DT, INS_RULE_ID from ACCOUNT_INS_ACHIEVEMENT WHERE ACCOUNT_ID IN (SELECT ID FROM ACCOUNT WHERE EMAIL LIKE '"+memberid+"') and LOGGED_DT Like '"+createdDate+"' ORDER BY LOGGED_DT");
		System.out.println("query executed");
		if(rs.next())
		{	
			LoggedDate = rs.getString("LOGGED_DT");
			InsID = rs.getString("INS_RULE_ID");	
		}
		if(LoggedDate!=null&&InsID!=null)
		{
			return "Validated successfully"+LoggedDate+","+InsID;
		}
		else
		{
			return"Validation failed";
		}
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   28/JUL/16
	 * Modified Date:   
	 * Description : Create a common method to Check Account
	 * Ticket ID :     
	 * Required Inputs :No inputs
	 * @throws SQLException
	 *@throws ClassNotFoundException
	 */
	public static String checkAccount(WebDriver driver)
	{
		try
		{
			WebElement signInDiffercc= driver.findElement(By.xpath(SIGN_WITH_DIFFERENT_ACCOUNT));
			click(signInDiffercc);
			WebElement addAccount=driver.findElement(By.xpath(ADD_ACCOUNT));
			click(addAccount);
		}
		catch(Exception e)
		{
			System.out.println("Already logged");
		}
		return "login now";
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   28/JUL/16
	 * Modified Date:   
	 * Description : Create a common method to type message body
	 * Ticket ID :     
	 * Required Inputs :No inputs
	 * @throws SQLException
	 *@throws ClassNotFoundException
	 */
	public static void typemessagebody(WebDriver driver) throws IOException
	{
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.Reports_Path+"GroupAnd1on1SessionInSameIntervalMember.properties");
		urlsProperties.load(input);
		String Memberid=urlsProperties.getProperty("MEMBER_ID").trim();
		WebElement message_area= driver.findElement(By.xpath(MESSAGE_AREA));
		actionType(driver,message_area,": "+Memberid+" " );
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   01/AUG/16
	 * Modified Date:   
	 * Description : Create a common method to create property file to store member id,summary created date,Program interval number and reduce weight
	 * Ticket ID :     
	 * Required Inputs :No input
	 */
	public static void createpropertyforgroupsession1()
	{
		try {
			Properties properties = new Properties();
			properties.setProperty("MEMBER_ID",GroupSession1memberid);
			properties.setProperty("SMRY.CREATED_DATE", GroupSession1CreatedDate);
			properties.setProperty("Program_Interval_Number",GroupSession1Programinterval);
			properties.setProperty("Reduced_weight", GroupSession1Reduceweight);
			File file = new File(Directory.Reports_Path+"GroupAnd1on1SessionInSameIntervalMember.properties");
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
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   03/AUG/16
	 * Modified Date:   
	 * Description : Create a common method to send a mail for the group and 1on1 session attends in the same interval
	 * Ticket ID :     
	 * Required Inputs :
	 */  	
	public static final String user = null;
	public static final String pass =null;
	public static void groupAnd1on1Sendemail(final String username,final String password,String from,String to,String cc,String subject,String body,String thanks) throws Exception 
	{  	 		   
		//Get the session object 
		Properties props = new Properties();  
		props.put( "mail.smtp.starttls.enable", true );
		props.put( "mail.smtp.auth", true );
		props.put( "mail.smtp.host", "smtp.gmail.com" );
		props.put( "mail.smtp.port", "587" );
		Session session = Session.getDefaultInstance(props,  
				new javax.mail.Authenticator() {  
			protected PasswordAuthentication getPasswordAuthentication()
			{  
				return new PasswordAuthentication(username,password);  
			}  
		});  
		//Compose the message  
		try {  
			MimeMessage message = new MimeMessage(session);  
			message.setFrom(new InternetAddress(from));  
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to)); 
			message.setRecipients( Message.RecipientType.CC,
					InternetAddress.parse(cc) );
			message.setSubject(subject);  
			//send the message 
			Properties urlsProperties = new Properties();
			InputStream input = new FileInputStream(Directory.Reports_Path+"GroupAnd1on1SessionInSameIntervalMember.properties");
			urlsProperties.load(input);
			String Memberid=urlsProperties.getProperty("MEMBER_ID").trim();
			String messagebody = Memberid;
			System.out.println("Message body : "+messagebody);
			message.setText(body+" "+messagebody+" "+thanks);		 			 
			Transport.send(message); 			     
			System.out.println("message sent successfully...");  
		} catch (MessagingException e) {e.printStackTrace();}  
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   03/AUG/16
	 * Modified Date:   
	 * Description : Create a common method to get RA member for the missed group session 
	 * Ticket ID :     
	 * Required Inputs :No inputs
	 * @throws SQLException
	 *@throws ClassNotFoundException
	 */
	public static String memberforgrpsessionmissed="";
	public static String creationdategroupsessionmissed="";
	public static String getMemberForGrpSessionMissed(WebDriver driver) throws ClassNotFoundException, SQLException 
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
		ResultSet rs = stat.executeQuery("select ACNT.EMAIL member, CE.START_DT_TIME start_dt FROM CALENDAR_EVENT CE , CALENDAR_EVENT_ATTENDEE CEA, ACCOUNT ACNT WHERE CE.ID= CEA.EVENT_ID AND ACNT.ID= CEA.ACCOUNT_ID AND CE.SESSION_TYPE_ID= '01' AND CE.SESSION_STATUS= 'Missed' AND CE.START_DT<SYSDATE and ACNT.STATUS= 'Active' AND ACNT.EMAIL not like '%api%' ORDER BY CE. START_DT DESC");
		System.out.println("query executed");
		if(rs.next())
		{	
			memberforgrpsessionmissed = rs.getString("member");
			creationdategroupsessionmissed=	rs.getString("start_dt");
			System.out.println("Member Maild ID :"+memberforgrpsessionmissed);
		}

		return memberforgrpsessionmissed;
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   03/AUG/16
	 * Modified Date:   
	 * Description : Create a common method to create property file to store member id,summary created date,Program interval number and reduce weight for claims group session scenario#3
	 * Ticket ID :     
	 * Required Inputs :
	 */
	public static void createpropertyforgroupsession3()
	{
		try 
		{
			Properties properties = new Properties();
			properties.setProperty("MEMBER_ID",memberforgrpsessionmissed);
			properties.setProperty("SMRY.CREATED_DATE", creationdategroupsessionmissed);
			properties.setProperty("Program_Interval_Number",GroupSession1Programinterval);
			properties.setProperty("Reduced_weight", GroupSession1Reduceweight);
			File file = new File(Directory.uploadFilePath+"MissedGroupAnd1on1Session.properties");
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
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   03/JUL/16
	 * Modified Date:   
	 * Description : Create a common method to retrieve member id from database
	 * Ticket ID :     
	 * Required Inputs :Table name-Membername and created date 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	static String GroupSession2memberid ="";
	static String GroupSession2CreatedDate="";
	public static String retrieveMemberIDGroupSession2(WebDriver driver) throws ClassNotFoundException, SQLException 
	{ 
		Connection conn =null;
		Statement stat=null;
		String port = Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		String CurrentDate = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
		String Month=new SimpleDateFormat("MMM").format(Calendar.getInstance().getTime());
		String CurrentMonth=Month.toUpperCase();
		String Currentyear=new SimpleDateFormat("yy").format(Calendar.getInstance().getTime());
		String date=CurrentDate+"-"+CurrentMonth+"-"+Currentyear;
		String Currentdate=date.trim();
		System.out.println(Currentdate);
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("SELECT SMRY.CREATED_DT as GroupSession1CreatedDate,PROGRAM_INTERVAL_NUMBER,SESSION_DATE_UTC, ACNT.EMAIL Member_Email, PROV.EMAIL Coach_Email FROM SUMMARY_ACCOUNT_TODATE SMRY,PROVIDER PROV, ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID AND SMRY.ACCOUNT_ID=ACNT.ID AND SMRY.ONBOARDING_STATUS='MEMBER ONBOARDED' AND ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ID IN(SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_STATUS='Scheduled' AND SESSION_TYPE_ID='01' AND MP_INTERVAL_NUMBER > (SELECT MAX(MP_INTERVAL_NUMBER) FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_TYPE_ID='01' AND SCHEDULED_DT<SYSDATE AND SCHEDULED_DT>SYSDATE + INTERVAL '-1' DAY)))AND ACNT.EMAIL not like '%api%'AND PROV.EMAIL not like '%api%'and SMRY.CREATED_DT not like '"+Currentdate+"' and PROGRAM_INTERVAL_NUMBER >1 ORDER BY SESSION_DATE_UTC Desc");
		System.out.println("query executed");

		if(rs.next())
		{
			GroupSession2memberid = rs.getString("Member_Email");
			GroupSession2CreatedDate = rs.getString("GroupSession1CreatedDate");
			System.out.println(GroupSession2memberid);
			System.out.println(GroupSession2CreatedDate);
		}
		return GroupSession2memberid;
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   03/AUG/16
	 * Modified Date:   
	 * Description : Create a common method to create property file to store member id,summary created date,Program interval number and reduce weight for claims group session scenario#2
	 * Ticket ID :     
	 * Required Inputs :
	 */
	public static void createpropertyforgroupsession2()
	{
		try 
		{
			Properties properties = new Properties();
			properties.setProperty("MEMBER_ID",GroupSession2memberid);
			properties.setProperty("SMRY.CREATED_DATE",GroupSession2CreatedDate );
			properties.setProperty("Program_Interval_Number",GroupSession1Programinterval);
			properties.setProperty("Reduced_weight", GroupSession1Reduceweight);
			File file = new File(Directory.Reports_Path+"AttendedGroupMissed1on1Session.properties");
			FileOutputStream fileOut = new FileOutputStream(file);
			properties.store(fileOut, "This is to store member for claims");
			fileOut.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   03/JUL/16
	 * Modified Date:   
	 * Description : Create a common method to retrieve coach id from database
	 * Ticket ID :     
	 * Required Inputs :Table name-Membername and created date 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String retrieveCoachIDGroupSession2(WebDriver driver) throws ClassNotFoundException, SQLException 
	{ 
		Connection conn =null;
		Statement stat=null;
		String port = Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		String CurrentDate = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
		String Month=new SimpleDateFormat("MMM").format(Calendar.getInstance().getTime());
		String CurrentMonth=Month.toUpperCase();
		String Currentyear=new SimpleDateFormat("yy").format(Calendar.getInstance().getTime());
		String date=CurrentDate+"-"+CurrentMonth+"-"+Currentyear;
		String Currentdate=date.trim();
		System.out.println(Currentdate);
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("SELECT SMRY.CREATED_DT as GroupSession1CreatedDate,PROGRAM_INTERVAL_NUMBER,SESSION_DATE_UTC, ACNT.EMAIL Member_Email, PROV.EMAIL Coach_Email FROM SUMMARY_ACCOUNT_TODATE SMRY,PROVIDER PROV, ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID AND SMRY.ACCOUNT_ID=ACNT.ID AND SMRY.ONBOARDING_STATUS='MEMBER ONBOARDED' AND ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ID IN(SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_STATUS='Scheduled' AND SESSION_TYPE_ID='01' AND MP_INTERVAL_NUMBER > (SELECT MAX(MP_INTERVAL_NUMBER) FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_TYPE_ID='01' AND SCHEDULED_DT<SYSDATE AND SCHEDULED_DT>SYSDATE + INTERVAL '-1' DAY)))AND ACNT.EMAIL not like '%api%'AND PROV.EMAIL not like '%api%'and SMRY.CREATED_DT not like '"+Currentdate+"' and PROGRAM_INTERVAL_NUMBER >1 ORDER BY SESSION_DATE_UTC Desc");
		System.out.println("query executed");
		String coach=null;
		if(rs.next())
		{
			coach = rs.getString("Coach_Email");
			System.out.println(coach);
		}
		return coach;
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   17/AUG/16
	 * Modified Date:   
	 * Description : Create a common method to handle RA member connectivity issue 
	 * Ticket ID :     
	 * Required Inputs :No inputs
	 */
	public static void handleRAConnectivityIssue(WebDriver driver)  
	{ 
		try
		{
			waitForElement(driver, RA_CONNECTIVITY_ISSUE_OK);
			jsClickByXPath(driver, RA_CONNECTIVITY_ISSUE_OK);
			wait(driver, "5");
			jsClickByXPath(driver, RA_CONNECTIVITY_ISSUE_OK);
		}
		catch(Exception e)
		{

		}
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   18/AUG/16
	 * Modified Date:   
	 * Description : Create a common method to retrieve member which has interval n=1 from database 
	 * Ticket ID :     
	 * Required Inputs :Table name-Membername and created date 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	static String memberhasnis1 ="";
	static String created_dt_memhasnis1 ="";
	static String coachEmail ="";

	public static String retrieveMemberHasNIs1(WebDriver driver) throws ClassNotFoundException, SQLException 
	{ 
		Connection conn =null;
		Statement stat=null;
		String port = Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("SELECT ACNT.EMAIL Member_Email, PROV.EMAIL Coach_Email, SMRY.created_dt creatd_dt FROM SUMMARY_ACCOUNT_TODATE SMRY, PROVIDER PROV, ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID AND SMRY.ACCOUNT_ID=ACNT.ID AND SMRY.ONBOARDING_STATUS='CLASSROOM NOT STARTED' AND ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ACCOUNT_PROGRAM.START_DT_TIME>SYSDATE + INTERVAL '-10' DAY AND ID IN (SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_TYPE_ID='01'))ORDER BY SMRY.SESSION_DATE_UTC ASC");
		System.out.println("query executed");
		String result= "";
		if(rs.next())
		{
			memberhasnis1 = rs.getString("MEMBER_EMAIL");
			created_dt_memhasnis1= rs.getString("creatd_dt");
			coachEmail= rs.getString("COACH_EMAIL");
			System.out.println(memberhasnis1);
			System.out.println(coachEmail);
			result= memberhasnis1+","+coachEmail;
		}
		return result;
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   18/AUG/16
	 * Modified Date:   
	 * Description : Create a common method to create property file to store member id for claims (Group session for the member is in first interval)
	 * Ticket ID :     
	 * Required Inputs :No input
	 */
	public static void createPropertyforMemberHasNIs1()
	{
		try 
		{
			Properties properties = new Properties();
			properties.setProperty("MEMBER_ID",memberhasnis1);
			properties.setProperty("CREATED_DATE",created_dt_memhasnis1);
			File file = new File(Directory.uploadFilePath+"//GroupMemberIsInFirstInterval.properties");
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
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   18/AUG/16
	 * Modified Date:   
	 * Description : Create a common method to retrieve member for the cliams (without weight loss)
	 * Ticket ID :     
	 * Required Inputs :Table name-Membername and created date 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	static String GroupmemberidWithoutWeightLoss ="";
	static String GroupCreatedDateWithoutWeightLoss="";
	public static String retrieveMemberWithoutWeightLoss(WebDriver driver) throws ClassNotFoundException, SQLException 
	{ 
		Connection conn =null;
		Statement stat=null;
		String port = Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		String CurrentDate = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
		String Month=new SimpleDateFormat("MMM").format(Calendar.getInstance().getTime());
		String CurrentMonth=Month.toUpperCase();
		String Currentyear=new SimpleDateFormat("yy").format(Calendar.getInstance().getTime());
		String date=CurrentDate+"-"+CurrentMonth+"-"+Currentyear;
		String Currentdate=date.trim();
		System.out.println(Currentdate);
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("SELECT SMRY.CREATED_DT as CDate,SMRY.PROGRAM_INTERVAL_NUMBER,SESSION_DATE_UTC session_dt, ACNT.EMAIL Member_Email, PROV.EMAIL Coach_Email FROM SUMMARY_ACCOUNT_TODATE SMRY,PROVIDER PROV, ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID AND SMRY.ACCOUNT_ID=ACNT.ID AND SMRY.ONBOARDING_STATUS='MEMBER ONBOARDED' AND ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ID IN(SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_STATUS='Scheduled' AND SESSION_TYPE_ID='01' AND MP_INTERVAL_NUMBER > (SELECT MAX(MP_INTERVAL_NUMBER) FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_TYPE_ID='01' AND SCHEDULED_DT<SYSDATE AND SCHEDULED_DT>SYSDATE + INTERVAL '-1' DAY)))AND ACNT.EMAIL not like '%api%'AND PROV.EMAIL not like '%api%'and SMRY.CREATED_DT not like '"+Currentdate+"' and PROGRAM_INTERVAL_NUMBER >1 ORDER BY SESSION_DATE_UTC ASC");
		System.out.println("query executed");
		String coachEmail ="";
		while(rs.next())
		{
			GroupmemberidWithoutWeightLoss = rs.getString("Member_Email");
			GroupCreatedDateWithoutWeightLoss = rs.getString("session_dt");
			coachEmail= rs.getString("COACH_EMAIL");
			System.out.println(GroupmemberidWithoutWeightLoss);
			System.out.println(GroupCreatedDateWithoutWeightLoss);
		}
		String result=GroupmemberidWithoutWeightLoss+","+coachEmail;
		return result;
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   18/AUG/16
	 * Modified Date:   
	 * Description : Create a common method to create property file to store member id,summary created date for the claims (Member Without weight loss)
	 * Ticket ID :     
	 * Required Inputs :No input
	 */
	public static void createPropertyforMemberWithoutWeightLoss()
	{
		try {
			Properties properties = new Properties();
			properties.setProperty("MEMBER_ID",GroupmemberidWithoutWeightLoss);
			properties.setProperty("SMRY.CREATED_DATE", GroupCreatedDateWithoutWeightLoss);
			File file = new File(Directory.uploadFilePath+"//GroupWithoutWeightLoss.properties");
			FileOutputStream fileOut = new FileOutputStream(file);
			properties.store(fileOut, "This is to store member for claims");
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   24/AUG/16
	 * Modified Date:   
	 * Description : Create a common method to get RA member for the missed group session and attend 1on1 session when coach and member has no overlap
	 * Ticket ID :     
	 * Required Inputs :No inputs
	 * @throws SQLException
	 *@throws ClassNotFoundException
	 */
	public static String memberforgrpsessionmissednooverlap="";
	public static String creationdategroupsessionmissednooverlap="";
	public static String getMemberForGrpSessionMissedNoOverlap(WebDriver driver) throws ClassNotFoundException, SQLException 
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
		ResultSet rs = stat.executeQuery("select ACNT.EMAIL as member, CE.START_DT_TIME as created_date FROM CALENDAR_EVENT CE , CALENDAR_EVENT_ATTENDEE CEA, ACCOUNT ACNT WHERE CE.ID= CEA.EVENT_ID AND ACNT.ID= CEA.ACCOUNT_ID AND CE.SESSION_TYPE_ID= '01' AND CE.SESSION_STATUS= 'Missed' AND CE.START_DT<SYSDATE AND ACNT.EMAIL not like '%api%' ORDER BY CE. START_DT DESC");
		System.out.println("query executed");
		if(rs.next())
		{	
			memberforgrpsessionmissednooverlap = rs.getString("member");
			creationdategroupsessionmissednooverlap= rs.getString("created_date");
		}
		return memberforgrpsessionmissednooverlap;
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   24/AUG/16
	 * Modified Date:   
	 * Description : Create a common method to create property file to store member id,summary created date,Program interval number and reduce weight for claims (No overlap between member and coach)
	 * Ticket ID :     
	 * Required Inputs :
	 */
	public static void createPropertyNoOverlap()
	{
		try 
		{
			Properties properties = new Properties();
			properties.setProperty("MEMBER_ID",memberforgrpsessionmissednooverlap);
			properties.setProperty("SMRY.CREATED_DATE", creationdategroupsessionmissednooverlap);
			properties.setProperty("Program_Interval_Number",GroupSession1Programinterval);
			properties.setProperty("Reduced_weight", GroupSession1Reduceweight);
			File file = new File(Directory.uploadFilePath+"//MissedGroupAnd1on1SessionNoOverlap.properties");
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
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   25/AUG/16
	 * Modified Date:   
	 * Description : Create a common method to get RA BeFit member  to attend group session and validate the claims
	 * Ticket ID :     
	 * Required Inputs :No inputs
	 * @throws SQLException
	 *@throws ClassNotFoundException
	 */
	public static String memberforgrpsessionmissedbefit="";
	public static String creationdategroupsessionmissedbefit="";
	public static String getMemberForGrpSessionBeFit(WebDriver driver) throws ClassNotFoundException, SQLException 
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
		ResultSet rs = stat.executeQuery("SELECT ACNT.EMAIL Member_Email, PROV.EMAIL Coach_Email,SMRY.created_dt creatd_dt FROM SUMMARY_ACCOUNT_TODATE SMRY, PROVIDER PROV, ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID AND SMRY.ACCOUNT_ID=ACNT.ID AND SMRY.ONBOARDING_STATUS='CLASSROOM NOT STARTED' AND ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ACCOUNT_PROGRAM.START_DT_TIME>SYSDATE + INTERVAL '-52' DAY AND ID IN (SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_TYPE_ID='05'))ORDER BY SMRY.SESSION_DATE_UTC ASC");
		System.out.println("query executed");
		if(rs.next())
		{	

			memberforgrpsessionmissedbefit = rs.getString("MEMBER_EMAIL");
			creationdategroupsessionmissedbefit= rs.getString("CREATD_DT");		
		}

		return memberforgrpsessionmissedbefit;
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   25/AUG/16
	 * Modified Date:   
	 * Description : Create a common method to create property file to store member id,summary created date,Program interval number and reduce weight for claims (Active by both Attendance and Weight loss)
	 * Ticket ID :     
	 * Required Inputs :
	 */
	public static void createPropertyForBefitByBoth()
	{
		try 
		{
			Properties properties = new Properties();
			properties.setProperty("MEMBER_ID",memberforgrpsessionmissedbefit);
			properties.setProperty("SMRY.CREATED_DATE",creationdategroupsessionmissedbefit );
			properties.setProperty("Program_Interval_Number",GroupSession1Programinterval);
			properties.setProperty("Reduced_weight", GroupSession1Reduceweight);
			File file = new File(Directory.uploadFilePath+"//AttendedGroupByBothActive.properties");
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
	}
	/**
	 * Name :    Abinaya.P
	 * Created Date:   25/AUG/16
	 * Modified Date:   
	 * Description : Create a common method to get RA BeFit member to validate roll up and session status (Active by both Attendance and Weight loss)
	 * @throws SQLException
	 *@throws ClassNotFoundException
	 * @throws IOException 
	 */
	public static String checkRollupRABefitByBothActive(WebDriver driver) throws ClassNotFoundException, SQLException, IOException
	{
		String mailid=null;
		String date=null;
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.uploadFilePath+"//AttendedGroupByBothActive.properties");
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
		ResultSet rs = stat.executeQuery("SELECT EVENT_START_DT, ATTENDANCE_PRTCPNT_ARRIVAL, ATTENDANCE_PRTCPNT_DEPARTURE, ATTENDANCE_PRTCPNT_MINUTES, ATTENDANCE_STATUS, SESSION_ACTUAL_MINUTES, ROLLUP_STATUS FROM CALENDAR_EVENT_ATTENDEE WHERE ACCOUNT_ID IN (SELECT ID FROM ACCOUNT WHERE EMAIL LIKE '"+mailid+"') and EVENT_START_DT like '"+date1[0]+"%'  order by EVENT_START_DT");
		System.out.println("query executed");
		String output=null;
		if(rs.next())
		{
			output="Row is retrieved for the negative scenario";	
		}
		else
		{
			output="No rows for the negative scenario";	
		}
		return output;
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   25/AUG/16
	 * Modified Date:   
	 * Description : Create a common method to get RA BeFit member to validate claims (Active by both Attendance and Weight loss)
	 * @throws SQLException
	 *@throws ClassNotFoundException
	 * @throws IOException 
	 */
	public static String rollup_prcs_dt=null;
	public static String checkClaimsRABefitByBothActive(WebDriver driver) throws ClassNotFoundException, SQLException, IOException
	{
		String mailid=null;
		String date=null;
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.uploadFilePath+"//AttendedGroupByBothActive.properties");
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
		String output=null;
		if(rs.next())
		{
			output="Claims is achieved for the negative scenario";	
		}
		else
		{
			output="No claims for the negative scenario";	
		}
		return output;
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   25/AUG/16
	 * Modified Date:   
	 * Description : Create a common method to get RA BeFit member to validate claims (No overlap between member and coach)
	 * @throws SQLException
	 *@throws ClassNotFoundException
	 * @throws IOException 
	 */
	public static String rollup_prcs_dt_no_overlap=null;
	public static String checkClaimsNoOverlap(WebDriver driver) throws ClassNotFoundException, SQLException, IOException
	{
		String mailid=null;
		String date=null;
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.uploadFilePath+"//MissedGroupAnd1on1SessionNoOverlap.properties");
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
		String output=null;
		if(rs.next())
		{
			output="Claims is achieved";
		}
		else
		{
			output="No Claims is achieved";
		}
		return	output;  
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   26/AUG/16
	 * Modified Date:   
	 * Description : Create a common method to send a mail for the group session claims(No overlap between member and coach)
	 * Ticket ID :     
	 * Required Inputs :
	 */
	public static void groupSendEmailNoOverlap(final String username,final String password,String from,String to,String cc,String subject,String body,String thanks) throws Exception 
	{  	 		   
		//Get the session object 
		Properties props = new Properties();  
		props.put( "mail.smtp.starttls.enable", true );
		props.put( "mail.smtp.auth", true );
		props.put( "mail.smtp.host", "smtp.gmail.com" );
		props.put( "mail.smtp.port", "587" );
		Session session = Session.getDefaultInstance(props,  
				new javax.mail.Authenticator() {  
			protected PasswordAuthentication getPasswordAuthentication()
			{  
				return new PasswordAuthentication(username,password);  
			}  
		});  
		//Compose the message  
		try {  
			MimeMessage message = new MimeMessage(session);  
			message.setFrom(new InternetAddress(from));  
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to)); 
			message.setRecipients( Message.RecipientType.CC,
					InternetAddress.parse(cc) );
			message.setSubject(subject);  
			//send the message 
			Properties urlsProperties = new Properties();
			InputStream input = new FileInputStream(Directory.uploadFilePath+"//MissedGroupAnd1on1SessionNoOverlap.properties");
			urlsProperties.load(input);
			String member_id=urlsProperties.getProperty("MEMBER_ID").trim();
			String created_date=urlsProperties.getProperty("SMRY.CREATED_DATE").trim();				
			String messagebody = member_id+" and the session creation date is "+created_date;
			System.out.println("Message body : "+messagebody);
			message.setText(body+" "+messagebody+" "+thanks);		 			 
			Transport.send(message); 			     
			System.out.println("message sent successfully...");  
		} catch (MessagingException e) {e.printStackTrace();}  
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   25/AUG/16
	 * Modified Date:   
	 * Description : Create a common method to get RA BeFit member to validate roll up and session status (Claims-Member is in first interval)
	 * @throws SQLException
	 *@throws ClassNotFoundException
	 * @throws IOException 
	 */
	public static void checkRollupMemberIsInFirstInt(WebDriver driver, String parti_min,String attend_status1,String actual_min1,String rollup_status1) throws ClassNotFoundException, SQLException, IOException
	{
		String mailid=null;
		String date=null;
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.uploadFilePath+"//GroupMemberIsInFirstIntervals.properties");
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
		ResultSet rs = stat.executeQuery("SELECT EVENT_START_DT, ATTENDANCE_PRTCPNT_ARRIVAL, ATTENDANCE_PRTCPNT_DEPARTURE, ATTENDANCE_PRTCPNT_MINUTES, ATTENDANCE_STATUS, SESSION_ACTUAL_MINUTES, ROLLUP_STATUS FROM CALENDAR_EVENT_ATTENDEE WHERE ACCOUNT_ID IN (SELECT ID FROM ACCOUNT WHERE EMAIL LIKE '"+mailid+"') and EVENT_START_DT like '"+date1[0]+"%'  order by EVENT_START_DT");
		System.out.println("query executed");
		String start_dt=null;
		String arrival_time=null;
		String departure_time=null;
		String participant_min=null;
		String attendance_status=null;
		String session_actual_min=null;
		String rollup_status=null;
		while(rs.next())
		{
			start_dt= rs.getString("EVENT_START_DT");	
			arrival_time= rs.getString("ATTENDANCE_PRTCPNT_ARRIVAL");	
			departure_time= rs.getString("ATTENDANCE_PRTCPNT_DEPARTURE");	
			participant_min= rs.getString("ATTENDANCE_PRTCPNT_MINUTES");	
			attendance_status= rs.getString("ATTENDANCE_STATUS");	
			session_actual_min= rs.getString("SESSION_ACTUAL_MINUTES");	
			rollup_status= rs.getString("ROLLUP_STATUS");	
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
		if(arrival_time!=null) 
		{
			System.out.println("Arrival time is not null");
		}
		else
		{
			System.out.println("Arrival time is null");  
			Assert.fail();
		}
		if(departure_time!=null) 
		{
			System.out.println("Departure time is not null");
		}
		else
		{
			System.out.println("Departure time is null");  
			Assert.fail();
		}
		if(participant_min.equals(parti_min)) 
		{
			System.out.println("Participant time equals database");
		}
		else
		{
			System.out.println("Participant time not equals database");  
			Assert.fail();
		}
		if(attendance_status.equals(attend_status1)) 
		{
			System.out.println("Attendance status equals database");
		}
		else
		{
			System.out.println("Attendance status not equals database");  
			Assert.fail();
		}
		if(session_actual_min.equals(actual_min1)) 
		{
			System.out.println("Actual minutes equals database");
		}
		else
		{
			System.out.println("Actual minutes not equals database");  
			Assert.fail();
		}
		if(rollup_status.equals(rollup_status1)) 
		{
			System.out.println("Rollup status equals database");
		}
		else
		{
			System.out.println("Rollup status not equals database");  
			Assert.fail();
		}
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   12/SEP/16
	 * Modified Date:   
	 * Description : Create a common method to get RA BeFit member to validate roll up and session status (No Weight loss)
	 * @throws SQLException
	 *@throws ClassNotFoundException
	 * @throws IOException 
	 */
	public static String checkRollupNoWeightLoss(WebDriver driver) throws ClassNotFoundException, SQLException, IOException
	{
		String mailid=null;
		String date=null;
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.uploadFilePath+"//GroupWithoutWeightLoss.properties");
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
		ResultSet rs = stat.executeQuery("SELECT EVENT_START_DT, ATTENDANCE_PRTCPNT_ARRIVAL, ATTENDANCE_PRTCPNT_DEPARTURE, ATTENDANCE_PRTCPNT_MINUTES, ATTENDANCE_STATUS, SESSION_ACTUAL_MINUTES, ROLLUP_STATUS FROM CALENDAR_EVENT_ATTENDEE WHERE ACCOUNT_ID IN (SELECT ID FROM ACCOUNT WHERE EMAIL LIKE '"+mailid+"') and EVENT_START_DT like '"+date1[0]+"%'  order by EVENT_START_DT");
		System.out.println("query executed");
		String output=null;
		if(rs.next())
		{
			output="Row is retrieved for the negative scenario";	
		}
		else
		{
			output="No rows for the negative scenario";	
		}
		return output;
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   12/SEP/16
	 * Modified Date:   
	 * Description : Create a common method to get RA BeFit member to validate claims (No Weight loss)
	 * @throws SQLException
	 *@throws ClassNotFoundException
	 * @throws IOException 
	 */
	public static String checkClaimsNoWeightLoss(WebDriver driver) throws ClassNotFoundException, SQLException, IOException
	{
		String mailid=null;
		String date=null;
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.uploadFilePath+"//GroupWithoutWeightLoss.properties");
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
		String output=null;
		if(rs.next())
		{
			output="Claims is achieved for the negative scenario";	
		}
		else
		{
			output="No claims for the negative scenario";	
		}
		return output;
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   25/AUG/16
	 * Modified Date:   
	 * Description : Create a common method to validate roll up and session status (Claims-Missed group and Attended 1on1 Session)
	 * @throws SQLException
	 *@throws ClassNotFoundException
	 * @throws IOException 
	 */
	public static void checkRollupMissedGrpAttended1on1(WebDriver driver, String parti_min,String attend_status1,String actual_min1,String rollup_status1) throws ClassNotFoundException, SQLException, IOException
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
		ResultSet rs = stat.executeQuery("SELECT START_DT_TIME, ATTENDANCE_PRTCPNT_ARRIVAL, ATTENDANCE_PRTCPNT_DEPARTURE, ATTENDANCE_PRTCPNT_MINUTES, ATTENDANCE_STATUS, SESSION_ACTUAL_MINUTES, ROLLUP_STATUS FROM CALENDAR_EVENT WHERE ACCOUNT_ID IN (SELECT ID FROM ACCOUNT WHERE EMAIL LIKE '"+mailid+"') and START_DT_TIME like '"+date1[0]+"%'  order by START_DT_TIME");
		System.out.println("query executed");
		String start_dt=null;
		String arrival_time=null;
		String departure_time=null;
		String participant_min=null;
		String attendance_status=null;
		String session_actual_min=null;
		String rollup_status=null;
		while(rs.next())
		{
			start_dt= rs.getString("START_DT_TIME");	
			arrival_time= rs.getString("ATTENDANCE_PRTCPNT_ARRIVAL");	
			departure_time= rs.getString("ATTENDANCE_PRTCPNT_DEPARTURE");	
			participant_min= rs.getString("ATTENDANCE_PRTCPNT_MINUTES");	
			attendance_status= rs.getString("ATTENDANCE_STATUS");	
			session_actual_min= rs.getString("SESSION_ACTUAL_MINUTES");	
			rollup_status= rs.getString("ROLLUP_STATUS");	
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
		if(arrival_time!=null) 
		{
			System.out.println("Arrival time is not null");
		}
		else
		{
			System.out.println("Arrival time is null");  
			Assert.fail();
		}
		if(departure_time!=null) 
		{
			System.out.println("Departure time is not null");
		}
		else
		{
			System.out.println("Departure time is null");  
			Assert.fail();
		}
		if(participant_min.equals(parti_min)) 
		{
			System.out.println("Participant time equals database");
		}
		else
		{
			System.out.println("Participant time not equals database");  
			Assert.fail();
		}
		if(attendance_status.equals(attend_status1)) 
		{
			System.out.println("Attendance status equals database");
		}
		else
		{
			System.out.println("Attendance status not equals database");  
			Assert.fail();
		}
		if(session_actual_min.equals(actual_min1)) 
		{
			System.out.println("Actual minutes equals database");
		}
		else
		{
			System.out.println("Actual minutes not equals database");  
			Assert.fail();
		}
		if(rollup_status.equals(rollup_status1)) 
		{
			System.out.println("Rollup status equals database");
		}
		else
		{
			System.out.println("Rollup status not equals database");  
			Assert.fail();
		}
	}

	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   24/MAY/16
	 * Modified Date:   
	 * Description : RA- Group ZLive session for different browser
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */

	public static void claimsZLiveSessionForFirstInterval(WebDriver driver,String testData, String coachEmail1, String memberEmail1) throws Exception
	{
		String[] testData1 = testData.split(",");
		String coachBrowser=testData1[0];
		String memberBrowser=testData1[1];


		// Coach Login in Browser Launch 
		WebDriver driver1 = null;

		if ("firefox".equalsIgnoreCase(coachBrowser) ) 
		{
			driver =  new FirefoxBrowser().getDriver();
			driver.manage().window().maximize();
		}
		else if ( "ie".equalsIgnoreCase(coachBrowser) )
		{
			driver = ( WebDriver ) new IEBrowser().getDriver();
			driver.manage().window().maximize();
		}
		else if ( "safari".equalsIgnoreCase(coachBrowser)) 
		{
			driver = new SafariBrowser().getDriver();
			driver.manage().window().maximize();
		}
		wait( driver, "4" );

		// Coach Login
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.coachLoginWithReferenceStep(driver, coachEmail1);
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver);

		// Browser launch for Member side
		// Test data seperation from spread sheet
		if ("firefox".equalsIgnoreCase(memberBrowser) ) 
		{
			driver1 =  new FirefoxBrowser().getDriver();
			driver1.manage().window().maximize();
		}
		else if ( "ie".equalsIgnoreCase(memberBrowser) )
		{
			driver1 = ( WebDriver ) new IEBrowser().getDriver();
			driver1.manage().window().maximize();
		}
		else if ( "chrome".equalsIgnoreCase(memberBrowser)) 
		{
			driver1 = new ChromeBrowser().getDriver();
			driver1.manage().window().maximize();
		}
		else if ( "safari".equalsIgnoreCase(memberBrowser)) 
		{
			driver1 = new SafariBrowser().getDriver();
			driver1.manage().window().maximize();
		}

		// Member Login with different browser
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raGroupZLiveSessionMemberLoginDifferentBrowser(driver1, memberEmail1);
		com.zillion.qa.realappealmember.member.termsAndConditionPopupHandle( driver1 );

		Robot rb = new Robot();
		wait( driver, "4" );
		rb.keyPress(KeyEvent.VK_A);
		rb.keyRelease(KeyEvent.VK_A);

		com.zillion.qa.realappealmember.member.browserSupportMatrixRA( driver1 );

		// Append Member URl 
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raMemberGroupSessionAppendURL(driver1);

		waitForElement(driver1, RA_MEMBER_GROUP_SESSION_JOIN_BUTTON);
		WebElement joinButton = driver1.findElement(By.xpath(OR.RA_MEMBER_GROUP_SESSION_JOIN_BUTTON));
		click(joinButton);
		wait(driver1, "3");
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raEnterWeightDuringLiveSession( driver1 );
		wait(driver1, "7");

		// Allow plugin for ZLiveSession
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.allowGroupZLivePlugin(driver1);

		// Retrieve the group session attended time
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raMemberSessionTime(driver1);

		waitForElement(driver1, RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CONNECTED_TEXT);
		WebElement closeButton = driver1.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CLOSE_BUTTON));
		WebElement memberChatTextbox = driver1.findElement(By.xpath(OR.RA_MEMBER_LIVESESSION_CHAT_TEXTBOX));
		WebElement memberChatSendButton = driver1.findElement(By.xpath(OR.RA_MEMBER_LIVESESSION_SEND_BUTTON));
		verifyElementIsPresent(driver1, closeButton);
		verifyElementIsPresent(driver1, memberChatTextbox);
		verifyElementIsPresent(driver1, memberChatSendButton);
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver1);


		// Switch Member to coach
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver1, "4");
		driver.manage().window().maximize();
		wait(driver, "4");

		// Append Coach URL
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raMemberGroupSessionAppendURL(driver);
		wait(driver, "5");
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raCoachUpcomingSessionTimeDifferentBrowser(driver);
		Screen Allow1=new Screen(); 
		wait( driver, "8" );
		Pattern image1 = new Pattern(Directory.uploadFilePath+"BlankArea.PNG");
		Allow1.wait(image1, 15);
		Allow1.click(image1);
		wait( driver, "3" );
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.shareSelectedDevice(driver);

		wait(driver, "7");
		WebElement shareScreenButton = driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_SHARE_SCREEN_BUTTON));
		WebElement endSessionButton = driver.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_BUTTON));
		WebElement coachMicOn = driver.findElement(By.xpath(OR.COACH_SESSION_MIC_ON));
		WebElement coachVideoOn = driver.findElement(By.xpath(OR.COACH_SESSION_VIDEO_ON));
		WebElement coachChatTextbox = driver.findElement(By.xpath(OR.COACH_SESSION_CHAT_TEXTBOX));
		WebElement coachSendButton = driver.findElement(By.xpath(OR.COACH_SESSION_CHAT_SEND_BUTTON));
		WebElement coachTileMemberMicDisabled = driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_COACH_TILE_MEMBER_MIC_DISABLED));
		verifyElementIsPresent(driver, coachChatTextbox);
		verifyElementIsPresent(driver, coachSendButton);
		verifyElementIsPresent(driver, shareScreenButton);
		verifyElementIsPresent(driver, endSessionButton);
		verifyElementIsPresent(driver, coachMicOn);
		verifyElementIsPresent(driver, coachVideoOn);
		verifyElementIsPresent(driver, coachTileMemberMicDisabled);

		jsClickByXPath(driver, COACH_SESSION_END_SESSION_BUTTON);
		wait(driver, "2" );
		WebElement endSessionText= driver.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_TEXT));
		verifyElementIsPresent(driver, endSessionText);
		WebElement endSessionYesButton= driver.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_YES_BUTTON));
		WebElement endSessionNoButton= driver.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_NO_BUTTON));
		verifyElementIsPresent(driver, endSessionYesButton);
		verifyElementIsPresent(driver, endSessionNoButton);
		jsClickByXPath(driver, COACH_SESSION_END_SESSION_BUTTON);
		wait(driver, "3" );
		jsClickByXPath(driver, COACH_SESSION_END_SESSION_YES_BUTTON);
		wait(driver, "3" );
		WebElement coachCommentsTextbox= driver.findElement(By.xpath(OR.COACH_COMMENTS_TEXTBOX));
		sendKeys(coachCommentsTextbox, "Session completed with the member");
		jsClickByXPath(driver, COACH_END_SESSION_AND_MARKED_AS_COMPLETED);
		wait(driver, "2" );
		jsClickByXPath(driver, COACH_SESSION_MEMBER_DONE_BUTTON);
		wait(driver, "4" );
		com.zillion.qa.realappealcoach.coach.realAppealCoachLogout(driver);

		// Switch Coach to Member
		// Take screen shot for coach video
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver);
		driver.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "4" );
		driver1.manage().window().maximize();
		wait(driver1, "4");

		WebElement coachRatingHeader= driver1.findElement(By.xpath(OR.MEMBER_COACH_RATING_HEADER_TEXT));
		WebElement coachRatingTextbox= driver1.findElement(By.xpath(OR.MEMBER_COACH_RATING_TEXTBOX));
		WebElement coachRatingSubmitButton= driver1.findElement(By.xpath(OR.MEMBER_COACH_RATING_SUBMIT_BUTTON));
		WebElement coachRatingCloseButton= driver1.findElement(By.xpath(OR.MEMBER_COACH_RATING_CLOSE_BUTTON));
		WebElement ratingStar1= driver1.findElement(By.xpath(OR.GROUP_SESSION_COACH_RATING_STAR1));
		WebElement ratingStar2= driver1.findElement(By.xpath(OR.GROUP_SESSION_COACH_RATING_STAR2));
		WebElement ratingStar3= driver1.findElement(By.xpath(OR.GROUP_SESSION_COACH_RATING_STAR3));
		WebElement ratingStar4= driver1.findElement(By.xpath(OR.GROUP_SESSION_COACH_RATING_STAR4));
		WebElement ratingStar5= driver1.findElement(By.xpath(OR.GROUP_SESSION_COACH_RATING_STAR5));
		verifyElementIsPresent(driver1, coachRatingHeader);
		verifyElementIsPresent(driver1, coachRatingTextbox);
		verifyElementIsPresent(driver1, coachRatingSubmitButton);
		verifyElementIsPresent(driver1, coachRatingCloseButton);
		verifyElementIsPresent(driver1, ratingStar1);
		verifyElementIsPresent(driver1, ratingStar2);
		verifyElementIsPresent(driver1, ratingStar3);
		verifyElementIsPresent(driver1, ratingStar4);
		verifyElementIsPresent(driver1, ratingStar5);
		click(ratingStar1);
		click(ratingStar2);
		click(ratingStar3);
		click(ratingStar4);
		click(ratingStar5);
		sendKeys(coachRatingTextbox, "Session completed with the coach");
		wait(driver1, "2");
		click(coachRatingSubmitButton);
		wait(driver1, "3");
		com.zillion.qa.realappealmember.member.realAppealMemberlogout(driver1);
		com.zillion.qa.realappealmember.Claims.createPropertyforMemberHasNIs1();
		driver.close();
		driver1.close();

	}

	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   24/MAY/16
	 * Modified Date:   
	 * Description : RA- Group ZLive session for different browser
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */

	public static void claimsZLiveSessionForMemberNotWeightLoss(WebDriver driver,String testData, String coachEmail1, String memberEmail1) throws Exception
	{
		String[] testData1 = testData.split(",");
		String coachBrowser=testData1[0];
		String memberBrowser=testData1[1];


		// Coach Login in Browser Launch 
		WebDriver driver1 = null;

		if ("firefox".equalsIgnoreCase(coachBrowser) ) 
		{
			driver =  new FirefoxBrowser().getDriver();
			driver.manage().window().maximize();
		}
		else if ( "ie".equalsIgnoreCase(coachBrowser) )
		{
			driver = ( WebDriver ) new IEBrowser().getDriver();
			driver.manage().window().maximize();
		}
		else if ( "safari".equalsIgnoreCase(coachBrowser)) 
		{
			driver = new SafariBrowser().getDriver();
			driver.manage().window().maximize();
		}
		wait( driver, "4" );

		// Coach Login
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.coachLoginWithReferenceStep(driver, coachEmail1);
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver);

		// Browser launch for Member side
		// Test data seperation from spread sheet
		if ("firefox".equalsIgnoreCase(memberBrowser) ) 
		{
			driver1 =  new FirefoxBrowser().getDriver();
			driver1.manage().window().maximize();
		}
		else if ( "ie".equalsIgnoreCase(memberBrowser) )
		{
			driver1 = ( WebDriver ) new IEBrowser().getDriver();
			driver1.manage().window().maximize();
		}
		else if ( "chrome".equalsIgnoreCase(memberBrowser)) 
		{
			driver1 = new ChromeBrowser().getDriver();
			driver1.manage().window().maximize();
		}
		else if ( "safari".equalsIgnoreCase(memberBrowser)) 
		{
			driver1 = new SafariBrowser().getDriver();
			driver1.manage().window().maximize();
		}

		// Member Login with different browser
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raGroupZLiveSessionMemberLoginDifferentBrowser(driver1, memberEmail1);
		com.zillion.qa.realappealmember.member.termsAndConditionPopupHandle( driver1 );

		Robot rb = new Robot();
		wait( driver, "4" );
		rb.keyPress(KeyEvent.VK_A);
		rb.keyRelease(KeyEvent.VK_A);

		com.zillion.qa.realappealmember.member.browserSupportMatrixRA( driver1 );

		// Append Member URl 
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raMemberGroupSessionAppendURL(driver1);

		waitForElement(driver1, RA_MEMBER_GROUP_SESSION_JOIN_BUTTON);
		WebElement joinButton = driver1.findElement(By.xpath(OR.RA_MEMBER_GROUP_SESSION_JOIN_BUTTON));
		click(joinButton);
		wait(driver1, "3");
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raEnterWeightDuringLiveSession( driver1 );
		wait(driver1, "7");

		// Allow plugin for ZLiveSession
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.allowGroupZLivePlugin(driver1);

		WebElement memberSessionTime = driver1.findElement(By.xpath(OR.RA_MEMBER_SESSION_TIME));
		String sessionsTime=memberSessionTime.getText();
		SimpleDateFormat ft = new SimpleDateFormat("EEE hhh:mm a");
		java.util.Date t=ft.parse(sessionsTime);
		ft.applyPattern("hh:mma");
		sessiontime1=ft.format(t).toLowerCase().trim();
		System.out.print(ft.format(t));
		System.out.print("Time"+sessiontime1);
		// Retrieve the group session attended time
		//com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raMemberSessionTime(driver1);

		waitForElement(driver1, RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CONNECTED_TEXT);
		WebElement closeButton = driver1.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CLOSE_BUTTON));
		WebElement memberChatTextbox = driver1.findElement(By.xpath(OR.RA_MEMBER_LIVESESSION_CHAT_TEXTBOX));
		WebElement memberChatSendButton = driver1.findElement(By.xpath(OR.RA_MEMBER_LIVESESSION_SEND_BUTTON));
		verifyElementIsPresent(driver1, closeButton);
		verifyElementIsPresent(driver1, memberChatTextbox);
		verifyElementIsPresent(driver1, memberChatSendButton);
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver1);


		// Switch Member to coach
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver1, "4");
		driver.manage().window().maximize();
		wait(driver, "4");

		String coachURL= driver.getCurrentUrl();
		String coachAppendURL= coachURL+"?attendnow/&Sessioncount=20";
		Navigate.get(driver, coachAppendURL);
		// Append Coach URL
		wait(driver, "5");
		wait( driver, "20" );
		String attendnoeor ="//tr//td[contains(text(),'"+GroupAttendNow+"')]/following::td[contains(text(),'"+sessiontime1+"')]/following-sibling::td[7]/div/button";
		System.out.println("GroupAttendNow"+GroupAttendNow);
		System.out.println("SessionTime"+sessiontime1);
		waitForElement(driver, attendnoeor);
		jsClickByXPath(driver, attendnoeor);
		wait( driver, "20" );
		//		com.zillion.qa.realappealmember.Claims.attendnow(driver);
		//com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raCoachUpcomingSessionTimeDifferentBrowser(driver);
		Screen Allow1=new Screen(); 
		wait( driver, "8" );
		Pattern image1 = new Pattern(Directory.uploadFilePath+"BlankArea.PNG");
		Allow1.wait(image1, 15);
		Allow1.click(image1);
		wait( driver, "3" );
		wait(driver, "6");

		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.shareSelectedDevice(driver);

		wait(driver, "10");
		WebElement shareScreenButton = driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_SHARE_SCREEN_BUTTON));
		WebElement endSessionButton = driver.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_BUTTON));
		WebElement coachMicOn = driver.findElement(By.xpath(OR.COACH_SESSION_MIC_ON));
		WebElement coachVideoOn = driver.findElement(By.xpath(OR.COACH_SESSION_VIDEO_ON));
		WebElement coachChatTextbox = driver.findElement(By.xpath(OR.COACH_SESSION_CHAT_TEXTBOX));
		WebElement coachSendButton = driver.findElement(By.xpath(OR.COACH_SESSION_CHAT_SEND_BUTTON));
		WebElement coachTileMemberMicDisabled = driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_COACH_TILE_MEMBER_MIC_DISABLED));
		verifyElementIsPresent(driver, coachChatTextbox);
		verifyElementIsPresent(driver, coachSendButton);
		verifyElementIsPresent(driver, shareScreenButton);
		verifyElementIsPresent(driver, endSessionButton);
		verifyElementIsPresent(driver, coachMicOn);
		verifyElementIsPresent(driver, coachVideoOn);
		verifyElementIsPresent(driver, coachTileMemberMicDisabled);

		jsClickByXPath(driver, COACH_SESSION_END_SESSION_BUTTON);
		wait(driver, "2" );
		WebElement endSessionText= driver.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_TEXT));
		verifyElementIsPresent(driver, endSessionText);
		WebElement endSessionYesButton= driver.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_YES_BUTTON));
		WebElement endSessionNoButton= driver.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_NO_BUTTON));
		verifyElementIsPresent(driver, endSessionYesButton);
		verifyElementIsPresent(driver, endSessionNoButton);
		jsClickByXPath(driver, COACH_SESSION_END_SESSION_BUTTON);
		wait(driver, "3" );
		jsClickByXPath(driver, COACH_SESSION_END_SESSION_YES_BUTTON);
		wait(driver, "3" );
		WebElement coachCommentsTextbox= driver.findElement(By.xpath(OR.COACH_COMMENTS_TEXTBOX));
		sendKeys(coachCommentsTextbox, "Session completed with the member");
		jsClickByXPath(driver, COACH_END_SESSION_AND_MARKED_AS_COMPLETED);
		wait(driver, "2" );
		jsClickByXPath(driver, COACH_SESSION_MEMBER_DONE_BUTTON);
		wait(driver, "4" );
		com.zillion.qa.realappealcoach.coach.realAppealCoachLogout(driver);

		// Switch Coach to Member
		// Take screen shot for coach video
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver);
		driver.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "4" );
		driver1.manage().window().maximize();
		wait(driver1, "4");

		WebElement coachRatingHeader= driver1.findElement(By.xpath(OR.MEMBER_COACH_RATING_HEADER_TEXT));
		WebElement coachRatingTextbox= driver1.findElement(By.xpath(OR.MEMBER_COACH_RATING_TEXTBOX));
		WebElement coachRatingSubmitButton= driver1.findElement(By.xpath(OR.MEMBER_COACH_RATING_SUBMIT_BUTTON));
		WebElement coachRatingCloseButton= driver1.findElement(By.xpath(OR.MEMBER_COACH_RATING_CLOSE_BUTTON));
		WebElement ratingStar1= driver1.findElement(By.xpath(OR.GROUP_SESSION_COACH_RATING_STAR1));
		WebElement ratingStar2= driver1.findElement(By.xpath(OR.GROUP_SESSION_COACH_RATING_STAR2));
		WebElement ratingStar3= driver1.findElement(By.xpath(OR.GROUP_SESSION_COACH_RATING_STAR3));
		WebElement ratingStar4= driver1.findElement(By.xpath(OR.GROUP_SESSION_COACH_RATING_STAR4));
		WebElement ratingStar5= driver1.findElement(By.xpath(OR.GROUP_SESSION_COACH_RATING_STAR5));
		verifyElementIsPresent(driver1, coachRatingHeader);
		verifyElementIsPresent(driver1, coachRatingTextbox);
		verifyElementIsPresent(driver1, coachRatingSubmitButton);
		verifyElementIsPresent(driver1, coachRatingCloseButton);
		verifyElementIsPresent(driver1, ratingStar1);
		verifyElementIsPresent(driver1, ratingStar2);
		verifyElementIsPresent(driver1, ratingStar3);
		verifyElementIsPresent(driver1, ratingStar4);
		verifyElementIsPresent(driver1, ratingStar5);
		click(ratingStar1);
		click(ratingStar2);
		click(ratingStar3);
		click(ratingStar4);
		click(ratingStar5);
		sendKeys(coachRatingTextbox, "Session completed with the coach");
		wait(driver1, "2");
		click(coachRatingSubmitButton);
		wait(driver1, "3");
		com.zillion.qa.realappealmember.member.realAppealMemberlogout(driver1);
		com.zillion.qa.realappealmember.Claims.createPropertyforMemberHasNIs1();
		driver.close();
		driver1.close();

	}

	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:  04/APR/17
	 * Modified Date:
	 * Description : Create a common method to retrieve Member Account Program id who has missed group session and scheduled 1on1 session
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	static String accountProgramID ="";
	static String scheduleddatemakeupsessionmissednooverlap ="";
	public  static String retrieveMemberAccountPgmIDWhoseHasMissedGroupSessionAndScheduled1ON1Session(WebDriver driver) throws ParseException, ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("SELECT ID, ACCOUNT_PROGRAM_ID, SCHEDULED_DT, SCHEDULED_END_DT,PSD.MP_INTERVAL_NUMBER FROM ACCOUNT_PROGRAM_SESSION_DETAIL PSD WHERE SESSION_TYPE_ID='01' AND SESSION_STATUS='Missed' AND PROGRAM_INTERVAL_START_DT<SYSDATE AND PROGRAM_INTERVAL_END_DT>SYSDATE AND IS_MAKEUP_SESSION=0 AND ACCOUNT_PROGRAM_ID NOT IN (SELECT ACCOUNT_PROGRAM_ID FROM PROGRAM_SESSION_DETAIL_HISTORY HSRY WHERE IS_MAKEUP_SESSION=1 AND PROGRAM_INTERVAL_START_DT<SYSDATE + INTERVAL '-52' DAY AND PROGRAM_INTERVAL_END_DT>SYSDATE)AND ACCOUNT_PROGRAM_ID IN (SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_TYPE_ID='02' AND SESSION_STATUS='Unscheduled' AND PROGRAM_INTERVAL_START_DT<SYSDATE AND PROGRAM_INTERVAL_END_DT>SYSDATE) ORDER BY PROGRAM_INTERVAL_START_DT DESC");
		System.out.println("query executed");

		if(rs.next())
		{
			accountProgramID = rs.getString("ACCOUNT_PROGRAM_ID");
			scheduleddatemakeupsessionmissednooverlap = rs.getString("SCHEDULED_DT");
			System.out.println("Member Account Program id  "+accountProgramID);
		}
		return accountProgramID;
	}

	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:  04/APR/17
	 * Modified Date:
	 * Description : Create a common method to retrieve Member Account ID
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static String retrieveMemberAccountIDWhoseHasMissedGroupSessionAndScheduled1ON1Session(WebDriver driver,String accountId) throws ParseException, ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ID='"+accountId+"'");
		System.out.println("query executed");
		String accountID="";
		if(rs.next())
		{
			accountID = rs.getString("ACCOUNT_ID");
			System.out.println("Member Account id "+accountID);
		}
		return accountID;
	}

	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:  04/APR/17
	 * Modified Date:
	 * Description : Create a common method to retrieve Member Email who has missed group session and scheduled 1on1 session
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	static String memberforMakeupsessionmissednooverlap ="";
	public  static String retrieveMemberEmailWhoseHasMissedGroupSessionAndScheduled1ON1Session(WebDriver driver,String MemberEmail) throws ParseException, ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("SELECT EMAIL FROM ACCOUNT WHERE ID= '"+MemberEmail+"'");
		System.out.println("query executed");

		if(rs.next())
		{
			memberforMakeupsessionmissednooverlap = rs.getString("Email");
			System.out.println("Available Member Email "+memberforMakeupsessionmissednooverlap);
		}
		return memberforMakeupsessionmissednooverlap;
	}
	/**
	 * Name : Vinothkumar.M
	 * Created Date:   25/APR/17
	 * Modified Date:   
	 * Description : Create a common method to create property file to store member id,summary created date,Program interval number and reduce weight for claims (No overlap between member and coach)
	 * Ticket ID :     
	 * Required Inputs :
	 */
	public static void createPropertyNoOverlapForMakeupSession()
	{
		try 
		{
			Properties properties = new Properties();
			properties.setProperty("MEMBER_ID",memberforMakeupsessionmissednooverlap);
			properties.setProperty("SCHEDULED_DT", scheduleddatemakeupsessionmissednooverlap);
			properties.setProperty("Program_Interval_Number",GroupSession1Programinterval);
			properties.setProperty("Reduced_weight", GroupSession1Reduceweight);
			File file = new File(Directory.uploadFilePath+"//CoachandMemberAttendsMakeupSessionWithNooverlap.properties");
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
	}

	/**
	 * Name :    Vinothkumar.M
	 * Created Date:  26/APR/17
	 * Modified Date:   
	 * Description : Create a common method to get member to validate claims (No claims when coach and member attends makeup with no overlap)
	 * @throws SQLException
	 *@throws ClassNotFoundException
	 * @throws IOException 
	 */

	public static String checkClaimsNoOverlapForMakeupSession(WebDriver driver) throws ClassNotFoundException, SQLException, IOException
	{
		String mailid=null;
		String date=null;
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.uploadFilePath+"//CoachandMemberAttendsMakeupSessionWithNooverlap.properties");
		urlsProperties.load(input);
		mailid=urlsProperties.getProperty("MEMBER_ID").trim();
		date=urlsProperties.getProperty("SCHEDULED_DT").trim();
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
		String output=null;
		if(rs.next())
		{
			output="Claims is achieved";
		}
		else
		{
			output="No Claims is achieved";
		}
		return	output;  
	}

	/** Name : Vinothkumar.M
	 * Created Date:   26/APR/17
	 * Modified Date:   
	 * Description : Create a common method to create property file to store member id,summary created date,Program interval number and reduce weight for claims
	 * Ticket ID :     
	 * Required Inputs :
	 */
	public static void createPropertyForMakeupIsAttendedNCompletedOnlyByCoach()
	{
		try 
		{
			Properties properties = new Properties();
			properties.setProperty("MEMBER_ID",memberforMakeupsessionmissednooverlap);
			properties.setProperty("SCHEDULED_DT", scheduleddatemakeupsessionmissednooverlap);
			properties.setProperty("Program_Interval_Number",GroupSession1Programinterval);
			properties.setProperty("Reduced_weight", GroupSession1Reduceweight);
			File file = new File(Directory.uploadFilePath+"//MakeupIsAttendedNCompletedOnlyByCoach.properties");
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
	}

	/**
	 * Name :    Vinothkumar.M
	 * Created Date:  26/APR/17
	 * Modified Date:   
	 * Description : Create a common method to get member to validate claims (No claims when the makeup is attended and completed only by coach)
	 * @throws SQLException
	 *@throws ClassNotFoundException
	 * @throws IOException 
	 */

	public static String checkClaimsForMakeupIsAttendedNCompletedOnlyByCoach(WebDriver driver) throws ClassNotFoundException, SQLException, IOException
	{
		String mailid=null;
		String date=null;
		Properties urlsProperties = new Properties();
		InputStream input = new FileInputStream(Directory.uploadFilePath+"//MakeupIsAttendedNCompletedOnlyByCoach.properties");
		urlsProperties.load(input);
		mailid=urlsProperties.getProperty("MEMBER_ID").trim();
		date=urlsProperties.getProperty("SCHEDULED_DT").trim();
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
		String output=null;
		if(rs.next())
		{
			output="Claims is achieved";
		}
		else
		{
			output="No Claims is achieved";
		}
		return	output;  
	}

	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:  26/APR/17
	 * Modified Date:
	 * Description : Create a common method to retrieve Member who has missed 10n1 session and can schedule makeup session
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static String retrieveMemberAccountPGMIDWhoseHasMissedOneonOneSessionNScheduleMakeupSession(WebDriver driver) throws ParseException, ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("SELECT ID, ACCOUNT_PROGRAM_ID, SCHEDULED_DT, SCHEDULED_END_DT,PSD.MP_INTERVAL_NUMBER FROM ACCOUNT_PROGRAM_SESSION_DETAIL PSD WHERE SESSION_TYPE_ID='01' AND SESSION_STATUS='Missed' AND PROGRAM_INTERVAL_START_DT<SYSDATE AND PROGRAM_INTERVAL_END_DT>SYSDATE AND IS_MAKEUP_SESSION=0 AND ACCOUNT_PROGRAM_ID NOT IN (SELECT ACCOUNT_PROGRAM_ID FROM PROGRAM_SESSION_DETAIL_HISTORY HSRY WHERE IS_MAKEUP_SESSION=1 AND PROGRAM_INTERVAL_START_DT<SYSDATE + INTERVAL '-52' DAY AND PROGRAM_INTERVAL_END_DT>SYSDATE)AND ACCOUNT_PROGRAM_ID IN (SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_TYPE_ID='02' AND SESSION_STATUS='Missed' AND PROGRAM_INTERVAL_START_DT<SYSDATE AND PROGRAM_INTERVAL_END_DT>SYSDATE) ORDER BY PROGRAM_INTERVAL_START_DT DESC");
		System.out.println("query executed");
		if(rs.next())
		{
			accountProgramID = rs.getString("ACCOUNT_PROGRAM_ID");
			scheduleddatemakeupsessionmissednooverlap = rs.getString("SCHEDULED_DT");
			System.out.println("Member Account Program id  "+accountProgramID);
		}
		return accountProgramID;
	}

	/**
	 * Name : Vinothkumar.M
	 * Created Date:   26/APR/17
	 * Modified Date:   
	 * Description : Create a common method to create property file to store member id,summary created date,Program interval number and reduce weight for claims (No overlap between member and coach)
	 * Ticket ID :     
	 * Required Inputs :
	 */
	public static void createPropertyForAttended1on1AndMakeupSession()
	{
		try 
		{
			Properties properties = new Properties();
			properties.setProperty("MEMBER_ID",memberforMakeupsessionmissednooverlap);
			properties.setProperty("SCHEDULED_DT", scheduleddatemakeupsessionmissednooverlap);
			properties.setProperty("Program_Interval_Number",GroupSession1Programinterval);
			properties.setProperty("Reduced_weight", GroupSession1Reduceweight);
			File file = new File(Directory.uploadFilePath+"//GroupClaimsTriggeredFor1on1IfItHappenedPriorToMakeupSession.properties");
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
	}

	/** 
	 * Name : Vinothkumar.M
	 * Created Date:   26/APR/16
	 * Modified Date:   
	 * Description : Create a common method to reduce weight in tracker  
	 * Ticket ID :     
	 * Required Inputs :Program interval number
	 *
	 */

	public static String makeupSessionWeightloss(WebDriver driver)
	{
		String Currentweight="";
		double Reducedweight;
		WebElement MemberTracker = driver.findElement(By.xpath(RA_MEMBER_TRACKERS_TAB));
		Manipulation.click(MemberTracker);
		Manipulation.wait(driver, "5");
		WebElement WeightTab = driver.findElement(By.xpath(RA_MEMBER_WEIGHT_SUB_TAB));
		Manipulation.verifyElementIsPresent(driver, WeightTab);
		Manipulation.click(WeightTab);
		Manipulation.wait(driver, "2");
		WebElement TrackWeight = driver.findElement(By.xpath(RA_MEMBER_TRACK_WEIGHT_HEADER_TEXT));
		Manipulation.verifyElementIsPresent(driver, TrackWeight);
		Manipulation.waitForElement(driver, RA_MEMBER_CURRENT_WEIGHT);
		WebElement CurrentWeight = driver.findElement(By.xpath(RA_MEMBER_CURRENT_WEIGHT));
		Currentweight=ElementActions.getText(CurrentWeight);
		System.out.println("Current weight value"+Currentweight);
		double Currentweight1 = Double.parseDouble(Currentweight);
		double Interval = Double.parseDouble(GroupSession1Programinterval);
		Reducedweight=Currentweight1-(Interval*0.4);
		DecimalFormat df1 = new DecimalFormat("0.0");
		df1.setMaximumFractionDigits(1);
		String weightreduced = df1.format(Reducedweight);
		System.out.println("Reduce weight value"+weightreduced);
		String WeightReduced = String.valueOf(weightreduced);
		Manipulation.waitForElement(driver, RA_MEMBER_WEIGHT_REDUCE_TEXTBOX);
		WebElement ReduceWeight= driver.findElement(By.xpath(RA_MEMBER_WEIGHT_REDUCE_TEXTBOX));
		Manipulation.clearAndType(ReduceWeight,WeightReduced);
		Manipulation.wait(driver, "1");
		WebElement SaveButton= driver.findElement(By.id(RA_MEMBER_TRACKER_WEIGHT_SAVE_BUTTON));
		Manipulation.click(SaveButton);
		Manipulation.wait(driver, "7");
		WebElement CurrentWeight1 = driver.findElement(By.xpath(RA_MEMBER_CURRENT_WEIGHT));
		GroupSession1Reduceweight= ElementActions.getText(CurrentWeight1);
		if(GroupSession1Reduceweight.equalsIgnoreCase(WeightReduced))
		{
			return "CurrentWeight is reduced from"+Currentweight+"to"+GroupSession1Reduceweight;
		}
		else
		{
			return "CurrentWeight"+Currentweight+"is not reduced";
		}
	}
	/**
	 * Name : Vinothkumar.M
	 * Created Date:   26/APR/17
	 * Modified Date:   
	 * Description : Create a common method to create property file to store member id,summary created date,Program interval number and reduce weight for claims
	 * Ticket ID :     
	 * Required Inputs :
	 */
	public static void createPropertyForGroupClaimsTriggeredForMakeupWhenOneOnOneIsMissed()
	{
		try 
		{
			Properties properties = new Properties();
			properties.setProperty("MEMBER_ID",memberforMakeupsessionmissednooverlap);
			properties.setProperty("SCHEDULED_DT", scheduleddatemakeupsessionmissednooverlap);
			properties.setProperty("Program_Interval_Number",GroupSession1Programinterval);
			properties.setProperty("Reduced_weight", GroupSession1Reduceweight);
			File file = new File(Directory.uploadFilePath+"//GroupClaimsTriggeredForMakeupWhenOneOnOneIsMissed.properties");
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
	}

	/**
	 * Name : Vinothkumar.M
	 * Created Date:   27/APR/17
	 * Modified Date:   
	 * Description : Create a common method to create property file to store member id,summary created date,Program interval number and reduce weight for claims
	 * Ticket ID :     
	 * Required Inputs :
	 */
	public static void createPropertyForAttendedMakeupAndOneOnOneSession()
	{
		try 
		{
			Properties properties = new Properties();
			properties.setProperty("MEMBER_ID",memberforMakeupsessionmissednooverlap);
			properties.setProperty("SCHEDULED_DT", scheduleddatemakeupsessionmissednooverlap);
			properties.setProperty("Program_Interval_Number",GroupSession1Programinterval);
			properties.setProperty("Reduced_weight", GroupSession1Reduceweight);
			File file = new File(Directory.uploadFilePath+"//GroupClaimsTriggeredForMakeupIfItHappenedPriorToOneonOneSession.properties");
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
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   25/JUL/16
	 * Modified Date:   
	 * Description : Create a common method to retrieve program interval number 
	 * Ticket ID :     
	 * Required Inputs :Member id
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */

	public static String retrieveprogramintervalGroupAnd1on1Attend(WebDriver driver) throws ClassNotFoundException, SQLException 
	{ 
		Connection conn =null;
		Statement stat=null;
		String port = Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select PROGRAM_INTERVAL_NUMBER  FROM SUMMARY_ACCOUNT_TODATE SMRY,PROVIDER PROV, ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID AND SMRY.ACCOUNT_ID=ACNT.ID AND ACNT.EMAIL='"+GroupSession1memberid+"'");			
		System.out.println("query executed");
		if(rs.next())
		{
			GroupSession1Programinterval = rs.getString("PROGRAM_INTERVAL_NUMBER");
			System.out.println("Program Interval number is "+GroupSession1Programinterval);
		}
		return GroupSession1Programinterval;
	}

	/**
	 * Name :    Abinaya.P
	 * Created Date:   25/JUL/16
	 * Modified Date:   
	 * Description : Create a common method to reduce weight in tracker  
	 * Ticket ID :     
	 * Required Inputs :Program interval number
	 *
	 */

	public static String WeightlossGroupAttend1on1(WebDriver driver1)
	{
		String Currentweight="";
		double Reducedweight;
		//		Navigate.refreshPage(driver);
		WebElement MemberTracker = driver1.findElement(By.xpath(RA_MEMBER_TRACKERS_TAB));
		Manipulation.click(MemberTracker);
		Manipulation.wait(driver1, "5");
		WebElement WeightTab = driver1.findElement(By.xpath(RA_MEMBER_WEIGHT_SUB_TAB));
		Manipulation.verifyElementIsPresent(driver1, WeightTab);
		Manipulation.click(WeightTab);
		Manipulation.wait(driver1, "2");
		WebElement TrackWeight = driver1.findElement(By.xpath(RA_MEMBER_TRACK_WEIGHT_HEADER_TEXT));
		Manipulation.verifyElementIsPresent(driver1, TrackWeight);
		Manipulation.waitForElement(driver1, RA_MEMBER_CURRENT_WEIGHT);
		WebElement CurrentWeight = driver1.findElement(By.xpath(RA_MEMBER_CURRENT_WEIGHT));
		Currentweight=ElementActions.getText(CurrentWeight);
		System.out.println("Current weight value"+Currentweight);
		double Currentweight1 = Double.parseDouble(Currentweight);
		double Interval = Double.parseDouble(GroupSession1Programinterval);
		Reducedweight=Currentweight1-(Interval*0.4);
		DecimalFormat df1 = new DecimalFormat("0.0");
		df1.setMaximumFractionDigits(1);
		String weightreduced = df1.format(Reducedweight);
		System.out.println("Reduce weight value"+weightreduced);
		String WeightReduced = String.valueOf(weightreduced);
		Manipulation.waitForElement(driver1, RA_MEMBER_WEIGHT_REDUCE_TEXTBOX);
		WebElement ReduceWeight= driver1.findElement(By.xpath(RA_MEMBER_WEIGHT_REDUCE_TEXTBOX));
		Manipulation.clearAndType(ReduceWeight,WeightReduced);
		Manipulation.wait(driver1, "1");
		WebElement SaveButton= driver1.findElement(By.id(RA_MEMBER_TRACKER_WEIGHT_SAVE_BUTTON));
		Manipulation.click(SaveButton);
		Manipulation.wait(driver1, "7");
		WebElement CurrentWeight1 = driver1.findElement(By.xpath(RA_MEMBER_CURRENT_WEIGHT));
		GroupSession1Reduceweight= ElementActions.getText(CurrentWeight1);
		if(GroupSession1Reduceweight.equalsIgnoreCase(WeightReduced))
		{
			return "CurrentWeight is reduced from"+Currentweight+"to"+GroupSession1Reduceweight;
		}
		else
		{
			return "CurrentWeight"+Currentweight+"is not reduced";
		}
	}

	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   24/MAY/16
	 * Modified Date:   
	 * Description : RA- Group ZLive session for different browser
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */

	public static void raClaimsGroupAnd1on1AttendZLiveSession(WebDriver driver,String testData) throws Exception
	{
		String[] testData1 = testData.split(",");
		String coachBrowser=testData1[0];
		String memberBrowser=testData1[1];

		// Coach Login in Browser Launch 
		WebDriver driver1 = null;

		if ("firefox".equalsIgnoreCase(coachBrowser) ) 
		{
			driver =  new FirefoxBrowser().getDriver();
			driver.manage().window().maximize();
		}
		else if ( "ie".equalsIgnoreCase(coachBrowser) )
		{
			driver = ( WebDriver ) new IEBrowser().getDriver();
			driver.manage().window().maximize();
		}
		else if ( "safari".equalsIgnoreCase(coachBrowser)) 
		{
			driver = new SafariBrowser().getDriver();
			driver.manage().window().maximize();
		}
		wait( driver, "4" );

		// Coach Login
		com.zillion.qa.realappealmember.Claims.retrieveCoachIdandLogin(driver);
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver);

		// Browser launch for Member side
		// Test data seperation from spread sheet
		if ("firefox".equalsIgnoreCase(memberBrowser) ) 
		{
			driver1 =  new FirefoxBrowser().getDriver();
			driver1.manage().window().maximize();
		}
		else if ( "ie".equalsIgnoreCase(memberBrowser) )
		{
			driver1 = ( WebDriver ) new IEBrowser().getDriver();
			driver1.manage().window().maximize();
		}
		else if ( "chrome".equalsIgnoreCase(memberBrowser)) 
		{
			driver1 = new ChromeBrowser().getDriver();
			driver1.manage().window().maximize();
		}
		else if ( "safari".equalsIgnoreCase(memberBrowser)) 
		{
			driver1 = new SafariBrowser().getDriver();
			driver1.manage().window().maximize();
		}

		// Member Login with different browser
		com.zillion.qa.realappealmember.Claims.retrieveMemberIdandLogin(driver1);

		try
		{
			waitForElement(driver1, TERMS_AND_CONDITION_AGREE_BUTTON);
			WebElement agreeButton = driver1.findElement(By.xpath(OR.TERMS_AND_CONDITION_AGREE_BUTTON));
			verifyElementIsPresent(driver1, agreeButton);
			jsClickByXPath(driver1, TERMS_AND_CONDITION_AGREE_BUTTON);
		}
		catch(Exception e)
		{

		}
		Robot rb = new Robot();
		wait( driver, "4" );
		rb.keyPress(KeyEvent.VK_A);
		rb.keyRelease(KeyEvent.VK_A);
		// Open robot for default
		//com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.openRobotDefault( driver);

		/*// To handle tracker server unavailable popup after Login
		com.zillion.qa.realappealmember.member.handleTrackerServerUnavailable( driver1);

		// To handle browser support popup
		com.zillion.qa.realappealmember.member.browserSupportMatrixRA( driver1 );
		 */
		// Append Member URl 
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raMemberGroupSessionAppendURL(driver1);

		waitForElement(driver1, RA_MEMBER_GROUP_SESSION_JOIN_BUTTON);
		WebElement joinButton = driver1.findElement(By.xpath(OR.RA_MEMBER_GROUP_SESSION_JOIN_BUTTON));
		click(joinButton);
		wait(driver1, "3");
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raEnterWeightDuringLiveSession( driver1 );
		wait(driver1, "7");

		// Allow plugin for ZLiveSession
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.allowGroupZLivePlugin(driver1);

		// Retrieve the group session attended time
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raMemberSessionTime(driver1);

		waitForElement(driver1, RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CONNECTED_TEXT);
		WebElement closeButton = driver1.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CLOSE_BUTTON));
		WebElement memberChatTextbox = driver1.findElement(By.xpath(OR.RA_MEMBER_LIVESESSION_CHAT_TEXTBOX));
		WebElement memberChatSendButton = driver1.findElement(By.xpath(OR.RA_MEMBER_LIVESESSION_SEND_BUTTON));
		verifyElementIsPresent(driver1, closeButton);
		verifyElementIsPresent(driver1, memberChatTextbox);
		verifyElementIsPresent(driver1, memberChatSendButton);
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver1);

		// Switch Member to coach
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver1, "4");
		driver.manage().window().maximize();
		wait(driver, "4");

		// Append Coach URL
		String getCurrentURL= driver.getCurrentUrl();
		String appendCurrentURL= getCurrentURL+"?attendnow/&Sessioncount=10";
		Navigate.get( driver, appendCurrentURL );
		wait(driver, "5");
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raCoachUpcomingSessionTimeDifferentBrowser(driver);
		//		Navigate.refreshPage(driver);
		Screen Allow1=new Screen(); 
		wait( driver, "8" );
		Pattern image1 = new Pattern(Directory.uploadFilePath+"BlankArea.PNG");
		Allow1.wait(image1, 15);
		Allow1.click(image1);
		wait( driver, "3" );
		wait(driver, "6");
		/*WebElement providerHeader = driver1.findElement(By.xpath(OR.RA_PROGRAM_ADMIN_REAL_APPEAL_LOGO));
		verifyElementIsPresent(driver, providerHeader);
		mouseOver(driver, providerHeader);
		jsClickByXPath(driver, RA_PROGRAM_ADMIN_REAL_APPEAL_LOGO);*/
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
			wait( driver, "4" );
			rb.keyPress(KeyEvent.VK_CONTROL); 
			rb.keyPress(KeyEvent.VK_S);
			rb.keyRelease(KeyEvent.VK_CONTROL); 
			rb.keyRelease(KeyEvent.VK_S);
			wait( driver, "8" ); 
			rb.keyPress(KeyEvent.VK_S);
			rb.keyRelease(KeyEvent.VK_S);
		}
		wait(driver, "10");
		WebElement shareScreenButton = driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_SHARE_SCREEN_BUTTON));
		WebElement endSessionButton = driver.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_BUTTON));
		WebElement coachMicOn = driver.findElement(By.xpath(OR.COACH_SESSION_MIC_ON));
		WebElement coachVideoOn = driver.findElement(By.xpath(OR.COACH_SESSION_VIDEO_ON));
		WebElement coachChatTextbox = driver.findElement(By.xpath(OR.COACH_SESSION_CHAT_TEXTBOX));
		WebElement coachSendButton = driver.findElement(By.xpath(OR.COACH_SESSION_CHAT_SEND_BUTTON));
		verifyElementIsPresent(driver, coachChatTextbox);
		verifyElementIsPresent(driver, coachSendButton);
		verifyElementIsPresent(driver, shareScreenButton);
		verifyElementIsPresent(driver, endSessionButton);
		verifyElementIsPresent(driver, coachMicOn);
		verifyElementIsPresent(driver, coachVideoOn);
		jsClickByXPath(driver, COACH_SESSION_END_SESSION_BUTTON);
		wait(driver, "2" );
		WebElement endSessionText= driver.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_TEXT));
		verifyElementIsPresent(driver, endSessionText);
		WebElement endSessionYesButton= driver.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_YES_BUTTON));
		WebElement endSessionNoButton= driver.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_NO_BUTTON));
		verifyElementIsPresent(driver, endSessionYesButton);
		verifyElementIsPresent(driver, endSessionNoButton);
		jsClickByXPath(driver, COACH_SESSION_END_SESSION_BUTTON);
		wait(driver, "3" );
		jsClickByXPath(driver, COACH_SESSION_END_SESSION_YES_BUTTON);
		wait(driver, "3" );
		WebElement coachCommentsTextbox= driver.findElement(By.xpath(OR.COACH_COMMENTS_TEXTBOX));
		sendKeys(coachCommentsTextbox, "Session completed with the member");
		jsClickByXPath(driver, COACH_END_SESSION_AND_MARKED_AS_COMPLETED);
		wait(driver, "2" );
		jsClickByXPath(driver, COACH_SESSION_MEMBER_DONE_BUTTON);
		wait(driver, "4" );

		// Switch Coach to Member
		// Take screen shot for coach video
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver);
		driver.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "4" );
		driver1.manage().window().maximize();
		wait(driver1, "4");

		WebElement coachRatingHeader= driver1.findElement(By.xpath(OR.MEMBER_COACH_RATING_HEADER_TEXT));
		WebElement coachRatingTextbox= driver1.findElement(By.xpath(OR.MEMBER_COACH_RATING_TEXTBOX));
		WebElement coachRatingSubmitButton= driver1.findElement(By.xpath(OR.MEMBER_COACH_RATING_SUBMIT_BUTTON));
		WebElement coachRatingCloseButton= driver1.findElement(By.xpath(OR.MEMBER_COACH_RATING_CLOSE_BUTTON));
		WebElement ratingStar1= driver1.findElement(By.xpath(OR.GROUP_SESSION_COACH_RATING_STAR1));
		WebElement ratingStar2= driver1.findElement(By.xpath(OR.GROUP_SESSION_COACH_RATING_STAR2));
		WebElement ratingStar3= driver1.findElement(By.xpath(OR.GROUP_SESSION_COACH_RATING_STAR3));
		WebElement ratingStar4= driver1.findElement(By.xpath(OR.GROUP_SESSION_COACH_RATING_STAR4));
		WebElement ratingStar5= driver1.findElement(By.xpath(OR.GROUP_SESSION_COACH_RATING_STAR5));
		verifyElementIsPresent(driver1, coachRatingHeader);
		verifyElementIsPresent(driver1, coachRatingTextbox);
		verifyElementIsPresent(driver1, coachRatingSubmitButton);
		verifyElementIsPresent(driver1, coachRatingCloseButton);
		verifyElementIsPresent(driver1, ratingStar1);
		verifyElementIsPresent(driver1, ratingStar2);
		verifyElementIsPresent(driver1, ratingStar3);
		verifyElementIsPresent(driver1, ratingStar4);
		verifyElementIsPresent(driver1, ratingStar5);
		click(ratingStar1);
		click(ratingStar2);
		click(ratingStar3);
		click(ratingStar4);
		click(ratingStar5);
		sendKeys(coachRatingTextbox, "Session completed with the coach");
		wait(driver1, "2");
		click(coachRatingSubmitButton);
		wait(driver1, "3");
		jsClickByXPath(driver1, REAL_APPEAL_MEMBER_DASHBOARD_TAB);
		wait(driver1, "3");
		waitForElement(driver1, RA_MEMBERLIVESESSION_SCHEDULE_BUTTON);
		jsClickByXPath(driver1, RA_MEMBERLIVESESSION_SCHEDULE_BUTTON);
		wait(driver1, "2");
		waitForElement(driver1, RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_AVAILABLE_BUTTON);
		jsClickByXPath(driver1, RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_AVAILABLE_BUTTON);
		wait(driver1, "2");
		jsClickByXPath(driver1, RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_AVAILABLE_BUTTON_AVAILABLE_TIME);
		wait(driver1, "2");
		jsClickByXPath(driver1, RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_CONFIRM_BUTTON);
		wait(driver1, "2");
		jsClickByXPath(driver1, REAL_APPEAL_MEMBER_DASHBOARD_TAB);
		wait(driver1, "3");
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raMemberGroupSessionAppendURL(driver1);
		wait(driver1, "6");
		waitForElement(driver1, RA_1ON1_LIVE_SESSION_MEMBER_ATTEND_SESSION_JOIN_BUTTON);
		jsClickByXPath(driver1, RA_1ON1_LIVE_SESSION_MEMBER_ATTEND_SESSION_JOIN_BUTTON);
		wait(driver1, "8");
		waitForElement(driver1, RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CONNECTED_TEXT);

		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver1);

		// Switch Member to coach
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver1, "4");
		driver.manage().window().maximize();
		wait(driver, "4");

		Navigate.get( driver, appendCurrentURL );
		wait(driver, "5");
		waitForElement(driver, RA_COACH_ATTEND_1ON1_SESSION_ATTENDNOW_BUTTON);
		jsClickByXPath(driver, RA_COACH_ATTEND_1ON1_SESSION_ATTENDNOW_BUTTON);
		wait(driver, "7");
		Allow1.wait(image1, 15);
		Allow1.click(image1);
		wait( driver, "3" );
		wait(driver, "6");
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
			wait( driver, "4" );
			rb.keyPress(KeyEvent.VK_CONTROL); 
			rb.keyPress(KeyEvent.VK_S);
			rb.keyRelease(KeyEvent.VK_CONTROL); 
			rb.keyRelease(KeyEvent.VK_S);
			wait( driver, "8" ); 
			rb.keyPress(KeyEvent.VK_S);
			rb.keyRelease(KeyEvent.VK_S);
		}
		wait(driver, "7");
		jsClickByXPath(driver, COACH_SESSION_END_SESSION_BUTTON);
		wait(driver, "2" );
		jsClickByXPath(driver, COACH_SESSION_END_SESSION_BUTTON);
		wait(driver, "3" );
		jsClickByXPath(driver, COACH_SESSION_END_SESSION_YES_BUTTON);
		wait(driver, "3" );
		WebElement commentsTextbox = driver.findElement(By.xpath(OR.COACH_1ON1_COMMENTS_TEXTBOX));
		sendKeys(commentsTextbox, "Session completed with the member");
		WebElement completedRadioButton = driver.findElement(By.xpath(OR.COACH_SESSION_SESSION_COMPLETED_RADIO_BUTTON));
		click(completedRadioButton);
		wait(driver, "2" );
		WebElement DoneButton = driver.findElement(By.xpath(OR.COACH_SESSION_1ON1_MEMBER_DONE_BUTTON));
		click(DoneButton);
		wait(driver, "4" );

		// Switch Coach to Member
		// Take screen shot for coach video
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver);
		driver.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "4" );
		driver1.manage().window().maximize();
		wait(driver1, "4");

		WebElement memberratingStar1 = driver1.findElement(By.xpath(OR.RA_GROUP_SESSION_COACH_RATING_STAR_1));
		WebElement memberratingStar2 = driver1.findElement(By.xpath(OR.RA_GROUP_SESSION_COACH_RATING_STAR_2));
		WebElement memberratingStar3 = driver1.findElement(By.xpath(OR.RA_GROUP_SESSION_COACH_RATING_STAR_3));
		WebElement memberratingStar4 = driver1.findElement(By.xpath(OR.RA_GROUP_SESSION_COACH_RATING_STAR_4));
		WebElement memberratingStar5 = driver1.findElement(By.xpath(OR.RA_GROUP_SESSION_COACH_RATING_STAR_5));
		WebElement memberratingTextbox = driver1.findElement(By.xpath(OR.MEMBER_COACH_RATING_TEXTBOX));
		WebElement memberratingSubmitButton = driver1.findElement(By.xpath(OR.MEMBER_COACH_RATING_SUBMIT_BUTTON));
		verifyElementIsPresent(driver1, memberratingStar1);
		verifyElementIsPresent(driver1, memberratingStar2);
		verifyElementIsPresent(driver1, memberratingStar3);
		verifyElementIsPresent(driver1, memberratingStar4);
		verifyElementIsPresent(driver1, memberratingStar5);
		verifyElementIsPresent(driver1, memberratingStar1);
		verifyElementIsPresent(driver1, memberratingSubmitButton);
		click(ratingStar1);
		click(ratingStar2);
		click(ratingStar3);
		click(ratingStar4);
		click(ratingStar5);
		click(memberratingTextbox);
		sendKeys(memberratingTextbox, "Session completed with coach");
		click(memberratingSubmitButton);
		wait(driver1, "4");
		com.zillion.qa.realappealmember.Claims.retrieveprogramintervalGroupAnd1on1Attend(driver1);
		com.zillion.qa.realappealmember.Claims.WeightlossGroupAttend1on1(driver1);
		com.zillion.qa.realappealmember.Claims.createpropertyforgroupsession1();
	}
	
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   07/MAR/17
	 * Modified Date:   
	 * Description : RA- 10n1 ZLive session for different browser
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */

	public static void raMissedGroupAttend1on1ZLiveSession(WebDriver driver,String testData, String coachEmail1, String memberEmail1) throws Exception
	{
		String[] testData1 = testData.split(",");
		String memberBrowser=testData1[0];
		String coachBrowser=testData1[1];
		
		// Coach Login in Browser Launch 
		WebDriver driver1 = null;

		if ("firefox".equalsIgnoreCase(memberBrowser) ) 
		{
			driver =  new FirefoxBrowser().getDriver();
			driver.manage().window().maximize();
		}
		else if ( "ie".equalsIgnoreCase(memberBrowser) )
		{
			driver = ( WebDriver ) new IEBrowser().getDriver();
			driver.manage().window().maximize();
		}
		else if ( "safari".equalsIgnoreCase(memberBrowser)) 
		{
			driver = new SafariBrowser().getDriver();
			driver.manage().window().maximize();
		}
		else if ( "chrome".equalsIgnoreCase(memberBrowser)) 
		{
			driver = new ChromeBrowser().getDriver();
			driver.manage().window().maximize();
		}
		wait( driver, "4" );

		// Member Login

		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raGroupZLiveSessionMemberLogin(driver, memberEmail1);

		// Open robot for default
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.openRobotDefault( driver);

		// To handle tracker server unavailable popup after Login
		com.zillion.qa.realappealmember.member.handleTrackerServerUnavailable( driver);

		// To handle browser support popup
		com.zillion.qa.realappealmember.member.browserSupportMatrixRA( driver );

		// Append URL for member force attand the session
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raMemberOneOnOneSessionAppendURL(driver);
		wait( driver, "4" );
		waitForElement(driver, RA_1ON1_LIVE_SESSION_MEMBER_ATTEND_SESSION_JOIN_BUTTON);
		WebElement memberAttendnowButton = driver.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_ATTEND_SESSION_JOIN_BUTTON));
		click(memberAttendnowButton);
		wait( driver, "3" );

		//Enter member weight before attending session
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raEnterWeightDuringLiveSessionDifferentBrowser( driver );
		wait( driver, "5" );

		// Allow plugin for the Zlive 
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.allowGroupZLivePluginDifferentBrowser(driver);
		wait( driver, "3" );
		waitForElement(driver, RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CONNECTED_TEXT);
		WebElement sessionCloseButton = driver.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CLOSE_BUTTON));
		verifyElementIsPresent(driver, sessionCloseButton);
		
		String sessiontime1=null;
		WebElement memberSessionTime = driver.findElement(By.xpath(OR.RA_MEMBER_SESSION_TIME));
		String sessionsTime=memberSessionTime.getText();
		SimpleDateFormat ft = new SimpleDateFormat("EEE hhh:mm a");
		java.util.Date t=ft.parse(sessionsTime);
		ft.applyPattern("hh:mma");
		sessiontime1=ft.format(t).toLowerCase().trim();
		System.out.print(ft.format(t));
		System.out.print("Time"+sessiontime1);

		// Browser launch for Coach side
		// Test data seperation from spread sheet
		if ("firefox".equalsIgnoreCase(coachBrowser) ) 
		{
			driver1 =  new FirefoxBrowser().getDriver();
			driver1.manage().window().maximize();
		}
		else if ( "ie".equalsIgnoreCase(coachBrowser) )
		{
			driver1 = ( WebDriver ) new IEBrowser().getDriver();
			driver1.manage().window().maximize();
		}
		else if ( "chrome".equalsIgnoreCase(coachBrowser)) 
		{
			driver1 = new ChromeBrowser().getDriver();
			driver1.manage().window().maximize();
		}
		else if ( "safari".equalsIgnoreCase(coachBrowser)) 
		{
			driver1 = new SafariBrowser().getDriver();
			driver1.manage().window().maximize();
		}

		// Coach Login with different browser
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.coachLoginWithReferenceStepDifferentBrowser(driver1, coachEmail1);
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raCoachOneOnOneSessionAppendURLDifferentBrowser(driver1);
		wait(driver1, "3");
		WebElement coachUpcomingSessionTime = driver1.findElement(By.xpath("//tr//td[text()='"+sessiontime1+"']/following::td[text()='1on1']/following-sibling::td//following-sibling::td//following-sibling::td/following-sibling::td/div/a"));
		verifyElementIsPresent(driver1, coachUpcomingSessionTime); 
		click(coachUpcomingSessionTime);
		wait(driver1, "7");

		// share selected device for the coach Zlivesession
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.shareSelectedDeviceDifferentBrowser(driver1);
		wait(driver1, "4");
		WebElement coachSettingsButton = driver1.findElement(By.xpath(OR.COACH_SESSION_SETTINGS_BUTTON));
		WebElement coachEndSessionButton = driver1.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_BUTTON));
		verifyElementIsPresent(driver1, coachSettingsButton);
		verifyElementIsPresent(driver1, coachEndSessionButton);
		click(coachEndSessionButton);
		wait(driver1, "3");
		WebElement endSessionText = driver1.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_TEXT));
		WebElement endSessionYesButton = driver1.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_YES_BUTTON));
		WebElement endSessionNoButton = driver1.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_NO_BUTTON));
		verifyElementIsPresent(driver1, endSessionText);
		verifyElementIsPresent(driver1, endSessionYesButton);
		verifyElementIsPresent(driver1, endSessionNoButton);
		click(endSessionYesButton);
		wait(driver1, "3");
		waitForElement(driver1, COACH_SESSION_SESSION_COMPLETE_HEADER_TEXT);
		WebElement coachSessionCompletedHeader = driver1.findElement(By.xpath(OR.COACH_SESSION_SESSION_COMPLETE_HEADER_TEXT));
		WebElement sessionEndedText = driver1.findElement(By.xpath(OR.COACH_SESSION_YOUR_1ON1_SESSION_ENDED_TEXT));
		WebElement commentsTextbox = driver1.findElement(By.xpath(OR.COACH_1ON1_COMMENTS_TEXTBOX));
		WebElement completedRadioButton = driver1.findElement(By.xpath(OR.COACH_SESSION_SESSION_COMPLETED_RADIO_BUTTON));
		WebElement memberDidNotAttendRadioButton = driver1.findElement(By.xpath(OR.COACH_SESSION_MEMBER_DID_NOT_ATTEND_RADIO_BUTTON));
		WebElement SessionCompletedText = driver1.findElement(By.xpath(OR.COACH_SESSION_SESSION_COMPLETED_TEXT));
		WebElement MemberDidNotAttendText = driver1.findElement(By.xpath(OR.COACH_SESSION_MEMBER_DID_NOT_ATTEND_TEXT));
		WebElement DoneButton = driver1.findElement(By.xpath(OR.COACH_SESSION_1ON1_MEMBER_DONE_BUTTON));
		verifyElementIsPresent(driver1, coachSessionCompletedHeader);
		verifyElementIsPresent(driver1, sessionEndedText);
		verifyElementIsPresent(driver1, commentsTextbox);
		verifyElementIsPresent(driver1, completedRadioButton);
		verifyElementIsPresent(driver1, memberDidNotAttendRadioButton);
		verifyElementIsPresent(driver1, SessionCompletedText);
		verifyElementIsPresent(driver1, MemberDidNotAttendText);
		verifyElementIsPresent(driver1, DoneButton);
		click(commentsTextbox);
		sendKeys(commentsTextbox, "Session Completed");
		click(completedRadioButton);
		wait(driver1, "1");
		click(DoneButton);
		wait(driver1, "2");

		//RealAppeal coach Logout
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.realAppealCoachLogout(driver1);

		// Minimize the coach browser
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver1);


		// Switch Coach to member
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver1, "8");
		driver.manage().window().maximize();
		wait(driver, "10");

		WebElement ratingStar1 = driver.findElement(By.xpath(OR.RA_GROUP_SESSION_COACH_RATING_STAR_1));
		WebElement ratingStar2 = driver.findElement(By.xpath(OR.RA_GROUP_SESSION_COACH_RATING_STAR_2));
		WebElement ratingStar3 = driver.findElement(By.xpath(OR.RA_GROUP_SESSION_COACH_RATING_STAR_3));
		WebElement ratingStar4 = driver.findElement(By.xpath(OR.RA_GROUP_SESSION_COACH_RATING_STAR_4));
		WebElement ratingStar5 = driver.findElement(By.xpath(OR.RA_GROUP_SESSION_COACH_RATING_STAR_5));
		WebElement ratingTextbox = driver.findElement(By.xpath(OR.MEMBER_COACH_RATING_TEXTBOX));
		WebElement ratingSubmitButton = driver.findElement(By.xpath(OR.MEMBER_COACH_RATING_SUBMIT_BUTTON));
		verifyElementIsPresent(driver, ratingStar1);
		verifyElementIsPresent(driver, ratingStar2);
		verifyElementIsPresent(driver, ratingStar3);
		verifyElementIsPresent(driver, ratingStar4);
		verifyElementIsPresent(driver, ratingStar5);
		verifyElementIsPresent(driver, ratingTextbox);
		verifyElementIsPresent(driver, ratingSubmitButton);
		click(ratingStar1);
		click(ratingStar2);
		click(ratingStar3);
		click(ratingStar4);
		click(ratingStar5);
		click(ratingTextbox);
		sendKeys(ratingTextbox, "Session completed with coach");
		click(ratingSubmitButton);
		wait(driver, "4");
		com.zillion.qa.realappealmember.member.realAppealMemberlogout(driver);
		driver.close();
		driver1.close();
	}
}
