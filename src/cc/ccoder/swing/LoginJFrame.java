package cc.ccoder.swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import cc.ccoder.dao.UserDao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class LoginJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField fieldUser;
	private JPasswordField fieldPass;
	private JButton btnLogin;
	private JButton btnRegister;
	private JLabel labalShow;
	private int countLogin = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginJFrame frame = new LoginJFrame();
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
	public LoginJFrame() {
		setTitle("登录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labalUser = new JLabel("用户名");
		labalUser.setFont(new Font("宋体", Font.PLAIN, 13));
		labalUser.setBounds(64, 55, 54, 15);
		contentPane.add(labalUser);

		JLabel labelPass = new JLabel("密码");
		labelPass.setFont(new Font("宋体", Font.PLAIN, 13));
		labelPass.setBounds(64, 102, 54, 15);
		contentPane.add(labelPass);

		fieldUser = new JTextField();
		fieldUser.setBounds(157, 52, 144, 28);
		contentPane.add(fieldUser);
		fieldUser.setColumns(10);

		fieldPass = new JPasswordField();
		fieldPass.setColumns(10);
		fieldPass.setBounds(157, 99, 144, 28);
		contentPane.add(fieldPass);

		btnLogin = new JButton("登录");
		btnLogin.setBounds(77, 184, 93, 23);
		contentPane.add(btnLogin);

		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String username = fieldUser.getText();
				String password = new String(fieldPass.getPassword());
				UserDao dao = new UserDao();
				if (dao.userLogin(username, password) == 1) {
					// 登录成功
					LoginJFrame.this.dispose();
					OrdersJFrame dFrame = new OrdersJFrame();
					dFrame.setVisible(true);
					System.out.println("登录成功");
				}
				countLogin = countLogin + 1;
				if (countLogin >= 3) {
					JOptionPane.showConfirmDialog(LoginJFrame.this, "登录失败", "次数已经全部用完了", JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					fieldUser.setText("");
					fieldPass.setText("");
					labalShow.setText("");
					countLogin = 0;
				}
				System.out.println(countLogin);
			}
		});

		btnRegister = new JButton("注册");
		btnRegister.setBounds(238, 184, 93, 23);
		contentPane.add(btnRegister);
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginJFrame.this.dispose();
				RegisterJFrame frame = new RegisterJFrame();
				frame.setVisible(true);
			}
		});

		labalShow = new JLabel("");
		labalShow.setBounds(168, 147, 120, 15);
		contentPane.add(labalShow);
	}
}
