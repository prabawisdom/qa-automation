package com.zillion.qa.vspnmember;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.zillion.qa.commands.Manipulation;
import com.zillion.qa.utils.Directory;

public class member extends Manipulation implements OR {

	/**
	 * Name :Vigneshraj.M         
	 * Created Date:07/Jan/2017    
	 * Modified Date:     
	 * Description :   Create a common method to retrieve the VSPN member to schedule 1 on 1 session from member
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String retrieveVSPNMemberToSchedule1on1Session(WebDriver driver) throws ClassNotFoundException, SQLException 
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
		ResultSet rs = stat.executeQuery("SELECT ACNT.EMAIL Member_Email FROM SUMMARY_ACCOUNT_TODATE SMRY, ACCOUNT ACNT WHERE SMRY.ACCOUNT_ID=ACNT.ID AND ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ID IN (SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_TYPE_ID='3E1035B3DD3E0C94E0530100007FE6CC' AND SESSION_STATUS  IN ('Unscheduled'))) ORDER BY SMRY.CREATED_DT DESC;");
		String member_mail1="";
		System.out.println("query executed");
		while(rs.next())
		{     
			member_mail1 = rs.getString("MEMBER_EMAIL");
		}
		System.out.println("Member Mail id is "+member_mail1);
		return member_mail1;
	}

	/**
	 * Name :Vigneshraj.M         
	 * Created Date:07/Jan/2017    
	 * Modified Date:     
	 * Description :   Create a common method to get the Session Attended time by the Member
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public  static String memberSessionTime(WebDriver driver) throws ParseException
	{
		String sessiontime1=null;
		WebElement memberSessionTime = driver.findElement(By.xpath(OR.RA_MEMBER_SESSION_TIME));
		String sessionsTime=memberSessionTime.getText();
		SimpleDateFormat ft = new SimpleDateFormat("EEE hhh:mm a");
		java.util.Date t=ft.parse(sessionsTime);
		ft.applyPattern("hh:mma");
		sessiontime1=ft.format(t).toLowerCase().trim();
		System.out.print(ft.format(t));
		System.out.print("Time"+sessiontime1);
		return sessiontime1;
	}

	/**
	 * Name :Vigneshraj.M         
	 * Created Date:07/Jan/2017    
	 * Modified Date:     
	 * Description :   Create a common method to Click Attendnow for the Member attended time
	 * Required Inputs :
	 *
	 */
	public  static String vspnPAAttendNowButtonSameBrowser(WebDriver driver, String ScheduleTime31) 
	{
		WebElement memberSessionTime = driver.findElement(By.xpath("//div[@id='mentorUpcomingSessions']//tbody//tr/td[contains(text(),'"+ScheduleTime31+"')]//following-sibling::td/following-sibling::td[6]/div/a[contains(text(),'Attend Now')]"));
		click(memberSessionTime);
		return ScheduleTime31;
	}

}
