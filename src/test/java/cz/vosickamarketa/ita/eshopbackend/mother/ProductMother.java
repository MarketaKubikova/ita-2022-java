package cz.vosickamarketa.ita.eshopbackend.mother;

import cz.vosickamarketa.ita.eshopbackend.domain.Product;
import cz.vosickamarketa.ita.eshopbackend.model.CreateProductDto;
import cz.vosickamarketa.ita.eshopbackend.model.ProductDto;

public class ProductMother {
    public static Product prepareProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Rychlé šípy");
        product.setDescription("Nejlepší kniha");
        product.setPrice(249L);
        product.setStock(55L);
        product.setImage("cover");

        return product;
    }

    public static ProductDto prepareProductDto() {
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("Rychlé šípy");
        productDto.setDescription("Nejlepší kniha");
        productDto.setPrice(249L);
        productDto.setStock(55L);
        productDto.setImage("cover");

        return productDto;
    }

    public static CreateProductDto prepareCreateProductDto() {
        CreateProductDto createProductDto = new CreateProductDto();
        createProductDto.setName("Rychlé šípy");
        createProductDto.setDescription("Nejlepší kniha");
        createProductDto.setPrice(249L);
        createProductDto.setStock(55L);
        createProductDto.setImage("cover");

        return createProductDto;
    }
}
