package ui;


//import javax.swing.JPanel;
//import javax.swing.JFrame;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;
//import javax.swing.JPasswordField;
//import javax.swing.JButton;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JTextPane;
//import javax.swing.JComboBox;
//import javax.swing.JRadioButton;
//import javax.swing.JScrollPane;
import javax.swing.*;



public class LoginPanel {
  public final String PANEL_ID = "LOGIN PANEL";

  private JTextField loginJPloginTF;
	private JPasswordField passwordField;


  public JButton btnLogin = new JButton("Login");

  public View(Jframe frame) {

    JPanel loginPanel = new JPanel();


    // Button Definitions
    btnLogin.setBounds(167, 110, 89, 23);

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				//String userName = loginJPloginTF.getText();
				//String userName = "JohnMD";

				//String userName = loginJPloginTF.getText();

				String password = "";
				//char charArray [] = ;
				password = (String.valueOf(passwordField.getPassword()));


				//if (password == "")
				//System.out.println("You cannot leave the password field blank. ");


				//String accountNumberString = ();
				int acctNumber = (Integer.valueOf(loginJPloginTF.getText()));
				try {
					login(password, acctNumber);
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

    JButton btnCreateNewAccount = new JButton("Create New Account");
      btnCreateNewAccount.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
          cardLayout.show(frame.getContentPane(), CREATE_NEW_ACCT_PANEL);
			}
		});

		btnCreateNewAccount.setBounds(153, 144, 145, 23);
		loginPanel.add(btnCreateNewAccount);


		frame.getContentPane().add(loginPanel, PANEL_ID);
		loginPanel.setLayout(null);

    loginPanel.add(btnLogin);



		loginJPloginTF = new JTextField();
		loginJPloginTF.setBounds(112, 47, 174, 20);
		loginPanel.add(loginJPloginTF);
		loginJPloginTF.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(112, 78, 174, 20);
		loginPanel.add(passwordField);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(26, 81, 68, 14);
		loginPanel.add(lblPassword);

		JLabel lblDontHaveAn = new JLabel("Don't have an account?");
		lblDontHaveAn.setBounds(10, 148, 285, 14);
		loginPanel.add(lblDontHaveAn);

		JLabel lblAccountNumber_1 = new JLabel("Account Number");
		lblAccountNumber_1.setBounds(10, 50, 95, 14);
		loginPanel.add(lblAccountNumber_1);

    


  }

  public static void main(String[] args) {
  }
}
