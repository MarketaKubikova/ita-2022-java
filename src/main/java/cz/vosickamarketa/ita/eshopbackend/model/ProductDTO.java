package cz.vosickamarketa.ita.eshopbackend.model;

public class ProductDTO {
    Long id;
    String name;
    String description;
    Long price;
    Long stock;
    String image;

    public ProductDTO(Long id, String name, String description, Long price, Long stock, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getPrice() {
        return price;
    }

    public Long getStock() {
        return stock;
    }

    public String getImage() {
        return image;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
