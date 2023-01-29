package cz.vosickamarketa.ita.eshopbackend.service.impl;

import cz.vosickamarketa.ita.eshopbackend.domain.Product;
import cz.vosickamarketa.ita.eshopbackend.model.ProductDTO;
import cz.vosickamarketa.ita.eshopbackend.repository.ProductRepository;
import cz.vosickamarketa.ita.eshopbackend.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        return mapToDTO(getProduct(id));
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = mapToEntity(productDTO);
        Product newProduct = productRepository.save(product);

        return mapToDTO(newProduct);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = getProduct(id);
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setImage(productDTO.getImage());

        Product updatedProduct = productRepository.save(product);

        return mapToDTO(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = getProduct(id);
        productRepository.delete(product);
    }

    private ProductDTO mapToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setStock(product.getStock());
        productDTO.setImage(product.getImage());

        return productDTO;
    }

    private Product mapToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setImage(productDTO.getImage());

        return product;
    }

    private Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
