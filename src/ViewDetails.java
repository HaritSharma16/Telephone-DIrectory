import java.awt.BorderLayout;
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
import javax.swing.border.MatteBorder;

import db.Cp;
import db.ResizeIcon;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewDetails extends JFrame {

	private JPanel contentPane;
	static String Susername;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	String paths;
	static String name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewDetails frame = new ViewDetails(Susername,name);
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
	public ViewDetails(String Susername,String name) {
		this.Susername=Susername;
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 150, 450, 303);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Details");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(181, 11, 125, 36);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 45, 450, 2);
		contentPane.add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 59, 140, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Contact");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 97, 140, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Alternate Contact");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(10, 140, 140, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(10, 176, 140, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblNewLabel_5.setBounds(311, 60, 129, 127);
		contentPane.add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField.setBounds(141, 58, 160, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_1.setBounds(141, 96, 160, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_2.setBounds(141, 139, 159, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_3.setBounds(141, 175, 160, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				
				try {
					Connection con=Cp.getCon();
					Statement st=con.createStatement();
					ResultSet rs=st.executeQuery("select * from "+Susername+" where name='"+name+"'");
					if(rs.next())
					{
						textField.setText(rs.getString(1));
						textField_1.setText((rs.getString(2)));
						textField_2.setText(rs.getString(3));
						textField_3.setText(rs.getString(4));
						Blob b=rs.getBlob(5);
						byte barr[]=b.getBytes(1,(int)b.length());
						Image img=Toolkit.getDefaultToolkit().createImage(barr);
					ImageIcon icon=ResizeIcon.resizeImage(img,lblNewLabel_5);
						lblNewLabel_5.setIcon(icon);
					}
					
				}
				catch(SQLException e3)
				{
					JOptionPane.showMessageDialog(null,"Doesnot Exist");
				}
			}
		});
		
		JButton btnNewButton = new JButton("Change Photo");
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
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(300, 198, 150, 36);
		contentPane.add(btnNewButton);
		
		
		JButton btnNewButton_1 = new JButton("Save Changes");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(Susername);
				String name1=textField.getText();

				try {
					Connection con=Cp.getCon();
					PreparedStatement ps=con.prepareStatement("update "+Susername+" SET name=?,Contact1=?,Contact2=?,email=?,photo=? where name=?");
				ps.setString(6,name1);
				ps.setString(1,textField.getText());
				ps.setLong(2, Long.parseLong(textField_1.getText()));
				ps.setLong(3, Long.parseLong(textField_2.getText()));
				ps.setString(4, textField_3.getText());
				FileInputStream fis=new FileInputStream(paths);
				ps.setBinaryStream(5,fis,fis.available());
				ps.executeUpdate();
				int i= ps.executeUpdate();
				if(i==1)
				{
					JOptionPane.showMessageDialog(null,"Saved Successfully!");
				}
				else
					JOptionPane.showMessageDialog(null,"Failed to save!");
				dispose();
				}
				catch(SQLException e3)
				{
					JOptionPane.showMessageDialog(null, e3);
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnNewButton_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnNewButton_1.setIcon(new ImageIcon("C:src\\save.png"));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(10, 247, 140, 36);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Reset");
		btnNewButton_2.setIcon(new ImageIcon("C:src\\reset.png"));
		btnNewButton_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ViewDetails(Susername,name).setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setBounds(171, 247, 140, 36);
		contentPane.add(btnNewButton_2);
		 
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new contact(Susername).setVisible(true);
				dispose();
			}
		});
		lblNewLabel_6.setIcon(new ImageIcon("C:src\\back.png"));
		lblNewLabel_6.setBounds(0, 0, 56, 47);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon("src\\sign back.jpg"));
		lblNewLabel_7.setBounds(0, 0, 450, 303);
		contentPane.add(lblNewLabel_7);
	}
}
