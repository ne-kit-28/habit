package y_lab.usecases;

import y_lab.domain.entities.Habit;
import y_lab.out.repositories.HabitRepositoryImpl;
import y_lab.out.repositories.UserRepositoryImpl;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class GetHabitsUseCase {
    private final HabitRepositoryImpl habitRepository;

    public GetHabitsUseCase(HabitRepositoryImpl habitRepository) {
        this.habitRepository = habitRepository;
    }

    ArrayList<Habit> getHabits(Long userId) { //TODO filter
        ArrayList<Habit> habits = habitRepository.findHabitsByUserId(userId).orElseThrow(NoSuchElementException::new);
        for(Habit habit : habits)
            System.out.println(habit.getName() + " " + habit.getDescription());
        return habits;
    }
}
