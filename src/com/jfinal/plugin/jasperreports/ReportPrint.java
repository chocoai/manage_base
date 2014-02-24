package com.jfinal.plugin.jasperreports;

import net.sf.jasperreports.engine.JasperPrint;

public class ReportPrint {
	JasperPrint jasperPrint = null;

	public JasperPrint getJasperPrint() {
		return jasperPrint;
	}

	public ReportPrint(JasperPrint jasperPrint) {
		super();
		this.jasperPrint = jasperPrint;
	}
}
