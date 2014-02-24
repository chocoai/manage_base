package cn.com.jandar.model;

import java.util.List;

import com.google.common.collect.Lists;
import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.kit.StringKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * 
 * @author 
 *
 */
@TableBind(tableName="ts_code")
public class Code extends Model<Code>{

	private static final long serialVersionUID = 1700019844387414062L;
	public static final Code dao = new Code();
	
	/**
	 * 获得字典列表
	 * @return
	 */
	public static List<Record> getCodeList(String search){
		StringBuffer sqlBuffer = new StringBuffer("select * from ts_code ");
		if (!StringKit.isBlank(search)){
			sqlBuffer.append("where ts_code.TYPENAME like '%"+search+"%' ");
			}
		sqlBuffer.append("group by ts_code.TYPE order by ts_code.TYPE");
		List<Record> codelist = Db.find(sqlBuffer.toString());	
		return codelist;
	}
	/**
	 * 通过id获得字典
	 * @return
	 */
	public static Code getCodeById(String id){
		return Code.dao.findById(id);	
	}
	
	public static List<Code> getCodeByType(String type){
		List<Code> codes = Code.dao.find("select * from ts_code where TYPE =?", type);
//		if(codes.size()==1&&codes.get(0).getStr("CODE")==null){
//			return null;
//		}
		return codes;
	}
	
	public static List<Code> getCodeByIds(String ids){
		List<Code> codeList = Code.dao.find("select * from ts_code where id in ("+ids+")");
		return codeList;
	}
	
    public static String delete(String[] ids) {
        for (String id : ids) {
            if (!Code.dao.deleteById(id)) {
                return "error";
            }
        }
        return "success";
    }
    
    public static Page<Code> getCodePage(int sPageNum, int sPageSize, String orderBy, String order, String search) {
        StringBuffer sqlBuffer = new StringBuffer("FROM ts_code ");
        if (!StringKit.isBlank(search)) {
            sqlBuffer.append("where ts_code.CODE like '%" + search + "%' ");
        }
        sqlBuffer.append(" order by ").append(orderBy).append(" ").append(order);
        Page<Code> codePage = (Page<Code>) Code.dao.paginate(sPageNum, sPageSize, "SELECT * ",
                                                                         sqlBuffer.toString());
        return codePage;
    }
    
    public static String save(Code code){
    	code.save();
    	return "保存成功";
    }
    
    public static String update(Code code){
    	code.update();
    	return "更新成功";
    }
    public static String isExitByCode(String code) {
        Code code1 = Code.dao.findFirst("select * from ts_code where ts_code.CODE = ?",code);
        if(code1 == null){
            return "true";
        }
        return "false";
    }
    
    
    public static boolean addtype(String type,String typename){
    	boolean boo=true;
    	List<Code> code = Lists.newArrayList();	
    	code=Code.dao.find("select * from ts_code where ts_code.TYPE=?", type);
    	if(code.size()>0){
    		boo = false;
    	}else{
    		boo = new Code().set("TYPE", type).set("TYPENAME", typename).save();
    	}
    	return boo;
    }
    
    /**
     * 保存字典
     * 要求：相同(type)类别中，编码(code)应不同
     * @param code
     * @return boolean
     */
    public static boolean savezidian(Code code){
    	boolean boo = true;
    	String type = code.getStr("TYPE");
    	String cdoe = code.getStr("CODE");
    	List<Code> codes = Lists.newArrayList();
    	codes = Code.dao.find("select * from ts_code where TYPE =?", type);
    	if(codes.get(0).getStr("CODE")==null&&codes.size()==1){
    		codes.get(0).set("CODE", code.get("CODE"));
			codes.get(0).set("VALUE", code.get("VALUE"));
			codes.get(0).set("SORT", code.get("SORT"));
			boo=codes.get(0).update();
			return boo;
    	}
    	
    	for(int i=0;i<codes.size();i++){
    		if(codes.get(i).getStr("CODE")!=null&&codes.get(i).getStr("CODE").equals(cdoe)){
    			boo= false;
    			break;
    			}
    		}
    	if(boo)Code.save(code);
    	
    	return boo;
    }
    
    public static boolean updatezidian(Code code){
    	boolean boo = true;
    	String type = code.getStr("TYPE");
    	String cdoe = code.getStr("CODE");
    	List<Code> codes = Lists.newArrayList();
    	codes = Code.dao.find("select * from ts_code where TYPE =?", type);
    	for(int i=0;i<codes.size();i++){
    		if(codes.get(i).getStr("CODE")!=null&&codes.get(i).getStr("CODE")==cdoe){
    			boo= false;
    			break;
    			}
    	}
    	
    	if(boo)Code.update(code);
    	
    	return boo;
    }
	
}
