package topjava.graduation.to;

import lombok.*;

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
