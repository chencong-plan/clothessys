package cc.ccoder.swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class UpdateJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField fieldColthes;
	private JTextField fieldPrice;
	private JTextField fieldVipPrice;
	
	public static int selectId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateJFrame frame = new UpdateJFrame();
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
	public UpdateJFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labelClothes = new JLabel("消费项目");
		labelClothes.setBounds(65, 41, 54, 15);
		contentPane.add(labelClothes);

		fieldColthes = new JTextField();
		fieldColthes.setBounds(148, 38, 166, 21);
		contentPane.add(fieldColthes);
		fieldColthes.setColumns(10);

		JLabel labelPrice = new JLabel("单价");
		labelPrice.setBounds(65, 80, 54, 15);
		contentPane.add(labelPrice);

		JLabel labelVipPrice = new JLabel("会员单价");
		labelVipPrice.setBounds(65, 128, 54, 15);
		contentPane.add(labelVipPrice);

		fieldPrice = new JTextField();
		fieldPrice.setColumns(10);
		fieldPrice.setBounds(148, 77, 166, 18);
		contentPane.add(fieldPrice);

		fieldVipPrice = new JTextField();
		fieldVipPrice.setColumns(10);
		fieldVipPrice.setBounds(148, 125, 166, 18);
		contentPane.add(fieldVipPrice);

		//被选择的id
		System.out.println(this.selectId);
		
		
		JButton btnUpdate = new JButton("修改");
		btnUpdate.setBounds(92, 192, 93, 23);
		contentPane.add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		JButton btnBack = new JButton("返回");
		btnBack.setBounds(241, 192, 93, 23);
		contentPane.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UpdateJFrame.this.dispose();
				OrdersJFrame ordersJFrame = new OrdersJFrame();
				ordersJFrame.setVisible(true);
			}
		});
	}

}
