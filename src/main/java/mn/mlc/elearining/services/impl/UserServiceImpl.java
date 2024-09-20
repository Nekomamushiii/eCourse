package mn.mlc.elearining.services.impl;

import mn.mlc.elearining.entities.Role;
import mn.mlc.elearining.entities.User;
import mn.mlc.elearining.entities.dtos.UserRegisterDTO;
import mn.mlc.elearining.entities.enums.UserRole;
import mn.mlc.elearining.exceptions.UsernameNotFoundException;
import mn.mlc.elearining.repositories.RoleRepository;
import mn.mlc.elearining.repositories.UserRepository;
import mn.mlc.elearining.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void userRegister(UserRegisterDTO userRegisterDTO) {
        Role adminRole = this.roleRepository.findByRole(UserRole.USER);
        User user = modelMapper.map(userRegisterDTO,User.class);
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()))
                .setRoles(List.of(adminRole));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {

        return userRepository.findByUsername(username).orElseThrow(UsernameNotFoundException::new);
    }
}
