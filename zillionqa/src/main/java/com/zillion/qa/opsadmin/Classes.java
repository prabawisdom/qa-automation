package com.zillion.qa.opsadmin;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.Calendar;
import java.sql.*;
import com.zillion.qa.commands.Manipulation;
import com.zillion.qa.commands.Navigate;
import com.zillion.qa.utils.Directory;
import java.awt.AWTException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
public class Classes extends Manipulation implements OR
{
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   03/02/2016
	 * Modified Date:   
	 * Description :   Create a common method to VERIFY CURRENT DATE 
	 * Required Inputs :  
	 */
	public static void currentDateHFopsTimeSlot(WebDriver driver)
	{
		String currentdate = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
		String currentdate2= currentdate.substring(0).replace("0", "");
		System.out.println("currentdate: "+currentdate2);
		WebElement Currentdateclick=driver.findElement(By.xpath(OR.OPS_ADMIN_TIMESLOT_CHECK_STARTDATE));
		click(Currentdateclick);
		WebElement currentdate1=driver.findElement(By.xpath(OR.OPS_ADMIN_TIMESLOT_CHECK_FROM_START+currentdate2+OPS_ADMIN_TIMESLOT_CHECK_FROM_END));
		verifyElementIsPresent(driver,currentdate1);
	}
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   03/02/2016
	 * Modified Date:   
	 * Description :   Create a common method to DATE AND TIME ENABLED
	 * Required Inputs :  
	 */
	public static String dateAndTimeEnabled(WebDriver driver)
	{
		try 
		{
			WebElement earlyMoringDisabledDate= driver.findElement(By.xpath(OR.OPS_ADMIN_EARLY_MORNING_DISABLED_DATE));
			verifyElementIsNotPresent(driver, earlyMoringDisabledDate);
		}
		catch(Exception e)
		{
			WebElement earlyMoringEnableDate= driver.findElement(By.xpath(OR.OPS_ADMIN_EARLY_MORNING_ENABLED_DATE));
			verifyElementIsPresent(driver, earlyMoringEnableDate);
			click(earlyMoringEnableDate);
			Navigate.waitTime(driver, "2");
			WebElement earlyMorningTime= driver.findElement(By.xpath(OR.OPS_ADMIN_EARLY_MORNING_TIME));
			verifyElementIsPresent(driver, earlyMorningTime);
			click(earlyMorningTime);
			return"Early Morning is Enabled";
		}
		try 
		{
			WebElement eveningDisabledDate= driver.findElement(By.xpath(OR.OPS_ADMIN_EARLY_EVENING_DISABLED_DATE));
			verifyElementIsNotPresent(driver, eveningDisabledDate);
		}
		catch(Exception e)
		{
			WebElement eveningEnabledDate= driver.findElement(By.xpath(OR.OPS_ADMIN_EARLY_EVENING_ENABLED_DATE));
			verifyElementIsPresent(driver, eveningEnabledDate);
			click(eveningEnabledDate);
			Navigate.waitTime(driver, "2");
			WebElement eveningTime= driver.findElement(By.xpath(OR.OPS_ADMIN_EVENING_TIME));
			verifyElementIsPresent(driver, eveningTime);
			click(eveningTime);
			return"Evening is Enabled";
		}
		try 
		{
			WebElement lateEveningDisabledDate= driver.findElement(By.xpath(OR.OPS_ADMIN_LATE_EVENING_DISABLED_DATE));
			verifyElementIsNotPresent(driver, lateEveningDisabledDate);
		}
		catch(Exception e)
		{
			WebElement lateEveningEnabledDate= driver.findElement(By.xpath(OR.OPS_ADMIN_LATE_EVENING_ENABLED_DATE));
			verifyElementIsPresent(driver, lateEveningEnabledDate);
			click(lateEveningEnabledDate);
			Navigate.waitTime(driver, "2");
			WebElement lateEveningTime= driver.findElement(By.xpath(OR.OPS_ADMIN_LATE_EVENING_TIME));
			verifyElementIsPresent(driver, lateEveningTime);
			click(lateEveningTime);
			return"Late Evening is Enabled";
		}
		return" Date and the Time is selected ";
	}
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   03/02/2016
	 * Modified Date:   
	 * Description :   Create a common method to VALIDATE UPCOMING, PREVIOUS and ALL
	 * Required Inputs :  
	 */
	public static void validateUpcomingPreviousAllSession(WebDriver driver)
	{
		//------Upcoming Session
		WebElement SessionDropDOwn=driver.findElement(By.xpath(OR.OPS_ADMIN_SESSION_DROPDOWN));
		verifyElementIsPresent(driver,SessionDropDOwn);
		jsClickByXPath(driver, OR.OPS_ADMIN_SESSION_DROPDOWN);
		WebElement UpcomingOption=driver.findElement(By.xpath(OR.OPS_ADMIN_UPCOMING_OPTION));
		WebElement PreviousOption=driver.findElement(By.xpath(OR.OPS_ADMIN_PREVIOUS_OPTION));
		WebElement AllOption=driver.findElement(By.xpath(OR.OPS_ADMIN_ALL_OPTION));
		WebElement pageCounterNumber=driver.findElement(By.xpath(OR.OPS_ADMIN_PAGE_COUNTER_NUMBER));
		verifyElementIsPresent(driver, UpcomingOption);
		verifyElementIsPresent(driver, PreviousOption);
		verifyElementIsPresent(driver, AllOption);
		jsClickByXPath(driver, OR.OPS_ADMIN_UPCOMING_OPTION);
		wait(driver, "3");
		WebElement OneMonthAhead=driver.findElement(By.xpath(OR.OPS_ADMIN_ONE_MONTH_AHEAD_FROM));
		waitForElement(driver, OR.OPS_ADMIN_ONE_MONTH_AHEAD_FROM);
		verifyElementIsPresent(driver, OneMonthAhead);
		WebElement UpcomingStartDate=driver.findElement(By.xpath(OR.OPS_ADMIN_UPCOMING_START_DATE));
		verifyElementIsPresent(driver, UpcomingStartDate);
		//------Validate Current date in Start date of Upcoming session
		Calendar cal = Calendar.getInstance();
		String currentdate12 = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
		System.out.println("currentdate: "+currentdate12);
		WebElement Currentdateclick=driver.findElement(By.xpath(OR.OPS_ADMIN_UPCOMING_START_DATE));
		click(Currentdateclick);
		wait(driver, "3");
		WebElement currentdate5=driver.findElement(By.xpath(OR.OPS_ADMIN_TIMESLOT_CHECK_FROM_START+currentdate12+OPS_ADMIN_TIMESLOT_CHECK_FROM_END));
		System.out.println("currentdate1: "+currentdate5);
		verifyElementIsPresent(driver,currentdate5);
		click(currentdate5);
		click(pageCounterNumber);
		//-----Page counter for Upcoming session
		String UpcomingPageCounter1= driver.findElement(By.xpath(OR.OPS_ADMIN_PAGE_COUNTER_NUMBER)).getText();
		String UpcomingPageCounter2= UpcomingPageCounter1.replace("(", "");
		String UpcomingPageCounter3= UpcomingPageCounter2.replace(")", "");
		String UpcomingPageCounter4= UpcomingPageCounter3.replace(",", "");
		System.out.println("Upcoming session counter number: "+UpcomingPageCounter4);
		//------Previous Session
		jsClickByXPath(driver, OR.OPS_ADMIN_SESSION_DROPDOWN);
		jsClickByXPath(driver, OR.OPS_ADMIN_PREVIOUS_OPTION);
		wait(driver, "3");
		WebElement OneMonthBack=driver.findElement(By.xpath(OR.OPS_ADMIN_ONE_MONTH_BACK_FROM));
		waitForElement(driver, OR.OPS_ADMIN_ONE_MONTH_BACK_FROM);
		verifyElementIsPresent(driver, OneMonthBack);
		//------Validate Current date in Start date of Previous session
		click(Currentdateclick);
		wait(driver, "3");
		WebElement currentdate6=driver.findElement(By.xpath(OR.OPS_ADMIN_TIMESLOT_CHECK_FROM_START+currentdate12+OPS_ADMIN_TIMESLOT_CHECK_FROM_END));
		System.out.println("currentdate1: "+currentdate6);
		verifyElementIsPresent(driver,currentdate6);
		click(currentdate6);
		click(pageCounterNumber);
		//-----Page counter for Previous session
		String PreviousPageCounter1= driver.findElement(By.xpath(OR.OPS_ADMIN_PAGE_COUNTER_NUMBER)).getText();
		String PreviousPageCounter2= PreviousPageCounter1.replace("(", "");
		String PreviousPageCounter3= PreviousPageCounter2.replace(")", "");
		String PreviousPageCounter4= PreviousPageCounter3.replace(",", "");
		System.out.println("Previous session counter number: "+PreviousPageCounter4);
		int upcomingpagecounter0 = Integer.parseInt(UpcomingPageCounter4);
		int previouspagecounter0 = Integer.parseInt(PreviousPageCounter4);
		int SumOfUpcomingAndPrevious= upcomingpagecounter0+previouspagecounter0;
		System.out.println("Sum of Upcoming and previous: "+SumOfUpcomingAndPrevious);
	}
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   03/02/2016
	 * Modified Date:   
	 * Description :   Create a common method to VERIFY CURRENT DATE 
	 * Required Inputs :  
	 */
	public static void nextDateCoachUnavailabeTimeSlot(WebDriver driver)
	{
		try
		{
			waitForElement(driver, COACH_UNAVAILABILITY_START_DATE);
			WebElement Currentdateclick=driver.findElement(By.xpath(OR.COACH_UNAVAILABILITY_START_DATE));
			actionClick(driver, Currentdateclick);
			/*String currentdate = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
			System.out.println("Currentdate: "+currentdate);
			int currentdate4 = Integer.parseInt(currentdate);
			int currentdate5 = currentdate4+1;
			String currentdate6 = Integer.toString(currentdate5);
			String currentmonth = new SimpleDateFormat("M").format(Calendar.getInstance().getTime());
			int currentmonth4 = Integer.parseInt(currentmonth);
			System.out.println("Next Current date: "+currentmonth4);
			int currentmonth5 = currentmonth4-1;
			String currentmonth6 = Integer.toString(currentmonth5);
			System.out.println("Next Current date: "+currentdate6);
			System.out.println("Next Current month: "+currentmonth6);*/
			
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 1);
			String currentdate6 = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
			String currentmonth6 = Integer.toString(cal.get(Calendar.MONTH));
			System.out.println("Next Current date: "+currentdate6);
			System.out.println("Next Current month: "+currentmonth6);
			String currentyear = Integer.toString(cal.get(Calendar.YEAR));
			wait(driver, "7");
			WebElement currentdate12=driver.findElement(By.xpath(OR.COACH_TIMESLOT_CHECK_FROM_START+currentdate6+"' and @data-month='"+currentmonth6+COACH_TIMESLOT_CHECK_FROM_END));
			verifyElementIsPresent(driver,currentdate12);
			click(currentdate12);
			System.out.println("Next date of the current date is selected");
		}
		catch(Exception e)
		{
			waitForElement(driver, COACH_UNAVAILABILITY_START_DATE);
			WebElement Currentdateclick=driver.findElement(By.xpath(OR.COACH_UNAVAILABILITY_START_DATE));
			actionClick(driver, Currentdateclick);
			String currentdate01 = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
			WebElement currentdate13=driver.findElement(By.xpath(OR.COACH_TIMESLOT_CHECK_FROM_START+currentdate01+COACH_TIMESLOT_CHECK_FROM_END_AND_DISABLED_DATE));
			verifyElementIsPresent(driver,currentdate13);
			WebElement calender_next_monthButton=driver.findElement(By.xpath(OR.COACH_TIMESLOT_CHECK_FROM_START+currentdate01+COACH_TIMESLOT_CHECK_FROM_END_AND_DISABLED_DATE));
			verifyElementIsPresent(driver,calender_next_monthButton);
			click(calender_next_monthButton);
			WebElement calender_next_DateOne=driver.findElement(By.xpath(OR.COACH_TIMESLOT_CALENDER_ONE_DATE));
			verifyElementIsPresent(driver,calender_next_DateOne);
			click(calender_next_DateOne);
			System.out.println("Next month is selected from the calender");
		}	
	}
	
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   16/02/2016
	 * Modified Date:  
	 * Description :   Create a common method to Verify Created Lecture Session
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws AWTException 
	 */
	public static void verifyCreatedLectureSession(WebDriver driver)
	{
		try
		{
			WebElement created_lecture_session6_00_6_45=driver.findElement(By.xpath(OR.CREATED_LECTURE_SESSION_6_00_6_45));
			waitForElement(driver, OR.CREATED_LECTURE_SESSION_6_00_6_45);
			verifyElementIsPresent(driver,created_lecture_session6_00_6_45);
		}
		catch(Exception e)
		{
			WebElement nextPage_Navigator=driver.findElement(By.xpath(OR.NEXT_PAGE_NAVIGATOR));
			verifyElementIsPresent(driver,nextPage_Navigator);
			click(nextPage_Navigator);
			Navigate.waitTime(driver, "20");
			Navigate.pageDown(driver);
			Navigate.pageDown(driver);
			waitForElement(driver, OR.CREATED_LECTURE_SESSION_6_00_6_45);
			WebElement created_lecture_session6_00_6_45=driver.findElement(By.xpath(OR.CREATED_LECTURE_SESSION_6_00_6_45));
			verifyElementIsPresent(driver,created_lecture_session6_00_6_45);
		}
	}
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   16/02/2016
	 * Modified Date:  
	 * Description :   Create a common method to Verify Created Lecture Session
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws AWTException 
	 */
	public static void cancelCreatedLectureSession(WebDriver driver)
	{
		WebElement created_lecture_session6_00_6_45=driver.findElement(By.xpath(OR.CREATED_LECTURE_SESSION_5_00_6_15));
		waitForElement(driver, OR.CREATED_LECTURE_SESSION_5_00_6_15);
		click(created_lecture_session6_00_6_45);
		WebElement cancelScheduleOption=driver.findElement(By.xpath(OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_SCHEDULE_CANCEL_SCHEDULE_OPTION));
		waitForElement(driver, OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_SCHEDULE_CANCEL_SCHEDULE_OPTION);
		waitForElement(driver, OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_SCHEDULE_ASSIGN_SUBSTITUTE_OPTION);
		click(cancelScheduleOption);
		waitForElement(driver, OPS_ADMIN_PROVIDERS_LECTURE_SESSION_CANCEL_SESSION_BUTTON);
		Navigate.waitTime(driver, "10");
		wait(driver, "3");
		waitForElement(driver, OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_CANCEL_SESSION_TITLE);
		WebElement cancelSessionTitle=driver.findElement(By.xpath(OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_CANCEL_SESSION_TITLE));
		WebElement lectureTitleLabel=driver.findElement(By.xpath(OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_LECTURE_TITLE_LABEL));
		WebElement startTimeLabel=driver.findElement(By.xpath(OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_START_TIME_LABEL));
		WebElement cancelSessionButton=driver.findElement(By.xpath(OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_CANCEL_SESSION_BUTTON));
		WebElement backToScheduleButton=driver.findElement(By.xpath(OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_BACK_TO_SCHEDULE));
		verifyElementIsPresent(driver,cancelSessionTitle);
		verifyElementIsPresent(driver,lectureTitleLabel);
		verifyElementIsPresent(driver,startTimeLabel);
		verifyElementIsPresent(driver,cancelSessionButton);
		verifyElementIsPresent(driver,backToScheduleButton);
		jsClickByXPath(driver, OPS_ADMIN_PROVIDERS_LECTURE_SESSION_CANCEL_SESSION_BUTTON);
		Navigate.waitTime(driver, "10");
		wait(driver, "2");
		waitForElement(driver, OPS_ADMIN_PROVIDERS_LECTURE_SESSION_SCHEDULE_CANCEL_POPUP_CANCELLED_SUCCESS_TEXT);
		wait(driver, "1");
		waitForElement(driver, OPS_ADMIN_PROVIDERS_LECTURE_SESSION_SCHEDULE_CANCEL_POPUP_OK_BUTTON);
		jsClickByXPath(driver, OPS_ADMIN_PROVIDERS_LECTURE_SESSION_SCHEDULE_CANCEL_POPUP_OK_BUTTON);
	}
	public  static  void oneononeApprovalcheckbox(WebDriver driver,String data) throws ClassNotFoundException, SQLException 
	{
		String port = Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		int  approvedStatus =0;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select IS_APPROVED from mp_provider_approved where session_type_id= '30100AA52BEAEE0EE1'and provider_id= '"+data+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			approvedStatus= rs.getInt("IS_APPROVED");
			System.out.println(approvedStatus);
		}
		if(approvedStatus==0)
		{
			WebElement checkbox=driver.findElement(By.xpath(HFOPS_PROVIDER_COACH1_APPROVAL_1ON1SESSION));
			wait(driver, "1");
			click(checkbox);
			WebElement save_btn=driver.findElement(By.xpath(OR.HFOPS_APPROVAL_SAVE));
			wait(driver, "1");
			click(save_btn);
			wait(driver, "1");
			waitForElement(driver, HFOPS_APPROVAL_SUCCESS_MSG);
			jsClickByXPath(driver, HFOPS_APPROVAL_SUCCESS_MSG);
		}
	}
	public  static  void lectureSessionApprovalcheckbox(WebDriver driver,String data) throws ClassNotFoundException, SQLException 
	{
		int approvedStatus = 0;
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
		ResultSet rs = stat.executeQuery("select IS_APPROVED from mp_provider_approved where session_type_id= '30100AA52BEAEE0EE3'and provider_id= '"+data+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			approvedStatus= rs.getInt("IS_APPROVED");
			System.out.println(approvedStatus);
		}
		if(approvedStatus==0)
		{
			WebElement checkbox=driver.findElement(By.xpath(HFOPS_PROVIDER_COACH1_APPROVAL_LECTURE_SESSION));
			wait(driver, "1");
			click(checkbox);
			WebElement save_btn=driver.findElement(By.xpath(OR.HFOPS_APPROVAL_SAVE));
			wait(driver, "1");
			click(save_btn);
			waitForElement(driver, HFOPS_APPROVAL_SUCCESS_MSG);
			jsClickByXPath(driver, HFOPS_APPROVAL_SUCCESS_MSG);
		}
		else
		{
			System.out.println("unapproved"+approvedStatus);
		}
	}
	public  static  void accept1(WebDriver driver) throws ClassNotFoundException, SQLException 
	{
		try
		{
			Alert alert = driver.switchTo().alert();
			alert.accept();
			WebElement save_btn=driver.findElement(By.xpath(OR.HFOPS_APPROVAL_SAVE));
			wait(driver, "1");
			click(save_btn);
		}
		catch(Exception e)
		{
			WebElement save_btn=driver.findElement(By.xpath(OR.HFOPS_APPROVAL_SAVE));
			wait(driver, "1");
			click(save_btn);
		}
	}
	/**
	 * Name :V.Suresh        
	 * Created Date: 10/June/2016    
	 * Modified Date:     
	 */
	public  static  void groupsessionApprovalcheckbox(WebDriver driver) throws ClassNotFoundException, SQLException 
	{
		WebElement checkBox1=driver.findElement(By.xpath(HFOPS_PROVIDER_COACH1_APPROVAL_1ON1SESSION));
		if(checkBox1.isSelected())
		{
			click(checkBox1);
			try
			{
				Alert alert = driver.switchTo().alert();
				alert.accept();
				WebElement save_btn=driver.findElement(By.xpath(OR.HFOPS_APPROVAL_SAVE));
				wait(driver, "1");
				click(save_btn);
			}
			catch(Exception e)
			{
				WebElement save_btn=driver.findElement(By.xpath(OR.HFOPS_APPROVAL_SAVE));
				wait(driver, "1");
				click(save_btn);
			}
		}
		else
		{
			WebElement save_btn=driver.findElement(By.xpath(OR.HFOPS_APPROVAL_SAVE));
			wait(driver, "1");
			click(save_btn);
		}
	}
	/**
	 * Name :     LEENA P.
	 * Created Date:   16/02/2016
	 * Modified Date:  
	 * Description :   Create a common method to Cancel lecture session
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws AWTException 
	 */
	public static void cancelLectureSession(WebDriver driver, String lectureTitle)
	{
		try
		{
			String lecture=OR.COACHES_LECTURE_TITLE_START+lectureTitle+COACHES_LECTURE_TITLE_END;
			WebElement created_lecture_session=driver.findElement(By.xpath(OR.COACHES_LECTURE_TITLE_START+lectureTitle+COACHES_LECTURE_TITLE_END));
			waitForElement(driver, lecture);
			verifyElementIsPresent(driver,created_lecture_session);
			jsClickByXPath(driver, lecture);
			WebElement cancelScheduleOption=driver.findElement(By.xpath(OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_SCHEDULE_CANCEL_SCHEDULE_OPTION));
			WebElement assignSubstituteOption=driver.findElement(By.xpath(OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_SCHEDULE_ASSIGN_SUBSTITUTE_OPTION));
			verifyElementIsPresent(driver,cancelScheduleOption);
			verifyElementIsPresent(driver,assignSubstituteOption);
			jsClickByXPath(driver, OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_SCHEDULE_CANCEL_SCHEDULE_OPTION);
			wait(driver, "5");
			waitForElement(driver, OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_CANCEL_SESSION_TITLE);
			WebElement cancelSessionTitle=driver.findElement(By.xpath(OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_CANCEL_SESSION_TITLE));
			WebElement lectureTitleLabel=driver.findElement(By.xpath(OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_LECTURE_TITLE_LABEL));
			WebElement startTimeLabel=driver.findElement(By.xpath(OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_START_TIME_LABEL));
			WebElement cancelSessionButton=driver.findElement(By.xpath(OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_CANCEL_SESSION_BUTTON));
			WebElement backToScheduleButton=driver.findElement(By.xpath(OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_BACK_TO_SCHEDULE));
			verifyElementIsPresent(driver,cancelSessionTitle);
			verifyElementIsPresent(driver,lectureTitleLabel);
			verifyElementIsPresent(driver,startTimeLabel);
			verifyElementIsPresent(driver,cancelSessionButton);
			verifyElementIsPresent(driver,backToScheduleButton);
			WebElement cancelButton =driver.findElement(By.xpath(OR.COACHES_LECTURE_CANCEL_SESSION_BUTTON));
			click(cancelButton);
			wait(driver, "10");
			WebElement popUpSuccessOkButton1 =driver.findElement(By.xpath(OR.COACHES_LECTURE_CANCEL_SESSION_POPUP_OK_BUTTON));
			popUpSuccessOkButton1.click();
			wait(driver, "5");
			waitForElement(driver, OR.SCHEDULE_PAGE_NAVIGATOR_RIGHT);
			jsClickByXPath(driver, OR.SCHEDULE_PAGE_NAVIGATOR_RIGHT);
			Navigate.pageDown(driver);
			Navigate.pageDown(driver);
		}
		catch(Exception e) 
		{
			waitForElement(driver, OR.SCHEDULE_PAGE_NAVIGATOR_LEFT);
			jsClickByXPath(driver, OR.SCHEDULE_PAGE_NAVIGATOR_LEFT);
			Navigate.pageDown(driver);
			Navigate.pageDown(driver);
			try
			{
				String lecture=OR.COACHES_LECTURE_TITLE_START+lectureTitle+COACHES_LECTURE_TITLE_END;
				WebElement created_lecture_session=driver.findElement(By.xpath(OR.COACHES_LECTURE_TITLE_START+lectureTitle+COACHES_LECTURE_TITLE_END));
				waitForElement(driver, lecture);
				verifyElementIsPresent(driver,created_lecture_session);
				jsClickByXPath(driver, lecture);
				WebElement cancelScheduleOption=driver.findElement(By.xpath(OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_SCHEDULE_CANCEL_SCHEDULE_OPTION));
				WebElement assignSubstituteOption=driver.findElement(By.xpath(OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_SCHEDULE_ASSIGN_SUBSTITUTE_OPTION));
				verifyElementIsPresent(driver,cancelScheduleOption);
				verifyElementIsPresent(driver,assignSubstituteOption);
				jsClickByXPath(driver, OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_SCHEDULE_CANCEL_SCHEDULE_OPTION);
				wait(driver, "5");
				waitForElement(driver, OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_CANCEL_SESSION_TITLE);
				WebElement cancelSessionTitle=driver.findElement(By.xpath(OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_CANCEL_SESSION_TITLE));
				WebElement lectureTitleLabel=driver.findElement(By.xpath(OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_LECTURE_TITLE_LABEL));
				WebElement startTimeLabel=driver.findElement(By.xpath(OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_START_TIME_LABEL));
				WebElement cancelSessionButton=driver.findElement(By.xpath(OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_CANCEL_SESSION_BUTTON));
				WebElement backToScheduleButton=driver.findElement(By.xpath(OR.OPS_ADMIN_PROVIDERS_LECTURE_SESSION_BACK_TO_SCHEDULE));
				verifyElementIsPresent(driver,cancelSessionTitle);
				verifyElementIsPresent(driver,lectureTitleLabel);
				verifyElementIsPresent(driver,startTimeLabel);
				verifyElementIsPresent(driver,cancelSessionButton);
				verifyElementIsPresent(driver,backToScheduleButton);
				WebElement cancelButton =driver.findElement(By.xpath(OR.COACHES_LECTURE_CANCEL_SESSION_BUTTON));
				click(cancelButton);
				wait(driver, "10");
				WebElement popUpSuccessOkButton1 =driver.findElement(By.xpath(OR.COACHES_LECTURE_CANCEL_SESSION_POPUP_OK_BUTTON));
				popUpSuccessOkButton1.click();
				wait(driver, "5");
				waitForElement(driver, OR.SCHEDULE_PAGE_NAVIGATOR_RIGHT);
				jsClickByXPath(driver, OR.SCHEDULE_PAGE_NAVIGATOR_RIGHT);
				Navigate.pageDown(driver);
				Navigate.pageDown(driver);
			}
			catch(Exception e1) {
				waitForElement(driver, OR.SCHEDULE_PAGE_NAVIGATOR_RIGHT);
				jsClickByXPath(driver, OR.SCHEDULE_PAGE_NAVIGATOR_RIGHT);
				Navigate.pageDown(driver);
				Navigate.pageDown(driver);
			}
		}
	}
	/**
	 * Name :     LEENA P.
	 * Created Date:   16/02/2016
	 * Modified Date:  
	 * Description :   Create a common method to Verify Lecture session
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws AWTException 
	 */
	public static void verifyLectureSession(WebDriver driver, String lectureTitle)
	{
		wait(driver, "5");
		String lecture = "//a[contains(@class,'fc-time-grid-event') and contains(@class,'sessionCalendarEvent')]//div[contains(@class,'fc-title') and contains(.,'"+lectureTitle+"')]";
		try
		{	
			WebElement created_lecture_session=driver.findElement(By.xpath(lecture));
			waitForElement(driver, lecture);
			verifyElementIsPresent(driver,created_lecture_session);
		}
		catch(Exception e)
		{
			waitForElement(driver, OR.SCHEDULE_PAGE_NAVIGATOR_LEFT);
			jsClickByXPath(driver, OR.SCHEDULE_PAGE_NAVIGATOR_LEFT);
			Navigate.pageDown(driver);
			Navigate.pageDown(driver);
			WebElement created_lecture_session=driver.findElement(By.xpath(lecture));
			waitForElement(driver, lecture);
			verifyElementIsPresent(driver,created_lecture_session);
		}
	}
	/**
	 * Name :    Abinaya.p
	 * Created Date:   15/Sep/2016
	 * Modified Date:  
	 * Description :   Create a common method to check the approval for 1on1 session from PA
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws AWTException 
	 */
	public  static  void oneononeApprovalcheckboxFromPA(WebDriver driver,String data) throws ClassNotFoundException, SQLException 
	{
		String port = Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		int approvedStatus=0;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select IS_APPROVED from mp_provider_approved where session_type_id= '39448FC1B9923573E053A5121FAC4CA5'and provider_id= '"+data+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			approvedStatus= rs.getInt("IS_APPROVED");
			System.out.println(approvedStatus);
		}
		if(approvedStatus==0)
		{
			WebElement checkbox=driver.findElement(By.xpath(PA_PROVIDER_COACH1_APPROVAL_1ON1SESSION));
			wait(driver, "1");
			click(checkbox);
			WebElement save_btn=driver.findElement(By.xpath(OR.HFOPS_APPROVAL_SAVE));
			wait(driver, "1");
			click(save_btn);
			waitForElement(driver, HFOPS_APPROVAL_SUCCESS_MSG);
			jsClickByXPath(driver, HFOPS_APPROVAL_SUCCESS_MSG);
		}
	}
	
	
	/**
	 * Name :     LEENA P.
	 * Created Date:   
	 * Modified Date:  
	 * Description :   Create a common method to Verify Created 1on1 Session
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws AWTException 
	 */
	public static void verify1on1Session(WebDriver driver, String memname)
	{
	
		try{
			WebElement click_day_view = driver.findElement(By.xpath(OR.COACHES_GEAR_BUTTON_SCHEDULE_CALENDER_DAY_VIEW_BUTTON));
			click(click_day_view);
			String OneonOnename = "//div[@class='fc-content']//div[contains(text(),'1on1') and contains(text(),'"+memname+"')]";
			WebElement verify_1on1Session=driver.findElement(By.xpath(OneonOnename));
			click(verify_1on1Session);
		}
		catch(Exception e)
		{
			WebElement calendar_week_view = driver.findElement(By.xpath(OR.NEXT_PAGE_NAVIGATOR));
			click(calendar_week_view);
			String OneonOnename = "//div[@class='fc-content']//div[contains(text(),'1on1') and contains(text(),'"+memname+"')]";
			WebElement verify_1on1Session=driver.findElement(By.xpath(OneonOnename));
			click(verify_1on1Session);
		}
		
	}
	
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   03/02/2016
	 * Modified Date:   
	 * Description :   Create a common method to VERIFY CURRENT DATE 
	 * Required Inputs :  
	 */
	public static void coachAvailabilityCurrentDate(WebDriver driver)
	{
			waitForElement(driver, COACH_UNAVAILABILITY_START_DATE);
			String currentDate1 = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
			String currentDate = currentDate1.replaceFirst("0", "");
			System.out.println("Current date :"+currentDate);
			WebElement Currentdateclick=driver.findElement(By.xpath(OR.COACH_UNAVAILABILITY_START_DATE));
			actionClick(driver, Currentdateclick);
			wait(driver, "3");
			WebElement currentdate12=driver.findElement(By.xpath("//td[contains(@class,'xdsoft_date xdsoft_day')]/div[text()='"+currentDate+"']"));
			verifyElementIsPresent(driver,currentdate12);
			click(currentdate12);
			wait(driver, "3");
			System.out.println("Next date of the current date is selected");
		}
	/**
	 * Name :     VinothKumar.M 
	 * Created Date:   09/05/2017
	 * Modified Date:   
	 * Description :   Create a common method to NEXT DATE COACH UNAVAILABLE LIST
	 * Required Inputs :  
	 */
	public static void nextDateCoachUnavailabeList(WebDriver driver)
	{
			waitForElement(driver, OPS_ADMIN_TIMESLOT_CHECK_STARTDATE);
			WebElement Currentdateclick=driver.findElement(By.xpath(OR.OPS_ADMIN_TIMESLOT_CHECK_STARTDATE));
			actionClick(driver, Currentdateclick);	
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 1);
			String currentdate6 = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
			String currentmonth6 = Integer.toString(cal.get(Calendar.MONTH));
			System.out.println("Next Current date: "+currentdate6);
			System.out.println("Next Current month: "+currentmonth6);
			String currentyear = Integer.toString(cal.get(Calendar.YEAR));
			wait(driver, "7");
			WebElement currentdate12=driver.findElement(By.xpath(OR.COACH_TIMESLOT_CHECK_FROM_START+currentdate6+"' and @data-month='"+currentmonth6+COACH_TIMESLOT_CHECK_FROM_END));
			verifyElementIsPresent(driver,currentdate12);
			click(currentdate12);
			wait(driver, "5");
			System.out.println("Next date of the current date is selected");
	
	}
	
}