package com.zillion.qa.realappealmember;

public interface OR {
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   22/Apr/16 
	 * Modified Date:  
	 */ 
	public final  String  RA_MEMBER_DATE ="//div[@class='col-xs-12 btn-group-vertical ng-scope open']//button";
	public final  String  RA_MEMBER_INBOX_MESSAGE_COUNT ="//div[@class='pagination-btn pull-right']/span//b[3]";
	public final  String  RA_MEMBER_INBOX_NEXT_PAGENAVIGATOR_DISABLED_BUTTON ="//div[@class='pagination-btn pull-right']//div//button//following-sibling::span/parent::button//following-sibling::button[@disabled='']";
	public final  String  RA_MEMBER_INBOX_NEXT_PAGENAVIGATOR_ENABLED_BUTTON ="//button[@id='nxt_Button_MsgCenter_Member']";
	public final String  YOP_EMAIL_TEXTBOX="//input[@id='login']"; 
	public final String YOP_EMAIL_CHECK_INBOX="//input[@type='submit']"; 
	public final String YOP_EMAIL_IFRAME="//iframe[@id='ifmail']";
	public final String RA_MEMBER_ENROLLMENT_MAIL="//td[contains(.,'Welcome to Real Appeal')]";
	public final String RA_MEMBER_ENROLLMENT_MAIL_CONTENT="//b[text()='The next step is to attend your Online Personalization Session on']";
	public final String GUERRILLA_MAIL_DROPDOWN="//select[@id='gm-host-select']";
	public final String GUERRILLA_MAIL_TEXTBOX="//span//input[@type='text']";
	public final String GUERRILLA_MAIL_SET_BUTTON="//button[contains(text(),'Set')]";
	public final String GUERRILLA_MAIL_SECURITY_EMAIL="//td[contains(.,'Reset Your Security QA')]";
	public final String GUERRILLA_MAIL_EDIT_BUTTON="//span[@id='inbox-id']"; 
	public final String RA_MEMBER_LANDINGPAGE_PASSWORD="//input[@id='navbarLoginFormPassword']"; 
	public final String RA_MEMBER_LOGIN_BUTTON="//button[text()='LOG IN']"; 
	public final String REAL_APPEAL_MEMBER_DASHBOARD_TAB="//li[text()='dashboard']"; 
	public final String RA_MEMBER_1on1_MAIL="//span[contains(text(),'Your 1on1 Coaching Session has been rescheduled')]";
	public final String RA_MEMBER_1on1_CONTENT="//div[contains(text(),'This message is to confirm your new time for your 1-on-1 session')]";
	public final String YOP_EMAIL_IFRAME1="//html/body/div[5]/table/tbody/tr[1]/td/table/tbody/tr/td[1]/table/tbody/tr[2]/td/iframe";
	public final String YOP_EMAIL_IFRAME2="//html/body/div[5]/table/tbody/tr[1]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td[1]/iframe";
	public final String YOP_EMAIL_DELETE ="//a[@class='igif lmenudelall']"; 
	public final String YOP_EMAIL_EMPTY_MAIL="//td[@class='menusep']//div/ul//li[3]/a"; 
	public final String RA_MEMBER_1on1_MAIL_CANCELLATION="//span[contains(text(),'1-on-1 Session Cancellation')]";
	public final String RA_MEMBER_1on1_CONTENT_CANCELLATION="//div[contains(text(),'has been canceled')]";
	public final String RA_MEMBER_1on1_MAIL_SCHEDULED="//*[contains(text(),'Your 1-on-1 Session Confirmation')]";
	public final String RA_MEMBER_1on1_CONTENT_SCHEDULED="//*[contains(text(),'This message is to confirm your upcoming 1-on-1 session.')]";
	public final String RA_MEMBER_LOGIN_MESSAGE="//span[@class='greeting-intro']";  
	public final String ORBERA_MEMBER_1on1_MAIL_SCHEDULE="//*[contains(text(),'1-on-1 Session Rescheduled')]";
	public final String ORBERA_MEMBER_1on1_CONTENT_SCHEDULE="//*[contains(text(),'your new time for your 1-on-1 session')]";
	public final String ORBERA_MEMBER_1on1_MAIL_CANCELLATION ="//span[contains(text(),'1on1 Session Has been Canceled')]"; 
	public final String ORBERA_MEMBER_1on1_CONTENT_SCHEDULE_UPCOMING="//div[contains(text(),'This message is to confirm your upcoming 1-on-1 session')]";
	public final String ORBERA_COACH_1on1_MAIL_GR="//td[contains(.,'Welcome to Real Appeal')]";
	public final String RA_MEMBER_CS_MAIL_CANCELLATION="//*[contains(text(),'Customization Session Cancellation')]";
	public final String YOPMAIL_SECURITY_QA_EMAIL="//*[contains(.,'Reset Your Security QA')]";
	public final String YOPMAIL_SECURITY_QA_EMAIL_CONTENT="//a[contains(.,'Click here to reset your security QA')]";

	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   24/May/16 
	 * Modified Date:
	 * Description: OR for the RealAppeal LiveSession  
	 */ 
	public final String YOPMAIL_DELETE ="//a[contains(@title,'Delete this mail')]";
	public final  String  RA_MEMBER_BEFORE_LOGIN_BUTTON ="//a[@id='loginAnchorId']";
	public final  String  RA_MEMBER_USERNAME_TEXTBOX ="//input[@id='navbarLoginFormEmail']";
	public final  String  RA_MEMBER_PASSWORD_TEXTBOX ="//input[@id='navbarLoginFormPassword']";
	public final  String  RA_MEMBERLIVESESSION_LOGIN_BUTTON ="//button[@id='navbarLoginFormSubmitBtn']";
	public final  String  RA_MEMBERLIVESESSION_SCHEDULE_YOUR_SESSION_TEXT ="//div[text()='Schedule your 1-on-1 session']";
	public final  String  RA_MEMBERLIVESESSION_SCHEDULE_BUTTON ="//button[@id='memberSessionsCard_ScheduleOneOnOneSessions_btn' and text()='schedule']";
	public final  String  RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_HEADER ="//h1[text()='Schedule Individual session']";
	public final  String  RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_TITLE ="//h3[text()='Next Available Spots']";
	public final  String  RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_TIMEZONE_DROPDOWN ="//select[@id='timeZone']";
	public final  String  RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_AVAILABLE_BUTTON ="//div[@id='timeSelection']//button[@ng-disabled='false']";
	public final  String  RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_AVAILABLE_BUTTON_AVAILABLE_TIME ="//div[@id='timeSelection']//button[@ng-disabled='false']/following-sibling::ul/li[2]/a";
	public final  String  RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_CONFIRM_BUTTON ="//button[@id='btnSchedule']";
	public final  String  RA_1ON1_LIVE_SESSION_MEMBER_ATTEND_SESSION_JOIN_BUTTON ="//div[text()='Your 1-on-1 Session:']//following-sibling::div/parent::div/following-sibling::div/button[@id='memberSessionsCard_JoinOneOnOneSessions_btn']";
	public final  String  RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CONNECTED_TEXT ="//div[@class='live-session-inner-wrapper row']/div/div//div[2]/div/span[text()='Connected!']";
	public final  String  RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CLOSE_BUTTON ="//span[@class='modal-close']";
	public final  String  RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CLOSE_BUTTON_CLICK_CONFIRM_TEXT ="//p[text()='Are you sure you want to close the session?']";
	public final  String  RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CLOSE_BUTTON_CLICK_CANCEL_BUTTON ="//button[@ng-click='CANCELcloseConfirmModal()']";
	public final  String  RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CLOSE_BUTTON_CLICK_OK_BUTTON ="//button[@ng-click='OKcloseConfirmModal()']";
	public final  String  RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CHAT_TEXTBOX ="//input[@ng-model='chatMessage.message']";
	public final  String  RA_1ON1_LIVE_SESSION_MEMBER_JOINED_SESSION_CHAT_SEND_BUTTON ="//button[@ng-click='sendChatMessage()']";
	public final String RA_MEMBER_DASHBOARD_RESCHDULE_BUTTON="//button[@id='memberSessionsCard_ChangemissedOneOnOneSession_btn']";
	public final String RA_COACH_ATTEND_1ON1_SESSION_ATTENDNOW_BUTTON="//td[contains(text(),'1on1')]/following-sibling::td[4]/div/a[contains(text(),'Attend Now')]";

	
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   24/May/16 
	 * Modified Date:  
	 */ 
	public final String MEMBER_SESSION_MIC_ON="//div[@id='mic-status-icon'and@class='device-status-icon']";
	public final String MEMBER_SESSION_MIC_OFF="//div[@id='mic-status-icon'and@class='device-status-icon highlight']";
	public final String MEMBER_SESSION_VIDEO_ON="//div[@id='video-status-icon'and@class='device-status-icon']";
	public final String MEMBER_SESSION_VIDEO_OFF="//div[@id='video-status-icon'and@class='device-status-icon highlight']";
	public final String MEMBER_SESSION_CHAT_TEXTBOX="//input[@ng-model='chatMessage']";
	public final String MEMBER_SESSION_CHAT_SEND_BUTTON="//div[@id='chat-send-button']//button[@ng-click='sendChatMessage()']";
	public final String WELCOME_HEADER="//div[@class='livesession-section-wrapper']//div//span[text()='Welcome to your 1on1 session']";
	public final String CONNECTED_TO_LIVE_SESSION="//div[@id='memberCanStreamVideoContainer']/div/div/div/div[3]/livesession-connected[contains(text(),'Connected')]";

