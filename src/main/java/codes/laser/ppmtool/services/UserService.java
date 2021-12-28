/**
 * @author : Arjun Gautam
 * e-mail : arjungautam5431@gmail.com
 * Date :2021-02-19
 * Time :21:20
 */
package codes.laser.ppmtool.services;

import codes.laser.ppmtool.exceptions.UsernameAlreadyExistsException;
import codes.laser.ppmtool.model.Role;
import codes.laser.ppmtool.model.RoleConstants;
import codes.laser.ppmtool.model.User;
import codes.laser.ppmtool.pojo.ProfileUpdatePojo;
import codes.laser.ppmtool.pojo.UserRegistrationPojo;
import codes.laser.ppmtool.repositories.RoleRepository;
import codes.laser.ppmtool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //save as leader
    @Transactional
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
                user.setRole(new Role(1));
                user.setFullName(userRegistrationPojo.getFullName());
                return userRepository.save(user);

            } else {
                throw new UsernameAlreadyExistsException("Username '" + userRegistrationPojo.getUsername() + "' already exists");
            }
    }

    public User updateLeaderProfile(ProfileUpdatePojo profileUpdatePojo)
    {
        User user=userRepository.findUserByIDL(profileUpdatePojo.getUserId());
        user.setDesignation(profileUpdatePojo.getDesignation());
        user.setSkillsDescription(profileUpdatePojo.getSkillsDescription());
        user.setExperienceInYear(profileUpdatePojo.getExperienceInYear());
        return userRepository.save(user);

    }


    //save as developer
    @Transactional
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
            user.setRole(new Role(2));
            user.setFullName(userRegistrationPojo.getFullName());
            return userRepository.save(user);
        } else {
            throw new UsernameAlreadyExistsException("Username '" + userRegistrationPojo.getUsername() + "' already exists");
        }

    }

    //update developer profile
    public User updateDeveloperProfile(ProfileUpdatePojo profileUpdatePojo)
    {
        User user=userRepository.findUserByID(profileUpdatePojo.getUserId());
        user.setDesignation(profileUpdatePojo.getDesignation());
        user.setSkillsDescription(profileUpdatePojo.getSkillsDescription());
        user.setExperienceInYear(profileUpdatePojo.getExperienceInYear());
        return userRepository.save(user);

    }
}
