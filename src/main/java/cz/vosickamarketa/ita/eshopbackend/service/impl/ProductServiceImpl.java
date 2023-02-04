package cz.vosickamarketa.ita.eshopbackend.service.impl;

import cz.vosickamarketa.ita.eshopbackend.domain.Product;
import cz.vosickamarketa.ita.eshopbackend.mapper.ProductMapper;
import cz.vosickamarketa.ita.eshopbackend.model.CreateProductDto;
import cz.vosickamarketa.ita.eshopbackend.model.ProductDto;
import cz.vosickamarketa.ita.eshopbackend.repository.ProductRepository;
import cz.vosickamarketa.ita.eshopbackend.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public ProductDto saveProduct(CreateProductDto productDto) {
        Product product = productMapper.toDomain(productDto);

        productRepository.save(product);

        return productMapper.toDto(product);
    }

    @Override
    public ProductDto updateProduct(Long id, CreateProductDto productDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        productMapper.mergeProduct(product, productDto);
        productRepository.save(product);

        return productMapper.toDto(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
