package codes.laser.ppmtool.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class UserRegistrationPojo {

    @Email(message = "Username needs to be an email ")
    @NotNull(message = "Username is required")
    @Column(unique = true)
    private String username;

    @NotNull(message = "Please enter your full name")
    private String fullName;

    @NotNull(message = "Password field is required")
    private String password;

    @Transient
    @NotNull(message = "confirmPassword field is required")
    private String confirmPassword;

}
