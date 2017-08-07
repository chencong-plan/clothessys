package cc.ccoder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator.OfDouble;

import cc.ccoder.entity.Orders;
import cc.ccoder.util.DBUtils;

/**
 * 
 * @author chencong 衣物实体数据层
 */
public class OrdersDao {

	public List<Orders> getOrdersList() {
		List<Orders> lists = new ArrayList<>();
		Connection connection = DBUtils.getConnection();
		if (connection == null) {
			return null;
		}
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		try {
			String sql = "select * from orders";
			pStatement = connection.prepareStatement(sql);
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				Orders orders = new Orders();
				orders.setId(resultSet.getInt("id"));
				orders.setClotherType(resultSet.getString("clothes_type"));
				orders.setPrice(resultSet.getDouble("price"));
				orders.setVipPrice(resultSet.getDouble("vip_price"));
				lists.add(orders);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (pStatement != null) {
					pStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lists;
	}

	public Integer insertOrderInfo(Orders orders) {
		if (orders == null) {
			return null;
		}
		Connection connection = DBUtils.getConnection();
		if (connection == null) {
			return null;
		}
		PreparedStatement pStatement = null;
		int count = 0;
		try {
			String sql = "insert into orders (clothes_type,price,vip_price) values (?,?,?)";
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, orders.getClotherType());
			pStatement.setDouble(2, orders.getPrice());
			pStatement.setDouble(3, orders.getVipPrice());
			count = pStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (pStatement != null) {
					pStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	public Integer updateOrderInfo(Orders orders) {
		Connection connection = DBUtils.getConnection();
		if (connection == null) {
			return null;
		}
		PreparedStatement pStatement = null;
		int count = 0;
		String sql = "update orders set clothes_type=?,price=?,vip_price=? where id = ?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, orders.getClotherType());
			pStatement.setDouble(2, orders.getPrice());
			pStatement.setDouble(3, orders.getVipPrice());
			pStatement.setInt(4, orders.getId());
			count = pStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (pStatement != null) {
					pStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	public Integer deleteOrdersInfo(Integer id) {
		if (id == null) {
			return null;
		}
		Connection connection = DBUtils.getConnection();
		PreparedStatement pStatement = null;
		int count = 0;
		String sql = "delete from orders where id =? ";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, id);
			count = pStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (pStatement != null) {
					pStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	
	public static void main(String[] args) {
		OrdersDao dao = new OrdersDao();
		Orders orders = new Orders();
		orders.setClotherType("小毛衣");
		orders.setPrice(122.5);
		orders.setVipPrice(115.5);
		System.out.println(dao.insertOrderInfo(orders));
	}
}
