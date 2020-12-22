package topjava.graduation.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurant", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "unique_restaurant_name_idx")})
@ToString(callSuper = true, exclude = "dishes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant extends AbstractNamedEntity {

    @JsonManagedReference(value = "restaurant")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Dish> dishes;

    public Restaurant(Long id, String name) {
        super(id, name);
    }
}