package com.zillion.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Properties;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;

import com.zillion.qa.ZadoReports;
import com.zillion.qa.enums.ReportLabels;
import com.zillion.qa.exceptions.ZadoReporterException;
import com.zillion.qa.writers.HTMLDesignFilesWriter;
/**
 * Configurations
 * @author Babu 
 *
 */
public class Directory {

	static Logger log = Logger.getLogger(Directory.class.getName());
	public static final String CURRENTDir = System.getProperty("user.dir");
	public static final String SEP = System.getProperty("file.separator");
	public static String REPORTSDIRName = "Zado Reports";
	public static String REPORTSDir = CURRENTDir + SEP + REPORTSDIRName;
	public static String RESULTSDir = REPORTSDir + SEP + "Results";
	public static String HTMLDESIGNDIRName = "HTML_Design_Files";
	public static String HTMLDESIGNDir = REPORTSDir + SEP + HTMLDESIGNDIRName;
	public static String CSSDIRName = "CSS";
	public static String CSSDir = HTMLDESIGNDir + SEP + CSSDIRName;
	public static String IMGDIRName = "IMG";
	public static String IMGDir = HTMLDESIGNDir + SEP + IMGDIRName;
	public static String JSDIRName = "JS";
	public static String JSDir = HTMLDESIGNDir + SEP + JSDIRName;
	public static String RUNName = "Test Execution_1"; // Babu
	public static String RUNDir = RESULTSDir + SEP + RUNName;
	public static String SETTINGSFile = RESULTSDir + SEP + "Settings.properties";
	public static final char JS_SETTINGS_DELIM = ';';
	public static final String REPO_DELIM = "##@##@##";
	public static final char JS_FILE_DELIM = ',';
	public static final String MENU_LINK_NAME = "Run ";
	public static final String SCREENSHOT_TYPE = "PNG";
	public static final String SCREENSHOT_EXTENSION = ".PNG";
	private static String logo = null;
	public static String SCREENSHOT_DIRName = "img";
	public static boolean generateConfigReports = true;
	public static boolean generateExcelReports = false;
	public static boolean takeScreenshot = false;
	public static boolean continueExecutionAfterStepFailed = false;
	public static boolean recordExecutionAvailable = false;
	public static boolean recordSuiteExecution = false;
	public static boolean recordTestMethodExecution = false;
	public static boolean reexecution=true;
	public static final String MAIN_RECORD_FILE_NAME = "ATU_CompleteSuiteRecording";
	public static String userName=null;
	public static String password=null;
	public static String smtpHost=null;
	public static String fromAddress=null;
	public static String toAddress=null;
	public static String ccAddress=null;
	//public static String bccAddress=null;
	public static String testCasePath=null;
	public static String ORSheetPath=null;
	public static String browser=null;
	public static String Subject=null;
	public static String Reports_Path=null;
	public static String Zipfolder_Path=null;
	public static String WIDGET_HTML_FILE=null;
	public static String WaitFor=null;
	public static String yopmailurl=null;
	public static String Providerurl=null;
	public static String Coachesurl=null;
	public static String OpsAdminurl=null;
	public static String Memberurl=null;
	public static String Practitionerusername=null;
	public static String Practitionerpassword=null;
	public static String Programadminusername=null;
	public static String Programadminpassword=null;
	public static String Coachesusername1=null;
	public static String Coachespassword1=null;
	public static String Coachesusername2=null;
	public static String Coachespassword2=null;
	public static String Coachesusername3=null;
	public static String Coachespassword3=null;
	public static String OpsAdminusername=null;
	public static String OpsAdminpassword=null;
	public static String Memberusername1=null;
	public static String Memberpassword1=null;
	public static String Memberusername2=null;
	public static String Memberpassword2=null;
	public static String uploadFilePath=null;
	public static String MailDropurl=null;
	public static String Member_Tracker_Journal_181826_178095_mailid=null;
	public static String Coaches_Member_Profile_186455_mailid=null;
	public static String Lecturememberusername=null;
	public static String Lecturememberpassword=null;
	public static String Verification_186452_mailid=null;
	public static String Zado_Program_Admin_General_Tab_192272_mailid=null;
	public static String PAwellnesscorpusername=null;
	public static String PAwellnesscorppassword=null;
	public static String Guerrillamailurl=null;
	public static String Sessionmemberusername=null;
	public static String Sessionmemberpassword=null;
	public static String AppendURL=null;
	public static String Accountidofthemember=null;
	public static String LiveSessionCoachUsername=null;
	public static String LiveSessionCoachPassword=null;
	public static String liveSessionMemberUsername=null;
	public static String liveSessionMemberPassword=null;
	public static String CoachAppendURL=null;
	public static String liveSessionCoachbrowser=null;
	public static String retrieveNameOfTheMember_mailid=null;
	public static String Oracle_Port=null;
	public static String Oracle_Databasename=null;
	public static String Oracle_User=null;
	public static String Oracle_Pass =null;
	public static String Oracle_Hostname=null;
	public static String Sql_Hostname=null;
	public static String Sql_Databasename=null;
	public static String Sql_User=null;
	public static String Sql_Pass=null;
	public static String Sql_Port=null;
	public static String zado_environment=null;
	public static String PAapolloEndousername=null;
	public static String PAapolloEndopassword=null;
	public static String RA_Member_Url=null;
	public static String RA_Provider_Url=null;
	public static String RA_Member_Username1=null;
	public static String RA_Member_Password1=null;
	public static String RA_Coach_Username1=null;
	public static String RA_Coach_Pasword1=null;
	public static String RA_PA_Username1=null;
	public static String RA_PA_Pasword1=null;
	public static String MOBILE_APPPATH=null;
	public static String MOBILEAPP_APK_NAME=null;
	public static String MOBILE_DEVICE_NAME=null;
	public static String MOBILE_DEVICE_VERSION=null;
	public static String MOBILE_APK_APPPACKAGE=null;
	public static String MOBILE_DEVICE_TYPE=null;
	public static String MOBILE_IOSDEVICE_UDID= null;
	public static String MOBILE_APP_TYPE=null;
	public static String MOBILE_WEB_BROWSER_NAME=null;
	public static String MOBILE_WEB_URL=null;
	public static String RAMemberAppendURL=null;
	public static String RACoachAppendURL=null;
	public static String AllSessioncoachesusername=null;
	public static String AllSessioncoachespassword=null;
	public static String Epochurl=null;
	public static String RA_Enrollment_Insurance_Provider=null;
	public static String RA_Enrollment_Member_Id=null;
	public static String RA_Enrollment_Group_Member=null;
	public static String RA_Enrollment_First_Name=null;
	public static String RA_Enrollment_Last_Name=null;
	public static String RA_Enrollment_Month=null;
	public static String RA_Enrollment_Day=null;
	public static String RA_Enrollment_Year=null;
	public static String Client2_RA_Enrollment_Member_Id=null;
	public static String Client2_RA_Enrollment_Group_Number=null;
	public static String Client2_RA_Enrollment_Last_Name=null;
	public static String Client2_RA_Enrollment_First_Name=null;
	public static String Client2_RA_Enrollment_Insurance_Provider=null;
	public static String Client1_RA_Organization_ID=null;
	public static String Client2_RA_Organization_ID=null;
	public static String Client2_RA_Enrollment_Month=null;
	public static String Client2_RA_Enrollment_Day=null;
	public static String Client2_RA_Enrollment_Year=null;
	public static String RA_Enrollment_Organization_Name=null;
	public static String UseCaseOne_RA_Enrollment_Month=null;
	public static String UseCaseOne_RA_Enrollment_Day=null;
	public static String UseCaseOne_RA_Enrollment_Year=null;
	public static String UseCaseOne_RA_Enrollment_Member_Id=null;
	public static String UseCaseOne_RA_Enrollment_Group_Number=null;
	public static String UseCaseOne_RA_Enrollment_Last_Name=null;
	public static String UseCaseOne_RA_Enrollment_First_Name=null;
	public static String UseCaseOne_RA_Enrollment_Insurance_Provider=null;
	public static String RA_UseCaseOneCustomEnroll_Member_Url=null;
	public static String UseCaseTwo_RA_Enrollment_Month=null;
	public static String UseCaseTwo_RA_Enrollment_Day=null;
	public static String UseCaseTwo_RA_Enrollment_Year=null;
	public static String UseCaseTwo_RA_Enrollment_Member_Id=null;
	public static String UseCaseTwo_RA_Enrollment_Group_Number=null;
	public static String UseCaseTwo_RA_Enrollment_Last_Name=null;
	public static String UseCaseTwo_RA_Enrollment_First_Name=null;
	public static String UseCaseTwo_RA_Enrollment_Insurance_Provider=null;
	public static String RA_UseCaseTwoCustomEnroll_Member_Url=null;
	public static String UseCaseThree_RA_Enrollment_Month=null;
	public static String UseCaseThree_RA_Enrollment_Day=null;
	public static String UseCaseThree_RA_Enrollment_Year=null;
	public static String UseCaseThree_RA_Enrollment_Member_Id=null;
	public static String UseCaseThree_RA_Enrollment_Group_Number=null;
	public static String UseCaseThree_RA_Enrollment_Last_Name=null;
	public static String UseCaseThree_RA_Enrollment_First_Name=null;
	public static String UseCaseThree_RA_Enrollment_Insurance_Provider=null;
	public static String RA_UseCaseThreeCustomEnroll_Member_Url=null;
	public static String UseCaseFourUnitedHeath_RA_Enrollment_Month=null;
	public static String UseCaseFourUnitedHeath_RA_Enrollment_Day=null;
	public static String UseCaseFourUnitedHeath_RA_Enrollment_Year=null;
	public static String UseCaseFourUnitedHeath_RA_Enrollment_Member_Id=null;
	public static String UseCaseFourUnitedHeath_RA_Enrollment_Group_Number=null;
	public static String UseCaseFourUnitedHeath_RA_Enrollment_Last_Name=null;
	public static String UseCaseFourUnitedHeath_RA_Enrollment_First_Name=null;
	public static String UseCaseFourUnitedHeath_RA_Enrollment_Insurance_Provider=null;
	public static String RA_UseCaseFourUnitedHeathCustomEnroll_Member_Url=null;
	public static String UseCaseFourClient2_RA_Enrollment_Month=null;
	public static String UseCaseFourClient2_RA_Enrollment_Day=null;
	public static String UseCaseFourClient2_RA_Enrollment_Year=null;
	public static String UseCaseFourClient2_RA_Enrollment_Member_Id=null;
	public static String UseCaseFourClient2_RA_Enrollment_Group_Number=null;
	public static String UseCaseFourClient2_RA_Enrollment_Last_Name=null;
	public static String UseCaseFourClient2_RA_Enrollment_First_Name=null;
	public static String UseCaseFourClient2_RA_Enrollment_Insurance_Provider=null;
	public static String RA_UseCaseFourClient2CustomEnroll_Member_Url=null;
	public static String UseCaseFourClient3_RA_Enrollment_Month=null;
	public static String UseCaseFourClient3_RA_Enrollment_Day=null;
	public static String UseCaseFourClient3_RA_Enrollment_Year=null;
	public static String UseCaseFourClient3_RA_Enrollment_Member_Id=null;
	public static String UseCaseFourClient3_RA_Enrollment_Group_Number=null;
	public static String UseCaseFourClient3_RA_Enrollment_Last_Name=null;
	public static String UseCaseFourClient3_RA_Enrollment_First_Name=null;
	public static String UseCaseFourClient3_RA_Enrollment_Insurance_Provider=null;
	public static String RA_UseCaseFourClient3CustomEnroll_Member_Url=null;
	public static String PracticePAusername=null;
	public static String PracticePApassword=null;
	public static String PracticePA2username=null;
	public static String PracticePA2password=null;
	public static String RA_Prov_Org=null;
	public static String PracticePA2name=null;
	public static String PracticePAABCusername=null;
	public static String PracticePAABCpassword=null;
	public static String ProgramadminKidneyusername=null;
	public static String KidneyMemberusername1=null;
	public static String Orbera_Configurablecolumn_OrganizationID=null;
	public static String Orbera_UserAlert_AbcPractice_OrganizationID=null;
	public static String RA_Member_username2=null;
	public static String RA_Member_username3=null;
	public static String RA_Member_username4=null;
	public static String RA_Member_username5=null;
	public static String GRID_IP=null;
	public static String RA_Member_Client=null;
	public static String MDAC_Jira_Url=null;
	public static String MDAC_Jira_Username=null;
	public static String MDAC_Jira_Password=null;
	public static String T2_Coach_Username2=null;
	public static String T2_Coach_Username1=null;
	public static String Mast_ProgramId=null;
	public static String T2_SFTPUsername=null;
	public static String T2_SFTPPassword=null;
	public static String T2_SFTPHostName=null;
	public static String T2_SFTPChannel=null;
	public static String T2_EnrollmentBatchCount=null;
	
