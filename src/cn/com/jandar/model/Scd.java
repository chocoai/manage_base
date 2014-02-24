package cn.com.jandar.model;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.kit.StringKit;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

/**
 * @author
 */
@TableBind(tableName = "p_scd")
public class Scd extends Model<Scd> {
	private static final long serialVersionUID = -2063970373324095312L;
	public static final Scd dao = new Scd();

	/**
	 * 获得单据列表
	 * 
	 * @return
	 */
	public static Page<Scd> getScdList(int sPageNum, int sPageSize,
			String orderBy, String order, String ddzt,
			String SEARCH_LIKES_CUSTOMER, String SEARCH_LIKES_DATETIME_BEGIN,
			String SEARCH_LIKES_DATETIME_END) {

		// SELECT p2.CUSTOMERID,p2.id p2Id, p1.* FROM p_scdsb p1,p_scd p2 WHERE
		// p2.CUSTOMERID IN(10) AND p2.ID = p1.SCDID;
		List<Object> param = new ArrayList<Object>();
		
		
		StringBuffer sqlBuffer = new StringBuffer(
				"   FROM p_scd , p_scdsb sb WHERE sb.SCDID = p_scd.ID");

		if(!StringKit.isBlank(ddzt)){
			sqlBuffer.append(" and p_scd.ddzt = ? ");
			param.add(ddzt);
		}else{
			sqlBuffer.append(" and p_scd.ddzt != '010' and p_scd.ddzt != '011' ");
		}

		if (!StringKit.isBlank(SEARCH_LIKES_CUSTOMER)) {
			sqlBuffer.append(" and p_scd.CUSTOMERID = ? ");
			param.add(SEARCH_LIKES_CUSTOMER);
		}
		if (!StringKit.isBlank(SEARCH_LIKES_DATETIME_BEGIN)) {
			sqlBuffer.append(" and p_scd.DDCJSJ >=? ");
			param.add(SEARCH_LIKES_DATETIME_BEGIN);
		}
		if (!StringKit.isBlank(SEARCH_LIKES_DATETIME_END)) {
			sqlBuffer.append(" and p_scd.DDCJSJ  <= ? ");
			param.add(SEARCH_LIKES_DATETIME_END);
		}

		sqlBuffer.append(" GROUP BY sb.SCDID order by ").append(orderBy)
				.append(" ").append(order);
		
		Page<Scd> scdPage = (Page<Scd>) Scd.dao.paginate(sPageNum, sPageSize,
				"SELECT  p_scd.*,sb.SCSBBH,sb.SCDID  ", sqlBuffer.toString(),
				param.toArray());

		return scdPage;
	}

	
	/**
	 * 分页查询数据库订单列表
	 * 
	 * @return
	 */
	public static Page<Scd> getScds(int sPageNum, int sPageSize,
			String orderBy, String order, String ddzt,
			String SEARCH_LIKES_CUSTOMER, String SEARCH_LIKES_DATETIME_BEGIN,
			String SEARCH_LIKES_DATETIME_END) {

		List<Object> param = new ArrayList<Object>();
		StringBuffer sqlBuffer = new StringBuffer(
				" FROM p_scd LEFT JOIN c_produce ON ");
		
		if(!StringKit.isBlank(ddzt)){
			sqlBuffer.append("p_scd.ZFRKDH = c_produce.dh where 1=1 ");
		} else{
			sqlBuffer.append("p_scd.ckdh = c_produce.dh where 1=1 ");
		}
		if(!StringKit.isBlank(ddzt)){
			sqlBuffer.append(" and p_scd.ddzt = ? ");
			param.add(ddzt);
		}else{
			sqlBuffer.append(" and p_scd.ddzt != '010' and p_scd.ddzt != '011' ");
		}

		if (!StringKit.isBlank(SEARCH_LIKES_CUSTOMER)) {
			sqlBuffer.append(" and p_scd.CUSTOMERID = ? ");
			param.add(SEARCH_LIKES_CUSTOMER);
		}
		if (!StringKit.isBlank(SEARCH_LIKES_DATETIME_BEGIN)) {
			sqlBuffer.append(" and p_scd.DDCJSJ >=? ");
			param.add(SEARCH_LIKES_DATETIME_BEGIN);
		}
		if (!StringKit.isBlank(SEARCH_LIKES_DATETIME_END)) {
			sqlBuffer.append(" and p_scd.DDCJSJ  <= ? ");
			param.add(SEARCH_LIKES_DATETIME_END);
		}
		
		Page<Scd> scdPage = (Page<Scd>) Scd.dao.paginate(sPageNum, sPageSize,
				"SELECT  p_scd.*,c_produce.DHZT ", sqlBuffer.toString(),
				param.toArray());

		return scdPage;
	}
	
	/**
	 * 根据单号查询单据S
	 * 
	 * @param dh
	 *            (单号)
	 */
	public static Scd getscdByDh(String dh) {
		return Scd.dao.findFirst("select * from p_scd where dh = ?", dh);
	}

	/**
	 * @param user
	 * @param scd
	 * @return
	 */
	public static synchronized boolean save(User user, Scd scd) {
		scd.set("OPERATOR", user.getStr("username"));
		scd.set("OPDATE", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
		scd.set("DDCJSJ", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
		return scd.save();
	}

	/**
	 * @param user
	 * @param scd
	 * @return
	 */
	public static synchronized boolean update(User user, Scd scd) {
		scd.set("UPOPERATOR", user.getStr("username"));
		scd.set("UPIPDATE", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));

		return scd.update();
	}

	public static Scd getscdById(String id) {
		return Scd.dao.findFirst("select * from p_scd where id = ?", id);
	}

}