	public static String LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_BUTTON ="//span[@ng-click='openSettings()']"; 
	public static String LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_TITLE ="//div[@class='modal-title text-center' and text()='Settings']"; 
	public static String LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_CLOSE_BUTTON ="//span[@ng-click='modal.cancel()']"; 
	public static String LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_VIDEO_OPTION ="//a[text()='Video']"; 
	public static String LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_AUDIO_OPTION ="//a[text()='Audio']"; 
	public static String LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_CAMERA_LABEL ="//label[text()='Camera:']"; 
	public static String LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_CAMERA_DROPDOWN ="//select[@id='camSelect']"; 
	public static String LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_CAMERA_OPTION ="//option[contains(text(),'Integrated Webcam')]"; 
	public static String LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_CHECK_CONNECTIVITY_BUTTON ="//button[text()='CHECK CONNECTIVITY']"; 
	public static String LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_MICROPHONE_LABEL ="//label[text()='Microphone:']"; 
	public static String LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_SPEAKERS_LABEL ="//label[text()='Speakers:']"; 
	public static String LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_SPEAKERS_VOLUME_LABEL ="//label[text()='Speaker Volume Control:']"; 
	public static String LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_MIC_DROPDOWN ="//select[@id='micSelect']"; 
	public static String LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_SPEAKER_DROPDOWN ="//select[@id='spkSelect']"; 
	public static String LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_SLIDER ="//ui-slider[@class='ui-slider-default']"; 
	public static String LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_PLAY_TEST_SOUND_BUTTON ="//a[text()='Play test sound']"; 
	public static String LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_MICROPHONE_OPTION ="//option[contains(text(),'Microphone (High Definition Audio Device)')]"; 
	public static String LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_SPEAKER_OPTION ="//option[contains(text(),'Speakers (High Definition Audio Device)')]"; 
	public static String LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_MICROPHONE_VOLUME_LABEL ="//label[text()='Microphone volume:']"; 
	public static String LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_MICROPHONE_ACTIVITY_LABEL ="//label[text()='Microphone activity:']"; 
	public static String LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_MIC_ENABLE_CHECKBOX ="//input[@id='micActivityEnabledChckbx']"; 
	public static String LIVESESSION_1ON1_SESSION_COACH_SETTINGS_TITLE ="//div[text()='Settings']"; 
	public static String LIVESESSION_1ON1_SESSION_COACH_SETTINGS_CLOSE_BUTTON ="//span[@id='activity-modal-close']";
	public static String LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_MAC_CAMERA_OPTION ="//option[contains(text(),'FaceTime HD Camera (Built-in)')]";

	/**
	 * Name :     VigneshRaj
	 * Created Date:   25/May/2016 
	 * Modified Date: 
	 *  RA 1on1 Live session Coach Login
	 */

	public static String COACHES_LOGIN_PAGE_LOGO_REF ="//div[@id='orgLoginBoxLogo']";
	public static String COACHES_USERNAME_1 ="//input[@type='email']";
	public static String COACHES_PASSWORD ="//input[@type='password']";
	public static String COACHES_LOGIN_BUTTON ="//button[@id='login_button']";
	public final String COACH_1ON1_ATTEND_NOW_BUTTON="//tr//td[text()='1on1']/following-sibling::td/following-sibling::td/following-sibling::td/following-sibling::td/div/a[contains(text(),'Attend Now')]";
	public final String COACH_MEMBER_CONNECTED_TEXT="//div[@id='member-tracker']//dl//div[text()='Member Connected']";
	public final String COACH_CONNECTION_HISTORY_TEXT="//div[@id='member-tracker']//dl/dt[text()='Connection History']";
	public final String COACH_SESSION_SESSION_TIMER_LABEL="//div[@id='member-tracker']//dl//dt[text()='Session Timer']";
	public final String COACH_SESSION_MAILING_ADDRESS_LABEL="//div[@id='member-tracker']//dl//dt[text()='Mailing Address']";
	public final String COACH_SESSION_DOB_LABEL="//div[@id='member-tracker']//dd//strong[text()='Date of Birth:']";
	public final String COACH_SESSION_ALLOWED_CONTACT_LABEL="//div[@id='member-tracker']//dl//dt[text()='Allowed Contact Method(s)']";
	public final String COACH_SESSION_MEMBER_LOCAL_TIME_LABEL="//div[@id='member-tracker']//dd//strong[text()='Member local time:']";
	public final String COACH_SESSION_BROWSE_NOTES_BUTTON="//div[@id='member-tracker']//button[text()='Browse Notes']";
	public final String COACH_SESSION_SETTINGS_BUTTON="//div//span[@id='openSettingsGear']";
	public final String COACH_SESSION_END_SESSION_BUTTON="//div//button[@id='endSessionBtn']";
	public final String COACH_SESSION_MIC_ON="//div[@id='mic-status-icon'and@class='device-status-icon']";
	public final String COACH_SESSION_VIDEO_ON="//div[@id='video-status-icon'and@class='device-status-icon']";
	public final String COACH_SESSION_CHAT_TEXTBOX="//input[@id='chatMsgInputField']";
	public final String COACH_SESSION_CHAT_SEND_BUTTON="//div[@id='chat-send-button']//button[@ng-click='sendChatMessage()']";
	public final String MEMBER_DASHBOARD_ICON="//div[@id='navbarWrapper']//div[@id='navbar-member']//ul//li//a//i";
	public final String COACH_SESSION_MIC_DISABLED="//div[@class='toolbar']//div[2]//span[@id='memberMic0'and@class='clickable fa fa-microphone-slash red']";
	public final String COACH_SESSION_MIC_ENABLED="//div[@class='toolbar']//div[2]//span[@id='memberMic0'and@class='clickable fa fa-microphone green']";
	public final String COACH_SESSION_CAMERA_ENABLED="//div[@class='toolbar']//div[2]//span[@id='memberCamera0'and@class='clickable fa fa-video-camera green']";
	public final String COACH_SESSION_CAMERA_DISABLED="//div[@class='toolbar']//div[2]//span[@id='memberCamera0'and@class='clickable fa fa-eye-slash red']";
	public static String LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_FIREFOX_BROWSER_ICON="//span[@class='users-browser-icon sprite-Firefox-icon']";
	public static String LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_CHROME_BROWSER_ICON="//span[@class='users-browser-icon sprite-Chrome-icon']";
	public static String LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_IE_BROWSER_ICON="//span[@class='users-browser-icon sprite-Internet-ie-icon']";
	public static String LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_MEMBER_ID=" //div[@class='modal-body-notification ng-scope']//a";
	public static String LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_OK_BUTTON="//button[text()='OK']";
	public static String LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_MEMBER_ID_HEADER="//strong[text()='Member ID:']";
	public static String LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_SESSION_ID_HEADER="//strong[text()='Session ID:']";
	public static String LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_USER_AGENT_HEADER="//strong[text()='UserAgent:']";
	public static String LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_LIVESESSION_VERSION_HEADER="//strong[text()='LiveSession Version:']";
	public static String LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_MICROPHONE_OPTIONS_HEADER="//strong[text()='Microphone Options:']";
	public static String LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_MICROPHONE_SELECTED_HEADER="//strong[text()='Microphone Selected:']";
	public static String LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_SPEAKER_OPTIONS_HEADER="//strong[text()='Speaker Options:']";
	public static String LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_SPEAKER_SELECTED_HEADER="//strong[text()='Speaker Selected:']";
	public static String LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_CAMERA_OPTIONS_HEADER="//strong[text()='Camera Options:']";
	public static String LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_CAMERA_SELECTED_HEADER="//strong[text()='Camera Selected:']";
	public final String COACH_SESSION_END_SESSION_TEXT="//div[@class='modal-header']//div[contains(text(),'Are you sure you want to leave this session?')]";
	public final String COACH_SESSION_END_SESSION_YES_BUTTON="//button[contains(text(),'Yes')]";
	public final String COACH_SESSION_END_SESSION_NO_BUTTON="//button[contains(text(),'No')]";
	public final String COACH_SESSION_SESSION_COMPLETE_HEADER_TEXT="//div[@class='modal-header text-center no-p']//span[text()='Session Complete']";
	public final String COACH_SESSION_YOUR_1ON1_SESSION_ENDED_TEXT="//div[@class='text-center modal-body']//span[text()='Your one on one session has ended.']";
	public final String COACH_1ON1_COMMENTS_TEXTBOX="//div[@class='session-text-response']//textarea[@ng-model='sessionComments']";
	public final String COACH_SESSION_SESSION_COMPLETED_RADIO_BUTTON="//div[@class='radio']//input[@value='Completed']";
	public final String COACH_SESSION_MEMBER_DID_NOT_ATTEND_RADIO_BUTTON="//div[@class='radio']//input[@value='Missed']";
	public final String COACH_SESSION_SESSION_COMPLETED_TEXT="//div[@class='radio']//span[text()='Session Completed']";
	public final String COACH_SESSION_MEMBER_DID_NOT_ATTEND_TEXT="//div[@class='radio']//span[text()='Member Did Not Attend']";
	public final String COACH_SESSION_MEMBER_DONE_BUTTON="//button[text()='Done']";
	public final String MEMBER_COACH_RATING_HEADER_TEXT="//div[@class='modal-title text-center rating-head ng-binding']";
	public final String MEMBER_COACH_STAR1="//div[@class='session-star-rating']//span/i[1]";
	public final String MEMBER_COACH_STAR2="//div[@class='session-star-rating']//span/i[2]";
	public final String MEMBER_COACH_STAR3="//div[@class='session-star-rating']//span/i[3]";
	public final String MEMBER_COACH_STAR4="//div[@class='session-star-rating']//span/i[4]";
	public final String MEMBER_COACH_STAR5="//div[@class='session-star-rating']//span/i[5]";
	public final String MEMBER_COACH_RATING_TEXTBOX="//textarea[@ng-model='textResponse']";
	public final String MEMBER_COACH_RATING_SUBMIT_BUTTON="//button[text()='Submit']";
	public final String MEMBER_COACH_RATING_CLOSE_BUTTON="//div[@class='modal-header row']//div//span[@ng-click='modal.cancel()']";
	public final String  RA_10N1SESSION_CONFIRM_SUCCESS_MSG="//p[contains(text(),'You have scheduled the 1on1 for')]/following-sibling::button";
	public final String  RA_10N1SESSION_RECONFIRM_SUCCESS_MSG="//p[contains(text(),'You have rescheduled the 1on1 for')]/following-sibling::button";
	public final String COACH_SESSION_MIC_OFF="//div[@id='mic-status-icon'and@class='device-status-icon highlight']";
	public final String COACH_SESSION_VIDEO_OFF="//div[@id='video-status-icon'and@class='device-status-icon highlight']";
	public final String SCHEDULE_CUSTOMIZATION_TIME_AND_DATE="//span[@id='selectedTime']";
	public final String TIME_AND_DATE_AFTER_SCHEDULE_CUSTOMIZATION_="//div[@class='sessionCancellationContainer']//div[2]/div";
	public final String EPIC_INPUTBOX="//input[@id='ecinput']";
	public final String EPIC_TIMESTAMP_TO_HUMANDATE="//button[contains(@title,'Epoch to Human date ')]";
	public final String EPIC_RESULT="//div[@id='result1']//span";
	public final String PROVIDER_USER_SESSION_POPUP="//p[contains(text(),'user is already active in another browser')]//following-sibling::button";
	public final String COACH_COMMENTS_TEXTBOX="//textarea[@id='coachNotesTA']";
	public final String COACH_END_SESSION_AND_MARKED_AS_COMPLETED="//div[@class='session-status-response col-centered']/div/label/input[@id='gsNotesOpt1']";
	public final String GROUP_SESSION_COACH_RATING_STAR1="//span[@class='session-star-display ng-isolate-scope ng-valid']/i[1]";
	public final String GROUP_SESSION_COACH_RATING_STAR2="//span[@class='session-star-display ng-isolate-scope ng-valid']/i[2]";
	public final String GROUP_SESSION_COACH_RATING_STAR3="//span[@class='session-star-display ng-isolate-scope ng-valid']/i[3]";
	public final String GROUP_SESSION_COACH_RATING_STAR4="//span[@class='session-star-display ng-isolate-scope ng-valid']/i[4]";
	public final String GROUP_SESSION_COACH_RATING_STAR5="//span[@class='session-star-display ng-isolate-scope ng-valid']/i[5]";
	public final String COACH_SESSION_1ON1_MEMBER_DONE_BUTTON="//button[text()='DONE']";
	
	
	/**
	 * Name :Vinothkumar.M
	 * Created Date:   9/June/16 
	 * Modified Date:  
	 * RA Group session Member side
	 */ 
	public final String RA_MEMBER_GROUP_SESSION_JOIN_BUTTON="//div[@ng-controller='groupsessionModalCtrl']/button[text()='join']";
	public final String RA_MEMBER_SESSION_MIC_ON="//div[@id='mic-status-icon'and@class='device-status-icon']";
	public final String RA_MEMBER_SESSION_MIC_OFF="//div[@id='mic-status-icon'and@class='device-status-icon gray']";

