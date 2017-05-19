package com.zillion.qa.realappealprogramadmin;

public interface OR {

	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   21/Apr/16
	 * Modified Date:
	 * Description :   Create a OR for Login and Logout Funtionality RA program Admin.
	 * Ticket ID :
	 * Required Inputs :  Username and Password
	 *
	 */
	public final String RA_PROGRAM_ADMIN_USERNAME="//input[@placeholder='Email Address']";
	public final String RA_PROGRAM_ADMIN_PASSWORD="//input[@placeholder='Password']";
	public final String RA_PROGRAM_ADMIN_LOGIN="//button[@id='login_button']";
	public final String RA_PROGRAM_ADMIN_REAL_APPEAL_LOGO="//a[@id='orgNavbarBrand']";
	public final String RA_PROGRAM_ADMIN_SIGNOUT_LINK="//div[@class='text-right inline-table-cell']//i";
	public final String RA_PROGRAM_ADMIN_SIGNOUT_BUTTON="//a[@id='navbar_hfSysUserSettings_SignOut']";
	public final String RA_PROGRAM_ADMIN_SIGNOUT_VERIFY="//a[@id='sign_in_again_link']";
	public final String RA_PROGRAM_ADMIN_UNAPPROVED_COUNT="//ul[@class='list-inline']/li[2]/span";
	public final String RA_PROGRAM_ADMIN_UNAPPROVED_SUB_TAB="//div[@id='navbarPrimarySubNav']/ul/li/a[text()='unapproved']";
	public final String RA_PROGRAM_ADMIN_UNAPPROVED_HEADER="//ul[@class='list-inline']/li/span[text()='Unapproved']";
	public final String RA_PROGRAM_ADMIN_UNAPPROVED_TABLE_FIRST_CHECKBOX="//tbody[@id='unapprovedClasses_TableBody']/tr[1]/td[11]/input";
	public final String RA_PROGRAM_ADMIN_UNAPPROVED_TABLE_SECOND_CHECKBOX="//tbody[@id='unapprovedClasses_TableBody']/tr[2]/td[11]/input";
	public final String UNAPPROVED_ACTION_BTN="//span[@class='fa fa-angle-down']/parent::a[@class='btn btn-primary dropdown-toggle']";
	public final String UNAPPROVED_APPROVE_LINK="//ul[@class='dropdown-menu dropdown-menu-right']/li/a";
	public final String RA_PROGRAM_ADMIN_APPROVED_SUB_TAB="//div[@id='navbarPrimarySubNav']/ul/li/a[text()='approved']";
	public final String UNAPPROVED_TABLE_LIST_EMPTY="//td[text()='No record to display']";
	public final String INSUFFICIENT_PROGRAM_MEMBER_PROFILE_ENROLLMENT_STATUS="//span[text()='Enrollment Status:']/following::span/a";
	public final String INSUFFICIENT_PROGRAM_MEMBER_PROFILE_EMAIL="//input[@id='username']";
	public final String UNAPPROVED_PROGRAM_LINK="//a[@id='unapprovedClasses_Item_User_Link0']/i";
	public final String INSUFFICIENT_PROGRAM_MEMBER_NAME="//tr[@id='insufficientClasses_innerTableBodyRow0']/td[1]/a]";
	public final String INSUFFICIENT_PROGRAM_SCHEDULE_CLASS="//tbody[@id='insufficientClasses_tbody']//td[text()='Scheduled']";
	public final String INSUFFICIENT_PROGRAM_SCHEDULE_CLASS_PREPONE_POSTPONE_UNAVAILABLE="//tbody[@id='insufficientClasses_tbody']//td[text()='Scheduled']/following-sibling::td/following-sibling::td/following-sibling::td/following-sibling::span";
	public final String INSUFFICIENT_PROGRAM_INSUFFICIENT_CLASS="//tbody[@id='insufficientClasses_tbody']//td[text()='Insufficient']";
	public final String INSUFFICIENT_PROGRAM_INSUFFICIENT_CLASS_PREPONE_AVAILABLE="//tbody[@id='insufficientClasses_tbody']//td[text()='Insufficient']/following-sibling::td/following-sibling::td/following-sibling::td/span";
	public final String INSUFFICIENT_PROGRAM_INSUFFICIENT_CLASS_POSTPONE_AVAILABLE="//tbody[@id='insufficientClasses_tbody']//td[text()='Insufficient']/following-sibling::td/following-sibling::td/following-sibling::td/span[2]";
	public final String RA_MEMBER_PASSWORD="//input[@id='navbarLoginFormPassword']";
	public static String RA_MEMBER_LOGIN_BUTTON ="//button[@id='navbarLoginFormSubmitBtn']";
	public final String RA_COACH_PASSWORD="//input[@placeholder='Password']";
	public final String COACH_UNAVAILABILITY_START_DATE="//input[@id='eventStartDate']";
	public final String COACH_TIMESLOT_CHECK_FROM_START="//div[@class='xdsoft_calendar']//tr//td[contains(@class,'xdsoft_date xdsoft_day') and @data-date='";
	public final String COACH_TIMESLOT_CHECK_FROM_END="']";
	public final String COACH_TIMESLOT_CHECK_FROM_END_AND_DISABLED_DATE="']//following-sibling::td[contains(@class,'xdsoft_date xdsoft_other_month') and @data-date='1']";
	public final String COACH_TIMESLOT_CALENDER_ONE_DATE="//div[@class='xdsoft_calendar']//tr//td[contains(@class,'xdsoft_date xdsoft_day') and @data-date='1']";
	public static String COACHES_LOGIN_BUTTON ="//button[@id='login_button']";
	public static String RA_MEMBER_VERIFY_AVIALABLE_TIME_START ="//ul[@class='dropdown-menu schedule-default-menu']/li/a[text()='";
	public static String RA_MEMBER_VERIFY_AVIALABLE_TIME_END="']";
	public static String INSUFFICIENT_CLASSES_ROW="//tbody[@id='insufficientClasses_tbody']/tr[";
	public static String SCHEDULED_STATUS_IN_INSUFFICIENT_CLASS="]//td[contains(@id,'insufficientClasses_tdStatus')]";
	public static String INSUFFICIENT_CLASS_NEXT_BUTTON="//button[@id='insufficientNextButton']";
	public static String INSUFFICIENT_CLASS_PAGINATION="//span[@class='pagination-info']/span[contains(@class,'ng-binding')]";
	public static String INSUFFICIENT_MEMBER_NAME="//a[contains(@href,'members/profile')]";
	public static final String PROVIDER_USER_SESSION_POPUP="//p[contains(text(),'user is already active in another browser')]//following-sibling::button";

	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   21/Apr/16
	 * Modified Date:
	 * Description :   Create a OR for for select Last month availability  Date
	 * Ticket ID :
	 * Required Inputs :  Username and Password
	 *
	 */
	public static String INSUFFICIENT_CLASS_START_DATE_TEXTBOX="//input[@id='insufficientClassesClass_StartDate']";
	public static String INSUFFICIENT_CLASS_START_DATE_PREVIOUS_MONTH_ARROW="//div[@class='xdsoft_datetimepicker xdsoft_ xdsoft_noselect '][2]//button[@class='xdsoft_prev']";
	public static String UNAPPROVED_CLASS_PROGRAM_ASC_DESC_ARROW="//table[@id='unapprovedClassesTable']/thead/tr/th[2]/span[2]";
	public final String RA_COACH_MEMBERS_TAB="//div[@class='navbar-item-wrapper']//span[text()='Members']";
	public final String RA_COACH_MEMBERS_SORT_DROPDOWN="//select[@id='filterMemberList']";
	public final String RA_COACH_MEMBERS_SEARCH_TEXTBOX="//input[@id='searchBar']";
	public final String RA_COACH_MEMBERS_SEARCH_BUTTON="//button[@id='submit-search']";
	public final String RA_COACHES_SCHEDULE_CALENDER_SCHEDULE_SAVE_BUTTON="//button[@id='saveAvailability']";
	public final String RA_COACHES_SCHEDULE_CALENDER_SCHEDULE_POPUP_TEXT="//p[contains(text(),'Your schedule update was successful')]";
	public final String RA_COACHES_SCHEDULE_CALENDER_SCHEDULE_POPUP_OK_BUTTON="//button[contains(text(),'OK')]";
	public final String RA_COACHES_EVENT_START_CANNOT_BE_MORE_THAN_HR_POPUP_ALERT_MSG="//p[contains(text(),'Event start cannot be more than an hour ago')]";
	public final String RA_COACHES_EVENT_START_CANNOT_BE_MORE_THAN_HR_POPUP_ALERT_MSG_OK_BUTTON="//button[contains(text(),'OK')]";
	public final String RA_PROGRAM_ADMIN_WEEK_DOWN_ARROW="//span[@id='unapprovedClassesFilterDown_WeekNum']";

