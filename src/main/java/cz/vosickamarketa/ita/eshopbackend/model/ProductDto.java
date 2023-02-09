package cz.vosickamarketa.ita.eshopbackend.model;

import cz.vosickamarketa.ita.eshopbackend.customvalidation.StartsWithUppercase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProductDto {
    private Long id;
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
