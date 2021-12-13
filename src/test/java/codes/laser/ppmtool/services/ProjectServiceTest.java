package codes.laser.ppmtool.services;

import codes.laser.ppmtool.model.Project;
import codes.laser.ppmtool.repositories.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProjectServiceTest {

    @Autowired
    private ProjectService projectService;
    @MockBean
    private ProjectRepository projectRepository;

    @BeforeEach
    void setUp() {
        Project project = new Project();
        project.setProjectIdentifier("1234");
        project.setProjectLeader("Arjun Gautam");
        project.setProjectName("Demo");

        Mockito.when(projectRepository.findByProjectIdentifier("1234"))

                .thenReturn(project);
    }

    @Test
    public void whenValidIdentifierOfProject_thenProjectShouldBeFound() {
        String projectIdentifier = "1234";
        String username = "Arjun Gautam";
        Project found = projectService.findProjectByIdentifier(projectIdentifier, username);

        assertEquals(projectIdentifier, found.getProjectIdentifier());

    }
}