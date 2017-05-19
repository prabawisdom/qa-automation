package com.zillion.qa.preventT2;

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
import com.jcraft.jsch.*;
import com.zillion.qa.commands.Manipulation;
import com.zillion.qa.commands.Navigate;
import com.zillion.qa.utils.Directory;
import com.zillion.qa.utils.Platform;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public class Enrollment extends Manipulation implements OR
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
	public static String csvFilePath=Directory.uploadFilePath+Directory.SEP+"zado.csv";


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
						String randomNumber = UUID.randomUUID().toString();
						String emailAddress = timeStamp+milli+randomNumber+"@guerrillamail.com";
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
						String randomNumber = UUID.randomUUID().toString();
						String emailAddress = timeStamp+milli+randomNumber+"@guerrillamail.com";
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
		return Email;
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
	public static String sftpConnectionBatchEnrollment(WebDriver driver) throws Exception 
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
//		wait(driver,"600");
		return mail;
	}

}
