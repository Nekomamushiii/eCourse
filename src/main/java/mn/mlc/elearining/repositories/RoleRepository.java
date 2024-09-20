package mn.mlc.elearining.repositories;

import mn.mlc.elearining.entities.Role;
import mn.mlc.elearining.entities.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRole(UserRole role);
    List<Role> findAllByRoleIn(List<UserRole> role);
}
