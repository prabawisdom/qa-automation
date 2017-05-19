package com.zillion.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.zillion.qa.utils.Directory;
import com.zillion.qa.utils.Platform;
import com.zillion.qa.writers.CurrentRunPageWriter;
import com.zillion.qa.writers.TestCaseReportsPageWriter;

public class MailingReport
{

	private static String getCurrentTime;
	private static String Zipfolder_path = Directory.Zipfolder_Path;
	static PrintWriter printwriter;

	public static void main( String[] args ) throws IOException, MessagingException {
		SendMail();    
	}

	public static void SendMail() throws IOException, MessagingException {

		final String username = Directory.userName;
		final String password = Directory.password;
		final String subject = Directory.Subject;
		final String environment = Directory.zado_environment;
		Properties props = new Properties();
		props.put( "mail.smtp.starttls.enable", true );
		props.put( "mail.smtp.auth", true );
		props.put( "mail.smtp.host", Directory.smtpHost );
		props.put( "mail.smtp.port", "587" );
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		Message message = new MimeMessage( session );
		message.setFrom( new InternetAddress( Directory.fromAddress ) );
		message.setRecipients( Message.RecipientType.TO,
				InternetAddress.parse( Directory.toAddress ) );
		message.setRecipients( Message.RecipientType.CC,
				InternetAddress.parse( Directory.ccAddress ) );

		String[] environmentName = environment.split("-");
		String sub = subject +" "+environmentName[1];
		message.setSubject( sub );

		MimeBodyPart messageBodyPart1 = new MimeBodyPart();
		String file = Zipfolder_path + getCurrentTime + ".zip";
		File srcfile = new File( file );
		DataSource source = new FileDataSource( file );
		messageBodyPart1.setDataHandler( new DataHandler( source ) );
		float size = ( srcfile.length() / 1024 );
		if ( size < 25600 )  {
			messageBodyPart1.attachFile( file );
		}
		else {
			messageBodyPart1.setContent( "<br>"
					+ "<p> Attachment file Size is more than 25 MB </p>",
					"text/html" );
		}

		String passCount = String.valueOf( CurrentRunPageWriter.passCount );
		String failCount = String.valueOf( CurrentRunPageWriter.failCount );
		String skipCount = String.valueOf( CurrentRunPageWriter.skipCount );
		String totalRun = String.valueOf(
				CurrentRunPageWriter.passCount +
				CurrentRunPageWriter.failCount +
				CurrentRunPageWriter.skipCount );
		String executionType = System.getProperty( "zado.execution.parseq" ).trim();

		final String msg = "<b><h2 ALIGN=CENTER>ZAutomate Results</h2></b>\n" +
				"<table ALIGN=CENTER border=2>\n" +
				"  <thead>\n" +
				"    <tr style=\"color:black;\">\n" +
				"      <th ALIGN=LEFT>Test Run Details</th>\n" +
				"      <th>&nbsp;</th>\n" +
				"    </tr>\n" +
				"  </thead>\n" +
				"  <tbody>\n" +
				"    <tr BGCOLOR=LightBlue style=\"color:black;\">\n" +
				"      <td ALIGN=LEFT>Total TestCases</td>\n" +
				"      <td ALIGN=RIGHT>" + totalRun + "</td>\n" +
				"    </tr>\n" +
				"    <tr BGCOLOR=LimeGreen style=\"color:black;\">\n" +
				"      <td ALIGN=LEFT>Passed List</td>\n" +
				"      <td ALIGN=RIGHT>" + passCount + "</td>\n" +
				"    </tr>\n" +
				"    <tr BGCOLOR=LightPink style=\"color:black;\">\n" +
				"      <td ALIGN=LEFT>Failed List</td>\n" +
				"      <td ALIGN=RIGHT>" + failCount + "</td>\n" +
				"    </tr>\n" +
				"    <tr BGCOLOR=SandyBrown style=\"color:black;\">\n" +
				"      <td ALIGN=LEFT>Skipped List</td>\n" +
				"      <td ALIGN=RIGHT>" + skipCount + "</td>\n" +
				"    </tr>\n" +
				"    <tr BGCOLOR=Khaki style=\"color:black;\">\n" +
				"      <td ALIGN=LEFT>OS Name</td>\n" +
				"      <td ALIGN=RIGHT>" + Platform.OS.toUpperCase() + "</td>\n" +
				"    </tr>\n" +
				"    <tr BGCOLOR=LightGreen style=\"color:black;\">\n" +
				"      <td ALIGN=LEFT>Browser Name</td>\n" +
				"      <td ALIGN=RIGHT>" + Directory.browser.toUpperCase() + "</td>\n" +
				"    </tr>\n" +
				"    <tr BGCOLOR=OliveDrab style=\"color:black;\">\n" +
				"      <td ALIGN=LEFT>Execution Type</td>\n" +
				"      <td ALIGN=RIGHT>" + executionType.toUpperCase() + "</td>\n" +
				"    </tr>\n" +
				"    <tr BGCOLOR=LightSkyBlue style=\"color:black;\">\n" +
				"      <td ALIGN=LEFT>HostName</td>\n" +
				"      <td ALIGN=RIGHT>" + Platform.getHostName().toUpperCase() + "</td>\n" +
				"    </tr>\n" +
				"  </tbody>\n" +
				"</table>\n" +
				"<p>This mail sent from <cite><strong><ul><u><A HREF=\"http://zado-tech.com\">Zado Infotech Solution</A></u></ul></strong></cite></p>";
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(msg, "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart( messageBodyPart );

		String logFile = TestCaseReportsPageWriter.createOrGetFile();
		if (new File(logFile).isFile()) {
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
			messageBodyPart2.setDataHandler(new DataHandler(new FileDataSource(logFile)));
			messageBodyPart2.attachFile(logFile);
			multipart.addBodyPart( messageBodyPart2 );
		}

		message.setContent( multipart );
		Transport.send( message );

		System.out.println("===============================================");
		System.out.println("Report Mail has been Sent			   ");
		System.out.println("===============================================");
	}

	public static void MailZipped() throws Exception
	{
		File directoryToZip = new File( Directory.Reports_Path );
		List< File > fileList = new ArrayList< File >();
		getAllFiles( directoryToZip, fileList );
		writeZipFile( directoryToZip, fileList );
	}

	public static void getAllFiles( File dir, List< File > fileList )
			throws InterruptedException {

		File[] files = dir.listFiles();
		for ( File file : files )
		{
			fileList.add( file );
			if ( file.isDirectory() ) {
				getAllFiles( file, fileList );
			}
			else {
				// System.out.println("file:" + file.getCanonicalPath());
			}
		}
	}

	public static void writeZipFile( File directoryToZip, List< File > fileList )
			throws Exception  {
		try { 
			// E yyyy.MM.dd 'at' hh:mm:ss a
			getCurrentTime = new SimpleDateFormat( "E_yyyy_MM_dd_HH_mm_ss_a" )
					.format( Calendar.getInstance().getTime() );
			FileOutputStream fos = new FileOutputStream( Zipfolder_path
					+ getCurrentTime + ".zip" );
			ZipOutputStream zos = new ZipOutputStream( fos );
			for ( File file : fileList ) {
				if ( !file.isDirectory() ) {
					addToZip( directoryToZip, file, zos );
				}
			}
			zos.close();
			fos.close();
		}
		catch ( FileNotFoundException e ) {
			e.printStackTrace();
		}
		catch ( IOException e ) {
			e.printStackTrace();
		}
	}

	public static void addToZip( File directoryToZip, File file,
			ZipOutputStream zos ) throws FileNotFoundException, IOException  {
		FileInputStream fis = new FileInputStream( file );
		String zipFilePath = file.getCanonicalPath().substring(
				directoryToZip.getCanonicalPath().length() + 1,
				file.getCanonicalPath().length() );
		ZipEntry zipEntry = new ZipEntry( zipFilePath );
		zos.putNextEntry( zipEntry );
		byte[] bytes = new byte[ 1024 ];
		int length;
		while ( ( length = fis.read( bytes ) ) >= 0 )
		{
			zos.write( bytes, 0, length );
		}
		zos.closeEntry();
		fis.close();
	}

	/***
	 * Name : Gobi.E
	 * Title : System Shutdown
	 * Purpose : Automatically system has to be shutdown once execution is complete
	 * @throws IOException
	 */
	public static void systemShutdown() throws IOException {
		Runtime runtime = Runtime.getRuntime();
		Process proc = runtime.exec("shutdown -l -t 30");//.exec("shutdown -l");//.exec("shutdown -l -t 0").exec("shutdown -r");		
		System.out.println(""+proc);
		System.exit(0);
	}

}