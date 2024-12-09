package com.gestionventas.repository;

import com.gestionventas.domain.Empleado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    @Query("SELECT e FROM Empleado e WHERE " +
            "(:nombre IS NULL OR LOWER(e.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) AND " +
            "(:apellido IS NULL OR LOWER(e.apellido) LIKE LOWER(CONCAT('%', :apellido, '%'))) AND " +
            "(:direccion IS NULL OR LOWER(e.direccion) LIKE LOWER(CONCAT('%', :direccion, '%'))) AND " +
            "(:telefono IS NULL OR e.telefono = :telefono) AND " +
            "(:sueldo IS NULL OR e.sueldo = :sueldo) AND " +
            "(:fechanacimiento IS NULL OR e.fechanacimiento = :fechanacimiento) AND " +
            "(:state IS NULL OR e.state = :state)")
    Page<Empleado> findByFilters(@Param("nombre") String nombre,
                                 @Param("apellido") String apellido,
                                 @Param("direccion") String direccion,
                                 @Param("telefono") Integer telefono,  // Cambiar a Integer
                                 @Param("sueldo") Double sueldo,
                                 @Param("fechanacimiento") Date fechanacimiento,
                                 @Param("state") Boolean state,
                                 Pageable pageable);
}
