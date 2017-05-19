package com.zillion.qa.util;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.zillion.qa.commands.ElementActions;
import com.zillion.qa.commands.IOSActions;
import com.zillion.qa.commands.LocatorBy;
import com.zillion.qa.commands.Manipulation;
import com.zillion.qa.commands.Navigate;
import io.appium.java_client.ios.IOSDriver;

/**
 * Common methods for all kind of actions (Selenium Actions, CrowdTwist specific common methods)
 * @author Babu
 *
 */
public class IOSCommandUtils {

	public WebElement element;
	public String normalXpath;
	Object returnObj = null;
	public WebElement element1;
	public WebElement element2;
	public static String getText = "";
	public static String getSize = "";
	public static String getText1 = "";
	public static HashMap<Integer, String> getTextMap = new HashMap<Integer, String>();
	public static String[] widgetUrlCount=new String[100];
	public static int widgetUrls=0;
	public static String[] splitInputData;
	/**
	 * Locators
	 * @param driver
	 * @param locateBy
	 * @param orLocator
	 * @return
	 */

	public WebElement findElement(WebDriver driver, String locateBy,
			String orLocator, String orLocatorStart, String orLocatorEnd, String referenceStep)  {

		switch (locateBy) {
		case "ByID":
			element = LocatorBy.locateById(driver, orLocator);
			break;
		case "DeleteCookies":
			Navigate.deleteAllCookies(driver);
			break;
		case "ByName":
			element = LocatorBy.locateByName(driver, orLocator);
			break;
		case "ByOrName":
			element = LocatorBy.locateByOrName(driver, orLocator);
			break;
		case "ByXPath":
			element = LocatorBy.locateByXPath(driver, orLocator);
			break;
		case "ByLinkText":
			element = LocatorBy.locateByLinkText(driver, orLocator);
			break;
		case "ByTagName":
			element = LocatorBy.locateByTagName(driver, orLocator);
			break;
		case "ByClassName":
			element = LocatorBy.locateByClassName(driver, orLocator);
			break;
		case "ByCssSelector":
			element = LocatorBy.locateByCssSelector(driver, orLocator);
			break;
		case "ByPartialLinkText":
			element = LocatorBy.locateByPartialLinkText(driver, orLocator);
			break;
		case "Xpath":
			normalXpath = LocatorBy.linkCount(driver, orLocator);
			break;
		case "ByXpaths":
			element1 = LocatorBy.locateByXPath(driver, orLocatorStart);
			element2 = LocatorBy.locateByXPath(driver, orLocatorEnd);
			break;
		default:
			break;
		}
		return element;
	}

	/**
	 * Common selenium Actions and CrowdTwist actions
	 * @param driver
	 * @param element
	 * @param action
	 * @param inputData
	 * @param stepNo
	 * @param referenceStep
	 * @return
	 * @throws Exception
	 */

