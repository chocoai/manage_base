package cn.com.jandar.interceptor;


import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;


/**
 * 盘存期，不能操作系统出入库
 * @author 
 *
 */
public class PancunInterceptor implements Interceptor{

	
	
	/**
	 * 盘存期模块控制   如果为盘存期，则 出入库模块停用
	 */
	public void intercept(ActionInvocation ai) {
		ai.invoke();
	}

}
