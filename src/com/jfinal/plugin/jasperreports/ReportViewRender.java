package com.jfinal.plugin.jasperreports;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.render.Render;

/**
 * @author cwledit
 *
 */
@SuppressWarnings("serial")
public class ReportViewRender extends Render {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public static final String XLS = "xls";
	public static final String PDF = "pdf";
	public static final String CSV = "csv";
	public static final String HTML = "html";
	public static final String XML = "xml";
	public static final String RTF = "rtf";



	public static final String ATTACHMENT = "attachment";
	public static final String REPORT_NAME = "reportName";
	public static final String FORMAT = "format";
	public static final String REPORT_PRINT = "reportPrint";
	public static String IMAGE_SERVLET_URL = "imageServletUrl";


	private static Map<String, IReportFileExporter> EXPORTER_MAP =
		new HashMap<String, IReportFileExporter>(4);

	static {
		EXPORTER_MAP.put(XLS, new ReportXlsExporter());
		EXPORTER_MAP.put(PDF, new ReportPdfExporter());
		EXPORTER_MAP.put(CSV, new ReportCsvExporter());
		EXPORTER_MAP.put(HTML, new ReportHtmlExporter());
		EXPORTER_MAP.put(XML, new ReportXmlExporter());
		EXPORTER_MAP.put(RTF, new ReportRtfExporter());
	}

	@SuppressWarnings("unchecked")
	public void render() {
		//获取数据
		Enumeration<String> attrs = request.getAttributeNames();
		Map model = new HashMap();
		while (attrs.hasMoreElements()) {
			String attrName = attrs.nextElement();
			model.put(attrName, request.getAttribute(attrName));
		}
		//填充报表文件.jasper,生成文件.jrPrint
		String reportName = (String) model.get(REPORT_NAME);//报表的文件名
		String format = ((String) model.get(FORMAT)).toLowerCase();//报表的格式pdf xls .....
		String attachment = ((String) model.get(ATTACHMENT)).toLowerCase();//报表的格式pdf xls .....
		ReportPrint reportPrint = (ReportPrint) model.get(REPORT_PRINT);//这就是之前生成的中间文件
		if(format.equals(HTML))
			response.setContentType("text/html;charset=utf-8");
		else if(format.equals(CSV))
			response.setContentType("text/plain;charset=utf-8");
		else if(format.equals(XLS))
			response.setContentType("application/vnd.ms-excel");
		else if(format.equals(PDF))
			response.setContentType("application/pdf");
		else if(format.equals(XML))
			response.setContentType("text/xml");
		else if(format.equals(RTF))
			response.setContentType("application/rtf");
		else{
			try {
				response.getOutputStream().close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		/* http头里的文件名貌似不支持utf-8，gbk之类的编码，需要转换一下
		 * 另外发现如果用new String(reportName.getBytes("UTF-8"), "iso-8859-1")的话Chrome和FF的
		 * 下载对话框的文件名是正常的，IE却是乱码，只能用GBK才正常
		 */
		try {
			if(attachment.toLowerCase().equals("true")){
				response.setHeader("Content-Disposition","attachment;filename=\"" +
						new String(reportName.getBytes("gb2312"),"ISO8859-1") + "\"");
			}
			else
				response.setHeader("Content-Disposition","inline;filename=\"" +
						new String(reportName.getBytes("gb2312"),"ISO8859-1") + "\"");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		exportFile(reportPrint, format, response,model);
	}

	@SuppressWarnings("unchecked")
	private void exportFile(ReportPrint reportPrint, String format, HttpServletResponse response,Map model) {
		try {
			_exportFile(reportPrint, format, response,model);
		} catch (JRException e) {
			logger.error("导出报表异常", e);
		} catch (IOException e) {
			logger.error(null, e);
		}
	}

	@SuppressWarnings("unchecked")
	private void _exportFile(ReportPrint reportPrint, String format, HttpServletResponse response,Map model) throws IOException, JRException {
		OutputStream buffOS = null;
		try {
			buffOS =response.getOutputStream();
			IReportFileExporter exporter = null;

			if (EXPORTER_MAP.containsKey(format)) {
				exporter = EXPORTER_MAP.get(format);//获取需要格式的导出类
				exporter.export(reportPrint, buffOS,model);
			} else {
				logger.error("错误的报表格式:" + format);
			}
		} finally {
			if (buffOS != null) {
				buffOS.close();
			}
		}
	}

}
