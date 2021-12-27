/**
 * Created By Arjun Gautam .
 * Date: 2020-11-25
 * Time: 07:13
 * Project Name : ppmtool
 */
package codes.laser.ppmtool.services;

import codes.laser.ppmtool.exceptions.ProjectIDException;
import codes.laser.ppmtool.exceptions.ProjectNotFoundException;
import codes.laser.ppmtool.model.Backlog;
import codes.laser.ppmtool.model.Project;
import codes.laser.ppmtool.model.ProjectMembers;
import codes.laser.ppmtool.model.User;
import codes.laser.ppmtool.repositories.BacklogRepository;
import codes.laser.ppmtool.repositories.ProjectMembersRepository;
import codes.laser.ppmtool.repositories.ProjectRepository;
import codes.laser.ppmtool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectMembersRepository projectMembersRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Project saveOrUpdateProject(Project project, String username) {

        if (project.getId() != null) {
            Project existingProject = projectRepository.findByProjectIdentifier(project.getProjectIdentifier());
            if (existingProject != null && (!existingProject.getProjectLeader().equals(username))) {
                throw new ProjectNotFoundException("Project not found in your account");
            } else if (existingProject == null) {
                throw new ProjectNotFoundException("Project with ID: '" + project.getProjectIdentifier() + "' cannot be updated because it doesn't exist");
            }
        }

        try {

            User user = userRepository.findByUsername(username);
            //project.setUser(user);
            project.setProjectLeader(user.getUsername());
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

            if (project.getId() == null) {
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            }
            if (project.getId() != null) {
                project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
            }
            //also save on the project members table
            ProjectMembers projectMembers=new ProjectMembers();
            projectMembers.setProject(project);
            projectMembers.setUser(user);
            projectMembersRepository.save(projectMembers);
            return projectRepository.save(project);

        } catch (Exception e) {
            throw new ProjectIDException("Project Id '" + project.getProjectIdentifier().toUpperCase() + "'already exists.");
        }
    }

    public Project findProjectByIdentifier(String projectId, String username) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null) {
            throw new ProjectIDException("Project Id'" + projectId + "'does not exist.");
        }
        if (!project.getProjectLeader().equals(username)) {
            throw new ProjectNotFoundException("Project not found in your account");
        }
        return project;
    }

    public Iterable<Project> findAllProjects(String username) {
        return projectRepository.findAllByProjectLeader(username);
    }

    public void deleteProjectByIdentifier(String projectId, String username) {

        projectRepository.delete(findProjectByIdentifier(projectId, username));

    }


}
