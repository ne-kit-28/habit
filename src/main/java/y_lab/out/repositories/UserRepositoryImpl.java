package y_lab.out.repositories;

import lombok.Getter;
import lombok.Setter;
import y_lab.domain.entities.User;
import y_lab.domain.repositories.UserRepository;
import y_lab.service.DataService;

import java.io.*;
import java.util.*;

@Getter
@Setter
public class UserRepositoryImpl implements UserRepository, DataService {
    HashMap<Long, User> users = new HashMap<>();
    ArrayList<String> adminEmails = new ArrayList<>();
    Long idGenerated = 0L;

    public UserRepositoryImpl(String fileName, String admins) {
        this.loadFromFile(fileName);
        this.loadAdminsFromFile(admins);
        //adminEmails.add("admin@ya.ru");
        //adminEmails.add("admin2@ya.ru");
    }

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

    /**
     * @param adminsFile
     */
    @Override
    public void loadAdminsFromFile(String adminsFile) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(adminsFile))) {
            this.setAdminEmails((ArrayList<String>) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param adminsFile
     */
    @Override
    public void saveAdmins(String adminsFile) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(adminsFile))) {
            out.writeObject(this.getAdminEmails());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveToFile(String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(this.getUsers());
            out.writeObject(this.getIdGenerated());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void loadFromFile(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            this.setUsers((HashMap<Long, User>) in.readObject());
            this.setIdGenerated((Long) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
