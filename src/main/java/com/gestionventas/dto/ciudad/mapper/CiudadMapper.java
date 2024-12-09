package com.gestionventas.dto.ciudad.mapper;

import com.gestionventas.domain.Ciudad;
import com.gestionventas.dto.ciudad.CiudadDto;
import com.gestionventas.dto.ciudad.CiudadSaveDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CiudadMapper {
    @Mapping(source = "nombre", target = "cityName")
    CiudadDto toCiudadDto(Ciudad ciudad);
    Ciudad toCiudad (CiudadSaveDto ciudadSaveDto);

    /*MapStruct tomará los valores de los campos del DTO (CiudadSaveDto)
     y los copiará en los campos equivalentes de la entidad (Ciudad),
     pero solo sobreescribirá los valores de los campos que están presentes en el DTO.*/
    // @MappingTarget Ciudad ciudad: Es la entidad existente que necesitas actualizar con los valores provenientes del DTO.
    Ciudad updateCiudad(CiudadSaveDto ciudadSaveDto, @MappingTarget Ciudad ciudad);

    /*Al usar stream ya no es necesario definir ese mapeo
    List<CiudadDto> toCiudadesDtos(List<Ciudad> ciudades);
     */
}
