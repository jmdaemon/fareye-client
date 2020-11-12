public class EmptyAcctPanel { 

	final String EMPTY_ACCOUNT_PANEL = "EMPTY_ACCOUNT_PANEL";

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

}
