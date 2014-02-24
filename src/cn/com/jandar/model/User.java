package cn.com.jandar.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.kit.StringKit;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

/**
 * @author
 * 
 */
@TableBind(tableName = "ts_user")
public class User extends Model<User> {
	private static final long serialVersionUID = -2063970373324095312L;
	public static final User dao = new User();
	public static final String LOGIN_USER = "loginUser"; // 登入用户session中的key
															// name

	/**
	 * 获得用户列表
	 * 
	 * @return
	 */
	public static List<User> getUserList() {
		List<User> userList = User.dao
				.find("select * from ts_user order by id");
		return userList;
	}

	public static User getUserById(String id) {
		return User.dao.findById(id);
	}

	public static String delete(String[] ids) {
		for (String id : ids) {
			if (!User.dao.deleteById(id)) {
				return "error";
			}
		}
		return "success";
	}

	public static Page<User> getUserPage(int sPageNum, int sPageSize,
			String orderBy, String order, String search) {
		List<Object> param = new ArrayList<Object>();
		StringBuffer sqlBuffer = new StringBuffer("FROM ts_user  ");
		if (!StringKit.isBlank(search)) {
			sqlBuffer
					.append("where (ts_user.username like ? or ts_user.email like ?)");
			param.add("%" + search + "%");
			param.add("%" + search + "%");
		}
		sqlBuffer.append(" order by ").append(orderBy).append(" ")
				.append(order);
		Page<User> userPage = (Page<User>) User.dao.paginate(sPageNum,
				sPageSize, "SELECT * ", sqlBuffer.toString(), param.toArray());
		return userPage;
	}

	public static String save(User user) {
		user.set("createDate", new Timestamp(System.currentTimeMillis()));
		user.set("modifyDate", new Timestamp(System.currentTimeMillis()));
		user.save();
		return "保存成功";
	}

	public static String update(User user) {
		user.set("modifyDate", new Timestamp(System.currentTimeMillis()));
		user.update();
		return "更新成功";
	}

	public static String isExitByUserName(String userName) {
		User user = User.dao.findFirst(
				"select * from ts_user where ts_user.username = ?", userName);
		if (user == null) {
			return "true";
		}
		return "false";
	}

	public static User getLoginUser(Controller c) {
		return c.getSessionAttr(LOGIN_USER);
	}

	public static boolean isLogin(Controller c) {
		return c.getSessionAttr(LOGIN_USER) != null;
	}

	public static User login(String loginName, String password) {
		if (StringKit.notBlank(loginName, password)) {
			return User.dao
					.findFirst(
							"select * from ts_user where username = ? and password = ?",
							loginName, password);
		}
		return null;
	}
}
