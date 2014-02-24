package cn.com.jandar.action.manage.base;

import cn.com.jandar.action.admin.AdminBaseController;
import cn.com.jandar.model.Cjgz;
import cn.com.jandar.model.User;

import com.jfinal.plugin.activerecord.Page;

import cty.kit.route.ButtonBind;
import cty.kit.route.ControllerBind;
/**
 * 
 * @author wy
 *
 */

@ControllerBind(controllerKey="/manage/base/cjgz",resource="常见故障维护")
public class CjgzController extends AdminBaseController{
	
	@ButtonBind(buttonname="查询")
	public void index(){
		
		Page<Cjgz> page =  Cjgz.getCjgzPage(getParaToInt("pageNumber",1),getParaToInt("pageSize",PAGESIZE),getPara("orderBy","b_cjgz.ID"),getPara("order","desc"),getPara("filter_LIKES_sblx"));
		keepPara("filter_LIKES_sblx");
		setAttr("page", page);
		render("cjgz_list.html");
	}
	
	@ButtonBind(buttonname="新增")
	public void add(){
		render("cjgz_input.html");
	}
	@ButtonBind(buttonname="新增")
	public void save(){
		Cjgz cjgz = getModel(Cjgz.class);
		cjgz.set("OPERATOR",((User)getSessionAttr(User.LOGIN_USER)).get("username"));
		setAttr("msg", cjgz.save(cjgz));
		setAttr("redirectionUrl", "/manage/base/cjgz");
		render("/admin/common/success.html");
	}

	@ButtonBind(buttonname="更新")
	public void edit(){
		Cjgz cjgz = Cjgz.getCjgzById(getPara("ID"));
		setAttr("cjgz", cjgz);
		render("cjgz_input.html");
	}
	
	@ButtonBind(buttonname="更新")
	public void update(){
		Cjgz cjgz = getModel(Cjgz.class);
		cjgz.set("UPOPERATOR",((User)getSessionAttr(User.LOGIN_USER)).get("username"));
		setAttr("msg", Cjgz.update(cjgz));
		setAttr("redirectionUrl", "/manage/base/cjgz");
		render("/admin/common/success.html");
	}
	
	@ButtonBind(buttonname="查看")
	public void seek(){
		Cjgz cjgz = Cjgz.getCjgzById(getPara("ID"));
		setAttr("cjgz", cjgz);
		render("cjgz_seek.html");
	}
}
