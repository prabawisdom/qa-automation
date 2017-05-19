package com.zillion.qa.opsadmin;
public interface OR {
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   17/Dec/15
	 * Modified Date:
	 * Description :   Create a OR for Login and Logout Funtionality OPS/Admin
	 * Ticket ID :
	 * Required Inputs :  Username and Password
	 */
	public final String OPS_ADMIN_USERNAME="//input[@placeholder='Email Address']";
	public final String OPS_ADMIN_PASSWORD="//input[@placeholder='Password']";
	public final String OPS_ADMIN_LOGIN="//button[@id='login_button']";
	public final String OPS_ADMIN_ZILLION_LOGO="//a[@id='orgNavbarBrand']";
	public final String OPS_ADMIN_SIGNOUT_LINK="//div[@class='text-right inline-table-cell']//i";
	public final String OPS_ADMIN_SIGNOUT_BUTTON="//a[@id='navbar_hfSysUserSettings_SignOut']";
	public final String OPS_ADMIN_SIGNOUT_VERIFY="//a[@id='sign_in_again_link']";
	public final String OPS_ADMIN_PROVIDERS_TAB_ORG_TEXTBOX="//select[@id='userOrganization']";
	public static final String PROVIDER_USER_SESSION_POPUP="//p[contains(text(),'user is already active in another browser')]//following-sibling::button";
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   04/Jan/16
	 * Modified Date:
	 * Description :   Create a common method to verify all sessions in OPS admin
	 * Ticket ID :
	 * Required Inputs :  Username and Password
	 */
	public final String OPS_SESSION_MESSAGE="//td[text()='There are currently no sessions.']";
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   05/Jan/16
	 * Modified Date:
	 * Description :   Create a common method to select Date/Time for sessions in OPS admin
	 * Ticket ID :
	 * Required Inputs :
	 */
	public final String NOTES1="//table[@class='table system-user-table table-standard']//tr[1]//td//a";
	public final String NOTES_EDIT="//table[@class='table system-user-table table-standard']//a[text()='Edit']";
	public final String NOTESEDIT_TEXTAREA="//textarea[@id='coachnotes0']";
	public final String NOTES_APPLYCHANGES="//table[@class='table system-user-table table-standard']//li//button";
	public final String NOTES_SUCC_MESS="//div[@class='modal-content-notification']//button";
	public final String NOTES_NEWENTRY="//div[@class='toolbar-input-group']//button";
	public final String NOTES_SELECT_CATEGORY="//select[@id='notescat']";
	public final String NOTES_TEXTAREA="//textarea[@id='coachnotes']";
	public final String NOTES_SAVEENTRY="//button[text()='Save Entry']";
	public final String NOTES_CATEGORY_VALUE= "//option[text()='SESSION_NOTES']";
	public final String NOTES_NEW_SUCC= "//p[text()='Coach Internal Note Created Successfully.']/following-sibling::button";
	public final String NOTES_NEW_ERR= "//p[text()='Please Select Category.']/following-sibling::button";
	public final String NOTES_EDIT_SUCC= "//p[text()='Coach Note Updated Successfully.']/following::button";
	/**
	 * Name         :   Suresh.V
	 * Created Date :   14/Dec/15
	 * Modified Date:   01.Feb.2016
	 * Description  :   ADD patient/Member creation with full valid data and verification
	 */
	public final String PATIENT_HEADER_LABLE_IN_PRACTITIONER_URL="//span[text()='Patient List']";
	public final String PROVIDER_URL_SETTING_IMAGE="//span[@id='navbar_hfSysUserSettings']//i";
	public final String PROVIDER_URL_SIGNOUT_LINK="//a[@id='navbar_hfSysUserSettings_SignOut']";
	public final String PROVIDER_URL_ADD_PATIENT_BUTTON ="//button[@id='addNewMember']";
	public final String ADD_MEMBER_TEXT_INLEFT_CORNER="//span[text()='Add Member']";
	public final String CLOSE_ICON ="//a[@ng-click='close()']";
	public final String HFOPS_URL_ADD_MEMBER_LAST_STAGE="//button[text()='ADD MEMBER']";
	public final String PROVIDER_URL_ALERTMSG_1STNAME_REQUIRED="//p[text()='Your First Name Is Required.']";
	public final String PROVIDER_URL_ALERTMSG_LASTNAME_REQUIRED="//p[text()='Your Last Name Is Required.']";
	public final String PROVIDER_URL_ALERTMSG_EMAIL_REQUIRED="//span[text()='Your email is required.']";
	public final String PROVIDER_URL_ALERTMSG_CONFIRMEMAIL_REQUIRED="//span[text()='You must confirm your email address.']";
	public final String PROVIDER_URL_ALERTMSG_MONTH_REQUIRED="//span[text()='Month is required.']";
	public final String PROVIDER_URL_ALERTMSG_DATE_REQUIRED="//span[text()='Day is required.']";
	public final String PROVIDER_URL_ALERTMSG_YEAR_REQUIRED="//span[text()='Year is required.']";
	public final String PROVIDER_URL_ADD_PATIENT_FIRSTNAME_TEXTBOX="//input[@id='firstName']";
	public final String PROVIDER_URL_ADD_PATIENT_LASTNAME_TEXTBOX="//input[@id='lastName']";
	public final String PROVIDER_URL_ADD_PATIENT_EMAIL_TEXTBOX="//input[@id='email']";
	public final String PROVIDER_URL_ADD_PATIENT_CONFIRMEMAIL_TEXTBOX="//input[@id='confirmEmail']";
	public final String PROVIDER_URL_ADD_PATIENT_ADD_MONTH_FIELD="//select[@id='dobMonth']";
	public final String PROVIDER_URL_ADD_PATIENT_ADD_DAY_FIELD="//select[@id='dobDay']";
	public final String PROVIDER_URL_ADD_PATIENT_ADD_YEAR_FIELD="//select[@id='dobYear']";
	public final String PROVIDER_URL_ADD_PATIENT_ORGANIZATION_FIELD="//input[@id='selfOrganization']";
	public final String PROVIDER_URL_ADD_PATIENT_EMAIL_ADDRESS_NOTMATCH_MSG="//span[contains(text(),'email address do not match')]";
	public final String PROVIDER_URL_ADD_PATIENT_FINALSTAGE_BUTTON="//button[text()='ADD PATIENT']";
	public final String PROVIDER_URL_ADD_PATIENT_COUNT="//span[@class='page-header-counter-number align-middle ng-binding ng-scope']";
	public final String PROVIDER_URL_ADD_PATIENT_AGE_LIMIT="//span[contains(text(),'Oops! Sorry, members must be at least 18 years of age to participate in the program.')]";
	public final String FIRSTNAME_LABEL="//span[contains(text(),'First Name *')]";
	public final String LASTNAME_LABEL="//span[contains(text(),'Last Name *')]";
	public final String EMAIL_LABEL="//span[contains(text(),'Email *')]";
	public final String CONFIRMEMAIL_LABEL="//span[contains(text(),'Confirm Email *')]";
	public final String DOB_LABEL="//span[contains(text(),'Date of Birth *')]";
	public final String ORGANIZATION_LABEL="//span[contains(text(),'Organization *')]";
	public final String ORGANIZATION="//select[@name='organization']";
	public final String MEMBER_ADDED_SUCCESS_MSG="//h4[text()='Member has been added successfully.']";
	public final String MEMBER_ADDED_SUCCESS_OK_BUTTON="//div[@class='modal-content']//button[contains(text(),'OK')]";
	public final String MEMBER_COUNT="//span[@class='page-header-counter-number align-middle ng-binding ng-scope']";
	public final String ADD_MEMBER_BUTTON="//button[@id='addNewMember']";
	public final String ADD_MEMBER_CLEAR_BUTTON="//a[text()='Clear']";
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   19/01/2016
	 * Modified Date:
	 * Description :   Create a common method to current date time slot
	 * Ticket ID :
	 * Required Inputs :
	 */
	public final String OPS_ADMIN_TIMESLOT_CHECK_FROM_START="//td[contains(@class,'xdsoft_date xdsoft_current xdsoft_today xdsoft_weekend') and @data-date='";
	public final String OPS_ADMIN_TIMESLOT_CHECK_FROM_END="']";
	public final String OPS_ADMIN_TIMESLOT_CHECK_STARTDATE="//input[@id='unavailabilityStartDate']";
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   19/01/2016
	 * Modified Date:
	 * Description :   Create a common method to current date time slot
	 * Ticket ID :
	 * Required Inputs :
	 */
	public final String OPS_ADMIN_EARLY_MORNING_ENABLED_DATE="//div[@class='time-schedule-container animate']//div//button[@ng-disabled='false']//div[text()='Early Morning']";
	public final String OPS_ADMIN_EARLY_MORNING_DISABLED_DATE="//div[@class='time-schedule-container animate']//div//button[@ng-disabled='true']//div[text()='Early Morning']";
	public final String OPS_ADMIN_EARLY_EVENING_ENABLED_DATE="//div[@class='time-schedule-container animate']//div//button[@ng-disabled='false']//div[text()='Evening']";
	public final String OPS_ADMIN_EARLY_EVENING_DISABLED_DATE="//div[@class='time-schedule-container animate']//div//button[@ng-disabled='true']//div[text()='Evening']";
	public final String OPS_ADMIN_LATE_EVENING_ENABLED_DATE="//div[@class='time-schedule-container animate']//div//button[@ng-disabled='false']//div[text()='Late Evening']";
	public final String OPS_ADMIN_LATE_EVENING_DISABLED_DATE="//div[@class='time-schedule-container animate']//div//button[@ng-disabled='true']//div[text()='Late Evening']";
	public final String OPS_ADMIN_EARLY_MORNING_TIME="//div[text()='12:45AM ']";
	public final String OPS_ADMIN_EARLY_EVENING_TIME="//div[text()='5:30PM ']";
	public final String OPS_ADMIN_LATE_EVENING_TIME="//div[text()='10:00PM ']";
	public final String OPS_ADMIN_EVENING_TIME="//div[text()='6:30PM ']";
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   03/02/2016
	 * Modified Date:
	 * Description :   Create a common method to select apollo zado coach
	 * Ticket ID :
	 * Required Inputs :
	 */
	public final String OPS_ADMIN_APOLLO_COACH_LINK="//table//tbody//tr//td//a[contains(text(),'apollo zadocoach')]";
	public final String OPS_ADMIN_APOLLO_COACH_CALENDER="//table//tbody//tr//td//a[contains(text(),'apollo zadocoach')]/parent::td[1]/following-sibling::td[3]/a";
	public final String OPS_ADMIN_NEXT_PAGE_NAVIGATOR="//button[@id='previousPage_NextButton']";
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   03/02/2016
	 * Modified Date:
	 * Description :   Create a common method to choose created lecture session from Member portal
	 * Ticket ID :
	 * Required Inputs :
	 */
	public final String MEMBER_VIEW_SESSION_OF_LECTURE_SESSION_TIMING="//div[@id='lectureSessionModal']//div[1]//ul//li[contains(text(),'Sat')]/parent::ul/parent::div/following-sibling::div//ul//li[2]//span[contains(text(),' 06:00pm - 06:45pm')]";
	public final String MEMBER_VIEW_SESSION_OF_SATURDAY_TEXT="//div[@id='lectureSessionModal']//div[1]//ul//li[contains(text(),'Sat')]";
	public final String MEMBER_VIEW_SESSION_NEXT_PAGE_NAVIGATOR="//div//ul[@class='pagination']//li//span[text()='>']";
	public final String MEMBER_VIEW_SESSION_FOLLOWING_SIGNUP="//div[@id='lectureSessionModal']//div[1]//ul//li[contains(text(),'Sat')]/parent::ul/parent::div/following-sibling::div//ul//li[2]//span[contains(text(),' 06:00pm - 06:45pm')]/parent::li/parent::ul/parent::li/preceding-sibling::li/parent::ul/parent::div/following-sibling::div/button[text()='Sign up']";
	public final String MEMBER_VIEW_SESSION_SUCCESSFULL_POPUP_TEXT="//div[@class='modal-content']//div//div//div[contains(text(),'Session Scheduled Succesfully')]";
	public final String MEMBER_VIEW_SESSION_SUCCESSFULL_POPUP_OK_BUTTON="//div[@class='modal-content']//div//div//button[text()='OK']";
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   03/02/2016
	 * Modified Date:
	 * Description :   Create a common method to select Timeslot
	 * Ticket ID :
	 * Required Inputs :
	 */
	public final String TIME_SLOT_SATURDAY_HEADER="//div[@id='timeSelection']//div//div//span[contains(text(),'Saturday')]";
	public final String TIME_SLOT_SATURDAY_EVENING_BUTTON="//div[@id='timeSelection']//div//div//span[contains(text(),'Saturday')]/parent::div[1]/following-sibling::div[4]//button";
	public final String TIME_SLOT_NEXT_PAGE_NAVIGATOR="//div[@class='time-select-nav-control']//span[2]//span";
	/**
	 * Name :     SURESH V
	 * Created Date:   10/02/2016
	 * Modified Date:
	 * Required Inputs :
	 */
	public final String MEMBER_PROFILE_EDIT_TOGGLE="//div[@class='info-box-row']//div[text()='Member']/parent::div//div//edit-toggler//div//i";
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   03/02/2016
	 * Modified Date:
	 * Description :   Create a common method to Classes, session
	 * Required Inputs :
	 */
	public final String OPS_ADMIN_SESSION_DROPDOWN="//div[@class='dropdown select-dropdown']//button";
	public final String OPS_ADMIN_UPCOMING_OPTION="//a[contains(text(),'Upcoming')]";
	public final String OPS_ADMIN_PREVIOUS_OPTION="//a[contains(text(),'Previous ')]";
	public final String OPS_ADMIN_ALL_OPTION="//a[contains(text(),'All')]";
	public final String OPS_ADMIN_UPCOMING_START_DATE="//input[@id='startDt']";
	public final String OPS_ADMIN_ONE_MONTH_AHEAD_FROM="//b[contains(text(),'1 month ahead from:')]";
	public final String OPS_ADMIN_ONE_MONTH_BACK_FROM="//b[contains(text(),'1 month back from:')]";
	public final String OPS_ADMIN_PAGE_COUNTER_NUMBER="//div[@class='page-header-counter']//ul//li[2]";
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   03/02/2016
	 * Modified Date:
	 * Description :   Create a common method to cOACH UNAVAILABILITY START DATE
	 * Required Inputs :
	 */
	public final String COACH_UNAVAILABILITY_START_DATE="//input[@id='eventStartDate']";
	public final String COACH_TIMESLOT_CHECK_FROM_START="//div[@class='xdsoft_calendar']//table//tbody//tr//td[contains(@class,'xdsoft_date xdsoft_day') and @data-date='";
	public final String COACH_TIMESLOT_CHECK_FROM_MIDDLE="' and @data-month='";
	public final String COACH_TIMESLOT_CHECK_FROM_END="']";
	public final String COACH_TIMESLOT_CHECK_FROM_END_AND_DISABLED_DATE="']//following-sibling::td[contains(@class,'xdsoft_date xdsoft_other_month') and @data-date='1']";
	public final String COACH_TIMESLOT_CALENDER_NEXT_MONTH_BUTTON="//div//button[@class='xdsoft_next']";
	public final String COACH_TIMESLOT_CALENDER_ONE_DATE="//div[@class='xdsoft_calendar']//table//tbody//tr//td[contains(@class,'xdsoft_date xdsoft_day') and @data-date='1']";
	public final String COACH_TIMESLOT_CALENDER="//input[@id='eventStartDate']";
	/**
	 * Name :     VigneshRaj
	 * Created Date:   26Feb2016
	 * Modified Date:
	 */
	public final String CREATED_LECTURE_SESSION_5_00_6_15="//div[@class='fc-content']//div//span[text()='5:30 pm - 6:15 pm']";
	public final String CREATED_LECTURE_SESSION_6_00_6_45="//div[@class='fc-content']//div//span[text()='6:00 pm - 6:45 pm']";
	public final String NEXT_PAGE_NAVIGATOR="//div[@id='arshawFullCalendar']//div[@class='fc-button-group']//button[2]";
	public final String OPS_ADMIN_PROVIDERS_LECTURE_SESSION_SCHEDULE_CANCEL_SCHEDULE_OPTION="//div[@class='popover-inner']//div//a[text()='Cancel Lecture']";
	public final String OPS_ADMIN_PROVIDERS_LECTURE_SESSION_SCHEDULE_ASSIGN_SUBSTITUTE_OPTION="//div[@class='popover-inner']//div//a[text()='Assign substitute coach']";
	public final String OPS_ADMIN_PROVIDERS_LECTURE_SESSION_CANCEL_SESSION_TITLE="//div[@class='col-xs-12 no-p']//div//h2[contains(text(),'Cancel Session')]";
	public final String OPS_ADMIN_PROVIDERS_LECTURE_SESSION_LECTURE_TITLE_LABEL="//div[@class='col-xs-12 no-p']//p[contains(text(),'Lecture Title:')]";
	public final String OPS_ADMIN_PROVIDERS_LECTURE_SESSION_START_TIME_LABEL="//div[@class='col-xs-12 no-p']//p[contains(text(),'Start Time:')]";
	public final String OPS_ADMIN_PROVIDERS_LECTURE_SESSION_CANCEL_SESSION_BUTTON="//div//button[text()='Cancel Session']";
	public final String OPS_ADMIN_PROVIDERS_LECTURE_SESSION_BACK_TO_SCHEDULE="//div//button[text()='Back to Schedule']";
	public final String OPS_ADMIN_PROVIDERS_LECTURE_SESSION_SCHEDULE_CANCEL_POPUP_CANCELLED_SUCCESS_TEXT="//p[contains(text(),'Session Enrollment Cancelled Successfully.')]";
	public final String OPS_ADMIN_PROVIDERS_LECTURE_SESSION_SCHEDULE_CANCEL_POPUP_OK_BUTTON="//button[contains(text(),'OK')]";
	public final String OPS_ADMIN_PAGENUMBER_INPUT="//input[@id='pageNumber']";
	public final String PROGRAM_NAME_IN_LIST="//td[@class='ng-scope']/a";
	public final String REAL_APPEAL_TEXT_HEADER="//div[@class='page-header-counter']/ul/li[1]/span";
	/**
	 * Name :     SURESH V
	 * Created Date:   15/03/2016
	 * Modified Date:  06April2016
	 */
	public final String PATIENT_SEARCH_TEXTBOX="//input[@id='searchBar']";
	public final String PATIENT_SEARCH_SUBMIT_BUTTON="//button[@id='submit-search']";
	public final String MEMBER_PROFILE_CONTACT_EDIT="//div[@id='contactInfoPanel']//i[@class='fa fa-edit']";
	public final String MEMBER_PROFILE_MAIL="//input[@id='username']";
	public final String MEMBER_PROFILE_CONTACT_SAVE="//input[@id='saveMemberContactButton']";
	public final String HFOPS_MEMBER_PROFILE_CONTACT_INFO_EDIT_SUCCESS_MESSAGE="//div[@id='postPatienceSpinner']//div[contains(text(),'Your edit was successfully processed.')]/button";
	public final String MEMBER_PROFILE_MONTH="//select[@id='dobMonth']";
	public final String MEMBER_PROFILE_DAY="//select[@id='dobDay']";
	public final String MEMBER_PROFILE_YEAR="//select[@id='dobYear']";
	public final String MEMBER_PROFILE_DOB_SAVE="//input[@id='saveMemberDOBButton']";
	public final String MEMBER_PROFILE_MONTH1="//select[@id='dobMonth']/option[text()='10']";
	public final String MEMBER_PROFILE_DAY1="//select[@id='dobDay']/option[text()='11']";
	public final String MEMBER_PROFILE_YEAR1="//select[@id='dobYear']/option[text()='1986']";
	public final String MEMBER_LOGIN_PROFILE_DATE="//span[@class='fw400']/span[2]";
	public final String MEMBER_LOGIN_PROFILE_MONTH="//span[@class='fw400']/span[1]";
	public final String MEMBER_LOGIN_PROFILE_YEAR="//span[@class='fw400']/span[3]";
	public final String DOB_EDIT_LINK="//div[text()='Date of Birth']/following::div/i";
	public final String PATIENT_SORT_DROPDOWN="//select[@id='filterMemberList']";
	public final String PATIENT_SORT_DROPDOWN_EMAIL_OPTION="//select[@id='filterMemberList']//option[text()='Email']";
	public final String SERACH_TEXT_BOX_ACTIVE="//input[@id='searchBar']";
	public final String SEARCHED_MEMBER1="//a[@id='editLink0']";
	public final String INVALID_DOB_ERROR_MSG="//span[text()='Member must be 18 years old.']";
	public final String OPS_MEMBER_REQUEST_SEARCHBY="//select[@ng-model='filterMemberRequest']";
	public final String MEMBER_REQUEST_SAERCH_TEXTBOX="//div[@id='backendSearchInputGroup']/input";
	public final String SEARCH_SUBMIT_BUTTON="//button[@id='submit-search']";
	public final String SEARCHRESULT_COACH_NAME="//a[contains(@href,'/users/userprofile')]";
	public final String COACH_NAME_HEADER="//div[@id='profilePanel']//ul//li//h3";
	public final String TRACKING_SUBTAB_PROGRESS_TAB_TARGET_WEIGHT="//div[@id='progress_update']/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g']/following-sibling::*[local-name()='g']/following-sibling::*[local-name()='g']/following-sibling::*[local-name()='text']";
	public static String HFOPS_PROVIDER_COACH1_APPROVAL_1ON1SESSION ="//input[@id='PA_ProviderApprove_RealAppeal_Checkbox0']";
	public static String HFOPS_PROVIDER_COACH1_APPROVAL_LECTURE_SESSION ="//input[@id='PA_ProviderApprove_RealAppeal_Checkbox1']";
	public final String HFOPS_APPROVAL_SAVE="//button[@id='updateApprovalsBtn']";
	public static String PA_PROVIDER_COACH1_APPROVAL_1ON1SESSION ="//input[@id='PA_ProviderApprove_RealAppeal_Checkbox2']";
	public static String PA_PROVIDER_COACH1_APPROVAL_LECTURE_SESSION ="//input[@id='PA_ProviderApprove_RealAppeal_Checkbox1']";
	public final String HFOPS_APPROVAL_SUCCESS_MSG="//div[text()='Your edit was successfully processed.']//button";
	/**
	 * Name :     LEENA P.
	 * Created Date:   August/02/2016
	 */
	public final String SCHEDULE_PAGE_NAVIGATOR_LEFT="//div[@id='arshawFullCalendar']//div[@class='fc-button-group']//button[1]";
	public final String VERIFY_CREATED_LECTURE="//a[@class='fc-time-grid-event fc-event fc-start fc-end sessionCalendarEvent ng-scope']//div[contains(@class,'fc-title') and contains(.,'testingnotification') or contains(.,'lecturesessionnotification')]";
	public final String VERIFY_CREATED_LECTURE_FOR_SUBSTITUTE="//a[@class='fc-time-grid-event fc-event fc-start fc-end sessionCalendarEvent ng-scope']//div[contains(@class,'fc-title') and contains(.,'testingnotification') or contains(.,'lecturesessionnotification')]";
	public final String SCHEDULE_PAGE_NAVIGATOR_RIGHT="//div[@id='arshawFullCalendar']//div[@class='fc-button-group']//button[2]";
	public final String COACHES_SCHEDULE_CALENDER_SCHEDULE_POPUP_OK_BUTTON="//button[text()='OK' and contains(@ng-click,'closeNotifyModal')]";
	public final String COACHES_LECTURE_TITLE_START="//a[contains(@class,'fc-time-grid-event') and contains(@class,'sessionCalendarEvent')]//div[contains(@class,'fc-title') and contains(.,'";
	public final String COACHES_LECTURE_TITLE_END="')]";
	public final String COACHES_LECTURE_CANCEL_SESSION_BUTTON="//div//button[text()='Cancel Session']";
	public final String COACHES_LECTURE_CANCEL_SESSION_POPUP_OK_BUTTON="//p[contains(text(),'Cancelled')]//parent::div//button";
	public final String PROVIDER_PHYSICIAN_IS_REQUIRED_ERROR_MSG="//span[contains(text(),'Physician is required')]";
	public final String PROVIDER_PHYSICIAN_LABEL="//span[contains(text(),'Physician')]";
	public final String PROVIDER_PHYSICIAN_DROPDOWN="//select[@name='physician']";
	public final String PROVIDER_URL_ADD_PATIENT_PROGRAM_FIELD="//span[contains(text(),'Programs ')]//following::select";
	public final String PROGRAM_LABEL="//span[contains(text(),'Programs ')]";
	public final String PROVIDER_PROGRAM_IS_REQUIRED_ERROR_MSG="//span[contains(text(),'Program is required.')]";
	public final String PROVIDER_ORBERA_POST_PLACEMENT_MEMBER = "//td[contains(@text(),'Post-placement for 52 weeks')]//preceding-sibling::td[2]";
	public final String NEXT_PAGE_ARROW = "//button[@id='nxt_Button_Members']";
	public final String PROVIDERS_TAB = "//span[contains(text(),'Providers')]";
	public final String ADD_NEW_PROVIDER = "//button[@id='createNewProviderBtn']";
	public final String ADD_NEW_PROVIDER_PAGE = "//div[contains(text(),'Personal Information')]";
	public final String ORGANIZATION_NAME = "//select[@id='userOrganization']";
	public final String ROLE_NAME = "//select[@id='userRoleId']";
	public final String FIRST_NAME = "//input[@name='firstName']";
	public final String EMAIL = "//input[@id='email']";
	public final String BIO = "//textarea[@id='bio']";
	public final String PARTITIONER_YOP_EMAIL_TEXTBOX="//input[@id='login']";
	public final String PARTITIONER_YOP_EMAIL_CHECKBOX="//input[@type='submit' and @value='Check Inbox']";
	public final String PARTITIONER_YOP_EMAIL_IFRAME="//iframe[@id='ifmail']";
	public final String PARTITIONER_YOP_EMAIL_GENERATED_LINK="//a[contains(text(),'Click here to complete your remaining enrollment process')]";
	public final String STATUS_EDIT = "//div[@id='statusPanel']//div[contains(text(),'Status')]//parent::div//i[contains(@class,'fa fa-edit')]";
	public final String SET_PROVIDER_ACTIVE_TEXT = "//p[contains(text(),'Marking this Provider as active will let the Provider access the application again')]";
	public final String CONTINUE_BUTTON = "//a[contains(text(),'CONTINUE')]";
	public final String PROVIDER_ADDED_SUCCESSFULLY_OK = "//div[@id='spinnerSuccess']//button";
	public final String PROGRAM_ADMIN_CLIENTS_TAB = "//span[text()='Clients']";
	public final String PROGRAM_ADMIN_CLIENTS_TAB_FILTER_DROPDOWN  = "//select[@id='filterClientList']";
	public final String CLIENT_APOLLO_ENDO = "//a[text()='Apollo Endo']";
	public final String VIEW_CLIENTS = "//button[text()='VIEW CLIENTS']";
	public final String CLIENT_ABC_PRACTICE = "//a[text()='ABC Practice']";
	public final String PAYMENT = "//a[text()='PAYMENT']";
	public final String ADD_PAYMENT_METHOD_BUTTON = "//button[@id='addPaymentMethodBtn']";
	public final String PAYMENT_TYPE = "//select[@name='paymentType']";
	public final String ADD_PAYMENT_BUTTON = "//button[contains(text(),'ADD PAYMENT METHOD')]";
	public final String PAYMENT_SUCCESS_MESSAGE = "//p[contains(text(),'Payment method')]";
	public final String OK_BUTTON = "//button[contains(text(),'OK')]";
	public final String PAYMENT_SUB_CLIENT_SEARCH = "//input[@id='subClientSearchBar']";
	public final String SUB_CLIENT = "//div[@id='scrollDiv']/table/tbody/tr/td[1]";
	public final String  PAYMENT_SUB_CLIENT_SEARCH_MAGNIFIER= "//button[@id='getUsers_search_submit_sub']";
	public final String PRACTITIONER_PROFILE_EDIT_BUTTON = "//div[@class='edit-toggler-backend pull-right']/i";
	public final String PACLIENT_GENERAL_TAB_STATE = "//select[@id='mailingState']";
	public final String  POSTALCODE= "//input[@id='zip']";
	public final String SAVE_BUTTON = "//button[text()='Save']";
	public final String PROVIDER_YOP_EMAIL_GENERATED_LINK="//a[contains(text(),'Click here to activate your account')]";
	public final String PROVIDER_ACTIVATION_PASSWORD="//label[contains(text(),'New Password')]//following-sibling::div//input[@type='password']";
	public final String PROVIDER_ACTIVATION_REENTER_PASSWORD="//label[contains(text(),'Re-enter Password')]//following-sibling::div//input[@type='password']";
	public final String PROVIDER_ACTIVATION_SUBMIT_BUTTON="//button[@type='submit']";
	public final String PROVIDER_FIRST_NAME="firstName";
	public final String PROVIDER_LAST_NAME="lastName";
	public final String PROVIDER_PHONE="phone";
	public final String PROVIDER_STREET="street";
	public final String PROVIDER_CITY="city";
	public final String PROVIDER_STATE="state";
	public final String PROVIDER_DOB_MONTH="dobMonth";
	public final String PROVIDER_DOB="dobDay";
	public final String PROVIDER_DOB_YEAR="dobYear";
	public final String PROVIDER_CREATE_SAVE_BTN="submitBtnCreateUserForm";
	public final String PROVIDER_ACTIVATE_BTN="isProviderActiveTrue";
	public final String PROVIDER_SUBMIT_STATUS="submitBtnStatusForm";
	public final String CREATE_PROVIDER_APPROVAL_TAB="clientsTabPrograms_InsuranceProfile_Link";
	public final String PROVIDER_LOGIN_BUTTON="//button[@id='login_button']";
	public final String EMAIL_TEXTBOX_PROVIDER_URL="//input[@type='email']";
	public final String PASSWORD_TEXTBOX_PROVIDER_URL="//input[@type='password']";
	public final String  PRACTITIONER_PATIENT_TAB="//span[text()='Patients']";
	public final String USER_SIGNOUT_PARENT_LINK = "//li[@class='navbar-nav-settings']//span[@id='navbar_hfSysUserSettings']//i[@class='fa fa-angle-down pull-right']";
	public final String PRACTITIONER_GEAR_SETTING_LINK_MY_PROFILE_OPTION = "//a[text()='My Profile']";
	public final String MYPROFILE_PAYMENT_TAB = "//a[text()='Payment']";
	public final String  PAYMENT_ADD_NEW_CREDIT_CARD= "//button[contains(text(),' Add New Credit Card ')]";
	public final String PAYMENT_CARD_NAME = "//input[@id='cardName']";
	public final String PAYMENT_CARD_NUMBER = "//input[@id='cardNumber']";
	public final String PAYMENT_EXP_DATE = "//select[@name='expDate']";
	public final String PAYMENT_EXP_YEAR = "//select[@name='expYear']";
	public final String PAYMENT_SECURITY_CODE = "//input[@id='cvc']";
	public final String PAYMENT_SAVE_CREDIT_CARD= "//button[text()='SAVE CREDIT CARD']";
	public final String ADD_PATIENT_TEXT_INLEFT_CORNER="//span[text()='Add Patient']";
	public final String PRACTITIONER_PROGRAM_DROPDOWN= "//select[@name='program']";
	public final String PATIENT_SUB_TITTLE = "//div[@id='navbarPrimaryInner']//a//span[text()='Patients']";
	public final String ADD_PATIENT_PAYMENT = "//button[text()='ADD PATIENT']";
	public final String ADD_PATIENT_PAYMENT_CANCEL = "//a[text()='Cancel']";
	public final String  PAYMENT_DELETE_CREDIT_CARD= "//td[contains(text(),'paymenttest')]//following-sibling::td[4]/i";
	public final String PAYMENT_CONFIRM_DELETE = "//button[text()='CONFIRM DELETE']";
	public final String RA_PROVIDER_YOP_EMAIL_GENERATED_LINK="//div[@id='mailmillieu']//a[contains(text(),'";
	public final String RA_YOPMAIL_GENERATED_LINK_INBOX="//div[@id='m1']//span[contains(text(),'";
	public final String RA_YOPMAIL_GENERATED_LINK_INBOX_2="')]";
	public final String YOPMAIL_IFRAME_INBOX="//iframe[@id='ifinbox']";
	public final String  PAYMENT_DELETE_TWENTYCC_CREDIT_CARD= "//td[contains(text(),'twentycc')]//following-sibling::td[4]/i";
	public final String  CREATED_1on1_SESSION= "//div[@class='fc-content']//div[contains(text(),'1on1')]";
	public final String COACHES_GEAR_BUTTON_SCHEDULE_CALENDER_DAY_VIEW_BUTTON = "//button[@id='calendarDayViewBtn']";
    
	public final String MEMBER_PROFILE_EMAIL_CHECKBOX="//input[@id='preferredFormOfContact_Email']";


}

