package com.zillion.qa.programadmin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.zillion.qa.commands.Manipulation;
import com.zillion.qa.commands.Navigate;
import com.zillion.qa.utils.Directory;
public class Dashboard extends Manipulation implements OR {
	/**
	 * Name         :   Suresh.V
	 * Created Date :   15/Dec/15
	 * Modified Date:   04/Jan/16
	 * Description  :   Using provider "URL" login as a PA account with validation
	 * Required Inputs: Username and password.
	 * Test_ID:185875
	 */
	public static void programAdminLogin(WebDriver driver)
	{
		Navigate.get(driver, Directory.Providerurl);
		Navigate.maximize(driver);
		WebElement login = driver.findElement(By.xpath(OR.PROVIDER_LOGIN_BUTTON));
		verifyElementIsPresent(driver, login);
		WebElement usernameTextbox=driver.findElement(By.xpath(OR.EMAIL_TEXTBOX_PROVIDER_URL));
		click(login);
		WebElement usernameRequiredMessage = driver.findElement(By.xpath(OR.USERNAME_REQUIRED_IN_LOGIN_PAGE));
		WebElement passwordRequiredMessage = driver.findElement(By.xpath(OR.PASSWORD_REQUIRED_IN_LOGIN_PAGE));
		verifyElementIsPresent(driver, usernameRequiredMessage);
		verifyElementIsPresent(driver, passwordRequiredMessage);
		sendKeys(usernameTextbox, Directory.Programadminusername);
		click(login);
		verifyElementIsPresent(driver, passwordRequiredMessage);
		WebElement passwordTextbox= driver.findElement(By.xpath(OR.PASSWORD_TEXTBOX_PROVIDER_URL));
		sendKeys(passwordTextbox, Directory.Programadminpassword);
		click(login);
		waitForElement(driver, PROGRAM_ADMIN_PATIENTS_TAB);
		WebElement dashboardTab = driver.findElement(By.xpath(OR.PROGRAM_ADMIN_DASHBOARD_TAB));
		WebElement userId=driver.findElement(By.xpath(OR.PROGRAM_ADMIN_USER_ID_DISPLAY_RIGHT_CORNER));
		verifyElementIsPresent(driver, userId);
		verifyElementIsPresent(driver, dashboardTab);
		WebElement patientTab=driver.findElement(By.xpath(OR.PROGRAM_ADMIN_PATIENTS_TAB));
		WebElement programsTab=driver.findElement(By.xpath(OR.PROGRAM_ADMIN_PROGRAMS_TAB));
		WebElement providersTab=driver.findElement(By.xpath(OR.PROGRAM_ADMIN_PROVIDERS_TAB));
		WebElement clientsTab=driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLIENTS_TAB));
		WebElement messagesTab=driver.findElement(By.xpath(OR.PROGRAM_ADMIN_MESSAGES_TAB));
		verifyElementIsPresent(driver, patientTab);
		verifyElementIsPresent(driver, programsTab);
		verifyElementIsPresent(driver, providersTab);
		verifyElementIsPresent(driver, clientsTab);
		verifyElementIsPresent(driver, messagesTab);
	}
	/**
	 * Name         :   Suresh.V
	 * Created Date :   15/Dec/15
	 * Modified Date:   04/Jan/16
	 * Description  :   Using provider "URL"  PA account signout.
	 */
	public static void programAdminLogout(WebDriver driver)
	{
		wait(driver, "5");
		WebElement signOutWayLink= driver.findElement(By.xpath(OR.PA_SETTING_IMAGE));
		wait(driver, "5");
		verifyElementIsPresent(driver, signOutWayLink);
		click(signOutWayLink);
		WebElement signOutLink= driver.findElement(By.xpath(OR.PA_SIGNOUT_LINK));
		click(signOutLink);
		wait(driver, "10");
		WebElement againSignInLink= driver.findElement(By.xpath(OR.SIGNIN_AGAIN_LINK));
		verifyElementIsPresent(driver, againSignInLink);
	}
	/**
	 * Name :  VinothKumar.M
	 * Created Date:   11/Feb/16
	 * Modified Date:
	 * Description :Create a common method for Login into PA_Wellnesscorp_login
	 * Ticket ID :
	 * Required Inputs :Username and Password
	 */
	public  static void paWellnesscorpLogin(WebDriver driver)
	{
		Navigate.get(driver, Directory.Coachesurl);
		Navigate.maximize(driver);
		WebElement Coacheslogin_logo= driver.findElement(By.xpath(OR.COACHES_LOGIN_PAGE_LOGO_REF));
		verifyElementIsPresent(driver, Coacheslogin_logo);
		WebElement coach_username= driver.findElement(By.xpath(OR.PA_WELLNESSCORP_USERNAME));
		verifyElementIsPresent(driver, coach_username);
		WebElement coach_password= driver.findElement(By.xpath(OR.PA_WELLNESSCORP_PASSWORD));
		verifyElementIsPresent(driver, coach_password);
		WebElement Coacheslogin_button= driver.findElement(By.xpath(OR.PA_WELLNESSCORP_LOGIN_BUTTON));
		verifyElementIsPresent(driver, Coacheslogin_button);
		sendKeys(coach_username,Directory.PAwellnesscorpusername);
		sendKeys(coach_password, Directory.PAwellnesscorppassword);
		click(Coacheslogin_button);
		Navigate.waitTime(driver, "20");
	}
	/**
	 * Name :     Vinothkumar.M
	 * Created Date:   11/Feb/16
	 * Modified Date:
	 * Description :   Create a common method for Logout for PA_Wellnesscorp_logout
	 * Ticket ID :
	 * Required Inputs :  Username and Password
	 */
	public  static void paWellnesscorpLogout(WebDriver driver)
	{
		Navigate.waitTime(driver, "20");
		waitForElement(driver, OR.COACHES_SIGNOUT_LINK_BUTTON);
		WebElement Signout_link_settings = driver.findElement(By.xpath(OR.COACHES_SIGNOUT_LINK_BUTTON));
		verifyElementIsPresent(driver, Signout_link_settings);
		click(Signout_link_settings);
		WebElement Signout_button= driver.findElement(By.xpath(OR.COACHES_SIGNOUT_BUTTON));
		verifyElementIsPresent(driver, Signout_button);
		click(Signout_button);
		Navigate.waitTime(driver, "20");
		WebElement coachesYouHaveSignedOutTextRef= driver.findElement(By.xpath(OR.COACHES_SIGNOUT_YOU_HAVE_SIGNED_OUT_TEXT_REF));
		WebElement coachesSignInAgainTextRef= driver.findElement(By.xpath(OR.COACHES_SIGNOUT_SIGN_IN_AGAIN_TEXT_REF));
		verifyElementIsPresent(driver, coachesYouHaveSignedOutTextRef);
		verifyElementIsPresent(driver, coachesSignInAgainTextRef);
	}
	/**
	 * Name :    Suresh V
	 * Created Date:   17/Mar/16
	 */
	public static void launchProviderUrl(WebDriver driver)
	{
		Navigate.get(driver, Directory.Providerurl);
	}
	public static void getHttpUrlProvider(WebDriver driver)
	{
		driver.get(Directory.Providerurl);
	}
	/**
	 * Name :      Vigneshraj.M
	 * Created Date:   15/Apr/16
	 * Modified Date:
	 * Description :   Create a common method for Logout for PA_Apollo Endo_login
	 * Ticket ID :
	 * Required Inputs :  Username and Password
	 */
	public static void paApolloEndoLogin(WebDriver driver)
	{
		Navigate.get(driver, Directory.Providerurl);
		Navigate.maximize(driver);
		WebElement Coacheslogin_logo= driver.findElement(By.xpath(OR.COACHES_LOGIN_PAGE_LOGO_REF));
		verifyElementIsPresent(driver, Coacheslogin_logo);
		WebElement coach_username= driver.findElement(By.xpath(OR.PA_WELLNESSCORP_USERNAME));
		verifyElementIsPresent(driver, coach_username);
		WebElement coach_password= driver.findElement(By.xpath(OR.PA_WELLNESSCORP_PASSWORD));
		verifyElementIsPresent(driver, coach_password);
		WebElement Coacheslogin_button= driver.findElement(By.xpath(OR.PA_WELLNESSCORP_LOGIN_BUTTON));
		verifyElementIsPresent(driver, Coacheslogin_button);
		sendKeys(coach_username,Directory. PAapolloEndousername);
		sendKeys(coach_password, Directory.PAapolloEndopassword);
		click(Coacheslogin_button);
	}
	/**
	 * Name :      Vinothkumar.M
	 * Created Date:  26/Sep/16
	 * Modified Date:
	 * Description :   Create a common method for Login into Practice PA
	 * Ticket ID :
	 * Required Inputs :  Username and Password
	 */
	public static void practicePALogin(WebDriver driver)
	{
		Navigate.get(driver, Directory.Providerurl);
		Navigate.maximize(driver);
		wait(driver, "4");
		waitForElement(driver, PROVIDER_LOGIN_BUTTON);
		WebElement login = driver.findElement(By.xpath(OR.PROVIDER_LOGIN_BUTTON));
		verifyElementIsPresent(driver, login);
		WebElement usernameTextbox=driver.findElement(By.xpath(OR.EMAIL_TEXTBOX_PROVIDER_URL));
		click(login);
		WebElement usernameRequiredMessage = driver.findElement(By.xpath(OR.USERNAME_REQUIRED_IN_LOGIN_PAGE));
		WebElement passwordRequiredMessage = driver.findElement(By.xpath(OR.PASSWORD_REQUIRED_IN_LOGIN_PAGE));
		verifyElementIsPresent(driver, usernameRequiredMessage);
		verifyElementIsPresent(driver, passwordRequiredMessage);
		sendKeys(usernameTextbox, Directory.PracticePAusername);
		click(login);
		verifyElementIsPresent(driver, passwordRequiredMessage);
		WebElement passwordTextbox= driver.findElement(By.xpath(OR.PASSWORD_TEXTBOX_PROVIDER_URL));
		sendKeys(passwordTextbox, Directory.PracticePApassword);
		click(login);
		wait(driver, "7");
		waitForElement(driver, PROGRAM_ADMIN_PATIENTS_TAB);
		waitForElement(driver, PROGRAM_ADMIN_DASHBOARD_TAB);
		waitForElement(driver, PROGRAM_ADMIN_USER_ID_DISPLAY_RIGHT_CORNER);
	}
	/**
	 * Name :      Vinothkumar.M
	 * Created Date:  26/Sep/16
	 * Modified Date:
	 * Description :   Create a common method for Logout in to Practice PA
	 * Ticket ID :
	 * Required Inputs :  Username and Password
	 */
	public static void practicePALogout(WebDriver driver)
	{
		wait(driver, "5");
		WebElement signOutWayLink= driver.findElement(By.xpath(OR.PA_SETTING_IMAGE));
		wait(driver, "5");
		verifyElementIsPresent(driver, signOutWayLink);
		click(signOutWayLink);
		WebElement signOutLink= driver.findElement(By.xpath(OR.PA_SIGNOUT_LINK));
		click(signOutLink);
		wait(driver, "10");
		WebElement againSignInLink= driver.findElement(By.xpath(OR.SIGNIN_AGAIN_LINK));
		verifyElementIsPresent(driver, againSignInLink);
	}
	/**
	 * Name :      SURESH V
	 * Created Date:  13/Oct/16
	 * Modified Date:
	 * Description :   Create a common method for Login into Practice PA
	 * Ticket ID :
	 * Required Inputs :  Username and Password
	 */
	public static void practicePALogin2(WebDriver driver)
	{
		Navigate.get(driver, Directory.Providerurl);
		Navigate.maximize(driver);
		WebElement login = driver.findElement(By.xpath(OR.PROVIDER_LOGIN_BUTTON));
		verifyElementIsPresent(driver, login);
		WebElement usernameTextbox=driver.findElement(By.xpath(OR.EMAIL_TEXTBOX_PROVIDER_URL));
		click(login);
		WebElement usernameRequiredMessage = driver.findElement(By.xpath(OR.USERNAME_REQUIRED_IN_LOGIN_PAGE));
		WebElement passwordRequiredMessage = driver.findElement(By.xpath(OR.PASSWORD_REQUIRED_IN_LOGIN_PAGE));
		verifyElementIsPresent(driver, usernameRequiredMessage);
		verifyElementIsPresent(driver, passwordRequiredMessage);
		sendKeys(usernameTextbox, Directory.PracticePA2username);
		click(login);
		verifyElementIsPresent(driver, passwordRequiredMessage);
		WebElement passwordTextbox= driver.findElement(By.xpath(OR.PASSWORD_TEXTBOX_PROVIDER_URL));
		sendKeys(passwordTextbox, Directory.PracticePA2password);
		click(login);
		waitForElement(driver, PROGRAM_ADMIN_PATIENTS_TAB);
		WebElement dashboardTab = driver.findElement(By.xpath(OR.PROGRAM_ADMIN_DASHBOARD_TAB));
		WebElement userId=driver.findElement(By.xpath(OR.PROGRAM_ADMIN_USER_ID_DISPLAY_RIGHT_CORNER));
		verifyElementIsPresent(driver, userId);
		verifyElementIsPresent(driver, dashboardTab);
	}
	/**
	 * Name :Abinaya.P
	 * Created Date:26/Oct/2016
	 * Modified Date:
	 * Description :Create a common method to get practice program admin which has upcoming sessions
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static String getPracticePgmAdmin(WebDriver driver,String sessionid) throws ParseException, ClassNotFoundException, SQLException
	{
		String mail="";
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
		ResultSet rs = stat.executeQuery("select P.ID, P.EMAIL AS MAIL, P.FIRST_NAME, P.LAST_NAME from PROVIDER P, MP_PROVIDER_APPROVED MPA where P.ID= MPA.PROVIDER_ID AND P.USER_ROLE_ID= '04' AND P.IS_ACTIVE= '1' AND P.EMAIL NOT LIKE '%api%' AND MPA.SESSION_TYPE_ID= '"+sessionid+"' AND MPA.WILL_DELIVER = '1' AND MPA.IS_APPROVED= '1'");
		System.out.println("query executed");
		while(rs.next())
		{
			mail= rs.getString("MAIL");
			System.out.println("Mail id "+mail+" is retrieved from the Table");
		}
		return mail;
	}
	/**
	 * Name         :   Leena P.
	 * Created Date :   06/Dec/16
	 * Description  :   Using VSPN provider "URL" login as a Kidney PA account with validation
	 * Required Inputs: Username and password.
	 */
	public static void programAdminLoginKidney(WebDriver driver)
	{
		Navigate.get(driver, Directory.Providerurl);
		Navigate.maximize(driver);
		wait(driver, "10");
		WebElement login = driver.findElement(By.xpath(OR.PROVIDER_LOGIN_BUTTON));
		verifyElementIsPresent(driver, login);
		WebElement usernameTextbox=driver.findElement(By.xpath(OR.EMAIL_TEXTBOX_PROVIDER_URL));
		click(login);
		wait(driver, "4");
		WebElement usernameRequiredMessage = driver.findElement(By.xpath(OR.USERNAME_REQUIRED_IN_LOGIN_PAGE));
		WebElement passwordRequiredMessage = driver.findElement(By.xpath(OR.PASSWORD_REQUIRED_IN_LOGIN_PAGE));
		verifyElementIsPresent(driver, usernameRequiredMessage);
		verifyElementIsPresent(driver, passwordRequiredMessage);
		sendKeys(usernameTextbox, Directory.ProgramadminKidneyusername);
		click(login);
		wait(driver, "4");
		verifyElementIsPresent(driver, passwordRequiredMessage);
		WebElement passwordTextbox= driver.findElement(By.xpath(OR.PASSWORD_TEXTBOX_PROVIDER_URL));
		sendKeys(passwordTextbox, Directory.Programadminpassword);
		click(login);
		wait(driver, "10");
		waitForElement(driver, PROGRAM_ADMIN_PATIENTS_TAB);
		WebElement dashboardTab = driver.findElement(By.xpath(OR.PROGRAM_ADMIN_DASHBOARD_TAB));
		WebElement userId=driver.findElement(By.xpath(OR.PROGRAM_ADMIN_USER_ID_DISPLAY_RIGHT_CORNER));
		verifyElementIsPresent(driver, userId);
		verifyElementIsPresent(driver, dashboardTab);
		WebElement patientTab=driver.findElement(By.xpath(OR.PROGRAM_ADMIN_PATIENTS_TAB));
		WebElement programsTab=driver.findElement(By.xpath(OR.PROGRAM_ADMIN_PROGRAMS_TAB));
		WebElement providersTab=driver.findElement(By.xpath(OR.PROGRAM_ADMIN_PROVIDERS_TAB));
		WebElement clientsTab=driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLIENTS_TAB));
		WebElement messagesTab=driver.findElement(By.xpath(OR.PROGRAM_ADMIN_MESSAGES_TAB));
		verifyElementIsPresent(driver, patientTab);
		verifyElementIsPresent(driver, programsTab);
		verifyElementIsPresent(driver, providersTab);
		verifyElementIsPresent(driver, clientsTab);
		verifyElementIsPresent(driver, messagesTab);
	}
	/**
	 * Name         :   Vinothkumar.M
	 * Created Date :   20/Dec/16
	 * Modified Date:
	 * Description  :   Using provider "URL"  PA account signout
	 */
	public static void programAdminKidneyLogout(WebDriver driver)
	{
		wait(driver, "5");
		WebElement signOutWayLink= driver.findElement(By.xpath(OR.PA_SETTING_IMAGE));
		wait(driver, "5");
		verifyElementIsPresent(driver, signOutWayLink);
		click(signOutWayLink);
		WebElement signOutLink= driver.findElement(By.xpath(OR.PA_SIGNOUT_LINK));
		click(signOutLink);
		wait(driver, "10");
		WebElement againSignInLink= driver.findElement(By.xpath(OR.SIGNIN_AGAIN_LINK));
		verifyElementIsPresent(driver, againSignInLink);
	}
	
	/**
	 * Name :      Vinothkumar.M
	 * Created Date:  26/Sep/16
	 * Modified Date:
	 * Description :   Create a common method for Login into Practice PA
	 * Ticket ID :
	 * Required Inputs :  Username and Password
	 */
	public static void practicePAURL(WebDriver driver)
	{
		Navigate.get(driver, Directory.Providerurl);
		Navigate.maximize(driver);
	}
}