	/**
	 * Retrieve values from custom properties
	 * @throws ZadoReporterException
	 * @throws Exception 
	 */
	public static void init() throws ZadoReporterException, Exception {
		if (getCustomProperties() != null) {
			log.info("Reading from custom properties");
			Properties localProperties = new Properties();

			try {
				localProperties.load(new FileReader(getCustomProperties()));
				String reportsDir = localProperties.getProperty("zado.reports.dir")			.trim();
				String headerText = localProperties.getProperty(			"zado.proj.header.text").trim();	
				String projectDescription = localProperties.getProperty(			"zado.proj.description").trim();
				String takeScreenShot = localProperties.getProperty(			"zado.reports.takescreenshot").trim();
				String configReports = localProperties.getProperty(			"zado.reports.configurationreports").trim();
				String excelReport = localProperties.getProperty("zado.reports.excel")			.trim();
				String contExectution = localProperties.getProperty(			"zado.reports.continueExecutionAfterStepFailed").trim();
				String reExecution = localProperties.getProperty(   "zado.failures.reexecution").trim();
				//String recordExecution = localProperties.getProperty(			"zado.reports.recordExecution").trim();
				userName = localProperties.getProperty(			"zado.mail.username").trim();
				password = localProperties.getProperty(			"zado.mail.password").trim();
				smtpHost = localProperties.getProperty(			"zado.mail.smtp.host").trim();
				fromAddress = localProperties.getProperty(			"zado.mail.from.address").trim();
				toAddress = localProperties.getProperty(			"zado.mail.to.address").trim();
				ccAddress = localProperties.getProperty(				"zado.mail.cc.address").trim();	
				Subject = localProperties.getProperty(				"zado.mail.subject").trim();
				Reports_Path = localProperties.getProperty(				"zado.reports.dir").trim();
				Zipfolder_Path = localProperties.getProperty(				"zado.mail.zipfolder").trim();
				testCasePath = localProperties.getProperty(			"zado.proj.testcasePath").trim();
				String ORSheet = localProperties.getProperty(			"zado.proj.ORSheet.path").trim();
				ORSheetPath=testCasePath+"/"+ORSheet;
				browser = localProperties.getProperty(			"zado.browser.name").trim().toLowerCase();
				//WIDGET_HTML_FILE = localProperties.getProperty(			"zado.proj.widget").trim().toLowerCase();
				WaitFor = localProperties.getProperty(			"zado.proj.waitSec").trim().toLowerCase();
				liveSessionCoachbrowser = localProperties.getProperty(       "zado.browser.liveSession.coach.name").trim().toLowerCase();
				zado_environment= localProperties.getProperty("zado.environment").trim();
				Properties urlsProperties = new Properties();
				System.out.println("testCasePath: "+testCasePath);
				InputStream input = new FileInputStream(testCasePath+"/"+zado_environment+".properties");
				urlsProperties.load(input);				
				String uploadPath=localProperties.getProperty(   "zado.proj.uploadfile").trim();
				uploadFilePath=testCasePath+"/"+uploadPath+"/";
				String logo1 = localProperties.getProperty("zado.proj.header.logo")			.trim();
				logo=uploadFilePath+logo1;
				yopmailurl=urlsProperties.getProperty(			"zado.proj.yopmail").trim();
				Providerurl=urlsProperties.getProperty(			"Zado.url.ProviderUrl").trim();
				Coachesurl=urlsProperties.getProperty(			"Zado.url.CoachesUrl").trim();
				OpsAdminurl=urlsProperties.getProperty(			"Zado.url.OpsAdminUrl").trim();
				Memberurl=urlsProperties.getProperty(			"Zado.url.MemberUrl").trim();
				Practitionerusername=urlsProperties.getProperty(			"Zado.Practitioner.username").trim();
				Practitionerpassword=urlsProperties.getProperty(			"Zado.Practitioner.password").trim();
				Programadminusername=urlsProperties.getProperty(			"Zado.ProgramAdmin.username").trim();
				Programadminpassword=urlsProperties.getProperty(			"Zado.ProgramAdmin.Password").trim();
				Coachesusername1=urlsProperties.getProperty(			"Zado.Coaches.username1").trim();
				Coachespassword1=urlsProperties.getProperty(			"Zado.Coaches.password1").trim();
				Coachesusername2=urlsProperties.getProperty(			"Zado.Coaches.username2").trim();
				Coachespassword2=urlsProperties.getProperty(			"Zado.Coaches.password2").trim();
				Coachesusername3=urlsProperties.getProperty(			"Zado.Coaches.username3").trim();
				Coachespassword3=urlsProperties.getProperty(			"Zado.Coaches.password3").trim();
				OpsAdminusername=urlsProperties.getProperty(			"Zado.OpsAdmin.username").trim();
				OpsAdminpassword=urlsProperties.getProperty(			"Zado.OpsAdmin.password").trim();		
				Memberusername1=urlsProperties.getProperty(			"Zado.Member.username1").trim();	
				Memberpassword1=urlsProperties.getProperty(			"Zado.member.password1").trim();
				Memberusername2=urlsProperties.getProperty(			"Zado.Member.username2").trim();	
				Memberpassword2=urlsProperties.getProperty(			"Zado.member.password2").trim();
				MailDropurl=urlsProperties.getProperty(			"zado.proj.mailDrop").trim();  
				Lecturememberusername=urlsProperties.getProperty(			"Zado.Member.Lecture.username").trim();	
				Lecturememberpassword=urlsProperties.getProperty(			"Zado.menber.Lecture.password").trim();
				PAwellnesscorpusername=urlsProperties.getProperty(         "Zado.PA.Wellnesscorp.username").trim(); 
				PAwellnesscorppassword=urlsProperties.getProperty(         "Zado.PA.Wellnesscorp.password").trim(); 
				Guerrillamailurl=urlsProperties.getProperty(         "zado.proj.GuerrillaMail").trim();
				Sessionmemberusername=urlsProperties.getProperty(			"Zado.Member.Session.username").trim();	
				Sessionmemberpassword=urlsProperties.getProperty(			"Zado.menber.Session.password").trim();
				AppendURL=urlsProperties.getProperty(			"Zado.url.Member.AppendURL").trim();
				liveSessionMemberUsername=urlsProperties.getProperty(			"Zado.Member.liveSessionMemberUsername").trim();	
				liveSessionMemberPassword=urlsProperties.getProperty(			"Zado.menber.liveSessionMemberPassword").trim();
				CoachAppendURL=urlsProperties.getProperty(       "Zado.url.Coach.AppendURL").trim();             
				Oracle_Port=urlsProperties.getProperty(       "Oracle.Port").trim();
				Oracle_Databasename=urlsProperties.getProperty(       "Oracle.Databasename").trim();
				Oracle_User=urlsProperties.getProperty(       "Oracle.User").trim();
				Oracle_Pass=urlsProperties.getProperty(       "Oracle.Pass").trim();
				Oracle_Hostname=urlsProperties.getProperty(       "Oracle.Hostname").trim();
				Sql_Port=urlsProperties.getProperty(       "Sql.Port").trim();
				Sql_Databasename=urlsProperties.getProperty(       "Sql.Databasename").trim();
				Sql_User=urlsProperties.getProperty(       "Sql.User").trim();
				Sql_Pass=urlsProperties.getProperty(       "Sql.Pass").trim();
				Sql_Hostname=urlsProperties.getProperty(       "Sql.Hostname").trim();
				PAapolloEndousername=urlsProperties.getProperty(         "Zado.PA.ApolloEndo.username").trim(); 
				PAapolloEndopassword=urlsProperties.getProperty(         "Zado.PA.ApolloEndo.password").trim();
				RA_Member_Url=urlsProperties.getProperty(         "Zado.Ra.url.MemberUrl").trim();
				RA_Provider_Url=urlsProperties.getProperty(         "Zado.Ra.url.ProviderUrl").trim();
				RA_Member_Username1=urlsProperties.getProperty(         "Zado.Ra.Member.username1").trim();
				RA_Member_Password1=urlsProperties.getProperty(         "Zado.Ra.member.password1").trim();
				RA_Coach_Username1=urlsProperties.getProperty(         "Zado.Ra.coach.username1").trim();
				RA_Coach_Pasword1=urlsProperties.getProperty(         "Zado.Ra.coach.password1").trim();
				RA_PA_Username1=urlsProperties.getProperty(         "Zado.Ra.ProgramAdmin.username1").trim();
				RA_PA_Pasword1=urlsProperties.getProperty(         "Zado.Ra.ProgramAdmin.password1").trim();
				//Mobile Configuration
				String MOBILE_APP_PATH=localProperties.getProperty(   "zado.mobileapp.apk.path").trim();
				MOBILE_APPPATH=uploadFilePath+MOBILE_APP_PATH;
				MOBILEAPP_APK_NAME=localProperties.getProperty(   "zado.mobile.apk.name").trim();
				MOBILE_DEVICE_NAME=localProperties.getProperty(   "zado.mobile.devicename").trim();
				MOBILE_DEVICE_VERSION=localProperties.getProperty(   "zado.mobile.version").trim();
				MOBILE_APK_APPPACKAGE=localProperties.getProperty(   "zado.mobile.apppackage.name").trim();
				MOBILE_APP_TYPE = localProperties.getProperty(   "zado.mobile.app.type").trim();
				MOBILE_WEB_BROWSER_NAME = localProperties.getProperty(   "zado.mobile.web.browser").trim();
				MOBILE_WEB_URL = localProperties.getProperty(   "zado.mobile.web.Url").trim();
				MOBILE_DEVICE_TYPE = localProperties.getProperty(   "zado.mobile.device.type").trim();
				MOBILE_IOSDEVICE_UDID = localProperties.getProperty(   "zado.ios.mobile.udid").trim();
				GRID_IP = localProperties.getProperty(			"zado.Grid.name").trim().toLowerCase();
				
				RAMemberAppendURL=urlsProperties.getProperty("Zado.Ra.url.MemberAppendUrl").trim();
				RACoachAppendURL=urlsProperties.getProperty("Zado.Ra.url.CoachAppendUrl").trim();
				AllSessioncoachesusername=urlsProperties.getProperty(			"Zado.AllSession.Coaches.username").trim();
				AllSessioncoachespassword=urlsProperties.getProperty(			"Zado.AllSession.Coaches.password").trim();
				Epochurl=urlsProperties.getProperty(			"Zado.epoch.url").trim();
				RA_Enrollment_Insurance_Provider=urlsProperties.getProperty("RA.Enrollment.Insurance.Provider").trim();
				RA_Enrollment_Member_Id=urlsProperties.getProperty("RA.Enrollment.Member.Id").trim();
				RA_Enrollment_Group_Member=urlsProperties.getProperty("RA.Enrollment.Group.Number").trim();
				RA_Enrollment_First_Name=urlsProperties.getProperty("RA.Enrollment.First.Name").trim();
				RA_Enrollment_Last_Name=urlsProperties.getProperty("RA.Enrollment.Last.Name").trim();
				RA_Enrollment_Month=urlsProperties.getProperty("RA.Enrollment.Month").trim();
				RA_Enrollment_Day=urlsProperties.getProperty("RA.Enrollment.Day").trim();
				RA_Enrollment_Year=urlsProperties.getProperty("RA.Enrollment.Year").trim();
				Client2_RA_Enrollment_Member_Id=urlsProperties.getProperty("Client2.RA.Enrollment.Member.Id").trim();
				Client2_RA_Enrollment_Group_Number=urlsProperties.getProperty("Client2.RA.Enrollment.Group.Number").trim();
				Client2_RA_Enrollment_Last_Name=urlsProperties.getProperty("Client2.RA.Enrollment.Last.Name").trim();
				Client2_RA_Enrollment_First_Name=urlsProperties.getProperty("Client2.RA.Enrollment.First.Name").trim();
				Client2_RA_Enrollment_Insurance_Provider=urlsProperties.getProperty("Client2.RA.Enrollment.Insurance.Provider").trim();
				Client1_RA_Organization_ID=urlsProperties.getProperty("Client1.RA.OrganizationID").trim();
				Client2_RA_Organization_ID=urlsProperties.getProperty("Client2.RA.OrganizationID").trim();
				Client2_RA_Enrollment_Month=urlsProperties.getProperty("Client2.RA.Enrollment.Month").trim();
				Client2_RA_Enrollment_Day=urlsProperties.getProperty("Client2.RA.Enrollment.Day").trim();
				Client2_RA_Enrollment_Year=urlsProperties.getProperty("Client2.RA.Enrollment.Year").trim();
				RA_Enrollment_Organization_Name=urlsProperties.getProperty("RA.Enrollment.Organization.Name").trim();
				UseCaseOne_RA_Enrollment_Month=urlsProperties.getProperty("UseCaseOne.RA.Enrollment.Month").trim();
				UseCaseOne_RA_Enrollment_Day=urlsProperties.getProperty("UseCaseOne.RA.Enrollment.Day").trim();
				UseCaseOne_RA_Enrollment_Year=urlsProperties.getProperty("UseCaseOne.RA.Enrollment.Year").trim();
				UseCaseOne_RA_Enrollment_Member_Id=urlsProperties.getProperty("UseCaseOne.RA.Enrollment.Member.Id").trim();
				UseCaseOne_RA_Enrollment_Group_Number=urlsProperties.getProperty("UseCaseOne.RA.Enrollment.Group.Number").trim();
				UseCaseOne_RA_Enrollment_Last_Name=urlsProperties.getProperty("UseCaseOne.RA.Enrollment.Last.Name").trim();
				UseCaseOne_RA_Enrollment_First_Name=urlsProperties.getProperty("UseCaseOne.RA.Enrollment.First.Name").trim();
				UseCaseOne_RA_Enrollment_Insurance_Provider=urlsProperties.getProperty("UseCaseOne.RA.Enrollment.Insurance.Provider").trim();
				RA_UseCaseOneCustomEnroll_Member_Url=urlsProperties.getProperty(         "Zado.Ra.url.UseCaseOne.Custom.MemberUrl").trim();
				UseCaseTwo_RA_Enrollment_Month=urlsProperties.getProperty("UseCaseTwo.RA.Enrollment.Month").trim();
				UseCaseTwo_RA_Enrollment_Day=urlsProperties.getProperty("UseCaseTwo.RA.Enrollment.Day").trim();
				UseCaseTwo_RA_Enrollment_Year=urlsProperties.getProperty("UseCaseTwo.RA.Enrollment.Year").trim();
				UseCaseTwo_RA_Enrollment_Member_Id=urlsProperties.getProperty("UseCaseTwo.RA.Enrollment.Member.Id").trim();
				UseCaseTwo_RA_Enrollment_Group_Number=urlsProperties.getProperty("UseCaseTwo.RA.Enrollment.Group.Number").trim();
				UseCaseTwo_RA_Enrollment_Last_Name=urlsProperties.getProperty("UseCaseTwo.RA.Enrollment.Last.Name").trim();
				UseCaseTwo_RA_Enrollment_First_Name=urlsProperties.getProperty("UseCaseTwo.RA.Enrollment.First.Name").trim();
				UseCaseTwo_RA_Enrollment_Insurance_Provider=urlsProperties.getProperty("UseCaseTwo.RA.Enrollment.Insurance.Provider").trim();
				RA_UseCaseTwoCustomEnroll_Member_Url=urlsProperties.getProperty(         "Zado.Ra.url.UseCaseTwo.Custom.MemberUrl").trim();
				UseCaseThree_RA_Enrollment_Month=urlsProperties.getProperty("UseCaseThree.RA.Enrollment.Month").trim();
				UseCaseThree_RA_Enrollment_Day=urlsProperties.getProperty("UseCaseThree.RA.Enrollment.Day").trim();
				UseCaseThree_RA_Enrollment_Year=urlsProperties.getProperty("UseCaseThree.RA.Enrollment.Year").trim();
				UseCaseThree_RA_Enrollment_Member_Id=urlsProperties.getProperty("UseCaseThree.RA.Enrollment.Member.Id").trim();
				UseCaseThree_RA_Enrollment_Group_Number=urlsProperties.getProperty("UseCaseThree.RA.Enrollment.Group.Number").trim();
				UseCaseThree_RA_Enrollment_Last_Name=urlsProperties.getProperty("UseCaseThree.RA.Enrollment.Last.Name").trim();
				UseCaseThree_RA_Enrollment_First_Name=urlsProperties.getProperty("UseCaseThree.RA.Enrollment.First.Name").trim();
				UseCaseThree_RA_Enrollment_Insurance_Provider=urlsProperties.getProperty("UseCaseThree.RA.Enrollment.Insurance.Provider").trim();
				RA_UseCaseThreeCustomEnroll_Member_Url=urlsProperties.getProperty(         "Zado.Ra.url.UseCaseThree.Custom.MemberUrl").trim();
				UseCaseFourUnitedHeath_RA_Enrollment_Month=urlsProperties.getProperty("UseCaseFourUnitedHeath.RA.Enrollment.Month").trim();
				UseCaseFourUnitedHeath_RA_Enrollment_Day=urlsProperties.getProperty("UseCaseFourUnitedHeath.RA.Enrollment.Day").trim();
				UseCaseFourUnitedHeath_RA_Enrollment_Year=urlsProperties.getProperty("UseCaseFourUnitedHeath.RA.Enrollment.Year").trim();
				UseCaseFourUnitedHeath_RA_Enrollment_Member_Id=urlsProperties.getProperty("UseCaseFourUnitedHeath.RA.Enrollment.Member.Id").trim();
				UseCaseFourUnitedHeath_RA_Enrollment_Group_Number=urlsProperties.getProperty("UseCaseFourUnitedHeath.RA.Enrollment.Group.Number").trim();
				UseCaseFourUnitedHeath_RA_Enrollment_Last_Name=urlsProperties.getProperty("UseCaseFourUnitedHeath.RA.Enrollment.Last.Name").trim();
				UseCaseFourUnitedHeath_RA_Enrollment_First_Name=urlsProperties.getProperty("UseCaseFourUnitedHeath.RA.Enrollment.First.Name").trim();
				UseCaseFourUnitedHeath_RA_Enrollment_Insurance_Provider=urlsProperties.getProperty("UseCaseFourUnitedHeath.RA.Enrollment.Insurance.Provider").trim();
				RA_UseCaseFourUnitedHeathCustomEnroll_Member_Url=urlsProperties.getProperty(         "Zado.Ra.url.UseCaseFourUnitedHeath.Custom.MemberUrl").trim();
				UseCaseFourClient2_RA_Enrollment_Month=urlsProperties.getProperty("UseCaseFourClient2.RA.Enrollment.Month").trim();
				UseCaseFourClient2_RA_Enrollment_Day=urlsProperties.getProperty("UseCaseFourClient2.RA.Enrollment.Day").trim();
				UseCaseFourClient2_RA_Enrollment_Year=urlsProperties.getProperty("UseCaseFourClient2.RA.Enrollment.Year").trim();
				UseCaseFourClient2_RA_Enrollment_Member_Id=urlsProperties.getProperty("UseCaseFourClient2.RA.Enrollment.Member.Id").trim();
				UseCaseFourClient2_RA_Enrollment_Group_Number=urlsProperties.getProperty("UseCaseFourClient2.RA.Enrollment.Group.Number").trim();
				UseCaseFourClient2_RA_Enrollment_Last_Name=urlsProperties.getProperty("UseCaseFourClient2.RA.Enrollment.Last.Name").trim();
				UseCaseFourClient2_RA_Enrollment_First_Name=urlsProperties.getProperty("UseCaseFourClient2.RA.Enrollment.First.Name").trim();
				UseCaseFourClient2_RA_Enrollment_Insurance_Provider=urlsProperties.getProperty("UseCaseFourClient2.RA.Enrollment.Insurance.Provider").trim();
				RA_UseCaseFourClient2CustomEnroll_Member_Url=urlsProperties.getProperty(         "Zado.Ra.url.UseCaseFourClient2.Custom.MemberUrl").trim();
				UseCaseFourClient3_RA_Enrollment_Month=urlsProperties.getProperty("UseCaseFourClient3.RA.Enrollment.Month").trim();
				UseCaseFourClient3_RA_Enrollment_Day=urlsProperties.getProperty("UseCaseFourClient3.RA.Enrollment.Day").trim();
				UseCaseFourClient3_RA_Enrollment_Year=urlsProperties.getProperty("UseCaseFourClient3.RA.Enrollment.Year").trim();
				UseCaseFourClient3_RA_Enrollment_Member_Id=urlsProperties.getProperty("UseCaseFourClient3.RA.Enrollment.Member.Id").trim();
				UseCaseFourClient3_RA_Enrollment_Group_Number=urlsProperties.getProperty("UseCaseFourClient3.RA.Enrollment.Group.Number").trim();
				UseCaseFourClient3_RA_Enrollment_Last_Name=urlsProperties.getProperty("UseCaseFourClient3.RA.Enrollment.Last.Name").trim();
				UseCaseFourClient3_RA_Enrollment_First_Name=urlsProperties.getProperty("UseCaseFourClient3.RA.Enrollment.First.Name").trim();
				UseCaseFourClient3_RA_Enrollment_Insurance_Provider=urlsProperties.getProperty("UseCaseFourClient3.RA.Enrollment.Insurance.Provider").trim();
				RA_UseCaseFourClient3CustomEnroll_Member_Url=urlsProperties.getProperty(         "Zado.Ra.url.UseCaseFourClient3.Custom.MemberUrl").trim();
				PracticePAusername=urlsProperties.getProperty(   "Zado.PracticePA.username").trim();
			    PracticePApassword=urlsProperties.getProperty(   "Zado.PracticePA.password").trim();
			    PracticePA2username=urlsProperties.getProperty(   "Zado.PracticePA2.username").trim();
			    PracticePA2password=urlsProperties.getProperty(   "Zado.PracticePA2.password").trim();
			    PracticePA2name=urlsProperties.getProperty(   "Zado.PracticePA2.name").trim();
			    RA_Prov_Org=urlsProperties.getProperty(   "Zado.Ra.coach.org").trim();
			    PracticePAABCusername=urlsProperties.getProperty(   "Zado.PracticePAABC.username").trim();
			    PracticePAABCpassword=urlsProperties.getProperty(   "Zado.PracticePAABC.password").trim();
			    ProgramadminKidneyusername=urlsProperties.getProperty(   "Zado.ProgramAdmin.kidney.username").trim();
			    KidneyMemberusername1=urlsProperties.getProperty(   "Zado.Member.username3.kidney").trim();
			    Orbera_Configurablecolumn_OrganizationID=urlsProperties.getProperty("Orbera.Configurablecolumn.OrganizationID").trim();
			    Orbera_UserAlert_AbcPractice_OrganizationID=urlsProperties.getProperty("Orbera.UserAlert.AbcPractice.OrganizationID").trim();
			    RA_Member_username2=urlsProperties.getProperty(   "Zado.Ra.Member.username2").trim();
			    RA_Member_username3=urlsProperties.getProperty(   "Zado.Ra.programcompleted.username3").trim();
			    RA_Member_Client=urlsProperties.getProperty(   "Zado.Ra.Member.Client").trim();
			    MDAC_Jira_Url=urlsProperties.getProperty(         "Zado.url.MDACJiraURL").trim();
			    MDAC_Jira_Username=urlsProperties.getProperty(         "Zado.MDAC.username").trim();
			    MDAC_Jira_Password=urlsProperties.getProperty(         "Zado.MDAC.password").trim();
			    RA_Member_username4=urlsProperties.getProperty(   "Zado.Ra.pwdreset.username4").trim();
			    RA_Member_username5=urlsProperties.getProperty(   "Zado.Ra.Member.username5").trim();
			    T2_Coach_Username2=urlsProperties.getProperty(   "Zado.t2.coach.username2").trim();
			    T2_Coach_Username1=urlsProperties.getProperty(   "Zado.t2.coach.username1").trim();
			    Mast_ProgramId=urlsProperties.getProperty(   "Mast.ProgramID").trim();
				T2_EnrollmentBatchCount = localProperties.getProperty(			"preventT2.enrollment.count").trim();
				T2_SFTPUsername=urlsProperties.getProperty(   "Zado.t2.sftp.username").trim();
				T2_SFTPPassword=urlsProperties.getProperty(   "Zado.t2.sftp.password").trim();
				T2_SFTPHostName=urlsProperties.getProperty(   "Zado.t2.sftp.hostname").trim();
				T2_SFTPChannel=urlsProperties.getProperty(   "Zado.t2.sftp.channel").trim();
				try {
					if ((headerText != null) && (headerText.length() > 0)) {
						ReportLabels.HEADER_TEXT.setLabel(headerText);
					}
					if ((reExecution != null) && (reExecution.length() > 0)) {
						try {
							reexecution = Boolean.parseBoolean(reExecution);
						} catch (Exception localException1) {
						}
					}
					if ((takeScreenShot != null) && (takeScreenShot.length() > 0)) {
						try {
							takeScreenshot = Boolean.parseBoolean(takeScreenShot);
						} catch (Exception localException1) {
						}
					}
					if ((configReports != null) && (configReports.length() > 0)) {
						try {
							generateConfigReports = Boolean.parseBoolean(configReports);
						} catch (Exception localException2) {
						}
					}
					if ((excelReport != null) && (excelReport.length() > 0)) {
						try {
							generateExcelReports = Boolean.parseBoolean(excelReport);
						} catch (Exception localException3) {
						}
					}
					if ((contExectution != null) && (contExectution.length() > 0)) {
						try {
							continueExecutionAfterStepFailed = Boolean
									.parseBoolean(contExectution);
						} catch (Exception localException4) {
						}
					}
					/*if ((recordExecution != null) && (recordExecution.length() > 0)) {
			try {
			    RecordingFor localRecordingFor = RecordingFor
				    .valueOf(recordExecution.toUpperCase());
			    if (localRecordingFor == RecordingFor.SUITE) {
				recordSuiteExecution = true;
			    } else if (localRecordingFor == RecordingFor.TESTMETHOD) {
				recordTestMethodExecution = true;
			    }
			} catch (Throwable localThrowable) {
			}
		    }
					File file = new File(reportsDir);
					if(file.exists())
					{
						FileUtils.cleanDirectory(file); //clean out directory (this is optional -- but good know)
			            FileUtils.forceDelete(file);
						System.out.println("Report directory deleted");
					}*/
					File outputFile = new File(Directory.Zipfolder_Path);
				     if(!outputFile.exists()){
				      outputFile.mkdir();
				     }
					
					
					if ((projectDescription != null) && (projectDescription.length() > 0)) {
						ZadoReports.indexPageDescription = projectDescription;
					}
					if ((reportsDir != null) && (reportsDir.length() > 0)) {
						REPORTSDir = reportsDir;
						REPORTSDIRName = new File(REPORTSDir).getName();
						RESULTSDir = REPORTSDir + SEP + "Results";
						HTMLDESIGNDIRName = "HTML_Design_Files";
						HTMLDESIGNDir = REPORTSDir + SEP + HTMLDESIGNDIRName;
						CSSDIRName = "CSS";
						CSSDir = HTMLDESIGNDir + SEP + CSSDIRName;
						IMGDIRName = "IMG";
						IMGDir = HTMLDESIGNDir + SEP + IMGDIRName;
						JSDIRName = "JS";
						JSDir = HTMLDESIGNDir + SEP + JSDIRName;
						RUNName = "Run_";
						RUNDir = RESULTSDir + SEP + RUNName;
						SETTINGSFile = RESULTSDir + SEP + "Settings.properties";
					}
				} catch (Exception localException5) {
					throw new ZadoReporterException(localException5.toString());
				}
			} catch (FileNotFoundException localFileNotFoundException) {
				throw new ZadoReporterException(
						"The Path for the Custom Properties file could not be found", localFileNotFoundException);
			} catch (IOException localIOException) {
				throw new ZadoReporterException(
						"Problem Occured while reading the Zado Reporter Config File");
			}
		}
	}

