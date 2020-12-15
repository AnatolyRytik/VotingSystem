package topjava.graduation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_time"}, name = "user_vote_date_idx")})
@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
public class Vote extends AbstractBaseEntity {

    @Column(name = "user_id", nullable = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Long userId;

    @Column(name = "restaurant_id", nullable = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Long restaurantId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "date_time", unique = true)
    @NotNull
    private LocalDate date;

    public Vote(Long userId, Long restaurantId, @NotNull LocalDate date) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.date = date;
    }
}
