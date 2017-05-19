package com.zillion.qa.realappealprogramadmin;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;

import com.zillion.qa.commands.Manipulation;
import com.zillion.qa.commands.Navigate;
import com.zillion.qa.utils.Directory;
import com.zillion.qa.utils.Platform;

public class programadmin extends Manipulation implements OR
{
	/**
	 * Name :      Abinaya
	 * Created Date:   21/Apr/16
	 * Modified Date:
	 * Description :   Create a common method to retrieve minimum, opt and maximum limit of classroom
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	static String availablity_time1=null;
	static String split_hours=null;
	static String availablity_am_pm=null;
	static String split_minutes=null;
	static String date2=null;
	static String member_schdule_time=null;
	public static String retrieveLimitsOfClasses(WebDriver driver, String programname) throws ClassNotFoundException, SQLException
	{
		String max="";
		String min="";
		String opt="";
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
		ResultSet rs = stat.executeQuery("select classroom_max_size, classroom_min_size, classroom_optimal_size, program_name from mp_a_master_program where program_name = '"+programname+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			max= rs.getString("classroom_max_size");
			min= rs.getString("classroom_min_size");
			opt= rs.getString("classroom_optimal_size");
		}

		return "classroom_max_size: "+max+"\n"+"classroom_min_size: "+min+"\n"+"classroom_optimal_size: "+opt;
	}

	/**
	 * Name :      Abinaya
	 * Created Date:   21/Apr/16
	 * Modified Date:
	 * Description :   Create a common method to Launch provider URL
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void realAppealLaunchProviderURL(WebDriver driver)
	{
		driver.navigate().to(Directory.RA_Provider_Url);
	}

	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   21/Apr/2016
	 * Description :Create a common method for Login into Real Appeal OpsAdmin Login
	 * Ticket ID :
	 * Required Inputs :Username and Password
	 *
	 */
	public static void realAppealProgramAdminLogin(WebDriver driver)
	{
		Navigate.get(driver, Directory.RA_Provider_Url);
		Navigate.maximize(driver);
		wait(driver, "5");
		WebElement Palogin= driver.findElement(By.xpath(RA_PROGRAM_ADMIN_USERNAME));
		waitForElement(driver,RA_PROGRAM_ADMIN_USERNAME);
		sendKeys(Palogin, Directory.RA_PA_Username1);
		WebElement Papassword= driver.findElement(By.xpath(RA_PROGRAM_ADMIN_PASSWORD));
		sendKeys(Papassword, Directory.RA_PA_Pasword1);
		WebElement loginbutton= driver.findElement(By.xpath(RA_PROGRAM_ADMIN_LOGIN));
		click(loginbutton);
		wait(driver, "5");
		try{
			//Navigate.waitTime(driver, "20");
			WebElement Palogopresent= driver.findElement(By.xpath(RA_PROGRAM_ADMIN_REAL_APPEAL_LOGO));
			waitForElement(driver,RA_PROGRAM_ADMIN_REAL_APPEAL_LOGO);
			verifyElementIsPresent(driver, Palogopresent);
		}
		catch(Exception e) {
			WebElement userSessionPopup = driver.findElement(By.xpath(PROVIDER_USER_SESSION_POPUP));
			click(userSessionPopup);
			WebElement Palogopresent= driver.findElement(By.xpath(RA_PROGRAM_ADMIN_REAL_APPEAL_LOGO));
			waitForElement(driver,RA_PROGRAM_ADMIN_REAL_APPEAL_LOGO);
			verifyElementIsPresent(driver, Palogopresent);
		}
		wait(driver, "5");
	}

	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   21/Apr/2016
	 * Description :Create a common method for Login into Real Appeal OpsAdmin Logout
	 * Ticket ID :
	 * Required Inputs :Username and Password
	 *
	 */
	public static void realAppealProgramAdminLogout(WebDriver driver)
	{
		waitForElement(driver,RA_PROGRAM_ADMIN_SIGNOUT_LINK);
		jsClickByXPath(driver,RA_PROGRAM_ADMIN_SIGNOUT_LINK);
		jsClickByXPath(driver,RA_PROGRAM_ADMIN_SIGNOUT_BUTTON);
		Navigate.waitTime(driver, "20");
		waitForElement(driver,RA_PROGRAM_ADMIN_SIGNOUT_VERIFY);
		Navigate.waitTime(driver, "20");
	}

	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   21/Apr/2016
	 * Description :Create a common method for Login into Real Appeal OpsAdmin Logout
	 * Ticket ID :
	 * Required Inputs :Username and Password
	 *
	 */
	public static String compareAndVerifyTheApprovedAndUnapprovedClassesCount(WebDriver driver,String approvedCount,String unapprovedCount)
	{
		System.out.println("Before Approved count :"+approvedCount);
		System.out.println("Before UApproved count :"+unapprovedCount);
		String approvedCount1 = approvedCount.replaceAll("\\W", "");
		int Approved = Integer.parseInt(approvedCount1);
		System.out.println("First string:"+Approved);
		String unapprovedCount1 = unapprovedCount.replaceAll("\\W", "");
		int UnApproved = Integer.parseInt(unapprovedCount1);
		System.out.println("Second string:"+UnApproved);
		wait(driver, "2");
		if(UnApproved >= 2)
		{
			WebElement  first_checbox= driver.findElement(By.xpath( RA_PROGRAM_ADMIN_UNAPPROVED_TABLE_FIRST_CHECKBOX));
			click(first_checbox);
			wait(driver, "1");
			WebElement  action_btn= driver.findElement(By.xpath(UNAPPROVED_ACTION_BTN));
			verifyElementIsPresent(driver, action_btn);
			click(action_btn);
			WebElement  approve_link= driver.findElement(By.xpath(UNAPPROVED_APPROVE_LINK));
			click(approve_link);
			wait(driver, "1");
			WebElement  afterunapproved= driver.findElement(By.xpath(RA_PROGRAM_ADMIN_UNAPPROVED_COUNT));
			wait(driver, "2");
			String AfterunApprovedcount=afterunapproved.getText();
			System.out.println("after UnApproved count :"+AfterunApprovedcount);
			WebElement  approved_subtab= driver.findElement(By.xpath(RA_PROGRAM_ADMIN_APPROVED_SUB_TAB));
			click(approved_subtab);
			wait(driver, "4");
			WebElement  afterapproved= driver.findElement(By.xpath(RA_PROGRAM_ADMIN_UNAPPROVED_COUNT));
			String AfterApprovedcount=afterapproved.getText();
			System.out.println("After UnApproved count :"+AfterApprovedcount);
			String Afterapproved = AfterApprovedcount.replaceAll("\\W", "");
			int afterApproved = Integer.parseInt(Afterapproved);
			System.out.println("First string:"+afterApproved);
			String Afterunapproved = AfterunApprovedcount.replaceAll("\\W", "");
			int afterUnApproved = Integer.parseInt(Afterunapproved);
			System.out.println("Second string:"+afterUnApproved);
			if(afterApproved==Approved+2)
			{
				if(UnApproved==afterUnApproved+2)
				{
					return "Condition Matched";
				}
			}
			else
			{
				return "Condition Not Matched";
			}
		}
		else if(UnApproved == 1)
		{
			WebElement  first_checbox= driver.findElement(By.xpath( RA_PROGRAM_ADMIN_UNAPPROVED_TABLE_FIRST_CHECKBOX));
			click(first_checbox);
			wait(driver, "1");
			WebElement  action_btn= driver.findElement(By.xpath(UNAPPROVED_ACTION_BTN));
			verifyElementIsPresent(driver, action_btn);
			click(action_btn);
			WebElement  approve_link= driver.findElement(By.xpath(UNAPPROVED_APPROVE_LINK));
			click(approve_link);
			wait(driver, "1");
			WebElement  afterunapproved= driver.findElement(By.xpath(RA_PROGRAM_ADMIN_UNAPPROVED_COUNT));
			wait(driver, "2");
			String AfterunApprovedcount=afterunapproved.getText();
			System.out.println("after UnApproved count :"+AfterunApprovedcount);
			WebElement  approved_subtab= driver.findElement(By.xpath(RA_PROGRAM_ADMIN_APPROVED_SUB_TAB));
			click(approved_subtab);
			wait(driver, "2");
			String AfterApprovedcount=afterunapproved.getText();
			System.out.println("After UnApproved count :"+AfterApprovedcount);
			String Afterapproved = AfterApprovedcount.replaceAll("\\W", "");
			int afterApproved = Integer.parseInt(Afterapproved);
			System.out.println("First string:"+afterApproved);
			String Afterunapproved = AfterunApprovedcount.replaceAll("\\W", "");
			int afterUnApproved = Integer.parseInt(Afterunapproved);
			System.out.println("Second string:"+afterUnApproved);
			if(afterApproved==Approved+2)
			{
				if(UnApproved==afterUnApproved+2)
				{
					return "Condition Matched";
				}
			}
			else
			{
				return "Condition Not Matched";
			}
		}
		else if(UnApproved == 0)
		{
			WebElement  no_record= driver.findElement(By.xpath(UNAPPROVED_TABLE_LIST_EMPTY));
			verifyElementIsPresent(driver,  no_record);
			wait(driver, "5");
		}
		return " ";
	}
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   25/Apr/2016
	 * Description :Create a common method for verify the Schedule Classes
	 * Ticket ID :
	 *
	 */
	public static void verifyTheScheduleClasses(WebDriver driver)
	{
		int count = driver.findElements(By.xpath("//table [@id='tblData']//following::tbody[@id='insufficientClasses_tbody']/tr[*]/td[7]")).size();
		System.out.println(count);
		String classes="Scheduled";

		for(int i=1;i<=count;i++)
		{
			String Class_Status=driver.findElement( By.xpath( "//table [@id='tblData']//following::tbody[@id='insufficientClasses_tbody']/tr["+i+"]/td[7]")).getText();
			System.out.println(Class_Status);

			if(Class_Status.equalsIgnoreCase(classes))
			{
				WebElement click_here_link= driver.findElement(By.xpath("//a[text()='Click Here To View']"));
				WebElement click_here_text= driver.findElement(By.xpath("//div[@class='toolbar-input-group' and text()='You have classes that do not have a coach assigned. ']"));
				verifyElementIsPresent(driver, click_here_link);
				verifyElementIsPresent(driver, click_here_text);
				System.out.println("Should be displayed in scheduled class" + i);
			}
			else
			{
				WebElement click_here_link= driver.findElement(By.xpath("//a[text()='Click Here To View']"));
				WebElement click_here_text= driver.findElement(By.xpath("//div[@class='toolbar-input-group' and text()='You have classes that do not have a coach assigned. ']"));
				verifyElementIsNotPresent(driver, click_here_link);
				verifyElementIsNotPresent(driver, click_here_text);
				System.out.println(" Should not  be displayed in scheduled class" +i);
			}
		}
	}

	/**
	 * Name :     Abinaya
	 * Created Date:   26/Apr/2016
	 * Description :Create a common method for validate unapproved class name on coach
	 * Ticket ID :
	 * Required Inputs :
	 *
	 */
	public static void validateClassOnCoach(WebDriver driver, String classname)
	{
		int size=driver.findElements(By.xpath("//td[@id='approvedClasses_Item_ClassID*']")).size();
		for(int i=1;i<size;i++)
		{
			String classname1=driver.findElement(By.xpath("//td[@id='approvedClasses_Item_ClassID"+i+"']")).getText();
			if(classname.equals(classname1))
			{
				System.out.println("");
			}
			{
				System.out.println("");
			}
		}
	}

	/**
	 * Name :     Abinaya
	 * Created Date:   26/Apr/2016
	 * Description :Create a common method for Check the Enrollment status for RA Member
	 * Ticket ID :
	 * Required Inputs :
	 *
	 */
	public  static String dBRealAppealMemberEnrollmentStatus(WebDriver driver) throws ClassNotFoundException, SQLException
	{
		WebElement Email_ID= driver.findElement(By.xpath(INSUFFICIENT_PROGRAM_MEMBER_PROFILE_EMAIL));
		String Member_Email_ID=Email_ID.getAttribute("value");
		System.out.println(Member_Email_ID);
		//Data base Script
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
		ResultSet rs = stat.executeQuery(" select onboarding_status from summary_account_todate where account_id=(select id from account where email='"+Member_Email_ID+"')");;
		System.out.println("query executed");
		String DBEnrollmentstatus="re";
		while(rs.next())
		{
			DBEnrollmentstatus = rs.getString("onboarding_status");
			System.out.println("DBEnrollmentstatus"+ DBEnrollmentstatus);
		}
		return DBEnrollmentstatus;
	}

	/**
	 * Name :  Vinothkumar.M
	 * Created Date:   9/May/2016
	 * Description :Create a common method for Classroom not approved
	 * Ticket ID :
	 * Required Inputs :
	 *
	 */
	public  static String verifyDBOnboardingStatus(WebDriver driver) throws ClassNotFoundException, SQLException
	{
		String DBEnrollmentstatus = null;
		String Member_Enrollment_status=null;
		wait(driver, "2");
		int count =driver.findElements(By.xpath("//table[@id='memberInfoTable']/tbody/tr/following::tr[*]/td[2]/div[1]/a")).size();
		System.out.println(count);
		WebElement Member_name= driver.findElement(By.xpath("//table[@id='memberInfoTable']/tbody/tr/following::tr[1]/td[2]/div[1]/a"));
		wait(driver, "2");
		click(Member_name);
		wait(driver, "3");
		WebElement Member_Enrollment_status1= driver.findElement(By.xpath(INSUFFICIENT_PROGRAM_MEMBER_PROFILE_ENROLLMENT_STATUS));
		Member_Enrollment_status=Member_Enrollment_status1.getText();
		wait(driver, "3");
		WebElement Email_ID= driver.findElement(By.xpath(INSUFFICIENT_PROGRAM_MEMBER_PROFILE_EMAIL));
		String Member_Email_ID=Email_ID.getAttribute("value");
		System.out.println(Member_Email_ID);
		System.out.println("Member_Enrollment_status: "+Member_Enrollment_status);

		//Data base Script
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
		ResultSet rs = stat.executeQuery("select onboarding_status from summary_account_todate where account_id=(select id from account where email='"+Member_Email_ID+"')");
		System.out.println("query executed");
		while(rs.next())
		{
			DBEnrollmentstatus = rs.getString("onboarding_status");
			System.out.println("DBEnrollmentstatus :"+ DBEnrollmentstatus);
		}
		wait(driver, "2");

		if(DBEnrollmentstatus.equalsIgnoreCase(Member_Enrollment_status))
		{
			System.out.println("Conditions Matched");

		}
		else
		{
			System.out.println("Conditions Not Matched");
		}


		return Member_Enrollment_status;
	}
	/**
	 * Name :Suresh.V
	 * Created Date: 11/May/2016
	 * Modified Date:
	 * Description :   Create a common method for Verifying Unapproved RAPD classifications
	 * @throws FindFailed
	 **/
	public static String verifyUnapprovedRAPDclassifications(WebDriver driver)
	{
		WebElement  unapproved= driver.findElement(By.xpath(RA_PROGRAM_ADMIN_UNAPPROVED_SUB_TAB));
		click(unapproved);
		waitForElement(driver, RA_PROGRAM_ADMIN_UNAPPROVED_HEADER);
		WebElement  unapproved_count= driver.findElement(By.xpath(RA_PROGRAM_ADMIN_UNAPPROVED_COUNT));
		verifyElementIsPresent(driver, unapproved_count);
		wait(driver, "2");
		String BeforeunApproved=unapproved_count.getText();
		System.out.println("Before UnApproved count :"+BeforeunApproved);
		String unapprovedcount = BeforeunApproved.replaceAll("\\W", "");
		int UnApproved = Integer.parseInt(unapprovedcount);
		System.out.println("Second string:"+UnApproved);
		wait(driver, "2");
		String Unapproved_Classification="RA-PD";
		int count = driver.findElements(By.xpath("//table[@id='unapprovedClassesTable']//following::tbody/tr[*]//td[3]")).size();
		System.out.println(count);

		for(int i=1;i<=count;i++)
		{
			String Classifications=driver.findElement( By.xpath( "//table[@id='unapprovedClassesTable']//following::tbody/tr["+i+"]//td[3]")).getText();
			System.out.println(Classifications);

			if(Unapproved_Classification.equalsIgnoreCase(Classifications))
			{
				if(Unapproved_Classification.equalsIgnoreCase(Classifications))
				{
					return "Condition Matched";
				}
				else
				{
					return "Condition Not Matched";
				}
			}
			else if(UnApproved == 0)
			{
				WebElement  no_record= driver.findElement(By.xpath(UNAPPROVED_TABLE_LIST_EMPTY));
				verifyElementIsPresent(driver,  no_record);
				wait(driver, "5");
				return "There are no Unapproved classes";
			}
		}
		WebElement  unapproved_class_link= driver.findElement(By.xpath("//i[@class='fa fa-chevron-right']"));
		click(unapproved_class_link);
		int membercount = driver.findElements(By.xpath("//table[@id='memberInfoTable']//tr[1]/following-sibling::tr[*]")).size();
		System.out.println(count);
		if(membercount>=2)
		{
			return "Condition Matched";
		}
		else
		{
			return "Condition Not Matched";
		}
	}