	/**
	 * Name :Vinothkumar.M
	 * Created Date:   9/June/16 
	 * Modified Date:  
	 * RA Group session Coach side
	 */ 
	public final String COACH_RA_GROUP_SESSION_MEMBER_ONLINE_HEADER="//p[@id='coachMembersOnline']";
	public final String COACH_RA_GROUP_SESSION_SHARE_SCREEN_BUTTON="//button[@id='openScreenShareBtn']";
	public final String COACH_RA_GROUP_SESSION_COACH_TILE_MEMBER_MIC_DISABLED="//span[@class='clickable fa fa-microphone-slash gray']";
	public final String COACH_RA_GROUP_SESSION_VIDEO_BUTTON="//div[@class='btn-group']//a[contains(text(),'Video')]";
	public final String COACH_RA_GROUP_SESSION_CONTENT_BUTTON=" //div[@class='btn-group']//a[contains(text(),'Content')]";
	public final String COACH_RA_GROUP_SESSION_SCRIPT_BUTTON="//div[@class='btn-group']//a[contains(text(),'Script')]";
	public final String COACH_RA_GROUP_SESSION_SESSION_COMPLETE_HEADER_TEXT="//div[@class='modal-header']//h3[text()='Session Complete']";
	public final String COACH_RA_GROUP_SESSION_YOUR_GROUP_SESSION_ENDED_TEXT="//div//span[contains(text(),'Your Group Session has ended')]";
	public final String COACH_RA_GROUP_SESSION_SESSION_COMMENTS_TEXTBOX="//div[@class='session-text-response']//textarea[@ng-model='coachNotes']";
	public final String COACH_RA_GROUP_SESSION_END_SESSION_RADIO_BUTTON="//input[@id='gsNotesOpt1' and @type='radio']";
	public final String COACH_RA_GROUP_SESSION_LEAVE_THE_SESSION_AND_JOIN_AGAIN_RADIO_BUTTON="//input[@id='gsNotesOpt2' and @type='radio']";
	public final String COACH_RA_GROUP_SESSION_CANCEL_AND_RESUME_THE_SESSION_RADIO_BUTTON="//input[@id='gsNotesOpt3' and @type='radio']";
	public final String COACH_RA_GROUP_SESSION_END_SESSION_TEXT="//span[contains(text(),'End session and mark as completed')]";
	public final String COACH_RA_GROUP_SESSION_LEAVE_THE_SESSION_AND_JOIN_AGAIN_TEXT="//span[contains(text(),'Leave the session and join again')]";
	public final String COACH_RA_GROUP_SESSION_CANCEL_AND_RESUME_THE_SESSION_TEXT="//span[contains(text(),'Cancel and resume the session')]";
	public final String COACH_RA_GROUP_SESSION_MEMBER_DONE_BUTTON="//button[contains(text(),'Done')]";
	public final String COACH_RA_GROUP_SESSION_PLEASE_SELECT_ONE_OF_THE_OPTIONS_ERROR_MSG="//div[contains(text(),'Please select one of the options')]";
	public final String COACH_RA_GROUP_SESSION_ATTEND_NOW_BUTTON="//tr//td[text()='Group']/following-sibling::td//following-sibling::td//following-sibling::td/following-sibling::td/div/button";
	public final String COACH_RA_GROUP_SESSION_SCREEN_SHARE_HEADER="//h3[text()='Screen Share']";
	public final String COACH_RA_GROUP_SESSION_SCREEN_SHARE_REFRESH_BUTTON="//a[@title='refresh available screens / windows']";
	public final String COACH_RA_GROUP_SESSION_SHARE_SCREEN_IMAGE="//div[@id='webRtcShareScreenModal']/div[2]/a";
	public final String COACH_RA_GROUP_SESSION_STOP_SHARING_SCREEN="//button[@id='stopScreenShareBtn']";

	/**
	 * Name :     VigneshRaj
	 * Created Date:   25/May/2016 
	 * Modified Date: 
	 *  RA 1on1 Live session Coach Login
	 */
	public final String RA_MEMBER_LOGOUT_BUTTON="//a[@id='Trackers_Weight_Logout_Link']";
	public final String RA_MEMBER_SIGNED_OUT_TEXT="//h1[contains(text(),'You have signed out.')]";
	public final String RA_MEMBER_LOGOUT_BUTTON_TOP="//a[@id='navbarLogOutLink']";
	public final String COACH_RA_GROUP_SESSION_EJECT_VIDEO_BUTTON="//button[@id='stopRecordedVideo']";
	public final String COACH_RA_GROUP_SESSION_VIDEO="//div//img[@class='img-responsive center-block']";
	public final String COACH_RA_GROUP_SESSION_RESTART_FROM_THE_BEGINNING_BUTTON="//button[@id='restartRecordedVideo']";
	public final String COACH_RA_GROUP_SESSION_PLAY_VIDEO_BUTTON="//button[@id='resumeRecordedVideo']";
	public final String COACH_RA_GROUP_SESSION_PAUSE_BUTTON="//button[@id='pauseRecordedVideo']";
	public final String COACH_RA_GROUP_SESSION_JUMP_BACK_THIRTY_SECONDS_BUTTON="//button[@id='back30RecordedVideo']";
	public final String COACH_RA_GROUP_SESSION_JUMP_FORWARD_THIRTY_SECOND_BUTTON="//button[@id='foreword30RecordedVideo']";
	public final String COACH_RA_GROUP_SESSION_MUTE_MEMBERS_BUTTON="//div[@class='tabbable tabs-left']//button[@id='muteAllMembersBtn']";
	public final String COACHES_UPCOMING_SESSION_DATE_FIELD="//span[text()='Upcoming Sessions']//following::tbody/tr/td[2]";
	public final String COACH_RA_GROUP_SESSION_CLASSES_TIME_FIELD="//tr[@id='approvedClasses_TableRow0']//td[6]";
	public final String COACH_RA_GROUP_SESSION_CLASSES_TAB="//span[contains(text(),'Classes')]";
	public final String COACH_CLASSES_TAB_CHEVRON_LINK="//a[@id='approvedClasses_Item_User_Link0']";
	public final String COACH_CLASSES_TAB_MEMBER_NAME="//table[@id='memberInfoTable']//tbody//div//a";
	public final String COACH_CLASSES_TAB_MEMBER_PROFILE_EMAIL="//input[@id='username']";
	public final String COACH_DASHBOARD_TAB="//span[contains(text(),'Dashboard')]";
	public final String COACH_RA_GROUP_SESSION_COACH_TILE_MEMBER_MIC_ENABLED="//span[@class='fa fa-microphone green clickable']";

