import java.util.Random;
public class BankAccount {
	private	int accountNum;
	private	double balance;
	private	String firstName;
	private	String middleName;
	private	String lastName;
	private	String password;
	private	String message;
	
	
	
	// For bank account timestamps, you can use a char array
	// When looping through the message, keep track of the last /n and the next /n
	// Create subtrings of these messages and assign them to the char array
	// Then sort the strings in the array from last to most recent
	// 
	BankAccount(double amt,	String	fName,	String	mName,	String	lName,	int acctNum)
	{
		
		if (amt > -1 && fName!= "" && mName!= "" && lName.length() > 1 && acctNum > 0)
		{
			balance = amt;
			firstName = fName;
			middleName = mName;
			lastName = lName;
			accountNum = acctNum;
			password = firstName.substring(0, 3) + lastName.substring(lastName.length() - 2); //+ lastName.substring(lastName.length() - 1);
			message = "";
		}
		
		else
		{
			if (amt > -1)
				balance = amt;
			
			else
				balance = 0.0;
			
			// Have to make this nested
			if (firstName != "")
				firstName = fName;
			else
				firstName = "???";
				
			if (middleName != "")
				middleName = mName;
			
			else
				middleName = "???";
			
			if (lastName != "")
				lastName = lName;
			else
				lastName = "???";
			/*
			if (firstName != "")
			{
				firstName = fName;
				{
					if (lastName != "")
						lastName = firstName;
					
					else
						lastName = "???";
				}	
				
			}
			else
				{
					firstName = "???";
					
					if (lastName != "")
						lastName = "???";
					else
						lastName = firstName;
				}
			*/
			

			if (acctNum > 0)
				accountNum = acctNum;
			
			else
				accountNum = 0;
			
			if (firstName.length() > 2 && lastName.length() > 1)
				password = firstName.substring(0, 3) + lastName.substring(lastName.length() - 2); //+ lastName.substring(lastName.length() - 1);
			
			else
				password = "?????";
			
			message = "";
		}
		System.out.println(password);
		System.out.println(accountNum);
	}

	BankAccount(String	fName,	String	lName,	int acctNum)
	{
		
		if (fName!= "" && lName != "" && acctNum > 0)
		{
			
			balance = 0.0;
			if (firstName != "")
				firstName = "???";
			else
				firstName = fName;
			
			middleName = "";
			
			if (lastName != "")
				lastName = "???";
			
			accountNum = acctNum;
			password = firstName.substring(0, 3) + lastName.substring(lastName.length() - 2); //+ lastName.substring(lastName.length() - 1);
			message = "";

		}
	}
	
	
	

	boolean deposit(double amount)
	{
		if (amount >= 0)
		{
			//System.out.println("Deposit	of amount $" + amount + " made.\n");
			message += ("Deposit of Amount $" + amount + " made.\n");
			balance += amount;
			
			return true;

		}

		else if (amount < 0)
		{
			//System.out.println("Deposit unsuccessful: invalid amount.\n");
			message += ("Deposit unsuccessful: invalid amount.\n");
			return false;
		}

		return false;
	}

	boolean withdraw(double amount)
	{
		if (amount <= balance)
		{
			message += ("Withdrawal of Amount $" + amount + " made.\n");
			balance -= amount;
			return true;
		}

		else if (amount > balance)
		{
			message += ("A bank overdraft fee of $25 will be charged to your account. We thank you for your business.\n");
			balance -= amount;
			return false;
		}

		return false;

	}

	double getBalance()
	{
		return balance;
	}

	void display()
	{
		System.out.println("Account Number: " + accountNum);
		if (middleName == "")
		{
			System.out.println("Account Holder: " + firstName + " " + lastName);
		}

		else
			System.out.println("Account Holder: " + firstName + " " + middleName + " " + lastName);


		System.out.println("Account Balance: $" + balance);
	}
	
	String getName() 
	{
		if (middleName == "")
			return (firstName + " " + lastName);
		else
		{
			String fullname = firstName + " " + middleName + " " + lastName;
			return fullname;
		}
	}
	String gFN() { return firstName; }
	String gMN() { return middleName; }
	String gLN() { return lastName; } 
	
	boolean setHistory(String history) { message += history; return true;}
	
	int getAccountNum() { return accountNum; }
	
	boolean resetPassword(String currentPassword, String newPassword)
	{
		if (currentPassword.equals(password))
		{

			message += ("Password Successfully Changed!\n");
			password = newPassword;
			return true;
		}

		else
		{
			message += ("Password Reset Unsuccessful . Please Try Again.\n");
			return false;
		}

	}
	// Fix this one to return it to void and make it not easily heckable
	/*
	void getPassword()
	{
		message += ("Ha! If only if it were that easy! ECB will be notified of your attempts to hack us, criminal!\n");
		

	}
	*/
	
