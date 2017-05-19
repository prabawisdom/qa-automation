package com.zillion.qa.programadmin;
import org.openqa.selenium.WebDriver;

import com.zillion.qa.utils.Directory;
public class Messages
{
	/**
	 * Name         :   Abinaya.P
	 * Created Date :   23/Nov/16
	 * Modified Date:
	 * Description  : To get the program admin from the config file
	 * Required Inputs:
	 *
	 */
	public static String getPAFromConfigFile(WebDriver driver)
	{
		String value=Directory.PracticePA2name;
		return value;
	}
}
