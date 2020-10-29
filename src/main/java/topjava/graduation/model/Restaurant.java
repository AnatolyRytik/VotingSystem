package topjava.graduation.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "restaurant", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "name_idx")})
public class Restaurant extends AbstractNamedEntity {

    @Column(name = "name", nullable = false, unique = true)
    @Size(min = 2, max = 70)
    @NotBlank
    private String name;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("name DESC")
    private List<Lunch> lunches;

    public Restaurant() {
    }

    public Restaurant(Long id, String name) {
        super(id, name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Lunch> getLunches() {
        return this.lunches;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", id=" + getId() +
                '}';
    }
}