package y_lab.usecases;

import y_lab.domain.entities.Frequency;
import y_lab.domain.entities.Habit;
import y_lab.out.repositories.HabitRepositoryImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Optional;

public class GetHabitsUseCase {
    private final HabitRepositoryImpl habitRepository;

    public GetHabitsUseCase(HabitRepositoryImpl habitRepository) {
        this.habitRepository = habitRepository;
    }

    public ArrayList<Habit> getHabits(Long userId, Object filter) {
        ArrayList<Habit> habits = habitRepository.findHabitsByUserId(userId).orElseThrow(NoSuchElementException::new);
        if (filter instanceof LocalDate instanceFilter) {
            habits = new ArrayList<>(habits.stream()
                    .filter(habit -> habit.getCreatedAt().isAfter(instanceFilter))
                    .sorted(Comparator.comparing(Habit::getCreatedAt))
                    .toList());
        }
        else if (filter instanceof Frequency instanceFilter) {
            habits = new ArrayList<>(habits.stream()
                    .filter(habit -> habit.getFrequency().equals(instanceFilter))
                    .toList());
        }
        if (habits.isEmpty())
            System.out.println("No habits");
        else
            for(Habit habit : habits) {
                System.out.println("Name: " + habit.getName());
                System.out.println("Description: " + habit.getDescription());
                System.out.println("Created at: " + habit.getCreatedAt());
                System.out.println("Frequency: " + habit.getFrequency().toString());
                System.out.println();
            }
        return habits;
    }

    public Long getHabit(String habitName, Long userId) {
        Optional<Habit> habit = habitRepository.findByName(habitName, userId);
        if (habit.isPresent()) {
            System.out.println("Name: " + habit.get().getName());
            System.out.println("Description: " + habit.get().getDescription());
            System.out.println("Created at: " + habit.get().getCreatedAt());
            System.out.println("Frequency: " + habit.get().getFrequency().toString());
            return habit.get().getId();
        }
        System.out.println("No such habit");
        return -1L;
    }
}
