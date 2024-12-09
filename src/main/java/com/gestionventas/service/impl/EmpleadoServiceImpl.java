package com.gestionventas.service.impl;


import com.gestionventas.domain.Ciudad;
import com.gestionventas.domain.Empleado;
import com.gestionventas.dto.ciudad.CiudadDto;
import com.gestionventas.dto.empleado.EmpleadoDto;
import com.gestionventas.dto.empleado.EmpleadoFilterDto;
import com.gestionventas.dto.empleado.EmpleadoSaveDto;
import com.gestionventas.dto.empleado.mapper.EmpleadoMapper;
import com.gestionventas.repository.CiudadRepository;
import com.gestionventas.repository.EmpleadoRepository;
import com.gestionventas.service.IEmpleadoService;
import com.gestionventas.shared.exeption.ResourceNotFoundException;
import com.gestionventas.shared.page.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
@RequiredArgsConstructor
@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

    private final EmpleadoMapper empleadoMapper;
    private final EmpleadoRepository empleadoRepository;
    private final CiudadRepository ciudadRepository;

    @Override
    public List<EmpleadoDto> findAll() {
        return empleadoRepository.findAll().stream()
                .map(empleadoMapper::toEmpleadoDto)
                .toList();
    }

    @Override
    public EmpleadoDto findById(Long id) {
        return empleadoRepository.findById(id)
                .map(empleadoMapper::toEmpleadoDto)
                .orElseThrow(() ->  new ResourceNotFoundException("Empleado no encontrada con ID: " + id));
    }

    @Override
    public EmpleadoDto create(EmpleadoSaveDto empleadoBody) {
        ciudadRepository.findById(empleadoBody.getIdCiudad())
                .orElseThrow(() ->  new ResourceNotFoundException("Ciudad no encontrada con ID: " + empleadoBody.getIdCiudad()));
        Empleado empleado = empleadoMapper.toEmpleado(empleadoBody);
        empleado.setState(true);
        return empleadoMapper.toEmpleadoDto(empleadoRepository.save(empleado));
    }

    @Override
    public EmpleadoDto update(Long id, EmpleadoSaveDto empleadoBody) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Empleado no encontrada con ID: " + id));
        ciudadRepository.findById(empleadoBody.getIdCiudad())
                .orElseThrow(() ->  new ResourceNotFoundException("Ciudad no encontrada con ID: " + id));

        empleadoMapper.updateEmpleado(empleadoBody,empleado);
        return empleadoMapper.toEmpleadoDto(empleadoRepository.save(empleado));
    }

    @Override
    public EmpleadoDto disable(Long id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Empleado no encontrada con ID: " + id));
        empleado.setState(!empleado.isState());
        return empleadoMapper.toEmpleadoDto(empleadoRepository.save(empleado));
    }

    @Override
    public PageResponse<EmpleadoDto> findPaginated(EmpleadoFilterDto filter) {
        Sort sort = filter.getSortDir().equalsIgnoreCase("asc") ? Sort.by(filter.getSortBy()).ascending() : Sort.by(filter.getSortBy()).descending();
        Pageable pageable = PageRequest.of(filter.getPage()- 1, filter.getSize(),sort);

        Page<Empleado> empleadoPage = empleadoRepository.findByFilters(
                filter.getNombre(),
                filter.getApellido(),
                filter.getDireccion(),
                filter.getTelefono(),
                filter.getSueldo(),
                filter.getFechanacimiento(),
                filter.getState(), pageable
        );

        List<EmpleadoDto> empleadosDtos = empleadoPage.getContent().stream()
                .map(empleadoMapper::toEmpleadoDto)
                .toList();
        return PageResponse.<EmpleadoDto>builder()
                .content(empleadosDtos)
                .currentPage(empleadoPage.getNumber() + 1)
                .perPage(empleadoPage.getSize())
                .totalPages(empleadoPage.getTotalPages())
                .totalElements(empleadoPage.getTotalElements())
                .build();
    }
/*  Sin private Long idCiudad;
*  @Override
    public EmpleadoDto create(EmpleadoSaveDto empleadoBody) {
        Ciudad ciudad=  ciudadRepository.findById(empleadoBody.getIdCiudad())
                .orElseThrow(() ->  new ResourceNotFoundException("Ciudad no encontrada con ID: " + empleadoBody.getIdCiudad()));

        Empleado empleado = empleadoMapper.toEmpleado(empleadoBody);
        empleado.setCiudad(ciudad);
        empleado.setState(true);
        return empleadoMapper.toEmpleadoDto(empleadoRepository.save(empleado));
    }

* */

    /* LAZY
    @Override
    public List<EmpleadoDto> findAll() {
        List<Empleado> empleados = empleadoRepository.findAll();
        List<EmpleadoDto> empleadoDTOs = new ArrayList<>();

        for (Empleado empleado : empleados) {
            EmpleadoDto dto = new EmpleadoDto();
            dto.setId(empleado.getId());
            dto.setNombreEm(empleado.getNombre());
            dto.setApellido(empleado.getApellido());
            dto.setDireccion(empleado.getDireccion());
            dto.setTelefono(empleado.getTelefono());
            dto.setSueldo(empleado.getSueldo());
            dto.setFechanacimiento(empleado.getFechanacimiento());
            dto.setCiudadNombre(empleado.getCiudad() != null ? empleado.getCiudad().getNombre() : null);
            dto.setState(empleado.isState());

            empleadoDTOs.add(dto);
        }
        return empleadoDTOs;
    } */
}
