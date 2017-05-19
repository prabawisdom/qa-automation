package com.zillion.qa.realappealmember;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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

public class FlexibleMakeupSession extends Manipulation implements OR {



	/**
	 * Name :     Vinothkumar.M
	 * Created Date:   11/APR/16
	 * Modified Date:   
	 * Description : RA- Group Schedule and Makeup session for different browser
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */

	public static void raGroupScheduleAndAttendMakeupSession(WebDriver driver,String testData, String coachEmail2, String memberEmail2) throws Exception
	{
		String[] testData1 = testData.split(",");
		String coachBrowser=testData1[0];
		String memberBrowser=testData1[1];
		String coachChattext=testData1[2];
		String memberChattext=testData1[3];
		String coachMicEnableOrDisabled=testData1[4];
		String coachVideoEnableOrDisabled=testData1[5];
		String MemberRaiseHand=testData1[6];

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
		com.zillion.qa.realappealmember.member.termsAndConditionPopupHandle( driver1 );

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
		wait(driver1, "5");

		WebElement yourNextGroupSessionTitle= driver1.findElement(By.xpath(OR.RA_MEMBER_UP_NEXT_WIDGET_YOUR_NEXT_GROUP_SESSION_TITLE));
		WebElement scheduleYour1on1SessionTitle= driver1.findElement(By.xpath(OR.RA_MEMBER_UP_NEXT_WIDGET_SCHEDULE_YOUR_ONE_ON_ONE_SESSION_TITLE));
		WebElement groupSessionCountDown= driver1.findElement(By.xpath(OR.RA_MEMBER_UP_NEXT_WIDGET_GROUP_SESSION_COUNTDOWN));
		WebElement oneonOneSessionScheduleButton= driver1.findElement(By.xpath(OR.RA_MEMBER_UP_NEXT_WIDGET_ONE_ON_ONE_SESSION_SCHEDULE_BUTTON));
		WebElement scheduleAMakeupSessionLink= driver1.findElement(By.xpath(OR.RA_MEMBER_CLICK_HERE_TO_SCHEDULE_A_MAKEUP_SESSION_LINK));
		verifyElementIsPresent(driver1, yourNextGroupSessionTitle);
		verifyElementIsPresent(driver1, scheduleYour1on1SessionTitle);
		verifyElementIsPresent(driver1, groupSessionCountDown);
		verifyElementIsPresent(driver1, oneonOneSessionScheduleButton);
		verifyElementIsNotPresent(driver1, scheduleAMakeupSessionLink);

		com.zillion.qa.realappealmember.member.realAppealMemberlogout(driver1);
		driver.close();
		driver1.close();

	}

