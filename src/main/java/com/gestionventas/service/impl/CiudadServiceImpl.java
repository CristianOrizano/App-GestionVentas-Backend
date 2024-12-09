package com.gestionventas.service.impl;

import com.gestionventas.domain.Ciudad;
import com.gestionventas.dto.ciudad.CiudadDto;
import com.gestionventas.dto.ciudad.CiudadFilterDto;
import com.gestionventas.dto.ciudad.CiudadSaveDto;
import com.gestionventas.dto.ciudad.mapper.CiudadMapper;
import com.gestionventas.repository.CiudadRepository;
import com.gestionventas.service.ICiudadService;
import com.gestionventas.shared.exeption.ResourceNotFoundException;
import com.gestionventas.shared.page.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CiudadServiceImpl implements ICiudadService {

    private final CiudadMapper ciudadMapper;
    private final CiudadRepository ciudadRepository;

    @Override
    public List<CiudadDto> findAll() {
        return ciudadRepository.findAll().stream()
                .map(ciudadMapper::toCiudadDto)// Transforma cada coleccion a dto
                .toList();   // Recolecta el resultado como una List
    }
    @Override
    public CiudadDto findById(Long id) {
        return ciudadRepository.findById(id) //devuelve un Optional<Ciudad>
                .map(ciudadMapper::toCiudadDto)//map de Optional transforma un único valor, si está presente.
                .orElseThrow(() ->  new ResourceNotFoundException("Ciudad no encontrada con ID: " + id));
    }
    @Override
    public CiudadDto create(CiudadSaveDto ciudadBody) {
        Ciudad ciudad = ciudadMapper.toCiudad(ciudadBody);
        return ciudadMapper.toCiudadDto(ciudadRepository.save(ciudad));
    }

    @Override
    public CiudadDto update(Long id, CiudadSaveDto ciudadBody) {
        Ciudad ciudad = ciudadRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Ciudad no encontrada con ID: " + id));
        ciudadMapper.updateCiudad(ciudadBody,ciudad);
        return ciudadMapper.toCiudadDto(ciudadRepository.save(ciudad));
    }

    @Override
    public CiudadDto disable(Long id) {
        return null;
    }

    @Override
    public PageResponse<CiudadDto> findPaginated(CiudadFilterDto filter) {
        Sort sort = filter.getSortDir().equalsIgnoreCase("asc") ? Sort.by(filter.getSortBy()).ascending() : Sort.by(filter.getSortBy()).descending();
        Pageable pageable = PageRequest.of(filter.getPage()- 1, filter.getSize(),sort);
        //sin filtros
        // Page<Ciudad> ciudadPage = ciudadRepository.findAll(pageable);
        //con filtros
        Page<Ciudad> ciudadPage = ciudadRepository.findAllWithFilters(filter.getNombre(), pageable);

        // Mapear cada elemento de la página (Ciudad) a CiudadDto
        List<CiudadDto> ciudadDtos = ciudadPage.getContent().stream()
                .map(ciudadMapper::toCiudadDto)  // Mapeamos de Ciudad a CiudadDto
                .toList(); // Lista inmutable
                //.collect(Collectors.toList());  // Lista mutable

        // Crear el objeto PageResponse
        return PageResponse.<CiudadDto>builder()
                .content(ciudadDtos)
                .currentPage(ciudadPage.getNumber() + 1)
                .perPage(ciudadPage.getSize())
                .totalPages(ciudadPage.getTotalPages())
                .totalElements(ciudadPage.getTotalElements())
                .build();
    }

}
