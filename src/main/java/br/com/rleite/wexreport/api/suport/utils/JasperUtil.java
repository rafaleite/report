package br.com.rleite.wexreport.api.suport.utils;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Classe responsável por gerar relatórios Jasper e retornar o seu array de bytes.
 *
 * @param <T>
 */
public class JasperUtil<T>  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Value("${wexreport.path.jrxml}")
	private String pathJrxml;
	
	/**
	 * Gera um relatório Jasper em PDF e retorna seu array de bytes.
	 * 
	 * @param pColecao
	 * @param pParametros
	 * @param pNomeRelatorio
	 * @return
	 * @throws JRException
	 */
	public byte[] gerarByteJasper(List<T> pColecao, ConcurrentMap<String, Object> pParametros, String pNomeRelatorio) throws JRException {
		
		StringBuilder pathReports = new StringBuilder(pathJrxml).append(pNomeRelatorio).append(".jrxml");
		
		log.info("criando Jasper DataSource...");
		JRDataSource source = new JRBeanCollectionDataSource(pColecao);

		log.info("compilando relatório {}...", pNomeRelatorio);
		JasperReport report = JasperCompileManager.compileReport(pathReports.toString());

		log.info("preenchendo relatório {}...", pNomeRelatorio);
		JasperPrint print = JasperFillManager.fillReport(report, pParametros, source);

		return JasperExportManager.exportReportToPdf(print);
	}

}
