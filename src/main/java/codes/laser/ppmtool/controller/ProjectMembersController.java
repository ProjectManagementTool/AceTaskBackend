package codes.laser.ppmtool.controller;

import codes.laser.ppmtool.model.ProjectMembers;
import codes.laser.ppmtool.pojo.ProjectMembersPojo;
import codes.laser.ppmtool.services.ProjectMembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/project-members")
public class ProjectMembersController {


    @Autowired
    private ProjectMembersService service;

    @PostMapping("/add")
    public ResponseEntity<?> AddMembers( @RequestBody ProjectMembersPojo projectMembersPojo) {


        ProjectMembers members = service.addMembersToProject(projectMembersPojo);
        return new ResponseEntity<>(members, HttpStatus.CREATED);

    }

    //get project members by project id
    @GetMapping("/all/{projectId}")
    public ResponseEntity<?> getALl(@PathVariable Long projectId) {
        List<ProjectMembers> members = service.getAllMembers(projectId);
        return new ResponseEntity<>(members, HttpStatus.FOUND);
    }
}