	/**
	 * Name :     Vinothkumar.M
	 * Created Date:   12/APR/16
	 * Modified Date:   
	 * Description : UNW - Makeup session is completed and with different 1on1 statuses
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static void makeupSessionIsCompletedAndWithDifferent1ON1Status(WebDriver driver,String testData, String coachEmail2, String memberEmail2) throws Exception
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
        
		com.zillion.qa.realappealmember.member.termsAndConditionPopupHandle( driver1 );
	
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

		// Verify Differing 1on1 Status
		WebElement yourNextGroupSessionTitle= driver1.findElement(By.xpath(OR.RA_MEMBER_UP_NEXT_WIDGET_YOUR_NEXT_GROUP_SESSION_TITLE));
		WebElement scheduleYour1on1SessionTitle= driver1.findElement(By.xpath(OR.RA_MEMBER_UP_NEXT_WIDGET_SCHEDULE_YOUR_ONE_ON_ONE_SESSION_TITLE));
		WebElement groupSessionCountDown= driver1.findElement(By.xpath(OR.RA_MEMBER_UP_NEXT_WIDGET_GROUP_SESSION_COUNTDOWN));
		WebElement oneonOneSessionScheduleButton= driver1.findElement(By.xpath(OR.RA_MEMBER_UP_NEXT_WIDGET_ONE_ON_ONE_SESSION_SCHEDULE_BUTTON));
		WebElement scheduleAMakeupSessionLink= driver1.findElement(By.xpath(OR.RA_MEMBER_CLICK_HERE_TO_SCHEDULE_A_MAKEUP_SESSION_LINK));
		verifyElementIsPresent(driver1, yourNextGroupSessionTitle);
		verifyElementIsPresent(driver1, scheduleYour1on1SessionTitle);
		verifyElementIsPresent(driver1, groupSessionCountDown);
		verifyElementIsPresent(driver1, oneonOneSessionScheduleButton);
		verifyElementIsNotPresent(driver1, scheduleAMakeupSessionLink);

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

		jsClickByXPath(driver1, RA_MEMBER_YOUR_ONE_ON_ONE_SESSION_WIDGET_CHANGE_BUTTON);
		wait(driver1, "4" );
		jsClickByXPath(driver1, RA_MEMBER_ONE_ON_ONE_SESSION_CANCEL_SESSION_BUTTON);
		wait(driver1, "5");
		jsClickByXPath(driver1, RA_MEMBER_ONE_ON_ONE_SESSION_CANCEL_SESSION_OK_BUTTON);
		wait(driver1, "6");
		waitForElement( driver1, OR.REAL_APPEAL_MEMBER_DASHBOARD_TAB );
		jsClickByXPath(driver1, REAL_APPEAL_MEMBER_DASHBOARD_TAB);
		wait(driver1, "6");
		WebElement oneonOneSessionWidgetRescheduleButton= driver1.findElement(By.xpath(OR.RA_MEMBER_YOUR_ONE_ON_ONE_SESSION_WIDGET_RESCHEDULE_BUTTON));
		verifyElementIsPresent(driver1, oneonOneSessionWidgetRescheduleButton);

		jsClickByXPath(driver1, RA_MEMBER_YOUR_ONE_ON_ONE_SESSION_WIDGET_RESCHEDULE_BUTTON);
		wait(driver1, "4" );
		waitForElement(driver1, RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_AVAILABLE_BUTTON);
		jsClickByXPath(driver1, RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_AVAILABLE_BUTTON_AVAILABLE_TIME);
		wait( driver1, "2" );
		jsClickByXPath(driver1, RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_CONFIRM_BUTTON);
		wait( driver1, "7" );

		com.zillion.qa.realappealmember.member.realAppealMemberlogout(driver1);
		driver.close();
		driver1.close();

	}

	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:   17/APR/17
	 * Modified Date:   
	 * Description : RA- 10n1 UNW for the member who completes 1on1 & missed group in 52nd interval
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */

	public static void memberWhoCompletes1on1AndMissedGroupIn52ndInterval(WebDriver driver,String testData, String coachEmail2, String memberEmail2) throws Exception
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

		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raGroupZLiveSessionMemberLogin(driver, memberEmail2);
        
		// Open robot for default
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.openRobotDefault( driver);

		// To handle tracker server unavailable popup after Login
		com.zillion.qa.realappealmember.member.handleTrackerServerUnavailable( driver);

		// To handle browser support popup
		com.zillion.qa.realappealmember.member.browserSupportMatrixRA( driver );
		
		com.zillion.qa.realappealmember.member.termsAndConditionPopupHandle( driver);

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
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.coachLoginWithReferenceStepDifferentBrowser(driver1, coachEmail2);
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
		WebElement DoneButton = driver1.findElement(By.xpath(OR.COACH_1ON1_SESSION_MEMBER_DONE_BUTTON));
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

		// Verify Link to sign up for Makeup session should be displayed below to No Upcoming sessions section if the member has missed the group session in current interval

