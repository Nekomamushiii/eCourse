package mn.mlc.elearining.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminPageController {
    @GetMapping("/admin/role-management")
    public String showRoleManagementPage(){
        return "admin";
    }
}
