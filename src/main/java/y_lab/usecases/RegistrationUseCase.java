package y_lab.usecases;

import y_lab.domain.entities.Role;
import y_lab.domain.entities.User;
import y_lab.domain.repositories.UserRepository;
import y_lab.service.EmailValidatorService;

import static y_lab.usecases.utils.HashFunction.hashPassword;

public class RegistrationUseCase {
    private final UserRepository userRepository;

    public RegistrationUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(String name, String email, String password) {

        Role role;

        if (!EmailValidatorService.isValid(email)){
            System.out.println("Email is incorrect!");
            return;
        }

        // Проверка существования пользователя
        if (userRepository.isEmailExist(email)) {
            System.out.println("User with this email already exists!");
            return;
        }

        if (userRepository.isAdminEmail(email))
            role = Role.ADMINISTRATOR;
        else
            role = Role.REGULAR;
        // Создание нового пользователя
        User user = new User(email, hashPassword(password), name, false, role);

        // Сохранение пользователя
        userRepository.save(user);

        System.out.println("Registration successful!");
    }
}
