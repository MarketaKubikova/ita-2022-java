package cz.vosickamarketa.ita.eshopbackend.domain;


import cz.vosickamarketa.ita.eshopbackend.customvalidation.StartsWithUppercase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @StartsWithUppercase
    @NotBlank
    @Length(max = 256)
    private String name;
    @Length(max = 512)
    private String description;
    @Positive
    private Long price;
    @PositiveOrZero
    private Long stock;
    @NotBlank
    private String image;
}
