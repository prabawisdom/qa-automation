package com.zillion.qa.coaches;
public interface OR
{
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   14/12/2015
	 * Modified Date:
	 * Description :   Create a common method for Login Coaches URL for User-1
	 * Ticket ID :    ZA-41
	 * Required Inputs :  No Inputs Required
	 * Purpose :   Logout from the CMS platform
	 */
	public static String COACHES_LOGIN_PAGE_LOGO_REF ="//div[@id='orgLoginBoxLogo']";
	public static String COACHES_USERNAME_1 ="//input[@type='email']";
	public static String COACHES_PASSWORD ="//input[@type='password']";
	public static String COACHES_LOGIN_BUTTON ="//button[@id='login_button']";
	public static String COACHES_LOGIN_USERNAME_REQUIRED ="//span[text()='Your email address is required.']";
	public static String COACHES_LOGIN_PASSWORD_REQUIRED ="//span[text()='Your password is required.']";
	public static String COACHES_LOGO ="//a[@id='orgNavbarBrand']";
	public static String COACHES_SIGNOUT_LINK_BUTTON ="//div[@class='text-right inline-table-cell']//i";
	public static String COACHES_LOGIN_DASHBOARD_REF ="//div[@class='navbar-item-wrapper']//span[text()='Dashboard']";
	public static String COACHES_LOGIN_PROGRAMS_REF ="//div[@class='navbar-item-wrapper']//span[text()='Programs']";
	public static String COACHES_LOGIN_CLASSES_REF ="//div[@class='navbar-item-wrapper']//span[text()='Classes']";
	public static String COACHES_LOGIN_MEMBERS_REF ="//div[@class='navbar-item-wrapper']//span[text()='Members']";
	public static String COACHES_LOGIN_MESSAGES_REF ="//div[@class='navbar-item-wrapper']//span[text()='Messages']";
	public static String COACHES_LOGIN_UPCOMING_SESSIONS_TEXT_REF ="//div[@class='page-header-counter']//span[text()='Upcoming Sessions']";
	public static String COACHES_LOGIN_NEW_MESSAGES_TEXT_REF ="//div[@class='page-header-counter']//span[text()='New Messages']";
	public static String COACHES_LOGIN_VIEW_FULL_SCHEDULE_TEXT_REF ="//div[@class='system-user-toolbar-inner']//a[text()='view full schedule']";
	public static String COACHES_SIGNOUT_BUTTON ="//div[@class='text-right inline-table-cell']//i/parent::span/following-sibling::ul//li[*]/a[@id='navbar_hfSysUserSettings_SignOut']";
	public static String COACHES_LOGIN_VIEW_FULL_MESSAGES_TEXT_REF ="//div[@class='system-user-toolbar-inner']//a[text()='view all messages']";
	public static String COACHES_LOGIN_POWERED_BY_ZILLION_LOGO_REF ="//img[@class='powered-logo']";
	public static String COACHES_LOGIN_PRIVACY_POLICY_TEXT_REF ="//div[@class='copyright-text']//a[text()='Privacy Policy']";
	public static String COACHES_LOGIN_TERMS_OF_USE_TEXT_REF ="//div[@class='copyright-text']//a[text()='Terms of Use']";
	public static String COACHES_SIGNOUT_YOU_HAVE_SIGNED_OUT_TEXT_REF ="//div[@id='loggedoutPageContent']//h1[text()='You have signed out.']";
	public static String COACHES_SIGNOUT_SIGN_IN_AGAIN_TEXT_REF ="//div//a[text()='Sign in again.']";
	public static String COACHES_MEMBER_LIST_COUNT="//span[@class='page-header-counter-label backend-page-title align-middle ng-binding']";
	public static String COACHES_MEMBER_LIST_HEADER_NAME="//span[contains(text(),'Name')]";
	public static String COACHES_MEMBER_LIST_HEADER_EMAIL="//span[contains(text(),'Email')]";
	public static String COACHES_MEMBER_LIST_HEADER_PROGRAM="//th//span[contains(text(),'Program')]";
	public static String COACHES_MEMBER_LIST_HEADER_CLASS="//th//span[contains(text(),'Class')]";
	public static String COACHES_MEMBER_LIST_HEADER_COACH="//th//span[contains(text(),'Coach')]";
	public static String COACHES_MEMBER_LIST_HEADER_ACCOUNT_STATUS="//th//span[contains(text(),'Account')]";
	public static String COACHES_MEMBER_LIST_HEADER_ONBOARDING_STATUS="//th//span[contains(text(),'Onboarding')]";
	public static String COACHES_MEMBER_LIST_HEADER_CLIENT="//th//span[contains(text(),'Client')]";
	public static String COACHES_MEMBER_LIST_HEADER_MEMBERID="//th//span[contains(text(),'Member ID ')]";
	public final String COACHES_SORT_DROPDOWN="//select[@id='filterMemberList']";
	public final String COACHES_SORT_DROPDOWN_ALL_OPTION="//select[@id='filterMemberList']//option[text()='All']";
	public final String COACHES_SORT_DROPDOWN_NAME_OPTION="//select[@id='filterMemberList']//option[text()='Name']";
	public final String COACHES_SORT_DROPDOWN_EMAIL_OPTION="//select[@id='filterMemberList']//option[text()='Email']";
	public final String COACHES_SORT_DROPDOWN_CLASS_OPTION="//select[@id='filterMemberList']//option[text()='Class']";
	public final String COACHES_SORT_DROPDOWN_ONBOARDING_STATUS_OPTION="//select[@id='filterMemberList']//option[text()='Onboarding Status']";
	public final String COACHES_SORT_DROPDOWN_CLIENT_OPTION="//select[@id='filterMemberList']//option[text()='Client']";
	public final String COACHES_SORT_DROPDOWN_ID_OPTION="//select[@id='filterMemberList']//option[contains(text(),'ID')]";
	public final String COACHES_SEARCH_TEXTBOX="//input[@id='searchBar']";
	public final String COACHES_SEARCH_SUBMIT_BUTTON="//button[@id='submit-search']";
	public final String COACHES_SEARCH_CLEAR_BUTTON="//a[@id='getMembers_search_clear']";
	public final String COACHES_COLUMN_NAME_TEXT="//tbody[@id='membersList']/tr/td[1]";
	public final String COACHES_COLUMN_EMAIL_TEXT="//tbody[@id='membersList']/tr/td[2]";
	public final String COACHES_COLUMN_ONBOARDING_TEXT="//tbody[@id='membersList']/tr/td[7]";
	public final String COACHES_COLUMN_CLIENT_TEXT="//tbody[@id='membersList']/tr/td[8]";
	public final String COACHES_COLUMN_MEMBER_ID_TEXT="//tbody[@id='membersList']/tr/td[9]";
	public final String COACHES_MEMBERS_TAB="//a[@id='navMembersLink']//span[contains(text(),'Members')]";
	public final String COACH_NOSESSION_MSG="//td[contains(text(),'There are currently no sessions')]";
	/**
	 * SURESH.V
	 * 3rd/Feb/16
	 */
	public final String PRACTITIONER_MY_PROFILE_EDIT_BUTTON="//div[@class='edit-toggler-backend pull-right']/i";
	public final String PRACTITIONER_MY_PROFILE_FIRSTNAME_EDIT_TEXTBOX="//input[@id='firstName']";
	public final String PRACTITIONER_MY_PROFILE_LASTNAME_EDIT_TEXTBOX="//input[@id='lastName']";
	public final String PRACTITIONER_MY_PROFILE_EDIT_AND_SAVE_BUTTON="//input[@id='submitBtnProfileForm']";
	public final String PRACTITIONER_PATIENT_NAME="//tbody//a[contains(@href,'profile')]";
	public final String NEW_PATIENT_FULFILLMENT_STATUS_NOT_INITIATED="//span[text()='Fulfillment Status:']/following-sibling::span[text()='Fulfillment Not Initiated']";
	public final String SUCCESS_MESSAGE_OKBUTTON="//div[@id='spinnerSuccess']/button[text()='OK']";
	public final String PHONE_NUMBER_TEXTBOX="//input[@id='phone']";
	public final String ADDRESSONE_TEXTBOX="//input[@id='street']";
	public final String CITY_TEXTBOX="//input[@id='city']";
	public final String ZIP_CODE_TEXTBOX="//input[@id='postalCode']";
	public final String NICKNAME_TEXTBOX="//input[@id='nickname']";
	public final String SELECT_STATE="//select[@id='state']";
	public final String BIO_EDIT_BUTTON="//div[text()='Bio']/parent::div//div//edit-toggler /div/i";
	public final String BIO_TEXT_AREA="//textarea[@id='bio']";
	public final String BIO_TEXT_AREA_SAVE_BUTTON="//input[@id='submitBtnBioForm']";
	public final String BIO_TEXT_AREA_SAVE_SUCCESS_OK="//div[@id='spinnerSuccess']/button";
	public final String PHONE_EXTENSION="//input[@id='extension']";
	public final String PHONE_EXTENSION_ALERT_MSG_NUMBERS_ONLY="//span[contains(text(),'Phone extension can only be numbers')]";
	public final String EMAIL_EDIT_BUTTON="//div[@id='credentialsPanel']//div[text()='Credentials']/parent::div//div//edit-toggler//div//i";
	public final String EMAIL_EDIT_TEXTBOX="//div//input[@id='email']";
	public final String PROVIDER_URL_ADD_PATIENT_ADD_MONTH_FIELD="//select[@id='dobMonth']";
	public final String PROVIDER_URL_ADD_PATIENT_ADD_DAY_FIELD="//select[@id='dobDay']";
	public final String PROVIDER_URL_ADD_PATIENT_ADD_YEAR_FIELD="//select[@id='dobYear']";
	public final String NEW_UNREAD_MESSAGE_LINK="//tr//th[text()='Subject']/parent::tr/parent::thead/following-sibling::tbody/tr[1]/td[2]/a";
	public final String COACHES_DASHBOARD_TAB="//div[@class='navbar-item-wrapper']//span[text()='Dashboard']";
	public final String COACHES_DASHBOARD_NO_NEW_MESSAGES="//tbody/tr/td[text()='No New Messages']";
	public final String ALL_SESSION_PAGE_COUNTER_NUMBER="//span[@class='page-header-counter-number align-middle ng-binding']";
	public final String UPCOMING_SESSION_DATE_FIELD="//tbody[@id='sessionClasses_tableBody']/tr/td[1]";
	public final String UPCOMING_SESSION_TIME_FIELD="//tbody[@id='sessionClasses_tableBody']/tr/td[2]";
	public final String UPCOMING_SESSION_PROGRAM_FIELD="//tbody[@id='sessionClasses_tableBody']/tr/td[3]";
	public final String UPCOMING_SESSION_SESSION_TYPE_FIELD="//tbody[@id='sessionClasses_tableBody']/tr/td[5]";
	public final String UPCOMING_SESSION_STATUS_FIELD="//tbody[@id='sessionClasses_tableBody']/tr/td[8]";
	/**
	 * Name :    Leena P.
	 * Created Date:   11/July/16
	 * Modified Date:
	 * Description :   Create a OR for coach schedules 1on1 session
	 * Ticket ID :
	 * Required Inputs :
	 */
	public final String  YOP_EMAIL_TEXTBOX="//input[@id='login']";
	public final String YOP_EMAIL_CHECK_INBOX="//input[@type='submit']";
	public final String YOP_EMAIL_IFRAME="//html/body/div[5]/table/tbody/tr[1]/td/table/tbody/tr/td[1]/table/tbody/tr[2]/td/iframe";
	public final String GUERRILLA_MAIL_DROPDOWN="//select[@id='gm-host-select']";
	public final String GUERRILLA_MAIL_TEXTBOX="//span//input[@type='text']";
	public final String GUERRILLA_MAIL_SET_BUTTON="//button[contains(text(),'Set')]";
	public final String GUERRILLA_MAIL_EDIT_BUTTON="//span[@id='inbox-id']";
	public final String ORBERA_COACH_1on1_MAIL="//*[@id='m1']//span[contains(text(),'1-on-1 Session Rescheduled')]";
	public final String ORBERA_COACH_1on1_MAIL_GR="//tbody//tr//td[3]";
	public final String ORBERA_COACH_1on1_CONTENT="//div[contains(text(),'The 1-on-1 session you were scheduled')]";
	public final String YOP_EMAIL_IFRAME1="//html/body/div[5]/table/tbody/tr[1]/td/table/tbody/tr/td[1]/table/tbody/tr[2]/td/iframe";
	public final String YOP_EMAIL_IFRAME2="//html/body/div[5]/table/tbody/tr[1]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td[1]/iframe";
	public final String ORBERA_COACH_1on1_CANCELlATION_MAIL="//*[@id='m1']//span[contains(text(),'Session Canceled')]";
	public final String ORBERA_COACH_1on1_CANCELLATION_CONTENT="//div[contains(text(),'has been canceled')]";
	public final String ORBERA_COACH_1on1_MAIL_SCHEDULED="//*[@id='m1']//*[contains(text(),'Session Scheduled: 1-on-1')]";
	public final String ORBERA_COACH_1on1_CONTENT_SCHEDULED="//div[contains(@id,'mail')]//*[contains(text(),'scheduled to conduct a 1-on-1 session')]";
	public final String GUERRILLA_MAIL_INBOX_BUTTON="//a[@id='back_to_inbox_link']";
	public final String GUERRILLA_MAIL_EMAIL_CHECKBOX="//tbody//tr/td[1]//input[@type='checkbox']";
	public final String GUERRILLA_MAIL_DELETE_BUTTON="//input[@id='del_button']";
	/**
	 * Name :    Leena P.
	 * Created Date:   11/July/16
	 * Modified Date:
	 * Description :   Create a OR for coach schedules 1on1 session
	 * Ticket ID :
	 * Required Inputs :
	 */
	public final String ORBERA_COACH_SUBSTITUTE_MAIL="//span[contains(text(),'assigned as a substitute coach')]";
	public final String ORBERA_COACH_SUBSTITUTE_EMAIL_CONTENT="//div[contains(text(),'You have been assigned as the substitute coach')]";
	public final String ORBERA_COACH_LECTURE_MAIL="//div[@id='m1']//*[contains(text(),'Group Session Confirmation')]";
	public final String ORBERA_PA_LECTURE_MAIL="//span[contains(text(),'Session Scheduled: Lecture')]";
	public final String ORBERA_COACH_LECTURE_CONTENT="//div[contains(text(),'Congratulations!')]";
	public final String ORBERA_PA_LECTURE_CONTENT="//div[contains(text(),'Congratulations! Your lecture session has been scheduled successfully.')]";
	public final String SELECT_ALL_BUTTON="//a[@id='e0']";
	public final String YOPMAIL_SELECT_FIRST_MESSAGE="//a[contains(text(),'This message')]";
	public final String DELETE_ALL_BUTTON="//a[contains(text(),'Delete')]//span";
	public final String SELECTED_MSGS="//a[contains(text(),'Selected messages')]";
	public final String ORBERA_COACH_LECTURE_CANCELlATION_MAIL="//div[@id='m1']//span[contains(text(),'Session Canceled')]";
	public final String ORBERA_COACH_LECTURE_CANCELLATION_CONTENT="//div[contains(text(),'has been canceled')]";
	public static String  PA_WC_MEMBER_POST_ACTIVE_ABCPRACTICE="//tr//td[text()='ABC Practice']//preceding-sibling::td[contains(text(),'Active')]//preceding-sibling::td[contains(text(),'Post')]//preceding-sibling::td//a";
	public final String NEXT_BUTTON="//button[@id='nxt_Button_Members']";
}