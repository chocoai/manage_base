package cn.com.jandar.action.admin;

import java.util.List;

import cn.com.jandar.kit.DbUtil;
import cn.com.jandar.plugin.DicPlugin;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import cty.kit.route.ButtonBind;
import cty.kit.route.ControllerBind;


@ControllerBind(controllerKey="/admin/startxt",resource="系统启用")
public class StartxtController extends AdminBaseController{
	@ButtonBind(buttonname="系统启用")
	public void index(){
		List<Record> list = Db.find("select * from t_xtcs ");
		setAttr("xtcs", list);
		render("startxt.html");
	}
	@ButtonBind(buttonname="更新")
	public void edit(){
		String id = getPara("id");
		Record re = Db.findById("t_xtcs", id);
		setAttr("xt", re);
		render("startxt_input.html");
	}
	@ButtonBind(buttonname="更新")
	public void update(){
		String id = getPara("id");
		String value = getPara("value");
		Record re = Db.findById("t_xtcs", id);
		re.set("value", value);
		if(Db.update("t_xtcs", re)){
			setAttr("msg", "更新成功！");
			DicPlugin.loadt_xtcsDb();
		}
		setAttr("redirectionUrl", "/admin/startxt");
		render("../common/success.html");
	}
	@ButtonBind(buttonname="启用")
	public void qy(){
		String id = getPara("id");
		Record re = Db.findById("t_xtcs", id);
		re.set("value", "002");
		if(Db.update("t_xtcs", re)){
			setAttr("msg", "启用成功！");
			DicPlugin.loadt_xtcsDb();
		}
		setAttr("redirectionUrl", "/admin/startxt");
		render("../common/success.html");
	}
}
