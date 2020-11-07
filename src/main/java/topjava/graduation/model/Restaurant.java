package topjava.graduation.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurant")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class Restaurant extends AbstractNamedEntity {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @OrderBy("name DESC")
    @JsonManagedReference(value = "restaurant")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Lunch> lunches;


    public Restaurant(Long id, String name) {
        super(id, name);
    }
}