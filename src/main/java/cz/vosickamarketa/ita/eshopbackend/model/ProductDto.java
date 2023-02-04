package cz.vosickamarketa.ita.eshopbackend.model;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Long price;
    private Long stock;
    private String image;
}
