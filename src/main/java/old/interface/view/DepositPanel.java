public class DepositPanel {
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


}
