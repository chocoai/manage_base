package cn.com.jandar.action.manage.bxgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.OrderedMap;
import org.apache.commons.collections.map.LinkedMap;

import cn.com.jandar.action.admin.AdminBaseController;
import cn.com.jandar.interceptor.StartXtInterceptor;
import cn.com.jandar.model.Bxd;
import cn.com.jandar.plugin.DicPlugin;

import com.jfinal.aop.Before;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Page;

import cty.kit.route.ButtonBind;
import cty.kit.route.ControllerBind;

@Before(StartXtInterceptor.class)
@ControllerBind(controllerKey="/manage/bxgl/bxdhf",resource="报修单回复")
public class BxdhfController extends AdminBaseController{
	
	@ButtonBind(buttonname="查询")
	public void index(){
		Page<Bxd> page =  Bxd.getBxdPage(getParaToInt("pageNumber",1),getParaToInt("pageSize",PAGESIZE),getPara("orderBy","s_bxd.ID"),getPara("order","desc"),getPara("filter_LIKES_name"));
		keepPara("filter_LIKES_name");
		setAttr("page", page);
		render("bxdhf_list.html");
	}
	
	@ButtonBind(buttonname="更新")
	public void edit(){
		Bxd bxd = Bxd.getBxdById(getPara("id"));
		setAttr("bxd", bxd);
		render("bxdhf_input.html");
	}
	
	
	@ButtonBind(buttonname="回复")
	public void relpy(){
		Bxd bxd = getModel(Bxd.class);
		setAttr("msg", Bxd.reply(bxd,this));
		setAttr("redirectionUrl", "/manage/bxgl/bxdhf");
		render("/admin/common/success.html");
	}
	
	@SuppressWarnings("unchecked")
	@ButtonBind(buttonname="打印")
	public void printrepair(){
		
		String id=getPara("id");
		Bxd bxd = Bxd.getBxdById(id);
		
		Map report_param = new HashMap();
		report_param.put("OPDATE", bxd.get("OPDATE").toString());  //报修日期
		report_param.put("DH", bxd.getStr("DH"));     //报修编号(单号)
		report_param.put("SBLX", DicPlugin.jkzidian.get("SBLX").get(bxd.getStr("SBLX")));		//故障配件类型
    	
    	//报表列表数据源
		List report_dataSourceList=new ArrayList();
		OrderedMap row = new LinkedMap();
		row.put("GZSM", bxd.getStr("GZSM"));			//故障现象说明
		row.put("BZ",bxd.getStr("BZ"));		//备注
		row.put("CSNAME",  DicPlugin.b_customerall.get(bxd.getInt("CUSTOMERID")+""));		//客户名称，使用客户id获得
		report_dataSourceList.add(row);	
		
		//报表信息
		String report_jasperUrl=PathKit.getWebRootPath()+"/report/repairreport/repairreport.jasper";
		String report_imageServletUrl=PathKit.getWebRootPath()+"/report/images/";
		String report_fileName="报修单";
		String report_fileExt= getPara("reportFormat", "pdf");
		print(report_jasperUrl, report_param, report_dataSourceList, report_imageServletUrl, report_fileName, report_fileExt);
	}
	
	
}
