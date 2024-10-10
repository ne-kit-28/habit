package y_lab.usecases;

import y_lab.domain.entities.User;
import y_lab.out.repositories.UserRepositoryImpl;

import java.util.Optional;
import java.util.UUID;

import static y_lab.usecases.utils.HashFunction.hashPassword;

public class PasswordResetUseCase {
    private final UserRepositoryImpl userRepository;

    public PasswordResetUseCase(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    // Запрос на сброс пароля
    public void requestPasswordReset(String email) {

        // Поиск пользователя по email
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            System.out.println("User with this email does not exist!");
            return;
        }

        // Генерация токена сброса пароля
        String token = generateResetToken();
        user.get().setResetToken(token);

        // Симуляция отправки email (в реальном приложении здесь отправляется email)
        sendResetEmail(user.get().getEmail(), token, "reset token: ");

        System.out.println("Password reset token sent to your email.");
    }

    public void resetPassword(String email, String token, String newPassword) {

        // Поиск пользователя по email
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            System.out.println("User with this email does not exist!");
            return;
        }

        // Проверка валидности токена
        if (!token.equals(user.get().getResetToken())) {
            System.out.println("Invalid token!");
            return;
        }


        // Сброс пароля и очистка токена
        user.get().setPasswordHash(hashPassword(newPassword));
        user.get().setResetToken(null);

        // Симуляция отправки email (в реальном приложении здесь отправляется email)
        sendResetEmail(user.get().getEmail(), newPassword, "new password: ");

        System.out.println("Password has been successfully reset!");
    }

    // Метод генерации уникального токена
    private String generateResetToken() {
        return UUID.randomUUID().toString(); // Генерация уникального UUID токена
    }

    // Симуляция отправки email с токеном
    private void sendResetEmail(String email, String token, String element) {
        // В реальном приложении тут будет логика отправки письма на email
        System.out.println("Email sent to " + email + " with " + element + token);
    }
}
