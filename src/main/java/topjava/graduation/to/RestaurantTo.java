package topjava.graduation.to;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantTo extends BaseTo {
    private Long id;

    @NotBlank
    private String name;
}
