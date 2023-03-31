package org.Adalid_Sanche.service;

import org.Adalid_Sanche.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IntProductosService {
	public List<Producto> obtenerProductos();
	public void guardar(Producto producto);
	public void eliminar(int idProducto);
	public Producto buscarPorId(int idProducto);
	Page<Producto> BuscarTodas(Pageable page);
}

