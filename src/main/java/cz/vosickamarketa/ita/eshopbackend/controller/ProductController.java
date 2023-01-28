package cz.vosickamarketa.ita.eshopbackend.controller;

import cz.vosickamarketa.ita.eshopbackend.model.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class ProductController {
    ProductDTO product1 = new ProductDTO(1L, "Rychlé šípy", "Super úžasné dobrodužství", 249L, 13L, "cover1");
    ProductDTO product2 = new ProductDTO(2L, "Rychlé šípy 2", "Super úžasné dobrodužství pokračuje", 279L, 9L, "cover2");
    ProductDTO product3 = new ProductDTO(3L, "O pejskovi a kočičce", "Pohádka", 229L, 12L, "cover3");

    List<ProductDTO> productList = List.of(product1, product2, product3);

    @GetMapping("product")
    public List<ProductDTO> getAllProducts() {
        return productList;
    }

    @GetMapping("product/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable(name="id") Long id) {
            ProductDTO product = productList.stream()
                    .filter(p -> p.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            return ResponseEntity.ok(product);
    }
}