	public final String NO_MATCHING_CLASSES_FOUND_OK_BUTTON="//button[contains(text(),'OK')]";
	public final String CLASS_SELECTION_PREFERENCES_SUBMIT_BUTTON="//button[@id='submitSessionChangeUnapproved']";
	public final String CLASS_SELECTION_PREFERENCES_WEEK_DROPDOWN="//select[@id='classroomInterval']";

	public final String NEW_UNREAD_MESSAGE_LINK="//tr//th[text()='Subject']/parent::tr/parent::thead/following-sibling::tbody/tr[1]//td[3]//a";
	public final String COACHES_DASHBOARD_TAB="//div[@class='navbar-item-wrapper']//span[text()='Dashboard']";

	//public final String RA_MEMBER_CHANGE_CLASS_ASSIGN="//button[contains(text(),'Assign')]";

	public final String RA_MEMBER_CHANGE_CLASS_ASSIGN="//button[contains(text(),'Assign')]";
	public final String OPS_SESSION_GUIDE_FULLSCREEN="//button[@id='presentationMode']";
	public final String OPS_SESSION_GUIDE_AUTOMATICZOOM="//option[text()='Automatic Zoom' and @selected='selected']";
	public final String OPS_SESSION_GUIDE_PAGINATION="//input[@id='pageNumber']/following-sibling::span[@id='numPages']";
	public final String OPS_SESSION_GUIDE_PRINT="//button[@id='print']";
	public final String OPS_SESSION_GUIDE_DOWNLOAD="//button[@id='download']";
	public final String OPS_SESSION_GUIDE_TOGGLE_SLIDEBAR="//button[@id='sidebarToggle']";
	public final String OPS_SESSION_PAGENUMBER_INPUT="//input[@id='pageNumber']"; //space
	public final String OPS_SESSION_GUIDE_SECONDARY_TOOLBAR="//button[@id='secondaryToolbarToggle']";
	public final String OPS_SESSION_GUIDE_LAST_PAGE="//button[@id='lastPage']";
	public final String OPS_SESSION_GUIDE_FIRST_PAGE="//button[@id='firstPage']";
	public final String OPS_SESSION_GUIDE_ROTATE_CLOCKWISE="//button[@id='pageRotateCw']";
	public final String OPS_SESSION_GUIDE_ROTATE_ANTICLOCKWISE="//button[@id='pageRotateCcw']";
	public final String OPS_SESSION_GUIDE_DOCUMENT_PROPERTIES="//button[@id='documentProperties']";
	public final String OPS_SESSION_GUIDE_DOCUMENT_PROPERTIES_POPUP_CLOSE="//button[@id='documentPropertiesClose']";
	public final String OPS_SESSION_PAGENUMBER_INPUT_CHROME="//input[@id='input']";
	public final String OPS_SESSION_GUIDE_ROTATE_CLOCKWISE_CHROME="//iron-icon[@id='icon']";
	//	public final String OPS_SESSION_GUIDE_DOCUMENT_PROPERTIES_POPUP_CLOSE="//button[@id='documentPropertiesClose']";
	public final String OPS_SESSION_NEXT_NAVIGATION_PAGE="//button[@id='nxt_Button_MemberRequest']";
	public final String RA_HFOPS_SESSION_SHOW_RATING_DISABLED_ICON="//button[@id='nxt_Button_MemberRequest']";
	public final String SCHEDULED_STATUS="//td[contains(text(),'Scheduled')]";
	public final String RA_PROGRAMADMIN_MEMBERS_TAB_ARE_YOU_SURE_WANT_TO_CHANGE_CLASS_YES_RADIO_BUTTON="//input[@id='confirmYes']";
	public final String OPS_ADMIN_PROVIDERS_LECTURE_SESSION_POPUP_OK_BUTTON="//button[text()='OK']";
	public final String MEMBER_PROFILE_CHANGE_CLASS_PREFERENCES_SWITCH_CLASS_SUCCESS_OK_BUTTON="//p[text()='Switch Classes Successful']/following-sibling::div//button";
	public final String MEMBER_COUNT="//span[contains(text(),'of')]/following::span";


