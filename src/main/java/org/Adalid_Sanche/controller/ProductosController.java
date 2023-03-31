package org.Adalid_Sanche.controller;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.Adalid_Sanche.entity.Producto;
import org.Adalid_Sanche.service.IntCategoriasService;
import org.Adalid_Sanche.service.IntProductosService;
import org.Adalid_Sanche.util.Utileria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/productos")
public class ProductosController {
	
	@Autowired
	private IntCategoriasService serviceCategorias;
	
	@Autowired
	private IntProductosService serviceProductos;
	
	@PostMapping("/guardar")
	public String guardar(Producto producto, BindingResult result, @RequestParam("archivoImagen") MultipartFile multiPart, RedirectAttributes model) {
		System.out.println(producto);
		if(result.hasErrors()) {
			for(ObjectError error: result.getAllErrors()) {
				System.out.println("Ocurrio un error: " + error.getDefaultMessage());
		     }
			return "/productos/formProducto";
		 } 
		if(!multiPart.isEmpty()) {
			String ruta = "c:/productos/img-productos/"; //Windows
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if(nombreImagen != null) {
				producto.setImagen(nombreImagen);
		     }	
		 }
		
		if(producto.getId()== null) model.addFlashAttribute("msg", "Producto agregado");
		else  model.addFlashAttribute("msg", "Producto modificado");
		
			serviceProductos.guardar(producto);
			return "redirect:/productos/indexPaginate";
		 }
	
	@GetMapping ("/nueva")
	public String nueva(Producto producto, Model model) {
		model.addAttribute("categorias", serviceCategorias.obtenerCategorias());
		return "productos/formProducto"; 
	}
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		model.addAttribute("productos", serviceProductos.obtenerProductos());
		return "productos/listaProductos";
	} 
	
	@GetMapping(value = "/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
	Page<Producto>lista = serviceProductos.BuscarTodas(page);
	model.addAttribute("productos", lista);
		return "productos/listaProductos";
	}
	
	@GetMapping("/eliminar")
	public String eliminar(@RequestParam("id") int idProducto, RedirectAttributes model) {
  		model.addFlashAttribute("msg", "Producto eliminado");
		serviceProductos.eliminar(idProducto);
		return "redirect:/productos/indexPaginate";
	}
	
	@GetMapping("/consultar")
	public String buscar(@RequestParam("id") int idProducto, Model model) {
		model.addAttribute("producto", serviceProductos.buscarPorId(idProducto));
		model.addAttribute("categorias", serviceCategorias.obtenerCategorias());
		return "productos/formProducto";
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
			
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			}
			
			@Override
			public String getAsText() throws IllegalArgumentException {
				return DateTimeFormatter.ofPattern("dd-MM-yyyy").format((LocalDate)getValue()); 
			}					
		});
	}
}




