package cn.com.jandar.action.manage.demo;

import java.util.List;

import cn.com.jandar.action.admin.AdminBaseController;
import cn.com.jandar.kit.Constant;
import cn.com.jandar.kit.DbUtil;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import cty.kit.route.ButtonBind;
import cty.kit.route.ControllerBind;


@ControllerBind(controllerKey="/manage/demo/ssuo",resource="测试搜索")
public class DemoController extends AdminBaseController{
	@ButtonBind(buttonname="智能搜索1")
	public void index(){
		System.out.println(Constant.findb_goods_dh("000"));
		System.out.println(Constant.checkdevice("000", 12, 3));
		render("demobaid.html");
	}
	@ButtonBind(buttonname="智能搜索1")
	public void ajax_baidu(){
		System.out.println("ajax_baidu");
		String cs = DbUtil.readDbString(getPara("cs"));
		if("".equals(cs)){
			renderJson("aaa","");
			return;
		}
		String fields[] =  {"name"};
		String yj = Constant.getSqlLikeAndCn(fields,cs);
		List<Record> list = Db.find("select * from ts_menu where "+yj);
			StringBuffer sb = new StringBuffer();
			for(int i =0;i<list.size();i++){
				Record r = list.get(i);
				sb.append(r.get("name")+"<br>");
			}
		renderJson("aaa",sb.toString());
	}
	@ButtonBind(buttonname="智能搜索2")
	public void tots(){
		render("ts.html");
	}
	/**
	 * 
	模糊查询厂商类型设备
	select b.id,
	CONCAT(a.FNAME,'|',(select c.VALUE from ts_code c where c.TYPE = 'SBLX' and c.code = b.sblx),'|',b.DNAME,'|',b.SBXH,'|',b.SBSM)  ms
	from b_factory a,b_device b where a.ID = b.FACTORYID and a.YXBZ = '001'  and b.SBZT != '003'
	模糊查询单号
	 */
	@ButtonBind(buttonname="智能搜索2")
	public void ajax_ts(){
		System.out.println("ajax_ts");
		String cs = DbUtil.readDbString(getPara("cs"));
		if("".equals(cs)){
			renderJson("aaa","");
			return;
		}
		String fields[] =  {"name"};
		String yj = Constant.getSqlLikeAndCn(fields,cs);
		List<Record> list = Db.find("select * from ts_menu where "+yj);
			StringBuffer sb = new StringBuffer();
			for(int i =0;i<list.size();i++){
				Record r = list.get(i);
				sb.append(r.get("name")+"|");
			}
		renderJson("aaa",sb.toString());
	}
	@ButtonBind(buttonname="智能搜索3")
	public void todemo_znss(){
		render("demo_znss.html");
	}
	@ButtonBind(buttonname="智能搜索3")
	public void ajax_dq(){
		System.out.println("ajax_dq");
		renderJson("info",Constant.smartsearch_dq(DbUtil.readDbString(getPara("cs"))));
	}
	@ButtonBind(buttonname="智能搜索3")
	public void ajax_sb(){
		System.out.println("ajax_sb");
		renderJson("info",Constant.smartsearch_sb(DbUtil.readDbString(getPara("cs")), "001"));
	}
	@ButtonBind(buttonname="智能搜索3")
	public void ajax_dh(){
		System.out.println("ajax_dh");
		renderJson("info",Constant.smartsearch_dh(DbUtil.readDbString(getPara("cs"))));
	}
}
