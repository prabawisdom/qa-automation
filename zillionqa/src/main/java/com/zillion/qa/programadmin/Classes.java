package com.zillion.qa.programadmin;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.zillion.qa.commands.Manipulation;
import com.zillion.qa.commands.Navigate;
import com.zillion.qa.utils.Directory;
public class Classes extends Manipulation implements OR
{
	/**
	 * Name         :   VIGNESHRAJ.M
	 * Created Date :   07/Jan/16
	 * Modified Date:
	 * Description  : Validate Insufficient/AllSessions Tab under classes tab in Program Admin/OpsAdmin
	 * Required Inputs:
	 * Vulcan>Provider portal>Program Admin> Classes
	 * Test_ID:185888
	 * Test_ID:185889
	 */
	public static void validateInsufficientAllSessionTab(WebDriver driver)
	{
		WebElement classesTab= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_HEADER));
		verifyElementIsPresent(driver, classesTab);
		click(classesTab);
		Navigate.waitTime(driver, "5");
		waitForElement(driver, OR.PROGRAM_ADMIN_CLASSES_TAB_INSUFFICIENT_SUBTAB);
		WebElement insufficientSubTab= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_INSUFFICIENT_SUBTAB));
		WebElement allSessionsSubTab= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_SUBTAB));
		WebElement timeSlotSubTab= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_TIMESLOTCHECK_SUBTAB));
		WebElement insufficientTitle= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_INSUFFICIENT_TITLE));
		WebElement insufficientTotalCount= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_INSUFFICIENT_TOTAL_COUNT));
		verifyElementIsPresent(driver, insufficientSubTab);
		verifyElementIsPresent(driver, allSessionsSubTab);
		verifyElementIsPresent(driver, timeSlotSubTab);
		verifyElementIsPresent(driver, insufficientTitle);
		verifyElementIsPresent(driver, insufficientTotalCount);
		String InsufficientCount = driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_INSUFFICIENT_TOTAL_COUNT)).getText();
		System.out.println("Total number of Insufficient Count is :"+InsufficientCount);
		click(allSessionsSubTab);
		waitForElement(driver, OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_TITLE);
		WebElement allSessionTitle= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_TITLE));
		WebElement allSessionTotalCount= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_TOTAL_COUNT));
		WebElement sessionDropdown= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_SESSION_DROPDOWN));
		verifyElementIsPresent(driver, allSessionTitle);
		verifyElementIsPresent(driver, allSessionTotalCount);
		verifyElementIsPresent(driver, sessionDropdown);
		String AllsessionCount = driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_TOTAL_COUNT)).getText();
		System.out.println("Total number of All Session Count is :"+AllsessionCount);
		click(sessionDropdown);
		WebElement dropdownUpcomingOption= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_TITLE));
		WebElement dropdownPreviousOption= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_TITLE));
		WebElement allSedropdownAllOption= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_TITLE));
		verifyElementIsPresent(driver, dropdownUpcomingOption);
		verifyElementIsPresent(driver, dropdownPreviousOption);
		verifyElementIsPresent(driver, allSedropdownAllOption);
		click(sessionDropdown);
		WebElement oneMonthAheadTextRef= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_UPCOMING_1MONTH_AHEAD_TEXT_REF));
		WebElement upcomingStartDate= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_UPCOMING_START_DATE));
		WebElement startDateCalender= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_UPCOMING__START_DATE_CALENDER));
		WebElement sessionFilter= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_UPCOMING_SESSION_FILTER));
		verifyElementIsPresent(driver, oneMonthAheadTextRef);
		verifyElementIsPresent(driver, upcomingStartDate);
		verifyElementIsPresent(driver, startDateCalender);
		verifyElementIsPresent(driver, sessionFilter);
		click(sessionFilter);
		WebElement filterAll= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_UPCOMING_SESSION_FILTER_ALL));
		WebElement filterPatient= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_UPCOMING_SESSION_FILTER_PATIENT));
		WebElement filterCoach= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_UPCOMING_SESSION_FILTER_COACH));
		WebElement filterSessionWeek= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_UPCOMING_SESSION_FILTER_SESSION_WEEK));
		WebElement filterSessionStatus= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_UPCOMING_SESSION_FILTER_SESSION_STATUS));
		verifyElementIsPresent(driver, filterAll);
		verifyElementIsPresent(driver, filterPatient);
		verifyElementIsPresent(driver, filterCoach);
		verifyElementIsPresent(driver, filterSessionWeek);
		verifyElementIsPresent(driver, filterSessionStatus);
		WebElement dateHeader= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_CONTENT_TABLE_DATE));
		WebElement timeHeader= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_CONTENT_TABLE_TIME));
		WebElement programHeader= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_CONTENT_TABLE_PROGRAM));
		WebElement sessionWeekHeader= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_CONTENT_TABLE_SESSIONWEEK));
		WebElement sessionTypeHeader= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_CONTENT_TABLE_SESSIONTYPE));
		WebElement patientHeader= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_CONTENT_TABLE_PATIENT));
		WebElement cityHeader= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_CONTENT_TABLE_CITY));
		WebElement coachHeader= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_CONTENT_TABLE_COACH));
		WebElement statusHeader= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_CONTENT_TABLE_STATUS));
		WebElement loginCountHeader= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_CONTENT_TABLE_LOGINCOUNT));
		WebElement loginDateHeader= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_CONTENT_TABLE_LOGINDATE));
		WebElement loginTimeHeader= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_CONTENT_TABLE_LOGINTIME));
		WebElement joinTimeHeader= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_CONTENT_TABLE_JOINTIME));
		WebElement clientNameHeader= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_CONTENT_TABLE_CLIENT_NAME));
		WebElement actionHeader= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_CONTENT_TABLE_ACTION));
		verifyElementIsPresent(driver, dateHeader);
		verifyElementIsPresent(driver, timeHeader);
		verifyElementIsPresent(driver, programHeader);
		verifyElementIsPresent(driver, sessionWeekHeader);
		verifyElementIsPresent(driver, sessionTypeHeader);
		verifyElementIsPresent(driver, patientHeader);
		verifyElementIsPresent(driver, cityHeader);
		verifyElementIsPresent(driver, coachHeader);
		verifyElementIsPresent(driver, statusHeader);
		verifyElementIsPresent(driver, loginCountHeader);
		verifyElementIsPresent(driver, loginDateHeader);
		verifyElementIsPresent(driver, loginTimeHeader);
		verifyElementIsPresent(driver, joinTimeHeader);
		verifyElementIsPresent(driver, clientNameHeader);
		verifyElementIsPresent(driver, actionHeader);
		selectByVisibletext(sessionDropdown, "Previous ");
		waitForElement(driver, OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_UPCOMING_1MONTH_BACK_TEXT_REF);
		WebElement oneMonthBackTextRef= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_UPCOMING_1MONTH_BACK_TEXT_REF));
		verifyElementIsPresent(driver, oneMonthBackTextRef);
		selectByVisibletext(sessionDropdown, "All");
		WebElement endDate= driver.findElement(By.xpath(OR.PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_UPCOMING_END_DATE));
		verifyElementIsPresent(driver, upcomingStartDate);
		verifyElementIsPresent(driver, endDate);
	}
	/**
	 * Name         :   Abinaya.P
	 * Created Date :   21/Oct/16
	 * Modified Date:
	 * Description  : To validate whether current date is displayed over the day on the calender
	 * Required Inputs:
	 */
	public static void validateDayOnCalender(WebDriver driver)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		waitForElement(driver, "//td[contains(@class,'fc-today') and @data-date='"+dateFormat.format(date)+"']");
	}
	/**
	 * Name         :   Abinaya.P
	 * Created Date :   21/Oct/16
	 * Modified Date:
	 * Description  : To validate whether current week is displayed over the day on the calender
	 * Required Inputs:
	 */
	public static void validateWeeekOnCalender(WebDriver driver)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.getFirstDayOfWeek();
	}
	/**
	 * Name         :   Suresh V
	 * Created Date :   25/Oct/16
	 * Modified Date:
	 * Description  : Validate current date highlighted colour
	 */
	public static void highlightedColorAndCurrentDateValidate(WebDriver driver)
	{
		Date today = new Date();
		System.out.println("1."+"Today date is : " + today);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(today);
		String dateTrim=date.trim();
		System.out.println("2."+"Format change today date is : " + dateTrim);
		WebElement Date2=driver.findElement(By.xpath(OR.HIGHLIGHTED_DATE_FIRSTHALF+"'"+dateTrim+"'"+HIGHLIGHTED_DATE_SECONDHALF));
		verifyElementIsPresent(driver, Date2);
	}
	/**
	 * Name         :   Abinaya.P
	 * Created Date :   03/Nov/16
	 * Modified Date:
	 * Description  : To validate whether current month is displayed over the day on the calender
	 * Required Inputs:
	 */
	public static void validateMonthOnCalender(WebDriver driver)
	{
		int month=Calendar.getInstance().get(Calendar.MONTH);
		System.out.println("month "+month);
		if(month==0||month==2||month==4||month==6||month==8||month==10)
		{
			waitForElement(driver, "//td[contains(@data-date,'31')]");
		}
		else if(month==1||month==3||month==5||month==7||month==9||month==11)
		{
			waitForElement(driver, "//td[contains(@data-date,'30')]");
		}
	}
	/**
	 * Name         :   Abinaya.P
	 * Created Date :   03/Nov/16
	 * Modified Date:
	 * Description  : To validate whether local timezone is displayed on the application
	 * Required Inputs:
	 */
	public static void validateTimeZone(WebDriver driver,String timezone1)
	{
		Calendar now = Calendar.getInstance();
		TimeZone timeZone = now.getTimeZone();
		System.out.println("Timezone1 : " + timezone1);
		System.out.println("Current TimeZone is : " + timeZone.getID());
		if(timezone1.equals(timeZone.getID()))
		{
			System.out.println("Displayed Timezone matches with Local Timezone");
		}
		else
		{
			System.out.println("Displayed Timezone does not match with Local Timezone");
			Assert.fail();
		}
	}
	
	/**
	 * Name :     Bharath Kumar. M
	 * Created Date:   17/May/17
	 * Modified Date:
	 * Description : Create a common method to reset security Question and Answer
	 * Ticket ID :
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static void changeSecurityQuestionAndAnswer(WebDriver driver, String mailid)
	{
		wait(driver, "5");
		if(mailid.contains("yopmail.com"))
		{
			Navigate.get(driver, Directory.yopmailurl);
			Navigate.maximize(driver);
			WebElement yopEmailTextbox= driver.findElement(By.xpath(OR.YOP_EMAIL_TEXTBOX));
			verifyElementIsPresent(driver, yopEmailTextbox);
			clear(yopEmailTextbox);
			sendKeys(yopEmailTextbox, mailid);
			WebElement checkInbox= driver.findElement(By.xpath(OR.YOP_EMAIL_CHECK_INBOX));
			verifyElementIsPresent(driver, checkInbox);
			jsClickByXPath(driver, OR.YOP_EMAIL_CHECK_INBOX);
			wait(driver, "5");
			if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
			WebElement iframe2= driver.findElement(By.id(OR.YOPMAIL_IFRAME2));
			Navigate.switchToFrame(driver, iframe2);
			WebElement Resetsecurityemail= driver.findElement(By.xpath(OR.RESET_YOUR_SECURITY_QA));
			verifyElementIsPresent(driver, Resetsecurityemail);
			WebElement Resetlink= driver.findElement(By.xpath(OR.RESET_YOUR_SECURITY_LINK));
			verifyElementIsPresent(driver, Resetlink);
			getwindowandclose(driver, Resetlink);
			wait(driver, "5");
			WebElement ResetLabel= driver.findElement(By.xpath(OR.RESET_YOUR_SECURITY_QUESTION_PLEASE_SELECT_QA_LABEL));
			verifyElementIsPresent(driver, ResetLabel);
			WebElement ResetQaDropdown= driver.findElement(By.xpath(OR.SECURITY_QA_RESET_OPTION_DROPDOWN));
			selectByVisibletext(ResetQaDropdown, "What is the last name of the teacher who gave you your first failing grade?");
			wait(driver, "5");
			WebElement AnswerBox= driver.findElement(By.xpath(OR.SECURITY_QA_ANSWER_BOX));
			sendKeys(AnswerBox, "test");
			jsClickByXPath(driver, OR.SUBMIT_BUTTON_RESET_PASSWORD);
			wait(driver, "3");
			WebElement SuccessMessage= driver.findElement(By.xpath(OR.SECURITY_QA_RESET_PAGE_QA_SUCCESSFULLY_ADDED_MESSAGE));
			verifyElementIsPresent(driver, SuccessMessage);
		}
		else if(mailid.contains("guerrillamail"))
		{
			Navigate.get(driver, Directory.Guerrillamailurl);
			Navigate.maximize(driver);
			wait(driver, "2");
			WebElement Guerrillamaildropdown= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_DROPDOWN));
			selectByValue(Guerrillamaildropdown, "guerrillamail.com");
			WebElement GuerrillaMailEditButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_EDIT_BUTTON));
			click(GuerrillaMailEditButton);
			waitForElement(driver, GUERRILLA_MAIL_TEXTBOX);
			WebElement GuerrillaMailTextbox= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_TEXTBOX));
			clearAndType(GuerrillaMailTextbox, mailid);
			WebElement	 GuerrillaMailSetButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_SET_BUTTON));
			click(GuerrillaMailSetButton);
			wait(driver, "5");
			com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.NOTIFICATION_EMAIL_GUERRILLA);
			wait(driver, "5");
			if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
			{
				driver.get("javascript:document.getElementById('overridelink').click();");
				wait(driver, "5");
			}
			WebElement ResetEmail= driver.findElement(By.xpath(OR.GUERRILLA_RESET_EMAIL));
			verifyElementIsPresent(driver, ResetEmail);
			click(ResetEmail);
			wait(driver, "3");
			WebElement Resetlink= driver.findElement(By.xpath(OR.RESET_YOUR_SECURITY_LINK));
			verifyElementIsPresent(driver, Resetlink);
			getwindowandclose(driver, Resetlink);
			wait(driver, "5");
			WebElement ResetLabel= driver.findElement(By.xpath(OR.RESET_YOUR_SECURITY_QUESTION_PLEASE_SELECT_QA_LABEL));
			verifyElementIsPresent(driver, ResetLabel);
			WebElement ResetQaDropdown= driver.findElement(By.xpath(OR.SECURITY_QA_RESET_OPTION_DROPDOWN));
			selectByVisibletext(ResetQaDropdown, "What is the last name of the teacher who gave you your first failing grade?");
			wait(driver, "5");
			WebElement AnswerBox= driver.findElement(By.xpath(OR.SECURITY_QA_ANSWER_BOX));
			sendKeys(AnswerBox, "test");
			jsClickByXPath(driver, OR.SUBMIT_BUTTON_RESET_PASSWORD);
			wait(driver, "3");
			WebElement SuccessMessage= driver.findElement(By.xpath(OR.SECURITY_QA_RESET_PAGE_QA_SUCCESSFULLY_ADDED_MESSAGE));
			verifyElementIsPresent(driver, SuccessMessage);
			
		}
	}
	/**
	 * Name :     Bharath Kumar. M
	 * Created Date:   19/May/17
	 * Modified Date:
	 * Description : Create a common method to find total number messages diplayed under message subtab for PA	
	 */
	public static String totalNumberOfMessages(WebDriver driver)
	{
		int totalLinkSize2 = driver.findElements(By.xpath(OR.TOTAL_NUMBER_OF_MESSAGES)).size();
		System.out.println("Total No of messages displayed" + totalLinkSize2);
		String count = Integer.toString(totalLinkSize2);
		return count;
	}
	
}