	/**
	 * Name : Bharath Kumar
	 * Created Date:   03/May/17
	 * Modified Date:
	 */

	public final String RA_MEMBER_CHANGE_CLASS_UNAPPROVED_ASSIGN="//td[text()='Unapproved']/following::td[6]/button[text()='Assign']";
	public final String RA_MEMBER_CHANGE_CLASS_INSUFFICIENT_ASSIGN="//td[text()='Insufficient']/following::td[6]/button[text()='Assign']";
	public final String RA_MEMBER_DASHBOARD_RESCHDULE_BUTTON="//button[@id='memberSessionsCard_ChangemissedOneOnOneSession_btn']";
	public final  String  RA_MEMBERLIVESESSION_SCHEDULE_BUTTON ="//button[@id='memberSessionsCard_ScheduleOneOnOneSessions_btn' and text()='schedule']";
	public final String MEMBER_SESSION_CHANGE_BUTTON="//*[@id='memberSessionsCard_ChangeOneOnOneSessions_btn']";
	public final String MEMBER_CANCEL_SESSION_BUTTON="//button[@id='cancel']";
	public final String MEMBER_SESSION_POPUP_OK="//button[text()='OK']";
	public final String REAL_APPEAL_MEMBER_DASHBOARD_TAB="//li[text()='dashboard']";
	public final  String  RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_AVAILABLE_BUTTON_AVAILABLE_TIME ="//div[@id='timeSelection']//button[@ng-disabled='false']/following-sibling::ul/li[2]/a";
	public final  String  RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_CONFIRM_BUTTON ="//button[@id='btnSchedule']";
	public final  String  RA_1ON1_LIVE_SESSION_MEMBER_TIMESELECTION_AVAILABLE_BUTTON ="//div[@id='timeSelection']//button[@ng-disabled='false']";

	public final String MEMBER_PROFILE_CHANGE_CLASS_PREFERENCES_SWITCH_CLASS_SUCCESS_TEXT="//p[text()='Switch Classes Successful']";
	public final String RA_COACH_ASSIGN_SWITCH_SUCCESSFUL_OK_BUTTON="//p[contains(text(),'Switch Classes Successful')]/following::div/button[contains(text(),'OK')]";


}
