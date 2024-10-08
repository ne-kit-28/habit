package y_lab.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Progress {
    private long id;
    private User user;
    private Habit habit;
    private LocalDate date;
}
