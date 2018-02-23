package br.com.rleite.wexreport.api.cliweb.devedor;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cliweb/devedor")
public class AcordoDevedorController {
	
	@Autowired
	private AcordoDevedorService service;
	
	 @RequestMapping(value = "/acordo", method = RequestMethod.POST,
	            produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> gerarAcordo(@RequestBody List<AcordoDevedorFields> entity, HttpServletResponse response) {
		 
		 ByteArrayInputStream bis = new ByteArrayInputStream(this.service.gerarPdfAcordo());

	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

	        return ResponseEntity
	                .ok()
	                .headers(headers)
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(new InputStreamResource(bis));
		 
	}
	
}
