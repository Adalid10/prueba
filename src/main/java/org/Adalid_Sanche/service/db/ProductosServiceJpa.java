package org.Adalid_Sanche.service.db;

import java.util.List;
import java.util.Optional;

import org.Adalid_Sanche.entity.Producto;
import org.Adalid_Sanche.repository.ProductosRepository;
import org.Adalid_Sanche.service.IntProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Primary
public class ProductosServiceJpa implements IntProductosService {
	
	@Autowired
	private ProductosRepository repoProducto;
	
	@Override
	public void guardar(Producto producto) {
		repoProducto.save(producto);
	}

	@Override
	public List<Producto> obtenerProductos() {
		return repoProducto.findAll();
	}

@Override
public Producto buscarPorId(int isProducto) {
	Optional<Producto> optional = repoProducto.findById(isProducto);
	if(optional.isPresent()) {
		return optional.get();
	}
	return null;
}

	@Override
	public void eliminar(int isProducto) {
		repoProducto.deleteById(isProducto);	
	}
	
	@Override
	public Page<Producto> BuscarTodas(Pageable page) {
		return repoProducto.findAll(page);
	}

}
