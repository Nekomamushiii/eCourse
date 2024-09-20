package mn.mlc.elearining.controllers;

import jakarta.validation.Valid;
import mn.mlc.elearining.entities.dtos.UserRegisterDTO;
import mn.mlc.elearining.services.UserService;
import mn.mlc.elearining.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }


    @GetMapping("/register")
    public String registerPage(Model model){
        if(!model.containsAttribute("userRegisterDTO"))
            model.addAttribute("userRegisterDTO", new UserRegisterDTO());
        return "register";
    }

    @PostMapping("/register")
    public String userRegister(@Valid UserRegisterDTO userRegisterDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userRegisterDTO",userRegisterDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDTO",bindingResult);
            return "redirect:register";
        }
        userService.userRegister(userRegisterDTO);
        return "redirect:login";
    }
    @GetMapping("/teacher")
    public String teacher(){
        return "teacher";
    }
}
