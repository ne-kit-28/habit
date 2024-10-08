package y_lab.usecases;

import y_lab.domain.entities.Role;
import y_lab.domain.entities.User;
import y_lab.domain.repositories.UserRepository;

import static y_lab.usecases.HashFunction.hashPassword;

public class RegistrationUseCase {
    private final UserRepository userRepository;

    public RegistrationUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(String name, String email, String password) {

        // Проверка существования пользователя
        if (userRepository.isEmailExist(email)) {
            System.out.println("User with this email already exists!");
            return;
        }

        // Создание нового пользователя
        User user = new User(email, hashPassword(password), name, false, Role.REGULAR);

        // Сохранение пользователя
        userRepository.save(user);

        System.out.println("Registration successful!");
    }
}
