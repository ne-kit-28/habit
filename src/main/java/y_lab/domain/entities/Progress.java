package y_lab.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Progress {
    private Long id;
    private User user;
    private Habit habit;
    private LocalDate date;
}
