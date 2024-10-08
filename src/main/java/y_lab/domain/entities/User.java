package y_lab.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class User {
    //private long id;
    private String email;
    private String passwordHash;
    private String name;
    private boolean isBlock = false;
    private Role role;
}
