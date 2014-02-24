package cn.com.jandar.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.jandar.kit.DbUtil;

import com.jfinal.core.Controller;
import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.kit.StringKit;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;


/**
 * @author 
 *
 */
@TableBind(tableName="b_customer")
public class Customer extends Model<Customer> {
    private static final long serialVersionUID = -2063970373324095312L;
    public static final Customer dao = new Customer();
    public static final String LOGIN_USER = "loginUser"; //登入用户session中的key name
    
    /**
     * 获得客户列表
     * @return
     */
    public static List<Customer> getCustomerList() {
        List<Customer> customerList = Customer.dao.find("select * from b_customer order by id");
        return customerList;
    }
    /**
     * 根据ID编号查询客户
     */
    public static Customer getCustomerById(String id){
        return Customer.dao.findById(id);
    }
    /**
     * 批量删除用户
     */
    public static String delete(String[] ids) {
        for (String id : ids) {
            if (!Customer.dao.deleteById(id)) {
                return "error";
            }
        }
        return "success";
    } 
    
    /**
     * 分页查询用户列表
     */
    public static Page<Customer> getCustomerPage(int sPageNum, int sPageSize, String orderBy, String order, String search) {
    	 List<Object> param = new ArrayList<Object>();
         StringBuffer sqlBuffer = new StringBuffer("FROM b_customer  ");
         if (!StringKit.isBlank(search)) {
             sqlBuffer.append("where (b_customer.CNAME like ? or b_customer.LXR like ?)");
             param.add("%"+search+"%");
             param.add("%"+search+"%");
         }
         sqlBuffer.append(" order by ").append(orderBy).append(" ").append(order);
         Page<Customer> customerPage = (Page<Customer>) Customer.dao.paginate(sPageNum, sPageSize, "SELECT * ",
                                                                          sqlBuffer.toString(),param.toArray());
         return customerPage;
    }
    
    /**
     * 保存客户,操作员默认为操作者的姓名
     */
    public static String save(Customer customer,Controller c) {
    	User user = c.getSessionAttr(LOGIN_USER);
    	customer.set("OPDATE", new Timestamp(System.currentTimeMillis()));
    	customer.set("UPIPDATE", new Timestamp(System.currentTimeMillis()));
    	customer.set("OPERATOR",user.getStr("name"));
    	customer.set("YXBZ", "001");
    	customer.save();
        return "保存成功";
    }
    /**
     * 保存客户,更新操作员默认为操作者的姓名
     */
    public static String update(Customer customer,Controller c) {
    	User user = c.getSessionAttr(LOGIN_USER);
    	customer.set("UPIPDATE", new Timestamp(System.currentTimeMillis()));
    	customer.set("UPOPERATOR",user.getStr("name"));
    	customer.update();
        return "更新成功";
    }
    
    /**
     * @param areaCode
     * @return
     * 		根据客户区域查询出客户
     */
    public static Map<String,String> gerCustomerByArea(String areaCode){
    	  List<Customer> customerList = Customer.dao.find("select * from b_customer b where b.BAREA = ? order by id",areaCode);
    	  Map<String,String> customer = new LinkedHashMap<String,String> ();
    	  for(Customer record: customerList){
  			String id = DbUtil.readDbString(record.get("id"));
  			String fsname = DbUtil.readDbString(record.get("csname"));
  			String lxr = DbUtil.readDbString(record.get("lxr"));
  			String lxdh = DbUtil.readDbString(record.get("lxdh"));
  			String info = "["+fsname+"]["+lxr+"]["+lxdh+"]";
  			customer.put(id, info);
    	  }
    	  return customer;
    }
}
