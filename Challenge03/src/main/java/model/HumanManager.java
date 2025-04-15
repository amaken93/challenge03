package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HumanManager {
	private Connection co = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	public void setHuman(String name, String age, String group) {
		try {
			getConnection();

			String sql = "INSERT INTO user_list (name, age, group_id) VALUES(?, ?, ?)";
			ps = co.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, Integer.parseInt(age));
			ps.setInt(3, Integer.parseInt(group));
			ps.executeUpdate();

			co.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error dbConnect");
			e.printStackTrace();
		} finally {
			try {
				close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Human> getHumanList() {
		ArrayList<Human> list = new ArrayList<Human>();
		try {
			getConnection();

			String sql = "SELECT * FROM user_list INNER JOIN group_list on user_list.group_id = group_list.group_id";
			ps = co.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String group = rs.getString("group_name");
				Human human = new Human(name, age, group);
				list.add(human);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return list;
	}

	private void getConnection() throws ClassNotFoundException, SQLException {
		if (co == null) {
			Class.forName("org.postgresql.Driver");

			String url = "jdbc:postgresql://127.0.0.1:5432/test";
			String user = "postgres";
			String password = "";

			co = DriverManager.getConnection(url, user, password);
			co.setAutoCommit(false);
		}
	}

	private void close() throws SQLException {
		if (rs != null)
			rs.close();
		if (ps != null)
			ps.close();
		if (co != null)
			co.close();
	}
}
