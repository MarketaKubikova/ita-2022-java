package cz.vosickamarketa.ita.eshopbackend.mapper;

import cz.vosickamarketa.ita.eshopbackend.domain.Product;
import cz.vosickamarketa.ita.eshopbackend.model.CreateProductDto;
import cz.vosickamarketa.ita.eshopbackend.model.ProductDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static cz.vosickamarketa.ita.eshopbackend.mother.ProductMother.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Test
    void toDomain() {
        CreateProductDto productDto = prepareCreateProductDto();
        Product result = productMapper.toDomain(productDto);

        assertEquals(productDto.getName(), result.getName());
        assertEquals(productDto.getDescription(), result.getDescription());
        assertEquals(productDto.getPrice(), result.getPrice());
        assertEquals(productDto.getStock(), result.getStock());
        assertEquals(productDto.getImage(), result.getImage());
    }

    @Test
    void toDto() {
        Product product = prepareProduct();
        ProductDto result = productMapper.toDto(product);

        assertEquals(product.getId(), result.getId());
        assertEquals(product.getName(), result.getName());
        assertEquals(product.getDescription(), result.getDescription());
        assertEquals(product.getPrice(), result.getPrice());
        assertEquals(product.getStock(), result.getStock());
        assertEquals(product.getImage(), result.getImage());
    }

    @Test
    void mergeProduct() {
        Product product = prepareProduct();
        CreateProductDto productDto = prepareCreateProductDto();

        productMapper.mergeProduct(product, productDto);

        assertEquals(product.getName(), productDto.getName());
        assertEquals(product.getDescription(), productDto.getDescription());
        assertEquals(product.getPrice(), productDto.getPrice());
        assertEquals(product.getStock(), productDto.getStock());
        assertEquals(product.getImage(), productDto.getImage());
    }
}
