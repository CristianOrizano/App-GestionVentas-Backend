package com.gestionventas.dto.producto.mapper;

import com.gestionventas.domain.Cliente;
import com.gestionventas.domain.Producto;
import com.gestionventas.dto.categoria.mapper.CategoriaMapper;
import com.gestionventas.dto.cliente.ClienteDto;
import com.gestionventas.dto.cliente.ClienteSaveDto;
import com.gestionventas.dto.producto.ProductoDto;
import com.gestionventas.dto.producto.ProductoSaveDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,uses = CategoriaMapper.class)
public interface ProductoMapper {
    ProductoDto toDto(Producto producto);
    Producto toEntity (ProductoSaveDto productoSaveDto);
    Producto updateEntity(ProductoSaveDto productoSaveDto, @MappingTarget Producto producto);
}
