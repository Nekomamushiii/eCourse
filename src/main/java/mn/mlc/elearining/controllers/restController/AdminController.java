package mn.mlc.elearining.controllers.restController;

import mn.mlc.elearining.entities.Role;
import mn.mlc.elearining.entities.User;
import mn.mlc.elearining.entities.enums.UserRole;
import mn.mlc.elearining.entities.model.UserModel;
import mn.mlc.elearining.repositories.RoleRepository;
import mn.mlc.elearining.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AdminController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    @GetMapping("/users")
    public List<UserModel> getAllUsers(){
        return userRepository.findAll().stream()
                .map(user -> new UserModel(user.getId(),user.getUsername(),user.getRoles()))
                .collect(Collectors.toList());
    }
    @PutMapping("/users/{userId}/roles")
    public ResponseEntity<?> updateUserRoles(@PathVariable("userId") Long userId,
                                             @RequestBody List<UserRole> newRoles){
        User user = userRepository.findById(userId).orElseThrow(()->new UsernameNotFoundException("User not found!"));
        List<Role> roles = roleRepository.findAllByRoleIn(newRoles);
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok("User roles updated successfully");
    }
}
