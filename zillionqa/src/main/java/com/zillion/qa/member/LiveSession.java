package com.zillion.qa.member;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Objects;
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
import com.zillion.qa.utils.Directory;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class LiveSession extends Manipulation implements OR 
{
	/**
	 * Name :VinothKumar.M         
	 * Created Date: 01/Mar/2016    
	 * Modified Date:     
	 * Description :   Create a common method for 1on1 Live Session
	 * Required Inputs :  
	 * @throws Exception 
	 */
	public static String oneOnOneliveSession(WebDriver driver,String testData) throws Exception
	{
		String[] testData1 = testData.split(",");
		String inputBrowser=testData1[0];
		String inputMemberMic=testData1[1];
		String inputMemberVideo=testData1[2];
		String inputCoachChatText=testData1[6];
		String inputMemberChatText=testData1[7];
		String inputCoachSessionCommentTextbox=testData1[8];
		String inputMemberCoachRatingTextbox=testData1[9];
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
		com.zillion.qa.member.liveSessionSubCommonMethods.getOrberaMember1on1SessionFromMember(driver);
		com.zillion.qa.member.liveSessionSubCommonMethods.oneOnOneliveSessionMemberLogin(driver);
		wait(driver, "8");
		waitForElement( driver, OR.MEMBER_SCHEDULE_1ON1SESSION );
		WebElement schedule1on1Button = driver.findElement(By.xpath(OR.MEMBER_SCHEDULE_1ON1SESSION));
		click(schedule1on1Button);
		wait( driver, "10" );
		com.zillion.qa.member.Dashboard.dateSession(driver);
		waitForElement( driver, OR.MEMBER_SESSION_CONFIRM );
		WebElement sessionConfirmButton = driver.findElement(By.xpath(OR.MEMBER_SESSION_CONFIRM));
		verifyElementIsPresent(driver, sessionConfirmButton);
		click(sessionConfirmButton);
		wait( driver, "4" );
		waitForElement( driver, OR.MEMBER_SESSION_SUCCESS_MSG );
		WebElement sessionSuccessMSG = driver.findElement(By.xpath(OR.MEMBER_SESSION_SUCCESS_MSG));
		verifyElementIsPresent(driver, sessionSuccessMSG);
		WebElement dashBoardButton = driver.findElement(By.xpath(OR.MEMBER_DASHBOARD_ICON));
		verifyElementIsPresent(driver, dashBoardButton);
		click(dashBoardButton);
		wait( driver, "8" );
		com.zillion.qa.member.Dashboard.appendTextToURL(driver);
		wait( driver, "7" );
		waitForElement( driver, OR.MEMBER_ATTEND_NOW_BUTTON );
		jsClickByXPath(driver, OR.MEMBER_ATTEND_NOW_BUTTON);
		wait( driver, "10" );  
		// Common method to Allow system Plugins. Click Allow and Allow&Remember
		com.zillion.qa.member.liveSessionSubCommonMethods.allowPlugins(driver);
		// Verify Welcome Member header, Member Mic&Video button
		WebElement memberChatTextbox = driver.findElement(By.xpath(OR.MEMBER_SESSION_CHAT_TEXTBOX));
		WebElement memberSendButton = driver.findElement(By.xpath(OR.MEMBER_SESSION_CHAT_SEND_BUTTON));
		verifyElementIsPresent(driver, memberChatTextbox);
		verifyElementIsPresent(driver, memberSendButton);
		waitForElement( driver, OR.WELCOME_HEADER );
		WebElement welcomeHeader = driver.findElement(By.xpath(OR.WELCOME_HEADER));
		verifyElementIsPresent(driver, welcomeHeader);
		waitForElement( driver, OR.CONNECTED_TO_LIVE_SESSION );
		WebElement connectedToLiveSession = driver.findElement(By.xpath(OR.CONNECTED_TO_LIVE_SESSION));
		verifyElementIsPresent(driver, connectedToLiveSession);
		WebElement memberMicOn = driver.findElement(By.xpath(OR.MEMBER_SESSION_MIC_ON));
		WebElement memberVideoOn = driver.findElement(By.xpath(OR.MEMBER_SESSION_VIDEO_ON));
		verifyElementIsPresent(driver, memberMicOn);
		verifyElementIsPresent(driver, memberVideoOn);

		// Retrieve Member session time
		com.zillion.qa.member.liveSessionSubCommonMethods.orberaMemberSessionTime(driver);

		// Verify Member gear setting option
		com.zillion.qa.member.liveSessionSubCommonMethods.memberGearSettings( driver );

		// Retrieve Member AccountID, ProgramID, Coach hostname and Coach Email

		/*String accountid=com.zillion.qa.member.liveSessionSubCommonMethods.retrieveMemberAccountId(driver,testData);
		String pgmid= com.zillion.qa.member.liveSessionSubCommonMethods.retrieveAccountProgramID( driver,accountid );
		String hostname=com.zillion.qa.member.liveSessionSubCommonMethods.retrieve1on1SessionHostNameWithProgramID( driver,pgmid );
		com.zillion.qa.member.liveSessionSubCommonMethods.retrive1on1SessionEmailWithCoachHostId( driver,hostname );*/

		//com.zillion.qa.member.liveSessionSubCommonMethods.getOrbera1on1SessionFromCoach(driver);

		WebDriver driver1 = null;


		// Coach Live Session in Browser Launch 

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

		// Common method for Coach Login with retrieved coach email as Username
		// Password with 3 attempts (Healthfleet2015, Healthfleet2016 and Zillion2016)

		com.zillion.qa.member.liveSessionSubCommonMethods.oneOnOneLiveSessionCoachLogin(driver1);

		// Common method Coach to Attend Now URL 

		com.zillion.qa.member.Dashboard.coachAppendTextToURL(driver1);
		wait( driver, "15" );

		// Click on Attend now 

		/*waitForElement(driver1, OR.COACH_ATTEND_NOW_BUTTON );
		WebElement coachAttendNow = driver1.findElement(By.xpath(OR.COACH_ATTEND_NOW_BUTTON));
		verifyElementIsPresent(driver1, coachAttendNow);
		jsClickByXPath(driver1, OR.COACH_ATTEND_NOW_BUTTON);
		wait( driver, "15" );*/

		com.zillion.qa.member.liveSessionSubCommonMethods.orberaCoachUpcomingSessionTime(driver1);

		// Common method to Allow system Plugins. Click Allow and Allow&Remember

		com.zillion.qa.member.liveSessionSubCommonMethods.allowPlugins(driver);

		// Attend Live session and verify All the fields

		waitForElement( driver1, OR.COACH_MEMBER_CONNECTED_TEXT );
		WebElement connectionHistory = driver1.findElement(By.xpath(OR.COACH_CONNECTION_HISTORY_TEXT));
		verifyElementIsPresent(driver1, connectionHistory);
		WebElement memberConnectedText = driver1.findElement(By.xpath(OR.COACH_MEMBER_CONNECTED_TEXT));
		WebElement sessionTimerLabel = driver1.findElement(By.xpath(OR.COACH_SESSION_SESSION_TIMER_LABEL));
		WebElement mailingAddressLabel = driver1.findElement(By.xpath(OR.COACH_SESSION_MAILING_ADDRESS_LABEL));
		WebElement dobLabel = driver1.findElement(By.xpath(OR.COACH_SESSION_DOB_LABEL));
		WebElement allowedContactLabel = driver1.findElement(By.xpath(OR.COACH_SESSION_ALLOWED_CONTACT_LABEL));
		WebElement memberLocalTimeLabel = driver1.findElement(By.xpath(OR.COACH_SESSION_MEMBER_LOCAL_TIME_LABEL));
		WebElement browseNotesButton = driver1.findElement(By.xpath(OR.COACH_SESSION_BROWSE_NOTES_BUTTON));
		WebElement settingsButton = driver1.findElement(By.xpath(OR.COACH_SESSION_SETTINGS_BUTTON));
		WebElement endSessionButton = driver1.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_BUTTON));
		WebElement coachMicOn = driver1.findElement(By.xpath(OR.COACH_SESSION_MIC_ON));
		WebElement coachVideoOn = driver1.findElement(By.xpath(OR.COACH_SESSION_VIDEO_ON));
		WebElement coachChatTextbox = driver1.findElement(By.xpath(OR.COACH_SESSION_CHAT_TEXTBOX));
		WebElement coachSendButton = driver1.findElement(By.xpath(OR.COACH_SESSION_CHAT_SEND_BUTTON));
		verifyElementIsPresent(driver1, coachMicOn);
		verifyElementIsPresent(driver1, coachVideoOn);
		verifyElementIsPresent(driver1, memberConnectedText);
		verifyElementIsPresent(driver1, sessionTimerLabel);
		verifyElementIsPresent(driver1, mailingAddressLabel);
		verifyElementIsPresent(driver1, dobLabel);
		verifyElementIsPresent(driver1, allowedContactLabel);
		verifyElementIsPresent(driver1, memberLocalTimeLabel);
		verifyElementIsPresent(driver1, browseNotesButton);
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
		wait(driver, "2" );
		// Common method for Coach Video Enable/Disable

		//com.zillion.qa.member.liveSessionSubCommonMethods.coachOneOnOneSessionVideoEnableAndDisable(driver1,testData);

		// Common method to get the Session ID from the current URL

		com.zillion.qa.member.liveSessionSubCommonMethods.getCurrentURL( driver1 );
		wait( driver, "10" );

		// Switching  Coach to Member. Member verifies coach message in member chat area 
		// Member send message to Coach and verify the sent message in Member chat area

		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "5");
		driver.manage().window().maximize();
		wait(driver, "4");
		WebElement dashBordTab = driver.findElement(By.xpath(OR.MEMBER_DASHBOARD_ICON));
		WebElement memberWelcomeToLiveSession = driver.findElement(By.xpath("//div[@class='chat-message ng-scope']//div[2]/div[contains(text(),'"+inputCoachChatText+"')]"));
		verifyElementIsPresent(driver, dashBordTab);
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

		com.zillion.qa.member.liveSessionSubCommonMethods.retrive1on1SessionAttendanceStatusWithSessionID( driver);

		// Common method to retrieve Session rating stars and with given stars in script

		com.zillion.qa.member.liveSessionSubCommonMethods.retrieveMemberAccountId( driver, testData);

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

	/**
	 * Name :Vigneshraj     
	 * Created Date: 11/Mar/2016  
	 * Modified Date:  
	 * Description : Create a common method to Lecture Live session
	 * Ticket ID :   
	 * Required Inputs : Inputs Required from Test data from Spread sheet
	 * @throws Exception 
	 */
	public static String lectureAddLiveLiveSessionForDifferentBrowser(WebDriver driver,String testData, String coachEmail1) throws Exception

	{

		// Browser launch for Member side
		// Test data seperation from spread sheet

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

		com.zillion.qa.member.liveSessionSubCommonMethods.liveSessionMemberLoginForLectureSession(driver,testData);

		// Common method Member to Attend Now URL

		com.zillion.qa.member.Dashboard.appendTextToURL(driver);
		wait( driver, "4" );
		Navigate.pageUp( driver );

		// Click on Attend now button and Verify the buttons and elements

		waitForElement( driver, OR.LECTURE_SESSION_MEMBER_ATTEND_NOW_BUTTON );
		WebElement memberAttendNowButton = driver.findElement(By.xpath(OR.LECTURE_SESSION_MEMBER_ATTEND_NOW_BUTTON));
		verifyElementIsPresent(driver, memberAttendNowButton);
		jsClickByXPath(driver, OR.LECTURE_SESSION_MEMBER_ATTEND_NOW_BUTTON);
		wait( driver, "7" );  

		// Common method to Allow system Plugins. Click Allow and Allow&Remember

		com.zillion.qa.member.liveSessionSubCommonMethods.allowPluginDifferentBrowser(driver);

		wait( driver, "5" );
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
		com.zillion.qa.member.liveSessionSubCommonMethods.memberGearSettings( driver );
		com.zillion.qa.member.liveSessionSubCommonMethods.retrieveLectureSessionHostNameWithProgramID( driver );
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
		com.zillion.qa.member.liveSessionSubCommonMethods.orberaCoacheLoginDifferentBrowser(driver1,coachEmail1);
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
		com.zillion.qa.member.liveSessionSubCommonMethods.allowPluginsForDifferentBrowser(driver1);
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
		verifyElementIsPresent(driver1, memberOnlineHeader);
		verifyElementIsPresent(driver1, shareScreenButton);
		verifyElementIsPresent(driver1, endSessionButton);
		verifyElementIsPresent(driver1, coachMicOn);
		verifyElementIsPresent(driver1, coachVideoOn);
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
		
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver1);
		
		// Switch Coach to Member
		// Member verify coach chat text and Member send chat text to Coach
		// Member verify chat text in Member chat area
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver1, "8");
		driver.manage().window().maximize();
		wait(driver, "10");
		
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
		wait(driver, "8");
		driver1.manage().window().maximize();
		wait(driver1, "10");
		
		WebElement coachWelcomeToLiveSession = driver1.findElement(By.xpath("//div[@class='chat-message ng-scope']//div[2]/div[contains(text(),'"+inputMemberChatText+"')]"));
		verifyElementIsPresent(driver1, coachWelcomeToLiveSession);
		// Common method Coach Mute/Unmute Members button
		//com.zillion.qa.member.liveSessionSubCommonMethods.lectureSessionCoachMuteMembersButton(driver1,testData);
		// Switch Coach to Member
		
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver1);
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "5");
		driver.manage().window().maximize();
		wait(driver, "8");
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
		driver1.manage().window().maximize();
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
		
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver1);
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "5");
		driver.manage().window().maximize();
		wait(driver, "4");
		ZadoReports.add("TakeScreenShot","Screen shot for the Member Screen share","", "Screen shot took successfully for Member screen share",Objects.toString("", ""),LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));
		// Switch Member to Coach
		// Verify Stop sharing button is available
		driver.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "5");
		driver1.manage().window().maximize();
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
		
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver1);
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "5");
		driver.manage().window().maximize();
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
		wait(driver, "4" );
		com.zillion.qa.member.Dashboard.memberLogout(driver);
		
		driver.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "5");
		driver1.manage().window().maximize();
		wait(driver, "4");
		com.zillion.qa.member.liveSessionSubCommonMethods.coachesLogoutDifferentBrowser(driver1);
		// Driver Close
		driver.close();
		driver1.close();
		return ElementWait;
	}
	
	/**
	 * Name :Vigneshraj     
	 * Created Date: 11/Mar/2016  
	 * Modified Date:  
	 * Description : Create a common method to Lecture Z-Live session
	 * Ticket ID :   
	 * Required Inputs : Inputs Required from Test data from Spread sheet
	 * @throws Exception 
	 */
	public static String lectureZLiveLiveSessionForDifferentBrowser(WebDriver driver,String testData, String coachEmail1) throws Exception

	{

		// Browser launch for Member side
		// Test data seperation from spread sheet

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

		com.zillion.qa.member.liveSessionSubCommonMethods.liveSessionMemberLoginForLectureSession(driver,testData);

		// Common method Member to Attend Now URL

		com.zillion.qa.member.Dashboard.appendTextToURL(driver);
		wait( driver, "4" );
		Navigate.pageUp( driver );

		// Click on Attend now button and Verify the buttons and elements

		waitForElement( driver, OR.LECTURE_SESSION_MEMBER_ATTEND_NOW_BUTTON );
		WebElement memberAttendNowButton = driver.findElement(By.xpath(OR.LECTURE_SESSION_MEMBER_ATTEND_NOW_BUTTON));
		verifyElementIsPresent(driver, memberAttendNowButton);
		jsClickByXPath(driver, OR.LECTURE_SESSION_MEMBER_ATTEND_NOW_BUTTON);
		wait( driver, "7" );  

		// Common method to Allow system Plugins. Click Allow and Allow&Remember

		com.zillion.qa.member.liveSessionSubCommonMethods.allowPluginDifferentBrowser(driver);

		wait( driver, "5" );
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
		com.zillion.qa.member.liveSessionSubCommonMethods.memberGearSettings( driver );
		com.zillion.qa.member.liveSessionSubCommonMethods.retrieveLectureSessionHostNameWithProgramID( driver );
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
		com.zillion.qa.member.liveSessionSubCommonMethods.orberaCoacheLoginDifferentBrowser(driver1,coachEmail1);
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
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.shareSelectedDeviceDifferentBrowser(driver1);
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
		verifyElementIsPresent(driver1, memberOnlineHeader);
		verifyElementIsPresent(driver1, shareScreenButton);
		verifyElementIsPresent(driver1, endSessionButton);
		verifyElementIsPresent(driver1, coachMicOn);
		verifyElementIsPresent(driver1, coachVideoOn);
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
		
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver1);
		
		// Switch Coach to Member
		// Member verify coach chat text and Member send chat text to Coach
		// Member verify chat text in Member chat area
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver1, "8");
		driver.manage().window().maximize();
		wait(driver, "10");
		
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
		wait(driver, "8");
		driver1.manage().window().maximize();
		wait(driver1, "10");
		
		WebElement coachWelcomeToLiveSession = driver1.findElement(By.xpath("//div[@class='chat-message ng-scope']//div[2]/div[contains(text(),'"+inputMemberChatText+"')]"));
		verifyElementIsPresent(driver1, coachWelcomeToLiveSession);
		// Common method Coach Mute/Unmute Members button
		//com.zillion.qa.member.liveSessionSubCommonMethods.lectureSessionCoachMuteMembersButton(driver1,testData);
		// Switch Coach to Member
		
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver1);
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "5");
		driver.manage().window().maximize();
		wait(driver, "8");
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
		driver1.manage().window().maximize();
		wait(driver, "4");
		WebElement screenShareButton = driver1.findElement(By.xpath(OR.COACH_LECTURE_SESSION_SHARE_SCREEN_BUTTON));
		verifyElementIsPresent(driver1, screenShareButton);
		com.zillion.qa.member.liveSessionSubCommonMethods.getWindowToScreenShareAndInstallDifferentBrowser(driver);
