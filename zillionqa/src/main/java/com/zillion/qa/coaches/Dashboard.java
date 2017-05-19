package com.zillion.qa.coaches;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.zillion.qa.commands.Manipulation;
import com.zillion.qa.commands.Navigate;
import com.zillion.qa.utils.Directory;
public class Dashboard extends Manipulation implements OR
{
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   14/12/2015
	 * Modified Date:  6/1/2016
	 * Description :   Create a common method for Login Coaches URL for User-1
	 * Ticket ID :    ZA-41
	 * Required Inputs :  No Inputs Required
	 * Purpose :   Logout from the CMS platform
	 */
	public  static void coachesLogin(WebDriver driver)
	{
		Navigate.get(driver, Directory.Coachesurl);
		Navigate.maximize(driver);
		WebElement Coacheslogin_logo= driver.findElement(By.xpath(OR.COACHES_LOGIN_PAGE_LOGO_REF));
		verifyElementIsPresent(driver, Coacheslogin_logo);
		WebElement coach_username= driver.findElement(By.xpath(OR.COACHES_USERNAME_1));
		verifyElementIsPresent(driver, coach_username);
		WebElement coach_password= driver.findElement(By.xpath(OR.COACHES_PASSWORD));
		verifyElementIsPresent(driver, coach_password);
		WebElement Coacheslogin_button= driver.findElement(By.xpath(OR.COACHES_LOGIN_BUTTON));
		verifyElementIsPresent(driver, Coacheslogin_button);
		click(Coacheslogin_button);
		WebElement coachUsernameRequired= driver.findElement(By.xpath(OR.COACHES_LOGIN_USERNAME_REQUIRED));
		WebElement coachPasswordRequired= driver.findElement(By.xpath(OR.COACHES_LOGIN_PASSWORD_REQUIRED));
		verifyElementIsPresent(driver, coachUsernameRequired);
		verifyElementIsPresent(driver, coachPasswordRequired);
		sendKeys(coach_username,Directory.Coachesusername1);
		sendKeys(coach_password, Directory.Coachespassword1);
		click(Coacheslogin_button);
		wait(driver, "5");
		System.out.println("Coaches Logged in successfully");
	}
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   14/12/2015
	 * Modified Date:
	 * Description :Coaches Logout
	 * Purpose :   Logout from the CMS platform
	 */
	public  static void coachesLogout(WebDriver driver)
	{
		wait(driver, "5");
		Navigate.waitTime(driver, "20");
		waitForElement(driver, OR.COACHES_SIGNOUT_LINK_BUTTON);
		WebElement Signout_link_settings = driver.findElement(By.xpath(OR.COACHES_SIGNOUT_LINK_BUTTON));
		actionClick(driver, Signout_link_settings);
		waitForElement(driver, COACHES_SIGNOUT_BUTTON);
		jsClickByXPath(driver, COACHES_SIGNOUT_BUTTON);
		wait(driver, "5");
		Navigate.waitTime(driver, "20");
		waitForElement(driver, COACHES_SIGNOUT_YOU_HAVE_SIGNED_OUT_TEXT_REF);
	}
	/**
	 * Name         :   Abinaya
	 * Created Date :   09Feb2016
	 * Modified Date:
	 * Description  : Create a common method to validate coach profile across database
	 * Testcase Sheet: Coaches_General_Tab_185839.xlsx
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String coachProfileValidationUsingDB(WebDriver driver,String inputData,String coachProfileMemEmail1) throws ClassNotFoundException, SQLException
	{
		String[] inputData1 = inputData.split(",");
		String inputFirstName=inputData1[0];
		String inputLastName=inputData1[1];
		String inputPhoneNumber=inputData1[2];
		String inputAddress=inputData1[3];
		String inputAddress1=inputData1[4];
		String inputCity=inputData1[5];
		String inputState=inputData1[6];
		String inputZipCode=inputData1[7];
		String inputGender=inputData1[8];
		String inputDob=inputData1[9];
		String inputBio=inputData1[10];
		String mailid=inputData1[11];
		String allfields="";
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
		ResultSet rs = stat.executeQuery("select FIRST_NAME,LAST_NAME,EMAIL,PHONE,STREET,STREET2,CITY,STATE,POSTAL_CODE,GENDER,BIRTH_DT,BIO from provider where email ='"+coachProfileMemEmail1+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			String firstname = rs.getString("FIRST_NAME");
			System.out.println("FirstName="+firstname);
			String lastname = rs.getString("LAST_NAME");
			System.out.println("LastName="+lastname);
			String email = rs.getString("EMAIL");
			System.out.println("EMAIL="+email);
			String phonenumber = rs.getString("PHONE");
			System.out.println("PHONENUMBER="+phonenumber);
			String address = rs.getString("STREET");
			System.out.println("Address="+address);
			String address11 = rs.getString("STREET2");
			System.out.println("Address1="+address11);
			String city1 = rs.getString("CITY");
			System.out.println("CITY="+city1);
			String state = rs.getString("STATE");
			System.out.println("STATE="+state);
			String postalcode = rs.getString("POSTAL_CODE");
			System.out.println("POSTAL_CODE="+postalcode);
			String gender = rs.getString("GENDER");
			System.out.println("GENDER="+gender);
			String dob = rs.getString("BIRTH_DT");
			System.out.println("BIRTH_DT="+dob);
			String bio = rs.getString("BIO");
			System.out.println("Bio="+bio);
			if ((firstname.equals(inputFirstName)))
			{
				System.out.println("First name from coach portal and Database is matched");
			}
			else
			{
				System.out.println("First name from coach portal and Database is not matched");
				Assert.fail();
			}
			if ((lastname.equals(inputLastName)))
			{
				System.out.println("Last name from coach portal and Database is matched");
			}
			else
			{
				System.out.println("Last name from coach portal and Database is not matched");
				Assert.fail();
			}
			if ((email.equals(mailid)))
			{
				System.out.println("Email from coach portal and Database is matched");
			}
			else
			{
				System.out.println("Email from coach portal and Database is not matched");
				Assert.fail();
			}
			if ((phonenumber.equals(inputPhoneNumber)))
			{
				System.out.println("Phone number from coach portal and Database is matched");
			}
			else
			{
				System.out.println("Phone number from coach portal and Database is not matched");
				Assert.fail();
			}
			if ((address.equals(inputAddress)))
			{
				System.out.println("Address from coach portal and Database is matched");
			}
			else
			{
				System.out.println("Address from coach portal and Database is not matched");
				Assert.fail();
			}
			if ((address11.equals(inputAddress1)))
			{
				System.out.println("Address1 from coach portal and Database is matched");
			}
			else
			{
				System.out.println("Address1 from coach portal and Database is not matched");
				Assert.fail();
			}
			if ((city1.equals(inputCity)))
			{
				System.out.println("City from coach portal and Database is matched");
			}
			else
			{
				System.out.println("City from coach portal and Database is not matched");
				Assert.fail();
			}
			if ((state.equals(inputState)))
			{
				System.out.println("State from coach portal and Database is matched");
			}
			else
			{
				System.out.println("State from coach portal and Database is not matched");
				Assert.fail();
			}
			if ((postalcode.equals(inputZipCode)))
			{
				System.out.println("Zipcode from coach portal and Database is matched");
			}
			else
			{
				System.out.println("Zipcode from coach portal and Database is not matched");
				Assert.fail();
			}
			if ((gender.equals(inputGender)))
			{
				System.out.println("Gender from coach portal and Database is matched");
			}
			else
			{
				System.out.println("Gender from coach portal and Database is not matched");
				Assert.fail();
			}
			if ((dob.equals(inputDob)))
			{
				System.out.println("DOB from coach portal and Database is matched");
			}
			else
			{
				System.out.println("DOB from coach portal and Database is not matched");
				Assert.fail();
			}
			if ((bio.trim().equals(inputBio)))
			{
				System.out.println("Bio from coach portal and Database is matched");
			}
			else
			{
				System.out.println("Bio from coach portal and Database is not matched");
				Assert.fail();
			}
		}
		return allfields;
	}
	/**
	 * Name         :   Abinaya
	 * Created Date :   11Feb2016
	 * Modified Date:
	 * Description  : Create a common method to retrieve phone from coach profile over database
	 * Testcase Sheet:Coaches_General_Tab_185839.xlsx
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String retrievePhoneFromCoachDB(WebDriver driver, String inputData) throws ClassNotFoundException, SQLException
	{
		String phone="";
		String mailId=inputData;
		String port = Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user =Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select FIRST_NAME,LAST_NAME,EMAIL,MOBILE_PHONE,PHONE,STREET,STREET2,CITY,STATE,POSTAL_CODE,GENDER,BIRTH_DT,BIO from provider where email ='"+mailId+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			phone = rs.getString("PHONE");
			System.out.println("PHONE="+phone);
		}
		return phone;
	}
	/**
	 * Name         :   Abinaya
	 * Created Date :   11Feb2016
	 * Modified Date:
	 * Description  : Create a common method to retrieve mobile from coach profile over database
	 * Testcase Sheet:Coaches_General_Tab_185839.xlsx
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String retrieveMobileFromCoachDB(WebDriver driver, String mailId) throws ClassNotFoundException, SQLException
	{
		String mobile="";
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
		ResultSet rs = stat.executeQuery("select FIRST_NAME,LAST_NAME,EMAIL,MOBILE_PHONE,PHONE,STREET,STREET2,CITY,STATE,POSTAL_CODE,GENDER,BIRTH_DT,BIO from provider where email ='"+mailId+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			mobile = rs.getString("MOBILE_PHONE");
			System.out.println("MOBILE_PHONE="+mobile);
		}
		return mobile;
	}
	/**
	 * Name         :   Abinaya
	 * Created Date :   11Feb2016
	 * Modified Date:
	 * Description  : Create a common method to retrieve city from coach profile over database
	 * TestcaseSheet : Coaches_General_Tab_185839.xlsx
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String retrieveCityFromCoachDB(WebDriver driver, String mailId) throws ClassNotFoundException, SQLException
	{
		String city="";
		String port = Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass =Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select FIRST_NAME,LAST_NAME,EMAIL,MOBILE_PHONE,PHONE,STREET,STREET2,CITY,STATE,POSTAL_CODE,GENDER,BIRTH_DT,BIO from provider where email ='"+mailId+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			city = rs.getString("CITY");
			System.out.println("CITY="+city);
		}
		return city;
	}
	/**
	 * Name         :   Abinaya
	 * Created Date :   11Feb2016
	 * Modified Date:
	 * Description  : Create a common method to retrieve zipcode from coach profile over database
	 * TestcaseSheet : Coaches_General_Tab_185839.xlsx
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String retrieveZipFromCoachDB(WebDriver driver, String mailId) throws ClassNotFoundException, SQLException
	{
		String zip="";
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
		ResultSet rs = stat.executeQuery("select FIRST_NAME,LAST_NAME,EMAIL,MOBILE_PHONE,PHONE,STREET,STREET2,CITY,STATE,POSTAL_CODE,GENDER,BIRTH_DT,BIO from provider where email ='"+mailId+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			zip = rs.getString("POSTAL_CODE");
			System.out.println("POSTAL_CODE="+zip);
		}
		return zip;
	}
	/**
	 * Name         :   Abinaya
	 * Created Date :   12Feb2016
	 * Modified Date:
	 * Description  : Create a common method to retrieve bio from coach profile over database
	 * TestcaseSheet : Coaches_General_Tab_185839.xlsx
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String retrieveBioFromCoachDB(WebDriver driver, String mailId) throws ClassNotFoundException, SQLException
	{
		String bio="";
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
		ResultSet rs = stat.executeQuery("select BIO from provider where email ='"+mailId+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			bio = rs.getString("BIO");
			System.out.println("BIO="+bio);
		}
		return bio;
	}
	/**
	 * Name         :   VIGNESHRAJ.M
	 * Created Date :   12Feb2016
	 * Modified Date:
	 * Description  : Create a common method to retrieve bio from coach profile over database
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static void liveSessionCoachLogin(WebDriver driver)
	{
		Navigate.get(driver, Directory.Coachesurl);
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
		click(Coacheslogin_button);
		WebElement coachUsernameRequired= driver.findElement(By.xpath(OR.COACHES_LOGIN_USERNAME_REQUIRED));
		WebElement coachPasswordRequired= driver.findElement(By.xpath(OR.COACHES_LOGIN_PASSWORD_REQUIRED));
		verifyElementIsPresent(driver, coachUsernameRequired);
		verifyElementIsPresent(driver, coachPasswordRequired);
		sendKeys(coach_username,Directory.LiveSessionCoachUsername);
		sendKeys(coach_password, Directory.LiveSessionCoachPassword);
		click(Coacheslogin_button);
		Navigate.waitTime(driver, "20");
	}
	/**
	 * Created By:SURESH V
	 * DATE:17/march/2016
	 * Modified Date:18/March/2016
	 */

