package user;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class UserData {
    private String name;
    private String email;
    private String password;

}
