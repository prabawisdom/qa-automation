package com.zillion.qa.opsadmin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.zillion.qa.commands.Manipulation;
import com.zillion.qa.commands.Navigate;
import com.zillion.qa.utils.Directory;
import com.zillion.qa.utils.Platform;
public class Providers extends Manipulation implements OR
{
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   03/02/2016
	 * Modified Date:
	 * Description :   Create a common method to Member verify lecture session
	 * Ticket ID :
	 * Required Inputs :
	 */
	public static String memberVerifyLectureSession(WebDriver driver)
	{
		try
		{
			WebElement saturdayText= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_OF_SATURDAY_TEXT));
			WebElement lectureSessionTiming= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_OF_LECTURE_SESSION_TIMING));
			verifyElementIsPresent(driver, saturdayText);
			verifyElementIsPresent(driver, lectureSessionTiming);
			WebElement signUpButton= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_FOLLOWING_SIGNUP));
			verifyElementIsPresent(driver, signUpButton);
			click(signUpButton);
			Navigate.waitTime(driver, "5");
			WebElement successfullPopUpText= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_SUCCESSFULL_POPUP_TEXT));
			WebElement successfullPopUpOkButton= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_SUCCESSFULL_POPUP_OK_BUTTON));
			verifyElementIsPresent(driver, successfullPopUpText);
			verifyElementIsPresent(driver, successfullPopUpOkButton);
			click(successfullPopUpOkButton);
		}
		catch(Exception e)
		{
			WebElement nextPageNavigator= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_NEXT_PAGE_NAVIGATOR));
			verifyElementIsPresent(driver, nextPageNavigator);
			click(nextPageNavigator);
			Navigate.waitTime(driver, "5");
		}
		try
		{
			WebElement saturdayText= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_OF_SATURDAY_TEXT));
			WebElement lectureSessionTiming= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_OF_LECTURE_SESSION_TIMING));
			verifyElementIsPresent(driver, saturdayText);
			verifyElementIsPresent(driver, lectureSessionTiming);
			WebElement signUpButton= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_FOLLOWING_SIGNUP));
			verifyElementIsPresent(driver, signUpButton);
			click(signUpButton);
			Navigate.waitTime(driver, "5");
			WebElement successfullPopUpText= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_SUCCESSFULL_POPUP_TEXT));
			WebElement successfullPopUpOkButton= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_SUCCESSFULL_POPUP_OK_BUTTON));
			verifyElementIsPresent(driver, successfullPopUpText);
			verifyElementIsPresent(driver, successfullPopUpOkButton);
			click(successfullPopUpOkButton);
		}
		catch(Exception e)
		{
			WebElement nextPageNavigator= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_NEXT_PAGE_NAVIGATOR));
			verifyElementIsPresent(driver, nextPageNavigator);
			click(nextPageNavigator);
			Navigate.waitTime(driver, "5");
		}
		try
		{
			WebElement saturdayText= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_OF_SATURDAY_TEXT));
			WebElement lectureSessionTiming= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_OF_LECTURE_SESSION_TIMING));
			verifyElementIsPresent(driver, saturdayText);
			verifyElementIsPresent(driver, lectureSessionTiming);
			WebElement signUpButton= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_FOLLOWING_SIGNUP));
			verifyElementIsPresent(driver, signUpButton);
			click(signUpButton);
			Navigate.waitTime(driver, "5");
			WebElement successfullPopUpText= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_SUCCESSFULL_POPUP_TEXT));
			WebElement successfullPopUpOkButton= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_SUCCESSFULL_POPUP_OK_BUTTON));
			verifyElementIsPresent(driver, successfullPopUpText);
			verifyElementIsPresent(driver, successfullPopUpOkButton);
			click(successfullPopUpOkButton);
		}
		catch(Exception e)
		{
			WebElement nextPageNavigator= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_NEXT_PAGE_NAVIGATOR));
			verifyElementIsPresent(driver, nextPageNavigator);
			click(nextPageNavigator);
			Navigate.waitTime(driver, "5");
		}
		try
		{
			WebElement saturdayText= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_OF_SATURDAY_TEXT));
			WebElement lectureSessionTiming= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_OF_LECTURE_SESSION_TIMING));
			verifyElementIsPresent(driver, saturdayText);
			verifyElementIsPresent(driver, lectureSessionTiming);
			WebElement signUpButton= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_FOLLOWING_SIGNUP));
			verifyElementIsPresent(driver, signUpButton);
			click(signUpButton);
			Navigate.waitTime(driver, "5");
			WebElement successfullPopUpText= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_SUCCESSFULL_POPUP_TEXT));
			WebElement successfullPopUpOkButton= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_SUCCESSFULL_POPUP_OK_BUTTON));
			verifyElementIsPresent(driver, successfullPopUpText);
			verifyElementIsPresent(driver, successfullPopUpOkButton);
			click(successfullPopUpOkButton);
		}
		catch(Exception e)
		{
			WebElement nextPageNavigator= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_NEXT_PAGE_NAVIGATOR));
			verifyElementIsPresent(driver, nextPageNavigator);
			click(nextPageNavigator);
			Navigate.waitTime(driver, "5");
		}
		try
		{
			WebElement saturdayText= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_OF_SATURDAY_TEXT));
			WebElement lectureSessionTiming= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_OF_LECTURE_SESSION_TIMING));
			verifyElementIsPresent(driver, saturdayText);
			verifyElementIsPresent(driver, lectureSessionTiming);
			WebElement signUpButton= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_FOLLOWING_SIGNUP));
			verifyElementIsPresent(driver, signUpButton);
			click(signUpButton);
			Navigate.waitTime(driver, "5");
			WebElement successfullPopUpText= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_SUCCESSFULL_POPUP_TEXT));
			WebElement successfullPopUpOkButton= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_SUCCESSFULL_POPUP_OK_BUTTON));
			verifyElementIsPresent(driver, successfullPopUpText);
			verifyElementIsPresent(driver, successfullPopUpOkButton);
			click(successfullPopUpOkButton);
		}
		catch(Exception e)
		{
			WebElement nextPageNavigator= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_NEXT_PAGE_NAVIGATOR));
			verifyElementIsPresent(driver, nextPageNavigator);
			click(nextPageNavigator);
			Navigate.waitTime(driver, "5");
		}
		try
		{
			WebElement saturdayText= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_OF_SATURDAY_TEXT));
			WebElement lectureSessionTiming= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_OF_LECTURE_SESSION_TIMING));
			verifyElementIsPresent(driver, saturdayText);
			verifyElementIsPresent(driver, lectureSessionTiming);
			WebElement signUpButton= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_FOLLOWING_SIGNUP));
			verifyElementIsPresent(driver, signUpButton);
			click(signUpButton);
			Navigate.waitTime(driver, "5");
			WebElement successfullPopUpText= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_SUCCESSFULL_POPUP_TEXT));
			WebElement successfullPopUpOkButton= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_SUCCESSFULL_POPUP_OK_BUTTON));
			verifyElementIsPresent(driver, successfullPopUpText);
			verifyElementIsPresent(driver, successfullPopUpOkButton);
			click(successfullPopUpOkButton);
		}
		catch(Exception e)
		{
			WebElement nextPageNavigator= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_NEXT_PAGE_NAVIGATOR));
			verifyElementIsPresent(driver, nextPageNavigator);
			click(nextPageNavigator);
			Navigate.waitTime(driver, "5");
		}
		try
		{
			WebElement saturdayText= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_OF_SATURDAY_TEXT));
			WebElement lectureSessionTiming= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_OF_LECTURE_SESSION_TIMING));
			verifyElementIsPresent(driver, saturdayText);
			verifyElementIsPresent(driver, lectureSessionTiming);
			WebElement signUpButton= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_FOLLOWING_SIGNUP));
			verifyElementIsPresent(driver, signUpButton);
			click(signUpButton);
			Navigate.waitTime(driver, "5");
			WebElement successfullPopUpText= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_SUCCESSFULL_POPUP_TEXT));
			WebElement successfullPopUpOkButton= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_SUCCESSFULL_POPUP_OK_BUTTON));
			verifyElementIsPresent(driver, successfullPopUpText);
			verifyElementIsPresent(driver, successfullPopUpOkButton);
			click(successfullPopUpOkButton);
		}
		catch(Exception e)
		{
			WebElement nextPageNavigator= driver.findElement(By.xpath(OR.MEMBER_VIEW_SESSION_NEXT_PAGE_NAVIGATOR));
			verifyElementIsPresent(driver, nextPageNavigator);
			click(nextPageNavigator);
			Navigate.waitTime(driver, "5");
		}
		return "Lecture session is verified and Signed up from Member";
	}
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   03/02/2016
	 * Modified Date:
	 * Description :   Create a common method to Timeslot Search
	 * Ticket ID :
	 * Required Inputs :
	 */
	public static String timeSlotSearch(WebDriver driver)
	{
		try
		{
			WebElement saturdayHeader= driver.findElement(By.xpath(OR.TIME_SLOT_SATURDAY_HEADER));
			WebElement saturdayEveningButton= driver.findElement(By.xpath(OR.TIME_SLOT_SATURDAY_EVENING_BUTTON));
			verifyElementIsPresent(driver, saturdayHeader);
			verifyElementIsPresent(driver, saturdayEveningButton);
		}
		catch(Exception e)
		{
			WebElement nextPageNavigator= driver.findElement(By.xpath(OR.TIME_SLOT_NEXT_PAGE_NAVIGATOR));
			verifyElementIsPresent(driver, nextPageNavigator);
			click(nextPageNavigator);
			Navigate.waitTime(driver, "5");
		}
		try
		{
			WebElement saturdayHeader= driver.findElement(By.xpath(OR.TIME_SLOT_SATURDAY_HEADER));
			WebElement saturdayEveningButton= driver.findElement(By.xpath(OR.TIME_SLOT_SATURDAY_EVENING_BUTTON));
			verifyElementIsPresent(driver, saturdayHeader);
			verifyElementIsPresent(driver, saturdayEveningButton);
		}
		catch(Exception e)
		{
			WebElement nextPageNavigator= driver.findElement(By.xpath(OR.TIME_SLOT_NEXT_PAGE_NAVIGATOR));
			verifyElementIsPresent(driver, nextPageNavigator);
			click(nextPageNavigator);
			Navigate.waitTime(driver, "5");
		}
		return "Timeslot should be searchedin the view session";
	}
	/**
	 * Name :     Abinaya.P
	 * Created Date:   27/09/2016
	 * Modified Date:
	 * Description :   Create a common method to click appropraite coach
	 * Ticket ID :
	 * Required Inputs :
	 */
	public static void clickOnAppropriateProvider(WebDriver driver,String data)
	{
		String provider="//a[text()='"+data+"']/parent::td/following-sibling::td[4]//span";
		waitForElement(driver, provider);
		jsClickByXPath(driver, provider);
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   06/Jun/16
	 * Modified Date:
	 * Description :   Create a common method to retrieve mail id using first name and last name in the database
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String retrieveMailWithNameOfPrv(WebDriver driver, String name) throws ClassNotFoundException, SQLException
	{
		String mail ="";
		String[]names=name.split("\\s+");
		System.out.println("name"+name);
		System.out.println("names[0]"+names[0]);
		System.out.println("names[1]"+names[1]);
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
		ResultSet rs = stat.executeQuery("select email from provider where first_name='"+names[0]+"' and last_name='"+names[1]+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			mail = rs.getString("email");
			System.out.println(mail);
		}
		return mail;
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   11/Nov/16
	 * Modified Date:
	 * Description :   Create a common method to choose organization while creating provider
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void chooseOrgForProv(WebDriver driver)
	{
		WebElement org= driver.findElement(By.xpath(OR.OPS_ADMIN_PROVIDERS_TAB_ORG_TEXTBOX));
		selectByVisibletext(org, Directory.RA_Prov_Org);
	}
	/**
	 * Name :     LEENA P.
	 * Created Date:   17/Nov/16
	 * Modified Date:
	 * Description :   Create a common method to create a RA provider
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String Email1 = "";
	public static String createRAProvider(WebDriver driver, String testData)
	{
		try{
			String provider=testData;
			WebElement providersTab = driver.findElement(By.xpath(OR.PROVIDERS_TAB));
			click(providersTab);
			Manipulation.wait(driver, "15");
			WebElement addNewProvider = driver.findElement(By.xpath(OR.ADD_NEW_PROVIDER));
			click(addNewProvider);
			Manipulation.wait(driver, "5");
			WebElement addNewProviderPage = driver.findElement(By.xpath(OR.ADD_NEW_PROVIDER_PAGE));
			verifyElementIsPresent(driver, addNewProviderPage);
			WebElement orgName = driver.findElement(By.xpath(OR.ORGANIZATION_NAME));
			orgName.click();
			Manipulation.wait(driver, "3");
			Manipulation.selectByValue(orgName,"02");
			WebElement roleName = driver.findElement(By.xpath(OR.ROLE_NAME));
			Manipulation.selectByVisibletext(roleName, provider);
			WebElement firstName = driver.findElement(By.xpath(OR.FIRST_NAME));
			String fName = dynamicSendkeys(driver, "", firstName);
			driver.findElement(By.id(OR.PROVIDER_FIRST_NAME)).sendKeys(fName);
			driver.findElement(By.id(OR.PROVIDER_LAST_NAME)).sendKeys("RA");
			driver.findElement(By.id(OR.PROVIDER_PHONE)).sendKeys("1111111111");
			driver.findElement(By.id(OR.PROVIDER_STREET)).sendKeys("501 merrit 7");
			driver.findElement(By.id(OR.PROVIDER_CITY)).sendKeys("Norwalk");
			WebElement state = driver.findElement(By.id(OR.PROVIDER_STATE));
			selectByValue(state,"string:CT");
			driver.findElement(By.id("postalCode")).sendKeys("06851");
			driver.findElement(By.id("nickname")).sendKeys(provider);
			driver.findElement(By.id("genderMale")).click();
			WebElement month = driver.findElement(By.id(OR.PROVIDER_DOB_MONTH));
			selectByValue(month,"string:01");
			WebElement day = driver.findElement(By.id(OR.PROVIDER_DOB));
			selectByValue(day,"string:01");
			WebElement year = driver.findElement(By.id(OR.PROVIDER_DOB_YEAR));
			selectByValue(year,"number:1988");
			WebElement email = driver.findElement(By.xpath(OR.EMAIL));
			String Email1 = dynamicSendkeys(driver, "qa@yopmail.com", email);
			System.out.println("Email is : " +Email1);
			WebElement emailTextBox = driver.findElement(By.id("email"));
			Manipulation.clearAndType(emailTextBox,Email1);
			WebElement bio = driver.findElement(By.xpath(OR.BIO));
			sendKeys(bio,Email1);
			driver.findElement(By.id(OR.PROVIDER_CREATE_SAVE_BTN)).click();
			wait(driver, "5");
			System.out.println("Coach created");
			Manipulation.waitForElement(driver, OR.STATUS_EDIT);
			jsClickByXPath(driver, OR.STATUS_EDIT);
			wait(driver, "2");
			driver.findElement(By.id(OR.PROVIDER_ACTIVATE_BTN)).click();
			wait(driver, "3");
			driver.findElement(By.id(OR.PROVIDER_SUBMIT_STATUS)).click();
			wait(driver, "3");
			WebElement statusEditText = driver.findElement(By.xpath(OR.SET_PROVIDER_ACTIVE_TEXT));
			verifyElementIsPresent(driver, statusEditText);
			WebElement continueEdit = driver.findElement(By.xpath(OR.CONTINUE_BUTTON));
			click(continueEdit);
			wait(driver, "3");
			WebElement successOK = driver.findElement(By.xpath(OR.PROVIDER_ADDED_SUCCESSFULLY_OK));
			click(successOK);
			com.zillion.qa.programadmin.Dashboard.programAdminLogout(driver);
			Navigate.get(driver, Directory.yopmailurl);
			Navigate.maximize(driver);
			WebElement yopEmailTextbox= driver.findElement(By.xpath(OR.PARTITIONER_YOP_EMAIL_TEXTBOX));
			verifyElementIsPresent(driver, yopEmailTextbox);
			clear(yopEmailTextbox);
			sendKeys(yopEmailTextbox, Email1);
			WebElement checkInbox= driver.findElement(By.xpath(OR.PARTITIONER_YOP_EMAIL_CHECKBOX));
			verifyElementIsPresent(driver, checkInbox);
			jsClickByXPath(driver, OR.PARTITIONER_YOP_EMAIL_CHECKBOX);
			wait(driver, "5");
			WebElement iframe= driver.findElement(By.xpath(OR.PARTITIONER_YOP_EMAIL_IFRAME));
			Navigate.switchToFrame(driver, iframe);
			WebElement generatedLink= driver.findElement(By.xpath(OR.PROVIDER_YOP_EMAIL_GENERATED_LINK));
			verifyElementIsPresent(driver, generatedLink);
			Manipulation.getwindowandclose(driver, generatedLink);
			wait(driver, "5");
			WebElement activationPwd= driver.findElement(By.xpath(OR.PROVIDER_ACTIVATION_PASSWORD));
			sendKeys(activationPwd, "Healthfleet2015");
			WebElement activationReenterPwd= driver.findElement(By.xpath(OR.PROVIDER_ACTIVATION_REENTER_PASSWORD));
			sendKeys(activationReenterPwd, "Healthfleet2015");
			WebElement activationSubmitBtn= driver.findElement(By.xpath(OR.PROVIDER_ACTIVATION_SUBMIT_BUTTON));
			click(activationSubmitBtn);
			wait(driver, "5");
			if (Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
			String providerDetails = fName+","+Email1;
			return providerDetails;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	/**
	 * Name         :   Leena P.
	 * Created Date :   22/Nov/16
	 * Modified Date:   22/Nov/16
	 * Description  :   Yopmail
	 */
	public static void yopMailActivation(WebDriver driver, String link, String generatedEmail)
	{
		String yopmailLinks[] = link.split(",") ;
		String yopmailInbox = yopmailLinks[0];
		String yopmailEmail = yopmailLinks[1];
		Navigate.get(driver, Directory.yopmailurl);
		Navigate.maximize(driver);
		wait(driver, "5");
		WebElement yopEmailTextbox= driver.findElement(By.xpath(OR.PARTITIONER_YOP_EMAIL_TEXTBOX));
		verifyElementIsPresent(driver, yopEmailTextbox);
		Manipulation.clearAndType(yopEmailTextbox,generatedEmail);
		wait(driver, "5");
		Manipulation.mouseOver(driver, yopEmailTextbox);
		WebElement checkInbox= driver.findElement(By.xpath(OR.PARTITIONER_YOP_EMAIL_CHECKBOX));
		Manipulation.elementTobeClickable(driver,checkInbox);
		click(checkInbox);
		wait(driver, "5");
		WebElement iframeInbox= driver.findElement(By.xpath(OR.YOPMAIL_IFRAME_INBOX));
		Navigate.switchToFrame(driver, iframeInbox);
		String generatedLinkInbox = OR.RA_YOPMAIL_GENERATED_LINK_INBOX+yopmailInbox+RA_YOPMAIL_GENERATED_LINK_INBOX_2;
		WebElement generatedLink= driver.findElement(By.xpath(generatedLinkInbox));
		verifyElementIsPresent(driver, generatedLink);
		Navigate.switchToDefaultFrame(driver);
		WebElement iframe= driver.findElement(By.xpath(OR.PARTITIONER_YOP_EMAIL_IFRAME));
		Navigate.switchToFrame(driver, iframe);
		String generatedLinkEmail = OR.RA_PROVIDER_YOP_EMAIL_GENERATED_LINK+yopmailEmail+RA_YOPMAIL_GENERATED_LINK_INBOX_2;
		WebElement generatedLinkEmail1= driver.findElement(By.xpath(generatedLinkEmail));
		verifyElementIsPresent(driver, generatedLinkEmail1);
		Manipulation.getwindowandclose(driver, generatedLinkEmail1);
		wait(driver, "5");
		if (Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
		{
			driver.get("javascript:document.getElementById('overridelink').click();");
			wait(driver, "5");
		}
	}
}