package com.jfinal.plugin.jasperreports;

import java.io.OutputStream;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRXmlExporter;

public class ReportXmlExporter implements IReportFileExporter {

	@SuppressWarnings("unchecked")
	public void export(ReportPrint reportPrint, OutputStream os, Map model)
			throws JRException {
			JRXmlExporter exporter = new JRXmlExporter();
	        exporter.setParameter(JRExporterParameter.JASPER_PRINT, reportPrint.getJasperPrint());
	        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
	        exporter.exportReport();

	}

}
