import java.awt.EventQueue;
/////////////////////////////////////////////////////////////////////////////////////////////////////////
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;

import javax.swing.JFrame;

import java.awt.GridBagLayout;
import java.awt.CardLayout;

import javax.swing.JPanel;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;
import java.util.Arrays;

public class GUI {

	private JFrame frame;
	CardLayout cardLayout = new CardLayout(0, 0);

	int MAX_COUNT = 100;
	private JTextField withdrawFromJPwithdrawAmt;
	private JTextField withdrawJPwithdrawAmtTF;
	private JTextField depositJPdepositTF;
	private BankAccount curr;
	private BankAccount b;

	private String G_Password;
	private int A_NUMBER;
	private String BG_Password;
	private int BA_NUMBER;
	private double BA_BAL;
	//private BankAccount testAcct = new BankAccount(1, "aaa", "bbb", "ccc", 5678);
	private BankAccount users[] = new BankAccount[MAX_COUNT];
	private int numberOfUsers = 0;

	//private BankAccount users[] = { curr, depositAcct };
	private JTextField withdrawFromJPwithdrawFromTF;
	private JPasswordField withdrawFromJPpassword;

	/**
	 * Launch the application.-
	 */


	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

