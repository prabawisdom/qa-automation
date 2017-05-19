package com.zillion.qa.practitioner;
import java.awt.AWTException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.zillion.qa.commands.ElementActions;
import com.zillion.qa.commands.Manipulation;
import com.zillion.qa.commands.Navigate;

import com.zillion.qa.utils.Directory;
import com.zillion.qa.utils.Platform;
public class Patients extends Manipulation implements OR
{
	public static String browserName;
	/**
	 * Name         :   Suresh.V
	 * Created Date :   14/Dec/15
	 * Modified Date:   12/jan/2016
	 * Description  :   Using provider "URL" login as a practitioner account and verify all static elements.
	 * Required Inputs: Username and password.
	 * Vulcan>Provider portal>Practitioner Dashboard
	 * Test_ID:185968
	 * Test_ID:185969
	 */
	public static void practitionerLogin(WebDriver driver)
	{
		Navigate.get(driver, Directory.Providerurl);
		Navigate.maximize(driver);
		wait(driver, "3");
		WebElement login = driver.findElement(By.xpath(OR.PROVIDER_LOGIN_BUTTON));
		verifyElementIsPresent(driver, login);
		WebElement usernameTextbox=driver.findElement(By.xpath(OR.EMAIL_TEXTBOX_PROVIDER_URL));
		WebElement passwordTextbox= driver.findElement(By.xpath(OR.PASSWORD_TEXTBOX_PROVIDER_URL));
		sendKeys(usernameTextbox, Directory.Practitionerusername);
		sendKeys(passwordTextbox, Directory.Practitionerpassword);
		click(login);
		wait(driver, "10");
		WebElement Patient = driver.findElement(By.xpath(OR.PRACTITIONER_PATIENT_TAB));
		verifyElementIsPresent(driver, Patient);
		click(Patient);
		wait(driver, "5");
	}
	/**
	 * Name         :   Suresh.V
	 * Created Date :   14/Dec/15
	 * Modified Date:   14/Dec/15
	 * Description  :   Using provider "URL"  practitioner account signout
	 */
	public static void practitionerLogout(WebDriver driver)
	{
		WebElement SIGNOUT_WAY_LINK= driver.findElement(By.xpath(OR.PROVIDER_URL_SETTING_IMAGE));
		verifyElementIsPresent(driver, SIGNOUT_WAY_LINK);
		click(SIGNOUT_WAY_LINK);
		WebElement SIGNOUT_LINK= driver.findElement(By.xpath(OR.PROVIDER_URL_SIGNOUT_LINK));
		click(SIGNOUT_LINK);
		Navigate.waitTime(driver, "20");
	}
	/**
	 * Name         :   Suresh.V
	 * Created Date :   14/Dec/15
	 * Modified Date:   19/Dec/15
	 * Description  :   Using provider "URL"  practitioner Add new member
	 * Vulcan>Provider portal>Practitioner>Patients list>Add new Member
	 * Test ID:178113
	 * Test ID:185974
	 * Test ID:185975
	 */
	public static String Email="";
	public static String practitionerAddNewPatient(WebDriver driver, String testData) throws ClassNotFoundException, SQLException, AWTException
	{
		wait(driver, "1");
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
		WebElement patientListCount= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_COUNT));
		String count = patientListCount.getText();
		WebElement addPatient= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_BUTTON));
		verifyElementIsPresent(driver, addPatient);
		click(addPatient);
		wait(driver, "2");
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
		sendKeys(lastName, inputLastName);
		WebElement emailNotMatch= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_EMAIL_ADDRESS_NOTMATCH_MSG));
		Email = dynamicSendkeys(driver, "qa@guerrillamail.com", email);
		dynamicSendkeys(driver, "QA", confirmEmail);
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
		waitForElement(driver, PROVIDER_URL_ADD_PATIENT_AGE_LIMIT);
		selectByValue(yearAdd, inputYear);
		wait(driver, "2");
		jsClickByXPath(driver, PROVIDER_URL_ADD_PATIENT_LAST_STAGE);
		wait(driver, "5");
		try
		{
			waitForElement(driver, INVOICE_NUMBER_ALERT_MESSAGE);
			WebElement poNumber= driver.findElement(By.xpath(OR.INVOICE_NUMBER_TEXT_BOX));
			Manipulation.clearAndType(poNumber,"In12345");
			System.out.println("Entered PO number");
			WebElement proceedbutton= driver.findElement(By.xpath(OR.PROVIDER_ADD_PATIENT_PAYMENT_METHOD_PROCEED_BUTTON));
			visibilityElement(driver, proceedbutton);
			wait(driver, "1");
			jsClickByXPath(driver, OR.PROVIDER_ADD_PATIENT_PAYMENT_METHOD_PROCEED_BUTTON);
			wait(driver, "1");
			waitForElement(driver, PATIENT_ADDED_SUCCESS_MSG);
			jsClickByXPath(driver, OR.PATIENT_HAS_BEEN_ADDED_SUCCESSFULLY_OK_BUTTON);
			wait(driver, "5");
		}
		catch(Exception e){
			System.out.println("Payment type is not Invoice ");
			try {
				WebElement proceedbutton= driver.findElement(By.xpath(OR.PROVIDER_ADD_PATIENT_PAYMENT_METHOD_PROCEED_BUTTON));
				visibilityElement(driver, proceedbutton);
				wait(driver, "1");
				jsClickByXPath(driver, OR.PROVIDER_ADD_PATIENT_PAYMENT_METHOD_PROCEED_BUTTON);
				wait(driver, "1");
				waitForElement(driver, PATIENT_ADDED_SUCCESS_MSG);
				jsClickByXPath(driver, OR.PATIENT_HAS_BEEN_ADDED_SUCCESSFULLY_OK_BUTTON);
				wait(driver, "5");
				WebElement close= driver.findElement(By.xpath(OR.PATIENT_CLOSE));
				visibilityElement(driver, close);
				jsClickByXPath(driver, OR.PATIENT_CLOSE);
			}
			catch(Exception e1){
				waitForElement(driver, PATIENT_ADDED_SUCCESS_MSG);
				jsClickByXPath(driver, OR.PATIENT_HAS_BEEN_ADDED_SUCCESSFULLY_OK_BUTTON);
				wait(driver, "5");
				System.out.println("VSPN patient to be added");
			}
		}
		WebElement patientListCount1= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_COUNT));
		String count1 = patientListCount1.getText();
		if(count1!=count)
		{
			System.out.println("Patient is added successfully");
		}
		else
		{
			System.out.println("Patient is not added");
			Assert.fail();
		}
		return Email;
	}
	/**
	 * Name         :   Suresh.V
	 * Created Date :   27jan2016
	 * Description  :
	 */
	public static void practitionerAddNewPatientAndVerify(WebDriver driver)
	{
		WebElement memberFilterList=driver.findElement(By.xpath(OR.PROVIDER_URL_PARTITIONER_ADMIN_FILTERMEMBER_LIST_LINK));
		click(memberFilterList);
		WebElement searchIcon=driver.findElement(By.xpath(OR.PROVIDER_URL_PARTITIONER_SUBMIT_SEARCH_IMAGE));
		WebElement textBox=driver.findElement(By.xpath(OR.PATIENT_SEARCH_TEXTBOX));
		selectByValue(memberFilterList, "email");
		sendKeys(textBox,Email);
		click(searchIcon);
		wait(driver, "2");
		WebElement patientName=driver.findElement(By.xpath(OR.PRACTITIONER_PATIENT_NAME));
		click(patientName);
		Navigate.waitTime(driver, "20");
		WebElement fulfillmentStatusNotInitiated=driver.findElement(By.xpath(OR.NEW_PATIENT_FULFILLMENT_STATUS_NOT_INITIATED));
		verifyElementIsPresent(driver, fulfillmentStatusNotInitiated);
	}
	/**
	 * Name         :   Vinothkumar.M
	 * Created Date :   16/Dec/15
	 * Modified Date:   16/Dec/15
	 * Description  :   Yopmail
	 * Vulcan>Member portal>Enrollment/Onboarding
	 * Test ID:178685
	 * Test ID:181282
	 */
	public static void yopMail(WebDriver driver)
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
		WebElement generatedLink= driver.findElement(By.xpath(OR.PARTITIONER_YOP_EMAIL_GENERATED_LINK));
		verifyElementIsPresent(driver, generatedLink);
		wait(driver, "5");
		if (Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
		{
			driver.get("javascript:document.getElementById('overridelink').click();");
			wait(driver, "5");
		}
	}
	/**
	 * Name         :   Vinothkumar.M
	 * Created Date :   13/Jan/16
	 * Modified Date:   19/Jan/16
	 * Description  :  MailDrop
	 * Vulcan>Member portal>Enrollment/Onboarding
	 * Test ID:178685
	 * Test ID:181282
	 * Test ID:176148
	 * Updated member Enrollment process for the browser FF,Chrome.In IE browser yet to be handled
	 */
	public static void mailDrop(WebDriver driver)
	{
		Navigate.get(driver, Directory.MailDropurl);
		Navigate.maximize(driver);
		WebElement mailDropTextbox= driver.findElement(By.xpath(OR.PARTITIONER_MAIL_DROP_TEXTBOX));
		verifyElementIsPresent(driver, mailDropTextbox);
		clearAndType(mailDropTextbox,Email);
		System.out.println(Email);
		WebElement mailDropGoButton= driver.findElement(By.xpath(OR.PARTITIONER_MAIL_DROP_SUBMIT_BUTTON));
		verifyElementIsPresent(driver, mailDropGoButton);
		click(mailDropGoButton);
		wait(driver, "5");
		try
		{
			WebElement mailDropReloadButton= driver.findElement(By.xpath(OR.MAIL_DROP_RELOAD_BUTTON));
			verifyElementIsPresent(driver, mailDropReloadButton);
			click(mailDropReloadButton);
			waitForElement(driver, OR.PARTITIONER_KULFI_WELCOME_LINK_MESSAGE);
		}
		catch(Exception e)
		{
			WebElement mailDropReloadButton= driver.findElement(By.xpath(OR.MAIL_DROP_RELOAD_BUTTON));
			click(mailDropReloadButton);
			waitForElement(driver, OR.PARTITIONER_KULFI_WELCOME_LINK_MESSAGE);
			try
			{
				click(mailDropReloadButton);
				waitForElement(driver, OR.PARTITIONER_KULFI_WELCOME_LINK_MESSAGE);
			}
			catch(Exception e1)
			{
				click(mailDropReloadButton);
				waitForElement(driver, OR.PARTITIONER_KULFI_WELCOME_LINK_MESSAGE);
			}
		}
		wait(driver, "2");
		WebElement welcomeLinkMessage=driver.findElement(By.xpath(OR.PARTITIONER_KULFI_WELCOME_LINK_MESSAGE));
		click(welcomeLinkMessage);
		wait(driver, "2");
		WebElement iframe= driver.findElement(By.xpath(OR.PARTITIONER_MAIL_DROP_MESSAGE_IFRAME));
		Navigate.switchToFrame(driver, iframe);
		WebElement generatedLink= driver.findElement(By.xpath(OR.PARTITIONER_MAILDROP_GENERATED_LINK));
		verifyElementIsPresent(driver, generatedLink);
		rightClickMailDrop(driver,generatedLink);
		wait(driver, "5");
		if (Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
		{
			driver.get("javascript:document.getElementById('overridelink').click();");
			wait(driver, "5");
		}
	}
	/**
	 * Name         :   Vinothkumar.M
	 * Created Date :   19/Jan/16
	 * Modified Date:
	 * Description  :  MailDrop Member enrollment completion mail
	 * Vulcan>Member portal>Enrollment/Onboarding
	 * Test ID:178123
	 */
	public static void mailDropRegistrationCompleteLink(WebDriver driver)
	{
		Navigate.get(driver, Directory.MailDropurl);
		Navigate.maximize(driver);
		WebElement mailDropTextbox= driver.findElement(By.xpath(OR.PARTITIONER_MAIL_DROP_TEXTBOX));
		verifyElementIsPresent(driver, mailDropTextbox);
		clearAndType(mailDropTextbox,Email);
		System.out.println(Email);
		WebElement mailDropGoButton= driver.findElement(By.xpath(OR.PARTITIONER_MAIL_DROP_SUBMIT_BUTTON));
		verifyElementIsPresent(driver, mailDropGoButton);
		click(mailDropGoButton);
		wait(driver, "5");
		try
		{
			WebElement mailDropReloadButton= driver.findElement(By.xpath(OR.MAIL_DROP_RELOAD_BUTTON));
			verifyElementIsPresent(driver, mailDropReloadButton);
			click(mailDropReloadButton);
			waitForElement(driver, OR.MAIL_DROP_REGISTRATION_COMPLETE_LINK);
		}
		catch(Exception e)
		{
			WebElement mailDropReloadButton= driver.findElement(By.xpath(OR.MAIL_DROP_RELOAD_BUTTON));
			click(mailDropReloadButton);
			waitForElement(driver, OR.MAIL_DROP_REGISTRATION_COMPLETE_LINK);
			try
			{
				click(mailDropReloadButton);
				waitForElement(driver, OR.MAIL_DROP_REGISTRATION_COMPLETE_LINK);
			}
			catch(Exception e1)
			{
				click(mailDropReloadButton);
				waitForElement(driver, OR.MAIL_DROP_REGISTRATION_COMPLETE_LINK);
			}
		}
		wait(driver, "2");
		WebElement registrationCompleteLink=driver.findElement(By.xpath(OR.MAIL_DROP_REGISTRATION_COMPLETE_LINK));
		click(registrationCompleteLink);
		wait(driver, "2");
		WebElement iframe= driver.findElement(By.xpath(OR.PARTITIONER_MAIL_DROP_MESSAGE_IFRAME));
		Navigate.switchToFrame(driver, iframe);
		WebElement congratulationMessage= driver.findElement(By.xpath(OR.MAIL_DROP_CONGRATULATION_MESSAGE));
		verifyElementIsPresent(driver, congratulationMessage);
	}
	/**
	 * Name         :   Vinothkumar.M
	 * Created Date :   19/Jan/16
	 * Modified Date:
	 * Description  : Member Login After Enrollment
	 */
	public static void memberLoginAfterEnrollment(WebDriver driver) {

		Navigate.get(driver, Directory.Memberurl);
		Navigate.maximize(driver);
		wait(driver, "10");
		WebElement member_username = driver.findElement(By.xpath(OR.MEMBER_YOURMAIL));
		clearAndType(member_username,Email);
		WebElement member_password = driver.findElement(By.xpath(OR.MEMBER_YOURPASSWORD));
		jsClickByXPath(driver, OR.MEMBER_YOURPASSWORD);
		clearAndType(member_password,"Password1");
		waitForElement(driver, OR.MEMBER_LOGIN_BUTTON);
		jsClickByXPath(driver, OR.MEMBER_LOGIN_BUTTON);
		wait(driver, "10");
	}
	/**
	 * Name         :   Suresh.V
	 * Created Date :   22/Jan/16
	 * Modified Date:
	 * Description  :
	 */
	public static void practitionerProfileValidationAndVerification(WebDriver driver)
	{
		Navigate.get(driver, Directory.Providerurl);
		Navigate.maximize(driver);
		WebElement login = driver.findElement(By.xpath(OR.PROVIDER_LOGIN_BUTTON));
		verifyElementIsPresent(driver, login);
		WebElement usernameTextbox=driver.findElement(By.xpath(OR.EMAIL_TEXTBOX_PROVIDER_URL));
		WebElement passwordTextbox= driver.findElement(By.xpath(OR.PASSWORD_TEXTBOX_PROVIDER_URL));
		sendKeys(usernameTextbox, "sureshtesting5@yopmail.com");
		sendKeys(passwordTextbox, "Password1");
		click(login);
		wait(driver, "20");
		waitForElement(driver, PATIENT_HEADER_LABLE_IN_PRACTITIONER_URL);
		String maximumCharacter="ABCDEFGHIJKLMNOPQRSTUVWXYZADOTESTING";
		WebElement profileParentLink= driver.findElement(By.xpath(OR.USER_SIGNOUT_PARENT_LINK));
		click(profileParentLink);
		WebElement profileLink= driver.findElement(By.xpath(OR.MY_PROFILE_LINK));
		click(profileLink);
		Navigate.waitTime(driver, "20");
		WebElement manageMyProfileHeader = driver.findElement(By.xpath(OR.MANAGE_MY_PROFILE_HEADER));
		verifyElementIsPresent(driver, manageMyProfileHeader);
		WebElement activeGeneralTab = driver.findElement(By.xpath(OR.ACTIVE_GENERAL_TAB));
		verifyElementIsPresent(driver, activeGeneralTab);
		WebElement editButton = driver.findElement(By.xpath(OR.PRACTITIONER_MY_PROFILE_EDIT_BUTTON));
		click(editButton);
		wait(driver, "2");
		WebElement firstNameTextBox = driver.findElement(By.xpath(OR.PRACTITIONER_MY_PROFILE_FIRSTNAME_EDIT_TEXTBOX));
		clearAndType(firstNameTextBox,maximumCharacter);
		WebElement lastNameTextBox = driver.findElement(By.xpath(OR.PRACTITIONER_MY_PROFILE_LASTNAME_EDIT_TEXTBOX));
		clearAndType(lastNameTextBox,maximumCharacter);
		WebElement saveButton = driver.findElement(By.xpath(OR.PRACTITIONER_MY_PROFILE_EDIT_AND_SAVE_BUTTON));
		click(saveButton);
	}
	/**
	 * Name         :   Suresh.V
	 * Created Date :   02Feb2016
	 * Modified Date:   09rd-Feb2016
	 * Description  :
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String providerMyProfileVerificationAndValidationUsingDB(WebDriver driver, String testData) throws ClassNotFoundException, SQLException
	{
		String[] testData1 = testData.split(",");
		String maximumCharacter=testData1[0];
		String bioMaximumCharacter=testData1[1];
		String phoneNumber=testData1[2];
		String zipCode=testData1[3];
		String address1=testData1[4];
		String city=testData1[5];
		String nickName=testData1[6];
		String inputMonth=testData1[7];
		String inputDay=testData1[8];
		String inputYear=testData1[9];
		String clickState=testData1[10];
		String inputEmail=testData1[11];
		wait(driver, "2");
		int bioCharactersLength=bioMaximumCharacter.length();
		int charactersLength=maximumCharacter.length();
		System.out.println("Character Length="+charactersLength);
		System.out.println("Bio Character Length="+bioCharactersLength);
		WebElement firstNameTextBox = driver.findElement(By.xpath(OR.PRACTITIONER_MY_PROFILE_FIRSTNAME_EDIT_TEXTBOX));
		clearAndType(firstNameTextBox,maximumCharacter);
		WebElement lastNameTextBox = driver.findElement(By.xpath(OR.PRACTITIONER_MY_PROFILE_LASTNAME_EDIT_TEXTBOX));
		clearAndType(lastNameTextBox,maximumCharacter);
		WebElement phoneTextbox = driver.findElement(By.xpath(OR.PHONE_NUMBER_TEXTBOX));
		WebElement addressOneTextbox = driver.findElement(By.xpath(OR.ADDRESSONE_TEXTBOX));
		WebElement cityTextbox = driver.findElement(By.xpath(OR.CITY_TEXTBOX));
		WebElement zipCodeTextbox = driver.findElement(By.xpath(OR.ZIP_CODE_TEXTBOX));
		WebElement nickNameTextbox = driver.findElement(By.xpath(OR.NICKNAME_TEXTBOX));
		sendKeys(phoneTextbox, phoneNumber);
		WebElement phoneExtensionTextBox = driver.findElement(By.xpath(OR.PHONE_EXTENSION));
		clearAndType(phoneExtensionTextBox,"test");
		WebElement phoneExtensionAlertMsgNumberOnlyAndMinimum3Digit = driver.findElement(By.xpath(OR.PHONE_EXTENSION_ALERT_MSG_NUMBERS_ONLY));
		verifyElementIsPresent(driver, phoneExtensionAlertMsgNumberOnlyAndMinimum3Digit);
		clearAndType(phoneExtensionTextBox,"1");
		verifyElementIsPresent(driver, phoneExtensionAlertMsgNumberOnlyAndMinimum3Digit);
		clearAndType(phoneExtensionTextBox,"123");
		clearAndType(zipCodeTextbox, zipCode);
		clearAndType(addressOneTextbox,address1);
		clearAndType(cityTextbox,city);
		clearAndType(nickNameTextbox,nickName);
		WebElement selectState = driver.findElement(By.xpath(OR.SELECT_STATE));
		WebElement monthSelect = driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_ADD_MONTH_FIELD));
		WebElement daySelect = driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_ADD_DAY_FIELD));
		WebElement yearSelect = driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_ADD_YEAR_FIELD));
		selectByValue(selectState,clickState );
		selectByValue(monthSelect, inputMonth);
		selectByValue(daySelect, inputDay);
		selectByValue(yearSelect, inputYear);
		WebElement saveButton = driver.findElement(By.xpath(OR.PRACTITIONER_MY_PROFILE_EDIT_AND_SAVE_BUTTON));
		click(saveButton);
		wait(driver, "2");
		WebElement okButton = driver.findElement(By.xpath(OR.SUCCESS_MESSAGE_OKBUTTON));
		click(okButton);
		WebElement bioEditButton = driver.findElement(By.xpath(OR.BIO_EDIT_BUTTON));
		WebElement bioTextArea = driver.findElement(By.xpath(OR.BIO_TEXT_AREA));
		WebElement bioSaveButton = driver.findElement(By.xpath(OR.BIO_TEXT_AREA_SAVE_BUTTON));
		click(bioEditButton);
		clearAndType(bioTextArea, bioMaximumCharacter);
		click(bioSaveButton);
		wait(driver, "2");
		WebElement successOkButton = driver.findElement(By.xpath(OR.BIO_TEXT_AREA_SAVE_SUCCESS_OK));
		click(successOkButton);
		String allfields="";
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
		ResultSet rs = stat.executeQuery("select LAST_NAME,FIRST_NAME,PHONE,NICKNAME,CITY,POSTAL_CODE,STREET,BIO from provider where email= '"+inputEmail+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			String firstname = rs.getString("FIRST_NAME");
			System.out.println("FirstName="+firstname);
			String lastname = rs.getString("LAST_NAME");
			System.out.println("LastName="+lastname);
			int firstNameLength=firstname.length();
			int lastNameLength=lastname.length();
			System.out.println("FirstName Length="+firstNameLength);
			System.out.println("LastName Length="+lastNameLength);
			String phonenumber = rs.getString("PHONE");
			String nickname = rs.getString("NICKNAME");
			String city1 = rs.getString("CITY");
			String postalcode = rs.getString("POSTAL_CODE");
			String street = rs.getString("STREET");
			System.out.println(phonenumber);
			String bio = rs.getString("BIO");
			System.out.println("Bio="+bio);
			int bioLength=bio.length();
			System.out.println("Bio length="+bioLength);
			if ((bioCharactersLength>bioLength))
			{
				System.out.println("Bio text length is less than 500");
			}
			else
			{
				System.out.println("Bio text length is more than 500");
				Assert.fail();
			}
			if ((charactersLength>firstNameLength))
			{
				System.out.println("First name length is less than 35");
			}
			else
			{
				System.out.println("First name length is more than 35");
				Assert.fail();
			}
			if ((charactersLength>lastNameLength))
			{
				System.out.println("Last name length is less than 35");
			}
			else
			{
				System.out.println("Last name length is more than 35");
				Assert.fail();
			}
			if ((phonenumber.equals(phoneNumber)))
			{
				System.out.println("Phone number is matched");
			}
			else
			{
				System.out.println("Phone number is not matched");
				Assert.fail();
			}
			if ((nickname.equals(nickName)))
			{
				System.out.println("Nick name is matched");
			}
			else
			{
				System.out.println("Nick name is not matched");
				Assert.fail();
			}
			if ((city1.equals(city)))
			{
				System.out.println("city is matched");
			}
			else
			{
				System.out.println("city is not matched");
				Assert.fail();
			}
			if ((postalcode.equals(zipCode)))
			{
				System.out.println("Postal code is matched");
			}
			else
			{
				System.out.println("Postal code is not matched");
				Assert.fail();
			}
			if ((street.equals(address1)))
			{
				System.out.println("Address is matched");
			}
			else
			{
				System.out.println("Address is not matched");
				Assert.fail();
			}
		}
		return allfields;
	}
	/**
	 * Name         :   Vinothkumar.M
	 * Created Date :   15/Feb/16
	 * Modified Date:   24/Feb/2016
	 * Description  :  GuerrillaMail
	 * Vulcan>Member portal>Enrollment/Onboarding
	 */
	public static void guerrillaMail(WebDriver driver)
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
			if(driver.findElement( By.xpath( GUERRILLA_MAIL_WELCOME_MESSAGE ) ).isDisplayed())
			{
				waitForElement(driver, GUERRILLA_MAIL_WELCOME_MESSAGE);
			}
			else
			{
				driver.navigate().refresh();
				wait( driver, "3" );
				if(driver.findElement( By.xpath( GUERRILLA_MAIL_WELCOME_MESSAGE ) ).isDisplayed())
				{
					waitForElement(driver, GUERRILLA_MAIL_WELCOME_MESSAGE);
				}
				else
				{
					driver.navigate().refresh();
					wait( driver, "5" );
					waitForElement(driver, GUERRILLA_MAIL_WELCOME_MESSAGE);
				}
			}
		}
		catch(Exception e)
		{
			driver.navigate().refresh();
			wait( driver, "3" );
			waitForElement(driver, GUERRILLA_MAIL_WELCOME_MESSAGE);
		}
		WebElement GuerrillaMailWelcomeMessage= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_WELCOME_MESSAGE));
		verifyElementIsPresent(driver, GuerrillaMailWelcomeMessage);
		click(GuerrillaMailWelcomeMessage);
		wait(driver, "10");
		WebElement generatedLink= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_GENERATED_LINK));
		waitForElement(driver, GUERRILLA_MAIL_GENERATED_LINK);
		verifyElementIsPresent(driver, generatedLink);
		getwindowandclose(driver, generatedLink); // changed by Leena - 09/30
		wait(driver, "5");
		if (Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
		{
			driver.get("javascript:document.getElementById('overridelink').click();");
			wait(driver, "5");
		}
	}
	/**
	 * Name         :   Vinothkumar.M
	 * Created Date :   15/Feb/16
	 * Modified Date:   24/Feb/2016
	 * Description  :  Guerrilla Mail Member enrollment completion mail
	 * Vulcan>Member portal>Enrollment/Onboarding
	 */
	public static void guerrillaMailRegistrationCompleteLink(WebDriver driver, String Email)
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
	 * Name : VINOTHKUMAR.M
	 * Created Date: 15/April/2016
	 * Modified Date:
	 * Description :   Common method to enrollmentProcessStep1PrivacyPolicyLink Enable/Disable from spread sheet
	 * Ticket ID :
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String enrollmentProcessStep1PrivacyPolicyLink(WebDriver driver,String testData)
	{
		String[] testData1 = testData.split(",");
		String inputPrivacyPolicyLink=testData1[0];
		if ("EnablePrivacyPolicyLink".equalsIgnoreCase(inputPrivacyPolicyLink) )
		{
			String currentWindow= Manipulation.GetCurrentWindow( driver );
			WebElement privacyPolicyLink= driver.findElement(By.xpath(OR.PRIVACY_POLICY_LINK));
			Manipulation.getSecondWindow( driver, privacyPolicyLink );
			wait( driver, "10" );
			waitForElement(driver, PRIVACY_POLICY_PAGE);
			WebElement privacyPolicyPage= driver.findElement(By.xpath(OR.PRIVACY_POLICY_PAGE));
			verifyElementIsPresent(driver, privacyPolicyPage);
			driver.close();
			Manipulation.switchParticularWindow( driver, currentWindow );
		}
		else  if ("DisablePrivacyPolicyLink".equalsIgnoreCase(inputPrivacyPolicyLink) )
		{
			System.out.println("Privacy Policy Link is not Verified");
		}
		return ElementWait;
	}
	/**
	 * Name : VINOTHKUMAR.M
	 * Created Date: 15/April/2016
	 * Modified Date:
	 * Description : Common method to enrollmentProcessStep1TermsAndConditionLink Enable/Disable from spread sheet
	 * Ticket ID :
	 * Required Inputs : Input from Input data from spreadsheet
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String enrollmentProcessStep1TermsAndConditionLink(WebDriver driver,String testData)
	{
		String[] testData1 = testData.split(",");
		String inputTermsAndConditionLink=testData1[0];
		if ("EnableTermsAndConditionLink".equalsIgnoreCase(inputTermsAndConditionLink) )
		{
			String currentWindow= Manipulation.GetCurrentWindow( driver );
			WebElement termsAndConditionLink= driver.findElement(By.xpath(OR.TERMS_AND_CONDITION_LINK));
			Manipulation.getSecondWindow( driver, termsAndConditionLink );
			wait( driver, "5" );
			waitForElement(driver, TERMS_AND_CONDITION_PAGE);
			WebElement termsAndConditionPage= driver.findElement(By.xpath(OR.TERMS_AND_CONDITION_PAGE));
			verifyElementIsPresent(driver, termsAndConditionPage);
			driver.close();
			Manipulation.switchParticularWindow( driver, currentWindow );
		}
		else  if ("DisableTermsAndConditionLink".equalsIgnoreCase(inputTermsAndConditionLink) )
		{
			System.out.println("Terms And Condition Link is not Verified");
		}
		return ElementWait;
	}
	/**
	 * Name : VINOTHKUMAR.M
	 * Created Date: 15/April/2016
	 * Modified Date:
	 * Description :   Common method to enrollmentProcessStep2PrivacyPolicyLink Enable/Disable from spread sheet
	 * Ticket ID :
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String enrollmentProcessStep2PrivacyPolicyLink(WebDriver driver,String testData)
	{
		String[] testData1 = testData.split(",");
		String inputPrivacyPolicyLink=testData1[0];
		if ("EnablePrivacyPolicyLink".equalsIgnoreCase(inputPrivacyPolicyLink) )
		{
			String currentWindow= Manipulation.GetCurrentWindow( driver );
			WebElement privacyPolicyLink= driver.findElement(By.xpath(OR.MEMBER_STEP2_PRIVACY_POLICY_LINK));
			Manipulation.getSecondWindow( driver, privacyPolicyLink );
			wait( driver, "5" );
			waitForElement(driver, PRIVACY_POLICY_PAGE);
			WebElement privacyPolicyPage= driver.findElement(By.xpath(OR.PRIVACY_POLICY_PAGE));
			verifyElementIsPresent(driver, privacyPolicyPage);
			driver.close();
			Manipulation.switchParticularWindow( driver, currentWindow );
		}
		else  if ("DisablePrivacyPolicyLink".equalsIgnoreCase(inputPrivacyPolicyLink) )
		{
			System.out.println("Privacy Policy Link is not Verified");
		}
		return ElementWait;
	}
	/**
	 * Name : VINOTHKUMAR.M
	 * Created Date: 15/April/2016
	 * Modified Date:
	 * Description : Common method to enrollmentProcessStep2TermsAndConditionLink Enable/Disable from spread sheet
	 * Ticket ID :
	 * Required Inputs : Input from Input data from spreadsheet
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String enrollmentProcessStep2TermsAndConditionLink(WebDriver driver,String testData)
	{
		String[] testData1 = testData.split(",");
		String inputTermsAndConditionLink=testData1[0];
		if ("EnableTermsAndConditionLink".equalsIgnoreCase(inputTermsAndConditionLink) )
		{
			String currentWindow= Manipulation.GetCurrentWindow( driver );
			WebElement termsAndConditionLink= driver.findElement(By.xpath(OR.MEMBER_STEP2_TERMS_AND_CONDITIONS_LINK));
			Manipulation.getSecondWindow( driver, termsAndConditionLink );
			wait( driver, "5" );
			waitForElement(driver, TERMS_AND_CONDITION_PAGE);
			WebElement termsAndConditionPage= driver.findElement(By.xpath(OR.TERMS_AND_CONDITION_PAGE));
			verifyElementIsPresent(driver, termsAndConditionPage);
			driver.close();
			Manipulation.switchParticularWindow( driver, currentWindow );
		}
		else  if ("DisableTermsAndConditionLink".equalsIgnoreCase(inputTermsAndConditionLink) )
		{
			System.out.println("Terms And Condition Link is not Verified");
		}
		return ElementWait;
	}
	/**
	 * Name :     Suresh.V
	 * Created Date:  17/jan/16
	 * Modified Date:  19/July/2016
	 * Description :   Create a common method for Add new member
	 */
	public static void hfopsAddNewMember(WebDriver driver,String testData)
	{
		//Add Member
		String[] testData1 = testData.split(",");
		String inputFirstName=testData1[0];
		String inputLastName=testData1[1];
		String inputMonth=testData1[2];
		String inputDay=testData1[3];
		String inputInvalidYear=testData1[4];
		String inputYear=testData1[5];
		String inputOrganization=testData1[6];
		String inputprogram=testData1[7];
		String inputphysician=testData1[8];
		wait(driver, "10");
		WebElement patientListCount= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_COUNT));
		String count = patientListCount.getText();
		WebElement addPatient= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_BUTTON));
		verifyElementIsPresent(driver, addPatient);
		click(addPatient);
		WebElement firstnameLabel= driver.findElement(By.xpath(OR.FIRSTNAME_LABEL));
		WebElement lastnameLabel= driver.findElement(By.xpath(OR.LASTNAME_LABEL));
		WebElement emailnameLabel= driver.findElement(By.xpath(OR.EMAIL_LABEL));
		WebElement confirmEmailLabel= driver.findElement(By.xpath(OR.CONFIRMEMAIL_LABEL));
		WebElement bateOfBirthLabel= driver.findElement(By.xpath(OR.DOB_LABEL));
		WebElement organizationLabel= driver.findElement(By.xpath(OR.ORGANIZATION_LABEL));
		WebElement programLabel= driver.findElement(By.xpath(OR.PROGRAM_LABEL));
		verifyElementIsPresent(driver, firstnameLabel);
		verifyElementIsPresent(driver, lastnameLabel);
		verifyElementIsPresent(driver, emailnameLabel);
		verifyElementIsPresent(driver, confirmEmailLabel);
		verifyElementIsPresent(driver, bateOfBirthLabel);
		verifyElementIsPresent(driver, organizationLabel);
		verifyElementIsPresent(driver, programLabel);
		WebElement addMemberLastLine= driver.findElement(By.xpath(OR.HFOPS_URL_ADD_MEMBER_LAST_STAGE));
		click(addMemberLastLine);
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
		WebElement firstName= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_FIRSTNAME_TEXTBOX));
		WebElement lastName= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_LASTNAME_TEXTBOX));
		WebElement email= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_EMAIL_TEXTBOX));
		WebElement confirmEmail= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_CONFIRMEMAIL_TEXTBOX));
		WebElement monthAdd= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_ADD_MONTH_FIELD));
		WebElement dayAdd= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_ADD_DAY_FIELD));
		WebElement yearAdd= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_ADD_YEAR_FIELD));
		sendKeys(firstName, inputFirstName);
		sendKeys(lastName, inputLastName);
		WebElement emailNotMatch= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_EMAIL_ADDRESS_NOTMATCH_MSG));
		Email = dynamicSendkeys(driver, "qa@guerrillamail.com", email);
		dynamicSendkeys(driver, "QA", confirmEmail);
		verifyElementIsPresent(driver, emailNotMatch);
		clear(confirmEmail);
		sendKeys(confirmEmail,Email);
		System.out.println("GuerrillaMail"+Email);
		selectByValue(monthAdd,inputMonth);
		selectByValue(dayAdd, inputDay);
		selectByValue(yearAdd, inputInvalidYear);
		click(addMemberLastLine);
		wait(driver, "1");
		WebElement organization= driver.findElement(By.xpath(OR.ORGANIZATION));
		selectByVisibletext(organization, inputOrganization);
		wait(driver, "1");
		click(addMemberLastLine);
		WebElement programRequiredAlertMsg =driver.findElement(By.xpath(OR.PROVIDER_PROGRAM_IS_REQUIRED_ERROR_MSG));
		verifyElementIsPresent(driver, programRequiredAlertMsg);
		WebElement program= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_PROGRAM_FIELD));
		selectByVisibletext(program, inputprogram);
		wait(driver, "1");
		Navigate.waitTime(driver, "5");
		WebElement physicianIsRequiredAlertMsg= driver.findElement(By.xpath(OR.PROVIDER_PHYSICIAN_IS_REQUIRED_ERROR_MSG));
		verifyElementIsPresent(driver, physicianIsRequiredAlertMsg);
		WebElement physician= driver.findElement(By.xpath(OR.PROVIDER_PHYSICIAN_DROPDOWN));
		selectByVisibletext(physician, inputphysician);
		wait(driver, "1");
		Navigate.waitTime( driver, "10" );
		click(addMemberLastLine);
		Navigate.waitTime(driver, "5");
		WebElement patientAgeLimit= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_AGE_LIMIT));
		verifyElementIsPresent(driver, patientAgeLimit);
		selectByValue(yearAdd, inputYear);
		Navigate.waitTime(driver, "1");
		click(addMemberLastLine);
		wait(driver, "5");
		WebElement successMessage= driver.findElement(By.xpath(OR.MEMBER_ADDED_SUCCESS_MSG));
		WebElement okButton= driver.findElement(By.xpath(OR.MEMBER_ADDED_SUCCESS_OK_BUTTON));
		verifyElementIsPresent(driver, successMessage);
