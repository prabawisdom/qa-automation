package com.zillion.qa.practitioner;
public interface OR
{
	/**
	 * V.Suresh
	 * Provider URL>>Practitioner login
	 * 15.12.2015
	 */
	public final String PROVIDER_LOGIN_BUTTON="//button[@id='login_button']";
	public final String EMAIL_TEXTBOX_PROVIDER_URL="//input[@type='email']";
	public final String PASSWORD_TEXTBOX_PROVIDER_URL="//input[@type='password']";
	public final String PRACTITIONER_DASHBOARD_TAB="//span[text()='Dashboard']";
	public final String  PRACTITIONER_MESSAGE_TAB="//span[text()='Messages']";
	public final String  PRACTITIONER_PATIENT_TAB="//span[text()='Patients']";
	/**
	 * Name         :   Suresh.V
	 * Created Date :   14/Dec/15
	 * Modified Date:   19/Jan/2016
	 * Description  :   ADD patient/Member creation with full valid data and verification
	 */
	public final String PATIENT_HEADER_LABLE_IN_PRACTITIONER_URL="//span[text()='Patient List']";
	public final String PROVIDER_URL_SETTING_IMAGE="//span[@id='navbar_hfSysUserSettings']//i";
	public final String PROVIDER_URL_SIGNOUT_LINK="//a[@id='navbar_hfSysUserSettings_SignOut']";
	public final String PROVIDER_URL_ADD_PATIENT_BUTTON ="//button[@id='addNewMember']";
	public final String ADD_PATIENT_TEXT_INLEFT_CORNER="//div[text()='Add Patient']";
	public final String CLOSE_ICON ="//a[@ng-click='close()']";
	public final String PROVIDER_URL_ADD_PATIENT_LAST_STAGE="//button[text()='ADD PATIENT']";
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
	public final String PO_NUMBER="//input[@id='ponumber']";
	/**
	 * Name         :   Suresh.V
	 * Created Date :   21/Dec/15
	 * Modified Date:   28/Dec/15
	 * Description  :   Provider URL>>Practitioner login>>Full static element verification
	 */
	public final String PROVIDER_URL_PARTITIONER_PATIENTS_NAVIGATION_LINK="//a[@id='navMembersLink']";
	public final String PROVIDER_URL_PARTITIONER_ADMIN_FILTERMEMBER_LIST_LINK="//select[@id='filterMemberList']";
	public final String PROVIDER_URL_PARTITIONER_FILTERMEMBER_LIST_ALL="//select[@id='filterMemberList']//option[text()='All']";
	public final String PROVIDER_URL_PARTITIONER_FILTERMEMBER_LIST_NAME="//select[@id='filterMemberList']//option[text()='Name']";
	public final String PROVIDER_URL_PARTITIONER_FILTERMEMBER_LIST_EMAIL="//select[@id='filterMemberList']//option[text()='Email']";
	public final String PROVIDER_URL_PARTITIONER_FILTERMEMBER_LIST_CLASS="//select[@id='filterMemberList']//option[text()='Class']";
	public final String PROVIDER_URL_PARTITIONER_FILTERMEMBER_LIST_ONBOARDING_STATUS="//select[@id='filterMemberList']//option[text()='Onboarding Status']";
	public final String PROVIDER_URL_PARTITIONER_FILTERMEMBER_LIST_CLIENT="//select[@id='filterMemberList']//option[text()='Client']";
	public final String PROVIDER_URL_PARTITIONER_FILTERMEMBER_LIST_PATIENT_ID="//select[@id='filterMemberList']//option[text()='Patient ID']";
	public final String PROVIDER_URL_PARTITIONER_TABLE_TITLE_NAME="//span[text()='Name']";
	public final String PROVIDER_URL_PARTITIONER_TABLE_TITLE_EMAIL="//span[text()='Email']";
	public final String PROVIDER_URL_PARTITIONER_TABLE_TITLE_PROGRAM="//span[text()='Program']";
	public final String PROVIDER_URL_PARTITIONER_TABLE_TITLE_CLASS="//span[text()='Class']";
	public final String PROVIDER_URL_PARTITIONER_TABLE_TITLE_COACH="//span[text()='Coach']";
	public final String PROVIDER_URL_PARTITIONER_TABLE_TITLE_ACCOUNT="//span[text()='Account']";
	public final String PROVIDER_URL_PARTITIONER_TABLE_TITLE_ONBOARDING_STATUS="//span[text()='Onboarding']";
	public final String PROVIDER_URL_PARTITIONER_TABLE_TITLE_CLIENT="//span[text()='Client']";
	public final String PROVIDER_URL_PARTITIONER_TABLE_TITLE_PATIENT_ID="//span[text()='Patient ID ']";
	public final String PROVIDER_URL_PARTITIONER_PREVIOUS_PAGE_BUTTON_DISABLED="//button[@id='prev_Button_Members'and @disabled='disabled']";
	public final String PROVIDER_URL_PARTITIONER_NEXT_PAGE_BUTTON="//button[@id='nxt_Button_Members']";
	public final String PROVIDER_URL_PARTITIONER_SUBMIT_SEARCH_IMAGE="//button[@id='submit-search']";
	public final String PROVIDER_URL_PARTITIONER_SEARCHBAR_DISABLED="//input[@id='searchBar'and @disabled='']";
	public final String PROVIDER_URL_PARTITIONER_LOGGED_IN_AS_SIMPLE_MESSAGE="//span[text()='you are logged in as']";
	public final String PROVIDER_URL_PARTITIONER_IE="//a[contains(text(),'Continue to this')]";
	public final String PARTITIONER_YOP_EMAIL_TEXTBOX="//input[@id='login']";
	public final String PARTITIONER_YOP_EMAIL_CHECKBOX="//input[@type='submit']";
	public final String PARTITIONER_YOP_EMAIL_IFRAME="//iframe[@id='ifmail']";
	public final String PARTITIONER_YOP_EMAIL_GENERATED_LINK="//a[contains(text(),'Click here to complete your remaining enrollment process')]";
	/**
	 * VIGNESHRAJ.M
	 *  Mail Drop>Practitioner.
	 * 23.12.2015
	 */
	public final String PARTITIONER_MAIL_DROP_TEXTBOX="//div[@class='unit four-of-five']//form[@id='nav-form']//input[@id='nav-inbox']";
	public final String PARTITIONER_MAIL_DROP_SUBMIT_BUTTON="//button[@type='submit']";
	public final String PARTITIONER_KULFI_WELCOME_LINK_MESSAGE="//a[contains(text(),'Welcome to your Orbera Journey!')]";
	public final String PARTITIONER_MAIL_DROP_MESSAGE_IFRAME="//iframe[@id='messageframe']";
	public final String PARTITIONER_MAILDROP_GENERATED_LINK="//a[text()='Click here to complete your remaining enrollment process']";
	/**
	 * VIGNESHRAJ.M
	 * Sorting All Members Columns>Practitioner.
	 * 23.12.2015
	 */
	public final String PATIENT_SORT_DROPDOWN="//select[@id='filterMemberList']";
	public final String PATIENT_SORT_DROPDOWN_ALL_OPTION="//select[@id='filterMemberList']//option[text()='All']";
	public final String PATIENT_SORT_DROPDOWN_NAME_OPTION="//select[@id='filterMemberList']//option[text()='Name']";
	public final String PATIENT_SORT_DROPDOWN_EMAIL_OPTION="//select[@id='filterMemberList']//option[text()='Email']";
	public final String PATIENT_SORT_DROPDOWN_CLASS_OPTION="//select[@id='filterMemberList']//option[text()='Class']";
	public final String PATIENT_SORT_DROPDOWN_ONBOARDING_STATUS_OPTION="//select[@id='filterMemberList']//option[text()='Onboarding Status']";
	public final String PATIENT_SORT_DROPDOWN_CLIENT_OPTION="//select[@id='filterMemberList']//option[text()='Client']";
	public final String PATIENT_SORT_DROPDOWN_ID_OPTION="//select[@id='filterMemberList']//option[contains(text(),'ID')]";
	public final String PATIENT_COLUMN_NAME_TEXT="//tbody[@id='membersList']/tr/td[1]";
	public final String PATIENT_COLUMN_EMAIL_TEXT="//tbody[@id='membersList']/tr/td[2]";
	public final String PATIENT_COLUMN_ONBOARDING_TEXT="//tbody[@id='membersList']/tr/td[7]";
	public final String PATIENT_COLUMN_CLIENT_TEXT="//tbody[@id='membersList']/tr/td[8]";
	public final String PATIENT_COLUMN_PATIENT_ID_TEXT="//tbody[@id='membersList']/tr/td[9]";
	public final String PATIENT_SEARCH_TEXTBOX="//input[@id='searchBar']";
	public final String PATIENT_SEARCH_SUBMIT_BUTTON="//button[@id='submit-search']";
	public final String PATIENT_SEARCH_CLEAR_BUTTON="//a[@id='getMembers_search_clear']";
	public final String PATIENT_SEARCH_TEXTBOX_DISABLED="//div[@id='backend-provider-search']//input[@disabled='']";
	public final String PATIENT_HAS_BEEN_ADDED_SUCCESSFULLY_OK_BUTTON="//div[@class='modal-content']//button[contains(text(),'OK')]";
	public final String PROVIDER_ADD_PATIENT_PAYMENT_METHOD_PROCEED_BUTTON="//button[contains(text(),'Proceed')]";
	public final String PATIENT_CLOSE="//input[@value='Close']";
	public final String MAIL_DROP_REGISTRATION_COMPLETE_LINK="//a[contains(text(),'Registration complete')]";
	public final String MAIL_DROP_CONGRATULATION_MESSAGE="//div[contains(text(),'Congratulations! You have completed your Registration successfully.')]";
	public final String PATIENT_ADDED_SUCCESS_MSG="//h4[text()='Patient has been added successfully.']";
	public final String MAIL_DROP_RELOAD_BUTTON="//a[@id='inbox-reload']";
	public final String MEMBER_YOURMAIL ="//input[@name='email']";
	public final String MEMBER_YOURPASSWORD ="//input[@name='password']";
	public final String MEMBER_LOGIN_LINK ="//a[contains(text(),'LOG IN')]";
	public final String MEMBER_DASHBOARD ="//*[@id='dashboardfullview_navItem']";
	public final String SCHEDULE_ORBERA_PLACEMENT_CLOSE_BUTTON="//button[contains(text(),'OK')]";
	public final String MEMBER_LOGIN_BUTTON="//button[contains(text(),'LOG IN')]";
	/**
	 * SURESH.V
	 * 22jan2016
	 */
	public final String MY_PROFILE_LINK="//a[@id='navbar_hfSysUserSettings_MyProfile']";
	public final String USER_SIGNOUT_PARENT_LINK="//li[@class='navbar-nav-settings']//span[@id='navbar_hfSysUserSettings']//i";
	public final String MANAGE_MY_PROFILE_HEADER="//h3[text()='Manage My Profile']";
	public final String ACTIVE_GENERAL_TAB="//li[@class='active']//a[@id='clientsTabGeneral_Profile_Link']";
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
	public final String ORGANIZATION="//select[@name='organization']";
	/**
	 * Vinothkumar.M
	 * GuerrillaMail
	 */
	public final String GUERRILLA_MAIL_DROPDOWN="//select[@id='gm-host-select']";
	public final String GUERRILLA_MAIL_TEXTBOX="//span//input[@type='text']";
	public final String GUERRILLA_MAIL_SET_BUTTON="//button[contains(text(),'Set')]";
	public final String GUERRILLA_MAIL_ACTIVATE_YOUR_ACCOUNT_LINK="";
	public final String GUERRILLA_MAIL_EDIT_BUTTON="//span[@id='inbox-id']";
	public final String GUERRILLA_MAIL_WELCOME_MESSAGE="//a[contains(text(),'Welcome to your Orbera Journey!')]";
	public final String GUERRILLA_MAIL_GENERATED_LINK="//a[contains(text(),'Complete Your Registration')]";
	public final String GUERRILLA_MAIL_COMPLETE_LINK="//a[contains(text(),'Registration complete')]";
	public final String GUERRILLA_MAIL_CONGRATULATION_MESSAGE="//div[contains(text(),'Congratulations! You have completed your Registration successfully.')]";
	public final String GUERRILLA_MAIL_WELCOME_MESSAGE_VSPN="//*[contains(text(),'Welcome to your VSPN Patient Journey')]";
	public final String GUERRILLA_MAIL_GENERATED_LINK_VSPN="//a[contains(text(),'Click here to complete your remaining enrollment process')]";
	public final String GUERRILLA_MAIL_WELCOME_TO_RA_LINK="//a[contains(text(),'Welcome to Real Appeal')]";
	public final String ORBERA_COACH_1on1_MAIL_GR="//tbody//tr//td[3]";
	/**
	 * Vinothkumar.M
	 * Enrollment Process
	 */
	public final String PRIVACY_POLICY_LINK="//li/a[text()='Privacy Policy']";
	public final String PRIVACY_POLICY_PAGE="//div[@id='pageHeader'][text()='Privacy Policy']";
	public final String TERMS_AND_CONDITION_LINK="//li/a[text()='Terms of Use']";
	public final String TERMS_AND_CONDITION_PAGE="//div[@id='pageHeader'][text()='Terms and Conditions']";
	public final String MEMBER_STEP2_TERMS_AND_CONDITIONS_LINK="//a[contains(text(),'Terms and Conditions')]";

