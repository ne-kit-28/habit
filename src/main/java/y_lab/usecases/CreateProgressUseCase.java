package y_lab.usecases;

import y_lab.domain.entities.Habit;
import y_lab.domain.entities.Progress;
import y_lab.domain.entities.User;
import y_lab.out.repositories.HabitRepositoryImpl;
import y_lab.out.repositories.ProgressRepositoryImpl;
import y_lab.out.repositories.UserRepositoryImpl;

import java.time.LocalDate;
import java.util.NoSuchElementException;

public class CreateProgressUseCase {
    private final HabitRepositoryImpl habitRepository;
    private final UserRepositoryImpl userRepository;
    private final ProgressRepositoryImpl progressRepository;

    public CreateProgressUseCase(HabitRepositoryImpl habitRepository
            , UserRepositoryImpl userRepository
            , ProgressRepositoryImpl progressRepository ) {
        this.habitRepository = habitRepository;
        this.userRepository = userRepository;
        this.progressRepository = progressRepository;
    }

    public void createProgress(Long userId, Long habitId) {
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        Habit habit = habitRepository.findById(habitId).orElseThrow(NoSuchElementException::new);
        Progress progress = Progress.builder()
                .user(user)
                .habit(habit)
                .date(LocalDate.now())
                .build();
        progressRepository.save(progress);
        System.out.println("The habit is compete");
    }
}