	public Object executeAction(IOSDriver<?> driver, WebElement element,
			String action, String inputData, int stepNo, String referenceStep ) throws Exception {
		//Object returnObj = null;
		switch (action) {

		case "GetUrl":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals("")) {
				int refStep = new Integer(referenceStep);
				String getText=getTextMap.get(Integer.valueOf(refStep));
				Navigate.get(driver, getText);
			}
			else
			{
				Navigate.get(driver, inputData);
			}
			break;
		case "NavigateToURL":
			Navigate.navigateUrl(driver,inputData);
			break;	
		case "Wait":
			// Manipulation.wait(driver, inputData);
			break;
		case "WaitTime":
			Navigate.waitTime(driver, inputData);
			break;
		case "Maximize":
			Navigate.maximize(driver);
			break;
		case "Click":
			Manipulation.click(element);
			break;
		case "ActionClick":
			Manipulation.actionClick(driver,element);
			break;
		case "JsClick":
			Manipulation.jsClickByXPath(driver, normalXpath);
			break;
		case "JsTypeByXPath":
			Manipulation.jsTypeByXPath(driver, normalXpath,inputData);
			break;
		case "DoubleClick":
			Manipulation.doubleClick(driver, element);
			break;
		case "ClickAt":
			String[] coordinates = StringUtils.split(inputData, ",");
			int x = new Integer(coordinates[0]);
			int y = new Integer(coordinates[1]);
			Manipulation.clickAt(driver, element, x, y);
			break;
		case "ClickAndHold":
			Manipulation.clickAndHold(driver, element);
			break; 
		case "Clear":
			Manipulation.clear(element);
			break;
		case "ActionClear":
			Manipulation.actionClear(driver, element);
			break;
		case "Type":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals(""))
			{
				int refStep = new Integer(referenceStep);
				String gettext= getTextMap.get(Integer.valueOf(refStep));
				returnObj=Manipulation.sendKeys(element, gettext);
				getTextMap.put(stepNo, returnObj.toString());
			}
			else
			{
				returnObj=Manipulation.sendKeys(element, inputData);
				getTextMap.put(stepNo, returnObj.toString());
			}
			break;
		case "ClearAndType":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals(""))
			{
				int refStep = new Integer(referenceStep);
				inputData= getTextMap.get(Integer.valueOf(refStep));
			}
			Manipulation.clearAndType(element,inputData);
			break;
		case "ActionType":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals(""))
			{
				int refStep = new Integer(referenceStep);
				inputData= getTextMap.get(Integer.valueOf(refStep));
			}
			Manipulation.actionType(driver,element, inputData);
			break;
		case "TypeDynamicValue":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals(""))
			{
				int refStep = new Integer(referenceStep);
				inputData= getTextMap.get(Integer.valueOf(refStep));
			}
			returnObj = Manipulation.dynamicSendkeys(driver ,inputData, element);
			getTextMap.put(stepNo, returnObj.toString());
			break;	
		case "Submit":
			Manipulation.submit(element);
			break;
		case "MouseOver":
			Manipulation.mouseOver(driver, element);
			break;
		case "MouseOverAndClick":
			Manipulation.mouseOverAndClick(driver, element);
			break;
		case "GetText":
			returnObj = ElementActions.getText(element);
			getText = returnObj.toString();	
			getText1= getText.replace("...", "");
			returnObj = getText1;
			getTextMap.put(stepNo, returnObj.toString());				
			break;
		case "GetAttribute":
			returnObj = ElementActions.getAttribute(element, inputData);
			getTextMap.put(stepNo, returnObj.toString());
			break;		
		case "GetCount":
			returnObj = Manipulation.linkCounts(driver, normalXpath);			
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "GetCurrentURL":
			returnObj = Manipulation.getCurrentURL(driver);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "SelectCheckBox":
			Manipulation.selectCheckBox(element);
			break;		
		case "SelectByIndex":
			Manipulation.selectByIndex(element, inputData);
			break;
		case "SelectByValue":
			Manipulation.selectByValue(element, inputData);
			break;
		case "SelectByVisibleText":		
			if (inputData == null && referenceStep != null && !referenceStep.trim().equals("")) {
				int refStep1 = new Integer(referenceStep);			
				String getText1 = getTextMap.get(Integer.valueOf(refStep1));				
				returnObj = Manipulation.selectByVisibletext(element,getText1);
			} else {				
				returnObj = Manipulation.selectByVisibletext(element,inputData);
			}
			break;
		case "DeSelectCheckBox":
			Manipulation.deSelectCheckBox(element);
			break;
		case "DeSelectByIndex":
			Manipulation.deSelectByIndex(element, inputData);
			break;
		case "DeSelectByValue":
			Manipulation.deSelectByValue(element, inputData);
			break;
		case "DeSelectByVisibleText":
			if (inputData == null && referenceStep != null && !referenceStep.trim().equals("")) 
			{
				int refStep1 = new Integer(referenceStep);			
				String getText1 = getTextMap.get(Integer.valueOf(refStep1));				
				Manipulation.deSelectByVisibletext(element, getText1);
			} 
			else {
				Manipulation.deSelectByVisibletext(element, inputData);
			}
			break;			
		case "SwitchFrameByName":
			Navigate.switchToFrame(driver, inputData);
			break;
		case "SwitchFrameByIndex":
			int index = new Integer(inputData);
			Navigate.switchToFrame(driver, index);
			break;
		case "SwitchFrameByXpath":
			Navigate.switchToFrame(driver, element);
			break;
		case "SwitchFrame":
			Navigate.switchToFrame(driver, element);
			break;
		case "SwitchToDefaultFrame":			
			Navigate.switchToDefaultFrame(driver);
			break;			
		case "Refresh":
			Navigate.refreshPage(driver);
			break;
		case "Back":
			Navigate.goBack(driver);
			break;
		case "Forward":
			Navigate.goForward(driver);
			break;
		case "AlertOk":
			returnObj = Navigate.alertOk(driver, element);
			break;	    
		case "DismissAlert":
			Navigate.dismissAlert(driver);
			break;  	    
		case "AlertDismiss":
			returnObj = Navigate.alertDismiss(driver, element);
			break;
		case "PromptBox":
			returnObj = Navigate.promptBox(driver, element, inputData);
			break;		
		case "GenerateAlert":
			Navigate.alertGenerate(driver,inputData);
			break;			
		case "Close":
			Navigate.close(driver);
			break;		
		default:
			break;
		case "GetWindowHandle":
			Manipulation.getWindow(driver, element);
			break;
		case "SwitchToDefaultWindow":
			Manipulation.switchWindow(driver);
			break;	
		case "SwitchToDefaultContent":
			Manipulation.switchDefaultContent(driver);
			break;
		case "GetAutoIt":
			Manipulation.getAutoit(driver, inputData);
			break;
		case "ScrollDown":
			Navigate.pageDown(driver);
			break;
		case "ScrollUp":
			Navigate.pageUp(driver);
			break;
		case "ScrollBottom":
			Navigate.scrollBottom(driver);
			break;      
		case "KeyboardPageUp":
			Navigate.keyboardPageUp(driver);
			break;
		case "KeyboardPageDown":
			Navigate.keyboardPageDown(driver);
			break;
		case "KeyboardEnd":
			Navigate.keyboardEnd(driver);
			break;	
		case "KeyboardTab":
			Navigate.keyboardTab(driver);
			break;		
		case "PageMaximize":
			Navigate.pageMaximize(driver);
			break;	
		case "Enter":
			Navigate.enter(driver);
			break;
		case "RobotEnter":
			Navigate.robotEnter(driver);
			break;
		case "Esc":
			Navigate.esc(driver);
			break;
		case "KeyboardArrowUp":
			Navigate.keyboardArrowUp(driver);
			break;	
		case "KeyboardArrowDown":
			Navigate.keyboardArrowDown(driver);
			break;	
		case "KeyboardArrowLeft":
			Navigate.keyboardArrowLeft(driver);
			break;	
		case "KeyboardArrowRight":
			Navigate.keyboardArrowRight(driver);
			break;			
		case "Drag":
			Manipulation.dragElement(driver, element);
			break;
		case "Drop":
			Manipulation.dropElement(driver, element);
			break;		
		case "VerifyElementIsSelected":
			Manipulation.elementIsSelected(driver, element);
			break;
		case "VerifyElementIsPresent":
			Manipulation.verifyElementIsPresent(driver, element);
			break;
		case "VerifyElementIsNotPresent":
			returnObj = Manipulation.verifyElementIsNotPresent(driver, element);
			break;
		case "VerifyElementIsEnable":
			Manipulation.elementIsEnable(driver, element);
			break;		
		case "WaitUntilVisibilityOfElement":
			Manipulation.visibilityElement(driver, element);
			break;	
		case "WaitUntilInvisibilityOfElement":
			Manipulation.inVisibilityElement(driver, normalXpath);
			break;			
		case "VerifyTextIsPresent":
			Manipulation.testIsPresent(driver,element, inputData);
			break;	
		case "WaitUntilTextToBeNotPresent":
			Manipulation.testIsNotPresent(driver,normalXpath, inputData);
			break;		
		case "WaitUntilTextToBePresent":
			Manipulation.textTobePresent(driver,element, inputData);
			break;	
		case "WaitUntilElementToBeClickable":
			Manipulation.elementTobeClickable(driver,element);
			break;	
		case "WaitUntilElementToBeSelected":
			Manipulation.elementToBeSelected(driver,element);
			break;	
		case "TextToBePresentInElementValue":
			Manipulation.textElementPresentValue(driver,element,inputData);
			break;		
		case "WaitForElementPresent":
			Manipulation.waitForElement(driver, normalXpath);
			break;
		case "WaitForElementNotPresent":
			Manipulation.waitForElementNotpresent(driver,normalXpath);
			break;	
		case "CheckTwoString":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals("")) {
				String[] referenceSteps = StringUtils.split(referenceStep, ",");
				int refStep1 = new Integer(referenceSteps[0]);
				int refStep2 = new Integer(referenceSteps[1]);
				String getText1 = getTextMap.get(Integer.valueOf(refStep1));
				String getText2 = getTextMap.get(Integer.valueOf(refStep2));
				returnObj = Manipulation.condtionMatch(getText1, getText2);
			}
			else if(inputData != null && referenceStep != null
					&& !referenceStep.trim().equals("")){            	
				int refStep1 = new Integer(referenceStep);               
				String getText1 = getTextMap.get(Integer.valueOf(refStep1));               
				returnObj = Manipulation.condtionMatch(getText1, inputData);
			}
			break;
		case "CheckStringNotMatched":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals("")) {
				String[] referenceSteps = StringUtils.split(referenceStep, ",");
				int refStep1 = new Integer(referenceSteps[0]);
				int refStep2 = new Integer(referenceSteps[1]);
				String getText1 = getTextMap.get(Integer.valueOf(refStep1));
				String getText2 = getTextMap.get(Integer.valueOf(refStep2));
				returnObj = Manipulation.condtionNotMatch(getText1, getText2);
			}
			else if(inputData != null && referenceStep != null && !referenceStep.trim().equals(""))
			{
				int refStep2 = new Integer(referenceStep);
				String getText1 = getTextMap.get(Integer.valueOf(refStep2));
				returnObj = Manipulation.condtionNotMatch(inputData,getText1);	

			}
			break;		
		case "DeleteAllCookies":
			Navigate.deleteAllCookies(driver);
			break;			
		case "TakeScreenShot":
			Navigate.screenShot(driver,inputData);
			break;					
		case "Highlight":
			Navigate.highLightElement(driver,element);
			break;	
		case "NewTab":
			Navigate.newTab(driver);
			break;
		case "CloseTab":
			Navigate.closeTab(driver);
			break;	
		case "WaitForAjaxQuery":
			Manipulation.waitForAjax(driver);
			break;	
		case "SendHttpPost":
			returnObj=Navigate.sendHttpPost(inputData);
			break;
		case "SplitAndOpenURL":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals("")) {
				int refStep = new Integer(referenceStep);
				String getText=getTextMap.get(Integer.valueOf(refStep));				
				String[] openURL = getText.split("https://www.google.de/");				
				driver.get(openURL[0]);
			}
			break;			
		case "ConcatStrings":
			String concat="";
			if (inputData == null && referenceStep != null
					&& !referenceStep.trim().equals("")){
				String[] splitReference=referenceStep.split(",");
				int size=splitReference.length;
				for(int i=0;i<size;i++){
					String getText12=getTextMap.get(Integer.valueOf(splitReference[i]));
					concat=concat+getText12;
				}
			}
			if (inputData != null && referenceStep == null
					&& !inputData.trim().equals("")) {
				splitInputData=inputData.split(",");
				int size=splitInputData.length;
				for(int i=0;i<size;i++)
				{
					concat=concat+splitInputData[i];
				}	
			}
			System.out.println(concat);
			returnObj=concat;
			break;
		case "Concat2String":
			String[] splitreference=referenceStep.split(",");
			int refStep12 = new Integer(splitreference[0]);
			int refStep13 = new Integer(splitreference[1]);
			String getText12=getTextMap.get(Integer.valueOf(refStep12));
			String getText13=getTextMap.get(Integer.valueOf(refStep13));
			String con = getText12.concat(getText13);
			returnObj=con;
			break;
		case "CoachesLogin":
			com.zillion.qa.coaches.Dashboard.coachesLogin(driver);
			break;
		case "CoachesLogout":
			com.zillion.qa.coaches.Dashboard.coachesLogout(driver);
			break;
		case "MemberLogin":
			com.zillion.qa.member.Dashboard.memberLogin(driver);
			break;
		case "MemberLogout":
			com.zillion.qa.member.Dashboard.memberLogout(driver);
			break;
		case "PractitionerLogin":
			com.zillion.qa.practitioner.Patients.practitionerLogin(driver);
			break;
		case "PractitionerLogout":
			com.zillion.qa.practitioner.Patients.practitionerLogout(driver);
			break;
		case "ProgramAdminLogin":
			com.zillion.qa.programadmin.Dashboard.programAdminLogin(driver);
			break;
		case "ProgramAdminLogout":
			com.zillion.qa.programadmin.Dashboard.programAdminLogout(driver);
			break;
		case "PractitionerAddNewPatient":
			com.zillion.qa.practitioner.Patients.practitionerAddNewPatient(driver,inputData);
			break;	 
		case "OpsAdminLogin":
			com.zillion.qa.opsadmin.Dashboard.opsAdminLogin(driver);
			break;
		case "OpsAdminLogout":
			com.zillion.qa.opsadmin.Dashboard.opsAdminLogout(driver);
			break;
		case "YopMail":
			com.zillion.qa.practitioner.Patients.yopMail(driver);
			break;
		case "ViewContentFromArticle":
			com.zillion.qa.member.Dashboard.viewContentFromArticle(driver);				   
			break;
		case "CurrentWindowName":
			returnObj=Manipulation.GetCurrentWindow(driver);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "GetSecondWindowHandle":
			Manipulation.getSecondWindow(driver, element);
			break;
		case "SwitchParticularWindow":
			int refStep2 = new Integer(referenceStep);
			String win=getTextMap.get(Integer.valueOf(refStep2));
			Manipulation.switchParticularWindow(driver, win);
			break;		
		case "FileUploadRobot":
			Manipulation.fileUploadRobot(driver, inputData);
			break;	
		case "OpsSessionCount":
			com.zillion.qa.opsadmin.Members.opsSessionCount(driver);
			break;
		case "VerifyNotesMember":
			com.zillion.qa.opsadmin.Members.verifyNotesMember(driver);
			break;		 
		case "UploadPhotos":
			com.zillion.qa.member.Dashboard.uploadPhotos(driver,inputData);
			break;   
		case "DashboardWeight":
			com.zillion.qa.member.Dashboard.dashboardEnterWeight(driver, inputData);
			break;
		case "DeletePhotos":
			com.zillion.qa.member.Dashboard.deletePhotos(driver);
			break;
		case "ValidateInsufficientAllSessionTab":
			com.zillion.qa.programadmin.Classes.validateInsufficientAllSessionTab(driver);
			break;
		case "PaginationVerificationPAClientTab":
			com.zillion.qa.programadmin.Clients.clientTabPaginationVerification(driver,inputData);
			break;
		case "MailDrop":
			com.zillion.qa.practitioner.Patients.mailDrop(driver);
			break;  
		case "RightClickMailDrop":
			Manipulation.rightClickMailDrop(driver, element);
			break;
		case "MailDropRegistrationCompleteLink":
			com.zillion.qa.practitioner.Patients.mailDropRegistrationCompleteLink(driver);
			break;  
		case "AddNewMember":
			com.zillion.qa.opsadmin.Members.addNewMember(driver,inputData);
			break;
		case "NewMemberClearButtonValidation":
			com.zillion.qa.opsadmin.Members.addNewMemberClearButton(driver,inputData);
			break;
		case "GuerillaMailReload":
			com.zillion.qa.member.Dashboard.guerrillaMail(driver,normalXpath);			   
			break;			
		case "MemberLoginAfterEnrollment":
			com.zillion.qa.practitioner.Patients.memberLoginAfterEnrollment(driver);
			break;  	
		case "PractitionerProfileVerificationAndValidation":
			com.zillion.qa.practitioner.Patients.practitionerProfileValidationAndVerification(driver);
			break; 
		case "PractitionerAddPatientVerify":
			com.zillion.qa.practitioner.Patients.practitionerAddNewPatientAndVerify(driver);
			break; 
		case "ValidationDbMemberProfile":
			int mailRefStep = new Integer(referenceStep);
			String mailId= getTextMap.get(Integer.valueOf(mailRefStep));
			returnObj = com.zillion.qa.coaches.Members.validationDbMemberProfile(driver,inputData,mailId);
			break; 		
		case "ValidationDbJournal":
			returnObj = com.zillion.qa.member.Tracker.validationDbJournal(driver, inputData);
			break;
		case "DeletePhotosBeforeUpload":
			com.zillion.qa.member.Dashboard.deletePhotosBeforeUpload(driver);
			break;
		case "ValidateUpcomingSessions":
			com.zillion.qa.coaches.Classes.validateUpcomingSessions(driver);
			break;
		case "SeparateDigitsAndAlphabets":
			com.zillion.qa.commands.Manipulation.separateDigitsAndAlphabets(driver, inputData);
			break;
		case "TypeDynamicValueReuse":
			returnObj = Manipulation.dynamicSendkeys(driver ,inputData, element);
			getTextMap.put(stepNo, returnObj.toString());
			break;		
		case "ValidatePreviousSessions":
			com.zillion.qa.coaches.Classes.validatePreviousSessions(driver);
			break;
		case "DataBaseProviderMyProfileVerificationAndValidation":
			com.zillion.qa.practitioner.Patients.providerMyProfileVerificationAndValidationUsingDB(driver,inputData);
			break;
		case "DateAndTimeEnabled":
			returnObj = com.zillion.qa.opsadmin.Classes.dateAndTimeEnabled(driver);
			break; 
		case "MemberVerifyLectureSession":
			returnObj = com.zillion.qa.opsadmin.Providers.memberVerifyLectureSession(driver);
			break;
		case "TimeSlotSearch":
			returnObj = com.zillion.qa.opsadmin.Providers.timeSlotSearch(driver);
			break;
		case "DbMemberUserid":
			returnObj =com.zillion.qa.member.Tracker.dbMemberUserid(driver,inputData);
			getTextMap.put(stepNo, returnObj.toString());
			break;	
		case "RetrieveProviderIdDB":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals("")) {
				int ref1 = new Integer(referenceStep);
				String ref2= getTextMap.get(Integer.valueOf(ref1));
				returnObj =com.zillion.qa.coaches.Classes.retrieveProviderId(driver,ref2);
				getTextMap.put(stepNo, returnObj.toString());
			}
			else if(inputData != null && referenceStep != null
					&& !referenceStep.trim().equals("")){  
				returnObj =com.zillion.qa.coaches.Classes.retrieveProviderId(driver,inputData);
				getTextMap.put(stepNo, returnObj.toString());
			}
			break;	
		case "CoachApprovalStatus1on1Session":
			returnObj =com.zillion.qa.coaches.Classes.coachApprovedStatus1on1Session(driver);
			break;	
		case "CurrentDateHFopsTimeSlot":
			com.zillion.qa.opsadmin.Classes.currentDateHFopsTimeSlot(driver);
			break;
		case "LectureMemberLogin":
			com.zillion.qa.member.Dashboard.lectureMemberLogin(driver);
			break;
		case "CoachUnapprovedStatus1on1Session":
			returnObj =com.zillion.qa.coaches.Classes.coachUnapprovedStatus1on1Session(driver);
			break;	
		case "CoachApprovedStatusLectureSession":
			returnObj =com.zillion.qa.coaches.Classes.coachApprovedStatusLectureSession(driver);
			break;	
		case "CoachUnapprovedStatusLectureSession":
			returnObj =com.zillion.qa.coaches.Classes.coachUnapprovedStatusLectureSession(driver);
			break;	
		case "TypeReference":
			int refStep = new Integer(referenceStep);
			String gettext= getTextMap.get(Integer.valueOf(refStep));
			returnObj=Manipulation.sendKeys(element, gettext);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "CoachProfileValidationUsingDB":
			int coachProfileMemEmail = new Integer(referenceStep);
			String coachProfileMemEmail1 = getTextMap.get(Integer.valueOf(coachProfileMemEmail));
			returnObj = com.zillion.qa.coaches.Dashboard
			.coachProfileValidationUsingDB(driver,inputData,coachProfileMemEmail1);
			break;
		case "ProgramAdminProfileValidationUsingDB":
			returnObj =com.zillion.qa.programadmin.Patients.programAdminProfileValidationUsingDB(driver, inputData);
			break;   	
		case "PAWellnesscorpLogin":
			com.zillion.qa.programadmin.Dashboard.paWellnesscorpLogin(driver);
			break;      
		case "PAWellnesscorpLogout":
			com.zillion.qa.programadmin.Dashboard.paWellnesscorpLogout(driver);
			break;    
		case "RetrievePhoneFromCoachDB":
			returnObj =com.zillion.qa.coaches.Dashboard.retrievePhoneFromCoachDB(driver,inputData );
			getTextMap.put(stepNo, returnObj.toString());
			break;	
		case "RetrieveMobileFromCoachDB":
			returnObj =com.zillion.qa.coaches.Dashboard.retrieveMobileFromCoachDB(driver,inputData);
			getTextMap.put(stepNo, returnObj.toString());
			break;	
		case "RetrieveCityFromCoachDB":
			returnObj =com.zillion.qa.coaches.Dashboard.retrieveCityFromCoachDB(driver,inputData);
			getTextMap.put(stepNo, returnObj.toString());
			break;	
		case "RetrieveZipFromCoachDB":
			returnObj =com.zillion.qa.coaches.Dashboard.retrieveZipFromCoachDB(driver,inputData);
			getTextMap.put(stepNo, returnObj.toString());
			break;	
		case "RetrieveBioFromCoachDB":
			returnObj =com.zillion.qa.coaches.Dashboard.retrieveBioFromCoachDB(driver,inputData);
			getTextMap.put(stepNo, returnObj.toString());
			break;	
		case "SumOfUpcomingAndPrevious":
			com.zillion.qa.opsadmin.Classes.validateUpcomingPreviousAllSession(driver);
			break;
		case "GuerrillaMail":
			com.zillion.qa.practitioner.Patients.guerrillaMail(driver);
			break;
		case "ScheduleOrRescheduleOrCancelSession":
			com.zillion.qa.member.Dashboard.scheduleOrRescheduleOrCancelSession(driver,inputData);
			break;  
		case "VerifyViewSessions":
			com.zillion.qa.member.Dashboard.verifyViewSessions(driver);;
			break;  
		case "ContentLibrarySearchVerification":
			com.zillion.qa.member.Library.memberContentLibrarySearchAndVerify(driver);
			break; 
		case "CalculateMaxValueofStartingWeight":
			returnObj= com.zillion.qa.member.Tracker.calculateMaxValueofStartingWeight(driver);
			getTextMap.put(stepNo, returnObj.toString());
			break; 
		case "CalculateMoreThanMaxValueofStartingWeight":
			returnObj=com.zillion.qa.member.Tracker.calculateMoreThanMaxValueofStartingWeight(driver);
			getTextMap.put(stepNo, returnObj.toString());
			break; 
		case "NextDateCoachUnavailableTimeSlot":
			com.zillion.qa.opsadmin.Classes.nextDateCoachUnavailabeTimeSlot(driver);
			break;
		case "AlertAccept":
			returnObj = Navigate.alertAccept(driver);
			break;
		case "RetrieveCurrentWeight":
			int refStep1 = new Integer(referenceStep);
			String gettext1= getTextMap.get(Integer.valueOf(refStep1));
			returnObj =com.zillion.qa.member.Tracker.retrieveCurrentWeight(driver,gettext1);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "CalculateMaxValueofStartingWeightDashboard":
			com.zillion.qa.member.Tracker.calculateMaxValueofStartingWeightDashboard(driver);
			break;	
		case "CalculateMoreThanMaxValueofStartingWeightDashboard":
			com.zillion.qa.member.Tracker.calculateMoreThanMaxValueofStartingWeightDashboard(driver);
			break;	
		case "CalculateBMI":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals("")) {
				String[] referenceSteps = StringUtils.split(referenceStep, ",");
				int refStep3 = new Integer(referenceSteps[0]);
				int refStep4 = new Integer(referenceSteps[1]);
				String getText1 = getTextMap.get(Integer.valueOf(refStep3));
				String getText2 = getTextMap.get(Integer.valueOf(refStep4));
				returnObj =com.zillion.qa.member.Tracker.calculateBMI(driver, getText1, getText2);
				getTextMap.put(stepNo, returnObj.toString());
			}
			else if(inputData != null && referenceStep != null && !referenceStep.trim().equals(""))
			{
				String[] referenceSteps = StringUtils.split(referenceStep, ",");
				int refStep3 = new Integer(referenceSteps[0]);
				int refStep4 = new Integer(referenceSteps[1]);
				String getText1 = getTextMap.get(Integer.valueOf(refStep3));
				String getText2 = getTextMap.get(Integer.valueOf(refStep4));
				returnObj =com.zillion.qa.member.Tracker.calculateBMI(driver, getText1, getText2);	
				Assert.fail();
			}
			break;
		case "RetrieveHeightOfTheMember":
			returnObj =com.zillion.qa.member.Tracker.retrieveHeightOfTheMember(driver);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "RetrieveAccountIdOfTheMember":
			returnObj =com.zillion.qa.member.Tracker.retrieveAccountIdOfTheMember(driver, inputData);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "SessionMemberLogin":
			com.zillion.qa.member.Dashboard.sessionMemberLogin(driver);
			break;
		case "ChangePlacementDateFuture":
			com.zillion.qa.member.Dashboard.changePlacementDateFuture(driver);
			break;
		case "MemberSignUpLectureSessionTwelveTimes":
			com.zillion.qa.member.Dashboard.memberSignUpLectureSession(driver);
			break;
		case "VerifyCreatedLectureSession":
			com.zillion.qa.opsadmin.Classes.verifyCreatedLectureSession(driver);
			break;
		case "CancelCreatedLectureSession":
			com.zillion.qa.opsadmin.Classes.cancelCreatedLectureSession(driver);
			break;
		case "DateSession":
			com.zillion.qa.member.Dashboard.dateSession(driver);
			break;
		case "AppendTextToURL":
			com.zillion.qa.member.Dashboard.appendTextToURL(driver);
			break;
		case "OneOnOneLiveSession":
			com.zillion.qa.member.LiveSession.oneOnOneliveSession(driver,inputData);
			break;
		case "ChangePageNumber":
			com.zillion.qa.opsadmin.Programs.changePageNumber(driver, inputData);
			break;	
		case "RetrieveProgramId1OfTheMember":
			int refStepForAccountid = new Integer(referenceStep);
			String AccountIdText= getTextMap.get(Integer.valueOf(refStepForAccountid));
			returnObj =com.zillion.qa.member.Dashboard.retrieveProgramId1OfTheMember(driver, AccountIdText);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "RetrieveProgramId2OfTheMember":
			int refStepForAccountid2 = new Integer(referenceStep);
			String AccountIdText2= getTextMap.get(Integer.valueOf(refStepForAccountid2));
			returnObj =com.zillion.qa.member.Dashboard.retrieveProgramId2OfTheMember(driver, AccountIdText2);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "UpdateDateinAccountProgram":
			String[] referenceSteps = StringUtils.split(referenceStep, ",");
			int refStep3 = new Integer(referenceSteps[0]);
			int refStep4 = new Integer(referenceSteps[1]);
			String getText1 = getTextMap.get(Integer.valueOf(refStep3));
			String getText2 = getTextMap.get(Integer.valueOf(refStep4));
			com.zillion.qa.member.Dashboard.updateDateinAccountProgram(driver, getText1, getText2);
			break;
		case "UpdateDateinAccountProgramMembership":
			String[] referenceSteps1 = StringUtils.split(referenceStep, ",");
			int refStep31 = new Integer(referenceSteps1[0]);
			int refStep41 = new Integer(referenceSteps1[1]);
			String getText11 = getTextMap.get(Integer.valueOf(refStep31));
			String getText21 = getTextMap.get(Integer.valueOf(refStep41));
			com.zillion.qa.member.Dashboard.updateDateinAccountProgramMembership(driver, getText11, getText21);
			break;
		case "compareRealAppeal":
			com.zillion.qa.opsadmin.Programs.compare_Real_Appeal(driver);
			break;
		case "SplitStringCompare":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals("")) {
				System.out.println("reference number:"+referenceStep);
				String[] splitreference2=referenceStep.split(",");
				System.out.println("reference split1:"+splitreference2[0]);
				System.out.println("reference split2:"+splitreference2[1]);
				int st1 = new Integer(splitreference2[0]);
				int refStep123 = new Integer(splitreference2[1]);
				String ss=getTextMap.get(Integer.valueOf(st1));
				String getText123 = getTextMap.get(Integer.valueOf(refStep123));
				returnObj=com.zillion.qa.opsadmin.Programs.spliting_string(ss,getText123);
			}
			break;
		case "RetrieveMemberAccountId":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals("")) {
				int accountidref = new Integer(referenceStep);
				String accountidref1 = getTextMap.get(Integer.valueOf(accountidref));
				returnObj = com.zillion.qa.member.liveSessionSubCommonMethods.retrieveMemberAccountId(driver,accountidref1);
				getTextMap.put(stepNo, returnObj.toString());
			}
			else if(inputData != null){   
				System.out.println("inputData"+inputData);
				returnObj = com.zillion.qa.member.liveSessionSubCommonMethods.retrieveMemberAccountId(driver,inputData);
				getTextMap.put(stepNo, returnObj.toString());
			}             
			break;
		case "RetrieveLectureSessionMemberAccountId":
			returnObj = com.zillion.qa.member.liveSessionSubCommonMethods.retrieveLectureSessionMemberAccountId(driver,inputData);
			getTextMap.put(stepNo, returnObj.toString());
			break;  
		case "RetrieveAccountProgramID":
			if (inputData == null && referenceStep != null && !referenceStep.trim().equals("")) {
				int refnumberpgmid = new Integer(referenceStep);
				String refnumberpgmid1 = getTextMap.get(Integer.valueOf(refnumberpgmid));
				returnObj =com.zillion.qa.member.liveSessionSubCommonMethods.retrieveAccountProgramID(driver,refnumberpgmid1);
			}
			else if(inputData != null && referenceStep != null && !referenceStep.trim().equals("")){ 
				returnObj =com.zillion.qa.member.liveSessionSubCommonMethods.retrieveAccountProgramID(driver,inputData);
			}
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "RetrieveHostNameWithProgramID":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals("")) {
				int hstnameref = new Integer(referenceStep);
				String hstname = getTextMap.get(Integer.valueOf(hstnameref));
				System.out.println("hstname"+hstname);
				returnObj =com.zillion.qa.member.liveSessionSubCommonMethods.retrieve1on1SessionHostNameWithProgramID(driver,hstname);
			}
			else if(inputData != null && referenceStep != null
					&& !referenceStep.trim().equals("")){  
				returnObj =com.zillion.qa.member.liveSessionSubCommonMethods.retrieve1on1SessionHostNameWithProgramID(driver,inputData);
			}
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "RetrieveNameOfTheMember":
			returnObj = com.zillion.qa.opsadmin.Dashboard.retrieveNameOfTheMember(driver,inputData);
			getTextMap.put(stepNo, returnObj.toString());
			break; 
		case "RetrieveFirstNameOfTheMember":
			returnObj = com.zillion.qa.opsadmin.Dashboard.retrieveFirstNameOfTheMember(driver,inputData);
			getTextMap.put(stepNo, returnObj.toString());
			break; 
		case "GetMemberFirstNameFromApp":
			int firstName = new Integer(referenceStep);
			String firstName2= getTextMap.get(Integer.valueOf(firstName));
			returnObj = com.zillion.qa.opsadmin.Dashboard.getMemberFirstNameFromApp(driver, firstName2);
			getTextMap.put(stepNo, returnObj.toString());
			break; 
		case "GetMemberLastNameFromApp":
			int memberLabel = new Integer(referenceStep);
			String memberLabel2= getTextMap.get(Integer.valueOf(memberLabel));
			returnObj = com.zillion.qa.opsadmin.Dashboard.getMemberLastNameFromApp(driver, memberLabel2);
			getTextMap.put(stepNo, returnObj.toString());
			break; 
		case "RetrieveLastNameOfTheMember":
			returnObj = com.zillion.qa.opsadmin.Dashboard.retrieveLastNameOfTheMember(driver,inputData);
			getTextMap.put(stepNo, returnObj.toString());
			break; 
		case "Retrieve1on1SessionEmailWithCoachHostName":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals("")) {
				int refStep2coachmailid = new Integer(referenceStep);
				String refStep2coachmail = getTextMap.get(Integer.valueOf(refStep2coachmailid));
				returnObj =com.zillion.qa.member.liveSessionSubCommonMethods.retrive1on1SessionEmailWithCoachHostId(driver,refStep2coachmail);
			}else if(inputData != null && referenceStep != null
					&& !referenceStep.trim().equals("")){ 
				returnObj =com.zillion.qa.member.liveSessionSubCommonMethods.retrive1on1SessionEmailWithCoachHostId(driver,inputData);
			}
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "RetrieveLectureSessionEmailWithCoachHostName":
			returnObj =com.zillion.qa.member.liveSessionSubCommonMethods.retriveLectureSessionEmailWithCoachHostName(driver);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "OpsAdminUrl":
			com.zillion.qa.opsadmin.Dashboard.launchOpsAdminUrl(driver);
			break;
		case "ProviderUrl":
			com.zillion.qa.programadmin.Dashboard.launchProviderUrl(driver);
			break;
		case "MemberUrl":
			com.zillion.qa.member.Dashboard.launchMemberUrl(driver);
			break;
		case "CoachUrl":
			com.zillion.qa.coaches.Dashboard.launchCoachUrl(driver);
			break;
		case "HttpCoachUrl":
			com.zillion.qa.coaches.Dashboard.getHttpUrlCoach(driver);
			break;
		case "HttpOpsAdminUrl":
			com.zillion.qa.opsadmin.Dashboard.getHttpUrlOpsAdmin(driver);
			break;
		case "HttpMemberUrl":
			com.zillion.qa.member.Dashboard.getHttpUrlMember(driver);
			break;
		case "HttpProviderUrl":
			com.zillion.qa.programadmin.Dashboard.getHttpUrlProvider(driver);
			break;
		case "BrowserMinimize":
			com.zillion.qa.member.liveSessionSubCommonMethods.browserMinimize(driver);
			break;
		case "AllowPlugins":
			com.zillion.qa.member.liveSessionSubCommonMethods.allowPlugins(driver);
			break;   
		case "DeleteAllMails":
			com.zillion.qa.member.Dashboard.deleteAllMails(driver);
			break;    
		case "VerifyClientSearch":
			int clientname1 = new Integer(referenceStep);
			String clientname2= getTextMap.get(Integer.valueOf(clientname1));
			com.zillion.qa.opsadmin.Members.verifyClientSearch(driver,clientname2);
			break;
		case "VerifyMemberOnboardedStatusOrders":
			returnObj=com.zillion.qa.opsadmin.Members.verifyMemberOnboardedstatusOrders(driver);
			break; 
		case "VerifyMembersNameAscendingOrder":
			returnObj=com.zillion.qa.opsadmin.Members.verifyMembersNameAscendingOrder(driver);
			break;
		case "VerifyMemberClientOrders":
			returnObj=com.zillion.qa.opsadmin.Members.verifyMemberClientOrders(driver);
			break; 
		case "VerifyMembersEmailOrders":
			returnObj=com.zillion.qa.opsadmin.Members.verifyMembersEmailOrders(driver);
			break;
		case "VerifyMemberProgramOrders":
			returnObj=com.zillion.qa.opsadmin.Members.verifyMemberProgramOrders(driver);
			break;
		case "RetrieveIdOfTheMember":
			if (inputData == null && referenceStep != null && !referenceStep.trim().equals("")) {
				int refnumberpgmid = new Integer(referenceStep);
				String refnumberpgmid1 = getTextMap.get(Integer.valueOf(refnumberpgmid));
				returnObj = com.zillion.qa.opsadmin.Dashboard.retrieveIdOfTheMember(driver,refnumberpgmid1);
				getTextMap.put(stepNo, returnObj.toString());
			}
			else if(inputData != null && referenceStep != null && !referenceStep.trim().equals("")){ 
				System.out.println("inputData"+inputData);
				returnObj = com.zillion.qa.opsadmin.Dashboard.retrieveIdOfTheMember(driver,inputData);
				System.out.println("returnObj"+returnObj);
				getTextMap.put(stepNo, returnObj.toString());
			}
			break; 
		case "RatingStars":
			com.zillion.qa.member.liveSessionSubCommonMethods.sessionratingStars(driver);
			break; 
		case "RatingNotesCoachSide":
			com.zillion.qa.member.liveSessionSubCommonMethods.ratingNotesCoachSide(driver, inputData);
			break;
		case "VerifyMemberAccountStatusOrders":
			com.zillion.qa.opsadmin.Dashboard.verifyMemberAccountStatusOrders(driver);
			break;
		case "RetrieveHostnameofCoach":
			returnObj =com.zillion.qa.member.liveSessionSubCommonMethods.retrieveHostnameofCoach(driver);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "OneOnOneLiveSessionCoachLogin":
			com.zillion.qa.member.liveSessionSubCommonMethods.oneOnOneLiveSessionCoachLogin(driver);
			break;
		case "RetrieveAddressOfTheMember":
			returnObj =com.zillion.qa.opsadmin.Members.retrieveAddressOfTheMember(driver, inputData);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "RetrieveAddress1OfTheMember":
			returnObj =com.zillion.qa.opsadmin.Members.retrieveAddress1OfTheMember(driver, inputData);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "OpsAdminMemberEditDOB":
			returnObj=com.zillion.qa.opsadmin.Members.opsAdminDOB(driver);
			break;
		case "MemberProfileDOB":
			returnObj=com.zillion.qa.opsadmin.Members.memberProfileDOB(driver);
			break;
		case "CompareDOB":
			returnObj=com.zillion.qa.opsadmin.Members.compareDOB(driver);
			break;
		case "RetrieveScreenNameOfTheMember":
			returnObj =com.zillion.qa.opsadmin.Members.retrieveScreenNameOfTheMember(driver, inputData);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "RetrieveSecurityOfTheMember":
			returnObj =com.zillion.qa.opsadmin.Members.retrieveSecurityOfTheMember(driver, inputData);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "VerifyMemberCoachOrders":
			returnObj =com.zillion.qa.opsadmin.Members.verifyMemberCoachOrders(driver);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "VerifyMemberMemberIdOrders":
			returnObj=com.zillion.qa.opsadmin.Members.verifyMemberMemberIdOrders(driver);
			break;    
		case "ScreenShotInReport":
			Navigate.ScreenShotinReport(driver,inputData);
			break; 
		case "RetrieveAvailableMemberAccountId":
			com.zillion.qa.member.liveSessionSubCommonMethods.retrieveAvailableMemberAccountId(driver);
			break;
		case "RetrieveAvailableMemberEmailIdWithAccountID":
			int accountid = new Integer(referenceStep);
			String accountid1 = getTextMap.get(Integer.valueOf(accountid)); 
			returnObj =com.zillion.qa.member.liveSessionSubCommonMethods.retrieveAvailableMemberEmailIdWithAccountID(driver,accountid1);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "RetrieveemailphonesmsenableOfTheMember":
			returnObj =com.zillion.qa.opsadmin.Members.retrieveemailphonesmsenableOfTheMember(driver, inputData);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "CoachNameVerify":
			com.zillion.qa.opsadmin.Members.coachNameVerify(driver, inputData);
			break;
		case "HFOPsValidationDbMemberInsurance":
			returnObj=com.zillion.qa.opsadmin.Members.hFOPsValidationDbMemberInsurance(driver);
			break;
		case "VerifyMembersRequestDateAscendingOrders":
			returnObj=com.zillion.qa.opsadmin.Members.verifyMembersRequestDateAscendingOrders(driver);
			break;
		case "VerifyMembersRequestDateDescendingOrders":
			returnObj=com.zillion.qa.opsadmin.Members.verifyMembersRequestDateDescendingOrders(driver);
			break;
		case "RetrieveCotentCount":
			returnObj =com.zillion.qa.member.Dashboard.retrieveCotentCount(driver);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "CalculateDisplayedContentsCount":
			returnObj =com.zillion.qa.member.Dashboard.calculateDisplayedContentsCount(driver);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "TargetWeight":
			returnObj=com.zillion.qa.opsadmin.Members.targetWeight(driver);
			break;  
		case "HalfPageScrollDown":
			com.zillion.qa.opsadmin.Members.halfPageDown(driver);
			break;
		case "VerifyMemberDateOfBirthUsingDB":
			com.zillion.qa.opsadmin.Members.verifyMemberDateOfBirthUsingDB(driver, inputData);
			break;
		case "RobotTab":
			Navigate.robotTab(driver);
			break;
		case "GetAttributeWithPrefixConcate":
			returnObj = ElementActions.getAttributeWithPrefixConcate(element, inputData);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "CheckTwoStringWithConcatedPrefixForFirstValue":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals("")) {
				String[] referenceSteps14 = StringUtils.split(referenceStep, ",");
				int refStep11 = new Integer(referenceSteps14[0]);
				int refStep21 = new Integer(referenceSteps14[1]);
				String getText115 = getTextMap.get(Integer.valueOf(refStep11));
				String getText215 = getTextMap.get(Integer.valueOf(refStep21));
				returnObj = Manipulation.condtionMatch("Re:"+getText115, getText215);
			}
			break;
		case "EnrollmentProcessStep1PrivacyPolicyLink":
			com.zillion.qa.practitioner.Patients.enrollmentProcessStep1PrivacyPolicyLink(driver, inputData);
			break;
		case "EnrollmentProcessStep1TermsAndConditionLink":
			com.zillion.qa.practitioner.Patients.enrollmentProcessStep1TermsAndConditionLink(driver, inputData);
			break;
		case "EnrollmentProcessStep2PrivacyPolicyLink":
			com.zillion.qa.practitioner.Patients.enrollmentProcessStep2PrivacyPolicyLink(driver, inputData);
			break;
		case "EnrollmentProcessStep2TermsAndConditionLink":
			com.zillion.qa.practitioner.Patients.enrollmentProcessStep2TermsAndConditionLink(driver, inputData);
			break;
		case "JenkinDbTimeslot":
			returnObj =com.zillion.qa.member.Dashboard.jenkinDbTimeslot(driver, inputData);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "JenkinStartdate":
			returnObj =com.zillion.qa.member.Dashboard.jenkinStartdate(driver);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "ReloadTilMailGetsLoaded":
			com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver, normalXpath);		   
			break;	
		case "VerifyUnreadmessages":
			com.zillion.qa.coaches.Messages.verifyUnreadmessages(driver);
			break;
		case "PaApolloEndoLogin":
			com.zillion.qa.programadmin.Dashboard.paApolloEndoLogin(driver);
			break;
		case "MessageSentByProviderSubjectValidatedWithDB":
			int refStep22 = new Integer(referenceStep);
			String getText115 = getTextMap.get(Integer.valueOf(refStep22)); 
			System.out.println("getSubjectFromSheet: "+getText115);
			String[] subjectFromSheet=getText115.split("This");
			String subjectTimeStampFromSheet= subjectFromSheet[0];
			System.out.println("Splitted subject time stamp from sheet Subject: "+subjectTimeStampFromSheet);
			com.zillion.qa.coaches.Messages.messageSentByProviderSubjectValidatedWithDB(driver,subjectTimeStampFromSheet,getText115,inputData);
			break;   
		case "MessageSentByProviderBodyValidatedWithDB":
			int refStep23 = new Integer(referenceStep);
			String getText116 = getTextMap.get(Integer.valueOf(refStep23));
			System.out.println("getBodyFromSheet: "+getText116);
			String[] bodyFromSheet=getText116.split("DO");
			String bodyTimeStampFromSheet= bodyFromSheet[0];
			System.out.println("Splitted body time stamp from sheet body: "+bodyTimeStampFromSheet);
			com.zillion.qa.coaches.Messages.messageSentByProviderBodyValidatedWithDB(driver,bodyTimeStampFromSheet,getText116,inputData);
			break;  
		case "MessageReceivedByProviderSubjectValidatedWithDB":
			int refStep24 = new Integer(referenceStep);
			String getText117 = getTextMap.get(Integer.valueOf(refStep24)); 
			System.out.println("getMessageReceivedSubjectFromSheet: "+getText117);
			String[] messageReceivedSubjectFromSheet=getText117.split("This");
			String messageReceivedsubjectTimeStampFromSheet= messageReceivedSubjectFromSheet[0];
			System.out.println("Splitted subject time stamp from sheet Subject: "+messageReceivedsubjectTimeStampFromSheet);
			com.zillion.qa.coaches.Messages.messageReceivedByProviderSubjectValidatedWithDB(driver,messageReceivedsubjectTimeStampFromSheet,getText117,inputData);
			break;   
		case "MessageReceivedByProviderBodyValidatedWithDB":
			int refStep25 = new Integer(referenceStep);
			String getText118 = getTextMap.get(Integer.valueOf(refStep25)); 
			System.out.println("getMessageReceivedBodyFromSheet: "+getText118);
			String[] messageReceivedBodyFromSheet=getText118.split("DO");
			String messageReceivedBodyTimeStampFromSheet= messageReceivedBodyFromSheet[0];
			System.out.println("Splitted body time stamp from sheet body: "+messageReceivedBodyTimeStampFromSheet);
			com.zillion.qa.coaches.Messages.messageReceivedByProviderBodyValidatedWithDB(driver,messageReceivedBodyTimeStampFromSheet,getText118,inputData);
			break;   
		case "MessageReceivedByMemberSubjectValidatedWithDB":
			int refStep26 = new Integer(referenceStep);
			String getText119 = getTextMap.get(Integer.valueOf(refStep26)); 
			System.out.println("getMessageReceivedSubjectFromSheet: "+getText119);
			String[] messageReceivedMemberSubjectFromSheet=getText119.split("This");
			String messageReceivedMemberSubjectTimeStampFromSheet= messageReceivedMemberSubjectFromSheet[0];
			System.out.println("Splitted subject time stamp from sheet Subject: "+messageReceivedMemberSubjectTimeStampFromSheet);
			com.zillion.qa.coaches.Messages.messageReceivedByMemberSubjectValidatedWithDB(driver,messageReceivedMemberSubjectTimeStampFromSheet,getText119,inputData);
			break; 
		case "MessageReceivedByMemberBodyValidatedWithDB":
			int refStep27 = new Integer(referenceStep);
			String getText120 = getTextMap.get(Integer.valueOf(refStep27)); 
			System.out.println("getMessageReceivedSubjectFromSheet: "+getText120);
			String[] messageReceivedMemberBodyFromSheet=getText120.split("DO");
			String messageReceivedMemberBodyTimeStampFromSheet= messageReceivedMemberBodyFromSheet[0];
			System.out.println("Splitted body time stamp from sheet body: "+messageReceivedMemberBodyTimeStampFromSheet);
			com.zillion.qa.coaches.Messages.messageReceivedByMemberBodyValidatedWithDB(driver,messageReceivedMemberBodyTimeStampFromSheet,getText120,inputData);
			break; 
		case "RetrieveLimitsOfClasses":
			returnObj =com.zillion.qa.realappealprogramadmin.programadmin.retrieveLimitsOfClasses(driver, inputData);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "RealAppealLaunchMemberURL":
			com.zillion.qa.realappealmember.member.realAppealLaunchMemberURL(driver);
			break;
		case "RealAppealLaunchProviderURL":
			com.zillion.qa.realappealprogramadmin.programadmin.realAppealLaunchProviderURL(driver);
			break;
		case "GetWindowUsingJsclick":
			Manipulation.getWindowUsingJsclick(driver,normalXpath );
			break;
		case "RealAppealProgramAdminLogin":
			com.zillion.qa.realappealprogramadmin.programadmin.realAppealProgramAdminLogin(driver);
			break;
		case "RealAppealProgramAdminLogout":
			com.zillion.qa.realappealprogramadmin.programadmin.realAppealProgramAdminLogout(driver);
			break;        
		case "RealAppealCoachLogin":
			com.zillion.qa.realappealcoach.coach.realAppealCoachLogin(driver);
			break;
		case "RealAppealCoachLogout":
			com.zillion.qa.realappealcoach.coach.realAppealCoachLogout(driver);
			break;   
		case "RetrieveCoachAvailableHrs":
			returnObj = com.zillion.qa.realappealcoach.coach.retrieveCoachAvailableHrs(driver, inputData);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "VerifyTheScheduleClasses":
			com.zillion.qa.realappealprogramadmin.programadmin.verifyTheScheduleClasses(driver);
			break;
		case "ValidateClassOnCoach":
			int coachref = new Integer(referenceStep);
			String coachref1 = getTextMap.get(Integer.valueOf(coachref)); 
			com.zillion.qa.realappealprogramadmin.programadmin.validateClassOnCoach(driver,coachref1);
			break;
		case "DBRealAppealMemberEnrollmentStatus":
			returnObj =com.zillion.qa.realappealprogramadmin.programadmin.dBRealAppealMemberEnrollmentStatus(driver);
			getTextMap.put(stepNo, returnObj.toString());
			break;  
		case "MessageSentByMemberSubjectValidatedWithDB":
			int refStep28 = new Integer(referenceStep);
			String getText121 = getTextMap.get(Integer.valueOf(refStep28)); 
			System.out.println("getMessageReceivedSubjectFromSheet: "+getText121);
			String[] messageSentMemberSubjectFromSheet=getText121.split("This");
			String messageSentMemberSubjectTimeStampFromSheet= messageSentMemberSubjectFromSheet[0];
			System.out.println("Splitted subject time stamp from sheet Subject: "+messageSentMemberSubjectTimeStampFromSheet);
			com.zillion.qa.coaches.Messages.messageSentByMemberSubjectValidatedWithDB(driver,messageSentMemberSubjectTimeStampFromSheet,getText121,inputData);
			break;
		case "MessageSentByMemberBodyValidatedWithDB":
			int refStep29 = new Integer(referenceStep);
			String getText122 = getTextMap.get(Integer.valueOf(refStep29)); 
			System.out.println("getMessageReceivedBodyFromSheet: "+getText122);
			String[] messageSentMemberBodyFromSheet=getText122.split("DO");
			String messageSentMemberBodyTimeStampFromSheet= messageSentMemberBodyFromSheet[0];
			System.out.println("Splitted body time stamp from sheet body: "+messageSentMemberBodyTimeStampFromSheet);
			com.zillion.qa.coaches.Messages.messageSentByMemberBodyValidatedWithDB(driver,messageSentMemberBodyTimeStampFromSheet,getText122,inputData);
			break;     
		case "RealAppealMemberVerifyPaginationEnableOrDisabled":
			com.zillion.qa.realappealmember.member.realAppealMemberVerifyPaginationEnableOrDisabled(driver);
			break;
		case "Guerrillamailurl":
			com.zillion.qa.member.Dashboard.launchGuerrillamailUrl(driver);
			break;
		case "RAProviderUrl":
			com.zillion.qa.realappealcoach.coach.launchRAProviderUrl(driver);
			break;
		case "RAMemberUrl":
			com.zillion.qa.realappealmember.member.launchRAMemberUrl(driver);
			break;
		case "AppendTextToRAMemberURL":
			com.zillion.qa.realappealmember.member.appendTextToRAMemberURL(driver);
			break;
		case "AssignedCoachOfRAMemberLogin":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals("")) {
				int assignedcoachref = new Integer(referenceStep);
				String assignedcoachref1 = getTextMap.get(Integer.valueOf(assignedcoachref));
				com.zillion.qa.realappealcoach.coach.assignedCoachOfMemberLogin(driver, assignedcoachref1);
			}
			else if(inputData != null && referenceStep != null
					&& !referenceStep.trim().equals("")){  
				com.zillion.qa.realappealcoach.coach.assignedCoachOfMemberLogin(driver, inputData);
			}
			break;
		case "AppendTextToRACoachURL":
			com.zillion.qa.realappealcoach.coach.appendTextToRACoachURL(driver);
			break;
		case "RetreieveMemberScheduledSessionFromDB":
			String[] inputs3=inputData.split(",");
			returnObj =com.zillion.qa.realappealmember.member.retreieveMemberToScheduleSessionFromDB(driver, inputs3[0],inputs3[1]);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "RetrieveHostNameForCustomizationSessionWithProgramID":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals("")) {
				int hstnameref = new Integer(referenceStep);
				String hstname = getTextMap.get(Integer.valueOf(hstnameref));
				System.out.println("hstname"+hstname);
				returnObj =com.zillion.qa.member.liveSessionSubCommonMethods.retrieveHostIDForCustomizationSessionWithProgramID(driver,hstname);
			}
			else if(inputData != null && referenceStep != null
					&& !referenceStep.trim().equals("")){  
				returnObj =com.zillion.qa.member.liveSessionSubCommonMethods.retrieveHostIDForCustomizationSessionWithProgramID(driver,inputData);
			}
			getTextMap.put(stepNo, returnObj.toString());
			break; 
		case "VerifyDBOnboardingStatus":
			returnObj = com.zillion.qa.realappealprogramadmin.programadmin.verifyDBOnboardingStatus(driver);
			break;
		case "VerifyMemberMailEnrollment":
			int mailref = new Integer(referenceStep);
			String mailinput = getTextMap.get(Integer.valueOf(mailref));
			com.zillion.qa.realappealmember.member.verifyMemberMailEnrollment(driver, mailinput);
			break;
		case "VerifyCoachMailCustomizationSession":
			int coachmailref = new Integer(referenceStep);
			String coachmailinput = getTextMap.get(Integer.valueOf(coachmailref));
			com.zillion.qa.realappealcoach.coach.verifyCoachMailCustomizationSession(driver, coachmailinput);
			break;    
		case "RealAppealMemberEnterPassword": 
			com.zillion.qa.realappealmember.member.realAppealMemberEnterPassword(driver);
			break; 
		case "CoachEnterPassword": 
			com.zillion.qa.coaches.Dashboard.coachEnterPassword(driver);
			break; 
		case "GetAttributeValue":
			returnObj =  com.zillion.qa.realappealprogramadmin.programadmin.getAttribute(driver,element);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "VerifyDBOnboardingStatusClassroomNotStarted":
			returnObj =  com.zillion.qa.realappealprogramadmin.programadmin.verifyDBOnboardingStatusClassroomNotStarted(driver);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "VerifyUnapprovedRAPDclassifications":
			com.zillion.qa.realappealprogramadmin.programadmin.verifyUnapprovedRAPDclassifications(driver);
			break;
		case "CoachAllSessionSubtabVerification":
			com.zillion.qa.coaches.Dashboard.coachAllSessionSubtabVerification(driver);
			break;
		case "FridayBluearea":
			com.zillion.qa.member.liveSessionSubCommonMethods.fridayBluearea(driver);
			break;
		case "LiveSessionLectureSessionTimeSlotSearch":
			com.zillion.qa.member.liveSessionSubCommonMethods.liveSessionLectureSessionTimeSlotSearch(driver);
			break;
		case "CancelCreatedLectureLiveSession":
			com.zillion.qa.member.liveSessionSubCommonMethods.cancelCreatedLectureLiveSession(driver);
			break;
		case "ValidateMemberOnboardingStatus":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals("")) {
				int mailidref = new Integer(referenceStep);
				String Membermailidref = getTextMap.get(Integer.valueOf(mailidref));
				System.out.println("hstname"+Membermailidref);
				returnObj = com.zillion.qa.realappealmember.member.validateMemberOnboardingStatus(driver, Membermailidref);
			}
			else if(inputData != null && referenceStep != null
					&& !referenceStep.trim().equals("")){  
				returnObj =com.zillion.qa.realappealmember.member.validateMemberOnboardingStatus(driver, inputData);;
			}
			getTextMap.put(stepNo, returnObj.toString());
			break; 
		case"RetrieveHostNameForAnySessionWithProgramID":
			int hstnameref = new Integer(referenceStep);
			String hstname = getTextMap.get(Integer.valueOf(hstnameref));
			String inputData1[]=inputData.split(",");
			String sessionType=inputData1[0];
			String session_Type_Id=inputData1[1];
			returnObj =com.zillion.qa.realappealmember.member.retrieveHostNameWithProgramID(driver, hstname, sessionType, session_Type_Id);
			getTextMap.put(stepNo, returnObj.toString());
			break; 
		case "RetrieveAcntIdWithOnboardingStatusAndMpName":
			String[] inputs= inputData.split(",");
			String onboarding_status=inputs[0];
			String mp_name=inputs[1];
			returnObj = com.zillion.qa.realappealmember.member.retrieveAcntIdWithOnboardingStatusAndMpName(driver, onboarding_status, mp_name);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "ScheduleClassPreponePostponeVerify":
			com.zillion.qa.realappealprogramadmin.programadmin.scheduleClassPreponePostponeVerify(driver);
			break;
		case "VerifyMemberMessageInboxPagination":
			returnObj = com.zillion.qa.realappealprogramadmin.programadmin.verifyMemberMessageInboxPagination(driver);
			break;
		case "RetrieveHostNickNameUsingHostId":
			int hstidref = new Integer(referenceStep);
			String hstid = getTextMap.get(Integer.valueOf(hstidref));
			returnObj = com.zillion.qa.realappealmember.member.retrieveHostNickNameUsingHostId(driver,hstid );
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "VerifyMemberDBOnboardingStatus":
			returnObj = com.zillion.qa.realappealprogramadmin.programadmin.verifyMemberDBOnboardingStatus(driver);
			break;
		case "RetrieveClassroomId":
			int idref = new Integer(referenceStep);
			String id = getTextMap.get(Integer.valueOf(idref));
			returnObj = com.zillion.qa.realappealmember.member.retrieveClassroomId(driver,id );
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "RetrieveIsApprovedClassroom":
			int classroomidref = new Integer(referenceStep);
			String classroomid = getTextMap.get(Integer.valueOf(classroomidref));
			returnObj = com.zillion.qa.realappealmember.member.retrieveIsApproved(driver,classroomid);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "SessionWidgetDisplay":
			com.zillion.qa.member.Dashboard.sessionWidgetDisplay(driver);
			break;
		case "AllSessionCoachLogin":
			com.zillion.qa.coaches.Dashboard.allSessionCoachLogin(driver);
			break;
		case "VerifyApprovedStatusOfSession":
			int providridref = new Integer(referenceStep);
			String providrid = getTextMap.get(Integer.valueOf(providridref));
			returnObj =com.zillion.qa.coaches.Classes.verifyApprovedStatusOfSession(driver,providrid);
			break;	
		case "ALertAccept1":
			com.zillion.qa.opsadmin.Classes.accept1(driver);
			break;
		case "JsPagedown":
			com.zillion.qa.member.Library.pagedown1(driver);     
			break;
		case "VerifyCoachMail1on1Session":
			if (inputData != null && referenceStep == null)
			{
				com.zillion.qa.realappealcoach.coach.verifyCoachMail1on1Session(driver, inputData);
			}
			else if(inputData == null && referenceStep != null
					&& !referenceStep.trim().equals("")){            	
				int mailidref = new Integer(referenceStep);               
				String mailidtext = getTextMap.get(Integer.valueOf(mailidref));  
				com.zillion.qa.realappealcoach.coach.verifyCoachMail1on1Session(driver, mailidtext);
			}
			break;
		case "VerifyMemberMail1on1Session":    	           	
			int mailidref = new Integer(referenceStep);               
			String mailidtext = getTextMap.get(Integer.valueOf(mailidref));  
			com.zillion.qa.realappealmember.member.verifyMemberMail1on1Session(driver, mailidtext);
			break;
		case "DeleteYopMails":  
			com.zillion.qa.realappealmember.member.deleteYopMails(driver);
			break;
		case "VerifyCoachMail1on1SessionCancellation":
			if (inputData != null && referenceStep == null)
			{
				com.zillion.qa.realappealcoach.coach.verifyCoachMail1on1Session(driver, inputData);
			}
			else if(inputData == null && referenceStep != null
					&& !referenceStep.trim().equals("")){            	
				int mailidref1 = new Integer(referenceStep);               
				String mailidtext1 = getTextMap.get(Integer.valueOf(mailidref1));  
				com.zillion.qa.realappealcoach.coach.verifyCoachMail1on1SessionCancellation(driver, mailidtext1);
			}
			break;
		case "VerifyMemberMail1on1SessionCancellation":    	           	
			int mailidref1 = new Integer(referenceStep);               
			String mailidtext1 = getTextMap.get(Integer.valueOf(mailidref1));  
			com.zillion.qa.realappealmember.member.verifyMemberMail1on1SessionCancellation(driver, mailidtext1);
			break;
		case "SumOfTwoStrings":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals("")) {
				String[] stringReferenceSteps = StringUtils.split(referenceStep, ",");
				int string12 = new Integer(stringReferenceSteps[0]);
				int string13 = new Integer(stringReferenceSteps[1]);
				String getText16 = getTextMap.get(Integer.valueOf(string12));
				String getText26 = getTextMap.get(Integer.valueOf(string13));
				returnObj = Manipulation.sumOfTwoString(getText16, getText26);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			}
		case "ScrollingToElementofAPage":
			if (inputData != null && referenceStep == null)
			{
				returnObj= IOSActions.iosscrolluntilelement(driver,inputData,normalXpath);
			}
			break;
		case "IOSPageScrolldown":
			IOSActions.iosPageScrolldown(driver);
			break;
		case "TouchElement":
			IOSActions.iOStouchElement(driver, element);
			break;
		case "ClearTextField":
			IOSActions.clearTextField(driver, element);
			break;
		case "MobilePageDown":
			IOSActions.mobilePageDown(driver);
			break;
		case "MobilePageUp":
			IOSActions.mobilePageUp(driver);
			break;
		}
		return returnObj;
	}  

}