	/**
	 * Name : Vinothkumar.M
	 * Created Date: 13/June/2016 
	 * Modified Date: 
	 * RA Group Live session Content Articles & PDF
	 */
	public final String COACH_CONTENT_CONTRACT_ARTICLES_LINK="//h4[contains(text(),'Contract')]";
	public final String COACH_CONTENT_CONTRACT_ARTICLES_COMMITMENT_CONTRACT_MAIN_HEADER="//span[contains(text(),'Commitment Contract')]";
	public final String COACH_CONTENT_CONTRACT_ARTICLES_HEADER="//div[@class='activity-description' and contains(text(),'focus on becoming a savvier shopper and choosing healthier carbs')]";
	public final String COACH_CONTENT_CONTRACT_ARTICLES_SUB_HEADER="//div[contains(text(),'Setting myself up for success entails bringing home the right foods')]";
	public final String COACH_CONTENT_CONTRACT_ARTICLES_REAL_APPEAL_SHOPPING_LIST_LABEL="//span[@class='custom-check-btn']/input/following::strong[text()='Real Appeal shopping list']/parent::label";
	public final String COACH_CONTENT_CONTRACT_ARTICLES_NUTRITION_FACTS_LABEL="//span[@class='custom-check-btn']/input/following::strong[text()='read the Nutrition Facts label']/parent::label";
	public final String COACH_CONTENT_CONTRACT_ARTICLES_EXAMINE_THE_INGREDIENT_LISTS_LABEL="//span[@class='custom-check-btn']/input/following::strong[text()='examine the ingredient lists']/parent::label";
	public final String COACH_CONTENT_CONTRACT_ARTICLES_CONTINUE_MY_OTHER_HEALTHY_HEALTHY_HABITS_LABEL="//span[@class='custom-check-btn']/input/following::strong[text()='continue my other healthy habits']/parent::label";
	public final String COACH_CONTENT_CONTRACT_ARTICLES_QUESTION_IS_REQUIRED_ERROR_MSG="//li[contains(text(),'This question is required')]";
	public final String COACH_CONTENT_CONTRACT_ARTICLES_I_COMMIT_BUTTON="//div[@class='sg-button-bar']//input";
	public final String COACH_CONTENT_CONTRACT_ARTICLES_SUCCESS_MSG=" //div[contains(text(),'Good Luck')]";
	public final String COACH_CONTENT_CONTRACT_ARTICLES_SIGNED_UP_FOR_YOUR_COMMITMENT_CONTRACT_MSG=" //div[contains(text(),'You have now signed up for your commitment contract.')]";
	public final String COACH_CONTENT_QUIZ_ARTICLES="//h4[contains(text(),'Session 6: Quiz')]";
	public final String COACH_CONTENT_QUIZ_ARTICLES_QUESTION="//div[text()='Which has more calories?']";
	public final String COACH_CONTENT_QUIZ_ARTICLES_ANSWER_OPTION1="//label[contains(text(),'Pure Leaf Lemon Sweetened Iced Tea')]/div[@class='img-question-header']/span[text()='Lipton' ]";
	public final String COACH_CONTENT_QUIZ_ARTICLES_ANSWER_OPTION2="//label/div[@class='img-question-header']/span[text()='Honest Tea']";
	public final String COACH_CONTENT_QUIZ_ARTICLES_ANSWER_SUBMIT_BUTTON="//input[@id='sg_SubmitButton']";
	public final String COACH_CONTENT_QUIZ_ARTICLES_QUIZ_SCORE=" //div[text()='Quiz Score']";
	public final String COACH_CONTENT_QUIZ_ARTICLES_QUIZ_WRONG_ANSWER=" //div[@class='sg-question-options']/div/div[text()='Sorry!! The answer is Lipton Pure Leaf Lemon Sweetened Ice tea']";
	public final String COACH_CONTENT_QUIZ_ARTICLES_QUIZ_RIGHT_ANSWER=" //div[@class='sg-question-options']/div/div[text()='Congratulations!!! The answer is Lipton Pure Leaf Lemon Sweetened Ice tea']";
	public final String COACH_CONTENT_SESSION_GUIDE_ARTICLES_LINK="//h4[contains(text(),'Session Guide')]";
	public final String COACH_SESSION_GUIDE_FULLSCREEN="//button[@id='presentationMode']";
	public final String COACH_SESSION_GUIDE_AUTOMATICZOOM="//select[@id='scaleSelect']";
	public final String COACH_SESSION_GUIDE_PAGINATION="//input[@id='pageNumber']/following-sibling::span[@id='numPages']";
	public final String COACH_SESSION_GUIDE_PRINT="//button[@id='print']";
	public final String COACH_SESSION_GUIDE_DOWNLOAD="//button[@id='download']";
	public final String COACH_SESSION_GUIDE_TOGGLE_SLIDEBAR="//button[@id='sidebarToggle']";
	public final String COACH_SESSION_PAGNUMBER_INPUT="//input[@id='pageNumber']";
	public final String COACH_SESSION_GUIDE_PAGE2="//*[@id='pageContainer2']/xhtml:div[2]/xhtml:div[46]";
	public final String COACH_SESSION_GUIDE_SECONDARY_TOOLBAR="//button[@id='secondaryToolbarToggle']";
	public final String COACH_SESSION_GUIDE_FIRST_PAGE="//button[@id='firstPage']";
	public final String COACH_SESSION_GUIDE_ROTATE_CLOCKWISE="//button[@id='pageRotateCw']";
	public final String COACH_SESSION_GUIDE_ROTATE_ANTICLOCKWISE=" //button[@id='pageRotateCcw']";
	public final String COACH_SESSION_GUIDE_DOCUMENT_PROPERTIES="//button[@id='documentProperties']";
	public final String COACH_SESSION_GUIDE_DOCUMENT_PROPERTIES_POPUP_CLOSE="//button[@id='documentPropertiesClose']";
	public final String COACH_SESSION_GUIDE_LASTPAGE="//*[@id='pageContainer1']/xhtml:div[2]";
	public final String MEMBER_RA_GROUP_SESSION_RAISE_HAND="//span[contains(text(),'RAISE HAND')]";
	public final String MEMBER_RA_GROUP_SESSION_LOWER_HAND="//span[contains(text(),'LOWER HAND')]";
	public final String COACH_RA_GROUP_SESSION_LOWER_HAND_SYMBOL="//span[@ng-click='lowerHand(engagedUser)']";
	public final String COACH_RA_GROUP_SESSION_LOWER_ALL_HANDS_BUTTON="//button[@id='lowerAllHandsBtn']";
	public final String COACH_RA_GROUP_SESSION_SCRIPT_LINK="//span[@class='highlight']/a";
	public final String RA_MEMBER_GROUP_SESSION_UPCOMING_GROUP_SESSIONS_TITLE="//div[@id='memberSessionsCard_UpcomingGroupSessions_title']";
	public final String RA_MEMBER_GROUP_SESSION_UPCOMING_GROUP_SESSIONS_COUNTDOWN="//div[@id='memberSessionsCard_UpcomingGroupSessions_countdown']";
	public final String RA_MEMBER_GROUP_SESSION_PROFILE_LINK="//a[@id='navbar-identity-profile-link']";
	public final String RA_MEMBER_GROUP_SESSION_1ON1_SESSION_YOUR_SCHEDULE_HEADER="//span[@id='profile_MySchedule_Label']";
	public final String RA_MEMBER_SESSION_TIME="//h5[@class='livesession-col col-3 session-time ng-binding']";
	public final String RA_COACH_CLASSES_TAB_PROGRAM_DROPDOWN="//select[@id='pname']";
	public final String RA_COACH_CLASSES_TAB_GO_BUTTON="//button[@id='approvedClassesSearchBtn']";
	public final String RA_COACH_CLASSES_TAB_START_DATE_FILTER="//span[@id='approvedClassesFilterDown_ClassDate']";
	public final String RA_MEMBER_INSURANCEPROVIDER="//input[@id='insuranceProvider']";
	public final String RA_MEMBER_MEMBERID="//input[@id='memberId']";
	public final String RA_MEMBER_GROUPNUMBER="//input[@id='groupNumber']";
	public final String RA_MEMBER_FIRSTNAME="//input[@id='security_firstName']";
	public final String RA_MEMBER_LASTNAME="//input[@id='security_lastName']";
	public final String RA_MEMBER_DOBMONTH="//select[@id='dobMonth']";
	public final String RA_MEMBER_DOBDAY="//select[@id='dobDay']";
	public final String RA_MEMBER_DOBYEAR="//select[@id='dobYear']";
	public final String RA_FIRSTNAME_AFTER_INSURANCE="//input[@id='signatureFirstName']";
	public final String RA_LASTNAME_AFTER_INSURANCE="//input[@id='signatureLastName']";
	public final String RA_MEMBER_LANDINGPAGE_USERNAME="//input[@id='navbarLoginFormEmail']";
	public final String RA_INSURANCE_AUTO_SUGGEST="//li[contains(@id,'typeahead')]//a";
	public final String RA_INSURANCE_AUTO_SUGGEST_USECASE_FOUR="//ul[@role='listbox']//li[2]/a";
	public final String RA_PROGRAM_ADMIN_REAL_APPEAL_LOGO="//a[@id='orgNavbarBrand']";
	public final String RA_MEMBER_SETTINGS_PHI_POPUP_ACCEPT_BUTTON="//button[@id='agreementAccept']";
	public final String RA_MEMBER_STEP1_NEXTBUTTON_ENABLED="//button[text()='Next Step']";
	public final String RA_MEMBER_PHI_DECLINE_BUTTON="//button[@id='agreementDecline']";
	public final String RA_MEMBER_LOGIN_LINK="//*[@id='loginAnchorId']";
	public final String RA_MEMBER_LOGIN_PASSWORD="//input[@type='password']";
	
	
	

