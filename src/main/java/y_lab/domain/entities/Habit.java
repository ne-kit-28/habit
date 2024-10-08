package y_lab.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Habit {
    private long id;
    private User user;
    private String name;
    private String description;
    private Frequency frequency;
    private LocalDate createdAt;
}