	public static void launchCoachUrl(WebDriver driver)
	{
		//Navigate.get(driver, Directory.Coachesurl);
		try
		{
			Navigate.navigateUrl(driver, Directory.Coachesurl);
			System.out.println("try blockkkkkkkkk launchCoachUrl");
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
	public static void getHttpUrlCoach(WebDriver driver)
	{
		driver.get(Directory.Coachesurl);
	}
	/**
	 * Name :     Abinaya.P
	 * Created Date:   11/May/2016
	 * Modified Date:
	 * Description :   Create a common method for
	 * Ticket ID :
	 * Required Inputs :  No Inputs Required
	 */
	public  static void coachEnterPassword(WebDriver driver)
	{
		WebElement coach_password= driver.findElement(By.xpath(OR.COACHES_PASSWORD));
		try
		{
			sendKeys(coach_password, "Healthfleet2015");
			waitForElement(driver, OR.COACHES_LOGIN_BUTTON);
			jsClickByXPath(driver, OR.COACHES_LOGIN_BUTTON);
			waitForElement(driver, OR.COACHES_LOGO);
			wait(driver, "5");
		}
		catch(Exception e)
		{
			clear(coach_password);
			sendKeys(coach_password, "Zadotesting1");
			waitForElement(driver, OR.COACHES_LOGIN_BUTTON);
			jsClickByXPath(driver, OR.COACHES_LOGIN_BUTTON);
			waitForElement(driver, OR.COACHES_LOGO);
			wait(driver, "5");
		}
	}
	/**
	 * Name :     VigneshRaj.M
	 * Created Date:   11/May/2016
	 * Modified Date:
	 * Description :   Create a common method for verifying the coach validation All session
	 * Ticket ID :
	 * Required Inputs :  No Inputs Required
	 */
	public  static void coachAllSessionSubtabVerification (WebDriver driver)
	{
		try
		{
			String counterNumber = driver.findElement(By.xpath(OR.ALL_SESSION_PAGE_COUNTER_NUMBER)).getText();
			String counterNumber1 = counterNumber.replace("(", "");
			String counterNumber2 = counterNumber1.replace(")", "");
			int pagecounter = Integer.parseInt(counterNumber2);
			if (pagecounter>=1)
			{
				WebElement dateField= driver.findElement(By.xpath(OR.UPCOMING_SESSION_DATE_FIELD));
				WebElement timeField= driver.findElement(By.xpath(OR.UPCOMING_SESSION_TIME_FIELD));
				WebElement programField= driver.findElement(By.xpath(OR.UPCOMING_SESSION_PROGRAM_FIELD));
				WebElement sessionTypeField= driver.findElement(By.xpath(OR.UPCOMING_SESSION_SESSION_TYPE_FIELD));
				WebElement statusField= driver.findElement(By.xpath(OR.UPCOMING_SESSION_STATUS_FIELD));
				verifyElementIsPresent(driver, dateField);
				verifyElementIsPresent(driver, timeField);
				verifyElementIsPresent(driver, programField);
				verifyElementIsPresent(driver, sessionTypeField);
				verifyElementIsPresent(driver, statusField);
				System.out.println("The Upcoming session fields are verified");
			}
		}
		catch(Exception e)
		{
			System.out.println("There is no Upcoming Session for this coach");
		}
	}
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   14/12/2015
	 * Modified Date:  6/1/2016
	 * Description :   For All Session subtab coach
	 * Ticket ID :
	 * Required Inputs :  No Inputs Required
	 * Purpose :   Logout from the CMS platform
	 */
	public  static void allSessionCoachLogin(WebDriver driver)
	{
		Navigate.get(driver, Directory.Coachesurl);
		Navigate.maximize(driver);
		WebElement Coacheslogin_logo= driver.findElement(By.xpath(OR.COACHES_LOGIN_PAGE_LOGO_REF));
		verifyElementIsPresent(driver, Coacheslogin_logo);
		WebElement coach_username= driver.findElement(By.xpath(OR.COACHES_USERNAME_1));
		verifyElementIsPresent(driver, coach_username);
		WebElement coach_password= driver.findElement(By.xpath(OR.COACHES_PASSWORD));
		verifyElementIsPresent(driver, coach_password);
		WebElement Coacheslogin_button= driver.findElement(By.xpath(OR.COACHES_LOGIN_BUTTON));
		verifyElementIsPresent(driver, Coacheslogin_button);
		click(Coacheslogin_button);
		WebElement coachUsernameRequired= driver.findElement(By.xpath(OR.COACHES_LOGIN_USERNAME_REQUIRED));
		WebElement coachPasswordRequired= driver.findElement(By.xpath(OR.COACHES_LOGIN_PASSWORD_REQUIRED));
		verifyElementIsPresent(driver, coachUsernameRequired);
		verifyElementIsPresent(driver, coachPasswordRequired);
		sendKeys(coach_username,Directory.AllSessioncoachesusername);
		sendKeys(coach_password, Directory.AllSessioncoachespassword);
		click(Coacheslogin_button);
		Navigate.waitTime(driver, "20");
		System.out.println("Coaches Logged in successfully");
	}
}