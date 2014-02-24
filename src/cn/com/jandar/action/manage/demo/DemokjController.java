package cn.com.jandar.action.manage.demo;

import cn.com.jandar.action.admin.AdminBaseController;
import cn.com.jandar.kit.Constant;

import com.jfinal.plugin.activerecord.Record;

import cty.kit.route.ControllerBind;


@ControllerBind(controllerKey="/manage/demo/kj",resource="测试控件")
public class DemokjController extends AdminBaseController{
	public static int a = 1;
	public void index(){
//		Constant.save(a+++"");
		
		Record re = new Record();
		
		
		render("fl.html");
	}
	
	public void mt1(){
		render("motai1.html");
	}
	public void mt2(){
		setAttr("a001", "");
		render("motai2.html");
	}
	public void mt3(){
		render("motai3.html");
	}
}