	public static void mkDirs(String paramString) {
		File localFile = new File(paramString);
		if (!localFile.exists()) {
			localFile.mkdirs();
		}
	}

	public static boolean exists(String paramString) {
		File localFile = new File(paramString);
		return localFile.exists();
	}
	/**
	 * Verifying required files for report generation
	 * @throws Exception 
	 */
	public static void verifyRequiredFiles() throws Exception {
		init();
		log.info("Setting Reports Dir---"+REPORTSDir);
		log.info("Setting Results Dir---"+RESULTSDir);
		mkDirs(REPORTSDir);
		if (!exists(RESULTSDir)) {
			mkDirs(RESULTSDir);
			SettingsFile.initSettingsFile();
		}
		if (!exists(HTMLDESIGNDir)) {
			mkDirs(HTMLDESIGNDir);
			mkDirs(CSSDir);
			mkDirs(JSDir);
			mkDirs(IMGDir);
			HTMLDesignFilesWriter.writeCSS();
			HTMLDesignFilesWriter.writeIMG();
			HTMLDesignFilesWriter.writeJS();
		}
		if ((logo != null) && (logo.length() > 0)) {
			String str = new File(logo).getName();
			if (!new File(IMGDir + SEP + str).exists()) {
				copyImage(logo);
			}
			ReportLabels.PROJ_LOGO.setLabel(str);
		}
	}

