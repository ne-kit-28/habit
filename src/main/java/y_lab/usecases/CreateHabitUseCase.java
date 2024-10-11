package y_lab.usecases;

import y_lab.domain.entities.Frequency;
import y_lab.domain.entities.Habit;
import y_lab.domain.entities.User;
import y_lab.out.repositories.HabitRepositoryImpl;
import y_lab.out.repositories.UserRepositoryImpl;

import java.time.LocalDate;

public class CreateHabitUseCase {
    private final HabitRepositoryImpl habitRepository;
    private final UserRepositoryImpl userRepository;

    public CreateHabitUseCase(HabitRepositoryImpl habitRepository, UserRepositoryImpl userRepository) {
        this.habitRepository = habitRepository;
        this.userRepository = userRepository;
    }

    public void createHabit(Long userId, String name, String description, Frequency frequency) {
        if (habitRepository.findByName(name, userId).isPresent()) {
            System.out.println("Habit with such name exist");
            System.out.println("Habit is not created");
            return;
        }
        Habit habit = new Habit(name, description, frequency, LocalDate.now());
        User user = userRepository.findById(userId).orElseThrow();
        habit.setUser(user);
        habitRepository.save(habit);
        System.out.println("Habit " + name + " is created!");
    }
}
