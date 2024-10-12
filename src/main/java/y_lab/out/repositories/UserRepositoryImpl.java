package y_lab.out.repositories;

import y_lab.domain.entities.User;
import y_lab.domain.repositories.UserRepository;

import java.util.*;

public class UserRepositoryImpl implements UserRepository {
    HashMap<Long, User> users = new HashMap<>();
    ArrayList<String> adminEmails = new ArrayList<>();

    Long idGenerated = 0L;

    /**
     * @param email
     * @return
     */
    @Override
    public boolean isEmailExist(String email) {
        for (Map.Entry<Long, User> entry : users.entrySet()) {
            if (Objects.equals(entry.getValue().getEmail(), email))
                return true;
        }
        return false;
    }

    /**
     * @param email
     * @return
     */
    @Override
    public boolean isAdminEmail(String email) {
        adminEmails.add("nik");
        return adminEmails.contains(email);
    }

    /**
     * @param user
     */
    @Override
    public void save(User user) {
        user.setId(idGenerated);
        users.put(idGenerated, user);
        ++idGenerated;
    }

    /**
     * @param email
     * @return
     */
    @Override
    public Optional<User> findByEmail(String email) {
        for (Map.Entry<Long, User> entry : users.entrySet()) {
            if (Objects.equals(entry.getValue().getEmail(), email))
                return Optional.of(entry.getValue());
        }
        return Optional.empty();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<User> findById(Long id) {
        return Optional.of(users.get(id));
    }

    /**
     * @return
     */
    @Override
    public ArrayList<User> getAll() {
        return new ArrayList<>(users.values());
    }

    /**
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        users.remove(id);
    }

    /**
     * @param id
     */
    @Override
    public void update(Long id, User user) {
        if (users.containsKey(id))
            users.replace(id, user);
    }
}
