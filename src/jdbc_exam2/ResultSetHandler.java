package jdbc_exam2;

import java.sql.ResultSet;
import java.sql.SQLException;

//结果集处理器
public interface ResultSetHandler {
	void handle(ResultSet rs) throws SQLException;
}
