package mk.ukim.finki.wp.wpelibrary.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.wp.wpelibrary.model.enumerations.Category;


import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double price;

    private String title;

    private String description;

    private Integer quantity;

    @Column(columnDefinition="text",length = 1000)
    private String imglink="";

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    private Publisher publisher;

    @ManyToMany
    private List<Author> author;

    public Item(Double price, String title,String description, Integer quantity, String imglink, Category category) {
        this.price = price;
        this.title = title;
        this.description=description;
        this.quantity = quantity;
        this.imglink = imglink;
        this.category = category;
    }

    public Item(Double price, String title,String description, Integer quantity, String imglink, Category category, Publisher publisher) {
        this.price = price;
        this.title = title;
        this.description=description;
        this.quantity = quantity;
        this.imglink = imglink;
        this.category = category;
        this.publisher = publisher;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getImglink() {
        return imglink;
    }

    public void setImglink(String imglink) {
        this.imglink = imglink;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
