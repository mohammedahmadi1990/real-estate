package com.realestate.realestatemanagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "estate")
@NoArgsConstructor
@Getter
@Setter
public class Estate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="address", nullable = false)
    private String address;

    @DateTimeFormat(pattern = "yyy-MM-dd")
    @Column(name="listed_date", nullable = false)
    private Date listedDate;

    @Column(name="price", nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name="user", nullable = false)
    private User user;

    @ManyToMany
    @JoinColumn(name="notes")
    private List<Note> notes;

    public Estate(String address, Date listedDate, double price, User user, List<Note> notes) {
        this.address = address;
        this.listedDate = listedDate;
        this.price = price;
        this.user = user;
        this.notes = notes;
    }

    public Estate(String address, Date listedDate, double price, User user) {
        this.address = address;
        this.listedDate = listedDate;
        this.price = price;
        this.user = user;
    }
}
