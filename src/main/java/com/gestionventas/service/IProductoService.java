package com.gestionventas.service;

import com.gestionventas.dto.empleado.EmpleadoDto;
import com.gestionventas.dto.empleado.EmpleadoFilterDto;
import com.gestionventas.dto.empleado.EmpleadoSaveDto;
import com.gestionventas.dto.producto.ProductoDto;
import com.gestionventas.dto.producto.ProductoFilterDto;
import com.gestionventas.dto.producto.ProductoSaveDto;
import com.gestionventas.shared.page.PageResponse;

import java.util.List;

public interface IProductoService {
    List<ProductoDto> findAll();
    ProductoDto findById(Long id);
    ProductoDto create(ProductoSaveDto productoBody);
    ProductoDto update(Long id, ProductoSaveDto productoBody) ;
    ProductoDto disable(Long id) ;
    PageResponse<ProductoDto> findPaginated(ProductoFilterDto filter);
}
