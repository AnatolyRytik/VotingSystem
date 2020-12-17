package topjava.graduation.to;


import topjava.graduation.HasId;

public abstract class BaseTo implements HasId {
    protected Long id;

    public BaseTo() {
    }

    public BaseTo(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
