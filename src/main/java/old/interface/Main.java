public class MainPanel {

	final String MAIN_PANEL = "MAIN PANEL";


    initialize();

    /**
     * Initialize the contents of the frame.
     */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(cardLayout);



		//addAccount("Test", "", "Last");
		//addAccount(firstName, middleName, lastName);
		//curr = searchAccount(("Tes"+ "St"));

		/*
		Date date = new Date();
	    System.out.println(new Timestamp(date.getTime()));
		 */
		/*
		JLabel mainPanelJPacctNamelbl = new JLabel("Account Name: ");
		mainPanelJPacctNamelbl.setBounds(10, 11, 115, 14);
		//mainPanel.add(mainPanelJPacctNamelbl);
		 */

		// Bring up History Panel from History button

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);


}


    JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, MAIN_PANEL);
		mainPanel.setLayout(null);


		
		JButton btnNewButton = new JButton("Create New Account");
		btnNewButton.setBounds(10, 107, 52, -7);
		mainPanel.add(btnNewButton);

		JButton btnResetPassword = new JButton("Reset Password");
		btnResetPassword.setBounds(10, 51, 115, 45);
		mainPanel.add(btnResetPassword);

    // Bring up Deposit Panel from Deposit button
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(frame.getContentPane(), DEPOSIT_PANEL);



			}
		});

		btnDeposit.setBounds(153, 51, 115, 45);
		mainPanel.add(btnDeposit);


		// Bring up EmptyAccount Panel from Empty Account button
		JButton btnEmptyAccount = new JButton("Empty Account");
		btnEmptyAccount.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cardLayout.show(frame.getContentPane(), EMPTY_ACCOUNT_PANEL);

			}
		});

		btnEmptyAccount.setBounds(301, 163, 123, 45);
		mainPanel.add(btnEmptyAccount);

		JButton btnBack = new JButton("Logout");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(frame.getContentPane(), LOGIN_PANEL);
			}
		});
		btnBack.setBounds(10, 11, 98, 23);
		mainPanel.add(btnBack);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}




}
