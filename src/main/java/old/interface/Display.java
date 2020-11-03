public class DisplayPanel {
  final String DISPLAY_PANEL = "DISPLAY_PANEL";

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



}