		WebElement yourNextGroupSessionTitle= driver.findElement(By.xpath(OR.RA_MEMBER_UP_NEXT_WIDGET_YOUR_NEXT_GROUP_SESSION_TITLE));
		WebElement scheduleAMakeupSessionLink= driver.findElement(By.xpath(OR.RA_MEMBER_CLICK_HERE_TO_SCHEDULE_A_MAKEUP_SESSION_LINK));
		verifyElementIsPresent(driver, yourNextGroupSessionTitle);
		verifyElementIsNotPresent(driver, scheduleAMakeupSessionLink);
		com.zillion.qa.realappealmember.member.realAppealMemberlogout(driver);
		driver.close();
		driver1.close();
	}

	/**
	 * Name :     Vinothkumar.M
	 * Created Date:   20/APR/16
	 * Modified Date:   
	 * Description : RA- Group Temporary substitution of a session to which members signed up as a part of makeup
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */

	public static void temporarySubstitutionOfASessionAttendMakeupSession (WebDriver driver,String testData, String coachEmail2, String memberEmail2) throws Exception
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
		com.zillion.qa.realappealmember.member.termsAndConditionPopupHandle( driver1 );

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

		// Verify  group members information should display the members who signed up via makeup with a small indicator near the member name for makeup sesion

		jsClickByXPath(driver, COACHES_SETTINGS_LINK_BUTTON);
		wait(driver, "2" );
		jsClickByXPath(driver, COACHES_SETTINGS_LINK_MYPROFILE_BUTTON);
		wait(driver, "7" );
		jsClickByXPath(driver, COACHES_GEAR_BUTTON_MY_PROFILE_ALL_SESSION_SUBTAB);
		wait(driver, "7" );
		jsClickByXPath(driver, RA_ASSIGNED_COACH_GROUP_MEMBERS_INFORMATION_ICON);
		wait(driver, "3" );
		WebElement makeupMemberName= driver.findElement(By.xpath(OR.RA_COACH_GROUP_MEMBERS_DETAIL_MAKEUP_MEMBERS_NAME));
		WebElement makeupSessionIndicator= driver.findElement(By.xpath(OR.RA_COACH_GROUP_MEMBERS_DETAIL_MEMBER_NAME_MAKEUP_SESSION_INDICATOR));
		verifyElementIsPresent(driver, makeupMemberName);
		verifyElementIsPresent(driver, makeupSessionIndicator);
		jsClickByXPath(driver, CLOSE_LINK);
		wait(driver, "3" );

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
		com.zillion.qa.realappealmember.member.realAppealMemberlogout(driver1);

		com.zillion.qa.realappealprogramadmin.programadmin.realAppealProgramAdminLogin(driver1);

		// Verify Member List - Makeup Attendance, Last Session type and Last Session date

		wait(driver1, "5");
		WebElement raCoachMembersTab= driver1.findElement(By.xpath(OR.RA_COACH_MEMBERS_TAB));
		verifyElementIsPresent(driver1, raCoachMembersTab);
		click(raCoachMembersTab);
		wait(driver1, "10");
		WebElement raCoachMembersSortDropdown= driver1.findElement(By.xpath(OR.RA_COACH_MEMBERS_SORT_DROPDOWN));
		selectByVisibletext(raCoachMembersSortDropdown,"Email" );
		wait(driver1, "1");
		WebElement raCoachMembersSearchTextbox= driver1.findElement(By.xpath(OR.RA_COACH_MEMBERS_SEARCH_TEXTBOX));
		sendKeys(raCoachMembersSearchTextbox, memberEmail2);
		WebElement raCoachMembersearchButton= driver1.findElement(By.xpath(OR.RA_COACH_MEMBERS_SEARCH_BUTTON));
		click(raCoachMembersearchButton);
		wait(driver1, "5");
		jsClickByXPath(driver1, PRO_PATIENT_MEMBER_LINK);
		wait(driver, "10" );
		jsClickByXPath(driver1, MEMBER_SESSIONS_TAB);
		wait(driver, "7" );

		WebElement groupLastSessionCompletedStatus= driver1.findElement(By.xpath(OR.RA_PA_GROUP_LAST_SESSION_COMPLETED_STATUS));
		WebElement groupLastSessionType= driver1.findElement(By.xpath(OR.RA_PA_GROUP_LAST_SESSION_TYPE));
		WebElement groupLastSessionDate= driver1.findElement(By.xpath(OR.RA_PA_GROUP_LAST_SESSION_DATE));
		verifyElementIsPresent(driver1, groupLastSessionCompletedStatus);
		verifyElementIsPresent(driver1, groupLastSessionType);
		verifyElementIsPresent(driver1, groupLastSessionDate);

		jsClickByXPath(driver1, RA_PA_MEMBER_TRACKING_TAB);
		wait(driver, "10" );
		jsClickByXPath(driver1, RA_PA_MEMBER_TRACKING_SUMMARY);
		wait(driver, "10" );

		WebElement lastSessionAttendedHeader= driver1.findElement(By.xpath(OR.RA_PA_MEMBER_TRACKING_SUMMARY_LAST_SESSION_ATTENDED_HEADER));
		WebElement sessionsAttendedHeader= driver1.findElement(By.xpath(OR.RA_PA_MEMBER_TRACKING_SUMMARY_SESSIONS_ATTENDED_HEADER));
		WebElement lastSessionAttendedValue= driver1.findElement(By.xpath(OR.RA_PA_MEMBER_TRACKING_SUMMARY_LAST_SESSION_ATTENDED_VALUE));
		WebElement sessionsAttendedValue= driver1.findElement(By.xpath(OR.RA_PA_MEMBER_TRACKING_SUMMARY_SESSIONS_ATTENDED_VALUE));
		verifyElementIsPresent(driver1, lastSessionAttendedHeader);
		verifyElementIsPresent(driver1, sessionsAttendedHeader);
		verifyElementIsPresent(driver1, lastSessionAttendedValue);
		verifyElementIsPresent(driver1, sessionsAttendedValue);

		com.zillion.qa.realappealprogramadmin.programadmin.realAppealProgramAdminLogout(driver1);

		driver.close();
		driver1.close();

	}	

	/**
	 * Name :     VINOTHKUMAR.M
	 * Created Date:   25/APR/17
	 * Modified Date:   
	 * Description : Group claims triggered for 1on1 if it happened prior to makeup session
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */

	public static void groupClaimsTriggeredFor1on1IfItHappenedPriorToMakeupSession(WebDriver driver,String testData, String coachEmail2, String memberEmail2) throws Exception
	{
		String[] testData1 = testData.split(",");
		String memberBrowser=testData1[0];
		String coachBrowser=testData1[1];

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

		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raGroupZLiveSessionMemberLogin(driver, memberEmail2);

		// Open robot for default
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.openRobotDefault( driver);

		// To handle tracker server unavailable popup after Login
		com.zillion.qa.realappealmember.member.handleTrackerServerUnavailable( driver);

		// To handle browser support popup
		com.zillion.qa.realappealmember.member.browserSupportMatrixRA( driver );
		
		com.zillion.qa.realappealmember.member.termsAndConditionPopupHandle( driver);

		// Append URL for member force attend the session
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
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.coachLoginWithReferenceStep(driver1, coachEmail2);
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
		WebElement DoneButton = driver1.findElement(By.xpath(OR.COACH_1ON1_SESSION_MEMBER_DONE_BUTTON));
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
		wait(driver1, "5");

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

		jsClickByXPath(driver, RA_MEMBER_CLICK_HERE_TO_SCHEDULE_A_MAKEUP_SESSION_LINK);
		wait(driver1, "4" );
		WebElement timeselectionSelectTime = driver.findElement(By.xpath(OR.RA_1ON1_MAKEUP_SESSION_MEMBER_TIMESELECTION_AVAILABLE_BUTTON));
		verifyElementIsPresent(driver, timeselectionSelectTime);
		jsClickByXPath(driver, RA_MEMBER_MAKEUP_SESSION_TIMESELECTION_AVAILABLE_BUTTON_AVAILABLE_TIME);
		wait( driver1, "2" );
		WebElement confirmButton = driver.findElement(By.xpath(OR.RA_MEMBER_MAKEUP_SESSION_TIMESELECTION_CONFIRM_BUTTON));
		verifyElementIsPresent(driver, confirmButton);
		click(confirmButton);
		wait( driver1, "7" );

		// Append Member URl 
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raMemberGroupSessionAppendURL(driver);

		wait( driver1, "5" );
		waitForElement(driver, RA_MEMBER_GROUP_SESSION_JOIN_BUTTON);
		jsClickByXPath(driver, RA_MEMBER_GROUP_SESSION_JOIN_BUTTON);
		wait(driver1, "3");
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raEnterWeightDuringLiveSession( driver);
		wait(driver, "7");

		// Allow plugin for ZLiveSession
		//		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.allowGroupZLivePlugin(driver);

		// Retrieve the group session attended time
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raMemberSessionTime(driver);

		// Switch Member to coach
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "4");
		driver.manage().window().maximize();
		wait(driver1, "4");

		com.zillion.qa.realappealprogramadmin.programadmin.realAppealProgramAdminLogin(driver1);

		// Verify Member List - Makeup Attendance, Last Session type and Last Session date

		wait(driver1, "5");
		WebElement raCoachMembersTab= driver1.findElement(By.xpath(OR.RA_COACH_MEMBERS_TAB));
		verifyElementIsPresent(driver1, raCoachMembersTab);
		click(raCoachMembersTab);
		wait(driver1, "10");
		WebElement raCoachMembersSortDropdown= driver1.findElement(By.xpath(OR.RA_COACH_MEMBERS_SORT_DROPDOWN));
		selectByVisibletext(raCoachMembersSortDropdown,"Email" );
		wait(driver1, "1");
		WebElement raCoachMembersSearchTextbox= driver1.findElement(By.xpath(OR.RA_COACH_MEMBERS_SEARCH_TEXTBOX));
		sendKeys(raCoachMembersSearchTextbox, memberEmail2);
		WebElement raCoachMembersearchButton= driver1.findElement(By.xpath(OR.RA_COACH_MEMBERS_SEARCH_BUTTON));
		click(raCoachMembersearchButton);
		wait(driver1, "5");
		jsClickByXPath(driver1, PRO_PATIENT_MEMBER_LINK);
		wait(driver, "10" );
		jsClickByXPath(driver1, MEMBER_SESSIONS_TAB);
		wait(driver, "7" );
		jsClickByXPath(driver1, INSOURCE_PRACTICE_PA_ASSIGNED_COACH);
		wait(driver, "7" );
		WebElement coachEmail= driver1.findElement(By.xpath(OR.ONE_ON_ONE_SESSION_ASSIGNED_COACH_EMAIL));
		String assignedCoachEmail = coachEmail.getText();
		com.zillion.qa.realappealprogramadmin.programadmin.realAppealProgramAdminLogout(driver1);

		Navigate.get(driver1, Directory.RA_Provider_Url);
		wait(driver, "5");
		Navigate.maximize(driver1);
		WebElement Coacheslogin_logo= driver1.findElement(By.xpath(OR.COACHES_LOGIN_PAGE_LOGO_REF));
		waitForElement( driver1, OR.COACHES_LOGIN_PAGE_LOGO_REF );
		verifyElementIsPresent(driver1, Coacheslogin_logo);
		WebElement coach_username= driver1.findElement(By.xpath(OR.COACHES_USERNAME_1));
		verifyElementIsPresent(driver1, coach_username);
		WebElement coach_password= driver1.findElement(By.xpath(OR.COACHES_PASSWORD));
		verifyElementIsPresent(driver1, coach_password);
		WebElement Coacheslogin_button= driver1.findElement(By.xpath(OR.COACHES_LOGIN_BUTTON));
		verifyElementIsPresent(driver1, Coacheslogin_button);
		sendKeys(coach_username,assignedCoachEmail);
		wait(driver1, "2");
		sendKeys(coach_password,"Healthfleet2015");
		wait(driver1, "2");
		click(Coacheslogin_button);
		wait(driver1, "10");

		// Append Coach URL
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raMemberGroupSessionAppendURL(driver1);
		wait(driver, "5");
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raCoachUpcomingSessionTimeDifferentBrowser(driver1);
		
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
			Robot rb = new Robot();
			wait( driver, "4" );
			rb.keyPress(KeyEvent.VK_CONTROL); 
			rb.keyPress(KeyEvent.VK_S);
			rb.keyRelease(KeyEvent.VK_CONTROL); 
			rb.keyRelease(KeyEvent.VK_S);
			wait( driver, "8" ); 
			rb.keyPress(KeyEvent.VK_S);
			rb.keyRelease(KeyEvent.VK_S);
		}
		
		
