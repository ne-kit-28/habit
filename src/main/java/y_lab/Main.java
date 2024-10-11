package y_lab;

import y_lab.in.Adapters.ConsoleUserInputAdapter;
import y_lab.out.repositories.HabitRepositoryImpl;
import y_lab.out.repositories.ProgressRepositoryImpl;
import y_lab.out.repositories.UserRepositoryImpl;
import y_lab.usecases.*;


public class Main {
    public static void main(String[] args) {

        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        HabitRepositoryImpl habitRepository = new HabitRepositoryImpl();
        ProgressRepositoryImpl progressRepository = new ProgressRepositoryImpl();

        CreateHabitUseCase createHabitUseCase = new CreateHabitUseCase(habitRepository, userRepository);
        CreateProgressUseCase createProgressUseCase = new CreateProgressUseCase(habitRepository, userRepository, progressRepository);
        DeleteHabitUseCase deleteHabitUseCase = new DeleteHabitUseCase(habitRepository,progressRepository);
        EditUserUseCase editUserUseCase = new EditUserUseCase(userRepository,habitRepository, progressRepository);
        GenerateProgressStatisticsUseCase generateProgressStatisticsUseCase = new GenerateProgressStatisticsUseCase(progressRepository, habitRepository, userRepository);
        GetHabitsUseCase getHabitsUseCase = new GetHabitsUseCase(habitRepository);
        LoginUseCase loginUseCase = new LoginUseCase(userRepository);
        PasswordResetUseCase passwordResetUseCase = new PasswordResetUseCase(userRepository);
        StreakCalculationUseCase streakCalculationUseCase = new StreakCalculationUseCase(progressRepository, habitRepository);
        ProgressReportUseCase progressReportUseCase = new ProgressReportUseCase(generateProgressStatisticsUseCase, streakCalculationUseCase);
        RegistrationUseCase registrationUseCase = new RegistrationUseCase(userRepository);
        UpdateHabitUseCase updateHabitUseCase = new UpdateHabitUseCase(habitRepository);

        ConsoleUserInputAdapter inputAdapter = new ConsoleUserInputAdapter(createHabitUseCase, createProgressUseCase
                , deleteHabitUseCase, editUserUseCase,generateProgressStatisticsUseCase,getHabitsUseCase,loginUseCase
                ,passwordResetUseCase,progressReportUseCase,registrationUseCase,streakCalculationUseCase
                ,updateHabitUseCase);

        inputAdapter.start(); //TODO регулярки для email, сохранение в файл и востановление, роль админа
    }
}