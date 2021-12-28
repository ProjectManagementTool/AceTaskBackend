package codes.laser.ppmtool.services;

import codes.laser.ppmtool.model.Project;
import codes.laser.ppmtool.model.ProjectMembers;
import codes.laser.ppmtool.model.User;
import codes.laser.ppmtool.pojo.ProjectMembersPojo;
import codes.laser.ppmtool.repositories.ProjectMembersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectMembersService {

    private final ProjectMembersRepository projectMembersRepository;


    public ProjectMembers addMembersToProject(ProjectMembersPojo projectMembersPojo){

        ProjectMembers projectMembers=new ProjectMembers();
        projectMembers.setUser(new User(projectMembersPojo.getUserid()));
        projectMembers.setProject(new Project(projectMembersPojo.getProjectid()));
        return projectMembersRepository.save(projectMembers);

    }

    public List<ProjectMembers> getAllMembers(Long projectId)
    {
        return projectMembersRepository.findALL(projectId);
    }


}
