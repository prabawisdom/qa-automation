package com.zillion.qa.realappealmember;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

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
import com.zillion.qa.utils.Platform;

public class RAGroupLiveSession extends Manipulation implements OR
{

	public static String raGroupliveSession(WebDriver driver,String testData) throws Exception
	{
		String[] testData1 = testData.split(",");
		String inputBrowser=testData1[0];
		String inputCoachChatText=testData1[4];
		String inputMemberChatText=testData1[5];
		String inputCoachSessionCommentTextbox=testData1[8];
		String inputMemberCoachRatingTextbox=testData1[9];
		String inputCoachMemberTileMic=testData1[10];
		String inputMemberRaiseHand=testData1[11];
		String inputMuteMemberButton=testData1[2];

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

		// Common method for Coach Login with retrieved coach email as Username
		// Password with 3 attempts (Healthfleet2015, Healthfleet2016 and Zillion2016)
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raAppealGroupLiveSessionCoachLogin( driver1,testData );

		// Retrieve Available member in coach classes tab
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.classesRetrieveAvailableMember(driver1);


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

		// Common method to member Login 
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.realAppealGroupLiveSessionMemberLogin( driver);
		wait(driver, "10");

		// Common method Member to Attend Now URL
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raMemberGroupSessionAppendURL(driver);
		WebElement joinButton = driver.findElement(By.xpath(OR.RA_MEMBER_GROUP_SESSION_JOIN_BUTTON));
		verifyElementIsPresent( driver, joinButton );
		click(joinButton);
		wait( driver, "15" );

		// Common method to Allow system Plugins. Click Allow and Allow&Remember
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.pluginAlertAccept(driver);
		com.zillion.qa.member.liveSessionSubCommonMethods.allowPlugins(driver);

		// Retrieve Member session time
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raMemberSessionTime(driver);

		// Verify Member joined Session close button
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

		// Verify Member Mic button
		WebElement memberChatTextbox = driver.findElement(By.xpath(OR.MEMBER_SESSION_CHAT_TEXTBOX));
		WebElement memberSendButton = driver.findElement(By.xpath(OR.MEMBER_SESSION_CHAT_SEND_BUTTON));
		verifyElementIsPresent(driver, memberChatTextbox);
		verifyElementIsPresent(driver, memberSendButton);
		WebElement memberMicDisabled = driver.findElement(By.xpath(OR.RA_MEMBER_SESSION_MIC_OFF));
		verifyElementIsPresent(driver, memberMicDisabled);

		// Verify Member gear setting option
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.realAppealmemberGearSettings( driver );

		// Switch Member to coach
		driver.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "5");
		driver1.manage().window().maximize();
		wait(driver, "4");
		WebElement coachDashboardTab = driver1.findElement(By.xpath(OR.COACH_DASHBOARD_TAB));
		verifyElementIsPresent(driver1, coachDashboardTab);
		jsClickByXPath(driver1, OR.COACH_DASHBOARD_TAB);
		wait( driver1, "10" );

		// Append Coach URL to force attend the RA Group Live session 

		String getCoachCurrentURL= driver1.getCurrentUrl();
		String appendCoachCurrentURL= getCoachCurrentURL+"?attendnow";
		Navigate.get( driver1, appendCoachCurrentURL );
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raCoachUpcomingSessionTime(driver1);

		// Adding interval number 6 in the coach URL
		String getCoachURL= driver1.getCurrentUrl();
		String appendCoachURL= getCoachURL+"&intervalNumber=6";
		Navigate.get( driver1, appendCoachURL );
		wait(driver1, "5" );

		// Common method to Allow system Plugins.Click Allow and Allow&Remember
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.allowPlugins(driver);
		wait(driver, "5");