	/**
	 * Name :     ABINAYA.P
	 * Created Date:   22/July/16 
	 * Modified Date:  
	 */ 
	public final String RA_MEMBER_TRACKERS_TAB="//li[text()='Trackers']";
	public final String RA_MEMBER_WEIGHT_SUB_TAB="//a[text()='Weight']";
	public final String RA_MEMBER_TRACK_WEIGHT_HEADER_TEXT="//span[text()='Track Weight']";
	public final String RA_MEMBER_CURRENT_WEIGHT="//span[text()='Current Weight']/parent::div/following-sibling::div/span[@id='Trackers_Weight_Right_Label']";
	public final String RA_MEMBER_WEIGHT_REDUCE_TEXTBOX="//input[@id='trackersWeightEnterWeight']";
	public final String RA_MEMBER_TRACKER_WEIGHT_SAVE_BUTTON="trackersWeightSaveWeightBtn";
	public final String RA_BREAKFAST_CALORIES="//tbody//tr[1]//td[contains(text(),'cal')]";
	public final String RA_LUNCH_CALORIES="//tbody//tr[2]//td[contains(text(),'cal')]";
	public final String RA_DINNER_CALORIES="//tbody//tr[3]//td[contains(text(),'cal')]";
	public final String RA_SNACK_CALORIES="//tbody//tr[4]//td[contains(text(),'cal')]";
	public final String RA_TOTAL_CALORIES="//div/*[local-name()='svg']/*[3]/*[local-name()='image']/following-sibling::*[local-name()='text']";
	public static String SIGN_WITH_DIFFERENT_ACCOUNT = "//a[contains(text(),'Sign in with a different account')]";
	public static String ADD_ACCOUNT = "//a[text()='Add account']";
	public static String MESSAGE_AREA = "//div[@aria-label='Message Body']";
	public static String RA_CONNECTIVITY_ISSUE_OK = "//div[@class='modal-content']//div[4][@class='row-centered']//div//button[contains(text(),'OK')]";

	/**
	 * Name :     LEENA P.
	 * Created Date:   Aug/10/16
	 * Modified Date:  
	 */ 
	public final String ORBERA_MEMBER_1on1_MAIL_RESCHEDULE="//span[contains(text(),'Your 1on1 Coaching Session has been rescheduled')]";
	public final String ORBERA_MEMBER_1on1_CONTENT_RESCHEDULE="//div[contains(text(),'This message is to confirm your upcoming 1-on-1 session')]";
	public final String ORBERA_MEMBER_1on1_MAIL_MISSED="//*[contains(text(),'1-on-1') and contains(text(),'Session') and contains(text(),'Missed')]";
	public final String ORBERA_MEMBER_LECTURE_MAIL_MISSED="//span[contains(text(),'Healthy Body') and contains(text(),'Session') and contains(text(),'Missed')]";
	public final String ORBERA_MEMBER_CONTENT_MISSED="//span[contains(text(),'You missed our scheduled')]";
	public final String ORBERA_MEMBER_1on1_CONTENT_MISSED="//span[contains(text(),'1-on-1')]";
	public final String ORBERA_MEMBER_LECTURE_CONTENT_MISSED="//span[contains(text(),'Healthy Body')]";
	public final String YOPMAIL_IFRAME1="ifinbox";
	public final String YOPMAIL_IFRAME2="ifmail";
	public final String ORBERA_MEMBER_FROM_HFOPS_SESSION_STATUS_PAGE_NAVIGATOR="//button[@id='next_Button_Request']";
	public final String ORBERA_MEMBER_FROM_HFOPS_SESSION_STATUS_LECTURE="//td[contains(@id,'sessionClasses_tdStatus') and contains(text(),'Completed')]";
	public final String ORBERA_MEMBER_FROM_HFOPS_SESSION_STATUS_1on1="//td[contains(@id,'sessionClasses_tdSessionType') and contains(text(),'1on1')]//parent::tr//td[contains(@id,'sessionClasses_tdStatus') and contains(text(),'Missed')]";
	public final String ORBERA_MEMBER_1on1_MAIL_REMINDER="//span[contains(text(),'1on1 Session') and contains(text(),'Reminder')]";
	public final String ORBERA_MEMBER_1on1_CONTENT_REMINDER="//div[contains(text(),'Your 1-on-1 is scheduled')]";
	public final String ORBERA_MEMBER_LECTURE_MAIL_SIGNUP="//span[contains(text(),'Your Group Session') and contains(text(),'Reminder')]";
	public final String ORBERA_MEMBER_LECTURE_MAIL_CONTENT_SIGNUP="//div[contains(text(),'Your Healthy Body is scheduled for')]";
	public final String ORBERA_MEMBER_1on1_MAIL_CONTENT_CANCELLATION ="//div[contains(text(),'Your 1on1 Session') and contains(text(),'has been canceled')]"; 
	public final String ORBERA_MEMBER_LECTURE_MAIL_SIGNUP_CONFIRMATION ="//*[contains(text(),'Your Group Session') and contains(text(),'Has been Scheduled')]";
	public final String ORBERA_MEMBER_LECTURE_MAIL_SIGNUP_CONFIRMATION_CONTENT ="//*[contains(text(),'This message is to confirm your Group Session')]";

	/**
	 * Name : Vinothkumar.M
	 * Created Date:   Aug/25/16
	 * Modified Date:  
	 */ 
	public final String RA_MEMBER_SETTINGS_CONTACT_LAST_NAME_TEXTBOX="//input[@id='lastName']";
	public final String RA_MEMBER_SETTINGS_CONTACT_FIRST_NAME_TEXTBOX="//input[@id='firstName']";
	public final String RA_MEMBER_SETTINGS_INSURANCEPROVIDER="//input[@id='primaryInsuranceProvider']";
	public final String RA_MEMBER_SETTINGS_MEMBERID="//input[@id='primaryInsuranceMemberId']";
	public final String RA_MEMBER_SETTINGS_GROUPNUMBER="//input[@id='primaryGroupNumber']";
	public final String RA_MEMBER_SETTINGS_INSURANCE_AUTO_SUGGEST="//li[contains(@id,'typeahead')]";

	/**
	 * Name : Vinothkumar.M
	 * Created Date:   Aug/25/16
	 * Modified Date:  
	 */
	public final String RA_PA_MEMBER_TAB_SEARCH_TEXTBOX="//input[@id='searchBar']";
	public final String RA_MEMBER_INSURANCE_PROVIDER_CLICK_HERE_LINK="//a[contains(text(),'click here')]";
	public final String RA_MEMBER_INSURANCE_PROVIDER_LIST_HUMANA="//li[@id='insuranceProvider10']//a[contains(text(),'Humana')]";
	public final String RA_MEMBER_INSURANCE_PROVIDER_LIST_CENTURY_LINK_CARRIER="//a[contains(text(),'CenturyLink Carrier 1')]";
	public final String ORBERA_MEMBER_LECTURE_MAIL_REMINDER="//*[contains(text(),'Your Group Session') and contains(text(),'Reminder')]";
	public final String ORBERA_MEMBER_LECTURE_CONTENT_REMINDER="//*[contains(text(),'Your Group Session') and contains(text(),'is scheduled for')]";
	public final String MEMBER_PROFILE_MONTH="//select[@id='dobMonth']";
	public final String MEMBER_PROFILE_DAY="//select[@id='dobDay']";
	public final String MEMBER_PROFILE_YEAR="//select[@id='dobYear']";
	public final String MEMBER_PROFILE_DOB_SAVE="//input[@id='saveMemberDOBButton']";
	public final String MEMBER_PROFILE_MONTH1="//select[@id='dobMonth']/option[text()='10']";
	public final String MEMBER_PROFILE_DAY1="//select[@id='dobDay']/option[text()='11']";
	public final String MEMBER_PROFILE_YEAR1="//select[@id='dobYear']/option[text()='1986']";
	public final String RA_DOB="//div[text()='Date of Birth']/following::div";
	public final String CANCEL_SESSION_FOR_SCHEDULED_ONE_ON_ONE_START_OR="//tbody[@id='sessionClasses_tableBody']//tr//td[contains(text(),'";
	public final String CANCEL_SESSION_FOR_SCHEDULED_ONE_ON_ONE_END_OR="')]/following-sibling::td[7]/ul/li/a[@title='Cancel Session']";
	public final String COACHES_SCHEDULE_CALENDER_SCHEDULE_START_TIME_TEXTBOX="//input[@id='eventStartTime']";
	public final String COACHES_SCHEDULE_CALENDER_SCHEDULE_END_TIME_TEXTBOX="//input[@id='eventEndTime']";
	public final String SESSION_CONFLICTS_RESCHEDULE_OPTION_START_OR="//table[@class='table system-user-table table-standard table-fixed']//tr/td[2][contains(text(),'";
	public final String SESSION_CONFLICTS_RESCHEDULE_OPTION_END_OR="')]/parent::tr/td[10]/div/a[contains(text(),'Reschedule')]";
	public final String SESSION_CONFLICTS_CANCEL_OPTION_START_OR="//table[@class='table system-user-table table-standard table-fixed']//tr/td[2][contains(text(),'";
	public final String SESSION_CONFLICTS_CANCEL_OPTION_END_OR="')]/parent::tr/td[10]/div/a[contains(text(),'Cancel')]";
	public final String ASSIGNED_COACH_SUBSTITUTE_FOR_SCHEDULED_GROUP_START_OR="//tr//td[contains(text(),'";
	public final String ASSIGNED_COACH_SUBSTITUTE_FOR_SCHEDULED_GROUP_END_OR="')]/following-sibling::td[contains(text(),'Group')]/following-sibling::td[4]//a[contains(text(),'Find a substitute')]";
	public final String RA_MEMBER_SCHEDULED_ONE_ON_ONE_SESSION_TIME="//h4[@id='selected1on1Class']";
	public final String MEMBER_SESSION_CHANGE_BUTTON="//*[@id='memberSessionsCard_ChangeOneOnOneSessions_btn']";
	public final String MEMBER_CANCEL_SESSION_BUTTON="//button[@id='cancel']";
	public final String MEMBER_SESSION_POPUP_OK="//button[text()='OK']";
	public final String MEMBER_BROWSER_SUPPORT_MATRIX_OK_BUTTON="//button[contains(text(),'OK')]";
	public final String MEMBER_BROWSER_SUPPORT_MATRIX_CLOSE_BUTTON="//span[@id='activity-modal-close']";
	public final String RA_LIVE_SESSION_BEFORE_ATTENDING_ENTER_WEIGHT_TEXTBOX="//input[@id='inforcementWeight']";
	public final String RA_LIVE_SESSION_BEFORE_ATTENDING_ENTER_WEIGHT_SUBMIT_BUTTON="//button[@id='browserUnsupportedFooterOk']";
	public final String LECTURE_SESSION_MEMBER_SESSION_MIC_DISABLED="//div[@id='mic-status-icon'and@class='device-status-icon gray']";
	public final String VSPN_SUMMARY_LINK ="//a[contains(@target,'PDF')]";
	public final String VSPN_TUTORIAL_LINK ="//a[contains(@target,'Tutorial')]";
	public final String PREVENT_T2_SELECT_EXERCISE_AMOUNT_DROPDOWN="//select[@name='dd_inforcementActivity']";

	
	
