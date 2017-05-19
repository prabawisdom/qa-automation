package com.zillion.qa.member;
public interface OR {
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   14/Dec/15 
	 * Modified Date:  
	 */
	public final  String  MEMBER_YOURMAIL ="//input[@id='navbarLoginFormEmail']"; 
	public final  String  MEMBER_YOURPASSWORD ="//input[@type='password']"; 
	public final  String  MEMBER_LOGIN_BUTTON ="//button[text()='LOG IN']"; 
	public final  String  MEMBER_DASHBOARD ="//*[@id='dashboardfullview_navItem']"; 
	public final  String  MEMBER_PROFILE ="//i[@class='fa fa-angle-down']";
	public final  String  MEMBER_LOGOUT ="//ul[@class='dropdown-menu  menu-list-vertical']//a[@ng-click='doLogout()']"; 
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   29/Dec/15 
	 * Modified Date:  
	 */
	public final  String  MEMBER_ARTICLE_CLOSE ="//*[@id='libraryFullpageView']//li[2]//a[@ng-click='close()']";
	public final  String   MEMBER_LIBRARY_CONTENT="//*[@id='libraryFullpageView']//div[@class='hf-modal-content']"; 
	public final  String  MEMBER_LIBRARY_ARTICLE_CONTENT ="//div[@ng-bind-html='articleDescription']";
	public final  String  MEMBER_LIBRARY_TAB ="//*[@id='libraryfullview_navItem']"; 
	public final  String  MEMBER_LIBRARY_ALLCONTENT ="//a[text()='All Content']"; 
	public final  String  MEMBER_LIBRARY_VIDEO_PAUSE ="//button[@title='Pause clip']"; 
	public final  String  MEMBER_DASH_UPLOADPHOTO ="//*[@id='dashboardStatsTemp']//span[text()='Upload Photo']"; 
	public final  String  MEMBER_UPLOAD_SAVE ="//button[text()='Save']"; 
	public final  String  MEMBER_DASH_CONTENT ="//div[@id='dashboardModule_welcomeSnapshotContainer']//ul//ul//li//span";
	public final  String  MEMBER_PHOTO1 ="//div[@id='foodTrackerListItems']//div[contains(@class, 'foodItemWrapper')][1]";
	public final  String  MEMBER_TRACKER_UPLOADIMAGE ="//input[@id='hfImgCropFileName-dashBrdBeforeImg']"; 
	public final  String  MEMBER_PHOTO_ORIENTATION="//i[@title='Rotate Left']";
	public final String MAIL_DROP_RELOAD_BUTTON="//a[@id='inbox-reload']";
	public final String MEMBER_DELETE_PHOTO="//span[text()='Delete Photo']";
	public final String MEMBER_UPLOAD_SAVE1="//button[contains(@class,'btn btn-primary center-block btn-lg btn-block')]";
	public final  String  MEMBER_TRACKER_UPLOADPHOTO ="//input[@id='hfImgCropFileName-dashBrdBeforeImg']";
	/**
	 * Name :     Abinaya.P
	 * Created Date:   16/02/2016
	 * Modified Date:  
	 * Description :   Create a common method to schedule/Reschedule/Cancel Session from member portal
	 * Ticket ID :     
	 * Required Inputs :  
	 */
	public final String MEMBER_SCHEDULE_1ON1SESSION="//button[@id='memberSessionsCard_Schedule_oneOnOneSessionBtnschedule0_btn']";
	public final String MEMBER_SCHEDULE_SESSIONDATE="//button[@ng-disabled='false']//span[text()='Morning']";
	public final String MEMBER_SCHEDULE_SESSIONTIME="//li[2]//a[@ng-click='selectTime($event); hideDropdown()']";
	public final String MEMBER_SESSIONIN_NEXT_INTERVAL="//div[text()='Your next session can be scheduled in the next interval']";
	public final String MEMBER_SESSION_CONFIRM="//button[@id='btnSchedule']";
	public final String MEMBER_SESSION_SUCCESS_MSG="//div[contains(text(),'scheduled your Orbera Program 1on1')]";
	public final String MEMBER_ADDTO_CALENDER="//button[text()=' Add to Calendar ']";
	public final String MEMBER_GOOGLE_CALENDER="//span[text()=' Google Calendar']";
	public final String MEMBER_ICAL_CALENDER="//a[@id='iCalIcsFile']";
	public final String MEMBER_YAHOO_CALENDER="//span[text()=' Yahoo Calendar']";
	public final String MEMBER_OUTLOOK_CALENDER="//a[@id='outlookIcsFile']";
	public final String MEMBER_SCHEDULED_10N1SESSION_="//*[@id='dashboardAlertBoxContainer']/div[1]/div[2]/div[2]/div[2]/ul/li[1]";
	public final String CHANGE_SESSION="//button[@id='memberSessionsCard_ChangeOneOnOneSessions_btn']";
	public final String CHANGE_SCHEDULED_TIME="//button[@id='reSchedule']";
	public final String MEMBER_RESCHEDULE_SESSIONTIME="//li[2]//a[@ng-click='selectTime($event); hideDropdown()']";
	public final String MEMBER_CANCEL_SESSION="//button[text()='Cancel Session']";
	public final String MEMBER_SESSION_POPUP_CANCEL="//button[text()='Cancel']";
	public final String MEMBER_SESSION_POPUP_CLOSE="//*[@id='individual-session-cancellation']/div[2]/modal/div/div[2]/div/div/div/div[1]/ul/li[2]/a";
	public final String MEMBER_SESSION_POPUP_OK="//button[text()='OK']";
	public final String SESSION_CANCEL_MSG="//*[@id='sessionMessage']";
	public final String VIEW_SESSION_CLOSE="//*[@id='dashboardAlertBoxContainer']/div[2]/modal/div/div[2]/div/div/div/div[1]/ul/li[2]/a";
	public final String GMAIL_USERNAME="//input[@id='Email']";
	public final String GMAIL_PASSWORD="//input[@id='Passwd']";
	public final String GMAIL_NEXT="//input[@id='next']";
	public final String GMAIL_SIGNIN="//input[@id='signIn']";
	public final String GMAIL_SAVE_CALENDER="//div[text()='Save']";
	public final String GMAIL_CALENDER_CONFIRM_MSG="//span[text()='Orbera Program 1on1  Session']";
	public final String GMAIL_SETTING="//div[@id='gbwa']/following-sibling::div[2]/div";
	public final String GMAIL_LOGOUT="//a[text()='Sign out']";
	public final String GMAIL_STAY_SIGIN="//input[@id='PersistentCookie']";
	public final String YAHOO_SAVE_CALENDER="//a[text()='Save']";
	public final String YAHOO_SETTING="//b[text()='dummy']";
	public final String YAHOO_LOGOUT="//a[@id='uh-signout' and contains(text(),'Sign Out')]";
	public final String YAHOO_USERNAME="//input[@id='login-username']";
	public final String YAHOO_PASSWORD="//input[@id='login-passwd']";
	public final String YAHOO_STAY_SIGIN="//*[@id='persistency']/div/label";
	public final String YAHOO_SIGNIN="//button[@id='login-signin']";
	public final String MEMBER_SESSION_RESCHEDULE="//div[@id='dashboardBannerBarTemp']//div[*]//button[@id='memberSessionsCard_ChangemissedOneOnOneSession_btn']";
	public final String MEMBER_SESSION_SCHEDULE="//div[@id='dashboardBannerBarTemp']//div[*]//button[@id='memberSessionsCard_Schedule_oneOnOneSessionBtnschedule0_btn']";
	public final String MEMBER_SESSION_CHANGE="//div[@id='dashboardBannerBarTemp']//div[*]//button[@id='memberSessionsCard_ChangeOneOnOneSessions_btn']";
	/**
	 * Name :     Suresh.V
	 * Created Date:   16Feb2016
	 * Modified Date:  
	 */
	public final String CONTENT_LIBRARY_ENTRIES_COUNT="//div[@id='librarySinglePageTemplate']//div[@id='libraryToolbarRow']//div/h5";
	public final String CONTENT_LIBRARY_PAGE_SEARCH_TEXTBOX1="//input[@placeholder='Search for...']";
	public final String CONTENT_LIBRARY_PAGE_SEARCH_BUTTON="//input[@type='text']/parent::div/span/button[@type='button']";
	public final String MEMBER_RECIPES="//a[contains(text(),'Recipes')]";
	public final String MEMBER_NUTRITION="//a[contains(text(),'Nutrition')]";
	public final String MEMBER_LIFESTYLE="//a[contains(text(),'Lifestyle')]";
	public final String GETTING_FIT_SUB_MENU="//a[contains(text(),'Getting Fit')]";
	public final String MEMBER_ALLCONTENT="//a[contains(text(),'All Content')]";
	/**
	 * Name :     Abinaya
	 * Created Date:   22Feb2016
	 * Modified Date:  
	 */
	public final String STARTING_WEIGHT="//div[@id='weightInfo']/following-sibling::div//div[@class='tracker-sidebar-list']//div[7]/div/div/span";
	public final String TRACKER_WEIGHT_INPUT="//input[@id='manual-entry-value']";
	public final String MEMBER_DASHBOARD_WEIGHT_INPUT="//input[@id='dashboardEnterWeight']";
	public final String MEMBER_TRACKER="//div[@id='site-canvas']//ul//li[11]/a";
	/**
	 * Name :     VigneshRaj
	 * Created Date:   26Feb2016
	 * Modified Date:  
	 */
	public final String PLACEMENT_APPOINTMENT_DAY="//select[@id='inrDay']";
	public final String PLACEMENT_APPOINTMENT_MONTH="//select[@id='inrMonth']";
	public final String PLACEMENT_APPOINTMENT_YEAR="//select[@id='inrYear']";
	public final String PLACEMENT_APPOINTMENT_SUBMIT_BUTTON="//div[@class='hf-modal-content']//input[@value='SUBMIT']";
	public final String PLACEMENT_APPOINTMENT_CLOSE_BUTTON="//div[@class='hf-modal']//div[@class='hf-modal-header']/div/div[2]/a";
	public final String PLACEMENT_APPOINTMENT_CREATED_LECTURE_SESSION="//div[@id='dashboardAlertBoxContainer']//div//div[4]/div//div[2]/ul/li[1]/following-sibling::li[contains(text(),'06:00pm - 06:45pm')]";
	public final String PLACEMENT_APPOINTMENT_VIEW_SESSION="//button[text()='View Sessions']";
	public final String VIEW_SESSION_BUTTON="//button[contains(text(),'View Sessions')]";
	public final String SESSION_DATE="//button[@class='btn btn-schedule-default border-primary button brand-c-primary' and @ng-disabled='false']";
	public final String SESSION_TIME="//ul[@class='dropdown-menu schedule-default-menu']//li[2]/a";
	public final String MEMBER_ATTEND_NOW_BUTTON="//button[@id='memberSessionsCard_JoinOneOnOneSessions_btn']";
	public final String LECTURE_SESSION_MEMBER_ATTEND_NOW_BUTTON="//button[@id='memberSessionsCard_JoinUpcomingGroupSessions_btn']";
	public final String WELCOME_HEADER="//div[@class='livesession-section-wrapper']//div//span[text()='Welcome to your 1on1 session']";
	public final String CONNECTED_TO_LIVE_SESSION="//div[@id='memberCanStreamVideoContainer']/div/div/div/div[3]/livesession-connected[contains(text(),'Connected')]";
	public final String MEMBER_SESSION_MIC_ON="//div[@id='mic-status-icon'and@class='device-status-icon']";
	public final String MEMBER_SESSION_MIC_OFF="//div[@id='mic-status-icon'and@class='device-status-icon highlight']";
	public final String MEMBER_SESSION_VIDEO_ON="//div[@id='video-status-icon'and@class='device-status-icon']";
	public final String MEMBER_SESSION_VIDEO_OFF="//div[@id='video-status-icon'and@class='device-status-icon highlight']";
	public final String MEMBER_SESSION_CHAT_TEXTBOX="//input[@id='chatMessageInput']";
	public final String MEMBER_SESSION_CHAT_SEND_BUTTON="//div[@id='chat-send-button']//button[@ng-click='sendChatMessage()']";
	public final String MEMBER_ATTEND_TOLIVE_SESSION_TEXT="//div[@class='chat-message ng-scope']//div[2]/div[contains(text(),'Coach welcome to 1on 1 live session')]";
	public final String MEMBER_WELCOME_TOLIVE_SESSION_TEXT="//div[@class='chat-message ng-scope']//div[2]/div[contains(text(),'Member welcome to 1on 1 live session')]";
	public final String MEMBER_SESSION_CHANGE_BUTTON="//button[contains(text(),'Change')]";
	public final String MEMBER_DASHBOARD_ICON="//div[@id='navbarWrapper']//div[@id='navbar-member']//div//a";
	public final String MEMBER_COACH_RATING_HEADER_TEXT="//live-session-rating-modal//div[@class='hf-modal-header']//div[text()='Coach Rating']";
	public final String MEMBER_COACH_STAR1="//div[@class='session-star-rating text-icon-xl']//th/span/i";
	public final String MEMBER_COACH_STAR2="//div[@class='session-star-rating text-icon-xl']//th/span/i/following-sibling::span/following-sibling::i";
	public final String MEMBER_COACH_STAR3="//div[@class='session-star-rating text-icon-xl']//th/span/i/following-sibling::span/following-sibling::i/following-sibling::span/following-sibling::i";
	public final String MEMBER_COACH_STAR4="//div[@class='session-star-rating text-icon-xl']//th/span/i/following-sibling::span/following-sibling::i/following-sibling::span/following-sibling::i/following-sibling::span/following-sibling::i";
	public final String MEMBER_COACH_STAR5="//div[@class='session-star-rating text-icon-xl']//th/span/i/following-sibling::span/following-sibling::i/following-sibling::span/following-sibling::i/following-sibling::span/following-sibling::i/following-sibling::span/following-sibling::i";
	public final String MEMBER_COACH_RATING_TEXTBOX="//textarea[@ng-model='textResponse']";
	public final String MEMBER_COACH_RATING_SUBMIT_BUTTON="//button[text()='Submit']";
	public final String MEMBER_COACH_RATING_CLOSE_BUTTON="//div[text()='Coach Rating']//parent::span//following::a[@ng-click='close()']";   
	public final String COACH_ATTEND_NOW_BUTTON="//span[contains(text(),'Orbera Program: Post-placement for 52 weeks')]/parent::td/following-sibling::td//following-sibling::td//following-sibling::td/div/a";
	public final String COACH_SESSION_MIC_DISABLED="//div[@class='toolbar']//div[2]//span[@id='memberMic0'and@class='clickable fa fa-microphone-slash red']";
	public final String COACH_SESSION_MIC_ENABLED="//div[@class='toolbar']//div[2]//span[@id='memberMic0'and@class='clickable fa fa-microphone green']";
	public final String COACH_SESSION_CAMERA_ENABLED="//div[@class='toolbar']//div[2]//span[@id='memberCamera0'and@class='clickable fa fa-video-camera green']";
	public final String COACH_SESSION_CAMERA_DISABLED="//div[@class='toolbar']//div[2]//span[@id='memberCamera0'and@class='clickable fa fa-eye-slash red']";
	public final String COACH_ATTEND_TOLIVE_SESSION_TEXT="//div[@class='chat-message ng-scope']//div[2]/div[contains(text(),'Member welcome to 1on 1 live session')]";
	public final String COACH_CONNECTION_HISTORY_TEXT="//div[@id='member-tracker']//dl/dt[text()='Connection History']";
	public final String COACH_MEMBER_CONNECTED_TEXT="//div[@id='member-tracker']//dl//div[text()='Member Connected']";
	public final String COACH_SESSION_CHAT_TEXTBOX="//input[@id='chatMsgInputField']";
	public final String COACH_SESSION_CHAT_SEND_BUTTON="//div[@id='chat-send-button']//button[@ng-click='sendChatMessage()']";
	public final String COACH_SESSION_CONNECTION_HISTORY_LABEL="//div[@id='member-tracker']//dl/dt[text()='Connection History']";
	public final String COACH_SESSION_SESSION_TIMER_LABEL="//div[@id='member-tracker']//dl//dt[text()='Session Timer']";
	public final String COACH_SESSION_MAILING_ADDRESS_LABEL="//div[@id='member-tracker']//dl//dt[text()='Mailing Address']";
	public final String COACH_SESSION_DOB_LABEL="//div[@id='member-tracker']//dd//strong[text()='Date of Birth:']";
	public final String COACH_SESSION_ALLOWED_CONTACT_LABEL="//div[@id='member-tracker']//dl//dt[text()='Allowed Contact Method(s)']";
	public final String COACH_SESSION_MEMBER_LOCAL_TIME_LABEL="//div[@id='member-tracker']//dd//strong[text()='Member local time:']";
	public final String COACH_SESSION_BROWSE_NOTES_BUTTON="//div[@id='member-tracker']//button[text()='Browse Notes']";
	public final String COACH_SESSION_SETTINGS_BUTTON="//div//span[@id='openSettingsGear']";
	public final String COACH_SESSION_END_SESSION_BUTTON="//div//button[@id='endSessionBtn']";
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
	public final String COACH_SESSION_MEMBER_DONE_BUTTON="//div[@class='modal-footer']//button[text()='DONE']";
	public final String COACH_WELCOME_TOLIVE_SESSION_TEXT="//div[@class='chat-message ng-scope']//div[2]/div[contains(text(),'Coach welcome to 1on 1 live session')]";
	public final String COACH_SESSION_MIC_ON="//div[@id='mic-status-icon'and@class='device-status-icon']";
	public final String COACH_SESSION_VIDEO_ON="//div[@id='video-status-icon'and@class='device-status-icon']";
	public final String COACH_SESSION_MIC_OFF="//div[@id='mic-status-icon'and@class='device-status-icon highlight']";
	public final String COACH_SESSION_VIDEO_OFF="//div[@id='video-status-icon'and@class='device-status-icon highlight']";
	public final String GETTINGFIT_SUBMENU ="//a[text()='Getting Fit']";
	public final String GETTINGFIT_VIEW_ARTICLE_BUTTON ="//button[contains(text(),'View Article')]";
	public final String FRIDAY_HEADER="//div[@id='timeSelection']//div//div//span[contains(text(),'Friday')]";
	public final String FRIDAY_EVENING_BUTTON="//div[@id='timeSelection']//div//div//span[contains(text(),'Friday')]/parent::div[1]/following-sibling::div[4]//button";
	public final String NEXT_PAGE_NAVIGATOR="//div[@class='time-select-nav-control']//span[2]//span";
	public final String SESSION_TIME_SLOT_6_00PM="//div[@id='timeSelection']//div//div//span[contains(text(),'Friday')]/parent::div[1]/following-sibling::div[4]//ul//li//a/div[text()='6:00PM ']";
	public final String SESSION_TIME_SLOT_5_00PM="//div[@id='timeSelection']//div//div//span[contains(text(),'Friday')]/parent::div[1]/following-sibling::div[4]//ul//li//a/div[text()='5:00PM ']";
	public final String PREVIOUS_PAGE_NAVIGATOR="//span[@id='btnPrevious']";
	/**
	 * Name :     VigneshRaj
	 * Created Date:   26Feb2016
	 * Modified Date:  
	 */
	public final String LIVESESSION_MEMBER_VIEW_SESSION_OF_FRIDAY_TEXT="//div[@id='lectureSessionModal']//div[1]//ul//li[contains(text(),'Fri')]";
	public final String LIVESESSION_MEMBER_VIEW_SESSION_NEXT_PAGE_NAVIGATOR="//div//ul[@class='pagination']//li//span/a/i[@class='fa fa-angle-right']";
	public final String LIVESESSION_MEMBER_VIEW_SESSION_FOLLOWING_SIGNUP="//div[@id='lectureSessionModal']//div[1]//ul//li[contains(text(),'Fri')]/parent::ul/parent::div/following-sibling::div//ul//li[2]//span[contains(text(),' 05:00pm - 05:45pm')]/parent::li/parent::ul/parent::li/preceding-sibling::li/parent::ul/parent::div/following-sibling::div/button[text()='Sign up']";
	public final String LIVESESSION_MEMBER_VIEW_SESSION_SUCCESSFULL_POPUP_TEXT="//div[@class='modal-content']//div//div//div[contains(text(),'Session Scheduled Succesfully')]";
	public final String LIVESESSION_MEMBER_VIEW_SESSION_SUCCESSFULL_POPUP_OK_BUTTON="//div[@class='modal-content']//div//div//button[text()='OK']";
	public final String LIVESESSION_MEMBER_VIEW_SESSION_OF_LECTURE_SESSION_TIMING="//div[@id='lectureSessionModal']//div[1]//ul//li[contains(text(),'Fri')]/parent::ul/parent::div/following-sibling::div//ul//li[2]//span[contains(text(),' 05:00pm - 05:45pm')]";
	public final String VIEW_SESSION_OF_TITLE_START="//div[@id='lectureSessionModal']//div[text()='";
	public final String OR_END="']";
	public final String VIEW_SESSION_OF_LECTURE_SESSION_TIMING_START="//div[@id='lectureSessionModal']//div[text()='";
	public final String VIEW_SESSION_OF_LECTURE_SESSION_TIMING_END="']//parent::span//parent::div//following-sibling::div/div[contains(.,'5:30pm - 6:15pm')]";
	public final String VIEW_SESSION_FOLLOWING_SIGNUP_START="//div[@id='lectureSessionModal']//div[text()='";
	public final String VIEW_SESSION_FOLLOWING_SIGNUP_END="']//parent::span//parent::div//following-sibling::div/div[contains(.,'5:30pm - 6:15pm')]/parent::div/parent::div/following-sibling::div/button[text()='Sign up']";
	public final String WELLNESSCORP_VIEW_SESSION_OF_TITLE="//div[@id='lectureSessionModal']//div[text()='WellnessCorpLectureLiveSession']";
	public final String WELLNESSCORP_VIEW_SESSION_OF_LECTURE_SESSION_TIMING="//div[@id='lectureSessionModal']//div[text()='WellnessCorpLectureLiveSession']//parent::span//parent::div//following-sibling::div/div[contains(.,'5:30pm - 6:15pm')]";
	public final String WELLNESSCORP_VIEW_SESSION_FOLLOWING_SIGNUP="//div[@id='lectureSessionModal']//div[text()='WellnessCorpLectureLiveSession']//parent::span//parent::div//following-sibling::div/div[contains(.,'5:30pm - 6:15pm')]/parent::div/parent::div/following-sibling::div/button[text()='Sign up']";
	/**
	 * Name :     VigneshRaj
	 * Created Date:   26Feb2016
	 * Modified Date:  
	 */
	public final String OPSADMIN_PROVIDERSTAB="//div[@class='navbar-item-wrapper']//span[text()='Providers']";
	public final String OPSADMIN_PROVIDERSTAB_SEARCH_DROPDOWN="//div[@class='toolbar-input-group']//select";
	public final String OPSADMIN_SEARCH_FIELD="//div[@id='backendSearchInputGroup']//input[@id='searchField']";
	public final String OPSADMIN_SUBMITBUTTON="//button[@id='getUsers_search_submit']";
	public final String OPSADMIN_APOLLO_ZADO_COACH_LINK="//table//tbody//tr//td//a[contains(text(),'apollo zadocoach')]";
	public final String OPSADMIN_APOLLO_ZADO_COACH_CALENDER="//table//tbody//tr//td//a[contains(text(),'apollo zadocoach')]/parent::td[1]/following-sibling::td[3]/a";
	public final String OPSADMIN_CALENDER_ADD_BUTTON="//div[@class='schedule-calendar']//button[@class='btn btn-info btn-medium dropdown-toggle']";
	public final String CALENDER_ADD_BTN_1ON1_OPTION="//ul//li//a[text()='1 on 1']";
	public final String CALENDER_ADD_BTN_AVAILABILITY_OPTION="//ul//li//a[text()='Availability']";
	public final String AVAILABILITY_PAGE_AVAILABLE_RADIO_BTN="//input[@value='Available']";
	public final String AVAILABILITY_PAGE_REPEATS_BTN="//label//span[@class='onoffswitch-switch']";
	public final String SCHEDULE_CALENDER_SCHEDULE_AVA_FOR_TEXT="//h3[contains(text(),'Schedule Availability for')]";
	public final String CALENDER_SCHEDULE_START_TIME_TEXTBOX="//input[@id='eventStartTime']";
	public final String CALENDER_SCHEDULE_END_TIME_TEXTBOX="//input[@id='eventEndTime']";
	public final String CALENDER_SCHEDULE_FRIDAY_CHECKBOX="//ul[@id='scheduleDaysOfWeek']//li[6]//input[@id='checkFriday']";
	public final String CALENDER_SCHEDULE_SATURDAY_CHECKBOX="//ul[@id='scheduleDaysOfWeek']//li[7]//input[@id='checkSaturday']";
	public final String CALENDER_SCHEDULE_NEVER_CHECKBOX="//input[@id='checkNever']";
	public final String CALENDER_SCHEDULE_SAVE_BUTTON="//button[@id='saveAvailability']";
	public final String CALENDER_SCHEDULE_POPUP_TEXT="//div[@class='modal-content-notification']//p[contains(text(),'Your schedule update was successful')]";
	public final String CALENDER_SCHEDULE_POPUP_OK_BUTTON="//div[@class='modal-content-notification']//button";
	public final String SCHEDULE_PAGE_NAVIGATOR_RIGHT="//div[@id='arshawFullCalendar']//div[@class='fc-button-group']//button[2]";
	public final String SCHEDULE_AVAILABLITY_FRIDAY_BLUE_AREA="//table/thead/following-sibling::tbody/tr/td/div[2]/div/div[3]/table/tbody/tr/td[7]//div//div[10]";
	public final String NEW_LECTURE_SESSION_OPTION="//div[@class='popover-inner']//div//a[text()='New Lecture Session']";
	public final String SESSION_PROGRAM_NAME_DROPDOWN="//body//div[@class='page-content ng-scope']//div//div[3]//select";
	public final String SESSION_ORBERA_PROGRAM_52="//select//option[text()='Orbera Program: Post-placement for 52 weeks']";
	public final String SESSION_TITLE_TEXTBOX="//body//div[@class='page-content ng-scope']//div//div[5]//input";
	public final String SESSION_DESCRIPTION_TEXTBOX="//body//div[@class='page-content ng-scope']//div//div[6]//input";
	public final String SESSION_CONTINUE_BUTTON="//body//div[@class='page-content ng-scope']//div//div[7]//button[text()='Continue']";
	public final String AVAILABLE_TIME_DROPDOWN="//div[@id='divTimeZone']//ul//li[2]//select[@id='availableTimeSlotFilter']";
	public final String AVAILABLED_HOURS_OPTION="//select[@id='availableTimeSlotFilter']//option[text()='Available Hours']";
	public final String SESSION_SCHEDULE_CONFIRM_BUTTON="//div[@id='divSelectedTime']//div//div//button[@id='btnSchedule']";
	public final String SESSION_SCHEDULE_BUTTON="//div[@class='col-xs-12 no-p']//div//button[text()='Schedule']";
	public final String CONFIRMATION_POPUP_OK_BUTTON="//div[@class='modal-content-notification']//div//button[text()='OK']";
	public final String CREATED_LECTURE_SESSION_AREA="//table/thead/following-sibling::tbody/tr/td/div[2]//div//div[3]/following-sibling::div//table//tbody//tr//td[7]//div/a/div/div[2]";
	public final String COACH_LECTURE_SESSION_MEMBER_ONLINE_HEADER="//p[@id='coachMembersOnline']";
	public final String COACH_LECTURE_SESSION_SHARE_SCREEN_BUTTON="//button[@id='openScreenShareBtn']";
	public final String COACH_LECTURE_SESSION_UNMUTE_MEMBERS_BUTTON="//button[text()='Unmute Members']";
	public final String COACH_LECTURE_SESSION_MUTE_MEMBERS_BUTTON="//button[text()='Mute Members']";
	public final String COACH_LECTURE_SESSION_SESSION_COMPLETE_HEADER_TEXT="//div[@class='modal-header']//h3[text()='Session Complete']";
	public final String COACH_LECTURE_SESSION_YOUR_GROUP_SESSION_ENDED_TEXT="//div//span[contains(text(),'Your Group Session has ended')]";
	public final String COACH_LECTURE_SESSION_SESSION_COMMENTS_TEXTBOX="//div[@class='session-text-response']//textarea[@ng-model='coachNotes']";
	public final String COACH_LECTURE_SESSION_END_SESSION_RADIO_BUTTON="//input[@id='gsNotesOpt1' and @type='radio']";
	public final String COACH_LECTURE_SESSION_LEAVE_THE_SESSION_AND_JOIN_AGAIN_RADIO_BUTTON="//input[@id='gsNotesOpt2' and @type='radio']";
	public final String COACH_LECTURE_SESSION_CANCEL_AND_RESUME_THE_SESSION_RADIO_BUTTON="//input[@id='gsNotesOpt3' and @type='radio']";
	public final String COACH_LECTURE_SESSION_END_SESSION_TEXT="//span[contains(text(),'End session and mark as completed')]";
	public final String COACH_LECTURE_SESSION_LEAVE_THE_SESSION_AND_JOIN_AGAIN_TEXT="//span[contains(text(),'Leave the session and join again')]";
	public final String COACH_LECTURE_SESSION_CANCEL_AND_RESUME_THE_SESSION_TEXT="//span[contains(text(),'Cancel and resume the session')]";
	public final String COACH_LECTURE_SESSION_MEMBER_DONE_BUTTON="//button[contains(text(),'Done')]";
	public final String COACH_LECTURE_SESSION_PLEASE_SELECT_ONE_OF_THE_OPTIONS_ERROR_MSG="//div[contains(text(),'Please select one of the options')]";
	public final String COACH_LECTURE_SESSION_WELCOME_HEADER="//span[contains(text(),'Welcome to your Healthy Body ')]";
	public final String LECTURE_SESSION_MEMBER_SESSION_MIC_DISABLED="//div[@id='mic-status-icon'and@class='device-status-icon gray']";
	public final String LECTURE_SESSION_MEMBER_SESSION_VIDEO_DISABLED="//div[@id='video-status-icon'and@class='device-status-icon gray']";
	public final String COACH_LECTURE_SESSION_COACH_TILE_MEMBER_MIC_ENABLED="//span[@class='clickable fa fa-microphone green']";
	public final String COACH_LECTURE_SESSION_COACH_TILE_MEMBER_MIC_DISABLED="//span[@class='clickable fa fa-microphone-slash gray']";
	public final String OPSADMIN_LECTURE_SESSION_COACH_LINK="//table//tbody//tr//td//a[contains(text(),'lecture session coach')]";
	public final String OPSADMIN_LECTURE_SESSION_COACH_CALENDER="//span[@id='PA_ProviderList_FirstUser_Calender_Link']";
	public final String LECTURE_SESSION_SCHEDULE_AVAILABLITY_FRIDAY_BLUE_AREA="//div[@class='admin-dashboard-container system-user-page-wrapper']//div[@ng-class='calendarSpan']//div[@id='arshawFullCalendar']/div/div/table/tbody/tr/td//div[@class='fc-day-grid']/following-sibling::div/div[@class='fc-time-grid']//div[3]/table/tbody/tr//td[7]/div//div[36]";
	public final String COACH_LECTURE_SESSION_SCREEN_SHARE_HEADER="//h3[text()='Screen Share']";
	public final String COACH_LECTURE_SESSION_SCREEN_SHARE_REFRESH_BUTTON="//a[@title='refresh available screens / windows']";
	public final String COACH_LECTURE_SESSION_SHARE_SCREEN_IMAGE="//div[@id='webRtcShareScreenModal']/div[2]/a[2]/img";
	public final String COACH_LECTURE_SESSION_STOP_SHARING_SCREEN="//button[@id='stopScreenShareBtn']";
	/**
	 * Name :     VigneshRaj
	 * Created Date:   17/March/2016 
	 * Modified Date: 
	 *  1on1 Live session Coach Login
	 */
	public static String COACHES_LOGIN_PAGE_LOGO_REF ="//div[@id='orgLoginBoxLogo']";
	public static String COACHES_USERNAME_1 ="//input[@type='email']";
	public static String COACHES_PASSWORD ="//input[@type='password']";
	public static String COACHES_LOGIN_BUTTON ="//button[@id='login_button']";
	public static String COACHES_LOGIN_USERNAME_REQUIRED ="//span[text()='Your email address is required.']";
	public static String COACHES_LOGIN_PASSWORD_REQUIRED ="//span[text()='Your password is required.']";
	public static String COACHES_CLASSES_TAB ="//div[@class='navbar-item-wrapper']//span[text()='Classes']";
	public static String COACHES_SIGNOUT_BUTTON ="//div[@class='text-right inline-table-cell']//i/parent::span/following-sibling::ul//li[*]/a[@id='navbar_hfSysUserSettings_SignOut']";
	public static String COACHES_SIGNOUT_YOU_HAVE_SIGNED_OUT_TEXT_REF ="//div[@id='loggedoutPageContent']//h1[text()='You have signed out.']";
	public static String COACHES_SCHEDULE_OPTION ="//a[contains(text(),'Schedule')]";
	/**
	 * Name :     VigneshRaj
	 * Created Date:   29/March/2016 
	 * Modified Date: 
	 *  1on1 Live session Gear icon Settings
	 */
	public static String LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_BUTTON ="//span[@ng-click='openSettings()']"; 
	public static String LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_TITLE ="//div[text()='Settings']"; 
	public static String LIVESESSION_1ON1_SESSION_MEMBER_SETTINGS_CLOSE_BUTTON ="//div[contains(text(),'Settings')]//parent::span//following::a[@ng-click='close()']"; 
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
	public final String CREATED_LECTURE_SESSION_6_00_6_45="//div[@class='fc-content']//div//span[text()='6:00 pm - 6:45 pm']";
	public final String OPS_ADMIN_PROVIDERS_LECTURE_SESSION_SCHEDULE_CANCEL_SCHEDULE_OPTION="//div[@class='popover-inner']//div//a[text()='Cancel Lecture']";
	public final String OPS_ADMIN_PROVIDERS_LECTURE_SESSION_SCHEDULE_ASSIGN_SUBSTITUTE_OPTION="//div[@class='popover-inner']//div//a[text()='Assign substitute coach']";
	public final String OPS_ADMIN_PROVIDERS_LECTURE_SESSION_CANCEL_SESSION_TITLE="//div[@class='col-xs-12 no-p']//div//h2[contains(text(),'Cancel Session')]";
	public final String OPS_ADMIN_PROVIDERS_LECTURE_SESSION_LECTURE_TITLE_LABEL="//div[@class='col-xs-12 no-p']//p[contains(text(),'Lecture Title:')]";
	public final String OPS_ADMIN_PROVIDERS_LECTURE_SESSION_START_TIME_LABEL="//div[@class='col-xs-12 no-p']//p[contains(text(),'Start Time:')]";
	public final String OPS_ADMIN_PROVIDERS_LECTURE_SESSION_CANCEL_SESSION_BUTTON="//div//button[text()='Cancel Session']";
	public final String OPS_ADMIN_PROVIDERS_LECTURE_SESSION_BACK_TO_SCHEDULE="//div//button[text()='Back to Schedule']";
	public final String OPS_ADMIN_PROVIDERS_LECTURE_SESSION_SCHEDULE_CANCEL_POPUP_CANCELLED_SUCCESS_TEXT="//p[contains(text(),'Session Enrollment Cancelled Successfully')]";
	public final String OPS_ADMIN_PROVIDERS_LECTURE_SESSION_SCHEDULE_CANCEL_POPUP_OK_BUTTON="//button[contains(text(),'OK')]";
	/**
	 * Name :     VigneshRaj
	 * Created Date:   4/APR/2016 
	 * Modified Date: 
	 *  1on1 Live session Member Connections Browser Icon
	 */
	public static String LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_FIREFOX_BROWSER_ICON="//span[@class='users-browser-icon sprite-Firefox-icon']";
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
	public static String LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_SPEAKER_SELECTED_HEADER=" //strong[text()='Speaker Selected:']";
	public static String LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_CAMERA_OPTIONS_HEADER="//strong[text()='Camera Options:']";
	public static String LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_CAMERA_SELECTED_HEADER="//strong[text()='Camera Selected:']";
	public static String LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_CHROME_BROWSER_ICON="//span[@class='users-browser-icon sprite-Chrome-icon']";
	/**
	 * Name :     VigneshRaj
	 * Created Date:   4/APR/2016 
	 * Modified Date: 
	 *  Session Widget display
	 */
	public static String SESSION_WIDGET_VIEW_SESSION_BUTTON_TEXT="//span[contains(text(),'Sign up for upcoming')]";
	public static String SESSION_WIDGET_VIEW_SESSION_BUTTON="//button[text()='View Sessions']";
	public static String SESSION_WIDGET_1ON1_YOUR_NEXT_SESSION="//span[text()='Your next session can be scheduled in the next interval']";
	public static String SCHEDULE_BUTTON_AVAILABLE="//button[@id='memberSessionsCard_ScheduleOneOnOneSessions_btn']";
	public final String GETTINGFIT_VIEW_ARTICLE_LINK ="//button[contains(text(),'View Article')]";
	public final String GETTINGFIT_VIEW_ARTICLE_PDF_BUTTON ="//div[contains(text(),'Getting Fit')]/following::div[3]/div[2]/div/div//div/following::button[contains(text(),'View Article')]";
	/**
	 * Name :     LEENA.P
	 * Created Date:   14/JULY/16 
	 * Modified Date:  
	 */ 
	public final String YOP_EMAIL_TEXTBOX="//input[@id='login']"; 
	public final String YOP_EMAIL_CHECK_INBOX="//input[@type='submit']"; 
	public final String YOP_EMAIL_IFRAME="//iframe[@id='ifmail']";
	public final String GUERRILLA_MAIL_DROPDOWN="//select[@id='gm-host-select']";
	public final String GUERRILLA_MAIL_TEXTBOX="//span//input[@type='text']";
	public final String GUERRILLA_MAIL_SET_BUTTON="//button[contains(text(),'Set')]";
	public final String GUERRILLA_MAIL_EDIT_BUTTON="//span[@id='inbox-id']";  
	public final String ORBERA_MEMBER_1on1_MAIL="//*[@id='m1']//span[contains(text(),'Your 1on1 Coaching Session has been rescheduled')]";
	public final String ORBERA_MEMBER_1on1_CONTENT="//div[contains(text(),'This message is to confirm your upcoming 1-on-1 session')]";
	public final String YOP_EMAIL_IFRAME1="//html/body/div[5]/table/tbody/tr[1]/td/table/tbody/tr/td[1]/table/tbody/tr[2]/td/iframe";
	public final String YOP_EMAIL_IFRAME2="//html/body/div[5]/table/tbody/tr[1]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td[1]/iframe";
	public final String YOP_EMAIL_DELETE ="//a[@class='igif lmenudelall']"; 
	public final String YOP_EMAIL_EMPTY_MAIL="//td[@class='menusep']//div/ul//li[3]/a"; 
	public final String ORBERA_MEMBER_1on1_MAIL_CANCELLATION="//*[@id='m1']//span[contains(text(),'1on1')][contains(text(),'Canceled')]";
	public final String ORBERA_MEMBER_1on1_CONTENT_CANCELLATION="//div[contains(text(),'has been canceled')]";
	public final String ORBERA_MEMBER_1on1_MAIL_SCHEDULED="//*[@id='m1']//span[contains(text(),'1on1 Session Has been Scheduled')]";
	public final String ORBERA_MEMBER_1on1_CONTENT_SCHEDULED="//div[contains(text(),'This message is to confirm your 1on1 Session')]";
	public final String SELECT_ALL_BUTTON="//a[@id='e0']";
	public final String DELETE_ALL_BUTTON="//a[contains(text(),'Delete')]";
	public final String SELECTED_MSGS="//a[contains(text(),'Selected messages')]";
	public final String ORBERA_MEMBER_1on1_MAIL_SCHEDULED_GR="//*[contains(text(),'1on1 Session Has been Scheduled')]";
	public final String ORBERA_MEMBER_1on1_COACHING_SCHEDULED_GR="//*[contains(text(),'Your 1on1 Coaching Session has been rescheduled')]";
	public final String MEMBER_VIEW_SESSION_FOLLOWING_SIGNUP="//div[@class='hf-modal-content']//div[text()='testingnotification']//parent::span/parent::div/parent::div/following-sibling::div//button[contains(text(),'Sign up')]";
	public final String MEMBER_VIEW_SESSION_SUCCESSFULL_POPUP_TEXT="//*[contains(text(),'Session Scheduled Successfully')]";
	public final String MEMBER_VIEW_SESSION_SUCCESSFULL_POPUP_OK_BUTTON="//div[@class='modal-content']//div//div//button[text()='OK']";
	public final String VERIFY_CREATED_LECTURE_SESSION_NOTIFICATION="//div[contains(text(),'testingnotification')]";
	public final String AVAILABLE_SESSIONS_CLOSE_BUTTON="//div[contains(text(),'Available Upcoming Sessions')]//parent::span//parent::div//following-sibling::div/a";
	public final String NEXT_PAGE_ARROW="//li[@title='Next Page']//a";
	public final String ORBERA_MEMBER_LECTURE_CONTENT_CANCELLATION="//div[contains(.,'has been canceled')]";
	public final String MEMBER_VIEW_SESSION_OF_LECTURE_SESSION_TIMING="//div[@id='lectureSessionModal']//div[1]//ul//li[contains(text(),'Sat')]/parent::ul/parent::div/following-sibling::div//ul//li[2]//span[contains(text(),' 06:00pm - 06:45pm')]";
	public final String MEMBER_VIEW_SESSION_OF_SATURDAY_TEXT="//div[@id='lectureSessionModal']//div[1]//ul//li[contains(text(),'Sat')]";
	public final String MEMBER_VIEW_SESSION_NEXT_PAGE_NAVIGATOR="//i[@class='fa fa-angle-right']";  
	public final String MEMBER_UPCOMING_SESSIONS_PAGES = "lecturePagingData";
	public final String PROGRAM_SELECTION ="//label[text()='Program Sessions']/following-sibling::div//select";
	public final String ORBERA_MEMBER_LECTURE_MAIL_CANCELLATION="//div[@id='m1']//span[contains(text(),'Canceled')]";
	public final String ORBERA_PA_LECTURE_MAIL_CANCELLATION="//span[contains(text(),'Session Canceled: Lecture')]";
	public final String YOPMAIL_DELETE ="//a[contains(@title,'Delete this mail')]";
	public final String ORBERA_MEMBER_SESSION_TIME="//h5[@class='livesession-col session-time ng-binding']";
	public final String  OPS_ADMIN_MEMBERS_TAB="//div[@class='navbar-item-wrapper']//span[text()='Members']";
	public final String  PATIENT_SORT_DROPDOWN="//select[@id='filterMemberList']";
	public final String  PATIENT_SEARCH_TEXTBOX="//input[@id='searchBar']";
	public final String  PATIENT_SEARCH_SUBMIT_BUTTON="//button[@id='submit-search']";
	public final String  SEARCHED_MEMBER1="//a[@id='editLink0']";
	public final String  OPSADMIN_MEMBER_TABLE_COACH_NAME="//span[@ng-if='!isCoach && !isPractitioner']//a";
	public final String  PROGRAM_ADMIN_PROVIDER_TAB_SEARCH_DROPDOWN="//div[@class='table-head']//select[@id='PA_ProviderList_All_Dropdown=']";
	public final String  PROGRAM_ADMIN_PROVIDER_TAB_SEARCH_TEXTBOX="//div[@class='table-head']//input[@id='searchField']";
	public final String  PROGRAM_ADMIN_PROVIDER_TAB_SEARCH_TEXTBOX_SUBMIT_BUTTON="//button[@id='getUsers_search_submit']";
	public final String  COACHES_GEAR_BUTTON_SCHEDULE_ADD_BUTTON="//div[@class='schedule-calendar']//button[@class='btn btn-info btn-medium dropdown-toggle']";
	public final String  COACHES_SCHEDULE_CALENDER_ADD_AVAILABLITY_OPTION="//ul//li//a[text()='Availability']";
	public final String  COACHES_SCHEDULE_CALENDER_SCHEDULE_REPEATS_BUTTON="//label//span[@class='onoffswitch-switch']";
	public final String  COACHES_SCHEDULE_CALENDER_SCHEDULE_AVA_FOR_TEXT="//h3[contains(text(),'Schedule Availability for')]";
	public final String  COACHES_SCHEDULE_CALENDER_SCHEDULE_START_TIME_TEXTBOX="//input[@id='eventStartTime']";
	public final String  COACHES_SCHEDULE_CALENDER_SCHEDULE_END_TIME_TEXTBOX="//input[@id='eventEndTime']";
	public final String  COACHES_SCHEDULE_CALENDER_SCHEDULE_NEVER_CHECKBOX="//input[@id='checkNever']";
	public final String  COACHES_SCHEDULE_CALENDER_SCHEDULE_SAVE_BUTTON="//button[@id='saveAvailability']";
	public final String  COACHES_SCHEDULE_CALENDER_SCHEDULE_POPUP_TEXT="//div[@class='modal-content-notification']//p[contains(text(),'Your schedule update was successful')]";
	public final String  COACHES_SCHEDULE_CALENDER_SCHEDULE_POPUP_OK_BUTTON="//div[@class='modal-content-notification']//button";
	public final String  OPS_ADMIN_PROVIDERS_HEADER_TAB="//div[@id='navbarPrimaryInner']//span[text()='Providers']";
	public final String  LECTURE_SESSION_FRIDAY_CHECKBOX="//ul[@id='scheduleDaysOfWeek']//li[6]//input[@id='checkFriday']";
	public final String  LECTURE_SESSION_MONDAY_CHECKBOX="//input[@id='checkMonday']";
	public final String  LECTURE_SESSION_TUESDAY_CHECKBOX="//input[@id='checkTuesday']";
	public final String  LECTURE_SESSION_WEDNESDAY_CHECKBOX="//input[@id='checkWednesday']";
	public final String  LECTURE_SESSION_THURSDAY_CHECKBOX="//input[@id='checkThursday']";
	public final String  COACHES_YOU_HAVE_SIGNED_OUT_TEXT="//h1[contains(text(),'You have signed out')]";
	public final String  COACHES_SIGN_IN_AGAIN_BUTTON="//a[contains(text(),'Sign in again')]";
	public static String COACHES_LOGIN_DASHBOARD_REF ="//div[@class='navbar-item-wrapper']//span[text()='Dashboard']";
	public static String COACHES_GEAR_BUTTON_SCHEDULE_PAGE_NAVIGATOR_RIGHT ="//div[@id='arshawFullCalendar']//div[@class='fc-button-group']//button[2]//span";
	public static String MEMBER_TRACKERS_FITNESS_TAB_CUSTOM_SUBTAB_START_DATE ="//input[@id='dateRangeStart']";
	public static String MEMBER_TRACKERS_FITNESS_TAB_CUSTOM_SUBTAB_END_DATE ="//input[@id='dateRangeEnd']";
	public static String PROVIDER_MEMBER_TRACKERS_FITNESS_TAB_CUSTOM_SUBTAB_START_DATE ="//input[@id='fitnessDateRangeStart']";
	public static String PROVIDER_MEMBER_TRACKERS_FITNESS_TAB_CUSTOM_SUBTAB_END_DATE ="//input[@id='fitnessDateRangeEnd']";
	public static String START_DATE_MONTH_SELECTOR_START ="//button[@class='btn btn-default btn-sm pull-left']/parent::th/following-sibling::th/button/strong[contains(text(),'";
	public static String START_DATE_MONTH_OR_START ="//button[@class='btn btn-default btn-info active']/span[contains(text(),'";
	public static String START_DATE_OR_END ="')]";
	public static String START_DATE_DAY_OR_START ="//input[@id='fitnessDateRangeStart']/following-sibling::ul//li//table//tbody//button[contains(@class,'btn btn-default')]/span[contains(text(),'";
	public static String WEIGHT_START_DATE_DAY_OR_START ="//input[@id='dateRangeStart']/following-sibling::ul//li//table//tbody//button[contains(@class,'btn btn-default')]/span[contains(text(),'";
	public final String  ASSIGNED_COACH_SUBSTITUTE_FOR_SCHEDULED_ONE_ON_ONE_START_OR="//tbody[@id='sessionClasses_tableBody']//tr/td[contains(text(),'";
	public final String  ASSIGNED_COACH_SUBSTITUTE_FOR_SCHEDULED_ONE_ON_ONE_END_OR="')]/following-sibling::td[7]/ul/li/a[@title='Substitute Coach']";
	public final String SESSION_CONFLICTS_RESCHEDULE_OPTION_START_OR="//table[@class='table system-user-table table-standard table-fixed']//tr/td[2][contains(text(),'";
	public final String SESSION_CONFLICTS_RESCHEDULE_OPTION_END_OR="')]/parent::tr/td[10]/div/a[contains(text(),'Reschedule')]";
	public final String SESSION_CONFLICTS_CANCEL_OPTION_START_OR="//table[@class='table system-user-table table-standard table-fixed']//tr/td[2][contains(text(),'";
	public final String SESSION_CONFLICTS_CANCEL_OPTION_END_OR="')]/parent::tr/td[10]/div/a[contains(text(),'Cancel')]";
	public final String MEMBER_CERTAIN_FEATURES_IN_THE_ORBERA_NOT_SUPPORTED_BY_YOUR_BROWSER_WARNING_MSG="//div[contains(text(),'Certain features in the Orbera platform')]";
	public final String MEMBER_CERTAIN_FEATURES_IN_THE_ORBERA_CLOSE_BUTTON="//a[@class='alert-close']";
	public final String VSPN_TUTORIAL_LINK ="//a[contains(@target,'Tutorial')]";
	public final String VSPN_SUMMARY_LINK ="//a[contains(@target,'PDF')]";
	public final String MEMBER_ORBERA_REMOVAL_POPUP_TEXT ="//div[@class='hf-modal-positioning']/div/div/div/section/div[5]//div[contains(text(),'Did you have your Orbera removal')]";
	public final String MEMBER_ORBERA_REMOVAL_YES_I_HAD_BUTTON ="//a[contains(@target,'Tutorial')]";
	public final String HFOPS_CLASSES_ALLSESSION_PROGRAM_LINK_NAME ="//tbody[@id='sessionClasses_tableBody']//tr/td[3]/a";
	public final String HFOPS_CLASSES_ALLSESSION_PROGRAM_SORT_ARROW ="//th[@id='sessionClasses_Program']/span[3]";
	public final String MEMBER_SESSION_TIME="//h5[@class='livesession-col session-time ng-binding']";
	public final String LIVE_SESSION_MEMBER_JOINED_SESSION_CONNECTED_TEXT ="//div[@class='live-session-inner-wrapper row']/div/div//div[2]/div/span[text()='Connected!']";
	public static String COACHES_SIGNOUT_LINK_BUTTON ="//div[@class='text-right inline-table-cell']//i";
	public final String RA_PROGRAM_ADMIN_REAL_APPEAL_LOGO="//a[@id='orgNavbarBrand']";
	public static final String PROVIDER_USER_SESSION_POPUP="//p[contains(text(),'user is already active in another browser')]//following-sibling::button";
	public final String ORBERA_SESSION_CHANGE_LIVE_DROPDOWN="//select[@ng-model='SelectedProvider']";
	public final String ORBERA_SESSION_CHANGE_SAVE_BUTTON="//button[@ng-click='save();']";
	public final String ORBERA_SESSION_CAMERA_BUTTON="//span[@class='fa fa-video-camera']";
	public final String LECTURE_SESSION_SCREEN_SHARING_ADD_BROWSER_PLUGIN="//a[@class='button  prominent  add installer']";
	public final String MEMBER_ONEONONE_SESSION_TIME="//h5[@class='livesession-col col-3 session-time ng-binding']";

	
}