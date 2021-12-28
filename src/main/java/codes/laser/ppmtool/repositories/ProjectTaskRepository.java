/**
 * Created By Arjun Gautam .
 * Date: 2020-12-08
 * Time: 19:31
 * Project Name : ppmtool
 */
package codes.laser.ppmtool.repositories;

import codes.laser.ppmtool.model.ProjectTask;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask,Long> {
    List<ProjectTask> findByProjectIdentifierOrderByPriority(String id);

    ProjectTask findByProjectSequence(String sequence);

    //get total project tasks
    @Query(value = "SELECT COUNT(summary) FROM project_task where project_identifier =?1",nativeQuery = true)
    Integer getTotalProjectTasks(String projectIdentifier);

    //get total TO_DO projects tasks
    @Query(value = "SELECT COUNT(summary) FROM project_task where project_identifier =?1 and status=0",nativeQuery = true)
    Integer getTotalTODOProjectTasks(String projectIdentifier);

    //get total progress tasks
    @Query(value = "SELECT COUNT(summary) FROM project_task where project_identifier =?1 and status=1",nativeQuery = true)
    Integer getTotalPROGRESStasks(String projectIdentifier);

    //get total completed tasks
    @Query(value = "SELECT COUNT(summary) FROM project_task where project_identifier =?1 and status=2",nativeQuery = true)
    Integer getTotalCompletedTasks(String projectIdentifier);

}
