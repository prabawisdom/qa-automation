package com.zillion.qa.member;
import java.util.Objects;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.zillion.qa.ZadoReports;
import com.zillion.qa.commands.Manipulation;
import com.zillion.qa.commands.Navigate;
import com.zillion.qa.config.ChromeBrowser;
import com.zillion.qa.config.FirefoxBrowser;
import com.zillion.qa.config.IEBrowser;
import com.zillion.qa.config.SafariBrowser;
import com.zillion.qa.enums.LogAs;
import com.zillion.qa.reports.CaptureScreen;
import com.zillion.qa.reports.CaptureScreen.ScreenshotOf;
public class WellnessCorpPALectureLiveSession extends  Manipulation implements OR {
	/**
	 * Name :Vinothkumar.M     
	 * Created Date: 21/Oct/2016  
	 * Modified Date:  
	 * Description : Create a common method to Lecture Live session
	 * Ticket ID :   
	 * Required Inputs : Inputs Required from Test data from Spread sheet
	 * @throws Exception 
	 */
	public static String wellnessCorpPAlectureLiveSession(WebDriver driver,String testData,String verifyMemberSignUp3) throws Exception
	{
		// Browser launch for Member side
		// Test data separation from spread sheet
		String[] testData1 = testData.split(",");
		String inputBrowser=testData1[0];
		String inputCoachChatText=testData1[4];
		String inputMemberChatText=testData1[5];
		String inputCoachSessionCommentTextbox=testData1[8];
		String inputMemberCoachRatingTextbox=testData1[9];
		String inputCoachMemberTileMic=testData1[10];
		String inputMuteMemberButton=testData1[11];
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
		// Common method to member Login and Sign up for the Created lecture session by Ops Admin
		com.zillion.qa.member.liveSessionSubCommonMethods.wellnessCorpLiveSessionMemberSignUpLectureSession(driver,testData,verifyMemberSignUp3);
		// Common method Member to Attend Now URL
		com.zillion.qa.member.Dashboard.appendTextToURL(driver);
		wait( driver, "4" );
		Navigate.pageUp( driver );
		// Click on Attend now button and Verify the buttons and elements
		waitForElement( driver, OR.LECTURE_SESSION_MEMBER_ATTEND_NOW_BUTTON );
		WebElement memberAttendNowButton = driver.findElement(By.xpath(OR.LECTURE_SESSION_MEMBER_ATTEND_NOW_BUTTON));
		verifyElementIsPresent(driver, memberAttendNowButton);
		jsClickByXPath(driver, OR.LECTURE_SESSION_MEMBER_ATTEND_NOW_BUTTON);
		wait( driver, "5" );  
		WebElement memberCertainFeaturesWarningMsg = driver.findElement(By.xpath(OR.MEMBER_CERTAIN_FEATURES_IN_THE_ORBERA_NOT_SUPPORTED_BY_YOUR_BROWSER_WARNING_MSG));
		verifyElementIsPresent(driver, memberCertainFeaturesWarningMsg);
		jsClickByXPath(driver, OR.MEMBER_CERTAIN_FEATURES_IN_THE_ORBERA_CLOSE_BUTTON);
		wait( driver, "5" ); 
		jsClickByXPath(driver, OR.LECTURE_SESSION_MEMBER_ATTEND_NOW_BUTTON);
		wait( driver, "20" ); 
		// Common method to Allow system plugins. Click Allow and Allow&Remember
		com.zillion.qa.member.liveSessionSubCommonMethods.allowPlugins(driver);
		WebElement memberChatTextbox = driver.findElement(By.xpath(OR.MEMBER_SESSION_CHAT_TEXTBOX));
		WebElement memberSendButton = driver.findElement(By.xpath(OR.MEMBER_SESSION_CHAT_SEND_BUTTON));
		verifyElementIsPresent(driver, memberChatTextbox);
		verifyElementIsPresent(driver, memberSendButton);
		waitForElement( driver, OR.COACH_LECTURE_SESSION_WELCOME_HEADER );
		WebElement welcomeHeader = driver.findElement(By.xpath(OR.COACH_LECTURE_SESSION_WELCOME_HEADER));
		verifyElementIsPresent(driver, welcomeHeader);
		waitForElement( driver, OR.CONNECTED_TO_LIVE_SESSION );
		WebElement connectedToLiveSession = driver.findElement(By.xpath(OR.CONNECTED_TO_LIVE_SESSION));
		verifyElementIsPresent(driver, connectedToLiveSession);
		WebElement memberMicDisabled = driver.findElement(By.xpath(OR.LECTURE_SESSION_MEMBER_SESSION_MIC_DISABLED));
		verifyElementIsPresent(driver, memberMicDisabled);
		// Common method to verify Member gear settings 
		com.zillion.qa.member.liveSessionSubCommonMethods.memberGearSettings( driver );
		// Common method to retrieve Coach host name 
		com.zillion.qa.member.liveSessionSubCommonMethods.retrieveLectureSessionHostNameWithProgramID( driver );
		// Common method to retrieve Coach Email with coach host name
		com.zillion.qa.member.liveSessionSubCommonMethods.retriveLectureSessionEmailWithCoachHostName( driver );
		WebDriver driver1 = null;
		// Coach Lecture live session
		// Browser launch for Coach
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
		wait( driver, "8" );
		// Common method to Login Coach with retrieved coach email from DB
		// Password with 3 attempts (Healthfleet2015, Healthfleet2016 and Zillion2016)
		com.zillion.qa.member.liveSessionSubCommonMethods.lectureLiveSessionCoachLogin(driver1);
		// Common method Coach to Attend Now URL
		com.zillion.qa.member.Dashboard.coachAppendTextToURL(driver1);
		wait( driver, "15" );
		// Click on Attend now button
		waitForElement(driver1, OR.COACH_ATTEND_NOW_BUTTON );
		WebElement coachAttendNow = driver1.findElement(By.xpath(OR.COACH_ATTEND_NOW_BUTTON));
		verifyElementIsPresent(driver1, coachAttendNow);
		jsClickByXPath(driver1, OR.COACH_ATTEND_NOW_BUTTON);
		wait( driver, "20" );
		// Common method to Allow system Plugins. Click Allow and Allow&Remember
		com.zillion.qa.member.liveSessionSubCommonMethods.allowPlugins(driver);
		// Verify all the field in Coach live session
		wait(driver1, "5" );
		waitForElement( driver1, OR.COACH_LECTURE_SESSION_MEMBER_ONLINE_HEADER );
		WebElement memberOnlineHeader = driver1.findElement(By.xpath(OR.COACH_LECTURE_SESSION_MEMBER_ONLINE_HEADER));
		WebElement shareScreenButton = driver1.findElement(By.xpath(OR.COACH_LECTURE_SESSION_SHARE_SCREEN_BUTTON));
		WebElement endSessionButton = driver1.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_BUTTON));
		WebElement coachMicOn = driver1.findElement(By.xpath(OR.COACH_SESSION_MIC_ON));
		WebElement coachVideoOn = driver1.findElement(By.xpath(OR.COACH_SESSION_VIDEO_ON));
		WebElement coachChatTextbox = driver1.findElement(By.xpath(OR.COACH_SESSION_CHAT_TEXTBOX));
		WebElement coachSendButton = driver1.findElement(By.xpath(OR.COACH_SESSION_CHAT_SEND_BUTTON));
		WebElement coachTileMemberMicDisabled = driver1.findElement(By.xpath(OR.COACH_LECTURE_SESSION_COACH_TILE_MEMBER_MIC_DISABLED));
		verifyElementIsPresent(driver1, memberOnlineHeader);
		verifyElementIsPresent(driver1, shareScreenButton);
		verifyElementIsPresent(driver1, endSessionButton);
		verifyElementIsPresent(driver1, coachMicOn);
		verifyElementIsPresent(driver1, coachVideoOn);
		verifyElementIsPresent(driver1, coachTileMemberMicDisabled);
		// Common method to verify Coach gear settings
		com.zillion.qa.member.liveSessionSubCommonMethods.coachGearSettings( driver1 );
		// Coach send chat text to member
		// Coach verify chat text in chat area 
		actionType(driver1,coachChatTextbox,inputCoachChatText);
		wait(driver1, "2" );
		actionClick( driver1, coachSendButton );
		wait(driver1, "2" );
		Navigate.pageUp( driver );
		WebElement coachAttendToLiveSession = driver1.findElement(By.xpath("//div[@class='chat-message ng-scope']//div[2]/div[contains(text(),'"+inputCoachChatText+"')]"));
		verifyElementIsPresent(driver1, coachAttendToLiveSession);
		// Common method Coach Mic Enable/Disable
		com.zillion.qa.member.liveSessionSubCommonMethods.coachLectureSessionMicEnableAndDisable(driver1,testData); 
		// Common method Coach Video Enable/Disable
		com.zillion.qa.member.liveSessionSubCommonMethods.coachLectureSessionVideoEnableAndDisable(driver1,testData);
		// Common method Coach Member Tile Mic Enable/Disable
		com.zillion.qa.member.liveSessionSubCommonMethods.lectureSessionCoachMemberTileMicEnable(driver1,testData);
		// Common method get Session ID with current URL
		com.zillion.qa.member.liveSessionSubCommonMethods.getCurrentURL( driver1 );
		wait( driver, "10" );
		// Switch Coach to Member
		// Member verify coach chat text and Member send chat text to Coach
		// Member verify chat text in Member chat area
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
		// Verify Coach Member tile Mic Enable/Disable
		if ("CoachMemberTileMicDisable".equalsIgnoreCase(inputCoachMemberTileMic))  
		{
			verifyElementIsPresent(driver, memberMicDisabled);
		}
		else if ( "CoachMemberTileMicEnable".equalsIgnoreCase(inputCoachMemberTileMic) )
		{
			WebElement micEnabled = driver.findElement(By.xpath(OR.MEMBER_SESSION_MIC_ON));
			verifyElementIsPresent(driver, micEnabled);
		}
		// Switch Member to Coach
		// Coach verify Member chat text
		driver.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "5");
		driver1.manage().window().setPosition(new Point(0, 0));
		wait(driver, "4");
		WebElement coachWelcomeToLiveSession = driver1.findElement(By.xpath("//div[@class='chat-message ng-scope']//div[2]/div[contains(text(),'"+inputMemberChatText+"')]"));
		verifyElementIsPresent(driver1, coachWelcomeToLiveSession);
		// Common method Coach Mute/Unmute Members button
		//com.zillion.qa.member.liveSessionSubCommonMethods.lectureSessionCoachMuteMembersButton(driver1,testData);
		// Switch Coach to Membe
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "5");
		driver.manage().window().setPosition(new Point(0, 0));
		wait(driver, "4");
		// Member verify Mic Enable/Disable 
		if ("UnmuteMembersButton".equalsIgnoreCase(inputMuteMemberButton))  
		{
			verifyElementIsPresent(driver, memberMicDisabled);
		}
		else if ( "MuteMembersButton".equalsIgnoreCase(inputMuteMemberButton) )
		{
			WebElement micEnabled = driver.findElement(By.xpath(OR.MEMBER_SESSION_MIC_ON));
			verifyElementIsPresent(driver, micEnabled);
		}
		// Switch Member to Coach
		// Coach share screen to member
		driver.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "5");
		driver1.manage().window().setPosition(new Point(0, 0));
		wait(driver, "4");
		WebElement screenShareButton = driver1.findElement(By.xpath(OR.COACH_LECTURE_SESSION_SHARE_SCREEN_BUTTON));
		verifyElementIsPresent(driver1, screenShareButton);
		jsClickByXPath(driver1, OR.COACH_LECTURE_SESSION_SHARE_SCREEN_BUTTON);
		wait(driver, "5");
		WebElement screenShareRefreshButton = driver1.findElement(By.xpath(OR.COACH_LECTURE_SESSION_SCREEN_SHARE_REFRESH_BUTTON));
		verifyElementIsPresent(driver1, screenShareRefreshButton);
		jsClickByXPath(driver1, OR.COACH_LECTURE_SESSION_SCREEN_SHARE_REFRESH_BUTTON);
		wait(driver, "5");
		jsClickByXPath(driver1, OR.COACH_LECTURE_SESSION_SHARE_SCREEN_IMAGE);
		wait(driver, "5");
		// Switch Coach to Member
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "5");
		driver.manage().window().setPosition(new Point(0, 0));
		wait(driver, "4");
		ZadoReports.add("TakeScreenShot","Screen shot for the Member Screen share","", "Screen shot took successfully for Member screen share",Objects.toString("", ""),LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));
		// Switch Member to Coach
		// Verify Stop sharing button is available
		driver.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "5");
		driver1.manage().window().setPosition(new Point(0, 0));
		wait(driver, "4");
		WebElement screenStopSharingButton = driver1.findElement(By.xpath(OR.COACH_LECTURE_SESSION_STOP_SHARING_SCREEN));
		verifyElementIsPresent(driver1, screenStopSharingButton);
		jsClickByXPath(driver1, OR.COACH_LECTURE_SESSION_STOP_SHARING_SCREEN);
		wait(driver, "2");
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
		else if ( "chrome".equalsIgnoreCase(inputBrowser) )
		{
			WebElement chromeBrowserIcon = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_CHROME_BROWSER_ICON));
			verifyElementIsPresent(driver1, chromeBrowserIcon);
			jsClickByXPath(driver1, OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_CHROME_BROWSER_ICON);
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
		com.zillion.qa.member.liveSessionSubCommonMethods.compareAccountIDWithBrowserToolBarIcon(driver1);
		jsClickByXPath(driver1, OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_OK_BUTTON);
		wait( driver1, "3" );
		// Verify all the fields and text
		// Coach End lecture live session button
		// Coach send comments to member
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
		waitForElement(driver1, OR.COACH_LECTURE_SESSION_SESSION_COMPLETE_HEADER_TEXT );
		WebElement coachSessionComplete = driver1.findElement(By.xpath(OR.COACH_LECTURE_SESSION_SESSION_COMPLETE_HEADER_TEXT));
		WebElement coachGroupSessionEndedText = driver1.findElement(By.xpath(OR.COACH_LECTURE_SESSION_YOUR_GROUP_SESSION_ENDED_TEXT));
		WebElement coachSessionCommentTextbox = driver1.findElement(By.xpath(OR.COACH_LECTURE_SESSION_SESSION_COMMENTS_TEXTBOX));
		WebElement coachSessionEndSessionRadioButton= driver1.findElement(By.xpath(OR.COACH_LECTURE_SESSION_END_SESSION_RADIO_BUTTON));
		WebElement coachSessionLeaveSessionRadioButton = driver1.findElement(By.xpath(OR.COACH_LECTURE_SESSION_LEAVE_THE_SESSION_AND_JOIN_AGAIN_RADIO_BUTTON));
		WebElement coachSessionCancelandResumeSessionRadioButton = driver1.findElement(By.xpath(OR.COACH_LECTURE_SESSION_CANCEL_AND_RESUME_THE_SESSION_RADIO_BUTTON));
		WebElement coachSessionEndSessionText = driver1.findElement(By.xpath(OR.COACH_LECTURE_SESSION_END_SESSION_TEXT));
		WebElement coachSessionLeaveSessionText = driver1.findElement(By.xpath(OR.COACH_LECTURE_SESSION_LEAVE_THE_SESSION_AND_JOIN_AGAIN_TEXT));
		WebElement coachSessionCancelandResumeText= driver1.findElement(By.xpath(OR.COACH_LECTURE_SESSION_CANCEL_AND_RESUME_THE_SESSION_TEXT));
		WebElement coachEndSessionDoneButton = driver1.findElement(By.xpath(OR.COACH_LECTURE_SESSION_MEMBER_DONE_BUTTON));
		WebElement coachSessionPleaseSelectOptions = driver1.findElement(By.xpath(OR.COACH_LECTURE_SESSION_PLEASE_SELECT_ONE_OF_THE_OPTIONS_ERROR_MSG));
		verifyElementIsPresent(driver1, coachSessionComplete);
		verifyElementIsPresent(driver1, coachGroupSessionEndedText);
		verifyElementIsPresent(driver1, coachSessionCommentTextbox);
		verifyElementIsPresent(driver1, coachSessionEndSessionRadioButton);
		verifyElementIsPresent(driver1, coachSessionLeaveSessionRadioButton);
		verifyElementIsPresent(driver1, coachSessionCancelandResumeSessionRadioButton);
		verifyElementIsPresent(driver1, coachSessionEndSessionText);
		verifyElementIsPresent(driver1, coachSessionLeaveSessionText);
		verifyElementIsPresent(driver1, coachSessionCancelandResumeText);
		verifyElementIsPresent(driver1, coachEndSessionDoneButton);
		jsClickByXPath(driver1, OR.COACH_LECTURE_SESSION_MEMBER_DONE_BUTTON);
		wait( driver, "3" );
		verifyElementIsPresent(driver1, coachSessionPleaseSelectOptions);
		jsClickByXPath(driver1, OR.COACH_LECTURE_SESSION_SESSION_COMMENTS_TEXTBOX);
		actionType(driver1,coachSessionCommentTextbox,inputCoachSessionCommentTextbox);
		wait( driver, "2" );
		jsClickByXPath(driver1, OR.COACH_LECTURE_SESSION_END_SESSION_RADIO_BUTTON);
		jsClickByXPath(driver1, OR.COACH_LECTURE_SESSION_MEMBER_DONE_BUTTON);
		wait( driver, "3" );
		// Switch Coach to Member
		// Member gives rating to Coach
		// Member send rating comments to Coach
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "5");
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
		Navigate.waitTime( driver, "10" );
		// Common method to retrieve Session Attendance status from DB with session ID
		// com.zillion.qa.member.liveSessionSubCommonMethods.retriveLectureSessionAttendanceStatusWithSessionID( driver);
		// Common method to retrieve Session rating stars and with given stars in script
		com.zillion.qa.member.liveSessionSubCommonMethods.sessionratingStars( driver);
		// Common method to retrieve Coach rating notes from DB and compare with given input from spread sheet
		com.zillion.qa.member.liveSessionSubCommonMethods.ratingNotesCoachSide( driver, testData);
		// Common method to retrieve Member rating notes from DB and compare with given input from spread sheet
		com.zillion.qa.member.liveSessionSubCommonMethods.ratingNotesMemberSide( driver, testData);
		// Driver Close
		driver.close();
		driver1.close();
		return ElementWait;
	}
}