	/**
	 * Name :VinothKumar.M
	 * Created Date: 10/May/2016
	 * Modified Date:
	 * Description :   Create a common method for get Attribute value
	 */
	public  static String getAttribute(WebDriver driver,WebElement element)
	{
		//WebElement Email_ID= driver.findElement(By.xpath(INSUFFICIENT_PROGRAM_MEMBER_PROFILE_EMAIL));
		String Member_Email_ID=element.getAttribute("value");
		return Member_Email_ID;
	}

	/**
	 * Name :VinothKumar.M
	 * Created Date: 10/May/2016
	 * Modified Date:
	 * Description :   Create a common method for Verifying DB for Member onboarding status Classroom Not started
	 */
	public  static String verifyDBOnboardingStatusClassroomNotStarted(WebDriver driver) throws ClassNotFoundException, SQLException
	{
		String DBEnrollmentstatus = null;
		WebElement Email_ID= driver.findElement(By.xpath(INSUFFICIENT_PROGRAM_MEMBER_PROFILE_EMAIL));
		String Member_Email_ID=Email_ID.getAttribute("value");
		System.out.println(Member_Email_ID);
		WebElement Member_Enrollment_status1= driver.findElement(By.xpath(INSUFFICIENT_PROGRAM_MEMBER_PROFILE_ENROLLMENT_STATUS));
		String Member_Enrollment_status=Member_Enrollment_status1.getText();
		System.out.println(Member_Enrollment_status);
		wait(driver, "3");

		//Data base Script
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
		ResultSet rs = stat.executeQuery(" select onboarding_status from summary_account_todate where account_id=(select id from account where email='"+Member_Email_ID+"')");
		System.out.println("query executed");
		while(rs.next())
		{
			DBEnrollmentstatus = rs.getString("onboarding_status");
			System.out.println("DBEnrollmentstatus :"+ DBEnrollmentstatus);
		}

		if(DBEnrollmentstatus.equalsIgnoreCase( Member_Enrollment_status ))
		{
			return "Conditions Matched";
		}
		else
		{
			return "Conditions are not Matched";
		}
	}

	/**
	 * Name :VinothKumar.M
	 * Created Date: 10/May/2016
	 * Modified Date:
	 * Description :   Create a common method for Verifying prepone and postpone scheduled class
	 */
	public static void scheduleClassPreponePostponeVerify(WebDriver driver)
	{
		try
		{
			WebElement scheduleClass= driver.findElement(By.xpath(INSUFFICIENT_PROGRAM_MEMBER_PROFILE_ENROLLMENT_STATUS));
			WebElement scheduleClassPoreponePostPoneUnavailable= driver.findElement(By.xpath(INSUFFICIENT_PROGRAM_MEMBER_PROFILE_ENROLLMENT_STATUS));
			WebElement insufficientClass= driver.findElement(By.xpath(INSUFFICIENT_PROGRAM_INSUFFICIENT_CLASS));
			WebElement insufficientClassPoreponeAvailable= driver.findElement(By.xpath(INSUFFICIENT_PROGRAM_INSUFFICIENT_CLASS_PREPONE_AVAILABLE));
			WebElement insufficientClassPostponeAvailable= driver.findElement(By.xpath(INSUFFICIENT_PROGRAM_INSUFFICIENT_CLASS_POSTPONE_AVAILABLE));
			verifyElementIsPresent(driver, scheduleClass);
			verifyElementIsNotPresent(driver, scheduleClassPoreponePostPoneUnavailable);
			verifyElementIsPresent(driver, insufficientClass);
			verifyElementIsPresent(driver, insufficientClassPoreponeAvailable);
			verifyElementIsPresent(driver, insufficientClassPostponeAvailable);
			System.out.println("Schedule class has no Prepone and post pone option");
		}
		catch(Exception e)
		{
			System.out.println("Schedule Class is not available");
		}
	}

	/**
	 * Name :VigneshRaj.M
	 * Created Date: 10/May/2016
	 * Modified Date:
	 * Description :   Create a common method for Verifying member inbox pagination
	 */
	public  static String verifyMemberMessageInboxPagination(WebDriver driver)
	{
		WebElement Pagination_count= driver.findElement(By.xpath("//div[@class='pagination-btn pull-right']/span/b[3]"));
		String Inbox_pagination_count=Pagination_count.getText();
		int Inbox_pagination_count1 = Integer.parseInt(Inbox_pagination_count);
		System.out.println(Inbox_pagination_count);

		if(Inbox_pagination_count1 > 10)
		{
			return "pagination button should be Enabled";
		}
		else
		{
			return "pagination button should be Disabled";
		}
	}

	/**
	 * Name :Suresh
	 * Created Date: 10/May/2016
	 * Modified Date:
	 * Description :   Create a common method for Verifying DB for Member onboarding status
	 */
	public  static String verifyMemberDBOnboardingStatus(WebDriver driver) throws ClassNotFoundException, SQLException
	{
		String DBEnrollmentstatus="";
		String Message="Onboarding status from application and Db matched";
		WebElement Email_ID= driver.findElement(By.xpath(INSUFFICIENT_PROGRAM_MEMBER_PROFILE_EMAIL));
		String Member_Email_ID=Email_ID.getAttribute("value");
		System.out.println(Member_Email_ID);
		WebElement Member_Enrollment_status1= driver.findElement(By.xpath(INSUFFICIENT_PROGRAM_MEMBER_PROFILE_ENROLLMENT_STATUS));
		String Member_Enrollment_status=Member_Enrollment_status1.getText();
		System.out.println("Member_Enrollment_status :" +Member_Enrollment_status);
		wait(driver, "3");

		//Data base Script
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
		ResultSet rs = stat.executeQuery(" select onboarding_status from summary_account_todate where account_id=(select id from account where email='"+Member_Email_ID+"')");
		System.out.println("query executed");
		if(rs.next())
		{
			DBEnrollmentstatus = rs.getString("ONBOARDING_STATUS");
			System.out.println("DBEnrollmentstatus :"+ DBEnrollmentstatus);
		}
		if(DBEnrollmentstatus.equalsIgnoreCase(Member_Enrollment_status))
		{
			System.out.print(Message);
		}
		return DBEnrollmentstatus+":"+Message;
	}

	/**
	 * Name :VigneshRaj.M
	 * Created Date: 30/May/2016
	 * Modified Date:
	 * Description :   Create a common method for Real appeal member password
	 **/
	public  static void rAmemberEnterPassword(WebDriver driver)
	{
		WebElement member_password= driver.findElement(By.xpath(RA_MEMBER_PASSWORD));
		try
		{
			wait(driver, "2");
			sendKeys(member_password, "Password1");
			waitForElement(driver,RA_MEMBER_LOGIN_BUTTON);
			jsClickByXPath(driver,RA_MEMBER_LOGIN_BUTTON);
			try
			{
				wait(driver, "2");
				clear(member_password);
				sendKeys(member_password, "Password2");
				waitForElement(driver,RA_MEMBER_LOGIN_BUTTON);
				jsClickByXPath(driver,RA_MEMBER_LOGIN_BUTTON);
				try
				{
					wait(driver, "2");
					clear(member_password);
					sendKeys(member_password, "Zillion2016");
					waitForElement(driver,RA_MEMBER_LOGIN_BUTTON);
					jsClickByXPath(driver,RA_MEMBER_LOGIN_BUTTON);
				}
				catch(Exception e)
				{
					System.out.println("Password are in matched");
				}
			}
			catch(Exception e)
			{
				System.out.println("Password not in matched");
			}
		}
		catch(Exception e)
		{
			System.out.println("Password are in matched");
		}
		Navigate.waitTime(driver, "20");
		System.out.println("Coaches Logged in successfully");
	}
	/**
	 * Name :VigneshRaj.M
	 * Created Date: 30/May/2016
	 * Modified Date:
	 * Description :   Create a common method for

	 **/
	public static String realAppealMemberDatesession(WebDriver driver)
	{
		try
		{
			String CurrentDateOR = "//div[@id='timeSelection']/div/div/div/div/span";
			WebElement CurrentDate = driver.findElement(By.xpath(CurrentDateOR));
			date2 = CurrentDate.getText();
			String CurrentDateEvents = "//span[text()='"+date2+"']/parent::div/following-sibling::div/button[@ng-disabled='false']";
			WebElement Currentevents = driver.findElement(By.xpath(CurrentDateEvents));
			click( Currentevents );
			wait(driver, "3");
			WebElement CurrenteventsDrop = driver.findElement(By.xpath("//ul[@class='dropdown-menu schedule-default-menu']/li[2]/a"));
			member_schdule_time=CurrenteventsDrop.getText();
			System.out.println("Time : "+member_schdule_time);
			wait(driver, "5");
		}
		catch(Exception e)
		{
			String CurrentDateOR = "//div[@id='timeSelection']/div[2]/div/div/div/span//following::div[5]/following::div/div/span";
			WebElement CurrentDate = driver.findElement(By.xpath(CurrentDateOR));
			String date2 = CurrentDate.getText();
			String CurrentDateEvents = "//span[text()='"+date2+"']/parent::div/following-sibling::div/button[@ng-disabled='false']";
			WebElement Currentevents = driver.findElement(By.xpath(CurrentDateEvents));
			click( Currentevents );
			wait(driver, "3");
			WebElement CurrenteventsDrop = driver.findElement(By.xpath("//ul[@class='dropdown-menu schedule-default-menu']/li[2]/a"));
			member_schdule_time=CurrenteventsDrop.getText();
			System.out.println("Time : "+member_schdule_time);
			wait(driver, "5");
		}
		String sessiontime[]=member_schdule_time.split(":");
		split_hours = sessiontime[0];
		String aftersplit_sessiontime= sessiontime[1];
		availablity_am_pm = aftersplit_sessiontime.substring(2);
		System.out.println("After convertion5 :" +availablity_am_pm);
		String[] split_minute=aftersplit_sessiontime.split(availablity_am_pm);
		split_minutes= split_minute[0];
		String availablity_time=split_hours+":"+split_minutes+" "+availablity_am_pm;
		availablity_time1= availablity_time.toLowerCase();
		System.out.println("After convertion5 :" +availablity_time1);
		return availablity_time1;
	}