//		click(okButton);
		jsClickByXPath(driver,MEMBER_ADDED_SUCCESS_OK_BUTTON);
		wait(driver, "5");
		WebElement patientListCount1= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_COUNT));
		String count1 = patientListCount1.getText();
		if(count1!=count)
		{
			System.out.println("Patient is added successfully");
		}
		else
		{
			System.out.println("Patient is not added");
			Assert.fail();
		}
	}
	public static void practitionerlogin(WebDriver driver)
	{
		Navigate.get(driver, Directory.Providerurl);
		Navigate.maximize(driver);
		wait(driver, "3");
		WebElement login = driver.findElement(By.xpath(OR.PROVIDER_LOGIN_BUTTON));
		verifyElementIsPresent(driver, login);
		WebElement usernameTextbox=driver.findElement(By.xpath(OR.EMAIL_TEXTBOX_PROVIDER_URL));
		WebElement passwordTextbox= driver.findElement(By.xpath(OR.PASSWORD_TEXTBOX_PROVIDER_URL));
		sendKeys(usernameTextbox, Directory.Practitionerusername);
		sendKeys(passwordTextbox, Directory.Practitionerpassword);
		click(login);
		wait(driver, "10");
		WebElement Dashboard = driver.findElement(By.xpath(OR.PRACTITIONER_DASHBOARD_TAB));
		verifyElementIsPresent(driver, Dashboard);
		WebElement Message = driver.findElement(By.xpath(OR.PRACTITIONER_MESSAGE_TAB));
		verifyElementIsPresent(driver, Message);
		WebElement Patient = driver.findElement(By.xpath(OR.PRACTITIONER_PATIENT_TAB));
		verifyElementIsPresent(driver, Patient);
	}
	public static String getPractitionerIDFromQuery(WebDriver driver) throws ClassNotFoundException, SQLException
	{
		String practitionerID ="";
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
		ResultSet rs = stat.executeQuery("SELECT ACNT.EMAIL Member_Email, PROV.EMAIL Coach_Email, SUMMARY_EXTENSION, substr(summary_extension,instr(SMRY.SUMMARY_EXTENSION,'practitionerId')+17,32) FROM SUMMARY_ACCOUNT_TODATE SMRY, PROVIDER PROV, ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID AND SMRY.ACCOUNT_ID=ACNT.ID AND ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ID IN (SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_TYPE='1on1' AND SESSION_STATUS IN ('Unscheduled','Missed') AND ACCOUNT_PROGRAM_ID IN (SELECT ID FROM ACCOUNT_PROGRAM WHERE ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM SUMMARY_ACCOUNT_TODATE WHERE SUMMARY_EXTENSION LIKE '%medicalProcedureMilestone1Answer%YES, I HAD IT%') AND MAST_PROGRAM_ID='30100AA52BEA' AND START_DT_TIME>SYSDATE + INTERVAL '-30' DAY))) AND SUMMARY_EXTENSION LIKE '%practitionerId%' and SUMMARY_EXTENSION NOT LIKE '%null%'");
		System.out.println("query executed");
		while(rs.next())
		{
			practitionerID = rs.getString("SUBSTR(SUMMARY_EXTENSION,INSTR(SMRY.SUMMARY_EXTENSION,'PRACTITIONERID')+17,32)");
		}
		System.out.println(practitionerID);
		return practitionerID;
	}
	/**
	 * Created: Suresh V
	 * This method for get practitioner mail id using practitioner ID.
	 * Orbera-Message center testcases
	 * @param driver
	 * @param inputData
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String getPractitionerMailIdFromPractitionerID(WebDriver driver,String inputData) throws ClassNotFoundException, SQLException
	{
		String emailID ="";
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
		ResultSet rs = stat.executeQuery("select email from Provider where id ='"+inputData+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			emailID = rs.getString("EMAIL");
		}
		System.out.println(emailID);
		return emailID;
	}
	public static String getCoachEmailIdFromQuery(WebDriver driver) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("SELECT ACNT.EMAIL Member_Email, ACNT.FIRST_NAME Member_FirstName, ACNT.LAST_NAME Member_LastName, PROV.EMAIL Coach_Email, SUMMARY_EXTENSION, substr(summary_extension,instr(SMRY.SUMMARY_EXTENSION,'practitionerId')+17,32) FROM SUMMARY_ACCOUNT_TODATE SMRY, PROVIDER PROV, ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID AND SMRY.ACCOUNT_ID=ACNT.ID AND ACCOUNT_ID IN(SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ACCOUNT_PROGRAM_ID IN (SELECT ID FROM ACCOUNT_PROGRAM WHERE ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM SUMMARY_ACCOUNT_TODATE WHERE SUMMARY_EXTENSION LIKE '%medicalProcedureMilestone1Answer%YES, I HAD IT%') AND MAST_PROGRAM_ID='30100AA52BEA' AND START_DT_TIME>SYSDATE + INTERVAL '-52' DAY)) AND SUMMARY_EXTENSION LIKE '%apollo practitioner%' and SUMMARY_EXTENSION NOT LIKE '%null%'");
		System.out.println("query executed");
		String member_mail1="";
		String member_firstName="";
		String member_LastName="";
		String coach_mail1="";
		String memberCoach = "";
		if(rs.next())
		{
			member_mail1 = rs.getString("Member_Email");
			member_firstName = rs.getString("Member_FirstName");
			member_LastName = rs.getString("Member_LastName");
			coach_mail1 = rs.getString("Coach_Email");
		}
		memberCoach=member_mail1+","+member_firstName+","+member_LastName+","+coach_mail1;
		return memberCoach;
	}
	public static void practitionerAddNewPatientforReSchedule(WebDriver driver, String testData) throws ClassNotFoundException, SQLException
	{
		String[] testData1 = testData.split(",");
		String inputFirstName=testData1[0];
		String inputLastName=testData1[1];
		String inputMonth=testData1[2];
		String inputDay=testData1[3];
		String inputYear=testData1[4];
		String inputOrganization=testData1[5];
		String inputphysician=testData1[6];
		Navigate.waitTime(driver, "10");
		WebElement patientListCount= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_COUNT));
		String count = patientListCount.getText();
		WebElement addPatient= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_BUTTON));
		verifyElementIsPresent(driver, addPatient);
		click(addPatient);
		WebElement addPatientHeader= driver.findElement(By.xpath(OR.ADD_PATIENT_TEXT_INLEFT_CORNER));
		verifyElementIsPresent(driver, addPatientHeader);
		WebElement emailnameLabel= driver.findElement(By.xpath(OR.EMAIL_LABEL));
		WebElement confirmEmailLabel= driver.findElement(By.xpath(OR.CONFIRMEMAIL_LABEL));
		WebElement bateOfBirthLabel= driver.findElement(By.xpath(OR.DOB_LABEL));
		WebElement organizationLabel= driver.findElement(By.xpath(OR.ORGANIZATION_LABEL));
		waitForElement(driver, FIRSTNAME_LABEL);
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
		sendKeys(lastName, inputLastName);
		Email = dynamicSendkeys(driver, "qa@guerrillamail.com", email);
		dynamicSendkeys(driver, "QA", confirmEmail);
		waitForElement(driver, PROVIDER_URL_ADD_PATIENT_EMAIL_ADDRESS_NOTMATCH_MSG);
		clear(confirmEmail);
		sendKeys(confirmEmail,Email);
		System.out.println("GuerrillaMail"+Email);
		selectByValue(monthAdd,inputMonth);
		selectByValue(dayAdd, inputDay);
		selectByValue(yearAdd, inputYear);
		click(addPatientLastLine);
		wait(driver, "1");
		WebElement organization= driver.findElement(By.xpath(OR.ORGANIZATION));
		selectByVisibletext(organization, inputOrganization);
		wait(driver, "1");
		Navigate.waitTime( driver, "10" );
		click(addPatientLastLine);
		Navigate.waitTime(driver, "5");
		waitForElement(driver, PROVIDER_PHYSICIAN_IS_REQUIRED_ERROR_MSG);
		WebElement physician= driver.findElement(By.xpath(OR.PROVIDER_PHYSICIAN_DROPDOWN));
		selectByVisibletext(physician, inputphysician);
		wait(driver, "1");
		Navigate.waitTime( driver, "10" );
		click(addPatientLastLine);
		Navigate.waitTime(driver, "5");
		WebElement patientAgeLimit= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_AGE_LIMIT));
		verifyElementIsPresent(driver, patientAgeLimit);
		selectByValue(yearAdd, inputYear);
		wait(driver, "1");
		jsClickByXPath(driver, PROVIDER_URL_ADD_PATIENT_LAST_STAGE);
		Navigate.waitTime(driver, "5");
		waitForElement(driver, PATIENT_ADDED_SUCCESS_MSG);
		jsClickByXPath(driver, OR.PATIENT_HAS_BEEN_ADDED_SUCCESSFULLY_OK_BUTTON);
		wait(driver, "5");
		WebElement patientListCount1= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_COUNT));
		String count1 = patientListCount1.getText();
		if(count1!=count)
		{
			System.out.println("Patient is added successfully");
		}
		else
		{
			System.out.println("Patient is not added");
			Assert.fail();
		}
	}
	/**
	 * Name         :   Vinoth Kumar.M
	 * Created Date :   12/10/16
	 * Description  :
	 */
	public static String practicePAAddNewPatient(WebDriver driver, String testData) throws ClassNotFoundException, SQLException, AWTException
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
		WebElement patientListCount= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_COUNT));
		String count = patientListCount.getText();
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
		//WebElement programLabel= driver.findElement(By.xpath(OR.PROGRAM_LABEL));
		verifyElementIsPresent(driver, firstnameLabel);
		verifyElementIsPresent(driver, lastnameLabel);
		verifyElementIsPresent(driver, emailnameLabel);
		verifyElementIsPresent(driver, confirmEmailLabel);
		verifyElementIsPresent(driver, bateOfBirthLabel);
		verifyElementIsPresent(driver, organizationLabel);
		jsClickByXPath(driver, OR.PROVIDER_URL_ADD_PATIENT_LAST_STAGE);
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
		jsClickByXPath(driver, OR.PROVIDER_URL_ADD_PATIENT_LAST_STAGE);
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
		jsClickByXPath(driver, OR.PROVIDER_URL_ADD_PATIENT_LAST_STAGE);
		Navigate.waitTime(driver, "5");
		waitForElement(driver, PROVIDER_URL_ADD_PATIENT_AGE_LIMIT);
		selectByValue(yearAdd, inputYear);
		Navigate.waitTime(driver, "1");
		jsClickByXPath(driver, OR.PROVIDER_URL_ADD_PATIENT_LAST_STAGE);
		wait(driver, "5");
		Navigate.waitTime(driver, "10");
		try{

			waitForElement(driver, INVOICE_NUMBER_ALERT_MESSAGE);
			WebElement poNumber= driver.findElement(By.xpath(OR.INVOICE_NUMBER_TEXT_BOX));
			Manipulation.clearAndType(poNumber,"In12345");
			System.out.println("Entered PO number");
			WebElement proceedbutton= driver.findElement(By.xpath(OR.PROVIDER_ADD_PATIENT_PAYMENT_METHOD_PROCEED_BUTTON));
			visibilityElement(driver, proceedbutton);
			wait(driver, "1");
			jsClickByXPath(driver, OR.PROVIDER_ADD_PATIENT_PAYMENT_METHOD_PROCEED_BUTTON);
			wait(driver, "1");
			waitForElement(driver, PATIENT_ADDED_SUCCESS_MSG);
			jsClickByXPath(driver, OR.PATIENT_HAS_BEEN_ADDED_SUCCESSFULLY_OK_BUTTON);
			wait(driver, "5");
		}
		catch(Exception e)
		{
			System.out.println("Payment type is not Invoice ");
			WebElement proceedbutton= driver.findElement(By.xpath(OR.PROVIDER_ADD_PATIENT_PAYMENT_METHOD_PROCEED_BUTTON));
			visibilityElement(driver, proceedbutton);
			wait(driver, "4");
			try
			{
				jsClickByXPath(driver, OR.PROVIDER_ADD_PATIENT_PAYMENT_METHOD_PROCEED_BUTTON);
				wait(driver, "1");
				waitForElement(driver, PATIENT_ADDED_SUCCESS_MSG);
				jsClickByXPath(driver, OR.PATIENT_HAS_BEEN_ADDED_SUCCESSFULLY_OK_BUTTON);
				wait(driver, "5");
				WebElement close= driver.findElement(By.xpath(OR.PATIENT_CLOSE));
				visibilityElement(driver, close);
				jsClickByXPath(driver, OR.PATIENT_CLOSE);
			}
			catch(Exception e1)
			{
				System.out.println("Entered in catch ");
				wait(driver, "3");
				waitForElement(driver, MEMBER_ADDED_SUCCESS_OK_BUTTON);
				jsClickByXPath(driver, OR.MEMBER_ADDED_SUCCESS_OK_BUTTON);
				wait(driver, "3");
				System.out.println("Ok button is clicked");
				WebElement chosecreditcard= driver.findElement(By.xpath("//div[@ng-if='creditCardPaymentType && cardList']"));
				selectByValue(chosecreditcard, "Visa card ending in 4242, Name on Card ZADO TEST");
				wait(driver, "3");
				jsClickByXPath(driver, OR.PROVIDER_ADD_PATIENT_PAYMENT_METHOD_PROCEED_BUTTON);
				wait(driver, "1");
				waitForElement(driver, PATIENT_ADDED_SUCCESS_MSG);
				jsClickByXPath(driver, OR.PATIENT_HAS_BEEN_ADDED_SUCCESSFULLY_OK_BUTTON);
				wait(driver, "5");
				WebElement close= driver.findElement(By.xpath(OR.PATIENT_CLOSE));
				visibilityElement(driver, close);
				jsClickByXPath(driver, OR.PATIENT_CLOSE);
			}
		}
		WebElement patientListCount1= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_COUNT));
		String count1 = patientListCount1.getText();
		if(count1!=count)
		{
			System.out.println("Patient is added successfully");
		}
		else
		{
			System.out.println("Patient is not added");
			Assert.fail();
		}
		return Email;
	}
	/**
	 * Created: Abinaya V
	 * This method is to handle either credit card or invoice
	 * Orbera-Practice program admin - add new patient test case
	 * @param driver
	 * @param inputData
	 */
	public static void practitionerAddNewPatientCCorInvoice(WebDriver driver)
	{
		try
		{
			waitForElement(driver, INVOICE_NUMBER_ALERT_MESSAGE);
			WebElement poNumber= driver.findElement(By.xpath(OR.INVOICE_NUMBER_TEXT_BOX));
			Manipulation.clearAndType(poNumber,"In12345");
			System.out.println("Entered PO number");
			WebElement proceedbutton= driver.findElement(By.xpath(OR.PROVIDER_ADD_PATIENT_PAYMENT_METHOD_PROCEED_BUTTON));
			visibilityElement(driver, proceedbutton);
			wait(driver, "2");
			waitForElement(driver, PROVIDER_ADD_PATIENT_PAYMENT_METHOD_PROCEED_BUTTON);
			jsClickByXPath(driver, OR.PROVIDER_ADD_PATIENT_PAYMENT_METHOD_PROCEED_BUTTON);
			wait(driver, "2");
			waitForElement(driver, PATIENT_ADDED_SUCCESS_MSG);
			jsClickByXPath(driver, OR.PATIENT_HAS_BEEN_ADDED_SUCCESSFULLY_OK_BUTTON);
			wait(driver, "5");
		}
		catch(Exception e){
			System.out.println("Payment type is not Invoice ");
			WebElement proceedbutton= driver.findElement(By.xpath(OR.PROVIDER_ADD_PATIENT_PAYMENT_METHOD_PROCEED_BUTTON));
			visibilityElement(driver, proceedbutton);
			wait(driver, "2");
			jsClickByXPath(driver, OR.PROVIDER_ADD_PATIENT_PAYMENT_METHOD_PROCEED_BUTTON);
			wait(driver, "3");
			waitForElement(driver, PATIENT_ADDED_SUCCESS_MSG);
			jsClickByXPath(driver, OR.PATIENT_HAS_BEEN_ADDED_SUCCESSFULLY_OK_BUTTON);
			wait(driver, "5");
			WebElement close= driver.findElement(By.xpath(OR.PATIENT_CLOSE));
			visibilityElement(driver, close);
			jsClickByXPath(driver, OR.PATIENT_CLOSE);
		}
	}
	/**
	 * Created: Bharath Kumar M
	 * This method is to handle login Practice PA with user name: abc.pa@yopmail.com
	 * @param driver
	 * @param inputData
	 */
	public static void practicePAABCLogin(WebDriver driver)
	{
		Navigate.get(driver, Directory.Providerurl);
		Navigate.maximize(driver);
		wait(driver, "3");
		WebElement login = driver.findElement(By.xpath(OR.PROVIDER_LOGIN_BUTTON));
		verifyElementIsPresent(driver, login);
		WebElement usernameTextbox=driver.findElement(By.xpath(OR.EMAIL_TEXTBOX_PROVIDER_URL));
		WebElement passwordTextbox= driver.findElement(By.xpath(OR.PASSWORD_TEXTBOX_PROVIDER_URL));
		sendKeys(usernameTextbox, Directory.PracticePAABCusername);
		sendKeys(passwordTextbox, Directory.PracticePAABCpassword);
		click(login);
		wait(driver, "10");
		WebElement Patient = driver.findElement(By.xpath(OR.PRACTITIONER_PATIENT_TAB));
		verifyElementIsPresent(driver, Patient);
		click(Patient);
		wait(driver, "5");
	}
	/**
	 * Created: Bharath Kumar M
	 * This method is to handle Logout Practice PA with user name: abc.pa@yopmail.com
	 * @param driver
	 * @param inputData
	 */
	public static void practicePAABCLogout(WebDriver driver)
	{
		WebElement SIGNOUT_WAY_LINK= driver.findElement(By.xpath(OR.PROVIDER_URL_SETTING_IMAGE));
		verifyElementIsPresent(driver, SIGNOUT_WAY_LINK);
		click(SIGNOUT_WAY_LINK);
		WebElement SIGNOUT_LINK= driver.findElement(By.xpath(OR.PROVIDER_URL_SIGNOUT_LINK));
		click(SIGNOUT_LINK);
		Navigate.waitTime(driver, "20");
	}
	/**
	 * Created: Bharath Kumar M
	 * This method is to handle add patient Proceed button when payment method is invoice
	 * @param driver
	 * @param inputData
	 */
	public static void proceedbuttonttoinvoice(WebDriver driver)
	{
		try
		{
			wait(driver, "2");
			Manipulation.waitForElementNotpresent(driver, PROVIDER_ADD_PATIENT_PO_NUMBER_TEXTBOX);
			jsClickByXPath(driver, PROVIDER_ADD_PATIENT_PAYMENT_METHOD_PROCEED_BUTTON);
			wait(driver, "4");
		}
		catch(Exception e)
		{
			wait(driver, "2");
			Manipulation.waitForElement(driver, PROVIDER_ADD_PATIENT_PO_NUMBER_TEXTBOX);
			WebElement Po_Number_TestBox= driver.findElement(By.xpath(OR.PROVIDER_ADD_PATIENT_PO_NUMBER_TEXTBOX));
			Manipulation.sendKeys(Po_Number_TestBox, "1234");
			waitForElement(driver, PROVIDER_ADD_PATIENT_PAYMENT_METHOD_PROCEED_BUTTON);
			jsClickByXPath(driver, PROVIDER_ADD_PATIENT_PAYMENT_METHOD_PROCEED_BUTTON);
			wait(driver, "4");
		}
	}
	/**
	 * Name         :   Leena P.
	 * Created Date :   29/Nov/2016
	 * Description  :  GuerrillaMail for VSPN
	 */
	public static void guerrillaMailVSPN(WebDriver driver)
	{
		Navigate.get(driver, Directory.Guerrillamailurl);
		Navigate.maximize(driver);
		wait(driver, "3");
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
		getwindowandclose(driver, generatedLink); // changed by Leena - 09/30
		wait(driver, "5");
		if (Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
		{
			driver.get("javascript:document.getElementById('overridelink').click();");
			wait(driver, "5");
		}
	}
	/**
	 * Name :Vinothkumar.M
	 * Created Date:02/Dec/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve the orbera member who has alert because of not attending 1on1 in last 30 days
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String getPractitionerMemberLastOneOnOneSession30DaysAgo(WebDriver driver) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select EMAIL from account where ORGANIZATION_ID='"+Directory.Orbera_UserAlert_AbcPractice_OrganizationID+"' and id in (select COMMON_USER_ID from user_alerts where MP_ALERT_ID = (select id from mp_alerts where alert_id = (select id from platform_alerts where name = 'Last 1-on-1 session more than ''x'' days ago')) and IS_VALID = 1)");
		System.out.println("query executed");
		String member_mail1="";
		while(rs.next())
		{
			member_mail1 = rs.getString("EMAIL");

		}
		System.out.println("query executed"+member_mail1);
		return member_mail1;
	}
	/**
	 * Name :Vinothkumar.M
	 * Created Date:02/Dec/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve the orbera member who has alert because of gaining >=7 lbs in 14 consecutive days
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String getPractitionerMemberWeightGainOfMoreThan7LBSIn1Day(WebDriver driver) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select EMAIL from account where ORGANIZATION_ID='"+Directory.Orbera_UserAlert_AbcPractice_OrganizationID+"' and id in (select COMMON_USER_ID from user_alerts where MP_ALERT_ID = (select id from mp_alerts where alert_id = (select id from platform_alerts where name = 'Weight gain of more than ''x'' lbs in ''y'' days')) and IS_VALID = 1)");
		System.out.println("query executed");
		String member_mail1="";
		while(rs.next())
		{
			member_mail1 = rs.getString("EMAIL");

		}
		return member_mail1;
	}
	/**
	 * Name :Vinothkumar.M
	 * Created Date:02/Dec/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve the orbera member who has alert because of not getting balloon removed after 7 months from insertion
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String getPractitionerMemberBallonNotRemovedAfter7MonthsInsertionDate(WebDriver driver) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select EMAIL from account where ORGANIZATION_ID='"+Directory.Orbera_UserAlert_AbcPractice_OrganizationID+"' and id in (select COMMON_USER_ID from user_alerts where MP_ALERT_ID = (select id from mp_alerts where alert_id = (select id from platform_alerts where name = 'Balloon not removed after ''x'' months from insertion date')) and IS_VALID = 1)");
		System.out.println("query executed");
		String member_mail1="";
		while(rs.next())
		{
			member_mail1 = rs.getString("EMAIL");

		}
		return member_mail1;
	}
	/**
	 * Name :Vinothkumar.M
	 * Created Date:02/Dec/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve the orbera member who has alert because of not scheduling removal date within 45 days of 6 months from insertion
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String getPractitionerMemberRemovalDateNotYetScheduled(WebDriver driver) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("SELECT EMAIL from account where ORGANIZATION_ID='"+Directory.Orbera_UserAlert_AbcPractice_OrganizationID+"' and id in (select COMMON_USER_ID from user_alerts where MP_ALERT_ID = (select id from mp_alerts where alert_id = (select id from platform_alerts where name = 'Removal date not yet scheduled')) and IS_VALID = 1)");
		System.out.println("query executed");
		String member_mail1="";
		if(rs.next())
		{
			member_mail1 = rs.getString("EMAIL");
			System.out.print(member_mail1);
		}
		return member_mail1;
	}
	
	
}