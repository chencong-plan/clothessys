package cc.ccoder.swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.ws.soap.AddressingFeature;

import cc.ccoder.dao.OrdersDao;
import cc.ccoder.entity.Orders;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AddOrdersJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField fiedColthes;
	private JTextField fieldPrice;
	private JTextField fiedVipPrice;
	private JLabel labelShow;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddOrdersJFrame frame = new AddOrdersJFrame();
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
	public AddOrdersJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("消费项");
		label.setBounds(70, 40, 54, 15);
		contentPane.add(label);

		fiedColthes = new JTextField();
		fiedColthes.setBounds(136, 37, 126, 21);
		contentPane.add(fiedColthes);
		fiedColthes.setColumns(10);

		JLabel label_price = new JLabel("单价");
		label_price.setBounds(70, 80, 54, 15);
		contentPane.add(label_price);

		fieldPrice = new JTextField();
		fieldPrice.setColumns(10);
		fieldPrice.setBounds(136, 77, 126, 21);
		contentPane.add(fieldPrice);

		JLabel labelVipPrice = new JLabel("会员价");
		labelVipPrice.setBounds(70, 128, 54, 15);
		contentPane.add(labelVipPrice);

		fiedVipPrice = new JTextField();
		fiedVipPrice.setColumns(10);
		fiedVipPrice.setBounds(136, 128, 126, 21);
		contentPane.add(fiedVipPrice);

		JButton btnSubmit = new JButton("新增");
		btnSubmit.setBounds(85, 206, 93, 23);
		contentPane.add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String clothesType = fiedColthes.getText();
					double price = Double.parseDouble(fieldPrice.getText());
					double vipPrice = Double.parseDouble(fiedVipPrice.getText());
					Orders orders = new Orders();
					orders.setClotherType(clothesType);
					orders.setPrice(price);
					orders.setVipPrice(vipPrice);
					OrdersDao dao = new OrdersDao();
					if (dao.insertOrderInfo(orders) >= 1) {
						AddOrdersJFrame.this.dispose();
						OrdersJFrame ordersJFrame = new OrdersJFrame();
						ordersJFrame.setVisible(true);
						ordersJFrame.addJTable();
					}else {
						JOptionPane.showConfirmDialog(AddOrdersJFrame.this, "添加数据异常","错误",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
					}
					
				} catch (NumberFormatException e1) {
					//e1.printStackTrace();
					JOptionPane.showConfirmDialog(AddOrdersJFrame.this, "输入框不能够为空","错误",JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE);
					labelShow.setText("请输入正确格式的价格");
				}
				labelShow.setText("");
			}
		});

		JButton btnBack = new JButton("返回");
		btnBack.setBounds(216, 206, 93, 23);
		contentPane.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddOrdersJFrame.this.dispose();
				OrdersJFrame ordersJFrame = new OrdersJFrame();
				ordersJFrame.setVisible(true);
			}
		});

		labelShow = new JLabel("");
		labelShow.setBounds(143, 169, 107, 15);
		contentPane.add(labelShow);
	}

}
