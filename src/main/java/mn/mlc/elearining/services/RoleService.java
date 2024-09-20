package mn.mlc.elearining.services;

import jakarta.annotation.PostConstruct;
import mn.mlc.elearining.entities.Role;
import mn.mlc.elearining.entities.enums.UserRole;
import mn.mlc.elearining.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @PostConstruct
    public void initRole(){
        if(roleRepository.count() == 0){
            Arrays.stream(UserRole.values())
                    .map(role -> new Role().setRole(role))
                    .forEach(this.roleRepository::saveAndFlush);
        }
    }
}
