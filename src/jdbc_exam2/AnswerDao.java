package jdbc_exam2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Dao,访问数据库t_answers表数据
public class AnswerDao {
	public List<Answer> select() {
		List<Answer> answers = new ArrayList<Answer>();
		DbUtil.executeQuery("select * from t_answers", new ResultSetHandler() {
			@Override
			public void handle(ResultSet rs) throws SQLException {
				while (rs.next()) {
					Answer answer = new Answer();
					answer.setId(rs.getInt(1));
					answer.setAnswer(rs.getString(2));
					answer.setItemId(rs.getInt(3));
					answers.add(answer);
				}
			}
		});
		return answers;
	}

// 保存答案
	public void save(Answer answer) {
		DbUtil.executeUpdate("insert into t_answers(answer, i_id) values(?, ?)", answer.getAnswer(),
				answer.getItemId());
	}

// 更新考生提交的答案
	public void update(Answer answer) {
		DbUtil.executeUpdate("update t_answers set answer = ? where i_id = ?", answer.getAnswer(), answer.getItemId());
	}

// 删除所有的答案
	public void delete() {
		DbUtil.executeUpdate("delete from t_answers");
	}

// 通过题号查询考生提交的答案
	public Answer getByItemId(int itemId) {
		Answer answer = new Answer();
		DbUtil.executeQuery("select * from t_answers where i_id = ?", new ResultSetHandler() {
			@Override
			public void handle(ResultSet rs) throws SQLException {
				if (rs.next()) {
					answer.setId(rs.getInt(1));
					answer.setAnswer(rs.getString(2));
					answer.setItemId(rs.getInt(3));
				}
			}
		}, itemId);
		return answer;
	}

//查询答案的数量
	public int getCount() {
		int[] count = new int[1];
		DbUtil.executeQuery("select count(*) from t_answers", new ResultSetHandler() {
			@Override
			public void handle(ResultSet rs) throws SQLException {
				rs.next();
				count[0] = rs.getInt(1);
			}
		});
		return count[0];
	}

	// 保存或者修改
	public void saveOrUpdate(Answer answer) {
		// 查询数据库中此题目的答案
		Answer dbAnswer = getByItemId(answer.getItemId());
		if (dbAnswer.getAnswer() != null) {// 已经有答案
			update(answer);// 修改答案
		} else {// 如果没有答案
			save(answer);// 保存答案
		}
	}
}
