public class WithdrawFromPanel {

	final String WITHDRAW_FROM_PANEL = "WITHDRAW_FROM_PANEL";

    withdrawJPwithdrawAmtTF = new JTextField();
		withdrawJPwithdrawAmtTF.setBounds(151, 106, 126, 20);
		withdrawPanel.add(withdrawJPwithdrawAmtTF);
		withdrawJPwithdrawAmtTF.setColumns(10);

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


}
