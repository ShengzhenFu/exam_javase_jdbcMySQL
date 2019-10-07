package jdbc_exam2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUtil {
	public static final String URL = "jdbc:mysql://localhost:3306/java_exam?characterEncoding=utf-8&useSSL=false";
	public static final String USER_NAME = "root";
	public static final String PASSWORD = "root";
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getCon() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

// 执行任意sql查询
	public static void executeQuery(String sql, ResultSetHandler handler, Object... params) {
		Connection con = getCon();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
// 设置参数
			for (int i = 1; i <= params.length; i++) {
				ps.setObject(i, params[i - 1]);
			}
			rs = ps.executeQuery();
// 调用传递进来的结果集处理器处理结果集
			handler.handle(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

// 执行任意sql更新
	public static void executeUpdate(String sql, Object... params) {
		Connection con = getCon();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			for (int i = 1; i <= params.length; i++) {
				ps.setObject(i, params[i - 1]);
			}
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
