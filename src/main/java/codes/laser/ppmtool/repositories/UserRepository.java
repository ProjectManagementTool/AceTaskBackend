package codes.laser.ppmtool.repositories;

import codes.laser.ppmtool.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    User getById(Long id);

    @Query(value = "select * from user where id =?1",nativeQuery = true)
    User findUserByIDL(Long id);

    @Query(value = "select * from user where id =?1",nativeQuery = true)
    User findUserByID(Long id);


}
