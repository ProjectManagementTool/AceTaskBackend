package codes.laser.ppmtool.repositories;

import codes.laser.ppmtool.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface RoleRepository extends JpaRepository<Role,Integer> {


    @Query(value = "select * from role where name='L'",nativeQuery = true)
    Role findRoleByNameLeader();

    @Query(value = "select * from role where name='D'",nativeQuery = true)
    Role findRoleByNameDeveloper();
}