//		jsClickByXPath(driver1, OR.COACH_LECTURE_SESSION_SHARE_SCREEN_BUTTON);
//		wait(driver, "5");
//		WebElement screenShareRefreshButton = driver1.findElement(By.xpath(OR.COACH_LECTURE_SESSION_SCREEN_SHARE_REFRESH_BUTTON));
//		verifyElementIsPresent(driver1, screenShareRefreshButton);
//		jsClickByXPath(driver1, OR.COACH_LECTURE_SESSION_SCREEN_SHARE_REFRESH_BUTTON);
//		wait(driver, "5");
//		jsClickByXPath(driver1, OR.COACH_LECTURE_SESSION_SHARE_SCREEN_IMAGE);
//		wait(driver, "5");
		// Switch Coach to Member
		
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver1);
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "5");
		driver.manage().window().maximize();
		wait(driver, "4");
		ZadoReports.add("TakeScreenShot","Screen shot for the Member Screen share","", "Screen shot took successfully for Member screen share",Objects.toString("", ""),LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));
		// Switch Member to Coach
		// Verify Stop sharing button is available
		driver.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "5");
		driver1.manage().window().maximize();
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
		
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver1);
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "5");
		driver.manage().window().maximize();
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
		wait(driver, "4" );
		com.zillion.qa.member.Dashboard.memberLogout(driver);
		
		driver.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "5");
		driver1.manage().window().maximize();
		wait(driver, "4");
		com.zillion.qa.member.liveSessionSubCommonMethods.coachesLogoutDifferentBrowser(driver1);
		// Driver Close
		driver.close();
		driver1.close();
		return ElementWait;
	}
	/**
	 * Name :     LEENA.P
	 * Created Date:   14/JULY/16
	 * Modified Date:   
	 * Description : Create a common method to verify the mail of the member once the 101 session is scheduled
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static void verifyOrberaMemberMail1on1Session(WebDriver driver, String mailid) 
	{	
		wait(driver, "3");	
		String Parent_Window = driver.getWindowHandle(); 
		Navigate.newTab(driver);
		if(mailid.contains("yopmail.com"))
		{
			Navigate.get(driver, Directory.yopmailurl);
			for (String Second_Window : driver.getWindowHandles())
			{
				driver.switchTo().window(Second_Window);	
				Navigate.maximize(driver);
				WebElement yopEmailTextbox= driver.findElement(By.xpath(OR.YOP_EMAIL_TEXTBOX));
				verifyElementIsPresent(driver, yopEmailTextbox);
				clear(yopEmailTextbox);
				sendKeys(yopEmailTextbox, mailid);
				WebElement checkInbox= driver.findElement(By.xpath(OR.YOP_EMAIL_CHECK_INBOX));
				verifyElementIsPresent(driver, checkInbox);
				jsClickByXPath(driver, OR.YOP_EMAIL_CHECK_INBOX);
				wait(driver, "5");
				WebElement iframe= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME1));
				Navigate.switchToFrame(driver, iframe);
				try
				{	
					WebElement generatedLink= driver.findElement(By.xpath(OR.ORBERA_MEMBER_1on1_MAIL_SCHEDULED));
					verifyElementIsPresent(driver, generatedLink);
					com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_MEMBER_1on1_MAIL_SCHEDULED);
					wait(driver, "5");
					if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
					{
						driver.get("javascript:document.getElementById('overridelink').click();"); 
						wait(driver, "5");
					}
					jsClickByXPath(driver, OR.ORBERA_MEMBER_1on1_MAIL_SCHEDULED);  
					wait(driver, "3");
					Navigate.switchToDefaultFrame(driver);
					WebElement iframe2= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME2));
					Navigate.switchToFrame(driver, iframe2);
					waitForElement(driver, OR.ORBERA_MEMBER_1on1_CONTENT_SCHEDULED);
				}
				catch(Exception e)
				{	

					WebElement generatedLink= driver.findElement(By.xpath(OR.ORBERA_MEMBER_1on1_MAIL));
					verifyElementIsPresent(driver, generatedLink);
					com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_MEMBER_1on1_MAIL);
					wait(driver, "5");
					if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
					{
						driver.get("javascript:document.getElementById('overridelink').click();"); 
						wait(driver, "5");
					}
					jsClickByXPath(driver, OR.ORBERA_MEMBER_1on1_MAIL);  
					wait(driver, "3");
					Navigate.switchToDefaultFrame(driver);
					WebElement iframe2= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME2));
					Navigate.switchToFrame(driver, iframe2);
					waitForElement(driver, OR.ORBERA_MEMBER_1on1_CONTENT);
				}
				Navigate.switchToDefaultFrame(driver);
			}
			WebElement iframe= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME1));
			Navigate.switchToFrame(driver, iframe);
			com.zillion.qa.coaches.Classes.deleteYopmailMsg(driver);
			Navigate.switchToDefaultFrame(driver); 
			Navigate.closeTab(driver);
			driver.switchTo().window(Parent_Window);	    
		}
		else if(mailid.contains("guerrillamail"))
		{
			Navigate.get(driver, Directory.Guerrillamailurl);
			for (String Second_Window : driver.getWindowHandles())
			{
				driver.switchTo().window(Second_Window);	
				Navigate.maximize(driver);
				wait(driver, "2");
				WebElement Guerrillamaildropdown= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_DROPDOWN));
				selectByValue(Guerrillamaildropdown, "guerrillamail.com");
				WebElement GuerrillaMailEditButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_EDIT_BUTTON));
				click(GuerrillaMailEditButton);
				waitForElement(driver, GUERRILLA_MAIL_TEXTBOX);
				WebElement GuerrillaMailTextbox= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_TEXTBOX));
				clearAndType(GuerrillaMailTextbox, mailid);
				WebElement GuerrillaMailSetButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_SET_BUTTON));
				click(GuerrillaMailSetButton);
				wait(driver, "5");
				try{
					com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_MEMBER_1on1_MAIL_SCHEDULED_GR);
					wait(driver, "5");

					if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
					{
						driver.get("javascript:document.getElementById('overridelink').click();"); 
						wait(driver, "5");
					}
					jsClickByXPath(driver, OR.ORBERA_MEMBER_1on1_MAIL_SCHEDULED_GR);  
					wait(driver, "3");
					waitForElement(driver, OR.ORBERA_MEMBER_1on1_CONTENT);  
				}
				catch(Exception e){
					com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_MEMBER_1on1_COACHING_SCHEDULED_GR);
					wait(driver, "5");

					if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
					{
						driver.get("javascript:document.getElementById('overridelink').click();"); 
						wait(driver, "5");
					}
					jsClickByXPath(driver, OR.ORBERA_MEMBER_1on1_COACHING_SCHEDULED_GR);  
					wait(driver, "3");
					waitForElement(driver, OR.ORBERA_MEMBER_1on1_CONTENT); 
				}
			}
			Navigate.closeTab(driver);
			driver.switchTo().window(Parent_Window);	
		}
	}
	public static void verifyOrberaMemberMail1on1SessionCancellation(WebDriver driver, String mailid) 
	{		
		wait(driver, "3");	
		String Parent_Window = driver.getWindowHandle(); 
		Navigate.newTab(driver);
		if(mailid.equalsIgnoreCase("yopmail.com"))
		{
			Navigate.get(driver, Directory.yopmailurl);
			for (String Second_Window : driver.getWindowHandles())
			{
				driver.switchTo().window(Second_Window);	
				Navigate.maximize(driver);
				WebElement yopEmailTextbox= driver.findElement(By.xpath(OR.YOP_EMAIL_TEXTBOX));
				verifyElementIsPresent(driver, yopEmailTextbox);
				clear(yopEmailTextbox);
				sendKeys(yopEmailTextbox, mailid);
				WebElement checkInbox= driver.findElement(By.xpath(OR.YOP_EMAIL_CHECK_INBOX));
				verifyElementIsPresent(driver, checkInbox);
				jsClickByXPath(driver, OR.YOP_EMAIL_CHECK_INBOX);
				wait(driver, "5");
				WebElement iframe= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME1));
				Navigate.switchToFrame(driver, iframe);
				WebElement generatedLink= driver.findElement(By.xpath(OR.ORBERA_MEMBER_1on1_MAIL_CANCELLATION));
				verifyElementIsPresent(driver, generatedLink);
				com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_MEMBER_1on1_MAIL_CANCELLATION);
				wait(driver, "5");
				if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
				{
					driver.get("javascript:document.getElementById('overridelink').click();"); 
					wait(driver, "5");
				}
				jsClickByXPath(driver, OR.ORBERA_MEMBER_1on1_MAIL_CANCELLATION);  
				wait(driver, "3");
				Navigate.switchToDefaultFrame(driver);
				WebElement iframe2= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME2));
				Navigate.switchToFrame(driver, iframe2);
				waitForElement(driver, OR.ORBERA_MEMBER_1on1_CONTENT_CANCELLATION);
				Navigate.switchToDefaultFrame(driver);
			}
			Navigate.closeTab(driver);
			driver.switchTo().window(Parent_Window);	    
		}
		else if(mailid.equalsIgnoreCase("guerrillamail"))
		{
			Navigate.get(driver, Directory.Guerrillamailurl);
			for (String Second_Window : driver.getWindowHandles())
			{
				driver.switchTo().window(Second_Window);	
				Navigate.maximize(driver);
				wait(driver, "2");
				WebElement Guerrillamaildropdown= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_DROPDOWN));
				selectByValue(Guerrillamaildropdown, "guerrillamail.com");
				WebElement GuerrillaMailEditButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_EDIT_BUTTON));
				click(GuerrillaMailEditButton);
				waitForElement(driver, GUERRILLA_MAIL_TEXTBOX);
				WebElement GuerrillaMailTextbox= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_TEXTBOX));
				clearAndType(GuerrillaMailTextbox, mailid);
				WebElement GuerrillaMailSetButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_SET_BUTTON));
				click(GuerrillaMailSetButton);
				wait(driver, "5");
				com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_MEMBER_1on1_MAIL_CANCELLATION);
				wait(driver, "5");
				if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
				{
					driver.get("javascript:document.getElementById('overridelink').click();"); 
					wait(driver, "5");
				}
				jsClickByXPath(driver, OR.ORBERA_MEMBER_1on1_MAIL_CANCELLATION);  
				wait(driver, "3");
				waitForElement(driver, OR.ORBERA_MEMBER_1on1_CONTENT_CANCELLATION);    
			}
			Navigate.closeTab(driver);
			driver.switchTo().window(Parent_Window);	
		}
	}	   		
	/**
	 * Name :     LEENA.P
	 * Created Date:   31/AUG/16
	 * Modified Date:   
	 * Description : Create a common method to verify the mail of the member once the lecture session is cancelled
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static void verifyOrberaMemberLectureCancellation(WebDriver driver, String mailid) 
	{	
		wait(driver, "3");	
		String Parent_Window = driver.getWindowHandle(); 
		Navigate.newTab(driver);
		if(mailid.contains("yopmail.com"))
		{
			Navigate.get(driver, Directory.yopmailurl);
			for (String Second_Window : driver.getWindowHandles())
			{
				driver.switchTo().window(Second_Window);	
				Navigate.maximize(driver);
				WebElement yopEmailTextbox= driver.findElement(By.xpath(OR.YOP_EMAIL_TEXTBOX));
				verifyElementIsPresent(driver, yopEmailTextbox);
				clear(yopEmailTextbox);
				sendKeys(yopEmailTextbox, mailid);
				WebElement checkInbox= driver.findElement(By.xpath(OR.YOP_EMAIL_CHECK_INBOX));
				verifyElementIsPresent(driver, checkInbox);
				jsClickByXPath(driver, OR.YOP_EMAIL_CHECK_INBOX);
				wait(driver, "5");
				WebElement iframe= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME1));
				Navigate.switchToFrame(driver, iframe);
				WebElement generatedLink= driver.findElement(By.xpath(OR.ORBERA_MEMBER_LECTURE_MAIL_CANCELLATION));
				verifyElementIsPresent(driver, generatedLink);
				com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_MEMBER_LECTURE_MAIL_CANCELLATION);
				wait(driver, "5");
				if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
				{
					driver.get("javascript:document.getElementById('overridelink').click();"); 
					wait(driver, "5");
				}
				jsClickByXPath(driver, OR.ORBERA_MEMBER_LECTURE_MAIL_CANCELLATION);  
				wait(driver, "3");
				Navigate.switchToDefaultFrame(driver);
				WebElement iframe2= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME2));
				Navigate.switchToFrame(driver, iframe2);
				waitForElement(driver, OR.ORBERA_MEMBER_LECTURE_CONTENT_CANCELLATION);
				Navigate.switchToDefaultFrame(driver);
				waitForElement(driver, YOPMAIL_DELETE);
				jsClickByXPath(driver, YOPMAIL_DELETE);
				wait(driver, "3");
				Navigate.closeTab(driver);
				driver.switchTo().window(Parent_Window);	    
			}
		}
		else if(mailid.contains("guerrillamail"))
		{
			Navigate.get(driver, Directory.Guerrillamailurl);
			for (String Second_Window : driver.getWindowHandles())
			{
				driver.switchTo().window(Second_Window);	
				Navigate.maximize(driver);
				wait(driver, "2");
				WebElement Guerrillamaildropdown= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_DROPDOWN));
				selectByValue(Guerrillamaildropdown, "guerrillamail.com");
				WebElement GuerrillaMailEditButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_EDIT_BUTTON));
				click(GuerrillaMailEditButton);
				waitForElement(driver, GUERRILLA_MAIL_TEXTBOX);
				WebElement GuerrillaMailTextbox= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_TEXTBOX));
				clearAndType(GuerrillaMailTextbox, mailid);
				WebElement GuerrillaMailSetButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_SET_BUTTON));
				click(GuerrillaMailSetButton);
				wait(driver, "5");
				com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_MEMBER_LECTURE_MAIL_CANCELLATION);
				wait(driver, "5");
				if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
				{
					driver.get("javascript:document.getElementById('overridelink').click();"); 
					wait(driver, "5");
				}
				jsClickByXPath(driver, OR.ORBERA_MEMBER_LECTURE_MAIL_CANCELLATION); 
				wait(driver, "3");
				waitForElement(driver, OR.ORBERA_MEMBER_LECTURE_CONTENT_CANCELLATION);  
			}
			Navigate.closeTab(driver);
			driver.switchTo().window(Parent_Window);	
		}
	}

	/**
	 * Name :     ABINAYA
	 * Created Date:   22/NOV/16
	 * Modified Date:   
	 * Description : Create a common method to verify the mail of the member once the lecture session is cancelled
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static void verifyOrberaPALectureCancellation(WebDriver driver, String mailid) 
	{	
		wait(driver, "3");	
		String Parent_Window = driver.getWindowHandle(); 
		Navigate.newTab(driver);
		if(mailid.contains("yopmail.com"))
		{
			Navigate.get(driver, Directory.yopmailurl);
			for (String Second_Window : driver.getWindowHandles())
			{
				driver.switchTo().window(Second_Window);	
				Navigate.maximize(driver);
				WebElement yopEmailTextbox= driver.findElement(By.xpath(OR.YOP_EMAIL_TEXTBOX));
				verifyElementIsPresent(driver, yopEmailTextbox);
				clear(yopEmailTextbox);
				sendKeys(yopEmailTextbox, mailid);
				WebElement checkInbox= driver.findElement(By.xpath(OR.YOP_EMAIL_CHECK_INBOX));
				verifyElementIsPresent(driver, checkInbox);
				jsClickByXPath(driver, OR.YOP_EMAIL_CHECK_INBOX);
				wait(driver, "5");
				WebElement iframe= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME1));
				Navigate.switchToFrame(driver, iframe);
				WebElement generatedLink= driver.findElement(By.xpath(OR.ORBERA_PA_LECTURE_MAIL_CANCELLATION));
				verifyElementIsPresent(driver, generatedLink);
				com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_PA_LECTURE_MAIL_CANCELLATION);
				wait(driver, "5");
				if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
				{
					driver.get("javascript:document.getElementById('overridelink').click();"); 
					wait(driver, "5");
				}
				jsClickByXPath(driver, OR.ORBERA_PA_LECTURE_MAIL_CANCELLATION);  
				wait(driver, "3");
				Navigate.switchToDefaultFrame(driver);
				WebElement iframe2= driver.findElement(By.xpath(OR.YOP_EMAIL_IFRAME2));
				Navigate.switchToFrame(driver, iframe2);
				waitForElement(driver, OR.ORBERA_MEMBER_LECTURE_CONTENT_CANCELLATION);
				Navigate.switchToDefaultFrame(driver);
				waitForElement(driver, YOPMAIL_DELETE);
				jsClickByXPath(driver, YOPMAIL_DELETE);
				wait(driver, "3");
				Navigate.closeTab(driver);
				driver.switchTo().window(Parent_Window);	    
			}
		}
		else if(mailid.contains("guerrillamail"))
		{
			Navigate.get(driver, Directory.Guerrillamailurl);
			for (String Second_Window : driver.getWindowHandles())
			{
				driver.switchTo().window(Second_Window);	
				Navigate.maximize(driver);
				wait(driver, "2");
				WebElement Guerrillamaildropdown= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_DROPDOWN));
				selectByValue(Guerrillamaildropdown, "guerrillamail.com");
				WebElement GuerrillaMailEditButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_EDIT_BUTTON));
				click(GuerrillaMailEditButton);
				waitForElement(driver, GUERRILLA_MAIL_TEXTBOX);
				WebElement GuerrillaMailTextbox= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_TEXTBOX));
				clearAndType(GuerrillaMailTextbox, mailid);
				WebElement GuerrillaMailSetButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_SET_BUTTON));
				click(GuerrillaMailSetButton);
				wait(driver, "5");
				com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,OR.ORBERA_MEMBER_LECTURE_MAIL_CANCELLATION);
				wait(driver, "5");
				if (com.zillion.qa.utils.Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
				{
					driver.get("javascript:document.getElementById('overridelink').click();"); 
					wait(driver, "5");
				}
				jsClickByXPath(driver, OR.ORBERA_MEMBER_LECTURE_MAIL_CANCELLATION); 
				wait(driver, "3");
				waitForElement(driver, OR.ORBERA_MEMBER_LECTURE_CONTENT_CANCELLATION);  
			}
			Navigate.closeTab(driver);
			driver.switchTo().window(Parent_Window);	
		}
	}

	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   07/MAR/17
	 * Modified Date:   
	 * Description : Orbera- 10n1 AddLive session for different browser
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */

	public static void orbera1on1AddLiveSession(WebDriver driver,String testData, String coachEmail1, String memberEmail1) throws Exception
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
		com.zillion.qa.member.liveSessionSubCommonMethods.orberaMemberLoginForDifferentBrowser(driver, memberEmail1);

		// Open robot for default
		com.zillion.qa.member.liveSessionSubCommonMethods.openRobotDefault(driver);

		// Append URL for member force attand the session
		com.zillion.qa.member.liveSessionSubCommonMethods.orberaMemberOneOnOneSessionAppendURL(driver);
		wait( driver, "4" );
		waitForElement(driver, MEMBER_ATTEND_NOW_BUTTON);
		WebElement memberAttendnowButton = driver.findElement(By.xpath(OR.MEMBER_ATTEND_NOW_BUTTON));
		click(memberAttendnowButton);
		wait( driver, "3" );

		// Allow plugin for the AddLive 
		com.zillion.qa.member.liveSessionSubCommonMethods.allowPluginDifferentBrowser(driver);
		wait( driver, "7" );
		WebElement memberMicOn = driver.findElement(By.xpath(OR.MEMBER_SESSION_MIC_ON));
		WebElement memberVideoOn = driver.findElement(By.xpath(OR.MEMBER_SESSION_VIDEO_ON));
		verifyElementIsPresent(driver, memberMicOn);
		verifyElementIsPresent(driver, memberVideoOn);
		WebElement memberChatTextbox = driver.findElement(By.xpath(OR.MEMBER_SESSION_CHAT_TEXTBOX));
		WebElement memberChatSendButton = driver.findElement(By.xpath(OR.MEMBER_SESSION_CHAT_SEND_BUTTON));

		// Verify member gear settings
		com.zillion.qa.member.liveSessionSubCommonMethods.memberGearSettings( driver );
		String sessiontime1=null;
		WebElement memberSessionTime = driver.findElement(By.xpath(OR.MEMBER_SESSION_TIME));
		String sessionsTime=memberSessionTime.getText();
		SimpleDateFormat ft = new SimpleDateFormat("EEE hhh:mm a");
		java.util.Date t=ft.parse(sessionsTime);
		ft.applyPattern("hh:mma");
		sessiontime1=ft.format(t).toLowerCase().trim();
		System.out.print(ft.format(t));
		System.out.print("Time"+sessiontime1);

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
		com.zillion.qa.member.liveSessionSubCommonMethods.orberaCoacheLoginDifferentBrowser(driver1, coachEmail1);
		wait(driver1, "3");
		com.zillion.qa.member.liveSessionSubCommonMethods.orberaCoachSessionAppendURL(driver1);
		wait(driver1, "3");
		WebElement coachUpcomingSessionTime = driver1.findElement(By.xpath("//tr//td[text()='"+sessiontime1+"']/following::td[text()='1on1']/following-sibling::td//following-sibling::td//following-sibling::td/following-sibling::td/div/a"));
		verifyElementIsPresent(driver1, coachUpcomingSessionTime); 
		click(coachUpcomingSessionTime);
		wait(driver1, "8");

		// Coach Append URl to attend the 1on1 AddLiveSession
		com.zillion.qa.member.liveSessionSubCommonMethods.allowPluginsForDifferentBrowser(driver1);

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

		// Coaches Logout
		com.zillion.qa.member.liveSessionSubCommonMethods.coachesLogoutDifferentBrowser(driver1);

		// Minimize the coach browser
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver1);

		// Switch Coach to member
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver1, "8");
		driver.manage().window().maximize();
		wait(driver, "10");
		
		WebElement ratingStar1 = driver.findElement(By.xpath(OR.MEMBER_COACH_STAR1));
		WebElement ratingStar2 = driver.findElement(By.xpath(OR.MEMBER_COACH_STAR2));
		WebElement ratingStar3 = driver.findElement(By.xpath(OR.MEMBER_COACH_STAR3));
		WebElement ratingStar4 = driver.findElement(By.xpath(OR.MEMBER_COACH_STAR4));
		WebElement ratingStar5 = driver.findElement(By.xpath(OR.MEMBER_COACH_STAR5));
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
		com.zillion.qa.member.Dashboard.memberLogout(driver);
		driver.close();
		driver1.close();
	}
	
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   07/MAR/17
	 * Modified Date:   
	 * Description : Orbera- 10n1 AddLive session for different browser
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */

	public static void orbera1on1ZLiveSession(WebDriver driver,String testData, String coachEmail1, String memberEmail1) throws Exception
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
		com.zillion.qa.member.liveSessionSubCommonMethods.orberaMemberLoginForDifferentBrowser(driver, memberEmail1);

		// Open robot for default
		com.zillion.qa.member.liveSessionSubCommonMethods.openRobotDefault(driver);

		// Append URL for member force attand the session
		com.zillion.qa.member.liveSessionSubCommonMethods.orberaMemberOneOnOneSessionAppendURL(driver);
		wait( driver, "6" );
		waitForElement(driver, MEMBER_ATTEND_NOW_BUTTON);
		WebElement memberAttendnowButton = driver.findElement(By.xpath(OR.MEMBER_ATTEND_NOW_BUTTON));
		click(memberAttendnowButton);
		wait( driver, "3" );

		// Allow plugin for the AddLive 
		com.zillion.qa.member.liveSessionSubCommonMethods.allowPluginDifferentBrowser(driver);
		wait( driver, "7" );
		WebElement memberMicOn = driver.findElement(By.xpath(OR.MEMBER_SESSION_MIC_ON));
		WebElement memberVideoOn = driver.findElement(By.xpath(OR.MEMBER_SESSION_VIDEO_ON));
		verifyElementIsPresent(driver, memberMicOn);
		verifyElementIsPresent(driver, memberVideoOn);
		WebElement memberChatTextbox = driver.findElement(By.xpath(OR.MEMBER_SESSION_CHAT_TEXTBOX));
		WebElement memberChatSendButton = driver.findElement(By.xpath(OR.MEMBER_SESSION_CHAT_SEND_BUTTON));

		// Verify member gear settings
		com.zillion.qa.member.liveSessionSubCommonMethods.memberGearSettings( driver );
		String sessiontime1=null;
		WebElement memberSessionTime = driver.findElement(By.xpath(OR.MEMBER_SESSION_TIME));
		String sessionsTime=memberSessionTime.getText();
		SimpleDateFormat ft = new SimpleDateFormat("EEE hhh:mm a");
		java.util.Date t=ft.parse(sessionsTime);
		ft.applyPattern("hh:mma");
		sessiontime1=ft.format(t).toLowerCase().trim();
		System.out.print(ft.format(t));
		System.out.print("Time"+sessiontime1);

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
		com.zillion.qa.member.liveSessionSubCommonMethods.orberaCoacheLoginDifferentBrowser(driver1, coachEmail1);
		wait(driver1, "3");
		com.zillion.qa.member.liveSessionSubCommonMethods.orberaCoachSessionAppendURL(driver1);
		wait(driver1, "3");
		WebElement coachUpcomingSessionTime = driver1.findElement(By.xpath("//tr//td[text()='"+sessiontime1+"']/following::td[text()='1on1']/following-sibling::td//following-sibling::td//following-sibling::td/following-sibling::td/div/a"));
		verifyElementIsPresent(driver1, coachUpcomingSessionTime); 
		click(coachUpcomingSessionTime);
		wait(driver1, "8");

		// Coach Append URl to attend the 1on1 AddLiveSession
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

		// Coaches Logout
		com.zillion.qa.member.liveSessionSubCommonMethods.coachesLogoutDifferentBrowser(driver1);

		// Minimize the coach browser
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver1);

		// Switch Coach to member
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver1, "8");
		driver.manage().window().maximize();
		wait(driver, "10");
		
		WebElement ratingStar1 = driver.findElement(By.xpath(OR.MEMBER_COACH_STAR1));
		WebElement ratingStar2 = driver.findElement(By.xpath(OR.MEMBER_COACH_STAR2));
		WebElement ratingStar3 = driver.findElement(By.xpath(OR.MEMBER_COACH_STAR3));
		WebElement ratingStar4 = driver.findElement(By.xpath(OR.MEMBER_COACH_STAR4));
		WebElement ratingStar5 = driver.findElement(By.xpath(OR.MEMBER_COACH_STAR5));
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
		com.zillion.qa.member.Dashboard.memberLogout(driver);
		driver.close();
		driver1.close();
	}
	
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   07/MAR/17
	 * Modified Date:   
	 * Description : Orbera- 10n1 AddLive session for different browser
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */

	public static void orberaPA1on1ZLiveSession(WebDriver driver,String testData, String coachEmail1, String memberEmail1) throws Exception
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
		com.zillion.qa.member.liveSessionSubCommonMethods.orberaMemberLoginForDifferentBrowser(driver, memberEmail1);

		// Open robot for default
		com.zillion.qa.member.liveSessionSubCommonMethods.openRobotDefault(driver);

		// Append URL for member force attand the session
		com.zillion.qa.member.liveSessionSubCommonMethods.orberaMemberOneOnOneSessionAppendURL(driver);
		wait( driver, "4" );
		waitForElement(driver, MEMBER_ATTEND_NOW_BUTTON);
		WebElement memberAttendnowButton = driver.findElement(By.xpath(OR.MEMBER_ATTEND_NOW_BUTTON));
		click(memberAttendnowButton);
		wait( driver, "3" );

		// Allow plugin for the AddLive 
		com.zillion.qa.member.liveSessionSubCommonMethods.allowPluginDifferentBrowser(driver);
		wait( driver, "7" );
		WebElement memberMicOn = driver.findElement(By.xpath(OR.MEMBER_SESSION_MIC_ON));
		WebElement memberVideoOn = driver.findElement(By.xpath(OR.MEMBER_SESSION_VIDEO_ON));
		verifyElementIsPresent(driver, memberMicOn);
		verifyElementIsPresent(driver, memberVideoOn);
		WebElement memberChatTextbox = driver.findElement(By.xpath(OR.MEMBER_SESSION_CHAT_TEXTBOX));
		WebElement memberChatSendButton = driver.findElement(By.xpath(OR.MEMBER_SESSION_CHAT_SEND_BUTTON));

		// Verify member gear settings
		com.zillion.qa.member.liveSessionSubCommonMethods.memberGearSettings( driver );
		String sessiontime1=null;
		WebElement memberSessionTime = driver.findElement(By.xpath(OR.MEMBER_SESSION_TIME));
		String sessionsTime=memberSessionTime.getText();
		SimpleDateFormat ft = new SimpleDateFormat("EEE hhh:mm a");
		java.util.Date t=ft.parse(sessionsTime);
		ft.applyPattern("hh:mma");
		sessiontime1=ft.format(t).toLowerCase().trim();
		System.out.print(ft.format(t));
		System.out.print("Time"+sessiontime1);

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
		com.zillion.qa.member.liveSessionSubCommonMethods.orberaPALoginDifferentBrowser(driver1, coachEmail1);
		wait(driver1, "3");
		com.zillion.qa.member.liveSessionSubCommonMethods.orberaCoachSessionAppendURL(driver1);
		wait(driver1, "3");
		WebElement coachUpcomingSessionTime = driver1.findElement(By.xpath("//tr//td[text()='"+sessiontime1+"']/following::td[text()='1on1']/following-sibling::td//following-sibling::td//following-sibling::td/following-sibling::td/div/button"));
		verifyElementIsPresent(driver1, coachUpcomingSessionTime); 
		click(coachUpcomingSessionTime);
		wait(driver1, "8");

		// Coach Append URl to attend the 1on1 AddLiveSession
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

		// Coaches Logout
		com.zillion.qa.member.liveSessionSubCommonMethods.coachesLogoutDifferentBrowser(driver1);

		// Minimize the coach browser
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver1);

		// Switch Coach to member
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver1, "8");
		driver.manage().window().maximize();
		wait(driver, "10");
		
		WebElement ratingStar1 = driver.findElement(By.xpath(OR.MEMBER_COACH_STAR1));
		WebElement ratingStar2 = driver.findElement(By.xpath(OR.MEMBER_COACH_STAR2));
		WebElement ratingStar3 = driver.findElement(By.xpath(OR.MEMBER_COACH_STAR3));
		WebElement ratingStar4 = driver.findElement(By.xpath(OR.MEMBER_COACH_STAR4));
		WebElement ratingStar5 = driver.findElement(By.xpath(OR.MEMBER_COACH_STAR5));
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
		com.zillion.qa.member.Dashboard.memberLogout(driver);
		driver.close();
		driver1.close();
	}
}