package org.Adalid_Sanche.service;

import java.util.LinkedList;
import java.util.List;
import org.Adalid_Sanche.entity.Categoria;
import org.Adalid_Sanche.repository.CategoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoriasServiceImp implements IntCategoriasService { 
	  
	@Autowired
	private CategoriasRepository repoCategorias;
	
	private List<Categoria> categorias;
	
	public CategoriasServiceImp() {	
		categorias = new LinkedList<>(); 
		
		Categoria c1 = new Categoria();
		c1.setId(1);
		c1.setNombre("Ingenieria");
		c1.setDescripcion("Relacionado con diversas Areas de Ingenieria");
		
		Categoria c2 = new Categoria();
		c2.setId(2);
		c2.setNombre("Programadores Web");
		c2.setDescripcion("Relacionado con el Desarrollo Web");
		
		Categoria c3 = new Categoria();
		c3.setId(3);
		c3.setNombre("Contabilidad");
		c3.setDescripcion("Relacionado con nóminas y auditorias");
		
		Categoria c4 = new Categoria();
		c4.setId(4);
		c4.setNombre("Administración");
		c4.setDescripcion("Relacionado con administración de cuentas");
		
		Categoria c5 = new Categoria();
		c5.setId(5);
		c5.setNombre("Marketing");
		c5.setDescripcion("Ventas"); 
		
		categorias.add(c1);
		categorias.add(c2);
		categorias.add(c3); 
		categorias.add(c4); 
		categorias.add(c5); 
		
	} 
	
	@Override
	public void guardar(Categoria categoria) {
		categorias.add(categoria);		
	}
	
	@Override
	public List<Categoria> obtenerCategorias() {
		return categorias;
	}
	
	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		for(Categoria c : categorias) {
			if( c.getId().compareTo(idCategoria) == 0) {
				return c;
			}
		}
		return null;
	}
	
	@Override
	public void eliminar(Integer idCategoria) {
		categorias.remove(buscarPorId(idCategoria));
	}
	
	@Override
	public int numeroCategorias() {
		return categorias.size(); 
	}
	
	@Override
	public Page<Categoria> BuscarTodas(Pageable page) {
		return repoCategorias.findAll(page);
	}
}
