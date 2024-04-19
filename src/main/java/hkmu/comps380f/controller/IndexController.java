package hkmu.comps380f.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "redirect:/book/list";
    }


    @GetMapping("/login")
    public String login() {
        return "NewUI/loginNewUI";
    }

    @GetMapping("/register")
    public String register() {
        return "NewUI/registerNewUI";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login";
    }
    @GetMapping("/login_by_guest")
    public String login_by_guest(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/book/list";
    }
}