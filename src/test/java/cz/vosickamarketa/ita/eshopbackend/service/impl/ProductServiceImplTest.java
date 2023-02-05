package cz.vosickamarketa.ita.eshopbackend.service.impl;

import cz.vosickamarketa.ita.eshopbackend.domain.Product;
import cz.vosickamarketa.ita.eshopbackend.mapper.ProductMapper;
import cz.vosickamarketa.ita.eshopbackend.model.CreateProductDto;
import cz.vosickamarketa.ita.eshopbackend.model.ProductDto;
import cz.vosickamarketa.ita.eshopbackend.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static cz.vosickamarketa.ita.eshopbackend.mother.ProductMother.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private ProductRepository productRepository;
    @Mock
    ProductMapper productMapper;

    @Test
    void getProductById_shouldReturnOneProduct() {
        Product product = prepareProduct();
        ProductDto expectedResult = prepareProductDto();

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productMapper.toDto(product)).thenReturn(expectedResult);

        ProductDto result = productService.getProductById(1L);

        assertEquals(expectedResult, result);

        verify(productRepository).findById(1L);
        verify(productMapper).toDto(product);
    }

    @Test
    void getProductById_productNotFound_shouldThrowEx() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> productService.getProductById(1L));
        verifyNoInteractions(productMapper);
    }

    @Test
    void getAllProducts_shouldReturnManyItems() {
        Product product = prepareProduct();
        ProductDto productDto = prepareProductDto();
        List<Product> productList = List.of(product, product);

        when(productRepository.findAll()).thenReturn(productList);
        when(productMapper.toDto(product)).thenReturn(productDto);

        List<ProductDto> result = productService.getAllProducts();
        List<ProductDto> expectedResult = List.of(productDto, productDto);

        assertEquals(expectedResult, result);

        verify(productRepository).findAll();
        verify(productMapper, times(2)).toDto(product);
    }

    @Test
    void getAllProducts_shouldReturnEmptyList() {
        List<Product> productList = Collections.emptyList();

        when(productRepository.findAll()).thenReturn(productList);

        List<ProductDto> result = productService.getAllProducts();
        List<ProductDto> expectedResult = Collections.emptyList();

        assertEquals(expectedResult, result);

        verify(productRepository).findAll();
        verifyNoInteractions(productMapper);
    }

    @Test
    void saveProduct() {
        CreateProductDto createProductDto = prepareCreateProductDto();
        ProductDto expectedResult = prepareProductDto();
        Product product = prepareProduct();

        when(productMapper.toDomain(createProductDto)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.toDto(product)).thenReturn(expectedResult);

        ProductDto result = productService.saveProduct(createProductDto);

        assertEquals(expectedResult, result);

        verify(productRepository).save(product);
        verify(productMapper).toDomain(createProductDto);
        verify(productMapper).toDto(product);
    }

    @Test
    void updateProduct() {
        CreateProductDto createProductDto = prepareCreateProductDto();
        Product product = prepareProduct();

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        productService.updateProduct(1L, createProductDto);

        verify(productRepository).findById(1L);
        verify(productRepository).save(product);
        verify(productMapper).mergeProduct(product, createProductDto);
    }

    @Test
    void updateProduct_productNotFound_shouldThrowEx() {
        CreateProductDto createProductDto = prepareCreateProductDto();

        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> productService.updateProduct(1L, createProductDto));

        verify(productRepository).findById(1L);
        verifyNoMoreInteractions(productRepository);
        verifyNoInteractions(productMapper);
    }

    @Test
    void deleteProduct_success() {
        when(productRepository.existsById(1L)).thenReturn(true);

        productService.deleteProduct(1L);

        verify(productRepository).deleteById(1L);
    }

    @Test
    void deleteProduct_productNotFound_shouldThrowEx() {
        when(productRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResponseStatusException.class, () -> productService.deleteProduct(1L));

        verifyNoMoreInteractions(productRepository);
    }
}
