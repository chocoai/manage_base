package com.jfinal.plugin.jasperreports;

import java.io.OutputStream;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;

public class ReportHtmlExporter implements IReportFileExporter{
	@SuppressWarnings("unchecked")
	public void export(ReportPrint reportPrint, OutputStream os,Map model) throws JRException {
		        JRHtmlExporter exporter = new JRHtmlExporter();
		        exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, "images/");
		        if(model!=null && model.get(ReportViewRender.IMAGE_SERVLET_URL)!=null)
		        	exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, model.get(ReportViewRender.IMAGE_SERVLET_URL));
		        exporter.setParameter(JRExporterParameter.JASPER_PRINT, reportPrint.getJasperPrint());
		        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
		        exporter.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE); 
		        //exporter.setParameter(JRHtmlExporterParameter.ZOOM_RATIO, 1.5f);//解决生成HTML报表缩方比例问题
		        exporter.setParameter(JRHtmlExporterParameter.SIZE_UNIT, "pt");//解决生成HTML报表缩小问题
		        exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "<script src=\"/kiosk_yk/evolution2/js/replace.js\"></script><script src=\"/kiosk_yk/evolution2/js/jquery-1.7.1.min.js\"></script>");
		        exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, " <script>show_replace();</script>");
		        exporter.setParameter( JRHtmlExporterParameter.ZOOM_RATIO, 1.4f); 
		        exporter.exportReport();
		        
		    }

}
