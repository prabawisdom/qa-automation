package com.zillion.qa.opsadmin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import bsh.ParseException;

import com.zillion.qa.commands.ElementActions;
import com.zillion.qa.commands.Manipulation;
import com.zillion.qa.commands.Navigate;
import com.zillion.qa.utils.Directory;
public class Members extends Manipulation
{
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   04/Jan/16
	 * Modified Date:
	 * Description :   Create a common method to verify all sessions in OPS admin
	 * Ticket ID :
	 * Required Inputs :
	 */
	public static void opsSessionCount(WebDriver driver)
	{
		try
		{
			Manipulation.wait(driver, "5");
		}
		catch(Exception e)
		{
			int size=driver.findElements(By.xpath("//tbody[@id='sessionClasses_tableBody']//tr[*]")).size();
			for(int i=1;i<=size;i++)
			{
				WebElement sesssiontype = driver.findElement(By.xpath("//tbody[@id='sessionClasses_tableBody']//tr["+i+"]"));
				verifyElementIsPresent(driver, sesssiontype);
			}
		}
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   05/Jan/16
	 * Modified Date:
	 * Description :   Create a common method to verify notes in OPS admin
	 * Ticket ID :
	 * Required Inputs :
	 */
	public static void verifyNotesMember(WebDriver driver)
	{
		try
		{
			waitForElement(driver, OR.NOTES1);
			System.out.println("Notes is present");
			WebElement editnotes = driver.findElement(By.xpath(OR.NOTES_EDIT));
			click(editnotes);
			System.out.println("Click on edit");
			WebElement notesarea = driver.findElement(By.xpath(OR.NOTESEDIT_TEXTAREA));
			waitForElement(driver, OR.NOTESEDIT_TEXTAREA);
			clearAndType(notesarea, "notes");
			System.out.println("Enter Min notes");
			WebElement applychanges = driver.findElement(By.xpath(OR.NOTES_APPLYCHANGES));
			click(applychanges);
			System.out.println("Apply changes");
			wait(driver, "2");
			WebElement notessuccmess = driver.findElement(By.xpath(OR.NOTES_EDIT_SUCC));
			waitForElement(driver, OR.NOTES_EDIT_SUCC);
			click(notessuccmess);
			System.out.println("Accept Success Message");
			wait(driver, "2");
			waitForElement(driver, OR.NOTES_EDIT);
			jsClickByXPath(driver, OR.NOTES_EDIT);
			System.out.println("Click on edit");
			waitForAjax(driver);
			waitForElement(driver, OR.NOTESEDIT_TEXTAREA);
			WebElement notesarea1 = driver.findElement(By.xpath(OR.NOTESEDIT_TEXTAREA));
			clearAndType(notesarea1, "Dietary habits are the habitual decisions an individual or culture makes when choosing what foods to eat.");
			System.out.println("Enter Max notes");
			jsClickByXPath(driver, OR.NOTES_APPLYCHANGES);
			System.out.println("Apply changes");
			waitForElement(driver, OR.NOTES_EDIT_SUCC);
			jsClickByXPath(driver, OR.NOTES_EDIT_SUCC);
			System.out.println("Accept Success Message");
		}
		catch(Exception e)
		{
			waitForElement(driver, OR.NOTES_NEWENTRY);
			jsClickByXPath(driver, OR.NOTES_NEWENTRY);
			System.out.println("Click on New Entry");
			WebElement notessaveentry = driver.findElement(By.xpath(OR.NOTES_SAVEENTRY));
			click(notessaveentry);
			System.out.println("Save Entry");
			wait(driver, "2");
			waitForElement(driver, OR.NOTES_NEW_ERR);
			jsClickByXPath(driver, OR.NOTES_NEW_ERR);
			System.out.println("Accept Error Message");
			wait(driver, "2");
			WebElement category = driver.findElement(By.xpath(OR.NOTES_SELECT_CATEGORY));
			selectByIndex(category, "2");
			System.out.println("Select Category");
			wait(driver, "20");
			WebElement notesarea3 = driver.findElement(By.xpath(OR.NOTES_TEXTAREA));
			Manipulation.sendKeys(notesarea3, "notes");
			System.out.println("Enter notes");
			WebElement notessaveentry1 = driver.findElement(By.xpath(OR.NOTES_SAVEENTRY));
			click(notessaveentry1);
			System.out.println("Save Entry");
			wait(driver, "2");
			WebElement notessuccmess = driver.findElement(By.xpath(OR.NOTES_NEW_SUCC));
			verifyElementIsPresent(driver, notessuccmess);
			click(notessuccmess);
			System.out.println("Accept Success Message");
			wait(driver, "2");
			waitForElement(driver, OR.NOTES_EDIT);
			jsClickByXPath(driver, OR.NOTES_EDIT);
			System.out.println("Click on edit");
			waitForAjax(driver);
			waitForElement(driver, OR.NOTESEDIT_TEXTAREA);
			WebElement notesarea1 = driver.findElement(By.xpath(OR.NOTESEDIT_TEXTAREA));
			clearAndType(notesarea1, "Dietary habits are the habitual decisions an individual or culture makes when choosing what foods to eat.");
			System.out.println("Enter Max notes");
			jsClickByXPath(driver, OR.NOTES_APPLYCHANGES);
			System.out.println("Apply changes");
			waitForElement(driver, OR.NOTES_EDIT_SUCC);
			jsClickByXPath(driver, OR.NOTES_EDIT_SUCC);
			System.out.println("Accept Success Message");
		}
	}
	/**
	 * Name :     Suresh.V
	 * Created Date:  17/jan/16
	 * Modified Date:  17/March/2016
	 * Description :   Create a common method for Add new member
	 */
	public static String Email="";
	public static void addNewMember(WebDriver driver,String testData)
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
		Navigate.waitTime(driver, "5");
		WebElement successMessage= driver.findElement(By.xpath(OR.MEMBER_ADDED_SUCCESS_MSG));
		WebElement okButton= driver.findElement(By.xpath(OR.MEMBER_ADDED_SUCCESS_OK_BUTTON));
		verifyElementIsPresent(driver, successMessage);
		click(okButton);
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
	 * Name :     Suresh.V
	 * Created Date:   19/Jan/16
	 * Modified Date:  17/March/2016
	 * Description :   Create a common method for Add new member clear link
	 * Used Sheets:OpsAdmin_Add_New_Member_186442.xlsx
	 */
	public static void addNewMemberClearButton(WebDriver driver,String testData)
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
		WebElement memberListCount0= driver.findElement(By.xpath(OR.MEMBER_COUNT));
		String count0 = memberListCount0.getText();
		wait(driver, "10");
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
		WebElement closeMark= driver.findElement(By.xpath(OR.CLOSE_ICON));
		//verifyElementIsPresent(driver, closeMark);
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
		WebElement clear= driver.findElement(By.xpath(OR.ADD_MEMBER_CLEAR_BUTTON));
		click(clear);
		wait(driver, "5");
		click(closeMark);
		WebElement memberListCount2= driver.findElement(By.xpath(OR.MEMBER_COUNT));
		String count2 = memberListCount2.getText();
		if(count0.equals(count2))
		{
			System.out.println("Patient is not added and clear button is working fine");
		}
		else
		{
			System.out.println("Patient is added successfully");
			Assert.fail();
		}
	}
	/**
	 * Name :     Suresh.V
	 * Created Date:   09/Feb/16
	 * Modified Date:
	 * Description :   Create a common method to validate member/patient profile against database
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String hFOPsValidationDbMemberProfile(WebDriver driver) throws ClassNotFoundException, SQLException
	{
		String inputFirstName="Testing";
		String inputLastName="Test";
		String allfields="";
		String port = "1521";
		String database_name= "Kulfi";
		String user = "PROMETHEUS_DB_SORBET";
		String pass = "PrOm_SorBeT_DB";
		String hostname ="kulfi.c2nmjbfxq71p.us-east-1.rds.amazonaws.com";
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select FIRST_NAME,LAST_NAME from Account where email = '"+Directory.Coaches_Member_Profile_186455_mailid+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			String firstname = rs.getString("FIRST_NAME");
			System.out.println(firstname);
			String lastname = rs.getString("LAST_NAME");
			System.out.println(lastname);
			if(inputFirstName.equals(firstname))
			{
				System.out.println("First name from the database and coach portal is matched");
			}
			else
			{
				System.out.println("First name from the database and coach portal is not matched");
				Assert.fail();
			}
			if(inputLastName.equals(lastname))
			{
				System.out.println("Last name from the database and coach portal is matched");
			}
			else
			{
				System.out.println("Last name from the database and coach portal is not matched");
				Assert.fail();
			}
		}
		return allfields;
	}
	/**
	 * Name :     Abinaya
	 * Created Date:   09/Feb/16
	 * Modified Date:
	 * Description :   Create a common method to verify the client search from the HFOPS
	 * Ticket ID :
	 * Testcase sheet:SystemUser_Member_SelectClientInTheSearchFilterEnterValidClientName.xlsx
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void verifyClientSearch(WebDriver driver, String retrievedclientname)
	{
		String retrievedclientname1=retrievedclientname;
		for(int i=1;i<=5;i++)
		{
			String clientnames=driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td[5]")).getText();
			System.out.println("retrievedclientname1: "+retrievedclientname1);
			System.out.println("clientnames: "+clientnames);
			if(retrievedclientname1.equals(clientnames))
			{
				System.out.println("Input and Searched client name are same");
			}
			else
			{
				System.out.println("Input and Searched client name are not same");
				Assert.fail();
			}
		}
	}
	/**
	 * Name :     Abinaya
	 * Created Date:   28/Mar/16
	 * Modified Date:
	 * Description :   Create a common method to verify member ascending order from the HFOPS
	 * Ticket ID :
	 * Testcase sheet:
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String verifyMembersNameAscendingOrder(WebDriver driver)
	{
		int count=driver.findElements(By.xpath("//div[@id='pageTableContainer']/table/tbody/tr[*]/td[1]")).size();
		System.out.println("Count value" +count);
		String[] members = new String[count];
		for (int i=0;i<count;i++)
		{
			String member1=driver.findElement(By.xpath("//div[@id='pageTableContainer']/table/tbody/tr["+(i+1)+"]/td[1]")).getText();
			members[i]=member1;
		}
		String[] sort=members;
		Arrays.sort(sort);
		int i=0;
		for (i=0; i< sort.length;i++)
		{
			System.out.println("get sorted value:"+sort[i]);
		}
		if(Arrays.equals(sort, members))
		{
			return "Sorted and Matched";
		}
		else
		{
			return "Sorted and not Matched";
		}
	}
	/**
	 * Name :     Abinaya
	 * Created Date:   28/Mar/16
	 * Modified Date:
	 * Description :   Create a common method to verify email order from the HFOPS
	 * Ticket ID :
	 * Testcase sheet:
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String verifyMembersEmailOrders(WebDriver driver)
	{
		int count=driver.findElements(By.xpath("//div[@id='pageTableContainer']/table/tbody/tr[*]/td[2]")).size();
		System.out.println("Count value" +count);
		String[] members = new String[count];
		for (int i=0;i<count;i++)
		{
			String member1=driver.findElement(By.xpath("//div[@id='pageTableContainer']/table/tbody/tr["+(i+1)+"]/td[2]")).getText();
			members[i]=member1;
		}
		String[] sort=members;
		Arrays.sort(sort);
		int i=0;
		for (i=0; i< sort.length;i++)
		{
			System.out.println("get sorted value:"+sort[i]);
		}
		if(Arrays.equals(sort, members))
		{
			return "Sorted and Matched";
		}
		else
		{
			return "Sorted and not Matched";
		} }
	/**
	 * Name :     Abinaya
	 * Created Date:   28/Mar/16
	 * Modified Date:
	 * Description :   Create a common method to verify program order from the HFOPS
	 * Ticket ID :
	 * Testcase sheet:
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String verifyMemberProgramOrders(WebDriver driver)
	{
		int count=driver.findElements(By.xpath("//div[@id='pageTableContainer']/table/tbody/tr[*]/td[3]")).size();
		System.out.println("Count value" +count);
		String[] members = new String[count];
		for (int i=0;i<count;i++)
		{
			String member1=driver.findElement(By.xpath("//div[@id='pageTableContainer']/table/tbody/tr["+(i+1)+"]/td[3]")).getText();
			members[i]=member1;
		}
		String[] sort=members;
		Arrays.sort(sort);
		int i=0;
		for (i=0; i< sort.length;i++)
		{
			System.out.println("get sorted value:"+sort[i]);
		}
		if(Arrays.equals(sort, members))
		{
			return "Sorted and Matched";
		}
		else
		{
			return "Sorted and not Matched";
		}
	}
	public static String verifyMemberClassOrders(WebDriver driver)
	{
		int count=driver.findElements(By.xpath("//div[@id='pageTableContainer']/table/tbody/tr[*]/td[4]")).size();
		System.out.println("Count value" +count);
		String[] members = new String[count];
		for (int i=0;i<count;i++)
		{
			String member1=driver.findElement(By.xpath("//div[@id='pageTableContainer']/table/tbody/tr["+(i+1)+"]/td[4]")).getText();
			members[i]=member1;
		}
		String[] sort=members;
		Arrays.sort(sort);
		int i=0;
		for (i=0; i< sort.length;i++)
		{
			System.out.println("get sorted value:"+sort[i]);
		}
		if(Arrays.equals(sort, members))
		{
			return "Sorted and Matched";
		}
		else
		{
			return "Sorted and not Matched";
		}
	}
	public static String verifyMemberCoachOrders(WebDriver driver)
	{
		int count=driver.findElements(By.xpath("//div[@id='pageTableContainer']/table/tbody/tr[*]/td[5]")).size();
		System.out.println("Count value" +count);
		String[] members = new String[count];
		for (int i=0;i<count;i++)
		{
			String member1=driver.findElement(By.xpath("//div[@id='pageTableContainer']/table/tbody/tr["+(i+1)+"]/td[5]")).getText();
			members[i]=member1;
		}
		String[] sort=members;
		Arrays.sort(sort);
		int i=0;
		for (i=0; i< sort.length;i++)
		{
			System.out.println("get sorted value:"+sort[i]);
		}
		if(Arrays.equals(sort, members))
		{
			return "Sorted and Matched";
		}
		else
		{
			return "Sorted and not Matched";
		}
	}
	public static String verifyMemberClientOrders(WebDriver driver)
	{
		int count=driver.findElements(By.xpath("//div[@id='pageTableContainer']/table/tbody/tr[*]/td[8]")).size();
		System.out.println("Count value" +count);
		String[] members = new String[count];
		for (int i=0;i<count;i++)
		{
			String member1=driver.findElement(By.xpath("//div[@id='pageTableContainer']/table/tbody/tr["+(i+1)+"]/td[8]")).getText();
			members[i]=member1;
		}
		String[] sort=members;
		Arrays.sort(sort);
		int i=0;
		for (i=0; i< sort.length;i++)
		{

			System.out.println("get sorted value:"+sort[i]);

		}
		if(Arrays.equals(sort, members))
		{
			return "Sorted and Matched";
		}
		else
		{
			return "Sorted and not Matched";
		}
	}
	public static String verifyMemberMemberIdOrders(WebDriver driver)
	{
		int count=driver.findElements(By.xpath("//div[@id='pageTableContainer']/table/tbody/tr[*]/td[9]")).size();
		System.out.println("Count value" +count);
		String[] members = new String[count];
		for (int i=0;i<count;i++)
		{
			String member1=driver.findElement(By.xpath("//div[@id='pageTableContainer']/table/tbody/tr["+(i+1)+"]/td[9]")).getText();
			members[i]=member1;
		}
		String[] sort=members;
		Arrays.sort(sort);
		int i=0;
		for (i=0; i< sort.length;i++)
		{
			System.out.println("get sorted value:"+sort[i]);
		}
		if(Arrays.equals(sort, members))
		{
			return "Sorted and Matched";
		}
		else
		{
			return "Sorted and not Matched";
		}
	}
	public static String verifyMemberAccountStatusOrders(WebDriver driver)
	{
		int count=driver.findElements(By.xpath("//div[@id='pageTableContainer']/table/tbody/tr[*]/td[6]")).size();
		System.out.println("Count value" +count);
		String[] members = new String[count];
		for (int i=0;i<count;i++)
		{
			String member1=driver.findElement(By.xpath("//div[@id='pageTableContainer']/table/tbody/tr["+(i+1)+"]/td[6]")).getText();
			members[i]=member1;
		}
		String[] sort=members;
		Arrays.sort(sort);
		int i=0;
		for (i=0; i< sort.length;i++)
		{
			System.out.println("get sorted value:"+sort[i]);
		}
		if(Arrays.equals(sort, members))
		{
			return "Sorted and Matched";
		}
		else
		{
			return "Sorted and not Matched";
		}
	}
	public static String verifyMemberWeightChangeOrders(WebDriver driver)
	{
		int count=driver.findElements(By.xpath("//div[@id='pageTableContainer']/table/tbody/tr[*]/td[10]")).size();
		System.out.println("Count value" +count);
		String[] members = new String[count];
		for (int i=0;i<count;i++)
		{
			String member1=driver.findElement(By.xpath("//div[@id='pageTableContainer']/table/tbody/tr["+(i+1)+"]/td[10]")).getText();
			members[i]=member1;
		}
		String[] sort=members;
		Arrays.sort(sort);
		int i=0;
		for (i=0; i< sort.length;i++)
		{
			System.out.println("get sorted value:"+sort[i]);
		}
		if(Arrays.equals(sort, members))
		{
			return "Sorted and Matched";
		}
		else
		{
			return "Sorted and not Matched";
		}
	}
	public static String verifyMemberStartingWeightOrders(WebDriver driver)
	{
		int count=driver.findElements(By.xpath("//div[@id='pageTableContainer']/table/tbody/tr[*]/td11]")).size();
		System.out.println("Count value" +count);
		String[] members = new String[count];
		for (int i=0;i<count;i++)
		{
			String member1=driver.findElement(By.xpath("//div[@id='pageTableContainer']/table/tbody/tr["+(i+1)+"]/td[11]")).getText();
			members[i]=member1;
		}
		String[] sort=members;
		Arrays.sort(sort);
		int i=0;
		for (i=0; i< sort.length;i++)
		{
			System.out.println("get sorted value:"+sort[i]);
		}
		if(Arrays.equals(sort, members))
		{
			return "Sorted and Matched";
		}
		else
		{
			return "Sorted and not Matched";
		}
	}
	/**
	 * Name         :   Suresh V
	 * Created Date :   29March2016
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String emailCharactersLimitationVerification(WebDriver driver,String inputData, String id) throws ClassNotFoundException, SQLException
	{
		//input declaration:

		String maximumMailCharacters=inputData;
		String lastName=id;
		//int inputMailCharactersLength=maximumMailCharacters.length();

		WebElement emailTextBox = driver.findElement(By.xpath(OR.MEMBER_PROFILE_MAIL));
		String maxEmail = Manipulation.dynamicSendkeys(driver, maximumMailCharacters, emailTextBox);
		System.out.println("Character Length="+maxEmail.length());
		//clearAndType(emailTextBox,maximumMailCharacters);
		WebElement saveButton = driver.findElement(By.xpath(OR.MEMBER_PROFILE_CONTACT_SAVE));
		try {

			click(saveButton);
			wait(driver, "5");
		}
		catch(Exception e4)
		{   
			WebElement emailCheckbox = driver.findElement(By.xpath(OR.MEMBER_PROFILE_EMAIL_CHECKBOX));
			click(emailCheckbox);
			click(saveButton);
			wait(driver, "5");
		}

		WebElement successMessageOkButton = driver.findElement(By.xpath(OR.HFOPS_MEMBER_PROFILE_CONTACT_INFO_EDIT_SUCCESS_MESSAGE));
		click(successMessageOkButton);
		wait(driver, "2");
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
		ResultSet rs = stat.executeQuery("select EMAIL from ACCOUNT where id = '"+lastName+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			String email = rs.getString("EMAIL");
			System.out.println("EMAIL="+email);

			if ((email.equals(maximumMailCharacters)))
			{
				System.out.println("Email character length is more than 100");
				Assert.fail();
			}
			else
			{
				System.out.println("Email character length is less than or equal to 100");
			}
		}
		return allfields;
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   26/Feb/16
	 * Modified Date:
	 * Description :   Create a common method to retrieve address of the member
	 * Ticket ID :
	 * Testcase sheet:SystemUser_Member_InThePAProfileWhenTheNameIsEditedTheEditedNameMustBeReflectedInTheMemberDetailsInTheCoachMemberProfilememberList.xlsx
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String street="";
	public static String retrieveAddressOfTheMember(WebDriver driver, String mailId) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select MAILING_STREET from ACCOUNT where email = '"+mailId+"' ");
		System.out.println("query executed");
		while(rs.next())
		{
			street=rs.getString("MAILING_STREET");
			System.out.println("MAILING_STREET:"+street);
		}
		return street;
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   26/Feb/16
	 * Modified Date:
	 * Description :   Create a common method to retrieve address1 of the member
	 * Ticket ID :
	 * Testcase sheet:SystemUser_Member_InThePAProfileWhenTheNameIsEditedTheEditedNameMustBeReflectedInTheMemberDetailsInTheCoachMemberProfilememberList.xlsx
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String street1="";
	public static String retrieveAddress1OfTheMember(WebDriver driver, String mailId) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select MAILING_STREET2 from ACCOUNT where email = '"+mailId+"' ");
		System.out.println("query executed");
		while(rs.next())
		{
			street1=rs.getString("MAILING_STREET2");
			System.out.println("MAILING_STREET2:"+street1);
		}
		return street1;
	}
	/**
	 * Name :     Suresh.v
	 * Created Date:   31/Mar/16
	 * Modified Date:
	 * Description :   Create a common method to format DOB from the member profile of the HFOPS
	 * Ticket ID :
	 * Testcase sheet:SystemUser_Member_INThePAMemberProfileChangeTheDOBAndCheckIfTheDOBIsReflectedInThePACoachMemberDetailsAndInThePAProfileUnderPersonalInfo
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String OpsAdmin_DOB;
	public static String Member_DOB;
	public static String memberProfileDOB(WebDriver driver)
	{
		WebElement profileday = driver.findElement(By.xpath(OR.MEMBER_LOGIN_PROFILE_DATE));
		WebElement profilemonth = driver.findElement(By.xpath(OR.MEMBER_LOGIN_PROFILE_MONTH));
		WebElement profileyear = driver.findElement(By.xpath(OR.MEMBER_LOGIN_PROFILE_YEAR));
		String day= profileday.getText();
		String month= profilemonth.getText();
		String year= profileyear.getText();
		Navigate.waitTime(driver, "10");
		String Member_DOB = month+"/"+day+"/"+year;
		System.out.println(Member_DOB);
		return Member_DOB;
	}
	/**
	 * Name :     Suresh.v
	 * Created Date:   31/Mar/16
	 * Modified Date:
	 * Description :   Create a common method to format DOB of the member profile
	 * Ticket ID :
	 * Testcase sheet:
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
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
		Navigate.waitTime(driver, "10");
		click(profile_month);
		click(profile_month_select);
		Navigate.waitTime(driver, "10");
		click(profile_year);
		click(profile_year_select);
		Navigate.waitTime(driver, "10");
		click(profile_save_button);
		Navigate.waitTime(driver, "10");
		String day=profile_day_select.getText();
		String month=profile_month_select.getText();
		String year=profile_year_select.getText();
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
	 */
	public static String compareDOB(WebDriver driver)
	{
		if(OpsAdmin_DOB==Member_DOB)
		{
			return "OpsAdmin edited DOB and Member profile DOB are matched";
		}
		else
		{
			return "OpsAdmin edited DOB and Member profile DOB are not matched";
		}
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   01/Mar/16
	 * Modified Date:
	 * Description :   Create a common method to retrieve screen name of the member
	 * Ticket ID :
	 * Testcase SystemUser_Member_InThePAMemberProfileClickTheEditOfTheMemberInformationDivisionEnterManyCharactersAsPossibleInTheFNAndLNFields.xlsx
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String retrieveScreenNameOfTheMember(WebDriver driver, String screenName2) throws ClassNotFoundException, SQLException
	{
		String screenname ="";
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
		ResultSet rs = stat.executeQuery("select SCREEN_NAME from ACCOUNT where email = '"+screenName2+"' ");
		System.out.println("query executed");
		while(rs.next())
		{
			screenname=rs.getString("SCREEN_NAME");
			System.out.println("SCREEN_NAME "+screenname);
		}
		return screenname;
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   04/Apr/16
	 * Modified Date:
	 * Description :   Create a common method to retrieve screen name of the member
	 * Ticket ID :
	 * Testcase SystemUser_Member_InThePAMemberProfileClickTheEditOfTheMemberInformationDivisionEnterManyCharactersAsPossibleInTheFNAndLNFields.xlsx
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String securityquestion="";
	public static String securityanswer="";
	public static String retrieveSecurityOfTheMember(WebDriver driver, String securityans1) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select SECURITY_QUESTION,SECURITY_ANSWER from ACCOUNT where email = '"+securityans1+"' ");
		System.out.println("query executed");
		while(rs.next())
		{
			securityquestion=rs.getString("SECURITY_QUESTION");
			System.out.println("SCREEN_NAME "+securityquestion);
			securityanswer=rs.getString("SECURITY_ANSWER");
			System.out.println("SCREEN_NAME "+securityanswer);
		}
		return (securityquestion+","+securityanswer);
	}

	/**
	 * Name :     Bharath Kumar
	 * Created Date:   17/Feb/17
	 * Modified Date:
	 * Description :   To get the the security question and answer for the member from the DB
	 * Required Inputs :
	 * @throws ParseException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */	
	public  static String retrieveSecurityInfoOfTheMember(WebDriver driver,String inputdata,String emailId) throws ParseException, ClassNotFoundException, SQLException
	{
		String availableMemberSecurityInfo="";
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
		ResultSet rs = stat.executeQuery("select SECURITY_QUESTION,SECURITY_ANSWER from ACCOUNT where email = '"+emailId+"' ");
		System.out.println("query executed");
		System.out.println("input"+inputdata);
		if(inputdata.equalsIgnoreCase("SECURITY_QUESTION"))
		{
			if(rs.next())
			{
				availableMemberSecurityInfo  = rs.getString("SECURITY_QUESTION");
				System.out.println("Available Member Security Question "+availableMemberSecurityInfo+" is retrieved from the Table");
			}
		}
		else if(inputdata.equalsIgnoreCase("SECURITY_ANSWER"))
		{
			if(rs.next())
			{
				availableMemberSecurityInfo  = rs.getString("SECURITY_ANSWER");
				System.out.println("Available Member Security Answer "+availableMemberSecurityInfo+" is retrieved from the Table");
			}
		}
		return availableMemberSecurityInfo;
	}

	public static String verifyMemberOnboardedstatusOrders(WebDriver driver)
	{
		int count=driver.findElements(By.xpath("//div[@id='pageTableContainer']/table/tbody/tr[*]/td[7]")).size();
		System.out.println("Count value" +count);
		String[] members = new String[count];
		for (int i=0;i<count;i++)
		{
			String member1=driver.findElement(By.xpath("//div[@id='pageTableContainer']/table/tbody/tr["+(i+1)+"]/td[7]")).getText();
			members[i]=member1;
		}
		String[] sort=members;
		Arrays.sort(sort);
		int i=0;
		for (i=0; i< sort.length;i++)
		{
			System.out.println("get sorted value:"+sort[i]);
		}
		if(Arrays.equals(sort, members))
		{
			return "Sorted and Matched";
		}
		else
		{
			return "Sorted and not Matched";
		}
	}
	/**
	 * Name         :   Suresh.V
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String verifyMemberDateOfBirthUsingDB(WebDriver driver,String testData) throws ClassNotFoundException, SQLException
	{
		String[] testData1 = testData.split(",");
		String dateOfBirthMaximumLimit=testData1[0];
		String lastName=testData1[1];
		String sortEmail=testData1[2];
		String dateOfBirthMinimumLimit=testData1[3];
		String port = "1521";
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		String allfields="";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select BIRTH_DT from ACCOUNT where LAST_NAME = '"+lastName+"'");
		System.out.println("query executed");
		String dateOfBirth = "";
		while(rs.next())
		{
			dateOfBirth = rs.getString("BIRTH_DT");
			System.out.println("DateOfBirth="+dateOfBirth);
		}
		conn.close();
		WebElement memberSortDropDown = driver.findElement(By.xpath(OR.PATIENT_SORT_DROPDOWN));
		selectByValue(memberSortDropDown, sortEmail);
		WebElement textBox = driver.findElement(By.xpath(OR.SERACH_TEXT_BOX_ACTIVE));
		clearAndType(textBox,lastName);
		wait(driver, "1");
		WebElement submitButton = driver.findElement(By.xpath(OR.PATIENT_SEARCH_SUBMIT_BUTTON));
		click(submitButton);
		wait(driver, "2");
		WebElement memberNameLink = driver.findElement(By.xpath(OR.SEARCHED_MEMBER1));
		click(memberNameLink);
		wait(driver, "5");
		WebElement dateOfBirthEditButton = driver.findElement(By.xpath(OR.DOB_EDIT_LINK));
		WebElement dateOfYear = driver.findElement(By.xpath(OR.MEMBER_PROFILE_YEAR));
		click(dateOfBirthEditButton);
		wait(driver, "1");
		selectByValue(dateOfYear, dateOfBirthMaximumLimit);
		WebElement saveButton = driver.findElement(By.xpath(OR.MEMBER_PROFILE_DOB_SAVE));
		click(saveButton);
		WebElement alertMessage = driver.findElement(By.xpath(OR.INVALID_DOB_ERROR_MSG));
		verifyElementIsPresent(driver, alertMessage);
		System.out.println(alertMessage.getText());
		selectByValue(dateOfYear, dateOfBirthMinimumLimit);
		click(saveButton);
		verifyElementIsPresent(driver, alertMessage);
		System.out.println(alertMessage.getText());
		click(dateOfBirthEditButton);
		wait(driver, "2");
		Connection conn1 = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success 1");
		Statement stat1=conn1.createStatement();
		ResultSet rs1 = stat1.executeQuery("select BIRTH_DT from ACCOUNT where LAST_NAME = '"+lastName+"'");
		System.out.println("query executed 1");
		String getDateOfBirthAfterUnEdit = "";
		while(rs1.next())
		{
			getDateOfBirthAfterUnEdit = rs1.getString("BIRTH_DT");
			System.out.println("DateOfBirth 1="+getDateOfBirthAfterUnEdit);
		}
		Assert.assertEquals(dateOfBirth, getDateOfBirthAfterUnEdit);
		conn.close();
		return allfields;
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   01/Mar/16
	 * Modified Date:
	 * Description :   Create a common method to retrieve screen name of the member
	 * Ticket ID :
	 * Testcase SystemUser_Member_InThePAMemberProfileClickTheEditOfTheMemberInformationDivisionEnterManyCharactersAsPossibleInTheFNAndLNFields.xlsx
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String emailphonesmsenable="";
	public static String retrieveemailphonesmsenableOfTheMember(WebDriver driver, String emailPhonesmsEnable1) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select MAIN_EMAIL_AGREE_CONTACT,MAIN_PHONE_AGREE_CONTACT,MAIN_SMS_AGREE_CONTACT from account_contact where account_id in (select id from account where email like '%"+emailPhonesmsEnable1+"%')");
		System.out.println("query executed");
		while(rs.next())
		{
			String mail_agree=rs.getString("MAIN_EMAIL_AGREE_CONTACT");
			String phone_agree=rs.getString("MAIN_PHONE_AGREE_CONTACT");
			String sms_agree=rs.getString("MAIN_SMS_AGREE_CONTACT");
			emailphonesmsenable=mail_agree+","+phone_agree+","+sms_agree;
			System.out.println("Status of agree for email,phone and sms :" +emailphonesmsenable);
		}
		return emailphonesmsenable;
	}
	/**
	 * Name :    Suresh V
	 * Created Date:   08/April/16
	 * Modified Date:
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void coachNameVerify(WebDriver driver, String testData)
	{
		String[] testData1 = testData.split(",");
		String filterOption=testData1[0];
		String coachName=testData1[1];
		WebElement memberRequestFilter = driver.findElement(By.xpath(OR.OPS_MEMBER_REQUEST_SEARCHBY));
		selectByValue(memberRequestFilter, filterOption);
		WebElement textBox = driver.findElement(By.xpath(OR.MEMBER_REQUEST_SAERCH_TEXTBOX));
		WebElement submitIcon = driver.findElement(By.xpath(OR.SEARCH_SUBMIT_BUTTON));
		clearAndType(textBox,coachName);
		wait(driver, "1");
		click(submitIcon);
		wait(driver, "2");
		WebElement outputCoachName = driver.findElement(By.xpath(OR.SEARCHRESULT_COACH_NAME));
		String outPutCoach = outputCoachName.getText();
		System.out.println(outPutCoach);
		click(outputCoachName);
		wait(driver, "10");
		WebElement coachNameHeader = driver.findElement(By.xpath(OR.COACH_NAME_HEADER));
		String coachHeader=coachNameHeader.getText();
		System.out.println(coachHeader);
		String[] coachHeaderSplit=coachHeader.split("Editing Profile of Coach");
		String header;
		header=coachHeaderSplit[1].trim();
		System.out.println(header);
		Assert.assertEquals(outPutCoach, header);
	}
	public static String verifyMembersRequestDateOrders(WebDriver driver)
	{
		int count=driver.findElements(By.xpath("//table[@id='memberReq']/tbody/tr[*]/td[1]")).size();
		System.out.println("Count value" +count);
		String[] members = new String[count];
		for (int i=0;i<count;i++)
		{
			String member1=driver.findElement(By.xpath("//table[@id='memberReq']/tbody/tr["+(i+1)+"]/td[1]")).getText();
			members[i]=member1;
		}
		String[] sort=members;
		Arrays.sort(sort);
		int i=0;
		for (i=0; i< sort.length;i++)
		{
			System.out.println("get sorted value:"+sort[i]);
		}
		if(Arrays.equals(sort, members))
		{
			return "Sorted and Matched";
		}
		else
		{
			return "Sorted and not Matched";
		}
	}
	/**
	 * Name :Vinothkumar.m
	 * Created Date:   11/April/16
	 * Modified Date: Create a common method to Target Weight
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String targetWeight(WebDriver driver )
	{
		String TargetWeight = driver.findElement( By.xpath( OR.TRACKING_SUBTAB_PROGRESS_TAB_TARGET_WEIGHT )).getText();
		String s[]=TargetWeight.split("TARGET:");
		String s1=s[1].trim();
		System.out.println("reference split1:"+s1);
		return s1;
	}
	/**
	 * Name :Vinothkumar.m
	 * Created Date:   11/April/16
	 * Modified Date: Create a common method to Half page down
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void halfPageDown(WebDriver driver)  {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,700)", "");
	}
	public static String verifyMembersRequestDateAscendingOrders(WebDriver driver) throws ParseException, java.text.ParseException
	{
		int count=driver.findElements(By.xpath("//table[@id='memberReq']/tbody/tr[*]/td[1]")).size();
		String[] members = new String[count];
		String[] members1 = new String[count];
		for (int i=0;i<count;i++)
		{
			String member1=driver.findElement(By.xpath("//table[@id='memberReq']/tbody/tr["+(i+1)+"]/td[1]")).getText();
			members[i]=member1;
			SimpleDateFormat ft = new SimpleDateFormat("E MMM dd yyyy");
			java.util.Date t=ft.parse(members[i]);
			ft.applyPattern("MM-dd-yyyy");
			System.out.println("After convertion :" +ft.format(t));
			members1[i]=ft.format(t);
		}
		String[] sort=members1;
		Arrays.sort(sort);
		int i=0;
		for (i=0; i< sort.length;i++)
		{
			System.out.println("sorted in ascending order::"+sort[i]);
		}
		if(Arrays.equals(sort,members1))
		{
			return "Sorted and Matched";
		}
		else
		{
			return "Sorted and not Matched";
		}
	}
	public static String verifyMembersRequestDateDescendingOrders(WebDriver driver) throws ParseException, java.text.ParseException
	{
		int count=driver.findElements(By.xpath("//table[@id='memberReq']/tbody/tr[*]/td[1]")).size();
		System.out.println("Count value" +count);
		String[] members = new String[count];
		String[] members1 = new String[count];
		for (int i=0;i<count;i++)
		{
			String member1=driver.findElement(By.xpath("//table[@id='memberReq']/tbody/tr["+(i+1)+"]/td[1]")).getText();
			members[i]=member1;
			SimpleDateFormat ft = new SimpleDateFormat("E MMM dd yyyy");
			java.util.Date t=ft.parse(members[i]);
			ft.applyPattern("MM-dd-yyyy");
			System.out.println("After convertion :" +ft.format(t));
			members1[i]=ft.format(t);
		}
		String[] sort=members1;
		Arrays.sort(sort, Collections.reverseOrder());
		int i=0;
		for (i=0; i< sort.length;i++)
		{
			System.out.println("sorted in descending order::"+sort[i]);
		}
		if(members1==sort)
		{
			return "Sorted and Matched";
		}
		else
		{
			return "Sorted and not Matched";
		}
	}
	/**
	 * Name :Vigneshraj.M
	 * Created Date:11/Apr/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve the insurance information from database
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String hFOPsValidationDbMemberInsurance(WebDriver driver) throws ClassNotFoundException, SQLException
	{
		WebElement NameTextBox= driver.findElement(By.xpath("//div/span[text()='Carrier']/following::input[@class='form-control']"));
		String carrierNmae=NameTextBox.getAttribute("value");
		WebElement Memberid= driver.findElement(By.xpath("//div/span[text()='Member ID']/following::input[@class='form-control']"));
		String MemberID=Memberid.getAttribute("value");
		WebElement groupid= driver.findElement(By.xpath("//div/span[text()='Group ID']/following::input[@class='form-control']"));
		String GroupID=groupid.getAttribute("value");
		String Insurance_information=(carrierNmae+","+MemberID+","+GroupID);
		System.out.println(Insurance_information);
		//Data base Script
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
		ResultSet rs = stat.executeQuery("select INS.NAME, AIC.POLICY_NUMBER, AIC.INS_MEMBER_ID, AIC.ACCOUNT_ID from INS_CARRIER INS, ACCOUNT_INS_CARRIER AIC where INS.ID = AIC.INSURANCE_CARRIER_ID And AIC.account_id=(select ID from account where email ='QAMMR001@yopmail.com')");
		System.out.println("query executed");
		while(rs.next())
		{
			String DBcarriername = rs.getString("NAME");
			String DBmembergroupid  = rs.getString("POLICY_NUMBER");
			String DBmemberid = rs.getString("INS_MEMBER_ID");
			String DB_Insurance_information=(DBcarriername+","+DBmemberid+","+DBmembergroupid);
			System.out.println(DB_Insurance_information);
			if(Insurance_information.equals(DB_Insurance_information))
			{
				return "The insurance tab informations and  database endrollment values are  matched";
			}
			else
			{
				return "The insurance tab informations and  database endrollment values are not matched";
			}
		}
		return allfields;
	}
	/**
	 * Name :Vigneshraj.M
	 * Created Date:11/Apr/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve the orbera member to schedule 1 on 1 session from provider
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String getOrberaMember1on1SessionFromProv(WebDriver driver) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("SELECT ACNT.EMAIL Member_Email, PROV.EMAIL Coach_Email FROM SUMMARY_ACCOUNT_TODATE SMRY, PROVIDER PROV, ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID AND SMRY.ACCOUNT_ID=ACNT.ID AND ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ID IN (SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_TYPE='1on1' AND SESSION_STATUS IN ('Unscheduled') AND ACCOUNT_PROGRAM_ID IN (SELECT ID FROM ACCOUNT_PROGRAM WHERE ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM SUMMARY_ACCOUNT_TODATE WHERE MAST_PROGRAM_ID='30100AA52BEA' AND START_DT_TIME>SYSDATE + INTERVAL'-30' DAY)))) ORDER BY SMRY.CREATED_DT ASC");
		System.out.println("query executed");
		String member_mail1="";
		while(rs.next())
		{
			member_mail1 = rs.getString("Member_Email");
		}
		return member_mail1;
	}
	/**
	 * Name :     LEENA P.
	 * Created Date:   23/Sep/16
	 * Modified Date:
	 * Description :   Create a common method to verify find a member in Orbera Post Placement program
	 * Ticket ID :
	 * Required Inputs :
	 */
	public static String getOrberaMemberFromSearchResults(WebDriver driver)
	{
		try
		{
			Manipulation.wait(driver, "5");
			WebElement orberaMember = driver.findElement(By.xpath(OR.PROVIDER_ORBERA_POST_PLACEMENT_MEMBER));
			Manipulation.click(orberaMember);
			String memberName = ElementActions.getText(orberaMember);
			return memberName;
		}
		catch(Exception e)
		{
			WebElement nextPageNavigator = driver.findElement(By.xpath(OR.NEXT_PAGE_ARROW));
			Manipulation.click(nextPageNavigator);
			WebElement orberaMember = driver.findElement(By.xpath(OR.PROVIDER_ORBERA_POST_PLACEMENT_MEMBER));
			Manipulation.click(orberaMember);
			String memberName = ElementActions.getText(orberaMember);
			return memberName;
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
	public static String getMemberLastOneOnOneSession30DaysAgo(WebDriver driver) throws ClassNotFoundException, SQLException
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
	public static String getMemberWeightGainOfMoreThan7LBSIn1Day(WebDriver driver) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select EMAIL from account where id in (select COMMON_USER_ID from user_alerts where MP_ALERT_ID = (select id from mp_alerts where alert_id = (select id from platform_alerts where name = 'Weight gain of more than ''x'' lbs in ''y'' days')) and IS_VALID = 1)");
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
	public static String getMemberBallonNotRemovedAfter7MonthsInsertionDate(WebDriver driver) throws ClassNotFoundException, SQLException
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
		if(rs.next())
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
	public static String getMemberRemovalDateNotYetScheduled(WebDriver driver) throws ClassNotFoundException, SQLException
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
	/**
	 * Name :Vinothkumar.M
	 * Created Date:09/Dec/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve members who have Starting weight, Weight change, Last Session type, Last Session date
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws java.text.ParseException
	 */
	public static String getMemberConfigurableColumnStartingWeightandWeightchange(WebDriver driver, String WeightLoss,String StartingWeight,String LastSessionType,String LastSessionDate) throws ClassNotFoundException, SQLException, java.text.ParseException
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
		ResultSet rs = stat.executeQuery("select A. EMAIL, SAE.WEIGHT_NET_CHANGE, SAE.WEIGHT_STARTING, SAE.LAST_SESSION_ATTENDED_TYPE, SAE.LAST_SESSION_ATTENDED_DT  from ACCOUNT A, SUMMARY_ACCOUNT_EXTENSION SAE WHERE A.ID= SAE.ACCOUNT_ID AND SAE.LAST_MODIFIED_DT<SYSDATE AND A.EMAIL NOT LIKE '%api%' AND SAE.WEIGHT_STARTING IS NOT NULL AND SAE.WEIGHT_NET_CHANGE IS NOT NULL AND SAE.LAST_SESSION_ATTENDED_TYPE IS NOT NULL AND SAE.LAST_SESSION_ATTENDED_DT IS NOT NULL and A.ORGANIZATION_ID='"+Directory.Orbera_Configurablecolumn_OrganizationID+"'");
		System.out.println("query executed");
		String member_mail1="";
		String weightnet_change="";
		String weight_starting="";
		String last_session_attended_type="";
		String last_session_attended_dt="";
		String last_Sessionattended_date="";
		if(rs.next())
		{
			member_mail1 = rs.getString("EMAIL");
			System.out.print(member_mail1);
			weightnet_change =rs.getString("WEIGHT_NET_CHANGE");
			if (weightnet_change.contains("null"))
			{
				weightnet_change.replaceAll(weightnet_change, " ");
			}
			System.out.print(weightnet_change);
			weight_starting =rs.getString("WEIGHT_STARTING");
			if (weight_starting.contains("null"))
			{
				weight_starting.replaceAll(weight_starting, " ");
			}
			System.out.print(weight_starting);
			last_session_attended_type =rs.getString("LAST_SESSION_ATTENDED_TYPE");
			if (last_session_attended_type.contains("null"))
			{
				last_session_attended_type.replaceAll(last_session_attended_type, " ");
			}
			System.out.print(last_session_attended_type);
			last_session_attended_dt =rs.getString("LAST_SESSION_ATTENDED_DT");
			System.out.print(last_session_attended_dt);
			String[] lastSessionattendedtime=last_session_attended_dt.split(" ");
			String lastSessionattendedtime1=lastSessionattendedtime[0];
			System.out.println("lastSessionattendedtime1: "+lastSessionattendedtime1);
			//Converting the collected date
			DateFormat formatter = null;
			Date convertedDate = null;
			String Collected_Date= lastSessionattendedtime1;
			formatter =new SimpleDateFormat("yyyy-MM-dd");
			convertedDate =formatter.parse(Collected_Date);
			System.out.println("Date 1 : " + convertedDate);
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
			last_Sessionattended_date = DATE_FORMAT.format(convertedDate);
			if (last_Sessionattended_date.contains("null"))
			{
				last_Sessionattended_date.replaceAll(last_Sessionattended_date, " ");
			}
			System.out.println("Today in dd-MM-yyyy format : " + last_Sessionattended_date);
		}
		if (WeightLoss.equalsIgnoreCase(weightnet_change))
		{
			System.out.println("Weight Loss  are matched");
		}
		else
		{
			Assert.fail();
		}
		if (StartingWeight.equalsIgnoreCase(weight_starting))
		{
			System.out.println("Starting Weight are matched");
		}
		else
		{
			Assert.fail();
		}
		if (LastSessionType.equalsIgnoreCase(last_session_attended_type))
		{
			System.out.println("Last Session Type are matched");
		}
		else
		{
			Assert.fail();
		}
		if (LastSessionDate.equalsIgnoreCase(last_Sessionattended_date))
		{
			System.out.println("Last Session Date are matched");
		}
		else
		{
			Assert.fail();
		}
		return member_mail1;
	}
	/**
	 * Name :Vinothkumar.M
	 * Created Date:09/Dec/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve members who have Starting weight, Weight change, Last Session type, Last Session date
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws java.text.ParseException
	 */
	public static String getMemberWhoHaveStartingWeight(WebDriver driver) throws ClassNotFoundException, SQLException, java.text.ParseException
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
		ResultSet rs = stat.executeQuery("select A. EMAIL, SAE.WEIGHT_NET_CHANGE, SAE.WEIGHT_STARTING, SAE.LAST_SESSION_ATTENDED_TYPE, SAE.LAST_SESSION_ATTENDED_DT  from ACCOUNT A, SUMMARY_ACCOUNT_EXTENSION SAE WHERE A.ID= SAE.ACCOUNT_ID AND SAE.LAST_MODIFIED_DT<SYSDATE AND A.EMAIL NOT LIKE '%api%' AND SAE.WEIGHT_STARTING IS NOT NULL AND SAE.WEIGHT_NET_CHANGE IS NOT NULL AND SAE.LAST_SESSION_ATTENDED_TYPE IS NOT NULL AND SAE.LAST_SESSION_ATTENDED_DT IS NOT NULL and A.ORGANIZATION_ID='"+Directory.Orbera_Configurablecolumn_OrganizationID+"'");
		System.out.println("query executed");
		String member_mail1="";
		if(rs.next())
		{
			member_mail1 = rs.getString("EMAIL");
			System.out.print(member_mail1);
		}
		return member_mail1;
	}
	/**
	 * Name :Vinothkumar.M
	 * Created Date:09/Dec/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve members who have Placement date, Removal date, Assigned Coach, Practitioner name
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws java.text.ParseException
	 */
	public static String getMemberWhoHavePlacementDate(WebDriver driver) throws ClassNotFoundException, SQLException, java.text.ParseException
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
		ResultSet rs = stat.executeQuery("select A.EMAIL, SATD.ASSIGNED_PROVIDER_NAME, SATD.SUMMARY_EXTENSION from ACCOUNT A, SUMMARY_ACCOUNT_TODATE SATD where A.ID= SATD.ACCOUNT_ID AND SATD.ASSIGNED_PROVIDER_NAME IS NOT NULL AND SATD.SUMMARY_EXTENSION IS NOT NULL AND SATD.SUMMARY_EXTENSION LIKE '%medicalProcedureMilestone1%' AND A.EMAIL NOT LIKE '%api%' and A.ORGANIZATION_ID='"+Directory.Orbera_Configurablecolumn_OrganizationID+"'");
		System.out.println("query executed");
		String member_mail1="";
		if(rs.next())
		{
			member_mail1 = rs.getString("EMAIL");
			System.out.print(member_mail1);
		}
		return member_mail1;
	}
	/**
	 * Name : Bharath kumar.M
	 * Created Date:16/Dec/2016
	 * Modified Date:
	 * Description :   Created a common method to match Coach and Placement date for configurable columns
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws java.text.ParseException
	 */
	public static String getMemberConfigurableColumnCoachandPlacementDate(WebDriver driver, String Coach,String Placementdate) throws ClassNotFoundException, SQLException, java.text.ParseException
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
		ResultSet rs = stat.executeQuery("select A.EMAIL, SATD.ASSIGNED_PROVIDER_NAME, SATD.SUMMARY_EXTENSION from ACCOUNT A, SUMMARY_ACCOUNT_TODATE SATD where A.ID= SATD.ACCOUNT_ID AND SATD.ASSIGNED_PROVIDER_NAME IS NOT NULL AND SATD.SUMMARY_EXTENSION IS NOT NULL AND SATD.SUMMARY_EXTENSION LIKE '%medicalProcedureMilestone1%' AND A.EMAIL NOT LIKE '%api%'and A.ORGANIZATION_ID='"+Directory.Orbera_Configurablecolumn_OrganizationID+"'");
		System.out.println("query executed");
		String member_mail1="";
		String Coach1="";
		String Placementdate1="";
		String Placementdate2="";
		if(rs.next())
		{
			member_mail1 = rs.getString("EMAIL");
			System.out.println("Second member"+member_mail1);
			Coach1 = rs.getString("ASSIGNED_PROVIDER_NAME");
			System.out.println("Retrieved coach from DB"+Coach1);
			Placementdate1 = rs.getString("SUMMARY_EXTENSION");
			System.out.println("Retrieved placementdate from DB"+Placementdate1);
			String[] parts=Placementdate1.split(":");
			{
				String[] subpart=parts[1].split(",");
				{
					subpart[0]=subpart[0].replace('"', ' ');
					Placementdate2=subpart[0].trim();
				}
				System.out.println("Placementdate2"+Placementdate2);
			}
		}
		if(Coach.equalsIgnoreCase(Coach1))
		{
			System.out.println("Coach values are mached");
		}
		if(Placementdate.equalsIgnoreCase(Placementdate2))
		{
			System.out.println("Placementdate values are mached");
		}
		return member_mail1;
	}
	/**
	 * Name : Leena P.
	 * Created Date:22/Dec/2016
	 * Modified Date:
	 * Description :   Created a common method to retrieve VSPN Liver pre-evalualtion member
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws java.text.ParseException
	 */
	public static String getVSPNLiverMemberWithNoProvider(WebDriver driver) throws ClassNotFoundException, SQLException, java.text.ParseException
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
		ResultSet rs = stat.executeQuery("select EMAIL from ACCOUNT ACNT, SUMMARY_ACCOUNT_TODATE SMRY where ACNT.ID = SMRY.ACCOUNT_ID AND SMRY.ASSIGNED_PROVIDER_ID IS NULL AND ACNT.STATUS IN ('Active') AND ORGANIZATION_ID='406D2955CF4B6591E0530100007F178F' AND SMRY.MP_NAME IN ('Liver Pre-Evaluation') AND ACNT.EMAIL LIKE '%guerrillamail%'");
		System.out.println("query executed");
		String member_mail1="";
		while(rs.next())
		{
			member_mail1 = rs.getString("EMAIL");
		}
		conn.close();
		return member_mail1;
	}
	/**
	 * Name : Leena P.
	 * Created Date:22/Dec/2016
	 * Modified Date:
	 * Description :   Created a common method to retrieve VSPN Kidney pre-evalualtion member
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws java.text.ParseException
	 */
	public static String getVSPNKidneyMemberWithNoProvider(WebDriver driver) throws ClassNotFoundException, SQLException, java.text.ParseException
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
		ResultSet rs = stat.executeQuery("select EMAIL from ACCOUNT ACNT, SUMMARY_ACCOUNT_TODATE SMRY where ACNT.ID = SMRY.ACCOUNT_ID AND SMRY.ASSIGNED_PROVIDER_ID IS NULL AND ACNT.STATUS IN ('Active') AND ORGANIZATION_ID='4145D6EB67586107E0530100007F6223'  AND SMRY.MP_NAME IN ('Kidney Pre-Evaluation') AND ACNT.EMAIL LIKE '%guerrillamail%'");
		System.out.println("query executed");
		String member_mail1="";
		while(rs.next())
		{
			member_mail1 = rs.getString("EMAIL");
		}
		conn.close();
		return member_mail1;
	}
	
	
	/**
	 * Name         : Leena P.
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void selectClient1memberDOB(WebDriver driver) 
	{
		
		//WebElement dateOfBirthEditButton = driver.findElement(By.xpath(OR.DOB_EDIT_LINK));
		WebElement dateOfYear = driver.findElement(By.xpath(OR.MEMBER_PROFILE_YEAR));
		WebElement dateOfMonth = driver.findElement(By.xpath(OR.MEMBER_PROFILE_MONTH));
		WebElement dateOfDay = driver.findElement(By.xpath(OR.MEMBER_PROFILE_DAY));
		String doy = "number:"+Directory.RA_Enrollment_Year;
		System.out.println("date of year is:" +doy);
		String dom = "string:"+Directory.RA_Enrollment_Month;
		System.out.println("date of year is:" +dom);
		String dod = "string:"+Directory.RA_Enrollment_Day;
		
		wait(driver, "3");
		selectByValue(dateOfYear,doy);
		wait(driver, "3");
		selectByValue(dateOfMonth,dom);
		wait(driver, "3");
		click(dateOfDay);
		selectByVisibletext(dateOfDay,Directory.RA_Enrollment_Day);
		wait(driver, "3");
		
		WebElement saveButton = driver.findElement(By.xpath(OR.MEMBER_PROFILE_DOB_SAVE));
		click(saveButton);
		
	}
	
	/**
	 * Name: Bharath Kumar.M
	 * Created Date:  10/May/2017
	 * Description : Created common method to retrieve member whose Time slot is not selected 
	 * @param driver
	 * @param mail
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static String retrieveMemberTimeSlotNotSelected(WebDriver driver, String onboarding) throws ParseException, ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select ACNT.EMAIL as MEMEMAIL, SAT.PROGRAM_INTERVAL_NUMBER, SAT. ONBOARDING_STATUS from SUMMARY_ACCOUNT_TODATE SAT, ACCOUNT ACNT WHERE ACNT.ID= SAT.ACCOUNT_ID  and SAT.MAST_PROGRAM_ID= '02' AND SAT.ONBOARDING_STATUS= '"+onboarding+"' AND ACNT.EMAIL NOT LIKE '%api%' and ACNT.STATUS= 'Active' and ACNT.EMAIL NOT LIKE 'QAmember%default@yopmail.com'");
		System.out.println("query executed");
		String Member_Email= "";
		if(rs.next())
		{
			Member_Email = rs.getString("MEMEMAIL");
			System.out.println("Member_EMAIL :"+Member_Email);
		}
		return Member_Email;
	}
}