	/**
	 * Name : VigneshRaj.M
	 * Created Date:   Jan/30/16
	 * Modified Date:  
	 */
	public final String RA_GROUP_SESSION_CLICK_ADDLIVE_CHANGE_VIDEO_BUTTON="//tbody[@id='sessionClasses_tableBody']/tr/td[5][contains(text(),'Group')]/following-sibling::td[4][contains(text(),'Scheduled')]/following-sibling::td[5]/div[contains(text(),'ADDLIVE')]/parent::td/following-sibling::td/span[3]/following-sibling::a[3]/following-sibling::span[2]/a/span";
	public final String RA_GROUP_SESSION_CHANGE_LIVE_DROPDOWN="//select[@ng-model='SelectedProvider']";
	public final String RA_GROUP_SESSION_CHANGE_SAVE_BUTTON="//button[@ng-click='save();']";
	public final String RA_MEMBER_TRACKER_SERVER_UNAVAILABLE_TEXT="//p[contains(text(),'Tracker server is temporarily unavailable')]";
	public final String RA_MEMBER_TRACKER_SERVER_OK_BUTTON="//p[contains(text(),'Tracker has not been initiated as yet')]/following-sibling::button[text()='OK']";

	/**
	 * Name : VigneshRaj.M
	 * Created Date:   Jan/30/16
	 * Modified Date:  
	 */
	public final String COACH_LECTURE_SESSION_SHARE_SCREEN_BUTTON="//button[@id='openScreenShareBtn']";
	public final String GROUP_SESSION_SCREEN_SHARING_ADD_BROWSER_PLUGIN="//a[@class='button  prominent  add installer']";

	/**
	 * Name : Bharath Kumar
	 * Created Date:   16/Feb/17
	 * Modified Date:  
	 */
	public final String MEMBER_TRACKERS_EXERCISE_PREVIOUS_DAY_ARROW="//a[@id='trackersExerciseDateNavPrevious']";
	public final String ACCOUNT_SETTINGS_PREFERENCES_SMS_I_AGREE_RADIO_BUTTON="//label[contains(text(),'Email')]/parent::div/following-sibling::div//div//div/div//input[@id='personEmail']/following-sibling::ul/li[1]/input[@id='agreeToEmail']";
	public final String ACCOUNT_SETTINGS_SAVE_BUTTON_IN_PREFERENCES="//button[@id='savePrefUpdateBtn']";
	public final String ACCOUNT_SETTINGS_EDIT_BUTTON_WITH_PREFERENCES_HEADER="//span[contains(text(),'Preferences')]/following-sibling::div/a[@id='editPrefTogglerBtn']";
	public final String MEMBER_SESSION_SCHEDULE="//button[contains(text(),'SCHEDULE')]";	
	public final String MEMBER_SESSION_CHANGE="//button[contains(text(),'CHANGE')]";
	public final String MEMBER_CANCEL_SESSION="//button[text()='Cancel Session']";
	public final String TIME_SELECTION_RESCHEDULE="//div[@id='timeSelection']//div[@ng-repeat='details in timeData']//div[*]/button[@ng-disabled='false']";
	//public final String TIME_SELECTION_RESCHEDULE_TIME="//div[@id='timeSelection']//div[@ng-repeat='details in timeData']//div[*]/button[@ng-disabled='false']/following-sibling::ul/li[1]/a";
	public final String TIME_SELECTION_RESCHEDULE_TIME="//div[@id='timeSelection']//button[@ng-disabled='false']/following-sibling::ul/li[last()]/a";
	public final String MEMBER_SESSION_CONFIRM="//button[@id='btnSchedule']";
	public final String RESCHEDULE_SUCCESS_MESSAGE="//div[@id='sessionSpecificHeaderText' and contains(text(),'Congratulations')]";
	public final String MEMBER_TRACKERS_FOODE_PREVIOUS_DAY_ARROW="//a[contains(@class,'previousDay')]";


	/**
	 * Name : Bharath Kumar
	 * Created Date:   20/Feb/17
	 * Modified Date:  
	 */
	public final String RA_ACCEPT_AFTER_INSURANCE="//button[@id='agreementAccept']";
	public final String RA_MEMBER_LIVESESSION_CHAT_TEXTBOX="//input[@ng-model='chatMessage']";
	public final String RA_MEMBER_LIVESESSION_SEND_BUTTON="//button[contains(text(),'Send')]";
	public final String MDAC_JIRA_USERNAME="//input[@id='username']";
	public final String MDAC_JIRA_PASSWORD="//input[@id='password']";
	public final String MDAC_JIRA_LOGIN_BUTTON="//button[@id='login']";
	public final String MDAC_JIRA_USER_PROFILE_IMAGE="//a[@id='header-details-user-fullname']//span";
	public final String MDAC_JIRA_LOGOUT_LINK="//a[@id='log_out']";
	public final String MDAC_JIRA_NEXT_BUTTON="//button[@id='login-submit']";
	public final String RA_COACH_SIGNOUT_LINK="//div[@class='text-right inline-table-cell']//i";
	public final String RA_COACH_SIGNOUT_BUTTON="//a[@id='navbar_hfSysUserSettings_SignOut']";
	public final String RA_COACH_SIGNOUT_VERIFY="//a[@id='sign_in_again_link']";
	
	public final String RA_GROUP_SESSION_COACH_RATING_STAR_1="//div[@class='session-star-rating form-group clearfix']/span/i[1]";
	public final String RA_GROUP_SESSION_COACH_RATING_STAR_2="//div[@class='session-star-rating form-group clearfix']/span/i[2]";
	public final String RA_GROUP_SESSION_COACH_RATING_STAR_3="//div[@class='session-star-rating form-group clearfix']/span/i[3]";
	public final String RA_GROUP_SESSION_COACH_RATING_STAR_4="//div[@class='session-star-rating form-group clearfix']/span/i[4]";
	public final String RA_GROUP_SESSION_COACH_RATING_STAR_5="//div[@class='session-star-rating form-group clearfix']/span/i[5]";

/**
	 * Name : Bharath Kumar
	 * Created Date:   8/Mar/17
	 * Modified Date:  
	 */
	public final String EPOCH_HOUR="//input[@name='hh']";
	public final String EPOCH_MINUTE="//input[@name='mn']";
	public final String EPOCH_SECONDS="//input[@name='ss']";
	
	/**
	 * Name : Bharath Kumar
	 * Created Date:   24/Mar/17
	 * Modified Date:  
	 */
	
	public final String TERMS_CONDITION_HEADER = "//h3[contains(text(),'Please accept the')]";
	public final String YES_I_AGREE_BUTTON = "//button[@id='agreementAccept']";
	public final String DISAGREE_BUTTON = "//button[@id='agreementDecline']";
	public final String NOTIFICATION_EMAIL_YOP="//div[text()='The email address on file for your RA - Real Appeal account has been changed.']";
	public final String NOTIFICATION_EMAIL_GUERRILLA="//*[contains(text(),'Updated Your Email Address')]";
	public final String YOPMAIL_PASSWORD_CHANGE_NOTIFICATION="//div[contains(text(),'Hi')]/following::div[contains(.,'The password for your RA')]";
	public final String GUERRILLA_PASSWORD_CHANGE_NOTIFICATION="//*[contains(text(),'Your Password Has Been Updated ')]";
	public final String TERMS_AND_CONDITION_AGREE_BUTTON="//button[@id='agreementAccept']";
	public final String PROVIDER_HEADER="//body[@id='provider']//div[@class='inline-table-row']/div[2]/ul/li/a";
	public final String YOPMAIL_SUBJECT_NEW_MESSAGE="//div[contains(text(),'You Have a New Message')]";
	public final String YOPMAIL_MEMBER_NAME="//div[contains(text(),'Hi')]";
	public final String YOPMAIL_MEMBER_NEW_MESSAGE_MAIL_BODY="//div[contains(text(),'There')]";
	public final String YOPMAIL_MEMBER_NEW_MESSAGE_PLEASE_DO_NOT="//div[contains(text(),'Please do not')]";
	public final String YOPMAIL_MEMBER_NEW_MESSAGE_HAVING="//div[contains(text(),'Having')]";
	public final String YOPMAIL_MEMBER_NEW_MESSAGE_FOR_ANY="//div[contains(text(),'For any')]";
	public final String YOPMAIL_MEMBER_NEW_MESSAGE_SUPPORT_LINK="//div[text()='https://vra-kulfi.zillion.com']";
	public final String GUERRILLA_SUBJECT_NEW_MESSAGE="//td[contains(text(),'Kulfi: You Have a New Message!')]";
	public final String GUERRILLAMAIL_MEMBER_NAME="//div[contains(text(),'Hi')]";
	public final String GUERRILLAMAIL_MEMBER_NEW_MESSAGE_MAIL_BODY="//div[contains(text(),'There')]";
	public final String GUERRILLAMAIL_MEMBER_NEW_MESSAGE_PLEASE_DO_NOT="//div[contains(text(),'Please do not')]";
	public final String GUERRILLAMAIL_MEMBER_NEW_MESSAGE_HAVING="//div[contains(text(),'Having')]";
	public final String GUERRILLAMAIL_MEMBER_NEW_MESSAGE_FOR_ANY="//div[contains(text(),'For any')]";
	public final String GUERRILLAMAIL_MEMBER_NEW_MESSAGE_SUPPORT_LINK="//div[text()='https://vra-kulfi.zillion.com']";
	
