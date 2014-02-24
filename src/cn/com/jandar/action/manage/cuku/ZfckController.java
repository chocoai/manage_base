package cn.com.jandar.action.manage.cuku;

import cn.com.jandar.action.admin.AdminBaseController;
import cn.com.jandar.interceptor.StartXtInterceptor;

import com.jfinal.aop.Before;

import cty.kit.route.ControllerBind;

@Before(StartXtInterceptor.class)
@ControllerBind(controllerKey="/manage/cuku/zfck",resource="作废出库单")
public class ZfckController extends AdminBaseController{
	public void index(){
		renderText("作废出库单");
	}
}
