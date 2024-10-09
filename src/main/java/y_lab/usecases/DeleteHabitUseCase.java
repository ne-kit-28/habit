package y_lab.usecases;

import y_lab.domain.entities.Habit;
import y_lab.out.repositories.HabitRepositoryImpl;
import y_lab.out.repositories.ProgressRepositoryImpl;

import java.util.NoSuchElementException;

public class DeleteHabitUseCase {
    private final HabitRepositoryImpl habitRepository;
    private final ProgressRepositoryImpl progressRepository;

    public DeleteHabitUseCase(HabitRepositoryImpl habitRepository, ProgressRepositoryImpl progressRepository) {
        this.habitRepository = habitRepository;
        this.progressRepository = progressRepository;
    }

    public void deleteHabit(Long id) {
        habitRepository.delete(id);
        progressRepository.deleteAllByHabitId(id);
        System.out.println("Habit with id: " + id + " was deleted!");
    }
}
