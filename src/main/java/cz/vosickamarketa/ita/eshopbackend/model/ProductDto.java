package cz.vosickamarketa.ita.eshopbackend.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProductDto {
    private Long id;
    @NotBlank
    @Max(256)
    private String name;
    @NotBlank
    @Max(512)
    private String description;
    @PositiveOrZero
    private Long price;
    @PositiveOrZero
    private Long stock;
    @NotBlank
    private String image;
}
