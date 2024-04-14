package hkmu.comps380f.controller;

import hkmu.comps380f.dao.Service.UserManagementService;
import hkmu.comps380f.exception.UserNotFound;
import hkmu.comps380f.model.BookUser;
import hkmu.comps380f.model.Comment;
import hkmu.comps380f.model.UserRole;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.View;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

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

    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("addUser", "userForm", new UserController.Form());
    }
    public static class Form {
        private String username;
        private String password;
        private String fullName;
        private String email;
        private String address;
        private String[] roles;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String[] getRoles() {
            return roles;
        }

        public void setRoles(String[] roles) {
            this.roles = roles;
        }
    }
    @PostMapping("/create")
    public View create(Form form) throws IOException {
        String username = uService.createUser(
                form.getUsername(),
                form.getPassword(),
                form.getFullName(),
                form.getEmail(),
                form.getAddress(),
                form.getRoles()
        );
        return new RedirectView("/user/detail/" + username, true);
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
