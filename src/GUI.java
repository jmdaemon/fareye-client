import java.awt.EventQueue;
/////////////////////////////////////////////////////////////////////////////////////////////////////////
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JFrame;

import java.awt.GridBagLayout;
import java.awt.CardLayout;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.sql.Timestamp;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

/*
 * Notes for next Class:
 * 
 * 
 * Reminders:
 *
 * - SearchAccount isnt working because the number is out of the array's bounds
 * 
 * fix random generator
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * Finish
 * 
 * 
 * 
 */
public class GUI {

	private JFrame frame;
	CardLayout cardLayout = new CardLayout(0, 0);

	final String LOGIN_PANEL = "LOGIN PANEL";
	final String MAIN_PANEL = "MAIN PANEL";
	final String WITHDRAW_PANEL = "WITHDRAW_PANEL";
	final String DEPOSIT_PANEL = "DEPOSIT_PANEL";
	final String RESET_PASS_PANEL = "RESET_PASS_PANEL";
	final String DEPOSIT_TO_PANEL = "DEPOSIT_TO_PANEL";
	final String WITHDRAW_FROM_PANEL = "WITHDRAW_FROM_PANEL";
	final String HISTORY_PANEL = "HISTORY_PANEL";
	final String EMPTY_ACCOUNT_PANEL = "EMPTY_ACCOUNT_PANEL";
	final String DISPLAY_PANEL = "DISPLAY_PANEL";
	final String CREATE_NEW_ACCT_PANEL = "CREATE_NEW_ACCT_PANEL";
	int MAX_COUNT = 100;
	private JTextField depositToJPdepositToAcctTF;
	private JTextField depositToJPamountTF;
	private JTextField withdrawFromJPwithdrawAmt;
	private JTextField loginJPloginTF;
	private JPasswordField passwordField;
	private JTextField withdrawJPwithdrawAmtTF;
	private JTextField depositJPdepositTF;
	private JPasswordField resetPassJPnewPass;
	private JPasswordField resetPassJPcurpass;
	private BankAccount curr;
	private BankAccount b;

	private String G_Password;
	private int A_NUMBER;
	private String BG_Password;
	private int BA_NUMBER;
	private double BA_BAL;
	//private BankAccount testAcct = new BankAccount(1, "aaa", "bbb", "ccc", 5678);
	private BankAccount users[] = new BankAccount[MAX_COUNT];
	private int numberOfUsers = 0;
	/*
	 * We want to be able to have an array which will act as a small database for our accounts
	 * Methods to add:
	 * 1) getAccount : retrieves the account as denoted by the index in the array
	 * 2) addAccount : adds a new account as specified by the data of the names
	 * 3) 
	 * 
	 */
	// boolean

	//

	//

	void updateUserInfo()
	{

	}

