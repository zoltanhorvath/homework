package hu.horvathzoltan.service;

import hu.horvathzoltan.dto.UserDTO;
import java.io.Serializable;
import java.time.LocalDate;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
@Startup
public class UserManagementService implements Serializable {

    private final Map<String, UserDTO> userDTOMap = new HashMap<>();

    @PostConstruct
    private void init() {
        userDTOMap.put("admin", new UserDTO("admin", "Password1", "Admin", "Janos", LocalDate.of(1984, 2, 19), LocalDate.of(2016, 11, 1), true));
        userDTOMap.put("user", new UserDTO("user", "=passwordD", "User", "Sandor", LocalDate.of(1985, 4, 29), LocalDate.of(2014, 11, 1), false));
    }

    public UserDTO addUser(UserDTO userToAdd) {
        return userDTOMap.put(userToAdd.getUsername(), userToAdd);
    }

    public UserDTO removeUser(String username) {
        return userDTOMap.remove(username);
    }

    public UserDTO editUser(String username, UserDTO userToEdit) {
        userDTOMap.remove(username);
        userDTOMap.put(username, userToEdit);
        return userDTOMap.get(username);
    }

    public UserDTO getUser(String username) {
        return userDTOMap.get(username);
    }

    public List<UserDTO> getUsers() {
        return new ArrayList<>(userDTOMap.values());
    }
}
