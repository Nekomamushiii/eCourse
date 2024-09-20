package mn.mlc.elearining.services.impl;

import mn.mlc.elearining.entities.Role;
import mn.mlc.elearining.entities.User;
import mn.mlc.elearining.entities.dtos.UserRegisterDTO;
import mn.mlc.elearining.entities.enums.UserRole;
import mn.mlc.elearining.exceptions.UsernameNotFoundException;
import mn.mlc.elearining.repositories.RoleRepository;
import mn.mlc.elearining.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private UserRegisterDTO userRegisterDTO;
    private User user;
    private Role role;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setUsername("testUser");
        userRegisterDTO.setPassword("testPassword");

        user = new User();
        user.setUsername("testUser");

        role = new Role();
        role.setRole(UserRole.USER);
    }

    @Test
    public void testUserRegister() {
        when(roleRepository.findByRole(UserRole.USER)).thenReturn(role);
        when(modelMapper.map(any(UserRegisterDTO.class), eq(User.class))).thenReturn(user);
        when(passwordEncoder.encode(userRegisterDTO.getPassword())).thenReturn("encodedPassword");

        userService.userRegister(userRegisterDTO);

        verify(passwordEncoder).encode("testPassword");
        verify(userRepository).save(user);
        assertEquals(user.getPassword(), "encodedPassword");
        assertEquals(user.getRoles().size(), 1);
        assertEquals(user.getRoles().get(0).getRole(), UserRole.USER);
    }

    @Test
    public void testFindByUsername_UserFound() {
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));

        User foundUser = userService.findByUsername("testUser");

        assertNotNull(foundUser);
        assertEquals("testUser", foundUser.getUsername());
    }

    @Test
    public void testFindByUsername_UserNotFound() {
        when(userRepository.findByUsername("unknownUser")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userService.findByUsername("unknownUser"));
    }
}
