package y_lab.in.Adapters;

import y_lab.usecases.*;

public class ConsoleUserInputAdapter {
    private final CreateHabitUseCase createHabitUseCase;
    private final DeleteHabitUseCase deleteHabitUseCase;
    private final EditUserUseCase editUserUseCase;
    private final GetHabitsUseCase getHabitsUseCase;
    private final LoginUseCase loginUseCase;
    private final PasswordResetUseCase passwordResetUseCase;
    private final RegistrationUseCase registrationUseCase;
    private final UpdateHabitUseCase updateHabitUseCase;

    public ConsoleUserInputAdapter(CreateHabitUseCase createHabitUseCase, DeleteHabitUseCase deleteHabitUseCase, EditUserUseCase editUserUseCase, GetHabitsUseCase getHabitsUseCase, LoginUseCase loginUseCase, PasswordResetUseCase passwordResetUseCase, RegistrationUseCase registrationUseCase, UpdateHabitUseCase updateHabitUseCase) {
        this.createHabitUseCase = createHabitUseCase;
        this.deleteHabitUseCase = deleteHabitUseCase;
        this.editUserUseCase = editUserUseCase;
        this.getHabitsUseCase = getHabitsUseCase;
        this.loginUseCase = loginUseCase;
        this.passwordResetUseCase = passwordResetUseCase;
        this.registrationUseCase = registrationUseCase;
        this.updateHabitUseCase = updateHabitUseCase;
    }

    public void start(){

    }
}
