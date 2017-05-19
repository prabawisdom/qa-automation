
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zillion.qa.listeners.ConsoleLoggingListener;
import org.openqa.selenium.remote.internal.ApacheHttpClient;
import org.openqa.selenium.remote.internal.HttpClientFactory;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.zillion.qa.util.MailingReport;
import com.zillion.qa.utils.Directory;
import com.zillion.qa.writers.CurrentRunPageWriter;

public class ProgramTestNG {
	public static String ExecutionType=null;
	private void testRunner(Map<String, String> testngParams) {

		TestNG testNG = new TestNG();

		XmlSuite suite = new XmlSuite();

		if(ExecutionType.equalsIgnoreCase("sequential") || ExecutionType.isEmpty())
		{
			System.out.println("Sequential Execution started");
			suite.setDataProviderThreadCount(1);
		}
		else if(ExecutionType.equalsIgnoreCase("parallel"))
		{
			System.out.println("Parallel Execution started");
			suite.setDataProviderThreadCount(4);
		}
		suite.setName("Zillion QA");

		XmlTest test = new XmlTest(suite);
		test.setName("ZT");
		test.setParameters(testngParams);

		List<XmlClass> classez = new ArrayList<XmlClass>();
		classez.add(new XmlClass("TestNGClass"));
		test.setXmlClasses(classez);

		List<XmlTest> tests = new ArrayList<XmlTest>();
		tests.add(test);
		suite.setTests(tests);

		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);
		testNG.setXmlSuites(suites);

		if (System.getProperty("com.zillion.qa.log_tests", "").equalsIgnoreCase("log")) {
			testNG.addListener(new ConsoleLoggingListener());
		}

		testNG.run();
	}


	public static void main(String args[]) throws Exception {
		workAroundSeleniumBug3951TimeoutOf3Hours();
		ProgramTestNG program = new ProgramTestNG();
		String userDirectory = System.getProperty("user.dir");
		System.setProperty("log.file.location", userDirectory);
		Map<String,String> params = new HashMap<String,String>();
		ExecutionType=System.getProperty("zado.execution.parseq").trim();
		program.testRunner(params);
		try { MailingReport.MailZipped() ;} catch (Exception e) {e.printStackTrace();}
		try { MailingReport.SendMail()   ;} catch (Exception e) {e.printStackTrace();}
		if(Directory.reexecution){
			if(CurrentRunPageWriter.FailedTestCasesCount>0)	{
				CurrentRunPageWriter.writeFailedTestCases();
				ExecutionType=System.getProperty("zado.execution.parseq").trim();
				program.testRunner(params);
				try { MailingReport.MailZipped() ;} catch (Exception e) {e.printStackTrace();}
				try { MailingReport.SendMail()   ;} catch (Exception e) {e.printStackTrace();}
			}
			else {
				System.out.println("===============================================");
				System.out.println("No Failure Test Case(s) 			   ");
				System.out.println("===============================================");
			}
		}
		//try { MailingReport.Console()   ;} catch (IOException e) {e.printStackTrace();}
		// try { MailingReport.systemShutdown()   ;} catch (IOException e) {e.printStackTrace();}
	}

	private static void workAroundSeleniumBug3951TimeoutOf3Hours() throws NoSuchFieldException, IllegalAccessException {
		// https://github.com/seleniumhq/selenium-google-code-issue-archive/issues/3951
		// "3 hour timeout before getting UnreachableBrowserException"

		if (System.getProperty("com.zillion.qa.workaround3951", "").equalsIgnoreCase("true")) {
			final Field field = ApacheHttpClient.Factory.class.getDeclaredField("defaultClientFactory");
			field.setAccessible(true);

			// set both timeouts to one minute
			field.set(null, new HttpClientFactory(211000, 211000));
		}
	}
}
