package com.zillion.qa.programadmin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.ParseException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.zillion.qa.commands.Manipulation;
import com.zillion.qa.utils.Directory;
public class Clients extends Manipulation implements OR
{
	/**
	 * Name         :   Suresh.V
	 * Created Date :   11/Jan/16
	 * Modified Date:   17/March/16
	 * Description  :   Using provider "URL" login as a PA account,then verify client tab pagination symbols and counts.
	 * Required Inputs: Username and password.
	 * ProgramAdmin(Apollo Endo)_Client_Tab_Search_Filter_Selection_Verification_187090.xlsx
	 */
	public static void clientTabPaginationVerification(WebDriver driver,String testData)
	{
		String[] testData1 = testData.split(",");
		String selectOption=testData1[0];
		String inputWrongData=testData1[1];
		WebElement clientsTab=driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLIENTS_TAB));
		click(clientsTab);
		waitForElement(driver,PAGINATION_INFORMATION);
		waitForElement(driver,FILTER_CLIENT_LIST);
		waitForElement(driver,SEARCH_IMAGE);
		wait(driver, "5");
		WebElement filterDropDown=driver.findElement(By.xpath(OR.FILTER_CLIENT_LIST));
		selectByValue(filterDropDown, selectOption);
		WebElement searchTextBox=driver.findElement(By.xpath(OR.SERACH_TEXT_BOX_ACTIVE));
		sendKeys(searchTextBox, inputWrongData);
		WebElement searchImage=driver.findElement(By.xpath(OR.SEARCH_IMAGE));
		click(searchImage);
		wait(driver, "10");
		WebElement noResult=driver.findElement(By.xpath(OR.NO_RESULT_MSG));
		verifyElementIsPresent(driver, noResult);
	}
	public static void verifyclientnameAfterSearch(WebDriver driver,String client)
	{
		System.out.println("client name:"+client);
		WebElement Seachclient=driver.findElement(By.xpath("//tr[@class='ng-scope']/td/a[contains(text(),'"+client+"')]"));
		String clientname1=Seachclient.getText();
		System.out.println("client name1:"+clientname1);
		if(clientname1.equals(clientname1));
		{
			if((client).equalsIgnoreCase(clientname1))
			{
				System.out.println("Client names are matched");
			}
			else
			{
				String[] SearchClientname = clientname1.split(client);
				System.out.println("client name:"+SearchClientname[1]);
				String SearchClientname1=SearchClientname[1];
				String[] SearchClientname2 = clientname1.split(SearchClientname1);
				String SearchClientname3=SearchClientname2[0];
				System.out.println("client name:"+SearchClientname3);
				wait(driver, "2");
				if((client).equalsIgnoreCase(SearchClientname3))
				{
					System.out.println("Client names are matched");
				}
			}
		}
	}
	/**
	 * Name :Abinaya.P
	 * Created Date:25/Nov/2016
	 * Modified Date:
	 * Description :Create a common method to get an id of the provider
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static String getProviderId(WebDriver driver,String firstname,String lastname) throws ParseException, ClassNotFoundException, SQLException
	{
		String id="";
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
		ResultSet rs = stat.executeQuery("SELECT id FROM PROVIDER WHERE FIRST_NAME LIKE '"+firstname+"' and LAST_NAME LIKE '"+lastname+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			id= rs.getString("id");
			System.out.println("ID of the provider "+id+" is retrieved from the Table");
		}
		return id;
	}
	/**
	 * Name :Abinaya.P
	 * Created Date:25/Nov/2016
	 * Modified Date:
	 * Description :Create a common method to get an payment id of the provider
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static String getPaymentId(WebDriver driver,String id) throws ParseException, ClassNotFoundException, SQLException
	{
		String paymentid="";
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
		ResultSet rs = stat.executeQuery("select id from PAYMENT_IDENTITY_MAP WHERE COMMON_USER_ID= '"+id+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			paymentid= rs.getString("id");
			System.out.println("Payment ID of the provider "+id+" is retrieved from the Table");
		}
		return paymentid;
	}
	/**
	 * Name :Abinaya.P
	 * Created Date:25/Nov/2016
	 * Modified Date:
	 * Description :Create a common method to get billing history with respect to the payment
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static void getBillingHistory(WebDriver driver,String paymentid) throws ParseException, ClassNotFoundException, SQLException
	{
		for(int i=1;i<=2;i++)
		{
			String chargeid=driver.findElement(By.xpath("//th[text()='ORDER #']/parent::tr/parent::thead//following-sibling::tbody//tr["+i+"]//td[7]")).getText();
			String brand="";
			String amount="";
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
			ResultSet rs = stat.executeQuery("select AMOUNT, BRAND from PAYMENT_TRANSACTION WHERE PAYMENT_IDENTITY_MAP_ID= '"+paymentid+"' and CHARGE_ID='"+chargeid+"' ORDER BY CREATED_DT DESC");
			System.out.println("query executed");
			if(rs.next())
			{
				brand= rs.getString("BRAND");
				amount= rs.getString("AMOUNT");
			}
			String amount1=driver.findElement(By.xpath("//th[text()='ORDER #']/parent::tr/parent::thead//following-sibling::tbody//tr["+i+"]//td[3]")).getText();
			NumberFormat format = NumberFormat.getCurrencyInstance();
			Number number = format.parse(amount1);
			String brand1=driver.findElement(By.xpath("//th[text()='ORDER #']/parent::tr/parent::thead//following-sibling::tbody//tr["+i+"]//td[4]")).getText();
			String appamount=null;
			if(number.toString().length()==3)
			{
				appamount=number.toString()+"00";
			}
			else
			{
				appamount=number.toString().replace(".", "");
			}
			if(brand.equals(brand1))
			{
				System.out.println("Retrieved from the database:"+brand);
				System.out.println("Retrieved from the application:"+brand1);
				System.out.println("Card type matches the database");
			}
			else
			{
				System.out.println("Retrieved from the database:"+brand);
				System.out.println("Retrieved from the application:"+brand1);
				System.out.println("Card type does not match the database");
				Assert.fail();
			}
			if(amount.equals(appamount))
			{
				System.out.println("Retrieved from the database:"+amount);
				System.out.println("Retrieved from the application:"+appamount);
				System.out.println("Amount matches the database");
			}
			else
			{
				System.out.println("Retrieved from the database:"+amount);
				System.out.println("Retrieved from the application:"+appamount);
				System.out.println("Amount does not match the database");
				Assert.fail();
			}
		}
	}
	/**
	 * Name :Abinaya.P
	 * Created Date:25/Nov/2016
	 * Modified Date:
	 * Description :Created a common method to get Refeund on credit card from Back End(BE)
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static String refundoncreditcard(WebDriver driver,String emailid) throws ParseException, ClassNotFoundException, SQLException
	{
		String paymentid="";
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
		ResultSet rs = stat.executeQuery("SELECT USER_EMAIL, STATUS from PAYMENT_TRANSACTION WHERE ID = ( SELECT ID FROM ACCOUNT WHERE EMAIL LIKE '"+emailid+"');");
		System.out.println("query executed");
		while(rs.next())
		{
			paymentid= rs.getString("STATUS");
		}
		return paymentid;
	}
	/**
	 * Name :Abinaya.P
	 * Created Date:25/Nov/2016
	 * Modified Date:
	 * Description :Removing unavailability
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void  removeunavailability(WebDriver driver)
	{
		try
		{
			wait(driver, "2");
			waitForElement(driver, OR.SESSION_TIME_SLOT_5_30PM);
			jsClickByXPath(driver, SESSION_TIME_SLOT_5_30PM);
		}
		catch(Exception e)
		{
			wait(driver, "3");
			waitForElement(driver, OR.COACHES_SETTINGS_LINK_SCHEDULE_BUTTON);
			jsClickByXPath(driver, COACHES_SETTINGS_LINK_SCHEDULE_BUTTON);
			wait(driver, "6");
			waitForElement(driver, COACH_CALENDER_NEXT_PAGE_NAVIGATOR);
			jsClickByXPath(driver, COACH_CALENDER_NEXT_PAGE_NAVIGATOR);
			wait(driver, "3");
			WebElement unavailable=driver.findElement(By.xpath(OR.COACHES_SCHEDULE_CALENDER_SCHEDULE_UNAVAILABLITY_RED_AREA));
			jsClickByXPath(driver, COACHES_SCHEDULE_CALENDER_SCHEDULE_UNAVAILABLITY_RED_AREA);
			waitForElement(driver, OR.COACHES_SCHEDULE_CALENDER_SCHEDULE_EDIT_OR_REMOVE_UNAVAILABLITY);
			jsClickByXPath(driver, COACHES_SCHEDULE_CALENDER_SCHEDULE_EDIT_OR_REMOVE_UNAVAILABLITY);
			wait(driver, "3");
			waitForElement(driver, OR.COACHES_SCHEDULE_CALENDER_SCHEDULE_REMOVE_EVENT_TEXT);
			jsClickByXPath(driver, COACHES_SCHEDULE_CALENDER_SCHEDULE_REMOVE_EVENT_TEXT);
			waitForElement(driver, OR.REAL_APPEAL_COACH_ASSIGN_SUBSTITUTE_COACH_SAVE_BUTTON);
			jsClickByXPath(driver, REAL_APPEAL_COACH_ASSIGN_SUBSTITUTE_COACH_SAVE_BUTTON);
			wait(driver, "3");
			waitForElement(driver, OR.INSUFFICIENT_CLASS_MEMBER_MOVE_TO_POPUP_OK_BUTTON);
			jsClickByXPath(driver, INSUFFICIENT_CLASS_MEMBER_MOVE_TO_POPUP_OK_BUTTON);
			wait(driver, "3");
			waitForElement(driver, OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_TIME_SLOT_NEXT_PAGE_NAVIGATOR);
			jsClickByXPath(driver, OPS_ADMIN_PROVIDERS_LECTURE_SESSION_TIME_SLOT_NEXT_PAGE_NAVIGATOR);
			wait(driver, "6");
			Manipulation.clickAt(driver, unavailable, 872, 128);
			wait(driver, "2");
			waitForElement(driver, OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_POPUP_NEW_LECTURE_SESSION_OPTION);
			jsClickByXPath(driver, OPS_ADMIN_PROVIDERS_LECTURE_SESSION_POPUP_NEW_LECTURE_SESSION_OPTION);
			wait(driver, "3");
			waitForElement(driver, OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_TITLE_TEXTBOX);
			WebElement title=driver.findElement(By.xpath(OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_TITLE_TEXTBOX));
			sendKeys(title, "Zado Testing");
			WebElement description=driver.findElement(By.xpath(OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_DESCRIPTION_TEXTBOX));
			sendKeys(description, "Testing");
			WebElement program=driver.findElement(By.xpath(OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_PROGRAM_NAME_DROPDOWN));
			selectByVisibletext(program, "(New) Orbera Coach : Direct : Post Procedure");
			jsClickByXPath(driver, OPS_ADMIN_PROVIDERS_LECTURE_SESSION_CONTINUE_BUTTON);
			wait(driver, "2");
			waitForElement(driver, OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_TIME_SLOT_NEXT_PAGE_NAVIGATOR);
			jsClickByXPath(driver, OPS_ADMIN_PROVIDERS_LECTURE_SESSION_TIME_SLOT_NEXT_PAGE_NAVIGATOR);
			wait(driver, "2");
			com.zillion.qa.member.liveSessionSubCommonMethods.liveSessionLectureSessionTimeSlotSearch(driver);
			wait(driver, "2");
			waitForElement(driver, OR.SESSION_TIME_SLOT_5_30PM);
			jsClickByXPath(driver, SESSION_TIME_SLOT_5_30PM);
		}
	}
	/**
	 * Name :Abinaya.P
	 * Created Date:25/Nov/2016
	 * Modified Date:
	 * Description :Create a common method to get billing history with respect to the payment
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static void getInvoiceBillingHistory(WebDriver driver,String paymentid) throws ParseException, ClassNotFoundException, SQLException
	{
		for(int i=1;i<=2;i++)
		{
			String chargeid=driver.findElement(By.xpath("//th[text()='INVOICE']/parent::tr/parent::thead//following-sibling::tbody//tr["+i+"]//td[1]")).getText();
			String brand="";
			String amount="";
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
			ResultSet rs = stat.executeQuery("select amount,po_number from PAYMENT_INVOICE WHERE COMMON_USER_ID= '"+paymentid+"' and customer_receipt_Number='"+chargeid+"'");
			System.out.println("query executed");
			if(rs.next())
			{
				brand= rs.getString("PO_NUMBER");
				amount= rs.getString("AMOUNT");
			}
			String amount1=driver.findElement(By.xpath("//th[text()='INVOICE']/parent::tr/parent::thead//following-sibling::tbody//tr["+i+"]//td[3]")).getText();
			NumberFormat format = NumberFormat.getCurrencyInstance();
			Number number = format.parse(amount1);
			String brand1=driver.findElement(By.xpath("//th[text()='INVOICE']/parent::tr/parent::thead//following-sibling::tbody//tr["+i+"]//td[4]")).getText();
			String appamount=null;
			if(number.toString().length()==3)
			{
				appamount=number.toString()+"00";
			}
			else
			{
				appamount=number.toString().replace(".", "");
			}
			if(brand.equals(brand1))
			{
				System.out.println("Retrieved from the database:"+brand);
				System.out.println("Retrieved from the application:"+brand1);
				System.out.println("Card type matches the database");
			}
			else
			{
				System.out.println("Retrieved from the database:"+brand);
				System.out.println("Retrieved from the application:"+brand1);
				System.out.println("Card type does not match the database");
				Assert.fail();
			}
			if(amount.equals(appamount))
			{
				System.out.println("Retrieved from the database:"+amount);
				System.out.println("Retrieved from the application:"+appamount);
				System.out.println("Amount matches the database");
			}
			else
			{
				System.out.println("Retrieved from the database:"+amount);
				System.out.println("Retrieved from the application:"+appamount);
				System.out.println("Amount does not match the database");
				Assert.fail();
			}
		}
	}
}