	public final String RA_MEMBER_SIGNUP_INSURANCE_PROVIDER_PAGE_PHI_POPUP_FIRSTNAME_FIELD="//form[@id='signatureForm']//div[1]//span[contains(@ng-if,'account.firstName')]";
	public final String RA_MEMBER_SIGNUP_INSURANCE_PROVIDER_PAGE_PHI_POPUP_LASTNAME_FIELD="//form[@id='signatureForm']//div[2]//span[contains(@ng-if,'account.lastName')]";

	public final String  ASSIGNED_COACH_SUBSTITUTE_FOR_SCHEDULED_MAKEUP_SESSION_START_OR="//tbody[@id='sessionClasses_tableBody']//tr/td[contains(text(),'";
	public final String ASSIGNED_COACH_SUBSTITUTE_FOR_SCHEDULED_MAKEUP_SESSION_END_OR="')]/following-sibling::td/ul/li/a[@title='Substitute Coach']";
	public final String ASSIGNED_COACH_SHOULD_NOT_BE_SUBSTITUTE_FOR_SCHEDULED_MAKEUP_SESSION_START_OR="//tbody[@id='sessionClasses_tableBody']//tr/td[contains(text(),'";
	public final String ASSIGNED_COACH_SHOULD_NOT_BE_SUBSTITUTE_FOR_SCHEDULED_MAKEUP_SESSION_END_OR="')]/following-sibling::td/ul/li/a[@title='Substitute Coach'  and @disabled='disabled']";

	public final String RA_MEMBER_UP_NEXT_WIDGET_YOUR_NEXT_GROUP_SESSION_TITLE="//div[contains(text(),'Your Next Group Session:')]";
	public final String RA_MEMBER_UP_NEXT_WIDGET_SCHEDULE_YOUR_ONE_ON_ONE_SESSION_TITLE="//div[contains(text(),'Schedule your 1-on-1 session')]";
	public final String RA_MEMBER_UP_NEXT_WIDGET_GROUP_SESSION_COUNTDOWN="//div[@id='memberSessionsCard_UpcomingGroupSessions_countdown']";
	public final String RA_MEMBER_UP_NEXT_WIDGET_ONE_ON_ONE_SESSION_SCHEDULE_BUTTON="//button[@id='memberSessionsCard_ScheduleOneOnOneSessions_btn']";
	public final String RA_MEMBER_CLICK_HERE_TO_SCHEDULE_A_MAKEUP_SESSION_LINK="//a[contains(text(),'CLICK HERE TO SCHEDULE A MAKEUP SESSION')]";
	
	public final String RA_MEMBER_MAKEUP_SESSION_CONFIRMATION_EMAIL_YOP="//div[contains(text(),'Thanks for scheduling your makeup session! Your session is confirmed for the date and time below')]";
	
	/**
	 * Name : Bharath Kumar
	 * Created Date:   11/April/17
	 * Modified Date:  
	 */
	public final String IDLE_NOTIFICATION_COACH_EMAIL_YOP="//div[contains(text(),'has been assigned to your class.')]";
	public final String IDLE_NOTIFICATION_COACH_EMAIL_GUERRILLA="//td[contains(.,'has been assigned to your class')]";
	public final String IDLE_NOTIFICATION_COACH_EMAIL_BODY_GUERRILLA="//div[@class='email_body']/div[contains(.,'has been assigned to your class')]";
	public final String IDLE_NOTIFICATION_MEMBER_EMAIL_YOP="//div[contains(text(),'your class has been changed')]";
	public final String IDLE_NOTIFICATION_MEMBER_EMAIL_GUERRILLA="//td[contains(.,'New Class Time')]";
	public final String IDLE_NOTIFICATION_MEMBER_EMAIL_BODY_GUERRILLA="//div[@class='email_body']/div[contains(.,'your class has been changed')]";
	
	
	public final String RA_MEMBER_YOUR_ONE_ON_ONE_SESSION_WIDGET_CHANGE_BUTTON="//button[@id='memberSessionsCard_ChangeOneOnOneSessions_btn' and @analytics-label='1on1']";
	public final String RA_MEMBER_ONE_ON_ONE_SESSION_CANCEL_SESSION_BUTTON="//button[contains(text(),'Cancel Session')]";
	public final String RA_MEMBER_ONE_ON_ONE_SESSION_CANCEL_SESSION_OK_BUTTON="//button[contains(text(),'OK')]";
	public final String RA_MEMBER_YOUR_ONE_ON_ONE_SESSION_WIDGET_RESCHEDULE_BUTTON="//div[contains(text(),'Schedule your 1-on-1 session')]//following::div//button[2]";

	public final String RA_MEMBER_MAKEUP_SESSION_CONFIRMATION_EMAIL_GUERRILLA="//td[contains(.,'Makeup Session Confirmation')]";
	
	/**
	 * Name : Bharath Kumar
	 * Created Date:   17/April/17
	 * Modified Date:  
	 */
	public final String IDLE_MEMBER_NOT_LOGGED_IN_NOTIFICATION_EMAIL_YOP="//div[contains(.,'Idle Member Notification')]/following::div[contains(.,'Hi')]/div[3][contains(.,'It looks like you')]";
	public final String IDLE_EMAIL_NOTIFICATION_GUERRILLA="//span[contains(.,'It looks like you')]";
	public final String COACH_1ON1_SESSION_MEMBER_DONE_BUTTON="//button[text()='DONE']";
    
	public final String COACHES_SETTINGS_LINK_BUTTON="//div[@class='text-right inline-table-cell']//i";
	public final String COACHES_SETTINGS_LINK_MYPROFILE_BUTTON="//a[text()='My Profile']";
	public final String COACHES_GEAR_BUTTON_MY_PROFILE_ALL_SESSION_SUBTAB="//a[@id='clientsTabAllSessions_Link']";
	public final String RA_ASSIGNED_COACH_GROUP_MEMBERS_INFORMATION_ICON="//a[@ng-disabled='!p.isGroupSession']/i[@class='fa fa-check-circle small-icon']";
	public final String RA_COACH_GROUP_MEMBERS_DETAIL_MAKEUP_MEMBERS_NAME="//span[@class='makeupSessionIcon ng-scope']//i/parent::span/parent::a/parent::td[contains(.,'')]";
	public final String RA_COACH_GROUP_MEMBERS_DETAIL_MEMBER_NAME_MAKEUP_SESSION_INDICATOR="//span[@class='makeupSessionIcon ng-scope']//i";
	public final String CLOSE_LINK="//span[@ng-click='close()']";
	
	public final String RA_PA_GROUP_LAST_SESSION_COMPLETED_STATUS="//tbody[@id='sessionClasses_tableBody']//tr/td[contains(text(),'Group')]/following-sibling::td[contains(text(),'Completed')]";
	public final String RA_PA_GROUP_LAST_SESSION_TYPE="//tbody[@id='sessionClasses_tableBody']//tr/td[contains(text(),'Group')]";
	public final String RA_PA_GROUP_LAST_SESSION_DATE="//tbody[@id='sessionClasses_tableBody']//td[@id='sessionClasses_tdDate0']";
	public final String RA_COACH_MEMBERS_TAB="//div[@class='navbar-item-wrapper']//span[text()='Members']";
	public final String RA_COACH_MEMBERS_SORT_DROPDOWN="//select[@id='filterMemberList']";
	public final String RA_COACH_MEMBERS_SEARCH_TEXTBOX="//input[@id='searchBar']";
	public final String RA_COACH_MEMBERS_SEARCH_BUTTON="//button[@id='submit-search']";
	public final String PRO_PATIENT_MEMBER_LINK="//a[@id='editLink0']";
	public final String MEMBER_SESSIONS_TAB="//a[@id='PA_ProviderMembers_Sessions_Link' and text()='Sessions']";
	public final String RA_PA_MEMBER_TRACKING_TAB="//a[@id='PA_ProviderMembers_HealthTracking_Link']";
	public final String RA_PA_MEMBER_TRACKING_SUMMARY="//div[contains(text(),'Summary')]";
	
	public final String RA_PA_MEMBER_TRACKING_SUMMARY_LAST_SESSION_ATTENDED_VALUE="//div[@ng-if='i.lastAttendedSessionDate']";
	public final String RA_PA_MEMBER_TRACKING_SUMMARY_SESSIONS_ATTENDED_VALUE="//div[@ng-if='i.attendedSessionNumber']";
	public final String RA_PA_MEMBER_TRACKING_SUMMARY_LAST_SESSION_ATTENDED_HEADER="//th[@class='text-center']//a[contains(.,'Last Session Attended')]";
	public final String RA_PA_MEMBER_TRACKING_SUMMARY_SESSIONS_ATTENDED_HEADER="//th[@class='text-center']//a[contains(.,'Sessions Attended')]";
 
	public final String INSOURCE_PRACTICE_PA_ASSIGNED_COACH="//tr//td[contains(text(),'Group')]/following::td[2]//a";
	public final String ONE_ON_ONE_SESSION_ASSIGNED_COACH_EMAIL="//label[contains(text(),'Email')]//following::span";
    
	public final String RA_1ON1_MAKEUP_SESSION_MEMBER_TIMESELECTION_AVAILABLE_BUTTON="//div[@id='timeSelection']//button[@ng-disabled='false']/following-sibling::ul/li[1]/a";
	public final String RA_MEMBER_MAKEUP_SESSION_TIMESELECTION_AVAILABLE_BUTTON_AVAILABLE_TIME="//div[@id='timeSelection']//button[@ng-disabled='false']/following-sibling::ul/li[1]/a";
	public final String RA_MEMBER_MAKEUP_SESSION_TIMESELECTION_CONFIRM_BUTTON="//button[@id='btnSchedule']";

