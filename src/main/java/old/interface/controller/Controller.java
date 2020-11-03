public class Controller {
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
				SecretKey key;
				byte[] IV;
                                Arrays.fill( IV, (byte) 0 );
				
				String unencryptedResult = Encryption.decrypt(result.getBytes(), key, IV);
				JSONObject obj = new JSONObject(unencryptedResult);
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
			/*
			http.sendPost(HttpURLConnectionATM.URL+"php/login.php?", 
					"account_num=" 	+ 	Encryption.encrypt(account_num, key, IV) +
					"password=" 	+ 	Encryption.encrypt(pswd.getBytes("UTF-8"), key, IV) +
					"key=" 			+	key +
					"IV="			+	IV
					);
			*/
			System.out.println(http.response.toString());
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


	void login(String pswd, int accountNumber) throws NoSuchAlgorithmException
	{
		//String url = "https://josephmdiza.000webhostapp.com/";
		//HttpURLConnectionATM http = new HttpURLConnectionATM();
		
		HttpURLConnectionATM http = new HttpURLConnectionATM();
		// byte[] key = (Encryption.genKey()).toString().getBytes("UTF-8"); 
		Integer acctNum = Integer.valueOf(accountNumber);
		byte[] account_num = Integer.toString(acctNum).getBytes();
		// byte[] account_num = ByteBuffer.allocate().putInt(accountNumber).array();
		SecretKey key = Encryption.genKey();
		byte[] iv = Encryption.genIV();
		// byte[] IV = new String(iv).getBytes("UTF-8");
		// IvParameterSpec ivSpec = Encryption.getIVSpec();
		// SecretKeySpec keySpec = Encryption.getKeySpec();
		try {
			
			http.sendPost(HttpURLConnectionATM.URL+"php/login.php?", 
					"account_num=" 	+ 	Base64.getEncoder().encodeToString(Encryption.encrypt(account_num, key, iv)) +
					"&password=" 	+ 	Base64.getEncoder().encodeToString(Encryption.encrypt(pswd.getBytes(), key, iv)) +
					"&key=" 		+	Base64.getEncoder().encodeToString(key.getEncoded()) +
					"&IV="			+	Base64.getEncoder().encodeToString(iv)
					);
			
			System.out.println(HttpURLConnectionATM.URL+"php/login.php?" +
					"account_num=" 	+ 	Base64.getEncoder().encodeToString(Encryption.encrypt(account_num, key, iv)) +
					"&password=" 	+ 	Base64.getEncoder().encodeToString(Encryption.encrypt(pswd.getBytes(), key, iv)) +
					"&key=" 		+	Base64.getEncoder().encodeToString(key.getEncoded()) +
					"&IV="			+	Base64.getEncoder().encodeToString(iv) +
					"&Encoding="	+ key.getFormat()
					);
			// http.sendPost(HttpURLConnectionATM.URL+"php/testEncrypt.php?", "");
			// System.out.println(Base64.getEncoder().encodeToString(IV));
//			System.out.println(Base64.getEncoder().encodeToString(key.getEncoded()));
//			System.out.println(Base64.getEncoder().encodeToString(IV));
			System.out.println(http.response);
			// http.sendPost(HttpURLConnectionATM.URL+"php/login.php?request=", request);
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
}
