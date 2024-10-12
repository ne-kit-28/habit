package y_lab.usecases;

import y_lab.domain.entities.User;
import y_lab.domain.repositories.UserRepository;

import java.util.Optional;

import static y_lab.usecases.utils.HashFunction.hashPassword;

public class LoginUseCase {
    private final UserRepository userRepository;

    public LoginUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String email, String password) {

        // Проверка существования пользователя
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            System.out.println("User with this email does not exist!");
            User user_ = new User();
            user_.setId(-1L);
            return user_;
        }

        // Проверка пароля
        if (!user.get().getPasswordHash().equals(hashPassword(password))) {
            System.out.println("Incorrect password!");
            User user_ = new User();
            user_.setId(-1L);
            return user_;
        }

        if (user.get().isBlock()) {
            System.out.println("Your account is blocked!");
            User user_ = new User();
            user_.setId(-1L);
            return user_;
        }

        // Успешная авторизация
        System.out.println("Login successful! Welcome, " + user.get().getName());
        return user.get();
    }
}
