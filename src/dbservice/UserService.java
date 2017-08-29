package dbservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import model.User;

public class UserService {

	private Connection conn;

	public UserService() {
		conn = ConnectionFactory.getConnection();
	}

	/**
	 * 
	 * @param name
	 * @param pwd
	 * @return Id of the registered user, if success
	 */
	public int registerUser(String name, String pwd) {

		String sql = "insert into users(name, pwd) values(?,?)";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, name);
			stmt.setString(2, pwd);
			if (stmt.executeUpdate() > 0) {
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next())
					return rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * 
	 * @param name
	 * @param pwd
	 * @return Id of logged in user, if success
	 */
	public int loginUser(String name, String pwd) {
		String sql = "select * from users where name=? and pwd=? ";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, pwd);

			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				return rs.getInt("id");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public User getUserDetailsById(int id) {
		User user;
		String sql = "select * from users where id=?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setName(rs.getString("name"));
				user.setId(id);
				return user;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
