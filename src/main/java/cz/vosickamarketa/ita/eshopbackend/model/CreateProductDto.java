package cz.vosickamarketa.ita.eshopbackend.model;

import cz.vosickamarketa.ita.eshopbackend.customvalidation.StartsWithUppercase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDto {
    @StartsWithUppercase
    @NotBlank
    @Length(max = 256)
    private String name;
    @NotBlank
    @Length(max = 512)
    private String description;
    @Positive
    private Long price;
    @PositiveOrZero
    private Long stock;
    @NotBlank
    private String image;
}
