package com.zillion.qa.member;
import java.sql.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.zillion.qa.commands.Manipulation;
import com.zillion.qa.commands.Navigate;
import com.zillion.qa.utils.Directory;
public class Tracker extends Manipulation implements OR {
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   03/Feb/16 
	 * Modified Date:     
	 * Description :   Create a common method to retrieve user id of member for Journal    
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static String userid1 = "";
	public static String dbMemberUserid(WebDriver driver,String input) throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.jdbc.Driver");
		String url ="jdbc:mysql://"+Directory.Sql_Hostname+":"+Directory.Sql_Port+"/"+Directory.Sql_Databasename+"";
		Connection conn = DriverManager.getConnection(url,Directory.Sql_User,Directory.Sql_Pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();	
		System.out.println("Member mail id: "+input);
		ResultSet rs = stat.executeQuery("select userid from account where eMail = '"+input+"'");
		System.out.println("query executed");
		if(rs.next())
		{
			userid1 = rs.getString("userid");
			System.out.println("userid:"+userid1);
		}
		else
		{
			System.out.println("No results found from the sql execution");
		}
		return userid1;
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   28/Jan/16 
	 * Modified Date:     
	 * Description :   Create a common method to validate journal of the member against database 
	 * Ticket ID :  Member_Tracker_AddEditNote   
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static String validationDbJournal(WebDriver driver,String journal) throws ClassNotFoundException, SQLException 
	{ 
		String notesText = "";
		Class.forName("com.mysql.jdbc.Driver");
		String url ="jdbc:mysql://"+Directory.Sql_Hostname+":"+Directory.Sql_Port+"/"+Directory.Sql_Databasename+"";
		Connection conn = DriverManager.getConnection(url,Directory.Sql_User,Directory.Sql_Pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String systemdate=dateFormat.format(cal.getTime());
		System.out.println(systemdate);
		ResultSet rs = stat.executeQuery("select dailyentrydate, notesText from dailyentry where userid = '"+userid1+"'and dailyentrydate = '"+systemdate+"'");
		System.out.println("query executed");
		while(rs.next())
		{
			notesText = rs.getString("notesText");
			System.out.println(notesText);
			if(notesText.equals(journal))
			{
				System.out.println("Journal entered from member portal matches with Database");
			}
			else
			{
				System.out.println("Journal entered from member portal does not match with Database");
				Assert.fail();
			}
		}
		return notesText;
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   22/Feb/16 
	 * Modified Date:     
	 * Description :   Create a common method to calculate maximum value of starting weight from member portal
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static String calculateMaxValueofStartingWeight(WebDriver driver)  
	{
		Manipulation.wait(driver, "15");
		WebElement memberStartingWeight = driver.findElement(By.xpath(OR.STARTING_WEIGHT));
		String weight=memberStartingWeight.getText();
		System.out.println("weight:"+weight);
		float weight1=Float.parseFloat(weight);
		System.out.println("weight1:"+weight1);
		float weight2=weight1+100;
		System.out.println("weight2:"+weight2);
		String weight3=weight2+"";
		System.out.println("weight3:"+weight3);
		WebElement trackerWeightInput = driver.findElement(By.xpath(OR.TRACKER_WEIGHT_INPUT));
		Manipulation.clearAndType(trackerWeightInput,weight3);
		Double currentWeightDouble = new Double(weight3);	
		System.out.println("currentWeightDouble:"+currentWeightDouble);
		DecimalFormat df = new DecimalFormat("####0");
		String pound1 =String.format(df.format(currentWeightDouble));
		return pound1;
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   22/Feb/16 
	 * Modified Date:     
	 * Description :   Create a common method to calculate more than maximum value of starting weight from member portal
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static String calculateMoreThanMaxValueofStartingWeight(WebDriver driver)  
	{
		Manipulation.wait(driver, "5");
		WebElement memberStartingWeight = driver.findElement(By.xpath(OR.STARTING_WEIGHT));
		String weight=memberStartingWeight.getText();
		float weight1=Float.parseFloat(weight);
		float weight2=weight1+120;
		String weight3=weight2+"";
		WebElement trackerWeightInput = driver.findElement(By.xpath(OR.TRACKER_WEIGHT_INPUT));
		Manipulation.clearAndType(trackerWeightInput,weight3);
		return weight3;
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   22/Feb/16 
	 * Modified Date:     
	 * Description :   Create a common method to retrieve current weight of the member
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static String retrieveCurrentWeight(WebDriver driver, String userId1) throws ClassNotFoundException, SQLException  
	{
		String weight="";
		String port = Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		System.out.println(userId1+" Mailid");
		ResultSet rs = stat.executeQuery("select starting_weight from account_measurement m, account a, account_program p where a.id=m.account_id and p.id=m.account_program_id and p.is_active = 1 and email='"+userId1+"'");
		System.out.println("query executed");
		if(rs.next())
		{
			weight=rs.getString("starting_weight");
		}
		Float weightfloat=Float.parseFloat(weight);

		DecimalFormat df = new DecimalFormat("0.0");
		df.setMaximumFractionDigits(2);
		String startingweight = df.format(weightfloat);
		return startingweight;
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   22/Feb/16 
	 * Modified Date:     
	 * Description :   Create a common method to calculate maximum value of starting weight from Dashboard tab of member portal
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static String calculateMoreThanMaxValueofStartingWeightDashboard(WebDriver driver)  
	{
		Manipulation.jsClickByXPath(driver, OR.MEMBER_TRACKER); 
		Navigate.waitTime(driver, "5");
		WebElement memberStartingWeight = driver.findElement(By.xpath(OR.STARTING_WEIGHT));
		String weight=memberStartingWeight.getText();
		float weight1=Float.parseFloat(weight);
		float weight2=weight1+120;
		String weight3=weight2+"";
		Manipulation.jsClickByXPath(driver, OR.MEMBER_DASHBOARD); 
		Navigate.waitTime(driver, "5");
		Manipulation.waitForElement(driver, OR.MEMBER_DASHBOARD_WEIGHT_INPUT);
		WebElement trackerWeightInput = driver.findElement(By.xpath(OR.MEMBER_DASHBOARD_WEIGHT_INPUT));
		Manipulation.clearAndType(trackerWeightInput,weight3);
		return weight3;
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   22/Feb/16 
	 * Modified Date:     
	 * Description :   Create a common method to calculate more than maximum value of starting weight from Dashboard tab of member portal
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static String calculateMaxValueofStartingWeightDashboard(WebDriver driver)  
	{
		Manipulation.jsClickByXPath(driver, OR.MEMBER_TRACKER); 
		Manipulation.wait(driver, "3");
		WebElement memberStartingWeight = driver.findElement(By.xpath(OR.STARTING_WEIGHT));
		String weight=memberStartingWeight.getText();
		float weight1=Float.parseFloat(weight);
		float weight2=weight1+100;
		String weight3=weight2+"";
		Manipulation.jsClickByXPath(driver, OR.MEMBER_DASHBOARD); 
		Manipulation.wait(driver, "3");
		Manipulation.waitForElement(driver, OR.MEMBER_DASHBOARD_WEIGHT_INPUT);
		WebElement trackerWeightInput = driver.findElement(By.xpath(OR.MEMBER_DASHBOARD_WEIGHT_INPUT));
		Manipulation.clear(trackerWeightInput);
		Manipulation.actionType(driver, trackerWeightInput, weight3);
		return weight3;
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   26/Feb/16 
	 * Modified Date:     
	 * Description :   Create a common method to retrieve account id of the member
	 * Ticket ID :     
	 * Testcase sheet:Member_Tracker_MemberEntersWeight.xlsx
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static String accountId="";
	public static String retrieveAccountIdOfTheMember(WebDriver driver, String mailId) throws ClassNotFoundException, SQLException  
	{
		String port = Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select ID from ACCOUNT where EMAIL = '"+mailId+"' ");
		System.out.println("query executed");
		while(rs.next())
		{
			accountId=rs.getString("ID");
		}
		return accountId;
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   26/Feb/16 
	 * Modified Date:     
	 * Description :   Create a common method to retrieve height of the member
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static String retrieveHeightOfTheMember(WebDriver driver) throws ClassNotFoundException, SQLException  
	{
		String height="";
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass =Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select HEIGHT from ACCOUNT_MEASUREMENT where ACCOUNT_ID = '"+accountId+"' ");
		System.out.println("query executed");
		while(rs.next())
		{
			height = rs.getString("HEIGHT");
			System.out.println("HEIGHT="+height);
		}
		return height;
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   26/Feb/16 
	 * Modified Date:     
	 * Description :   Create a common method to calculate BMI member 
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static String calculateBMI(WebDriver driver,String height,String currentweight)  
	{
		String BMI="";
		float BMIint=0;
		float height1 = Float.parseFloat(height);
		float currentweight1 = Float.parseFloat(currentweight);
		BMIint = ((currentweight1 * 703)/(height1 * height1));
		DecimalFormat df = new DecimalFormat("#.#");
		BMI=df.format(BMIint);
		BMI= "" + BMI;
		return BMI;
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   22/Feb/16 
	 * Modified Date:     
	 * Description :   Create a common method to calculate more than maximum value of starting weight from Dashboard tab of member portal
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static String covertGramsToPounds(WebDriver driver, String weight)  
	{
		float fweight = Float.parseFloat(weight);
		System.out.println("fweight"+fweight);
		fweight=(float) (fweight*0.00220462);
		String weight3= fweight+"";
		return weight3;
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   22/Feb/16 
	 * Modified Date:     
	 * Description :   Create a common method 
	 */
	public static String splitWeightAndLbs(WebDriver driver, String weight)  
	{
		System.out.println("split :"+weight);
		String[] splitIbs=weight.split("\\s+");
		String splitIbs1=splitIbs[0];
		System.out.println("split :"+splitIbs1);
		Double splitIbs13=Double.parseDouble(splitIbs1);
		DecimalFormat df = new DecimalFormat("#.0");
		String splitIbs12=df.format(splitIbs13);
		System.out.println("split 12:"+splitIbs12);
		return splitIbs12;
	}
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   03/NOV/16 
	 * Modified Date:     
	 * Description :   Create a common method to validated the current day is present in the Trackers activity history 
	 */
	public static String fitnessDayViewValidateActivityHistory(WebDriver driver)  
	{
		String currentDay = new SimpleDateFormat("E").format(Calendar.getInstance().getTime());
		String currentMonth = new SimpleDateFormat("M").format(Calendar.getInstance().getTime());
		String currentDate = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
		String combinedCurentDate= currentDay+","+" "+currentMonth+"-"+currentDate;
		System.out.println("Combined current date format for fitness activity history: "+combinedCurentDate);
		return combinedCurentDate;
	}
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   03/NOV/16 
	 * Modified Date:     
	 * Description :   Create a common method to check the count for the different 
	 */
	public static String activityHistoryCountVerify (WebDriver driver,String testData, String getCount2)
	{
		int getCount= Integer.parseInt(getCount2);
		int testData1= Integer.parseInt(testData);
		if(getCount==testData1)
		{
			System.out.println("Both the count are same: "+getCount);
		}
		else if(getCount!=testData1)
		{
			Assert.fail("Both the count are not same");
		}
		return getCount2;
	}
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   03/NOV/16 
	 * Modified Date:     
	 * Description :   Create a common method to type current date in start and end date
	 */
	public static String typeCurrentDateStartEndDate (WebDriver driver)
	{
		String currentMonth = new SimpleDateFormat("MM").format(Calendar.getInstance().getTime());
		String currentDate = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
		String currentYear = new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
		String slash= "/";
		String dateFormat= currentMonth+slash+currentDate+slash+currentYear;
		WebElement customStartDate = driver.findElement(By.xpath(OR.MEMBER_TRACKERS_FITNESS_TAB_CUSTOM_SUBTAB_START_DATE));
		click(customStartDate);
		sendKeys(customStartDate, dateFormat);
		WebElement customEndDate = driver.findElement(By.xpath(OR.MEMBER_TRACKERS_FITNESS_TAB_CUSTOM_SUBTAB_END_DATE));
		click(customEndDate);
		sendKeys(customEndDate, dateFormat);
		return dateFormat;
	}
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   03/NOV/16 
	 * Modified Date:     
	 * Description :   Create a common method to type current date in start and end date
	 */
	public static String providerTypeCurrentDateStartEndDate (WebDriver driver)
	{
		String currentMonth = new SimpleDateFormat("MMMMM").format(Calendar.getInstance().getTime());
		String currentDate = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
		System.out.println("CurrentMonth : "+currentMonth);
		System.out.println("Current Date: "+currentDate);
		wait(driver, "3");
		WebElement currentMonth1 = driver.findElement(By.xpath(OR.START_DATE_MONTH_OR_START+currentMonth+START_DATE_OR_END));
		click(currentMonth1);
		wait(driver, "3");
		WebElement currentDay1 = driver.findElement(By.xpath(OR.START_DATE_DAY_OR_START+currentDate+START_DATE_OR_END));
		click(currentDay1);
		wait(driver, "3");
		return currentMonth;
	}
	/**
	 * Name :     VIGNESHRAJ.M
	 * Created Date:   03/NOV/16 
	 * Modified Date:     
	 * Description :   Create a common method to type current date in start and end date
	 */
	public static String weightProviderTypeCurrentDateStartEndDate (WebDriver driver)
	{
		String currentMonth = new SimpleDateFormat("MMMMM").format(Calendar.getInstance().getTime());
		String currentDate = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
		System.out.println("CurrentMonth : "+currentMonth);
		System.out.println("Current Date: "+currentDate);
		wait(driver, "3");
		WebElement currentMonth1 = driver.findElement(By.xpath(OR.START_DATE_MONTH_OR_START+currentMonth+START_DATE_OR_END));
		click(currentMonth1);
		wait(driver, "3");
		WebElement currentDay1 = driver.findElement(By.xpath(OR.WEIGHT_START_DATE_DAY_OR_START+currentDate+START_DATE_OR_END));
		click(currentDay1);
		wait(driver, "3");
		return currentMonth;
	}
	/**
	 * Name :     ABINAYA.P
	 * Created Date:   09/Nov/16 
	 * Modified Date:     
	 * Description :   Create a common method to retrieve member to validate tracker details
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static String getOrberaMemberForTracker(WebDriver driver) throws ClassNotFoundException, SQLException  
	{
		String mail="";
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass =Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select acnt.last_name, acnt.first_name, acnt.email, ai.MAP_SYSTEM_NAME from ACCOUNT acnt,ACCOUNT_PROGRAM AP, account_identity_map ai WHERE AP.ACCOUNT_ID = acnt.ID and ai.account_id=acnt.id AND AP.NAME='Orbera Program: Post-placement for 52 weeks' and acnt.status='Active' and AP.START_DT_TIME>SYSDATE + INTERVAL '-52' DAY and MAP_SYSTEM_NAME='VALIDIC'");
		System.out.println("query executed");
		while(rs.next())
		{
			mail = rs.getString("EMAIL");
		}
		System.out.println("Member mail="+mail);
		return mail;
	}
	/**
	 * Name :Vigneshraj     
	 * Created Date: 10/NOV/2016  
	 * Modified Date:  
	 * Description : Create a common method to Live Session 1on1 Coach Login
	 * Ticket ID :   
	 * Required Inputs :  No Inputs Required 
	 * @throws ParseException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public  static String coachLoginWithRefEmail(WebDriver driver, String coachMail)
	{
		wait(driver, "5");
		Navigate.get(driver, Directory.Coachesurl);
		wait(driver, "5");
		Navigate.maximize(driver);
		WebElement Coacheslogin_logo= driver.findElement(By.xpath(OR.COACHES_LOGIN_PAGE_LOGO_REF));
		waitForElement( driver, OR.COACHES_LOGIN_PAGE_LOGO_REF );
		verifyElementIsPresent(driver, Coacheslogin_logo);
		WebElement coach_username= driver.findElement(By.xpath(OR.COACHES_USERNAME_1));
		verifyElementIsPresent(driver, coach_username);
		WebElement coach_password= driver.findElement(By.xpath(OR.COACHES_PASSWORD));
		verifyElementIsPresent(driver, coach_password);
		WebElement Coacheslogin_button= driver.findElement(By.xpath(OR.COACHES_LOGIN_BUTTON));
		verifyElementIsPresent(driver, Coacheslogin_button);
		sendKeys(coach_username,coachMail);
		try
		{
			wait(driver, "2");
			sendKeys(coach_password,"Healthfleet2015");
			wait(driver, "2");
			click(Coacheslogin_button);
			wait(driver, "2");
			try
			{
				wait(driver, "2");
				clear( coach_password );
				sendKeys(coach_password,"Zillion2016");
				wait(driver, "2");
				click(Coacheslogin_button);
				wait(driver, "2");
				try
				{
					wait(driver, "2");
					clear( coach_password );
					sendKeys(coach_password,"Healthfleet2016");
					wait(driver, "2");
					click(Coacheslogin_button);
					wait(driver, "3");
				}
				catch(Exception e)
				{
					System.out.println("Password are in matched");
				}
			}
			catch(Exception e)
			{
				System.out.println("Password not in matched");
			}
		}
		catch(Exception e)
		{
			System.out.println("Password are in matched");
		}
		Navigate.waitTime(driver, "20");
		System.out.println("Coaches Logged in successfully");
		return coachMail;
	}
}