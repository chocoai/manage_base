package cn.com.jandar.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.kit.StringKit;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

/**
 * 
 * @author wy
 *
 */
@TableBind(tableName = "b_cjgz")
public class Cjgz extends Model<Cjgz>{
	
	private static final long serialVersionUID = 1L;
	public static final Cjgz dao = new Cjgz();
  
	public static Page<Cjgz> getCjgzPage(int sPageNum, int sPageSize,
			String orderBy, String order, String sblx) {
		List<Object> param = new ArrayList<Object>();
		StringBuffer sqlBuffer = new StringBuffer("FROM b_cjgz");
		String sql = " where (1=1 ";
		if(!StringKit.isBlank(sblx)) {
			sql += " and b_cjgz.SBLX like '%" + sblx + "%'";
		}
		
		sql += ")";
		sqlBuffer.append(sql);
		sqlBuffer.append(" order by ").append(orderBy).append(" ")
				.append(order);
		Page<Cjgz> cjgzPage = Cjgz.dao.paginate(sPageNum, sPageSize,
				"SELECT * ", sqlBuffer.toString(), param.toArray());
		return cjgzPage;
	}
	
	 public static Cjgz getCjgzById(String ID){
	        return Cjgz.dao.findById(ID);
    }
	 
	 public static String save(Cjgz cjgz) {
		    cjgz.set("OPDATE", new Timestamp(System.currentTimeMillis()));
		    cjgz.set("UPIPDATE", new Timestamp(System.currentTimeMillis()));
		    cjgz.save();
	        return "保存成功";
	}

	 public static String update(Cjgz cjgz) {
	    	cjgz.set("UPIPDATE", new Timestamp(System.currentTimeMillis()));
	    	cjgz.update();
	        return "更新成功";
	}
}
