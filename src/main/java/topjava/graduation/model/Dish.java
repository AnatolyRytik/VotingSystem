package topjava.graduation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "dish", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}, name = "dish_name_idx")})
@ToString(callSuper = true, exclude = {"restaurant"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dish extends AbstractNamedEntity {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    @Column(name = "date_time", nullable = false)
    private LocalDate date;

    @NotNull
    @Column(name = "price", nullable = false)
    private Double price;

    @JsonBackReference(value = "restaurant")
    @JoinColumn(name = "restaurant_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    public Dish(Long id, LocalDate date, String name, Double price, Restaurant restaurant) {
        super(id, name);
        this.date = date;
        this.price = price;
        this.restaurant = restaurant;
    }

    public Dish(String name, Double price, Restaurant restaurant, LocalDate date) {
        super(name);
        this.date = date;
        this.price = price;
        this.restaurant = restaurant;
    }

    public Dish(Dish dish) {
        this(dish.getId(), dish.getDate(), dish.getName(), dish.getPrice(), dish.getRestaurant());
    }
}
