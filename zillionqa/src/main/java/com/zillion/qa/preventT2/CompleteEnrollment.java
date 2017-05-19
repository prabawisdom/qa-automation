package com.zillion.qa.preventT2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.zillion.qa.commands.Manipulation;
import com.zillion.qa.commands.Navigate;
import com.zillion.qa.utils.Directory;
import com.zillion.qa.utils.Platform;

public class CompleteEnrollment extends Manipulation implements OR
{
	public static String Firstname="";
	public static String Email="";
	public static String Lastname="";
	public static String DOB="";
	public static String Address1="";
	public static String City="";
	public static String Zipcode="";
	public static String ClientMemberID="";
	public static String State="";
	public static String ClientName="";
	public static String SubDomains="";
	public static String existCsvFilePath=Directory.uploadFilePath+Directory.SEP+"zado1.csv";
	public static String excelFilePath=Directory.uploadFilePath+Directory.SEP+"zado1.xlsx";
	public static String csvFilePath=Directory.uploadFilePath+Directory.SEP+"zado2.csv";
	public static String emailAddress="";

	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   10/MAY/17
	 * Modified Date:   
	 * Description : Created common method to convert CSV file to Excel file
	 */
	public static void csvToXLSX() throws IOException {

		String csvFileAddress = existCsvFilePath;
		String xlsxFileAddress = excelFilePath;
		XSSFWorkbook workBook = new XSSFWorkbook();
		XSSFSheet sheet = workBook.createSheet("sheet1");
		String currentLine=null;
		int RowNum=0;
		BufferedReader br = new BufferedReader(new FileReader(csvFileAddress));
		while ((currentLine = br.readLine()) != null) 
		{
			String str[] = currentLine.split(",");
			RowNum++;
			XSSFRow currentRow=sheet.createRow(RowNum);
			for(int i=0;i<str.length;i++)
			{
				if (str[i]!=null)
				{
					currentRow.createCell(i).setCellValue(str[i]);
				}
			}
		}
		FileOutputStream fileOutputStream =  new FileOutputStream(xlsxFileAddress);
		workBook.write(fileOutputStream);
		fileOutputStream.close();
		System.out.println("Done");
	}

	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   10/MAY/17
	 * Modified Date:   
	 * Description : Created common method overrite the Excel file and fill all the parameters
	 */
	public static String overRiteExcelFile() throws IOException {
		XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(excelFilePath));
		XSSFSheet worksheet = wb.getSheetAt(0);
		Cell cell = null; 
		cell = worksheet.getRow(2).getCell(0);
		Firstname = cell.getStringCellValue();
		cell = worksheet.getRow(2).getCell(1);
		Lastname = cell.getStringCellValue();
		cell = worksheet.getRow(2).getCell(4);
		Email = cell.getStringCellValue();
		cell = worksheet.getRow(2).getCell(5);
		ClientMemberID = cell.getStringCellValue();
		cell = worksheet.getRow(2).getCell(6);
		DOB = cell.getStringCellValue();
		cell = worksheet.getRow(2).getCell(7);
		Address1 = cell.getStringCellValue();
		cell = worksheet.getRow(2).getCell(9);
		City = cell.getStringCellValue();
		cell = worksheet.getRow(2).getCell(10);
		State = cell.getStringCellValue();
		cell = worksheet.getRow(2).getCell(11);
		Zipcode = cell.getStringCellValue();
		cell = worksheet.getRow(2).getCell(16);
		ClientName = cell.getStringCellValue();
		cell = worksheet.getRow(2).getCell(17);
		SubDomains = cell.getStringCellValue();
		System.out.println("Email : "+Email);
		System.out.println("DOB : "+DOB);
		System.out.println("Address1 : "+Address1);
		{
			String count= Directory.T2_EnrollmentBatchCount;
			int enrollCount = Integer.parseInt(count)+1;
			Row Firstnamerow;
			Cell FirstNameCell = worksheet.getRow(1).getCell(0);
			String header = FirstNameCell.getStringCellValue();
			if (header.equalsIgnoreCase("FirstName")) {
				int i=2;
				while (i<=enrollCount) {

					Firstnamerow = worksheet.getRow(i); 
					if(Firstnamerow == null){
						Firstnamerow = worksheet.createRow(i);
						Cell FirstNamemyCell = Firstnamerow.getCell(0);
						FirstNamemyCell = Firstnamerow.createCell(0);
						FirstNamemyCell.setCellValue(Firstname);
						Cell LastNamemyCell = Firstnamerow.getCell(1);
						LastNamemyCell = Firstnamerow.createCell(1);
						LastNamemyCell.setCellValue(Lastname);
						Cell EmailmyCell = Firstnamerow.getCell(4);
						EmailmyCell = Firstnamerow.createCell(4);
						String timeStamp = new SimpleDateFormat("MMMmmss").format(Calendar.getInstance().getTime());
						int milli = Calendar.getInstance().get(Calendar.MILLISECOND);
						int milli1 = Calendar.getInstance().get(Calendar.MILLISECOND);
						int milli2 = Calendar.getInstance().get(Calendar.MILLISECOND);
						emailAddress = timeStamp+milli+milli1+milli2+"@guerrillamail.com";
						EmailmyCell.setCellValue(emailAddress);
						Cell ClientMemberIDmyCell = Firstnamerow.getCell(5);
						ClientMemberIDmyCell = Firstnamerow.createCell(5);
						ClientMemberIDmyCell.setCellValue(ClientMemberID);
						Cell DOBmyCell = Firstnamerow.getCell(6);
						DOBmyCell = Firstnamerow.createCell(6);
						DOBmyCell.setCellValue(DOB);
						Cell AddressmyCell = Firstnamerow.getCell(7);
						AddressmyCell = Firstnamerow.createCell(7);
						AddressmyCell.setCellValue(Address1);
						Cell CitymyCell = Firstnamerow.getCell(9);
						CitymyCell = Firstnamerow.createCell(9);
						CitymyCell.setCellValue(City);
						Cell StatemyCell = Firstnamerow.getCell(10);
						StatemyCell = Firstnamerow.createCell(10);
						StatemyCell.setCellValue(State);
						Cell ZipcodemyCell = Firstnamerow.getCell(11);
						ZipcodemyCell = Firstnamerow.createCell(11);
						ZipcodemyCell.setCellType(Cell.CELL_TYPE_STRING);
						ZipcodemyCell.setCellValue("06850");
						Cell ClientNamemyCell = Firstnamerow.getCell(16);
						ClientNamemyCell = Firstnamerow.createCell(16);
						ClientNamemyCell.setCellValue(ClientName);
						Cell SubDomainmyCell = Firstnamerow.getCell(17);
						SubDomainmyCell = Firstnamerow.createCell(17);
						SubDomainmyCell.setCellValue(SubDomains);
						System.out.println("Modified successfully :"+i);
					}

					else if (Firstnamerow != null)
					{
						Cell FirstNamemyCell = Firstnamerow.getCell(0);
						FirstNamemyCell.setCellValue(Firstname);
						Cell LastNamemyCell = Firstnamerow.getCell(1);
						LastNamemyCell.setCellValue(Lastname);
						Cell EmailmyCell = Firstnamerow.getCell(4);
						String timeStamp = new SimpleDateFormat("MMMmmss").format(Calendar.getInstance().getTime());
						int milli = Calendar.getInstance().get(Calendar.MILLISECOND);
						int milli1 = Calendar.getInstance().get(Calendar.MILLISECOND);
						int milli2 = Calendar.getInstance().get(Calendar.MILLISECOND);
						emailAddress = timeStamp+milli+milli1+milli2+"@guerrillamail.com";
						EmailmyCell.setCellValue(emailAddress);
						Cell ClientMemberIDmyCell = Firstnamerow.getCell(5);
						ClientMemberIDmyCell.setCellValue(ClientMemberID);
						Cell DOBmyCell = Firstnamerow.getCell(6);
						DOBmyCell.setCellValue(DOB);
						Cell AddressmyCell = Firstnamerow.getCell(7);
						AddressmyCell.setCellValue(Address1);
						Cell CitymyCell = Firstnamerow.getCell(9);
						CitymyCell.setCellValue(City);
						Cell StatemyCell = Firstnamerow.getCell(10);
						StatemyCell.setCellValue(State);
						Cell ZipcodemyCell = Firstnamerow.getCell(11);
						ZipcodemyCell.setCellType(Cell.CELL_TYPE_STRING);
						ZipcodemyCell.setCellValue("06850");
						Cell ClientNamemyCell = Firstnamerow.getCell(16);
						ClientNamemyCell.setCellValue(ClientName);
						Cell SubDomainmyCell = Firstnamerow.getCell(17);
						SubDomainmyCell.setCellValue(SubDomains);
						System.out.println("Modified successfully :"+i);
					}
					i++;
				}
				
				//
			}
		}

