import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import db.Cp;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.border.MatteBorder;

import com.toedter.calendar.JDateChooser;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class SignUp extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_4;
	String paths;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SignUp() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 150, 395, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Welcome().setVisible(true);
				dispose();
			}
		});
		lblNewLabel.setIcon(new ImageIcon("C:src\\back.png"));
		lblNewLabel.setBounds(10, 0, 75, 56);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 67, 112, 33);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 143, 112, 20);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Date of birth");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(10, 99, 112, 33);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Contact");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(10, 180, 112, 20);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Username");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(10, 211, 112, 20);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Password");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6.setBounds(10, 247, 112, 20);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Confirm Password");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_7.setBounds(10, 278, 126, 30);
		contentPane.add(lblNewLabel_7);
		
		textField = new JTextField();
		textField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		textField.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField.setBounds(146, 74, 211, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JDateChooser dateCh=new JDateChooser();
		dateCh.setBounds(146, 111, 211, 20);
		dateCh.setFont(new Font("Tahoma",Font.BOLD,14));
		dateCh.setDateFormatString("dd/MM/yyyy");
		Border border= BorderFactory.createLineBorder(Color.BLACK, 1);
		dateCh.setBorder(border);
		getContentPane().add(dateCh);
		
		
		textField_2 = new JTextField();
		textField_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_2.setBounds(146, 142, 211, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		textField_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_3.setBounds(146, 181, 211, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordField.setBounds(146, 256, 211, 20);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		passwordField_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordField_1.setBounds(146, 287, 211, 20);
		contentPane.add(passwordField_1);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_4.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		textField_4.setBounds(146, 212, 105, 24);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnNewButton = new JButton("Generate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] username=textField.getText().split(" ");
				SimpleDateFormat dFormat=new SimpleDateFormat("dd-MM-yyyy");
				String dob=dFormat.format(dateCh.getDate());
				String []DOB= dob.split("-");
				textField_4.setText(username[0]+DOB[0]);
				
			}
		});
		btnNewButton.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(261, 211, 96, 27);
		contentPane.add(btnNewButton);
		btnNewButton.setBackground(new Color((float)1.0,(float)1.0,(float)1.0,(float)0.5));
		JButton btnNewButton_1 = new JButton("Choose Photo");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon showPath;
				JFileChooser pic=new JFileChooser();
				pic.setCurrentDirectory(new File(System.getProperty("user.home")));
				int r=pic.showSaveDialog(null);
				if(r==JFileChooser.APPROVE_OPTION)
				{
					File selectedFile=pic.getSelectedFile();
					String path=selectedFile.getAbsolutePath();
					showPath=new ImageIcon(path);
					//showPath.setText(path);
					//lblNewLabel_4.setIcon(showPath);
					paths=path;
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(146, 318, 146, 23);
		contentPane.add(btnNewButton_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 358, 337, 52);
		panel.setBackground(new Color((float)1.0,(float)1.0,(float)1.0,(float)0.5));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("Signup");
		lblNewLabel_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name=textField.getText();
				SimpleDateFormat dFormat=new SimpleDateFormat("dd-MM-yyyy");
				String dob=dFormat.format(dateCh.getDate());
			    String email=textField_2.getText();
			    String contact=textField_3.getText();
			    String username=textField_4.getText();
			    String pw=passwordField.getText();
			    String conf=passwordField_1.getText();
			   if(pw.equals(conf)) {
			    try
			    {
			    	Connection con=Cp.getCon();
			    	Statement st=con.createStatement();
			    	//st.execute("insert into TdSignUp values('"+name+"','"+dob+"','"+email+"','"+contact+"','"+username+"','"+pw+"',)");
			    	
			    	PreparedStatement ps=con.prepareStatement("insert into TdSignUp values(?,?,?,?,?,?,?)");
			    	ps.setString(1, name);
					ps.setString(2, dob);
					ps.setString(3, email);
					ps.setInt(4, Integer.parseInt(contact));
					ps.setString(5, username);
					ps.setString(6, pw);
					
					FileInputStream fis= new FileInputStream(paths);
					ps.setBinaryStream(7, fis,fis.available());
					ps.executeUpdate();
					//System.out.println(username);
					st.execute("create table "+username+" (name varchar(100),Contact1 number(15),Contact2 number(15),email varchar(100),photo blob)");
			    	JOptionPane.showMessageDialog(null,"Signed Up Successfully");
			    	dispose();
			    }
			    catch(SQLException e1)
			    { //e1.printStackTrace();
			    	JOptionPane.showMessageDialog(null,e1);
		    } catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			   }
			   else
			   {
				   JOptionPane.showMessageDialog(null,"Passwords dont match!");
			   }
			}
		});
		lblNewLabel_8.setFont(new Font("Candara", Font.BOLD, 20));
		lblNewLabel_8.setBounds(46, 12, 70, 30);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Reset");
		lblNewLabel_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new SignUp().setVisible(true);
				dispose();
			}
		});
		lblNewLabel_9.setFont(new Font("Candara", Font.BOLD, 20));
		lblNewLabel_9.setBounds(227, 12, 56, 30);
		panel.add(lblNewLabel_9);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(165, 4, 2, 44);
		panel.add(separator);
		
		JLabel lblNewLabel_10 = new JLabel("Your Picture");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_10.setBounds(10, 319, 126, 14);
		contentPane.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("");
		lblNewLabel_11.setIcon(new ImageIcon("C:src\\sign back.jpg"));
		lblNewLabel_11.setBounds(0, 0, 395, 421);
		contentPane.add(lblNewLabel_11);
		
		
	}
}
