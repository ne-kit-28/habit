package y_lab.usecases;

import y_lab.domain.entities.Frequency;
import y_lab.domain.entities.Habit;
import y_lab.domain.entities.Progress;
import y_lab.out.repositories.HabitRepositoryImpl;
import y_lab.out.repositories.ProgressRepositoryImpl;
import y_lab.out.repositories.UserRepositoryImpl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

public class GenerateProgressStatisticsUseCase {
    private final ProgressRepositoryImpl progressRepository;
    private final HabitRepositoryImpl habitRepository;
    private final UserRepositoryImpl userRepository;

    public GenerateProgressStatisticsUseCase(ProgressRepositoryImpl progressRepository, HabitRepositoryImpl habitRepository, UserRepositoryImpl userRepository) {
        this.progressRepository = progressRepository;
        this.habitRepository = habitRepository;
        this.userRepository = userRepository;
    }

    public void generateProgressStatistics(Long habitId, String period) {
        Habit habit = habitRepository.findById(habitId).orElseThrow(NoSuchElementException::new);
        Frequency frequency = habit.getFrequency();
        // Определение периода для генерации статистики
        LocalDate startDate = calculateStartDate(period);
        LocalDate endDate = LocalDate.now();
        long totalDays = (ChronoUnit.DAYS.between(startDate, endDate) + 1) / (frequency == Frequency.WEEKLY ? 7 : 1);

        // Получение прогресса за указанный период
        ArrayList<Progress> progressList = progressRepository.findByHabitId(habitId);
        if (progressList.isEmpty()) {
            System.out.println("Habit: " + habit.getName());
            System.out.println("Period: " + period);
            System.out.println("Completed: 0 out of " + totalDays + " days.");
            System.out.println("Completion rate: 0%");
            return;
        }

        ArrayList<Progress> filteredProgresses = new ArrayList<>(progressList
                .stream()
                .filter(progress -> progress.getDate().isAfter(startDate))
                .toList());

        // Генерация статистики
        long completedDays = filteredProgresses.size();

        System.out.println("Habit: " + habit.getName());
        System.out.println("Period: " + period);
        System.out.println("Completed: " + completedDays + " out of " + totalDays + " days.");
        System.out.println("Completion rate: " + (completedDays * 100 / totalDays) + "%");
    }

    // Определение даты начала в зависимости от периода
    private LocalDate calculateStartDate(String period) {
        LocalDate today = LocalDate.now();
        return switch (period.toLowerCase()) {
            case "day" -> today.minusDays(1);
            case "week" -> today.minusWeeks(1);
            case "month" -> today.minusMonths(1);
            default -> throw new IllegalArgumentException("Unsupported period: " + period);
        };
    }
}

