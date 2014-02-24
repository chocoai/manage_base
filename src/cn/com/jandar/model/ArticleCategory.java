package cn.com.jandar.model;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;


@TableBind(tableName="articlecategory")
public class ArticleCategory extends Model<ArticleCategory> {
	private static final long serialVersionUID = 9009527659862973858L;
	public static final ArticleCategory dao = new ArticleCategory();
	public static final String ARTICLE_CATEGORY = "articlecategory";

	public static boolean isArticlecategory(Controller c){
		return c.getSessionAttr(ARTICLE_CATEGORY) != null;
	}
	public static List<ArticleCategory> getArticleCategoryTreeList() {
		//path*1 "*1"表示将字符串中的数字以数字的形式排序
		List<ArticleCategory> articleCategoryTreeList = ArticleCategory.dao.find("select * from articlecategory order by path*1");
		return articleCategoryTreeList;
	}
	public static ArticleCategory getArticleCategory(Object id) {
		return ArticleCategory.dao.findById(id);
	}

	// 保存
	public static String save(ArticleCategory articleCategory) {
		String pPath = "";// 父亲路径
		// 添加系统值id为空
		articleCategory.set("createDate",new Timestamp(System.currentTimeMillis()));
		articleCategory.set("modifyDate",new Timestamp(System.currentTimeMillis()));
		// 将articleCategory保存
		articleCategory.save();
		// 得到父类节点
		ArticleCategory articleCategoryParent = ArticleCategory.dao.findById(articleCategory.get("parent_id"));

		if (articleCategoryParent != null) {
			pPath = articleCategoryParent.get("path") + ",";
		}
		// 通过父类节点计算path和orderId并更新
		articleCategory.set("path", pPath + articleCategory.get("orderList")+ "-" + articleCategory.get("id"));
		articleCategory.update();
		return "保存成功";
	}

	// 更新
	public static String update(ArticleCategory articleCategory) {
		String oldPath = articleCategory.getStr("path");
		String newPath = null;
		String tempPath = articleCategory.get("orderList") + "-" + articleCategory.get("id");
		String[] str = oldPath.split(",");
		// 如果orderList发生改变，则更新path字段和孩子的path字段
		if (!str[str.length - 1].equals(tempPath)) {
			// 计算path并更新
			str[str.length - 1] = tempPath;
			newPath = str[0];
			for (int i = 1; i < str.length; i++) {
				newPath += "," + str[i];
			}
			articleCategory.set("path", newPath);
			// 通过path字段模糊长训 查找孩子，去掉本身
			List<ArticleCategory> articleCategoryChildList = ArticleCategory.dao.find("select * from articlecategory where id!=? and path like ?",articleCategory.get("id"), oldPath + "%");
			// 修改孩子中path字段
			for (int i = 0; i < articleCategoryChildList.size(); i++) {
				ArticleCategory tempArticleCategory = articleCategoryChildList.get(i);
				tempPath = tempArticleCategory.get("path");
				newPath = tempPath.replaceFirst(oldPath, newPath);// 替换孩子path字段中与父亲path字段相同部分
				tempArticleCategory.set("path", newPath);
				tempArticleCategory.update();// 更新孩子节点
			}
		}
		articleCategory.set("modifyDate",new Timestamp(System.currentTimeMillis()));
		articleCategory.update();
		return "修改成功";
	}

	public static List<String> deleteNodeById(String id) {
		List<String> errorList=new ArrayList<String>();
		if (id != null && !id.equals("")) {
			if (true) {
				errorList.add("删除失败：当前分类中有文章!");
				errorList.add("请在文章管理中先删除该分类文章，否则不能删除该分类!");
			} else {
				if (ArticleCategory.dao.deleteById(id)) {
					return null;
				} else {
					errorList.add("删除失败,系统错误！请联系管理员！");
				}
			}
		} else {
			errorList.add("删除失败，非法操作！");
		}
		return errorList;
	}
}
