package cc.ccoder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cc.ccoder.entity.User;
import cc.ccoder.util.DBUtils;

/**
 * 
 * @author chencong 用户操作
 */
public class UserDao {

	public Integer userLogin(String username, String password) {
		Connection connection = DBUtils.getConnection();
		if (connection == null) {
			return null;
		}
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		int count = 0;
		try {
			String sql = "select count(1) from User where username=? and password=?";
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, username);
			pStatement.setString(2, password);
			resultSet = pStatement.executeQuery();
			if (resultSet.next()) {
				count = resultSet.getInt(1);
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
		return count;
	}

	public Integer insertUserInfo(User user) {
		if (user == null) {
			return null;
		}
		Connection connection = DBUtils.getConnection();
		if (connection == null) {
			return null;
		}
		PreparedStatement pStatement = null;
		int count = 0;
		try {
			String sql = "insert into User (username,password,sex,city,phone) values (?,?,?,?,?)";
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, user.getUsername());
			pStatement.setString(2, user.getPassword());
			pStatement.setString(3, user.getSex());
			pStatement.setString(4, user.getCity());
			pStatement.setString(5, user.getPhone());
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
		UserDao dao = new UserDao();
		//System.out.println(dao.userLogin("admin", "admin"));
		User user = new User();
		user.setUsername("chencong");
		user.setPassword("123456");
		user.setSex("男");
		user.setCity("武汉");
		user.setPhone("123123131312");;
		System.out.println(dao.insertUserInfo(user));
	}
}
