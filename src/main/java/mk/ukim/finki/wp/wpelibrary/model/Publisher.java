package mk.ukim.finki.wp.wpelibrary.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String contact;
    private String address;

    @OneToMany(mappedBy = "publisher",fetch = FetchType.EAGER)
    private List<Item> item;

    public Publisher(String name, String address, String contact, List<Item> item) {
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.item = item;
    }

    public Publisher(String name, String contact, String address) {
        this.name = name;
        this.contact = contact;
        this.address = address;
    }


    public Publisher() {
    }
}
