package cz.vosickamarketa.ita.eshopbackend.mapper;

import cz.vosickamarketa.ita.eshopbackend.domain.Product;
import cz.vosickamarketa.ita.eshopbackend.model.CreateProductDto;
import cz.vosickamarketa.ita.eshopbackend.model.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface ProductMapper {
    Product toDomain(CreateProductDto productDto);
    ProductDto toDto(Product product);

    void mergeProduct(@MappingTarget Product target, CreateProductDto source);
}
