/**
 * @author Arjun Gautam
 * Project :ppmtool
 * Date : 2021-01-28
 * Time : 11:48
 */
package codes.laser.ppmtool.controller;

import codes.laser.ppmtool.model.User;
import codes.laser.ppmtool.payload.JWTLoginSuccessResponse;
import codes.laser.ppmtool.payload.LoginRequest;
import codes.laser.ppmtool.pojo.ProfileUpdatePojo;
import codes.laser.ppmtool.pojo.UserRegistrationPojo;
import codes.laser.ppmtool.security.JWTTokenProvider;
import codes.laser.ppmtool.services.MapValidationErrorService;
import codes.laser.ppmtool.services.UserService;
import codes.laser.ppmtool.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.validation.Valid;

import static codes.laser.ppmtool.security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private JWTTokenProvider tokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) return errorMap;

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX + tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));
    }

    //register for leader
    @PostMapping("/register-leader")
    public ResponseEntity<?> registerAsLeader(@Valid @RequestBody UserRegistrationPojo userRegistrationPojo) {
        //validate password match

            if(userRegistrationPojo.getPassword().equals(userRegistrationPojo.getConfirmPassword()))
            {
                User newUser = userService.saveUserAsLeader(userRegistrationPojo);
                return new ResponseEntity<>(newUser, HttpStatus.CREATED);
            }
            else
                return new ResponseEntity<>("Confirm Password Must Matched", HttpStatus.BAD_REQUEST);
    }
    //update for leader
    @PostMapping("/update-leader")
    public ResponseEntity<?> updateLeaderProfile(@Valid @RequestBody ProfileUpdatePojo profileUpdatePojo) {
        User newUser = userService.updateLeaderProfile(profileUpdatePojo);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    //register for developer
    @PostMapping("/register-developer")
    public ResponseEntity<?> registerAsDeveloper(@Valid @RequestBody UserRegistrationPojo userRegistrationPojo) {
        //validate password match

        if(userRegistrationPojo.getPassword().equals(userRegistrationPojo.getConfirmPassword()))
        {
            User newUser = userService.saveUserAsDeveloper(userRegistrationPojo);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>("Confirm Password Must Matched", HttpStatus.BAD_REQUEST);

    }

    //update for developers
    @PostMapping("/update-developer")
    public ResponseEntity<?> updateDeveloperProfile(@Valid @RequestBody ProfileUpdatePojo profileUpdatePojo) {
        User newUser = userService.updateDeveloperProfile(profileUpdatePojo);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

}
