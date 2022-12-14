package cl.infoclub.fsj.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.infoclub.fsj.entidad.Noticia;



@Controller
public class HomeController {
	private final static Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping("/")
	public String Main (Model modelo) {
		String nombre = "src/main/resources/static/noticias.txt";
		ArrayList<Noticia> listaNombres = new ArrayList<Noticia>();
		
		try {
			FileReader fr = new FileReader(nombre);
			BufferedReader br = new BufferedReader(fr);
			String data = br.readLine();
			
			while (data != null) {

				String[] datos = data.split("@@");
				Noticia n = new Noticia();
				
				n.setTitulo(datos[0]);
				n.setNoticia(datos[1]);
				n.setImagen(datos[2]);
				
				logger.info("Titulo: "+n.getTitulo());
				logger.info("Noticia: "+n.getNoticia());
				logger.info("Imagen: "+n.getImagen());
				
				listaNombres.add(n);
				data = br.readLine();
			}
			
			br.close();
			fr.close();
		}catch (Exception e) {
			logger.error("No se puede leer el fichero"+nombre+" : "+e);
		}
		
		for(int n=0; n<listaNombres.size();n++) {
		modelo.addAttribute("noticia"+n+"_titulo",listaNombres.get(n).getTitulo());
		modelo.addAttribute("noticia"+n+"_noticia",listaNombres.get(n).getNoticia());
		modelo.addAttribute("noticia"+n+"_imagen",listaNombres.get(n).getImagen());
		
		}
		return "main";
	}
	
	
}