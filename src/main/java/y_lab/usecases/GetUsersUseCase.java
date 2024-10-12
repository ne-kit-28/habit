package y_lab.usecases;

import y_lab.domain.entities.Habit;
import y_lab.domain.entities.Role;
import y_lab.domain.entities.User;
import y_lab.domain.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Optional;

public class GetUsersUseCase {
    private final UserRepository userRepository;

    public GetUsersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users;
        users = new ArrayList<>(userRepository.getAll().stream()
                .filter(user -> user.getRole().equals(Role.REGULAR))
                .toList());
        if (users.isEmpty())
            System.out.println("No users");
        else
            for(User user : users) {
                System.out.println("Email: " + user.getEmail());
                System.out.println("Name: " + user.getName());
                System.out.println("Is blocked: " + user.isBlock());
                System.out.println();
            }
        return users;
    }

    public Optional<User> getUser(String email) {
        return userRepository.findByEmail(email);
    }
}
