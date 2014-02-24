package cn.com.jandar.kit;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import cn.com.jandar.model.User;
import cn.com.jandar.plugin.DicPlugin;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class Constant {

	/**
	 * 用户菜单生成
	 * @param roleStr 所有能使用的功能
	 * @param user 用户登录信息
	 * @return
	 */
	public static User createManagerMenu(String roleStr,User user){
		Map<String,List<Record>> menuMap = new LinkedHashMap<String,List<Record>>();//格式:
		Map<String,String> valuecatalog = new LinkedHashMap<String,String>();//格式:(基本信息:2)
		Map<String,String> codecatalog = new LinkedHashMap<String,String>();//格式:(2:基本信息)
		Map<String,String> imagecatalog = new LinkedHashMap<String,String>();//格式:(基本信息:index.jpg)
		StringBuffer sbcatalog = new StringBuffer("");//存放有权限的目录
		for(Record e:DicPlugin.menuList){
			String parent_id = DbUtil.readDbString(e.getStr("PARENT_ID"));
			String url = DbUtil.readDbString(e.getStr("URL"));
//			System.out.println("PARENT_ID:"+parent_id);
//			System.out.println("URL:"+url);
			if(!"".equals(url)&&roleStr.indexOf(url)!=-1){
				if(menuMap.get(parent_id)==null){
					List<Record> mlist = new ArrayList<Record>(); 
					mlist.add(e);
					menuMap.put(parent_id, mlist);
				}else{
					List<Record> mlist = menuMap.get(parent_id);
					mlist.add(e);
				}
				sbcatalog.append(parent_id+",");
			}
		}
		System.out.println("----------------生成目录");
		String sbc = sbcatalog.toString();
		valuecatalog.put("首页", "0");
		imagecatalog.put("首页", "index0.png");
		for(Record e:DicPlugin.catalogList){
			String name = DbUtil.readDbString(e.getStr("NAME"));
			String code = DbUtil.readDbString(e.getInt("CODE"));
			String images = DbUtil.readDbString(e.getStr("IMAGES"));
//			System.out.println("NAME:"+name);
//			System.out.println("CODE:"+code);
//			System.out.println("IMAGES:"+images);
			if(!"".equals(code)&&sbc.indexOf(code)!=-1){
				valuecatalog.put(name, code);
				codecatalog.put(code, name);
				imagecatalog.put(name, images);
			}
		}
		user.put("menuMap", menuMap);
		user.put("valuecatalog", valuecatalog);//{网站首页=0, 设备管理=2, 信息维护=3, 日志管理=5, 系统管理=9}
		user.put("codecatalog", codecatalog);//{2=设备管理, 3=信息维护, 5=日志管理, 9=系统管理}
		user.put("imagecatalog", imagecatalog);//图片信息

		System.out.println(valuecatalog);
		System.out.println(codecatalog);
		System.out.println(imagecatalog);

		return user;
	}
	/**
	 * 单号生成
	 * @param d_type
	 * @return
	 */
	public static synchronized String findb_goods_dh(String d_type){
		Record re = Db.findFirst("select * from B_GOODS where D_TYPE = ? ",d_type);
		if(re==null)
			return "";
		else{
			String dhqz = DbUtil.readDbString(re.get("dhqz"));
			String dhscrq = DbUtil.readDbString(re.get("dhscrq"));
			int dhcd = DbUtil.readDbInt(re.get("dhcd"));
			int dhls = DbUtil.readDbInt(re.get("dhgs"));
			String nowdate = DateTime.now().toString("yyyy-MM-dd");
			if(dhscrq.equals(nowdate)){
				re.set("dhgs", dhls+1);
				Db.update("B_GOODS", re);
				return dhqz+"_"+DateTime.now().toString("yyMMdd")+"_"+formatNum(dhcd, dhls);
			}else{
				re.set("dhgs", 2);
				re.set("dhscrq", nowdate);
				Db.update("B_GOODS", re);
				return dhqz+"_"+DateTime.now().toString("yyMMdd")+"_"+formatNum(dhcd, 1);
			}
		}
	}
	public static String formatNum(int dhcd,int dhls){
		String stnum = String.valueOf(dhls);
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<dhcd-stnum.length();i++){
			sb.append("0");
		}
		return sb.toString()+stnum;
	}
	/**
	 * 生成条码
	 * @return
	 */
	public static String txm(){

		return "";
	}
	//测试
	public  static synchronized void  save(String user) {

		for(int i=0;i<5;i++){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(user+"|"+i);
		}
	}

	/**
	 * 判断仓库配件是否足够
	 * @param ckbh 所在仓库
	 * @param deviceid 设备ID
	 * @param num 需要的数量
	 * @return
	 */
	public static boolean checkdevice(String ckbh,int deviceid,int num){
		Record re = Db.findFirst("select sum(chnum) snum from c_produce_detail where DHZT = '003' and SZCK = ? and DEVICEID = ?",ckbh,deviceid);
		if(re==null)
			return false;
		int snum = DbUtil.readDbInt(re.get("snum"));
		if(snum>=num)
			return true;
		return false;
	}
	/**
	 * 模糊查询单号信息
	 * @param str
	 * @return
	 */
	public static String smartsearch_dh(String str){
		if("".equals(str))return "";
		//查询
		return "";
	}
	/**
	 * 模糊查询设备部件信息
	 * @param str
	 * @return
	 */
	public static String smartsearch_sb(String str,String sbzt){
		if("".equals(str))return "";
		String fields[] =  {"dname"};
		String yj = getSqlLikeAndCn(fields,str);
		List<Record> list = Db.find("select * from b_device where sbzt='"+sbzt+"' and "+yj);
		StringBuffer sb = new StringBuffer();
		for(Record record:list){
			String id = DbUtil.readDbString(record.get("id"));
			sb.append(id+"#"+DicPlugin.b_deviceall.get(id)+"||");
		}
		if(list==null||list.size()==0){
			String fieldsf[] =  {"jldw"};
			yj = getSqlLikeAndCn(fieldsf,str);
			list = Db.find("select * from b_device where sbzt='"+sbzt+"' and "+yj);
			for(Record record:list){
				String id = DbUtil.readDbString(record.get("id"));
				sb.append(id+"#"+DicPlugin.b_deviceall.get(id)+"||");
			}
		}
		if(list==null||list.size()==0){
			String fieldsf[] =  {"fname"};
			yj = getSqlLikeAndCn(fieldsf,str);
			list = Db.find("select * from v_factory_device where sbzt='"+sbzt+"' and "+yj);
			for(Record record:list){
				String id = DbUtil.readDbString(record.get("id"));
				sb.append(id+"#["+record.get("jldw","")+"]["+record.get("fname","")+"]["+record.get("dname","")+"]["+record.get("sblx","")+"]["+record.get("sbsm","")+"]||");
			}
		}
		if(list==null||list.size()==0){
			String fieldsf[] =  {"sblx"};
			yj = getSqlLikeAndCn(fieldsf,str);
			list = Db.find("select * from v_factory_device where sbzt='"+sbzt+"' and "+yj);
			for(Record record:list){
				String id = DbUtil.readDbString(record.get("id"));
				sb.append(id+"#["+record.get("jldw","")+"]["+record.get("fname","")+"]["+record.get("dname","")+"]["+record.get("sblx","")+"]["+record.get("sbsm","")+"]||");
			}
		}
		
		return sb.toString();
	}
	/**
	 * 模糊查询地区信息
	 * @param str
	 * @return
	 */
	public static String smartsearch_dq(String str){
		if("".equals(str))return "";
		String fields[] =  {"value"};
		String yj = getSqlLikeAndCn(fields,str);
		List<Record> list = Db.find("select * from ts_area where qybz='001' and "+yj);
		StringBuffer sb = new StringBuffer();
		for(Record record:list){
			String code = DbUtil.readDbString(record.get("code"));
			sb.append(code+"#"+DicPlugin.ts_area.get(code)+"||");
		}
		return sb.toString();
	}

	/**
	 * 拼出LIKE语句，实现模糊查询 
	 * 支持空格查询多个关键字（空格=或  ＆=且）
	 * 注：请用全角字符＆
	 * @param fields
	 * @param keyword
	 * @return
	 */
	public static String getSqlLikeAndCn(String fields[],String keyword){
		String likeStr="(";
		String[] s=keyword.split(" ");
		for (int i = 0; i < fields.length; i++) {
			for (int j = 0; j < s.length; j++) {
				if(!"".equals(s[j]))
				{
					if(likeStr.length()!=1)
						likeStr+=" OR (";
					else
						likeStr+=" (";
					//如果关键字还包含&符号，那就再切分
					if(s[j].indexOf("#")!=-1)
					{
						String[] _andS=s[j].split("#");
						String  _andResult="";
						for (int k = 0; k < _andS.length; k++) {
							if(!_andS[k].equals("")){
								if(_andResult.equals(""))
									_andResult=" "+fields[i].toUpperCase()+" LIKE '%" +_andS[k] + "%'  ";
								else
									_andResult+=" AND "+fields[i].toUpperCase()+" LIKE '%" +_andS[k] + "%'  ";
							}
						}
						likeStr+=" "+_andResult + " ";
					}
					else{
						if(s[j].length()==s[j].getBytes().length)
							likeStr+=" "+getCnStringSearchCondition(s[j],fields[i].toUpperCase());
						else
							likeStr+=" "+fields[i].toUpperCase()+" LIKE '%" + s[j] + "%'  ";

					}
					likeStr+=" ) ";
				}
			}
		}
		likeStr+=")";
		if(likeStr.equals("()"))
			return "";
		else
			return likeStr;
	}

	/**
	 * 中文字查询条件
	 * 
	 * @param fSearchString
	 * @param fFieldName
	 * @return
	 */
	public static String getCnStringSearchCondition(String fSearchString,String fFieldName) {
		int sLen = 0;
		String cConditionSql = "";
		String cTempCondition = "";
		String currenWord = "";
		cConditionSql = "(" + fFieldName + " LIKE '%" + fSearchString
		+ "%' OR ({%StringSearchCondition%}))";
		sLen = fSearchString.length();

		for (int i = 1; i <= sLen; i++) {
			currenWord = fSearchString.substring(i - 1, i);
			cTempCondition = cTempCondition
			+ getCnCharSearchCondition(currenWord, fFieldName, i);
			if (i < sLen)
				cTempCondition = cTempCondition + " and ";
		}
		return cConditionSql.replace("{%StringSearchCondition%}",
				cTempCondition);

	}

	/**
	 * 英文字母转中文SQL条件
	 * 
	 * @param cChar
	 * @param strFieldName
	 * @param cPos
	 * @return
	 * @throws SQLException 
	 */
	public static  String getCnCharSearchCondition(String cChar,String strFieldName, int cPos)  {
		String strBeginWord = "";
		String strEndWord = "";
		String strHeadChar = "";
		if ((cChar).length() >= 1)
			strHeadChar = (cChar.substring(0, 1)).toUpperCase();

		if (strHeadChar.equals("A")) {
			strBeginWord = "啊";
			strEndWord = "芭";
		}
		if (strHeadChar.equals("B")) {
			strBeginWord = "芭";
			strEndWord = "擦";
		}
		if (strHeadChar.equals("C")) {
			strBeginWord = "擦";
			strEndWord = "搭";
		}
		if (strHeadChar.equals("D")) {
			strBeginWord = "搭";
			strEndWord = "蛾";
		}
		if (strHeadChar.equals("E")) {
			strBeginWord = "蛾";
			strEndWord = "发";
		}
		if (strHeadChar.equals("F")) {
			strBeginWord = "发";
			strEndWord = "噶";
		}
		if (strHeadChar.equals("G")) {
			strBeginWord = "噶";
			strEndWord = "哈";
		}
		if (strHeadChar.equals("H")) {
			strBeginWord = "哈";
			strEndWord = "击";
		}
		if (strHeadChar.equals("I")) {
			strBeginWord = "击";
			strEndWord = "击";
		}
		if (strHeadChar.equals("J")) {
			strBeginWord = "击";
			strEndWord = "喀";
		}
		if (strHeadChar.equals("K")) {
			strBeginWord = "喀";
			strEndWord = "垃";
		}
		if (strHeadChar.equals("L")) {
			strBeginWord = "垃";
			strEndWord = "妈";
		}
		if (strHeadChar.equals("M")) {
			strBeginWord = "妈";
			strEndWord = "拿";
		}
		if (strHeadChar.equals("N")) {
			strBeginWord = "拿";
			strEndWord = "哦";
		}
		if (strHeadChar.equals("O")) {
			strBeginWord = "哦";
			strEndWord = "啪";
		}
		if (strHeadChar.equals("P")) {
			strBeginWord = "啪";
			strEndWord = "期";
		}
		if (strHeadChar.equals("Q")) {
			strBeginWord = "期";
			strEndWord = "然";
		}
		if (strHeadChar.equals("R")) {
			strBeginWord = "然";
			strEndWord = "撒";
		}
		if (strHeadChar.equals("S")) {
			strBeginWord = "撒";
			strEndWord = "塌";
		}
		if (strHeadChar.equals("T")) {
			strBeginWord = "塌";
			strEndWord = "挖";
		}
		if (strHeadChar.equals("U")) {
			strBeginWord = "挖";
			strEndWord = "挖";
		}
		if (strHeadChar.equals("V")) {
			strBeginWord = "挖";
			strEndWord = "挖";
		}
		if (strHeadChar.equals("W")) {
			strBeginWord = "挖";
			strEndWord = "昔";
		}
		if (strHeadChar.equals("X")) {
			strBeginWord = "昔";
			strEndWord = "压";
		}
		if (strHeadChar.equals("Y")) {
			strBeginWord = "压";
			strEndWord = "匝";
		}
		if (strHeadChar.equals("Z")) {
			strBeginWord = "匝";
			strEndWord = "卒";
		}
		return "(convert(substring(" + strFieldName + "," + cPos + ",1) using gbk)<'"+ strEndWord + "' AND convert(substring(" + strFieldName + "," + cPos+ ",1) using gbk)>='" + strBeginWord + "')";
	}


}