	private static void copyImage(String paramString) throws ZadoReporterException {
		File localFile = new File(paramString);
		if (!localFile.exists()) {
			return;
		}
		FileImageInputStream localFileImageInputStream = null;
		FileImageOutputStream localFileImageOutputStream = null;
		try {
			localFileImageInputStream = new FileImageInputStream(new File(
					paramString));
			localFileImageOutputStream = new FileImageOutputStream(new File(
					IMGDir + SEP + localFile.getName()));
			int i = 0;
			while ((i = localFileImageInputStream.read()) >= 0) {
				localFileImageOutputStream.write(i);
			}
			localFileImageOutputStream.close();
			return;
		} catch (Exception localException2) {
		} finally {
			try {
				localFileImageInputStream.close();
				localFileImageOutputStream.close();
				localFile = null;
			} catch (Exception localException4) {
				localFileImageInputStream = null;
				localFileImageOutputStream = null;
				localFile = null;
			}
		}
	}

	public static String getCustomProperties() {
		return System.getProperty("zado.reporter.config");
	}

	public static String createTestRunDateTime() {
		Calendar cal = Calendar.getInstance();
		return DateFormatUtils.format(cal, "dd-MM-yy, hh.mm aa");
	}

	public static String getTestRunDateTime(int run) {
		try {
			String testRun = SettingsFile.get("testRunDT");
			String timeArray [] = testRun.split(";");
			return timeArray[run-1];
		} catch (ZadoReporterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return " ";
	}
}