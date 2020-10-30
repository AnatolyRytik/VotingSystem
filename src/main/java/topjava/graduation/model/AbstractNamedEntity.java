package topjava.graduation.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class AbstractNamedEntity extends AbstractBaseEntity {


    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "name", nullable = false)
    protected String name;

    protected AbstractNamedEntity() {
    }

    protected AbstractNamedEntity(Long id, String name) {
        super(id);
        this.name = name;
    }
}