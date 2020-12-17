package topjava.graduation.to;

import lombok.Getter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@ToString(callSuper = true)
public class DishTo extends BaseTo {

    @NotBlank
    private final String name;

    @NotNull
    private final Double price;

    @NotNull
    private final Long restaurantId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    private final LocalDate date;

    public DishTo(String name, Double price, Long restaurantId, LocalDate date) {
        this.name = name;
        this.price = price;
        this.restaurantId = restaurantId;
        this.date = date;
    }
}
