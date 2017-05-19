package com.zillion.qa.opsadmin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.zillion.qa.commands.Manipulation;
import com.zillion.qa.commands.Navigate;
import com.zillion.qa.utils.Directory;
public class Clients extends Manipulation implements OR
{
	/**
	 * Name :     ABINAYA. P
	 * Created Date:   14/November/16
	 * Modified Date:
	 * Description :Create a common method to select Invoice/CreditCard from OPS Admin
	 * Ticket ID :
	 * Required Inputs :
	 */
	public static void selectInvoiceOrCreditCard(WebDriver driver, String paymentmode, String subclient)
	{
		Navigate.get(driver, Directory.OpsAdminurl);
		Navigate.maximize(driver);
		Navigate.waitTime(driver, "20");
		WebElement opslogin= driver.findElement(By.xpath(OR.OPS_ADMIN_USERNAME));
		waitForElement(driver,OR.OPS_ADMIN_USERNAME);
		sendKeys(opslogin, Directory.OpsAdminusername);
		WebElement opspassword= driver.findElement(By.xpath(OR.OPS_ADMIN_PASSWORD));
		sendKeys(opspassword, Directory.OpsAdminpassword);
		WebElement loginbutton= driver.findElement(By.xpath(OR.OPS_ADMIN_LOGIN));
		click(loginbutton);
		wait(driver, "4");
		Navigate.waitTime(driver, "20");
		waitForElement(driver, OR.OPS_ADMIN_ZILLION_LOGO);
		waitForElement(driver, OR.PROGRAM_ADMIN_CLIENTS_TAB);
		jsClickByXPath(driver, PROGRAM_ADMIN_CLIENTS_TAB);
		wait(driver, "5");
		waitForElement(driver, OR.PROGRAM_ADMIN_CLIENTS_TAB_FILTER_DROPDOWN);
		WebElement clientdropdown= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLIENTS_TAB_FILTER_DROPDOWN));
		Manipulation.selectByVisibletext(clientdropdown,"Client");
		wait(driver, "1");
		WebElement searchbox= driver.findElement(By.xpath(OR.PATIENT_SEARCH_TEXTBOX));
		waitForElement(driver, OR.PATIENT_SEARCH_TEXTBOX);
		sendKeys(searchbox, "Apollo");
		jsClickByXPath(driver, PATIENT_SEARCH_SUBMIT_BUTTON);
		wait(driver, "3");
		waitForElement(driver, OR.CLIENT_APOLLO_ENDO);
		jsClickByXPath(driver, VIEW_CLIENTS);
		wait(driver, "2");
		WebElement paymentsubclientsearch= driver.findElement(By.xpath(OR.PAYMENT_SUB_CLIENT_SEARCH));
		sendKeys(paymentsubclientsearch, subclient);
		jsClickByXPath(driver, PAYMENT_SUB_CLIENT_SEARCH_MAGNIFIER);
		wait(driver, "3");
		waitForElement(driver, "//a[contains(text(),'"+subclient+"')]");
		jsClickByXPath(driver, "//a[contains(text(),'"+subclient+"')]");
		wait(driver, "4");
		waitForElement(driver, OR.PAYMENT);
		jsClickByXPath(driver, PAYMENT);
		wait(driver, "4");
		waitForElement(driver, OR.ADD_PAYMENT_METHOD_BUTTON);
		jsClickByXPath(driver, ADD_PAYMENT_METHOD_BUTTON);
		waitForElement(driver, OR.PAYMENT_TYPE);
		WebElement paymenttype= driver.findElement(By.xpath(OR.PAYMENT_TYPE));
		Manipulation.selectByVisibletext(paymenttype,paymentmode);
		jsClickByXPath(driver, ADD_PAYMENT_BUTTON);
		wait(driver, "3");
		waitForElement(driver, OR.PAYMENT_SUCCESS_MESSAGE);
		System.out.println("above ok");
		waitForElement(driver, OR.OK_BUTTON);
		jsClickByXPath(driver, OK_BUTTON);
		wait(driver, "3");
		com.zillion.qa.opsadmin.Dashboard.opsAdminLogout(driver);
	}
	/**
	 * Name :     ABINAYA. P
	 * Created Date:   17/Nov/16
	 * Modified Date:
	 * Description :Change state from hfops
	 * Ticket ID :
	 * Required Inputs :
	 */
	public static void changeState(WebDriver driver, String subclient, String state, String zipcode)
	{
		waitForElement(driver, OR.OPS_ADMIN_ZILLION_LOGO);
		waitForElement(driver, OR.PROGRAM_ADMIN_CLIENTS_TAB);
		jsClickByXPath(driver, PROGRAM_ADMIN_CLIENTS_TAB);
		wait(driver, "3");
		waitForElement(driver, OR.PROGRAM_ADMIN_CLIENTS_TAB_FILTER_DROPDOWN);
		WebElement clientdropdown= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLIENTS_TAB_FILTER_DROPDOWN));
		Manipulation.selectByVisibletext(clientdropdown,"Client");
		wait(driver, "1");
		WebElement searchbox= driver.findElement(By.xpath(OR.PATIENT_SEARCH_TEXTBOX));
		sendKeys(searchbox, "Apollo");
		jsClickByXPath(driver, PATIENT_SEARCH_SUBMIT_BUTTON);
		wait(driver, "3");
		waitForElement(driver, OR.CLIENT_APOLLO_ENDO);
		jsClickByXPath(driver, VIEW_CLIENTS);
		wait(driver, "2");
		WebElement paymentsubclientsearch= driver.findElement(By.xpath(OR.PAYMENT_SUB_CLIENT_SEARCH));
		sendKeys(paymentsubclientsearch, subclient);
		jsClickByXPath(driver, PAYMENT_SUB_CLIENT_SEARCH_MAGNIFIER);
		wait(driver, "3");
		waitForElement(driver, "//a[contains(text(),'"+subclient+"')]");
		jsClickByXPath(driver, "//a[contains(text(),'"+subclient+"')]");
		wait(driver, "4");
		waitForElement(driver, OR.PRACTITIONER_PROFILE_EDIT_BUTTON);
		jsClickByXPath(driver, PRACTITIONER_PROFILE_EDIT_BUTTON);
		wait(driver, "1");
		WebElement State= driver.findElement(By.xpath(OR.PACLIENT_GENERAL_TAB_STATE));
		Manipulation.selectByVisibletext(State,state);
		wait(driver, "1");
		WebElement postalcode= driver.findElement(By.xpath(OR.POSTALCODE));
		clearAndType(postalcode, zipcode);
		waitForElement(driver, OR.SAVE_BUTTON);
		jsClickByXPath(driver, SAVE_BUTTON);
		wait(driver, "3");
		jsClickByXPath(driver, OK_BUTTON);
		wait(driver, "4");
	}
	/**
	 * Name :     ABINAYA. P
	 * Created Date:   17/Nov/16
	 * Modified Date:
	 * Description :Add Credit card in practitioner login
	 * Ticket ID :
	 * Required Inputs :
	 */
	public static void addcreditcard(WebDriver driver, String name, String cardnumber, String date, String year, String securitycode)
	{
		wait(driver, "10");
		WebElement Patient = driver.findElement(By.xpath(OR.PRACTITIONER_PATIENT_TAB));
		waitForElement(driver, OR.PRACTITIONER_PATIENT_TAB);
		click(Patient);
		wait(driver, "5");
		waitForElement(driver, OR.USER_SIGNOUT_PARENT_LINK);
		jsClickByXPath(driver, USER_SIGNOUT_PARENT_LINK);
		waitForElement(driver, OR.PRACTITIONER_GEAR_SETTING_LINK_MY_PROFILE_OPTION);
		jsClickByXPath(driver, PRACTITIONER_GEAR_SETTING_LINK_MY_PROFILE_OPTION);
		wait(driver, "5");
		waitForElement(driver, OR.MYPROFILE_PAYMENT_TAB);
		jsClickByXPath(driver, MYPROFILE_PAYMENT_TAB);
		waitForElement(driver, OR.PAYMENT_ADD_NEW_CREDIT_CARD);
		jsClickByXPath(driver, PAYMENT_ADD_NEW_CREDIT_CARD);
		WebElement paymentcardname= driver.findElement(By.xpath(OR.PAYMENT_CARD_NAME));
		waitForElement(driver, OR.PAYMENT_CARD_NAME);
		sendKeys(paymentcardname, name);
		WebElement paymentcardnumber= driver.findElement(By.xpath(OR.PAYMENT_CARD_NUMBER));
		waitForElement(driver, OR.PAYMENT_CARD_NUMBER);
		sendKeys(paymentcardnumber, cardnumber);
		WebElement paymentexpdate= driver.findElement(By.xpath(OR.PAYMENT_EXP_DATE));
		waitForElement(driver, OR.PAYMENT_EXP_DATE);
		Manipulation.selectByVisibletext(paymentexpdate,date);
		WebElement paymentexpyear= driver.findElement(By.xpath(OR.PAYMENT_EXP_YEAR));
		Manipulation.selectByVisibletext(paymentexpyear,year);
		WebElement paymentsecuritycode= driver.findElement(By.xpath(OR.PAYMENT_SECURITY_CODE));
		sendKeys(paymentsecuritycode, securitycode);
		wait(driver, "1");
		WebElement savecreditcard= driver.findElement(By.xpath(OR.PAYMENT_SAVE_CREDIT_CARD));
		waitForElement(driver, OR.PAYMENT_SAVE_CREDIT_CARD);
		visibilityElement(driver, savecreditcard);
		actionClick(driver, savecreditcard);
		wait(driver, "2");
	}
	public static String addpatient(WebDriver driver, String fname, String lname, String month, String day,String year,String organization, String physician, String practicprograms)
	{
		WebElement addPatientsubtittle= driver.findElement(By.xpath(OR.PATIENT_SUB_TITTLE));
		verifyElementIsPresent(driver, addPatientsubtittle);
		click(addPatientsubtittle);
		wait(driver, "2");
		WebElement addPatient= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_BUTTON));
		waitForElement(driver, OR.PROVIDER_URL_ADD_PATIENT_BUTTON);
		click(addPatient);
		WebElement firstName= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_FIRSTNAME_TEXTBOX));
		WebElement lastName= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_LASTNAME_TEXTBOX));
		WebElement emailid= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_EMAIL_TEXTBOX));
		WebElement confirmEmail= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_CONFIRMEMAIL_TEXTBOX));
		sendKeys(firstName, fname);
		sendKeys(lastName, lname);
		String email1 =	dynamicSendkeys(driver, "test@yopmail.com", emailid);
		sendKeys(confirmEmail, email1);
		WebElement paymentexpyear= driver.findElement(By.xpath(OR.PAYMENT_EXP_YEAR));
		Manipulation.selectByVisibletext(paymentexpyear,year);
		WebElement monthAdd= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_ADD_MONTH_FIELD));
		WebElement dayAdd= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_ADD_DAY_FIELD));
		WebElement yearAdd= driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_ADD_YEAR_FIELD));
		selectByVisibletext(monthAdd, month);
		selectByVisibletext(dayAdd, day);
		selectByVisibletext(yearAdd, year);
		WebElement organizations= driver.findElement(By.xpath(OR.ORGANIZATION));
		selectByVisibletext(organizations, organization);
		WebElement practiceprogram= driver.findElement(By.xpath(OR.PRACTITIONER_PROGRAM_DROPDOWN));
		selectByVisibletext(practiceprogram, practicprograms);
		WebElement physicians= driver.findElement(By.xpath(OR.PROVIDER_PHYSICIAN_DROPDOWN));
		selectByVisibletext(physicians, physician);
		waitForElement(driver, OR.ADD_PATIENT_PAYMENT);
		jsClickByXPath(driver, ADD_PATIENT_PAYMENT);
		wait(driver, "2");
		return email1;
	}

	public static void deletecreditcard(WebDriver driver)
	{
		wait(driver, "3");
		waitForElement(driver, OR.ADD_PATIENT_PAYMENT_CANCEL);
		jsClickByXPath(driver, ADD_PATIENT_PAYMENT_CANCEL);
		WebElement Patient = driver.findElement(By.xpath(OR.PRACTITIONER_PATIENT_TAB));
		verifyElementIsPresent(driver, Patient);
		click(Patient);
		wait(driver, "3");
		waitForElement(driver, OR.USER_SIGNOUT_PARENT_LINK);
		jsClickByXPath(driver, USER_SIGNOUT_PARENT_LINK);
		waitForElement(driver, OR.PRACTITIONER_GEAR_SETTING_LINK_MY_PROFILE_OPTION);
		jsClickByXPath(driver, PRACTITIONER_GEAR_SETTING_LINK_MY_PROFILE_OPTION);
		wait(driver, "3");
		waitForElement(driver, OR.MYPROFILE_PAYMENT_TAB);
		jsClickByXPath(driver, MYPROFILE_PAYMENT_TAB);
		waitForElement(driver, OR.PAYMENT_DELETE_CREDIT_CARD);
		jsClickByXPath(driver, PAYMENT_DELETE_CREDIT_CARD);
		waitForElement(driver, OR.PAYMENT_CONFIRM_DELETE);
		jsClickByXPath(driver, PAYMENT_CONFIRM_DELETE);
		wait(driver, "2");
	}
	public static int i;
	public static int count;
	public static void add20creditcard(WebDriver driver, String name, String cardnumber, String date, String year, String securitycode)
	{
		wait(driver, "5");
		String creditcardcount=(driver.findElement(By.xpath(OR.PROVIDER_URL_ADD_PATIENT_COUNT))).getText();
		creditcardcount = creditcardcount.replaceAll("\\p{P}","");
		count = Integer.parseInt(creditcardcount);
		System.out.println(count);
		for(i=count; i<20; i++)
		{
			waitForElement(driver, OR.PAYMENT_ADD_NEW_CREDIT_CARD);
			jsClickByXPath(driver, PAYMENT_ADD_NEW_CREDIT_CARD);
			WebElement paymentcardname= driver.findElement(By.xpath(OR.PAYMENT_CARD_NAME));
			waitForElement(driver, OR.PAYMENT_CARD_NAME);
			sendKeys(paymentcardname, name);

			WebElement paymentcardnumber= driver.findElement(By.xpath(OR.PAYMENT_CARD_NUMBER));
			waitForElement(driver, OR.PAYMENT_CARD_NUMBER);
			sendKeys(paymentcardnumber, cardnumber);
			WebElement paymentexpdate= driver.findElement(By.xpath(OR.PAYMENT_EXP_DATE));
			waitForElement(driver, OR.PAYMENT_EXP_DATE);
			Manipulation.selectByVisibletext(paymentexpdate,date);
			WebElement paymentexpyear= driver.findElement(By.xpath(OR.PAYMENT_EXP_YEAR));
			Manipulation.selectByVisibletext(paymentexpyear,year);
			WebElement paymentsecuritycode= driver.findElement(By.xpath(OR.PAYMENT_SECURITY_CODE));
			sendKeys(paymentsecuritycode, securitycode);
			wait(driver, "1");
			WebElement savecreditcard= driver.findElement(By.xpath(OR.PAYMENT_SAVE_CREDIT_CARD));
			waitForElement(driver, OR.PAYMENT_SAVE_CREDIT_CARD);
			visibilityElement(driver, savecreditcard);
			actionClick(driver, savecreditcard);
			wait(driver, "5");
			Navigate.refreshPage(driver);
			wait(driver, "5");
			waitForElement(driver, OR.MYPROFILE_PAYMENT_TAB);
			jsClickByXPath(driver, MYPROFILE_PAYMENT_TAB);
			wait(driver, "5");
		}
	}
	public static void deletetwentycreditcard(WebDriver driver)
	{
		System.out.println("ecount"+count);
		for(int j=0;j<(21-count);j++)
		{
			System.out.println("entered in for loop");
			waitForElement(driver, PAYMENT_DELETE_TWENTYCC_CREDIT_CARD);
			{
				System.out.println("entered in if");
				wait(driver, "5");
				jsClickByXPath(driver, PAYMENT_DELETE_TWENTYCC_CREDIT_CARD);
				wait(driver, "5");
				waitForElement(driver, OR.PAYMENT_CONFIRM_DELETE);
				jsClickByXPath(driver, PAYMENT_CONFIRM_DELETE);
				Navigate.refreshPage(driver);
				wait(driver, "5");
				waitForElement(driver, OR.MYPROFILE_PAYMENT_TAB);
				jsClickByXPath(driver, MYPROFILE_PAYMENT_TAB);
				wait(driver, "5");
			}
		}
	}
}