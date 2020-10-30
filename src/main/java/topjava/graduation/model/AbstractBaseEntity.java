package topjava.graduation.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractBaseEntity extends AbstractPersistable<Long> {


    public AbstractBaseEntity() {
    }

    protected AbstractBaseEntity(Long id) {
        setId(id);
    }

    public boolean isNew() {
        return super.isNew();
    }
}
