package cn.com.jandar.model;

import java.util.List;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;

/**
 * @author
 * 
 */
@TableBind(tableName = "b_store")
public class Store extends Model<Store> {
	private static final long serialVersionUID = -2063970373324095312L;
	public static final Store dao = new Store();

	/**
	 * 获得单号类型列表
	 * 
	 * @return
	 */
	public static List<Store> getStoreList(String orderBy, String order) {
		StringBuffer sqlBuffer = new StringBuffer(" select * from b_store where 1=1 ");
		sqlBuffer.append(" order by ").append(orderBy).append(" ").append(order);
		List<Store> storeList = Store.dao.find(sqlBuffer.toString());

		return storeList;
	}

	public static Store getStoreById(String id) {
		return Store.dao.findById(id);
	}
	
	public static String getStoreMaxCHBH(){
       return Db.queryStr(" select max(ckbh) from b_store where 1=1");		
	}

	public static String save(Store store) {
		store.save();
		return "保存成功";
	}

	public static String update(Store store) {
		store.update();
		return "更新成功";
	}
	
	public static Store getStoreByCKBH(String CKBH) {
		String sql = "select * from b_store where CKBH = ? ";
		return Store.dao.findFirst(sql,CKBH);
	}

}
