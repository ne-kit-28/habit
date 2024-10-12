package y_lab.service;

import y_lab.domain.entities.Frequency;
import y_lab.domain.entities.Habit;
import y_lab.out.repositories.HabitRepositoryImpl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorService {
    private final HabitRepositoryImpl habitRepository;
    private final NotificationService notificationService;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public ExecutorService(HabitRepositoryImpl habitRepository, NotificationService notificationService) {
        this.habitRepository = habitRepository;
        this.notificationService = notificationService;
    }


    public void startScheduler() {

        scheduler.scheduleAtFixedRate(() -> {
            Frequency frequency;
            for (Habit habit : habitRepository.getAll()) {
                frequency = habit.getFrequency();
                if (ChronoUnit.DAYS.between(LocalDate.now(), habit.getCreatedAt()) % (frequency.equals(Frequency.WEEKLY) ? 7 : 1) == 0) {
                    notificationService.sendNotification(habit.getUser().getEmail(), habit.getName());
                }
            }
        }, 0, 1, TimeUnit.DAYS); // Проверка напоминаний каждый день
    }

    public void stopScheduler() {
        scheduler.shutdown(); // Останавливает приём новых задач
        try {
            // Ожидание завершения уже запущенных задач (например, 30 секунд)
            if (!scheduler.awaitTermination(10, TimeUnit.SECONDS)) {
                scheduler.shutdownNow(); // Принудительное завершение, если задачи не завершились вовремя
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow(); // Прерывание ожидания завершения — выполняем немедленное завершение
            Thread.currentThread().interrupt(); // Восстанавливаем флаг прерывания
        }
    }
}