		// Verify all the field in Coach live session
		waitForElement( driver1, OR.COACH_RA_GROUP_SESSION_MEMBER_ONLINE_HEADER );
		WebElement memberOnlineHeader = driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_MEMBER_ONLINE_HEADER));
		WebElement shareScreenButton = driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_SHARE_SCREEN_BUTTON));
		WebElement endSessionButton = driver1.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_BUTTON));
		WebElement coachMicOn = driver1.findElement(By.xpath(OR.COACH_SESSION_MIC_ON));
		WebElement coachVideoOn = driver1.findElement(By.xpath(OR.COACH_SESSION_VIDEO_ON));
		WebElement coachChatTextbox = driver1.findElement(By.xpath(OR.COACH_SESSION_CHAT_TEXTBOX));
		WebElement coachSendButton = driver1.findElement(By.xpath(OR.COACH_SESSION_CHAT_SEND_BUTTON));
		WebElement coachTileMemberMicDisabled = driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_COACH_TILE_MEMBER_MIC_DISABLED));
		verifyElementIsPresent(driver1, memberOnlineHeader);
		verifyElementIsPresent(driver1, shareScreenButton);
		verifyElementIsPresent(driver1, endSessionButton);
		verifyElementIsPresent(driver1, coachMicOn);
		verifyElementIsPresent(driver1, coachVideoOn);
		verifyElementIsPresent(driver1, coachTileMemberMicDisabled);

		// Common method to verify Coach gear settings
		com.zillion.qa.member.liveSessionSubCommonMethods.coachGearSettings( driver1 );

		// Common method Coach Mute/Unmute Members button
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raGroupSessionCoachMuteMembersButton(driver1,testData);

		// Switch Coach to Member
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "5");
		driver.manage().window().setPosition(new Point(0, 0)); //IE issue
		wait(driver, "5");

		// Member verify Mic Enable/Disable 
		if ("CoachMembersUnMutebutton".equalsIgnoreCase(inputMuteMemberButton))  
		{
			verifyElementIsPresent(driver, memberMicDisabled);
		}
		else if ( "CoachMembersMutebutton".equalsIgnoreCase(inputMuteMemberButton) )
		{
			verifyElementIsPresent(driver, memberMicDisabled);
		} 

		// Switch Member to Coach
		// Coach verify Member chat text
		driver.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "5");
		driver1.manage().window().setPosition(new Point(0, 0));
		wait(driver, "4");

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
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raGroupSessionCoachMemberTileMicEnable(driver1,testData);

		// Common method get Session ID with current URL
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.getCurrentURL( driver1 );
		wait( driver, "10" );

		// Switch Coach to Member
		// Member verify coach chat text and Member send chat text to Coach
		// Member verify chat text in Member chat area
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "5");
		driver.manage().window().setPosition(new Point(0, 0)); 
		wait(driver, "5");
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

		// Common method Member Raise hand Enable/Disable
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raGroupSessionMemberRaiseHand(driver,testData);

		// Switch Member to Coach
		// Coach verify Member chat text
		driver.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "5");
		driver1.manage().window().setPosition(new Point(0, 0));
		wait(driver, "4");
		WebElement coachWelcomeToLiveSession = driver1.findElement(By.xpath("//div[@class='chat-message ng-scope']//div[2]/div[contains(text(),'"+inputMemberChatText+"')]"));
		verifyElementIsPresent(driver1, coachWelcomeToLiveSession); 

		// Verify Member Raise hand enable/disable
		if ("MemberRaiseHandEnable".equalsIgnoreCase(inputMemberRaiseHand))  
		{
			WebElement memberLowerHand = driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_LOWER_HAND_SYMBOL));
			verifyElementIsPresent(driver1, memberLowerHand);
			WebElement lowerAllHandsButton = driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_LOWER_ALL_HANDS_BUTTON));
			verifyElementIsPresent(driver1, lowerAllHandsButton);
		}
		else if ( "MemberRaiseHandDisable".equalsIgnoreCase(inputMemberRaiseHand) )
		{
			WebElement memberLowerHand = driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_LOWER_HAND_SYMBOL));
			verifyElementIsNotPresent(driver1, memberLowerHand);
			WebElement lowerAllHandsButton = driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_LOWER_ALL_HANDS_BUTTON));
			verifyElementIsNotPresent(driver1, lowerAllHandsButton);
		}

		// Switch Member to Coach
		driver.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "5");
		driver1.manage().window().setPosition(new Point(0, 0));
		wait(driver, "4");

		// Coach share screen to member
		driver.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "5");
		driver1.manage().window().setPosition(new Point(0, 0));
		wait(driver, "4");
		WebElement screenShareButton = driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_SHARE_SCREEN_BUTTON));
		verifyElementIsPresent(driver1, screenShareButton);
		jsClickByXPath(driver1, OR.COACH_RA_GROUP_SESSION_SHARE_SCREEN_BUTTON);
		wait(driver, "5");
		WebElement screenShareRefreshButton = driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_SCREEN_SHARE_REFRESH_BUTTON));
		verifyElementIsPresent(driver1, screenShareRefreshButton);
		jsClickByXPath(driver1, OR.COACH_RA_GROUP_SESSION_SCREEN_SHARE_REFRESH_BUTTON);
		wait(driver, "5");
		jsClickByXPath(driver1, OR.COACH_RA_GROUP_SESSION_SHARE_SCREEN_IMAGE);
		wait(driver, "5");

		// Switch Coach to Member
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "5");
		driver.manage().window().setPosition(new Point(0, 0));
		wait(driver, "4");

		// Take ScreenShot from the Member Side
		ZadoReports.add("TakeScreenShot","Screen shot for the Member Screen share","", "Screen shot took successfully for Member screen share",Objects.toString("", ""),LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));

		// Switch Member to Coach
		// Verify Stop sharing button is available
		driver.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "5");
		driver1.manage().window().setPosition(new Point(0, 0));
		wait(driver, "4");
		WebElement screenStopSharingButton = driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_STOP_SHARING_SCREEN));
		verifyElementIsPresent(driver1, screenStopSharingButton);
		jsClickByXPath(driver1, OR.COACH_RA_GROUP_SESSION_STOP_SHARING_SCREEN);
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

		// Play,Pause,Backward,Forward,Refresh and Eject Session 6 video
		jsClickByXPath(driver1, OR.COACH_RA_GROUP_SESSION_VIDEO_BUTTON);   
		wait( driver1, "2" );  
		WebElement videoLink= driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_VIDEO));
		verifyElementIsPresent(driver1, videoLink);
		click(videoLink);
		wait(driver1, "10" );
		WebElement play_button= driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_PLAY_VIDEO_BUTTON));
		verifyElementIsPresent(driver1, play_button);
		WebElement Pause_button= driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_PAUSE_BUTTON));
		verifyElementIsPresent(driver1, Pause_button);
		WebElement Backward_btn= driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_JUMP_BACK_THIRTY_SECONDS_BUTTON));
		verifyElementIsPresent(driver1, Backward_btn);
		WebElement forward_btn= driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_JUMP_FORWARD_THIRTY_SECOND_BUTTON));
		verifyElementIsPresent(driver1, forward_btn);
		WebElement Refresh_btn= driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_RESTART_FROM_THE_BEGINNING_BUTTON));
		verifyElementIsPresent(driver1, Refresh_btn);
		WebElement Eject_btn= driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_EJECT_VIDEO_BUTTON));
		verifyElementIsPresent(driver1, Eject_btn);
		wait( driver1, "5" );
		click(play_button);
		wait( driver1, "5" );

		// Switch Coach to Member
		// Take screen shot for coach video
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "5");
		driver.manage().window().setPosition(new Point(0, 0));
		wait(driver, "10");

		// Take Screenshot on Member Side
		ZadoReports.add("TakeScreenShot","Screen shot for the Member Screen Video","", "Screen shot took successfully for Member screen video",Objects.toString("", ""),LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));

		// Switch Member to Coach
		driver.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "5");
		driver1.manage().window().setPosition(new Point(0, 0));
		wait(driver, "4"); 
		click(Pause_button);
		wait(driver1, "10" );
		click( forward_btn);
		wait(driver1, "10" );
		click( Backward_btn);
		wait(driver1, "10" );
		click( Refresh_btn);
		wait(driver1, "10" );
		click( Eject_btn);
		wait(driver1, "10" );

		// Verify all the fields and text in session Quiz and contract link
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raGroupSessionQuizLink( driver1);
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raGroupSessionContractLink( driver1);
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raGroupSessionGuideLink( driver1);
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raGroupSessionScriptLink( driver1);

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
		waitForElement(driver1, OR.COACH_RA_GROUP_SESSION_SESSION_COMPLETE_HEADER_TEXT );
		WebElement coachSessionComplete = driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_SESSION_COMPLETE_HEADER_TEXT));
		WebElement coachGroupSessionEndedText = driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_YOUR_GROUP_SESSION_ENDED_TEXT));
		WebElement coachSessionCommentTextbox = driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_SESSION_COMMENTS_TEXTBOX));
		WebElement coachSessionEndSessionRadioButton= driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_END_SESSION_RADIO_BUTTON));
		WebElement coachSessionLeaveSessionRadioButton = driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_LEAVE_THE_SESSION_AND_JOIN_AGAIN_RADIO_BUTTON));
		WebElement coachSessionCancelandResumeSessionRadioButton = driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_CANCEL_AND_RESUME_THE_SESSION_RADIO_BUTTON));
		WebElement coachSessionEndSessionText = driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_END_SESSION_TEXT));
		WebElement coachSessionLeaveSessionText = driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_LEAVE_THE_SESSION_AND_JOIN_AGAIN_TEXT));
		WebElement coachSessionCancelandResumeText= driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_CANCEL_AND_RESUME_THE_SESSION_TEXT));
		WebElement coachEndSessionDoneButton = driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_MEMBER_DONE_BUTTON));
		WebElement coachSessionPleaseSelectOptions = driver1.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_PLEASE_SELECT_ONE_OF_THE_OPTIONS_ERROR_MSG));
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
		jsClickByXPath(driver1, OR.COACH_RA_GROUP_SESSION_MEMBER_DONE_BUTTON);
		wait( driver, "3" );
		verifyElementIsPresent(driver1, coachSessionPleaseSelectOptions);
		jsClickByXPath(driver1, OR.COACH_RA_GROUP_SESSION_SESSION_COMMENTS_TEXTBOX);
		actionType(driver1,coachSessionCommentTextbox,inputCoachSessionCommentTextbox);
		wait( driver, "2" );
		jsClickByXPath(driver1, OR.COACH_RA_GROUP_SESSION_END_SESSION_RADIO_BUTTON);
		jsClickByXPath(driver1, OR.COACH_RA_GROUP_SESSION_MEMBER_DONE_BUTTON);
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

		// Verify Upcoming Group Session Header and Count down of next group session
		// Verify 1on1 should not be available when viewing profile page of member
		WebElement upcomingGroupSessionstitle = driver.findElement(By.xpath(OR.RA_MEMBER_GROUP_SESSION_UPCOMING_GROUP_SESSIONS_TITLE));
		verifyElementIsPresent(driver, upcomingGroupSessionstitle);
		WebElement upcomingGroupSessionsCountDown = driver.findElement(By.xpath(OR.RA_MEMBER_GROUP_SESSION_UPCOMING_GROUP_SESSIONS_COUNTDOWN));
		verifyElementIsPresent(driver, upcomingGroupSessionsCountDown);
		WebElement memberProfileLink = driver.findElement(By.xpath(OR.RA_MEMBER_GROUP_SESSION_PROFILE_LINK));
		verifyElementIsPresent(driver, memberProfileLink);
		jsClickByXPath(driver, OR.RA_MEMBER_GROUP_SESSION_PROFILE_LINK);
		wait( driver, "5" );
		WebElement scheduleOneOnOneSessionNotAvailable = driver.findElement(By.xpath(OR.RA_MEMBER_GROUP_SESSION_1ON1_SESSION_YOUR_SCHEDULE_HEADER));
		verifyElementIsPresent(driver, scheduleOneOnOneSessionNotAvailable);

		// Common method to retrieve Member Account ID
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raGroupSessionRetrieveMemberAccountId( driver);

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
	 * Created Date:   24/MAY/16
	 * Modified Date:   
	 * Description : RA- Group ZLive session for different browser
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */

	public static void raGroupZLiveSession(WebDriver driver,String testData, String coachEmail1, String memberEmail1) throws Exception
	{
		String[] testData1 = testData.split(",");
		String coachBrowser=testData1[0];
		String memberBrowser=testData1[1];
		String coachChattext=testData1[2];
		String memberChattext=testData1[3];
		String coachMicEnableOrDisabled=testData1[4];
		String coachVideoEnableOrDisabled=testData1[5];
		String MemberRaiseHand=testData1[6];
		String CoachMemberTileMicEnable=testData1[7];

		// Coach Login in Browser Launch 
		WebDriver driver1 = null;

		if ("firefox".equalsIgnoreCase(coachBrowser) ) 
		{
			driver =  new FirefoxBrowser().getDriver();
			driver.manage().window().maximize();
		}
		else if ( "ie".equalsIgnoreCase(coachBrowser) )
		{
			driver = ( WebDriver ) new IEBrowser().getDriver();
			driver.manage().window().maximize();
		}
		else if ( "safari".equalsIgnoreCase(coachBrowser)) 
		{
			driver = new SafariBrowser().getDriver();
			driver.manage().window().maximize();
		}
		wait( driver, "4" );

		// Coach Login
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.coachLoginWithReferenceStep(driver, coachEmail1);
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver);

		// Browser launch for Member side
		// Test data seperation from spread sheet
		if ("firefox".equalsIgnoreCase(memberBrowser) ) 
		{
			driver1 =  new FirefoxBrowser().getDriver();
			driver1.manage().window().maximize();
		}
		else if ( "ie".equalsIgnoreCase(memberBrowser) )
		{
			driver1 = ( WebDriver ) new IEBrowser().getDriver();
			driver1.manage().window().maximize();
		}
		else if ( "chrome".equalsIgnoreCase(memberBrowser)) 
		{
			driver1 = new ChromeBrowser().getDriver();
			driver1.manage().window().maximize();
		}
		else if ( "safari".equalsIgnoreCase(memberBrowser)) 
		{
			driver1 = new SafariBrowser().getDriver();
			driver1.manage().window().maximize();
		}

		// Member Login with different browser
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raGroupZLiveSessionMemberLoginDifferentBrowser(driver1, memberEmail1);

		try
		{
			waitForElement(driver1, TERMS_AND_CONDITION_AGREE_BUTTON);
			WebElement agreeButton = driver1.findElement(By.xpath(OR.TERMS_AND_CONDITION_AGREE_BUTTON));
			verifyElementIsPresent(driver1, agreeButton);
			jsClickByXPath(driver1, TERMS_AND_CONDITION_AGREE_BUTTON);
		}
		catch(Exception e)
		{
			
		}
		Robot rb = new Robot();
		wait( driver, "4" );
		rb.keyPress(KeyEvent.VK_A);
		rb.keyRelease(KeyEvent.VK_A);
		// Open robot for default
		//com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.openRobotDefault( driver);

		/*// To handle tracker server unavailable popup after Login
		com.zillion.qa.realappealmember.member.handleTrackerServerUnavailable( driver1);

		// To handle browser support popup
		com.zillion.qa.realappealmember.member.browserSupportMatrixRA( driver1 );
		 */
		// Append Member URl 
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raMemberGroupSessionAppendURL(driver1);

		waitForElement(driver1, RA_MEMBER_GROUP_SESSION_JOIN_BUTTON);
		WebElement joinButton = driver1.findElement(By.xpath(OR.RA_MEMBER_GROUP_SESSION_JOIN_BUTTON));
		click(joinButton);
		wait(driver1, "3");
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raEnterWeightDuringLiveSession( driver1 );
		wait(driver1, "7");

		// Allow plugin for ZLiveSession
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.allowGroupZLivePlugin(driver1);

		// Retrieve the group session attended time
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raMemberSessionTime(driver1);

		waitForElement(driver1, RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CONNECTED_TEXT);
		WebElement closeButton = driver1.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CLOSE_BUTTON));
		WebElement memberChatTextbox = driver1.findElement(By.xpath(OR.RA_MEMBER_LIVESESSION_CHAT_TEXTBOX));
		WebElement memberChatSendButton = driver1.findElement(By.xpath(OR.RA_MEMBER_LIVESESSION_SEND_BUTTON));
		verifyElementIsPresent(driver1, closeButton);
		verifyElementIsPresent(driver1, memberChatTextbox);
		verifyElementIsPresent(driver1, memberChatSendButton);
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver1);

		// Switch Member to coach
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver1, "4");
		driver.manage().window().maximize();
		wait(driver, "4");

		// Append Coach URL
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raMemberGroupSessionAppendURL(driver);
		wait(driver, "5");
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raCoachUpcomingSessionTimeDifferentBrowser(driver);
//		Navigate.refreshPage(driver);
		Screen Allow1=new Screen(); 
		wait( driver, "8" );
		Pattern image1 = new Pattern(Directory.uploadFilePath+"BlankArea.PNG");
		Allow1.wait(image1, 15);
		Allow1.click(image1);
		wait( driver, "3" );
		wait(driver, "6");
		/*WebElement providerHeader = driver1.findElement(By.xpath(OR.RA_PROGRAM_ADMIN_REAL_APPEAL_LOGO));
		verifyElementIsPresent(driver, providerHeader);
		mouseOver(driver, providerHeader);
		jsClickByXPath(driver, RA_PROGRAM_ADMIN_REAL_APPEAL_LOGO);*/
		String OSName=Platform.OS.toUpperCase();
		if(OSName.contains("WINDOWS"))
		{
			Screen Allow=new Screen(); 
			wait( driver, "8" );
			Pattern image = new Pattern(Directory.uploadFilePath+"SendButton.PNG");
			Allow.wait(image, 15);
			Allow.click(image);
			wait( driver, "3" );
		}
		else if(OSName.contains("MAC"))
		{
			wait( driver, "4" );
			rb.keyPress(KeyEvent.VK_CONTROL); 
			rb.keyPress(KeyEvent.VK_S);
			rb.keyRelease(KeyEvent.VK_CONTROL); 
			rb.keyRelease(KeyEvent.VK_S);
			wait( driver, "8" ); 
			rb.keyPress(KeyEvent.VK_S);
			rb.keyRelease(KeyEvent.VK_S);
		}
		//com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.shareSelectedDevice(driver);

		wait(driver, "10");
		WebElement shareScreenButton = driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_SHARE_SCREEN_BUTTON));
		WebElement endSessionButton = driver.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_BUTTON));
		WebElement coachMicOn = driver.findElement(By.xpath(OR.COACH_SESSION_MIC_ON));
		WebElement coachVideoOn = driver.findElement(By.xpath(OR.COACH_SESSION_VIDEO_ON));
		WebElement coachChatTextbox = driver.findElement(By.xpath(OR.COACH_SESSION_CHAT_TEXTBOX));
		WebElement coachSendButton = driver.findElement(By.xpath(OR.COACH_SESSION_CHAT_SEND_BUTTON));
		WebElement coachTileMemberMicDisabled = driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_COACH_TILE_MEMBER_MIC_DISABLED));
		verifyElementIsPresent(driver, coachChatTextbox);
		verifyElementIsPresent(driver, coachSendButton);
		verifyElementIsPresent(driver, shareScreenButton);
		verifyElementIsPresent(driver, endSessionButton);
		verifyElementIsPresent(driver, coachMicOn);
		verifyElementIsPresent(driver, coachVideoOn);
		verifyElementIsPresent(driver, coachTileMemberMicDisabled);
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.groupZLiveSessionCoachGearSettings(driver);
		actionType(driver, coachChatTextbox, coachChattext);
		wait(driver, "1");
		actionClick(driver, coachSendButton);
		wait(driver, "2");
		WebElement coachChatTextToMember = driver.findElement(By.xpath("//div[@class='chat-message ng-scope']//div[2]/div[contains(text(),'"+coachChattext+"')]"));
		verifyElementIsPresent(driver, coachChatTextToMember);
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver);

		// Switch Coach to Member
		driver.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "4");
		driver1.manage().window().maximize();
		wait(driver1, "4");

		// Verify Member Mic button
		WebElement memberSendButton = driver1.findElement(By.xpath(OR.MEMBER_SESSION_CHAT_SEND_BUTTON));
		verifyElementIsPresent(driver1, memberChatTextbox);
		verifyElementIsPresent(driver1, memberSendButton);
		WebElement memberMicDisabled = driver1.findElement(By.xpath(OR.RA_MEMBER_SESSION_MIC_OFF));
		verifyElementIsPresent(driver1, memberMicDisabled);

		actionType(driver1, memberChatTextbox, memberChattext);
		wait(driver1, "1");
		actionClick(driver1, memberChatSendButton);
		wait(driver1, "2");
		WebElement memberChatTextToCoach = driver1.findElement(By.xpath("//div[@class='chat-message ng-scope']//div[2]/div[contains(text(),'"+memberChattext+"')]"));
		verifyElementIsPresent(driver1, memberChatTextToCoach);
		WebElement memberVerifyChatTextFromCoach = driver1.findElement(By.xpath("//div[@class='chat-message ng-scope']//div[2]/div[contains(text(),'"+coachChattext+"')]"));
		verifyElementIsPresent(driver1, memberVerifyChatTextFromCoach);

		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver1, "4" );
		driver.manage().window().maximize();
		driver.manage().window().setPosition(new Point(0, 0));
		wait(driver, "4");

		if ("CoachMicEnable".equalsIgnoreCase(coachMicEnableOrDisabled) ) 
		{ 
			try
			{
				jsClickByXPath(driver, OR.COACH_SESSION_MIC_OFF);
				wait(driver, "2");
				verifyElementIsPresent(driver, coachMicOn);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver, OR.COACH_SESSION_MIC_ON);
				wait(driver, "2");
				jsClickByXPath(driver, OR.COACH_SESSION_MIC_OFF);
				wait(driver, "2");
				verifyElementIsPresent(driver, coachMicOn);
			}
		}

		else if ( "CoachMicDisable".equalsIgnoreCase(coachMicEnableOrDisabled) )
		{
			try
			{
				jsClickByXPath(driver, OR.COACH_SESSION_MIC_ON);
				wait(driver, "2");
				WebElement coachMicOff = driver.findElement(By.xpath(OR.COACH_SESSION_MIC_OFF));
				verifyElementIsPresent(driver, coachMicOff);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver, OR.COACH_SESSION_MIC_OFF);
				wait(driver, "2");
				jsClickByXPath(driver, OR.COACH_SESSION_MIC_ON);
				WebElement coachMicOff = driver.findElement(By.xpath(OR.COACH_SESSION_MIC_OFF));
				wait(driver, "2");
				verifyElementIsPresent(driver, coachMicOff);
			}
		}

		if ("CoachVideoEnable".equalsIgnoreCase(coachVideoEnableOrDisabled) ) 
		{ 
			try
			{
				jsClickByXPath(driver, OR.COACH_SESSION_VIDEO_OFF);
				wait(driver, "2");
				verifyElementIsPresent(driver, coachVideoOn);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver, OR.COACH_SESSION_VIDEO_ON);
				wait(driver, "2");
				jsClickByXPath(driver, OR.COACH_SESSION_VIDEO_OFF);
				wait(driver, "2");
				verifyElementIsPresent(driver, coachVideoOn);
			}

		}

		else if ( "CoachVideoDisable".equalsIgnoreCase(coachVideoEnableOrDisabled) )
		{
			try
			{
				jsClickByXPath(driver, OR.COACH_SESSION_VIDEO_ON);
				wait(driver, "2");
				WebElement coachVideoOff = driver.findElement(By.xpath(OR.COACH_SESSION_VIDEO_OFF));
				verifyElementIsPresent(driver, coachVideoOff);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver, OR.COACH_SESSION_VIDEO_OFF);
				wait(driver, "2");
				jsClickByXPath(driver, OR.COACH_SESSION_VIDEO_ON);
				WebElement coachVideoOff = driver.findElement(By.xpath(OR.COACH_SESSION_VIDEO_OFF));
				wait(driver, "2");
				verifyElementIsPresent(driver, coachVideoOff);
			}
		}

		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver);
		driver.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "4" );
		driver1.manage().window().maximize();
		wait(driver1, "4");

		if ("MemberRaiseHandEnable".equalsIgnoreCase(MemberRaiseHand) ) 
		{
			jsClickByXPath(driver1, OR.MEMBER_RA_GROUP_SESSION_RAISE_HAND);
			WebElement memberLowerHand = driver1.findElement(By.xpath(OR.MEMBER_RA_GROUP_SESSION_LOWER_HAND));
			verifyElementIsPresent(driver1, memberLowerHand);
		}
		else if ( "MemberRaiseHandDisable".equalsIgnoreCase(MemberRaiseHand) )
		{
			jsClickByXPath(driver1, OR.MEMBER_RA_GROUP_SESSION_RAISE_HAND);
			wait(driver1, "2");
			jsClickByXPath(driver1, OR.MEMBER_RA_GROUP_SESSION_LOWER_HAND);
			WebElement memberRaiseHand = driver1.findElement(By.xpath(OR.MEMBER_RA_GROUP_SESSION_RAISE_HAND));
			verifyElementIsPresent(driver1, memberRaiseHand);
		}

		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver1, "4" );
		driver.manage().window().maximize();
		driver.manage().window().setPosition(new Point(0, 0));
		wait(driver, "4");

		if ("MemberRaiseHandEnable".equalsIgnoreCase(MemberRaiseHand))  
		{
			WebElement memberLowerHand = driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_LOWER_HAND_SYMBOL));
			verifyElementIsPresent(driver, memberLowerHand);
			WebElement lowerAllHandsButton = driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_LOWER_ALL_HANDS_BUTTON));
			verifyElementIsPresent(driver, lowerAllHandsButton);
		}
		else if ( "MemberRaiseHandDisable".equalsIgnoreCase(MemberRaiseHand) )
		{
			WebElement memberLowerHand = driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_LOWER_HAND_SYMBOL));
			verifyElementIsNotPresent(driver, memberLowerHand);
			WebElement lowerAllHandsButton = driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_LOWER_ALL_HANDS_BUTTON));
			verifyElementIsNotPresent(driver, lowerAllHandsButton);
		}

		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.getWindowToScreenShareAndInstallDifferentBrowser(driver);
		//Manipulation.switchWindow(driver);
		Navigate.refreshPage(driver);
		wait(driver, "7");
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.shareSelectedDevice(driver);
		wait(driver, "5");
		waitForElement(driver, COACH_RA_GROUP_SESSION_SHARE_SCREEN_BUTTON);
		jsClickByXPath(driver, COACH_RA_GROUP_SESSION_SHARE_SCREEN_BUTTON);
		wait(driver, "3");
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.selectScreenForGroupSessionZLive(driver);
		wait(driver, "3");

		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver);
		driver.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "4" );
		driver1.manage().window().maximize();
		wait(driver1, "4");

		com.zillion.qa.member.liveSessionSubCommonMethods.screenShotForMemberLiveSession( driver1 );
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver1, "4" );
		driver.manage().window().maximize();
		wait(driver, "4");

		WebElement screenStopSharingButton = driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_STOP_SHARING_SCREEN));
		verifyElementIsPresent(driver, screenStopSharingButton);
		jsClickByXPath(driver, OR.COACH_RA_GROUP_SESSION_STOP_SHARING_SCREEN);
		wait(driver, "2");

		// Check Browser Icon Coach Toolbar compare with Input member Browser
		if ("firefox".equalsIgnoreCase(memberBrowser) )  
		{
			WebElement firefoxBrowserIcon = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_FIREFOX_BROWSER_ICON));
			verifyElementIsPresent(driver, firefoxBrowserIcon);
			jsClickByXPath(driver, OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_FIREFOX_BROWSER_ICON);
			wait( driver, "3" );
		}
		else if ( "ie".equalsIgnoreCase(memberBrowser) )
		{
			WebElement ieBrowserIcon = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_IE_BROWSER_ICON));
			verifyElementIsPresent(driver, ieBrowserIcon);
			jsClickByXPath(driver, OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_IE_BROWSER_ICON);
			wait( driver, "3" );
		}
		else if ("chrome".equalsIgnoreCase(memberBrowser) )
		{
			WebElement ieBrowserIcon = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_CHROME_BROWSER_ICON));
			verifyElementIsPresent(driver, ieBrowserIcon);
			jsClickByXPath(driver, OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_CHROME_BROWSER_ICON);
			wait( driver, "3" );
		}
		WebElement memberAccountID = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_MEMBER_ID));
		WebElement popUpOkButton = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_OK_BUTTON));
		WebElement memberIDHeader = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_MEMBER_ID_HEADER));
		WebElement sessionIDHeader = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_SESSION_ID_HEADER));
		WebElement userAgentHeader = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_USER_AGENT_HEADER));
		WebElement versionHeader = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_LIVESESSION_VERSION_HEADER));
		WebElement micOptionHeader = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_MICROPHONE_OPTIONS_HEADER));
		WebElement micSelectedHeader = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_MICROPHONE_SELECTED_HEADER));
		WebElement speakerOptionHeader = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_SPEAKER_OPTIONS_HEADER));
		WebElement speakerSelectedHeader = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_SPEAKER_SELECTED_HEADER));
		verifyElementIsPresent(driver, memberAccountID);
		verifyElementIsPresent(driver, popUpOkButton);
		verifyElementIsPresent(driver, memberIDHeader);
		verifyElementIsPresent(driver, sessionIDHeader);
		verifyElementIsPresent(driver, userAgentHeader);
		verifyElementIsPresent(driver, versionHeader);
		verifyElementIsPresent(driver, micOptionHeader);
		verifyElementIsPresent(driver, micSelectedHeader);
		verifyElementIsPresent(driver, speakerOptionHeader);
		verifyElementIsPresent(driver, speakerSelectedHeader);
		jsClickByXPath(driver, OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_OK_BUTTON);
		wait( driver1, "3" );  

		String getCoachURL= driver.getCurrentUrl();
		String appendCoachURL= getCoachURL+"&intervalNumber=6";
		Navigate.get( driver, appendCoachURL );
		wait(driver, "5" );
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.shareSelectedDevice(driver);
		// Play,Pause,Backward,Forward,Refresh and Eject Session 6 video
		jsClickByXPath(driver, OR.COACH_RA_GROUP_SESSION_VIDEO_BUTTON);   
		wait( driver, "2" );  
		WebElement videoLink= driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_VIDEO));
		verifyElementIsPresent(driver, videoLink);
		click(videoLink);
		wait(driver, "10" );
		WebElement play_button= driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_PLAY_VIDEO_BUTTON));
		verifyElementIsPresent(driver, play_button);
		WebElement Pause_button= driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_PAUSE_BUTTON));
		verifyElementIsPresent(driver, Pause_button);
		WebElement Backward_btn= driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_JUMP_BACK_THIRTY_SECONDS_BUTTON));
		verifyElementIsPresent(driver, Backward_btn);
		WebElement forward_btn= driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_JUMP_FORWARD_THIRTY_SECOND_BUTTON));
		verifyElementIsPresent(driver, forward_btn);
		WebElement Refresh_btn= driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_RESTART_FROM_THE_BEGINNING_BUTTON));
		verifyElementIsPresent(driver, Refresh_btn);
		WebElement Eject_btn= driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_EJECT_VIDEO_BUTTON));
		verifyElementIsPresent(driver, Eject_btn);
		wait( driver, "5" );
		click(play_button);
		wait( driver, "5" );

		// Switch Coach to Member
		// Take screen shot for coach video
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver);
		driver.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "4" );
		driver1.manage().window().maximize();
		wait(driver1, "4");

		// Take Screenshot on Member Side
		ZadoReports.add("TakeScreenShot","Screen shot for the Member Screen Video","", "Screen shot took successfully for Member screen video",Objects.toString("", ""),LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));

		// Switch Member to Coach
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver1, "4" );
		driver.manage().window().maximize();
		wait(driver, "4");

		click( Eject_btn);
		wait(driver1, "3" );

		// Verify all the fields and text in session Quiz and contract link
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raGroupSessionQuizLinkDifferentBrowser( driver);
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raGroupSessionContractLinkDifferentBrowser( driver);
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raGroupSessionGuideLinkDifferentBrowser( driver);
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raGroupSessionScriptLinkDifferentBrowser( driver);
		wait(driver, "4" );
		jsClickByXPath(driver, COACH_SESSION_END_SESSION_BUTTON);
		wait(driver, "2" );
		WebElement endSessionText= driver.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_TEXT));
		verifyElementIsPresent(driver, endSessionText);
		WebElement endSessionYesButton= driver.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_YES_BUTTON));
		WebElement endSessionNoButton= driver.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_NO_BUTTON));
		verifyElementIsPresent(driver, endSessionYesButton);
		verifyElementIsPresent(driver, endSessionNoButton);
		jsClickByXPath(driver, COACH_SESSION_END_SESSION_BUTTON);
		wait(driver, "3" );
		jsClickByXPath(driver, COACH_SESSION_END_SESSION_YES_BUTTON);
		wait(driver, "3" );
		WebElement coachCommentsTextbox= driver.findElement(By.xpath(OR.COACH_COMMENTS_TEXTBOX));
		sendKeys(coachCommentsTextbox, "Session completed with the member");
		jsClickByXPath(driver, COACH_END_SESSION_AND_MARKED_AS_COMPLETED);
		wait(driver, "2" );
		jsClickByXPath(driver, COACH_SESSION_MEMBER_DONE_BUTTON);
		wait(driver, "4" );
		com.zillion.qa.realappealcoach.coach.realAppealCoachLogout(driver);

		// Switch Coach to Member
		// Take screen shot for coach video
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver);
		driver.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "4" );
		driver1.manage().window().maximize();
		wait(driver1, "4");
		
		WebElement coachRatingHeader= driver1.findElement(By.xpath(OR.MEMBER_COACH_RATING_HEADER_TEXT));
		WebElement coachRatingTextbox= driver1.findElement(By.xpath(OR.MEMBER_COACH_RATING_TEXTBOX));
		WebElement coachRatingSubmitButton= driver1.findElement(By.xpath(OR.MEMBER_COACH_RATING_SUBMIT_BUTTON));
		WebElement coachRatingCloseButton= driver1.findElement(By.xpath(OR.MEMBER_COACH_RATING_CLOSE_BUTTON));
		WebElement ratingStar1= driver1.findElement(By.xpath(OR.GROUP_SESSION_COACH_RATING_STAR1));
		WebElement ratingStar2= driver1.findElement(By.xpath(OR.GROUP_SESSION_COACH_RATING_STAR2));
		WebElement ratingStar3= driver1.findElement(By.xpath(OR.GROUP_SESSION_COACH_RATING_STAR3));
		WebElement ratingStar4= driver1.findElement(By.xpath(OR.GROUP_SESSION_COACH_RATING_STAR4));
		WebElement ratingStar5= driver1.findElement(By.xpath(OR.GROUP_SESSION_COACH_RATING_STAR5));
		verifyElementIsPresent(driver1, coachRatingHeader);
		verifyElementIsPresent(driver1, coachRatingTextbox);
		verifyElementIsPresent(driver1, coachRatingSubmitButton);
		verifyElementIsPresent(driver1, coachRatingCloseButton);
		verifyElementIsPresent(driver1, ratingStar1);
		verifyElementIsPresent(driver1, ratingStar2);
		verifyElementIsPresent(driver1, ratingStar3);
		verifyElementIsPresent(driver1, ratingStar4);
		verifyElementIsPresent(driver1, ratingStar5);
		click(ratingStar1);
		click(ratingStar2);
		click(ratingStar3);
		click(ratingStar4);
		click(ratingStar5);
		sendKeys(coachRatingTextbox, "Session completed with the coach");
		wait(driver1, "2");
		click(coachRatingSubmitButton);
		wait(driver1, "3");
		com.zillion.qa.realappealmember.member.realAppealMemberlogout(driver1);
		driver.close();
		driver1.close();

	}
	
	/**
	 * Name :     Vinothkumar.M
	 * Created Date:   18/APR/16
	 * Modified Date:   
	 * Description :Members Attend The Group Session And Schedule 1on1Session
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static void membersAttendTheGroupSessionAndSchedule1on1Session(WebDriver driver,String testData, String coachEmail2, String memberEmail2) throws Exception
	{
		
		String[] testData1 = testData.split(",");
		String coachBrowser=testData1[0];
		String memberBrowser=testData1[1];
		String coachChattext=testData1[2];
		String memberChattext=testData1[3];

		// Coach Login in Browser Launch 
		WebDriver driver1 = null;

		if ("firefox".equalsIgnoreCase(coachBrowser) ) 
		{
			driver =  new FirefoxBrowser().getDriver();
			driver.manage().window().maximize();
		}
		else if ( "ie".equalsIgnoreCase(coachBrowser) )
		{
			driver = ( WebDriver ) new IEBrowser().getDriver();
			driver.manage().window().maximize();
		}
		else if ( "safari".equalsIgnoreCase(coachBrowser)) 
		{
			driver = new SafariBrowser().getDriver();
			driver.manage().window().maximize();
		}
		wait( driver, "4" );

		// Coach Login
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.coachLoginWithReferenceStep(driver, coachEmail2);
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver);

		// Browser launch for Member side
		// Test data seperation from spread sheet
		if ("firefox".equalsIgnoreCase(memberBrowser) ) 
		{
			driver1 =  new FirefoxBrowser().getDriver();
			driver1.manage().window().maximize();
		}
		else if ( "ie".equalsIgnoreCase(memberBrowser) )
		{
			driver1 = ( WebDriver ) new IEBrowser().getDriver();
			driver1.manage().window().maximize();
		}
		else if ( "chrome".equalsIgnoreCase(memberBrowser)) 
		{
			driver1 = new ChromeBrowser().getDriver();
			driver1.manage().window().maximize();
		}
		else if ( "safari".equalsIgnoreCase(memberBrowser)) 
		{
			driver1 = new SafariBrowser().getDriver();
			driver1.manage().window().maximize();
		}

		// Member Login with different browser
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raGroupZLiveSessionMemberLoginDifferentBrowser(driver1, memberEmail2);

		try
		{
			waitForElement(driver1, TERMS_AND_CONDITION_AGREE_BUTTON);
			WebElement agreeButton = driver1.findElement(By.xpath(OR.TERMS_AND_CONDITION_AGREE_BUTTON));
			verifyElementIsPresent(driver1, agreeButton);
			jsClickByXPath(driver1, TERMS_AND_CONDITION_AGREE_BUTTON);
		}
		catch(Exception e)
		{
			
		}
		Robot rb = new Robot();
		wait( driver, "4" );
		rb.keyPress(KeyEvent.VK_A);
		rb.keyRelease(KeyEvent.VK_A);
		
		// Append Member URl 
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raMemberGroupSessionAppendURL(driver1);
        
		wait( driver1, "5" );
		waitForElement(driver1, RA_MEMBER_GROUP_SESSION_JOIN_BUTTON);
		jsClickByXPath(driver1, RA_MEMBER_GROUP_SESSION_JOIN_BUTTON);
		wait(driver1, "3");
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raEnterWeightDuringLiveSession( driver1 );
		wait(driver1, "7");

		// Allow plugin for ZLiveSession
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.allowGroupZLivePlugin(driver1);

		// Retrieve the group session attended time
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raMemberSessionTime(driver1);

		waitForElement(driver1, RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CONNECTED_TEXT);
		WebElement closeButton = driver1.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CLOSE_BUTTON));
		WebElement memberChatTextbox = driver1.findElement(By.xpath(OR.RA_MEMBER_LIVESESSION_CHAT_TEXTBOX));
		WebElement memberChatSendButton = driver1.findElement(By.xpath(OR.RA_MEMBER_LIVESESSION_SEND_BUTTON));
		verifyElementIsPresent(driver1, closeButton);
		verifyElementIsPresent(driver1, memberChatTextbox);
		verifyElementIsPresent(driver1, memberChatSendButton);
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver1);

		// Switch Member to coach
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver1, "4");
		driver.manage().window().maximize();
		wait(driver, "4");

		// Append Coach URL
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raMemberGroupSessionAppendURL(driver);
		wait(driver, "5");
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raCoachUpcomingSessionTimeDifferentBrowser(driver);
//		Navigate.refreshPage(driver);
		Screen Allow1=new Screen(); 
		wait( driver, "8" );
		Pattern image1 = new Pattern(Directory.uploadFilePath+"BlankArea.PNG");
		Allow1.wait(image1, 15);
		Allow1.click(image1);
		wait( driver, "3" );
		wait(driver, "6");
		
		String OSName=Platform.OS.toUpperCase();
		if(OSName.contains("WINDOWS"))
		{
			Screen Allow=new Screen(); 
			wait( driver, "8" );
			Pattern image = new Pattern(Directory.uploadFilePath+"SendButton.PNG");
			Allow.wait(image, 15);
			Allow.click(image);
			wait( driver, "3" );
		}
		else if(OSName.contains("MAC"))
		{
			wait( driver, "4" );
			rb.keyPress(KeyEvent.VK_CONTROL); 
			rb.keyPress(KeyEvent.VK_S);
			rb.keyRelease(KeyEvent.VK_CONTROL); 
			rb.keyRelease(KeyEvent.VK_S);
			wait( driver, "8" ); 
			rb.keyPress(KeyEvent.VK_S);
			rb.keyRelease(KeyEvent.VK_S);
		}

		wait(driver, "10");
		WebElement shareScreenButton = driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_SHARE_SCREEN_BUTTON));
		WebElement endSessionButton = driver.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_BUTTON));
		WebElement coachMicOn = driver.findElement(By.xpath(OR.COACH_SESSION_MIC_ON));
		WebElement coachVideoOn = driver.findElement(By.xpath(OR.COACH_SESSION_VIDEO_ON));
		WebElement coachChatTextbox = driver.findElement(By.xpath(OR.COACH_SESSION_CHAT_TEXTBOX));
		WebElement coachSendButton = driver.findElement(By.xpath(OR.COACH_SESSION_CHAT_SEND_BUTTON));
		WebElement coachTileMemberMicDisabled = driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_COACH_TILE_MEMBER_MIC_DISABLED));
		verifyElementIsPresent(driver, coachChatTextbox);
		verifyElementIsPresent(driver, coachSendButton);
		verifyElementIsPresent(driver, shareScreenButton);
		verifyElementIsPresent(driver, endSessionButton);
		verifyElementIsPresent(driver, coachMicOn);
		verifyElementIsPresent(driver, coachVideoOn);
		verifyElementIsPresent(driver, coachTileMemberMicDisabled);
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.groupZLiveSessionCoachGearSettings(driver);
		actionType(driver, coachChatTextbox, coachChattext);
		wait(driver, "1");
		actionClick(driver, coachSendButton);
		wait(driver, "2");
		WebElement coachChatTextToMember = driver.findElement(By.xpath("//div[@class='chat-message ng-scope']//div[2]/div[contains(text(),'"+coachChattext+"')]"));
		verifyElementIsPresent(driver, coachChatTextToMember);
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver);

		// Switch Coach to Member
		driver.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "4");
		driver1.manage().window().maximize();
		wait(driver1, "4");

		// Verify Member Mic button
		WebElement memberSendButton = driver1.findElement(By.xpath(OR.MEMBER_SESSION_CHAT_SEND_BUTTON));
		verifyElementIsPresent(driver1, memberChatTextbox);
		verifyElementIsPresent(driver1, memberSendButton);
		WebElement memberMicDisabled = driver1.findElement(By.xpath(OR.RA_MEMBER_SESSION_MIC_OFF));
		verifyElementIsPresent(driver1, memberMicDisabled);

		actionType(driver1, memberChatTextbox, memberChattext);
		wait(driver1, "1");
		actionClick(driver1, memberChatSendButton);
		wait(driver1, "2");
		WebElement memberChatTextToCoach = driver1.findElement(By.xpath("//div[@class='chat-message ng-scope']//div[2]/div[contains(text(),'"+memberChattext+"')]"));
		verifyElementIsPresent(driver1, memberChatTextToCoach);
		WebElement memberVerifyChatTextFromCoach = driver1.findElement(By.xpath("//div[@class='chat-message ng-scope']//div[2]/div[contains(text(),'"+coachChattext+"')]"));
		verifyElementIsPresent(driver1, memberVerifyChatTextFromCoach);

		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver1, "4" );
		driver.manage().window().maximize();
		driver.manage().window().setPosition(new Point(0, 0));
		wait(driver, "4");

		jsClickByXPath(driver, COACH_SESSION_END_SESSION_BUTTON);
		wait(driver, "2" );
		WebElement endSessionText= driver.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_TEXT));
		verifyElementIsPresent(driver, endSessionText);
		WebElement endSessionYesButton= driver.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_YES_BUTTON));
		WebElement endSessionNoButton= driver.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_NO_BUTTON));
		verifyElementIsPresent(driver, endSessionYesButton);
		verifyElementIsPresent(driver, endSessionNoButton);
		jsClickByXPath(driver, COACH_SESSION_END_SESSION_BUTTON);
		wait(driver, "3" );
		jsClickByXPath(driver, COACH_SESSION_END_SESSION_YES_BUTTON);
		wait(driver, "3" );
		WebElement coachCommentsTextbox= driver.findElement(By.xpath(OR.COACH_COMMENTS_TEXTBOX));
		sendKeys(coachCommentsTextbox, "Session completed with the member");
		jsClickByXPath(driver, COACH_END_SESSION_AND_MARKED_AS_COMPLETED);
		wait(driver, "2" );
		jsClickByXPath(driver, COACH_SESSION_MEMBER_DONE_BUTTON);
		wait(driver, "4" );
		com.zillion.qa.realappealcoach.coach.realAppealCoachLogout(driver);

		// Switch Coach to Member
		// Take screen shot for coach video
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver);
		driver.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "4" );
		driver1.manage().window().maximize();
		wait(driver1, "4");
		
		WebElement coachRatingHeader= driver1.findElement(By.xpath(OR.MEMBER_COACH_RATING_HEADER_TEXT));
		WebElement coachRatingTextbox= driver1.findElement(By.xpath(OR.MEMBER_COACH_RATING_TEXTBOX));
		WebElement coachRatingSubmitButton= driver1.findElement(By.xpath(OR.MEMBER_COACH_RATING_SUBMIT_BUTTON));
		WebElement coachRatingCloseButton= driver1.findElement(By.xpath(OR.MEMBER_COACH_RATING_CLOSE_BUTTON));
		WebElement ratingStar1= driver1.findElement(By.xpath(OR.GROUP_SESSION_COACH_RATING_STAR1));
		WebElement ratingStar2= driver1.findElement(By.xpath(OR.GROUP_SESSION_COACH_RATING_STAR2));
		WebElement ratingStar3= driver1.findElement(By.xpath(OR.GROUP_SESSION_COACH_RATING_STAR3));
		WebElement ratingStar4= driver1.findElement(By.xpath(OR.GROUP_SESSION_COACH_RATING_STAR4));
		WebElement ratingStar5= driver1.findElement(By.xpath(OR.GROUP_SESSION_COACH_RATING_STAR5));
		verifyElementIsPresent(driver1, coachRatingHeader);
		verifyElementIsPresent(driver1, coachRatingTextbox);
		verifyElementIsPresent(driver1, coachRatingSubmitButton);
		verifyElementIsPresent(driver1, coachRatingCloseButton);
		verifyElementIsPresent(driver1, ratingStar1);
		verifyElementIsPresent(driver1, ratingStar2);
		verifyElementIsPresent(driver1, ratingStar3);
		verifyElementIsPresent(driver1, ratingStar4);
		verifyElementIsPresent(driver1, ratingStar5);
		click(ratingStar1);
		click(ratingStar2);
		click(ratingStar3);
		click(ratingStar4);
		click(ratingStar5);
		sendKeys(coachRatingTextbox, "Session completed with the coach");
		wait(driver1, "2");
		click(coachRatingSubmitButton);
		wait(driver1, "5");
		
		jsClickByXPath(driver1, RA_MEMBER_UP_NEXT_WIDGET_ONE_ON_ONE_SESSION_SCHEDULE_BUTTON);
		wait(driver1, "4" );
		waitForElement(driver1, RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_AVAILABLE_BUTTON);
		WebElement timeselectionSelectTime = driver1.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_AVAILABLE_BUTTON_AVAILABLE_TIME));
		verifyElementIsPresent(driver1, timeselectionSelectTime);
		jsClickByXPath(driver1, RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_AVAILABLE_BUTTON_AVAILABLE_TIME);
		wait( driver1, "2" );
		WebElement confirmButton = driver1.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_CONFIRM_BUTTON));
		verifyElementIsPresent(driver, confirmButton);
		click(confirmButton);
		wait( driver1, "7" );
		WebElement oneonOneSessionWidgetChangeButton= driver1.findElement(By.xpath(OR.RA_MEMBER_YOUR_ONE_ON_ONE_SESSION_WIDGET_CHANGE_BUTTON));
		verifyElementIsPresent(driver1, oneonOneSessionWidgetChangeButton);
        
		com.zillion.qa.realappealmember.member.realAppealMemberlogout(driver1);
		driver.close();
		driver1.close();
	
	}
	
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   24/MAY/16
	 * Modified Date:   
	 * Description : RA- Group ZLive session for different browser
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */

	public static void preventT2GroupZLiveSession(WebDriver driver,String testData, String coachEmail1, String memberEmail1) throws Exception
	{
		String[] testData1 = testData.split(",");
		String coachBrowser=testData1[0];
		String memberBrowser=testData1[1];
		String coachChattext=testData1[2];
		String memberChattext=testData1[3];
		String coachMicEnableOrDisabled=testData1[4];
		String coachVideoEnableOrDisabled=testData1[5];
		String MemberRaiseHand=testData1[6];
		String CoachMemberTileMicEnable=testData1[7];

		// Coach Login in Browser Launch 
		WebDriver driver1 = null;

		if ("firefox".equalsIgnoreCase(coachBrowser) ) 
		{
			driver =  new FirefoxBrowser().getDriver();
			driver.manage().window().maximize();
		}
		else if ( "ie".equalsIgnoreCase(coachBrowser) )
		{
			driver = ( WebDriver ) new IEBrowser().getDriver();
			driver.manage().window().maximize();
		}
		else if ( "safari".equalsIgnoreCase(coachBrowser)) 
		{
			driver = new SafariBrowser().getDriver();
			driver.manage().window().maximize();
		}
		wait( driver, "4" );

		// Coach Login
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.coachLoginWithReferenceStep(driver, coachEmail1);
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver);

		// Browser launch for Member side
		// Test data seperation from spread sheet
		if ("firefox".equalsIgnoreCase(memberBrowser) ) 
		{
			driver1 =  new FirefoxBrowser().getDriver();
			driver1.manage().window().maximize();
		}
		else if ( "ie".equalsIgnoreCase(memberBrowser) )
		{
			driver1 = ( WebDriver ) new IEBrowser().getDriver();
			driver1.manage().window().maximize();
		}
		else if ( "chrome".equalsIgnoreCase(memberBrowser)) 
		{
			driver1 = new ChromeBrowser().getDriver();
			driver1.manage().window().maximize();
		}
		else if ( "safari".equalsIgnoreCase(memberBrowser)) 
		{
			driver1 = new SafariBrowser().getDriver();
			driver1.manage().window().maximize();
		}

		// Member Login with different browser
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raGroupZLiveSessionMemberLoginDifferentBrowser(driver1, memberEmail1);

		try
		{
			waitForElement(driver1, TERMS_AND_CONDITION_AGREE_BUTTON);
			WebElement agreeButton = driver1.findElement(By.xpath(OR.TERMS_AND_CONDITION_AGREE_BUTTON));
			verifyElementIsPresent(driver1, agreeButton);
			jsClickByXPath(driver1, TERMS_AND_CONDITION_AGREE_BUTTON);
		}
		catch(Exception e)
		{
			
		}
		Robot rb = new Robot();
		wait( driver, "4" );
		rb.keyPress(KeyEvent.VK_A);
		rb.keyRelease(KeyEvent.VK_A);
		// Open robot for default
		//com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.openRobotDefault( driver);

		/*// To handle tracker server unavailable popup after Login
		com.zillion.qa.realappealmember.member.handleTrackerServerUnavailable( driver1);

		// To handle browser support popup
		com.zillion.qa.realappealmember.member.browserSupportMatrixRA( driver1 );
		 */
		// Append Member URl 
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raMemberGroupSessionAppendURL(driver1);

		waitForElement(driver1, RA_MEMBER_GROUP_SESSION_JOIN_BUTTON);
		WebElement joinButton = driver1.findElement(By.xpath(OR.RA_MEMBER_GROUP_SESSION_JOIN_BUTTON));
		click(joinButton);
		wait(driver1, "3");
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raEnterWeightDuringLiveSession( driver1 );
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.selectExerciseAmountDuringLiveSession( driver1 );
		wait(driver1, "7");

		// Allow plugin for ZLiveSession
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.allowGroupZLivePlugin(driver1);

		// Retrieve the group session attended time
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raMemberSessionTime(driver1);

		waitForElement(driver1, RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CONNECTED_TEXT);
		WebElement closeButton = driver1.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CLOSE_BUTTON));
		WebElement memberChatTextbox = driver1.findElement(By.xpath(OR.RA_MEMBER_LIVESESSION_CHAT_TEXTBOX));
		WebElement memberChatSendButton = driver1.findElement(By.xpath(OR.RA_MEMBER_LIVESESSION_SEND_BUTTON));
		verifyElementIsPresent(driver1, closeButton);
		verifyElementIsPresent(driver1, memberChatTextbox);
		verifyElementIsPresent(driver1, memberChatSendButton);
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver1);

		// Switch Member to coach
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver1, "4");
		driver.manage().window().maximize();
		wait(driver, "4");

		// Append Coach URL
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raMemberGroupSessionAppendURL(driver);
		wait(driver, "5");
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raCoachUpcomingSessionTimeDifferentBrowser(driver);
//		Navigate.refreshPage(driver);
		Screen Allow1=new Screen(); 
		wait( driver, "8" );
		Pattern image1 = new Pattern(Directory.uploadFilePath+"BlankArea1.PNG");
		Allow1.wait(image1, 15);
		Allow1.click(image1);
		wait( driver, "3" );
		wait(driver, "6");
		/*WebElement providerHeader = driver1.findElement(By.xpath(OR.RA_PROGRAM_ADMIN_REAL_APPEAL_LOGO));
		verifyElementIsPresent(driver, providerHeader);
		mouseOver(driver, providerHeader);
		jsClickByXPath(driver, RA_PROGRAM_ADMIN_REAL_APPEAL_LOGO);*/
		String OSName=Platform.OS.toUpperCase();
		if(OSName.contains("WINDOWS"))
		{
			Screen Allow=new Screen(); 
			wait( driver, "8" );
			Pattern image = new Pattern(Directory.uploadFilePath+"SendButton.PNG");
			Allow.wait(image, 15);
			Allow.click(image);
			wait( driver, "3" );
		}
		else if(OSName.contains("MAC"))
		{
			wait( driver, "4" );
			rb.keyPress(KeyEvent.VK_CONTROL); 
			rb.keyPress(KeyEvent.VK_S);
			rb.keyRelease(KeyEvent.VK_CONTROL); 
			rb.keyRelease(KeyEvent.VK_S);
			wait( driver, "8" ); 
			rb.keyPress(KeyEvent.VK_S);
			rb.keyRelease(KeyEvent.VK_S);
		}
		//com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.shareSelectedDevice(driver);

		wait(driver, "10");
		WebElement shareScreenButton = driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_SHARE_SCREEN_BUTTON));
		WebElement endSessionButton = driver.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_BUTTON));
		WebElement coachMicOn = driver.findElement(By.xpath(OR.COACH_SESSION_MIC_ON));
		WebElement coachVideoOn = driver.findElement(By.xpath(OR.COACH_SESSION_VIDEO_ON));
		WebElement coachChatTextbox = driver.findElement(By.xpath(OR.COACH_SESSION_CHAT_TEXTBOX));
		WebElement coachSendButton = driver.findElement(By.xpath(OR.COACH_SESSION_CHAT_SEND_BUTTON));
		WebElement coachTileMemberMicDisabled = driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_COACH_TILE_MEMBER_MIC_DISABLED));
		verifyElementIsPresent(driver, coachChatTextbox);
		verifyElementIsPresent(driver, coachSendButton);
		verifyElementIsPresent(driver, shareScreenButton);
		verifyElementIsPresent(driver, endSessionButton);
		verifyElementIsPresent(driver, coachMicOn);
		verifyElementIsPresent(driver, coachVideoOn);
		verifyElementIsPresent(driver, coachTileMemberMicDisabled);
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.groupZLiveSessionCoachGearSettings(driver);
		actionType(driver, coachChatTextbox, coachChattext);
		wait(driver, "1");
		actionClick(driver, coachSendButton);
		wait(driver, "2");
		WebElement coachChatTextToMember = driver.findElement(By.xpath("//div[@class='chat-message ng-scope']//div[2]/div[contains(text(),'"+coachChattext+"')]"));
		verifyElementIsPresent(driver, coachChatTextToMember);
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver);

		// Switch Coach to Member
		driver.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "4");
		driver1.manage().window().maximize();
		wait(driver1, "4");

		// Verify Member Mic button
		WebElement memberSendButton = driver1.findElement(By.xpath(OR.MEMBER_SESSION_CHAT_SEND_BUTTON));
		verifyElementIsPresent(driver1, memberChatTextbox);
		verifyElementIsPresent(driver1, memberSendButton);
		WebElement memberMicDisabled = driver1.findElement(By.xpath(OR.RA_MEMBER_SESSION_MIC_OFF));
		verifyElementIsPresent(driver1, memberMicDisabled);

		actionType(driver1, memberChatTextbox, memberChattext);
		wait(driver1, "1");
		actionClick(driver1, memberChatSendButton);
		wait(driver1, "2");
		WebElement memberChatTextToCoach = driver1.findElement(By.xpath("//div[@class='chat-message ng-scope']//div[2]/div[contains(text(),'"+memberChattext+"')]"));
		verifyElementIsPresent(driver1, memberChatTextToCoach);
		WebElement memberVerifyChatTextFromCoach = driver1.findElement(By.xpath("//div[@class='chat-message ng-scope']//div[2]/div[contains(text(),'"+coachChattext+"')]"));
		verifyElementIsPresent(driver1, memberVerifyChatTextFromCoach);

		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver1, "4" );
		driver.manage().window().maximize();
		driver.manage().window().setPosition(new Point(0, 0));
		wait(driver, "4");

		if ("CoachMicEnable".equalsIgnoreCase(coachMicEnableOrDisabled) ) 
		{ 
			try
			{
				jsClickByXPath(driver, OR.COACH_SESSION_MIC_OFF);
				wait(driver, "2");
				verifyElementIsPresent(driver, coachMicOn);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver, OR.COACH_SESSION_MIC_ON);
				wait(driver, "2");
				jsClickByXPath(driver, OR.COACH_SESSION_MIC_OFF);
				wait(driver, "2");
				verifyElementIsPresent(driver, coachMicOn);
			}
		}

		else if ( "CoachMicDisable".equalsIgnoreCase(coachMicEnableOrDisabled) )
		{
			try
			{
				jsClickByXPath(driver, OR.COACH_SESSION_MIC_ON);
				wait(driver, "2");
				WebElement coachMicOff = driver.findElement(By.xpath(OR.COACH_SESSION_MIC_OFF));
				verifyElementIsPresent(driver, coachMicOff);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver, OR.COACH_SESSION_MIC_OFF);
				wait(driver, "2");
				jsClickByXPath(driver, OR.COACH_SESSION_MIC_ON);
				WebElement coachMicOff = driver.findElement(By.xpath(OR.COACH_SESSION_MIC_OFF));
				wait(driver, "2");
				verifyElementIsPresent(driver, coachMicOff);
			}
		}

		if ("CoachVideoEnable".equalsIgnoreCase(coachVideoEnableOrDisabled) ) 
		{ 
			try
			{
				jsClickByXPath(driver, OR.COACH_SESSION_VIDEO_OFF);
				wait(driver, "2");
				verifyElementIsPresent(driver, coachVideoOn);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver, OR.COACH_SESSION_VIDEO_ON);
				wait(driver, "2");
				jsClickByXPath(driver, OR.COACH_SESSION_VIDEO_OFF);
				wait(driver, "2");
				verifyElementIsPresent(driver, coachVideoOn);
			}

		}

		else if ( "CoachVideoDisable".equalsIgnoreCase(coachVideoEnableOrDisabled) )
		{
			try
			{
				jsClickByXPath(driver, OR.COACH_SESSION_VIDEO_ON);
				wait(driver, "2");
				WebElement coachVideoOff = driver.findElement(By.xpath(OR.COACH_SESSION_VIDEO_OFF));
				verifyElementIsPresent(driver, coachVideoOff);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver, OR.COACH_SESSION_VIDEO_OFF);
				wait(driver, "2");
				jsClickByXPath(driver, OR.COACH_SESSION_VIDEO_ON);
				WebElement coachVideoOff = driver.findElement(By.xpath(OR.COACH_SESSION_VIDEO_OFF));
				wait(driver, "2");
				verifyElementIsPresent(driver, coachVideoOff);
			}
		}

		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver);
		driver.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "4" );
		driver1.manage().window().maximize();
		wait(driver1, "4");

		if ("MemberRaiseHandEnable".equalsIgnoreCase(MemberRaiseHand) ) 
		{
			jsClickByXPath(driver1, OR.MEMBER_RA_GROUP_SESSION_RAISE_HAND);
			WebElement memberLowerHand = driver1.findElement(By.xpath(OR.MEMBER_RA_GROUP_SESSION_LOWER_HAND));
			verifyElementIsPresent(driver1, memberLowerHand);
		}
		else if ( "MemberRaiseHandDisable".equalsIgnoreCase(MemberRaiseHand) )
		{
			jsClickByXPath(driver1, OR.MEMBER_RA_GROUP_SESSION_RAISE_HAND);
			wait(driver1, "2");
			jsClickByXPath(driver1, OR.MEMBER_RA_GROUP_SESSION_LOWER_HAND);
			WebElement memberRaiseHand = driver1.findElement(By.xpath(OR.MEMBER_RA_GROUP_SESSION_RAISE_HAND));
			verifyElementIsPresent(driver1, memberRaiseHand);
		}

		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver1, "4" );
		driver.manage().window().maximize();
		driver.manage().window().setPosition(new Point(0, 0));
		wait(driver, "4");

		if ("MemberRaiseHandEnable".equalsIgnoreCase(MemberRaiseHand))  
		{
			WebElement memberLowerHand = driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_LOWER_HAND_SYMBOL));
			verifyElementIsPresent(driver, memberLowerHand);
			WebElement lowerAllHandsButton = driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_LOWER_ALL_HANDS_BUTTON));
			verifyElementIsPresent(driver, lowerAllHandsButton);
		}
		else if ( "MemberRaiseHandDisable".equalsIgnoreCase(MemberRaiseHand) )
		{
			WebElement memberLowerHand = driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_LOWER_HAND_SYMBOL));
			verifyElementIsNotPresent(driver, memberLowerHand);
			WebElement lowerAllHandsButton = driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_LOWER_ALL_HANDS_BUTTON));
			verifyElementIsNotPresent(driver, lowerAllHandsButton);
		}

		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.getWindowToScreenShareAndInstallDifferentBrowser(driver);
		//Manipulation.switchWindow(driver);
		Navigate.refreshPage(driver);
		wait(driver, "7");
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.shareSelectedDevice(driver);
		wait(driver, "5");
		waitForElement(driver, COACH_RA_GROUP_SESSION_SHARE_SCREEN_BUTTON);
		jsClickByXPath(driver, COACH_RA_GROUP_SESSION_SHARE_SCREEN_BUTTON);
		wait(driver, "3");
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.selectScreenForGroupSessionZLive(driver);
		wait(driver, "3");

		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver);
		driver.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "4" );
		driver1.manage().window().maximize();
		wait(driver1, "4");

		com.zillion.qa.member.liveSessionSubCommonMethods.screenShotForMemberLiveSession( driver1 );
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver1, "4" );
		driver.manage().window().maximize();
		wait(driver, "4");

		WebElement screenStopSharingButton = driver.findElement(By.xpath(OR.COACH_RA_GROUP_SESSION_STOP_SHARING_SCREEN));
		verifyElementIsPresent(driver, screenStopSharingButton);
		jsClickByXPath(driver, OR.COACH_RA_GROUP_SESSION_STOP_SHARING_SCREEN);
		wait(driver, "2");

		// Check Browser Icon Coach Toolbar compare with Input member Browser
		if ("firefox".equalsIgnoreCase(memberBrowser) )  
		{
			WebElement firefoxBrowserIcon = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_FIREFOX_BROWSER_ICON));
			verifyElementIsPresent(driver, firefoxBrowserIcon);
			jsClickByXPath(driver, OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_FIREFOX_BROWSER_ICON);
			wait( driver, "3" );
		}
		else if ( "ie".equalsIgnoreCase(memberBrowser) )
		{
			WebElement ieBrowserIcon = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_IE_BROWSER_ICON));
			verifyElementIsPresent(driver, ieBrowserIcon);
			jsClickByXPath(driver, OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_IE_BROWSER_ICON);
			wait( driver, "3" );
		}
		else if ("chrome".equalsIgnoreCase(memberBrowser) )
		{
			WebElement ieBrowserIcon = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_CHROME_BROWSER_ICON));
			verifyElementIsPresent(driver, ieBrowserIcon);
			jsClickByXPath(driver, OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_CHROME_BROWSER_ICON);
			wait( driver, "3" );
		}
		WebElement memberAccountID = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_MEMBER_ID));
		WebElement popUpOkButton = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_OK_BUTTON));
		WebElement memberIDHeader = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_MEMBER_ID_HEADER));
		WebElement sessionIDHeader = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_SESSION_ID_HEADER));
		WebElement userAgentHeader = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_USER_AGENT_HEADER));
		WebElement versionHeader = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_LIVESESSION_VERSION_HEADER));
		WebElement micOptionHeader = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_MICROPHONE_OPTIONS_HEADER));
		WebElement micSelectedHeader = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_MICROPHONE_SELECTED_HEADER));
		WebElement speakerOptionHeader = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_SPEAKER_OPTIONS_HEADER));
		WebElement speakerSelectedHeader = driver.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_SPEAKER_SELECTED_HEADER));
		verifyElementIsPresent(driver, memberAccountID);
		verifyElementIsPresent(driver, popUpOkButton);
		verifyElementIsPresent(driver, memberIDHeader);
		verifyElementIsPresent(driver, sessionIDHeader);
		verifyElementIsPresent(driver, userAgentHeader);
		verifyElementIsPresent(driver, versionHeader);
		verifyElementIsPresent(driver, micOptionHeader);
		verifyElementIsPresent(driver, micSelectedHeader);
		verifyElementIsPresent(driver, speakerOptionHeader);
		verifyElementIsPresent(driver, speakerSelectedHeader);
		jsClickByXPath(driver, OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_OK_BUTTON);
		wait( driver1, "3" );  

		jsClickByXPath(driver, COACH_SESSION_END_SESSION_BUTTON);
		wait(driver, "2" );
		WebElement endSessionText= driver.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_TEXT));
		verifyElementIsPresent(driver, endSessionText);
		WebElement endSessionYesButton= driver.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_YES_BUTTON));
		WebElement endSessionNoButton= driver.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_NO_BUTTON));
		verifyElementIsPresent(driver, endSessionYesButton);
		verifyElementIsPresent(driver, endSessionNoButton);
		jsClickByXPath(driver, COACH_SESSION_END_SESSION_BUTTON);
		wait(driver, "3" );
		jsClickByXPath(driver, COACH_SESSION_END_SESSION_YES_BUTTON);
		wait(driver, "3" );
		WebElement coachCommentsTextbox= driver.findElement(By.xpath(OR.COACH_COMMENTS_TEXTBOX));
		sendKeys(coachCommentsTextbox, "Session completed with the member");
		jsClickByXPath(driver, COACH_END_SESSION_AND_MARKED_AS_COMPLETED);
		wait(driver, "2" );
		jsClickByXPath(driver, COACH_SESSION_MEMBER_DONE_BUTTON);
		wait(driver, "4" );
		com.zillion.qa.realappealcoach.coach.realAppealCoachLogout(driver);

		// Switch Coach to Member
		// Take screen shot for coach video
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver);
		driver.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "4" );
		driver1.manage().window().maximize();
		wait(driver1, "4");
		
		WebElement coachRatingHeader= driver1.findElement(By.xpath(OR.MEMBER_COACH_RATING_HEADER_TEXT));
		WebElement coachRatingTextbox= driver1.findElement(By.xpath(OR.MEMBER_COACH_RATING_TEXTBOX));
		WebElement coachRatingSubmitButton= driver1.findElement(By.xpath(OR.MEMBER_COACH_RATING_SUBMIT_BUTTON));
		WebElement coachRatingCloseButton= driver1.findElement(By.xpath(OR.MEMBER_COACH_RATING_CLOSE_BUTTON));
		WebElement ratingStar1= driver1.findElement(By.xpath(OR.GROUP_SESSION_COACH_RATING_STAR1));
		WebElement ratingStar2= driver1.findElement(By.xpath(OR.GROUP_SESSION_COACH_RATING_STAR2));
		WebElement ratingStar3= driver1.findElement(By.xpath(OR.GROUP_SESSION_COACH_RATING_STAR3));
		WebElement ratingStar4= driver1.findElement(By.xpath(OR.GROUP_SESSION_COACH_RATING_STAR4));
		WebElement ratingStar5= driver1.findElement(By.xpath(OR.GROUP_SESSION_COACH_RATING_STAR5));
		verifyElementIsPresent(driver1, coachRatingHeader);
		verifyElementIsPresent(driver1, coachRatingTextbox);
		verifyElementIsPresent(driver1, coachRatingSubmitButton);
		verifyElementIsPresent(driver1, coachRatingCloseButton);
		verifyElementIsPresent(driver1, ratingStar1);
		verifyElementIsPresent(driver1, ratingStar2);
		verifyElementIsPresent(driver1, ratingStar3);
		verifyElementIsPresent(driver1, ratingStar4);
		verifyElementIsPresent(driver1, ratingStar5);
		click(ratingStar1);
		click(ratingStar2);
		click(ratingStar3);
		click(ratingStar4);
		click(ratingStar5);
		sendKeys(coachRatingTextbox, "Session completed with the coach");
		wait(driver1, "2");
		click(coachRatingSubmitButton);
		wait(driver1, "3");
		com.zillion.qa.realappealmember.member.realAppealMemberlogout(driver1);
		driver.close();
		driver1.close();

	}
}
