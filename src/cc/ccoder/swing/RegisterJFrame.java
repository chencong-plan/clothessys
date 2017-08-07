package cc.ccoder.swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cc.ccoder.dao.UserDao;
import cc.ccoder.entity.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class RegisterJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField fieldUser;
	private JTextField fieldPass;
	private JTextField fieldRePass;
	private JTextField fieldCity;
	private int comSelectIndex;
	private JRadioButton rbtnWoMan;
	private JRadioButton tbtnMan;
	private JLabel labaelPhone;
	private JTextField fieldPhone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterJFrame frame = new RegisterJFrame();
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
	public RegisterJFrame() {
		setTitle("注册");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 344);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labelUser = new JLabel("用户名");
		labelUser.setBounds(64, 26, 54, 15);
		contentPane.add(labelUser);

		JLabel labelPass = new JLabel("密码");
		labelPass.setBounds(64, 63, 54, 15);
		contentPane.add(labelPass);

		JLabel labelRePass = new JLabel("确认密码");
		labelRePass.setBounds(64, 101, 54, 15);
		contentPane.add(labelRePass);

		JLabel labelSex = new JLabel("性别");
		labelSex.setBounds(64, 142, 54, 15);
		contentPane.add(labelSex);

		JLabel labelCity = new JLabel("城市");
		labelCity.setBounds(64, 178, 54, 15);
		contentPane.add(labelCity);

		fieldUser = new JTextField();
		fieldUser.setBounds(159, 23, 133, 21);
		contentPane.add(fieldUser);
		fieldUser.setColumns(10);

		fieldPass = new JTextField();
		fieldPass.setColumns(10);
		fieldPass.setBounds(159, 60, 133, 21);
		contentPane.add(fieldPass);

		fieldRePass = new JTextField();
		fieldRePass.setColumns(10);
		fieldRePass.setBounds(159, 98, 133, 21);
		contentPane.add(fieldRePass);

		fieldCity = new JTextField();
		fieldCity.setColumns(10);
		fieldCity.setBounds(159, 175, 133, 21);
		contentPane.add(fieldCity);

		JButton btnSubmit = new JButton("提交");
		btnSubmit.setBounds(94, 255, 93, 23);
		contentPane.add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String sex = "";
				if (tbtnMan.isSelected()) {
					sex = "男";
				} else {
					sex = "女";
				}
				String pass = fieldPass.getText();
				String repass = fieldRePass.getText();
				String username = fieldUser.getText();
				String city = fieldCity.getText();
				String phone = fieldPhone.getText();
				if (pass.equals(repass)) {
					User user = new User();
					user.setUsername(username);
					user.setPassword(pass);
					user.setSex(sex);
					user.setCity(city);
					user.setPhone(phone);
					UserDao dao = new UserDao();
					if (dao.insertUserInfo(user) == 1) {
						RegisterJFrame.this.dispose();
						LoginJFrame loginJFrame = new LoginJFrame();
						loginJFrame.setVisible(true);
						System.out.println("注册成功");
					}
				} else {
					JOptionPane.showConfirmDialog(RegisterJFrame.this, "提示", "两次密码不一致", JOptionPane.YES_NO_OPTION,
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		JButton btnCancel = new JButton("取消");
		btnCancel.setBounds(232, 255, 93, 23);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				RegisterJFrame.this.dispose();
				LoginJFrame loginJFrame = new LoginJFrame();
				loginJFrame.setVisible(true);
			}
		});

		tbtnMan = new JRadioButton("男");
		tbtnMan.setBounds(159, 138, 54, 23);
		contentPane.add(tbtnMan);

		rbtnWoMan = new JRadioButton("女");
		rbtnWoMan.setBounds(232, 138, 54, 23);
		contentPane.add(rbtnWoMan);

		labaelPhone = new JLabel("电话");
		labaelPhone.setBounds(48, 214, 54, 15);
		contentPane.add(labaelPhone);

		fieldPhone = new JTextField();
		fieldPhone.setColumns(10);
		fieldPhone.setBounds(159, 206, 133, 21);
		contentPane.add(fieldPhone);
	}
}
