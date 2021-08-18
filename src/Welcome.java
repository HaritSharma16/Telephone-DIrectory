import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class Welcome extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome frame = new Welcome();
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
	public Welcome() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Dell\\eclipse-workspace\\TelephoneDir\\src\\app icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 100, 569, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(222, 22, 321, 344);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setBackground(new Color((float)1.0,(float)1.0,(float)1.0,(float)0.5));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 51, 301, 84);
		panel.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBackground(new Color((float)1.0,(float)1.0,(float)1.0,(float)0.5));
		
		JLabel lblNewLabel = new JLabel("SignUp");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new SignUp().setVisible(true);
			}
		});
		lblNewLabel.setForeground(new Color(0, 0, 205));
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
		lblNewLabel.setBounds(69, 11, 175, 62);
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 203, 301, 84);
		panel.add(panel_2);
		panel_2.setLayout(null);
		panel_2.setBackground(new Color((float)1.0,(float)1.0,(float)1.0,(float)0.5));
		
		
		JLabel lblNewLabel_1 = new JLabel("SignIn");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new SignIn().setVisible(true);
			}
		});
		lblNewLabel_1.setForeground(new Color(0, 0, 205));
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
		lblNewLabel_1.setBounds(72, 11, 171, 62);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("or");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_2.setBounds(135, 162, 63, 19);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:src\\welcome back.jpg"));
		lblNewLabel_3.setBounds(0, 0, 553, 389);
		contentPane.add(lblNewLabel_3);
	}
}
