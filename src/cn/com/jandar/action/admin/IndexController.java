package cn.com.jandar.action.admin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.com.jandar.kit.DbUtil;
import cn.com.jandar.model.User;

import com.jfinal.plugin.activerecord.Record;

import cty.kit.route.ControllerBind;

@ControllerBind(controllerKey="/admin", resource="后台首页")
public class IndexController extends AdminBaseController{

	public void index(){
		render("admin_index.html");
	} 

	public void header(){
		render("admin_header.html");
	} 

	public void middle(){
		render("admin_middle.html");
	} 
	public void main_1(){
		render("admin_main_1.html");
	}
	public void main(){
		/**
		 * 设置用户信息显示
		 */
		User user = getSessionAttr(User.LOGIN_USER);
		/**
		 * 设置快捷访问以及权限
		 */
		setAttr("kjfs1", "/hospital/monitor/sbxx");//终端监控
		setAttr("kjfs2", "/hospital/sjtj/ansjtj");//业务量统计
		setAttr("kjfs3", "/hospital/sjtj/tbtj");//图表统计
		setAttr("kjfs4", "/hospital/log/ywlog");//业务日志
		setAttr("kjfs5", "/hospital/log/jylog");//交易日志
		setAttr("kjfs6", "/hospital/xxwh/notice");//通知发布
		/**
		 * 设置当日医院业务量分布
		 */
		setAttr("ywfb", "[]");

		/**
		 * 设置当日业务量
		 */
		setAttr("sb_drywlHtml", "<font color=\"red\">没有信息！</font>");
		setAttr("conut_drywl", 0);
		/**
		 * 设置设备分布
		 */
		setAttr("sbxxHtml", "<font color=\"red\">没有信息！</font>");
		render("admin_main.html");
	}
	public void menu(){
		String firstfunction = "";
		Map<String,List<Record>> map = new LinkedHashMap<String,List<Record>>();//存放显示的菜单信息
		//获取业务号
		String menucode = DbUtil.readDbString(getPara("menucode"));
		if("".equals(menucode)||"0".equals(menucode)){//首页
			List<Record> list = new ArrayList<Record>();
			Record re = new Record();
			re.set("URL", "/admin/main");
			re.set("CODE", "1");
			re.set("PARENT_ID", "0");
			re.set("NAME", "首页");
			list.add(re);
			map.put("首页", list);
		}else{//其他功能
			User user = getSessionAttr(User.LOGIN_USER);
			Map<String,List<Record>> menuMap = user.get("menuMap");
			Map<String,String> codecatalog = user.get("codecatalog");
			String name = codecatalog.get(menucode);
			List<Record> list  = menuMap.get(menucode);
			map.put(name, list);
		}
		setAttr("menucode", map);
		System.out.println(map);
		//设置按钮点击跳转
		Set<String> key = map.keySet();
		for (Iterator it = key.iterator(); it.hasNext();) {
			String s = (String) it.next();
			List<Record> lire = map.get(s);
			for(int i=0;i<lire.size();i++){
				Record record = lire.get(i);
				firstfunction = DbUtil.readDbString(record.getStr("url"));
				break;
			}
		}
		if("".equals(firstfunction))firstfunction = "/admin/main";
		setAttr("firstfunction", firstfunction);
		render("admin_menu.html");
	}
}
