public class NewUserPanel {

	final String CREATE_NEW_ACCT_PANEL = "CREATE_NEW_ACCT_PANEL";
	private JTextField createAcctJPfirstnameTF;
	private JTextField createAcctJPmiddlenameTF;
	private JTextField createAcctJPlastnameTF;

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
