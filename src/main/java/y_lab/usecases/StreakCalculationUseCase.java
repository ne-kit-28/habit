package y_lab.usecases;

import y_lab.domain.entities.Frequency;
import y_lab.domain.entities.Habit;
import y_lab.domain.entities.Progress;
import y_lab.out.repositories.HabitRepositoryImpl;
import y_lab.out.repositories.ProgressRepositoryImpl;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class StreakCalculationUseCase {
    private final ProgressRepositoryImpl progressRepository;
    private final HabitRepositoryImpl habitRepository;

    public StreakCalculationUseCase(ProgressRepositoryImpl progressRepository
            , HabitRepositoryImpl habitRepository) {
        this.progressRepository = progressRepository;
        this.habitRepository = habitRepository;
    }

    public void calculateStreak(Long habitId) {
        Habit habit = habitRepository.findById(habitId).orElseThrow(NoSuchElementException::new);

        ArrayList<Progress> progressList = progressRepository.findByHabitId(habitId);
        if (progressList.isEmpty()) {
            System.out.println("Current streak: 0 days.");
            System.out.println("Max streak: 0 days.");
            return;
        }

        progressList = new ArrayList<>(progressList.stream()
                .sorted(Comparator.comparing(Progress::getDate))
                .toList());

        // Подсчет текущего streak'а
        int streak = 1;
        int maxStreak = 1;
        for (int i = 1; i < progressList.size(); ++i) {
            if (Period.between(progressList.get(i).getDate(), progressList.get(i - 1).getDate()).getDays()
                    <= (habit.getFrequency().equals(Frequency.WEEKLY) ? 7 : 1) ) {
                ++streak;
            } else if (streak > maxStreak) {
                maxStreak = streak;
                streak = 1;
            }
        }
        if (streak > maxStreak)
            maxStreak = streak;

        System.out.println("Current streak: " + streak + " days.");
        System.out.println("Max streak: " + maxStreak + " days.");
    }
}
