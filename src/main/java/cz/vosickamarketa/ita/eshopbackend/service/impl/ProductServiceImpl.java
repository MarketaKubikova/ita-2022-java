package cz.vosickamarketa.ita.eshopbackend.service.impl;

import cz.vosickamarketa.ita.eshopbackend.domain.Product;
import cz.vosickamarketa.ita.eshopbackend.mapper.ProductMapper;
import cz.vosickamarketa.ita.eshopbackend.model.CreateProductDto;
import cz.vosickamarketa.ita.eshopbackend.model.ProductDto;
import cz.vosickamarketa.ita.eshopbackend.repository.ProductRepository;
import cz.vosickamarketa.ita.eshopbackend.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDto> getAllProducts() {
        log.info("Returning all products in db.");

        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public ProductDto getProductById(Long id) {
        ProductDto productDto = productRepository.findById(id)
                .map(productMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        log.info("Returning product with id %d.".formatted(id));

        return productDto;
    }

    @Override
    public ProductDto saveProduct(CreateProductDto productDto) {
        Product product = productMapper.toDomain(productDto);

        productRepository.save(product);
        log.info("New product has been successfully saved to db with id %d.".formatted(product.getId()));

        return productMapper.toDto(product);
    }

    @Override
    public ProductDto updateProduct(Long id, CreateProductDto productDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        productMapper.mergeProduct(product, productDto);
        productRepository.save(product);
        log.info("Product with id %d has been successfully updated.".formatted(id));

        return productMapper.toDto(product);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            log.debug("Product with id %d cannot be found.".formatted(id));
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        log.info("Product with id %d has been deleted.".formatted(id));
        productRepository.deleteById(id);
    }
}