	public final String MEMBER_STEP2_PRIVACY_POLICY_LINK="//a[contains(text(),'Privacy Policy')]";

	public final String MEMBER_ADDED_SUCCESS_MSG="//h4[text()='Member has been added successfully.']";
	public final String MEMBER_ADDED_SUCCESS_OK_BUTTON="//h4[text()='Member has been added successfully.']//following::div//button[text()='OK']";
	public final String HFOPS_URL_ADD_MEMBER_LAST_STAGE="//button[text()='ADD MEMBER']";
	public final String PROVIDER_PHYSICIAN_IS_REQUIRED_ERROR_MSG="//span[contains(text(),'Physician is required')]";
	public final String PROVIDER_PHYSICIAN_LABEL="//span[contains(text(),'Physician')]";
	public final String PROVIDER_PHYSICIAN_DROPDOWN="//select[@name='physician']";
	public final String PROVIDER_URL_ADD_PATIENT_PROGRAM_FIELD="//span[contains(text(),'Programs ')]//following::select";
	public final String PROGRAM_LABEL="//span[contains(text(),'Programs ')]";
	public final String PROVIDER_PROGRAM_IS_REQUIRED_ERROR_MSG="//span[contains(text(),'Program is required.')]";
	public final String PRACTITIONER_LOGIN_PATIENT_TAB_TEXT_REF="//div[@id='navbarPrimaryInner']//a//span[text()='Patients']";
	public final String PATIENT_PAYMENT_CONFIRMATION_CLOSE_BUTTON="//input[@value='Close']";
	public final String PROVIDER_ADD_PATIENT_PO_NUMBER_TEXTBOX="//input[@id='ponumber']";
	public final String  PAYMENT_ADD_NEW_CREDIT_CARD= "//a[contains(.,'Add Credit Card')]";
	public final String PAYMENT_CARD_NAME = "//input[@id='cardName']";
	public final String PAYMENT_CARD_NUMBER = "//input[@id='cardNumber']";
	public final String PAYMENT_EXP_DATE = "//select[@name='expDate']";
	public final String PAYMENT_EXP_YEAR = "//select[@name='expYear']";
	public final String PAYMENT_SECURITY_CODE = "//input[@id='cvc']";
	public final String PAYMENT_SAVE_CREDIT_CARD= "//button[contains(text(),'SAVE CREDIT CARD')]";
	public final String INVOICE_NUMBER_ALERT_MESSAGE= "//span[contains(text(),'PO Number is required')]";
	public final String INVOICE_NUMBER_TEXT_BOX= "//input[@id='ponumber']";
	public final String PAYMENT_CARD_DECLINED_ERROR= "//div[@class='modal-body-notification ng-scope']/p";
	public final String PAYMENT_CARD_DECLINED_ERROR_OK_BUTTON= "//div[@class='modal-body-notification ng-scope']/button";
}