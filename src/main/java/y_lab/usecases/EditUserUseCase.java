package y_lab.usecases;

import y_lab.domain.entities.User;
import y_lab.out.repositories.UserRepositoryImpl;

import java.util.NoSuchElementException;
import java.util.Optional;

public class EditUserUseCase {
    private final UserRepositoryImpl userRepository;

    public EditUserUseCase(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    public void editUser(Long id, String newName, String newEmail, String newPassword) {
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()) {
            System.out.println("User with this email does not exist!");
            return;
        }

        // Проверка уникальности нового email
        if (newEmail != null && !newEmail.isEmpty() && userRepository.findByEmail(newEmail).isPresent()) {
            System.out.println("Email already in use by another account!");
            return;
        }

        if (newName != null && !newName.isEmpty())
            user.get().setName(newName);
        if (newEmail != null && !newEmail.isEmpty())
            user.get().setEmail(newEmail);
        if (newPassword != null && !newPassword.isEmpty())
            user.get().setPasswordHash(HashFunction.hashPassword(newPassword));

        System.out.println("Profile updated successfully!");
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void blockUser(Long id, boolean block) {
        // Проверка на null
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null.");
        }

        // Поиск пользователя по ID
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User with ID " + id + " not found."));

        // Блокировка пользователя
        user.setBlock(block);
    }
}
