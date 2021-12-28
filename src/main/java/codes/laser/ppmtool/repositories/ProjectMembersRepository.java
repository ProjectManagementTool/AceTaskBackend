package codes.laser.ppmtool.repositories;

import codes.laser.ppmtool.model.ProjectMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectMembersRepository extends JpaRepository<ProjectMembers,Integer> {


    @Query(value = "select * from project_members where projectid =?1",nativeQuery = true)
    List<ProjectMembers> findALL(Long id);


}
