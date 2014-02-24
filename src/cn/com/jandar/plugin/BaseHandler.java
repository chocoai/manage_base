package cn.com.jandar.plugin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;

import freemarker.ext.beans.BeansWrapper;

public class BaseHandler extends Handler {
	@Override
	public void handle(String target, HttpServletRequest request,
			HttpServletResponse response, boolean[] isHandled) {
		request.setAttribute("base", request.getContextPath());
		request.setAttribute("statics", BeansWrapper.getDefaultInstance().getStaticModels());
		nextHandler.handle(target, request, response, isHandled);
	}

}
