package cz.vosickamarketa.ita.eshopbackend.service;

import cz.vosickamarketa.ita.eshopbackend.model.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long id);
}
