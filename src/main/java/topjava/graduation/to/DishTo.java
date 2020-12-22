package topjava.graduation.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor
public class DishTo extends BaseTo {

    @NotBlank
    private String name;

    @NotNull
    private Double price;

    @NotNull
    private Long restaurantId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    private LocalDate date;

    public DishTo(@NotBlank String name, @NotNull Double price, @NotNull Long restaurantId, @NotNull LocalDate date) {
        this.name = name;
        this.price = price;
        this.restaurantId = restaurantId;
        this.date = date;
    }

    public DishTo(Long id, @NotBlank String name, @NotNull Double price, @NotNull Long restaurantId, @NotNull LocalDate date) {
        super(id);
        this.name = name;
        this.price = price;
        this.restaurantId = restaurantId;
        this.date = date;
    }
}
