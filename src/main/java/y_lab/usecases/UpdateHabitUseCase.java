package y_lab.usecases;

import y_lab.domain.entities.Frequency;
import y_lab.domain.entities.Habit;
import y_lab.out.repositories.HabitRepositoryImpl;
import y_lab.out.repositories.UserRepositoryImpl;

import java.util.Optional;

public class UpdateHabitUseCase {
    private final HabitRepositoryImpl habitRepository;

    public UpdateHabitUseCase(HabitRepositoryImpl habitRepository) {
        this.habitRepository = habitRepository;
    }

    public void updateHabit(Long id, String newName, String newDescription, Frequency newFrequency) {
        Optional<Habit> habit = habitRepository.findById(id);

        if(habit.isEmpty()) {
            System.out.println("Habit with this id does not exist!");
            return;
        }

        // Проверка уникальности нового name
        if (newName != null && !newName.isEmpty() && habitRepository.findByName(newName, habit.get().getUser().getId()).isPresent()) {
            System.out.println("Name already in use by another account!");
            return;
        }

        if (newName != null && !newName.isEmpty())
            habit.get().setName(newName);
        if (newDescription != null && !newDescription.isEmpty())
            habit.get().setDescription(newDescription);
        if (newFrequency != null)
            habit.get().setFrequency(newFrequency);

        System.out.println("Habit updated successfully!");
    }
}
