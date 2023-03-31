package org.Adalid_Sanche.service;

import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;
import org.Adalid_Sanche.entity.Producto;
import org.Adalid_Sanche.repository.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductosServiceImp implements IntProductosService { 
	
	@Autowired
	private ProductosRepository repoProductos;
	
	private List<Producto> productos;
	
	public ProductosServiceImp() {
		productos = new LinkedList<Producto>();
				
		try {
			Producto v1 = new Producto();
			v1.setId(1);
			v1.setNombre("Programador");
			v1.setDescripcion("Programación de aplicaciones moviles");
			v1.setPrecio(250.0);
			v1.setEstatus("Agotada");
			
			Producto v2 = new Producto();
			v2.setId(2);
			v2.setNombre("Contador Público");
			v2.setDescripcion("Finanzas y Auditoría");
			v2.setPrecio(200.0);
			v2.setEstatus("Disponible");
			
			productos.add(v1);
			productos.add(v2);
		
	    }catch(DateTimeParseException ex) {
		   System.out.println(ex.getMessage());
	      }
	  }
		
	@Override
	public void guardar(Producto producto) {
		productos.add(producto);
	}
	
	@Override
	public List<Producto> obtenerProductos(){
		return productos;
	}
	
	@Override
	public Producto buscarPorId(int idProducto) {
		Producto producto = null;
		for(Producto v: productos) {
			if(v.getId()==idProducto) {
				producto=v; 	
			}
		}
		return producto;
	}
	
	@Override
	public void eliminar(int idProducto) {
		productos.remove(buscarPorId(idProducto));
	}
	
	@Override
	public Page<Producto> BuscarTodas(Pageable page) {
		return repoProductos.findAll(page);
	}
	
}
