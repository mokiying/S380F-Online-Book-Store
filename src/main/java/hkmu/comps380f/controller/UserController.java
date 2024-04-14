package hkmu.comps380f.controller;

import hkmu.comps380f.dao.Service.UserManagementService;
import hkmu.comps380f.exception.UserNotFound;
import hkmu.comps380f.model.BookUser;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.View;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserManagementService uService;

    @GetMapping({"", "/", "/list"})

    public String list(ModelMap model) {
        model.addAttribute("userDB", uService.getUsers());
        return "userList";
    }

    @GetMapping(value = {"", "/detail/{username}"})
    public String view(ModelMap model,
                       @PathVariable("username") String username) throws UserNotFound {
        BookUser bookUser = uService.getUser(username);
        if (bookUser == null) throw new UserNotFound(username);
        System.out.println("user comment "+bookUser.getComments().toString());
        model.addAttribute("user", bookUser);
        model.addAttribute("comments", bookUser.getComments());
        model.addAttribute("roles", bookUser.getRoles());
        return "userDetail";
    }
    @GetMapping(value = {"", "/delete/{username}"})
    public View delete(ModelMap model,
                       @PathVariable("username") String username) throws UserNotFound {
        uService.delete(username);
        return new RedirectView("/user/list",true);
    }

    @GetMapping(value = {"/personal"})
    public String viewPersonal(ModelMap model, Principal principal) throws UserNotFound{
        BookUser bookUser = uService.getUser(principal.getName());
        model.addAttribute("user",bookUser);
        model.addAttribute("comments", bookUser.getComments());
        model.addAttribute("roles", bookUser.getRoles());
        return "userDetail";
    }
}