	String getPassword()
	{
		//message += ("Ha! If only if it were that easy! ECB will be notified of your attempts to hack us, criminal!\n");
		return password;
	}
	
	/*
	boolean checkAcctNumber(int acctNum)
	{
		//message += "Account Number successfully retrieved";
		if (accountNum.equals(acctNum))
		{
			getAccountNum;
		}
	}
	*/
	boolean checkPassword(String pswd)
	{
		if (pswd.equals(password))
		{
			return true;
		}
		else
			return false;
	}

	String getMessage()
	{
		return message;
	}

	void emptyAccount()
	{
		message += ("So much for \"Leaving something for a rainy day!\"\n");
		balance = 0;

	}
	//Give money to
	boolean transferTo(double amount, BankAccount otherAcct)
	{// Check if the amount of money you're going to transfer from your account to the other
	 // is 
		// 
		// withdraw(amount)
		if (amount <= balance)
		{
			// We take the money out of our account
			withdraw(amount);
			message += ("Transfer of $" + amount + " dollars to account " +
					otherAcct.getAccountNum() + " complete.\n");
			otherAcct.deposit(amount);
			return true;

		}

		//amount > otherAcct.balance
		// We want to check if the deposit is exceeding the giver's balance
		else if (amount > balance)
		{
			//withdraw(amount);
			message += ("Overdraft during transfer. Transaction not processed. "
					+ "Please contact us for further assistance. A fee of $25 was applied.\n");
			// This just gives them the fee, but not the transfer itself.
			balance -= 25;
			return false;
		}

		return false;
	}

	//Take money from
	boolean transferFrom(double amount,	BankAccount	otherAcct,	String	pswd)
	{
		if (otherAcct.checkPassword(pswd))
		{
			// This is a problem
			// otherAcct.withdraw(amount)
			// amount <= otherAcct.getBalance()
			if (amount <= otherAcct.getBalance())
			{
				otherAcct.withdraw(amount);
				message += ("Transfer of $" + amount + " dollars from " + 
						otherAcct.getAccountNum() + " complete.\n"  );
				//otherAcct.withdraw(balance);
				balance = balance + amount;
				
				return true;
			}

			else if (amount > otherAcct.getBalance())
			{
				// Take money from the other account
				//otherAcct.withdraw(amount);
				message += ("Transfer of $" + amount + " dollars from account " 
						+ otherAcct.getAccountNum() + 
						" unsuccessful due to insufficient funds. A fee of $25 was applied\n");
				otherAcct.message += ("An account unsuccessfully attempted to withdraw funds. A fee of $25 was applied\n");
				// 
				//balance = balance + amount; 
				otherAcct.balance -= 25;
				return false;

			}
		}
		else
		{
			message += ("Transfer of $" + amount + " dollars from account " +
					otherAcct.getAccountNum() + "Unsuccessful. " +
					"Please check password and try again.\n"
					);
		}

		return false;
		
		

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		BankAccount bnakakkount = new BankAccount(17, "John", "M", "Doe", 5);
		BankAccount testConstructor = new BankAccount("James", "Pizzle", 7);

		bnakakkount.display();

		System.out.println();
		bnakakkount.deposit(99);
		testConstructor.deposit(12);

		bnakakkount.display();
		System.out.println();
		bnakakkount.withdraw(4);
		testConstructor.withdraw(5);

		bnakakkount.getBalance();
		System.out.println(testConstructor.getBalance());

		System.out.println();
		bnakakkount.display();

		System.out.println();
		testConstructor.display();
		//This line prints a null

		System.out.println();
		bnakakkount.resetPassword("Johdo", "Dojo");
		bnakakkount.resetPassword("Xd", "eyy lmao");

		System.out.println();
		bnakakkount.getPassword();

		bnakakkount.emptyAccount();


		//This line prints null, because the message was initialized with ""
		System.out.println(bnakakkount.message);
		bnakakkount.display();



		bnakakkount.transferFrom(99, testConstructor, "JamPi");
		bnakakkount.transferFrom(99, testConstructor, "abcdef");
		bnakakkount.display();

		
		System.out.println();
		System.out.println(bnakakkount.message);
		 
		*/
	}

}
/*
class BankAccounts
{
	BankAccount users[] = { };
	
	// 
	void push (BankAccount b)
	{
		BankAccount newUsers[users.length + 1];
		for (int i = 0; i < users.length; i++)
		{
			newUsers[i] = users[i];
			if (i == users.length)
			{
				i++;
				newUsers[i] = b;
			}
		}
		
		
		
	}
}
*/
