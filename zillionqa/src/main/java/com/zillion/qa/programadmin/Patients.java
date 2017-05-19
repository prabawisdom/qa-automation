package com.zillion.qa.programadmin;
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
import com.zillion.qa.practitioner.OR;
import com.zillion.qa.utils.Directory;
/**
 * Name         :   Vinothkumar
 * Created Date :   09/Feb/2016
 * Modified Date:   18/Mar/2016
 * Description  : Create a common method to validate Program Admin profile across database
 * Testcase Sheet: Program_Admin_General_Tab_192271_192272
 * @return
 * @throws ClassNotFoundException
 * @throws SQLException
 */
public class Patients extends Manipulation implements OR
{
	public static String programAdminProfileValidationUsingDB(WebDriver driver, String testData) throws ClassNotFoundException, SQLException
	{
		String[] testData1 = testData.split(",");
		String maximumCharacter=testData1[0];
		String bioMaximumCharacter=testData1[1];
		String phoneNumber=testData1[2];
		String phoneExtensionTextBoxInvalidData1=testData1[3];
		String phoneExtensionTextBoxInvalidData2=testData1[4];
		String phoneExtensionTextBoxvalidData=testData1[5];
		String zipCode=testData1[6];
		String address1=testData1[7];
		String city=testData1[8];
		String nickName=testData1[9];
		String inputMonth=testData1[10];
		String inputDay=testData1[11];
		String inputYear=testData1[12];
		String clickState=testData1[13];
		String inputEmail=testData1[14];
		int bioCharactersLength=bioMaximumCharacter.length();
		int charactersLength=maximumCharacter.length();
		System.out.println("Character Length="+charactersLength);
		System.out.println("Bio Character Length="+bioCharactersLength);
		WebElement myProfileEditButton = driver.findElement(By.xpath(OR.PRACTITIONER_MY_PROFILE_EDIT_BUTTON));
		click(myProfileEditButton);
		wait(driver, "2");
		WebElement firstNameTextBox = driver.findElement(By.xpath(OR.PRACTITIONER_MY_PROFILE_FIRSTNAME_EDIT_TEXTBOX));
		clearAndType(firstNameTextBox,maximumCharacter);
		WebElement lastNameTextBox = driver.findElement(By.xpath(OR.PRACTITIONER_MY_PROFILE_LASTNAME_EDIT_TEXTBOX));
		clearAndType(lastNameTextBox,maximumCharacter);
		WebElement phoneTextbox = driver.findElement(By.xpath(OR.PHONE_NUMBER_TEXTBOX));
		clearAndType(phoneTextbox,phoneNumber);
		WebElement addressOneTextbox = driver.findElement(By.xpath(OR.ADDRESSONE_TEXTBOX));
		WebElement cityTextbox = driver.findElement(By.xpath(OR.CITY_TEXTBOX));
		WebElement zipCodeTextbox = driver.findElement(By.xpath(OR.ZIP_CODE_TEXTBOX));
		WebElement nickNameTextbox = driver.findElement(By.xpath(OR.NICKNAME_TEXTBOX));
		WebElement phoneExtensionTextBox = driver.findElement(By.xpath(OR.PHONE_EXTENSION));
		clearAndType(phoneExtensionTextBox,phoneExtensionTextBoxInvalidData1);
		WebElement phoneExtensionAlertMsgNumberOnlyAndMinimum3Digit = driver.findElement(By.xpath(OR.PHONE_EXTENSION_ALERT_MSG_NUMBERS_ONLY));
		verifyElementIsPresent(driver, phoneExtensionAlertMsgNumberOnlyAndMinimum3Digit);
		clearAndType(phoneExtensionTextBox,phoneExtensionTextBoxInvalidData2);
		verifyElementIsPresent(driver, phoneExtensionAlertMsgNumberOnlyAndMinimum3Digit);
		clearAndType(phoneExtensionTextBox,phoneExtensionTextBoxvalidData);
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
		ResultSet rs = stat.executeQuery("select LAST_NAME,FIRST_NAME,PHONE,NICKNAME,CITY,POSTAL_CODE,STREET,BIO from provider where email ='"+inputEmail+"'");
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
				System.out.println("Phone number is matched="+phoneNumber);
			}
			else
			{
				System.out.println("Phone number is not matched="+phonenumber);
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
}