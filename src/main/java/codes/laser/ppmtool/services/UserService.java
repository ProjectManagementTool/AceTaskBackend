/**
 * @author : Arjun Gautam
 * e-mail : arjungautam5431@gmail.com
 * Date :2021-02-19
 * Time :21:20
 */
package codes.laser.ppmtool.services;

import codes.laser.ppmtool.exceptions.UsernameAlreadyExistsException;
import codes.laser.ppmtool.model.Role;
import codes.laser.ppmtool.model.User;
import codes.laser.ppmtool.pojo.UserRegistrationPojo;
import codes.laser.ppmtool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //save as leader
    public User saveUserAsLeader(UserRegistrationPojo userRegistrationPojo) {
        User userFromDB = userRepository.findByUsername(userRegistrationPojo.getUsername());
        if (userFromDB == null) {
            User user = new User();
            user.setPassword(bCryptPasswordEncoder.encode(userRegistrationPojo.getPassword()));
            //Username has to be unique(exception)
            user.setUsername(userRegistrationPojo.getUsername());
            //Make sure that password and confirm password match
            //We don't persist or show the confirmPassword
            user.setConfirmPassword("");
            user.setLeader(true);
            user.setRole(Role.LEADER);
            user.setFullName(userRegistrationPojo.getFullName());
            return userRepository.save(user);
        } else {
            throw new UsernameAlreadyExistsException("Username '" + userRegistrationPojo.getUsername() + "' already exists");
        }

    }

    //save as developer
    public User saveUserAsDeveloper(UserRegistrationPojo userRegistrationPojo) {
        User userFromDB = userRepository.findByUsername(userRegistrationPojo.getUsername());
        if (userFromDB == null) {
            User user = new User();
            user.setPassword(bCryptPasswordEncoder.encode(userRegistrationPojo.getPassword()));
            //Username has to be unique(exception)
            user.setUsername(userRegistrationPojo.getUsername());
            //Make sure that password and confirm password match
            //We don't persist or show the confirmPassword
            user.setConfirmPassword("");
            user.setLeader(false);
            user.setRole(Role.DEVELOPER);
            user.setFullName(userRegistrationPojo.getFullName());
            return userRepository.save(user);
        } else {
            throw new UsernameAlreadyExistsException("Username '" + userRegistrationPojo.getUsername() + "' already exists");
        }

    }
}
