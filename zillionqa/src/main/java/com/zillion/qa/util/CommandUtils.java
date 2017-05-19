package com.zillion.qa.util;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.zillion.qa.commands.ElementActions;
import com.zillion.qa.commands.LocatorBy;
import com.zillion.qa.commands.Manipulation;
import com.zillion.qa.commands.Navigate;

/**
 * Common methods for all kind of actions (Selenium Actions, CrowdTwist specific
 * common methods)
 * @author Babu
 */
public class CommandUtils {

	public WebElement element;
	public String normalXpath;
	Object returnObj = null;
	public WebElement element1;
	public WebElement element2;
	public static String getText = "";
	public static String getSize = "";
	public static String getText1 = "";
	public static HashMap<Integer, String> getTextMap = new HashMap<Integer, String>();
	public static String[] widgetUrlCount = new String[100];
	public static int widgetUrls = 0;
	public static String[] splitInputData;
	/**
	 * Locators
	 *
	 * @param driver
	 * @param locateBy
	 * @param orLocator
	 * @return
	 */
	public WebElement findElement(WebDriver driver, String locateBy,
			String orLocator, String orLocatorStart, String orLocatorEnd,
			String referenceStep) {

		switch (locateBy) {
		case "ByID":
			element = LocatorBy.locateById(driver, orLocator);
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
		case "MergeByXpath":
			int refStep = new Integer(referenceStep);
			String refText = getTextMap.get(Integer.valueOf(refStep));
			orLocator = orLocatorStart + refText + orLocatorEnd;
			element = LocatorBy.locateByXPath(driver, orLocator);
			break;
		case "MergeXpath":
			int refSteps = new Integer(referenceStep);
			String refTexts = getTextMap.get(Integer.valueOf(refSteps));
			orLocator = orLocatorStart + refTexts + orLocatorEnd;
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
	 * Common selenium Actions
	 * @param driver
	 * @param element
	 * @param action
	 * @param inputData
	 * @param stepNo
	 * @param referenceStep
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public Object executeAction(WebDriver driver, WebElement element,
			String action, String inputData, int stepNo, String referenceStep)
					throws Exception {
		// Object returnObj = null;
		if(true){
			switch (action)
			{
			case "GetUrl":
				if (inputData == null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					int refStep = new Integer(referenceStep);
					String getText = getTextMap.get(Integer.valueOf(refStep));
					Navigate.get(driver, getText);
				} else {
					Navigate.get(driver, inputData);
				}
				break;
			case "NavigateToURL":
				Navigate.navigateUrl(driver, inputData);
				break;
			case "Wait":
				Manipulation.wait(driver, inputData);
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
				Manipulation.actionClick(driver, element);
				break;
			case "JsClick":
				Manipulation.jsClickByXPath(driver, normalXpath);
				break;
			case "JsTypeByXPath":
				Manipulation.jsTypeByXPath(driver, normalXpath, inputData);
				break;
			case "DoubleClick":
				Manipulation.doubleClick(driver, element);
				break;
			case "ClickAt":
				if (inputData != null) {
					String[] coordinates = StringUtils.split(inputData, ",");
					int x = new Integer(coordinates[0]);
					int y = new Integer(coordinates[1].trim());
					Manipulation.clickAt(driver, element, x, y);
				} else if (referenceStep != null) {
					int getxyref = new Integer(referenceStep);
					String getxyref1 = getTextMap.get(Integer.valueOf(getxyref));
					String[] coordinates = StringUtils.split(getxyref1, ",");
					int x = new Integer(coordinates[0]);
					int y = new Integer(coordinates[1].trim());
					Manipulation.clickAt(driver, element, x, y);
				}
				break;
			case "ClickUnavailabilityRedArea":
				if (inputData != null) {
					String[] coordinates = StringUtils.split(inputData, ",");
					int x = new Integer(coordinates[0]);
					int y = new Integer(coordinates[1].trim());
					com.zillion.qa.realappealcoach.coach.clickUnavailabilityRedArea(driver, element, x, y);
				} else if (referenceStep != null) {
					int getxyref = new Integer(referenceStep);
					String getxyref1 = getTextMap.get(Integer.valueOf(getxyref));
					String[] coordinates = StringUtils.split(getxyref1, ",");
					int x = new Integer(coordinates[0]);
					int y = new Integer(coordinates[1].trim());
					com.zillion.qa.realappealcoach.coach.clickUnavailabilityRedArea(driver, element, x, y);
				}
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
				&& !referenceStep.trim().equals("")) {
					int refStep = new Integer(referenceStep);
					String gettext = getTextMap.get(Integer.valueOf(refStep));
					returnObj = Manipulation.sendKeys(element, gettext);
					getTextMap.put(stepNo, returnObj.toString());
				} else {
					returnObj = Manipulation.sendKeys(element, inputData);
					getTextMap.put(stepNo, returnObj.toString());
				}
				break;
			case "ClearAndType":
				if (inputData == null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					int refStep = new Integer(referenceStep);
					inputData = getTextMap.get(Integer.valueOf(refStep));
				}
				returnObj = Manipulation.clearAndType(element, inputData);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "ActionType":
				if (referenceStep != null) {
					int refStep = new Integer(referenceStep);
					inputData = getTextMap.get(Integer.valueOf(refStep));
					Manipulation.actionType(driver, element, inputData);
				} else if (inputData != null) {
					Manipulation.actionType(driver, element, inputData);
					getTextMap.put(stepNo, inputData);
				}
				break;
			case "TypeDynamicValue":
				if (inputData == null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					int refStep = new Integer(referenceStep);
					inputData = getTextMap.get(Integer.valueOf(refStep));
				}
				returnObj = Manipulation
						.dynamicSendkeys(driver, inputData, element);
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
				getText1 = getText.replace("...", "");
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
				if (inputData == null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					int refStep1 = new Integer(referenceStep);
					String getText1 = getTextMap.get(Integer.valueOf(refStep1));
					returnObj = Manipulation.selectByVisibletext(element, getText1);
				} else {
					returnObj = Manipulation
							.selectByVisibletext(element, inputData);
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
				if (inputData == null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					int refStep1 = new Integer(referenceStep);
					String getText1 = getTextMap.get(Integer.valueOf(refStep1));
					Manipulation.deSelectByVisibletext(element, getText1);
				} else {
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
				Navigate.alertGenerate(driver, inputData);
				break;
			case "Close":
				Navigate.close(driver);
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
			case "EnterUsingSendKeys":
				Navigate.enter(element, driver);
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
				Manipulation.testIsPresent(driver, element, inputData);
				break;
			case "WaitUntilTextToBeNotPresent":
				Manipulation.testIsNotPresent(driver, normalXpath, inputData);
				break;
			case "WaitUntilTextToBePresent":
				Manipulation.textTobePresent(driver, element, inputData);
				break;
			case "WaitUntilElementToBeClickable":
				Manipulation.elementTobeClickable(driver, element);
				break;
			case "WaitUntilElementToBeSelected":
				Manipulation.elementToBeSelected(driver, element);
				break;
			case "TextToBePresentInElementValue":
				Manipulation.textElementPresentValue(driver, element, inputData);
				break;
			case "WaitForElementPresent":
				Manipulation.waitForElement(driver, normalXpath);
				break;
			case "WaitForElementNotPresent":
				Manipulation.waitForElementNotpresent(driver, normalXpath);
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
				} else if (inputData != null && referenceStep != null) {
					int refStep6 = new Integer(referenceStep);
					String getText1 = getTextMap.get(Integer.valueOf(refStep6));
					System.out.println("getText1" + getText1);
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
				} else if (inputData != null && referenceStep != null
						&& !referenceStep.trim().equals("")) {
					int refStep2 = new Integer(referenceStep);
					String getText1 = getTextMap.get(Integer.valueOf(refStep2));
					returnObj = Manipulation.condtionNotMatch(inputData, getText1);
				}
				break;
			case "DeleteAllCookies":
				Navigate.deleteAllCookies(driver);
				break;
			case "TakeScreenShot":
				Navigate.screenShot(driver, inputData);
				break;
			case "Highlight":
				Navigate.highLightElement(driver, element);
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
				returnObj = Navigate.sendHttpPost(inputData);
				break;
			case "SplitAndOpenURL":
				if (inputData == null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					int refStep = new Integer(referenceStep);
					String getText = getTextMap.get(Integer.valueOf(refStep));
					String[] openURL = getText.split("https://www.google.de/");
					driver.get(openURL[0]);
				}
				break;

			case "ConcatStrings":
				String concat = "";
				if (inputData == null && referenceStep != null
						&& !referenceStep.trim().equals("")) {
					String[] splitReference = referenceStep.split(",");
					int size = splitReference.length;
					for (int i = 0; i < size; i++) {
						String getText12 = getTextMap.get(Integer
								.valueOf(splitReference[i]));
						concat = concat + getText12;
					}
				}
				if (inputData != null && referenceStep == null
						&& !inputData.trim().equals("")) {
					splitInputData = inputData.split(",");
					int size = splitInputData.length;
					for (int i = 0; i < size; i++) {
						concat = concat + splitInputData[i];
					}
				}
				System.out.println(concat);
				returnObj = concat;
				break;
			case "Concat2String":
				String[] splitreference = referenceStep.split(",");
				int refStep12 = new Integer(splitreference[0]);
				int refStep13 = new Integer(splitreference[1]);
				String getText12 = getTextMap.get(Integer.valueOf(refStep12));
				String getText13 = getTextMap.get(Integer.valueOf(refStep13));
				String con = getText12.concat(getText13);
				returnObj = con;
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
				returnObj = com.zillion.qa.practitioner.Patients
				.practitionerAddNewPatient(driver, inputData);
				getTextMap.put(stepNo, returnObj.toString());
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
				returnObj = Manipulation.GetCurrentWindow(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "GetSecondWindowHandle":
				Manipulation.getSecondWindow(driver, element);
				break;
			case "SwitchParticularWindow":
				int refStep2 = new Integer(referenceStep);
				String win = getTextMap.get(Integer.valueOf(refStep2));
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
				com.zillion.qa.member.Dashboard.uploadPhotos(driver, inputData);
				break;
			case "DashboardWeight":
				com.zillion.qa.member.Dashboard.dashboardEnterWeight(driver,
						inputData);
				break;
			case "DeletePhotos":
				com.zillion.qa.member.Dashboard.deletePhotos(driver);
				break;
			case "ValidateInsufficientAllSessionTab":
				com.zillion.qa.programadmin.Classes
				.validateInsufficientAllSessionTab(driver);
				break;
			case "PaginationVerificationPAClientTab":
				com.zillion.qa.programadmin.Clients
				.clientTabPaginationVerification(driver, inputData);
				break;
			case "MailDrop":
				com.zillion.qa.practitioner.Patients.mailDrop(driver);
				break;
			case "RightClickMailDrop":
				Manipulation.rightClickMailDrop(driver, element);
				break;
			case "MailDropRegistrationCompleteLink":
				com.zillion.qa.practitioner.Patients
				.mailDropRegistrationCompleteLink(driver);
				break;
			case "AddNewMember":
				com.zillion.qa.opsadmin.Members.addNewMember(driver, inputData);
				break;
			case "NewMemberClearButtonValidation":
				com.zillion.qa.opsadmin.Members.addNewMemberClearButton(driver,
						inputData);
				break;
			case "GuerillaMailReload":
				com.zillion.qa.member.Dashboard.guerrillaMail(driver, normalXpath);
				break;
			case "MemberLoginAfterEnrollment":
				com.zillion.qa.practitioner.Patients
				.memberLoginAfterEnrollment(driver);
				break;
			case "PractitionerProfileVerificationAndValidation":
				com.zillion.qa.practitioner.Patients
				.practitionerProfileValidationAndVerification(driver);
				break;
			case "PractitionerAddPatientVerify":
				com.zillion.qa.practitioner.Patients
				.practitionerAddNewPatientAndVerify(driver);
				break;
			case "ValidationDbMemberProfile":
				int mailRefStep = new Integer(referenceStep);
				String mailId = getTextMap.get(Integer.valueOf(mailRefStep));
				returnObj = com.zillion.qa.coaches.Members
						.validationDbMemberProfile(driver, inputData, mailId);
				break;
			case "ValidationDbJournal":
				returnObj = com.zillion.qa.member.Tracker.validationDbJournal(
						driver, inputData);
				break;
			case "DeletePhotosBeforeUpload":
				com.zillion.qa.member.Dashboard.deletePhotosBeforeUpload(driver);
				break;
			case "ValidateUpcomingSessions":
				com.zillion.qa.coaches.Classes.validateUpcomingSessions(driver);
				break;
			case "SeparateDigitsAndAlphabets":
				com.zillion.qa.commands.Manipulation.separateDigitsAndAlphabets(
						driver, inputData);
				break;
			case "TypeDynamicValueReuse":
				returnObj = Manipulation
				.dynamicSendkeys(driver, inputData, element);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "ValidatePreviousSessions":
				com.zillion.qa.coaches.Classes.validatePreviousSessions(driver);
				break;
			case "DataBaseProviderMyProfileVerificationAndValidation":
				com.zillion.qa.practitioner.Patients
				.providerMyProfileVerificationAndValidationUsingDB(driver,
						inputData);
				break;
			case "DateAndTimeEnabled":
				returnObj = com.zillion.qa.opsadmin.Classes
				.dateAndTimeEnabled(driver);
				break;
			case "MemberVerifyLectureSession":
			{
				int verifyMember = new Integer(referenceStep);
				String verifyMemberSignUp1 = getTextMap.get(Integer.valueOf(verifyMember));
				returnObj = com.zillion.qa.member.liveSessionSubCommonMethods
						.memberVerifyLectureSession(driver,verifyMemberSignUp1);
				break;
			}
			case "TimeSlotSearch":
				returnObj = com.zillion.qa.opsadmin.Providers
				.timeSlotSearch(driver);
				break;
			case "DbMemberUserid":
				if (inputData != null && referenceStep == null) {
					returnObj = com.zillion.qa.member.Tracker.dbMemberUserid(
							driver, inputData);
					getTextMap.put(stepNo, returnObj.toString());
				} else if (inputData == null && referenceStep != null) {
					int ref1 = new Integer(referenceStep);
					String ref2 = getTextMap.get(Integer.valueOf(ref1));
					returnObj = com.zillion.qa.member.Tracker.dbMemberUserid(
							driver, ref2);
					getTextMap.put(stepNo, returnObj.toString());
				}
				break;
			case "RetrieveProviderIdDB":
				if (inputData == null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					int ref1 = new Integer(referenceStep);
					String ref2 = getTextMap.get(Integer.valueOf(ref1));
					returnObj = com.zillion.qa.coaches.Classes.retrieveProviderId(
							driver, ref2);
					getTextMap.put(stepNo, returnObj.toString());
				} else if (inputData != null && referenceStep != null
						&& !referenceStep.trim().equals("")) {
					returnObj = com.zillion.qa.coaches.Classes.retrieveProviderId(
							driver, inputData);
					getTextMap.put(stepNo, returnObj.toString());
				}
				break;
			case "CoachApprovalStatus1on1Session":
				returnObj = com.zillion.qa.coaches.Classes
				.coachApprovedStatus1on1Session(driver);
				break;
			case "CurrentDateHFopsTimeSlot":
				com.zillion.qa.opsadmin.Classes.currentDateHFopsTimeSlot(driver);
				break;
			case "LectureMemberLogin":
				com.zillion.qa.member.Dashboard.lectureMemberLogin(driver);
				break;
			case "CoachUnapprovedStatus1on1Session":
				returnObj = com.zillion.qa.coaches.Classes
				.coachUnapprovedStatus1on1Session(driver);
				break;
			case "CoachApprovedStatusLectureSession":
				returnObj = com.zillion.qa.coaches.Classes
				.coachApprovedStatusLectureSession(driver);
				break;
			case "CoachUnapprovedStatusLectureSession":
				returnObj = com.zillion.qa.coaches.Classes
				.coachUnapprovedStatusLectureSession(driver);
				break;
			case "TypeReference":
				int refStep = new Integer(referenceStep);
				String gettext = getTextMap.get(Integer.valueOf(refStep));
				returnObj = Manipulation.sendKeys(element, gettext);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "CoachProfileValidationUsingDB":
				int coachProfileMemEmail = new Integer(referenceStep);
				String coachProfileMemEmail1 = getTextMap.get(Integer.valueOf(coachProfileMemEmail));
				returnObj = com.zillion.qa.coaches.Dashboard
						.coachProfileValidationUsingDB(driver,inputData,coachProfileMemEmail1);
				break;
			case "ProgramAdminProfileValidationUsingDB":
				returnObj = com.zillion.qa.programadmin.Patients
				.programAdminProfileValidationUsingDB(driver, inputData);
				break;
			case "PAWellnesscorpLogin":
				com.zillion.qa.programadmin.Dashboard.paWellnesscorpLogin(driver);
				break;
			case "PAWellnesscorpLogout":
				com.zillion.qa.programadmin.Dashboard.paWellnesscorpLogout(driver);
				break;
			case "RetrievePhoneFromCoachDB":
				returnObj = com.zillion.qa.coaches.Dashboard
				.retrievePhoneFromCoachDB(driver, inputData);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RetrieveMobileFromCoachDB":
				returnObj = com.zillion.qa.coaches.Dashboard
				.retrieveMobileFromCoachDB(driver, inputData);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RetrieveCityFromCoachDB":
				returnObj = com.zillion.qa.coaches.Dashboard
				.retrieveCityFromCoachDB(driver, inputData);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RetrieveZipFromCoachDB":
				returnObj = com.zillion.qa.coaches.Dashboard
				.retrieveZipFromCoachDB(driver, inputData);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RetrieveBioFromCoachDB":
				returnObj = com.zillion.qa.coaches.Dashboard
				.retrieveBioFromCoachDB(driver, inputData);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "SumOfUpcomingAndPrevious":
				com.zillion.qa.opsadmin.Classes
				.validateUpcomingPreviousAllSession(driver);
				break;
			case "GuerrillaMail":
				com.zillion.qa.practitioner.Patients.guerrillaMail(driver);
				break;
			case "GuerrillaMailRegistrationCompleteLink":
				int EmailValue1 = new Integer(referenceStep);
				String EmailValue = getTextMap.get(Integer.valueOf(EmailValue1));
				com.zillion.qa.practitioner.Patients
				.guerrillaMailRegistrationCompleteLink(driver,EmailValue);
				break;
			case "ScheduleOrRescheduleOrCancelSession":
				com.zillion.qa.member.Dashboard
				.scheduleOrRescheduleOrCancelSession(driver, inputData);
				break;
			case "VerifyViewSessions":
				com.zillion.qa.member.Dashboard.verifyViewSessions(driver);
				break;
			case "ContentLibrarySearchVerification":
				com.zillion.qa.member.Library
				.memberContentLibrarySearchAndVerify(driver);
				break;
			case "CalculateMaxValueofStartingWeight":
				returnObj = com.zillion.qa.member.Tracker
				.calculateMaxValueofStartingWeight(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "CalculateMoreThanMaxValueofStartingWeight":
				returnObj = com.zillion.qa.member.Tracker
				.calculateMoreThanMaxValueofStartingWeight(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "NextDateCoachUnavailableTimeSlot":
				com.zillion.qa.opsadmin.Classes
				.nextDateCoachUnavailabeTimeSlot(driver);
				break;
			case "AlertAccept":
				returnObj = Navigate.alertAccept(driver);
				break;
			case "RetrieveCurrentWeight":
				int refStep1 = new Integer(referenceStep);
				String gettext1 = getTextMap.get(Integer.valueOf(refStep1));
				System.out.println("gettext1" + gettext1);
				returnObj = com.zillion.qa.member.Tracker.retrieveCurrentWeight(
						driver, gettext1);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "CalculateMaxValueofStartingWeightDashboard":
				com.zillion.qa.member.Tracker
				.calculateMaxValueofStartingWeightDashboard(driver);
				break;
			case "CalculateMoreThanMaxValueofStartingWeightDashboard":
				com.zillion.qa.member.Tracker
				.calculateMoreThanMaxValueofStartingWeightDashboard(driver);
				break;
			case "CalculateBMI":
				if (inputData == null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					String[] referenceSteps = StringUtils.split(referenceStep, ",");
					int refStep3 = new Integer(referenceSteps[0]);
					int refStep4 = new Integer(referenceSteps[1]);
					String getText1 = getTextMap.get(Integer.valueOf(refStep3));
					String getText2 = getTextMap.get(Integer.valueOf(refStep4));
					returnObj = com.zillion.qa.member.Tracker.calculateBMI(driver,
							getText1, getText2);
					getTextMap.put(stepNo, returnObj.toString());
				} else if (inputData != null && referenceStep != null
						&& !referenceStep.trim().equals("")) {
					String[] referenceSteps = StringUtils.split(referenceStep, ",");
					int refStep3 = new Integer(referenceSteps[0]);
					int refStep4 = new Integer(referenceSteps[1]);
					String getText1 = getTextMap.get(Integer.valueOf(refStep3));
					String getText2 = getTextMap.get(Integer.valueOf(refStep4));
					returnObj = com.zillion.qa.member.Tracker.calculateBMI(driver,
							getText1, getText2);
					Assert.fail();
				}
				break;
			case "RetrieveHeightOfTheMember":
				returnObj = com.zillion.qa.member.Tracker
				.retrieveHeightOfTheMember(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RetrieveAccountIdOfTheMember":
				returnObj = com.zillion.qa.member.Tracker
				.retrieveAccountIdOfTheMember(driver, inputData);
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
				com.zillion.qa.member.LiveSession.oneOnOneliveSession(driver,
						inputData);
				break;
			case "ChangePageNumber":
				com.zillion.qa.opsadmin.Programs
				.changePageNumber(driver, inputData);
				break;
			case "RetrieveProgramId1OfTheMember":
				int refStepForAccountid = new Integer(referenceStep);
				String AccountIdText = getTextMap.get(Integer
						.valueOf(refStepForAccountid));
				returnObj = com.zillion.qa.member.Dashboard
						.retrieveProgramId1OfTheMember(driver, AccountIdText);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RetrieveProgramId2OfTheMember":
				int refStepForAccountid2 = new Integer(referenceStep);
				String AccountIdText2 = getTextMap.get(Integer
						.valueOf(refStepForAccountid2));
				returnObj = com.zillion.qa.member.Dashboard
						.retrieveProgramId2OfTheMember(driver, AccountIdText2);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "UpdateDateinAccountProgram":
				String[] referenceSteps = StringUtils.split(referenceStep, ",");
				int refStep3 = new Integer(referenceSteps[0]);
				int refStep4 = new Integer(referenceSteps[1]);
				String getText1 = getTextMap.get(Integer.valueOf(refStep3));
				String getText2 = getTextMap.get(Integer.valueOf(refStep4));
				com.zillion.qa.member.Dashboard.updateDateinAccountProgram(driver,
						getText1, getText2);
				break;
			case "UpdateDateinAccountProgramMembership":
				String[] referenceSteps1 = StringUtils.split(referenceStep, ",");
				int refStep31 = new Integer(referenceSteps1[0]);
				int refStep41 = new Integer(referenceSteps1[1]);
				String getText11 = getTextMap.get(Integer.valueOf(refStep31));
				String getText21 = getTextMap.get(Integer.valueOf(refStep41));
				com.zillion.qa.member.Dashboard
				.updateDateinAccountProgramMembership(driver, getText11,
						getText21);
				break;
			case "compareRealAppeal":
				com.zillion.qa.opsadmin.Programs.compare_Real_Appeal(driver);
				break;
			case "SplitStringCompare":
				if (inputData == null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					System.out.println("reference number:" + referenceStep);
					String[] splitreference2 = referenceStep.split(",");
					System.out.println("reference split1:" + splitreference2[0]);
					System.out.println("reference split2:" + splitreference2[1]);
					int st1 = new Integer(splitreference2[0]);
					int refStep123 = new Integer(splitreference2[1]);
					String ss = getTextMap.get(Integer.valueOf(st1));
					String getText123 = getTextMap.get(Integer.valueOf(refStep123));
					returnObj = com.zillion.qa.opsadmin.Programs.spliting_string(
							ss, getText123);
				}
				break;
			case "RetrieveMemberAccountId":
				if (referenceStep != null) {
					int accountidref = new Integer(referenceStep);
					String accountidref1 = getTextMap.get(Integer
							.valueOf(accountidref));
					String accountidref2 = "temp," + accountidref1;
					returnObj = com.zillion.qa.member.liveSessionSubCommonMethods
							.retrieveMemberAccountId(driver, accountidref2);
					getTextMap.put(stepNo, returnObj.toString());
				} else if (inputData != null) {
					System.out.println("inputData" + inputData);
					returnObj = com.zillion.qa.member.liveSessionSubCommonMethods
							.retrieveMemberAccountId(driver, inputData);
					getTextMap.put(stepNo, returnObj.toString());
				}
				break;
			case "RetrieveLectureSessionMemberAccountId":
				returnObj = com.zillion.qa.member.liveSessionSubCommonMethods
				.retrieveLectureSessionMemberAccountId(driver, inputData);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RetrieveAccountProgramID":
				if (inputData == null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					int refnumberpgmid = new Integer(referenceStep);
					String refnumberpgmid1 = getTextMap.get(Integer
							.valueOf(refnumberpgmid));
					returnObj = com.zillion.qa.member.liveSessionSubCommonMethods
							.retrieveAccountProgramID(driver, refnumberpgmid1);
				} else if (inputData != null && referenceStep != null
						&& !referenceStep.trim().equals("")) {
					String mailId1 = "";
					returnObj = com.zillion.qa.member.liveSessionSubCommonMethods
							.retrieveAccountProgramID(driver, mailId1);
				}
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RetrieveHostNameWithProgramID":
				if (inputData == null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					int hstnameref = new Integer(referenceStep);
					String hstname = getTextMap.get(Integer.valueOf(hstnameref));
					System.out.println("hstname" + hstname);
					returnObj = com.zillion.qa.member.liveSessionSubCommonMethods
							.retrieve1on1SessionHostNameWithProgramID(driver,
									hstname);
				} else if (inputData != null && referenceStep != null
						&& !referenceStep.trim().equals("")) {
					returnObj = com.zillion.qa.member.liveSessionSubCommonMethods
							.retrieve1on1SessionHostNameWithProgramID(driver,
									inputData);
				}
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "LectureAddLiveLiveSessionForDifferentBrowser":
				int coachEmail = new Integer(referenceStep);
				String coachEmail1 = getTextMap.get(Integer
						.valueOf(coachEmail));
				com.zillion.qa.member.LiveSession.lectureAddLiveLiveSessionForDifferentBrowser(driver,
						inputData, coachEmail1);
				break;
			case "LectureZLiveLiveSessionForDifferentBrowser":
				int coachEmail12 = new Integer(referenceStep);
				String coachEmail2 = getTextMap.get(Integer
						.valueOf(coachEmail12));
				com.zillion.qa.member.LiveSession.lectureZLiveLiveSessionForDifferentBrowser(driver,
						inputData, coachEmail2);
				break;
			case "RetrieveNameOfTheMember":
				if (referenceStep != null) {
					int memberName = new Integer(referenceStep);
					String memberNamefor1on1 = getTextMap.get(Integer
							.valueOf(memberName));
					returnObj = com.zillion.qa.opsadmin.Dashboard
							.retrieveNameOfTheMember(driver, memberNamefor1on1);


				} else if (inputData != null) {
					returnObj = com.zillion.qa.opsadmin.Dashboard
							.retrieveNameOfTheMember(driver, inputData);
				}

				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RetrieveFirstNameOfTheMember":
				int memberName = new Integer(referenceStep);
				String memberFirstName = getTextMap.get(Integer
						.valueOf(memberName));
				returnObj = com.zillion.qa.opsadmin.Dashboard
						.retrieveFirstNameOfTheMember(driver, memberFirstName);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "GetMemberFirstNameFromApp":
				int firstName = new Integer(referenceStep);
				String firstName2 = getTextMap.get(Integer.valueOf(firstName));
				returnObj = com.zillion.qa.opsadmin.Dashboard
						.getMemberFirstNameFromApp(driver, firstName2);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "GetMemberLastNameFromApp":
				int memberLabel = new Integer(referenceStep);
				String memberLabel2 = getTextMap.get(Integer.valueOf(memberLabel));
				returnObj = com.zillion.qa.opsadmin.Dashboard
						.getMemberLastNameFromApp(driver, memberLabel2);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RetrieveLastNameOfTheMember":
				int memName = new Integer(referenceStep);
				String memberLastName = getTextMap.get(Integer
						.valueOf(memName));
				returnObj = com.zillion.qa.opsadmin.Dashboard
						.retrieveLastNameOfTheMember(driver, memberLastName);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "Retrieve1on1SessionEmailWithCoachHostName":
				if (referenceStep != null) {
					int refStep2coachmailid = new Integer(referenceStep);
					String refStep2coachmail = getTextMap.get(Integer
							.valueOf(refStep2coachmailid));
					returnObj = com.zillion.qa.member.liveSessionSubCommonMethods
							.retrive1on1SessionEmailWithCoachHostId(driver,
									refStep2coachmail);
					getTextMap.put(stepNo, returnObj.toString());
				} else if (inputData != null) {
					returnObj = com.zillion.qa.member.liveSessionSubCommonMethods
							.retrive1on1SessionEmailWithCoachHostId(driver,
									inputData);
					getTextMap.put(stepNo, returnObj.toString());
				}
				break;
			case "RetrieveLectureSessionEmailWithCoachHostName":
				returnObj = com.zillion.qa.member.liveSessionSubCommonMethods
				.retriveLectureSessionEmailWithCoachHostName(driver);
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
				com.zillion.qa.member.liveSessionSubCommonMethods
				.browserMinimize(driver);
				break;
			case "AllowPlugins":
				com.zillion.qa.member.liveSessionSubCommonMethods
				.allowPlugins(driver);
				break;
			case "DeleteAllMails":
				com.zillion.qa.member.Dashboard.deleteAllMails(driver);
				break;
			case "VerifyClientSearch":
				int clientname1 = new Integer(referenceStep);
				String clientname2 = getTextMap.get(Integer.valueOf(clientname1));
				com.zillion.qa.opsadmin.Members.verifyClientSearch(driver,
						clientname2);
				break;
			case "VerifyMemberOnboardedStatusOrders":
				returnObj = com.zillion.qa.opsadmin.Members
				.verifyMemberOnboardedstatusOrders(driver);
				break;
			case "VerifyMembersNameAscendingOrder":
				returnObj = com.zillion.qa.opsadmin.Members
				.verifyMembersNameAscendingOrder(driver);
				break;
			case "VerifyMemberClientOrders":
				returnObj = com.zillion.qa.opsadmin.Members
				.verifyMemberClientOrders(driver);
				break;
			case "VerifyMembersEmailOrders":
				returnObj = com.zillion.qa.opsadmin.Members
				.verifyMembersEmailOrders(driver);
				break;
			case "VerifyMemberProgramOrders":
				returnObj = com.zillion.qa.opsadmin.Members
				.verifyMemberProgramOrders(driver);
				break;
			case "RetrieveIdOfTheMember":
				if (referenceStep != null) {
					int refnumberpgmid = new Integer(referenceStep);
					String refnumberpgmid1 = getTextMap.get(Integer
							.valueOf(refnumberpgmid));
					returnObj = com.zillion.qa.opsadmin.Dashboard
							.retrieveIdOfTheMember(driver, refnumberpgmid1);
					getTextMap.put(stepNo, returnObj.toString());
				} else if (inputData != null) {
					System.out.println("inputData" + inputData);
					returnObj = com.zillion.qa.opsadmin.Dashboard
							.retrieveIdOfTheMember(driver, inputData);
					System.out.println("returnObj" + returnObj);
					getTextMap.put(stepNo, returnObj.toString());
				}
				break;
			case "EmailCharactersLimitationVerification":
				int id_1 = new Integer(referenceStep);
				String id2 = getTextMap.get(Integer
						.valueOf(id_1));
				com.zillion.qa.opsadmin.Members
				.emailCharactersLimitationVerification(driver, inputData, id2);
				break;
			case "RatingStars":
				com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
				.sessionratingStars(driver);
				break;
			case "RatingNotesCoachSide":
				com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
				.ratingNotesCoachSide(driver, inputData);
				break;
			case "VerifyMemberAccountStatusOrders":
				com.zillion.qa.opsadmin.Dashboard
				.verifyMemberAccountStatusOrders(driver);
				break;
			case "RetrieveHostnameofCoach":
				returnObj = com.zillion.qa.member.liveSessionSubCommonMethods
				.retrieveHostnameofCoach(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "OneOnOneLiveSessionCoachLogin":
				com.zillion.qa.member.liveSessionSubCommonMethods
				.oneOnOneLiveSessionCoachLogin(driver);
				break;
			case "RetrieveAddressOfTheMember":
				if (inputData != null) {
					returnObj = com.zillion.qa.opsadmin.Members
							.retrieveAddressOfTheMember(driver, inputData);
					getTextMap.put(stepNo, returnObj.toString());
				} else if (referenceStep != null) {
					int getxyref = new Integer(referenceStep);
					String getxyref1 = getTextMap.get(Integer.valueOf(getxyref));
					returnObj = com.zillion.qa.opsadmin.Members
							.retrieveAddressOfTheMember(driver, getxyref1);
					getTextMap.put(stepNo, returnObj.toString());
				}
				break;
			case "RetrieveAddress1OfTheMember":
				if (inputData != null) {
					returnObj = com.zillion.qa.opsadmin.Members
							.retrieveAddress1OfTheMember(driver, inputData);
					getTextMap.put(stepNo, returnObj.toString());
				} else if (referenceStep != null) {
					int getxyref = new Integer(referenceStep);
					String getxyref1 = getTextMap.get(Integer.valueOf(getxyref));
					returnObj = com.zillion.qa.opsadmin.Members
							.retrieveAddress1OfTheMember(driver, getxyref1);
					getTextMap.put(stepNo, returnObj.toString());
				}
				break;
			case "OpsAdminMemberEditDOB":
				returnObj = com.zillion.qa.realappealmember.member
				.opsAdminDOB(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "MemberProfileDOB":
				returnObj = com.zillion.qa.realappealmember.member
				.memberProfileDOB(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "CompareDOB":
				returnObj = com.zillion.qa.realappealmember.member
				.compareDOB(driver);
				break;
			case "RetrieveScreenNameOfTheMember":
				int screenName1 = new Integer(referenceStep);
				String screenName2 = getTextMap.get(Integer.valueOf(screenName1));
				returnObj = com.zillion.qa.opsadmin.Members
						.retrieveScreenNameOfTheMember(driver, screenName2);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RetrieveSecurityOfTheMember":
				int securityans = new Integer(referenceStep);
				String securityans1 = getTextMap.get(Integer.valueOf(securityans));
				returnObj = com.zillion.qa.opsadmin.Members
						.retrieveSecurityOfTheMember(driver, securityans1);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "VerifyMemberCoachOrders":
				returnObj = com.zillion.qa.opsadmin.Members
				.verifyMemberCoachOrders(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "VerifyMemberMemberIdOrders":
				returnObj = com.zillion.qa.opsadmin.Members
				.verifyMemberMemberIdOrders(driver);
				break;
			case "ScreenShotInReport":
				Navigate.ScreenShotinReport(driver, inputData);
				break;
			case "RetrieveAvailableMemberAccountId":
				com.zillion.qa.member.liveSessionSubCommonMethods
				.retrieveAvailableMemberAccountId(driver);
				break;
			case "RetrieveAvailableMemberEmailIdWithAccountID":
				int accountid = new Integer(referenceStep);
				String accountid1 = getTextMap.get(Integer.valueOf(accountid));
				returnObj = com.zillion.qa.member.liveSessionSubCommonMethods
						.retrieveAvailableMemberEmailIdWithAccountID(driver,
								accountid1);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RetrieveemailphonesmsenableOfTheMember":
				int emailPhonesmsEnable = new Integer(referenceStep);
				String emailPhonesmsEnable1 = getTextMap.get(Integer.valueOf(emailPhonesmsEnable));
				returnObj = com.zillion.qa.opsadmin.Members
						.retrieveemailphonesmsenableOfTheMember(driver, emailPhonesmsEnable1);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "CoachNameVerify":
				com.zillion.qa.opsadmin.Members.coachNameVerify(driver, inputData);
				break;
			case "HFOPsValidationDbMemberInsurance":
				returnObj = com.zillion.qa.opsadmin.Members
				.hFOPsValidationDbMemberInsurance(driver);
				break;
			case "VerifyMembersRequestDateAscendingOrders":
				returnObj = com.zillion.qa.opsadmin.Members
				.verifyMembersRequestDateAscendingOrders(driver);
				break;
			case "VerifyMembersRequestDateDescendingOrders":
				returnObj = com.zillion.qa.opsadmin.Members
				.verifyMembersRequestDateDescendingOrders(driver);
				break;
			case "RetrieveCotentCount":
				returnObj = com.zillion.qa.member.Dashboard
				.retrieveCotentCount(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "CalculateDisplayedContentsCount":
				returnObj = com.zillion.qa.member.Dashboard
				.calculateDisplayedContentsCount(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "TargetWeight":
				returnObj = com.zillion.qa.opsadmin.Members.targetWeight(driver);
				break;
			case "HalfPageScrollDown":
				com.zillion.qa.opsadmin.Members.halfPageDown(driver);
				break;
			case "VerifyMemberDateOfBirthUsingDB":
				com.zillion.qa.opsadmin.Members.verifyMemberDateOfBirthUsingDB(
						driver, inputData);
				break;
			case "RobotTab":
				Navigate.robotTab(driver);
				break;
			case "GetAttributeWithPrefixConcate":
				returnObj = ElementActions.getAttributeWithPrefixConcate(element,
						inputData);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "CheckTwoStringWithConcatedPrefixForFirstValue":
				if (inputData == null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					String[] referenceSteps14 = StringUtils.split(referenceStep,
							",");
					int refStep11 = new Integer(referenceSteps14[0]);
					int refStep21 = new Integer(referenceSteps14[1]);
					String getText115 = getTextMap.get(Integer.valueOf(refStep11));
					String getText215 = getTextMap.get(Integer.valueOf(refStep21));
					returnObj = Manipulation.condtionMatch("Re:" + getText115,
							getText215);
				}
				break;
			case "EnrollmentProcessStep1PrivacyPolicyLink":
				com.zillion.qa.practitioner.Patients
				.enrollmentProcessStep1PrivacyPolicyLink(driver, inputData);
				break;
			case "EnrollmentProcessStep1TermsAndConditionLink":
				com.zillion.qa.practitioner.Patients
				.enrollmentProcessStep1TermsAndConditionLink(driver,
						inputData);
				break;
			case "EnrollmentProcessStep2PrivacyPolicyLink":
				com.zillion.qa.practitioner.Patients
				.enrollmentProcessStep2PrivacyPolicyLink(driver, inputData);
				break;
			case "EnrollmentProcessStep2TermsAndConditionLink":
				com.zillion.qa.practitioner.Patients
				.enrollmentProcessStep2TermsAndConditionLink(driver,
						inputData);
				break;
			case "JenkinDbTimeslot":
				returnObj = com.zillion.qa.member.Dashboard.jenkinDbTimeslot(
						driver, inputData);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "JenkinStartdate":
				returnObj = com.zillion.qa.member.Dashboard.jenkinStartdate(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "ReloadTilMailGetsLoaded":
				com.zillion.qa.member.Dashboard.reloadTilMailGetsLoaded(driver,
						normalXpath);
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
				System.out.println("getSubjectFromSheet: " + getText115);
				String[] subjectFromSheet = getText115.split("This");
				String subjectTimeStampFromSheet = subjectFromSheet[0];
				System.out
				.println("Splitted subject time stamp from sheet Subject: "
						+ subjectTimeStampFromSheet);
				com.zillion.qa.coaches.Messages
				.messageSentByProviderSubjectValidatedWithDB(driver,
						subjectTimeStampFromSheet, getText115, inputData);
				break;
			case "MessageSentByProviderBodyValidatedWithDB":
				int refStep23 = new Integer(referenceStep);
				String getText116 = getTextMap.get(Integer.valueOf(refStep23));
				System.out.println("getBodyFromSheet: " + getText116);
				String[] bodyFromSheet = getText116.split("DO");
				String bodyTimeStampFromSheet = bodyFromSheet[0];
				System.out.println("Splitted body time stamp from sheet body: "
						+ bodyTimeStampFromSheet);
				com.zillion.qa.coaches.Messages
				.messageSentByProviderBodyValidatedWithDB(driver,
						bodyTimeStampFromSheet, getText116, inputData);
				break;
			case "MessageReceivedByProviderSubjectValidatedWithDB":
				int refStep24 = new Integer(referenceStep);
				String getText117 = getTextMap.get(Integer.valueOf(refStep24));
				System.out.println("getMessageReceivedSubjectFromSheet: "
						+ getText117);
				String[] messageReceivedSubjectFromSheet = getText117.split("This");
				String messageReceivedsubjectTimeStampFromSheet = messageReceivedSubjectFromSheet[0];
				System.out
				.println("Splitted subject time stamp from sheet Subject: "
						+ messageReceivedsubjectTimeStampFromSheet);
				com.zillion.qa.coaches.Messages
				.messageReceivedByProviderSubjectValidatedWithDB(driver,
						messageReceivedsubjectTimeStampFromSheet,
						getText117, inputData);
				break;
			case "MessageReceivedByProviderBodyValidatedWithDB":
				int refStep25 = new Integer(referenceStep);
				String getText118 = getTextMap.get(Integer.valueOf(refStep25));
				System.out
				.println("getMessageReceivedBodyFromSheet: " + getText118);
				String[] messageReceivedBodyFromSheet = getText118.split("DO");
				String messageReceivedBodyTimeStampFromSheet = messageReceivedBodyFromSheet[0];
				System.out.println("Splitted body time stamp from sheet body: "
						+ messageReceivedBodyTimeStampFromSheet);
				com.zillion.qa.coaches.Messages
				.messageReceivedByProviderBodyValidatedWithDB(driver,
						messageReceivedBodyTimeStampFromSheet, getText118,
						inputData);
				break;
			case "MessageReceivedByMemberSubjectValidatedWithDB":
				String[] referenceSteps11 = StringUtils.split(referenceStep, ",");
				int refStep26 = new Integer(referenceSteps11[0]);
				int refStep261 = new Integer(referenceSteps11[1]);
				String getText119 = getTextMap.get(Integer.valueOf(refStep26));
				String getText1191 = getTextMap.get(Integer.valueOf(refStep261));
				System.out.println("getMessageReceivedSubjectFromSheet: "
						+ getText119);
				String[] messageReceivedMemberSubjectFromSheet = getText119
						.split("This");
				String messageReceivedMemberSubjectTimeStampFromSheet = messageReceivedMemberSubjectFromSheet[0];
				System.out
				.println("Splitted subject time stamp from sheet Subject: "
						+ messageReceivedMemberSubjectTimeStampFromSheet);
				com.zillion.qa.coaches.Messages
				.messageReceivedByMemberSubjectValidatedWithDB(driver,
						messageReceivedMemberSubjectTimeStampFromSheet,
						getText119, getText1191);
				break;
			case "MessageReceivedByMemberBodyValidatedWithDB":
				String[] referenceSteps12 = StringUtils.split(referenceStep, ",");
				int refStep27 = new Integer(referenceSteps12[0]);
				int refStep271 = new Integer(referenceSteps12[1]);
				String getText120 = getTextMap.get(Integer.valueOf(refStep27));
				String getText123 = getTextMap.get(Integer.valueOf(refStep271));
				System.out.println("getMessageReceivedSubjectFromSheet: "
						+ getText120);
				String[] messageReceivedMemberBodyFromSheet = getText120
						.split("DO");
				String messageReceivedMemberBodyTimeStampFromSheet = messageReceivedMemberBodyFromSheet[0];
				System.out.println("Splitted body time stamp from sheet body: "
						+ messageReceivedMemberBodyTimeStampFromSheet);
				com.zillion.qa.coaches.Messages
				.messageReceivedByMemberBodyValidatedWithDB(driver,
						messageReceivedMemberBodyTimeStampFromSheet,
						getText120, getText123);
				break;
			case "RetrieveLimitsOfClasses":
				returnObj = com.zillion.qa.realappealprogramadmin.programadmin
				.retrieveLimitsOfClasses(driver, inputData);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "CompareUnApproved&ApprovedclassesCount":
				String[] ApprovedUnapproved = StringUtils.split(referenceStep, ",");
				int approvedcnt = new Integer(ApprovedUnapproved[0]);
				int unapprovedcnt = new Integer(ApprovedUnapproved[1]);
				String approvedCount = getTextMap.get(Integer.valueOf(approvedcnt));
				String unapprovedCount = getTextMap.get(Integer
						.valueOf(unapprovedcnt));
				returnObj = com.zillion.qa.realappealprogramadmin.programadmin
						.compareAndVerifyTheApprovedAndUnapprovedClassesCount(
								driver, approvedCount, unapprovedCount);
				break;
			case "RealAppealLaunchMemberURL":
				returnObj = com.zillion.qa.realappealmember.member
				.realAppealLaunchMemberURL(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RealAppealLaunchProviderURL":
				com.zillion.qa.realappealprogramadmin.programadmin
				.realAppealLaunchProviderURL(driver);
				break;
			case "GetWindowUsingJsclick":
				Manipulation.getWindowUsingJsclick(driver, normalXpath);
				break;
			case "RealAppealProgramAdminLogin":
				com.zillion.qa.realappealprogramadmin.programadmin
				.realAppealProgramAdminLogin(driver);
				break;
			case "RealAppealProgramAdminLogout":
				com.zillion.qa.realappealprogramadmin.programadmin
				.realAppealProgramAdminLogout(driver);
				break;
			case "RealAppealCoachLogin":
				com.zillion.qa.realappealcoach.coach.realAppealCoachLogin(driver);
				break;
			case "RealAppealCoachLogout":
				com.zillion.qa.realappealcoach.coach.realAppealCoachLogout(driver);
				break;
			case "RetrieveCoachAvailableHrs":
				if (inputData != null && referenceStep == null) {
					returnObj = com.zillion.qa.realappealcoach.coach
							.retrieveCoachAvailableHrs(driver, inputData);
					getTextMap.put(stepNo, returnObj.toString());
				} else if (inputData == null && referenceStep != null
						&& !referenceStep.trim().equals("")) {
					int mailidref1 = new Integer(referenceStep);
					String mailidtext1 = getTextMap
							.get(Integer.valueOf(mailidref1));
					returnObj = com.zillion.qa.realappealcoach.coach
							.retrieveCoachAvailableHrs(driver, mailidtext1);
					getTextMap.put(stepNo, returnObj.toString());
				}
				break;
			case "VerifyTheScheduleClasses":
				com.zillion.qa.realappealprogramadmin.programadmin
				.verifyTheScheduleClasses(driver);
				break;
			case "ValidateClassOnCoach":
				int coachref = new Integer(referenceStep);
				String coachref1 = getTextMap.get(Integer.valueOf(coachref));
				com.zillion.qa.realappealprogramadmin.programadmin
				.validateClassOnCoach(driver, coachref1);
				break;
			case "DBRealAppealMemberEnrollmentStatus":
				returnObj = com.zillion.qa.realappealprogramadmin.programadmin
				.dBRealAppealMemberEnrollmentStatus(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "MessageSentByMemberSubjectValidatedWithDB":
				String[] referenceSteps14 = StringUtils.split(referenceStep, ",");
				int refStep28 = new Integer(referenceSteps14[0]);
				int refStep281 = new Integer(referenceSteps14[1]);
				String getText121 = getTextMap.get(Integer.valueOf(refStep28));
				String getText1211 = getTextMap.get(Integer.valueOf(refStep281));
				System.out.println("getMessageReceivedSubjectFromSheet: "
						+ getText121);
				String[] messageSentMemberSubjectFromSheet = getText121
						.split("This");
				String messageSentMemberSubjectTimeStampFromSheet = messageSentMemberSubjectFromSheet[0];
				System.out
				.println("Splitted subject time stamp from sheet Subject: "
						+ messageSentMemberSubjectTimeStampFromSheet);
				com.zillion.qa.coaches.Messages
				.messageSentByMemberSubjectValidatedWithDB(driver,
						messageSentMemberSubjectTimeStampFromSheet,
						getText121, getText1211);
				break;
			case "MessageSentByMemberBodyValidatedWithDB":
				String[] referenceSteps15 = StringUtils.split(referenceStep, ",");
				int refStep29 = new Integer(referenceSteps15[0]);
				int refStep291 = new Integer(referenceSteps15[1]);

				String getText122 = getTextMap.get(Integer.valueOf(refStep29));
				String getText1221 = getTextMap.get(Integer.valueOf(refStep291));
				System.out
				.println("getMessageReceivedBodyFromSheet: " + getText122);
				String[] messageSentMemberBodyFromSheet = getText122.split("DO");
				String messageSentMemberBodyTimeStampFromSheet = messageSentMemberBodyFromSheet[0];
				System.out.println("Splitted body time stamp from sheet body: "
						+ messageSentMemberBodyTimeStampFromSheet);
				com.zillion.qa.coaches.Messages
				.messageSentByMemberBodyValidatedWithDB(driver,
						messageSentMemberBodyTimeStampFromSheet,
						getText122, getText1221);
				break;
			case "RealAppealMemberVerifyPaginationEnableOrDisabled":
				com.zillion.qa.realappealmember.member
				.realAppealMemberVerifyPaginationEnableOrDisabled(driver);
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
				com.zillion.qa.realappealmember.member
				.appendTextToRAMemberURL(driver);
				break;
			case "AssignedCoachOfRAMemberLogin":
				if (referenceStep != null) {
					int assignedcoachref = new Integer(referenceStep);
					String assignedcoachref1 = getTextMap.get(Integer
							.valueOf(assignedcoachref));
					com.zillion.qa.realappealcoach.coach
					.assignedCoachOfMemberLogin(driver, assignedcoachref1);
				} else if (inputData != null) {
					com.zillion.qa.realappealcoach.coach
					.assignedCoachOfMemberLogin(driver, inputData);
				}
				break;
			case "AppendTextToRACoachURL":
				com.zillion.qa.realappealcoach.coach.appendTextToRACoachURL(driver);
				break;
			case "RetreieveMemberScheduledSessionFromDB":
				String[] inputs3 = inputData.split(",");
				String sessiontype1 = inputs3[0];
				String status = inputs3[1];
				returnObj = com.zillion.qa.realappealmember.member
						.retreieveMemberToScheduleSessionFromDB(driver,
								sessiontype1, status);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RetrieveHostNameForCustomizationSessionWithProgramID":
				if (inputData == null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					int hstnameref = new Integer(referenceStep);
					String hstname = getTextMap.get(Integer.valueOf(hstnameref));
					System.out.println("hstname" + hstname);
					returnObj = com.zillion.qa.member.liveSessionSubCommonMethods
							.retrieveHostIDForCustomizationSessionWithProgramID(
									driver, hstname);
				} else if (inputData != null && referenceStep != null
						&& !referenceStep.trim().equals("")) {
					returnObj = com.zillion.qa.member.liveSessionSubCommonMethods
							.retrieveHostIDForCustomizationSessionWithProgramID(
									driver, inputData);
				}
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "VerifyDBOnboardingStatus":
				returnObj = com.zillion.qa.realappealprogramadmin.programadmin
				.verifyDBOnboardingStatus(driver);
				break;
			case "VerifyMemberMailEnrollment":
				int mailref = new Integer(referenceStep);
				String mailinput = getTextMap.get(Integer.valueOf(mailref));
				com.zillion.qa.realappealmember.member.verifyMemberMailEnrollment(
						driver, mailinput);
				break;
			case "VerifyCoachMailCustomizationSession":
				if (referenceStep != null) {
					int coachmailref = new Integer(referenceStep);
					String coachmailinput = getTextMap.get(Integer
							.valueOf(coachmailref));
					com.zillion.qa.realappealcoach.coach
					.verifyCoachMailCustomizationSession(driver,
							coachmailinput);
				} else if (inputData != null) {
					com.zillion.qa.realappealcoach.coach
					.verifyCoachMailCustomizationSession(driver, inputData);
				}
				break;
			case "RealAppealMemberEnterPassword":
				com.zillion.qa.realappealmember.member
				.realAppealMemberEnterPassword(driver);
				break;
			case "CoachEnterPassword":
				com.zillion.qa.coaches.Dashboard.coachEnterPassword(driver);
				break;
			case "GetAttributeValue":
				returnObj = com.zillion.qa.realappealprogramadmin.programadmin
				.getAttribute(driver, element);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "VerifyDBOnboardingStatusClassroomNotStarted":
				returnObj = com.zillion.qa.realappealprogramadmin.programadmin
				.verifyDBOnboardingStatusClassroomNotStarted(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "VerifyUnapprovedRAPDclassifications":
				com.zillion.qa.realappealprogramadmin.programadmin
				.verifyUnapprovedRAPDclassifications(driver);
				break;
			case "CoachAllSessionSubtabVerification":
				com.zillion.qa.coaches.Dashboard
				.coachAllSessionSubtabVerification(driver);
				break;
			case "FridayBluearea":
				com.zillion.qa.member.liveSessionSubCommonMethods
				.fridayBluearea(driver);
				break;
			case "LiveSessionLectureSessionTimeSlotSearch":
				com.zillion.qa.member.liveSessionSubCommonMethods
				.liveSessionLectureSessionTimeSlotSearch(driver);
				break;
			case "CancelCreatedLectureLiveSession":
				com.zillion.qa.member.liveSessionSubCommonMethods
				.cancelCreatedLectureLiveSession(driver);
				break;
			case "ValidateMemberOnboardingStatus":
				if (inputData == null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					int mailidref = new Integer(referenceStep);
					String Membermailidref = getTextMap.get(Integer
							.valueOf(mailidref));
					System.out.println("hstname" + Membermailidref);
					returnObj = com.zillion.qa.realappealmember.member
							.validateMemberOnboardingStatus(driver, Membermailidref);
					getTextMap.put(stepNo, returnObj.toString());
				} else if (inputData != null && referenceStep != null
						&& !referenceStep.trim().equals("")) {
					returnObj = com.zillion.qa.realappealmember.member
							.validateMemberOnboardingStatus(driver, inputData);
					getTextMap.put(stepNo, returnObj.toString());
				}
				break;
			case "RetrieveHostNameForAnySessionWithProgramID":
				int hstnameref = new Integer(referenceStep);
				String hstname = getTextMap.get(Integer.valueOf(hstnameref));
				String inputData1[] = inputData.split(",");
				String sessionType = inputData1[0];
				String session_Type_Id = inputData1[1];
				returnObj = com.zillion.qa.realappealmember.member
						.retrieveHostNameWithProgramID(driver, hstname,
								sessionType, session_Type_Id);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RetrieveAcntIdWithOnboardingStatusAndMpName":
				String[] inputs = inputData.split(",");
				String onboarding_status = inputs[0];
				String mp_name = inputs[1];
				returnObj = com.zillion.qa.realappealmember.member
						.retrieveAcntIdWithOnboardingStatusAndMpName(driver,
								onboarding_status, mp_name);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "ScheduleClassPreponePostponeVerify":
				com.zillion.qa.realappealprogramadmin.programadmin
				.scheduleClassPreponePostponeVerify(driver);
				break;
			case "VerifyMemberMessageInboxPagination":
				returnObj = com.zillion.qa.realappealprogramadmin.programadmin
				.verifyMemberMessageInboxPagination(driver);
				break;
			case "RetrieveHostNickNameUsingHostId":
				int hstidref = new Integer(referenceStep);
				String hstid = getTextMap.get(Integer.valueOf(hstidref));
				returnObj = com.zillion.qa.realappealmember.member
						.retrieveHostNickNameUsingHostId(driver, hstid);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "VerifyMemberDBOnboardingStatus":
				returnObj = com.zillion.qa.realappealprogramadmin.programadmin
				.verifyMemberDBOnboardingStatus(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RetrieveClassroomId":
				int idref = new Integer(referenceStep);
				String id = getTextMap.get(Integer.valueOf(idref));
				returnObj = com.zillion.qa.realappealmember.member
						.retrieveClassroomId(driver, id);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RetrieveIsApprovedClassroom":
				int classroomidref = new Integer(referenceStep);
				String classroomid = getTextMap
						.get(Integer.valueOf(classroomidref));
				returnObj = com.zillion.qa.realappealmember.member
						.retrieveIsApproved(driver, classroomid);
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
				returnObj = com.zillion.qa.coaches.Classes
						.verifyApprovedStatusOfSession(driver, providrid);
				break;
			case "OneonOneApprovalcheckbox":
				int providridref1 = new Integer(referenceStep);
				String providrid1 = getTextMap.get(Integer.valueOf(providridref1));
				com.zillion.qa.opsadmin.Classes.oneononeApprovalcheckbox(driver,
						providrid1);
				break;
			case "LectureSessionApprovalcheckbox":
				int providridref2 = new Integer(referenceStep);
				String providrid3 = getTextMap.get(Integer.valueOf(providridref2));
				com.zillion.qa.opsadmin.Classes.lectureSessionApprovalcheckbox(
						driver, providrid3);
				break;
			case "ALertAccept1":
				com.zillion.qa.opsadmin.Classes.accept1(driver);
				break;
			case "JsPagedown":
				com.zillion.qa.member.Library.pagedown1(driver);
				break;
			case "VerifyCoachMail1on1Session":
				if (inputData != null && referenceStep == null) {
					com.zillion.qa.realappealcoach.coach
					.verifyCoachMail1on1Session(driver, inputData);
				} else if (inputData == null && referenceStep != null
						&& !referenceStep.trim().equals("")) {
					int mailidref = new Integer(referenceStep);
					String mailidtext = getTextMap.get(Integer.valueOf(mailidref));
					com.zillion.qa.realappealcoach.coach
					.verifyCoachMail1on1Session(driver, mailidtext);
				}
				break;
			case "VerifyMemberMail1on1Session":
				int mailidref = new Integer(referenceStep);
				String mailidtext = getTextMap.get(Integer.valueOf(mailidref));
				com.zillion.qa.realappealmember.member.verifyMemberMail1on1Session(
						driver, mailidtext);
				break;
			case "DeleteYopMails":
				com.zillion.qa.realappealmember.member.deleteYopMails(driver);
				break;
			case "VerifyCoachMail1on1SessionCancellation":
				if (inputData != null && referenceStep == null) {
					com.zillion.qa.realappealcoach.coach
					.verifyCoachMail1on1SessionCancellation(driver, inputData);
				} else if (inputData == null && referenceStep != null
						&& !referenceStep.trim().equals("")) {
					int mailidref1 = new Integer(referenceStep);
					String mailidtext1 = getTextMap
							.get(Integer.valueOf(mailidref1));
					com.zillion.qa.realappealcoach.coach
					.verifyCoachMail1on1SessionCancellation(driver,
							mailidtext1);
				}
				break;
			case "VerifyMemberMail1on1SessionCancellation":
				int mailidref1 = new Integer(referenceStep);
				String mailidtext1 = getTextMap.get(Integer.valueOf(mailidref1));
				com.zillion.qa.realappealmember.member
				.verifyMemberMail1on1SessionCancellation(driver,
						mailidtext1);
				break;
			case "VerifyCoachMailEnrollment":
				int mailref2 = new Integer(referenceStep);
				String mailinput2 = getTextMap.get(Integer.valueOf(mailref2));
				com.zillion.qa.realappealcoach.coach.verifyCoachMailEnrollment(
						driver, mailinput2);
				break;
			case "VerifyConfirmMessage1on1Session":
				com.zillion.qa.realappealmember.member
				.verifyConfirmMessage1on1Session(driver);
				break;
			case "RAmemberEnterPassword":
				com.zillion.qa.realappealprogramadmin.programadmin
				.rAmemberEnterPassword(driver);
				break;
			case "RealAppealMemberDatesession":
				returnObj = com.zillion.qa.realappealprogramadmin.programadmin
				.realAppealMemberDatesession(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RealAppealMemberDatesession1":
				returnObj = com.zillion.qa.realappealprogramadmin.programadmin
				.realAppealMemberDatesession1(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RealAppealCoachEnterPassword":
				com.zillion.qa.realappealprogramadmin.programadmin
				.rACoachEnterPassword(driver);
				break;
			case "RealAppealcoachUnavailablitycheckbox":
				com.zillion.qa.realappealprogramadmin.programadmin
				.realAppealcoachUnavailabilitycheckbox(driver);
				break;
			case "RealAppealMemberChangeUnavailablityVerified":
				com.zillion.qa.realappealprogramadmin.programadmin
				.realAppealMemberChangeUnavailability(driver);
				break;
			case "DateCoachUnavailabeTimeSlot":
				com.zillion.qa.realappealprogramadmin.programadmin
				.currentDateCoachUnavailableTimeSlot(driver);
				break;
			case "RAOneOnOneliveSession":
				com.zillion.qa.realappealmember.RAOneOnOneLiveSession
				.raOneOnOneliveSession(driver, inputData);
				break;
			case "RetrieveAvailableScheduleMember":
				if (inputData != null) {
					returnObj = com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
							.retrieveAvailableScheduleMember(driver, inputData);
					if (returnObj.toString().equals("")) {
						returnObj = "No Data in the Table";
						getTextMap.put(stepNo, returnObj.toString());
						driver.close();
					} else {
						getTextMap.put(stepNo, returnObj.toString());
					}
				}
				break;
			case "RealAppealOneOnOneLiveSessionMemberLogin":
				if (inputData == null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					int assignedmemberref = new Integer(referenceStep);
					String assignedmemberref1 = getTextMap.get(Integer
							.valueOf(assignedmemberref));

					returnObj = com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
							.realAppealOneOnOneLiveSessionMemberLogin(driver,
									assignedmemberref1);
					getTextMap.put(stepNo, returnObj.toString());
				} else if (inputData != null && referenceStep != null
						&& !referenceStep.trim().equals("")) {
					returnObj = com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
							.realAppealOneOnOneLiveSessionMemberLogin(driver,
									inputData);
					getTextMap.put(stepNo, returnObj.toString());
				}
				break;
			case "RaMemberOneOnOneSessionAppendURL":
				com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
				.raMemberOneOnOneSessionAppendURL(driver);
				break;
			case "PluginAlertAccept":
				com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
				.pluginAlertAccept(driver);
				break;
			case "RealAppealMemberGearSettings":
				com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.realAppealmemberGearSettings( driver );
				break;
			case "SwitchToTab":
				int inputwindow = Integer.parseInt(inputData);
				Navigate.switchtotab(driver, inputwindow);
				break;
			case "RaOneOnOneLiveSessionCoachLogin":
				if (inputData == null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					int coachemailref1 = new Integer(referenceStep);
					String coachemailref2 = getTextMap.get(Integer
							.valueOf(coachemailref1));
					com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
					.raOneOnOneLiveSessionCoachLogin(driver, coachemailref2);
				} else if (inputData != null && referenceStep != null
						&& !referenceStep.trim().equals("")) {
					com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
					.raOneOnOneLiveSessionCoachLogin(driver, inputData);
				}
				break;
			case "RaCoachOneOnOneSessionAppendURL":
				com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
				.raCoachOneOnOneSessionAppendURL(driver);
				break;
			case "CoachGearSettings":
				com.zillion.qa.member.liveSessionSubCommonMethods
				.coachGearSettings(driver);
				break;
			case "PageUp":
				Navigate.pageUp(driver);
				break;
			case "CoachRAOneOnOneSessionMicEnableAndDisable":
				com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
				.coachRAOneOnOneSessionMicEnableAndDisable(driver,
						inputData);
				break;
			case "CoachRAOneOnOneSessionVideoEnableAndDisable":
				com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
				.coachRAOneOnOneSessionVideoEnableAndDisable(driver,
						inputData);
				break;
			case "GetSessionID":
				returnObj = com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
				.getCurrentURL(driver);
				break;
			case "MemberRAOneonOneSessionMicEnableAndDisable":
				returnObj = com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
				.memberRAOneonOneSessionMicEnableAndDisable(driver,
						inputData);
				break;
			case "MemberRAOneonOneSessionVideoEnableAndDisable":
				returnObj = com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
				.memberRAOneonOneSessionVideoEnableAndDisable(driver,
						inputData);
				break;
			case "CoachRAOneonOneSessionVerifyMemberMic":
				returnObj = com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
				.coachRAOneonOneSessionVerifyMemberMic(driver, inputData);
				break;
			case "CoachRAOneonOneSessionVerifyMemberVideo":
				returnObj = com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
				.coachRAOneonOneSessionVerifyMemberVideo(driver, inputData);
				break;
			case "Retrive1on1SessionAttendanceStatusWithSessionID":
				int memberemailref1 = new Integer(referenceStep);
				String memberemailref2 = getTextMap.get(Integer
						.valueOf(memberemailref1));
				com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
				.retrive1on1SessionAttendanceStatusWithSessionID(driver,
						memberemailref2);
				break;
			case "RAOneOnOneRetrieveMemberAccountId":
				int memberemailref3 = new Integer(referenceStep);
				String memberemailref4 = getTextMap.get(Integer
						.valueOf(memberemailref3));
				returnObj = com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
						.retrieveMemberAccountId(driver, memberemailref4);
				break;
			case "RatingNotesMemberSide":
				com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
				.ratingNotesMemberSide(driver, inputData);
				break;
			case "BrowserIconToolbar":
				com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
				.browserIconToolbar(driver);
				break;
			case "VerifyCoachTop5UpcomingSessions":
				com.zillion.qa.realappealcoach.coach
				.verifyCoachTop5UpcomingSessions(driver);
				break;
			case "VerifyCoachPastUpcomingSessionsAreNotAvailable":
				com.zillion.qa.realappealcoach.coach
				.verifyCoachPastUpcomingSessionsAreNotAvailable(driver);
				break;
			case "RetrieveMembersForCustomizationSession":
				String[] array = inputData.split(",");
				returnObj = com.zillion.qa.realappealmember.member
						.retrieveMembersForCustomizationSession(driver, array[0],
								array[1]);
				if (returnObj.toString().equals(",")) {
					returnObj = "No Data in the Table";
					getTextMap.put(stepNo, returnObj.toString());
					driver.close();
				} else {
					getTextMap.put(stepNo, returnObj.toString());
				}
				break;
			case "UpdateQRTZTableForClaims":
				if (referenceStep != null) {
					int claims = new Integer(referenceStep);
					String claims1 = getTextMap.get(Integer.valueOf(claims));
					returnObj = com.zillion.qa.realappealmember.member
							.updateQRTZTableForClaims(driver, claims1);
					getTextMap.put(stepNo, returnObj.toString());
				}
				break;
			case "ValidateRollupAndSessionStatusForClaims":
				returnObj = com.zillion.qa.realappealmember.member
				.validateRollupAndSessionStatusForClaims(driver);
				System.out.println("returnObj" + returnObj);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "ValidateClaimsAchievements":
				returnObj = com.zillion.qa.realappealmember.member
				.validateClaimsAchievements(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "InsufficientScheduledStatusVerify":
				com.zillion.qa.realappealprogramadmin.programadmin
				.insufficientScheduledStatusVerify(driver);
				break;
			case "VerifyingcoachupcomingsessionViewAndAttendnowButton":
				returnObj = com.zillion.qa.realappealcoach.coach
				.verifyingcoachupcomingsessionViewAndAttendnowButton(driver);
				break;
			case "VerifyingcoachupcomingsessionsareAscendingOrders":
				returnObj = com.zillion.qa.realappealcoach.coach
				.verifyingcoachupcomingsessionsareAscendingOrders(driver);
				break;
			case "UpcomingSessionAlertaccept":
				com.zillion.qa.realappealcoach.coach
				.upcomingSessionAlertaccept(driver);
				break;
			case "CustomizationDateAndTimeVerify":
				returnObj = com.zillion.qa.realappealmember.member
				.customizationDateAndTimeVerify(driver, inputData);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "CustomizationDateAndTimeVerifyAfterSchedule":
				returnObj = com.zillion.qa.realappealmember.member
				.customizationDateAndTimeVerifyAfterSchedule(driver,
						inputData);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "InsufficientHBMIScheduledStatusVerify":
				com.zillion.qa.realappealprogramadmin.programadmin
				.insufficientHBMIScheduledStatusVerify(driver);
				break;
			case "GetOrberaMemberFor1on1Session":
				returnObj = com.zillion.qa.member.Dashboard
				.getOrberaMemberFor1on1Session(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "OrberaMemberLoginUsingDB":
				int membermail2 = new Integer(referenceStep);
				String membermail11 = getTextMap.get(Integer.valueOf(membermail2));
				returnObj = com.zillion.qa.member.Dashboard
						.orberaMemberLoginUsingDB(driver, membermail11);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "UploadPhotos2":
				com.zillion.qa.member.Dashboard.uploadPhotos2(driver, inputData);
				break;
			case "RetrieveAvailableGroupSessionScheduleMember":
				if (inputData != null) {
					returnObj = com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
							.retrieveAvailableGroupSessionScheduleMember(driver,
									inputData);
					if (returnObj.toString().equals("")) {
						returnObj = "No Data in the Table";
						getTextMap.put(stepNo, returnObj.toString());
						driver.close();
					} else {
						getTextMap.put(stepNo, returnObj.toString());
					}
				}
				break;
			case "RAGroupliveSession":
				com.zillion.qa.realappealmember.RAGroupLiveSession
				.raGroupliveSession(driver, inputData);
				break;
			case "RealAppealMemberlogout":
				com.zillion.qa.realappealmember.member
				.realAppealMemberlogout(driver);
				break;
			case "UnzipDir":
				com.zillion.qa.realappealmember.Claims.unzipDir(driver);
				break;
			case "GetMemberFromApiAutomation":
				returnObj = com.zillion.qa.realappealmember.Claims
				.getMemberFromApiAutomation(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "DeletePreviousAPIAutomation":
				com.zillion.qa.realappealmember.Claims
				.deletePreviousAPIAutomation(driver);
				break;
			case "RetrieveNextFireTimeQRTZTable":
				returnObj = com.zillion.qa.realappealmember.member
				.retrieveNextFireTimeQRTZTable(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "AddNumberWithGetAttribute":
				int addref = new Integer(referenceStep);
				String addget = getTextMap.get(Integer.valueOf(addref));
				System.out.print("addget" + addget);
				System.out.print("inputData" + inputData);
				returnObj = Navigate.addNumberWithGetAttribute(driver, addget,
						inputData);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "InsufficientTabSelectClassDatePreviousMonthCurrentDate":
				com.zillion.qa.realappealprogramadmin.programadmin
				.insufficientTabSelectClassDatePreviousMonthCurrentDate(driver);
				break;
			case "RetreiveMemberWithSessionStatusRollupStatus":
				String[] inputs1 = inputData.split(",");
				returnObj = com.zillion.qa.realappealmember.Claims
						.retreiveMemberWithSessionStatusRollupStatus(driver,
								inputs1[0], inputs1[1], inputs1[2]);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RetrieveEnableProviderHoursIsOne":
				returnObj = com.zillion.qa.realappealprogramadmin.programadmin
				.retrieveEnableProviderHoursIsOne(driver, inputData);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "GroupsessionApprovalcheckbox":
				com.zillion.qa.opsadmin.Classes
				.groupsessionApprovalcheckbox(driver);
				break;
			case "FridayBluearea1":
				com.zillion.qa.member.liveSessionSubCommonMethods
				.fridayBluearea1(driver);
				break;
			case "FridayUnavailablearea":
				com.zillion.qa.member.liveSessionSubCommonMethods
				.fridayUnavailablearea(driver);
				break;
			case "DownloadFiles":
				com.zillion.qa.realappealmember.Claims.downloadFiles(driver);
				break;
			case "RetrieveSessionStatusUsingAcntId":
				int addref11 = new Integer(referenceStep);
				String acntid1 = getTextMap.get(Integer.valueOf(addref11));
				returnObj = com.zillion.qa.realappealmember.member
						.retrieveSessionStatusUsingAcntId(driver, acntid1,
								inputData);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RetrieveAccountIDWithMemberEmailID":
				int accountIdRef11 = new Integer(referenceStep);
				String accountIdRef12 = getTextMap.get(Integer
						.valueOf(accountIdRef11));
				returnObj = com.zillion.qa.realappealprogramadmin.programadmin
						.retrieveAccountIDWithMemberEmailID(driver, accountIdRef12);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RetrieveClassRoomIDWithMemberAccountID":
				int classRoomIDref11 = new Integer(referenceStep);
				String classRoomIDref12 = getTextMap.get(Integer
						.valueOf(classRoomIDref11));
				returnObj = com.zillion.qa.realappealprogramadmin.programadmin
						.retrieveClassRoomIDWithMemberAccountID(driver,
								classRoomIDref12);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RetrieveIsApprovedAndApprovedWithMemberClassRoomID":
				int approverdReff11 = new Integer(referenceStep);
				String approverdReff12 = getTextMap.get(Integer
						.valueOf(approverdReff11));
				returnObj = com.zillion.qa.realappealprogramadmin.programadmin
						.retrieveIsApprovedAndApprovedWithMemberClassRoomID(driver,
								approverdReff12);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RealAppealRetrieveMemberAccountId":
				int accountIDReff1134 = new Integer(referenceStep);
				String accountIDReff1234 = getTextMap.get(Integer
						.valueOf(accountIDReff1134));
				returnObj = com.zillion.qa.realappealcoach.coach
						.realAppealRetrieveMemberAccountId(driver,
								accountIDReff1234);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RealAppealRetrieveAccountProgramID":
				int programIDReff11345 = new Integer(referenceStep);
				String programIDReff12345 = getTextMap.get(Integer
						.valueOf(programIDReff11345));
				returnObj = com.zillion.qa.realappealcoach.coach
						.realAppealRetrieveAccountProgramID(driver,
								programIDReff12345);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RealAppealRetrieve1on1SessionHostNameWithProgramID":
				int hostIDReff11345 = new Integer(referenceStep);
				String hostIDReff12345 = getTextMap.get(Integer
						.valueOf(hostIDReff11345));
				returnObj = com.zillion.qa.realappealcoach.coach
						.realAppealRetrieve1on1SessionHostNameWithProgramID(driver,
								hostIDReff12345);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RealAppealRetrive1on1SessionEmailWithCoachHostId":
				int hostEmailReff11345 = new Integer(referenceStep);
				String hostEmailReff12345 = getTextMap.get(Integer
						.valueOf(hostEmailReff11345));
				returnObj = com.zillion.qa.realappealcoach.coach
						.realAppealRetrive1on1SessionEmailWithCoachHostId(driver,
								hostEmailReff12345);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "AllowPluginsUsingSikuli":
				com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
				.allowPlugins(driver);
				break;
			case "GetMemberId":
				com.zillion.qa.realappealprogramadmin.programadmin.getMemberId(
						driver, inputData);
				break;
			case "RetrieveMailIDFromAccountID":
				int mail = new Integer(referenceStep);
				String mailID = getTextMap.get(Integer.valueOf(mail));
				returnObj = com.zillion.qa.realappealprogramadmin.programadmin
						.retrieveMailIDFromAccountID(driver, mailID);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RetrieveMemberOrCoachEmailForCustomSession":
				if (inputData != null) {
					System.out.println("Pass");
					returnObj = com.zillion.qa.realappealprogramadmin.programadmin
							.retrieveMemberOrCoachEmailForCustomSession(driver,
									inputData);
					if (returnObj.toString().equals(",")) {
						returnObj = "No Data in the Table";
						getTextMap.put(stepNo, returnObj.toString());
						driver.close();
					} else {
						getTextMap.put(stepNo, returnObj.toString());
					}
				}
				break;
			case "CoachLoginFromRetrievedAssignedCoachFromRefStep":
				if (inputData == null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					int assignedcoachref = new Integer(referenceStep);
					String assignedcoachref1 = getTextMap.get(Integer
							.valueOf(assignedcoachref));
					com.zillion.qa.realappealcoach.coach
					.coachLoginFromRetrievedAssignedCoachFromRefStep(
							driver, assignedcoachref1);
				} else if (inputData != null && referenceStep != null
						&& !referenceStep.trim().equals("")) {
					com.zillion.qa.realappealcoach.coach
					.coachLoginFromRetrievedAssignedCoachFromRefStep(
							driver, inputData);
				}
				break;
			case "RAEnrollmentInsuranceDetails":
				com.zillion.qa.realappealmember.member
				.realAppealInsuranceDetails(driver);
				break;
			case "RetrieveMemberMailForCustomSession":
				if ((inputData != null)) {
					System.out.println("Pass");
					returnObj = com.zillion.qa.realappealprogramadmin.programadmin
							.retrieveMemberMailForCustomSession(driver, inputData);
					if (returnObj.toString().equals(",")) {
						returnObj = "No Data in the Table";
						getTextMap.put(stepNo, returnObj.toString());
						driver.close();
					} else {
						getTextMap.put(stepNo, returnObj.toString());
					}
				}
				break;
			case "RetrievegroupsessionAvailableCoachEmailID":
				returnObj = com.zillion.qa.realappealcoach.coach
				.retrievegroupsessionAvailableCoachEmailID(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "SplitWeightAndLbs":
				int weight1 = new Integer(referenceStep);
				String Splitweight = getTextMap.get(Integer.valueOf(weight1));
				returnObj = com.zillion.qa.member.Tracker.splitWeightAndLbs(driver,
						Splitweight);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "EnterRAFirstName":
				com.zillion.qa.realappealmember.member.enterRAFirstName(driver);
				returnObj = com.zillion.qa.realappealmember.member.enterRAFirstName(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "EnterRALastName":
				com.zillion.qa.realappealmember.member.enterRALastName(driver);
				returnObj =com.zillion.qa.realappealmember.member.enterRALastName(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "EnterRADOBDay":
				com.zillion.qa.realappealmember.member.enterRADOBDay(driver);
				break;
			case "EnterRADOBMonth":
				com.zillion.qa.realappealmember.member.enterRADOBMonth(driver);
				break;
			case "EnterRADOBYear":
				com.zillion.qa.realappealmember.member.enterRADOBYear(driver);
				break;
			case "EnterRAFirstNameOnInsurancePopUp":
				returnObj = com.zillion.qa.realappealmember.member
				.enterRAFirstNameOnInsurancePopUp(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "EnterRALastNameOnInsurancePopUp":
				returnObj = com.zillion.qa.realappealmember.member
				.enterRALastNameOnInsurancePopUp(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "StoreMemberDuringEnrollment":
				int member = new Integer(referenceStep);
				String memberdata = getTextMap.get(Integer.valueOf(member));
				com.zillion.qa.realappealmember.member.storeMemberDuringEnrollment(
						driver, memberdata);
				break;
			case "SplitUsingColon":
				int text = new Integer(referenceStep);
				String text1 = getTextMap.get(Integer.valueOf(text));
				returnObj = com.zillion.qa.realappealmember.member.splitUsingColon(
						driver, text1);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "EnterRAMemberUsername":
				returnObj = com.zillion.qa.realappealmember.member
				.enterRAMemberUsername(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "JenkinDbTimeslotForClass":
				returnObj = com.zillion.qa.member.Dashboard
				.jenkinDbTimeslotForClass(driver, inputData);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "GetOrberaMember1on1SessionFromProv":
				returnObj = com.zillion.qa.opsadmin.Members
				.getOrberaMember1on1SessionFromProv(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "GetOrberaMember1on1SessionFromMember":
				returnObj = com.zillion.qa.member.liveSessionSubCommonMethods
				.getOrberaMember1on1SessionFromMember(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "GetMemberIdForClaims":
				returnObj = com.zillion.qa.realappealmember.member
				.getMemberIdForClaims(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "ViewArticle":
				com.zillion.qa.member.Library.viewArticle(driver);
				break;
			case "VerifyclientnameAfterSearch":
				int client1 = new Integer(referenceStep);
				String clientname = getTextMap.get(Integer.valueOf(client1));
				com.zillion.qa.programadmin.Clients.verifyclientnameAfterSearch(
						driver, clientname);
				break;
			case "HFOPSAddNewMember":
				com.zillion.qa.practitioner.Patients.hfopsAddNewMember(driver,
						inputData);
				break;
			case "RetrieveGenderOfTheMember":
				int member6 = new Integer(referenceStep);
				String member7 = getTextMap.get(Integer.valueOf(member6));
				returnObj = com.zillion.qa.realappealmember.Claims
						.retrieveGenderOfTheMember(driver, member7);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "ReadMemberForGroup1Claims":
				returnObj = com.zillion.qa.realappealmember.Claims
				.readMemberForGroup1Claims(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "CheckSessionStatusFor1on1Session":
				int session_status = new Integer(referenceStep);
				String session_status1 = getTextMap.get(Integer
						.valueOf(session_status));
				returnObj = com.zillion.qa.realappealmember.Claims
						.checkSessionStatusFor1on1Session(driver, session_status1);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "CheckSessionStatusForGroup":
				int session_status3 = new Integer(referenceStep);
				String session_status4 = getTextMap.get(Integer
						.valueOf(session_status3));
				returnObj = com.zillion.qa.realappealmember.Claims
						.checkSessionStatusFor1on1Session(driver, session_status4);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RetrieveMemberID":
				String[] input = inputData.split(",");
				returnObj = com.zillion.qa.realappealmember.Claims
						.retrieveMemberID(driver, input[0], input[1], input[2]);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RetrieveCoachIdandLogin":
				com.zillion.qa.realappealmember.Claims
				.retrieveCoachIdandLogin(driver);
				break;
			case "RetrieveCoachID":
				returnObj = com.zillion.qa.realappealmember.Claims.retrieveCoachID(
						driver, inputData);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "AppendURL":
				returnObj = com.zillion.qa.realappealmember.Claims.AppendURL(
						driver, inputData);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RAMemberSessionTime":
				returnObj = com.zillion.qa.realappealmember.Claims
				.raMemberSessionTime(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RetrieveProgramInterval":
				if (inputData == null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					int Mid = new Integer(referenceStep);
					String ID = getTextMap.get(Integer.valueOf(Mid));
					returnObj = com.zillion.qa.realappealmember.Claims
							.retrieveprograminterval(driver, ID);
					getTextMap.put(stepNo, returnObj.toString());
				}
				break;
			case "MemberWeightLoss":

				if (inputData == null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					int Interval = new Integer(referenceStep);
					String Program_interval = getTextMap.get(Integer
							.valueOf(Interval));
					returnObj = com.zillion.qa.realappealmember.Claims.Weightloss(
							driver, Program_interval);
					getTextMap.put(stepNo, returnObj.toString());
				} else {
					returnObj = com.zillion.qa.realappealmember.Claims.Weightloss(
							driver, inputData);
					getTextMap.put(stepNo, returnObj.toString());
				}
				break;
			case "VerifyAddCalenderForSchedule":
				com.zillion.qa.member.Dashboard.verifyAddCalenderForSchedule(
						driver, inputData);
				break;
			case "DashboardCaloriesComparision":
				com.zillion.qa.realappealmember.member
				.dashboardCaloriesComparision(driver);
				break;
			case "DBRealAppealMember":
				returnObj = com.zillion.qa.realappealmember.member
				.dBRealAppealMember(driver, inputData);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "GroupAnd1on1Sendemail":
				String[] splitinput = inputData.split(",");
				com.zillion.qa.realappealmember.Claims.groupAnd1on1Sendemail(
						splitinput[0], splitinput[1], splitinput[2], splitinput[3],
						splitinput[4], splitinput[5], splitinput[6], splitinput[7]);
				break;
			case "GetMemberForGrpSessionMissed":
				returnObj = com.zillion.qa.realappealmember.Claims
				.getMemberForGrpSessionMissed(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "CreatePropertyforGroupSession3":
				com.zillion.qa.realappealmember.Claims
				.createpropertyforgroupsession3();
				break;
			case "RetrieveMemberIDGroupSession2":
				returnObj = com.zillion.qa.realappealmember.Claims
				.retrieveMemberIDGroupSession2(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "CreatePropertyforGroupSession2":
				com.zillion.qa.realappealmember.Claims
				.createpropertyforgroupsession2();
				break;
			case "RetrieveCoachIDGroupSession2":
				returnObj = com.zillion.qa.realappealmember.Claims
				.retrieveCoachIDGroupSession2(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "ValidateSummaryForGrp1Claims":
				String[] splitInputdata1 = inputData.split(",");
				returnObj = com.zillion.qa.realappealmember.Claims
						.validateSummaryForGrp1Claims(driver, splitInputdata1[0],
								splitInputdata1[1], splitInputdata1[2],
								splitInputdata1[3], splitInputdata1[4]);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "ValidateAchievementsGrp1Claims":
				returnObj = com.zillion.qa.realappealmember.Claims
				.validateAchievementsGrp1Claims(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "CancelLectureSession":
				com.zillion.qa.opsadmin.Classes.cancelLectureSession(driver,
						inputData);
				break;
			case "VerifyLectureSession":
				com.zillion.qa.opsadmin.Classes.verifyLectureSession(driver,
						inputData);
				break;
			case "VerifyOrberaCoachLectureSession":
				if (inputData != null && referenceStep == null) {
					com.zillion.qa.coaches.Classes
					.verifyorberaCoachMailLectureSession(driver, inputData);
				} else if (inputData == null && referenceStep != null
						&& !referenceStep.trim().equals("")) {
					int mailidreforbera1 = new Integer(referenceStep);
					String mailidtextorbera1 = getTextMap.get(Integer
							.valueOf(mailidreforbera1));
					com.zillion.qa.coaches.Classes
					.verifyorberaCoachMailLectureSession(driver,
							mailidtextorbera1);
				}
				break;
			case "VerifyOrberaSubstituteCoachEmailConfirmation":
				if (inputData != null) {
					com.zillion.qa.coaches.Classes
					.verifyorberaSubstituteCoachEMailConfirmation(driver,
							inputData);
				} else if (referenceStep != null) {
					int mailidreforbera1 = new Integer(referenceStep);
					String mailidtextorbera1 = getTextMap.get(Integer
							.valueOf(mailidreforbera1));
					com.zillion.qa.coaches.Classes
					.verifyorberaSubstituteCoachEMailConfirmation(driver,
							mailidtextorbera1);
				}
				break;
			case "VerifyMemberMail1on1Session2":
				if (referenceStep != null) {
					int mailidref2 = new Integer(referenceStep);
					String mailidtext2 = getTextMap
							.get(Integer.valueOf(mailidref2));
					com.zillion.qa.realappealmember.member
					.verifyMemberMail1on1Session2(driver, mailidtext2);
				} else if (inputData != null) {
					com.zillion.qa.realappealmember.member
					.verifyMemberMail1on1Session2(driver, inputData);
				}
				break;
			case "StoreMemberDuringEnrollmentMemberDidNotQualify":
				int memberDidNotQualify = new Integer(referenceStep);
				String memberDidNotQualifyData = getTextMap.get(Integer
						.valueOf(memberDidNotQualify));
				com.zillion.qa.realappealmember.member
				.storeMemberDuringEnrollmentMemberDidNotQualify(driver,
						memberDidNotQualifyData);
				break;
			case "EnterRAMemberUsernameMemberDidNotQualify":
				returnObj = com.zillion.qa.realappealmember.member
				.enterRAMemberUsernameMemberDidNotQuaify(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RobotRefreshPage":
				Navigate.robotRefreshPage(driver);
				break;
			case "storeMemberDuringEnrollmentBMIOutOfRange":
				int RAmemberMemberDidNotQualify = new Integer(referenceStep);
				String RAmemberMemberDidNotQualifyText = getTextMap.get(Integer
						.valueOf(RAmemberMemberDidNotQualify));
				com.zillion.qa.realappealmember.member
				.storeMemberDuringEnrollmentBMIOutOfRange(driver,
						RAmemberMemberDidNotQualifyText);
				break;
			case "EnterRAMemberUsernameBMIOutOfRange":
				returnObj = com.zillion.qa.realappealmember.member
				.enterRAMemberUsernameBMIOutOfRange(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "GetMemberIdForClaimsBMIOutOfRange":
				returnObj = com.zillion.qa.realappealmember.member
				.getMemberIdForClaimsBMIOutOfRange(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "ValidateClaimsAchievementsBMIOutOfRange":
				returnObj = com.zillion.qa.realappealmember.member
				.validateClaimsAchievementsBMIOutOfRange(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "ValidateRollupAndSessionStatusForClaimsBMIOutOfRange":
				returnObj = com.zillion.qa.realappealmember.member
				.validateRollupAndSessionStatusForClaimsBMIOutOfRange(driver);
				System.out.println("returnObj" + returnObj);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "VerifyOrberaMemberMail1on1SessionCancellation":
				if (referenceStep != null) {
					int cancelmail = new Integer(referenceStep);
					String cancelmailtext = getTextMap.get(Integer
							.valueOf(cancelmail));
					com.zillion.qa.realappealmember.member
					.verifyOrberaMemberMail1on1SessionCancellation(driver,
							cancelmailtext);
				} else if (inputData != null) {
					com.zillion.qa.realappealmember.member
					.verifyOrberaMemberMail1on1SessionCancellation(driver,
							inputData);
				}
				break;
			case "GetMemberIdForClaimsDidNotQualify":
				returnObj = com.zillion.qa.realappealmember.member
				.getMemberIdForClaimsDidNotQualify(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "ValidateRollupAndSessionStatusForClaimsDidNotQualify":
				returnObj = com.zillion.qa.realappealmember.member
				.validateRollupAndSessionStatusForClaimsDidNotQualify(driver);
				System.out.println("returnObj" + returnObj);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "ValidateClaimsAchievementsDidNotQualify":
				returnObj = com.zillion.qa.realappealmember.member
				.validateClaimsAchievementsDidNotQualify(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "ScrollToParticularElement":
				Navigate.scrollToParticularElement(driver, normalXpath);
				break;
			case "HandleRAConnectivityIssue":
				com.zillion.qa.realappealmember.Claims
				.handleRAConnectivityIssue(driver);
				break;
			case "StoreMemberDuringEnrollmentMemberOpsOutOfPgm":
				int memberOpsOutOfPgm = new Integer(referenceStep);
				String memberOpsOutOfPgmData = getTextMap.get(Integer
						.valueOf(memberOpsOutOfPgm));
				com.zillion.qa.realappealmember.member
				.storeMemberDuringEnrollmentMemberOpsOutOfPgm(driver,
						memberOpsOutOfPgmData);
				break;
			case "EnterRAMemberUsernameMemberOpsOutOfPgm":
				returnObj = com.zillion.qa.realappealmember.member
				.enterRAMemberUsernameMemberOpsOutOfPgm(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "GetMemberIdForClaimsOptOutOfPgm":
				returnObj = com.zillion.qa.realappealmember.member
				.getMemberIdForClaimsOptOutOfPgm(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "ValidateRollupAndSessionStatusForClaimsOptOutOfPgm":
				returnObj = com.zillion.qa.realappealmember.member
				.validateRollupAndSessionStatusForClaimsOptOutOfPgm(driver);
				System.out.println("returnObj" + returnObj);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "ValidateClaimsAchievementsOptOutOfPgm":
				returnObj = com.zillion.qa.realappealmember.member
				.validateClaimsAchievementsOptOutOfPgm(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RetrieveMemberHasNIs1":
				returnObj = com.zillion.qa.realappealmember.Claims
				.retrieveMemberHasNIs1(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "CreatePropertyforGroupSession1":
				com.zillion.qa.realappealmember.Claims
				.createpropertyforgroupsession1();
				break;
			case "CreatePropertyforMemberHasNIs1":
				com.zillion.qa.realappealmember.Claims
				.createPropertyforMemberHasNIs1();
				break;
			case "RetrieveMemberWithoutWeightLoss":
				returnObj = com.zillion.qa.realappealmember.Claims
				.retrieveMemberWithoutWeightLoss(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "CreatePropertyforMemberWithoutWeightLoss":
				com.zillion.qa.realappealmember.Claims
				.createPropertyforMemberWithoutWeightLoss();
				break;
			case "GetOrberaMemberMissed1on1SessionFromMember":
				returnObj = com.zillion.qa.member.liveSessionSubCommonMethods
				.getOrberaMemberMissed1on1SessionFromMember(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "VerifyMemberMailMissedSession":
				if (inputData != null && referenceStep == null) {
					com.zillion.qa.realappealmember.member
					.verifyMemberMailMissedSession(driver, inputData);
				} else if (inputData == null && referenceStep != null
						&& !referenceStep.trim().equals("")) {
					int mailidref2 = new Integer(referenceStep);
					String mailidtext2 = getTextMap
							.get(Integer.valueOf(mailidref2));
					com.zillion.qa.realappealmember.member
					.verifyMemberMailMissedSession(driver, mailidtext2);
				}
				break;
			case "GetOrberaMemberMissedLectureSessionFromMember":
				returnObj = com.zillion.qa.member.liveSessionSubCommonMethods
				.getOrberaMemberMissedLectureSessionFromMember(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "GetOrberaMemberReminderLectureSessionFromMember":
				returnObj = com.zillion.qa.member.liveSessionSubCommonMethods
				.getOrberaMemberReminderLectureSessionFromMember(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "VerifyMemberMailMissedLectureSession":
				if (inputData != null && referenceStep == null) {
					com.zillion.qa.realappealmember.member
					.verifyMemberMailMissedLectureSession(driver, inputData);
				} else if (inputData == null && referenceStep != null
						&& !referenceStep.trim().equals("")) {
					int mailidref2 = new Integer(referenceStep);
					String mailidtext2 = getTextMap
							.get(Integer.valueOf(mailidref2));
					com.zillion.qa.realappealmember.member
					.verifyMemberMailMissedLectureSession(driver,
							mailidtext2);
				}
				break;
			case "VerifyMemberMailReminderLectureSession":
				if (inputData != null && referenceStep == null) {
					com.zillion.qa.realappealmember.member
					.verifyMemberMailReminderLectureSession(driver,
							inputData);
				} else if (inputData == null && referenceStep != null
						&& !referenceStep.trim().equals("")) {
					int mailidref2 = new Integer(referenceStep);
					String mailidtext2 = getTextMap
							.get(Integer.valueOf(mailidref2));
					com.zillion.qa.realappealmember.member
					.verifyMemberMailReminderLectureSession(driver,
							mailidtext2);
				}
				break;
			case "StoreMemberOnEnrollmentBeFitPositive":
				int memberbefit = new Integer(referenceStep);
				String memberbefitdata = getTextMap.get(Integer
						.valueOf(memberbefit));
				com.zillion.qa.realappealmember.member
				.storeMemberOnEnrollmentBeFitPositive(driver,
						memberbefitdata);
				break;
			case "EnterRAMemberUsernameBeFitPostive":
				returnObj = com.zillion.qa.realappealmember.member
				.enterRAMemberUsernameBeFitPostive(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "GetMemberIdForClaimsBeFitPositive":
				returnObj = com.zillion.qa.realappealmember.member
				.getMemberIdForClaimsBeFitPositive(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "ValidateRollupForClaimsBeFitPositive":
				returnObj = com.zillion.qa.realappealmember.member
				.validateRollupForClaimsBeFitPositive(driver);
				System.out.println("returnObj" + returnObj);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "ValidateClaimsAchievementsBeFitPositive":
				returnObj = com.zillion.qa.realappealmember.member
				.validateClaimsAchievementsBeFitPositive(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "StoreMemberOnEnrollmentBeFitNegative":
				int memberbefit1 = new Integer(referenceStep);
				String memberbefitdata1 = getTextMap.get(Integer
						.valueOf(memberbefit1));
				com.zillion.qa.realappealmember.member
				.storeMemberOnEnrollmentBeFitNegative(driver,
						memberbefitdata1);
				break;
			case "EnterRAMemberUsernameBeFitNegative":
				returnObj = com.zillion.qa.realappealmember.member
				.enterRAMemberUsernameBeFitNegative(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "GetMemberIdForClaimsBeFitNegative":
				returnObj = com.zillion.qa.realappealmember.member
				.getMemberIdForClaimsBeFitNegative(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "ValidateRollupAndSessionStatusForClaimsBeFitNegative":
				returnObj = com.zillion.qa.realappealmember.member
				.validateRollupAndSessionStatusForClaimsBeFitNegative(driver);
				System.out.println("returnObj" + returnObj);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "ValidateClaimsAchievementsBeFitNegative":
				returnObj = com.zillion.qa.realappealmember.member
				.validateClaimsAchievementsBeFitNegative(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "VerifyPHIDataForSpecificClient":
				com.zillion.qa.realappealmember.member
				.verifyPHIDataForSpecificClient(driver);
				break;

			case "RAEnrollmentClient2InsuranceDetails":
				com.zillion.qa.realappealmember.member
				.realAppealClient2InsuranceDetails(driver);
				break;
			case "Client2EnterRALastName":
				com.zillion.qa.realappealmember.member
				.client2EnterRALastName(driver);
				break;
			case "GetMemberForRescheduleCust":
				returnObj = com.zillion.qa.realappealmember.member
				.getMemberForRescheduleCust(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "StoreMemberClaimsRescheduleCust":
				int memberreschedule = new Integer(referenceStep);
				String memberreschedule1 = getTextMap.get(Integer
						.valueOf(memberreschedule));
				com.zillion.qa.realappealmember.member
				.storeMemberClaimsRescheduleCust(driver, memberreschedule1);
				break;
			case "GetMemberIdForClaimsRescheduleCust":
				returnObj = com.zillion.qa.realappealmember.member
				.getMemberIdForClaimsRescheduleCust(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "ValidateRollupForClaimsRescheduleCust":
				returnObj = com.zillion.qa.realappealmember.member
				.validateRollupForClaimsRescheduleCust(driver);
				System.out.println("returnObj" + returnObj);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "ValidateClaimsAchievementsRescheduleCust":
				returnObj = com.zillion.qa.realappealmember.member
				.validateClaimsAchievementsRescheduleCust(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "GetMemberForGrpSessionMissedNoOverlap":
				returnObj = com.zillion.qa.realappealmember.Claims
				.getMemberForGrpSessionMissedNoOverlap(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "CreatePropertyNoOverlap":
				com.zillion.qa.realappealmember.Claims.createPropertyNoOverlap();
				break;
			case "NextPageNavigatorCheckCompletedLecture":
				com.zillion.qa.realappealmember.member
				.nextpagenavigatorcheckcompletedlecture(driver);
				break;
			case "NextPageNavigatorCheckMissed":
				com.zillion.qa.realappealmember.member
				.nextpagenavigatorcheckmissed(driver);
				break;
			case "VerifyMemberMailReminder1on1Session":
				if (inputData != null && referenceStep == null) {
					com.zillion.qa.realappealmember.member
					.verifyMemberMailReminder1on1Session(driver, inputData);
				} else if (inputData == null && referenceStep != null
						&& !referenceStep.trim().equals("")) {
					int mailidref2 = new Integer(referenceStep);
					String mailidtext2 = getTextMap
							.get(Integer.valueOf(mailidref2));
					com.zillion.qa.realappealmember.member
					.verifyMemberMailReminder1on1Session(driver,
							mailidtext2);
				}
				break;
			case "Client2EnterRAFirstName":
				com.zillion.qa.realappealmember.member
				.client2EnterRAFirstName(driver);
				break;
			case "RetreieveEmailForDifferentOnboardingStatusandOrgIDClient1":
				returnObj = com.zillion.qa.realappealmember.member
				.retreieveEmailForDifferentOnboardingStatusandOrgIDClient1(
						driver, inputData);
				getTextMap.put(stepNo, returnObj.toString());
				break;

			case "Client1SettingsEnterRALastName":
				com.zillion.qa.realappealmember.member
				.realAppealClient1SettingsEnterRALastName(driver);
				break;
			case "Client1SettingsPrimaryInsuranceDetails":
				com.zillion.qa.realappealmember.member
				.realAppealClient1SettingsPrimaryInsuranceDetails(driver);
				break;
			case "Client1SettingsEnterRAFirstName":
				com.zillion.qa.realappealmember.member
				.realAppealClient1SettingsEnterRAFirstName(driver);
				break;
			case "Practitionerlogin":
				com.zillion.qa.practitioner.Patients.practitionerlogin(driver);
				break;
			case "GetMemberForGrpSessionBeFit":
				returnObj = com.zillion.qa.realappealmember.Claims
				.getMemberForGrpSessionBeFit(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "CreatePropertyForBefitByBoth":
				com.zillion.qa.realappealmember.Claims
				.createPropertyForBefitByBoth();
				break;
			case "CheckRollupRABefitByBothActive":
				returnObj = com.zillion.qa.realappealmember.Claims
				.checkRollupRABefitByBothActive(driver);
				getTextMap.put(stepNo, returnObj.toString());
				if (returnObj.equals("Row is retrieved for the negative scenario")) {
					Assert.fail();
				}
				break;
			case "CheckClaimsRABefitByBothActive":
				returnObj = com.zillion.qa.realappealmember.Claims
				.checkClaimsRABefitByBothActive(driver);
				getTextMap.put(stepNo, returnObj.toString());
				if (returnObj
						.equals("Claims is achieved for the negative scenario")) {
					Assert.fail();
				}
				break;
			case "MemberSignUpLectureSessionForCoachConfirmation":
				String[] datas = inputData.split(",");
				com.zillion.qa.member.Dashboard
				.memberSignUpLectureSessionForCoachConfirmation(driver,
						datas[0], datas[1]);
				break;
			case "VerifyMemberMailSignUpLectureSession":
				if (inputData != null && referenceStep == null) {
					com.zillion.qa.realappealmember.member
					.verifyMemberMailSignUpLectureSession(driver, inputData);
				} else if (inputData == null && referenceStep != null
						&& !referenceStep.trim().equals("")) {
					int mailidref2 = new Integer(referenceStep);
					String mailidtext2 = getTextMap
							.get(Integer.valueOf(mailidref2));
					com.zillion.qa.realappealmember.member
					.verifyMemberMailSignUpLectureSession(driver,
							mailidtext2);
				}
				break;
			case "VerifyOrberaCoachMail1on1Session":
				if (referenceStep != null) {
					int mailidref2 = new Integer(referenceStep);
					String mailidtext2 = getTextMap
							.get(Integer.valueOf(mailidref2));
					com.zillion.qa.coaches.Classes
					.verifyorberaCoachMail1on1Session(driver, mailidtext2);
				} else if (inputData != null) {
					com.zillion.qa.coaches.Classes
					.verifyorberaCoachMail1on1Session(driver, inputData);
				}
				break;
			case "VerifyOrberaCoachMail1on1SessionCancellation":
				if (referenceStep != null) {
					int mailidref2 = new Integer(referenceStep);
					String mailidtext2 = getTextMap
							.get(Integer.valueOf(mailidref2));
					com.zillion.qa.coaches.Classes
					.verifyorberaCoachMail1on1SessionCancellation(driver,
							mailidtext2);
				} else if (inputData != null) {
					com.zillion.qa.coaches.Classes
					.verifyorberaCoachMail1on1SessionCancellation(driver,
							inputData);
				}
				break;
			case "VerifyOrberaMemberMail1on1Session":
				if (referenceStep != null) {
					int mailidref2 = new Integer(referenceStep);
					String mailidtext2 = getTextMap
							.get(Integer.valueOf(mailidref2));
					com.zillion.qa.member.LiveSession
					.verifyOrberaMemberMail1on1Session(driver, mailidtext2);
				} else if (inputData != null) {
					com.zillion.qa.member.LiveSession
					.verifyOrberaMemberMail1on1Session(driver, inputData);
				}
				break;
			case "GetPractitionerIDFromQuery":
				returnObj = com.zillion.qa.practitioner.Patients
				.getPractitionerIDFromQuery(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "GetPractitionerMailIdFromPractitionerID":
				if (referenceStep != null) {
					int practitionerID = new Integer(referenceStep);
					String practitionerId = getTextMap.get(Integer
							.valueOf(practitionerID));
					returnObj = com.zillion.qa.practitioner.Patients
							.getPractitionerMailIdFromPractitionerID(driver,
									practitionerId);
					getTextMap.put(stepNo, returnObj.toString());
				} else if (inputData != null) {
					com.zillion.qa.practitioner.Patients
					.getPractitionerMailIdFromPractitionerID(driver,
							inputData);
				}
				break;
			case "Client2EnterRADOBDay":
				com.zillion.qa.realappealmember.member.client2EnterRADOBDay(driver);
				break;
			case "Client2EnterRADOBMonth":
				com.zillion.qa.realappealmember.member
				.client2EnterRADOBMonth(driver);
				break;
			case "Client2EnterRADOBYear":
				com.zillion.qa.realappealmember.member
				.client2EnterRADOBYear(driver);
				break;
			case "SubmitMaxEnrollMentAllowed":
				returnObj = com.zillion.qa.realappealmember.member
				.submitMaxEnrollmentsAllowedForOrganization(driver,
						inputData);
				break;
			case "RetreieveMaxEnrollmentsAllowedForOrganization":
				returnObj = com.zillion.qa.realappealmember.member
				.retreieveMaxEnrollMentAllowedForOrganization(driver);
				break;
			case "CheckClaimsNoOverlap":
				returnObj = com.zillion.qa.realappealmember.Claims
				.checkClaimsNoOverlap(driver);
				System.out.print("returnObj" + returnObj);
				if (returnObj.equals("Claims is achieved")) {
					getTextMap.put(stepNo, returnObj.toString());
					Assert.fail();
				} else if (returnObj.equals("No Claims is achieved")) {
					getTextMap.put(stepNo, returnObj.toString());
				}
				break;
			case "GroupSendEmailNoOverlap":
				String[] splitinputoverlap = inputData.split(",");
				com.zillion.qa.realappealmember.Claims.groupSendEmailNoOverlap(
						splitinputoverlap[0], splitinputoverlap[1],
						splitinputoverlap[2], splitinputoverlap[3],
						splitinputoverlap[4], splitinputoverlap[5],
						splitinputoverlap[6], splitinputoverlap[7]);
				break;
			case "GetMemFromPropertyBeFitPositive":
				returnObj = com.zillion.qa.realappealmember.member
				.getMemFromPropertyBeFitPositive(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "GetMemFromPropertyReschedule":
				returnObj = com.zillion.qa.realappealmember.member
				.getMemFromPropertyReschedule(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "CheckAchievementsFromHFOPSReschCust":
				returnObj = com.zillion.qa.realappealmember.member
				.checkAchievementsFromHFOPSReschCust(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "AttendNow":
				com.zillion.qa.realappealmember.Claims.attendnow(driver);
				break;
			case "GetMemFromPropertyOptOfPgm":
				returnObj = com.zillion.qa.realappealmember.member
				.getMemFromPropertyOptOfPgm(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "CheckAchievementsFromHFOPSOptOutOfPgm":
				returnObj = com.zillion.qa.realappealmember.member
				.checkAchievementsFromHFOPSOptOutOfPgm(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "CheckAchievementsFromHFOPSRABefitByBothActive":
				returnObj = com.zillion.qa.realappealmember.member
				.checkAchievementsFromHFOPSRABefitByBothActive(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "VerifyOrberaMemberLectureCancellation":
				com.zillion.qa.member.LiveSession
				.verifyOrberaMemberLectureCancellation(driver, inputData);
				break;
			case "GetCoachEmailIdFromQuery":
				returnObj = com.zillion.qa.practitioner.Patients
				.getCoachEmailIdFromQuery(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RetrieveAllowMaximumEnrollmentToBeTrue":
				returnObj = com.zillion.qa.realappealmember.member
				.retrieveAllowMaximumEnrollmentToBeTrue(driver);
				break;
			case "ProgramAdminMemberSearchTextboxOrganizationName":
				returnObj = com.zillion.qa.realappealmember.member
				.programAdminMemberSearchTextboxOrganizationName(driver);
				break;
			case "GetMember":
				if (inputData == null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					int refStep11 = new Integer(referenceStep);
					String getMember = getTextMap.get(Integer.valueOf(refStep11));
					String[] multipleData = StringUtils.split(getMember, ",");
					String getMember1 = multipleData[0];
					returnObj = getMember1;
					getTextMap.put(stepNo, returnObj.toString());
					return returnObj;

				}
				break;
			case "GetCoach":
				if (referenceStep != null) {
					int refStep11 = new Integer(referenceStep);
					String getCoach = getTextMap.get(Integer.valueOf(refStep11));
					String[] multipleData = StringUtils.split(getCoach, ",");
					String getCoach1 = multipleData[3];
					returnObj = getCoach1;
					getTextMap.put(stepNo, returnObj.toString());
					return returnObj;
				}
				break;
			case "GetOrberaMemberSchedule1on1":
				returnObj = com.zillion.qa.coaches.Members
				.getOrberaMemberSchedule1on1(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "GetMemFromPropertyScheduleCust":
				returnObj = com.zillion.qa.realappealmember.member
				.getMemFromPropertyScheduleCust(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "CheckAchievementsFromHFOPSSchCust":
				returnObj = com.zillion.qa.realappealmember.member
				.checkAchievementsFromHFOPSSchCust(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RetrieveEmailForProgramCompletedStatusandOrgIDClient1":
				returnObj = com.zillion.qa.realappealmember.member
				.retrieveEmailForProgramCompletedStatusandOrgIDClient1(
						driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "SelectInsuranceProviderName":
				returnObj = com.zillion.qa.realappealmember.member
				.selectInsuranceProviderName(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "CheckRollupMemberIsInFirstInt":
				String[] memberisinfirst = inputData.split(",");
				com.zillion.qa.realappealmember.Claims
				.checkRollupMemberIsInFirstInt(driver, memberisinfirst[0],
						memberisinfirst[1], memberisinfirst[2],
						memberisinfirst[3]);
				break;
			case "CheckClaimsMemberIsInFirstInt":
				String[] memberisinfirst1 = inputData.split(",");
				com.zillion.qa.realappealmember.member
				.checkClaimsMemberIsInFirstInt(driver, memberisinfirst1[0],
						memberisinfirst1[1], memberisinfirst1[2],
						memberisinfirst1[3]);
				break;
			case "GetMemFromPropertyMemberIsInFirstInt":
				returnObj = com.zillion.qa.realappealmember.member
				.getMemFromPropertyMemberIsInFirstInt(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "CheckAchievementsFromHFOPSMemberIsInFirstInt":
				returnObj = com.zillion.qa.realappealmember.member
				.checkAchievementsFromHFOPSMemberIsInFirstInt(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "CheckRollupNotWeightLoss":
				returnObj = com.zillion.qa.realappealmember.Claims
				.checkRollupNoWeightLoss(driver);
				getTextMap.put(stepNo, returnObj.toString());
				if (returnObj.equals("Row is retrieved for the negative scenario")) {
					Assert.fail();
				}
				break;
			case "CheckClaimsNoWeightLoss":
				returnObj = com.zillion.qa.realappealmember.Claims
				.checkClaimsNoWeightLoss(driver);
				getTextMap.put(stepNo, returnObj.toString());
				if (returnObj
						.equals("Claims is achieved for the negative scenario")) {
					Assert.fail();
				}
				break;
			case "CheckRollupMissedGrpAttended1on1":
				String[] parametersdata = inputData.split(",");
				com.zillion.qa.realappealmember.Claims
				.checkRollupMissedGrpAttended1on1(driver,
						parametersdata[0], parametersdata[1],
						parametersdata[2], parametersdata[3]);
				break;
			case "CheckClaimsMissedGrpAttended1on1":
				String[] parametersdata1 = inputData.split(",");
				com.zillion.qa.realappealmember.member
				.checkClaimsMissedGrpAttended1on1(driver,
						parametersdata1[0], parametersdata1[1],
						parametersdata1[2], parametersdata1[3]);
				break;
			case "GetMemFromPropertyMissedGrpAttended1on1":
				returnObj = com.zillion.qa.realappealmember.member
				.getMemFromPropertyMissedGrpAttended1on1(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "CheckAchievementsFromHFOPSMissedGrpAttended1on1":
				returnObj = com.zillion.qa.realappealmember.member
				.checkAchievementsFromHFOPSMissedGrpAttended1on1(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RetrieveInactivemember":
				returnObj = com.zillion.qa.opsadmin.Dashboard
				.retrieveInactivemember1(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "UseCaseOneEnterRADOBDay":
				com.zillion.qa.realappealmember.member
				.useCaseOneEnterRADOBDay(driver);
				break;
			case "UseCaseOneEnterRADOBMonth":
				com.zillion.qa.realappealmember.member
				.useCaseOneEnterRADOBMonth(driver);
				break;
			case "UseCaseOneEnterRADOBYear":
				com.zillion.qa.realappealmember.member
				.useCaseOneEnterRADOBYear(driver);
				break;
			case "RealAppealUseCaseOneEnterRAFirstName":
				com.zillion.qa.realappealmember.member
				.realAppealUseCaseOneEnterRAFirstName(driver);
				break;
			case "RealAppealUseCaseOneEnterRALastName":
				com.zillion.qa.realappealmember.member
				.realAppealUseCaseOneEnterRALastName(driver);
				break;
			case "RealAppealUseCaseOneMemberURL":
				returnObj = com.zillion.qa.realappealmember.member
				.realAppealUseCaseOneMemberURL(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RealAppealCustomMemberID":
				com.zillion.qa.realappealmember.member
				.realAppealCustomMemberID(driver);
				break;
			case "EnterUseCaseOneRAFirstNameOnInsurancePopUp":
				com.zillion.qa.realappealmember.member
				.enterUseCaseOneRAFirstNameOnInsurancePopUp(driver);
				break;
			case "EnterUseCaseOneRALastNameOnInsurancePopUp":
				com.zillion.qa.realappealmember.member
				.enterUseCaseOneRALastNameOnInsurancePopUp(driver);
				break;
			case "UseCaseTwoEnterRADOBDay":
				com.zillion.qa.realappealmember.member
				.useCaseTwoEnterRADOBDay(driver);
				break;
			case "UseCaseTwoEnterRADOBMonth":
				com.zillion.qa.realappealmember.member
				.useCaseTwoEnterRADOBMonth(driver);
				break;
			case "UseCaseTwoEnterRADOBYear":
				com.zillion.qa.realappealmember.member
				.useCaseTwoEnterRADOBYear(driver);
				break;
			case "RealAppealUseCaseTwoEnterRAFirstName":
				com.zillion.qa.realappealmember.member
				.realAppealUseCaseTwoEnterRAFirstName(driver);
				break;
			case "RealAppealUseCaseTwoEnterRALastName":
				com.zillion.qa.realappealmember.member
				.realAppealUseCaseTwoEnterRALastName(driver);
				break;
			case "RealAppealUseCaseTwoMemberURL":
				returnObj = com.zillion.qa.realappealmember.member
				.realAppealUseCaseTwoMemberURL(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RealAppealUseCaseTwoCustomMemberID":
				com.zillion.qa.realappealmember.member
				.realAppealUseCaseTwoCustomMemberID(driver);
				break;
			case "EnterUseCaseTwoRAFirstNameOnInsurancePopUp":
				com.zillion.qa.realappealmember.member
				.enterUseCaseTwoRAFirstNameOnInsurancePopUp(driver);
				break;
			case "EnterUseCaseTwoRALastNameOnInsurancePopUp":
				com.zillion.qa.realappealmember.member
				.enterUseCaseTwoRALastNameOnInsurancePopUp(driver);
				break;
			case "RealAppealUseCaseTwoCustomGroupNumber":
				com.zillion.qa.realappealmember.member
				.realAppealUseCaseTwoCustomGroupNumber(driver);
				break;
			case "GetMemberFirstandLastName":
				if (inputData == null && referenceStep != null) {
					int refStep11 = new Integer(referenceStep);
					String getFirstandLastName = getTextMap.get(Integer
							.valueOf(refStep11));
					String[] multipleData = StringUtils.split(getFirstandLastName,
							",");
					String getFirstandLastName1 = multipleData[1] + " "
							+ multipleData[2];
					returnObj = getFirstandLastName1;
					getTextMap.put(stepNo, returnObj.toString());
					break;
				}
			case "GetOrberaMemberMessageCenter":
				returnObj = com.zillion.qa.coaches.Members
				.getOrberaMemberMessageCenter(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "MessageReceivedByMemberCoachNameValidatedWithDB":
				String[] referenceSteps13 = StringUtils.split(referenceStep, ",");
				int refStep30 = new Integer(referenceSteps13[0]);
				int refStep301 = new Integer(referenceSteps13[1]);
				int refStep302 = new Integer(referenceSteps13[2]);
				String getText125 = getTextMap.get(Integer.valueOf(refStep30));
				String getText1251 = getTextMap.get(Integer.valueOf(refStep301));
				String getText1252 = getTextMap.get(Integer.valueOf(refStep302));
				System.out.println("getMessageReceivedSubjectFromSheet: "
						+ getText125);
				String[] messageReceivedMemberSubjectFromSheet1 = getText125
						.split("This");
				String messageReceivedMemberSubjectTimeStampFromSheet1 = messageReceivedMemberSubjectFromSheet1[0];
				System.out
				.println("Splitted subject time stamp from sheet Subject: "
						+ messageReceivedMemberSubjectTimeStampFromSheet1);
				com.zillion.qa.coaches.Messages
				.messageSentByMemberCoachNameValidatedWithDB(driver,
						messageReceivedMemberSubjectTimeStampFromSheet1,
						getText1252, getText1251);
				break;
			case "UseCaseThreeEnterRADOBDay":
				com.zillion.qa.realappealmember.member
				.useCaseThreeEnterRADOBDay(driver);
				break;
			case "UseCaseThreeEnterRADOBMonth":
				com.zillion.qa.realappealmember.member
				.useCaseThreeEnterRADOBMonth(driver);
				break;
			case "UseCaseThreeEnterRADOBYear":
				com.zillion.qa.realappealmember.member
				.useCaseThreeEnterRADOBYear(driver);
				break;
			case "RealAppealUseCaseThreeEnterRAFirstName":
				com.zillion.qa.realappealmember.member
				.realAppealUseCaseThreeEnterRAFirstName(driver);
				break;
			case "RealAppealUseCaseThreeEnterRALastName":
				com.zillion.qa.realappealmember.member
				.realAppealUseCaseThreeEnterRALastName(driver);
				break;
			case "RealAppealUseCaseThreeMemberURL":
				returnObj = com.zillion.qa.realappealmember.member
				.realAppealUseCaseThreeMemberURL(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RealAppealUseCaseThreeCustomMemberID":
				com.zillion.qa.realappealmember.member
				.realAppealUseCaseThreeCustomMemberID(driver);
				break;
			case "EnterUseCaseThreeRAFirstNameOnInsurancePopUp":
				com.zillion.qa.realappealmember.member
				.enterUseCaseThreeRAFirstNameOnInsurancePopUp(driver);
				break;
			case "EnterUseCaseThreeRALastNameOnInsurancePopUp":
				com.zillion.qa.realappealmember.member
				.enterUseCaseThreeRALastNameOnInsurancePopUp(driver);
				break;
			case "RealAppealUseCaseThreeCustomGroupNumber":
				com.zillion.qa.realappealmember.member
				.realAppealUseCaseThreeCustomGroupNumber(driver);
				break;
			case "UseCaseFourUnitedHeathEnterRADOBDay":
				com.zillion.qa.realappealmember.member
				.useCaseFourUnitedHeathEnterRADOBDay(driver);
				break;
			case "UseCaseFourUnitedHeathEnterRADOBMonth":
				com.zillion.qa.realappealmember.member
				.useCaseFourUnitedHeathEnterRADOBMonth(driver);
				break;
			case "UseCaseFourUnitedHeathEnterRADOBYear":
				com.zillion.qa.realappealmember.member
				.useCaseFourUnitedHeathEnterRADOBYear(driver);
				break;
			case "RealAppealUseCaseFourUnitedHeathEnterRAFirstName":
				com.zillion.qa.realappealmember.member
				.realAppealUseCaseFourUnitedHeathEnterRAFirstName(driver);
				break;
			case "RealAppealUseCaseFourUnitedHeathEnterRALastName":
				com.zillion.qa.realappealmember.member
				.realAppealUseCaseFourUnitedHeathEnterRALastName(driver);
				break;
			case "RealAppealUseCaseUnitedHeathFourMemberURL":
				returnObj = com.zillion.qa.realappealmember.member
				.realAppealUseCaseUnitedHeathFourMemberURL(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RealAppealUseCaseFourUnitedHeathCustomMemberID":
				com.zillion.qa.realappealmember.member
				.realAppealUseCaseFourUnitedHeathCustomMemberID(driver);
				break;
			case "EnterUseCaseFourUnitedHeathRAFirstNameOnInsurancePopUp":
				com.zillion.qa.realappealmember.member
				.enterUseCaseFourUnitedHeathRAFirstNameOnInsurancePopUp(driver);
				break;
			case "EnterUseCaseFourUnitedHeathRALastNameOnInsurancePopUp":
				com.zillion.qa.realappealmember.member
				.enterUseCaseFourUnitedHeathRALastNameOnInsurancePopUp(driver);
				break;
			case "RealAppealUseCaseUnitedHeathFourCustomGroupNumber":
				com.zillion.qa.realappealmember.member
				.realAppealUseCaseUnitedHeathFourCustomGroupNumber(driver);
				break;
			case "RealAppealUseCaseFourUnitedHeathInsuranceCarrierSelect":
				com.zillion.qa.realappealmember.member
				.realAppealUseCaseFourUnitedHeathInsuranceCarrierSelect(driver);
				break;
			case "PractitionerAddNewPatientforReSchedule":
				com.zillion.qa.practitioner.Patients
				.practitionerAddNewPatientforReSchedule(driver, inputData);
				break;
			case "UseCaseFourClient2EnterRADOBDay":
				com.zillion.qa.realappealmember.member
				.useCaseFourClient2EnterRADOBDay(driver);
				break;
			case "UseCaseFourClient2EnterRADOBMonth":
				com.zillion.qa.realappealmember.member
				.useCaseFourClient2EnterRADOBMonth(driver);
				break;
			case "UseCaseFourClient2EnterRADOBYear":
				com.zillion.qa.realappealmember.member
				.useCaseFourClient2EnterRADOBYear(driver);
				break;
			case "RealAppealUseCaseFourClient2EnterRAFirstName":
				com.zillion.qa.realappealmember.member
				.realAppealUseCaseFourClient2EnterRAFirstName(driver);
				break;
			case "RealAppealUseCaseFourClient2EnterRALastName":
				com.zillion.qa.realappealmember.member
				.realAppealUseCaseFourClient2EnterRALastName(driver);
				break;
			case "RealAppealUseCaseClient2FourMemberURL":
				returnObj = com.zillion.qa.realappealmember.member
				.realAppealUseCaseClient2FourMemberURL(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RealAppealUseCaseFourClient2CustomMemberID":
				com.zillion.qa.realappealmember.member
				.realAppealUseCaseFourClient2CustomMemberID(driver);
				break;
			case "EnterUseCaseFourClient2RAFirstNameOnInsurancePopUp":
				com.zillion.qa.realappealmember.member
				.enterUseCaseFourClient2RAFirstNameOnInsurancePopUp(driver);
				break;
			case "EnterUseCaseFourClient2RALastNameOnInsurancePopUp":
				com.zillion.qa.realappealmember.member
				.enterUseCaseFourClient2RALastNameOnInsurancePopUp(driver);
				break;
			case "RealAppealUseCaseClient2FourCustomGroupNumber":
				com.zillion.qa.realappealmember.member
				.realAppealUseCaseClient2FourCustomGroupNumber(driver);
				break;
			case "RealAppealUseCaseFourClient2InsuranceCarrierSelect":
				com.zillion.qa.realappealmember.member
				.realAppealUseCaseFourClient2InsuranceCarrierSelect(driver);
				break;
			case "UseCaseFourClient3EnterRADOBDay":
				com.zillion.qa.realappealmember.member
				.useCaseFourClient3EnterRADOBDay(driver);
				break;
			case "UseCaseFourClient3EnterRADOBMonth":
				com.zillion.qa.realappealmember.member
				.useCaseFourClient3EnterRADOBMonth(driver);
				break;
			case "UseCaseFourClient3EnterRADOBYear":
				com.zillion.qa.realappealmember.member
				.useCaseFourClient3EnterRADOBYear(driver);
				break;
			case "RealAppealUseCaseFourClient3EnterRAFirstName":
				com.zillion.qa.realappealmember.member
				.realAppealUseCaseFourClient3EnterRAFirstName(driver);
				break;
			case "RealAppealUseCaseFourClient3EnterRALastName":
				com.zillion.qa.realappealmember.member
				.realAppealUseCaseFourClient3EnterRALastName(driver);
				break;
			case "RealAppealUseCaseClient3FourMemberURL":
				returnObj = com.zillion.qa.realappealmember.member
				.realAppealUseCaseClient3FourMemberURL(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RealAppealUseCaseFourClient3CustomMemberID":
				com.zillion.qa.realappealmember.member
				.realAppealUseCaseFourClient3CustomMemberID(driver);
				break;
			case "EnterUseCaseFourClient3RAFirstNameOnInsurancePopUp":
				com.zillion.qa.realappealmember.member
				.enterUseCaseFourClient3RAFirstNameOnInsurancePopUp(driver);
				break;
			case "EnterUseCaseFourClient3RALastNameOnInsurancePopUp":
				com.zillion.qa.realappealmember.member
				.enterUseCaseFourClient3RALastNameOnInsurancePopUp(driver);
				break;
			case "RealAppealUseCaseClient3FourCustomGroupNumber":
				com.zillion.qa.realappealmember.member
				.realAppealUseCaseClient3FourCustomGroupNumber(driver);
				break;
			case "RealAppealUseCaseFourClient3InsuranceCarrierSelect":
				com.zillion.qa.realappealmember.member
				.realAppealUseCaseFourClient3InsuranceCarrierSelect(driver);
				break;
			case "VerifyScheduleOrChange":
				if (referenceStep != null) {
					int mailschedule = new Integer(referenceStep);
					String mailschedule1 = getTextMap.get(Integer
							.valueOf(mailschedule));
					com.zillion.qa.member.Dashboard.verifyScheduleOrChange(driver,
							mailschedule1);
				} else {
					com.zillion.qa.member.Dashboard.verifyScheduleOrChange(driver,
							"dummyvalue");
				}
				break;
			case "OneononeApprovalcheckboxFromPA":
				int frompa = new Integer(referenceStep);
				String frompa1 = getTextMap.get(Integer.valueOf(frompa));
				com.zillion.qa.opsadmin.Classes.oneononeApprovalcheckboxFromPA(
						driver, frompa1);
				break;
			case "RealAppealMemberTrackerAddcaloriesValues":
				returnObj = com.zillion.qa.realappealmember.member
				.realAppealMemberTrackerAddcaloriesValues(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "SplitRealAppealMemberTrackerAddcaloriesValues":
				int refStep11 = new Integer(referenceStep);
				String getcaloriesvalue = getTextMap
						.get(Integer.valueOf(refStep11));
				String[] getcaloriesvalue1 = StringUtils.split(getcaloriesvalue,
						" ");
				String getcaloriesvalue2 = getcaloriesvalue1[0];
				returnObj = getcaloriesvalue2;
				getTextMap.put(stepNo, returnObj.toString());
				return returnObj;
			case "MemberTrackerCompareDailyCalorie":
				returnObj = com.zillion.qa.realappealmember.member
				.memberTrackerCompareDailyCalorie(driver, inputData);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "MemberStartingWeight":
				returnObj = com.zillion.qa.realappealmember.member
				.memberStartingWeight(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "DeleteAllSavedExercise":
				com.zillion.qa.realappealmember.member
				.deleteAllSavedExercise(driver);
				break;
			case "GetXYOFElement": {
				returnObj = Navigate.getXYOFElement(driver, normalXpath);
				getTextMap.put(stepNo, returnObj.toString());
			}
			break;
			case "PracticePALogin":
				com.zillion.qa.programadmin.Dashboard.practicePALogin(driver);
				break;

			case "PracticePALogout":
				com.zillion.qa.programadmin.Dashboard.practicePALogout(driver);
				break;
			case "ClickOnAppropriateProvider":
				if (inputData != null) {
					com.zillion.qa.opsadmin.Providers.clickOnAppropriateProvider(
							driver, inputData);
				} else if (referenceStep != null) {
					int getxyref = new Integer(referenceStep);
					String getxyref1 = getTextMap.get(Integer.valueOf(getxyref));
					com.zillion.qa.opsadmin.Providers.clickOnAppropriateProvider(
							driver, getxyref1);
				}
				break;
			case "CoachUnapprovedStatus1on1SessionByPA":
				returnObj = com.zillion.qa.coaches.Classes
				.coachUnapprovedStatus1on1SessionByPA(driver);
				break;
			case "CoachApprovalStatus1on1SessionByPA":
				returnObj = com.zillion.qa.coaches.Classes
				.coachApprovedStatus1on1SessionByPA(driver);
				break;
			case "GetWindowandClose":
				Manipulation.getwindowandclose(driver, element);
				break;
			case "RetrieveMailWithNameOfPrv":
				int prov_name = new Integer(referenceStep);
				String prov_name1 = getTextMap.get(Integer.valueOf(prov_name));
				returnObj = com.zillion.qa.opsadmin.Providers
						.retrieveMailWithNameOfPrv(driver, prov_name1);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "PracticePAAddNewPatient":
				returnObj = com.zillion.qa.practitioner.Patients
				.practicePAAddNewPatient(driver, inputData);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "PracticePALogin2":
				com.zillion.qa.programadmin.Dashboard.practicePALogin2(driver);
				break;
			case "SelectFuturedateSession":
				com.zillion.qa.member.Dashboard.selectFuturedateSession(driver);
				break;
			case "RealAppealCoachLoginUsingInput":
				int getxyreference = new Integer(referenceStep);
				String getxyreference1 = getTextMap.get(Integer
						.valueOf(getxyreference));
				com.zillion.qa.realappealcoach.coach
				.realAppealCoachLoginUsingInput(driver, getxyreference1);
				break;
			case "SplitRATrackerExerciseHours":
				int refStep221 = new Integer(referenceStep);
				String activityhours = getTextMap.get(Integer.valueOf(refStep221));
				String[] activityhours1 = StringUtils.split(activityhours, " ");
				String activityhours2 = activityhours1[0];
				returnObj = activityhours2;
				getTextMap.put(stepNo, returnObj.toString());
				return returnObj;
			case "WellnessCorpPAlectureLiveSession":
				int verifyMemberSignUp2 = new Integer(referenceStep);
				String verifyMemberSignUp3 = getTextMap.get(Integer
						.valueOf(verifyMemberSignUp2));
				com.zillion.qa.member.WellnessCorpPALectureLiveSession
				.wellnessCorpPAlectureLiveSession(driver, inputData,
						verifyMemberSignUp3);
				break;
			case "ValidateDayOnCalender":
				com.zillion.qa.programadmin.Classes.validateDayOnCalender(driver);
				break;
			case "PickmemberRequest":
				returnObj = com.zillion.qa.realappealmember.member
				.pickmemberRequest(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "HighlightedColorAndCurrentDateValidate":
				com.zillion.qa.programadmin.Classes
				.highlightedColorAndCurrentDateValidate(driver);
				break;
			case "LectureSessionTimeSlotSearch":
				com.zillion.qa.member.liveSessionSubCommonMethods
				.lectureSessionTimeSlotSearch(driver);
				break;
			case "VerifyCreatedLectureSessionForLiveSession":
				int lectureLiveSession123 = new Integer(referenceStep);
				String lectureLiveSession321 = getTextMap.get(Integer
						.valueOf(lectureLiveSession123));
				com.zillion.qa.member.liveSessionSubCommonMethods
				.verifyCreatedLectureSessionForLiveSession(driver,
						lectureLiveSession321);
				break;
			case "OneononeScheduleIntheCalendar":
				com.zillion.qa.member.Dashboard
				.oneononeScheduleIntheCalendar(driver);
				break;
			case "GetPracticePgmAdmin":
				returnObj = com.zillion.qa.programadmin.Dashboard
				.getPracticePgmAdmin(driver, inputData);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "TimeslotCoachNmaespliting_string":
				int refStep01 = new Integer(referenceStep);
				String coachname = getTextMap.get(Integer.valueOf(refStep01));
				String[] coachname1 = coachname.split("\\s+");
				String coachname2 = coachname1[3] + " " + coachname1[4];
				System.out.println(coachname2);
				returnObj = coachname2;
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "ValidateMonthOnCalender":
				com.zillion.qa.programadmin.Classes.validateMonthOnCalender(driver);
				break;
			case "ValidateTimeZone":
				int reference = new Integer(referenceStep);
				String timezone = getTextMap.get(Integer.valueOf(reference));
				com.zillion.qa.programadmin.Classes.validateTimeZone(driver,
						timezone);
				break;
			case "GetSelectedFirstElementOfSelectBox":
				returnObj = Manipulation
				.getSelectedFirstElementOfSelectBox(element);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "FitnessDayViewValidateActivityHistory":
				returnObj = com.zillion.qa.member.Tracker
				.fitnessDayViewValidateActivityHistory(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "GetActiveRAMember":
				returnObj = com.zillion.qa.realappealmember.member
				.getActiveRAMember(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "ActivityHistoryCountVerify":
				int getCount1 = new Integer(referenceStep);
				String getCount2 = getTextMap.get(Integer.valueOf(getCount1));
				returnObj = com.zillion.qa.member.Tracker
						.activityHistoryCountVerify(driver, inputData, getCount2);
				break;
			case "TypeCurrentDateStartEndDate":
				returnObj = com.zillion.qa.member.Tracker
				.typeCurrentDateStartEndDate(driver);
				break;
			case "ProviderTypeCurrentDateStartEndDate":
				returnObj = com.zillion.qa.member.Tracker
				.providerTypeCurrentDateStartEndDate(driver);
				break;
			case "CheckPartialText":
				if (inputData == null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					String[] referenceStepspartial = StringUtils.split(
							referenceStep, ",");
					int refSteppartial1 = new Integer(referenceStepspartial[0]);
					int refSteppartial2 = new Integer(referenceStepspartial[1]);
					String getText1par1 = getTextMap.get(Integer
							.valueOf(refSteppartial1));
					String getText2par2 = getTextMap.get(Integer
							.valueOf(refSteppartial2));
					returnObj = Manipulation.checkPartialText(getText1par1,
							getText2par2);
				} else if (inputData != null && referenceStep != null) {
					int refStep6 = new Integer(referenceStep);
					String getText1par1 = getTextMap.get(Integer.valueOf(refStep6));
					System.out.println("getText1" + getText1par1);
					returnObj = Manipulation.checkPartialText(getText1par1,
							inputData);
				}
				break;
			case "ChooseAptMemberWCSPA":
				com.zillion.qa.coaches.Members.chooseAptMemberWCSPA(driver);
				break;
			case "WeightProviderTypeCurrentDateStartEndDate":
				returnObj = com.zillion.qa.member.Tracker
				.weightProviderTypeCurrentDateStartEndDate(driver);
				break;
			case "GetOrberaMemberForTracker":
				returnObj = com.zillion.qa.member.Tracker
				.getOrberaMemberForTracker(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "CoachLoginWithRefEmail":
				int mail1 = new Integer(referenceStep);
				String coachMail = getTextMap.get(Integer.valueOf(mail1));
				returnObj = com.zillion.qa.member.Tracker.coachLoginWithRefEmail(
						driver, coachMail);
				break;
			case "ScheduledOneOnOneStartAndEnd":
				int startEnd = new Integer(referenceStep);
				String StartAndEndTime = getTextMap.get(Integer.valueOf(startEnd));
				returnObj = com.zillion.qa.member.Dashboard
						.scheduledOneOnOneStartAndEnd(driver, StartAndEndTime);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "AssignSubtituteCoachForScheduledOneOnOne":
				int scheduleTime = new Integer(referenceStep);
				String ScheduleTime = getTextMap.get(Integer.valueOf(scheduleTime));
				returnObj = com.zillion.qa.member.Dashboard
						.assignSubtituteCoachForScheduledOneOnOne(driver,
								ScheduleTime);
				break;
			case "ClickUsingSize":
				Manipulation.jsClickByXPath(driver, normalXpath);
				break;
			case "ChooseOrgForProv":
				com.zillion.qa.opsadmin.Providers.chooseOrgForProv(driver);
				break;
			case "TypeScheduledTimeAsUnavailable":
				int scheduleTime1 = new Integer(referenceStep);
				String ScheduleTime2 = getTextMap.get(Integer
						.valueOf(scheduleTime1));
				returnObj = com.zillion.qa.member.Dashboard
						.typeScheduledTimeAsUnavailable(driver, ScheduleTime2);
				break;
			case "SessionConflictsScheduledOneOnOneRescheduleOption":
				int scheduleTime2 = new Integer(referenceStep);
				String ScheduleTime3 = getTextMap.get(Integer
						.valueOf(scheduleTime2));
				returnObj = com.zillion.qa.member.Dashboard
						.sessionConflictsScheduledOneOnOneRescheduleOption(driver,
								ScheduleTime3);
				break;
			case "SessionConflictsScheduledOneOnOneCancelOption":
				int scheduleTime3 = new Integer(referenceStep);
				String ScheduleTime4 = getTextMap.get(Integer
						.valueOf(scheduleTime3));
				returnObj = com.zillion.qa.member.Dashboard
						.sessionConflictsScheduledOneOnOneCancelOption(driver,
								ScheduleTime4);
				break;

			case "SelectInvoiceOrCreditCard":
				String[] paymnetinput = StringUtils.split(inputData, ",");
				com.zillion.qa.opsadmin.Clients.selectInvoiceOrCreditCard(driver,
						paymnetinput[0], paymnetinput[1]);
				break;
			case "Getorberamember":
				returnObj = com.zillion.qa.member.Dashboard.getorberamember(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RetrieveCoachMailForGroupSession":
				returnObj = com.zillion.qa.realappealprogramadmin.programadmin
				.retrieveCoachMailForGroupSession(driver, inputData);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "ChangeState":
				String[] stateinput = StringUtils.split(inputData, ",");
				com.zillion.qa.opsadmin.Clients.changeState(driver, stateinput[0],
						stateinput[1], stateinput[2]);
				break;
			case "PracticePAscheduledOneOnOneStartAndEnd":
				int startEnd1 = new Integer(referenceStep);
				String StartAndEndTime1 = getTextMap
						.get(Integer.valueOf(startEnd1));
				returnObj = com.zillion.qa.member.Dashboard
						.PracticePAscheduledOneOnOneStartAndEnd(driver,
								StartAndEndTime1);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "CreateRAProvider":
				returnObj = com.zillion.qa.opsadmin.Providers.createRAProvider(
						driver, inputData);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "GetProviderFname":

				int refStep121 = new Integer(referenceStep);
				String getProviderName = getTextMap
						.get(Integer.valueOf(refStep121));
				String[] multipleData = StringUtils.split(getProviderName, ",");
				String getProviderFName = multipleData[0];
				returnObj = getProviderFName;
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "GetProviderEmail":
				int refStep122 = new Integer(referenceStep);
				String getProviderEmail = getTextMap.get(Integer
						.valueOf(refStep122));
				String[] multipleData1 = StringUtils.split(getProviderEmail, ",");
				String getProviderEmail1 = multipleData1[1];
				returnObj = getProviderEmail1;
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RealAppealScheduledOneOnOneStartTime":
				int starttime = new Integer(referenceStep);
				String Starttime = getTextMap.get(Integer.valueOf(starttime));
				returnObj = com.zillion.qa.realappealmember.member
						.realAppealScheduledOneOnOneStartTime(driver, Starttime);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RACoachScheduledOneOnOneStartTime":
				int starttime1 = new Integer(referenceStep);
				String Starttime2 = getTextMap.get(Integer.valueOf(starttime1));
				returnObj = com.zillion.qa.realappealmember.member
						.raCoachScheduledOneOnOneStartTime(driver, Starttime2);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RACoachScheduledOneOnOneEndTime":
				int starttime3 = new Integer(referenceStep);
				String Starttime4 = getTextMap.get(Integer.valueOf(starttime3));
				returnObj = com.zillion.qa.realappealmember.member
						.raCoachScheduledOneOnOneEndTime(driver, Starttime4);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "CancelSessionForScheduledOneOnOne":
				int starttime4 = new Integer(referenceStep);
				String Starttime5 = getTextMap.get(Integer.valueOf(starttime4));
				returnObj = com.zillion.qa.realappealmember.member
						.cancelSessionForScheduledOneOnOne(driver, Starttime5);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "AddCreditCard":
				String[] creditcardinput = StringUtils.split(inputData, ",");
				com.zillion.qa.opsadmin.Clients.addcreditcard(driver,
						creditcardinput[0], creditcardinput[1], creditcardinput[2],
						creditcardinput[3], creditcardinput[4]);
				break;
			case "YopmailActivation":
				int generatedEmail = new Integer(referenceStep);
				String generatedEmail1 = getTextMap.get(Integer
						.valueOf(generatedEmail));
				com.zillion.qa.opsadmin.Providers.yopMailActivation(driver,
						inputData, generatedEmail1);
				break;
			case "AddPatient":
				String[] patientinput = StringUtils.split(inputData, ",");
				returnObj = com.zillion.qa.opsadmin.Clients.addpatient(driver,
						patientinput[0], patientinput[1], patientinput[2],
						patientinput[3], patientinput[4], patientinput[5],
						patientinput[6], patientinput[7]);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "DeleteCreditCard":
				com.zillion.qa.opsadmin.Clients.deletecreditcard(driver);
				break;
			case "Add20CreditCard":
				String[] creditcardvalues = StringUtils.split(inputData, ",");
				com.zillion.qa.opsadmin.Clients.add20creditcard(driver,
						creditcardvalues[0], creditcardvalues[1],
						creditcardvalues[2], creditcardvalues[3],
						creditcardvalues[4]);
				break;
			case "practitionerAddNewPatientCCorInvoice":
				com.zillion.qa.practitioner.Patients
				.practitionerAddNewPatientCCorInvoice(driver);
				break;
			case "VerifyOrberaPALectureSession":
				if (inputData != null && referenceStep == null) {
					com.zillion.qa.coaches.Classes
					.verifyorberaPAMailLectureSession(driver, inputData);
				} else if (inputData == null && referenceStep != null
						&& !referenceStep.trim().equals("")) {
					int mailidreforbera1 = new Integer(referenceStep);
					String mailidtextorbera1 = getTextMap.get(Integer
							.valueOf(mailidreforbera1));
					com.zillion.qa.coaches.Classes
					.verifyorberaPAMailLectureSession(driver,
							mailidtextorbera1);
				}
				break;
			case "VerifyOrberaPALectureCancellation":
				com.zillion.qa.member.LiveSession
				.verifyOrberaPALectureCancellation(driver, inputData);
				break;

			case "RealappealSessionConflictsScheduledOneOnOneRescheduleOption":
				int scheduleTime4 = new Integer(referenceStep);
				String ScheduleTime5 = getTextMap.get(Integer
						.valueOf(scheduleTime4));
				returnObj = com.zillion.qa.realappealmember.member
						.realappealSessionConflictsScheduledOneOnOneRescheduleOption(
								driver, ScheduleTime5);
				break;
			case "RealappealSessionConflictsScheduledOneOnOneCancelOption":
				int scheduleTime5 = new Integer(referenceStep);
				String ScheduleTime6 = getTextMap.get(Integer
						.valueOf(scheduleTime5));
				returnObj = com.zillion.qa.realappealmember.member
						.realappealSessionConflictsScheduledOneOnOneCancelOption(
								driver, ScheduleTime6);
				break;
			case "RAAppealGroupLiveSessionCoachLogin":
				if (inputData == null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					int coachemailref1 = new Integer(referenceStep);
					String coachemailref2 = getTextMap.get(Integer
							.valueOf(coachemailref1));
					com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
					.raAppealGroupLiveSessionCoachLogin(driver,
							coachemailref2);
				} else if (inputData != null && referenceStep != null
						&& !referenceStep.trim().equals("")) {
					com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
					.raAppealGroupLiveSessionCoachLogin(driver, inputData);
				}
				break;
			case "RACoachScheduledGroupSessionStartAndEndTime":
				int starttime2 = new Integer(referenceStep);
				String Starttime3 = getTextMap.get(Integer.valueOf(starttime2));
				returnObj = com.zillion.qa.realappealmember.member
						.raCoachScheduledGroupSessionStartAndEndTime(driver,
								Starttime3);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "AssignSubtituteCoachForScheduledGroupSession":
				int ScheduleTime7 = new Integer(referenceStep);
				String ScheduleTime8 = getTextMap.get(Integer
						.valueOf(ScheduleTime7));
				returnObj = com.zillion.qa.realappealmember.member
						.assignSubtituteCoachForScheduledGroupSession(driver,
								ScheduleTime8);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "CheckValueIsEmpty":
				if (inputData == null && referenceStep != null) {
					int refSte = new Integer(referenceStep);
					String refSte2 = getTextMap.get(Integer.valueOf(refSte));
					returnObj = Manipulation.stringIsEmpty(refSte2);
				}
				break;
			case "RealAppealVerifyScheduleOrChange":
				if (referenceStep != null) {
					int mailschedule = new Integer(referenceStep);
					String mailschedule1 = getTextMap.get(Integer
							.valueOf(mailschedule));
					returnObj = com.zillion.qa.realappealmember.member
							.realappealVerifyScheduleOrChange(driver, mailschedule1);
					getTextMap.put(stepNo, returnObj.toString());
				} else {
					returnObj = com.zillion.qa.realappealmember.member
							.realappealVerifyScheduleOrChange(driver, "dummyvalue");
					getTextMap.put(stepNo, returnObj.toString());
				}
				break;
			case "RealAppealCoachMemberSearch":
				if (inputData == null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					int memEmail = new Integer(referenceStep);
					String memEmail1 = getTextMap.get(Integer.valueOf(memEmail));
					String[] memEmail2 = memEmail1.split(",");
					String memEmail3 = memEmail2[0];
					returnObj = com.zillion.qa.realappealprogramadmin.programadmin
							.realAppealCoachMemberSearch(driver, memEmail3);
					getTextMap.put(stepNo, returnObj.toString());
				}
				break;
			case "GetPAFromConfigFile":
				returnObj = com.zillion.qa.programadmin.Messages
				.getPAFromConfigFile(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "DeleteTwentyCreditCard":
				com.zillion.qa.opsadmin.Clients.deletetwentycreditcard(driver);
				break;

			case "RealAppealnextDateCoachUnavailabeTimeSlot":
				com.zillion.qa.realappealprogramadmin.programadmin
				.realAppealnextDateCoachUnavailabeTimeSlot(driver);
				break;
			case "ReturnTypeValueReUse":
				if (inputData != null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					int referenceData = new Integer(referenceStep);
					int testData = new Integer(inputData);
					String refData = getTextMap.get(Integer.valueOf(referenceData));
					String[] refData1 = refData.split(",");
					String refData2 = refData1[testData];
					returnObj = refData2;
					getTextMap.put(stepNo, returnObj.toString());
				}
				break;
			case "RefundOnCreditCard":
				int refSte = new Integer(referenceStep);
				String refSte2 = getTextMap.get(Integer.valueOf(refSte));
				returnObj = com.zillion.qa.programadmin.Clients.refundoncreditcard(
						driver, refSte2);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "GetProviderId":
				String[] nameinputs = inputData.split(",");
				returnObj = com.zillion.qa.programadmin.Clients.getProviderId(
						driver, nameinputs[0], nameinputs[1]);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "GetPaymentId":
				int refpay = new Integer(referenceStep);
				String refpay1 = getTextMap.get(Integer.valueOf(refpay));
				returnObj = com.zillion.qa.programadmin.Clients.getPaymentId(
						driver, refpay1);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "GetBillingHistory":
				int refbill = new Integer(referenceStep);
				String refbill1 = getTextMap.get(Integer.valueOf(refbill));
				com.zillion.qa.programadmin.Clients.getBillingHistory(driver,
						refbill1);
				break;
			case "RemoveunavailabilityAndSchedule":
				com.zillion.qa.programadmin.Clients.removeunavailability(driver);
				break;
			case "RAProgramAdminScheduledOneOnOneRescheduleOption":
				int starttime5 = new Integer(referenceStep);
				String Starttime6 = getTextMap.get(Integer.valueOf(starttime5));
				returnObj = com.zillion.qa.realappealmember.member
						.raProgramAdminScheduledOneOnOneRescheduleOption(driver,
								Starttime6);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "RAProgramAdminScheduledOneOnOneCancelOption":
				int starttime7 = new Integer(referenceStep);
				String Starttime8 = getTextMap.get(Integer.valueOf(starttime7));
				returnObj = com.zillion.qa.realappealmember.member
						.raProgramAdminScheduledOneOnOneCancelOption(driver,
								Starttime8);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "GetInvoiceBillingHistory":
				int refbil = new Integer(referenceStep);
				String refbil1 = getTextMap.get(Integer.valueOf(refbil));
				com.zillion.qa.programadmin.Clients.getInvoiceBillingHistory(
						driver, refbil1);

			case "GuerrillaMailVSPN":
				com.zillion.qa.practitioner.Patients.guerrillaMailVSPN(driver);
				break;
			}
			if(true){
				switch (action)
				{
				case "GetMemberLastOneOnOneSession30DaysAgo":
					returnObj = com.zillion.qa.opsadmin.Members
					.getMemberLastOneOnOneSession30DaysAgo(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "GetMemberWeightGainOfMoreThan7LBSIn1Day":
					returnObj = com.zillion.qa.opsadmin.Members
					.getMemberWeightGainOfMoreThan7LBSIn1Day(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "GetMemberBallonNotRemovedAfter7MonthsInsertionDate":
					returnObj = com.zillion.qa.opsadmin.Members
					.getMemberBallonNotRemovedAfter7MonthsInsertionDate(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "GetMemberRemovalDateNotYetScheduled":
					returnObj = com.zillion.qa.opsadmin.Members
					.getMemberRemovalDateNotYetScheduled(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "PracticePAABCLogin":
					com.zillion.qa.practitioner.Patients.practicePAABCLogin(driver);
					break;
				case "PracticePAABCLogout":
					com.zillion.qa.practitioner.Patients.practicePAABCLogout(driver);
					break;
				case "SelectNextDateTimeSlot":
					com.zillion.qa.member.liveSessionSubCommonMethods
					.selectNextDateTimeSlot(driver);
					break;
				case "ProceedButtonttoInvoice":
					com.zillion.qa.practitioner.Patients
					.proceedbuttonttoinvoice(driver);
					break;
				case "GetMemberConfigurableColumnStartingWeightandWeightchange":
					String[] referenceSteps141 = StringUtils.split(referenceStep, ",");
					int refStep381 = new Integer(referenceSteps141[0]);
					int refStep382 = new Integer(referenceSteps141[1]);
					int refStep383 = new Integer(referenceSteps141[2]);
					int refStep384 = new Integer(referenceSteps141[3]);
					String WeightLoss = getTextMap.get(Integer.valueOf(refStep381));
					String StartingWeight = getTextMap.get(Integer.valueOf(refStep382));
					String LastSessionType = getTextMap
							.get(Integer.valueOf(refStep383));
					String LastSessionDate = getTextMap
							.get(Integer.valueOf(refStep384));
					returnObj = com.zillion.qa.opsadmin.Members
							.getMemberConfigurableColumnStartingWeightandWeightchange(
									driver, WeightLoss, StartingWeight,
									LastSessionType, LastSessionDate);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "GetMemberWhoHaveStartingWeight":
					returnObj = com.zillion.qa.opsadmin.Members
					.getMemberWhoHaveStartingWeight(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "ProgramAdminLoginKidney":
					com.zillion.qa.programadmin.Dashboard
					.programAdminLoginKidney(driver);
					break;
				case "KidneyMemberLogin":
					com.zillion.qa.member.Dashboard.kidneyMemberLogin(driver);
					break;
				case "GetCoachFor1on1":
					returnObj = com.zillion.qa.member.liveSessionSubCommonMethods
					.getCoachfor1on1(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "Select1on1forMember":
					int memberName = new Integer(referenceStep);
					String memberNamefor1on1 = getTextMap.get(Integer
							.valueOf(memberName));
					com.zillion.qa.member.liveSessionSubCommonMethods
					.select1on1ForMember(driver, memberNamefor1on1);
					break;
				case "GetMemberWhoHavePlacementDate":
					returnObj = com.zillion.qa.opsadmin.Members
					.getMemberWhoHavePlacementDate(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "GetMemberConfigurableColumnCoachandPlacementDate":
					String[] referenceSteps142 = StringUtils.split(referenceStep, ",");
					int refStep391 = new Integer(referenceSteps142[0]);
					int refStep392 = new Integer(referenceSteps142[1]);
					String Coach = getTextMap.get(Integer.valueOf(refStep391));
					String Placementdate = getTextMap.get(Integer.valueOf(refStep392));
					returnObj = com.zillion.qa.opsadmin.Members
							.getMemberConfigurableColumnCoachandPlacementDate(
									driver, Coach, Placementdate);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "OrberaMemberGearSettings":
					com.zillion.qa.member.liveSessionSubCommonMethods.memberGearSettings( driver );
					break;
				case "GetOrberaCoach1on1SessionFromMember":
					returnObj =com.zillion.qa.member.liveSessionSubCommonMethods.getOrberaCoach1on1SessionFromMember(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "CoachAppendTextToURL":
					com.zillion.qa.member.Dashboard.coachAppendTextToURL(driver);
					break;
				case "GetRAMemberForVerifyingConfigurableColumn":
					returnObj = com.zillion.qa.realappealprogramadmin.programadmin
					.getRAMemberForVerifyingConfigurableColumn(driver,inputData);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "GetRACoachVerifyingConfigurableColumn":
					int Coach_Name1 = new Integer(referenceStep);
					String Coach_Name= getTextMap.get(Integer.valueOf(Coach_Name1));
					returnObj = com.zillion.qa.realappealprogramadmin.programadmin
							.getRACoachVerifyingConfigurableColumn(driver,Coach_Name);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "GetRAMemberConfigurableColumnClassroomandCoachname":
					String[] referenceSteps161 = StringUtils.split(referenceStep, ",");
					int refStep481 = new Integer(referenceSteps161[0]);
					int refStep482 = new Integer(referenceSteps161[1]);
					int refStep483 = new Integer(referenceSteps161[2]);
					int refStep484 = new Integer(referenceSteps161[3]);
					String ClassroomName = getTextMap.get(Integer.valueOf(refStep481));
					String Coachname = getTextMap.get(Integer.valueOf(refStep482));
					String Onboardingstatus = getTextMap.get(Integer.valueOf(refStep483));
					String MemberID = getTextMap.get(Integer.valueOf(refStep484));
					returnObj =  com.zillion.qa.realappealprogramadmin.programadmin.getRAMemberConfigurableColumnClassroomandCoachname(driver, ClassroomName, Coachname,Onboardingstatus,MemberID);
					break;
				case "GetPractitionerMemberLastOneOnOneSession30DaysAgo":
					returnObj =  com.zillion.qa.practitioner.Patients.getPractitionerMemberLastOneOnOneSession30DaysAgo(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "GetPractitionerMemberWeightGainOfMoreThan7LBSIn1Day":
					returnObj =  com.zillion.qa.practitioner.Patients
					.getPractitionerMemberWeightGainOfMoreThan7LBSIn1Day(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "GetPractitionerMemberBallonNotRemovedAfter7MonthsInsertionDate":
					returnObj =  com.zillion.qa.practitioner.Patients.getPractitionerMemberBallonNotRemovedAfter7MonthsInsertionDate(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "GetPractitionerMemberRemovalDateNotYetScheduled":
					returnObj =  com.zillion.qa.practitioner.Patients
					.getPractitionerMemberRemovalDateNotYetScheduled(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "ProgramAdminKidneyLogout":
					com.zillion.qa.programadmin.Dashboard.programAdminKidneyLogout(driver);
					break;
				case "KidneyMemberLogout":
					com.zillion.qa.member.Dashboard.kidneyMemberLogout(driver);
					break;
				case "VSPNPAAddNewPatient":
					returnObj =  com.zillion.qa.vspnprogramadmin
					.programadmin.vspnPAAddNewPatient(driver, inputData);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "VSPNGuerrillaMail":
					com.zillion.qa.vspnprogramadmin.programadmin.vspnguerrillaMail(driver);
					break;
				case "VSPNGuerrillaMailRegistrationCompleteLink":
					com.zillion.qa.vspnprogramadmin.programadmin.vspnGuerrillaMailRegistrationCompleteLink(driver);
					break;
				case "AllowPluginsForSameBrowser":
					com.zillion.qa.member.liveSessionSubCommonMethods
					.allowPluginsForSameBrowser(driver);
					break;
				case "GetVSPNLiverMemberWithNoProvider":
					returnObj = com.zillion.qa.opsadmin.Members
					.getVSPNLiverMemberWithNoProvider(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "GetVSPNKidneyMemberWithNoProvider":
					returnObj = com.zillion.qa.opsadmin.Members
					.getVSPNKidneyMemberWithNoProvider(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "VSPN_TimeslotCoachNmaespliting_string":
					int refStep01 = new Integer(referenceStep);
					String coachname = getTextMap.get(Integer.valueOf(refStep01));
					String[] coachname1 = coachname.split("\\s+");
					String coachname2 = coachname1[3] + " " + coachname1[4];
					System.out.println(coachname2);
					returnObj = coachname2;
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "OrberFeaturesSupportAlert":
					com.zillion.qa.member.liveSessionSubCommonMethods
					.orberFeaturesSupportAlert(driver);
					break;
				case "LectureSessionCoachMemberTileMicEnableSameBrowser":
					com.zillion.qa.member.liveSessionSubCommonMethods
					.lectureSessionCoachMemberTileMicEnableSameBrowser(driver,inputData);
					break;
				case "GetCurrentURLForSessionID":
					returnObj = com.zillion.qa.member.liveSessionSubCommonMethods.getCurrentURL( driver );
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "VerifyMemberTileEnableOrDisable":
					returnObj = com.zillion.qa.member.liveSessionSubCommonMethods.verifyMemberTileEnableOrDisable( driver, inputData );
					break;
				case "PracticePAscheduledOneOnOneStartTime":
					int startEnd1 = new Integer(referenceStep);
					String StartTime1 = getTextMap.get(Integer.valueOf(startEnd1));
					returnObj = com.zillion.qa.member.Dashboard.PracticePAscheduledOneOnOneStartTime(driver,StartTime1);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "PracticePAscheduledOneOnOneEndTime":
					int startEnd2 = new Integer(referenceStep);
					String EndTime1 = getTextMap.get(Integer.valueOf(startEnd2));
					returnObj = com.zillion.qa.member.Dashboard.PracticePAscheduledOneOnOneEndTime(driver,EndTime1);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "TypeScheduledTimeAsUnavailableStartTime":
					int scheduleTime1 = new Integer(referenceStep);
					String ScheduleTime2 = getTextMap.get(Integer.valueOf(scheduleTime1));
					returnObj = com.zillion.qa.member.Dashboard.typeScheduledTimeAsUnavailableStartTime(driver, ScheduleTime2);
					break;
				case "TypeScheduledTimeAsUnavailableEndTime":
					int scheduleTime2 = new Integer(referenceStep);
					String ScheduleTime3 = getTextMap.get(Integer.valueOf(scheduleTime2));
					returnObj = com.zillion.qa.member.Dashboard.typeScheduledTimeAsUnavailableEndTime(driver, ScheduleTime3);
					break;
				case "VerifyPatienJourney":
					int email2 = new Integer(referenceStep);
					String email = getTextMap.get(Integer.valueOf(email2));
					com.zillion.qa.vspnprogramadmin.programadmin.verifypatienjourney(driver, inputData, email);
					break;
				case "Verify1on1Email":
					int email3 = new Integer(referenceStep);
					String email4 = getTextMap.get(Integer.valueOf(email3));
					com.zillion.qa.vspnprogramadmin.programadmin.verify1on1email(driver, inputData, email4);
					break;
				case "ScreenShotForMemberLiveSession":
					returnObj = com.zillion.qa.member.liveSessionSubCommonMethods.screenShotForMemberLiveSession( driver );
					break;
				case "MemberTileBrowser":
					returnObj = com.zillion.qa.member.liveSessionSubCommonMethods.memberTileBrowser( driver );
					break;
				case "BrowserSupportMatrixRA":
					//returnObj = com.zillion.qa.realappealmember.member.browserSupportMatrixRA( driver );
					break;
				case "VSPNViewEducationFormats":
					com.zillion.qa.member.Library.vspnViewEducationalFormats(driver);
					break;
				case "RAEnterWeightDuringLiveSession":
					com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raEnterWeightDuringLiveSession( driver );
					break;
				case "RaAppealGroupLiveSessionCoachLoginSameBrowser":
					int classCoach1 = new Integer(referenceStep);
					String classCoach = getTextMap.get(Integer.valueOf(classCoach1));
					returnObj = com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
							.raAppealGroupLiveSessionCoachLoginSameBrowser(driver, classCoach);
					break;
				case "ClassesRetrieveAvailableMember":
					returnObj= com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.classesRetrieveAvailableMember(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "RealAppealGroupLiveSessionMemberLoginSameBrowser":
					int email21 = new Integer(referenceStep);
					String email22 = getTextMap.get(Integer.valueOf(email21));
					returnObj= com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.realAppealGroupLiveSessionMemberLoginSameBrowser(driver,email22);
					break;
				case "RaMemberGroupSessionAppendURL":
					com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raMemberGroupSessionAppendURL(driver);
					break;
				case "RAMemberGroupSessionTime":
					returnObj=com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raMemberSessionTime(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "RaCoachUpcomingSessionTimeDifferentBrowser":
					com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raCoachUpcomingSessionTimeDifferentBrowser(driver);
					break;
				case "RaCoachUpcomingSessionTimeSameBrowser":
					int time21 = new Integer(referenceStep);
					String time22 = getTextMap.get(Integer.valueOf(time21));
					com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raCoachUpcomingSessionTimeSameBrowser(driver,time22);
					break;
				case "ShareSelectedDevice":
					com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
					.shareSelectedDevice(driver);
					break;
				case "RaGroupSessionMemberRaiseHandSameBrowser":
					com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
					.raGroupSessionMemberRaiseHandSameBrowser(driver, inputData);
					break;
				case "VerifyGroupSessionMemberRaiseHandSameBrowser":
					com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
					.verifyGroupSessionMemberRaiseHandSameBrowser(driver, inputData);
					break;
				case "VerifyScheduleOrCancel":
					if (referenceStep != null) {
						int mailschedule = new Integer(referenceStep);
						String mailschedule1 = getTextMap.get(Integer
								.valueOf(mailschedule));
						com.zillion.qa.member.Dashboard.verifyScheduleOrCancel(driver,
								mailschedule1);
					} else {
						com.zillion.qa.member.Dashboard.verifyScheduleOrCancel(driver,
								"dummyvalue");
					}
					break;
				case "RaGroupSessionCoachMemberTileMicEnableSameBrowser":
					com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
					.raGroupSessionCoachMemberTileMicEnableSameBrowser(driver,inputData);
					break;
				case "RaVerifyMemberTileEnableOrDisableSameBrowser":
					com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
					.raVerifyMemberTileEnableOrDisableSameBrowser(driver,inputData);
					break;
				case "LaunchVspnProviderURL":
					com.zillion.qa.vspnprogramadmin.programadmin
					.launchVspnProviderURL(driver);
					break;
				case "RetrieveVSPNMemberToSchedule1on1Session":
					returnObj = com.zillion.qa.vspnmember.member
					.retrieveVSPNMemberToSchedule1on1Session(driver);
					break;
				case "MemberSessionTime":
					returnObj = com.zillion.qa.vspnmember.member
					.memberSessionTime(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "VspnPAAttendNowButtonSameBrowser":
					int scheduleTime21 = new Integer(referenceStep);
					String ScheduleTime31 = getTextMap.get(Integer.valueOf(scheduleTime21));
					returnObj = com.zillion.qa.vspnmember.member
							.vspnPAAttendNowButtonSameBrowser(driver,ScheduleTime31);
					break;
				case "RetrieveHostIDForAllSessionWithProgramID":
					int refnumberpgmid3 = new Integer(referenceStep);
					String refnumberpgmid4 = getTextMap.get(Integer
							.valueOf(refnumberpgmid3));
					returnObj = com.zillion.qa.member.liveSessionSubCommonMethods
							.retrieveHostIDForAllSessionWithProgramID(driver, refnumberpgmid4);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "VSPNViewEducationalFormatsPdfDocument":
					com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.vspnViewEducationalFormatsPdfDocument(driver);
					break;
				case "VspnMemberVerifyLectureSession":
					int vspnLecture = new Integer(referenceStep);
					String verifyCreatedVSPN = getTextMap.get(Integer.valueOf(vspnLecture));
					returnObj = com.zillion.qa.vspnprogramadmin.programadmin
							.vspnMemberVerifyLectureSession(driver,verifyCreatedVSPN);
					break;
				case "RetrieveAvailableMemberEmailId":
					returnObj = com.zillion.qa.member.liveSessionSubCommonMethods
					.retrieveAvailableMemberEmailId(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "RetrieveAvailableMemberEmailIdWithID":
					String[] inputs = inputData.split(",");
					String email1 = inputs[0];
					String Status = inputs[1];
					String Environment = inputs[2];
					returnObj = com.zillion.qa.member.liveSessionSubCommonMethods
							.retrieveAvailableMemberEmailIdWithID(driver,email1,Status,Environment);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "RealAppealUpcomingsessionCoachLogin":
					if (inputData == null && referenceStep != null
					&& !referenceStep.trim().equals("")) {
						int coachEmail = new Integer(referenceStep);
						String coachEmail1 = getTextMap.get(Integer.valueOf(coachEmail));
						String[] coachEmail2 = coachEmail1.split(",");
						String coachEmail3 = coachEmail2[1];
						returnObj = com.zillion.qa.realappealprogramadmin.programadmin
								.realAppealUpcomingsessionCoachLogin(driver, coachEmail3);
						getTextMap.put(stepNo, returnObj.toString());
					}
					break;
				case "RetrieveCoachEmailForCustomSession":
					if ((inputData != null)) {
						System.out.println("Pass");
						returnObj = com.zillion.qa.realappealprogramadmin.programadmin
								.retrieveCoachMailForCustomSession(driver, inputData);
						if (returnObj.toString().equals(",")) {
							returnObj = "No Data in the Table";
							getTextMap.put(stepNo, returnObj.toString());
							driver.close();
						} else {
							getTextMap.put(stepNo, returnObj.toString());
						}
					}
					break;
				case "OrberaRemovalHandlePopup":
					com.zillion.qa.member.liveSessionSubCommonMethods.orberaRemovalHandlePopup(driver);
					break;
				case "RetrieveAvailableMemberEmailIdInTestAndKulfi":
					returnObj = com.zillion.qa.member.liveSessionSubCommonMethods
					.retrieveAvailableMemberEmailIdWithIDinBoth(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "RetrieveRAAvailableMemberEmail":
					returnObj = com.zillion.qa.realappealcoach.coach
					.retrieveRAAvailableMemberEmail(driver,inputData);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "RetrieveAssignedRACoachEmail":
					int memberEmail11 = new Integer(referenceStep);
					String memberEmail121 = getTextMap.get(Integer.valueOf(memberEmail11));
					returnObj = com.zillion.qa.realappealcoach.coach
							.retrieveAssignedRACoachEmail(driver,memberEmail121);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "RaGroupSessionQuizLink":
					com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raGroupSessionQuizLink( driver);
					break;
				case "RaGroupSessionContractLink":
					com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raGroupSessionContractLink( driver);
					break;
				case "RaGroupSessionGuideLink":
					com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raGroupSessionGuideLink( driver);
					break;
				case "RaGroupSessionScriptLink":
					com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.raGroupSessionScriptLink( driver);
					break;
				case "VerifyElementIsNotDisplayed":
					returnObj = Manipulation.verifyElementIsnotdisplayed(driver, normalXpath);
					break;
				case "CoachGroupSessionAppendUrlForSession6":
					com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.coachGroupAppendUrlForSession6( driver);
					break;
				case "RetrieveMemConfigUserName2":
					returnObj = com.zillion.qa.realappealmember.member
					.retrieveMemConfigUserName2(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "ChangeAddLiveToZLive":
					int LiveType1 = new Integer(referenceStep);
					String LiveType = getTextMap.get(Integer.valueOf(LiveType1));
					com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.changeAddLiveToZLive( driver,LiveType,inputData);
					break;
				case "HandleTrackerServerUnavailable":
					com.zillion.qa.realappealmember.member.handleTrackerServerUnavailable( driver);
					break;
				case "OpenRobotDefault":
					com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.openRobotDefault( driver);
					break;
				case "ScreenSharingInstallAddOn":
					com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
					.screenSharingInstallAddOn(driver);
					break;
				case "GetWindowToScreenShareAndInstall":
					com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
					.getWindowToScreenShareAndInstall(driver);
					break;
				case "SelectScreenForGroupSessionZLive":
					com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
					.selectScreenForGroupSessionZLive(driver);
					break;
				case "RealAppealActiveInactiveMember":
					returnObj = com.zillion.qa.realappealmember.member
					.realAppealActiveInactiveMember(driver,inputData);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "SortProgramWise":
					com.zillion.qa.member.liveSessionSubCommonMethods.sortProgramWise( driver, inputData);
					break;
				case "GetTrackerExerciseFavValue":
					int value = new Integer(referenceStep);
					String applicationvalue = getTextMap.get(Integer.valueOf(value));
					returnObj= com.zillion.qa.realappealmember.member
							.getTrackerExerciseFavValue(driver, applicationvalue);
					getTextMap.put(stepNo, returnObj.toString());
					break;

				case "GetTrackerExerciseFavTimeValue":
					int value1 = new Integer(referenceStep);
					String applicationvalue1 = getTextMap.get(Integer.valueOf(value1));
					returnObj= com.zillion.qa.realappealmember.member
							.getTrackerExerciseFavTimeValue(driver,applicationvalue1);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "RealAppealCoachClientSearch":
					com.zillion.qa.realappealprogramadmin.programadmin.realAppealCoachClientSearch( driver);
					break;
				case "RAFilterMemWithClient":
					com.zillion.qa.realappealmember.member.realAppealfilterMemWithClient(element);
					break;
				case "NavigateToMonday":
					com.zillion.qa.realappealmember.member.navigateToMonday(driver, inputData);
					break;
				case "PreferenceAgreenDisagreeButton":
					com.zillion.qa.realappealmember.member.preferenceAgreenDisagreeButton(driver);
					break;
				case "RACustomizationSessionVerifyScheduleOrChange":
					com.zillion.qa.realappealmember.member.verifyScheduleOrChange(driver);
					break;

				case "RetrieveSecurityInfoOfTheMember":
					int DbEmail = new Integer(referenceStep);
					String DbEmailValue = getTextMap.get(Integer.valueOf(DbEmail));
					returnObj = com.zillion.qa.opsadmin.Members
							.retrieveSecurityInfoOfTheMember(driver,inputData,DbEmailValue);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "ChangeClassRoom":
					int dbProgramInterval = new Integer(referenceStep);
					String dbProgramIntervalValue = getTextMap.get(Integer.valueOf(dbProgramInterval));
					com.zillion.qa.realappealprogramadmin.programadmin.changeClassRoom(driver,dbProgramIntervalValue);
					break;
				case "HandlePHIPopup":
					com.zillion.qa.realappealmember.member
					.handlePHIPopup(driver);
					break;
				case "RetrieveMissedRACustomizationEmail":
					returnObj =  com.zillion.qa.realappealmember.member
					.retrieveMissedRACustomizationMemberEmail(driver,inputData);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "RetrieveAvailableCustomizationMemberEmailIdWithAccountID":
					int accountid = new Integer(referenceStep);
					String accountid1 = getTextMap.get(Integer.valueOf(accountid));
					returnObj = com.zillion.qa.realappealmember.member
							.retrieveAvailableMemberEmailIdWithAccountID(driver,
									accountid1);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "RAGroupZLiveSession":
					if (inputData != null && referenceStep != null)
					{
						String[] referenceSteps = StringUtils.split(referenceStep, ",");
						int coachEmail = new Integer(referenceSteps[0]);
						int memberEmail = new Integer(referenceSteps[1]);
						String coachEmail1 = getTextMap.get(Integer.valueOf(coachEmail));
						String memberEmail1 = getTextMap.get(Integer.valueOf(memberEmail));
						com.zillion.qa.realappealmember.RAGroupLiveSession.raGroupZLiveSession(driver,
								inputData, coachEmail1,memberEmail1);
					}
					break;
				case "VerifyQaSecurityEmail":
					int mailref = new Integer(referenceStep);
					String mailinput = getTextMap.get(Integer.valueOf(mailref));
					com.zillion.qa.realappealmember.member.verifyQaSecurityEmail(
							driver, mailinput);
					break;
				case "GetTimeInUpcomingSessionsSecForCustomization":
					returnObj = com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.getTimeInUpcomingSessionsSecForCustomization(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "ClickOnCustomizationsSessionTime":
					int out1 = new Integer(referenceStep);
					String time = getTextMap.get(Integer.valueOf(out1));
					com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.clickOnCustomizationsSessionTime(driver, time);
					break;
				case "RetrieveAvailable1on1SessionScheduleMember":
					returnObj = com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
					.retrieveAvailable1on1SessionScheduleMember(driver);
					if (returnObj.toString().equals("")) {
						returnObj = "No Data in the Table";
						getTextMap.put(stepNo, returnObj.toString());
						driver.close();
					} else {
						getTextMap.put(stepNo, returnObj.toString());
					}

					break;
				case "RetrieveMemberAccountIdFromEmail":
					int accountidref = new Integer(referenceStep);
					String accountidref1 = getTextMap.get(Integer
							.valueOf(accountidref));
					System.out.println("email id is :" +accountidref1);
					returnObj = com.zillion.qa.member.liveSessionSubCommonMethods
							.retrieveMemberAccountIdFromEmail(driver, accountidref1);
					getTextMap.put(stepNo, returnObj.toString());

					break;
				case "Verify1on1Session":
					int retrievedmemname = new Integer(referenceStep);
					String retrievedmemname1 = getTextMap.get(Integer
							.valueOf(retrievedmemname));
					com.zillion.qa.opsadmin.Classes
					.verify1on1Session(driver,retrievedmemname1);
					break;
				case "MDACJiraLogin":
					com.zillion.qa.realappealmember.member.mdacJiraLogin(driver);
					break;
				case "MDACJiraLogout":
					//com.zillion.qa.realappealmember.member.mdacJiraLogout(driver);
					break;
				case "RetrieveIdealMemberFromDb":
					returnObj = com.zillion.qa.realappealmember.member.retrieveIdealMemberFromDb(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "SecurityAnswerForParticularMember":
					int IdealMemberEmail = new Integer(referenceStep);
					String IdealMemberemail = getTextMap.get(Integer
							.valueOf(IdealMemberEmail));
					returnObj = com.zillion.qa.realappealmember.member.securityAnswerForParticularMember(driver, IdealMemberemail);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "RA1on1ZLiveSession":
					if (inputData != null && referenceStep != null)
					{
						String[] referenceSteps = StringUtils.split(referenceStep, ",");
						int coachEmail = new Integer(referenceSteps[0]);
						int memberEmail = new Integer(referenceSteps[1]);
						String coachEmail1 = getTextMap.get(Integer.valueOf(coachEmail));
						String memberEmail1 = getTextMap.get(Integer.valueOf(memberEmail));
						com.zillion.qa.realappealmember.RAOneOnOneLiveSession.ra1on1ZLiveSession(driver,
								inputData, coachEmail1,memberEmail1);
					}
					break;
				case "RealAppealRecepientMemberName":
					int name = new Integer(referenceStep);
					String RecepientName = getTextMap.get(Integer.valueOf(name));
					returnObj = com.zillion.qa.realappealmember.member
							.realAppealRecepientMemberName(driver, RecepientName);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "VerifyUserSession":
					com.zillion.qa.realappealcoach.coach.verifyUserSession(driver);
					break;
				case "UpdatingLastLoginDate":
					String Member_Id = getTextMap.get(Integer.valueOf(referenceStep));
					returnObj = com.zillion.qa.realappealmember.member.updatingLastLoginDate(driver,Member_Id);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "HourAndMinute":
					returnObj = com.zillion.qa.realappealmember.member.hourAndMinute(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "EnterHourInEpoch":
					if (inputData == null && referenceStep != null
					&& !referenceStep.trim().equals("")) {
						int Hour1 = new Integer(referenceStep);
						String Hour2 = getTextMap.get(Integer.valueOf(Hour1));
						String[] Hour = Hour2.split(",");
						String hour = Hour[0];
						System.out.println(hour);
						returnObj = com.zillion.qa.realappealmember.member.enterHourInEpoch(driver, hour);
						getTextMap.put(stepNo, returnObj.toString());
					}
					break;
				case "EnterMinuteInEpoch":
					if (inputData == null && referenceStep != null
					&& !referenceStep.trim().equals("")) {
						int Hour1 = new Integer(referenceStep);
						String Hour2 = getTextMap.get(Integer.valueOf(Hour1));
						String[] Hour = Hour2.split(",");
						String minute = Hour[1];
						System.out.println(minute);
						returnObj = com.zillion.qa.realappealmember.member.enterMinuteInEpoch(driver, minute);
						getTextMap.put(stepNo, returnObj.toString());
					}
					break;
				case "UpdateQRTZTableForIdle":
					if (referenceStep != null) {
						int Idle = new Integer(referenceStep);
						String Idle1 = getTextMap.get(Integer.valueOf(Idle));
						String Idle2[]=Idle1.split(":");
						System.out.println("Idle2[1]"+Idle2[1]);
						returnObj = com.zillion.qa.realappealmember.member
								.updateQRTZTableForIdle(driver, Idle2[1]);
						getTextMap.put(stepNo, returnObj.toString());
					}
					break;
				case "RA1on1AddLiveSession":
					if (inputData != null && referenceStep != null)
					{
						String[] referenceSteps = StringUtils.split(referenceStep, ",");
						int coachEmail = new Integer(referenceSteps[0]);
						int memberEmail = new Integer(referenceSteps[1]);
						String coachEmail1 = getTextMap.get(Integer.valueOf(coachEmail));
						String memberEmail1 = getTextMap.get(Integer.valueOf(memberEmail));
						com.zillion.qa.realappealmember.RAOneOnOneLiveSession.ra1on1AddLiveSession(driver,
								inputData, coachEmail1,memberEmail1);
					}
					break;
				case "ProviderChangeProfilePic":
					com.zillion.qa.realappealcoach.coach.providerChangeProfilePic(driver,inputData);
					break;

				case "RetrieveMDACProcessStatus":
					returnObj =  com.zillion.qa.realappealcoach.coach.retrieveMDACProcessStatus(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "GuerrillaMailWelcomeToRALink":
					int EmailValue1 = new Integer(referenceStep);
					String EmailValue = getTextMap.get(Integer.valueOf(EmailValue1));
					com.zillion.qa.realappealmember.member.
					guerrillaMailWelcomeToRALink(driver,EmailValue);
					break;

				case "Orbera1on1AddLiveSession":
					if (inputData != null && referenceStep != null)
					{
						String[] referenceSteps = StringUtils.split(referenceStep, ",");
						int coachEmail = new Integer(referenceSteps[0]);
						int memberEmail = new Integer(referenceSteps[1]);
						String coachEmail1 = getTextMap.get(Integer.valueOf(coachEmail));
						String memberEmail1 = getTextMap.get(Integer.valueOf(memberEmail));
						com.zillion.qa.member.LiveSession.orbera1on1AddLiveSession(driver,
								inputData, coachEmail1,memberEmail1);
					}
					break;
				case "RetrieveMemeberMissed1on1Session":
					returnObj = com.zillion.qa.realappealmember.member.retrieveMemeberMissed1on1Session(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;

				case "RealAppealCoachMDACResponseText":
					int text = new Integer(referenceStep);
					String ResponseText = getTextMap.get(Integer.valueOf(text));
					returnObj = com.zillion.qa.realappealcoach.coach
							.realAppealCoachMDACResponseText(driver, ResponseText);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "Orbera1on1ZLiveSession":
					if (inputData != null && referenceStep != null)
					{
						String[] referenceSteps = StringUtils.split(referenceStep, ",");
						int coachEmail = new Integer(referenceSteps[0]);
						int memberEmail = new Integer(referenceSteps[1]);
						String coachEmail1 = getTextMap.get(Integer.valueOf(coachEmail));
						String memberEmail1 = getTextMap.get(Integer.valueOf(memberEmail));
						com.zillion.qa.member.LiveSession.orbera1on1ZLiveSession(driver,
								inputData, coachEmail1,memberEmail1);
					}
					break;
				case "OrberaChangeAddLiveToZLive":
					int LiveType2 = new Integer(referenceStep);
					String LiveType3 = getTextMap.get(Integer.valueOf(LiveType2));
					com.zillion.qa.member.liveSessionSubCommonMethods.orberaChangeAddLiveToZLive( driver,LiveType3,inputData);
					break;
				case "RealAppealCoachMemberSearchMemberEmail":
					if (inputData == null && referenceStep != null
					&& !referenceStep.trim().equals("")) {
						int memEmail = new Integer(referenceStep);
						String memEmail1 = getTextMap.get(Integer.valueOf(memEmail));
						String[] memEmail2 = memEmail1.split(",");
						String memEmail3 = memEmail2[0];
						returnObj = memEmail3;
						getTextMap.put(stepNo, returnObj.toString());
					}
					break;
				case "RetrieveAvailableScheduleMemberMessage":
					if (inputData != null) {
						returnObj = com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
								.retrieveAvailableScheduleMemberMessage(driver, inputData);
						if (returnObj.toString().equals("")) {
							returnObj = "No Data in the Table";
							getTextMap.put(stepNo, returnObj.toString());
							driver.close();
						} else {
							getTextMap.put(stepNo, returnObj.toString());
						}
					}
					break;
				case "SplitMemberNameFromEmail":
					int Name = new Integer(referenceStep);
					String Name1 = getTextMap.get(Integer.valueOf(Name));
					String[] Name2 = Name1.split(" ");
					System.out.println(Name2[0]);
					System.out.println(Name2[1]);
					Name2[1] = Name2[1].replaceAll(",", "");
					System.out.println("After Replace" +Name2[1]);
					returnObj =Name2[1];
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "MemberFirstNameFromDB":
					int Email = new Integer(referenceStep);
					String Email1 = getTextMap.get(Integer.valueOf(Email));
					returnObj = com.zillion.qa.realappealmember.member.MemberFirstNameFromDB(driver,Email1);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "ChangeAddLiveOrZLiveLectureLiveSession":
					returnObj = com.zillion.qa.member.liveSessionSubCommonMethods.changeAddLiveOrZLiveLectureLiveSession( driver,inputData);
					break;
				case "RetrieveMemberDeclinedStatus":
					returnObj = com.zillion.qa.realappealmember.member.retrieveMemberDeclinedStatus(driver,inputData);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "StoreNewCustomizationMember":
					int NewCustomizationMember = new Integer(referenceStep);
					String NewCustomizationMember1 = getTextMap.get(Integer
							.valueOf(NewCustomizationMember));
					com.zillion.qa.realappealmember.member
					.storeNewCustomizationMember(driver,
							NewCustomizationMember1);
					break;
				case "RetrieveStoredCustomizatoinMember":
					returnObj = com.zillion.qa.realappealmember.member
					.retrieveStoredCustomizatoinMember(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "RealAppealCoachMemberSearchCoachEmail":
					if (inputData == null && referenceStep != null
					&& !referenceStep.trim().equals("")) {
						int memEmail = new Integer(referenceStep);
						String memEmail1 = getTextMap.get(Integer.valueOf(memEmail));
						String[] memEmail2 = memEmail1.split(",");
						String memEmail3 = memEmail2[1];
						returnObj = memEmail3;
						getTextMap.put(stepNo, returnObj.toString());
					}
					break;

				case "RAVerifyUnreadmessages":
					com.zillion.qa.realappealprogramadmin.programadmin.verifyUnreadmessages(driver);
					break;
				case "EmailSplit":
					if (inputData == null && referenceStep != null
					&& !referenceStep.trim().equals("")) {
						String reference = getTextMap.get(Integer.valueOf(referenceStep));
						String name1[]=reference.split(":\\s");
						returnObj = name1[2];
					}
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "RetrieveMemeberMissedGroupSession":
					returnObj = com.zillion.qa.realappealmember.member.retrieveMemeberMissedGroupSession(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;

				case "RetrieveIdealMemberFromDbOnboardingStatus":
					returnObj = com.zillion.qa.realappealmember.member.retrieveIdealMemberFromDbOnboardingStatus(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "ChangeClassRoomWithIntreval":
					com.zillion.qa.realappealprogramadmin.programadmin.changeClassRoomWithIntreval(driver);

				case "DeleteAllSavedFood":
					com.zillion.qa.realappealmember.member
					.deleteAllSavedFood(driver);

					break;
				case "TermsAndConditionPopupHandle":
					com.zillion.qa.realappealmember.member.termsAndConditionPopupHandle( driver );
					break;
				case "PracticePAURL":
					com.zillion.qa.programadmin.Dashboard.practicePAURL(driver);
					break;
				case "MemberOneOnOneSessionTime":
					returnObj=com.zillion.qa.member.liveSessionSubCommonMethods.memberOneOnOneSessionTime(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "AttendMemberOneOnOneSessionTime":
					int sessTime = new Integer(referenceStep);
					String sessTime1 = getTextMap.get(Integer.valueOf(sessTime));
					com.zillion.qa.member.liveSessionSubCommonMethods.attendMemberOneOnOneSessionTime(driver,sessTime1);
					break;
				case "VerifyPdfInDifferentBrowsers":
					com.zillion.qa.realappealprogramadmin.programadmin.verifyPdfInDifferentBrowsers(driver);
					break;
				case "GetWindowfitnessnutritionguideAssertDetails":
					com.zillion.qa.realappealprogramadmin.programadmin.getWindowfitnessnutritionguideAssertDetails(driver, normalXpath);
					break;
				case "GetWindowGuidAssertDetail":
					com.zillion.qa.realappealprogramadmin.programadmin.getWindowGuidAssertDetail(driver, normalXpath);
					break;
				case "TermsAndConditionPopupHandleDisagreeButton":
					returnObj = com.zillion.qa.realappealmember.member.termsAndConditionPopupHandleDisagreeButton( driver );
					break;
				case "OrberaPA1on1ZLiveSession":
					if (inputData != null && referenceStep != null)
					{
						String[] referenceSteps = StringUtils.split(referenceStep, ",");
						int coachEmail = new Integer(referenceSteps[0]);
						int memberEmail = new Integer(referenceSteps[1]);
						String coachEmail1 = getTextMap.get(Integer.valueOf(coachEmail));
						String memberEmail1 = getTextMap.get(Integer.valueOf(memberEmail));
						com.zillion.qa.member.LiveSession.orberaPA1on1ZLiveSession(driver,
								inputData, coachEmail1,memberEmail1);
					}
					break;
				case "RetrieveMemConfigUserNamePwdReset":
					returnObj = com.zillion.qa.realappealmember.member
					.retrieveMemConfigUserNamePwdReset(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;

				case "RetrieveMemConfigUserNameEmailChange":
					returnObj = com.zillion.qa.realappealmember.member
					.retrieveMemConfigUserNameEmailChange(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "VerifyMemberEmailChangeNotification":
					int mailidref = new Integer(referenceStep);
					String mailidtext = getTextMap.get(Integer.valueOf(mailidref));
					com.zillion.qa.realappealmember.member.verifyMemberEmailChangeNotification(
							driver, mailidtext);
					break;
				case "RetrieveMemberWhoseGroupSessionIsInProgress":
					returnObj = com.zillion.qa.realappealcoach.coach
					.retrieveMemberWhoseGroupSessionIsInProgress(driver,inputData);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "VerifyMemberPasswordChangeNotification":
					int mailidref1 = new Integer(referenceStep);
					String mailidtext2 = getTextMap.get(Integer.valueOf(mailidref1));
					com.zillion.qa.realappealmember.member.verifyMemberPasswordChangeNotification(driver, mailidtext2);
					break;
				case "AvailableSessionTime":
					returnObj = com.zillion.qa.member.Dashboard.availableSessionTime(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "CoachAvailabilityCurrentDate":
					com.zillion.qa.opsadmin.Classes.coachAvailabilityCurrentDate(driver);
					break;
				case "DeclinePHIPopup":
					com.zillion.qa.realappealmember.member
					.declinePHIPopup(driver);
					break;
				case "RetrieveMemberWhoseHasMissedGroupSession":
					returnObj = com.zillion.qa.realappealcoach.coach
					.retrieveMemberWhoseHasMissedGroupSession(driver,inputData);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "VerifyNotificationEmailCoachOrProgramAdminToMemberOrClass":
					int mailcoachtomember = new Integer(referenceStep);
					String mailidcoachtomember = getTextMap.get(Integer.valueOf(mailcoachtomember));
					returnObj = com.zillion.qa.realappealmember.member.verifyNotificationMailCoachOrProgramAdminSendsToMemberOrClass(
							driver, mailidcoachtomember);
					break;
				case "RetrieveMemberAccountPgmIDWhoseHasMissedGroupSession":
					returnObj = com.zillion.qa.realappealmember.Claims
					.retrieveMemberAccountPgmIDWhoseHasMissedGroupSessionAndScheduled1ON1Session(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "RetrieveMemberAccountIDWhoseHasMissedGroupSession":
					int refStepForAccountid = new Integer(referenceStep);
					String AccountIdText = getTextMap.get(Integer
							.valueOf(refStepForAccountid));
					returnObj = com.zillion.qa.realappealmember.Claims
							.retrieveMemberAccountIDWhoseHasMissedGroupSessionAndScheduled1ON1Session(driver,AccountIdText);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "RetrieveMemberEmailWhoseHasMissedGroupSessionAndScheduled1ON1Session":
					int refStepForEmail = new Integer(referenceStep);
					String MemberEmail = getTextMap.get(Integer
							.valueOf(refStepForEmail));
					returnObj = com.zillion.qa.realappealmember.Claims
							.retrieveMemberEmailWhoseHasMissedGroupSessionAndScheduled1ON1Session(driver,MemberEmail);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "EnterClient2RAFirstNameOnInsurancePopUp":
					com.zillion.qa.realappealmember.member
					.enterClient2RAFirstNameOnInsurancePopUp(driver);
					break;
				case "EnterClient2RALastNameOnInsurancePopUp":
					com.zillion.qa.realappealmember.member
					.enterClient2RALastNameOnInsurancePopUp(driver);
					break;
				case "AssignSubtituteCoachForScheduledMakeupSession":
					int starttime4 = new Integer(referenceStep);
					String Starttime5 = getTextMap.get(Integer.valueOf(starttime4));
					returnObj = com.zillion.qa.realappealmember.member
							.assignSubtituteCoachForScheduledMakeupSession(driver, Starttime5);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "VerifyMemberMailCSSessionCancellation":
					int mailidref12 = new Integer(referenceStep);
					String mailidtext12 = getTextMap.get(Integer.valueOf(mailidref12));
					com.zillion.qa.realappealmember.member
					.verifyMemberMailCSSessionCancellation(driver,
							mailidtext12);
					break;
				case "VerifyAssignedCoachShouldNotBeAllowedToSubstituteForScheduledMakeupSession":
					int starttime5 = new Integer(referenceStep);
					String Starttime6 = getTextMap.get(Integer.valueOf(starttime5));
					returnObj = com.zillion.qa.realappealmember.member
							.verifyAssignedCoachShouldNotBeAllowedToSubstituteForScheduledMakeupSession(driver, Starttime6);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "IdleDetection":
					returnObj =com.zillion.qa.realappealmember.member
					.idleDetection(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "TurnOffIdleDetection":
					com.zillion.qa.realappealmember.member
					.turnOffIdleDetection(driver);
					break;
				case "TurnOnIdleDetection":
					com.zillion.qa.realappealmember.member
					.turnOnIdleDetection(driver);
					break;
				case "ToGetActiveMemberId":
					if (inputData == null && referenceStep != null
					&& !referenceStep.trim().equals(""))
					{
						int emailwithid = new Integer(referenceStep);
						String emailwithid1 = getTextMap.get(Integer.valueOf(emailwithid));
						returnObj =com.zillion.qa.realappealmember.member.toGetActiveMemberid(driver , emailwithid1);
						getTextMap.put(stepNo, returnObj.toString());
					}
					else if (inputData != null && referenceStep == null
							&& !inputData.trim().equals(""))
					{

						returnObj =com.zillion.qa.realappealmember.member.toGetActiveMemberid(driver , inputData);
						getTextMap.put(stepNo, returnObj.toString());
					}
					break;
				case "ToFindLastLoginDate":
					int MemberId = new Integer(referenceStep);
					String MemberId1 = getTextMap.get(Integer.valueOf(MemberId));
					returnObj =com.zillion.qa.realappealmember.member
							.toFindLastLoginDate(driver,MemberId1);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "ToChangeLoginDatebydaysBehind":
					if (inputData != null && referenceStep != null)
					{
						int id1 = new Integer(referenceStep);
						String ID2 = getTextMap.get(Integer.valueOf(id1));
						System.out.println("Days"+inputData+": id1 "+ID2 );
						com.zillion.qa.realappealmember.member
						.ToChangeLoginDatebydaysBehind(driver,ID2,inputData);
					}
					break;
				case "ActiveMemberEmail":
					int emailId = new Integer(referenceStep);
					String activeEmail = getTextMap.get(Integer.valueOf(emailId));
					String activeEmail1[]  = activeEmail .split(",");
					returnObj = activeEmail1[1];
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "RetrieveMaximumMakeUpAttendees":
					returnObj =  com.zillion.qa.realappealcoach.coach.retrieveMaximumMakeUpAttendees(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "CheckIfTheMakeUpSessionIsConfiguredForRAGroupSession":
					returnObj =  com.zillion.qa.realappealcoach.coach.checkIfTheMakeUpSessionIsConfiguredForRAGroupSession(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "CheckIfTheMakeUpSessionIsNotConfiguredForAnySessionOtherThanRAGroupSession":
					returnObj =  com.zillion.qa.realappealcoach.coach.checkIfTheMakeUpSessionIsNotConfiguredForAnySessionOtherThanRAGroupSession(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "StoreIdleMember":
					int IdleMember2 = new Integer(referenceStep);
					String idlemember1 = getTextMap.get(Integer
							.valueOf(IdleMember2));
					com.zillion.qa.realappealmember.member
					.storeIdleMember(driver,
							idlemember1);
					break;
				case "MemberAcceptedorDeclinedPHIandClientDisabledPHI":
					returnObj = com.zillion.qa.realappealmember.member
					.memberAcceptedorDeclinedPHIandClientDisabledPHI(driver, inputData);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "ClaimsZLiveSessionForFirstInterval":
					int result = new Integer(referenceStep);
					String result1 = getTextMap.get(Integer.valueOf(result));
					String[] result2  = result1.split(",");
					String memberEmail1= result2[0];
					String coachEmail1= result2[1];
					System.out.println("Member Email :"+result2[0]);
					System.out.println("Coach Email :"+result2[1]);
					com.zillion.qa.realappealmember.Claims.claimsZLiveSessionForFirstInterval(driver,inputData,coachEmail1,memberEmail1);
					break;
				case "ClaimsZLiveSessionForMemberNotWeightLoss":
					int res = new Integer(referenceStep);
					String res1 = getTextMap.get(Integer.valueOf(res));
					String[] res2  = res1.split(",");
					String memberEmail12= res2[0];
					String coachEmail12= res2[1];
					System.out.println("Member Email :"+res2[0]);
					System.out.println("Coach Email :"+res2[1]);
					com.zillion.qa.realappealmember.Claims.claimsZLiveSessionForMemberNotWeightLoss(driver,inputData,coachEmail12,memberEmail12);
					break;
				case "RAGroupScheduleAndAttendMakeupSession":
					if (inputData != null && referenceStep != null)
					{
						String[] referenceSteps = StringUtils.split(referenceStep, ",");
						int coachEmail = new Integer(referenceSteps[0]);
						int memberEmail = new Integer(referenceSteps[1]);
						String coachEmail2 = getTextMap.get(Integer.valueOf(coachEmail));
						String memberEmail2 = getTextMap.get(Integer.valueOf(memberEmail));
						com.zillion.qa.realappealmember.FlexibleMakeupSession.raGroupScheduleAndAttendMakeupSession(driver,
								inputData, coachEmail2,memberEmail2);
					}
					break;
				case "VerifyMakeupSessionConfirmationEmail":
					int mailidref2 = new Integer(referenceStep);
					String mailidtext1 = getTextMap.get(Integer.valueOf(mailidref2));
					com.zillion.qa.realappealmember.member.verifyMakeupSessionConfirmationEmail(
							driver, mailidtext1);
					break;
				case "CurrentDate":
					returnObj = Manipulation
					.dynamickeys(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "RetrieveIdealMemberFromDbOnboardingStatusProgramInterval":
					if (inputData == null && referenceStep != null
					&& !referenceStep.trim().equals("")) {
						int Mid = new Integer(referenceStep);
						String ID = getTextMap.get(Integer.valueOf(Mid));
					returnObj = com.zillion.qa.realappealmember.member.retrieveIdealMemberFromDbOnboardingStatusProgramInterval(driver,ID);
					getTextMap.put(stepNo, returnObj.toString());
					}
					break;
				case "verifyIdleNotificationCoachEmail":
					int idlemailid = new Integer(referenceStep);
					String idleEmailid = getTextMap.get(Integer.valueOf(idlemailid));
					com.zillion.qa.realappealmember.member.verifyIdleNotificationCoachEmail(driver, idleEmailid);
					break;
				case "verifyIdleNotificationMemberEmail":
					int idleMembermailid = new Integer(referenceStep);
					String idleMemberEmailid = getTextMap.get(Integer.valueOf(idleMembermailid));
					com.zillion.qa.realappealmember.member.verifyIdleNotificationMemberEmail(driver, idleMemberEmailid);
					break;
				case "ToGetActiveMemberEmail":
					returnObj =com.zillion.qa.realappealmember.member
					.toGetActiveMemberemail(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "MakeupSessionIsCompletedAndWithDifferent1On1Status":
					if (inputData != null && referenceStep != null)
					{
						String[] referenceSteps = StringUtils.split(referenceStep, ",");
						int coachEmail = new Integer(referenceSteps[0]);
						int memberEmail = new Integer(referenceSteps[1]);
						String coachEmail2 = getTextMap.get(Integer.valueOf(coachEmail));
						String memberEmail2 = getTextMap.get(Integer.valueOf(memberEmail));
						com.zillion.qa.realappealmember.FlexibleMakeupSession.makeupSessionIsCompletedAndWithDifferent1ON1Status(driver,
								inputData, coachEmail2,memberEmail2);
					}
					break;
				case "ShowRatingDisabledForAllPage":
					com.zillion.qa.realappealprogramadmin.programadmin.showRatingDisabledForAllPage(driver);
					break;
				case "RetrieveActiveMemberFromDbOnboardingStatus":
					returnObj = com.zillion.qa.realappealmember.member.retrieveActiveMemberFromDbOnboardingStatus(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "RetrieveActiveMemberFromDbOnboardingStatusProgramInterval":
					returnObj = com.zillion.qa.realappealmember.member.retrieveActiveMemberFromDbOnboardingStatusProgramInterval(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "RetrieveMemOrgIDClient2":
					returnObj = com.zillion.qa.realappealmember.member
					.retrieveMemOrgIDClient2(
							driver, inputData);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "VerifyIdleMemberFirstAndSecondNotificationEmail":
					int idlemembermailid = new Integer(referenceStep);
					String idlememberEmailid = getTextMap.get(Integer.valueOf(idlemembermailid));
					com.zillion.qa.realappealmember.member.verifyIdleMemberFirstAndSecondNotificationEmail(driver, idlememberEmailid);
					break;
				case "MemberWhoCompletes1on1AndMissedGroupIn52ndInterval":
					if (inputData != null && referenceStep != null)
					{
						String[] referenceSteps = StringUtils.split(referenceStep, ",");
						int memberEmail = new Integer(referenceSteps[0]);
						int coachEmail = new Integer(referenceSteps[1]);
						String coachEmail2 = getTextMap.get(Integer.valueOf(coachEmail));
						String memberEmail2 = getTextMap.get(Integer.valueOf(memberEmail));
						com.zillion.qa.realappealmember.FlexibleMakeupSession.memberWhoCompletes1on1AndMissedGroupIn52ndInterval(driver,
								inputData, coachEmail2,memberEmail2);
					}
					break;

				case "RetrieveStoredFirstIdleMember":
					returnObj = com.zillion.qa.realappealmember.member
					.retrieveStoredFirstIdleMember(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "StoreIdleMember2":
					int IdleMember3 = new Integer(referenceStep);
					String idlemember2 = getTextMap.get(Integer
							.valueOf(IdleMember3));
					com.zillion.qa.realappealmember.member
					.storeIdleMember2(driver,
							idlemember2);
					break;
				case "RetrieveStoredSecondIdleMember":
					returnObj = com.zillion.qa.realappealmember.member
					.retrieveStoredSecondIdleMember(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "SelectClient1memberDOB":
					com.zillion.qa.opsadmin.Members.selectClient1memberDOB(driver);
					break;
				case "PreviousSessionPageCustomizationRecords":
					com.zillion.qa.realappealprogramadmin.programadmin.previousSessionPageCustomizationRecords(driver);
					break;
				case "MembersAttendTheGroupSessionAndSchedule1on1Session":
					if (inputData != null && referenceStep != null)
					{
						String[] referenceSteps = StringUtils.split(referenceStep, ",");
						int coachEmail = new Integer(referenceSteps[0]);
						int memberEmail = new Integer(referenceSteps[1]);
						String coachEmail2 = getTextMap.get(Integer.valueOf(coachEmail));
						String memberEmail2 = getTextMap.get(Integer.valueOf(memberEmail));
						com.zillion.qa.realappealmember.RAGroupLiveSession.membersAttendTheGroupSessionAndSchedule1on1Session(driver,
								inputData, coachEmail2,memberEmail2);
					}
					break;
				case "AllowPluginsForDifferentBrowserSupport":
					com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
					.allowPluginsForDifferentBrowserSupport(driver);
					break;

				case "TemporarySubstitutionOfASessionAttendMakeupSession":
					if (inputData != null && referenceStep != null)
					{
						String[] referenceSteps = StringUtils.split(referenceStep, ",");
						int coachEmail = new Integer(referenceSteps[0]);
						int memberEmail = new Integer(referenceSteps[1]);
						String coachEmail2 = getTextMap.get(Integer.valueOf(coachEmail));
						String memberEmail2 = getTextMap.get(Integer.valueOf(memberEmail));
						com.zillion.qa.realappealmember.FlexibleMakeupSession.temporarySubstitutionOfASessionAttendMakeupSession(driver,
								inputData, coachEmail2,memberEmail2);
					}
					break;
				case "IdleDetectionStatusChangeToOn":
					if (inputData == null && referenceStep != null) {
						int refStep6 = new Integer(referenceStep);
						String getText1 = getTextMap.get(Integer.valueOf(refStep6));
						int idleStatus = Integer.parseInt(getText1);
						System.out.println("getText1" + getText1);
						if(idleStatus !=1){
							com.zillion.qa.realappealmember.member
							.turnOnIdleDetection(driver);
						}
					}
					break;
				case "IdleDetectionStatusChangeToOFF":
					if (inputData == null && referenceStep != null) {
						int refStep16 = new Integer(referenceStep);
						String getText11 = getTextMap.get(Integer.valueOf(refStep16));
						int idleStatus1 = Integer.parseInt(getText11);
						System.out.println("getText1" + getText11);
						if(idleStatus1 !=0){
							com.zillion.qa.realappealmember.member
							.turnOffIdleDetection(driver);
						}
					}
					break;

				case "CreatePropertyNoOverlapForMakeupSession":
					com.zillion.qa.realappealmember.Claims.createPropertyNoOverlapForMakeupSession();

					break;
				case "CheckClaimsNoOverlapForMakeupSession":
					returnObj = com.zillion.qa.realappealmember.Claims
					.checkClaimsNoOverlapForMakeupSession(driver);
					System.out.print("returnObj" + returnObj);
					if (returnObj.equals("Claims is achieved")) {
						getTextMap.put(stepNo, returnObj.toString());
						Assert.fail();
					} else if (returnObj.equals("No Claims is achieved")) {
						getTextMap.put(stepNo, returnObj.toString());
					}
					break;
				case "CreatePropertyForMakeupIsAttendedNCompletedOnlyByCoach":
					com.zillion.qa.realappealmember.Claims.createPropertyForMakeupIsAttendedNCompletedOnlyByCoach();
					break;
				case "GroupClaimsTriggeredFor1on1IfItHappenedPriorToMakeupSession":
					if (inputData != null && referenceStep != null)
					{
						String[] referenceSteps = StringUtils.split(referenceStep, ",");
						int memberEmail = new Integer(referenceSteps[0]);
						int coachEmail = new Integer(referenceSteps[1]);
						String coachEmail2 = getTextMap.get(Integer.valueOf(coachEmail));
						String memberEmail2 = getTextMap.get(Integer.valueOf(memberEmail));
						com.zillion.qa.realappealmember.FlexibleMakeupSession.groupClaimsTriggeredFor1on1IfItHappenedPriorToMakeupSession(driver,
								inputData, coachEmail2,memberEmail2);
					}
					break;
				case "CheckClaimsForMakeupIsAttendedNCompletedOnlyByCoach":
					returnObj = com.zillion.qa.realappealmember.Claims
					.checkClaimsForMakeupIsAttendedNCompletedOnlyByCoach(driver);
					System.out.print("returnObj" + returnObj);
					if (returnObj.equals("Claims is achieved")) {
						getTextMap.put(stepNo, returnObj.toString());
						Assert.fail();
					} else if (returnObj.equals("No Claims is achieved")) {
						getTextMap.put(stepNo, returnObj.toString());
					}
					break;
				case "SplitWeightOnly":
					int weight = new Integer(referenceStep);
					String weight1 = getTextMap.get(Integer.valueOf(weight));
					String weight2[]=weight1.split("\\s+");
					System.out.println("weight2[0]" + weight2[0]);
					System.out.println("weight2[1]" + weight2[1]);
					returnObj =  weight2[0];
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "RetrieveMemberAccountPGMIDWhoseHasMissedOneonOneSessionNScheduleMakeupSession":
					returnObj = com.zillion.qa.realappealmember.Claims
					.retrieveMemberAccountPGMIDWhoseHasMissedOneonOneSessionNScheduleMakeupSession(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;

				case "GroupClaimsTriggeredForMakeupWhenOneOnOneIsMissed":
					if (inputData != null && referenceStep != null)
					{
						String[] referenceSteps = StringUtils.split(referenceStep, ",");
						int coachEmail = new Integer(referenceSteps[0]);
						int memberEmail = new Integer(referenceSteps[1]);
						String coachEmail2 = getTextMap.get(Integer.valueOf(coachEmail));
						String memberEmail2 = getTextMap.get(Integer.valueOf(memberEmail));
						com.zillion.qa.realappealmember.FlexibleMakeupSession.groupClaimsTriggeredForMakeupWhenOneOnOneIsMissed(driver,
								inputData, coachEmail2,memberEmail2);
					}
					break;
				case "CheckRescheduledStatus":
					com.zillion.qa.realappealprogramadmin.programadmin.checkRescheduledStatus(driver);
					break;
				case "PHIConsentDetailsOfMemberIdleStatus":
					com.zillion.qa.realappealmember.member
					.phiConsentDetailsOfMemberIdleStatus(driver);
					break;
				case "ToGetMemberStatus":
					int memberEmail = new Integer(referenceStep);
					String memEmail= getTextMap.get(Integer.valueOf(memberEmail));
					returnObj =com.zillion.qa.realappealmember.member
							.toGetMemberStatus(driver,memEmail);
					getTextMap.put(stepNo, returnObj.toString());
					break;

				case "GroupClaimsTriggeredForMakeupIfItHappenedPriorToOneonOneSession":
					if (inputData != null && referenceStep != null)
					{
						String[] referenceSteps = StringUtils.split(referenceStep, ",");
						int coachEmail = new Integer(referenceSteps[0]);
						int memberEmail111 = new Integer(referenceSteps[1]);
						String coachEmail2 = getTextMap.get(Integer.valueOf(coachEmail));
						String memberEmail2 = getTextMap.get(Integer.valueOf(memberEmail111));
						com.zillion.qa.realappealmember.FlexibleMakeupSession.groupClaimsTriggeredForMakeupIfItHappenedPriorToOneonOneSession(driver,
								inputData, coachEmail2,memberEmail2);
					}
					break;
				case "MoveMemberToSelectedClassroom":
					com.zillion.qa.realappealprogramadmin.programadmin.moveMemberToSelectedClassroom(driver);
					break;

				case "DBMember":
					String testdata[] = inputData.split(",");
					String onboardStatus = testdata[0];
					String mp_name = testdata[1];
					returnObj = com.zillion.qa.realappealmember.member
							.dBMember(driver, onboardStatus, mp_name);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "SftpConnectionBatchEnrollment":
					returnObj=com.zillion.qa.preventT2.Enrollment
					.sftpConnectionBatchEnrollment(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "GuerrillaMailPreventT2Enrollment":
					com.zillion.qa.preventT2.CompleteEnrollment
					.guerrillaMailPreventT2Enrollment(driver);
					break;
				case "PreventT2MemberEnrollment":
					com.zillion.qa.preventT2.CompleteEnrollment
					.preventT2MemberEnrollment(driver);
					break;
				case "ClassStatusUnApprovedAssign":
					int dBProgramInterval = new Integer(referenceStep);
					String dBProgramIntervalValue = getTextMap.get(Integer.valueOf(dBProgramInterval));
					com.zillion.qa.realappealprogramadmin.programadmin.classStatusUnApprovedAssign(driver,dBProgramIntervalValue);
					break;
				case "ScheduleMissed1on1":
					returnObj = com.zillion.qa.realappealprogramadmin.programadmin.scheduleMissed1on1(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "RetrieveUnScheduleMember":
					returnObj = com.zillion.qa.realappealprogramadmin.programadmin.retrieveUnScheduleMember(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "GuerrillaMailPreventT2EnrollmentEmailCompletedEmail":
					com.zillion.qa.preventT2.CompleteEnrollment
					.guerrillaMailPreventT2EnrollmentEmailCompletedEmail(driver);
					break;
				case "VerifyRACoachMail1on1Session":
					if (inputData != null && referenceStep == null) {
						com.zillion.qa.realappealcoach.coach
						.verifyCoachMail1on1Session(driver, inputData);
					} else if (inputData == null && referenceStep != null
							&& !referenceStep.trim().equals("")) {
						int mailidref3 = new Integer(referenceStep);
						String mailidtext3 = getTextMap.get(Integer.valueOf(mailidref3));
						com.zillion.qa.realappealmember.member
						.verifyRACoachMail1on1Session(driver, mailidtext3);
					}
					break;
				case "VerifyRAMemberMail1on1Session":
					if (inputData != null && referenceStep == null) {
						com.zillion.qa.realappealcoach.coach
						.verifyCoachMail1on1Session(driver, inputData);
					} else if (inputData == null && referenceStep != null
							&& !referenceStep.trim().equals("")) {
						int mailidref3 = new Integer(referenceStep);
						String mailidtext3 = getTextMap.get(Integer.valueOf(mailidref3));
						com.zillion.qa.realappealmember.member
						.verifyRAMemberMail1on1Session(driver, mailidtext3);
					}
					break;
				case "FindCompletedCustomization":
					com.zillion.qa.realappealprogramadmin.programadmin.findCompletedCustomization(driver);
					break;
				case "Find1on1CompletedShowRating":
					com.zillion.qa.realappealprogramadmin.programadmin.find1on1CompletedShowRating(driver);
					break;
				case "FindGroupSessionCompleted":
					com.zillion.qa.realappealprogramadmin.programadmin.findGroupSessionCompleted(driver);
					break;
				case "Missed_Disabled_1On1":
					com.zillion.qa.realappealprogramadmin.programadmin.missed_Disabled_1On1(driver);
					break;
				case "MissedGroupSessionRatingsVerify":
					com.zillion.qa.realappealprogramadmin.programadmin.missedGroupSessionRatingsVerify(driver);
					break;
				case "Canceled1on1SessionRatingsVerify":
					com.zillion.qa.realappealprogramadmin.programadmin.canceled1on1SessionRatingsVerify(driver);
					break;
				case "MissedCustomizationSessionRatingDisabledVerify":
					com.zillion.qa.realappealprogramadmin.programadmin.missedCustomizationSessionRatingDisabledVerify(driver);
					break;
				case "CanceledCustomizationSessionRatingDisabledVerify":
					com.zillion.qa.realappealprogramadmin.programadmin.canceledCustomizationSessionRatingDisabledVerify(driver);
					break;
				case "CanceledGroupSessionHaveDisabledRatingsVerify":
					com.zillion.qa.realappealprogramadmin.programadmin.canceledGroupSessionHaveDisabledRatingsVerify(driver);
					break;
				case "Missed1on1SessionRatingsDisabledVerify":
					com.zillion.qa.realappealprogramadmin.programadmin.missed1on1SessionRatingsDisabledVerify(driver);
					break;
				case "ClassStatusInSufficientAssign":
					int dBProgramInterval1 = new Integer(referenceStep);
					String dBProgramIntervalValue1 = getTextMap.get(Integer.valueOf(dBProgramInterval1));
					com.zillion.qa.realappealprogramadmin.programadmin.classStatusInSufficientAssign(driver,dBProgramIntervalValue1);
					break;
				case "MemberIn52Interval":
					String name1[]=inputData.split(",");
					returnObj = com.zillion.qa.realappealprogramadmin.programadmin.idleMemberIn52Interval(driver, name1[0],name1[1]);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "PreventT2GroupZLiveSession":
					if (inputData != null && referenceStep != null)
					{
						String[] referenceSteps = StringUtils.split(referenceStep, ",");
						int coachmail1 = new Integer(referenceSteps[0]);
						int membermail1 = new Integer(referenceSteps[1]);
						String coachMail = getTextMap.get(Integer.valueOf(coachmail1));
						String memberMail = getTextMap.get(Integer.valueOf(membermail1));
						com.zillion.qa.realappealmember.RAGroupLiveSession.preventT2GroupZLiveSession(driver,
								inputData, coachMail,memberMail);
					}
					break;
				case "CancelSessionInMemberSide":
					com.zillion.qa.realappealprogramadmin.programadmin.cancelSessionInMemberSide(driver);
					break;
				case "RescheduleSessionInMemberSide":
					com.zillion.qa.realappealprogramadmin.programadmin.rescheduleSessionInMemberSide(driver);
					break;
				case "RetrieveGroupSessionScheduledMember":
					returnObj = com.zillion.qa.realappealprogramadmin.programadmin.retrieveGroupSessionScheduledMember(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "RetrieveScheduledCSMember":
					returnObj = com.zillion.qa.realappealprogramadmin.programadmin.retrieveScheduledCSMember(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;

				case "SelectExerciseAmountDuringLiveSession":
					com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods.selectExerciseAmountDuringLiveSession( driver );
					break;

				case "NextDateCoachUnavailableList":
					com.zillion.qa.opsadmin.Classes
					.nextDateCoachUnavailabeList(driver);
					break;

				case "RAMemberLogin":
					returnObj = com.zillion.qa.realappealmember.member.raMemberLoginUser(driver,inputData);
					getTextMap.put(stepNo, returnObj.toString());
					break;

				case "RAClaimsGroupAnd1on1AttendZLiveSession":
					if (inputData != null && referenceStep == null)
					{
						com.zillion.qa.realappealmember.Claims.raClaimsGroupAnd1on1AttendZLiveSession(driver,inputData);
					}
					break;
				case "RetrieveCompleted1on1SessionMember":
					returnObj = com.zillion.qa.realappealprogramadmin.programadmin.retrieveCompleted1on1SessionMember(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "IdleMemberChangeClassRoom":
					com.zillion.qa.realappealprogramadmin.programadmin.idleMemberChangeClassRoom(driver);		
					break;
				case "RAMissedGroupAttend1on1ZLiveSession":
					if (inputData != null && referenceStep != null)
					{
						String[] referenceSteps = StringUtils.split(referenceStep, ",");
						int coacEmail = new Integer(referenceSteps[0]);
						int membEmail = new Integer(referenceSteps[1]);
						String coEmail1 = getTextMap.get(Integer.valueOf(coacEmail));
						String membEmail1 = getTextMap.get(Integer.valueOf(membEmail));
						com.zillion.qa.realappealmember.Claims.raMissedGroupAttend1on1ZLiveSession(driver,
								inputData, coEmail1,membEmail1);
					}
					break;
				case "VerifyNotificationEmailMessageContents":
					com.zillion.qa.realappealmember.member.verifyNotificationEmailMessageContents(driver);
					break;
				case "RetrieveMemberTimeSlotNotSelected":
					returnObj = com.zillion.qa.opsadmin.Members.retrieveMemberTimeSlotNotSelected(driver, inputData);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "SplitMemberTrackerWeight":
					if (inputData == null && referenceStep != null	&& !referenceStep.trim().equals(""))
					{
						int Weight = new Integer(referenceStep);
						String trackerWeight = getTextMap.get(Integer.valueOf(Weight));
						trackerWeight.trim();
						System.out.println("Tracker MemberWeight "+trackerWeight);
						String[] MemberWeight=trackerWeight.split("\\.");
						System.out.println("MemberWeight "+MemberWeight[0]);
						String MemberTrackerWeight=MemberWeight[0];
						System.out.println("MemberTracker "+MemberTrackerWeight);
						returnObj=MemberTrackerWeight;
						getTextMap.put(stepNo, returnObj.toString());
						break;
					}
				case "VerifyNotificationEmailMessage":
					if (inputData == null && referenceStep != null && !referenceStep.trim().equals("")) {
						int notifyMailid = new Integer(referenceStep);
						String notifyMailId = getTextMap.get(Integer.valueOf(notifyMailid));
						com.zillion.qa.realappealmember.member.verifyNotificationEmailMessage(driver,notifyMailId);
						break;
					}
				case "ChangeSecurityQuestionAndAnswer":
					int emailname = new Integer(referenceStep);
					String emailname1 = getTextMap.get(Integer.valueOf(emailname));
					com.zillion.qa.programadmin.Classes.changeSecurityQuestionAndAnswer(driver, emailname1);
					break;
				case "SftpConnectionLoginForCompleteEnrollment":
					returnObj=com.zillion.qa.preventT2.CompleteEnrollment
					.sftpConnectionLoginForCompleteEnrollment(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
				case "TotalNumberOfMessages":
					returnObj=com.zillion.qa.programadmin.Classes.totalNumberOfMessages(driver);
					getTextMap.put(stepNo, returnObj.toString());
					break;
					
				}

			}
			return returnObj;
		}
		return returnObj;
	}
}

