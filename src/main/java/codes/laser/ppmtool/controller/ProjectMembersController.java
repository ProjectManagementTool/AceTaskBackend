package codes.laser.ppmtool.controller;

import codes.laser.ppmtool.model.ProjectMembers;
import codes.laser.ppmtool.model.User;
import codes.laser.ppmtool.pojo.ProjectMembersPojo;
import codes.laser.ppmtool.pojo.UserRegistrationPojo;
import codes.laser.ppmtool.services.ProjectMembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/project-members")
public class ProjectMembersController {


    @Autowired
    private ProjectMembersService service;

    @PostMapping("/add")
    public ResponseEntity<?> AddMembers(@Valid @RequestBody ProjectMembersPojo projectMembersPojo) {


        ProjectMembers members = service.addMembersToProject(projectMembersPojo);
        return new ResponseEntity<>(members, HttpStatus.CREATED);

    }
}
