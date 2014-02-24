package com.jfinal.plugin.jasperreports;

import java.io.OutputStream;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;

public interface IReportFileExporter {
	@SuppressWarnings("unchecked")
	public void export(ReportPrint reportPrint, OutputStream os,Map model) throws JRException;
}
