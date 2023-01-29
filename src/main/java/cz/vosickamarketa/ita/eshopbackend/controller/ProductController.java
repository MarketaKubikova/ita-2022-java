package cz.vosickamarketa.ita.eshopbackend.controller;

import cz.vosickamarketa.ita.eshopbackend.model.ProductDTO;
import cz.vosickamarketa.ita.eshopbackend.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/products/")
public class ProductController {
    private final ProductService productService;

    @GetMapping()
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable(name="id") Long id) {
            return ResponseEntity.ok(productService.getProductById(id));
    }
}
