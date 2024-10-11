package y_lab.in.Adapters;

import y_lab.domain.entities.Frequency;
import y_lab.domain.entities.Habit;
import y_lab.usecases.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);
        String option;
        String name;
        String habitName;
        String email;
        String password;
        String description;
        Frequency frequency;
        Long userId;
        Long habitId;
        String buffer;
        ArrayList<Habit> habits;

        // Основное меню
        while (true) {
            System.out.println("Select option:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Request Password Reset");
            System.out.println("4. Exit");

            option = scanner.nextLine();

            switch (option) {
                case "1":
                    System.out.println("Enter your username:");
                    name = scanner.nextLine();
                    System.out.println("Enter your email:");
                    email = scanner.nextLine();
                    System.out.println("Enter your password:");
                    password = scanner.nextLine();
                    registrationUseCase.register(name, email, password);
                    break;
                case "2":
                    System.out.println("Enter your email:");
                    email = scanner.nextLine();
                    System.out.println("Enter your password:");
                    password = scanner.nextLine();
                    userId = loginUseCase.login(email, password);
                    if (userId != -1)
                        LOGIN:
                        while (true) {
                            System.out.println("Select option:");
                            System.out.println("1. Habits management");
                            System.out.println("2. Edit profile");
                            System.out.println("3. Delete account");
                            System.out.println("4. Log out");
                            option = scanner.nextLine();
                            switch (option) {
                                case "1":
                                    HABIT:
                                    while (true) {
                                        System.out.println("Select option:");
                                        System.out.println("1. Create habit");
                                        System.out.println("2. See habits"); // введите имя если знаете + сортровка
                                        System.out.println("3. previous menu");
                                        option = scanner.nextLine();
                                        switch (option) {
                                            case "1":
                                                System.out.println("Enter your habit's name:");
                                                name = scanner.nextLine();
                                                System.out.println("Enter your habit's description:");
                                                description = scanner.nextLine();
                                                System.out.println("Enter your habit's frequency: daily or weekly");
                                                frequency = scanner.nextLine().equalsIgnoreCase("daily") ? Frequency.DAILY: Frequency.WEEKLY;
                                                createHabitUseCase.createHabit(userId, name, description, frequency);
                                                break;
                                            case "2":
                                                System.out.println("Enter your habit's name if you know (or press Enter to skip):");
                                                habitName = scanner.nextLine();
                                                if (!habitName.isEmpty()) {
                                                    habitId = getHabitsUseCase.getHabit(habitName, userId);
                                                    if (habitId == -1L) {
                                                        System.out.println("No such habit!");
                                                        break;
                                                    }
                                                } else {
                                                    System.out.println("Enter type of sorting");
                                                    System.out.println("1. date of creating \n2. Daily first \n3. Weekly first");
                                                    option = scanner.nextLine();
                                                    habits = getHabitsUseCase.getHabits(userId, Frequency.DAILY); //TODO validation
                                                    if (habits.isEmpty())
                                                        break;
                                                    System.out.println("Enter your habit's name if you know (or press Enter to return back):");
                                                    habitName = scanner.nextLine();
                                                    if (!habitName.isEmpty()) {
                                                        habitId = getHabitsUseCase.getHabit(habitName, userId);
                                                        if (habitId == -1L) {
                                                            System.out.println("No such habit!");
                                                            break;
                                                        }
                                                    } else
                                                        break;
                                                }
                                                while (true) {
                                                    System.out.println("Select option:");
                                                    System.out.println("1. Mark the completion");
                                                    System.out.println("2. Edit habit");
                                                    System.out.println("3. Delete habit");
                                                    System.out.println("4. previous menu");
                                                    option = scanner.nextLine();

                                                    switch (option) {
                                                        case "1":
                                                            createProgressUseCase.createProgress(userId, habitId);
                                                            break;
                                                        case "2":
                                                            System.out.println("Enter new name (or press Enter to skip): ");
                                                            name = scanner.nextLine();

                                                            System.out.println("Enter new description (or press Enter to skip): ");
                                                            description = scanner.nextLine();

                                                            System.out.println("Enter new frequency (or press Enter to skip): "); //TODO validate
                                                            frequency = scanner.nextLine().equalsIgnoreCase("daily") ? Frequency.DAILY: Frequency.WEEKLY;
                                                            updateHabitUseCase.updateHabit(habitId, name, description, frequency);
                                                            break;
                                                        case "3":
                                                            deleteHabitUseCase.deleteHabit(habitId);
                                                            break HABIT;
                                                        case "4":
                                                            break HABIT;
                                                        default:
                                                            System.out.println("Invalid option. Please try again.");
                                                    }
                                                }
                                            case "3":
                                                System.out.println("log out succeed");
                                                habitId = -1L;
                                                break HABIT;
                                            default:
                                                System.out.println("Invalid option. Please try again.");
                                        }
                                    }
                                    break;
                                case "2":
                                    System.out.println("Enter new name (or press Enter to skip): ");
                                    String newName = scanner.nextLine();

                                    System.out.println("Enter new email (or press Enter to skip): ");
                                    String newEmail = scanner.nextLine();

                                    System.out.println("Enter new password (or press Enter to skip): ");
                                    String newPassword = scanner.nextLine();
                                    editUserUseCase.editUser(userId, newName, newEmail, newPassword);
                                    break;
                                case "3":
                                    System.out.println("are you sure you want to delete your account?");
                                    System.out.println("enter \"YES\" if you want to delete the account");
                                    buffer = scanner.nextLine();
                                    if (buffer.equals("YES")) {
                                        editUserUseCase.deleteUser(userId);
                                        break LOGIN;
                                    }
                                    System.out.println("not confirmed");
                                    break;
                                case "4":
                                    System.out.println("log out succeed");
                                    userId = -1L;
                                    break LOGIN;
                                default:
                                    System.out.println("Invalid option. Please try again.");
                            }
                        }
                    break;
                case "3":
                    System.out.println("Enter your email:");
                    email = scanner.nextLine();
                    passwordResetUseCase.requestPasswordReset(email);
                    break;
                case "4":
                    System.out.println("Exiting the program...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
