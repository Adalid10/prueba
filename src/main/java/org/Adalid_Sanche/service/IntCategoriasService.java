package org.Adalid_Sanche.service;

import java.util.List;

import org.Adalid_Sanche.entity.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IntCategoriasService {
	
	public void guardar(Categoria categoria); 
	public List<Categoria> obtenerCategorias();
	public Categoria buscarPorId(Integer idCategoria);
	public void eliminar(Integer idCategoria);
	public int numeroCategorias(); 
	Page<Categoria> BuscarTodas(Pageable page);

}
