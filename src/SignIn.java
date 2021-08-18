import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import db.Cp;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SignIn extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	String Susername;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignIn frame = new SignIn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 */
//	public SignIn(String un)
//	{
//		try
//		{
//			Connection con=Cp.getCon();
//			Statement st=con.createStatement();
//			ResultSet rs=st.executeQuery("select  *from TdSignUp where username='"+username+"'" );
//			if(rs.next()) {
//	        un=rs.getString(5);}
//			System.out.println(un);
//		}
//		catch(SQLException e)
//		{
//			e.printStackTrace();
//		}
//	}
	public SignIn() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 150, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Candara", Font.BOLD, 20));
		lblNewLabel.setBounds(26, 56, 127, 42);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Candara", Font.BOLD, 20));
		lblNewLabel_1.setBounds(26, 109, 127, 42);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField.setBackground(new Color(255, 255, 255));
		textField.setBounds(163, 66, 205, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordField.setBounds(163, 119, 205, 20);
		contentPane.add(passwordField);
		
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String username=textField.getText();
				String pass=passwordField.getText();
				try
				{
					Connection con=Cp.getCon();
					Statement st=con.createStatement();
					ResultSet rs=st.executeQuery("select  *from TdSignUp where username='"+username+"'" );
					if(rs.next()) {
				String un=rs.getString(5);
				String pw=rs.getString(6);
				Susername=un;
				//System.out.println(un+pw);
					if(textField.getText().equals(un) && passwordField.getText().equals(pw))
					{
					new homepage(un).setVisible(true);
					dispose();
					}
				else
					JOptionPane.showMessageDialog(null,"Credentials are not correct");
					
						}
					else
					{
						JOptionPane.showMessageDialog(null,"User Doesnot Exist");
					}
				con.close();
				}
				
				catch(SQLException e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		panel.setBounds(102, 188, 224, 66);
		panel.setBackground(new Color((float)1.0,(float)1.0,(float)1.0,(float)0.5));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("SignIn");
		lblNewLabel_2.setForeground(new Color(0, 0, 205));
		lblNewLabel_2.setFont(new Font("Candara", Font.BOLD, 25));
		lblNewLabel_2.setBounds(82, 11, 95, 44);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Welcome().setVisible(true);
			}
		});
		lblNewLabel_3.setIcon(new ImageIcon("C:src\\back.png"));
		lblNewLabel_3.setBounds(0, 0, 57, 50);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("C:src\\sign back.jpg"));
		lblNewLabel_4.setBounds(0, 0, 450, 300);
		contentPane.add(lblNewLabel_4);
		
	

	}
}
