package com.gestionventas.repository;

import com.gestionventas.domain.Ciudad;
import com.gestionventas.domain.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Query("SELECT p FROM Producto p WHERE " +
            "(:descripcion IS NULL OR LOWER(p.descripcion) LIKE LOWER(CONCAT('%', :descripcion, '%'))) AND " +
            "(:stock IS NULL OR p.stock = :stock) AND " +
            "(:precio IS NULL OR p.precio = :precio) AND " +
            "(:nimagen IS NULL OR LOWER(p.nimagen) LIKE LOWER(CONCAT('%', :nimagen, '%'))) AND " +
            "(:idCategoria IS NULL OR p.idCategoria = :idCategoria) AND " +
            "(:idMarca IS NULL OR p.idMarca = :idMarca) AND " +
            "(:state IS NULL OR p.state = :state)")
    Page<Producto> findByFilters(@Param("descripcion") String descripcion,
                                 @Param("stock") Integer stock,
                                 @Param("precio") Double precio,
                                 @Param("nimagen") String nimagen,
                                 @Param("idCategoria") Long idCategoria,
                                 @Param("idMarca") Long idMarca,
                                 @Param("state") Boolean state,
                                 Pageable pageable);
}
