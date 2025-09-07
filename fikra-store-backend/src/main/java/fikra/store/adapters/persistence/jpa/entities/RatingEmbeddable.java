package fikra.store.adapters.persistence.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class RatingEmbeddable {
    @Column(name="rating_rate")
    private double rate;

    @Column(name="rating_count")
    private int count;
}
