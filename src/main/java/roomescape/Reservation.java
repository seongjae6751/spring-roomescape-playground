package roomescape;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Reservation {
    private Long id;
    private String name;
    private String date;
    private String time;
}
