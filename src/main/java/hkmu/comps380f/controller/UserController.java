package hkmu.comps380f.controller;

import hkmu.comps380f.dao.UserService;
import hkmu.comps380f.exception.UserNotFound;
import hkmu.comps380f.model.User;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService uService;

    @GetMapping(value = {"", "/list"})
    public String list(ModelMap model) {
        model.addAttribute("userDB", uService.getUsers());
        return "userList";
    }

    @GetMapping(value = {"", "/detail/{username}"})
    public String view(ModelMap model,
                       @PathVariable("username") String username) throws UserNotFound {
        User user = uService.getUser(username);
        if (user == null) throw new UserNotFound(username);
        model.addAttribute("user", user);
        model.addAttribute("comments",user.getComments());
        System.out.println("roles"+user.getRoles());
        System.out.println("comment"+user.getComments());
        return "userDetail";
    }

}
