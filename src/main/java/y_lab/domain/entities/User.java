package y_lab.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String email;
    private String passwordHash;
    private String name;
    private boolean isBlock = false;
    private Role role;
    private String resetToken;

    public User(String email, String passwordHash, String name, boolean isBlock, Role role) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.name = name;
        this.isBlock = isBlock;
        this.role = role;
    }
}