	/**
	 * Name : Bharath Kumar
	 * Created Date:   27/April/17
	 * Modified Date:  
	 */
	
	public final String IDLE_MEMBER_PHI_CONSENT_DETAILS_AGREES="//div[text()='PHI Consent']/following::div[2]/span/b[text()='Agrees']";
	public final String IDLE_MEMBER_PHI_CONSENT_DETAILS_DECLINES="//div[text()='PHI Consent']/following::div[2]/span/b[text()='Declines']";
	public final String RA_PA_ONE_ON_ONE_SESSION_ASSIGNED_COACH="//tr//td[contains(text(),'1on1')]/following::td[2]//a";
	public final String GUERRILLA_MAIL_ACTIVATE_YOUR_ACCOUNT_LINK="";
	public final String GUERRILLA_MAIL_PREVENT_T2_WELCOME_MESSAGE="//td[contains(text(),'Welcome to Real Appeal Prevent T2')]";
	public final String GUERRILLA_MAIL_GENERATED_LINK="//a[contains(text(),'Complete Your Registration')]";
	public final String GUERRILLA_MAIL_COMPLETE_LINK="//a[contains(text(),'Registration complete')]";
	public final String GUERRILLA_MAIL_CONGRATULATION_MESSAGE="//div[contains(text(),'Congratulations! You have completed your Registration successfully.')]";
	public final String GUERRILLA_MAIL_WELCOME_MESSAGE_VSPN="//*[contains(text(),'Welcome to your VSPN Patient Journey')]";
	public final String GUERRILLA_MAIL_GENERATED_LINK_VSPN="//a[contains(text(),'Click here to complete your remaining enrollment process')]";
	public final String GUERRILLA_MAIL_WELCOME_TO_RA_LINK="//a[contains(text(),'Welcome to Real Appeal')]";
	public final String PREVENT_T2_ENROLLMENT_PASSWORD_TEXTBOX="//input[@id='obfuscatedPW']";
	public final String PREVENT_T2_ENROLLMENT_SECURITY_DROPDOWN="//select[@id='secureQuestion']";
	public final String PREVENT_T2_ENROLLMENT_SECURITY_ANSWER_TEXTBOX="//input[@id='secureAnswer']";
	public final String PREVENT_T2_ENROLLMENT_SCREENNAME_TEXTBOX="//input[@id='security_screenName']";
	public final String PREVENT_T2_ENROLLMENT_ACCEPT_CHECKBOX="//label[@id='signup_UserInformationPolicy_Checkbox_Label']";
	public final String PREVENT_T2_ENROLLMENT_SUBMIT_BUTTON="//button[@id='enrollmentCredentialsFormSubmitBtn']";
	public final String PREVENT_T2_ENROLLMENT_SELECT_GENDER="//input[@id='genderSelect1']";
	public final String PREVENT_T2_ENROLLMENT_CURRENT_WEIGHT_TEXTBOX="//input[@id='currentWeight']";
	public final String PREVENT_T2_ENROLLMENT_TARGET_WEIGHT_TEXTBOX="//input[@id='targetWeight']";
	public final String PREVENT_T2_ENROLLMENT_HEIGHTFOOT_TEXTBOX="//input[@id='heightFoot']";
	public final String PREVENT_T2_ENROLLMENT_HEIGHTINCH_TEXTBOX="//input[@id='heightInch']";
	public final String PREVENT_T2_ENROLLMENT_ACTIVITY_LEVEL="//input[@id='activityLevel1']";
	public final String PREVENT_T2_ENROLLMENT_PRIMARY_ADDRESS_TEXTBOX="//input[@id='primaryAddress']";
	public final String PREVENT_T2_ENROLLMENT_SECONDARY_ADDRESS_TEXTBOX="//input[@id='secondaryAddress']";
	public final String PREVENT_T2_ENROLLMENT_CITY_TEXTBOX="//input[@id='city']";
	public final String PREVENT_T2_ENROLLMENT_SELECT_STATE_DROPDOWN="//select[@id='selectedState']";
	public final String PREVENT_T2_ENROLLMENT_ZIPCODE_TEXTBOX="//input[@id='zipCode']";
	public final String PREVENT_T2_ENROLLMENT_PHONE_TEXTBOX="//input[@id='personHomePhone']";
	public final String PREVENT_T2_ENROLLMENT_ADDRESS1_REQUIRED="//p[contains(text(),'Address 1 is required')]";
	public final String PREVENT_T2_ENROLLMENT_CITY_REQUIRED="//p[contains(text(),'City is required')]";
	public final String PREVENT_T2_ENROLLMENT_STATE_REQUIRED="//p[contains(text(),'State is required')]";
	public final String PREVENT_T2_ENROLLMENT_ZIPCODE_REQUIRED="//p[contains(text(),'Zip Code is required')]";
	public final String PREVENT_T2_ENROLLMENT_NEXT_BUTTON="//button[contains(text(),'Next Step')]";
	public final String PREVENT_T2_ENROLLMENT_LASTNAME_REQUIRED="//p[contains(text(),'Last Name is required')]";
	public final String PREVENT_T2_ENROLLMENT_MONTH_REQUIRED="//span[contains(text(),'Month is required')]";
	public final String PREVENT_T2_ENROLLMENT_DAY_REQUIRED="//span[contains(text(),'Day is required')]";
	public final String PREVENT_T2_ENROLLMENT_YEAR_REQUIRED="//span[contains(text(),'Year is required')]";
	public final String PREVENT_T2_ENROLLMENT_INFORMATION_DOESNOT_MATCH_ERROR="//span[contains(text(),'The information you provided')]";
	public final String PREVENT_T2_ENROLLMENT_MEMBER_MUST_BE_OVER_EIGHTEEN="//span[contains(text(),'Oops! Sorry, members must be at least 18 years of age to participate in the program')]";
	public final String PREVENT_T2_ENROLLMENT_MUST_CREATE_PASSWORD="//span[contains(text(),'You must create a Password')]";
	public final String PREVENT_T2_ENROLLMENT_SECURITY_QN_REQUIRED="//p[contains(text(),'Security Question is required')]";
	public final String PREVENT_T2_ENROLLMENT_ANS_TO_SECURITY_QN="//p[contains(text(),'Answer to the Security Question is required')]";
	public final String PREVENT_T2_ENROLLMENT_SCREENNAME_REQUIRED="//p[contains(text(),'Screen Name is required')]";
	public final String PREVENT_T2_ENROLLMENT_CURRENT_WEIGHT_ERROR="//span[contains(text(),'Current Weight must be greater than 10')]";
	public final String PREVENT_T2_ENROLLMENT_TARGET_WEIGHT_ERROR="//span[contains(text(),'Weight must be greater than 10 lbs and cannot be more than 100 lbs over your weight')]";
	public final String PREVENT_T2_ENROLLMENT_VALURE_FOR_HEIGHT_FEET_ERROR="//span[contains(text(),'Please enter a valid value for feet')]";
	public final String PREVENT_T2_ENROLLMENT_VALURE_FOR_HEIGHT_INCHES_ERROR="//span[contains(text(),'Please enter a valid value for inches')]";
	public final String PREVENT_T2_ENROLLMENT_SCHEDULE_BUTTON_SUBMIT_BUTTON="//button[@id='btnSchedule']";
	public final String PREVENT_T2_ENROLLMENT_LOGIN_NOW_BUTTON="//button[@id='loginNowBtn']";
	public final String PREVENT_T2_ENROLLMENT_LOGIN_EMAIL_TEXTBOX="//input[@id='loginpage_Email_Textbox']";
	public final String PREVENT_T2_ENROLLMENT_LOGIN_PASSWORD_TEXTBOX="//input[@id='loginpage_Password_Textbox']";
	public final String PREVENT_T2_ENROLLMENT_LOGIN_BUTTON="//button[contains(text(),'LOG IN')]";
	public final String PREVENT_T2_ENROLLMENT_LOGOUT_BUTTON="//a[contains(text(),'Log out')]";
	public final String PREVENT_T2_ENROLLMENT_SIGNEDOUT_TEXT="//h1[contains(text(),'You have signed out')]";


	public final String RA_COACH_ONE_ON_ONE_SESSION_CONFIRMATION_EMAIL_GUERRILLA="//td[contains(.,'Session Scheduled: 1-on-1')]";
	public final String RA_COACH_ONE_ON_ONE_SESSION_CONFIRMATION_EMAIL_YOP="//div[contains(text(),'Session Scheduled: 1-on-1')]";
	public final String RA_MEMBER_ONE_ON_ONE_SESSION_CONFIRMATION_EMAIL_YOP="//div[contains(text(),'Your 1-on-1 Session Confirmation')]";
	public final String RA_MEMBER_ONE_ON_ONE_SESSION_CONFIRMATION_EMAIL_GUERRILLA="//td[contains(.,'Your 1-on-1 Session Confirmation')]";
	public final String RA_COACH_USERNAME="//input[@placeholder='Email Address']";
	public final String RA_COACH_PASSWORD="//input[@placeholder='Password']";
	public final String RA_COACH_LOGIN="//button[@id='login_button']";
	public final String RA_COACH_REAL_APPEAL_LOGO="//a[@id='orgNavbarBrand']";
	
	
	/**
	 * Name : Bharath Kumar
	 * Created Date:   11/May/17
	 * Modified Date:  
	 */
	public final String YOPMAIL_MEMBER_NEW_MESSAGE_SUPPORT_LINK_TEST="//div[text()='https://testmemfe.zillion.com']";
	public final String GUERRILLAMAIL_MEMBER_NEW_MESSAGE_SUPPORT_LINK_TEST="//div[text()='https://testmemfe.zillion.com']";
	
}
