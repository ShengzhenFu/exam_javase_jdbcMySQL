package jdbc_exam2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExamMain {

	public static Scanner s = new Scanner(System.in);
	public static ItemDao itemDao = new ItemDao();
	public static AnswerDao answerDao = new AnswerDao();

	public static void main(String[] args) {
		while (true) {
			System.out.println("******************");
			System.out.println("考试管理主菜单");
			System.out.println("1 进入考试.");
			System.out.println("2 显示上次成绩和答案.");
			System.out.println("3 退出考试系统.");
			System.out.println("请输入(1-3):");
			System.out.println("******************");
			char c = getUserAction();
			if (c == '1') {
				// 进入考试
				startExam();
			} else if (c == '2') {
				// 显示上次的成绩和答案
				calcScore();
			} else if (c == '3') {
				// 退出考试系统
				System.exit(0);;
			} else {
				System.out.println("输入有误");
			}
		}
	}

	public static void startExam() {
		// 进入考试
		// 打印菜单
		System.out.println("******************");
		System.out.println("欢迎进入考试系统");
		System.out.println("按键定义如下:");
		System.out.println("N 下一题,P 上一题,ABCD做出选择,F 结束考试");
		System.out.println("键入N开始考试,键入F结束考试");
		System.out.println("******************");
		// 必须键入N才能开始考试
		char cc = getUserAction();
		while (cc != 'N') {
			System.out.println("键入N开始考试");
			cc = getUserAction();
		}
		// 清除之前的答案
		answerDao.delete();
		// 当前做到哪一题了
		int currentNo = 1;
		while (true) {
			// 显示题目内容
			if (currentNo >= 1 && currentNo <= itemDao.getCount()) {
				displayItem(currentNo);
			}
			// 获取考生输入
			char ccc = getUserAction();
			// 如果考生输入了ABCD,把答案保存到数据库
			if (ccc == 'A' || ccc == 'B' || ccc == 'C' || ccc == 'D') {
				Answer answer = new Answer();
				answer.setAnswer(String.valueOf(ccc));
				answer.setItemId(currentNo);
				// 如果此题目没有答案就保存答案,如果有答案,就修改答案
				answerDao.saveOrUpdate(answer);
				if (currentNo < itemDao.getCount()) {
					// 题目编号自增
					currentNo++;
				}
			} else if (ccc == 'N') {// 进入下一题
				if (currentNo < itemDao.getCount()) {
					// 题目编号自增
					currentNo++;
				} else {
					System.out.println("已经是最后一题了");
				}
			} else if (ccc == 'P') {// 进入上一题
				if (currentNo > 1) {
					// 题目编号自减
					currentNo--;
				} else {
					System.out.println("当前已经是第一题了");
				}
			} else if (ccc == 'F') {// 结束考试
				// 结束之前判断一下是否还有题目未做
				int itemCount = itemDao.getCount();// 查询题目的数量
				int answerCount = answerDao.getCount();// 查询答案的数量
				if (itemCount == answerCount) {// 题目答完了,可以提交
					// 显示正确答案和考生答案,并计算分值
					calcScore();
					break;
				} else {
					System.out.println("题目未答完");
					// 保存没有作答的题目编号
					List<Integer> list = new ArrayList<Integer>();
					List<Item> items = itemDao.select();
					for (int i = 0; i < items.size(); i++) {
						Item item = items.get(i);
						// 使用题目编号,查询数据库中是否有对应的答案
						Answer answer = answerDao.getByItemId(item.getId());
						if (answer.getAnswer() == null) {// 此题目没有作答
							list.add(item.getId());// 将没有作答的题目编号加入到集合中
						}
					}
					// 显示没有作答的题目编号
					for (Integer i : list) {
						System.out.print("第" + i + "题");
					}
					System.out.println("没有作答");
				}
			} else {
				System.out.println("输入有误");
			}
		}
	}

	private static void calcScore() {
		System.out.println("******************");
		// 没有考过试
		if (answerDao.getCount() == 0) {
			System.out.println("还没有参加过考试");
			return;
		}
		// 显示正确答案
		List<Item> items = itemDao.select();
		for (Item item : items) {
			System.out.print(item.getAnswer() + " ");
		}
		System.out.println();
		// 显示考生答案
		List<Answer> answers = answerDao.select();
		for (Answer answer : answers) {
			System.out.print(answer.getAnswer() + " ");
		}
		System.out.println();
		int score = 0;
		// 计算分值
		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			Answer answer = answers.get(i);
			if (item.getAnswer().equals(answer.getAnswer())) {
				score = score + item.getScore();
			}
		}
		System.out.println("你的总分是:" + score);
		System.out.println("******************");
	}

	// 显示题目内容
	private static void displayItem(int currentNo) {
		System.out.println("******************");
		// 从数据库查询Item对象
		Item item = itemDao.get(currentNo);
		// 输出题目内容
		System.out.println(item);
		// 如果考生已经答过此题,则显示考生之前的答案
		// 查询数据库中此题目的答案
		Answer answer = answerDao.getByItemId(currentNo);
		if (answer.getAnswer() != null) {
			System.out.println("你之前的答案:" + answer.getAnswer());
		}
		System.out.println("******************");
	}

	// 获取用户输入的字符
	public static char getUserAction() {
		// 读取一整行
		String str = s.nextLine();
		char c = 0;
		if (str.length() > 0) {
			// 取第一个字符
			c = str.charAt(0);
		}
		while (c != -1) {
			// 统一转成大写字符
			c = Character.toUpperCase(c);
			if (c == '1' || c == '2' || c == '3' || c == 'N' || c == 'P' || c == 'A' || c == 'B' || c == 'C' || c == 'D'
					|| c == 'F') {
				break;
			} else {
				System.out.println("只能输入123NPABCDF");
				// 让用户重新输入
				str = s.nextLine();
				if (str.length() > 0) {
					// 取第一个字符
					c = str.charAt(0);
				}
			}
		}
		return c;
	}

}
