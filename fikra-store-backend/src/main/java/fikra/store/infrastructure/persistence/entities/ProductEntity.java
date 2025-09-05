package fikra.store.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="price", nullable = false)
    private double price;

    @Column(name="description", length = 1000)
    private String description;

    @Column(name="category")
    private String category;

    @Column(name="image_url")
    private String image;

    @Embedded
    private RatingEmbeddable rating;
}