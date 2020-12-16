import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Window.Type;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;

/**
 * Basic INFO
 * Program made for easy manual change the Dynamic Domain Name System Changes IP
 * @author ShadowFox
 * @version 1.1
 * Now with own GUI :D
 */

@SuppressWarnings("unused")
public class MainWindow extends JFrame 
{
	//Basic input variables from program
	protected static String domain = "";
	protected static String kuser = "";
    protected static String kpass = "";
    
    //Main Frame variables
	private JPanel contentPane;
    private static JTextField DomainField;
    private static JTextField UserField;
    private static JTextField PasswordField;
	private static final long serialVersionUID = 1L;
	
	//Application Startup
	public static void main(String[] args) throws IOException 
	{
		MainWindow frame = new MainWindow();
		frame.setVisible(true);
	}

	//Create the main frame
	public MainWindow() throws IOException 
	{
		//General Main Window Settings
		setType(Type.UTILITY);
		setTitle("DynamicDNS Tool");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 542, 360);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//Set IP Button
		JButton GoButton = new JButton("Set New IP");
		GoButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					JOptionPane.showMessageDialog(null, CheckStatus());
					System.exit(1);
				} 
				catch (MalformedURLException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GoButton.setBounds(390, 282, 126, 28);
		contentPane.add(GoButton);
		
		
		//Computer IP Text
		//Border
		JPanel IPBorder = new JPanel();
		IPBorder.setBorder(new TitledBorder(new LineBorder(new Color(204, 153, 0), 2, true), "IP", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(204, 153, 0)));
		IPBorder.setBackground(Color.DARK_GRAY);
		IPBorder.setBounds(6, 6, 514, 58);
		contentPane.add(IPBorder);
		IPBorder.setLayout(null);
		//Field
		JLabel IPLabel = new JLabel(GetIP());
		IPLabel.setFont(new Font("Lucida Sans", Font.PLAIN, 22));
		IPLabel.setForeground(Color.LIGHT_GRAY);
		IPLabel.setBounds(10, 11, 494, 36);
		IPBorder.add(IPLabel);
		
		
		//Domain Field
		//Border
		JPanel DomainBorder = new JPanel();
		DomainBorder.setLayout(null);
		DomainBorder.setBorder(new TitledBorder(new LineBorder(new Color(204, 153, 0), 2, true), "Domain", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(204, 153, 0)));
		DomainBorder.setBackground(Color.DARK_GRAY);
		DomainBorder.setBounds(6, 75, 514, 58);
		contentPane.add(DomainBorder);
		//Field
		DomainField = new JTextField();
		DomainField.setForeground(Color.LIGHT_GRAY);
		DomainField.setBounds(10, 19, 494, 28);
		DomainBorder.add(DomainField);
		DomainField.setFont(new Font("Lucida Sans", Font.PLAIN, 22));
		DomainField.setBackground(Color.DARK_GRAY);
		DomainField.setColumns(10);
		
		
		
		//User Field
		//Border
		JPanel UserBorder = new JPanel();
		UserBorder.setLayout(null);
		UserBorder.setBorder(new TitledBorder(new LineBorder(new Color(204, 153, 0), 2, true), "User", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(204, 153, 0)));
		UserBorder.setBackground(Color.DARK_GRAY);
		UserBorder.setBounds(6, 144, 514, 58);
		contentPane.add(UserBorder);
		//Field
		UserField = new JTextField();
		UserField.setForeground(Color.LIGHT_GRAY);
		UserField.setFont(new Font("Lucida Sans", Font.PLAIN, 22));
		UserField.setColumns(10);
		UserField.setBackground(Color.DARK_GRAY);
		UserField.setBounds(10, 19, 494, 28);
		UserBorder.add(UserField);

		
		
		//Password Field
		//Border
		JPanel PasswordBorder = new JPanel();
		PasswordBorder.setLayout(null);
		PasswordBorder.setBorder(new TitledBorder(new LineBorder(new Color(204, 153, 0), 2, true), "Password", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(204, 153, 0)));
		PasswordBorder.setBackground(Color.DARK_GRAY);
		PasswordBorder.setBounds(6, 213, 514, 58);
		contentPane.add(PasswordBorder);
		//Field
		PasswordField = new JTextField();
		PasswordField.setForeground(Color.LIGHT_GRAY);
		PasswordField.setFont(new Font("Lucida Sans", Font.PLAIN, 22));
		PasswordField.setColumns(10);
		PasswordField.setBackground(Color.DARK_GRAY);
		PasswordField.setBounds(10, 19, 494, 28);
		PasswordBorder.add(PasswordField);

		
	}
	
	//Check status of IP change in Google Domains
	public static String CheckStatus() throws MalformedURLException, IOException
	{
		domain = DomainField.getText().toLowerCase();
		kuser = UserField.getText();
		kpass = PasswordField.getText();
		
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

	//Change IP in Google Domains System
	@SuppressWarnings({ "resource", "unused" })
	public static String SetIP() throws MalformedURLException, IOException
	{
		Authenticator.setDefault(new MyAuthenticator());
		URL url = new URL("https://domains.google.com/nic/update?hostname=" + domain + "&myip=" + GetIP());
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
	
	//Commands to get IP from "meuip.com" API
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
	
	//Authentication process to Google Domains
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
