import java.awt.BorderLayout;
import java.sql.*;
import db.Cp;
import db.ResizeIcon;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyProfile extends JFrame {

	private JPanel contentPane;
	static String Susername;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
String paths;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyProfile frame = new MyProfile(Susername);
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
	
	public MyProfile(String Susername) {
		
		setUndecorated(true);
		this.Susername=Susername;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 150, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("My Profile");
		lblNewLabel.setFont(new Font("Candara", Font.BOLD, 20));
		lblNewLabel.setBounds(176, 0, 161, 45);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 43, 450, 2);
		contentPane.add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 56, 111, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Date of Birth");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 96, 111, 23);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Contact");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(10, 130, 111, 23);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(10, 164, 111, 23);
		contentPane.add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField.setBounds(131, 56, 148, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_1.setBounds(131, 96, 148, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_2.setBounds(131, 133, 148, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_3.setBounds(131, 167, 148, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblNewLabel_5.setBounds(299, 56, 141, 131);
		contentPane.add(lblNewLabel_5);
		
		JButton btnNewButton = new JButton("Choose Photo");
		btnNewButton.setIcon(new ImageIcon("C:src\\photo.png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon showPath;
				JFileChooser pic=new JFileChooser();
				pic.setCurrentDirectory(new File(System.getProperty("user.home")));
				int r=pic.showSaveDialog(null);
				if(r==JFileChooser.APPROVE_OPTION)
				{
					File selectedFile=pic.getSelectedFile();
					String path=selectedFile.getAbsolutePath();
					showPath=ResizeIcon.ResizeIcon(path, lblNewLabel_5);
					//showPath.setText(path);
					
					lblNewLabel_5.setIcon(showPath);
					paths=path;
				}
			}
		});
		btnNewButton.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(299, 198, 141, 31);
		contentPane.add(btnNewButton);
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
			

				try
				{
					Connection con=Cp.getCon();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select *from TdSignUp where Username='"+Susername+"' ");
			if (rs.next())
			{
				textField.setText(rs.getString("Name"));
				textField_1.setText(rs.getString("DOB"));
				textField_2.setText(rs.getString("Contact"));
				textField_3.setText(rs.getString("EMAIL"));
				Blob b=rs.getBlob("photo");
				byte barr[]=b.getBytes(1,(int)b.length());
				Image img=Toolkit.getDefaultToolkit().createImage(barr);
				ImageIcon icon=ResizeIcon.resizeImage(img,lblNewLabel_5);
				lblNewLabel_5.setIcon(icon);
				
			}
				}
				catch(SQLException e1)
				{
					JOptionPane.showMessageDialog(null,e1);				}
				
			}
		});
		
		JButton btnNewButton_1 = new JButton("Save");
		btnNewButton_1.setIcon(new ImageIcon("C:src\\save.png"));
		btnNewButton_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			//System.out.println(Susername);
				try
				{
					Connection con=Cp.getCon();
					PreparedStatement ps=con.prepareStatement("update TdSignup set name=?,dob=?,contact=?,email=?,photo=? where username=?");
					ps.setString(6,Susername);
					ps.setString(1, textField.getText());
					ps.setString(2,(textField_1.getText()));
					ps.setLong(3,Long.parseLong(textField_2.getText()));
					ps.setString(4, textField_3.getText());
					FileInputStream fis= new FileInputStream(paths);
					ps.setBinaryStream(5, fis,fis.available());
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Saved Succesfully!");
					dispose();
				}
				catch(SQLException e3)
				{
					JOptionPane.showMessageDialog(null, e3);
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(10, 241, 132, 31);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Reset");
		btnNewButton_2.setIcon(new ImageIcon("C:src\\reset.png"));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				lblNewLabel_5.setIcon(new ImageIcon());
			}
		});
		btnNewButton_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setBounds(152, 241, 132, 31);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.addMouseListener(new MouseAdapter() {
			@Override
			
			public void mouseClicked(MouseEvent e) {
				new homepage(Susername).setVisible(true);
				dispose();
			}
		});
		lblNewLabel_6.setIcon(new ImageIcon("C:src\\back.png"));
		lblNewLabel_6.setBounds(0, 0, 60, 45);
		contentPane.add(lblNewLabel_6);
		
		JButton btnNewButton_3 = new JButton("Delete");
		btnNewButton_3.setIcon(new ImageIcon("C:src\\delete.png"));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete?","Select",JOptionPane.YES_NO_OPTION);
			if(a==0) {
				try
			{
				Connection con=Cp.getCon();
				Statement st=con.createStatement();
				st.execute("drop table "+Susername);
				st.execute("delete from TdSignup where Username='"+Susername+"'");
				dispose();
				JOptionPane.showMessageDialog(null, "Account Deleted Sucsessfully");
				new Welcome().setVisible(true);
			}
			catch(SQLException e2)
			{
				JOptionPane.showMessageDialog(null,e2);
			}
			}}
		});
		btnNewButton_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_3.setBounds(294, 241, 132, 31);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon("C:src\\sign back.jpg"));
		lblNewLabel_7.setBounds(0, 0, 450, 300);
		contentPane.add(lblNewLabel_7);
	}
	
}
