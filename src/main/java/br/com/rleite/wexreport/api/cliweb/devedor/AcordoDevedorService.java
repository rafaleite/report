package br.com.rleite.wexreport.api.cliweb.devedor;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AcordoDevedorService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Value("${wexreport.path.jrxml}")
	private String pathJrxml;
	
	public byte[] gerarPdfAcordo() {
		System.out.println(pathJrxml);
		Path path = Paths.get(pathJrxml+"/sample.pdf");
		try {
			return Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Map<String, Object> getParametrosAcordo() {
		return null;
	}
	
	

}
