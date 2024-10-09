package y_lab.usecases;

import y_lab.domain.entities.User;
import y_lab.out.repositories.HabitRepositoryImpl;
import y_lab.out.repositories.ProgressRepositoryImpl;
import y_lab.out.repositories.UserRepositoryImpl;

import java.util.NoSuchElementException;
import java.util.Optional;

public class EditUserUseCase {
    private final UserRepositoryImpl userRepository;
    private final HabitRepositoryImpl habitRepository;
    private final ProgressRepositoryImpl progressRepository;

    public EditUserUseCase(UserRepositoryImpl userRepository, HabitRepositoryImpl habitRepository, ProgressRepositoryImpl progressRepository) {
        this.userRepository = userRepository;
        this.habitRepository = habitRepository;
        this.progressRepository = progressRepository;
    }

    public void editUser(Long id, String newName, String newEmail, String newPassword) {
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()) {
            System.out.println("User with this id does not exist!");
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
        habitRepository.deleteAllByUserId(id);
        progressRepository.deleteAllByUserId(id);
        System.out.println("User and all habits were deleted!");
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
