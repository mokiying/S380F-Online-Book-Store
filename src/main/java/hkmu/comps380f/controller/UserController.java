package hkmu.comps380f.controller;

import hkmu.comps380f.dao.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService uService;

    @GetMapping(value = {"", "/userList"})
    public String list(ModelMap model) {
        model.addAttribute("userDB", uService.getUsers());
        return "userList";
    }

}
