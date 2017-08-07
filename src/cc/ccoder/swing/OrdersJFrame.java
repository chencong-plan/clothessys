package cc.ccoder.swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.security.auth.kerberos.KerberosTicket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import cc.ccoder.dao.OrdersDao;
import cc.ccoder.entity.Orders;

public class OrdersJFrame extends JFrame {

	private JPanel contentPane;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;

	private JTable jTable;

	private DefaultTableModel tableModel;

	private int selectId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrdersJFrame frame = new OrdersJFrame();
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
	public OrdersJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();

		// 添加值
		addJTable();

		btnAdd = new JButton("新增");
		btnUpdate = new JButton("修改");
		btnDelete = new JButton("删除");

		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] rowNums = jTable.getSelectedRows();
				int[] idNums = new int[rowNums.length];
				StringBuffer sBuffer = new StringBuffer();
				for (int i = 0; i < rowNums.length; i++) {
					if (rowNums[i] >= 0) {
						sBuffer.append(rowNums[i] + " ");
						idNums[i] = (int) tableModel.getValueAt(rowNums[i], 0);
					} else {
						JOptionPane.showConfirmDialog(OrdersJFrame.this, "你还没有选中行", "提示", JOptionPane.YES_OPTION,
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
				int check = JOptionPane.showConfirmDialog(OrdersJFrame.this, "是否删除" + sBuffer.toString() + " 列", "提示",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (check == 0) {
					OrdersDao dao = new OrdersDao();
					for (int i : idNums) {
						dao.deleteOrdersInfo(i);
						System.out.println(i);
					}
				}
				OrdersJFrame.this.dispose();
				OrdersJFrame.this.setVisible(true);
			}
		});
		contentPane.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OrdersJFrame.this.dispose();
				AddOrdersJFrame addOrdersJFrame = new AddOrdersJFrame();
				addOrdersJFrame.setVisible(true);
			}
		});
		contentPane.add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowNum = jTable.getSelectedRow();
				int id = (int) tableModel.getValueAt(rowNum, 0);
				System.out.println(id);
				OrdersJFrame.this.dispose();
				UpdateJFrame.selectId = id;
				UpdateJFrame updateJFrame = new UpdateJFrame();
				updateJFrame.setVisible(true);
			}
		});
		contentPane.add(btnDelete);
		this.add(contentPane, BorderLayout.NORTH);

	}

	public void addJTable() {
		String[] cols = { "序号", "消费项", "单价", "会员价" };
		tableModel = new DefaultTableModel(cols, 0);
		// 创建一个Jtable
		jTable = new JTable(tableModel);
		// 使JTable内容居中
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(JLabel.CENTER);
		jTable.setDefaultRenderer(Object.class, renderer);
		// 给JTable添加内容项
		OrdersDao dao = new OrdersDao();
		List<Orders> lists = dao.getOrdersList();
		for (Orders orders : lists) {
			tableModel.addRow(
					new Object[] { orders.getId(), orders.getClotherType(), orders.getPrice(), orders.getVipPrice() });
		}
		JScrollPane scrollPane = new JScrollPane(jTable);
		this.add(scrollPane);
	}

}
