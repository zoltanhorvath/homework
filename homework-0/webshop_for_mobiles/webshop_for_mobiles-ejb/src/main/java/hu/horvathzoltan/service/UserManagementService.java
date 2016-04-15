package hu.horvathzoltan.service;

import hu.horvathzoltan.dto.UserDTO;

import java.time.LocalDate;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
@Startup
@LocalBean
public class UserManagementService {

    private final Map<String, UserDTO> userDTOMap = new HashMap<>();

    @PostConstruct
    private void init() {
        userDTOMap.put("Admin",
                new UserDTO.UserBuilder()
                        .setAdmin(true)
                        .setUsername("Admin")
                        .setFirstName("Admin")
                        .setLastName("Janos")
                        .setPassword("Password1")
                        .setDateOfBirth(LocalDate.of(1984, 2, 19))
                        .setRegistrationDate(LocalDate.of(2016, 11, 1))
                        .build()
        );
        userDTOMap.put("User",
                new UserDTO.UserBuilder()
                        .setAdmin(false)
                        .setUsername("User")
                        .setFirstName("User")
                        .setLastName("Sandoe")
                        .setPassword("=passworD")
                        .setDateOfBirth(LocalDate.of(1985, 4, 29))
                        .setRegistrationDate(LocalDate.of(2014, 11, 1))
                        .build()
        );
    }

    public UserDTO addUser(UserDTO userToAdd) {
        return userDTOMap.put(userToAdd.getUsername(),userToAdd);
    }

    public UserDTO removeUser(String username){
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

    public List<UserDTO> getUsers(){
        return new ArrayList<>(userDTOMap.values());
    }
}
