package y_lab.domain.repositories;

import y_lab.domain.entities.User;

import java.util.Optional;

public interface UserRepository {
    boolean isEmailExist(String email);
    void save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    void deleteById(Long id);
    void update(Long id, User user);
}
