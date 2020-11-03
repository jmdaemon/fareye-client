public class WithdrawPanel {
	final String WITHDRAW_PANEL = "WITHDRAW_PANEL";

    JPanel withdrawPanel = new JPanel();
		frame.getContentPane().add(withdrawPanel, WITHDRAW_PANEL);
		withdrawPanel.setLayout(null);


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

    btnWithdrawFrom.setBounds(301, 107, 123, 45);
		mainPanel.add(btnWithdrawFrom);



		
		// Bring up Withdraw_From Panel from Withdraw from button
		JButton btnWithdrawFrom = new JButton("Withdraw From");
		btnWithdrawFrom.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cardLayout.show(frame.getContentPane(), WITHDRAW_FROM_PANEL);


			}
		});

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

		JLabel lblWithdrawAmount = new JLabel("Withdraw Amount");
		lblWithdrawAmount.setBounds(32, 109, 109, 14);
		withdrawPanel.add(lblWithdrawAmount);
}
