package com.zillion.qa.realappealcoach;

public interface OR 
{

	/**
	 * Name :    Vinothkumar.M
	 * Created Date:   21/Apr/16 
	 * Modified Date:   
	 * Description :   Create a OR for Login and Logout Funtionality RA program Admin.
	 * Ticket ID :     
	 * Required Inputs :  Username and Password
	 */
	public final String RA_COACH_USERNAME="//input[@placeholder='Email Address']";
	public final String RA_COACH_PASSWORD="//input[@placeholder='Password']";
	public final String RA_COACH_LOGIN="//button[@id='login_button']";
	public final String RA_COACH_REAL_APPEAL_LOGO="//a[@id='orgNavbarBrand']";
	public final String RA_COACH_SIGNOUT_LINK="//div[@class='text-right inline-table-cell']//i";
	public final String RA_COACH_SIGNOUT_BUTTON="//a[@id='navbar_hfSysUserSettings_SignOut']";
	public final String RA_COACH_SIGNOUT_VERIFY="//a[@id='sign_in_again_link']";
	public final String PROVIDER_USER_SESSION_POPUP="//p[contains(text(),'user is already active in another browser')]//following-sibling::button";

	/**
	 * Name :    Abinaya
	 * Created Date:   04/May/16 
	 * Modified Date:   
	 * Description :   Create a OR for assignedCoachOfMemberLogin.
	 * Ticket ID :     
	 * Required Inputs :  
	 */
	public final String RA_MEMBER_REAL_APPEAL_LOGO="";
	public final String  YOP_EMAIL_TEXTBOX="//input[@id='login']"; 
	public final String YOP_EMAIL_CHECK_INBOX="//input[@type='submit']"; 
	public final String YOP_EMAIL_IFRAME="//iframe[@id='ifinbox']";
	public final String RA_MEMBER_ENROLLMENT_MAIL="//a[text()=' DEMO: Welcome to Real Appeal!']";
	public final String RA_MEMBER_ENROLLMENT_MAIL_CONTENT="//b[text()='The next step is to attend your Online Personalization Session on']";
	public final String GUERRILLA_MAIL_DROPDOWN="//select[@id='gm-host-select']";
	public final String GUERRILLA_MAIL_TEXTBOX="//span//input[@type='text']";
	public final String GUERRILLA_MAIL_SET_BUTTON="//button[contains(text(),'Set')]";
	public final String GUERRILLA_MAIL_EDIT_BUTTON="//span[@id='inbox-id']"; 
	public final String RA_COACH_CUSTOMIZATION_MAIL="//span[text()='Confirmation: Customization Session scheduled']";
	public final String RA_COACH_CUSTOMIZATION_MAIL_CONTENT="//div[contains(text(),'You are scheduled to conduct a Customization Session')]";
	public final String RA_COACH_1on1_MAIL="//div[@id='m1']//*[contains(text(),'1-on-1 Session Rescheduled')]";
	public final String RA_COACH_1on1_CONTENT="//div[contains(text(),'The 1-on-1 session you were scheduled')]";
	public final String YOP_EMAIL_IFRAME1="//iframe[@id='ifmail']";
	public final String YOP_EMAIL_IFRAME2="//html/body/div[5]/table/tbody/tr[1]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td[1]/iframe";
	public final String RA_COACH_1on1_CANCELlATION_MAIL="//*[contains(text(),'Session Canceled')]";
	public final String RA_COACH_1on1_CANCELLATION_CONTENT="//*[contains(text(),'has been canceled')]";
	public final String RA_COACH_ENROLLMENT_MAIL="//span[contains(text(),'Confirmation: Customization Session scheduled')]";
	public final String RA_COACH_ENROLLMENT_MAIL_CONTENT="//div[contains(text(),'You are scheduled to conduct a Customization Session')]";
	public final String YOPMAIL_RA_COACH_1on1_MAIL_SCHEDULED="//span[contains(text(),'Session Scheduled: 1-on-1')]";
	public final String YOPMAIL_RA_COACH_1on1_CONTENT_SCHEDULED="//div[contains(.,'You are scheduled to conduct a 1-on-1 session')]";
	public final String RA_COACH_DASHBOARD="//div[@id='navbarPrimaryInner']//span[text()='Dashboard']";
	public final String RA_COACH_ALERT="//div[contains(text(),'Your account')]";
	public final String RA_COACH_ENROLLMENT_MAIL_GUERILLA="//td[contains(.,'Customization Session scheduled')]";
	public final String GUERRILLAMAIL_RA_COACH_1on1_MAIL_SCHEDULED="//td[contains(.,'Session Scheduled: 1-on-1')]";
	public final String GUERRILLAMAIL_RA_COACH_1on1_CONTENT_SCHEDULED="//h3[contains(text(),'Session Scheduled: 1-on-1,')]";
	public final String GUERRILLAMAIL_RA_COACH_CUSTOMIZATION_MAIL="//td[contains(.,'Customization Session scheduled ')]";
	public final String ORBERA_COACH_1on1_MAIL_GR="//tbody//tr//td[3]";

	public final  String  MEMBER_PHOTO_ORIENTATION="//i[@title='Rotate Left']";
	public final  String  MEMBER_UPLOAD_SAVE ="//button[text()='Save']"; 
	public final String MEMBER_UPLOAD_SAVE1="//button[contains(@class,'btn btn-primary center-block btn-lg btn-block')]";
	public final String PROVIDER_CHANGE_PIC="//input[@id='hfImgCropFileName-providerPgProfileImg']"; 


}
