package y_lab.usecases;

import y_lab.domain.entities.User;
import y_lab.domain.repositories.UserRepository;

import static y_lab.usecases.HashFunction.hashPassword;

public class LoginUseCase {
    private final UserRepository userRepository;

    public LoginUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void login(String email, String password) {

        // Проверка существования пользователя
        User user = userRepository.findByEmail(email);
        if (user == null) {
            System.out.println("User with this email does not exist!");
            return;
        }

        // Проверка пароля
        if (!user.getPasswordHash().equals(hashPassword(password))) {
            System.out.println("Incorrect password!");
            return;
        }

        // Успешная авторизация
        System.out.println("Login successful! Welcome, " + user.getName());
    }
}
