import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

import db.Cp;
import net.proteanit.sql.DbUtils;
//import project.ConnectionProvider;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JSeparator;

public class contact extends JFrame {

	private JPanel contentPane;
	private JTable table;
	static String Susername;
	private JTextField txtSeachHere;
	JLabel lblNewLabel_4 ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					contact frame = new contact(Susername);
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
	public contact(String Susername) {
		this.Susername=Susername;
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				try
				{
					Connection con=Cp.getCon();
					Statement st=con.createStatement();
					//System.out.println(Susername);
					ResultSet rs=st.executeQuery("select *from "+Susername);
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					 
					ResultSet rst=st.executeQuery("select COUNT(*) from "+Susername);
					int i=0;
					rst.next();
				     i=rst.getInt(1);
					lblNewLabel_4.setText("Total Contacts "+Integer.toString(i));
					
				}
				catch(SQLException e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 150, 450, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 75, 430, 264);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 14));
		table.setBackground(Color.LIGHT_GRAY);
		JTableHeader header=table.getTableHeader();
		header.setFont(new Font("SansSerif",Font.BOLD,16));
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("ALL CONTACTS");
		lblNewLabel.setFont(new Font("Candara", Font.BOLD, 40));
		lblNewLabel.setBounds(102, 0, 278, 50);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("View");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name1 = null;
				int row=table.getSelectedRow();
				table.getModel().getValueAt(row,0);
				String name=(String) table.getModel().getValueAt(row,0);
				try
				{
					Connection con=Cp.getCon();
					Statement st=con.createStatement();
					ResultSet rs=st.executeQuery("select *from "+Susername+" where name='"+name+"'");
					if (rs.next())
					{
						name1=rs.getString(1);
					}
				}
				catch(SQLException e2)
				{
					JOptionPane.showMessageDialog(null,e2);
				}
			
				new ViewDetails(Susername,name1).setVisible(true);
				
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon("C:src\\view.png"));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(289, 50, 75, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Delete");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int a=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this contact?","Select",JOptionPane.YES_NO_OPTION);
				if(a==0) {
				int row=table.getSelectedRow();
				table.getModel().getValueAt(row,0);
				String name=(String) table.getModel().getValueAt(row,0);
				try
				{
					Connection con=Cp.getCon();
					Statement st=con.createStatement();
					st.execute("delete from "+Susername+" where name='"+name+"'");
					JOptionPane.showMessageDialog(null,"Deleted Successfully!");
					new contact(Susername).setVisible(true);
					dispose();
				}
				catch(SQLException e2)
				{
					JOptionPane.showMessageDialog(null,e2);
				}
			}}
		});
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setIcon(new ImageIcon("C:src\\delete.png"));
		lblNewLabel_2.setBounds(374, 50, 76, 24);
		contentPane.add(lblNewLabel_2);
		
		txtSeachHere = new JTextField();
		txtSeachHere.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSeachHere.setText("");
			}
		});
		txtSeachHere.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String number=txtSeachHere.getText();
				String name=txtSeachHere.getText();
				try {
					Connection con=Cp.getCon();
					Statement st=con.createStatement();
					ResultSet rs=st.executeQuery("select *from "+Susername+" where Contact1 like'%"+number+"%'or name like'%"+name+"%' ");
					//table.setAutoResizeMode(table.AUTO_RESIZE_OFF);
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}
				catch(Exception e2)
				{e2.printStackTrace();
					JOptionPane.showMessageDialog(null, e2);
				}
			}
		});
		txtSeachHere.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtSeachHere.setText("Search here");
		txtSeachHere.setBackground(Color.LIGHT_GRAY);
		txtSeachHere.setBounds(10, 52, 190, 20);
		contentPane.add(txtSeachHere);
		txtSeachHere.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 39, 450, 2);
		contentPane.add(separator);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new homepage(Susername).setVisible(true);
			}
		});
		lblNewLabel_3.setIcon(new ImageIcon("C:src\\back.png"));
		lblNewLabel_3.setBounds(0, 0, 57, 41);
		contentPane.add(lblNewLabel_3);
		
		 lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(10, 340, 190, 24);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon("C:src\\sign back.jpg"));
		lblNewLabel_5.setBounds(0, 0, 450, 375);
		contentPane.add(lblNewLabel_5);
	}
}
