package fikra.store.domain;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

/**
 * Small value object representing product rating.
 * Keep it immutable (or treat as value object) where possible.
 */

@Getter
@Setter
public class Rating {
    private double rate;
    private int count;

    public Rating() {}

    public Rating(double rate, int count) {
        this.rate = rate;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rating rating = (Rating) o;
        return Double.compare(rating.rate, rate) == 0 && count == rating.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rate, count);
    }

    @Override
    public String toString() {
        return "Rating{" + "rate=" + rate + ", count=" + count + '}';
    }
}
