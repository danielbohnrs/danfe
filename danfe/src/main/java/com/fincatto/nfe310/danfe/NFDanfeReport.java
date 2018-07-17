package com.fincatto.nfe310.danfe;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRXmlDataSource;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class NFDanfeReport {

	private final String xml;

	public NFDanfeReport(String xml) {
		this.xml = xml;
	}

	public byte[] gerarDanfeNFe(byte[] logoEmpresa) throws Exception {
		return toPDF(createJasperPrintNFe(logoEmpresa));
	}

	private static byte[] toPDF(JasperPrint print) throws JRException {
		return JasperExportManager.exportReportToPdf(print);
	}

	public JasperPrint createJasperPrintNFe(byte[] logoEmpresa) throws IOException, ParserConfigurationException, SAXException, JRException {
		try (InputStream in = NFDanfeReport.class.getClassLoader().getResourceAsStream("danfe/DANFE_NFE_RETRATO.jasper"); InputStream subDuplicatas = NFDanfeReport.class.getClassLoader().getResourceAsStream("danfe/DANFE_NFE_DUPLICATAS.jasper")) {
			final JRPropertiesUtil jrPropertiesUtil = JRPropertiesUtil.getInstance(DefaultJasperReportsContext.getInstance());
			jrPropertiesUtil.setProperty("net.sf.jasperreports.xpath.executer.factory", "net.sf.jasperreports.engine.util.xml.JaxenXPathExecuterFactory");

			Map<String, Object> parameters = new HashMap<>();
			parameters.put("SUBREPORT_DUPLICATAS", subDuplicatas);
			parameters.put("LOGO_EMPRESA", (logoEmpresa == null ? null : new ByteArrayInputStream(logoEmpresa)));

			return JasperFillManager.fillReport(in, parameters, new JRXmlDataSource(convertStringXMl2DOM(), "/nfeProc/NFe/infNFe/det"));
		}
	}

	private Document convertStringXMl2DOM() throws ParserConfigurationException, IOException, SAXException {
		try (StringReader stringReader = new StringReader(this.xml)) {
			InputSource inputSource = new InputSource();
			inputSource.setCharacterStream(stringReader);
			return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputSource);
		}
	}

}
