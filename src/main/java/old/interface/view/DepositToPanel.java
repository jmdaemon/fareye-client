public class DepositToPanel {
  final String DEPOSIT_TO_PANEL = "DEPOSIT_TO_PANEL";
  private JTextField depositToJPdepositToAcctTF;
	private JTextField depositToJPamountTF;


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

    JPanel depositToPanel = new JPanel();
		frame.getContentPane().add(depositToPanel, DEPOSIT_TO_PANEL);
		depositToPanel.setLayout(null);

	
		JButton backButton_3 = new JButton("Back");
		backButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(frame.getContentPane(), MAIN_PANEL);
			}
		});
		backButton_3.setBounds(10, 11, 64, 23);
		depositToPanel.add(backButton_3);


}
