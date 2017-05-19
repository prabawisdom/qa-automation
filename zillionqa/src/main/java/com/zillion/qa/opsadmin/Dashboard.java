package com.zillion.qa.opsadmin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.zillion.qa.commands.Manipulation;
import com.zillion.qa.commands.Navigate;
import com.zillion.qa.utils.Directory;
public class Dashboard extends Manipulation implements OR
{
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   17/Dec/15
	 * Modified Date:   12/Jan/16
	 * Description :Create a common method for Login into OPS/Admin
	 * Ticket ID :
	 * Required Inputs :Username and Password
	 */
	public static void opsAdminLogin(WebDriver driver)
	{
		Navigate.get(driver, Directory.OpsAdminurl);
		Navigate.maximize(driver);
		wait(driver, "10");
		WebElement opslogin= driver.findElement(By.xpath(OR.OPS_ADMIN_USERNAME));
		waitForElement(driver,OR.OPS_ADMIN_USERNAME);
		sendKeys(opslogin, Directory.OpsAdminusername);
		WebElement opspassword= driver.findElement(By.xpath(OR.OPS_ADMIN_PASSWORD));
		sendKeys(opspassword, Directory.OpsAdminpassword);
		WebElement loginbutton= driver.findElement(By.xpath(OR.OPS_ADMIN_LOGIN));
		click(loginbutton);
		wait(driver, "5");
		try {
		//Navigate.waitTime(driver, "20");
		WebElement opslogopresent= driver.findElement(By.xpath(OR.OPS_ADMIN_ZILLION_LOGO));
		waitForElement(driver, OR.OPS_ADMIN_ZILLION_LOGO);
		verifyElementIsPresent(driver, opslogopresent);
		}
		catch(Exception e) {
			
			WebElement userSessionPopup = driver.findElement(By.xpath(PROVIDER_USER_SESSION_POPUP));
			click(userSessionPopup);
			WebElement opslogopresent= driver.findElement(By.xpath(OR.OPS_ADMIN_ZILLION_LOGO));
			waitForElement(driver, OR.OPS_ADMIN_ZILLION_LOGO);
			verifyElementIsPresent(driver, opslogopresent);
		}
		wait(driver, "5");
	}
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   4/Jan/16
	 * Modified Date:
	 * Description :   Create a common method for Logout for OPS/Admin
	 * Ticket ID :
	 * Required Inputs :  Username and Password
	 */
	public static void opsAdminLogout(WebDriver driver)
	{
		waitForElement(driver, OR.OPS_ADMIN_SIGNOUT_LINK);
		jsClickByXPath(driver, OR.OPS_ADMIN_SIGNOUT_LINK);
		jsClickByXPath(driver, OR.OPS_ADMIN_SIGNOUT_BUTTON);
		Navigate.waitTime(driver, "20");
		waitForElement(driver, OR.OPS_ADMIN_SIGNOUT_VERIFY);
		Navigate.waitTime(driver, "20");
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   15/Mar/16
	 * Modified Date:
	 * Description :   Create a common method to retrieve firstname and lastname of the member from the database
	 * Testcase Sheet :     SystemUser_Member_SelectNameInTheFilterAndTypeValidNameThatIsListedInTheTableInTheFormatFirstnameLasnameAndClickEnterOrSearchcheckIfResultsShown.xlsx
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	public static String retrieveNameOfTheMember(WebDriver driver, String mailId) throws ClassNotFoundException, SQLException
	{
		String firstname ="";
		String lastname ="";
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
		ResultSet rs = stat.executeQuery("select FIRST_NAME,LAST_NAME from Account where email = '"+mailId+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			firstname = rs.getString("FIRST_NAME");
			System.out.println(firstname);
			lastname = rs.getString("LAST_NAME");
			System.out.println(lastname);
		}
		
		return firstname+" "+lastname;
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   15/Mar/16
	 * Modified Date:
	 * Description :   Create a common method to retrieve firstname of the member from the database
	 * Testcase Sheet: SystemUser_Member_SelectNameInTheFilterAndTypeValidFirstnameAloneAndClickEnterOrSearchCheckIfResultShown.xlsx
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String retrieveFirstNameOfTheMember(WebDriver driver,String memberFirstName) throws ClassNotFoundException, SQLException
	{
		String firstname ="";
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
		ResultSet rs = stat.executeQuery("select FIRST_NAME,LAST_NAME from Account where email = '"+memberFirstName+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			firstname = rs.getString("FIRST_NAME");
			System.out.println(firstname);
		}
		return firstname;
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   15/Mar/16
	 * Modified Date:
	 * Description :   Create a common method to split first name of the member from the application
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String getMemberFirstNameFromApp(WebDriver driver, String inputData)
	{
		String[] splited = inputData.split(" ");
		String firstName=splited[0];
		return  firstName;
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   15/Mar/16
	 * Modified Date:
	 * Description :   Create a common method to split last name of the member from the application
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String getMemberLastNameFromApp(WebDriver driver, String inputData)
	{

		String[] splited = inputData.split(" ");
		String lastName=splited[1];
		return  lastName;
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   15/Mar/16
	 * Modified Date:
	 * Description :   Create a common method to retrieve lastname of the member from the database
	 * Testcase Sheet:
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String retrieveLastNameOfTheMember(WebDriver driver, String memberLastName) throws ClassNotFoundException, SQLException
	{
		String lastname ="";
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
		ResultSet rs = stat.executeQuery("select FIRST_NAME,LAST_NAME from Account where email = '"+memberLastName+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			lastname = rs.getString("LAST_NAME");
			System.out.println(lastname);
		}
		return lastname;
	}
	/**
	 * Name :    Suresh V
	 * Created Date:   17/Mar/16
	 */
	public static void launchOpsAdminUrl(WebDriver driver)
	{
		Navigate.get(driver, Directory.OpsAdminurl);
	}
	/**
	 * Name :    Suresh V
	 * Created Date:   18/Mar/16
	 */
	public static void getHttpUrlOpsAdmin(WebDriver driver)
	{
		driver.get(Directory.OpsAdminurl);
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   28/Mar/16
	 * Modified Date:
	 * Description :   Create a common method to retrieve id of the member from the database
	 * Testcase Sheet: SystemUser_Member_InThePAMemberProfileWhenTheEditOfTheMemberInfoIsClickedTheMemberIDShouldNotBeEditable.xlsx
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String retrieveIdOfTheMember(WebDriver driver,String mailId) throws ClassNotFoundException, SQLException
	{
		String memberid ="";
		String port = Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		System.out.println("mailId"+mailId);
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select ID from Account where email = '"+mailId+"'");
		System.out.println("query executed");
		System.out.println("mailId"+mailId);
		while(rs.next())
		{
			memberid = rs.getString("ID");
			System.out.println("ID : "+memberid);
		}
		return memberid;
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   28/Mar/16
	 * Modified Date:
	 * Description :   Create a common method to verify account status order of the member from the database
	 * Testcase Sheet:
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
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
	public static String retrieveInactivemember1(WebDriver driver) throws ClassNotFoundException, SQLException
	{
		String memberid ="";
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
		ResultSet rs = stat.executeQuery("select * from account where status = 'Inactive' and email like '%guerrillamail.com'");
		System.out.println("query executed");
		if(rs.next())
		{
			memberid = rs.getString("EMAIL");
			System.out.println(memberid);
		}
		return memberid;
	}
}