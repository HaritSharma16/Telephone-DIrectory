import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;

import db.Cp;
import db.ResizeIcon;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class addContact extends JFrame {

	protected static String Susername ;
	private JPanel contentPane;
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
					addContact frame = new addContact(Susername );
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
	public addContact(String Susername) {
		this.Susername=Susername;
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 150, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(219, 11, 169, 29);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		textField.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField.setBounds(219, 51, 197, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Mobile Number");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(219, 86, 169, 29);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_1.setBounds(219, 126, 197, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Alternate Number");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(219, 157, 169, 29);
		contentPane.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_2.setBounds(219, 197, 197, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(218, 228, 170, 29);
		contentPane.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		textField_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_3.setBounds(219, 268, 197, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblNewLabel_4.setBounds(10, 20, 169, 166);
		contentPane.add(lblNewLabel_4);
		
		
		JButton btnNewButton_3 = new JButton("Choose Photo");
		btnNewButton_3.setIcon(new ImageIcon("C:src\\photo.png"));
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_3.setBackground(new Color((float)1.0,(float)1.0,(float)1.0,(float)0.5));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon showPath;
				JFileChooser pic=new JFileChooser();
				pic.setCurrentDirectory(new File(System.getProperty("user.home")));
				int r=pic.showSaveDialog(null);
				if(r==JFileChooser.APPROVE_OPTION)
				{
					File selectedFile=pic.getSelectedFile();
					String path=selectedFile.getAbsolutePath();
					showPath=ResizeIcon.ResizeIcon(path,lblNewLabel_4);
					//showPath.setText(path);
					lblNewLabel_4.setIcon(showPath);
					paths=path;
				}
			}
		});
		btnNewButton_3.setBounds(10, 197, 169, 39);
		contentPane.add(btnNewButton_3);
		
		
		JButton btnNewButton = new JButton("Reset");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new addContact(Susername).setVisible(true);
				dispose();
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:src\\reset.png"));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBackground(new Color((float)1.0,(float)1.0,(float)1.0,(float)0.5));
		btnNewButton.setBounds(10, 336, 134, 39);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Save");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				try
				{
					Connection con=Cp.getCon();
					PreparedStatement ps=con.prepareStatement("insert into "+Susername+" values(?,?,?,?,?)");
					ps.setString(1, textField.getText());
					ps.setInt(2, Integer.parseInt(textField_1.getText()));
					ps.setInt(3, Integer.parseInt(textField_2.getText()));
					ps.setString(4, textField_3.getText());
					
					FileInputStream fis= new FileInputStream(paths);
					ps.setBinaryStream(5, fis,fis.available());
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Added Succesfully");
					dispose();
					
				}
				catch(SQLException | IOException e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("C:src\\save.png"));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBackground(new Color((float)1.0,(float)1.0,(float)1.0,(float)0.5));
		btnNewButton_1.setBounds(154, 336, 142, 39);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Close");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a=JOptionPane.showConfirmDialog(null,"Are you sure you want to close the window ?","Select",JOptionPane.YES_NO_OPTION);
				if(a==0) {
				new homepage(Susername).setVisible(true);
				dispose();}
			}
		});
		btnNewButton_2.setIcon(new ImageIcon("C:src\\close.png"));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setBackground(new Color((float)1.0,(float)1.0,(float)1.0,(float)0.5));
		btnNewButton_2.setBounds(306, 336, 134, 39);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon("C:src\\sign back.jpg"));
		lblNewLabel_5.setBounds(0, 0, 450, 400);
		contentPane.add(lblNewLabel_5);
		
		
		
	}
}
