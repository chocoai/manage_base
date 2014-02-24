package cn.com.jandar.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.jandar.kit.DbUtil;
import cn.com.jandar.plugin.DicPlugin;

import com.jfinal.core.Controller;
import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

@TableBind(tableName = "ts_area")
public class Area extends Model<Area> {
	private static final long serialVersionUID = 9009527659862973858L;
	public static final Area dao = new Area();
	public static final String AREA = "Area";

	public static boolean isArea(Controller c) {
		return c.getSessionAttr(AREA) != null;
	}

	public static List<Area> getAreaTreeList() {

		List<Area> AreaTreeList = Area.dao.find("select * from ts_area");
		return AreaTreeList;
	}

	public static Area getArea(Object id) {
		return Area.dao.findById(id);
	}

	// 更新
	public static boolean update(String code) {
		boolean result = true;
		List<Area> areaList = Area.dao.find(
				"select * from ts_area where CODE like ?", code + "%");
		for (Area area : areaList) {
			if (area.getStr("QYBZ").equals("001")) {
				if (Db.update("update ts_area set QYBZ =? where CODE =?",
						"002", area.getStr("CODE")) <= 0) {
					result = false;
				}
			} else {
				if (Db.update("update ts_area set QYBZ =? where CODE =?",
						"001", area.getStr("CODE")) <= 0) {
					result = false;
				}
			}

		}

		return result;
	}

	/**
	 * @return
	 * 		查询有客户的区域
	 */
	public static Map<String,String> getCustomerAreaMap() {
		List<Area> customerAreaList = Area.dao.find("select * from ts_area area,b_customer b where area.code = b.BAREA  order by area.code ");
		Map<String,String> customer_area = new LinkedHashMap<String,String> ();
		if(customerAreaList!=null&&customerAreaList.size()>0){
			Map<String,String> areaAll  = DicPlugin.ts_area;
			for(Area area : customerAreaList){
				String code = DbUtil.readDbString(area.getStr("CODE"));
				customer_area.put(code,areaAll.get(code));
		  }
	    }
		return customer_area;
	}
}
