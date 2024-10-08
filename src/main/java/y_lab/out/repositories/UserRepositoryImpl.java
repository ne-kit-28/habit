package y_lab.out.repositories;

import y_lab.domain.entities.User;
import y_lab.domain.repositories.UserRepository;

import java.util.HashMap;

public class UserRepositoryImpl implements UserRepository {
    HashMap<String, User> users = new HashMap<>();

    /**
     * @param email
     * @return
     */
    @Override
    public boolean isEmailExist(String email) {
        return users.containsKey(email);
    }

    /**
     * @param user
     */
    @Override
    public void save(User user) {
        users.put(user.getEmail(), user);
    }

    /**
     * @param email
     * @return
     */
    @Override
    public User findByEmail(String email) {
        return users.get(email);
    }

    /**
     * @param email
     */
    @Override
    public void deleteByEmail(String email) {
        users.remove(email);
    }

    /**
     * @param oldEmail
     */
    @Override
    public void update(String oldEmail, User user) {
        if (oldEmail.equals(user.getEmail()))
            users.replace(oldEmail, user);
        else {
            deleteByEmail(oldEmail);
            save(user);
        }
    }
}
