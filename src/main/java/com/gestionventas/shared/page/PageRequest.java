package com.gestionventas.shared.page;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest {
    private int page;
    private int size;
    private String sortBy;
    private String sortDir;
}
