package codes.laser.ppmtool.services;


import codes.laser.ppmtool.model.Role;
import codes.laser.ppmtool.model.RoleConstants;
import codes.laser.ppmtool.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class PreDataConf {

    private final RoleRepository roleRepository;


    @Transactional
    @PostConstruct
    public void saveData() {

        addRoleLeader();
        addRoleDeveloper();
    }

    @Transactional
    public void addRoleLeader() {
        Role role = roleRepository.findRoleByNameLeader();
        if (role == null) {
            Role r = new Role();
            r.setName(RoleConstants.LEADER);
            roleRepository.save(r);
        }
    }

    @Transactional
    public void addRoleDeveloper() {
        Role role = roleRepository.findRoleByNameDeveloper();
        if (role == null) {
            Role r = new Role();
            r.setName(RoleConstants.DEVELOPER);
            roleRepository.save(r);
        }
    }
}
