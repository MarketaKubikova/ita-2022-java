package cz.vosickamarketa.ita.eshopbackend.service;

import cz.vosickamarketa.ita.eshopbackend.model.CreateProductDto;
import cz.vosickamarketa.ita.eshopbackend.model.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
    ProductDto getProductById(Long id);
    ProductDto saveProduct(CreateProductDto productDto);
    ProductDto updateProduct(Long id, CreateProductDto productDto);
    void deleteProduct(Long id);
}
