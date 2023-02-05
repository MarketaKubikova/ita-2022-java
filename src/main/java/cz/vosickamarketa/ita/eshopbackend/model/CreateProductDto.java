package cz.vosickamarketa.ita.eshopbackend.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDto {
    @NotBlank
    @Max(256)
    private String name;
    @NotBlank
    @Max(512)
    private String description;
    @Positive
    private Long price;
    @PositiveOrZero
    private Long stock;
    @NotBlank
    private String image;
}