//		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.shareSelectedDevice(driver1);
//		wait(driver, "6");
		
		/*jsClickByXPath(driver1, COACH_DASHBOARD_TAB);
		wait(driver, "5" );
		// Append Coach URL
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raMemberGroupSessionAppendURL(driver1);
		wait(driver, "5");
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raCoachUpcomingSessionTimeDifferentBrowser(driver1);*/

		wait( driver, "8" );
		Pattern image2 = new Pattern(Directory.uploadFilePath+"BlankArea.PNG");
		Allow1.wait(image2, 15);
		Allow1.click(image2);
		wait( driver, "3" );
		wait(driver, "6");

		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.shareSelectedDevice(driver1);
		
		wait(driver, "10");
		jsClickByXPath(driver1, COACH_SESSION_END_SESSION_BUTTON);
		wait(driver, "2" );
		jsClickByXPath(driver1, COACH_SESSION_END_SESSION_BUTTON);
		wait(driver1, "3" );
		jsClickByXPath(driver1, COACH_SESSION_END_SESSION_YES_BUTTON);
		wait(driver1, "3" );
		WebElement coachCommentsTextbox= driver1.findElement(By.xpath(OR.COACH_COMMENTS_TEXTBOX));
		sendKeys(coachCommentsTextbox, "Session completed with the member");
		jsClickByXPath(driver1, COACH_END_SESSION_AND_MARKED_AS_COMPLETED);
		wait(driver, "2" );
		jsClickByXPath(driver1, COACH_SESSION_MEMBER_DONE_BUTTON);
		wait(driver1, "4" );
		com.zillion.qa.realappealcoach.coach.realAppealCoachLogout(driver1);

		// Switch Coach to Member
		// Take screen shot for coach video
		com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver1);
		driver1.manage().window().setPosition(new Point(-2000, 0));
		wait(driver1, "4" );
		driver.manage().window().maximize();
		wait(driver, "4");

		WebElement coachRatingTextbox= driver.findElement(By.xpath(OR.MEMBER_COACH_RATING_TEXTBOX));
		WebElement coachRatingSubmitButton= driver.findElement(By.xpath(OR.MEMBER_COACH_RATING_SUBMIT_BUTTON));		
		click(ratingStar1);
		click(ratingStar2);
		click(ratingStar3);
		click(ratingStar4);
		click(ratingStar5);
		sendKeys(coachRatingTextbox, "Session completed with the coach");
		wait(driver1, "2");
		click(coachRatingSubmitButton);
		wait(driver1, "5");

		com.zillion.qa.realappealmember.Claims.retrieveprograminterval(driver,memberEmail2);
		com.zillion.qa.realappealmember.Claims.makeupSessionWeightloss(driver);
		com.zillion.qa.realappealmember.Claims.createPropertyForAttended1on1AndMakeupSession();
		com.zillion.qa.realappealmember.member.realAppealMemberlogout(driver);
		driver.close();
		driver1.close();
	}

	/**
	 * Name :     Vinothkumar.M
	 * Created Date:   26/APR/17
	 * Modified Date:   
	 * Description : Group claims triggered for makeup when 1on1 is missed
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */

	public static void groupClaimsTriggeredForMakeupWhenOneOnOneIsMissed(WebDriver driver,String testData, String coachEmail2, String memberEmail2) throws Exception
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
		com.zillion.qa.realappealmember.member.termsAndConditionPopupHandle( driver1 );

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

		com.zillion.qa.realappealmember.Claims.retrieveprograminterval(driver1,memberEmail2);
		com.zillion.qa.realappealmember.Claims.makeupSessionWeightloss(driver1);
		com.zillion.qa.realappealmember.Claims.createPropertyForGroupClaimsTriggeredForMakeupWhenOneOnOneIsMissed();
		com.zillion.qa.realappealmember.member.realAppealMemberlogout(driver1);

		driver.close();
		driver1.close();

	}	

	/**
	 * Name :     Vinothkumar.M
	 * Created Date:   27/APR/17
	 * Modified Date:   
	 * Description : Group claims triggered for Makeup if it happens prior to 1on1 
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */

	public static void groupClaimsTriggeredForMakeupIfItHappenedPriorToOneonOneSession(WebDriver driver,String testData, String coachEmail2, String memberEmail2) throws Exception
	{
		String[] testData1 = testData.split(",");
		String coachBrowser=testData1[0];
		String memberBrowser=testData1[1];

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
		com.zillion.qa.realappealmember.member.termsAndConditionPopupHandle( driver1 );

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

		// Append URL for member force attend the session
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raMemberOneOnOneSessionAppendURL(driver1);
		wait( driver, "4" );
		waitForElement(driver1, RA_1ON1_LIVE_SESSION_MEMBER_ATTEND_SESSION_JOIN_BUTTON);
		WebElement memberAttendnowButton = driver1.findElement(By.xpath(OR.RA_1ON1_LIVE_SESSION_MEMBER_ATTEND_SESSION_JOIN_BUTTON));
		click(memberAttendnowButton);
		wait( driver1, "3" );

		//Enter member weight before attending session
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raEnterWeightDuringLiveSessionDifferentBrowser( driver1);
		wait( driver1, "5" );

		String sessiontime1=null;
		WebElement memberSessionTime = driver1.findElement(By.xpath(OR.RA_MEMBER_SESSION_TIME));
		String sessionsTime=memberSessionTime.getText();
		SimpleDateFormat ft = new SimpleDateFormat("EEE hhh:mm a");
		java.util.Date t=ft.parse(sessionsTime);
		ft.applyPattern("hh:mma");
		sessiontime1=ft.format(t).toLowerCase().trim();
		System.out.print(ft.format(t));
		System.out.print("Time"+sessiontime1);

		// Switch Member to coach
		driver.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "4");
		driver.manage().window().maximize();
		wait(driver, "4");

		com.zillion.qa.realappealprogramadmin.programadmin.realAppealProgramAdminLogin(driver);

		// Verify Member List - Makeup Attendance, Last Session type and Last Session date

		wait(driver, "5");
		WebElement raCoachMembersTab= driver.findElement(By.xpath(OR.RA_COACH_MEMBERS_TAB));
		verifyElementIsPresent(driver, raCoachMembersTab);
		click(raCoachMembersTab);
		wait(driver, "10");
		WebElement raCoachMembersSortDropdown= driver.findElement(By.xpath(OR.RA_COACH_MEMBERS_SORT_DROPDOWN));
		selectByVisibletext(raCoachMembersSortDropdown,"Email" );
		wait(driver, "1");
		WebElement raCoachMembersSearchTextbox= driver.findElement(By.xpath(OR.RA_COACH_MEMBERS_SEARCH_TEXTBOX));
		sendKeys(raCoachMembersSearchTextbox, memberEmail2);
		WebElement raCoachMembersearchButton= driver.findElement(By.xpath(OR.RA_COACH_MEMBERS_SEARCH_BUTTON));
		click(raCoachMembersearchButton);
		wait(driver, "5");
		jsClickByXPath(driver, PRO_PATIENT_MEMBER_LINK);
		wait(driver, "10" );
		jsClickByXPath(driver, MEMBER_SESSIONS_TAB);
		wait(driver, "7" );
		jsClickByXPath(driver, RA_PA_ONE_ON_ONE_SESSION_ASSIGNED_COACH);
		wait(driver, "7" );
		WebElement coachEmail= driver.findElement(By.xpath(OR.ONE_ON_ONE_SESSION_ASSIGNED_COACH_EMAIL));
		String assignedCoachEmail = coachEmail.getText();
		com.zillion.qa.realappealprogramadmin.programadmin.realAppealProgramAdminLogout(driver);

		Navigate.get(driver, Directory.RA_Provider_Url);
		wait(driver, "5");
		Navigate.maximize(driver);
		WebElement Coacheslogin_logo= driver.findElement(By.xpath(OR.COACHES_LOGIN_PAGE_LOGO_REF));
		waitForElement( driver, OR.COACHES_LOGIN_PAGE_LOGO_REF );
		verifyElementIsPresent(driver, Coacheslogin_logo);
		WebElement coach_username= driver.findElement(By.xpath(OR.COACHES_USERNAME_1));
		verifyElementIsPresent(driver, coach_username);
		WebElement coach_password= driver.findElement(By.xpath(OR.COACHES_PASSWORD));
		verifyElementIsPresent(driver, coach_password);
		WebElement Coacheslogin_button= driver.findElement(By.xpath(OR.COACHES_LOGIN_BUTTON));
		verifyElementIsPresent(driver, Coacheslogin_button);
		sendKeys(coach_username,assignedCoachEmail);
		wait(driver, "2");
		sendKeys(coach_password,"Healthfleet2015");
		wait(driver, "2");
		click(Coacheslogin_button);
		wait(driver, "10");

		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raCoachOneOnOneSessionAppendURLDifferentBrowser(driver);
		wait(driver, "3");
		WebElement coachUpcomingSessionTime = driver.findElement(By.xpath("//tr//td[text()='"+sessiontime1+"']/following::td[text()='1on1']/following-sibling::td//following-sibling::td//following-sibling::td/following-sibling::td/div/a"));
		verifyElementIsPresent(driver, coachUpcomingSessionTime); 
		click(coachUpcomingSessionTime);
		wait(driver, "7");

		// share selected device for the coach Zlivesession
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.shareSelectedDeviceDifferentBrowser(driver);
		
		wait(driver, "10");
		jsClickByXPath(driver, COACH_SESSION_END_SESSION_BUTTON);
		wait(driver, "2" );
		jsClickByXPath(driver, COACH_SESSION_END_SESSION_BUTTON);
		wait(driver, "3" );
		jsClickByXPath(driver, COACH_SESSION_END_SESSION_YES_BUTTON);
		wait(driver, "3" );
		WebElement commentsTextbox = driver.findElement(By.xpath(OR.COACH_1ON1_COMMENTS_TEXTBOX));
       sendKeys(commentsTextbox, "Session completed with the member");
		jsClickByXPath(driver, COACH_SESSION_SESSION_COMPLETED_RADIO_BUTTON);
		wait(driver, "2" );
		jsClickByXPath(driver, COACH_1ON1_SESSION_MEMBER_DONE_BUTTON);
		wait(driver, "4" );

		//RealAppeal coach Logout
		com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.realAppealCoachLogout(driver);

		// Switch Coach to member
		driver.manage().window().setPosition(new Point(-2000, 0));
		wait(driver, "8");
		driver1.manage().window().maximize();
		wait(driver1, "10");

		WebElement ratingTextbox = driver1.findElement(By.xpath(OR.MEMBER_COACH_RATING_TEXTBOX));
		WebElement ratingSubmitButton = driver1.findElement(By.xpath(OR.MEMBER_COACH_RATING_SUBMIT_BUTTON));
		click(ratingStar1);
		click(ratingStar2);
		click(ratingStar3);
		click(ratingStar4);
		click(ratingStar5);
		click(ratingTextbox);
		sendKeys(ratingTextbox, "Session completed with coach");
		click(ratingSubmitButton);
		wait(driver1, "4");
		
		com.zillion.qa.realappealmember.Claims.retrieveprograminterval(driver1,memberEmail2);
		com.zillion.qa.realappealmember.Claims.makeupSessionWeightloss(driver1);
		com.zillion.qa.realappealmember.Claims.createPropertyForAttendedMakeupAndOneOnOneSession();
		com.zillion.qa.realappealmember.member.realAppealMemberlogout(driver1);

		driver.close();
		driver1.close();

	}	
}
