package hu.horvathzoltan.dto;


import hu.horvathzoltan.annotation.ChronologicalDate;
import hu.horvathzoltan.annotation.Past;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;


@ChronologicalDate(message = "{hu.horvathzoltan.dto.UserDTO.ChronologicalDate.message}")
public class UserDTO {

    @NotNull(message = "{hu.horvathzoltan.dto.UserDTO.username.NotNull.message}")
    @Size(min = 3, message = "{hu.horvathzoltan.dto.UserDTO.username.Size.message}")
    private String username;

    @NotNull(message = "{hu.horvathzoltan.dto.UserDTO.password.NotNull.message}")
    @Size(min = 6, message = "{hu.horvathzoltan.dto.UserDTO.password.Size.message}")
    @Pattern.List({
            @Pattern(regexp = "^(?=.*[a-z]).+$", message = "{hu.horvathzoltan.dto.UserDTO.password.Pattern.Lowercase.message}"),
            @Pattern(regexp = "^(?=.*[A-Z]).+$", message = "{hu.horvathzoltan.dto.UserDTO.password.Pattern.Uppercase.message}"),
            @Pattern(regexp = "^(?=.*(\\d|[\\=\\.\\,\\+\\<\\>])).+$", message = "{hu.horvathzoltan.dto.UserDTO.password.Pattern.SpecialCharacter.message")
    })
    private String password;

    private String firstName;

    private String lastName;

    @Past(message = "{hu.horvathzoltan.dto.UserDTO.dateOfBirth.Past.message}")
    private LocalDate dateOfBirth;

    public UserDTO(){
        //Default Constructor
    }


    private LocalDate registrationDate;

    private boolean admin;

    private List<MobileDTO> cart;

    private UserDTO(UserBuilder userBuilder) {
        this.username = userBuilder.username;
        this.password = userBuilder.password;
        this.firstName = userBuilder.firstName;
        this.lastName = userBuilder.lastName;
        this.dateOfBirth = userBuilder.dateOfBirth;
        this.registrationDate = userBuilder.registrationDate;
        this.admin = userBuilder.admin;
        this.cart = userBuilder.cart;
    }

    public static class UserBuilder {
        private String username;
        private String password;
        private String firstName;
        private String lastName;
        private LocalDate dateOfBirth;
        private LocalDate registrationDate;
        private boolean admin;
        private List<MobileDTO> cart;

        public UserBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder setDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public UserBuilder setRegistrationDate(LocalDate registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }

        public UserBuilder setAdmin(boolean admin) {
            this.admin = admin;
            return this;
        }

        public UserBuilder setCart(List<MobileDTO> cart) {
            this.cart = cart;
            return this;
        }

        public UserDTO build() {
            return new UserDTO(this);
        }
    }

    public List<MobileDTO> getCart() {
        return cart;
    }

    public void setCart(List<MobileDTO> cart) {
        this.cart = cart;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

}
