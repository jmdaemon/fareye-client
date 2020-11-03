public class TransacHistPanel {

	final String HISTORY_PANEL = "HISTORY_PANEL";

    JPanel historyPanel = new JPanel();
		frame.getContentPane().add(historyPanel, HISTORY_PANEL);
		historyPanel.setLayout(null);

		JButton backButton_6 = new JButton("Back");
		backButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(frame.getContentPane(), MAIN_PANEL);
			}
		});
		backButton_6.setBounds(11, 13, 64, 23);
		historyPanel.add(backButton_6);

		JLabel lblHistory = new JLabel("History");
		lblHistory.setBounds(42, 47, 46, 14);
		historyPanel.add(lblHistory);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 72, 337, 151);
		historyPanel.add(scrollPane);


		JButton btnHistory = new JButton("History");
		btnHistory.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cardLayout.show(frame.getContentPane(), HISTORY_PANEL);
				textArea.setText(curr.getMessage());

			}
		});

		btnHistory.setBounds(10, 163, 115, 45);
		mainPanel.add(btnHistory);
			}

		/*
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(130, 53, 120, 20);
		historyPanel.add(comboBox);
		 */
}
