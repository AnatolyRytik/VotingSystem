package topjava.graduation.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "lunch", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "name"}, name = "restaurant_id_name_idx")})
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
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

}
