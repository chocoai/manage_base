package cn.com.jandar.interceptor;


import cn.com.jandar.action.admin.AdminBaseController;
import cn.com.jandar.plugin.DicPlugin;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;


/**
 * 系统启用拦截器
 * @author 
 *
 */
public class StartXtInterceptor implements Interceptor{

	
	
	/**
	 * 系统启用拦截器
	 */
	public void intercept(ActionInvocation ai) {
//		AdminBaseController c = (AdminBaseController) ai.getController();
//		String viewPath = ai.getViewPath();
//		if(viewPath.indexOf("/manage/ruku/qcrk")==-1){
//			if("001".equals(DicPlugin.t_xtcs.get("XTQYBZ"))){
//				c.setAttr("msg", "系统还未启用，不能操作！");
//				c.setAttr("redirectionUrl", "/admin/main");
//				c.render("/admin/common/error.html");
//			}else{
//				ai.invoke();
//			}
//		}else{
//			if("002".equals(DicPlugin.t_xtcs.get("XTQYBZ"))){
//				c.setAttr("msg", "系统已启用，不能操作！");
//				c.setAttr("redirectionUrl", "/admin/main");
//				c.render("/admin/common/error.html");
//			}else{
//				ai.invoke();
//			}
//		}
		ai.invoke();
	}

}
