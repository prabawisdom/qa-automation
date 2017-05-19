package com.zillion.qa.realappealmember;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.zillion.qa.commands.Manipulation;
import com.zillion.qa.commands.Navigate;
import com.zillion.qa.config.ChromeBrowser;
import com.zillion.qa.config.FirefoxBrowser;
import com.zillion.qa.config.IEBrowser;
import com.zillion.qa.config.SafariBrowser;

public class RAOneOnOneLiveSession extends Manipulation implements OR
{
	public static String raOneOnOneliveSession(WebDriver driver,String testData) throws Exception
	{
		String result=com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.retrieveAvailableScheduleMember( driver,"02"+","+"Completed" );
		String[] testData1 = testData.split(",");
		String inputBrowser=testData1[0];
		String inputMemberMic=testData1[1];
		String inputMemberVideo=testData1[2];
		String inputCoachChatText=testData1[6];
		String inputMemberChatText=testData1[7];
		String inputCoachSessionCommentTextbox=testData1[8];
		String inputMemberCoachRatingTextbox=testData1[9];

		// Browser launch for Member side
		// Test data seperation from spread sheet
		if ("firefox".equalsIgnoreCase(inputBrowser) ) 
		{
			driver =  new FirefoxBrowser().getDriver();
		}
		else if ( "ie".equalsIgnoreCase(inputBrowser) )
		{
			driver = ( WebDriver ) new IEBrowser().getDriver();
		}
		else if ( "chrome".equalsIgnoreCase(inputBrowser)) 
		{
			driver = new ChromeBrowser().getDriver();
		}
		else if ( "safari".equalsIgnoreCase(inputBrowser)) 
		{
			driver = new SafariBrowser().getDriver();
		}

		// Member Login to schedule 1on1 live session
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.realAppealOneOnOneLiveSessionMemberLogin( driver,result);
		wait(driver, "10");
		WebElement schedule1on1Button = driver.findElement(By.xpath(OR.RA_MEMBERLIVESESSION_SCHEDULE_BUTTON));
		waitForElement( driver, OR.RA_MEMBERLIVESESSION_SCHEDULE_BUTTON );
		verifyElementIsPresent(driver, schedule1on1Button);
		click(schedule1on1Button);
		wait( driver, "10" );
		WebElement timeselectionHeader = driver.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_HEADER));
		WebElement timeselectionTitle = driver.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_TITLE));
		WebElement timezoneDropdown = driver.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_TIMEZONE_DROPDOWN));
		verifyElementIsPresent(driver, timeselectionHeader);
		verifyElementIsPresent(driver, timeselectionTitle);
		verifyElementIsPresent(driver, timezoneDropdown);
		WebElement timeselectionAvailableTimeSlotButton = driver.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_AVAILABLE_BUTTON));
		verifyElementIsPresent(driver, timeselectionAvailableTimeSlotButton);
		actionClick(driver, timeselectionAvailableTimeSlotButton);
		WebElement timeselectionSelectTime = driver.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_AVAILABLE_BUTTON_AVAILABLE_TIME));
		verifyElementIsPresent(driver, timeselectionSelectTime);
		actionClick(driver, timeselectionSelectTime);
		wait( driver, "2" );
		WebElement confirmButton = driver.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_CONFIRM_BUTTON));
		verifyElementIsPresent(driver, confirmButton);
		click(confirmButton);
		wait( driver, "7" );
		waitForElement( driver, OR.REAL_APPEAL_MEMBER_DASHBOARD_TAB );

		// Common method Member to Attend Now URL
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raMemberOneOnOneSessionAppendURL(driver);
		WebElement joinButton = driver.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_ATTEND_SESSION_JOIN_BUTTON));
		verifyElementIsPresent( driver, joinButton );
		click(joinButton);
		wait( driver, "15" );
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.pluginAlertAccept(driver);
		com.zillion.qa.member.liveSessionSubCommonMethods.allowPlugins(driver);
		waitForElement( driver, OR.RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CONNECTED_TEXT );
		WebElement closeButton = driver.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CLOSE_BUTTON));
		verifyElementIsPresent( driver, closeButton );
		click(closeButton);
		wait( driver, "2" );
		WebElement closePopupText = driver.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CLOSE_BUTTON_CLICK_CONFIRM_TEXT));
		WebElement closePopupCancelButton = driver.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CLOSE_BUTTON_CLICK_CANCEL_BUTTON));
		WebElement closePopupOkButton = driver.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CLOSE_BUTTON_CLICK_OK_BUTTON));
		verifyElementIsPresent( driver, closePopupText );
		verifyElementIsPresent( driver, closePopupCancelButton );
		verifyElementIsPresent( driver, closePopupOkButton );
		click(closePopupCancelButton);

		// Verify Welcome Member header, Member Mic&Video button
		WebElement memberChatTextbox = driver.findElement(By.xpath(OR.MEMBER_SESSION_CHAT_TEXTBOX));
		WebElement memberSendButton = driver.findElement(By.xpath(OR.MEMBER_SESSION_CHAT_SEND_BUTTON));
		verifyElementIsPresent(driver, memberChatTextbox);
		verifyElementIsPresent(driver, memberSendButton);
		WebElement memberMicOn = driver.findElement(By.xpath(OR.MEMBER_SESSION_MIC_ON));
		WebElement memberVideoOn = driver.findElement(By.xpath(OR.MEMBER_SESSION_VIDEO_ON));
		verifyElementIsPresent(driver, memberMicOn);
		verifyElementIsPresent(driver, memberVideoOn);

		// Verify Member gear setting option
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.realAppealmemberGearSettings( driver );

		// Coach Live Session in Browser Launch 
		WebDriver driver1 = null;
		String inputBrowser1=testData1[3];

		if ("firefox".equalsIgnoreCase(inputBrowser1) ) 
		{
			driver1 =  new FirefoxBrowser().getDriver();
		}
		else if ( "ie".equalsIgnoreCase(inputBrowser1) )
		{
			driver1 = ( WebDriver ) new IEBrowser().getDriver();
		}
		else if ( "safari".equalsIgnoreCase(inputBrowser)) 
		{
			driver = new SafariBrowser().getDriver();
		}
		wait( driver, "4" );
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raOneOnOneLiveSessionCoachLogin( driver1,result );

		// Append Coach URL to force attend the RA 1on1 Live session
		String getCoachCurrentURL= driver1.getCurrentUrl();
		String appendCoachCurrentURL= getCoachCurrentURL+"?attendnow";
		Navigate.get( driver1, appendCoachCurrentURL );
		waitForElement(driver1, OR.COACH_1ON1_ATTEND_NOW_BUTTON );
		WebElement coachAttendNow = driver1.findElement(By.xpath(OR.COACH_1ON1_ATTEND_NOW_BUTTON));
		verifyElementIsPresent(driver1, coachAttendNow);
		jsClickByXPath(driver1, OR.COACH_1ON1_ATTEND_NOW_BUTTON);
		wait( driver1, "15" );

		// Common method to Allow system Plugins. Click Allow and Allow&Remember
		com.zillion.qa.member.liveSessionSubCommonMethods.allowPlugins(driver);

		// Attend Live session and verify All the fields
		WebElement settingsButton = driver1.findElement(By.xpath(OR.COACH_SESSION_SETTINGS_BUTTON));
		WebElement endSessionButton = driver1.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_BUTTON));
		WebElement coachMicOn = driver1.findElement(By.xpath(OR.COACH_SESSION_MIC_ON));
		WebElement coachVideoOn = driver1.findElement(By.xpath(OR.COACH_SESSION_VIDEO_ON));
		WebElement coachChatTextbox = driver1.findElement(By.xpath(OR.COACH_SESSION_CHAT_TEXTBOX));
		WebElement coachSendButton = driver1.findElement(By.xpath(OR.COACH_SESSION_CHAT_SEND_BUTTON));
		verifyElementIsPresent(driver1, coachMicOn);
		verifyElementIsPresent(driver1, coachVideoOn);
		verifyElementIsPresent(driver1, settingsButton);
		verifyElementIsPresent(driver1, endSessionButton);
		verifyElementIsPresent(driver1, coachChatTextbox);
		verifyElementIsPresent(driver1, coachSendButton);

		// Common method to verify Coach gear setting option
		com.zillion.qa.member.liveSessionSubCommonMethods.coachGearSettings( driver1 );

		// Coach send message to member and verify the sent message in coach chat area
		actionType(driver1,coachChatTextbox,inputCoachChatText);
		wait(driver, "2" );
		actionClick( driver1, coachSendButton );
		wait(driver, "2" );
		Navigate.pageUp( driver );
		WebElement coachAttendToLiveSession = driver1.findElement(By.xpath("//div[@class='chat-message ng-scope']//div[2]/div[contains(text(),'"+inputCoachChatText+"')]"));
		verifyElementIsPresent(driver1, coachAttendToLiveSession);

		// Common method for Coach Mic Enable/Disable
		com.zillion.qa.member.liveSessionSubCommonMethods.coachOneOnOneSessionMicEnableAndDisable(driver1,testData);

		// Common method for Coach Video Enable/Disable
		com.zillion.qa.member.liveSessionSubCommonMethods.coachOneOnOneSessionVideoEnableAndDisable(driver1,testData);

		// Common method to get the Session ID from the current URL
		com.zillion.qa.member.liveSessionSubCommonMethods.getCurrentURL( driver1 );
		wait( driver, "10" );

		// Switching  Coach to Member. Member verifies coach message in member chat area 
		// Member send message to Coach and verify the sent message in Member chat area
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "5");
		driver.manage().window().maximize();
		wait(driver, "4");
		WebElement memberWelcomeToLiveSession = driver.findElement(By.xpath("//div[@class='chat-message ng-scope']//div[2]/div[contains(text(),'"+inputCoachChatText+"')]"));
		verifyElementIsPresent(driver, memberWelcomeToLiveSession);
		actionType(driver,memberChatTextbox,inputMemberChatText);
		wait( driver, "2" );
		actionClick( driver, memberSendButton );
		wait( driver, "2" );
		Navigate.pageUp( driver );
		WebElement memberAttendToLiveSession = driver.findElement(By.xpath("//div[@class='chat-message ng-scope']//div[2]/div[contains(text(),'"+inputMemberChatText+"')]"));
		verifyElementIsPresent(driver, memberAttendToLiveSession);

		// Common method for Member Mic Enable/Disable
		com.zillion.qa.member.liveSessionSubCommonMethods.memberOneonOneSessionMicEnableAndDisable(driver,testData);

		// Common method for Member Video Enable/Disable
		com.zillion.qa.member.liveSessionSubCommonMethods.memberOneonOneSessionVideoEnableAndDisable(driver,testData);
		wait(driver, "3");

		// Switching  Member to Coach
		//Coach verifies Member message in chat area
		//Coach verifies Member Mic Enable/Disable and  Member Video Enable/Disable
		driver.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "5");
		driver1.manage().window().setPosition(new Point(0, 0));
		wait(driver, "4");
		WebElement coachWelcomeToLiveSession = driver1.findElement(By.xpath("//div[@class='chat-message ng-scope']//div[2]/div[contains(text(),'"+inputMemberChatText+"')]"));
		verifyElementIsPresent(driver1, coachWelcomeToLiveSession);

		if ("MemberMicDisable".equalsIgnoreCase(inputMemberMic))  
		{
			WebElement micDisabled = driver1.findElement(By.xpath(OR.COACH_SESSION_MIC_DISABLED));
			verifyElementIsPresent(driver1, micDisabled);
		}
		else if ( "MemberMicEnable".equalsIgnoreCase(inputMemberMic) )
		{
			WebElement micEnabled = driver1.findElement(By.xpath(OR.COACH_SESSION_MIC_ENABLED));
			verifyElementIsPresent(driver1, micEnabled);
		}

		if ("MemberVideoEnable".equalsIgnoreCase(inputMemberVideo) )  
		{
			WebElement cameraEnabled = driver1.findElement(By.xpath(OR.COACH_SESSION_CAMERA_ENABLED));
			verifyElementIsPresent(driver1, cameraEnabled);
		}
		else if ( "MemberVideoDisable".equalsIgnoreCase(inputMemberVideo) )
		{
			WebElement cameraDisabled = driver1.findElement(By.xpath(OR.COACH_SESSION_CAMERA_DISABLED));
			verifyElementIsPresent(driver1, cameraDisabled);
		}

		// Check Browser Icon Coach Toolbar compare with Input member Browser
		if ("firefox".equalsIgnoreCase(inputBrowser) )  
		{
			WebElement firefoxBrowserIcon = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_FIREFOX_BROWSER_ICON));
			verifyElementIsPresent(driver1, firefoxBrowserIcon);
			jsClickByXPath(driver1, OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_FIREFOX_BROWSER_ICON);
			wait( driver1, "3" );
		}
		else if ( "ie".equalsIgnoreCase(inputBrowser) )
		{
			WebElement ieBrowserIcon = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_IE_BROWSER_ICON));
			verifyElementIsPresent(driver1, ieBrowserIcon);
			jsClickByXPath(driver1, OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_IE_BROWSER_ICON);
			wait( driver1, "3" );
		}
		WebElement memberAccountID = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_MEMBER_ID));
		WebElement popUpOkButton = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_OK_BUTTON));
		WebElement memberIDHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_MEMBER_ID_HEADER));
		WebElement sessionIDHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_SESSION_ID_HEADER));
		WebElement userAgentHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_USER_AGENT_HEADER));
		WebElement versionHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_LIVESESSION_VERSION_HEADER));
		WebElement micOptionHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_MICROPHONE_OPTIONS_HEADER));
		WebElement micSelectedHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_MICROPHONE_SELECTED_HEADER));
		WebElement speakerOptionHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_SPEAKER_OPTIONS_HEADER));
		WebElement speakerSelectedHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_SPEAKER_SELECTED_HEADER));
		WebElement cameraOptionHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_CAMERA_OPTIONS_HEADER));
		WebElement cameraSelectedHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_CAMERA_SELECTED_HEADER));
		verifyElementIsPresent(driver1, memberAccountID);
		verifyElementIsPresent(driver1, popUpOkButton);
		verifyElementIsPresent(driver1, memberIDHeader);
		verifyElementIsPresent(driver1, sessionIDHeader);
		verifyElementIsPresent(driver1, userAgentHeader);
		verifyElementIsPresent(driver1, versionHeader);
		verifyElementIsPresent(driver1, micOptionHeader);
		verifyElementIsPresent(driver1, micSelectedHeader);
		verifyElementIsPresent(driver1, speakerOptionHeader);
		verifyElementIsPresent(driver1, speakerSelectedHeader);
		verifyElementIsPresent(driver1, cameraOptionHeader);
		verifyElementIsPresent(driver1, cameraSelectedHeader);
		com.zillion.qa.member.liveSessionSubCommonMethods.compareAccountIDWithBrowserToolBarIcon(driver1);
		jsClickByXPath(driver1, OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_OK_BUTTON);
		wait( driver1, "3" );

		// Coach Ends session
		// Coach send Session comments after End session
		// Comments given in Input data in spread sheet
		jsClickByXPath(driver1, OR.COACH_SESSION_END_SESSION_BUTTON);
		wait(driver, "2");
		WebElement coachSessionEndText = driver1.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_TEXT));
		WebElement coachSessionYesButton = driver1.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_YES_BUTTON));
		WebElement coachSessionNoButton = driver1.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_NO_BUTTON));
		verifyElementIsPresent(driver1, coachSessionEndText);
		verifyElementIsPresent(driver1, coachSessionYesButton);
		verifyElementIsPresent(driver1, coachSessionNoButton);
		jsClickByXPath(driver1, OR.COACH_SESSION_END_SESSION_YES_BUTTON );
		wait(driver, "3" );
		waitForElement(driver1, OR.COACH_SESSION_SESSION_COMPLETE_HEADER_TEXT );
		WebElement coachSessionComplete = driver1.findElement(By.xpath(OR.COACH_SESSION_SESSION_COMPLETE_HEADER_TEXT));
		WebElement coach1on1SessionEndedText = driver1.findElement(By.xpath(OR.COACH_SESSION_YOUR_1ON1_SESSION_ENDED_TEXT));
		WebElement coachSessionCommentTextbox = driver1.findElement(By.xpath(OR.COACH_1ON1_COMMENTS_TEXTBOX));
		WebElement coachSessionCompletedRadio = driver1.findElement(By.xpath(OR.COACH_SESSION_SESSION_COMPLETED_RADIO_BUTTON));
		WebElement coachMemberDidNotAttendRadio = driver1.findElement(By.xpath(OR.COACH_SESSION_MEMBER_DID_NOT_ATTEND_RADIO_BUTTON));
		WebElement coachSessionCompleteText = driver1.findElement(By.xpath(OR.COACH_SESSION_SESSION_COMPLETED_TEXT));
		WebElement coachMemberDidNotAttendText = driver1.findElement(By.xpath(OR.COACH_SESSION_MEMBER_DID_NOT_ATTEND_TEXT));
		WebElement coachEndSessionDoneButton = driver1.findElement(By.xpath(OR.COACH_SESSION_MEMBER_DONE_BUTTON));
		verifyElementIsPresent(driver1, coachSessionComplete);
		verifyElementIsPresent(driver1, coach1on1SessionEndedText);
		verifyElementIsPresent(driver1, coachSessionCommentTextbox);
		verifyElementIsPresent(driver1, coachSessionCompletedRadio);
		verifyElementIsPresent(driver1, coachMemberDidNotAttendRadio);
		verifyElementIsPresent(driver1, coachSessionCompleteText);
		verifyElementIsPresent(driver1, coachMemberDidNotAttendText);
		verifyElementIsPresent(driver1, coachEndSessionDoneButton);
		jsClickByXPath(driver1, OR.COACH_1ON1_COMMENTS_TEXTBOX);
		sendKeys(coachSessionCommentTextbox,inputCoachSessionCommentTextbox);
		wait( driver, "2" );
		jsClickByXPath(driver1, OR.COACH_SESSION_SESSION_COMPLETED_RADIO_BUTTON);
		jsClickByXPath(driver1, OR.COACH_SESSION_MEMBER_DONE_BUTTON);
		wait( driver, "3" );

		// Switching  Coach to Member
		// Member gives ratings to Coach
		// Gives comments in Coach Rating textbox
		// Comments given in Input data in spread sheet
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "4" );
		driver.manage().window().setPosition(new Point(0, 0));
		wait(driver, "4");
		WebElement memberCoachRatingHeader = driver.findElement(By.xpath(OR.MEMBER_COACH_RATING_HEADER_TEXT));
		WebElement memberCoachStar1 = driver.findElement(By.xpath(OR.MEMBER_COACH_STAR1));
		WebElement memberCoachStar2 = driver.findElement(By.xpath(OR.MEMBER_COACH_STAR2));
		WebElement memberCoachStar3 = driver.findElement(By.xpath(OR.MEMBER_COACH_STAR3));
		WebElement memberCoachStar4 = driver.findElement(By.xpath(OR.MEMBER_COACH_STAR4));
		WebElement memberCoachStar5 = driver.findElement(By.xpath(OR.MEMBER_COACH_STAR5));
		WebElement memberCoachRatingTextbox = driver.findElement(By.xpath(OR.MEMBER_COACH_RATING_TEXTBOX));
		WebElement memberCoachRatingSubmitButton = driver.findElement(By.xpath(OR.MEMBER_COACH_RATING_SUBMIT_BUTTON));
		WebElement memberCoachRatingCloseButton = driver.findElement(By.xpath(OR.MEMBER_COACH_RATING_CLOSE_BUTTON));
		verifyElementIsPresent(driver, memberCoachRatingHeader);
		verifyElementIsPresent(driver, memberCoachStar1);
		verifyElementIsPresent(driver, memberCoachStar2);
		verifyElementIsPresent(driver, memberCoachStar3);
		verifyElementIsPresent(driver, memberCoachStar4);
		verifyElementIsPresent(driver, memberCoachStar5);
		verifyElementIsPresent(driver, memberCoachRatingTextbox);
		verifyElementIsPresent(driver, memberCoachRatingSubmitButton);
		verifyElementIsPresent(driver, memberCoachRatingCloseButton);
		click(memberCoachStar1);
		click(memberCoachStar2);
		click(memberCoachStar3);
		click(memberCoachStar4);
		click(memberCoachStar5);
		wait(driver, "2" );
		jsClickByXPath(driver, OR.MEMBER_COACH_RATING_TEXTBOX);
		sendKeys(memberCoachRatingTextbox,inputMemberCoachRatingTextbox);
		click(memberCoachRatingSubmitButton);
		wait(driver, "5" );

		// Common method to retrieve Session Attendance status from DB with session ID
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.retrive1on1SessionAttendanceStatusWithSessionID( driver,result);

		// Common method to retrieve Member Account ID
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.retrieveMemberAccountId( driver,result);

		// Common method to retrieve Session rating stars and with given stars in script
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.sessionratingStars( driver);

		// Common method to retrieve Coach rating notes from DB and compare with given input from spread sheet
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.ratingNotesCoachSide( driver, testData);

		// Common method to retrieve Member rating notes from DB and compare with given input from spread sheet
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.ratingNotesMemberSide( driver, testData);

		// Driver Close
		driver.close();
		driver1.close();     
		return testData;
	}
	
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   07/MAR/17
	 * Modified Date:   
	 * Description : RA- 10n1 ZLive session for different browser
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */

	public static void ra1on1ZLiveSession(WebDriver driver,String testData, String coachEmail1, String memberEmail1) throws Exception
	{
		String[] testData1 = testData.split(",");
		String memberBrowser=testData1[0];
		String coachBrowser=testData1[1];
		String coachChattext=testData1[2];
		String coachMicEnableOrDisabled=testData1[3];
		String coachVideoEnableOrDisabled=testData1[4];
		String memberChattext=testData1[5];
		String MemberMicEnableOrDisabled=testData1[6];
		String MemberVideoEnabledOrDisabled=testData1[7];



		// Coach Login in Browser Launch 
		WebDriver driver1 = null;

		if ("firefox".equalsIgnoreCase(memberBrowser) ) 
		{
			driver =  new FirefoxBrowser().getDriver();
			driver.manage().window().maximize();
		}
		else if ( "ie".equalsIgnoreCase(memberBrowser) )
		{
			driver = ( WebDriver ) new IEBrowser().getDriver();
			driver.manage().window().maximize();
		}
		else if ( "safari".equalsIgnoreCase(memberBrowser)) 
		{
			driver = new SafariBrowser().getDriver();
			driver.manage().window().maximize();
		}
		else if ( "chrome".equalsIgnoreCase(memberBrowser)) 
		{
			driver = new ChromeBrowser().getDriver();
			driver.manage().window().maximize();
		}
		wait( driver, "4" );

		// Member Login

		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raGroupZLiveSessionMemberLogin(driver, memberEmail1);

		// Open robot for default
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.openRobotDefault( driver);

		// To handle tracker server unavailable popup after Login
		com.zillion.qa.realappealmember.member.handleTrackerServerUnavailable( driver);

		// To handle browser support popup
		com.zillion.qa.realappealmember.member.browserSupportMatrixRA( driver );

		// Append URL for member force attand the session
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raMemberOneOnOneSessionAppendURL(driver);
		wait( driver, "4" );
		waitForElement(driver, RA_1ON1_LIVE_SESSION_MEMBER_ATTEND_SESSION_JOIN_BUTTON);
		WebElement memberAttendnowButton = driver.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_ATTEND_SESSION_JOIN_BUTTON));
		click(memberAttendnowButton);
		wait( driver, "3" );

		//Enter member weight before attending session
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raEnterWeightDuringLiveSessionDifferentBrowser( driver );
		wait( driver, "5" );

		// Allow plugin for the Zlive 
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.allowGroupZLivePluginDifferentBrowser(driver);
		wait( driver, "3" );
		waitForElement(driver, RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CONNECTED_TEXT);
		WebElement sessionCloseButton = driver.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CLOSE_BUTTON));
		verifyElementIsPresent(driver, sessionCloseButton);
		click(sessionCloseButton);
		wait(driver, "2");
		waitForElement(driver, RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CLOSE_BUTTON_CLICK_CONFIRM_TEXT);
		WebElement sessionCancelButton = driver.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CLOSE_BUTTON_CLICK_CANCEL_BUTTON));
		WebElement sessionCloseOkButton = driver.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CLOSE_BUTTON_CLICK_OK_BUTTON));
		verifyElementIsPresent(driver, sessionCancelButton);
		verifyElementIsPresent(driver, sessionCloseOkButton);
		click(sessionCancelButton);
		wait(driver, "2");
		WebElement memberChatTextbox = driver.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CHAT_TEXTBOX));
		WebElement memberChatSendButton = driver.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CHAT_SEND_BUTTON));
		verifyElementIsPresent(driver, memberChatTextbox);
		verifyElementIsPresent(driver, memberChatSendButton);
		WebElement memberMicOn = driver.findElement(By.xpath(OR.MEMBER_SESSION_MIC_ON));
		WebElement memberVideoOn = driver.findElement(By.xpath(OR.MEMBER_SESSION_VIDEO_ON));
		verifyElementIsPresent(driver, memberMicOn);
		verifyElementIsPresent(driver, memberVideoOn);
		
		String sessiontime1=null;
		WebElement memberSessionTime = driver.findElement(By.xpath(OR.RA_MEMBER_SESSION_TIME));
		String sessionsTime=memberSessionTime.getText();
		SimpleDateFormat ft = new SimpleDateFormat("EEE hhh:mm a");
		java.util.Date t=ft.parse(sessionsTime);
		ft.applyPattern("hh:mma");
		sessiontime1=ft.format(t).toLowerCase().trim();
		System.out.print(ft.format(t));
		System.out.print("Time"+sessiontime1);


		// Verify member gear settings
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.realAppealmemberGearSettings( driver );

		// Browser launch for Coach side
		// Test data seperation from spread sheet
		if ("firefox".equalsIgnoreCase(coachBrowser) ) 
		{
			driver1 =  new FirefoxBrowser().getDriver();
			driver1.manage().window().maximize();
		}
		else if ( "ie".equalsIgnoreCase(coachBrowser) )
		{
			driver1 = ( WebDriver ) new IEBrowser().getDriver();
			driver1.manage().window().maximize();
		}
		else if ( "chrome".equalsIgnoreCase(coachBrowser)) 
		{
			driver1 = new ChromeBrowser().getDriver();
			driver1.manage().window().maximize();
		}
		else if ( "safari".equalsIgnoreCase(coachBrowser)) 
		{
			driver1 = new SafariBrowser().getDriver();
			driver1.manage().window().maximize();
		}

		// Coach Login with different browser
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.coachLoginWithReferenceStepDifferentBrowser(driver1, coachEmail1);
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raCoachOneOnOneSessionAppendURLDifferentBrowser(driver1);
		wait(driver1, "3");
		WebElement coachUpcomingSessionTime = driver1.findElement(By.xpath("//tr//td[text()='"+sessiontime1+"']/following::td[text()='1on1']/following-sibling::td//following-sibling::td//following-sibling::td/following-sibling::td/div/a"));
		verifyElementIsPresent(driver1, coachUpcomingSessionTime); 
		click(coachUpcomingSessionTime);
		wait(driver1, "7");

		// share selected device for the coach Zlivesession
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.shareSelectedDeviceDifferentBrowser(driver1);
		wait(driver1, "4");
		WebElement coachSettingsButton = driver1.findElement(By.xpath(OR.COACH_SESSION_SETTINGS_BUTTON));
		WebElement coachEndSessionButton = driver1.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_BUTTON));
		WebElement coachMicOn = driver1.findElement(By.xpath(OR.COACH_SESSION_MIC_ON));
		WebElement coachVideoOn = driver1.findElement(By.xpath(OR.COACH_SESSION_VIDEO_ON));
		WebElement coachChatTextBox = driver1.findElement(By.xpath(OR.COACH_SESSION_CHAT_TEXTBOX));
		WebElement cochChatSendButton = driver1.findElement(By.xpath(OR.COACH_SESSION_CHAT_SEND_BUTTON));
		verifyElementIsPresent(driver1, coachSettingsButton);
		verifyElementIsPresent(driver1, coachEndSessionButton);
		verifyElementIsPresent(driver1, coachMicOn);
		verifyElementIsPresent(driver1, coachVideoOn);
		verifyElementIsPresent(driver1, coachChatTextBox);
		verifyElementIsPresent(driver1, cochChatSendButton);

		// Verify coach gear settings
		com.zillion.qa.member.liveSessionSubCommonMethods.coachGearSettings(driver1);

		actionType(driver1, coachChatTextBox, coachChattext);
		wait(driver1, "1");
		actionClick(driver1, cochChatSendButton);
		wait(driver1, "2");
		WebElement coachChatTextToMember = driver1.findElement(By.xpath("//div[@class='chat-message ng-scope']//div[2]/div[contains(text(),'"+coachChattext+"')]"));
		verifyElementIsPresent(driver1, coachChatTextToMember);

		if ("CoachMicEnable".equalsIgnoreCase(coachMicEnableOrDisabled) ) 
		{ 
			try
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_MIC_OFF);
				wait(driver1, "2");
				verifyElementIsPresent(driver1, coachMicOn);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_MIC_ON);
				wait(driver1, "2");
				jsClickByXPath(driver1, OR.COACH_SESSION_MIC_OFF);
				wait(driver1, "2");
				verifyElementIsPresent(driver1, coachMicOn);
			}
		}
		else if ( "CoachMicDisable".equalsIgnoreCase(coachMicEnableOrDisabled) )
		{
			try
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_MIC_ON);
				wait(driver1, "2");
				WebElement coachMicOff = driver1.findElement(By.xpath(OR.COACH_SESSION_MIC_OFF));
				verifyElementIsPresent(driver1, coachMicOff);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_MIC_OFF);
				wait(driver1, "2");
				jsClickByXPath(driver1, OR.COACH_SESSION_MIC_ON);
				WebElement coachMicOff = driver1.findElement(By.xpath(OR.COACH_SESSION_MIC_OFF));
				wait(driver1, "2");
				verifyElementIsPresent(driver1, coachMicOff);
			}
		}
		if ("CoachVideoEnable".equalsIgnoreCase(coachVideoEnableOrDisabled) ) 
		{ 
			try
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_VIDEO_OFF);
				wait(driver1, "2");
				verifyElementIsPresent(driver1, coachVideoOn);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_VIDEO_ON);
				wait(driver1, "2");
				jsClickByXPath(driver1, OR.COACH_SESSION_VIDEO_OFF);
				wait(driver1, "2");
				verifyElementIsPresent(driver1, coachVideoOn);
			}

		}
		else if ( "CoachVideoDisable".equalsIgnoreCase(coachVideoEnableOrDisabled) )
		{
			try
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_VIDEO_ON);
				wait(driver1, "2");
				WebElement coachVideoOff = driver1.findElement(By.xpath(OR.COACH_SESSION_VIDEO_OFF));
				verifyElementIsPresent(driver1, coachVideoOff);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_VIDEO_OFF);
				wait(driver1, "2");
				jsClickByXPath(driver1, OR.COACH_SESSION_VIDEO_ON);
				WebElement coachVideoOff = driver1.findElement(By.xpath(OR.COACH_SESSION_VIDEO_OFF));
				wait(driver1, "2");
				verifyElementIsPresent(driver1, coachVideoOff);
			}
		}

		// Minimize the coach browser
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver1);

		// Switch Member to coach
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver1, "8");
		driver.manage().window().maximize();
		wait(driver, "10");

		actionType(driver, memberChatTextbox, memberChattext);
		wait(driver, "1");
		actionClick(driver, memberChatSendButton);		
		wait(driver, "2");
		WebElement memberAttendToLiveSession = driver.findElement(By.xpath("//div[@class='chat-message ng-scope']//div[2]/div[contains(text(),'"+memberChattext+"')]"));
		verifyElementIsPresent(driver, memberAttendToLiveSession);
		WebElement memberVerifyChatTextFromCoach = driver.findElement(By.xpath("//div[@class='chat-message ng-scope']//div[2]/div[contains(text(),'"+coachChattext+"')]"));
		verifyElementIsPresent(driver, memberVerifyChatTextFromCoach);

		if ("MemberMicEnable".equalsIgnoreCase(MemberMicEnableOrDisabled) ) 
		{ 
			try
			{
				jsClickByXPath(driver, OR.MEMBER_SESSION_MIC_OFF);
				wait(driver, "2");

				verifyElementIsPresent(driver, memberMicOn);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver, OR.MEMBER_SESSION_MIC_ON);
				wait(driver, "2");
				jsClickByXPath(driver, OR.MEMBER_SESSION_MIC_OFF);
				wait(driver, "2");
				verifyElementIsPresent(driver, memberMicOn);
			}
		}

		else if ( "MemberMicDisable".equalsIgnoreCase(MemberMicEnableOrDisabled) )
		{
			try
			{
				jsClickByXPath(driver, OR.MEMBER_SESSION_MIC_ON);
				WebElement memberMicOff = driver.findElement(By.xpath(OR.MEMBER_SESSION_MIC_OFF));
				verifyElementIsPresent(driver, memberMicOff);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver, OR.MEMBER_SESSION_MIC_OFF);
				wait(driver, "2");
				jsClickByXPath(driver, OR.MEMBER_SESSION_MIC_ON);
				wait(driver, "2");
				WebElement memberMicOff = driver.findElement(By.xpath(OR.MEMBER_SESSION_MIC_OFF));
				verifyElementIsPresent(driver, memberMicOff);
			}
		}

		if ("MemberVideoEnable".equalsIgnoreCase(MemberVideoEnabledOrDisabled) ) 
		{ 
			try
			{
				jsClickByXPath(driver, OR.MEMBER_SESSION_VIDEO_OFF);
				wait(driver, "2");
				verifyElementIsPresent(driver, memberVideoOn);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver, OR.MEMBER_SESSION_VIDEO_ON);
				wait(driver, "2");
				jsClickByXPath(driver, OR.MEMBER_SESSION_VIDEO_OFF);
				wait(driver, "2");
				verifyElementIsPresent(driver, memberVideoOn);
			}

		}
		else if ( "MemberVideoDisable".equalsIgnoreCase(MemberVideoEnabledOrDisabled) )
		{
			try
			{
				jsClickByXPath(driver, OR.MEMBER_SESSION_VIDEO_ON);
				wait(driver, "2");
				WebElement memberVideoOff = driver.findElement(By.xpath(OR.MEMBER_SESSION_VIDEO_OFF));
				verifyElementIsPresent(driver, memberVideoOff);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver, OR.MEMBER_SESSION_VIDEO_OFF);
				wait(driver, "2");
				jsClickByXPath(driver, OR.MEMBER_SESSION_VIDEO_ON);
				wait(driver, "2");
				WebElement memberVideoOff = driver.findElement(By.xpath(OR.MEMBER_SESSION_VIDEO_OFF));
				verifyElementIsPresent(driver, memberVideoOff);
			}
		}

		// Switch Member to coach
		driver.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "8");
		driver1.manage().window().maximize();
		wait(driver1, "10");

		WebElement coachVerifyChatTextFromMember = driver1.findElement(By.xpath("//div[@class='chat-message ng-scope']//div[2]/div[contains(text(),'"+memberChattext+"')]"));
		verifyElementIsPresent(driver1, coachVerifyChatTextFromMember);

		if ("MemberMicDisable".equalsIgnoreCase(MemberMicEnableOrDisabled))  
		{
			WebElement micDisabled = driver1.findElement(By.xpath(OR.COACH_SESSION_MIC_DISABLED));
			verifyElementIsPresent(driver1, micDisabled);
		}
		else if ( "MemberMicEnable".equalsIgnoreCase(MemberMicEnableOrDisabled) )
		{
			WebElement micEnabled = driver1.findElement(By.xpath(OR.COACH_SESSION_MIC_ENABLED));
			verifyElementIsPresent(driver1, micEnabled);
		}

		if ("MemberVideoEnable".equalsIgnoreCase(MemberVideoEnabledOrDisabled) )  
		{
			WebElement cameraEnabled = driver1.findElement(By.xpath(OR.COACH_SESSION_CAMERA_ENABLED));
			verifyElementIsPresent(driver1, cameraEnabled);
		}
		else if ( "MemberVideoDisable".equalsIgnoreCase(MemberVideoEnabledOrDisabled) )
		{
			WebElement cameraDisabled = driver1.findElement(By.xpath(OR.COACH_SESSION_CAMERA_DISABLED));
			verifyElementIsPresent(driver1, cameraDisabled);
		}

		if (memberBrowser.equalsIgnoreCase( "firefox" ))  
		{
			WebElement firefoxBrowserIcon = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_FIREFOX_BROWSER_ICON));
			verifyElementIsPresent(driver1, firefoxBrowserIcon);
			jsClickByXPath(driver1, OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_FIREFOX_BROWSER_ICON);
			wait( driver1, "3" );
		}
		else if (memberBrowser.equalsIgnoreCase( "ie" ))
		{
			WebElement ieBrowserIcon = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_IE_BROWSER_ICON));
			verifyElementIsPresent(driver1, ieBrowserIcon);
			jsClickByXPath(driver1, OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_IE_BROWSER_ICON);
			wait( driver1, "3" );
		}
		else if (memberBrowser.equalsIgnoreCase( "chrome" ))
		{
			WebElement ieBrowserIcon = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_CHROME_BROWSER_ICON));
			verifyElementIsPresent(driver1, ieBrowserIcon);
			jsClickByXPath(driver1, OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_CHROME_BROWSER_ICON);
			wait( driver1, "3" );
		}

		wait(driver1, "4");
		WebElement memberID = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_MEMBER_ID));
		WebElement okButton = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_OK_BUTTON));
		WebElement memberIDHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_MEMBER_ID_HEADER));
		WebElement sessionIdHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_SESSION_ID_HEADER));
		WebElement userAgentHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_USER_AGENT_HEADER));
		WebElement versionHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_LIVESESSION_VERSION_HEADER));
		WebElement micOptionHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_MICROPHONE_OPTIONS_HEADER));
		WebElement micSelectedHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_MICROPHONE_SELECTED_HEADER));
		WebElement speakerOptionHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_SPEAKER_OPTIONS_HEADER));
		WebElement speakerSelectedHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_SPEAKER_SELECTED_HEADER));
		WebElement cameraOptionHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_CAMERA_OPTIONS_HEADER));
		WebElement cameraselectedHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_CAMERA_SELECTED_HEADER));
		verifyElementIsPresent(driver1, memberID);
		verifyElementIsPresent(driver1, okButton);
		verifyElementIsPresent(driver1, memberIDHeader);
		verifyElementIsPresent(driver1, sessionIdHeader);
		verifyElementIsPresent(driver1, userAgentHeader);
		verifyElementIsPresent(driver1, versionHeader);
		verifyElementIsPresent(driver1, micOptionHeader);
		verifyElementIsPresent(driver1, micSelectedHeader);
		verifyElementIsPresent(driver1, speakerOptionHeader);
		verifyElementIsPresent(driver1, speakerSelectedHeader);
		verifyElementIsPresent(driver1, cameraOptionHeader);
		verifyElementIsPresent(driver1, cameraselectedHeader);
		click(okButton);
		wait(driver1, "2");
		click(coachEndSessionButton);
		wait(driver1, "2");
		WebElement endSessionText = driver1.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_TEXT));
		WebElement endSessionYesButton = driver1.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_YES_BUTTON));
		WebElement endSessionNoButton = driver1.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_NO_BUTTON));
		verifyElementIsPresent(driver1, endSessionText);
		verifyElementIsPresent(driver1, endSessionYesButton);
		verifyElementIsPresent(driver1, endSessionNoButton);
		click(endSessionYesButton);
		wait(driver1, "3");
		waitForElement(driver1, COACH_SESSION_SESSION_COMPLETE_HEADER_TEXT);
		WebElement coachSessionCompletedHeader = driver1.findElement(By.xpath(OR.COACH_SESSION_SESSION_COMPLETE_HEADER_TEXT));
		WebElement sessionEndedText = driver1.findElement(By.xpath(OR.COACH_SESSION_YOUR_1ON1_SESSION_ENDED_TEXT));
		WebElement commentsTextbox = driver1.findElement(By.xpath(OR.COACH_1ON1_COMMENTS_TEXTBOX));
		WebElement completedRadioButton = driver1.findElement(By.xpath(OR.COACH_SESSION_SESSION_COMPLETED_RADIO_BUTTON));
		WebElement memberDidNotAttendRadioButton = driver1.findElement(By.xpath(OR.COACH_SESSION_MEMBER_DID_NOT_ATTEND_RADIO_BUTTON));
		WebElement SessionCompletedText = driver1.findElement(By.xpath(OR.COACH_SESSION_SESSION_COMPLETED_TEXT));
		WebElement MemberDidNotAttendText = driver1.findElement(By.xpath(OR.COACH_SESSION_MEMBER_DID_NOT_ATTEND_TEXT));
		WebElement DoneButton = driver1.findElement(By.xpath(OR.COACH_SESSION_1ON1_MEMBER_DONE_BUTTON));
		verifyElementIsPresent(driver1, coachSessionCompletedHeader);
		verifyElementIsPresent(driver1, sessionEndedText);
		verifyElementIsPresent(driver1, commentsTextbox);
		verifyElementIsPresent(driver1, completedRadioButton);
		verifyElementIsPresent(driver1, memberDidNotAttendRadioButton);
		verifyElementIsPresent(driver1, SessionCompletedText);
		verifyElementIsPresent(driver1, MemberDidNotAttendText);
		verifyElementIsPresent(driver1, DoneButton);
		click(commentsTextbox);
		sendKeys(commentsTextbox, "Session Completed");
		click(completedRadioButton);
		wait(driver1, "1");
		click(DoneButton);
		wait(driver1, "2");

		//RealAppeal coach Logout
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.realAppealCoachLogout(driver1);

		// Minimize the coach browser
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver1);


		// Switch Coach to member
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver1, "8");
		driver.manage().window().maximize();
		wait(driver, "10");

		WebElement ratingStar1 = driver.findElement(By.xpath(OR.RA_GROUP_SESSION_COACH_RATING_STAR_1));
		WebElement ratingStar2 = driver.findElement(By.xpath(OR.RA_GROUP_SESSION_COACH_RATING_STAR_2));
		WebElement ratingStar3 = driver.findElement(By.xpath(OR.RA_GROUP_SESSION_COACH_RATING_STAR_3));
		WebElement ratingStar4 = driver.findElement(By.xpath(OR.RA_GROUP_SESSION_COACH_RATING_STAR_4));
		WebElement ratingStar5 = driver.findElement(By.xpath(OR.RA_GROUP_SESSION_COACH_RATING_STAR_5));
		WebElement ratingTextbox = driver.findElement(By.xpath(OR.MEMBER_COACH_RATING_TEXTBOX));
		WebElement ratingSubmitButton = driver.findElement(By.xpath(OR.MEMBER_COACH_RATING_SUBMIT_BUTTON));
		verifyElementIsPresent(driver, ratingStar1);
		verifyElementIsPresent(driver, ratingStar2);
		verifyElementIsPresent(driver, ratingStar3);
		verifyElementIsPresent(driver, ratingStar4);
		verifyElementIsPresent(driver, ratingStar5);
		verifyElementIsPresent(driver, ratingTextbox);
		verifyElementIsPresent(driver, ratingSubmitButton);
		click(ratingStar1);
		click(ratingStar2);
		click(ratingStar3);
		click(ratingStar4);
		click(ratingStar5);
		click(ratingTextbox);
		sendKeys(ratingTextbox, "Session completed with coach");
		click(ratingSubmitButton);
		wait(driver, "4");
		com.zillion.qa.realappealmember.member.realAppealMemberlogout(driver);
		driver.close();
		driver1.close();
	}
	
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   07/MAR/17
	 * Modified Date:   
	 * Description : RA- 10n1 ZLive session for different browser
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */

	public static void ra1on1AddLiveSession(WebDriver driver,String testData, String coachEmail1, String memberEmail1) throws Exception
	{
		String[] testData1 = testData.split(",");
		String memberBrowser=testData1[0];
		String coachBrowser=testData1[1];
		String coachChattext=testData1[2];
		String coachMicEnableOrDisabled=testData1[3];
		String coachVideoEnableOrDisabled=testData1[4];
		String memberChattext=testData1[5];
		String MemberMicEnableOrDisabled=testData1[6];
		String MemberVideoEnabledOrDisabled=testData1[7];



		// Coach Login in Browser Launch 
		WebDriver driver1 = null;

		if ("firefox".equalsIgnoreCase(memberBrowser) ) 
		{
			driver =  new FirefoxBrowser().getDriver();
			driver.manage().window().maximize();
		}
		else if ( "ie".equalsIgnoreCase(memberBrowser) )
		{
			driver = ( WebDriver ) new IEBrowser().getDriver();
			driver.manage().window().maximize();
		}
		else if ( "safari".equalsIgnoreCase(memberBrowser)) 
		{
			driver = new SafariBrowser().getDriver();
			driver.manage().window().maximize();
		}
		else if ( "chrome".equalsIgnoreCase(memberBrowser)) 
		{
			driver = new ChromeBrowser().getDriver();
			driver.manage().window().maximize();
		}
		wait( driver, "4" );

		// Member Login

		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raGroupZLiveSessionMemberLogin(driver, memberEmail1);

		// Open robot for default
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.openRobotDefault( driver);

		// To handle tracker server unavailable popup after Login
		com.zillion.qa.realappealmember.member.handleTrackerServerUnavailable( driver);

		// To handle browser support popup
		com.zillion.qa.realappealmember.member.browserSupportMatrixRA( driver );

		// Append URL for member force attand the session
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raMemberOneOnOneSessionAppendURL(driver);
		wait( driver, "4" );
		waitForElement(driver, RA_1ON1_LIVE_SESSION_MEMBER_ATTEND_SESSION_JOIN_BUTTON);
		WebElement memberAttendnowButton = driver.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_ATTEND_SESSION_JOIN_BUTTON));
		click(memberAttendnowButton);
		wait( driver, "3" );

		//Enter member weight before attending session
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raEnterWeightDuringLiveSessionDifferentBrowser( driver );
		wait( driver, "5" );

		// Allow plugin for the Zlive 
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.allowGroupZLivePluginDifferentBrowser(driver);
		wait( driver, "3" );
		waitForElement(driver, RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CONNECTED_TEXT);
		WebElement sessionCloseButton = driver.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CLOSE_BUTTON));
		verifyElementIsPresent(driver, sessionCloseButton);
		click(sessionCloseButton);
		wait(driver, "2");
		waitForElement(driver, RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CLOSE_BUTTON_CLICK_CONFIRM_TEXT);
		WebElement sessionCancelButton = driver.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CLOSE_BUTTON_CLICK_CANCEL_BUTTON));
		WebElement sessionCloseOkButton = driver.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CLOSE_BUTTON_CLICK_OK_BUTTON));
		verifyElementIsPresent(driver, sessionCancelButton);
		verifyElementIsPresent(driver, sessionCloseOkButton);
		click(sessionCancelButton);
		wait(driver, "2");
		WebElement memberChatTextbox = driver.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CHAT_TEXTBOX));
		WebElement memberChatSendButton = driver.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CHAT_SEND_BUTTON));
		verifyElementIsPresent(driver, memberChatTextbox);
		verifyElementIsPresent(driver, memberChatSendButton);
		WebElement memberMicOn = driver.findElement(By.xpath(OR.MEMBER_SESSION_MIC_ON));
		WebElement memberVideoOn = driver.findElement(By.xpath(OR.MEMBER_SESSION_VIDEO_ON));
		verifyElementIsPresent(driver, memberMicOn);
		verifyElementIsPresent(driver, memberVideoOn);
		
		String sessiontime1=null;
		WebElement memberSessionTime = driver.findElement(By.xpath(OR.RA_MEMBER_SESSION_TIME));
		String sessionsTime=memberSessionTime.getText();
		SimpleDateFormat ft = new SimpleDateFormat("EEE hhh:mm a");
		java.util.Date t=ft.parse(sessionsTime);
		ft.applyPattern("hh:mma");
		sessiontime1=ft.format(t).toLowerCase().trim();
		System.out.print(ft.format(t));
		System.out.print("Time"+sessiontime1);

		// Verify member gear settings
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.realAppealmemberGearSettings( driver );

		// Browser launch for Coach side
		// Test data seperation from spread sheet
		if ("firefox".equalsIgnoreCase(coachBrowser) ) 
		{
			driver1 =  new FirefoxBrowser().getDriver();
			driver1.manage().window().maximize();
		}
		else if ( "ie".equalsIgnoreCase(coachBrowser) )
		{
			driver1 = ( WebDriver ) new IEBrowser().getDriver();
			driver1.manage().window().maximize();
		}
		else if ( "chrome".equalsIgnoreCase(coachBrowser)) 
		{
			driver1 = new ChromeBrowser().getDriver();
			driver1.manage().window().maximize();
		}
		else if ( "safari".equalsIgnoreCase(coachBrowser)) 
		{
			driver1 = new SafariBrowser().getDriver();
			driver1.manage().window().maximize();
		}

		// Coach Login with different browser
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.coachLoginWithReferenceStepDifferentBrowser(driver1, coachEmail1);
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raCoachOneOnOneSessionAppendURLDifferentBrowser(driver1);
		wait(driver1, "3");
		WebElement coachUpcomingSessionTime = driver1.findElement(By.xpath("//tr//td[text()='"+sessiontime1+"']/following::td[text()='1on1']/following-sibling::td//following-sibling::td//following-sibling::td/following-sibling::td/div/a"));
		verifyElementIsPresent(driver1, coachUpcomingSessionTime); 
		click(coachUpcomingSessionTime);
		wait(driver1, "10");

		// share selected device for the coach Zlivesession
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.allowPluginsForDifferentBrowser(driver1);
		wait(driver1, "4");
		WebElement coachSettingsButton = driver1.findElement(By.xpath(OR.COACH_SESSION_SETTINGS_BUTTON));
		WebElement coachEndSessionButton = driver1.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_BUTTON));
		WebElement coachMicOn = driver1.findElement(By.xpath(OR.COACH_SESSION_MIC_ON));
		WebElement coachVideoOn = driver1.findElement(By.xpath(OR.COACH_SESSION_VIDEO_ON));
		WebElement coachChatTextBox = driver1.findElement(By.xpath(OR.COACH_SESSION_CHAT_TEXTBOX));
		WebElement cochChatSendButton = driver1.findElement(By.xpath(OR.COACH_SESSION_CHAT_SEND_BUTTON));
		verifyElementIsPresent(driver1, coachSettingsButton);
		verifyElementIsPresent(driver1, coachEndSessionButton);
		verifyElementIsPresent(driver1, coachMicOn);
		verifyElementIsPresent(driver1, coachVideoOn);
		verifyElementIsPresent(driver1, coachChatTextBox);
		verifyElementIsPresent(driver1, cochChatSendButton);

		// Verify coach gear settings
		com.zillion.qa.member.liveSessionSubCommonMethods.coachGearSettings(driver1);

		actionType(driver1, coachChatTextBox, coachChattext);
		wait(driver1, "1");
		actionClick(driver1, cochChatSendButton);
		wait(driver1, "2");
		WebElement coachChatTextToMember = driver1.findElement(By.xpath("//div[@class='chat-message ng-scope']//div[2]/div[contains(text(),'"+coachChattext+"')]"));
		verifyElementIsPresent(driver1, coachChatTextToMember);

		if ("CoachMicEnable".equalsIgnoreCase(coachMicEnableOrDisabled) ) 
		{ 
			try
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_MIC_OFF);
				wait(driver1, "2");
				verifyElementIsPresent(driver1, coachMicOn);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_MIC_ON);
				wait(driver1, "2");
				jsClickByXPath(driver1, OR.COACH_SESSION_MIC_OFF);
				wait(driver1, "2");
				verifyElementIsPresent(driver1, coachMicOn);
			}
		}
		else if ( "CoachMicDisable".equalsIgnoreCase(coachMicEnableOrDisabled) )
		{
			try
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_MIC_ON);
				wait(driver1, "2");
				WebElement coachMicOff = driver1.findElement(By.xpath(OR.COACH_SESSION_MIC_OFF));
				verifyElementIsPresent(driver1, coachMicOff);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_MIC_OFF);
				wait(driver1, "2");
				jsClickByXPath(driver1, OR.COACH_SESSION_MIC_ON);
				WebElement coachMicOff = driver1.findElement(By.xpath(OR.COACH_SESSION_MIC_OFF));
				wait(driver1, "2");
				verifyElementIsPresent(driver1, coachMicOff);
			}
		}
		if ("CoachVideoEnable".equalsIgnoreCase(coachVideoEnableOrDisabled) ) 
		{ 
			try
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_VIDEO_OFF);
				wait(driver1, "2");
				verifyElementIsPresent(driver1, coachVideoOn);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_VIDEO_ON);
				wait(driver1, "2");
				jsClickByXPath(driver1, OR.COACH_SESSION_VIDEO_OFF);
				wait(driver1, "2");
				verifyElementIsPresent(driver1, coachVideoOn);
			}

		}
		else if ( "CoachVideoDisable".equalsIgnoreCase(coachVideoEnableOrDisabled) )
		{
			try
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_VIDEO_ON);
				wait(driver1, "2");
				WebElement coachVideoOff = driver1.findElement(By.xpath(OR.COACH_SESSION_VIDEO_OFF));
				verifyElementIsPresent(driver1, coachVideoOff);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_VIDEO_OFF);
				wait(driver1, "2");
				jsClickByXPath(driver1, OR.COACH_SESSION_VIDEO_ON);
				WebElement coachVideoOff = driver1.findElement(By.xpath(OR.COACH_SESSION_VIDEO_OFF));
				wait(driver1, "2");
				verifyElementIsPresent(driver1, coachVideoOff);
			}
		}

		// Minimize the coach browser
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver1);

		// Switch Member to coach
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver1, "8");
		driver.manage().window().maximize();
		wait(driver, "10");

		actionType(driver, memberChatTextbox, memberChattext);
		wait(driver, "1");
		actionClick(driver, memberChatSendButton);		
		wait(driver, "2");
		WebElement memberAttendToLiveSession = driver.findElement(By.xpath("//div[@class='chat-message ng-scope']//div[2]/div[contains(text(),'"+memberChattext+"')]"));
		verifyElementIsPresent(driver, memberAttendToLiveSession);
		WebElement memberVerifyChatTextFromCoach = driver.findElement(By.xpath("//div[@class='chat-message ng-scope']//div[2]/div[contains(text(),'"+coachChattext+"')]"));
		verifyElementIsPresent(driver, memberVerifyChatTextFromCoach);

		if ("MemberMicEnable".equalsIgnoreCase(MemberMicEnableOrDisabled) ) 
		{ 
			try
			{
				jsClickByXPath(driver, OR.MEMBER_SESSION_MIC_OFF);
				wait(driver, "2");

				verifyElementIsPresent(driver, memberMicOn);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver, OR.MEMBER_SESSION_MIC_ON);
				wait(driver, "2");
				jsClickByXPath(driver, OR.MEMBER_SESSION_MIC_OFF);
				wait(driver, "2");
				verifyElementIsPresent(driver, memberMicOn);
			}
		}

		else if ( "MemberMicDisable".equalsIgnoreCase(MemberMicEnableOrDisabled) )
		{
			try
			{
				jsClickByXPath(driver, OR.MEMBER_SESSION_MIC_ON);
				WebElement memberMicOff = driver.findElement(By.xpath(OR.MEMBER_SESSION_MIC_OFF));
				verifyElementIsPresent(driver, memberMicOff);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver, OR.MEMBER_SESSION_MIC_OFF);
				wait(driver, "2");
				jsClickByXPath(driver, OR.MEMBER_SESSION_MIC_ON);
				wait(driver, "2");
				WebElement memberMicOff = driver.findElement(By.xpath(OR.MEMBER_SESSION_MIC_OFF));
				verifyElementIsPresent(driver, memberMicOff);
			}
		}

		if ("MemberVideoEnable".equalsIgnoreCase(MemberVideoEnabledOrDisabled) ) 
		{ 
			try
			{
				jsClickByXPath(driver, OR.MEMBER_SESSION_VIDEO_OFF);
				wait(driver, "2");
				verifyElementIsPresent(driver, memberVideoOn);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver, OR.MEMBER_SESSION_VIDEO_ON);
				wait(driver, "2");
				jsClickByXPath(driver, OR.MEMBER_SESSION_VIDEO_OFF);
				wait(driver, "2");
				verifyElementIsPresent(driver, memberVideoOn);
			}

		}
		else if ( "MemberVideoDisable".equalsIgnoreCase(MemberVideoEnabledOrDisabled) )
		{
			try
			{
				jsClickByXPath(driver, OR.MEMBER_SESSION_VIDEO_ON);
				wait(driver, "2");
				WebElement memberVideoOff = driver.findElement(By.xpath(OR.MEMBER_SESSION_VIDEO_OFF));
				verifyElementIsPresent(driver, memberVideoOff);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver, OR.MEMBER_SESSION_VIDEO_OFF);
				wait(driver, "2");
				jsClickByXPath(driver, OR.MEMBER_SESSION_VIDEO_ON);
				wait(driver, "2");
				WebElement memberVideoOff = driver.findElement(By.xpath(OR.MEMBER_SESSION_VIDEO_OFF));
				verifyElementIsPresent(driver, memberVideoOff);
			}
		}

		// Switch Member to coach
		driver.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "8");
		driver1.manage().window().maximize();
		wait(driver1, "10");

		WebElement coachVerifyChatTextFromMember = driver1.findElement(By.xpath("//div[@class='chat-message ng-scope']//div[2]/div[contains(text(),'"+memberChattext+"')]"));
		verifyElementIsPresent(driver1, coachVerifyChatTextFromMember);

		if ("MemberMicDisable".equalsIgnoreCase(MemberMicEnableOrDisabled))  
		{
			WebElement micDisabled = driver1.findElement(By.xpath(OR.COACH_SESSION_MIC_DISABLED));
			verifyElementIsPresent(driver1, micDisabled);
		}
		else if ( "MemberMicEnable".equalsIgnoreCase(MemberMicEnableOrDisabled) )
		{
			WebElement micEnabled = driver1.findElement(By.xpath(OR.COACH_SESSION_MIC_ENABLED));
			verifyElementIsPresent(driver1, micEnabled);
		}

		if ("MemberVideoEnable".equalsIgnoreCase(MemberVideoEnabledOrDisabled) )  
		{
			WebElement cameraEnabled = driver1.findElement(By.xpath(OR.COACH_SESSION_CAMERA_ENABLED));
			verifyElementIsPresent(driver1, cameraEnabled);
		}
		else if ( "MemberVideoDisable".equalsIgnoreCase(MemberVideoEnabledOrDisabled) )
		{
			WebElement cameraDisabled = driver1.findElement(By.xpath(OR.COACH_SESSION_CAMERA_DISABLED));
			verifyElementIsPresent(driver1, cameraDisabled);
		}

		if (memberBrowser.equalsIgnoreCase( "firefox" ))  
		{
			WebElement firefoxBrowserIcon = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_FIREFOX_BROWSER_ICON));
			verifyElementIsPresent(driver1, firefoxBrowserIcon);
			jsClickByXPath(driver1, OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_FIREFOX_BROWSER_ICON);
			wait( driver1, "3" );
		}
		else if (memberBrowser.equalsIgnoreCase( "ie" ))
		{
			WebElement ieBrowserIcon = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_IE_BROWSER_ICON));
			verifyElementIsPresent(driver1, ieBrowserIcon);
			jsClickByXPath(driver1, OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_IE_BROWSER_ICON);
			wait( driver1, "3" );
		}
		else if (memberBrowser.equalsIgnoreCase( "chrome" ))
		{
			WebElement ieBrowserIcon = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_CHROME_BROWSER_ICON));
			verifyElementIsPresent(driver1, ieBrowserIcon);
			jsClickByXPath(driver1, OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_CHROME_BROWSER_ICON);
			wait( driver1, "3" );
		}

		wait(driver1, "4");
		WebElement memberID = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_MEMBER_ID));
		WebElement okButton = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_OK_BUTTON));
		WebElement memberIDHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_MEMBER_ID_HEADER));
		WebElement sessionIdHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_SESSION_ID_HEADER));
		WebElement userAgentHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_USER_AGENT_HEADER));
		WebElement versionHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_LIVESESSION_VERSION_HEADER));
		WebElement micOptionHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_MICROPHONE_OPTIONS_HEADER));
		WebElement micSelectedHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_MICROPHONE_SELECTED_HEADER));
		WebElement speakerOptionHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_SPEAKER_OPTIONS_HEADER));
		WebElement speakerSelectedHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_SPEAKER_SELECTED_HEADER));
		WebElement cameraOptionHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_CAMERA_OPTIONS_HEADER));
		WebElement cameraselectedHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_CAMERA_SELECTED_HEADER));
		verifyElementIsPresent(driver1, memberID);
		verifyElementIsPresent(driver1, okButton);
		verifyElementIsPresent(driver1, memberIDHeader);
		verifyElementIsPresent(driver1, sessionIdHeader);
		verifyElementIsPresent(driver1, userAgentHeader);
		verifyElementIsPresent(driver1, versionHeader);
		verifyElementIsPresent(driver1, micOptionHeader);
		verifyElementIsPresent(driver1, micSelectedHeader);
		verifyElementIsPresent(driver1, speakerOptionHeader);
		verifyElementIsPresent(driver1, speakerSelectedHeader);
		verifyElementIsPresent(driver1, cameraOptionHeader);
		verifyElementIsPresent(driver1, cameraselectedHeader);
		click(okButton);
		wait(driver1, "2");
		click(coachEndSessionButton);
		wait(driver1, "2");
		WebElement endSessionText = driver1.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_TEXT));
		WebElement endSessionYesButton = driver1.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_YES_BUTTON));
		WebElement endSessionNoButton = driver1.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_NO_BUTTON));
		verifyElementIsPresent(driver1, endSessionText);
		verifyElementIsPresent(driver1, endSessionYesButton);
		verifyElementIsPresent(driver1, endSessionNoButton);
		click(endSessionYesButton);
		wait(driver1, "3");
		waitForElement(driver1, COACH_SESSION_SESSION_COMPLETE_HEADER_TEXT);
		WebElement coachSessionCompletedHeader = driver1.findElement(By.xpath(OR.COACH_SESSION_SESSION_COMPLETE_HEADER_TEXT));
		WebElement sessionEndedText = driver1.findElement(By.xpath(OR.COACH_SESSION_YOUR_1ON1_SESSION_ENDED_TEXT));
		WebElement commentsTextbox = driver1.findElement(By.xpath(OR.COACH_1ON1_COMMENTS_TEXTBOX));
		WebElement completedRadioButton = driver1.findElement(By.xpath(OR.COACH_SESSION_SESSION_COMPLETED_RADIO_BUTTON));
		WebElement memberDidNotAttendRadioButton = driver1.findElement(By.xpath(OR.COACH_SESSION_MEMBER_DID_NOT_ATTEND_RADIO_BUTTON));
		WebElement SessionCompletedText = driver1.findElement(By.xpath(OR.COACH_SESSION_SESSION_COMPLETED_TEXT));
		WebElement MemberDidNotAttendText = driver1.findElement(By.xpath(OR.COACH_SESSION_MEMBER_DID_NOT_ATTEND_TEXT));
		WebElement DoneButton = driver1.findElement(By.xpath(OR.COACH_SESSION_MEMBER_DONE_BUTTON));
		verifyElementIsPresent(driver1, coachSessionCompletedHeader);
		verifyElementIsPresent(driver1, sessionEndedText);
		verifyElementIsPresent(driver1, commentsTextbox);
		verifyElementIsPresent(driver1, completedRadioButton);
		verifyElementIsPresent(driver1, memberDidNotAttendRadioButton);
		verifyElementIsPresent(driver1, SessionCompletedText);
		verifyElementIsPresent(driver1, MemberDidNotAttendText);
		verifyElementIsPresent(driver1, DoneButton);
		click(commentsTextbox);
		sendKeys(commentsTextbox, "Session Completed");
		click(completedRadioButton);
		wait(driver1, "1");
		click(DoneButton);
		wait(driver1, "7");

		//RealAppeal coach Logout
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.realAppealCoachLogout(driver1);

		// Minimize the coach browser
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver1);


		// Switch Coach to member
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver1, "8");
		driver.manage().window().maximize();
		wait(driver, "10");

		WebElement ratingStar1 = driver.findElement(By.xpath(OR.RA_GROUP_SESSION_COACH_RATING_STAR_1));
		WebElement ratingStar2 = driver.findElement(By.xpath(OR.RA_GROUP_SESSION_COACH_RATING_STAR_2));
		WebElement ratingStar3 = driver.findElement(By.xpath(OR.RA_GROUP_SESSION_COACH_RATING_STAR_3));
		WebElement ratingStar4 = driver.findElement(By.xpath(OR.RA_GROUP_SESSION_COACH_RATING_STAR_4));
		WebElement ratingStar5 = driver.findElement(By.xpath(OR.RA_GROUP_SESSION_COACH_RATING_STAR_5));
		WebElement ratingTextbox = driver.findElement(By.xpath(OR.MEMBER_COACH_RATING_TEXTBOX));
		WebElement ratingSubmitButton = driver.findElement(By.xpath(OR.MEMBER_COACH_RATING_SUBMIT_BUTTON));
		verifyElementIsPresent(driver, ratingStar1);
		verifyElementIsPresent(driver, ratingStar2);
		verifyElementIsPresent(driver, ratingStar3);
		verifyElementIsPresent(driver, ratingStar4);
		verifyElementIsPresent(driver, ratingStar5);
		verifyElementIsPresent(driver, ratingTextbox);
		verifyElementIsPresent(driver, ratingSubmitButton);
		click(ratingStar1);
		click(ratingStar2);
		click(ratingStar3);
		click(ratingStar4);
		click(ratingStar5);
		click(ratingTextbox);
		sendKeys(ratingTextbox, "Session completed with coach");
		click(ratingSubmitButton);
		wait(driver, "4");
		com.zillion.qa.realappealmember.member.realAppealMemberlogout(driver);
		driver.close();
		driver1.close();
	}
}