	/**
	 * Name :VigneshRaj.M
	 * Created Date: 30/May/2016
	 * Modified Date:
	 * Description :   Create a common method for split and return the session timing
	 **/
	public static String realAppealMemberDatesession1(WebDriver driver) throws ParseException
	{
		String availablity_time7=split_hours+":"+split_minutes;
		SimpleDateFormat df = new SimpleDateFormat("H:mm");
		Date d = df.parse(availablity_time7);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.MINUTE, 15);
		String newTime = df.format(cal.getTime());
		String AddnewTime1=newTime+" "+availablity_am_pm;
		String AddnewTime2=AddnewTime1.toLowerCase();
		System.out.println("After convertion1 :" +AddnewTime2);
		return AddnewTime2;
	}

	/**
	 * Name :VigneshRaj.M
	 * Created Date: 30/May/2016
	 * Modified Date:
	 * Description :   Create a common method for Coach unavailability checkbox
	 **/
	public static void realAppealcoachUnavailabilitycheckbox(WebDriver driver)
	{
		String[] day=date2.split(",");
		String currentday=day[0];
		System.out.println("After Split :" +currentday);
		if("Monday".equalsIgnoreCase(currentday))
		{
			WebElement monday_chechbox = driver.findElement(By.xpath("//ul[@id='scheduleDaysOfWeek']//li[2]//input[@id='checkMonday']"));
			click(monday_chechbox);
		}
		else if("Tuesday".equalsIgnoreCase(currentday))
		{
			WebElement tuesday_chechbox = driver.findElement(By.xpath("//ul[@id='scheduleDaysOfWeek']//li[3]//input[@id='checkTuesday']"));
			click(tuesday_chechbox);
		}
		else if("Wednesday".equalsIgnoreCase(currentday))
		{
			WebElement wednesday_chechbox = driver.findElement(By.xpath("//ul[@id='scheduleDaysOfWeek']//li[4]//input[@id='checkWednesday']"));
			click(wednesday_chechbox);
		}
		else if("Thursday".equalsIgnoreCase(currentday))
		{
			WebElement thursday_chechbox = driver.findElement(By.xpath("//ul[@id='scheduleDaysOfWeek']//li[5]//input[@id='checkThursday']"));
			click(thursday_chechbox);
		}
		else if("Friday".equalsIgnoreCase(currentday))
		{
			WebElement friday_chechbox = driver.findElement(By.xpath("//ul[@id='scheduleDaysOfWeek']//li[6]//input[@id='checkFriday']"));
			click(friday_chechbox);
		}
		else if("Saturday".equalsIgnoreCase(currentday))
		{
			WebElement saturday_chechbox = driver.findElement(By.xpath("//ul[@id='scheduleDaysOfWeek']//li[7]//input[@id='checkSaturday']"));
			click(saturday_chechbox);
		}
		else if("Sunday".equalsIgnoreCase(currentday))
		{
			WebElement sunday_chechbox = driver.findElement(By.xpath("//ul[@id='scheduleDaysOfWeek']//li[1]//input[@id='checkSunday']"));
			click(sunday_chechbox);
		}
	}

	/**
	 * Name :VigneshRaj.M
	 * Created Date: 30/May/2016
	 * Modified Date:
	 * Description :   Create a common method for coach Enter password
	 **/
	public  static void rACoachEnterPassword(WebDriver driver) throws Exception
	{
		WebElement coach_password= driver.findElement(By.xpath(RA_COACH_PASSWORD));
		WebElement Coacheslogin_button= driver.findElement(By.xpath(COACHES_LOGIN_BUTTON));
		try
		{
			wait(driver, "2");
			sendKeys(coach_password,"Healthfleet2015");
			wait(driver, "2");
			click(Coacheslogin_button);
			wait(driver, "2");
			try
			{
				wait(driver, "2");
				clear( coach_password );
				sendKeys(coach_password,"Zillion2016");
				wait(driver, "2");
				click(Coacheslogin_button);
				wait(driver, "2");
				try
				{
					wait(driver, "2");
					clear( coach_password );
					sendKeys(coach_password,"Healthfleet2016");
					wait(driver, "2");
					click(Coacheslogin_button);
					wait(driver, "3");
				}

				catch(Exception e)
				{
					System.out.println("Password are in matched");
				}
			}
			catch(Exception e)
			{
				System.out.println("Password not in matched");
			}
		}
		catch(Exception e)
		{
			System.out.println("Password are in matched");
		}
		Navigate.waitTime(driver, "20");
		System.out.println("Coaches Logged in successfully");
	}

	/**
	 * Name :VigneshRaj.M
	 * Created Date: 30/May/2016
	 * Modified Date:
	 * Description :   Create a common method for
	 **/
	public static void realAppealMemberChangeUnavailability(WebDriver driver)
	{
		String CurrentDateOR = "//div[@id='timeSelection']/div/div/div/div/span";
		WebElement CurrentDate = driver.findElement(By.xpath(CurrentDateOR));
		date2 = CurrentDate.getText();
		String CurrentDateEvents = "//span[text()='"+date2+"']/parent::div/following-sibling::div/button[@ng-disabled='false']";
		WebElement Currentevents = driver.findElement(By.xpath(CurrentDateEvents));
		click( Currentevents );
		wait(driver, "3");
		try
		{
			WebElement CurrenteventsDrop1 =driver.findElement(By.xpath("//ul[@class='dropdown-menu schedule-default-menu']/li/a[text()='"+member_schdule_time+"']"));
			verifyElementIsNotPresent(driver, CurrenteventsDrop1 );
		}
		catch(Exception e)
		{
			System.out.println("Schedule Class is not available");
		}
		wait(driver, "5");
	}

	/**
	 * Name :VigneshRaj.M
	 * Created Date: 30/May/2016
	 * Modified Date:
	 * Description :   Create a common method for select unavailability Date
	 **/
	public static void currentDateCoachUnavailableTimeSlot(WebDriver driver)
	{
		String[] day=date2.split(",");
		String scheduledate=day[1].trim();
		DateFormat df = new SimpleDateFormat("MMMMM dd");
		Date dateobj = new Date();
		String currentDate1=df.format(dateobj);
		System.out.println("time:"+currentDate1);
		if(currentDate1.equalsIgnoreCase(scheduledate))
		{
			System.out.println("Currentdate");
		}
		else
		{
			WebElement Currentdateclick=driver.findElement(By.xpath(OR.COACH_UNAVAILABILITY_START_DATE));
			click(Currentdateclick);
			String currentdate2 = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
			System.out.println("Currentdate: "+currentdate2);
			int currentdate4 = Integer.parseInt(currentdate2);
			int currentdate5 = currentdate4+1;
			String currentdate6 = Integer.toString(currentdate5);
			System.out.println("Next Current date: "+currentdate6);
			wait(driver, "7");
			WebElement currentdate12=driver.findElement(By.xpath(OR.COACH_TIMESLOT_CHECK_FROM_START+currentdate6+COACH_TIMESLOT_CHECK_FROM_END));
			verifyElementIsPresent(driver,currentdate12);
			click(currentdate12);
			System.out.println("Next date of the current date is selected");
		}
	}

	/**
	 * NAME:SURESH.V
	 * Created Date:2.06.2016
	 * Description :   Create a common method for verifying insufficient scheduled status
	 */
	public static void insufficientScheduledStatusVerify(WebDriver driver)
	{
		String pagination = driver.findElement(By.xpath(INSUFFICIENT_CLASS_PAGINATION)).getText();
		System.out.println("Page Size : "+pagination);
		int pageSize = Integer.parseInt(pagination);
		System.out.println("Converted Page size : "+pagination);
		int PageCount = 0;
		System.out.println("Total Page : "+PageCount);
		try
		{
			if(pageSize<=10)
			{
				int Classsize = driver.findElements(By.xpath(OR.INSUFFICIENT_CLASSES_ROW+"*"+SCHEDULED_STATUS_IN_INSUFFICIENT_CLASS)).size();
				System.out.println("Insufficient Class size : "+Classsize);
				for(int i = 1;i<=Classsize;i++)
				{
					String InsufficientStatus = driver.findElement(By.xpath(INSUFFICIENT_CLASSES_ROW+i+SCHEDULED_STATUS_IN_INSUFFICIENT_CLASS)).getText();
					System.out.println("Insufficient Status : "+InsufficientStatus);
					if(InsufficientStatus.equalsIgnoreCase("Scheduled"))
					{
						WebElement StatusInsufficient=driver.findElement(By.xpath(INSUFFICIENT_CLASSES_ROW+i+SCHEDULED_STATUS_IN_INSUFFICIENT_CLASS));
						click(StatusInsufficient);
						wait(driver, "2");
						break;
					}
				}
			}
			else
			{
				PageCount = pageSize/10;
				System.out.println("Total Page Line : "+PageCount);
				breakLoop:
					for(int i=1;i<=PageCount;i++)
					{
						int Classsize = driver.findElements(By.xpath(OR.INSUFFICIENT_CLASSES_ROW+"*"+SCHEDULED_STATUS_IN_INSUFFICIENT_CLASS)).size();
						System.out.println("Insufficient Class size : "+Classsize);

						for(int j = 1;j<=Classsize;j++)
						{
							String InsufficientStatus = driver.findElement(By.xpath(INSUFFICIENT_CLASSES_ROW+j+SCHEDULED_STATUS_IN_INSUFFICIENT_CLASS)).getText();
							System.out.println("Insufficient Status : "+InsufficientStatus);
							if(InsufficientStatus.equalsIgnoreCase("Scheduled"))
							{
								WebElement StatusInsufficient=driver.findElement(By.xpath(INSUFFICIENT_CLASSES_ROW+j+SCHEDULED_STATUS_IN_INSUFFICIENT_CLASS));
								click(StatusInsufficient);
								wait(driver, "2");
								break breakLoop;
							}
						}
						WebElement InsufficientNext=driver.findElement(By.xpath(INSUFFICIENT_CLASS_NEXT_BUTTON));
						click(InsufficientNext);
						wait(driver, "2");
					}
			}
		}
		catch(Exception e)
		{

		}
	}

	/**
	 * Name:Suresh V
	 * Creation Date:10-June-2016
	 * Description :   Create a common method for verifying insufficient HBMI scheduled status
	 */
	public static void insufficientHBMIScheduledStatusVerify(WebDriver driver)
	{
		String pagination = driver.findElement(By.xpath(INSUFFICIENT_CLASS_PAGINATION)).getText();
		System.out.println("Page Size : "+pagination);
		int pageSize = Integer.parseInt(pagination);
		System.out.println("Converted Page size : "+pagination);
		int PageCount = 0;
		System.out.println("Total Page : "+PageCount);
		try
		{
			if(pageSize<=10)
			{
				int Classsize = driver.findElements(By.xpath(OR.INSUFFICIENT_CLASSES_ROW+"*"+SCHEDULED_STATUS_IN_INSUFFICIENT_CLASS)).size();
				System.out.println("Insufficient Class size : "+Classsize);
				for(int i = 1;i<=Classsize;i++)
				{
					String InsufficientStatus = driver.findElement(By.xpath(INSUFFICIENT_CLASSES_ROW+i+SCHEDULED_STATUS_IN_INSUFFICIENT_CLASS)).getText();
					System.out.println("Insufficient Status : "+InsufficientStatus);
					if(InsufficientStatus.equalsIgnoreCase("Scheduled"))
					{
						WebElement StatusInsufficient=driver.findElement(By.xpath(INSUFFICIENT_CLASSES_ROW+i+SCHEDULED_STATUS_IN_INSUFFICIENT_CLASS));
						click(StatusInsufficient);
						wait(driver, "2");
						WebElement scheduledMemberInsufficient=driver.findElement(By.xpath(INSUFFICIENT_MEMBER_NAME));
						verifyElementIsPresent(driver,scheduledMemberInsufficient);
						break;
					}
				}
			}
			else
			{
				PageCount = pageSize/10;
				System.out.println("Total Page Line : "+PageCount);
				breakLoop:
					for(int i=1;i<=PageCount;i++)
					{
						int Classsize = driver.findElements(By.xpath(OR.INSUFFICIENT_CLASSES_ROW+"*"+SCHEDULED_STATUS_IN_INSUFFICIENT_CLASS)).size();
						System.out.println("Insufficient Class size : "+Classsize);
						for(int j = 1;j<=Classsize;j++)
						{
							String InsufficientStatus = driver.findElement(By.xpath(INSUFFICIENT_CLASSES_ROW+j+SCHEDULED_STATUS_IN_INSUFFICIENT_CLASS)).getText();
							System.out.println("Insufficient Status : "+InsufficientStatus);
							if(InsufficientStatus.equalsIgnoreCase("Scheduled"))
							{
								WebElement StatusInsufficient=driver.findElement(By.xpath(INSUFFICIENT_CLASSES_ROW+j+SCHEDULED_STATUS_IN_INSUFFICIENT_CLASS));
								click(StatusInsufficient);
								wait(driver, "2");
								//break;
								break breakLoop;
							}
						}
						WebElement InsufficientNext=driver.findElement(By.xpath(INSUFFICIENT_CLASS_NEXT_BUTTON));
						click(InsufficientNext);
						wait(driver, "2");
					}
			}
		}
		catch(Exception e)
		{

		}
	}

	/**
	 * Name :VigneshRaj.M
	 * Created Date: 09/June/2016
	 * Modified Date:
	 * Description :   Create a common method for select Last month availability  Date
	 **/
	public static void insufficientTabSelectClassDatePreviousMonthCurrentDate(WebDriver driver)
	{
		String currentMonth = new SimpleDateFormat("MM").format(Calendar.getInstance().getTime());
		String currentYear = new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
		System.out.println("Current Year :"+currentYear);
		String currentDate = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
		System.out.println("Current Date :"+currentDate);
		String consolidatedDateFormat = currentYear+"-"+currentMonth+"-"+currentDate;
		WebElement InsufficientStartDateTextbox=driver.findElement(By.xpath(INSUFFICIENT_CLASS_START_DATE_TEXTBOX));
		click(InsufficientStartDateTextbox);
		sendKeys(InsufficientStartDateTextbox, consolidatedDateFormat);
		System.out.println("Consolidated date format :"+consolidatedDateFormat);
	}

	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   13/JUNE/16
	 * Modified Date:
	 * Description : Create a common method to IS_AVAILABLEHOURS is equal to 1.
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public  static String retrieveEnableProviderHoursIsOne(WebDriver driver,String testData) throws ParseException, ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select ID, Name, IS_ENABLED_PROVIDER_HOURS from Organization");
		System.out.println("query executed");
		String EnabledProviderHoursShouldBeOne=testData;
		String IsEnabledProviderHours= "";

		if(rs.next())
		{
			IsEnabledProviderHours= rs.getString("IS_ENABLED_PROVIDER_HOURS").trim();
			String OrganizationName = rs.getString("NAME");
			String ProviderID = rs.getString("ID");
			System.out.println("Is Enabled Provider Hours is : "+IsEnabledProviderHours);
			System.out.println("Name of the Organization :"+OrganizationName);
			System.out.println("Provider ID : "+ProviderID);

			if(EnabledProviderHoursShouldBeOne.equalsIgnoreCase(IsEnabledProviderHours))
			{
				System.out.println("IS Enabled Provider hours 1 is verified");
			}
			else
			{
				System.out.println("IS Enabled Provider hours 1 is not verified");
				Assert.fail();
			}
		}
		return IsEnabledProviderHours;
	}

	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   13/JUNE/16
	 * Modified Date:
	 * Description : Create a common method to retrieve Account Id from the Mail
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public  static String retrieveAccountIDWithMemberEmailID(WebDriver driver, String accountIdRef12) throws ParseException, ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select ID from account where email='"+accountIdRef12+"'");
		System.out.println("query executed");
		String accountID="";

		if(rs.next())
		{
			accountID= rs.getString("ID");
			System.out.println("Account ID is  : "+accountID);
		}

		return accountID;
	}

	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   13/JUNE/16
	 * Modified Date:
	 * Description : Create a common method to retrieve Classroom ID with the Account ID
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public  static String retrieveClassRoomIDWithMemberAccountID(WebDriver driver, String classRoomIDref12) throws ParseException, ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select classroom_id  from classroom_account_signup where account_id = '"+classRoomIDref12+"'");
		System.out.println("query executed");
		String classroom_ID="";

		if(rs.next())
		{
			classroom_ID= rs.getString("CLASSROOM_ID");
			System.out.println("Classroom ID is  : "+classroom_ID);
		}

		return classroom_ID;
	}

	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   13/JUNE/16
	 * Modified Date:
	 * Description : Create a common method to retrieve Is_Approved and Approved with the Classroom_ID
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public  static String retrieveIsApprovedAndApprovedWithMemberClassRoomID(WebDriver driver,String classroom_ID) throws ParseException, ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select Is_approved, APPROVED from calendar_event where classroom_id='"+classroom_ID+"'");
		System.out.println("query executed");
		String Is_approved="";
		String Approved="";
		String Value_Result="";

		while(rs.next())
		{
			Is_approved= rs.getString("IS_APPROVED");
			Approved= rs.getString("APPROVED");
		}
		System.out.println("Is_Approved value is  : "+Is_approved);
		System.out.println("Approved value is  : "+Approved);

		if (Is_approved.equalsIgnoreCase("0") && Approved.equalsIgnoreCase("0"))
		{
			System.out.println("Is Approved and Approved value is Zero");
		}
		else
		{
			System.out.println("Condition not matched");
			Assert.fail();
		}
		return Value_Result;
	}

	/**
	 * Created By Suresh.V
	 * Description : Create a common method to retrieve Member ID
	 * @return
	 */
	public  static String getMemberId(WebDriver driver,String memberID)
	{
		String url=driver.getCurrentUrl();
		System.out.println("Current URL="+url);
		String[] URL = url.split("&");
		String URL0=URL[0];
		System.out.println(URL0);
		String[] id = URL0.split("=");
		String ID=id[1];
		System.out.println(ID);
		return ID;
	}

	/**
	 * Created By Suresh.V
	 * Description : Create a common method to retrieve Member Email from Account ID
	 * @return
	 */
	public  static String retrieveMailIDFromAccountID(WebDriver driver,String member_ID) throws ParseException, ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select email from account where id='"+member_ID+"'");
		System.out.println("query executed");
		String mailID="";
		if(rs.next())
		{
			mailID = rs.getString("EMAIL");
		}
		return mailID;
	}

	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   13/JUNE/16
	 * Modified Date:
	 * Description : Create a common method to IS_AVAILABLEHOURS is equal to 1.
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public  static String retrieveMemberOrCoachEmailForCustomSession(WebDriver driver,String testData) throws ParseException, ClassNotFoundException, SQLException
	{
		String[] testData1 = testData.split(",");
		String inputSessionType=testData1[0];
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
		ResultSet rs = stat.executeQuery("select c.start_dt_Time, C.SESSION_STATUS, acnt.email,prov.email prov_email from CALENDAR_EVENT C,PROVIDER PROV,ACCOUNT ACNT, SUMMARY_ACCOUNT_TODATE smry where prov.id= c.host_id and acnt.id=c.account_id and smry.account_id=acnt.id AND c.session_type_id ='"+inputSessionType+"'and c.session_status  in ('Scheduled') AND ACNT.EMAIL not like '%api%' AND PROV.EMAIL not like '%api%' and smry.onboarding_status='MEMBER SIDE ONBOARDED' and C.START_DT_TIME>sysdate ORDER BY C.START_DT_TIME ASC");
		System.out.println("query executed");
		String Coach_Email= "";

		if(rs.next())
		{
			Coach_Email = rs.getString("PROV_EMAIL");
			System.out.println("COACH_EMAIL :"+Coach_Email);
		}
		return Coach_Email;
	}

	/**
	 * Name :     Abinaya.P
	 * Created Date:   27/JUNE/16
	 * Modified Date:
	 * Description : Create a common method to retrieve member mail for Customization session
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public  static String retrieveMemberMailForCustomSession(WebDriver driver,String testData) throws ParseException, ClassNotFoundException, SQLException
	{
		String[] testData1 = testData.split(",");
		String inputSessionType=testData1[0];
		String inputSessionStatus=testData1[1];
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
		ResultSet rs = stat.executeQuery("select c.start_dt_Time, session_status, acnt.email member_email, prov.email coach_email from CALENDAR_EVENT C,PROVIDER PROV, ACCOUNT ACNT, SUMMARY_ACCOUNT_TODATE smry where  session_type_id = '"+inputSessionType+"' and session_status !='"+inputSessionStatus+"' AND ACNT.EMAIL not like '%api%' AND PROV.EMAIL not like '%api%' and prov.id= c.host_id and acnt.id=c.account_id and smry.onboarding_status='MEMBER SIDE ONBOARDED' and smry.account_id=acnt.id and C.START_DT_TIME>sysdate ORDER BY C.START_DT_TIME DESC");
		System.out.println("query executed");
		String Coach_Email="";
		String Member_Email= "";

		while(rs.next())
		{
			Member_Email = rs.getString("MEMBER_EMAIL");
			System.out.println("Coach Email  is : "+Coach_Email);
			System.out.println("Member Email :"+Member_Email);
		}

		return Member_Email;
	}

	/**
	 * Name :     Abinaya.P
	 * Created Date:   27/JUNE/16
	 * Modified Date:
	 * Description : Create a common method to retrieve coach or member mail for Customization session
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public  static String retrieveCoachOrMemberEmailForCustomSession(WebDriver driver,String testData) throws ParseException, ClassNotFoundException, SQLException
	{
		String[] testData1 = testData.split(",");
		String inputSessionType=testData1[0];
		String inputSessionStatus=testData1[1];
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
		ResultSet rs = stat.executeQuery("select c.start_dt_Time, session_status, acnt.email member_email, prov.email coach_email from CALENDAR_EVENT C,PROVIDER PROV, ACCOUNT ACNT, SUMMARY_ACCOUNT_TODATE smry where  session_type_id = '"+inputSessionType+"' and session_status !='"+inputSessionStatus+"' AND ACNT.EMAIL not like '%api%' AND PROV.EMAIL not like '%api%' and prov.id= c.host_id and acnt.id=c.account_id and smry.onboarding_status='MEMBER SIDE ONBOARDED' and smry.account_id=acnt.id and C.START_DT_TIME>sysdate ORDER BY C.START_DT_TIME DESC");
		System.out.println("query executed");
		String Coach_Email="";
		String Member_Email= "";

		if(rs.next())
		{
			Coach_Email= rs.getString("COACH_EMAIL");
			Member_Email = rs.getString("MEMBER_EMAIL");
			System.out.println("Coach Email  is : "+Coach_Email);
			System.out.println("Member Email :"+Member_Email);
		}

		return Member_Email;
	}


	/**
	 * Name :     Abinaya.P
	 * Created Date:   27/JUNE/16
	 * Modified Date:
	 * Description : Create a common method to retrieve member mail for Customization session
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public  static String retrieveCoachMailForGroupSession(WebDriver driver,String testData) throws ParseException, ClassNotFoundException, SQLException
	{
		String[] testData1 = testData.split(",");
		String inputSessionType=testData1[0];
		String inputSessionStatus=testData1[1];
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
		ResultSet rs = stat.executeQuery("SELECT ACNT.EMAIL Member_Email, PROV.EMAIL Coach_Email FROM SUMMARY_ACCOUNT_TODATE SMRY, PROVIDER PROV, ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID AND SMRY.ACCOUNT_ID=ACNT.ID AND SMRY.ONBOARDING_STATUS='MEMBER ONBOARDED' AND ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ACCOUNT_PROGRAM.START_DT_TIME>SYSDATE + INTERVAL '-52' DAY AND ID IN (SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_STATUS!='"+inputSessionStatus+"' AND SESSION_TYPE_ID='"+inputSessionType+"' AND MP_INTERVAL_NUMBER > (SELECT MAX(MP_INTERVAL_NUMBER) FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_TYPE_ID='02' AND SCHEDULED_DT<SYSDATE AND SCHEDULED_DT>SYSDATE + INTERVAL '-2' DAY)))AND ACNT.EMAIL not like '%api%' AND PROV.EMAIL not like '%api%' ORDER BY SMRY.SESSION_DATE_UTC DESC");
		System.out.println("query executed");
		String Coach_Email="";

		if(rs.next())
		{
			Coach_Email = rs.getString("COACH_EMAIL");
		}
		System.out.println("Coach Email  is : "+Coach_Email);
		return Coach_Email;
	}

	/**
	 * Name: Vinothkumar.M
	 * Created Date:  11/Nov/2016
	 * Description : Created common method for RA Coach Member search
	 * @param driver
	 * @param mail
	 */
	public static String realAppealCoachMemberSearch(WebDriver driver,String memEmail3)
	{
		wait(driver, "5");
		WebElement raCoachMembersTab= driver.findElement(By.xpath(OR.RA_COACH_MEMBERS_TAB));
		verifyElementIsPresent(driver, raCoachMembersTab);
		click(raCoachMembersTab);
		wait(driver, "10");
		WebElement raCoachMembersSortDropdown= driver.findElement(By.xpath(OR.RA_COACH_MEMBERS_SORT_DROPDOWN));
		selectByVisibletext(raCoachMembersSortDropdown,"Email" );
		wait(driver, "1");
		WebElement raCoachMembersSearchTextbox= driver.findElement(By.xpath(OR.RA_COACH_MEMBERS_SEARCH_TEXTBOX));
		sendKeys(raCoachMembersSearchTextbox, memEmail3);
		WebElement raCoachMembersearchButton= driver.findElement(By.xpath(OR.RA_COACH_MEMBERS_SEARCH_BUTTON));
		click(raCoachMembersearchButton);
		wait(driver, "5");
		return memEmail3;
	}

	/**
	 * Name: Vinothkumar.M
	 * Created Date:  25/Nov/2016
	 * Description : Created common method for RA Next Date coach unavailable timeslot
	 * @param driver
	 * @param mail
	 */
	public static void realAppealnextDateCoachUnavailabeTimeSlot(WebDriver driver)
	{
		try
		{
			WebElement coachCalendarEventStartDateAlertMsg= driver.findElement(By.xpath(OR.RA_COACHES_EVENT_START_CANNOT_BE_MORE_THAN_HR_POPUP_ALERT_MSG));
			verifyElementIsPresent(driver, coachCalendarEventStartDateAlertMsg);
			jsClickByXPath(driver,RA_COACHES_EVENT_START_CANNOT_BE_MORE_THAN_HR_POPUP_ALERT_MSG_OK_BUTTON);
			wait(driver, "3");
			com.zillion.qa.opsadmin.Classes.nextDateCoachUnavailabeTimeSlot(driver);
			wait(driver, "3");
			WebElement coachCalendarSaveButton= driver.findElement(By.xpath(OR.RA_COACHES_SCHEDULE_CALENDER_SCHEDULE_SAVE_BUTTON));
			click(coachCalendarSaveButton);
			wait(driver, "5");
			WebElement coachCalendarScheduledPopupText= driver.findElement(By.xpath(OR.RA_COACHES_SCHEDULE_CALENDER_SCHEDULE_POPUP_TEXT));
			verifyElementIsPresent(driver, coachCalendarScheduledPopupText);
			jsClickByXPath(driver,RA_COACHES_SCHEDULE_CALENDER_SCHEDULE_POPUP_OK_BUTTON);
			wait(driver, "3");
		}
		catch(Exception e)
		{
			WebElement coachCalendarScheduledPopupText= driver.findElement(By.xpath(OR.RA_COACHES_SCHEDULE_CALENDER_SCHEDULE_POPUP_TEXT));
			verifyElementIsPresent(driver, coachCalendarScheduledPopupText);
			jsClickByXPath(driver,RA_COACHES_SCHEDULE_CALENDER_SCHEDULE_POPUP_OK_BUTTON);
			wait(driver, "3");
		}

	}

	/**
	 * Name :Vinothkumar.M
	 * Created Date:17/Dec/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve members who have Onboarding status,Classroom, Member ID, Coach name
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws java.text.ParseException
	 *
	 */
	public static String getRAMemberForVerifyingConfigurableColumn(WebDriver driver, String Name) throws ClassNotFoundException, SQLException, java.text.ParseException
	{
		System.out.println("Name"+Name);
		String AB="A";

		if(Name.equals(AB))
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
			ResultSet rs = stat.executeQuery("select A.EMAIL, SATD.ASSIGNED_PROVIDER_NAME, SATD.CLASSROOM_NAME, SATD.ONBOARDING_STATUS, A.ID from ACCOUNT A, SUMMARY_ACCOUNT_TODATE SATD where A.ID= SATD.ACCOUNT_ID AND SATD.MAST_PROGRAM_ID= '02' AND SATD.ONBOARDING_STATUS='MEMBER ONBOARDED' AND SATD.CLASSROOM_NAME IS NOT NULL AND ASSIGNED_PROVIDER_NAME IS NOT NULL AND A.EMAIL NOT LIKE '%api%'ORDER BY EMAIL ASC");
			System.out.println("query executed");
			String member_mail1="";
			if(rs.next())
			{
				member_mail1 = rs.getString("EMAIL");
				System.out.print(member_mail1);
			}
			return member_mail1;
		}
		else
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
			ResultSet rs = stat.executeQuery("select A.EMAIL, SATD.ASSIGNED_PROVIDER_NAME, SATD.CLASSROOM_NAME, SATD.ONBOARDING_STATUS, A.ID from ACCOUNT A, SUMMARY_ACCOUNT_TODATE SATD where A.ID= SATD.ACCOUNT_ID AND SATD.MAST_PROGRAM_ID= '02' AND SATD.ONBOARDING_STATUS='MEMBER ONBOARDED' AND SATD.CLASSROOM_NAME IS NOT NULL AND ASSIGNED_PROVIDER_NAME IS NOT NULL AND A.EMAIL NOT LIKE '%api%'ORDER BY EMAIL ASC");
			System.out.println("query executed");
			String Assigned_Provider_Name="";
			if(rs.next())
			{
				Assigned_Provider_Name = rs.getString("ASSIGNED_PROVIDER_NAME");
				System.out.print(Assigned_Provider_Name);
			}
			return Assigned_Provider_Name;
		}
	}

	/**
	 * Name :Vinothkumar.M
	 * Created Date:17/Dec/2016
	 * Modified Date:
	 * Description :  Created a common method to match Classroom,Coach name,Onboarding status and Member ID for configurable columns
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws java.text.ParseException
	 *
	 */
	public static String getRAMemberConfigurableColumnClassroomandCoachname(WebDriver driver,String ClassroomName,String Coachname,String Onboardingstatus,String MemberID ) throws ClassNotFoundException, SQLException, java.text.ParseException
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
		ResultSet rs = stat.executeQuery("select A.EMAIL, SATD.ASSIGNED_PROVIDER_NAME, SATD.CLASSROOM_NAME, SATD.ONBOARDING_STATUS, A.ID from ACCOUNT A, SUMMARY_ACCOUNT_TODATE SATD where A.ID= SATD.ACCOUNT_ID AND SATD.MAST_PROGRAM_ID= '02' AND SATD.ONBOARDING_STATUS='MEMBER ONBOARDED' AND SATD.CLASSROOM_NAME IS NOT NULL AND ASSIGNED_PROVIDER_NAME IS NOT NULL AND A.EMAIL NOT LIKE '%api%'ORDER BY EMAIL DESC");
		System.out.println("query executed");
		String Coach1="";
		String Classroomname1="";
		String Onboarding_Status1="";
		String Member_ID1="";

		if(rs.next())
		{
			Coach1 = rs.getString("ASSIGNED_PROVIDER_NAME");
			System.out.println("Retrieved coach from DB"+Coach1);
			Classroomname1 = rs.getString("CLASSROOM_NAME");
			System.out.println("Retrieved Class room name from DB"+Classroomname1);
			Onboarding_Status1 = rs.getString("ONBOARDING_STATUS");
			System.out.println("Retrieved Onboarding status from DB"+Onboarding_Status1);
			Member_ID1 = rs.getString("ID");
			System.out.println("Retrieved Member ID from DB"+Member_ID1);
		}

		if(ClassroomName.equalsIgnoreCase(Classroomname1))
		{
			System.out.println("Classroom values are mached");
		}
		if(Coachname.equalsIgnoreCase(Coach1))
		{
			System.out.println("Coach values are mached");
		}
		if(Onboardingstatus.equalsIgnoreCase(Onboarding_Status1))
		{
			System.out.println("Onboarding Status values are mached");
		}
		if(MemberID.equalsIgnoreCase(Member_ID1))
		{
			System.out.println("Member ID values are mached");
		}
		return Coach1;
	}
	/**
	 * Name :Bharath.M
	 * Created Date:20/Dec/2016
	 * Modified Date:
	 * Description :   Created common method to Get RA coach email
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws java.text.ParseException
	 *
	 */
	public static String getRACoachVerifyingConfigurableColumn(WebDriver driver, String Coach_Name) throws ClassNotFoundException, SQLException, java.text.ParseException
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
		ResultSet rs = stat.executeQuery("select EMAIL from PROVIDER WHERE first_name||' '||last_name= '"+Coach_Name+"'");
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
	 * Name :     Vinothkumar.M
	 * Created Date:   25/Apr/2016
	 * Description :Create a common method for Real Appeal Coach Login
	 * Ticket ID :
	 * Required Inputs :Username and Password
	 * @return
	 *
	 */
	public static String realAppealUpcomingsessionCoachLogin(WebDriver driver,String coachEmail3)
	{
		Navigate.get(driver, Directory.RA_Provider_Url);
		Navigate.maximize(driver);
		wait(driver, "5");
		WebElement Palogin= driver.findElement(By.xpath(RA_PROGRAM_ADMIN_USERNAME));
		waitForElement(driver,RA_PROGRAM_ADMIN_USERNAME);
		sendKeys(Palogin, coachEmail3);
		WebElement Papassword= driver.findElement(By.xpath(RA_PROGRAM_ADMIN_PASSWORD));
		sendKeys(Papassword, Directory.RA_PA_Pasword1);
		WebElement loginbutton= driver.findElement(By.xpath(RA_PROGRAM_ADMIN_LOGIN));
		click(loginbutton);
		wait(driver, "4");
		Navigate.waitTime(driver, "20");
		WebElement Palogopresent= driver.findElement(By.xpath(RA_PROGRAM_ADMIN_REAL_APPEAL_LOGO));
		waitForElement(driver,RA_PROGRAM_ADMIN_REAL_APPEAL_LOGO);
		verifyElementIsPresent(driver, Palogopresent);
		wait(driver, "5");
		return coachEmail3;
	}

	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   20/JAN/17
	 * Modified Date:
	 * Description : Create a common method to retrieve member mail for Customization session
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public  static String retrieveCoachMailForCustomSession(WebDriver driver,String testData) throws ParseException, ClassNotFoundException, SQLException
	{
		String[] testData1 = testData.split(",");
		String inputSessionType=testData1[0];
		String inputSessionStatus=testData1[1];
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
		ResultSet rs = stat.executeQuery("select c.start_dt_Time, session_status, acnt.email member_email, prov.email coach_email from CALENDAR_EVENT C,PROVIDER PROV, ACCOUNT ACNT, SUMMARY_ACCOUNT_TODATE smry where  session_type_id = '"+inputSessionType+"' and session_status !='"+inputSessionStatus+"' AND ACNT.EMAIL not like '%api%' AND PROV.EMAIL not like '%api%' and prov.id= c.host_id and acnt.id=c.account_id and smry.onboarding_status='MEMBER SIDE ONBOARDED' and smry.account_id=acnt.id and C.START_DT_TIME>sysdate ORDER BY C.START_DT_TIME DESC");
		System.out.println("query executed");
		String Coach_Email="";
		String Member_Email= "";

		while(rs.next())
		{
			Member_Email = rs.getString("COACH_EMAIL");
			System.out.println("Coach Email  is : "+Coach_Email);
			System.out.println("Member Email :"+Member_Email);
		}
		return Member_Email;
	}

	/**
	 * Name: Vinothkumar.M
	 * Created Date:  11/Nov/2016
	 * Description : Created common method for RA Coach Member search
	 * @param driver
	 * @param mail
	 */
	public static void realAppealCoachClientSearch(WebDriver driver)
	{
		WebElement raCoachMembersSortDropdown= driver.findElement(By.xpath(OR.RA_COACH_MEMBERS_SORT_DROPDOWN));
		selectByVisibletext(raCoachMembersSortDropdown,"Client" );
		wait(driver, "1");
		WebElement raCoachMembersSearchTextbox= driver.findElement(By.xpath(OR.RA_COACH_MEMBERS_SEARCH_TEXTBOX));
		clearAndType(raCoachMembersSearchTextbox, Directory.RA_Enrollment_Organization_Name);
		WebElement raCoachMembersearchButton= driver.findElement(By.xpath(OR.RA_COACH_MEMBERS_SEARCH_BUTTON));
		click(raCoachMembersearchButton);
		wait(driver, "5");
	}

	/**
	 * Name: Bharath Kumar.M
	 * Created Date:  11/April/2017
	 * Description : Created common method for Change classroom
	 * @param driver
	 * @param mail
	 */
	public static void changeClassRoom(WebDriver driver,String DbProgramIntervalValue)
	{
		try
		{
			WebElement okButton= driver.findElement(By.xpath(OR.NO_MATCHING_CLASSES_FOUND_OK_BUTTON));
			click(okButton);
			wait(driver, "2");
			WebElement weekDropdown= driver.findElement(By.xpath(OR.CLASS_SELECTION_PREFERENCES_WEEK_DROPDOWN));
			selectByVisibletext(weekDropdown, DbProgramIntervalValue);
			wait(driver, "2");
			WebElement submitButton= driver.findElement(By.xpath(OR.CLASS_SELECTION_PREFERENCES_SUBMIT_BUTTON));
			click(submitButton);
			wait(driver, "4");
			int i=1;
			do
			{
				Integer result = Integer.valueOf(DbProgramIntervalValue);
				if(result<=52)
				{
					System.out.println("Entered in catch e2");
					wait(driver, "5");
					WebElement okButton1= driver.findElement(By.xpath(OR.NO_MATCHING_CLASSES_FOUND_OK_BUTTON));
					click(okButton1);
					wait(driver, "2");
					WebElement weekDropdown1= driver.findElement(By.xpath(OR.CLASS_SELECTION_PREFERENCES_WEEK_DROPDOWN));
					result= result+i;
					String str = Integer.toString(result);
					System.out.println("Value of str" +str);
					selectByVisibletext(weekDropdown1, str);
					wait(driver, "2");
					WebElement submitButton1= driver.findElement(By.xpath(OR.CLASS_SELECTION_PREFERENCES_SUBMIT_BUTTON));
					click(submitButton1);
					i++;
					wait(driver, "5");
				}
				else
				{
					do
					{
						System.out.println("Entered in catch e2");
						wait(driver, "5");
						WebElement okButton1= driver.findElement(By.xpath(OR.NO_MATCHING_CLASSES_FOUND_OK_BUTTON));
						click(okButton1);
						wait(driver, "2");
						WebElement weekDropdown1= driver.findElement(By.xpath(OR.CLASS_SELECTION_PREFERENCES_WEEK_DROPDOWN));
						result= result-i;
						String str = Integer.toString(result);
						System.out.println("Value of str" +str);
						selectByVisibletext(weekDropdown1, str);
						wait(driver, "2");
						WebElement submitButton1= driver.findElement(By.xpath(OR.CLASS_SELECTION_PREFERENCES_SUBMIT_BUTTON));
						click(submitButton1);
						i++;
						wait(driver, "5");
					}
					while(driver.findElement(By.xpath(NO_MATCHING_CLASSES_FOUND_OK_BUTTON)).isDisplayed());
				}
			}
			while(driver.findElement(By.xpath(NO_MATCHING_CLASSES_FOUND_OK_BUTTON)).isDisplayed())  ;
		}
		catch(Exception e1)
		{

		}
	}
	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:   21/MAR/17
	 * Description :Create a common method for verify Unread messages
	 * Ticket ID :
	 */
	public static void verifyUnreadmessages(WebDriver driver)
	{
		WebElement webElement = driver.findElement( By.xpath(OR.NEW_UNREAD_MESSAGE_LINK ) );
		String subject1=webElement.getText();
		System.out.println(subject1);
		WebElement subject = driver.findElement( By.xpath("//tr//th[text()='Subject']/parent::tr/parent::thead/following-sibling::tbody/tr[1]/td[3]/a[text()='"+subject1+"']" ) );
		verifyElementIsPresent(driver, webElement);
		click(webElement);
		wait(driver, "3");
		WebElement Dashboard = driver.findElement( By.xpath(OR.COACHES_DASHBOARD_TAB ) );
		verifyElementIsPresent(driver, Dashboard );
		actionClick( driver, Dashboard );
		verifyElementIsNotPresent(driver, subject);
		wait(driver, "3");
	}

	/**
	 * Name: Bharath Kumar
	 * Created Date:  23/Mar/2017
	 * Description : Created common method for Change classroom with intreval
	 * @param driver
	 * @param mail
	 */

	public static void changeClassRoomWithIntreval(WebDriver driver)
	{
		try
		{
			WebElement Assign= driver.findElement(By.xpath(OR.RA_MEMBER_CHANGE_CLASS_ASSIGN));
			WebElement okButton= driver.findElement(By.xpath(OR.NO_MATCHING_CLASSES_FOUND_OK_BUTTON));
			click(okButton);
			verifyElementIsNotPresent(driver, Assign);

		}
		catch(Exception e)
		{
			wait(driver, "2");
			WebElement weekDropdown= driver.findElement(By.xpath(OR.CLASS_SELECTION_PREFERENCES_WEEK_DROPDOWN));
			selectByVisibletext(weekDropdown, "2");
			wait(driver, "2");
			WebElement submitButton= driver.findElement(By.xpath(OR.CLASS_SELECTION_PREFERENCES_SUBMIT_BUTTON));
			click(submitButton);
			wait(driver, "3");
		}
	}

	/**
	 * Name: Vinoth Kumar
	 * Created Date:  27/Mar/2017
	 * Description : Created common method for Pdf in different browsers
	 * @param driver
	 * @param mail
	 */
	public static void verifyPdfInDifferentBrowsers(WebDriver driver) throws Exception{
		if(Platform.BROWSER_NAME.equalsIgnoreCase("firefox"))
		{
			WebElement FileFullScreen = driver.findElement(By.xpath(OPS_SESSION_GUIDE_FULLSCREEN));
			verifyElementIsPresent(driver,FileFullScreen);
			WebElement AutomaticZoom = driver.findElement(By.xpath(OPS_SESSION_GUIDE_AUTOMATICZOOM));
			verifyElementIsPresent(driver,AutomaticZoom);
			WebElement Pagination = driver.findElement(By.xpath(OPS_SESSION_GUIDE_PAGINATION));
			verifyElementIsPresent(driver,Pagination);
			WebElement Print = driver.findElement(By.xpath(OPS_SESSION_GUIDE_PRINT));
			verifyElementIsPresent(driver,Print);
			WebElement Download = driver.findElement(By.xpath(OPS_SESSION_GUIDE_DOWNLOAD));
			verifyElementIsPresent(driver,Download);
			WebElement ToogleSlideBar = driver.findElement(By.xpath(OPS_SESSION_GUIDE_TOGGLE_SLIDEBAR));
			verifyElementIsPresent(driver,ToogleSlideBar);
			jsClickByXPath(driver, OPS_SESSION_PAGENUMBER_INPUT);
			wait(driver,"2");
			com.zillion.qa.opsadmin.Programs.changePageNumber(driver, "2");
			Navigate.enter(driver);
			wait(driver,"1");
			jsClickByXPath(driver, OPS_SESSION_GUIDE_TOGGLE_SLIDEBAR);
			wait(driver,"1");
			jsClickByXPath(driver, OPS_SESSION_GUIDE_TOGGLE_SLIDEBAR);
			wait(driver,"1");
			jsClickByXPath(driver, OPS_SESSION_GUIDE_FULLSCREEN);
			wait(driver,"1");
			jsClickByXPath(driver, OPS_SESSION_GUIDE_DOWNLOAD);
			wait(driver,"2");
			Navigate.robotEnter(driver);
			WebElement SecondaryToolBar = driver.findElement(By.xpath(OPS_SESSION_GUIDE_SECONDARY_TOOLBAR));
			clickAndHold(driver, SecondaryToolBar);
			jsClickByXPath(driver, OPS_SESSION_GUIDE_FIRST_PAGE);
			wait(driver,"4");
			clickAndHold(driver, SecondaryToolBar);
			jsClickByXPath(driver, OPS_SESSION_GUIDE_LAST_PAGE);
			wait(driver,"4");
			clickAndHold(driver, SecondaryToolBar);
			jsClickByXPath(driver, OPS_SESSION_GUIDE_ROTATE_CLOCKWISE);
			wait(driver,"4");
			clickAndHold(driver, SecondaryToolBar);
			jsClickByXPath(driver, OPS_SESSION_GUIDE_ROTATE_ANTICLOCKWISE);
			wait(driver,"4");
			clickAndHold(driver, SecondaryToolBar);
			jsClickByXPath(driver, OPS_SESSION_GUIDE_DOCUMENT_PROPERTIES);
			wait(driver,"4");
			jsClickByXPath(driver, OPS_SESSION_GUIDE_DOCUMENT_PROPERTIES_POPUP_CLOSE);
			wait(driver,"5");
			Navigate.switchtotab(driver, 0);
		}
		else if(Platform.BROWSER_NAME.equalsIgnoreCase("chrome"))
		{

			Robot chromerotate=new Robot();
			wait(driver, "4");
			chromerotate.keyPress(KeyEvent.VK_SHIFT);
			chromerotate.keyPress(KeyEvent.VK_TAB);
			chromerotate.keyRelease(KeyEvent.VK_SHIFT);
			chromerotate.keyRelease(KeyEvent.VK_TAB);
			chromerotate.keyPress(KeyEvent.VK_SHIFT);
			chromerotate.keyPress(KeyEvent.VK_TAB);
			chromerotate.keyRelease(KeyEvent.VK_SHIFT);
			chromerotate.keyRelease(KeyEvent.VK_TAB);
			wait(driver, "1");
			chromerotate.keyPress(KeyEvent.VK_ENTER);
			chromerotate.keyRelease(KeyEvent.VK_ENTER);
			wait(driver, "1");
			chromerotate.keyPress(KeyEvent.VK_ENTER);
			chromerotate.keyRelease(KeyEvent.VK_ENTER);
			wait(driver, "1");
			chromerotate.keyPress(KeyEvent.VK_ENTER);
			chromerotate.keyRelease(KeyEvent.VK_ENTER);
			wait(driver, "1");
			chromerotate.keyPress(KeyEvent.VK_ENTER);
			chromerotate.keyRelease(KeyEvent.VK_ENTER);
			wait(driver, "1");
			chromerotate.keyPress(KeyEvent.VK_TAB);
			chromerotate.keyRelease(KeyEvent.VK_TAB);
			wait(driver, "1");
			chromerotate.keyPress(KeyEvent.VK_TAB);
			chromerotate.keyRelease(KeyEvent.VK_TAB);
			wait(driver, "1");
			chromerotate.keyPress(KeyEvent.VK_TAB);
			chromerotate.keyRelease(KeyEvent.VK_TAB);
			wait(driver, "1");
			chromerotate.keyPress(KeyEvent.VK_TAB);
			chromerotate.keyRelease(KeyEvent.VK_TAB);
			wait(driver, "1");
			chromerotate.keyPress(KeyEvent.VK_TAB);
			chromerotate.keyRelease(KeyEvent.VK_TAB);
			wait(driver, "1");
			chromerotate.keyPress(KeyEvent.VK_TAB);
			chromerotate.keyRelease(KeyEvent.VK_TAB);
			wait(driver, "1");
			chromerotate.keyPress(KeyEvent.VK_TAB);
			chromerotate.keyRelease(KeyEvent.VK_TAB);
			wait(driver, "1");
			chromerotate.keyPress(KeyEvent.VK_ENTER);
			chromerotate.keyRelease(KeyEvent.VK_ENTER);
			wait(driver, "1");
			chromerotate.keyPress(KeyEvent.VK_ENTER);
			chromerotate.keyRelease(KeyEvent.VK_ENTER);
			wait(driver, "1");
			chromerotate.keyPress(KeyEvent.VK_ENTER);
			chromerotate.keyRelease(KeyEvent.VK_ENTER);
			wait(driver, "1");
			chromerotate.keyPress(KeyEvent.VK_ENTER);
			chromerotate.keyRelease(KeyEvent.VK_ENTER);
			wait(driver, "1");
			chromerotate.keyPress(KeyEvent.VK_SHIFT);
			chromerotate.keyPress(KeyEvent.VK_TAB);
			chromerotate.keyRelease(KeyEvent.VK_SHIFT);
			chromerotate.keyRelease(KeyEvent.VK_TAB);
			String osName=Platform.OS.toUpperCase();
			if(osName.contains("WINDOWS"))
			{
				chromerotate.keyPress(KeyEvent.VK_4);
				chromerotate.keyRelease(KeyEvent.VK_4);
				chromerotate.keyPress(KeyEvent.VK_ENTER);
				chromerotate.keyRelease(KeyEvent.VK_ENTER);
			}
			else if(osName.contains("MAC"))
			{
				chromerotate.keyPress(KeyEvent.VK_SHIFT);
				chromerotate.keyPress(KeyEvent.VK_TAB);
				chromerotate.keyRelease(KeyEvent.VK_SHIFT);
				chromerotate.keyRelease(KeyEvent.VK_TAB);
				chromerotate.keyPress(KeyEvent.VK_SHIFT);
				chromerotate.keyPress(KeyEvent.VK_TAB);
				chromerotate.keyRelease(KeyEvent.VK_SHIFT);
				chromerotate.keyRelease(KeyEvent.VK_TAB);
				chromerotate.keyPress(KeyEvent.VK_ENTER);
				chromerotate.keyRelease(KeyEvent.VK_ENTER);
				chromerotate.keyPress(KeyEvent.VK_ENTER);
				chromerotate.keyRelease(KeyEvent.VK_ENTER);
				chromerotate.keyPress(KeyEvent.VK_ENTER);
				chromerotate.keyRelease(KeyEvent.VK_ENTER);
				chromerotate.keyPress(KeyEvent.VK_ENTER);
				chromerotate.keyRelease(KeyEvent.VK_ENTER);
				chromerotate.keyPress(KeyEvent.VK_SHIFT);
				chromerotate.keyPress(KeyEvent.VK_TAB);
				chromerotate.keyRelease(KeyEvent.VK_SHIFT);
				chromerotate.keyRelease(KeyEvent.VK_TAB);
				chromerotate.keyPress(KeyEvent.VK_4);
				chromerotate.keyRelease(KeyEvent.VK_4);
				chromerotate.keyPress(KeyEvent.VK_ENTER);
				chromerotate.keyRelease(KeyEvent.VK_ENTER);
			}
		}

	}

	/**
	 * Name: Vinoth Kumar
	 * Created Date:  27/Mar/2017
	 * Description : Created common method for getWindowGuidAssertDetail
	 * @param driver
	 * @param mail
	 */
	public static void getWindowGuidAssertDetail(WebDriver driver, String xpath) throws AWTException, FindFailed {
		Navigate.waitTime(driver, "5");
		Main_Window = driver.getWindowHandle();
		try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
		wait(driver, "3");
		jsClickByXPath(driver, xpath);
		wait(driver, "2");
		try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
		ArrayList<String> allWindows=new ArrayList<String>(driver.getWindowHandles());
		allWindows.remove(Main_Window);

		if (Platform.BROWSER_NAME.equalsIgnoreCase("firefox"))
		{
			driver.switchTo().window(allWindows.get(0));
			try
			{
				if(driver.findElement(By.xpath("//a[@id='overridelink']")).isDisplayed()){
					driver.get("javascript:document.getElementById('overridelink').click();");

				}
			}
			catch(Exception e)
			{

			}
		}
		else if(Platform.BROWSER_NAME.equalsIgnoreCase("chrome"))
		{
			wait(driver,"3");
			Screen s=new Screen();
			Pattern image = new Pattern(Directory.uploadFilePath+"Macpdfsgad.png");
			s.click(image);
			wait( driver, "3" );
			Navigate.switchtotab(driver, 1);
		}}

	/**
	 * Name: Vinoth Kumar
	 * Created Date:  27/Mar/2017
	 * Description : Created common method for getWindowfitnessnutritionguideAssertDetails
	 * @param driver
	 * @param mail
	 */
	public static void getWindowfitnessnutritionguideAssertDetails(WebDriver driver, String xpath) throws AWTException, FindFailed {
		Navigate.waitTime(driver, "5");
		Main_Window = driver.getWindowHandle();
		try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
		wait(driver, "3");
		jsClickByXPath(driver, xpath);
		wait(driver, "2");
		try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
		ArrayList<String> allWindows=new ArrayList<String>(driver.getWindowHandles());
		allWindows.remove(Main_Window);

		if (Platform.BROWSER_NAME.equalsIgnoreCase("firefox"))
		{
			driver.switchTo().window(allWindows.get(0));
			try
			{
				if(driver.findElement(By.xpath("//a[@id='overridelink']")).isDisplayed()){
					driver.get("javascript:document.getElementById('overridelink').click();");

				}
			}
			catch(Exception e)
			{

			}
		}
		else if(Platform.BROWSER_NAME.equalsIgnoreCase("chrome"))
		{
			wait(driver,"3");
			Screen s=new Screen();

			Pattern image = new Pattern(Directory.uploadFilePath+"Mackpdfclick.png");
			s.click(image);
			wait( driver, "3" );
			Navigate.switchtotab(driver, 1);
		}}
	public static void showRatingDisabledForAllPage(WebDriver driver){

		for(int i=1;i<360;i++)
		{
			jsClickByXPath(driver, OPS_SESSION_NEXT_NAVIGATION_PAGE);
			wait(driver, "4");
			WebElement show_rating_disabled_icon= driver.findElement(By.xpath(OR.RA_HFOPS_SESSION_SHOW_RATING_DISABLED_ICON));
			verifyElementIsPresent(driver, show_rating_disabled_icon);

		}
	}
	public static void previousSessionPageCustomizationRecords(WebDriver driver)
	{
		String member_count=driver.findElement(By.xpath(MEMBER_COUNT)).getText();
		int count=Integer.parseInt(member_count);
		System.out.println("TOTAL SESSION COUNT "+count);
		int value=count/10;
		int total_count=(int)Math.ceil(value);
		System.out.println("TOTAL COUNT PAGE "+total_count);
		for(int i=1;i<total_count;i++)
		{
			inVisibilityElement(driver, SCHEDULED_STATUS);
			jsClickByXPath(driver, OPS_SESSION_NEXT_NAVIGATION_PAGE);
			wait(driver, "1");
		}
	}
	public static void checkRescheduledStatus(WebDriver driver) {
		String ss;
		WebElement tr=driver.findElement(By.xpath("//span[@class='pagination-info']//span[3]"));
		String h;
		h=tr.getText();
		int j=Integer.parseInt(h);
		for(int i=1;i<=j;i++)
		{
			WebElement ele1=driver.findElement(By.xpath("//tbody[@id='sessionClasses_tableBody']//tr["+i+"]//td[contains(@id,'sessionClasses_tdStatus')]"));
			ss=ele1.getText();
			System.out.println("ss"+ss);
			if(ss.equalsIgnoreCase("Rescheduled"))
			{
				System.out.println("Rescheduled presented");
				Assert.fail();
			}
		}
	}

	/**
	 * Name: Vinoth Kumar
	 * Created Date:  27/Apr/2017
	 * Description : Created common method for MoveMemberToSelectedClassroom
	 * @param driver
	 * @param mail
	 */

	public static void moveMemberToSelectedClassroom(WebDriver driver){

		try{
			jsClickByXPath(driver, RA_PROGRAMADMIN_MEMBERS_TAB_ARE_YOU_SURE_WANT_TO_CHANGE_CLASS_YES_RADIO_BUTTON);
			jsClickByXPath(driver, OPS_ADMIN_PROVIDERS_LECTURE_SESSION_POPUP_OK_BUTTON);
			wait(driver,"2");
		}
		catch(Exception e){
			System.out.println("Move Member To Selected Classroom PopUp Not Displayed");
			jsClickByXPath(driver, MEMBER_PROFILE_CHANGE_CLASS_PREFERENCES_SWITCH_CLASS_SUCCESS_OK_BUTTON);

		}
	}

	/**
	 * Name: Bharath Kumar.M
	 * Created Date:  04/May/2017
	 * Modified Date: 15/May/2017
	 * Description : Created common method for clicking the Class Status UnApproved Assign
	 * @param driver
	 * @param mail
	 */
	public static void classStatusUnApprovedAssign(WebDriver driver,String DbProgramIntervalValue)
	{
		try
		{
			if(driver.findElements(By.xpath(RA_MEMBER_CHANGE_CLASS_UNAPPROVED_ASSIGN)).size()!=0)
			{
				System.out.println("UnApproved Assign Find");
			}
			else
			{
				WebElement weekDropdown= driver.findElement(By.xpath(OR.CLASS_SELECTION_PREFERENCES_WEEK_DROPDOWN));
				selectByVisibletext(weekDropdown, DbProgramIntervalValue);
				wait(driver, "2");
				WebElement submitButton= driver.findElement(By.xpath(OR.CLASS_SELECTION_PREFERENCES_SUBMIT_BUTTON));
				click(submitButton);
				Integer result = Integer.valueOf(DbProgramIntervalValue);
				int i=1;
				do
				{
					int	result1= result+i;					
					if(result1<=52)
					{
						System.out.println("Entered in catch e2");
						wait(driver, "5");
						if(driver.findElements(By.xpath(NO_MATCHING_CLASSES_FOUND_OK_BUTTON)).size()!=0)
						{
							WebElement okButton1= driver.findElement(By.xpath(OR.NO_MATCHING_CLASSES_FOUND_OK_BUTTON));
							click(okButton1);
						}
						wait(driver, "2");
						WebElement weekDropdown1= driver.findElement(By.xpath(OR.CLASS_SELECTION_PREFERENCES_WEEK_DROPDOWN));
						String str = Integer.toString(result1);
						System.out.println("Value of str" +str);
						selectByVisibletext(weekDropdown1, str);
						wait(driver, "2");
						WebElement submitButton1= driver.findElement(By.xpath(OR.CLASS_SELECTION_PREFERENCES_SUBMIT_BUTTON));
						click(submitButton1);
						i++;
						wait(driver, "5");
					}
					else
					{
						do
						{
							int result2= result-i;
							System.out.println("Entered in catch e2");
							wait(driver, "5");
							if(driver.findElements(By.xpath(NO_MATCHING_CLASSES_FOUND_OK_BUTTON)).size()!=0)
							{
								WebElement okButton1= driver.findElement(By.xpath(OR.NO_MATCHING_CLASSES_FOUND_OK_BUTTON));
								click(okButton1);
							}
							WebElement weekDropdown1= driver.findElement(By.xpath(OR.CLASS_SELECTION_PREFERENCES_WEEK_DROPDOWN));
							String str = Integer.toString(result2);
							System.out.println("Value of str" +str);
							selectByVisibletext(weekDropdown1, str);
							wait(driver, "2");
							WebElement submitButton1= driver.findElement(By.xpath(OR.CLASS_SELECTION_PREFERENCES_SUBMIT_BUTTON));
							click(submitButton1);
							i++;
							wait(driver, "5");
						}
						while(driver.findElements(By.xpath(RA_MEMBER_CHANGE_CLASS_UNAPPROVED_ASSIGN)).size()==0);
					}
				}
				while(driver.findElements(By.xpath(RA_MEMBER_CHANGE_CLASS_UNAPPROVED_ASSIGN)).size()==0)  ;
			}
		}
		catch(Exception e)
		{
			WebElement okButton= driver.findElement(By.xpath(OR.NO_MATCHING_CLASSES_FOUND_OK_BUTTON));
			click(okButton);
			wait(driver, "2");
			WebElement weekDropdown= driver.findElement(By.xpath(OR.CLASS_SELECTION_PREFERENCES_WEEK_DROPDOWN));
			selectByVisibletext(weekDropdown, DbProgramIntervalValue);
			wait(driver, "2");
			WebElement submitButton= driver.findElement(By.xpath(OR.CLASS_SELECTION_PREFERENCES_SUBMIT_BUTTON));
			click(submitButton);
			Integer result = Integer.valueOf(DbProgramIntervalValue);
			int i=1;
			do
			{
				int result1= result+i;
				System.out.println("Result: "+result1);
				if(result1<=52)
				{
					if(driver.findElements(By.xpath(NO_MATCHING_CLASSES_FOUND_OK_BUTTON)).size()!=0)
					{
						WebElement okButton1= driver.findElement(By.xpath(OR.NO_MATCHING_CLASSES_FOUND_OK_BUTTON));
						click(okButton1);
					}
					System.out.println("Entered in catch e2");
					wait(driver, "2");
					WebElement weekDropdown1= driver.findElement(By.xpath(OR.CLASS_SELECTION_PREFERENCES_WEEK_DROPDOWN));
					String str = Integer.toString(result1);
					System.out.println("Value of str" +str);
					selectByVisibletext(weekDropdown1, str);
					wait(driver, "2");
					WebElement submitButton1= driver.findElement(By.xpath(OR.CLASS_SELECTION_PREFERENCES_SUBMIT_BUTTON));
					click(submitButton1);
					i++;
					wait(driver, "5");
				}
				else
				{
					do
					{
						int result2= result-i;
						System.out.println("Entered in catch e2");
						wait(driver, "5");
						if(driver.findElement(By.xpath(NO_MATCHING_CLASSES_FOUND_OK_BUTTON)).isDisplayed())
						{
							WebElement okButton1= driver.findElement(By.xpath(OR.NO_MATCHING_CLASSES_FOUND_OK_BUTTON));
							click(okButton1);
						}
						WebElement weekDropdown1= driver.findElement(By.xpath(OR.CLASS_SELECTION_PREFERENCES_WEEK_DROPDOWN));
						String str = Integer.toString(result2);
						System.out.println("Value of str" +str);
						selectByVisibletext(weekDropdown1, str);
						wait(driver, "2");
						WebElement submitButton1= driver.findElement(By.xpath(OR.CLASS_SELECTION_PREFERENCES_SUBMIT_BUTTON));
						click(submitButton1);
						i++;
						System.out.println("i"+i);
						wait(driver, "5");
					}
					while(driver.findElements(By.xpath(RA_MEMBER_CHANGE_CLASS_UNAPPROVED_ASSIGN)).size()==0);
					{
						System.out.println("UnApproved Assign Finded1");
					}
				}
			}
			while(driver.findElements(By.xpath(RA_MEMBER_CHANGE_CLASS_UNAPPROVED_ASSIGN)).size()==0);
			{
				System.out.println("UnApproved Assign Finded");
			}
		}
	}
	public static void rettivesample4(WebDriver driver) {
		// TODO Auto-generated method stub
		try
		{
			WebElement verify=driver.findElement(By.xpath("//tbody//tr[*][contains(@id,'sessionClasses_tableBodyRow')]//td[contains(text(),'Group')]//following::td[contains(text(),'Completed')]//following::td[6]//a[contains(@title,'Group Members Information')]"));
			verify.isDisplayed();
		}
		catch(Exception io)
		{
			driver.findElement(By.xpath("//button[@id='nxt_Button_MemberRequest']")).click();
			WebElement verify=driver.findElement(By.xpath("//tbody//tr[*][contains(@id,'sessionClasses_tableBodyRow')]//td[contains(text(),'Group')]//following::td[contains(text(),'Completed')]//following::td[6]//a[contains(@title,'Group Members Information')]"));
			verify.isDisplayed();
			//	Thread.sleep(1000);
			wait(driver,"1");
		}
	}
	/**
	 * Name: Suresh V
	 * @param driver
	 * @param testData
	 * @return
	 * @throws ParseException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String scheduleMissed1on1(WebDriver driver) throws ClassNotFoundException, SQLException
	{
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
		ResultSet rs = stat.executeQuery("select A.EMAIL, CE.SESSION_STATUS from CALENDAR_EVENT CE, ACCOUNT A WHERE A.ID= CE.ACCOUNT_ID AND CE.SESSION_STATUS ='Missed' and CE.SESSION_TYPE_ID = '02' and START_DT_TIME>SYSDATE + INTERVAL '-10' DAY ORDER BY CE.START_DT desc;");
		System.out.println("query executed");
		String member_mail="";
		if(rs.next())
		{
			member_mail = rs.getString("EMAIL");
			System.out.println("Customization session-member "+member_mail+" is retrieved from the Table");
		}
		return member_mail;
	}
	/**
	 * Name: Suresh V
	 * @param driver
	 * @param testData
	 * @return
	 * @throws ParseException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static String retrieveUnScheduleMember(WebDriver driver) throws ParseException, ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("SELECT ACNT.EMAIL Member_Email, PROV.EMAIL Coach_Email FROM SUMMARY_ACCOUNT_TODATE SMRY, PROVIDER PROV, ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID AND SMRY.ACCOUNT_ID=ACNT.ID AND SMRY.ONBOARDING_STATUS='MEMBER ONBOARDED' AND ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ACCOUNT_PROGRAM.START_DT_TIME>SYSDATE + INTERVAL '-50' DAY AND ID IN (SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_STATUS in ('Unscheduled') AND PROGRAM_INTERVAL_START_DT<SYSDATE AND PROGRAM_INTERVAL_END_DT>SYSDATE AND SESSION_TYPE_ID='02')) AND ACNT.EMAIL not like '%api%' AND ACNT.EMAIL not like '%QAmember%-default@yopmail.com%' AND PROV.EMAIL not like '%api%' AND ACNT.STATUS='Active' ORDER BY SMRY.SESSION_DATE_UTC ASC");
		System.out.println("query executed");
		String retrievedscheduleAvailableMemberEmail="";
		//String retrievedscheduleAvailableMemberAssignedCoachEmail="";
		//String result="";
		if(rs.next())
		{
			retrievedscheduleAvailableMemberEmail = rs.getString("MEMBER_EMAIL");
			System.out.println("Customization session-member "+retrievedscheduleAvailableMemberEmail+" is retrieved from the Table");
		}
		return retrievedscheduleAvailableMemberEmail;
	}
	/**
	 * Suresh V
	 * @param driver
	 */
	public static void findCompletedCustomization(WebDriver driver) {
		try
		{
			WebElement verify=driver.findElement(By.xpath("//span[@id='sessionClasses_Title_SessionType']/following::td[text()='Customization']//following::span//following::a[@title='Show Rating']"));
			verify.isDisplayed();
		}
		catch(Exception io)
		{
			driver.findElement(By.xpath("//button[@id='nxt_Button_MemberRequest']")).click();
			WebElement verify=driver.findElement(By.xpath("//span[@id='sessionClasses_Title_SessionType']/following::td[text()='Customization']//following::span//following::a[@title='Show Rating']"));
			verify.isDisplayed();
		}
	}
	/**
	 * Suresh V
	 * @param driver
	 */
	public static void find1on1CompletedShowRating(WebDriver driver) {
		try
		{
			WebElement verify=driver.findElement(By.xpath("//tbody//tr[*][contains(@id,'sessionClasses_tableBodyRow')]//td[contains(text(),'1on1')]//following::td[contains(text(),'Completed')]//following::td[6]//a[contains(@title,'Show Rating')]"));
			verify.isDisplayed();
		}
		catch(Exception io)
		{
			driver.findElement(By.xpath("//button[@id='nxt_Button_MemberRequest']")).click();
			WebElement verify=driver.findElement(By.xpath("//tbody//tr[*][contains(@id,'sessionClasses_tableBodyRow')]//td[contains(text(),'1on1')]//following::td[contains(text(),'Completed')]//following::td[6]//a[contains(@title,'Show Rating')]"));
			verify.isDisplayed();
		}
	}
	/**
	 * Suresh V
	 * @param driver
	 */
	public static void findGroupSessionCompleted(WebDriver driver) {
		// TODO Auto-generated method stub
		try
		{
			WebElement verify=driver.findElement(By.xpath("//tbody//tr[*][contains(@id,'sessionClasses_tableBodyRow')]//td[contains(text(),'Group')]//following::td[contains(text(),'Completed')]//following::td[6]//a[contains(@title,'Group Members Information')]"));
			verify.isDisplayed();
		}
		catch(Exception io)
		{
			driver.findElement(By.xpath("//button[@id='nxt_Button_MemberRequest']")).click();
			WebElement verify=driver.findElement(By.xpath("//tbody//tr[*][contains(@id,'sessionClasses_tableBodyRow')]//td[contains(text(),'Group')]//following::td[contains(text(),'Completed')]//following::td[6]//a[contains(@title,'Group Members Information')]"));
			verify.isDisplayed();
			wait(driver,"1");
		}
	}
	/**
	 * Suresh V
	 * @param driver
	 */
	public static void missed_Disabled_1On1(WebDriver driver)
	{
		try
		{
			WebElement MISSED_DISABLED_1_ON_1=driver.findElement(By.xpath("//tbody//tr[*][contains(@id,'sessionClasses_tableBodyRow')]//td[contains(text(),'1on1')]//following::td[contains(text(),'Missed')]//following::td[6]//a[contains(@title,'Show Rating') and contains(@disabled,'disabled')]"));
			MISSED_DISABLED_1_ON_1.isDisplayed();
		}
		catch(Exception io)
		{
			driver.findElement(By.xpath("//button[@id='nxt_Button_MemberRequest']")).click();
			WebElement MISSED_DISABLED_1_ON_1=driver.findElement(By.xpath("//tbody//tr[*][contains(@id,'sessionClasses_tableBodyRow')]//td[contains(text(),'1on1')]//following::td[contains(text(),'Missed')]//following::td[6]//a[contains(@title,'Show Rating') and contains(@disabled,'disabled')]"));
			MISSED_DISABLED_1_ON_1.isDisplayed();

		}}
	public static void missedGroupSessionRatingsVerify(WebDriver driver) {
		try
		{
			WebElement GROUP_MISSED_DISABLED=driver.findElement(By.xpath("//tbody//tr[*][contains(@id,'sessionClasses_tableBodyRow')]//td[contains(text(),'Group')]//following::td[contains(text(),'Missed')]//following::td[6]//a[contains(@title,'Show Rating') and contains(@disabled,'disabled')]"));
			GROUP_MISSED_DISABLED.isDisplayed();
		}
		catch(Exception io)
		{
			driver.findElement(By.xpath("//button[@id='nxt_Button_MemberRequest']")).click();
			WebElement GROUP_MISSED_DISABLED=driver.findElement(By.xpath("//tbody//tr[*][contains(@id,'sessionClasses_tableBodyRow')]//td[contains(text(),'Group')]//following::td[contains(text(),'Missed')]//following::td[6]//a[contains(@title,'Show Rating') and contains(@disabled,'disabled')]"));
			GROUP_MISSED_DISABLED.isDisplayed();
			wait(driver,"1");
		}
	}
	public static void canceled1on1SessionRatingsVerify(WebDriver driver) {
		try
		{
			WebElement CANCELED1_ON_1=driver.findElement(By.xpath("//tbody//tr[*][contains(@id,'sessionClasses_tableBodyRow')]//td[contains(text(),'1on1')]//following::td[contains(text(),'Canceled')]//following::td[6]//a[contains(@title,'Show Rating') and contains(@disabled,'disabled')]"));
			CANCELED1_ON_1.isDisplayed();
		}
		catch(Exception io)
		{
			driver.findElement(By.xpath("//button[@id='nxt_Button_MemberRequest']")).click();
			WebElement CANCELED1_ON_1=driver.findElement(By.xpath("//tbody//tr[*][contains(@id,'sessionClasses_tableBodyRow')]//td[contains(text(),'1on1')]//following::td[contains(text(),'Canceled')]//following::td[6]//a[contains(@title,'Show Rating') and contains(@disabled,'disabled')]"));
			CANCELED1_ON_1.isDisplayed();
			// Thread.sleep(1000);
			wait(driver,"1");
		}
	}
	public static void missedCustomizationSessionRatingDisabledVerify(WebDriver driver) {
		try
		{
			WebElement CUSTOMIZATION_MISSED_DISABLED=driver.findElement(By.xpath("//tbody//tr[*][contains(@id,'sessionClasses_tableBodyRow')]//td[contains(text(),'Customization')]//following::td[contains(text(),'Missed')]//following::td[6]//a[contains(@title,'Show Rating') and contains(@disabled,'disabled')]"));
			CUSTOMIZATION_MISSED_DISABLED.isDisplayed();
		}
		catch(Exception io)
		{  driver.findElement(By.xpath("//button[@id='nxt_Button_MemberRequest']")).click();
		WebElement CUSTOMIZATION_MISSED_DISABLED=driver.findElement(By.xpath("//tbody//tr[*][contains(@id,'sessionClasses_tableBodyRow')]//td[contains(text(),'Customization')]//following::td[contains(text(),'Missed')]//following::td[6]//a[contains(@title,'Show Rating') and contains(@disabled,'disabled')]"));

		CUSTOMIZATION_MISSED_DISABLED.isDisplayed();
		// Thread.sleep(1000);
		wait(driver,"1");
		}
	}
	public static void canceledCustomizationSessionRatingDisabledVerify(WebDriver driver) {
		try
		{
			WebElement CUSTOMIZATION_CANCELLED=driver.findElement(By.xpath("//tbody//tr[*][contains(@id,'sessionClasses_tableBodyRow')]//td[contains(text(),'Customization')]//following::td[contains(text(),'Canceled')]//following::td[6]//a[contains(@title,'Show Rating') and contains(@disabled,'disabled')]"));
			CUSTOMIZATION_CANCELLED.isDisplayed();
		}
		catch(Exception io)
		{
			driver.findElement(By.xpath("//button[@id='nxt_Button_MemberRequest']")).click();
			WebElement CUSTOMIZATION_CANCELLED=driver.findElement(By.xpath("//tbody//tr[*][contains(@id,'sessionClasses_tableBodyRow')]//td[contains(text(),'Customization')]//following::td[contains(text(),'Canceled')]//following::td[6]//a[contains(@title,'Show Rating') and contains(@disabled,'disabled')]"));
			CUSTOMIZATION_CANCELLED.isDisplayed();
			// Thread.sleep(1000);
			wait(driver,"1");
		}
	}
	public static void canceledGroupSessionHaveDisabledRatingsVerify(WebDriver driver) {
		try
		{
			WebElement GROUP_CANCELED=driver.findElement(By.xpath("//tbody//tr[*][contains(@id,'sessionClasses_tableBodyRow')]//td[contains(text(),'Group')]//following::td[contains(text(),'Canceled')]//following::td[6]//a[contains(@title,'Show Rating') and contains(@disabled,'disabled')]"));
			GROUP_CANCELED.isDisplayed();
		}
		catch(Exception io)
		{
			driver.findElement(By.xpath("//button[@id='nxt_Button_MemberRequest']")).click();
			WebElement GROUP_CANCELED=driver.findElement(By.xpath("//tbody//tr[*][contains(@id,'sessionClasses_tableBodyRow')]//td[contains(text(),'Group')]//following::td[contains(text(),'Canceled')]//following::td[6]//a[contains(@title,'Show Rating') and contains(@disabled,'disabled')]"));
			GROUP_CANCELED.isDisplayed();
			wait(driver,"1");
		}
	}
	public static void missed1on1SessionRatingsDisabledVerify(WebDriver driver) {
		try
		{
			WebElement MISSED_DISABLED_1_ON_1=driver.findElement(By.xpath("//tbody//tr[*][contains(@id,'sessionClasses_tableBodyRow')]//td[contains(text(),'1on1')]//following::td[contains(text(),'Missed')]//following::td[6]//a[contains(@title,'Show Rating') and contains(@disabled,'disabled')]"));
			MISSED_DISABLED_1_ON_1.isDisplayed();
		}
		catch(Exception io)
		{
			driver.findElement(By.xpath("//button[@id='nxt_Button_MemberRequest']")).click();
			WebElement MISSED_DISABLED_1_ON_1=driver.findElement(By.xpath("//tbody//tr[*][contains(@id,'sessionClasses_tableBodyRow')]//td[contains(text(),'1on1')]//following::td[contains(text(),'Missed')]//following::td[6]//a[contains(@title,'Show Rating') and contains(@disabled,'disabled')]"));
			MISSED_DISABLED_1_ON_1.isDisplayed();
		}
	}

	/**
	 * Name: Bharath Kumar.M
	 * Created Date:  03/May/2017
	 * Modified Date: 15/May/2017
	 * Description : Created common method for clicking the Class Status InSufficient Assign
	 * @param driver
	 * @param mail
	 */
	public static void classStatusInSufficientAssign(WebDriver driver,String DbProgramIntervalValue)
	{
		try
		{
			if(driver.findElements(By.xpath(RA_MEMBER_CHANGE_CLASS_INSUFFICIENT_ASSIGN)).size()!=0)
			{
				System.out.println("UnApproved Assign Find");
			}
			else
			{
				WebElement weekDropdown= driver.findElement(By.xpath(OR.CLASS_SELECTION_PREFERENCES_WEEK_DROPDOWN));
				selectByVisibletext(weekDropdown, DbProgramIntervalValue);
				wait(driver, "2");
				WebElement submitButton= driver.findElement(By.xpath(OR.CLASS_SELECTION_PREFERENCES_SUBMIT_BUTTON));
				click(submitButton);
				Integer result = Integer.valueOf(DbProgramIntervalValue);
				int i=1;
				do
				{
					int	result1= result+i;					
					if(result1<=52)
					{
						System.out.println("Entered in catch e2");
						wait(driver, "5");
						if(driver.findElements(By.xpath(NO_MATCHING_CLASSES_FOUND_OK_BUTTON)).size()!=0)
						{
							WebElement okButton1= driver.findElement(By.xpath(OR.NO_MATCHING_CLASSES_FOUND_OK_BUTTON));
							click(okButton1);
						}
						wait(driver, "2");
						WebElement weekDropdown1= driver.findElement(By.xpath(OR.CLASS_SELECTION_PREFERENCES_WEEK_DROPDOWN));
						String str = Integer.toString(result1);
						System.out.println("Value of str" +str);
						selectByVisibletext(weekDropdown1, str);
						wait(driver, "2");
						WebElement submitButton1= driver.findElement(By.xpath(OR.CLASS_SELECTION_PREFERENCES_SUBMIT_BUTTON));
						click(submitButton1);
						i++;
						wait(driver, "5");
					}
					else
					{
						do
						{
							int result2= result-i;
							System.out.println("Entered in catch e2");
							wait(driver, "5");
							if(driver.findElements(By.xpath(NO_MATCHING_CLASSES_FOUND_OK_BUTTON)).size()!=0)
							{
								WebElement okButton1= driver.findElement(By.xpath(OR.NO_MATCHING_CLASSES_FOUND_OK_BUTTON));
								click(okButton1);
							}
							WebElement weekDropdown1= driver.findElement(By.xpath(OR.CLASS_SELECTION_PREFERENCES_WEEK_DROPDOWN));
							String str = Integer.toString(result2);
							System.out.println("Value of str" +str);
							selectByVisibletext(weekDropdown1, str);
							wait(driver, "2");
							WebElement submitButton1= driver.findElement(By.xpath(OR.CLASS_SELECTION_PREFERENCES_SUBMIT_BUTTON));
							click(submitButton1);
							i++;
							wait(driver, "5");
						}
						while(driver.findElements(By.xpath(RA_MEMBER_CHANGE_CLASS_INSUFFICIENT_ASSIGN)).size()==0);
					}
				}
				while(driver.findElements(By.xpath(RA_MEMBER_CHANGE_CLASS_INSUFFICIENT_ASSIGN)).size()==0)  ;
			}
		}
		catch(Exception e)
		{
			WebElement okButton= driver.findElement(By.xpath(OR.NO_MATCHING_CLASSES_FOUND_OK_BUTTON));
			click(okButton);
			wait(driver, "2");
			WebElement weekDropdown= driver.findElement(By.xpath(OR.CLASS_SELECTION_PREFERENCES_WEEK_DROPDOWN));
			selectByVisibletext(weekDropdown, DbProgramIntervalValue);
			wait(driver, "2");
			WebElement submitButton= driver.findElement(By.xpath(OR.CLASS_SELECTION_PREFERENCES_SUBMIT_BUTTON));
			click(submitButton);
			Integer result = Integer.valueOf(DbProgramIntervalValue);
			int i=1;
			do
			{
				int result1= result+i;
				System.out.println("Result: "+result1);
				if(result1<=52)
				{
					if(driver.findElements(By.xpath(NO_MATCHING_CLASSES_FOUND_OK_BUTTON)).size()!=0)
					{
						WebElement okButton1= driver.findElement(By.xpath(OR.NO_MATCHING_CLASSES_FOUND_OK_BUTTON));
						click(okButton1);
					}
					System.out.println("Entered in catch e2");
					wait(driver, "2");
					WebElement weekDropdown1= driver.findElement(By.xpath(OR.CLASS_SELECTION_PREFERENCES_WEEK_DROPDOWN));
					String str = Integer.toString(result1);
					System.out.println("Value of str" +str);
					selectByVisibletext(weekDropdown1, str);
					wait(driver, "2");
					WebElement submitButton1= driver.findElement(By.xpath(OR.CLASS_SELECTION_PREFERENCES_SUBMIT_BUTTON));
					click(submitButton1);
					i++;
					wait(driver, "5");
				}
				else
				{
					do
					{
						int result2= result-i;
						System.out.println("Entered in catch e2");
						wait(driver, "5");
						if(driver.findElement(By.xpath(NO_MATCHING_CLASSES_FOUND_OK_BUTTON)).isDisplayed())
						{
							WebElement okButton1= driver.findElement(By.xpath(OR.NO_MATCHING_CLASSES_FOUND_OK_BUTTON));
							click(okButton1);
						}
						WebElement weekDropdown1= driver.findElement(By.xpath(OR.CLASS_SELECTION_PREFERENCES_WEEK_DROPDOWN));
						String str = Integer.toString(result2);
						System.out.println("Value of str" +str);
						selectByVisibletext(weekDropdown1, str);
						wait(driver, "2");
						WebElement submitButton1= driver.findElement(By.xpath(OR.CLASS_SELECTION_PREFERENCES_SUBMIT_BUTTON));
						click(submitButton1);
						i++;
						System.out.println("i"+i);
						wait(driver, "5");
					}
					while(driver.findElements(By.xpath(RA_MEMBER_CHANGE_CLASS_INSUFFICIENT_ASSIGN)).size()==0);
					{
						System.out.println("UnApproved Assign Not Finded1");
					}
				}
			}
			while(driver.findElements(By.xpath(RA_MEMBER_CHANGE_CLASS_INSUFFICIENT_ASSIGN)).size()==0);
			{
				System.out.println("UnApproved Assign Not Finded");
			}
		}
	}
	/**
	 * Name: Bharath Kumar.M
	 * Created Date:  04/May/2017
	 * Description : Created common method to retrieve member in 52 interval whose status is Idle
	 * @param driver
	 * @param mail
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String idleMemberIn52Interval(WebDriver driver, String status, String os) throws ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select ACNT.EMAIL as MEMEMAIL, SAT.PROGRAM_INTERVAL_NUMBER, SAT. ONBOARDING_STATUS from SUMMARY_ACCOUNT_TODATE SAT, ACCOUNT ACNT WHERE ACNT.ID= SAT.ACCOUNT_ID AND SAT.PROGRAM_INTERVAL_NUMBER= '52'AND SAT.ONBOARDING_STATUS= '"+os+"' AND ACNT.EMAIL NOT LIKE '%api%' and ACNT.STATUS= '"+status+"'");
		System.out.println("query executed");
		String IdleMember="";
		if(rs.next())
		{
			IdleMember = rs.getString("MEMEMAIL");
			System.out.println("Idle Member Email "+IdleMember+" is retrieved from the Table whose in 52 interval");
		}
		return IdleMember;
	}
	/**
	 *Created By:Suresh V
	 */
	public static void cancelSessionInMemberSide(WebDriver driver)
	{
		try
		{
			jsClickByXPath(driver, MEMBER_SESSION_CHANGE_BUTTON);
			wait(driver, "6");
			waitForElement(driver, MEMBER_CANCEL_SESSION_BUTTON);
			jsClickByXPath(driver, MEMBER_CANCEL_SESSION_BUTTON);
			wait(driver, "3");
			waitForElement(driver, MEMBER_SESSION_POPUP_OK);
			jsClickByXPath(driver, MEMBER_SESSION_POPUP_OK);
			wait(driver, "6");
			waitForElement( driver, OR.REAL_APPEAL_MEMBER_DASHBOARD_TAB );
		}
		catch(Exception e)
		{
			try
			{
				waitForElement(driver, RA_MEMBERLIVESESSION_SCHEDULE_BUTTON);
				jsClickByXPath(driver, RA_MEMBERLIVESESSION_SCHEDULE_BUTTON);
				wait(driver, "6");
			}
			catch(Exception e1)
			{
				waitForElement(driver, RA_MEMBER_DASHBOARD_RESCHDULE_BUTTON);
				jsClickByXPath(driver, RA_MEMBER_DASHBOARD_RESCHDULE_BUTTON);
				wait(driver, "5");
			}
			waitForElement(driver, RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_AVAILABLE_BUTTON);
			WebElement timeselectionSelectTime = driver.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_AVAILABLE_BUTTON_AVAILABLE_TIME));
			verifyElementIsPresent(driver, timeselectionSelectTime);
			jsClickByXPath(driver, RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_AVAILABLE_BUTTON_AVAILABLE_TIME);
			wait( driver, "2" );
			String scheduledTime=driver.findElement(By.xpath("//h4[@id='selected1on1Class']")).getText();
			WebElement confirmButton = driver.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_CONFIRM_BUTTON));
			verifyElementIsPresent(driver, confirmButton);
			click(confirmButton);
			wait( driver, "7" );
			jsClickByXPath(driver, REAL_APPEAL_MEMBER_DASHBOARD_TAB);
			wait(driver, "6");
			jsClickByXPath(driver, MEMBER_SESSION_CHANGE_BUTTON);
			wait(driver, "6");
			waitForElement(driver, MEMBER_CANCEL_SESSION_BUTTON);
			jsClickByXPath(driver, MEMBER_CANCEL_SESSION_BUTTON);
			wait(driver, "3");
			waitForElement(driver, MEMBER_SESSION_POPUP_OK);
			jsClickByXPath(driver, MEMBER_SESSION_POPUP_OK);
			wait(driver, "6");
			waitForElement( driver, OR.REAL_APPEAL_MEMBER_DASHBOARD_TAB );

		}
	}
	/**
	 *Created By:Suresh V
	 */
	public static void rescheduleSessionInMemberSide(WebDriver driver)
	{
		waitForElement(driver, RA_MEMBER_DASHBOARD_RESCHDULE_BUTTON);
		jsClickByXPath(driver, RA_MEMBER_DASHBOARD_RESCHDULE_BUTTON);
		wait(driver, "5");
		waitForElement(driver, RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_AVAILABLE_BUTTON);
		WebElement timeselectionSelectTime = driver.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_AVAILABLE_BUTTON_AVAILABLE_TIME));
		verifyElementIsPresent(driver, timeselectionSelectTime);
		jsClickByXPath(driver, RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_AVAILABLE_BUTTON_AVAILABLE_TIME);
		wait( driver, "2" );
		String scheduledTime=driver.findElement(By.xpath("//h4[@id='selected1on1Class']")).getText();
		WebElement confirmButton = driver.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_CONFIRM_BUTTON));
		verifyElementIsPresent(driver, confirmButton);
		click(confirmButton);
		wait( driver, "7" );
	}
	/**
	 * Suresh V
	 * @param driver
	 * @return
	 * @throws ParseException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  static String retrieveGroupSessionScheduledMember(WebDriver driver) throws ParseException, ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("SELECT ACNT.EMAIL Member_Email, PROV.EMAIL Coach_Email FROM SUMMARY_ACCOUNT_TODATE SMRY, PROVIDER PROV, ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID AND SMRY.ACCOUNT_ID=ACNT.ID AND SMRY.ONBOARDING_STATUS='MEMBER ONBOARDED' AND ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ACCOUNT_PROGRAM.START_DT_TIME>SYSDATE + INTERVAL '-50' DAY AND ID IN (SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_STATUS!=('Completed') AND PROGRAM_INTERVAL_START_DT<SYSDATE AND PROGRAM_INTERVAL_END_DT>SYSDATE AND SESSION_TYPE_ID='01'))AND ACNT.EMAIL not like '%api%' AND PROV.EMAIL not like '%api%' AND ACNT.EMAIL not like '%QAmember%-default@yopmail.com%' and ACNT.STATUS IN ('Active') ORDER BY SMRY.SESSION_DATE_UTC DESC");
		System.out.println("query executed");
		String retrievedscheduleAvailableMemberEmail="";
		String retrievedscheduleAvailableMemberAssignedCoachEmail="";
		String result="";
		if(rs.next())
		{
			retrievedscheduleAvailableMemberEmail= rs.getString("MEMBER_EMAIL");
			//retrievedscheduleAvailableMemberAssignedCoachEmail = rs.getString("COACH_EMAIL");
			System.out.println("Available Member Email is "+retrievedscheduleAvailableMemberEmail);
			//System.out.println("Available Member Email Assigned coach Email is "+retrievedscheduleAvailableMemberAssignedCoachEmail);
			//result=retrievedscheduleAvailableMemberEmail+","+retrievedscheduleAvailableMemberAssignedCoachEmail;
		}
		else
		{
			result=",";
		}
		System.out.println("result "+result);
		return retrievedscheduleAvailableMemberEmail;
	}
	/**
	 *Suresh V
	 */
	public  static String retrieveScheduledCSMember(WebDriver driver) throws ParseException, ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select c.start_dt_Time, C.SESSION_STATUS, acnt.email,prov.email prov_email from CALENDAR_EVENT C,PROVIDER PROV,ACCOUNT ACNT, SUMMARY_ACCOUNT_TODATE smry where prov.id= c.host_id and acnt.id=c.account_id and smry.account_id=acnt.id AND c.session_type_id ='13'and c.session_status  in ('Scheduled') AND ACNT.EMAIL not like '%api%' AND PROV.EMAIL not like '%api%' and smry.onboarding_status='MEMBER SIDE ONBOARDED' and C.START_DT_TIME>sysdate ORDER BY C.START_DT_TIME ASC");
		System.out.println("query executed");
		String Member_Email= "";

		if(rs.next())
		{
			Member_Email = rs.getString("EMAIL");
			System.out.println("Member_EMAIL :"+Member_Email);
		}
		return Member_Email;
	}
	/**
	 *Created by:Suresh V
	 *Description : Created common method for get completed 1on1 session member
	 */
	public static String retrieveCompleted1on1SessionMember(WebDriver driver) throws ParseException, ClassNotFoundException, SQLException
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
		ResultSet rs = stat.executeQuery("select A.EMAIL, CE.SESSION_STATUS from CALENDAR_EVENT CE, ACCOUNT A WHERE A.ID= CE.ACCOUNT_ID AND CE.SESSION_STATUS ='Completed' and CE.SESSION_TYPE_ID = '02' and START_DT_TIME>SYSDATE + INTERVAL '-52' DAY ORDER BY CE.START_DT desc");
		System.out.println("query executed");
		String Member_Email= "";
		if(rs.next())
		{
			Member_Email = rs.getString("EMAIL");
			System.out.println("Member_EMAIL :"+Member_Email);
		}
		return Member_Email;
	}


	/**
	 * Name: VinothKumar.M
	 * Created Date:  11/May/2017
	 * Description : Created common method for Change classroom with interval
	 * @param driver
	 * @param mail
	 */

	public static void idleMemberChangeClassRoom(WebDriver driver)
	{
		try
		{
			wait(driver, "2");
			WebElement yesButton= driver.findElement(By.xpath(OR.RA_PROGRAMADMIN_MEMBERS_TAB_ARE_YOU_SURE_WANT_TO_CHANGE_CLASS_YES_RADIO_BUTTON));
			click(yesButton);
			wait(driver, "3");
			jsClickByXPath(driver, OR.NO_MATCHING_CLASSES_FOUND_OK_BUTTON);
			wait(driver, "3");

		}
		catch(Exception e)
		{
			wait(driver, "2");
			waitForElement(driver,MEMBER_PROFILE_CHANGE_CLASS_PREFERENCES_SWITCH_CLASS_SUCCESS_TEXT);
			WebElement switchClassText= driver.findElement(By.xpath(OR.MEMBER_PROFILE_CHANGE_CLASS_PREFERENCES_SWITCH_CLASS_SUCCESS_TEXT));
			verifyElementIsPresent(driver, switchClassText);
			jsClickByXPath(driver, OR.RA_COACH_ASSIGN_SWITCH_SUCCESSFUL_OK_BUTTON);
			wait(driver, "5");

		}
	}
}
