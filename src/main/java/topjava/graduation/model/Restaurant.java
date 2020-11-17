package topjava.graduation.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurant", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "unique_restaurant_name_idx")})
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class Restaurant extends AbstractNamedEntity {

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurant")
    private List<Dish> dishes;


    public Restaurant(Long id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "Restaurant [name=" + name +"]";
    }
}