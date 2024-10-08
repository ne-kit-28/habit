package y_lab.domain.repositories;

import y_lab.domain.entities.User;

public interface UserRepository {
    boolean isEmailExist(String email);
    void save(User user);
    User findByEmail(String email);
    void deleteByEmail(String email);
    void update(String oldEmail, User user);
}
