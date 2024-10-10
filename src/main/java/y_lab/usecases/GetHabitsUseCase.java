package y_lab.usecases;

import y_lab.domain.entities.Frequency;
import y_lab.domain.entities.Habit;
import y_lab.out.repositories.HabitRepositoryImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class GetHabitsUseCase {
    private final HabitRepositoryImpl habitRepository;

    public GetHabitsUseCase(HabitRepositoryImpl habitRepository) {
        this.habitRepository = habitRepository;
    }

    ArrayList<Habit> getHabits(Long userId, Object filter) {
        ArrayList<Habit> habits = habitRepository.findHabitsByUserId(userId).orElseThrow(NoSuchElementException::new);
        if (filter instanceof LocalDate instanceFilter) {
            habits = new ArrayList<>(habits.stream()
                    .filter(habit -> habit.getCreatedAt().equals(instanceFilter))
                    .toList());
        }
        else if (filter instanceof Frequency instanceFilter) {
            habits = new ArrayList<>(habits.stream()
                    .filter(habit -> habit.getFrequency().equals(instanceFilter))
                    .toList());
        }
        for(Habit habit : habits)
            System.out.println(habit.getName() + " " + habit.getDescription());
        return habits;
    }
}
