package cn.com.jandar.model;

import java.sql.Timestamp;
import java.util.List;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

/**
 * @author
 */
@TableBind(tableName = "c_produce_detail")
public class ProduceDetail extends Model<ProduceDetail> {
	private static final long serialVersionUID = -2063970373324095312L;
	public static final ProduceDetail dao = new ProduceDetail();

	// 默认从入库仓库取
	public static List<ProduceDetail> getProduceByDH(String dh, String SZCK) {
		List<ProduceDetail> produceDetailList = ProduceDetail.dao.find(
				"select * from c_produce_detail where dh = ? and SZCK = ?", dh,
				SZCK);
		return produceDetailList;
	}

	public static List<ProduceDetail> getProduceList() {
		List<ProduceDetail> produceList = ProduceDetail.dao
				.find("select * from c_produce_detail order by id");
		return produceList;
	}

	public static boolean save(ProduceDetail produceDetail, String out,
			String in, String[] deciveArray, String[] numArray) {
		boolean result = true;
		boolean checkResult = true;
		// 得到数量组
		int i = 0;

		for (String id : deciveArray) {
			int num = Integer.parseInt(numArray[i]);
			System.out.println(num + "-" + id + "-" + out);
			if (!ProduceDetailDraft.judgeDeviceTotal(num, id, out)) {
				// 有一种设备数量不合要求就中断判断
				checkResult = false;
				result = false;
				System.out.println(1 + "*" + result);
				break;
			}
			i++;
		}

		i = 0;
		if (checkResult) {
			// 遍历保存,现在是草稿只考虑出库
			for (String id : deciveArray) {
				int num = Integer.parseInt(numArray[i]);

				ProduceDetail in_draft = new ProduceDetail(); // 进库
				ProduceDetail out_draft = new ProduceDetail(); // 出库
				out_draft.setAttrs(produceDetail);
				in_draft.setAttrs(produceDetail);
				out_draft.set("DEVICEID", Integer.valueOf(id));
				in_draft.set("DEVICEID", Integer.valueOf(id));
				for (int k = 0; k < num; k++) {
					out_draft.set("id", null);
					out_draft.set("num", 1);
					out_draft.set("CHNUM", -1);
					out_draft.set("SZCK", out);
					result = out_draft.save();
					System.out.println(1 + "-" + result);
					in_draft.set("num", 1);
					in_draft.set("CHNUM", 1);
					in_draft.set("SZCK", in);
					result = in_draft.save();
				}
				i++;
			}
		}

		return result;
	}

	/**
	 * 出库或者入库
	 * 
	 * @param type
	 *            =0 入库 type=1 出库
	 * */
	public static boolean saveInOrOut(ProduceDetail produceDetail, String in,
			String out, int type, String[] deciveArray, String[] numArray) {
		boolean result = true;

		int i = 0;
		String storeId; // 仓库Id
		int chnum = 1;
		boolean checkResult = true;
		// 遍历保存,现在是草稿只考虑出库
		if (type == 0) {
			storeId = in;

		} else {
			storeId = out;
			chnum = -1;
			int j = 0;
			// 出库的情况需要判断产品数量
			for (String id : deciveArray) {
				int num = Integer.parseInt(numArray[i]);
				if (!ProduceDetailDraft.judgeDeviceTotal(num, id, out)) {
					// 有一种设备数量不合要求就中断判断
					checkResult = false;
					result = false;
					break;
				}
				j++;
			}

		}

		if (result && checkResult) {
			for (String id : deciveArray) {
				int num = Integer.parseInt(numArray[i]);
				ProduceDetail in_draft = new ProduceDetail(); // 进库
				in_draft.setAttrs(produceDetail);
				in_draft.set("DEVICEID", Integer.valueOf(id));

				for (int k = 0; k < num; k++) {
					in_draft.set("id", null);
					in_draft.set("num", 1);
					in_draft.set("CHNUM", chnum);
					in_draft.set("SZCK", storeId);
					result = in_draft.save();
				}
				i++;
			}
		}

		return result;
	}

	public static String update(ProduceDetail produce) {
		produce.set("modifyDate", new Timestamp(System.currentTimeMillis()));
		produce.update();
		return "更新成功";
	}

	/**
	 * @param DH
	 * @return 编辑与查看详情时显示设备详情
	 * 
	 */
	public static List<Record> getProduceDetailByDH(String DH) {
		String sql = "select *,sum(NUM)sums from c_produce_detail d  where DH = '"
				+ DH + "' group by d.DEVICEID";
		return Db.find(sql);
	}

	public static List<Record> getProduceDetailForReportByDH(String DH) {
		String sql = "select b.*,d.*,b.factoryid factoryid,sum(NUM)sums from c_produce_detail d,b_device b  where b.ID = d.DEVICEID and  DH = '" + DH + "' group by d.DEVICEID";
		return Db.find(sql);
	}

	/**
	 * @param DH
	 * @return 编辑与查看详情时显示设备详情
	 * 
	 */
	public static List<Record> getYkdProduceDetailByDH(String DH) {
		String sql = "select *,sum(NUM)sums from c_produce_detail d  where DH = '"
				+ DH + "' and CHNUM = '-1'  group by d.DEVICEID";
		return Db.find(sql);
	}

	/**
	 * 编辑时批量删除原来的设备详情
	 */
	public static String deleteByDH(String DH) {
		String sql = "select * from c_produce_detail   where DH = '" + DH + "'";
		List<ProduceDetail> list = ProduceDetail.dao.find(sql);
		if (list != null && list.size() > 0) {
			for (ProduceDetail p : list) {
				ProduceDetail.dao.deleteById(p.getInt("ID"));
			}
		}
		return "success";
	}

	public static List<Record> getProduceDetailByDHYiKu(String DH) {
		String sql = "select *,sum(NUM)/2 sums from c_produce_detail d  where DH = '"
				+ DH + "' group by d.DEVICEID";
		return Db.find(sql);
	}

}
