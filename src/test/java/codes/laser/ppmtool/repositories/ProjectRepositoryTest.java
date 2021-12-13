package codes.laser.ppmtool.repositories;

import codes.laser.ppmtool.model.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

/* Created By : Arjun Gautam */
@DataJpaTest
class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Project project=new Project();
        project.setProjectName("Demo");
        project.setProjectLeader("Arjun");
        project.setProjectIdentifier("1234");
        project.setDescription("This is the project description");

        entityManager.persist(project);
    }
    @Test
    public void whenFindByIdentifier_thenReturnProject(){
        Project project=projectRepository.findById(1L).get();
        assertEquals(project.getProjectName(),"Demo");
    }
}