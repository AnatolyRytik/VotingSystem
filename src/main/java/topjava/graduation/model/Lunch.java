package topjava.graduation.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "lunch", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "name"}, name = "restaurant_id_name_idx")})
public class Lunch extends AbstractNamedEntity {


    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;


    @Column(name = "price", nullable = false)
    private Double price;


    @JoinColumn(name = "restaurant_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    public Lunch() {
    }

    public Lunch(Long id, LocalDate date, String name, Double price) {
        super(id, name);
        this.date = date;
        this.price = price;
    }

    public Lunch(Long id, LocalDate date, String name, Double price, Restaurant restaurant) {
        super(id, name);
        this.date = date;
        this.price = price;
        this.restaurant = restaurant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Lunch{" + "id=" + id + ", name='" + name + '\'' + ", date=" + date + ", price=" + price + ", restaurant="
                + restaurant + '}';
    }


}
