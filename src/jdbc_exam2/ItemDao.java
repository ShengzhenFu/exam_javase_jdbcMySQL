package jdbc_exam2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Dao Data Access Object 提供数据库访问的功能
 *
 * @author
 *
 */
public class ItemDao {
// 查询t_items表中的所有数据,获得一个Item对象的集合
	public List<Item> select() {
		List<Item> items = new ArrayList<Item>();
		DbUtil.executeQuery("select * from t_items", new ResultSetHandler() {
			@Override
			public void handle(ResultSet rs) throws SQLException {
				while (rs.next()) {
					Item item = new Item();
					item.setId(rs.getInt(1));
					item.setQuestion(rs.getString(2));
					item.setOptionA(rs.getString(3));
					item.setOptionB(rs.getString(4));
					item.setOptionC(rs.getString(5));
					item.setOptionD(rs.getString(6));
					item.setAnswer(rs.getString(7));
					item.setScore(rs.getInt(8));
// 将item对象加入到集合中
					items.add(item);
				}
			}
		});
		return items;
	}

// 根据编号查询指定的题目
	public Item get(int n) {
		Item item = new Item();
		DbUtil.executeQuery("select * from t_items where id = ?", new ResultSetHandler() {
			@Override
			public void handle(ResultSet rs) throws SQLException {
				rs.next();
				item.setId(rs.getInt(1));
				item.setQuestion(rs.getString(2));
				item.setOptionA(rs.getString(3));
				item.setOptionB(rs.getString(4));
				item.setOptionC(rs.getString(5));
				item.setOptionD(rs.getString(6));
				item.setAnswer(rs.getString(7));
				item.setScore(rs.getInt(8));
			}
		}, n);
		return item;
	}

// 查询数据库中题目的数量
	public int getCount() {
		int[] count = new int[1];
		DbUtil.executeQuery("select count(*) from t_items", new ResultSetHandler() {
			@Override
			public void handle(ResultSet rs) throws SQLException {
				rs.next();
				count[0] = rs.getInt(1);
			}
		});
		return count[0];
	}
}