	void parseResult(String result)
	{	
		//System.out.println(result);
		try 
		{
			//System.out.println(result);

			if (result!=null && !result.equals("[]") && !result.equals("") && result.startsWith("{"))
			{
				JSONObject obj = new JSONObject(result);
				//BankAccount b = new BankAccount(obj.getDouble("balance"), obj.getString("f_name"), obj.getString("m_name"), obj.getString("l_name"), obj.getInt("acct_number")); 
				// curr = b;
				//System.out.println("Executing First if block.");

				System.out.println(obj.getDouble("balance"));
				System.out.println(obj.getString("f_name"));
				System.out.println(obj.getString("m_name"));
				System.out.println(obj.getInt("acct_number"));
				curr = new BankAccount(obj.getDouble("balance"), obj.getString("f_name"), obj.getString("m_name"), obj.getString("l_name"), obj.getInt("acct_number"));
				curr.setHistory(obj.getString("history"));
				System.out.print(obj.getString("history"));
			}
		} 
		catch (JSONException e) 
		{
			e.printStackTrace();
		} 

		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	void getUserInfo(String pswd, int acctNumber)
	{
		HttpURLConnectionATM http = new HttpURLConnectionATM();
		/*
		System.out.println(HttpURLConnectionATM.URL+"php/getUserInfo.php?"+ 
				"accountNum=" + acctNumber +
				"&password=" + pswd);
		 */
		try {
			http.sendPost(HttpURLConnectionATM.URL+"php/getUserInfo.php?", 
					"accountNum=" + acctNumber +
					"&password=" + pswd);

			//System.out.println(http.response.toString());
			if (http.response != null)
			{
				//System.out.println(http.response.toString());
				// retrieves entire row
				//System.out.println("Running...");
				parseResult(http.response.toString());
			}
			else
			{
				System.out.println("No User Info Retrieved!");
			}
		} 
		catch (Exception e) { e.printStackTrace(); }
	}


	void login(String pswd, int accountNumber)
	{
		//String url = "https://josephmdiza.000webhostapp.com/";
		//HttpURLConnectionATM http = new HttpURLConnectionATM();
		HttpURLConnectionATM http = new HttpURLConnectionATM();
		try {
			//System.out.println(accountNumber);

			http.sendPost(HttpURLConnectionATM.URL+"php/login.php?", 
					"account_num=" + accountNumber + 
					"&password=" + pswd);

			if (http.response != null)
			{

				if (http.response.toString().trim().equals("Login successful."))
				{

					//					System.out.println(http.response.toString());
					// handle success!
					// We'll just take the user inserted earlier

					//users[numberOfUsers] = new BankAccount (0.0, fName, mName, lName, acctNum);
					G_Password = pswd;
					A_NUMBER = accountNumber;

					getUserInfo(pswd, accountNumber);
					cardLayout.show(frame.getContentPane(), MAIN_PANEL);

				}

				else
				{
					JOptionPane.showMessageDialog(frame,
							"Error, account not found.",
							"Invalid Account Credentials",
							JOptionPane.ERROR_MESSAGE);
					/*
					System.out.println(password);
					System.out.println(curr.getPassword());
					 */
				}
			}
			else if (http.response.toString().trim().equals("Login failed."))
			{
				// handle login failed!
				JOptionPane.showMessageDialog (frame, "Incorrect username/password.", "Please try again.", JOptionPane.ERROR_MESSAGE);
			}

			else
			{
				// handle php error
				System.out.println("Php Error!\n"+http.response.toString());
			}
		}
		//}
		catch (Exception e) 
		{ 
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Server Unreachable.\nPlease try again after checking your Internet connection.", "Network Error", JOptionPane.ERROR_MESSAGE);
		}
	}


	void addAccount(String fName, String mName, String lName)
	{
		// To make this work we will need to edit the methods used to gain the data
		// Then instead of adding it directly to the bank account, we just use the website

		System.out.println("Text: " + createAcctJPfirstnameTF.getText());



		// Loop through and check if the account number is already in use

		//users[numberOfUsers] = new BankAccount (0.0, fName, mName, lName, acctNum);
		// createAcctJPfirstnameTF
		//int accountNumber;
		int acctNum = generateAcctNum();
		HttpURLConnectionATM http = new HttpURLConnectionATM();
		try {

			http.sendPost(HttpURLConnectionATM.URL+"php/addAccount.php?", 
					"firstName=" + createAcctJPfirstnameTF.getText() +
					"&lastName=" + createAcctJPlastnameTF.getText() + 
					"&middleName=" + createAcctJPmiddlenameTF.getText() +
					"&password=" + createAcctJPfirstnameTF.getText().substring(0, 3) + 
					createAcctJPlastnameTF.getText().substring(createAcctJPlastnameTF.getText().length() - 2) +
					"&accountNum=" + acctNum
					// accountNum= is now checkNum=
					);

			if (http.response != null)
			{ 

				/*
			if (http.response.toString().trim().equals("Not a valid block."))
			{
				// handle 
				JOptionPane.showMessageDialog (frame, "Block does not match your profile.", "Block Selection Error", JOptionPane.ERROR_MESSAGE);
			}*/

				// Unneeded
				//if (http.response.toString().trim().equals("Login successful."))
				//{
				// handle success
				// Loop through and check if the account number is already in use

				users[numberOfUsers] = new BankAccount (0.0, fName, mName, lName, acctNum);
			}

			else if (http.response.toString().trim().equals("Login failed."))
			{
				// handle login failed!
				JOptionPane.showMessageDialog (frame, "Incorrect username/password.", "Please try again.", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				// handle php error
				System.out.println("Php Error!\n"+http.response.toString());
			}
		}



		catch (Exception e) 
		{ 
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Server Unreachable.\nPlease try again after checking your Internet connection.", "Network Error", JOptionPane.ERROR_MESSAGE);
		}

		//for (int i = numberOfUsers; i < users.length; ++i)
		//{

		/*
		if (numberOfUsers < MAX_COUNT)
		{
			if (users[numberOfUsers + 1] == null)
			{
				numberOfUsers++;

				//System.out.println(users[numberOfUsers].getPassword());
				int acctNum = generateAcctNum();

				// Loop through and check if the account number is already in use
				for (int a = 0; a < users.length; a++)
				{
					if (users[a] != null)
					{

						if (acctNum == (users[a].getAccountNum()))
						{ // If it is in use, generate and assign the new account, a new random number
							acctNum = generateAcctNum();
						}
						else
							// Else just loop through until the end.
						{}
					}
					else
						a = MAX_COUNT;
				}
				users[numberOfUsers] = new BankAccount (0.0, fName, mName, lName, acctNum);
				//i = users.length - 1;
			}

			else
			{
				int acctNum = generateAcctNum();
				/*
					for (int a = 0; a < numberOfUsers; a++)
					{
						if (acctNum == (users[i].getAccountNum()))
						{
							acctNum = generateAcctNum();
						}
						else
						{}
					}
		 */
		/*
				users[numberOfUsers] = new BankAccount (0.0, fName, mName, lName, acctNum);
			}

			//return true;
		}
		else
			MAX_COUNT *= 5;
		//}

		//return false;
		 * */

	}

	// int current is the index of the current user

	BankAccount getAccount (int index)
	{
		// To optimize this, we should only iterate through the users to the number of users
		for (int i = 0; i < numberOfUsers; ++i)
		{
			if (i == index)
			{

				return users[index];
			}
		}

		//return users[current];
		return null;
	}

	//BankAccount
	BankAccount searchAccount(String url, HttpURLConnectionATM http, int accountNumber, String pswd)
	{
		/* Old Code 
		for (int i = 0; i < users.length; ++i)
		{
			if (users[i] != null)
			{
				if (users[i].checkPassword(pswd))
				{

					return users[i];

				}
				//else
				//System.out.println("No accounts with the corresponding password were found. ");
			}
			//else
			//System.out.println("No users found. ");
		}
		// Temporary Fix : Will adjust this to just return the current account
		return curr;
		 */

		//String url = "https://josephmdiza.000webhostapp.com/";
		//HttpURLConnectionATM http = new HttpURLConnectionATM();
		try {

			http.sendPost(url+"php/getUserInfo.php?", 
					"account_num=" + accountNumber + 
					"&password=" + pswd);




		}

		catch (Exception e) 
		{ 
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Server Unreachable.\nPlease try again after checking your Internet connection.", "Network Error", JOptionPane.ERROR_MESSAGE);
		}

		return null;

	}

	BankAccount searchAccount(int acctNum)
	{
		//boolean returned = false;

		for (int i = 0; i < users.length; i++)
		{
			if (users[i] != null)
			{
				if (users[i].getAccountNum() == acctNum)
				{
					//returned = true;
					return users[i];

				}


				//else
				//System.out.println("No accounts with the corresponding password were found. ");

			}
			//else
			//{
			//	System.out.println("Account not found");
			//	i = users.length - 1;
			//}

			//else
			//System.out.println("No users found.");
		}

		//if (returned != true)
		//{
		//	return null;
		//}
		// Temporary Fix : Will adjust this to just return the current account
		//else
		return null;


	}

	/* This doesnt exist anymore
	int getUserNum(String pswd)
	{
		BankAccount user = searchAccount(pswd);
		int acctNum = user.getAccountNum();

		return acctNum;

	}
	 */


	void initializeAccount(String password, int acctNum)
	{
		// When user logs in, this field sets the curr bank account to that of the user logging in
		// Could later change this to numberOfUsers
		for (int i = 0; i < users.length; ++i)
		{
			if (users[i].checkPassword(password))
			{
				if (users[i].getAccountNum() == acctNum)
				{
					curr = users[i];
					i = numberOfUsers;
				}
				//else
				//System.out.println("Yo yo yo you got the account number wrong yo.");
			}
			//else
			//System.out.println("Yo you cant leave the password field blank yo.");
		}
	}

	/*
	BankAccount searchAccount(int acctNum, String password)
	{
		for (int i = 0; i < users.length; i++)
		{
			if (users[i] != null)
			{
				//for (int i = 0; i < numberOfUsers; i++)
				//{
				if (users[i].checkPassword(password))
				{
					if (users[i].getAccountNum() == acctNum)
					{
						return users[i];
					}
					/*
						else
						{
							return null;
						}
	 */
	//else
	//System.out.println("Account Number not found. ");
	/*
				}
				//}
				//else
				//System.out.println("No accounts with the corresponding password were found. ");
			}
			//else
			//System.out.println("No users found. ");
		}
		// 
		return null;
	}
	 */
	
	
	boolean encryptData()
	{
		HttpURLConnectionATM http = new HttpURLConnectionATM();
		try {
			http.sendPost(HttpURLConnectionATM.URL+"php/encryptData.php?", 
					"accountNum=" + "" +
					"&password=");

			//System.out.println(http.response.toString());
			if (http.response != null)
			{
				//System.out.println(http.response.toString());
				// retrieves entire row
				//System.out.println("Running...");
				//parseResult(http.response.toString());
				return true;
			}
			else
			{
				System.out.println("No User Info Retrieved!");
			}
		} 
		catch (Exception e) { e.printStackTrace(); }
		return false;
	}
	
	int generateAcctNum()
	{
		Random randomGenerator = new Random();
		int acctNum = randomGenerator.nextInt(1000000);
		return acctNum;
	}
	//private BankAccount users[] = { curr, depositAcct };
	private JTextField withdrawFromJPwithdrawFromTF;
	private JPasswordField withdrawFromJPpassword;
	private JTextField createAcctJPfirstnameTF;
	private JTextField createAcctJPmiddlenameTF;
	private JTextField createAcctJPlastnameTF;
	/**
	 * Launch the application.-
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(cardLayout);

		JPanel loginPanel = new JPanel();
		frame.getContentPane().add(loginPanel, LOGIN_PANEL);
		loginPanel.setLayout(null);

		//addAccount("Test", "", "Last");
		//addAccount(firstName, middleName, lastName);
		//curr = searchAccount(("Tes"+ "St"));

		/*
		Date date = new Date();
	    System.out.println(new Timestamp(date.getTime()));
		 */
		/*
		JLabel mainPanelJPacctNamelbl = new JLabel("Account Name: ");
		mainPanelJPacctNamelbl.setBounds(10, 11, 115, 14);
		//mainPanel.add(mainPanelJPacctNamelbl);
		 */




		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{

				//String userName = loginJPloginTF.getText();
				//String userName = "JohnMD";

				//String userName = loginJPloginTF.getText();



				String password = "";
				//char charArray [] = ;
				password = (String.valueOf(passwordField.getPassword()));


				//if (password == "")
				//System.out.println("You cannot leave the password field blank. ");


				//String accountNumberString = ();
				int acctNumber = (Integer.valueOf(loginJPloginTF.getText()));
				
				login(password, acctNumber);

				//if (acctNumber == 0)
				//System.out.println("You cannot leave the account number field blank. ");

				//initializeAccount(password, acctNumber);
				// You can't input characters or have the field null.
				// while (password != null || password != "" || username != 0);
				// userName == "AAA"




			}
		});

		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, MAIN_PANEL);
		mainPanel.setLayout(null);

		btnLogin.setBounds(167, 110, 89, 23);
		loginPanel.add(btnLogin);

		JButton btnCreateNewAccount = new JButton("Create New Account");
		btnCreateNewAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(frame.getContentPane(), CREATE_NEW_ACCT_PANEL);
			}
		});
		btnCreateNewAccount.setBounds(153, 144, 145, 23);
		loginPanel.add(btnCreateNewAccount);

		loginJPloginTF = new JTextField();
		loginJPloginTF.setBounds(112, 47, 174, 20);
		loginPanel.add(loginJPloginTF);
		loginJPloginTF.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(112, 78, 174, 20);
		loginPanel.add(passwordField);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(26, 81, 68, 14);
		loginPanel.add(lblPassword);

		JLabel lblDontHaveAn = new JLabel("Don't have an account?");
		lblDontHaveAn.setBounds(10, 148, 285, 14);
		loginPanel.add(lblDontHaveAn);

		JLabel lblAccountNumber_1 = new JLabel("Account Number");
		lblAccountNumber_1.setBounds(10, 50, 95, 14);
		loginPanel.add(lblAccountNumber_1);

		JButton btnNewButton = new JButton("Create New Account");
		btnNewButton.setBounds(10, 107, 52, -7);
		mainPanel.add(btnNewButton);

		JButton btnResetPassword = new JButton("Reset Password");
		btnResetPassword.setBounds(10, 51, 115, 45);
		mainPanel.add(btnResetPassword);

		// Bring up Deposit Panel from Deposit button
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(frame.getContentPane(), DEPOSIT_PANEL);



			}
		});

		btnDeposit.setBounds(153, 51, 115, 45);
		mainPanel.add(btnDeposit);

		JPanel withdrawPanel = new JPanel();
		frame.getContentPane().add(withdrawPanel, WITHDRAW_PANEL);
		withdrawPanel.setLayout(null);


		JButton btnWithdraw_1 = new JButton("Withdraw");
		btnWithdraw_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				double withdrawAmount = Double.valueOf(withdrawJPwithdrawAmtTF.getText());

				//if (curr.withdraw(withdrawAmount))

				HttpURLConnectionATM http = new HttpURLConnectionATM();
				System.out.println(HttpURLConnectionATM.URL+"php/updateUser.php?"+ 
						"password=" + curr.getPassword() +
						"&balance=" + curr.getBalance() + 
						"&firstName=" + curr.gFN() +
						"&middleName=" + curr.gMN() +
						"&lastName=" + curr.gLN() + 
						"&accountNum=" + curr.getAccountNum() +
						"&choice=Withdraw" 
						);
				try {
					http.sendPost(HttpURLConnectionATM.URL+"php/updateUser.php?", 
							"password=" + curr.getPassword() +
							"&balance=" + curr.getBalance() + 
							"&firstName=" + curr.gFN() +
							"&middleName=" + curr.gMN() +
							"&lastName=" + curr.gLN() + 
							"&accountNum=" + curr.getAccountNum() +
							"&choice=Withdraw" +
							"&amount=" + withdrawAmount
							);

					System.out.println(http.response.toString());
					if (http.response != null)
					{
						System.out.print("Transaction successful.\n");
						getUserInfo(G_Password, A_NUMBER);
						System.out.println(curr.getPassword());
						System.out.println(curr.getAccountNum());
						System.out.print(curr.getBalance());
					}
					else
					{
						System.out.println("Transaction unsuccessful.\n");
						JOptionPane.showMessageDialog(frame,
								"Transaction unsuccessful.",
								"Withdrawal Invalid",
								JOptionPane.ERROR_MESSAGE);
					}


					//parseResult(http.response.toString());

				} 
				catch (Exception event) { event.printStackTrace(); }

				//System.out.println("eyy thats pretty gud my firend");
				JOptionPane.showMessageDialog(frame,
						"Transaction successful.",
						"Withdrawal",
						JOptionPane.PLAIN_MESSAGE);

				withdrawJPwithdrawAmtTF.setText("");
				cardLayout.show(frame.getContentPane(), MAIN_PANEL);

			}
		});
		btnWithdraw_1.setBounds(151, 137, 89, 45);
		withdrawPanel.add(btnWithdraw_1);

		withdrawJPwithdrawAmtTF = new JTextField();
		withdrawJPwithdrawAmtTF.setBounds(151, 106, 126, 20);
		withdrawPanel.add(withdrawJPwithdrawAmtTF);
		withdrawJPwithdrawAmtTF.setColumns(10);

		JLabel lblWithdrawAmount = new JLabel("Withdraw Amount");
		lblWithdrawAmount.setBounds(32, 109, 109, 14);
		withdrawPanel.add(lblWithdrawAmount);

		JButton btnBack_1 = new JButton("Back");
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(frame.getContentPane(), MAIN_PANEL);
			}
		});
		btnBack_1.setBounds(10, 11, 63, 23);
		withdrawPanel.add(btnBack_1);



		// Bring up Withdraw Panel from Withdraw button
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.setBounds(301, 51, 123, 45);
		mainPanel.add(btnWithdraw);

		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(frame.getContentPane(), WITHDRAW_PANEL);




			}
		});


		JPanel displayPanel = new JPanel();
		frame.getContentPane().add(displayPanel, DISPLAY_PANEL);
		displayPanel.setLayout(null);

		JLabel displayJPacctNumlbl = new JLabel("");
		displayJPacctNumlbl.setBounds(164, 106, 116, 14);
		displayPanel.add(displayJPacctNumlbl);

		JLabel displayJPacctNamelbl = new JLabel();
		displayJPacctNamelbl.setBounds(164, 81, 116, 14);
		displayPanel.add(displayJPacctNamelbl);


		JLabel displayJPbalancelbl = new JLabel("");
		displayJPbalancelbl.setBounds(164, 131, 116, 14);
		displayPanel.add(displayJPbalancelbl);

		// Bring up Display Panel from Display button
		JButton btnDisplay = new JButton("Display");
		btnDisplay.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cardLayout.show(frame.getContentPane(), DISPLAY_PANEL);
				displayJPacctNamelbl.setText(curr.getName());
				//displayJPacctNamelbl.
				displayJPacctNumlbl.setText(String.valueOf(curr.getAccountNum()));
				displayJPbalancelbl.setText(String.valueOf(curr.getBalance()));

			}
		});

		btnDisplay.setBounds(10, 107, 115, 45);
		mainPanel.add(btnDisplay);

		// Bring up Display_To Panel from Display To button
		JButton btnDepositTo = new JButton("Deposit To");
		btnDepositTo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cardLayout.show(frame.getContentPane(), DEPOSIT_TO_PANEL);
			}
		});

		btnDepositTo.setBounds(153, 107, 115, 45);
		mainPanel.add(btnDepositTo);

		// Bring up Withdraw_From Panel from Withdraw from button
		JButton btnWithdrawFrom = new JButton("Withdraw From");
		btnWithdrawFrom.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cardLayout.show(frame.getContentPane(), WITHDRAW_FROM_PANEL);


			}
		});

		btnWithdrawFrom.setBounds(301, 107, 123, 45);
		mainPanel.add(btnWithdrawFrom);

		// Bring up History Panel from History button



		// Bring up EmptyAccount Panel from Empty Account button
		JButton btnEmptyAccount = new JButton("Empty Account");
		btnEmptyAccount.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cardLayout.show(frame.getContentPane(), EMPTY_ACCOUNT_PANEL);

			}
		});

		btnEmptyAccount.setBounds(301, 163, 123, 45);
		mainPanel.add(btnEmptyAccount);

		JButton btnBack = new JButton("Logout");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(frame.getContentPane(), LOGIN_PANEL);
			}
		});
		btnBack.setBounds(10, 11, 98, 23);
		mainPanel.add(btnBack);



		JPanel despositPanel = new JPanel();
		frame.getContentPane().add(despositPanel, DEPOSIT_PANEL);
		despositPanel.setLayout(null);

		depositJPdepositTF = new JTextField();
		depositJPdepositTF.setBounds(164, 119, 128, 20);
		despositPanel.add(depositJPdepositTF);
		depositJPdepositTF.setColumns(10);

		//Deposit Button with new panel
		JButton btnDeposit_1 = new JButton("Deposit");
		btnDeposit_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cardLayout.show(frame.getContentPane(), DEPOSIT_PANEL);

				//String amount = depositJPdepositTF.getText();
				//int depositAmount = Integer.valueOf(amount);
				int depositAmount = Integer.valueOf(depositJPdepositTF.getText());
				HttpURLConnectionATM http = new HttpURLConnectionATM();

				// First we'll try and grab the balance for the other account by pinging getUserInfo




				try {
					http.sendPost(HttpURLConnectionATM.URL+"php/updateUser.php?", 
							"password=" + curr.getPassword() +
							"&balance=" + curr.getBalance() + 
							"&firstName=" + curr.gFN() +
							"&middleName=" + curr.gMN() +
							"&lastName=" + curr.gLN() + 
							"&accountNum=" + curr.getAccountNum() +
							"&choice=Deposit" +
							"&amount=" + depositAmount
							);

					System.out.println(http.response.toString());
					if (http.response != null)
					{
						System.out.print("Transaction successful.\n");
						getUserInfo(G_Password, A_NUMBER);
						System.out.println(curr.getPassword());
						System.out.println(curr.getAccountNum());
						System.out.print(curr.getBalance());



						//System.out.println("eyy thats pretty gud my firend");
						JOptionPane.showMessageDialog(frame,
								"Transaction successful.",
								"Withdrawal",
								JOptionPane.PLAIN_MESSAGE);


					}

					else
					{
						//System.out.println("Please try again.");
						JOptionPane.showMessageDialog(frame,
								"Deposit unsuccessful.",
								"Deposit Invalid",
								JOptionPane.ERROR_MESSAGE);
						
					}
				}
				catch (Exception event) { event.printStackTrace(); }

				depositJPdepositTF.setText("");
				cardLayout.show(frame.getContentPane(), MAIN_PANEL);
			}
		});
		btnDeposit_1.setBounds(161, 150, 89, 40);
		despositPanel.add(btnDeposit_1);



		JLabel lblDepositAmount = new JLabel("Deposit Amount");
		lblDepositAmount.setBounds(48, 122, 106, 14);
		despositPanel.add(lblDepositAmount);

		JButton btnBack_3 = new JButton("Back");
		btnBack_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(frame.getContentPane(), MAIN_PANEL);
			}
		});
		btnBack_3.setBounds(10, 11, 65, 23);
		despositPanel.add(btnBack_3);


		// Reset Password button with new panel
		JPanel resetPasswordPanel = new JPanel();
		frame.getContentPane().add(resetPasswordPanel, RESET_PASS_PANEL);
		resetPasswordPanel.setLayout(null);

		// Bring up Reset Password Panel from Reset Password button
		btnResetPassword.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cardLayout.show(frame.getContentPane(), RESET_PASS_PANEL);





			}
		});

		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				char currPassword[] = (resetPassJPcurpass.getPassword());
				String currentPassword = String.valueOf(currPassword);
				char newPassword[] = resetPassJPnewPass.getPassword();

				//String currentPassword = (resetPassJPcurpass.getPassword()).toString();
				//String newPassword = (resetPassJPnewPass.getPassword()).toString();


				String newPass = String.valueOf(newPassword);

				HttpURLConnectionATM http = new HttpURLConnectionATM();

				// First we'll try and grab the balance for the other account by pinging getUserInfo
				try {
					http.sendPost(HttpURLConnectionATM.URL+"php/updateUser.php?", 
							"password=" + currentPassword +
							"&balance=" + curr.getBalance() + 
							"&firstName=" + curr.gFN() +
							"&middleName=" + curr.gMN() +
							"&lastName=" + curr.gLN() + 
							"&accountNum=" + curr.getAccountNum() +
							"&choice=ResetPass" +
							"&newPass=" + newPass
							);

					System.out.println(http.response.toString());
					if (http.response != null)
					{
						System.out.print("Password change successful.\n");
						getUserInfo(G_Password, A_NUMBER);
						//getUserInfo(BG_Password, BA_NUMBER);
						System.out.println(curr.getPassword());
						System.out.println(curr.getAccountNum());
						System.out.print(curr.getBalance());

						JOptionPane.showMessageDialog(frame,
								"Password change successful.",
								"Password Change",
								JOptionPane.PLAIN_MESSAGE);

					}

					else
					{
						System.out.println("Transaction unsuccessful.\n");
						JOptionPane.showMessageDialog(frame,
								"Password change unsuccessful.",
								"Password Change Invalid",
								JOptionPane.ERROR_MESSAGE);
					}
					//parseResult(http.response.toString());

				} catch (Exception event) { event.printStackTrace(); }

				resetPassJPcurpass.setText("");
				resetPassJPnewPass.setText("");
				/*
	if (curr.resetPassword(currentPassword, newPass))
	{
		//System.out.println("Eyy thats pretty good. ");
		JOptionPane.showMessageDialog(frame,
				"Password change successful.",
				"Password Change",
				JOptionPane.PLAIN_MESSAGE);

		resetPassJPcurpass.setText("");
		resetPassJPnewPass.setText("");
		cardLayout.show(frame.getContentPane(), MAIN_PANEL);
	}

	else
	{
		//System.out.println("Please try again. ");
		JOptionPane.showMessageDialog(frame,
				"Password change unsuccessful.",
				"Password Change Invalid",
				JOptionPane.ERROR_MESSAGE);
		resetPassJPcurpass.setText("");
		resetPassJPnewPass.setText("");

	}*/
			}
		});

		btnEnter.setBounds(153, 163, 89, 39);
		resetPasswordPanel.add(btnEnter);

		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setBounds(47, 135, 225, 14);
		resetPasswordPanel.add(lblNewPassword);

		resetPassJPnewPass = new JPasswordField();
		resetPassJPnewPass.setBounds(153, 132, 119, 20);
		resetPasswordPanel.add(resetPassJPnewPass);

		resetPassJPcurpass = new JPasswordField();
		resetPassJPcurpass.setBounds(153, 101, 119, 20);
		resetPasswordPanel.add(resetPassJPcurpass);

		JLabel lblCurrentPassword = new JLabel("Current Password");
		lblCurrentPassword.setBounds(37, 104, 235, 14);
		resetPasswordPanel.add(lblCurrentPassword);

		JButton backButton_2 = new JButton("Back");
		backButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(frame.getContentPane(), MAIN_PANEL);
			}
		});
		backButton_2.setBounds(10, 11, 64, 23);
		resetPasswordPanel.add(backButton_2);

		JPanel depositToPanel = new JPanel();
		frame.getContentPane().add(depositToPanel, DEPOSIT_TO_PANEL);
		depositToPanel.setLayout(null);

		depositToJPdepositToAcctTF = new JTextField();
		depositToJPdepositToAcctTF.setBounds(155, 95, 132, 20);
		depositToPanel.add(depositToJPdepositToAcctTF);
		depositToJPdepositToAcctTF.setColumns(10);

		JLabel lblDepositTo = new JLabel("Deposit To:");
		lblDepositTo.setBounds(75, 98, 212, 14);
		depositToPanel.add(lblDepositTo);

		depositToJPamountTF = new JTextField();
		depositToJPamountTF.setBounds(155, 126, 132, 20);
		depositToPanel.add(depositToJPamountTF);
		depositToJPamountTF.setColumns(10);

		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(97, 129, 190, 14);
		depositToPanel.add(lblAmount);

		JButton btnDepositTo_1 = new JButton("Enter");
		btnDepositTo_1.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){

				//String accountNumber = depositToJPdepositToAcctTF.getText();
				//int acctNum = Integer.valueOf(accountNumber);
				// Use this for finding the bank account number later in the database

				/* You could also create a temporary bank account with the number
				 * for now. But should have an array of bank accounts to act as a database
				 * to search for and look for the bank account with the specific bank account number/id
				 * private BankAccount depositToAcct = new BankAccount(foo, bar, foo, bar);
				 * 
				 * 
				 * 
				 */


				//String amount = depositToJPamountTF.getText();
				int giveAmount = Integer.valueOf(depositToJPamountTF.getText());
				//depositAcct
				int acctNum = Integer.valueOf((depositToJPdepositToAcctTF.getText()));
				/*
				if (searchAccount(acctNum) == curr)
				{
					JOptionPane.showMessageDialog(frame,
						    "You can't transfer funds to yourself.",
						    "Transaction Invalid",
						    JOptionPane.ERROR_MESSAGE);
					acctNum = -1;
				}
				 */
				HttpURLConnectionATM http = new HttpURLConnectionATM();

				// First we'll try and grab the balance for the other account by pinging getUserInfo
				try {
					http.sendPost(HttpURLConnectionATM.URL+"php/getUserInfoEX.php?",
							"accountNum=" + acctNum);

					if (http.response != null)
					{
						String result = http.response.toString();
						System.out.print(result);
						try 
						{
							// 	if (result!=null && !result.equals("[]") && !result.equals("") && result.startsWith("{"))
							if (result!=null && !result.equals("[]") && !result.equals("") && result.startsWith("{"))
							{
								JSONObject obj = new JSONObject(http.response.toString());

								System.out.println("Executing inside of withdrawFrom: ");
								System.out.println(obj.getDouble("balance"));
								System.out.println(obj.getString("f_name"));
								System.out.println(obj.getString("m_name"));
								System.out.println(obj.getInt("acct_number"));
								b = new BankAccount(obj.getDouble("balance"), obj.getString("f_name"), obj.getString("m_name"), obj.getString("l_name"), obj.getInt("acct_number"));
								System.out.println("Balance of b: " + b.getBalance());
								BA_NUMBER = b.getAccountNum();
								BG_Password = b.getPassword();
								// And we'll assign to to the BA_BAL variable.
								BA_BAL = b.getBalance();

								try {
									http.sendPost(HttpURLConnectionATM.URL+"php/updateUser.php?", 
											"password=" + curr.getPassword() +
											"&balance=" + curr.getBalance() + 
											"&firstName=" + curr.gFN() +
											"&middleName=" + curr.gMN() +
											"&lastName=" + curr.gLN() + 
											"&accountNum=" + curr.getAccountNum() +
											"&choice=DepositTo" +
											"&amount=" + giveAmount +
											"&otherUserBal=" + BA_BAL + 
											"&otherUserNum=" + acctNum +
											"&otherUserPas=" + BG_Password
											);

									System.out.println(http.response.toString());

									if (http.response != null)
									{
										System.out.print("Transaction successful.\n");
										getUserInfo(G_Password, A_NUMBER);
										//getUserInfo(BG_Password, BA_NUMBER);
										System.out.println(curr.getPassword());
										System.out.println(curr.getAccountNum());
										System.out.print(curr.getBalance());

										JOptionPane.showMessageDialog(frame,
												"Funds transferred",
												"Transaction Processed",
												JOptionPane.PLAIN_MESSAGE);

									}

									else
									{
										System.out.println("Transaction unsuccessful.\n");
										JOptionPane.showMessageDialog(frame,
												"Transaction unsuccessful.",
												"Withdrawal Invalid",
												JOptionPane.ERROR_MESSAGE);
									}


									//parseResult(http.response.toString());

								} catch (Exception event) { event.printStackTrace(); }

							}			
						} catch (JSONException event) { event.printStackTrace(); }
					}

				} catch (Exception event) { event.printStackTrace(); }
				depositToJPamountTF.setText("");
				depositToJPdepositToAcctTF.setText("");
				cardLayout.show(frame.getContentPane(), MAIN_PANEL);
				/*
				BankAccount b = searchAccount(acctNum);

				if (b != null)
				{
					if (curr.transferTo(giveAmount, b))
					{

						//System.out.println("This is working as intended. ");
						JOptionPane.showMessageDialog(frame,
								"Deposit Successful.",
								"Transaction Processed",
								JOptionPane.PLAIN_MESSAGE);

						depositToJPamountTF.setText("");
						depositToJPdepositToAcctTF.setText("");
						cardLayout.show(frame.getContentPane(), MAIN_PANEL);
					}

					else
					{
						//System.out.println("y");
						depositToJPamountTF.setText("");
						depositToJPdepositToAcctTF.setText("");
						JOptionPane.showMessageDialog(frame,
								"Deposit Unsuccessful.",
								"Transaction Invalid",
								JOptionPane.ERROR_MESSAGE);
					}
				}

				else
				{
					JOptionPane.showMessageDialog(frame,
							"Deposit Unsuccessful.",
							"No accounts found",
							JOptionPane.ERROR_MESSAGE);

				}
				 */
			}
		});
		btnDepositTo_1.setBounds(155, 157, 99, 33);
		depositToPanel.add(btnDepositTo_1);

		JButton backButton_3 = new JButton("Back");
		backButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(frame.getContentPane(), MAIN_PANEL);
			}
		});
		backButton_3.setBounds(10, 11, 64, 23);
		depositToPanel.add(backButton_3);

		JPanel withdrawFromPanel = new JPanel();
		frame.getContentPane().add(withdrawFromPanel, WITHDRAW_FROM_PANEL);
		withdrawFromPanel.setLayout(null);

		withdrawFromJPwithdrawAmt = new JTextField();
		withdrawFromJPwithdrawAmt.setBounds(167, 143, 113, 20);
		withdrawFromPanel.add(withdrawFromJPwithdrawAmt);
		withdrawFromJPwithdrawAmt.setColumns(10);

		JLabel lblAmount_1 = new JLabel("Amount");
		lblAmount_1.setBounds(102, 146, 178, 14);
		withdrawFromPanel.add(lblAmount_1);

		JButton WithdrawFromJPEnterbtn = new JButton("Enter");
		WithdrawFromJPEnterbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Double withdrawFromAmt = Double.valueOf(withdrawFromJPwithdrawAmt.getText());
				String otherAcctPassword = String.valueOf(withdrawFromJPpassword.getPassword());
				int acctNum = Integer.valueOf(withdrawFromJPwithdrawFromTF.getText());

				//System.out.println("Password: " + otherAcctPassword);
				//BankAccount otherAccount = searchAccount(acctNum, otherAcctPassword);

				//HttpURLConnectionATM http = new HttpURLConnectionATM();
				//BankAccount b = searchAccount(HttpURLConnectionATM.URL, http, acctNum, otherAcctPassword);

				//if (b != null)
				//{
				//if (curr.transferFrom(withdrawFromAmt, b, otherAcctPassword))
				//{
				//System.out.println("This is working as intended.");
				HttpURLConnectionATM http = new HttpURLConnectionATM();

				// First we'll try and grab the balance for the other account by pinging getUserInfo
				try {
					http.sendPost(HttpURLConnectionATM.URL+"php/getUserInfo.php?",
							"accountNum=" + acctNum +
							"&password=" + otherAcctPassword);

					if (http.response != null)
					{
						String result = http.response.toString();
						System.out.print(result);
						try 
						{
							// 	if (result!=null && !result.equals("[]") && !result.equals("") && result.startsWith("{"))
							if (result!=null && !result.equals("[]") && !result.equals("") && result.startsWith("{"))
							{
								JSONObject obj = new JSONObject(http.response.toString());

								System.out.println("Executing inside of withdrawFrom: ");
								System.out.println(obj.getDouble("balance"));
								System.out.println(obj.getString("f_name"));
								System.out.println(obj.getString("m_name"));
								System.out.println(obj.getInt("acct_number"));
								b = new BankAccount(obj.getDouble("balance"), obj.getString("f_name"), obj.getString("m_name"), obj.getString("l_name"), obj.getInt("acct_number"));
								System.out.println("Balance of b: " + b.getBalance());
								BA_NUMBER = b.getAccountNum();

								// And we'll assign to to the BA_BAL variable.
								BA_BAL = b.getBalance();


								try {
									http.sendPost(HttpURLConnectionATM.URL+"php/updateUser.php?", 
											"password=" + curr.getPassword() +
											"&balance=" + curr.getBalance() + 
											"&firstName=" + curr.gFN() +
											"&middleName=" + curr.gMN() +
											"&lastName=" + curr.gLN() + 
											"&accountNum=" + curr.getAccountNum() +
											"&choice=WithdrawFrom" +
											"&amount=" + withdrawFromAmt +
											"&otherUserBal=" + BA_BAL + 
											"&otherUserNum=" + acctNum +
											"&otherUserPas=" + otherAcctPassword
											);

									System.out.println(http.response.toString());

									if (http.response != null)
									{
										System.out.print("Transaction successful.\n");
										getUserInfo(G_Password, A_NUMBER);
										//getUserInfo(BG_Password, BA_NUMBER);
										System.out.println(curr.getPassword());
										System.out.println(curr.getAccountNum());
										System.out.print(curr.getBalance());

										JOptionPane.showMessageDialog(frame,
												"Funds transferred",
												"Transaction Processed",
												JOptionPane.PLAIN_MESSAGE);

									}
									//parseResult(http.response.toString());

								} catch (Exception event) { event.printStackTrace(); }

							}			
						} catch (JSONException e) { e.printStackTrace(); }
					}

					else
					{
						System.out.println("Transaction unsuccessful.\n");
						JOptionPane.showMessageDialog(frame,
								"Transaction unsuccessful.",
								"Withdrawal Invalid",
								JOptionPane.ERROR_MESSAGE);
					}

				} catch (Exception event) { event.printStackTrace(); }





				/*

				 */

				withdrawFromJPwithdrawFromTF.setText("");
				withdrawFromJPpassword.setText("");
				withdrawFromJPwithdrawAmt.setText("");
				cardLayout.show(frame.getContentPane(), MAIN_PANEL);

				//}

				/*
					else
					{
						//System.out.println("just y");
						JOptionPane.showMessageDialog(frame,
								"Transaction not processed",
								"Transaction Invalid",
								JOptionPane.ERROR_MESSAGE);
					}
				 */
				//}
				/*
				else
				{
					JOptionPane.showMessageDialog(frame,
							"Transaction not processed",
							"Transaction Invalid",
							JOptionPane.ERROR_MESSAGE);
				}
				 */

			}
		});

		WithdrawFromJPEnterbtn.setBounds(167, 174, 113, 30);
		withdrawFromPanel.add(WithdrawFromJPEnterbtn);

		withdrawFromJPwithdrawFromTF = new JTextField();
		withdrawFromJPwithdrawFromTF.setColumns(10);
		withdrawFromJPwithdrawFromTF.setBounds(167, 81, 113, 20);
		withdrawFromPanel.add(withdrawFromJPwithdrawFromTF);

		JLabel label = new JLabel("Withdraw From:");
		label.setBounds(74, 84, 206, 14);
		withdrawFromPanel.add(label);

		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setBounds(102, 115, 178, 14);
		withdrawFromPanel.add(lblPassword_1);

		withdrawFromJPpassword = new JPasswordField();
		withdrawFromJPpassword.setBounds(167, 112, 113, 20);
		withdrawFromPanel.add(withdrawFromJPpassword);

		JButton backButton_4 = new JButton("Back");
		backButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(frame.getContentPane(), MAIN_PANEL);
			}
		});
		backButton_4.setBounds(10, 11, 64, 23);
		withdrawFromPanel.add(backButton_4);

		JPanel emptyAcctPanel = new JPanel();
		frame.getContentPane().add(emptyAcctPanel, EMPTY_ACCOUNT_PANEL);

		JButton backButton_5 = new JButton("Back");
		backButton_5.setBounds(10, 11, 64, 23);
		backButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(frame.getContentPane(), MAIN_PANEL);
			}
		});
		emptyAcctPanel.setLayout(null);
		emptyAcctPanel.add(backButton_5);

		JLabel lblEmptyBankAccount = new JLabel("Empty Bank Account?");
		lblEmptyBankAccount.setBounds(44, 93, 131, 23);
		emptyAcctPanel.add(lblEmptyBankAccount);

		JRadioButton rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.setBounds(181, 93, 109, 23);
		emptyAcctPanel.add(rdbtnYes);

		JButton btnEnter_1 = new JButton("Enter");
		btnEnter_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				System.out.println("Executing");
				if (rdbtnYes.isSelected())
				{
					System.out.println("Executing inside of Radio BTN");
					HttpURLConnectionATM http = new HttpURLConnectionATM();

					// First we'll try and grab the balance for the other account by pinging getUserInfo

					//String result = http.response.toString();
					//System.out.print(result);
					try 
					{
						// 	if (result!=null && !result.equals("[]") && !result.equals("") && result.startsWith("{"))

						http.sendPost(HttpURLConnectionATM.URL+"php/updateUser.php?", 
								"password=" + curr.getPassword() +
								"&balance=" + curr.getBalance() + 
								"&firstName=" + curr.gFN() +
								"&middleName=" + curr.gMN() +
								"&lastName=" + curr.gLN() + 
								"&accountNum=" + curr.getAccountNum() +
								"&choice=EmptyAccount"
								);

						System.out.println(http.response.toString());

						if (http.response != null)
						{
							//System.out.print("Transaction successful.\n");
							System.out.println("Executing inside HTTP RESPONSE");
							getUserInfo(G_Password, A_NUMBER);
							//getUserInfo(BG_Password, BA_NUMBER);
							System.out.println(curr.getPassword());
							System.out.println(curr.getAccountNum());
							System.out.print(curr.getBalance());

							JOptionPane.showMessageDialog(frame,
									"Account successfully emptied.",
									"Empty Account",
									JOptionPane.PLAIN_MESSAGE);

						}

						else
						{
							System.out.println("Transaction unsuccessful.\n");
							JOptionPane.showMessageDialog(frame,
									"Account not emptied.",
									"Empty Account ",
									JOptionPane.ERROR_MESSAGE);
						}
						//parseResult(http.response.toString());

					} catch (Exception event) { event.printStackTrace(); }

				}			


				else
				{
					System.out.println("Transaction unsuccessful.\n");
					JOptionPane.showMessageDialog(frame,
							"Account not emptied.",
							"Empty Account ",
							JOptionPane.ERROR_MESSAGE);
				}






				/*
					curr.emptyAccount();
					//System.out.println("This is also working as intended.");
					JOptionPane.showMessageDialog(frame,
							"Account successfully emptied.",
							"Empty Account",
							JOptionPane.PLAIN_MESSAGE);
					cardLayout.show(frame.getContentPane(), MAIN_PANEL);
				 */

				//}

				/*
				else if (rdbtnNo.isSelected())
				{
					//System.out.println("This is not working as intended.");
					JOptionPane.showMessageDialog(frame,
						    "Account not emptied.",
						    "Empty Account ",
						    JOptionPane.ERROR_MESSAGE);

				}
				 */
			}
		});
		btnEnter_1.setBounds(131, 153, 89, 23);
		emptyAcctPanel.add(btnEnter_1);

		JPanel historyPanel = new JPanel();
		frame.getContentPane().add(historyPanel, HISTORY_PANEL);
		historyPanel.setLayout(null);

		JButton backButton_6 = new JButton("Back");
		backButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(frame.getContentPane(), MAIN_PANEL);
			}
		});
		backButton_6.setBounds(11, 13, 64, 23);
		historyPanel.add(backButton_6);

		JLabel lblHistory = new JLabel("History");
		lblHistory.setBounds(42, 47, 46, 14);
		historyPanel.add(lblHistory);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 72, 337, 151);
		historyPanel.add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		/*
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(130, 53, 120, 20);
		historyPanel.add(comboBox);
		 */


		JLabel lblAccountName_1 = new JLabel("Account Name");
		lblAccountName_1.setBounds(57, 81, 97, 14);
		displayPanel.add(lblAccountName_1);

		JLabel lblAccountNumber = new JLabel("Account Number");
		lblAccountNumber.setBounds(47, 106, 107, 14);
		displayPanel.add(lblAccountNumber);

		JLabel lblBalance = new JLabel("Balance");
		lblBalance.setBounds(94, 131, 60, 14);
		displayPanel.add(lblBalance);

		JButton btnBack_2 = new JButton("Back");
		btnBack_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(frame.getContentPane(), MAIN_PANEL);
			}
		});
		btnBack_2.setBounds(10, 11, 70, 23);
		displayPanel.add(btnBack_2);










		JPanel createNewAcctPanel = new JPanel();
		frame.getContentPane().add(createNewAcctPanel, CREATE_NEW_ACCT_PANEL);
		createNewAcctPanel.setLayout(null);

		JButton backButton_7 = new JButton("Back");
		backButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(frame.getContentPane(),LOGIN_PANEL);
			}
		});
		backButton_7.setBounds(10, 11, 64, 23);
		createNewAcctPanel.add(backButton_7);

		createAcctJPfirstnameTF = new JTextField();
		createAcctJPfirstnameTF.setBounds(124, 63, 136, 20);
		createNewAcctPanel.add(createAcctJPfirstnameTF);
		createAcctJPfirstnameTF.setColumns(10);

		JLabel lblFirstName = new JLabel("First Name ");
		lblFirstName.setBounds(34, 66, 80, 14);
		createNewAcctPanel.add(lblFirstName);

		createAcctJPmiddlenameTF = new JTextField();
		createAcctJPmiddlenameTF.setBounds(124, 94, 136, 20);
		createNewAcctPanel.add(createAcctJPmiddlenameTF);
		createAcctJPmiddlenameTF.setColumns(10);

		JLabel lblMiddleName = new JLabel("Middle Name ");
		lblMiddleName.setBounds(18, 97, 80, 14);
		createNewAcctPanel.add(lblMiddleName);

		createAcctJPlastnameTF = new JTextField();
		createAcctJPlastnameTF.setBounds(124, 125, 136, 20);
		createNewAcctPanel.add(createAcctJPlastnameTF);
		createAcctJPlastnameTF.setColumns(10);

		JLabel lblLastName = new JLabel("Last Name ");
		lblLastName.setBounds(34, 122, 64, 14);
		createNewAcctPanel.add(lblLastName);

		JButton btnHistory = new JButton("History");
		btnHistory.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cardLayout.show(frame.getContentPane(), HISTORY_PANEL);
				textArea.setText(curr.getMessage());

			}
		});

		btnHistory.setBounds(10, 163, 115, 45);
		mainPanel.add(btnHistory);
		JButton createAcctJPbtnEnter = new JButton("Enter");
		createAcctJPbtnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstName = createAcctJPfirstnameTF.getText();
				String middleName = createAcctJPmiddlenameTF.getText();
				String lastName = createAcctJPlastnameTF.getText();

				if (firstName != "" || firstName != null)
				{
					if (firstName.length() > 2)
					{
						if (middleName != "" || middleName != null)
						{
							if (middleName.length() > 2)
							{
								if (lastName != "" || lastName != null)
								{
									if (lastName.length() > 1)
									{

										addAccount(firstName, middleName, lastName);
										//curr = searchAccount((firstName.substring(0, 3) + lastName.substring(0, 2)));
										JOptionPane.showMessageDialog(frame,
												"New Account Creation Successful! Your new account number is: " + "\"" + users[numberOfUsers].getAccountNum() + "\"" + ". " + 
														"Your new account password is: " + "\"" + firstName.substring(0, 3) + lastName.substring(lastName.length() - 2) //+ lastName.substring(lastName.length() - 1)
														+ "\"",
														"Account Creation Successful",
														JOptionPane.PLAIN_MESSAGE);

										//System.out.println("New Account Creation Successful. ");
										//System.out.println("Your new account number is: " + numberOfUsers);
										//System.out.println("Your new account password is: " + firstName.substring(0, 3) + lastName.substring(0, 2));
										// Comment this out when finished testing
										//+ lastName.substring(lastName.length() - 2)

										//curr = searchAccount(firstName.substring(0, 3) + lastName.substring(lastName.length() - 2));

										createAcctJPfirstnameTF.setText("");
										createAcctJPmiddlenameTF.setText("");
										createAcctJPlastnameTF.setText("");
										cardLayout.show(frame.getContentPane(), LOGIN_PANEL);


									}
									else
									{
										//System.out.println("Your last name must be greater than 1 character. ");
										JOptionPane.showMessageDialog(frame,
												"Your last name must be greater than 1 character. ",
												"Invalid Last Name",
												JOptionPane.ERROR_MESSAGE);
									}
								}
								else
								{
									//System.out.println("You cannot leave the Last name field blank. ");
									JOptionPane.showMessageDialog(frame,
											"Your last name is invalid. ",
											"Invalid Last Name",
											JOptionPane.ERROR_MESSAGE);
								}
							}
							else
							{
								//System.out.println("Your middle name must be greater than 2 characters. ");
								JOptionPane.showMessageDialog(frame,
										"Your middle name must be greater than 2 characters. ",
										"Invalid Middle Name",
										JOptionPane.ERROR_MESSAGE);
							}
						}
						else
						{
							//System.out.println("You cannot leave the middle name field blank. ");
							JOptionPane.showMessageDialog(frame,
									"Your middle name is invalid. ",
									"Invalid Middle Name",
									JOptionPane.ERROR_MESSAGE);
						}

					}
					else
					{
						//System.out.println("Your first name must be greater than 2 characters. ");
						JOptionPane.showMessageDialog(frame,
								"Your first name must be more than 2 characters. ",
								"Invalid First Name",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					//System.out.println("You cannot leave the first name field blank. ");
					JOptionPane.showMessageDialog(frame,
							"Your first name is invalid. ",
							"Invalid First Name",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		createAcctJPbtnEnter.setBounds(146, 156, 89, 23);
		createNewAcctPanel.add(createAcctJPbtnEnter);


	}
}
