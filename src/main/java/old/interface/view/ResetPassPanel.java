public class ResetPassPanel {

	final String RESET_PASS_PANEL = "RESET_PASS_PANEL";

  private JPasswordField resetPassJPnewPass;
	private JPasswordField resetPassJPcurpass;



    // Reset Password button with new panel
		JPanel resetPasswordPanel = new JPanel();
		frame.getContentPane().add(resetPasswordPanel, RESET_PASS_PANEL);
		resetPasswordPanel.setLayout(null);


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

}
