package com.gestionventas.shared.page;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {
    private List<T> content;        // Lista de elementos
    private int currentPage;        // Página actual
    private int perPage;       // por pagina
    private int totalPages;         // Total de páginas
    private long totalElements;     // Total de elementos
}