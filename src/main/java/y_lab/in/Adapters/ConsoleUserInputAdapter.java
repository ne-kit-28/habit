package y_lab.in.Adapters;

import y_lab.usecases.*;

public class ConsoleUserInputAdapter {
    private final CreateHabitUseCase createHabitUseCase;
    private final CreateProgressUseCase createProgressUseCase;
    private final DeleteHabitUseCase deleteHabitUseCase;
    private final EditUserUseCase editUserUseCase;
    private final GenerateProgressStatisticsUseCase generateProgressStatisticsUseCase;
    private final GetHabitsUseCase getHabitsUseCase;
    private final LoginUseCase loginUseCase;
    private final PasswordResetUseCase passwordResetUseCase;
    private final ProgressReportUseCase progressReportUseCase;
    private final RegistrationUseCase registrationUseCase;
    private final StreakCalculationUseCase streakCalculationUseCase;
    private final UpdateHabitUseCase updateHabitUseCase;

    public ConsoleUserInputAdapter(CreateHabitUseCase createHabitUseCase, CreateProgressUseCase createProgressUseCase
            , DeleteHabitUseCase deleteHabitUseCase, EditUserUseCase editUserUseCase
            , GenerateProgressStatisticsUseCase generateProgressStatisticsUseCase, GetHabitsUseCase getHabitsUseCase
            , LoginUseCase loginUseCase, PasswordResetUseCase passwordResetUseCase
            , ProgressReportUseCase progressReportUseCase, RegistrationUseCase registrationUseCase
            , StreakCalculationUseCase streakCalculationUseCase, UpdateHabitUseCase updateHabitUseCase) {
        this.createHabitUseCase = createHabitUseCase;
        this.createProgressUseCase = createProgressUseCase;
        this.deleteHabitUseCase = deleteHabitUseCase;
        this.editUserUseCase = editUserUseCase;
        this.generateProgressStatisticsUseCase = generateProgressStatisticsUseCase;
        this.getHabitsUseCase = getHabitsUseCase;
        this.loginUseCase = loginUseCase;
        this.passwordResetUseCase = passwordResetUseCase;
        this.progressReportUseCase = progressReportUseCase;
        this.registrationUseCase = registrationUseCase;
        this.streakCalculationUseCase = streakCalculationUseCase;
        this.updateHabitUseCase = updateHabitUseCase;
    }

    public void start(){

    }
}
