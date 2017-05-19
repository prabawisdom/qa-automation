package com.zillion.qa.opsadmin;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.zillion.qa.commands.Manipulation;
public class Programs extends Manipulation implements OR
{
	/**
	 * Name         :   Abinaya
	 * Created Date :   11Feb2016
	 * Modified Date:
	 * Description  : Create a common method to type page number in guide from ops admin
	 * @return
	 * @throws AWTException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void changePageNumber(WebDriver driver, String pagenumber) throws AWTException
	{
		Manipulation.jsClickByXPath(driver, OR.OPS_ADMIN_PAGENUMBER_INPUT);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_2);
	}
	public static void compare_Real_Appeal(WebDriver driver)
	{
		WebElement program = driver.findElement(By.xpath(OR.PROGRAM_NAME_IN_LIST));
		String program_text=program.getText();
		System.out.println(program_text);
		wait(driver, "2");
		click(program);
		wait(driver, "10");
		WebElement Real_appeal1 = driver.findElement(By.xpath(OR.REAL_APPEAL_TEXT_HEADER));
		String program_text1=Real_appeal1.getText();
		System.out.println(program_text1);
		String program_text2[]=program_text1.split(":");
		System.out.println(program_text2[1]);
		if(program_text.equalsIgnoreCase(program_text2[1].trim()))
		{
			System.out.println("Strings are matched");
		}
		else
		{
			System.out.println("Strings are not matched");
		}
	}
	/**
	 * Name         :   Abinaya
	 * Created Date :   11Feb2016
	 * Modified Date:
	 * Description  : Create a common method to split and get Answers of quiz from ops admin
	 * @return
	 * @throws AWTException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String spliting_string(String st1, String two )
	{
		System.out.println("First string:"+st1);
		String s[]=st1.split("\\s+");
		System.out.println(s[0]);
		String s1=s[0];
		System.out.println(s1);
		if(s1.equalsIgnoreCase(two.trim()))
		{
			return "Strings are matched";
		}
		else
		{
			return "Strings are not matched";
		}
	}
}