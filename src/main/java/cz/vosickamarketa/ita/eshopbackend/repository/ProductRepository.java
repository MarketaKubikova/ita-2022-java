package cz.vosickamarketa.ita.eshopbackend.repository;

import cz.vosickamarketa.ita.eshopbackend.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
