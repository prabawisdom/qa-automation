package com.zillion.qa.coaches;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.zillion.qa.commands.Manipulation;
import com.zillion.qa.utils.Directory;
/**
 * Name :     ABINAYA.P
 * Created Date:   28/Jan/16
 * Modified Date:
 * Description :   Create a common method to validate member/patient profile against database
 * Ticket ID :
 * Required Inputs :
 * @throws ClassNotFoundException
 * @throws SQLException
 */
public class Members extends Manipulation implements OR
{
	public static String validationDbMemberProfile(WebDriver driver, String testData,String mailId) throws ClassNotFoundException, SQLException
	{
		String[] testData1 = testData.split(",");
		String inputFirstName=testData1[0];
		String inputLastName=testData1[1];
		String inputAddress=testData1[2];
		String inputCity=testData1[3];
		String inputState=testData1[4];
		String inputPostalCode=testData1[5];
		String inputEmail=mailId;
		String inputHomePhone=testData1[6];
		String inputMobilePhone=testData1[7];
		String inputBirth=testData1[8];
		String inputSecurityAnswer=testData1[9];
		String inputStatus=testData1[10];
		String allfields="";
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select FIRST_NAME,LAST_NAME,MAILING_STREET,MAILING_STREET2,MAILING_CITY,MAILING_STATE,MAILING_POSTALCODE,EMAIL,HOME_PHONE,MOBILE_PHONE,AGE,SECURITY_ANSWER,STATUS from Account where email = '"+inputEmail+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			String firstname = rs.getString("FIRST_NAME");
			System.out.println(firstname);
			String lastname = rs.getString("LAST_NAME");
			System.out.println(lastname);
			String address1 = rs.getString("MAILING_STREET");
			System.out.println(address1);
			String city = rs.getString("MAILING_CITY");
			System.out.println(city);
			String state = rs.getString("MAILING_STATE");
			System.out.println(state);
			String zipcode = rs.getString("MAILING_POSTALCODE");
			System.out.println(zipcode);
			String mail = rs.getString("EMAIL");
			System.out.println(mail);
			String phone = rs.getString("HOME_PHONE");
			System.out.println(phone);
			String mobile = rs.getString("MOBILE_PHONE");
			System.out.println(mobile);
			String dob = rs.getString("AGE");
			System.out.println(dob);
			String securityanswer = rs.getString("SECURITY_ANSWER");
			System.out.println(securityanswer);
			String status = rs.getString("STATUS");
			System.out.println(status);
			allfields=firstname+"\n "+lastname+"\n"+address1+"\n"+city+"\n"+state+"\n"+zipcode+"\n"+mail+"\n"+phone+"\n"+mobile+"\n"+dob+"\n"+securityanswer+"\n"+status;
			if(inputFirstName.equals(firstname))
			{
				System.out.println("First name from the database and coach portal is matched");
			}
			else
			{
				System.out.println("First name from the database and coach portal is not matched");
				Assert.fail();
			}
			if(inputLastName.equals(lastname))
			{
				System.out.println("Last name from the database and coach portal is matched");
			}
			else
			{
				System.out.println("Last name from the database and coach portal is not matched");
				Assert.fail();
			}
			if(inputAddress.equals(address1))
			{
				System.out.println("Address1 from the database and coach portal is matched");
			}
			else
			{
				System.out.println("Address1 from the database and coach portal is not matched");
				Assert.fail();
			}
			if(inputCity.equals(city))
			{
				System.out.println("City from the database and coach portal is matched");
			}
			else
			{
				System.out.println("City from the database and coach portal is not matched");
				Assert.fail();
			}
			if(inputState.equals(state))
			{
				System.out.println("State from the database and coach portal is matched");
			}
			else
			{
				System.out.println("State from the database and coach portal is not matched");
				Assert.fail();
			}
			if(inputPostalCode.equals(zipcode))
			{
				System.out.println("Zipcode from the database and coach portal is matched");
			}
			else
			{
				System.out.println("Zipcode from the database and coach portal is not matched");
				Assert.fail();
			}
			if(inputEmail.equals(mail))
			{
				System.out.println("Mail from the database and coach portal is matched");
			}
			else
			{
				System.out.println("Mail from the database and coach portal is not matched");
				Assert.fail();
			}
			if(inputHomePhone.equals(phone))
			{
				System.out.println("Phone from the database and coach portal is matched");
			}
			else
			{
				System.out.println("Phone from the database and coach portal is not matched");
				Assert.fail();
			}
			if(inputMobilePhone.equals(mobile))
			{
				System.out.println("Mobile from the database and coach portal is matched");
			}
			else
			{
				System.out.println("Mobile from the database and coach portal is not matched");
				Assert.fail();
			}
			if(inputBirth.equals(dob))
			{
				System.out.println("Age from the database and coach portal is matched");
			}
			else
			{
				System.out.println("Age from the database and coach portal is not matched");
				Assert.fail();
			}
			if(inputSecurityAnswer.equals(securityanswer))
			{
				System.out.println("Securityanswer from the database and coach portal is matched");
			}
			else
			{
				System.out.println("Securityanswer from the database and coach portal is not matched");
				Assert.fail();
			}
			if(inputStatus.equals(status))
			{
				System.out.println("Status from the database and coach portal is matched");
			}
			else
			{
				System.out.println("Status from the database and coach portal is not matched");
				Assert.fail();
			}
		}
		return allfields;
	}
	/**
	 * Name :Leena P.
	 * Created Date:06/Sept/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve the orbera member with schedule button and the respective coach
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String member_mail1="";
	public static String coach_mail1="";
	public static String getOrberaMemberSchedule1on1(WebDriver driver) throws ClassNotFoundException, SQLException
	{
		String port = Directory.Oracle_Port;
		String database_name=Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("SELECT ACNT.EMAIL Member_Email, PROV.EMAIL Coach_Email, ACNT.first_name Member_FirstName, ACNT.last_name Member_LastName FROM SUMMARY_ACCOUNT_TODATE SMRY, PROVIDER PROV, ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID AND SMRY.ACCOUNT_ID=ACNT.ID AND ACCOUNT_ID IN  (SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ID IN (SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL  WHERE SESSION_TYPE='1on1' AND SESSION_STATUS IN ('Missed')  AND PROGRAM_INTERVAL_START_DT < SYSDATE AND PROGRAM_INTERVAL_END_DT>SYSDATE AND ACCOUNT_PROGRAM_ID IN (SELECT ID FROM ACCOUNT_PROGRAM WHERE ACCOUNT_ID IN  (SELECT ACCOUNT_ID FROM SUMMARY_ACCOUNT_TODATE WHERE SUMMARY_EXTENSION LIKE '%medicalProcedureMilestone1Answer%YES, I HAD IT%') AND MAST_PROGRAM_ID='30100AA52BEA' AND IS_ACTIVE=1)))ORDER BY SMRY.CREATED_DT ASC");
		System.out.println("query executed");
		String member_firstName="";
		String member_LastName="";
		String memberCoach = "";
		while(rs.next())
		{
			member_mail1 = rs.getString("Member_Email");
			member_firstName = rs.getString("Member_FirstName");
			member_LastName = rs.getString("Member_LastName");
			coach_mail1 = rs.getString("Coach_Email");
		}
		conn.close();
		memberCoach=member_mail1+","+member_firstName+","+member_LastName+","+coach_mail1;
		return memberCoach;
	}
	/**
	 * Name :Leena P.
	 * Created Date:06/Sept/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve the orbera coach with schedule button and the respective coach
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String getOrberaCoachSchedule1on1(WebDriver driver) throws ClassNotFoundException, SQLException
	{
		String port = Directory.Oracle_Port;
		String database_name=Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("SELECT PROV.EMAIL Coach_Email FROM SUMMARY_ACCOUNT_TODATE SMRY, PROVIDER PROV, ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID AND SMRY.ACCOUNT_ID=ACNT.ID AND ACCOUNT_ID IN(SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ID IN (SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_TYPE='1on1' AND SESSION_STATUS IN ('Missed') AND ACCOUNT_PROGRAM_ID IN (SELECT ID FROM ACCOUNT_PROGRAM WHERE ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM SUMMARY_ACCOUNT_TODATE WHERE SUMMARY_EXTENSION LIKE '%medicalProcedureMilestone1Answer%YES, I HAD IT%')AND MAST_PROGRAM_ID='30100AA52BEA' AND START_DT_TIME>SYSDATE + INTERVAL '-30' DAY)))");
		System.out.println("query executed");
		String coach_mail1="";
		while(rs.next())
		{
			coach_mail1 = rs.getString("Coach_Email");
		}
		return coach_mail1;
	}
	/**
	 * Name :Leena P.
	 * Created Date:06/Sept/2016
	 * Modified Date:
	 * Description :   Create a common method to retrieve the orbera member with schedule button and the respective coach
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String getOrberaMemberMessageCenter(WebDriver driver) throws ClassNotFoundException, SQLException
	{
		String port = Directory.Oracle_Port;
		String database_name=Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("SELECT ACNT.EMAIL Member_Email, ACNT.FIRST_NAME Member_FirstName, ACNT.LAST_NAME Member_LastName, PROV.EMAIL Coach_Email FROM SUMMARY_ACCOUNT_TODATE SMRY, PROVIDER PROV, ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID AND SMRY.ACCOUNT_ID=ACNT.ID AND ACCOUNT_ID IN(SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ID IN (SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_TYPE='1on1' AND SESSION_STATUS IN ('Missed') AND ACCOUNT_PROGRAM_ID IN (SELECT ID FROM ACCOUNT_PROGRAM WHERE ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM SUMMARY_ACCOUNT_TODATE WHERE SUMMARY_EXTENSION LIKE '%medicalProcedureMilestone1Answer%YES, I HAD IT%')AND MAST_PROGRAM_ID='30100AA52BEA' AND START_DT_TIME>SYSDATE + INTERVAL '-52' DAY)))");
		System.out.println("query executed");
		String member_mail1="";
		String member_firstName="";
		String member_LastName="";
		String coach_mail1="";
		String memberCoach = "";
		if(rs.next())
		{
			member_mail1 = rs.getString("Member_Email");
			member_firstName = rs.getString("Member_FirstName");
			member_LastName = rs.getString("Member_LastName");
			coach_mail1 = rs.getString("Coach_Email");
		}
		memberCoach=member_mail1+","+member_firstName+","+member_LastName+","+coach_mail1;
		return memberCoach;
	}
	/**
	 * Name :Abinaya.P
	 * Created Date:08/Nov/2016
	 * Modified Date:
	 * Description :   Create a common method to choose member which is under WCS PA
	 * Required Inputs :
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void chooseAptMemberWCSPA(WebDriver driver) throws ClassNotFoundException, SQLException
	{
		try
		{
			waitForElement(driver, PA_WC_MEMBER_POST_ACTIVE_ABCPRACTICE);
		}
		catch(Exception e)
		{
			try
			{
				jsClickByXPath(driver, NEXT_BUTTON);
				waitForElement(driver, PA_WC_MEMBER_POST_ACTIVE_ABCPRACTICE);
				jsClickByXPath(driver, PA_WC_MEMBER_POST_ACTIVE_ABCPRACTICE);
			}
			catch(Exception e1)
			{
				jsClickByXPath(driver, NEXT_BUTTON);
				waitForElement(driver, PA_WC_MEMBER_POST_ACTIVE_ABCPRACTICE);
				jsClickByXPath(driver, PA_WC_MEMBER_POST_ACTIVE_ABCPRACTICE);
			}
		}
	}
}