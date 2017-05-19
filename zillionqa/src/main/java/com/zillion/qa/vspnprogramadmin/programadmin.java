package com.zillion.qa.vspnprogramadmin;

import java.awt.AWTException;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.zillion.qa.commands.Manipulation;
import com.zillion.qa.commands.Navigate;
import com.zillion.qa.utils.Directory;
import com.zillion.qa.utils.Platform;

public class programadmin extends Manipulation implements OR
{

	/**
	 * Name         :   Vinoth Kumar.M
	 * Created Date :   22/12/16 
	 * Description  :   VSPN Add New patient
	 */
	public static String Email="";
	public static String vspnPAAddNewPatient(WebDriver driver, String testData) throws ClassNotFoundException, SQLException, AWTException 
	{
		wait(driver, "5");
		String[] testData1 = testData.split(",");
		String inputFirstName=testData1[0];
		String inputLastName=testData1[1];
		String inputMonth=testData1[2];
		String inputDay=testData1[3];
		String inputLesserthan18Year=testData1[4];
		String inputYear=testData1[5];
		String inputOrganization=testData1[6];
		String inputprogram=testData1[7];
		String inputphysician=testData1[8];
		Navigate.waitTime(driver, "10");
		WebElement patienttab= driver.findElement(By.xpath(OR.PRACTITIONER_LOGIN_PATIENT_TAB_TEXT_REF));
		click(patienttab);
		wait(driver, "1");
		WebElement addPatient= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_BUTTON));
		verifyElementIsPresent(driver, addPatient);
		click(addPatient);
		WebElement addPatientHeader= driver.findElement(By.xpath(OR.ADD_PATIENT_TEXT_INLEFT_CORNER));
		verifyElementIsPresent(driver, addPatientHeader);
		WebElement firstnameLabel= driver.findElement(By.xpath(OR.FIRSTNAME_LABEL));
		WebElement lastnameLabel= driver.findElement(By.xpath(OR.LASTNAME_LABEL));
		WebElement emailnameLabel= driver.findElement(By.xpath(OR.EMAIL_LABEL));
		WebElement confirmEmailLabel= driver.findElement(By.xpath(OR.CONFIRMEMAIL_LABEL));
		WebElement bateOfBirthLabel= driver.findElement(By.xpath(OR.DOB_LABEL));
		WebElement organizationLabel= driver.findElement(By.xpath(OR.ORGANIZATION_LABEL));
		verifyElementIsPresent(driver, firstnameLabel);
		verifyElementIsPresent(driver, lastnameLabel);
		verifyElementIsPresent(driver, emailnameLabel);
		verifyElementIsPresent(driver, confirmEmailLabel);
		verifyElementIsPresent(driver, bateOfBirthLabel);
		verifyElementIsPresent(driver, organizationLabel);
		WebElement addPatientLastLine= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_LAST_STAGE));
		click(addPatientLastLine);
		WebElement requiredMessageOne= driver.findElement(By.xpath(OR.PROVIDER_URL_ALERTMSG_1STNAME_REQUIRED));
		WebElement requiredMessage2= driver.findElement(By.xpath(OR.PROVIDER_URL_ALERTMSG_LASTNAME_REQUIRED));
		WebElement requiredMessage3= driver.findElement(By.xpath(OR.PROVIDER_URL_ALERTMSG_EMAIL_REQUIRED));
		WebElement requiredMessage4= driver.findElement(By.xpath(OR.PROVIDER_URL_ALERTMSG_CONFIRMEMAIL_REQUIRED));
		WebElement requiredMessage5= driver.findElement(By.xpath(OR.PROVIDER_URL_ALERTMSG_MONTH_REQUIRED));
		WebElement requiredMessage6= driver.findElement(By.xpath(OR.PROVIDER_URL_ALERTMSG_DATE_REQUIRED));
		WebElement requiredMessage7= driver.findElement(By.xpath(OR.PROVIDER_URL_ALERTMSG_YEAR_REQUIRED));
		verifyElementIsPresent(driver, requiredMessageOne);
		verifyElementIsPresent(driver, requiredMessage2);
		verifyElementIsPresent(driver, requiredMessage3);
		verifyElementIsPresent(driver, requiredMessage4);
		verifyElementIsPresent(driver, requiredMessage5);
		verifyElementIsPresent(driver, requiredMessage6);
		verifyElementIsPresent(driver, requiredMessage7);
		WebElement closeMark= driver.findElement(By.xpath(OR.CLOSE_ICON));
		verifyElementIsPresent(driver, closeMark);
		WebElement firstName= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_FIRSTNAME_TEXTBOX));
		WebElement lastName= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_LASTNAME_TEXTBOX));
		WebElement email= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_EMAIL_TEXTBOX));
		WebElement confirmEmail= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_CONFIRMEMAIL_TEXTBOX));
		WebElement monthAdd= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_ADD_MONTH_FIELD));
		WebElement dayAdd= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_ADD_DAY_FIELD));
		WebElement yearAdd= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_ADD_YEAR_FIELD));
		sendKeys(firstName, inputFirstName);
		dynamicSendkeys(driver, inputLastName, lastName);
		WebElement emailNotMatch= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_EMAIL_ADDRESS_NOTMATCH_MSG));
		Email = dynamicSendkeys(driver,"pa@guerrillamail.com", email);
		dynamicSendkeys(driver, "pa", confirmEmail);
		verifyElementIsPresent(driver, emailNotMatch);
		clear(confirmEmail);
		sendKeys(confirmEmail,Email);
		System.out.println("GuerrillaMail"+Email);
		selectByValue(monthAdd,inputMonth);
		selectByValue(dayAdd, inputDay);
		selectByValue(yearAdd, inputLesserthan18Year);
		click(addPatientLastLine);
		wait(driver, "1");
		WebElement organization= driver.findElement(By.xpath(OR.ORGANIZATION));
		selectByVisibletext(organization, inputOrganization);
		wait(driver, "1");
		Navigate.waitTime(driver, "5");
		WebElement physicianIsRequiredAlertMsg= driver.findElement(By.xpath(OR.PROVIDER_PHYSICIAN_IS_REQUIRED_ERROR_MSG));
		verifyElementIsPresent(driver, physicianIsRequiredAlertMsg);
		WebElement physician= driver.findElement(By.xpath(OR.PROVIDER_PHYSICIAN_DROPDOWN));
		selectByVisibletext(physician, inputphysician);
		wait(driver, "1");
		Navigate.robotTab(driver);
		Navigate.waitTime( driver, "10" );
		WebElement program= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_PROGRAM_FIELD));
		selectByVisibletext(program, inputprogram);
		wait(driver, "1");       
		Navigate.waitTime( driver, "10" );
		click(addPatientLastLine);
		Navigate.waitTime(driver, "5");
		wait(driver, "1");      
		selectByValue(yearAdd, inputYear);
		Navigate.waitTime(driver, "1");
		click(addPatientLastLine);
		wait(driver, "5");  
		jsClickByXPath(driver, OR.PATIENT_HAS_BEEN_ADDED_SUCCESSFULLY_OK_BUTTON);
		wait(driver, "2");
		return Email;
	}

	/**
	 * Name         :   Vinothkumar.M
	 * Created Date :   22/12/16
	 * Modified Date:   
	 * Description  :  GuerrillaMail
	 * Vulcan>Member portal>Enrollment/Onboarding
	 */
	public static void vspnguerrillaMail(WebDriver driver)
	{
		Navigate.get(driver, Directory.Guerrillamailurl);
		Navigate.maximize(driver);
		WebElement Guerrillamaildropdown= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_DROPDOWN));
		selectByValue(Guerrillamaildropdown, "guerrillamail.com");
		wait(driver, "3");
		WebElement GuerrillaMailEditButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_EDIT_BUTTON));
		click(GuerrillaMailEditButton);
		waitForElement(driver, GUERRILLA_MAIL_TEXTBOX);
		WebElement GuerrillaMailTextbox= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_TEXTBOX));
		clearAndType(GuerrillaMailTextbox, Email);
		WebElement GuerrillaMailSetButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_SET_BUTTON));
		wait(driver, "3");
		click(GuerrillaMailSetButton);
		wait(driver, "5");
		try{         
			if(driver.findElement( By.xpath( GUERRILLA_MAIL_WELCOME_MESSAGE_VSPN ) ).isDisplayed())
			{
				waitForElement(driver, GUERRILLA_MAIL_WELCOME_MESSAGE_VSPN);
			}
			else
			{
				driver.navigate().refresh();
				wait( driver, "3" );            
				if(driver.findElement( By.xpath( GUERRILLA_MAIL_WELCOME_MESSAGE_VSPN ) ).isDisplayed())
				{
					waitForElement(driver, GUERRILLA_MAIL_WELCOME_MESSAGE_VSPN);
				}
				else
				{
					driver.navigate().refresh();
					wait( driver, "5" );
					waitForElement(driver, GUERRILLA_MAIL_WELCOME_MESSAGE_VSPN);
				}
			}
		}
		catch(Exception e)
		{
			driver.navigate().refresh();
			wait( driver, "3" );
			waitForElement(driver, GUERRILLA_MAIL_WELCOME_MESSAGE_VSPN);         
		}
		WebElement GuerrillaMailWelcomeMessage= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_WELCOME_MESSAGE_VSPN));
		verifyElementIsPresent(driver, GuerrillaMailWelcomeMessage);
		click(GuerrillaMailWelcomeMessage);
		wait(driver, "10");
		WebElement generatedLink= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_GENERATED_LINK_VSPN));
		waitForElement(driver, GUERRILLA_MAIL_GENERATED_LINK_VSPN);
		verifyElementIsPresent(driver, generatedLink);
		getwindowandclose(driver, generatedLink); 
		wait(driver, "10");
		
		if (Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
		{
			driver.get("javascript:document.getElementById('overridelink').click();"); 
			wait(driver, "5");
		}

	}
	/**
	 * Name         :   Vinothkumar.M
	 * Created Date :   22/12/16
	 * Modified Date:   
	 * Description  :  To verify Registration complete mail
	 *
	 */
	public static void vspnGuerrillaMailRegistrationCompleteLink(WebDriver driver)
	{
		Navigate.get(driver, Directory.Guerrillamailurl);
		Navigate.maximize(driver);
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
		try{         
			if(driver.findElement( By.xpath( GUERRILLA_MAIL_COMPLETE_LINK ) ).isDisplayed())
			{
				waitForElement(driver, GUERRILLA_MAIL_COMPLETE_LINK);
			}
			else
			{
				driver.navigate().refresh();
				wait( driver, "3" );            
				if(driver.findElement( By.xpath( GUERRILLA_MAIL_COMPLETE_LINK ) ).isDisplayed())
				{
					waitForElement(driver, GUERRILLA_MAIL_COMPLETE_LINK);
				}
				else
				{
					driver.navigate().refresh();
					wait( driver, "3" );
					waitForElement(driver, GUERRILLA_MAIL_COMPLETE_LINK);
				}
			}
		}
		catch(Exception e)
		{
			driver.navigate().refresh();
			wait( driver, "10" );
			waitForElement(driver, GUERRILLA_MAIL_COMPLETE_LINK);         
		}
		WebElement GuerrillaMailCompleteLink= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_COMPLETE_LINK));
		verifyElementIsPresent(driver, GuerrillaMailCompleteLink);
	}
	
	/**
	 * Name         :   Vinothkumar.M
	 * Created Date :   22/12/16
	 * Modified Date:   
	 * Description  :  To verify VSPN Yopmail
	 *
	 */

	public static void  vspnyopmail(WebDriver driver)
	{
		Navigate.get(driver, Directory.yopmailurl);
		Navigate.maximize(driver);
		WebElement yopEmailTextbox= driver.findElement(By.xpath(OR.PARTITIONER_YOP_EMAIL_TEXTBOX));
		verifyElementIsPresent(driver, yopEmailTextbox);
		clear(yopEmailTextbox);
		sendKeys(yopEmailTextbox, Email);
		WebElement checkInbox= driver.findElement(By.xpath(OR.PARTITIONER_YOP_EMAIL_CHECKBOX));
		verifyElementIsPresent(driver, checkInbox);
		jsClickByXPath(driver, OR.PARTITIONER_YOP_EMAIL_CHECKBOX);
		wait(driver, "5");
		WebElement iframe= driver.findElement(By.xpath(OR.PARTITIONER_YOP_EMAIL_IFRAME));
		Navigate.switchToFrame(driver, iframe);
		wait(driver, "2");
		WebElement generatedLink= driver.findElement(By.xpath(OR.PARTITIONER_YOP_EMAIL_GENERATED_LINK));
		verifyElementIsPresent(driver, generatedLink);
		getwindowandclose(driver, generatedLink); 
		wait(driver, "5");
		if (Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
		{
			driver.get("javascript:document.getElementById('overridelink').click();"); 
			wait(driver, "5");
		}
	}
	
	/**
	 * Name         :   Vinothkumar.M
	 * Created Date :   22/12/16
	 * Modified Date:   
	 * Description  :  To verify schedule 1on1 Email
	 *
	 */
	public static void verify1on1email(WebDriver driver, String mailid, String email) 
	{		
		
		if(mailid.equalsIgnoreCase("yopmail"))
		{
			Navigate.get(driver, Directory.yopmailurl);
			Navigate.maximize(driver);
			WebElement yopEmailTextbox= driver.findElement(By.xpath(OR.PARTITIONER_YOP_EMAIL_TEXTBOX));
			verifyElementIsPresent(driver, yopEmailTextbox);
			clear(yopEmailTextbox);
			sendKeys(yopEmailTextbox, email);
			WebElement checkInbox= driver.findElement(By.xpath(OR.PARTITIONER_YOP_EMAIL_CHECKBOX));
			verifyElementIsPresent(driver, checkInbox);
			jsClickByXPath(driver, OR.PARTITIONER_YOP_EMAIL_CHECKBOX);
			wait(driver, "5");
			WebElement iframe= driver.findElement(By.xpath(OR.PARTITIONER_YOP_EMAIL_IFRAME));
			Navigate.switchToFrame(driver, iframe);
			wait(driver, "5");
			WebElement Notification= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_1ON1_SCHEDULED_LINK_CONTENT));
			verifyElementIsPresent(driver, Notification);
			if (Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();"); 
				wait(driver, "5");
				verifyElementIsPresent(driver, Notification);
			}
		}			
		else
		{
			Navigate.get(driver, Directory.Guerrillamailurl);
			Navigate.maximize(driver);
			WebElement Guerrillamaildropdown= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_DROPDOWN));
			selectByValue(Guerrillamaildropdown, "guerrillamail.com");
			wait(driver, "3");
			WebElement GuerrillaMailEditButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_EDIT_BUTTON));
			click(GuerrillaMailEditButton);
			waitForElement(driver, GUERRILLA_MAIL_TEXTBOX);
			WebElement GuerrillaMailTextbox= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_TEXTBOX));
			clearAndType(GuerrillaMailTextbox, email);
			WebElement GuerrillaMailSetButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_SET_BUTTON));
			wait(driver, "3");
			click(GuerrillaMailSetButton);
			wait(driver, "5");
			try{         
				if(driver.findElement( By.xpath( GUERRILLA_MAIL_1ON1_SCHEDULED_LINK ) ).isDisplayed())
				{
					click(GuerrillaMailSetButton);
					WebElement Vrifymessage= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_1ON1_SCHEDULED_LINK_CONTENT));
					verifyElementIsPresent(driver, Vrifymessage);
				}
				else
				{
					driver.navigate().refresh();
					wait( driver, "3" );            
					if(driver.findElement( By.xpath( GUERRILLA_MAIL_WELCOME_MESSAGE_VSPN ) ).isDisplayed())
					{
						click(GuerrillaMailSetButton);
						WebElement Vrifymessage= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_1ON1_SCHEDULED_LINK_CONTENT));
						verifyElementIsPresent(driver, Vrifymessage);
					}
					else
					{
						driver.navigate().refresh();
						wait( driver, "5" );
						click(GuerrillaMailSetButton);
						WebElement Vrifymessage= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_1ON1_SCHEDULED_LINK_CONTENT));
						verifyElementIsPresent(driver, Vrifymessage);
					}
				}
			}
			catch(Exception e)
			{
				driver.navigate().refresh();
				wait( driver, "3" );
				waitForElement(driver, GUERRILLA_MAIL_1ON1_SCHEDULED_LINK);         
			}
			
			if (Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();"); 
				wait(driver, "5");
			}
		}
	}
	
	public static void verifypatienjourney(WebDriver driver, String mailid, String email) 
	{		
		if(mailid.equalsIgnoreCase("yopmail"))
		{
			Navigate.get(driver, Directory.yopmailurl);
			Navigate.maximize(driver);
			WebElement yopEmailTextbox= driver.findElement(By.xpath(OR.PARTITIONER_YOP_EMAIL_TEXTBOX));
			verifyElementIsPresent(driver, yopEmailTextbox);
			clear(yopEmailTextbox);
			sendKeys(yopEmailTextbox, email);
			WebElement checkInbox= driver.findElement(By.xpath(OR.PARTITIONER_YOP_EMAIL_CHECKBOX));
			verifyElementIsPresent(driver, checkInbox);
			jsClickByXPath(driver, OR.PARTITIONER_YOP_EMAIL_CHECKBOX);
			wait(driver, "5");
			WebElement iframe= driver.findElement(By.xpath(OR.PARTITIONER_YOP_EMAIL_IFRAME));
			Navigate.switchToFrame(driver, iframe);
			wait(driver, "2");
			WebElement generatedLink= driver.findElement(By.xpath(OR.PARTITIONER_YOP_EMAIL_GENERATED_LINK));
			verifyElementIsPresent(driver, generatedLink);
			getwindowandclose(driver, generatedLink); 
			wait(driver, "5");
			if (Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();"); 
				wait(driver, "5");
			}
		}			
		else if(mailid.contains("guerrillamail"))
		{
			Navigate.get(driver, Directory.Guerrillamailurl);
			Navigate.maximize(driver);
			WebElement Guerrillamaildropdown= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_DROPDOWN));
			selectByValue(Guerrillamaildropdown, "guerrillamail.com");
			wait(driver, "3");
			WebElement GuerrillaMailEditButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_EDIT_BUTTON));
			click(GuerrillaMailEditButton);
			waitForElement(driver, GUERRILLA_MAIL_TEXTBOX);
			WebElement GuerrillaMailTextbox= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_TEXTBOX));
			clearAndType(GuerrillaMailTextbox, email);
			WebElement GuerrillaMailSetButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_SET_BUTTON));
			wait(driver, "3");
			click(GuerrillaMailSetButton);
			wait(driver, "5");
			try{         
				if(driver.findElement( By.xpath( GUERRILLA_MAIL_WELCOME_MESSAGE_VSPN ) ).isDisplayed())
				{
					waitForElement(driver, GUERRILLA_MAIL_WELCOME_MESSAGE_VSPN);
				}
				else
				{
					driver.navigate().refresh();
					wait( driver, "3" );            
					if(driver.findElement( By.xpath( GUERRILLA_MAIL_WELCOME_MESSAGE_VSPN ) ).isDisplayed())
					{
						waitForElement(driver, GUERRILLA_MAIL_WELCOME_MESSAGE_VSPN);
					}
					else
					{
						driver.navigate().refresh();
						wait( driver, "5" );
						waitForElement(driver, GUERRILLA_MAIL_WELCOME_MESSAGE_VSPN);
					}
				}
			}
			catch(Exception e)
			{
				driver.navigate().refresh();
				wait( driver, "3" );
				waitForElement(driver, GUERRILLA_MAIL_WELCOME_MESSAGE_VSPN);         
			}
			WebElement GuerrillaMailWelcomeMessage= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_WELCOME_MESSAGE_VSPN));
			verifyElementIsPresent(driver, GuerrillaMailWelcomeMessage);
			click(GuerrillaMailWelcomeMessage);
			wait(driver, "10");
			WebElement generatedLink= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_GENERATED_LINK_VSPN));
			waitForElement(driver, GUERRILLA_MAIL_GENERATED_LINK_VSPN);
			verifyElementIsPresent(driver, generatedLink);
			getwindowandclose(driver, generatedLink); 
			wait(driver, "10");
			if (Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();"); 
				wait(driver, "5");
			}
		}
	}
	
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   09/JAN/17
	 * Modified Date:   
	 * Description :   Create a common method to append url in member portal
	 * Ticket ID :     
	 * Required Inputs :  Username and Password
	 *
	 */

	public static void launchVspnProviderURL(WebDriver driver) 
	{
		Navigate.get(driver, Directory.Providerurl);
	}
	
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   03/02/2016
	 * Modified Date:   
	 * Description :   Create a common method to Member verify lecture session
	 * Ticket ID :     
	 * Required Inputs :  
	 *
	 */
	// Method will verify the created Lecture session by clicking View session for Sign Up

	public static String vspnMemberVerifyLectureSession(WebDriver driver, String verifyCreatedVSPN)
	{
		WebElement createdLectureSignUp= driver.findElement(By.xpath("//table[@id='lectureSessionDetailsTab3']//tr/td[3][contains(text(),'"+verifyCreatedVSPN+"')]/following-sibling::td/button[contains(text(),'Sign Up')]"));
		verifyElementIsPresent(driver, createdLectureSignUp);
		click(createdLectureSignUp);
		wait(driver, "3");
		return verifyCreatedVSPN;
	}
}
