package com.zillion.qa.programadmin;
public interface OR {
	/**
	 * Suresh.V
	 * 15.12.2015
	 * modified: 24.12.2015
	 * Program Admin Login and Logout.
	 */
	public final String PROVIDER_LOGIN_BUTTON="//button[@id='login_button']";
	public final String EMAIL_TEXTBOX_PROVIDER_URL="//input[@type='email']";
	public final String PASSWORD_TEXTBOX_PROVIDER_URL="//input[@type='password']";
	public final String PROGRAM_ADMIN_DASHBOARD_TAB="//span[text()='Dashboard']";
	public final String PA_SETTING_IMAGE="//div[@class='text-right inline-table-cell']//i";
	public final String PA_SIGNOUT_LINK="//a[@id='navbar_hfSysUserSettings_SignOut']";
	public final String USERNAME_REQUIRED_IN_LOGIN_PAGE="//span[text()='Your email address is required.']";
	public final String PASSWORD_REQUIRED_IN_LOGIN_PAGE="//span[text()='Your password is required.']";
	public final String SIGNIN_AGAIN_LINK="//div[@id='loggedoutPageContent']//a[text()='Sign in again.']";
	/**
	 * Suresh.V
	 * 15.12.2015
	 * modified: 24.12.2015
	 * Program Admin page All tabs verify
	 */
	public final String PROGRAM_ADMIN_PROGRAMS_TAB="//span[text()='Programs']";
	public final String PROGRAM_ADMIN_CLASSES_TAB="//span[text()='Classes']";
	public final String PROGRAM_ADMIN_PATIENTS_TAB="//span[text()='Patients']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB="//span[text()='Providers']";
	public final String PROGRAM_ADMIN_CLIENTS_TAB="//span[text()='Clients']";
	public final String PROGRAM_ADMIN_MESSAGES_TAB="//span[text()='Messages']";
	public final String PROGRAM_ADMIN_SESSIONS_TAB="//span[text()='Sessions']";
	public final String PROGRAM_ADMIN_USER_ID_DISPLAY_RIGHT_CORNER="//a[@id='sessionStateUserName']";
	/**
	 * Suresh.V
	 * 30.12.2015
	 * modified: 30.12.2015
	 * Program Admin
	 */
	public final String PROGRAM_ADMIN_ACTIVE_CLIENT_TAB="//li[@class='navbar-nav-btn nav-tab-active']//div//a//span[text()='Clients']";
	/**
	 * VIGNESHRAJ.M
	 * 05.01.2016
	 * modified:
	 * Add new Provider
	 */
	public final String PROGRAM_ADMIN_PROVIDERS_HEADER_TAB="//div[@id='navbarPrimaryInner']//span[text()='Providers']";
	public final String PROGRAM_ADMIN_NEW_PROVIDERS_BUTTON="//div[@class='table-head']//button[@id='createNewProviderBtn']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_CREATE_NEW_PROVIDER_TITLE="//div[@class='profile-header-container']//h3[text()='Create New Provider']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_GENERAL_TAB="//div[@class='profile-navtab-wrapper border-primary']//a[text()='General']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_LANGUAGES_TAB="//div[@class='profile-navtab-wrapper border-primary']//a[text()='Languages']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_APPROVAL_TAB="//div[@class='profile-navtab-wrapper border-primary']//a[text()='Approval']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_ALL_SESSIONS_TAB="//div[@class='profile-navtab-wrapper border-primary']//a[text()='All Sessions']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_PERSONAL_INFO_TEXT="//div[@id='profilePanel']//div[text()='Personal Information']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_ORG_LABEL="//div[@id='profilePanel']//label[text()='Organization: *']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_ROLE_LABEL="//div[@id='profilePanel']//label[text()='Role: *']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_FIRST_NAME_LABEL="//div[@id='profilePanel']//label[text()='First Name: *']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_LAST_NAME_LABEL="//div[@id='profilePanel']//label[text()='Last Name: *']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_PHONE_LABEL="//div[@id='profilePanel']//label[text()='Phone: *']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_EXT_LABEL="//div[@id='profilePanel']//label[text()='Ext:']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_MOBILE_LABEL="//div[@id='profilePanel']//label[text()='Mobile:']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_ADDRESS1_LABEL="//div[@id='profilePanel']//label[text()='Address 1: *']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_ADDRESS2_LABEL="//div[@id='profilePanel']//label[text()='Address 2:']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_CITY_LABEL="//div[@id='profilePanel']//label[text()='City: *']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_STATE_LABEL="//div[@id='profilePanel']//label[text()='State: *']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_ZIPCODE_LABEL="//div[@id='profilePanel']//label[text()='Zip Code: *']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_NICKNAME_LABEL="//div[@id='profilePanel']//label[text()='Nickname:']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_GENDER_LABEL="//div[@id='profilePanel']//label[text()='Gender: *']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_DOB_LABEL="//div[@id='profilePanel']//label[text()='Date of Birth: *']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_PROFILE_PIC_LABEL="//div[@id='profilePicturePanel']//div[text()='Profile Picture']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_CREDENTIALS_LABEL="//div[@id='credentialsPanel']//div[text()='Credentials']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_EMAIL_LABEL="//div[@id='credentialsPanel']//label[text()='Email: *']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_BIO_LABEL="//div[@id='bioPanel']//div[text()='Bio']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_CREATE_AND_SAVE_BUTTON="//input[@id='submitBtnCreateUserForm']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_ORG_TEXTBOX="//select[@id='userOrganization']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_ROLE_TEXTBOX="//select[@id='userRoleId']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_FIRST_NAME_TEXTBOX="//input[@id='firstName']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_LAST_NAME_TEXTBOX="//input[@id='lastName']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_PHONE_TEXTBOX="//input[@id='phone']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_EXT_TEXTBOX="//input[@id='extension']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_MOBILE_TEXTBOX="//input[@id='mobilePhone']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_ADDRESS1_TEXTBOX="//input[@id='street']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_ADDRESS2_TEXTBOX="//input[@id='street2']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_CITY_TEXTBOX="//input[@id='city']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_STATE_TEXTBOX="//select[@id='state']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_ZIPCODE_TEXTBOX="//input[@id='postalCode']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_NICKNAME_TEXTBOX="//input[@id='nickname']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_GENDER_TEXTBOX="//input[@id='genderMale']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_DOB_MONTH_TEXTBOX="//select[@id='dobMonth']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_DOB_DAY_TEXTBOX="//select[@id='dobDay']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_DOB_YEAR_TEXTBOX="//select[@id='dobYear']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_EMAIL_TEXTBOX="//input[@id='email']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_BIO_TEXTBOX="//textarea[@id='bio']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_ORG_REQUIRED="//span[text()='You must Select an organization.']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_ROLE_REQUIRED="//span[text()='You must specify a role for this provider.']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_FIRSTNAME_REQUIRED="//span[text()='First name is required.']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_LASTNAME_REQUIRED="//span[text()='Last name is required.']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_PHONE_REQUIRED="//span[text()='At least one phone number is required.']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_ADDRESS1_REQUIRED="//span[text()='Address 1 is required.']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_CITY_REQUIRED="//span[text()='City is required.']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_STATE_REQUIRED="//span[text()='State is required.']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_ZIPCODE_REQUIRED="//span[text()='Zip Code is required.']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_GENDER_REQUIRED="//span[text()='Gender is required.']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_MONTH_REQUIRED="//span[text()='Month is required.']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_DAY_REQUIRED="//span[text()='Day is required.']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_YEAR_REQUIRED="//span[text()='Year is required.']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_BIO_REQUIRED="//span[text()='Bio is required.']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_EDITING_PROFILE="//div[@class='profile-header-container']//h3[contains(text(),'Editing Profile')]";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_WEEKLY_AVAIL_TITLE_REF="//div[@id='availableHoursPanel']//div[text()='Weekly Availability']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_AVAILABLE_HOURS_LABEL="//div[@id='availableHoursPanel']//label[text()='Available Hours per Week:']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_WEEKLY_HOURS_LABEL="//div[@id='availableHoursPanel']//label[text()='Work Hours per Week: *']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_STATUS_TITLE="//div[@id='statusPanel']//div[text()='Status']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_STATUS_EDIT_BUTTON="//div[@id='statusPanel']//div[@class='edit-toggler-backend pull-right']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_STATUS_PROVIDER_STATUS_LABEL="//div[@id='statusPanel']//label[text()='Provider Status:']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_STATUS_ACTIVE_BUTTON="//div[@id='statusPanel']//input[@id='isProviderActiveTrue']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_STATUS_SAVE_BUTTON="//div[@id='statusPanel']//input[@id='submitBtnStatusForm']";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_POPUP_TEXT_REF="//div[@class='modal-dialog']//h4[contains(text(),'Marking this user as active')]";
	public final String PROGRAM_ADMIN_PROVIDERS_TAB_ACTIVATE_POPUP_OK_BUTTON="//div[@class='modal-dialog']//button[@id='activateProviderConfirmationOK']";
	/**
	 * VIGNESHRAJ.M
	 * 05.01.2016
	 * modified:
	 * Validate Insufficient, All Sessions Under Classes tab in Program Admin URL.
	 */
	public final String PROGRAM_ADMIN_CLASSES_TAB_HEADER="//div[@id='navbarPrimaryLevel']//span[text()='Classes']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_INSUFFICIENT_SUBTAB="//div[@id='navbarPrimarySubNav']//a[text()='insufficient']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_SUBTAB="//div[@id='navbarPrimarySubNav']//a[text()='all sessions']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_TIMESLOTCHECK_SUBTAB="//div[@id='navbarPrimarySubNav']//a[text()='timeslot check']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_INSUFFICIENT_TITLE="//div[@class='table-head']//span[text()='Insufficient Classes']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_INSUFFICIENT_TOTAL_COUNT="//div[@class='table-head']//span[@class='page-header-counter-number align-middle ng-binding']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_TITLE="//div[@class='table-head']//span[text()='All Sessions']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_TOTAL_COUNT="//div[@class='table-head']//span[@class='page-header-counter-number align-middle ng-binding']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_SESSION_DROPDOWN="//div[@class='system-user-toolbar text-right']//button[@type='button']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_DROPDOWN_UPCOMING="//ul[@class='dropdown-menu select-dropdown-list']//a[text()='Upcoming']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_DROPDOWN_PREVIOUS="//ul[@class='dropdown-menu select-dropdown-list']//a[text()='Previous ']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_DROPDOWN_ALL="//ul[@class='dropdown-menu select-dropdown-list']//a[text()='All']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_UPCOMING_1MONTH_AHEAD_TEXT_REF="//div[@class='system-user-toolbar text-right']//b[text()='1 month ahead from:']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_UPCOMING_1MONTH_BACK_TEXT_REF="//div[@ng-show='previousSession']//b[text()='1 month back from:']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_UPCOMING_START_DATE="//div[@class='system-user-toolbar text-right']//input[@id='startDt']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_UPCOMING_END_DATE="//div[@class='system-user-toolbar text-right']//input[@id='endDt']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_UPCOMING__START_DATE_CALENDER="//span[@ng-click='showStartDtCalender()']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_UPCOMING_SESSION_FILTER="//select[@ng-model='sessionFilter']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_UPCOMING_SESSION_FILTER_ALL="//select[@ng-model='sessionFilter']//option[text()='All']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_UPCOMING_SESSION_FILTER_PATIENT="//select[@ng-model='sessionFilter']//option[text()='Member']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_UPCOMING_SESSION_FILTER_COACH="//select[@ng-model='sessionFilter']//option[text()='Coach']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_UPCOMING_SESSION_FILTER_SESSION_WEEK="//select[@ng-model='sessionFilter']//option[text()='Session Week']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_UPCOMING_SESSION_FILTER_SESSION_STATUS="//select[@ng-model='sessionFilter']//option[text()='Session Status']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_CONTENT_TABLE_DATE="//table[@id='sessiontblData']//span[text()='Date ']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_CONTENT_TABLE_TIME="//table[@id='sessiontblData']//span[text()='Time ']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_CONTENT_TABLE_PROGRAM="//table[@id='sessiontblData']//span[text()='Program ']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_CONTENT_TABLE_SESSIONWEEK="//span[@id='sessionClasses_Title_SessionWeek']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_CONTENT_TABLE_SESSIONTYPE="//span[@id='sessionClasses_Title_SessionType']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_CONTENT_TABLE_PATIENT="//table[@id='sessiontblData']//span[text()='Patient ']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_CONTENT_TABLE_CITY="//table[@id='sessiontblData']//span[text()='City, State ']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_CONTENT_TABLE_COACH="//table[@id='sessiontblData']//span[text()='Coach ']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_CONTENT_TABLE_STATUS="//table[@id='sessiontblData']//span[text()='Status ']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_CONTENT_TABLE_LOGINCOUNT="//table[@id='sessiontblData']//span[text()='Login Count']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_CONTENT_TABLE_LOGINDATE="//th[@id='sessionClasses_Attendee_Login_Date']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_CONTENT_TABLE_LOGINTIME="//span[@id='sessionClasses_Title_Attendee_State']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_CONTENT_TABLE_JOINTIME="//span[@id='sessionClasses_Title_Attendee_Last_Join_Time']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_CONTENT_TABLE_CLIENT_NAME="//span[@id='sessionClasses_Title_Attendee_Client_Name']";
	public final String PROGRAM_ADMIN_CLASSES_TAB_ALL_SESSIONS_CONTENT_TABLE_ACTION="//span[@id='sessionClasses_Title_Action']";
	/**
	 * SURESH.V
	 * 11.01.2016
	 */
	public final String PAGINATION_INFORMATION="//span[@class='pagination-info']";
	public final String SELECT_VALUE_ALL="//option[@selected='true' and text()='All']";
	public final String SELECT_VALUE_CLIENT="//option[ text()='Client']";
	public final String FILTER_CLIENT_LIST="//select[@id='filterClientList']";
	public final String SEARCH_IMAGE="//button[@class='btn btn-default']";
	public final String SERACH_TEXT_BOX_ACTIVE="//input[@id='searchBar']";
	public final String PAGINATION_COUNT="//div[@class='table-pagination']//span[@class='pagination-info']//span[1]";
	public final String PAGINATION_PREVIOUSPAGE_DISABLE="//button[@id='previousPage_PrevButton'and @disabled='disabled']";
	public final String PAGINATION_NEXTPAGE_DISABLE="//button[@id='previousPage_NextButton'and @disabled='disabled']";
	public final String PAGINATION_NEXTPAGE_ENABLE="//button[@id='previousPage_NextButton']";
	/**
	 * PA Wellness corp
	 */
	public static String COACHES_LOGIN_PAGE_LOGO_REF ="//div[@id='orgLoginBoxLogo']";
	public static String PA_WELLNESSCORP_USERNAME ="//input[@type='email']";
	public static String PA_WELLNESSCORP_PASSWORD ="//input[@type='password']";
	public static String PA_WELLNESSCORP_LOGIN_BUTTON ="//button[@id='login_button']";
	public static String COACHES_SIGNOUT_LINK_BUTTON ="//div[@class='text-right inline-table-cell']//i";
	public static String COACHES_SIGNOUT_BUTTON ="//a[text()='Sign Out']";
	public static String COACHES_SIGNOUT_YOU_HAVE_SIGNED_OUT_TEXT_REF ="//div[@id='loggedoutPageContent']//h1[text()='You have signed out.']";
	public static String COACHES_SIGNOUT_SIGN_IN_AGAIN_TEXT_REF ="//div//a[text()='Sign in again.']";
	public static String NO_RESULT_MSG ="//td[text()='No search results found.']";
	public final String SCHEDULER_DAY_DATE="//div[@class='fc-left']//h2";
	public final String HIGHLIGHTED_DATE_FIRSTHALF="//td[contains(@class,'fc-today fc-state-highlight') and contains(@data-date,";
	public final String HIGHLIGHTED_DATE_SECONDHALF=")]";
	public final String HIGHLIGHTED_DATE="//td[@class='fc-day fc-widget-content fc-tue fc-today fc-state-highlight' and contains(@date-date,'')]";
	public final String SESSION_TIME_SLOT_5_30PM="//div[@id='timeSelection']//div//div//span[contains(text(),'Friday')]/parent::div[1]/following-sibling::div[4]//ul//li//a/div[text()='5:30PM ']";
	public final String COACHES_SETTINGS_LINK_SCHEDULE_BUTTON="//a[text()='Schedule']";
	public final String CLICK_ON_UNAVAILABILITY_RED_BLOCK="//div[@class='fc-bgevent unavailabilityCalendarEvent']";
	public final String COACHES_SCHEDULE_CALENDER_SCHEDULE_EDIT_OR_REMOVE_UNAVAILABLITY="//div[@class='popover-inner']//a[text()='Edit / Remove Unavailability']";
	public final String COACHES_SCHEDULE_CALENDER_SCHEDULE_REMOVE_EVENT_TEXT="//label[contains(text(),' Remove this event')]";
	public final String REAL_APPEAL_COACH_ASSIGN_SUBSTITUTE_COACH_SAVE_BUTTON="//button[text()='Save']";
	public final String INSUFFICIENT_CLASS_MEMBER_MOVE_TO_POPUP_OK_BUTTON="//button[text()='OK']";
	public final String OPS_ADMIN_PROVIDERS_LECTURE_SESSION_TIME_SLOT_NEXT_PAGE_NAVIGATOR="//div[@class='time-select-nav-control']//span[2]//span";
	public final String OPS_ADMIN_PROVIDERS_LECTURE_SESSION_POPUP_NEW_LECTURE_SESSION_OPTION="//div//a[text()='Create New Lecture']";
	public final String OPS_ADMIN_PROVIDERS_LECTURE_SESSION_DESCRIPTION_TEXTBOX="//body//div[@class='page-content ng-scope']//div//div[6]//input";
	public final String OPS_ADMIN_PROVIDERS_LECTURE_SESSION_TITLE_TEXTBOX="//body//div[@class='page-content ng-scope']//div//div[5]//input";
	public final String OPS_ADMIN_PROVIDERS_LECTURE_SESSION_CONTINUE_BUTTON="//body//div[@class='page-content ng-scope']//div//div[7]//button[text()='Continue']";
	public final String OPS_ADMIN_PROVIDERS_LECTURE_SESSION_PROGRAM_NAME_DROPDOWN="//body//div[@class='page-content ng-scope']//div//div[3]//select";
	public final String COACHES_SCHEDULE_CALENDER_SCHEDULE_UNAVAILABLITY_RED_AREA="//table/tbody//tr//td[7]/div//div[@class='fc-bgevent unavailabilityCalendarEvent']";
	public final String COACH_CALENDER_NEXT_PAGE_NAVIGATOR="//button[@class='fc-next-button fc-button fc-state-default fc-corner-right']";

	
	/**
	 * Bharath Kumar. M
	 * 17/May/2017
	 * modified:
	 * Object repositories for yopmail and guerrilamail
	 */
	public final String  YOP_EMAIL_TEXTBOX="//input[@id='login']"; 
	public final String YOP_EMAIL_CHECK_INBOX="//input[@type='submit']"; 
	public final String YOP_EMAIL_IFRAME="//iframe[@id='ifmail']";
	public final String YOPMAIL_IFRAME2="ifmail";
	public final String NOTIFICATION_EMAIL_YOP="//div[text()='The email address on file for your RA - Real Appeal account has been changed.']";
	public final String RESET_YOUR_SECURITY_QA="//div[contains(text(),'To reset the security qa for your RA')]";
	public final String RESET_YOUR_SECURITY_LINK="//div[contains(.,'Click here to reset your security QA')]/a";
	public final String RESET_YOUR_SECURITY_QUESTION_PLEASE_SELECT_QA_LABEL="//div[contains(text(),'Reset Security Question')]/following-sibling::div/p[contains(text(),'Please select any security question and an answer:')]";
	public final String SECURITY_QA_RESET_OPTION_DROPDOWN="//label[contains(text(),'Security Question')]/following-sibling::div/select[@id='securityQuestion']";
	public final String SECURITY_QA_ANSWER_BOX="//input[@name='secureAnswer' and @type='text']";
	public final String SUBMIT_BUTTON_RESET_PASSWORD="//button[@id='formSubmit_memberResetPass']";
	public final String	SECURITY_QA_RESET_PAGE_QA_SUCCESSFULLY_ADDED_MESSAGE="//b[contains(text(),'Your Security Question has been successfully updated')]";
	public final String GUERRILLA_MAIL_DROPDOWN="//select[@id='gm-host-select']";
	public final String GUERRILLA_MAIL_TEXTBOX="//span//input[@type='text']";
	public final String GUERRILLA_MAIL_SET_BUTTON="//button[contains(text(),'Set')]";
	public final String GUERRILLA_MAIL_SECURITY_EMAIL="//td[contains(.,'Reset Your Security QA')]";
	public final String GUERRILLA_MAIL_EDIT_BUTTON="//span[@id='inbox-id']";
	public final String NOTIFICATION_EMAIL_GUERRILLA="//td[contains(text(),'Updated Your Email Address')]";
	public final String GUERRILLA_RESET_EMAIL="//td[contains(text(),'Reset')]";
	
	
	/**
	 * VIGNESHRAJ.M
	 * 17/May/2017
	 * modified:
	 * Object repositories Total number of messages in messages subtab for PA
	 */
	public final String TOTAL_NUMBER_OF_MESSAGES="//div[@class='ng-scope' and @ng-show='!noInboxMessages']";

}