package mn.mlc.elearining.services;

import mn.mlc.elearining.entities.User;
import mn.mlc.elearining.entities.dtos.UserRegisterDTO;

public interface UserService {
    void userRegister(UserRegisterDTO userRegisterDTO);
    User findByUsername(String username);
}
