import java.io.IOException;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import com.sun.net.httpserver.HttpExchange;
import java.net.http.HttpClient;

/**
 * Basic INFO
 * Program made for easy manual change the Dynamic Domain Name System Changes IP
 * @author ShadowFox
 * @version 1.0
 */

@SuppressWarnings("unused")
public class BaseProgram
{
    protected final static String kuser = "";
    protected final static String kpass = "";
    protected final static String domain = "";
    
	public static void main(String[] args) throws IOException
	{
		//Start of Program...
		int Chose = JOptionPane.showConfirmDialog(null, "Your IP: " + GetIP() + "\nChange Google Domain IP?");
		switch(Chose) 
		{
			case 0:
				// Yes Case = Run
				//Show one Message Dialog with confirmation of status, IP is Successfully set or no
				JOptionPane.showMessageDialog(null, CheckStatus());
				break;
			case 1:
				// No Case = Exit
				break;
			case 2:
				// Cancel Case = Exit
				break;
			default:
				// Default Case = Exit
				break;
		}
	}
		
	public static String CheckStatus() throws MalformedURLException, IOException
	{
		
		String string = SetIP();
		String keyword = "good" + GetIP();
		String Status = "";
		Boolean found = Arrays.asList(string.split(" ")).contains(keyword);
		if(found)
		{
			//IF IP successfully changed
			Status = "New IP Successfully Set";
		}
		else
		{
			//IF IP not changed
			Status = "IP Already Set, Exited Without Changes";
		}
		return Status;
	}
		
	@SuppressWarnings("resource")
	public static String SetIP() throws MalformedURLException, IOException
	{
		Authenticator.setDefault(new MyAuthenticator());
		URL url = new URL("https://domains.google.com/nic/update?" + domain + "&myip=" + GetIP());
		InputStream ins = url.openConnection().getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
		Scanner SCANNER = new Scanner(url.openConnection().getInputStream());
		StringBuffer BUFFER = new StringBuffer();
		String str = "";
		
		while(SCANNER.hasNext())
		{
			BUFFER.append(SCANNER.next());
		}
		String OUTPUT = BUFFER.toString();
		return OUTPUT;
	}
	
	@SuppressWarnings("resource")
	public static String GetIP() throws IOException
	{
		//Variables 
			URL URL = new URL("http://meuip.com/api/meuip.php");
			Scanner SCANNER = new Scanner(URL.openStream());
			StringBuffer BUFFER = new StringBuffer();
			
			
		//Enhancements
			while(SCANNER.hasNext())
			{
				BUFFER.append(SCANNER.next());
			}
			String IP = BUFFER.toString();
			IP = IP.replaceAll("<[^>]*>", "");
			return IP;
		//Exit of GetIP() is IP from meuip.com
	}
	
    static class MyAuthenticator extends Authenticator 
    {
        protected PasswordAuthentication getPasswordAuthentication()
        {
            // Basic HTTP authentication that needs for Google Domain open
            System.err.println("Feeding username and password for " + getRequestingScheme());
            return (new PasswordAuthentication(kuser, kpass.toCharArray()));
        }
    }
	
}
