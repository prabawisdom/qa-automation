package com.zillion.qa.coaches;
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
public class Messages extends Manipulation  {
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   16/Apr/15
	 * Description :Create a common method for verify Unread messages
	 * Ticket ID :
	 */
	public static void verifyUnreadmessages(WebDriver driver)
	{
		WebElement webElement = driver.findElement( By.xpath(OR.NEW_UNREAD_MESSAGE_LINK ) );
		String subject1=webElement.getText();
		System.out.println(subject1);
		WebElement subject = driver.findElement( By.xpath("//tr//th[text()='Subject']/parent::tr/parent::thead/following-sibling::tbody/tr[1]/td[2]/a[text()='"+subject1+"']" ) );
		verifyElementIsPresent(driver, webElement);
		click(webElement);
		wait(driver, "3");
		WebElement Dashboard = driver.findElement( By.xpath(OR.COACHES_DASHBOARD_TAB ) );
		verifyElementIsPresent(driver, Dashboard );
		actionClick( driver, Dashboard );
		verifyElementIsNotPresent(driver, subject);
		Navigate.waitTime(driver, "5");
	}
	/**
	 * Name :     Vinothkumar.M
	 * Created Date:   19/Apr/15
	 * Description :Create a common method for Message sent by Provider Subject
	 * Ticket ID :
	 */
	public  static String messageSentByProviderSubjectValidatedWithDB(WebDriver driver,String subjectTimeStampFromSheet, String getText115,String testData) throws ParseException, ClassNotFoundException, SQLException
	{
		String[] testData1 = testData.split(",");
		String inputEmail=testData1[0];
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
		ResultSet rs = stat.executeQuery("select Subject, Body, FROM_NAME, FROM_ROLE, TO_NAME, TO_ROLE from MESSAGE where FROM_USER_ID = (select ID from provider where email ='"+inputEmail+"') AND Subject LIKE '"+subjectTimeStampFromSheet+"% %'  order by created_dt desc");
		String Subject="";
		while(rs.next())
		{
			Subject= rs.getString("SUBJECT");
			System.out.println("Subject is retrieved from the DB: "+Subject);
		}
		if(getText115.equalsIgnoreCase( Subject ))
		{
			System.out.println("Given Subject is matched with the DB subject");
		}
		return ElementWait;
	}
	/**
	 * Name :     Vinothkumar.M
	 * Created Date:   19/Apr/15
	 * Description :Create a common method for Message sent by Provider Body
	 * Ticket ID :
	 */
	public  static String messageSentByProviderBodyValidatedWithDB(WebDriver driver,String bodyTimeStampFromSheet,String getText116,String testData) throws ParseException, ClassNotFoundException, SQLException
	{
		String[] testData1 = testData.split(",");
		String inputEmail=testData1[0];
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
		ResultSet rs = stat.executeQuery("select Subject, Body, FROM_NAME, FROM_ROLE, TO_NAME, TO_ROLE from MESSAGE where FROM_USER_ID = (select ID from provider where email ='"+inputEmail+"') AND Body LIKE '"+bodyTimeStampFromSheet+"% %'  order by created_dt desc");
		String Body="";
		while(rs.next())
		{
			Body= rs.getString("BODY");
			System.out.println("Body is retrieved from the DB: "+Body);
		}
		if(getText116.equalsIgnoreCase( Body))
		{
			System.out.println("Given Body is matched with the DB body");
		}
		return ElementWait;
	}
	/**
	 * Name :     Vinothkumar.M
	 * Created Date:   19/Apr/15
	 * Description :Create a common method for Message received by Provider Subject
	 * Ticket ID :
	 */
	public  static String messageReceivedByProviderSubjectValidatedWithDB(WebDriver driver,String messageReceivedsubjectTimeStampFromSheet, String getText117,String testData) throws ParseException, ClassNotFoundException, SQLException
	{
		String[] testData1 = testData.split(",");
		String inputEmail=testData1[0];
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
		ResultSet rs = stat.executeQuery("select Subject, Body, TO_NAME, TO_ROLE, FROM_NAME, FROM_ROLE from MESSAGE where TO_USER_ID = (select ID from provider where email ='"+inputEmail+"') AND Subject LIKE '"+messageReceivedsubjectTimeStampFromSheet+"% %' order by created_dt desc");
		String Subject="";
		while(rs.next())
		{
			Subject= rs.getString("SUBJECT");
			System.out.println("Subject is retrieved from the DB: "+Subject);
		}
		if(getText117.equalsIgnoreCase( Subject ))
		{
			System.out.println("Given Subject is matched with the DB subject");
		}
		return ElementWait;
	}
	/**
	 * Name :     Vinothkumar.M
	 * Created Date:   19/Apr/15
	 * Description :Create a common method for Message received by Provider Body
	 * Ticket ID :
	 */
	public  static String messageReceivedByProviderBodyValidatedWithDB(WebDriver driver,String messageReceivedBodyTimeStampFromSheet, String getText118,String testData) throws ParseException, ClassNotFoundException, SQLException
	{
		String[] testData1 = testData.split(",");
		String inputEmail=testData1[0];
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
		ResultSet rs = stat.executeQuery("select Subject, Body, TO_NAME, TO_ROLE, FROM_NAME, FROM_ROLE from MESSAGE where TO_USER_ID = (select ID from provider where email ='"+inputEmail+"') AND Body LIKE '"+messageReceivedBodyTimeStampFromSheet+"% %' order by created_dt desc");
		String Body="";
		while(rs.next())
		{
			Body= rs.getString("BODY");
			System.out.println("Body is retrieved from the DB: "+Body);
		}
		if(getText118.equalsIgnoreCase( Body ))
		{
			System.out.println("Given body is matched with the DB body");
		}
		return ElementWait;
	}
	/**
	 * Name :     Vinothkumar.M
	 * Created Date:   20/Apr/15
	 * Description :Create a common method for Message received by Member Subject
	 * Ticket ID :
	 */
	public  static String messageReceivedByMemberSubjectValidatedWithDB(WebDriver driver,String messageReceivedMemberSubjectTimeStampFromSheet, String getText119,String testData) throws ParseException, ClassNotFoundException, SQLException
	{
		String[] testData1 = testData.split(",");
		String inputEmail=testData1[0];
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
		ResultSet rs = stat.executeQuery("select Subject, Body, TO_NAME, TO_ROLE, FROM_NAME, FROM_ROLE from MESSAGE where TO_USER_ID = (select ID from account where email ='"+inputEmail+"') AND Subject LIKE '"+messageReceivedMemberSubjectTimeStampFromSheet+"% %' order by created_dt desc");
		String Subject="";
		while(rs.next())
		{
			Subject= rs.getString("SUBJECT");
			System.out.println("Subject is retrieved from the DB: "+Subject);
		}
		if(getText119.equalsIgnoreCase( Subject ))
		{
			System.out.println("Given Subject is matched with the DB subject");
		}
		return ElementWait;
	}
	/**
	 * Name :     Vinothkumar.M
	 * Created Date:   20/Apr/15
	 * Description :Create a common method for Message received by Member Body
	 * Ticket ID :
	 */
	public  static String messageReceivedByMemberBodyValidatedWithDB(WebDriver driver,String messageReceivedMemberBodyTimeStampFromSheet, String getText119,String testData) throws ParseException, ClassNotFoundException, SQLException
	{
		String[] testData1 = testData.split(",");
		String inputEmail=testData1[0];
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
		ResultSet rs = stat.executeQuery("select Subject, Body, TO_NAME, TO_ROLE, FROM_NAME, FROM_ROLE from MESSAGE where TO_USER_ID = (select ID from account where email ='"+inputEmail+"') AND Body LIKE '"+messageReceivedMemberBodyTimeStampFromSheet+"% %' order by created_dt desc");
		String Body="";
		while(rs.next())
		{
			Body= rs.getString("BODY");
			System.out.println("Body is retrieved from the DB: "+Body);
		}
		if(getText119.equalsIgnoreCase( Body ))
		{
			System.out.println("Given Body is matched with the DB body");
		}
		return ElementWait;
	}
	/**
	 * Name :     Vinothkumar.M
	 * Created Date:   27/Apr/15
	 * Description :Create a common method for Message sent by Member Subject
	 * Ticket ID :
	 */
	public  static String messageSentByMemberSubjectValidatedWithDB(WebDriver driver,String messageSentMemberSubjectTimeStampFromSheet, String getText121,String testData) throws ParseException, ClassNotFoundException, SQLException
	{
		String[] testData1 = testData.split(",");
		String inputEmail=testData1[0];
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
		ResultSet rs = stat.executeQuery("select Subject, Body, FROM_NAME, FROM_ROLE, TO_NAME, TO_ROLE from MESSAGE where FROM_USER_ID = (select ID from account where email ='"+inputEmail+"') AND Subject LIKE '"+messageSentMemberSubjectTimeStampFromSheet+"% %' order by created_dt desc");
		String Subject="";
		while(rs.next())
		{
			Subject= rs.getString("SUBJECT");
			System.out.println("Subject is retrieved from the DB: "+Subject);
		}
		if(getText121.equalsIgnoreCase( Subject ))
		{
			System.out.println("Given Subject is matched with the DB subject");
		}
		return ElementWait;
	}
	/**
	 * Name :     Vinothkumar.M
	 * Created Date:   27/Apr/15
	 * Description :Create a common method for Message sent by Member body
	 * Ticket ID :
	 */
	public  static String messageSentByMemberBodyValidatedWithDB(WebDriver driver,String messageSentMemberBodyTimeStampFromSheet, String getText122,String testData) throws ParseException, ClassNotFoundException, SQLException
	{
		String[] testData1 = testData.split(",");
		String inputEmail=testData1[0];
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
		ResultSet rs = stat.executeQuery("select Subject, Body, FROM_NAME, FROM_ROLE, TO_NAME, TO_ROLE from MESSAGE where FROM_USER_ID = (select ID from account where email ='"+inputEmail+"') AND Body LIKE '"+messageSentMemberBodyTimeStampFromSheet+"% %' order by created_dt desc");
		String Body="";
		while(rs.next())
		{
			Body= rs.getString("BODY");
			System.out.println("Body is retrieved from the DB: "+Body);
		}
		if(getText122.equalsIgnoreCase( Body ))
		{
			System.out.println("Given Body is matched with the DB body");
		}
		return ElementWait;
	}
	/**
	 * Name :     Leena P.
	 * Created Date:   12/Sep/16
	 * Description :Create a common method for Coach name received by Member Subject
	 * Ticket ID :
	 */
	public  static String messageSentByMemberCoachNameValidatedWithDB(WebDriver driver,String messageReceivedMemberSubjectTimeStampFromSheet1, String getText1252,String testData) throws ParseException, ClassNotFoundException, SQLException
	{
		String[] testData1 = testData.split(",");
		String inputEmail=testData1[0];
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
		ResultSet rs = stat.executeQuery("select Subject, Body, TO_NAME, TO_ROLE, FROM_NAME, FROM_ROLE from MESSAGE where TO_USER_ID = (select ID from account where email ='"+inputEmail+"') AND Subject LIKE '"+messageReceivedMemberSubjectTimeStampFromSheet1+"% %' order by created_dt desc");
		String coachName="";
		while(rs.next())
		{
			coachName= rs.getString("FROM_NAME");
			System.out.println("Coach Name is retrieved from the DB: "+coachName);
		}
		if(getText1252.equalsIgnoreCase( coachName ))
		{
			System.out.println("Given Coach Name is matched with the DB subject");
		}
		return ElementWait;
	}
}