package cn.com.jandar.action.admin;


import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import cn.com.jandar.interceptor.AdminLoginInterceptor;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.jasperreports.ReportPrint;
import com.jfinal.plugin.jasperreports.ReportViewRender;

@Before(AdminLoginInterceptor.class)
public class AdminBaseController extends Controller{
    
    protected final static int PAGESIZE = 10;
    protected String errMsg="errorMessages";
    public static String TRANFLAG = "tranflag";
    public static String TRANSUCCESS = "success";
    public static String TRANERROR = "ERROR";
    
    @Override
    public void render(String view) {
    	if(view.indexOf("error")!=-1) 
    		setAttr(TRANFLAG, TRANERROR);
    	else
    		setAttr(TRANFLAG, TRANSUCCESS);
        keepPara("orderBy","order");
        
        super.render(view);
    }
	/**
	 * @param report_jasperUrl
	 * @param report_param
	 * @param report_dataSourceList
	 * @param report_imageServletUrl
	 * @param report_fileName
	 * @param report_fileExt
	 */
	@SuppressWarnings("unchecked")
	protected void print(String report_jasperUrl, Map report_params, List report_dataSourceList, String report_imageServletUrl, String report_fileName, String report_fileExt) {

		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(report_jasperUrl.replace("\\", "/").replace("///", "/"), report_params, new JRBeanCollectionDataSource(report_dataSourceList));
			setAttr(ReportViewRender.IMAGE_SERVLET_URL, report_imageServletUrl);
			setAttr(ReportViewRender.REPORT_NAME, report_fileName+"."+report_fileExt.toLowerCase());
			setAttr(ReportViewRender.FORMAT, report_fileExt.toLowerCase());
			setAttr(ReportViewRender.REPORT_PRINT, new ReportPrint(jasperPrint));
			setAttr(ReportViewRender.ATTACHMENT, getPara("attachment",""));
		} catch (JRException e) {
			e.printStackTrace();
		}
		render(new ReportViewRender());
	}
}
