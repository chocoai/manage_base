package cn.com.jandar.action.admin;

import cn.com.jandar.kit.Constant;
import cn.com.jandar.kit.DbUtil;

import com.jfinal.aop.ClearInterceptor;
import com.jfinal.aop.ClearLayer;

import cty.kit.route.ControllerBind;


@ControllerBind(controllerKey="/admin/ssuo",resource="ssuo")
public class SsuoController extends AdminBaseController{
	/**
	 * 地区搜索
	 */
	@ClearInterceptor(ClearLayer.ALL)
	public void ajax_dq(){
		System.out.println("ajax_dq");
		renderJson("info",Constant.smartsearch_dq(DbUtil.readDbString(getPara("cs"))));
	}
	/**
	 * 配件搜索
	 */
	@ClearInterceptor(ClearLayer.ALL)
	public void ajax_sb(){
		System.out.println("ajax_sb");
		renderJson("info",Constant.smartsearch_sb(DbUtil.readDbString(getPara("cs")), "001"));
	}
	/**
	 * 订单搜索
	 */
	@ClearInterceptor(ClearLayer.ALL)
	public void ajax_dh(){
		System.out.println("ajax_dh");
		renderJson("info",Constant.smartsearch_dh(DbUtil.readDbString(getPara("cs"))));
	}
}