		FileOutputStream output_file =new FileOutputStream(new File(excelFilePath));
		wb.write(output_file);
		output_file.close();
		System.out.println("Done");
		return emailAddress;
	}

	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   10/MAY/17
	 * Modified Date:   
	 * Description : Created common method Excel file to CSV file to upload it
	 */
	public static void convertXlToCsvFile() throws IOException, InvalidFormatException 
	{

		Workbook wb = new XSSFWorkbook(new File(excelFilePath));
		int sheetNo = Integer.parseInt("0");
		FormulaEvaluator fe = null;

		DataFormatter formatter = new DataFormatter();
		PrintStream out = new PrintStream(new FileOutputStream(csvFilePath),
				true, "UTF-8");
		byte[] bom = {(byte)0xEF, (byte)0xBB, (byte)0xBF};
		out.write(bom);
		{
			Sheet sheet = wb.getSheetAt(sheetNo);
			int firstRow= sheet.getFirstRowNum();

			for (int r = 0, rn = sheet.getLastRowNum() ; r <= rn ; r++) 
			{
				Row row = sheet.getRow(r);
				if ( row == null ) { out.print(""); continue; }
				boolean firstCell = true;
				for (int c = 0, cn = row.getLastCellNum() ; c < cn ; c++) {
					Cell cell = row.getCell(c, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
					if ( ! firstCell ) out.print(',');
					if ( cell != null ) {
						if ( fe != null ) cell = fe.evaluateInCell(cell);
						String value = formatter.formatCellValue(cell);
						if ( cell.getCellTypeEnum() == CellType.FORMULA ) {
							value = "=" + value;
						}
						out.print((value));
					}
					firstCell = false;
				}
				out.println();
			}
		}
	}

	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   10/MAY/17
	 * Modified Date:   
	 * Description : Created common method upload the CSV file to SFTP server
	 */
	public static void uploadCSVFile() throws Exception 
	{
		String SFTPWORKINGDIR = Directory.T2_SFTPChannel;
		Session session = null;
		Channel channel = null;
		ChannelSftp channelSftp = null;
		try {
			JSch jsch = new JSch();
			session = jsch.getSession(Directory.T2_SFTPUsername, Directory.T2_SFTPHostName, 22);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(Directory.T2_SFTPPassword);
			session.connect();
			channel = session.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp) channel;
			channelSftp.cd(SFTPWORKINGDIR);
			File f = new File(csvFilePath);
			channelSftp.put(new FileInputStream(f), f.getName());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   10/MAY/17
	 * Modified Date:   
	 * Description : Created common method delete both Excel and CSV file
	 */
	public static void deleteExcelAndCsvFile() throws Exception {

		File file = new File(excelFilePath);
		File file1 = new File(csvFilePath);
		file.delete();
		file1.delete();
	}

	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   10/MAY/17
	 * Modified Date:   
	 * Description : Created common method SFTP connection Login
	 */
	public static String sftpConnectionLoginForCompleteEnrollment(WebDriver driver) throws Exception 
	{
		/*JSch jsch = new JSch();
		Session session = null;
		session = jsch.getSession("t2-int", "sftp.zillion.com", 22);
		session.setConfig("StrictHostKeyChecking", "no");
		session.setPassword("Zillion@321");
		session.connect();
		Manipulation.wait(driver, "2");
		Channel channel = session.openChannel("sftp");
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		Manipulation.wait(driver, "2");
		sftpChannel.get("/home/t2-int/RAT2/enrollment/completed/test 4.csv.ok",Directory.RA_Prevent_T2_FilePath);
		sftpChannel.exit();
		session.disconnect(); */
		csvToXLSX();
		String mail=overRiteExcelFile();
		convertXlToCsvFile();
		uploadCSVFile();
		//		wait(driver,"10");
		//		deleteExcelAndCsvFile();
		wait(driver,"150");
		return emailAddress;
	}

	/**
	 * Name         :   VIGNESHRAJ.M
	 * Created Date :   20/Apr/17
	 * Modified Date:   
	 * Description  :  Common method for PreventT2Enrollment Guerrillamail link
	 */
	public static void guerrillaMailPreventT2Enrollment(WebDriver driver)
	{
		Navigate.get(driver, Directory.Guerrillamailurl);
		Navigate.maximize(driver);
		WebElement Guerrillamaildropdown= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_DROPDOWN));
		Manipulation.selectByValue(Guerrillamaildropdown, "guerrillamail.com");
		wait(driver, "3");
		WebElement GuerrillaMailEditButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_EDIT_BUTTON));
		click(GuerrillaMailEditButton);
		waitForElement(driver, GUERRILLA_MAIL_TEXTBOX);
		WebElement GuerrillaMailTextbox= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_TEXTBOX));
		clearAndType(GuerrillaMailTextbox, emailAddress);
		WebElement GuerrillaMailSetButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_SET_BUTTON));
		wait(driver, "3");
		click(GuerrillaMailSetButton);
		wait(driver, "5");
		try{
			if(driver.findElement( By.xpath( GUERRILLA_MAIL_PREVENT_T2_WELCOME_MESSAGE ) ).isDisplayed())
			{
				waitForElement(driver, GUERRILLA_MAIL_PREVENT_T2_WELCOME_MESSAGE);
			}
			else
			{
				driver.navigate().refresh();
				wait( driver, "3" );
				if(driver.findElement( By.xpath( GUERRILLA_MAIL_PREVENT_T2_WELCOME_MESSAGE ) ).isDisplayed())
				{
					waitForElement(driver, GUERRILLA_MAIL_PREVENT_T2_WELCOME_MESSAGE);
				}
				else
				{
					driver.navigate().refresh();
					wait( driver, "5" );
					waitForElement(driver, GUERRILLA_MAIL_PREVENT_T2_WELCOME_MESSAGE);
				}
			}
		}
		catch(Exception e)
		{
			driver.navigate().refresh();
			wait( driver, "3" );
			waitForElement(driver, GUERRILLA_MAIL_PREVENT_T2_WELCOME_MESSAGE);
		}
		WebElement GuerrillaMailWelcomeMessage= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_PREVENT_T2_WELCOME_MESSAGE));
		verifyElementIsPresent(driver, GuerrillaMailWelcomeMessage);
		click(GuerrillaMailWelcomeMessage);
		wait(driver, "10");
		WebElement generatedLink= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_GENERATED_LINK));
		waitForElement(driver, GUERRILLA_MAIL_GENERATED_LINK);
		verifyElementIsPresent(driver, generatedLink);
		getwindowandclose(driver, generatedLink); // changed by Leena - 09/30
		wait(driver, "5");
		if (Platform.BROWSER_NAME.equalsIgnoreCase("Internet Explorer"))
		{
			driver.get("javascript:document.getElementById('overridelink').click();");
			wait(driver, "5");
		}
	}


	/**
	 * Name         :   VIGNESHRAJ.M
	 * Created Date :   20/Apr/17
	 * Modified Date:   
	 * Description  :  Common method for PreventT2Enrollment
	 */
	public static void preventT2MemberEnrollment(WebDriver driver)
	{
		waitForElement(driver, RA_MEMBER_LASTNAME);
		WebElement lastname= driver.findElement(By.xpath(OR.RA_MEMBER_LASTNAME));
		WebElement month= driver.findElement(By.xpath(OR.RA_MEMBER_DOBMONTH));
		WebElement date= driver.findElement(By.xpath(OR.RA_MEMBER_DOBDAY));
		WebElement year= driver.findElement(By.xpath(OR.RA_MEMBER_DOBYEAR));
		click(lastname);
		click(month);
		click(date);
		click(year);
		click(month);
		wait(driver, "2");
		WebElement lastnameRequired= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_LASTNAME_REQUIRED));
		WebElement monthRequired= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_MONTH_REQUIRED));
		WebElement dayRequired= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_DAY_REQUIRED));
		WebElement yearRequired= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_YEAR_REQUIRED));
		verifyElementIsPresent(driver, lastnameRequired);
		verifyElementIsPresent(driver, monthRequired);
		verifyElementIsPresent(driver, dayRequired);
		verifyElementIsPresent(driver, yearRequired);
		sendKeys(lastname, Lastname);
		String[] dateformat= DOB.split("-");
		String year1= dateformat[0];
		String month1= dateformat[1];
		String date1= dateformat[2];
		selectByVisibletext(month, month1);
		selectByVisibletext(date, date1);
		selectByVisibletext(date, "2000");
		wait(driver, "2");
		WebElement memberMustBeOverEighteen= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_MEMBER_MUST_BE_OVER_EIGHTEEN));
		verifyElementIsPresent(driver, memberMustBeOverEighteen);
		int year2= Integer.valueOf(year1);
		int year3= year2+1;
		String year4= Integer.toString(year3);
		selectByVisibletext(date, year4);
		wait(driver, "2");
		WebElement infoNotMatch= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_INFORMATION_DOESNOT_MATCH_ERROR));
		verifyElementIsPresent(driver, infoNotMatch);
		wait(driver, "5");
		selectByVisibletext(year, year1);
		wait(driver, "2");
		WebElement nextButton= driver.findElement(By.xpath(OR.RA_MEMBER_STEP1_NEXTBUTTON_ENABLED));
		click(nextButton);
		wait(driver, "2");
		waitForElement(driver, PREVENT_T2_ENROLLMENT_PASSWORD_TEXTBOX);
		WebElement passwordTextbox= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_PASSWORD_TEXTBOX));
		click(passwordTextbox);
		WebElement enrollSecurityDropdown= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_SECURITY_DROPDOWN));
		click(enrollSecurityDropdown);
		click(passwordTextbox);
		WebElement mustCreatePasswordError= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_MUST_CREATE_PASSWORD));
		WebElement securityQnRequired= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_SECURITY_QN_REQUIRED));
		verifyElementIsPresent(driver, mustCreatePasswordError);
		verifyElementIsPresent(driver, securityQnRequired);
		sendKeys(passwordTextbox, "Password1");
		selectByVisibletext(enrollSecurityDropdown, "What was your favorite place to visit as a child?");
		wait(driver, "2");
		WebElement securityAnswertextbox= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_SECURITY_ANSWER_TEXTBOX));
		WebElement screenNameTextbox= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_SCREENNAME_TEXTBOX));
		click(securityAnswertextbox);
		click(screenNameTextbox);
		click(securityAnswertextbox);
		WebElement answerToSecurityQnRequired= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_ANS_TO_SECURITY_QN));
		WebElement screenNameRequired= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_SCREENNAME_REQUIRED));
		verifyElementIsPresent(driver, answerToSecurityQnRequired);
		verifyElementIsPresent(driver, screenNameRequired);
		sendKeys(securityAnswertextbox, "test");
		sendKeys(screenNameTextbox, "test");
		WebElement agreeCheckbox= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_ACCEPT_CHECKBOX));
		click(agreeCheckbox);
		wait(driver, "4");
		jsClickByXPath(driver, PREVENT_T2_ENROLLMENT_SUBMIT_BUTTON);
		wait(driver, "7");
		waitForElement(driver, PREVENT_T2_ENROLLMENT_SELECT_GENDER);
		WebElement selectGender= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_SELECT_GENDER));
		jsClickByXPath(driver, PREVENT_T2_ENROLLMENT_SELECT_GENDER);
		WebElement currentWeight= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_CURRENT_WEIGHT_TEXTBOX));
		WebElement targetWeight= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_TARGET_WEIGHT_TEXTBOX));
		WebElement heightFoot= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_HEIGHTFOOT_TEXTBOX));
		WebElement heightInch= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_HEIGHTINCH_TEXTBOX));
		click(currentWeight);
		wait(driver, "2");
		click(targetWeight);
		wait(driver, "2");
		click(heightFoot);
		wait(driver, "2");
		click(heightInch);
		wait(driver, "2");
		click(currentWeight);
		wait(driver, "2");
		WebElement currentWeightError= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_CURRENT_WEIGHT_ERROR));
		WebElement targetWeightError= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_TARGET_WEIGHT_ERROR));
		WebElement heightFeetError= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_VALURE_FOR_HEIGHT_FEET_ERROR));
		WebElement heightInchesError= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_VALURE_FOR_HEIGHT_INCHES_ERROR));
		verifyElementIsPresent(driver, currentWeightError);
		verifyElementIsPresent(driver, targetWeightError);
		verifyElementIsPresent(driver, heightFeetError);
		verifyElementIsPresent(driver, heightInchesError);
		sendKeys(currentWeight, "180");
		sendKeys(targetWeight, "150");
		sendKeys(heightFoot, "5");
		sendKeys(heightInch, "5");
		WebElement activityLevel= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_ACTIVITY_LEVEL));
		click(activityLevel);
		wait(driver, "2");
		WebElement nextBut= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_NEXT_BUTTON));
		click(nextBut);
		wait(driver, "4");
		waitForElement(driver, PREVENT_T2_ENROLLMENT_PRIMARY_ADDRESS_TEXTBOX);
		WebElement primaryAddress= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_PRIMARY_ADDRESS_TEXTBOX));
		WebElement secondaryAddress= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_SECONDARY_ADDRESS_TEXTBOX));
		WebElement city= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_CITY_TEXTBOX));
		WebElement state= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_SELECT_STATE_DROPDOWN));
		WebElement zipcode= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_ZIPCODE_TEXTBOX));
		WebElement phone= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_PHONE_TEXTBOX));
		click(primaryAddress);
		click(city);
		click(zipcode);
		click(phone);
		click(state);
		click(secondaryAddress);
		wait(driver, "2");
		WebElement primaryAddressRequired= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_ADDRESS1_REQUIRED));
		WebElement cityRequired= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_CITY_REQUIRED));
		WebElement stateRequired= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_STATE_REQUIRED));
		WebElement zipcodeRequired= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_ZIPCODE_REQUIRED));
		verifyElementIsPresent(driver, primaryAddressRequired);
		verifyElementIsPresent(driver, cityRequired);
		verifyElementIsPresent(driver, stateRequired);
		verifyElementIsPresent(driver, zipcodeRequired);
		sendKeys(primaryAddress, Address1);
		sendKeys(secondaryAddress, "Test");
		sendKeys(city, City);
		sendKeys(zipcode, Zipcode);
		selectByValue(state, "0");
		click(phone);
		sendKeys(phone, "1234567890");
		wait(driver, "2");
		jsClickByXPath(driver, PREVENT_T2_ENROLLMENT_NEXT_BUTTON);
		wait(driver, "7");
		com.zillion.qa.member.Dashboard.dateSession(driver);
		jsClickByXPath(driver, PREVENT_T2_ENROLLMENT_SCHEDULE_BUTTON_SUBMIT_BUTTON);
		wait(driver, "7");
		waitForElement(driver, PREVENT_T2_ENROLLMENT_LOGIN_NOW_BUTTON);
		jsClickByXPath(driver, PREVENT_T2_ENROLLMENT_LOGIN_NOW_BUTTON);
		wait(driver, "2");
		waitForElement(driver, PREVENT_T2_ENROLLMENT_LOGIN_EMAIL_TEXTBOX);
		WebElement loginEmailTextbox= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_LOGIN_EMAIL_TEXTBOX));
		WebElement loginPasswordTextbox= driver.findElement(By.xpath(OR.PREVENT_T2_ENROLLMENT_LOGIN_PASSWORD_TEXTBOX));
		sendKeys(loginEmailTextbox, emailAddress);
		sendKeys(loginPasswordTextbox, "Password1");
		jsClickByXPath(driver, PREVENT_T2_ENROLLMENT_LOGIN_BUTTON);
		wait(driver, "10");
		waitForElement(driver, PREVENT_T2_ENROLLMENT_LOGOUT_BUTTON);
		jsClickByXPath(driver, PREVENT_T2_ENROLLMENT_LOGOUT_BUTTON);
		wait(driver, "5");
		waitForElement(driver, PREVENT_T2_ENROLLMENT_SIGNEDOUT_TEXT);
	}

	/**
	 * Name         :   VIGNESHRAJ.M
	 * Created Date :   20/Apr/17
	 * Modified Date:   
	 * Description  :  Common method for PreventT2Enrollment Guerrillamail link completed Email check
	 */
	public static void guerrillaMailPreventT2EnrollmentEmailCompletedEmail(WebDriver driver)
	{
		Navigate.get(driver, Directory.Guerrillamailurl);
		Navigate.maximize(driver);
		WebElement Guerrillamaildropdown= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_DROPDOWN));
		Manipulation.selectByValue(Guerrillamaildropdown, "guerrillamail.com");
		wait(driver, "3");
		WebElement GuerrillaMailEditButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_EDIT_BUTTON));
		click(GuerrillaMailEditButton);
		waitForElement(driver, GUERRILLA_MAIL_TEXTBOX);
		WebElement GuerrillaMailTextbox= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_TEXTBOX));
		clearAndType(GuerrillaMailTextbox, emailAddress);
		WebElement GuerrillaMailSetButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_SET_BUTTON));
		wait(driver, "3");
		click(GuerrillaMailSetButton);
		wait(driver, "5");
		try{
			if(driver.findElement( By.xpath( GUERRILLA_MAIL_PREVENT_T2_WELCOME_MESSAGE ) ).isDisplayed())
			{
				waitForElement(driver, GUERRILLA_MAIL_PREVENT_T2_WELCOME_MESSAGE);
			}
			else
			{
				driver.navigate().refresh();
				wait( driver, "3" );
				if(driver.findElement( By.xpath( GUERRILLA_MAIL_PREVENT_T2_WELCOME_MESSAGE ) ).isDisplayed())
				{
					waitForElement(driver, GUERRILLA_MAIL_PREVENT_T2_WELCOME_MESSAGE);
				}
				else
				{
					driver.navigate().refresh();
					wait( driver, "5" );
					waitForElement(driver, GUERRILLA_MAIL_PREVENT_T2_WELCOME_MESSAGE);
				}
			}
		}
		catch(Exception e)
		{
			driver.navigate().refresh();
			wait( driver, "3" );
			waitForElement(driver, GUERRILLA_MAIL_PREVENT_T2_WELCOME_MESSAGE);
		}
		WebElement GuerrillaMailWelcomeMessage= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_PREVENT_T2_ENROLLMENT_COMPLETED));
		verifyElementIsPresent(driver, GuerrillaMailWelcomeMessage);
		click(GuerrillaMailWelcomeMessage);
		wait(driver, "4");
		waitForElement(driver, GUERRILLA_MAIL_PREVENT_T2_ENROLLMENT_COMPLETED_TEXT);
		WebElement generatedLink= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_PREVENT_T2_ENROLLMENT_COMPLETED_TEXT));
		verifyElementIsPresent(driver, generatedLink);
	}

}
