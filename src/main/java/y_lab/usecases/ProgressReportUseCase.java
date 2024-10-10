package y_lab.usecases;

public class ProgressReportUseCase {
    private final GenerateProgressStatisticsUseCase generateStatisticsUseCase;
    private final StreakCalculationUseCase streakCalculationUseCase;

    public ProgressReportUseCase(GenerateProgressStatisticsUseCase generateStatisticsUseCase
            , StreakCalculationUseCase streakCalculationUseCase) {
        this.generateStatisticsUseCase = generateStatisticsUseCase;
        this.streakCalculationUseCase = streakCalculationUseCase;
    }

    public void generateReport(String username, Long habitId, String period) { //String {"day", "week", "month"}
        // Генерация статистики
        generateStatisticsUseCase.generateProgressStatistics(habitId, period);

        // Подсчет streak
        streakCalculationUseCase.calculateStreak(habitId);

        System.out.println("Report generated successfully.");
    }
}
