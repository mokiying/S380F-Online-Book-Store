package hkmu.comps380f.controller;

import hkmu.comps380f.dao.Repository.AttachmentRepository;
import hkmu.comps380f.dao.Service.ShoppingCartService;
import hkmu.comps380f.dao.Service.UserManagementService;
import hkmu.comps380f.exception.*;
import hkmu.comps380f.model.*;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.View;

import java.io.IOException;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserManagementService uService;
    @Resource
    private ShoppingCartService cService;
    @Resource
    private AttachmentRepository aService;

    @GetMapping({"", "/", "/list"})

    public String list(ModelMap model) {
        model.addAttribute("userDB", uService.getUsers());
        return "NewUI/userListNewUI";
    }

    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("NewUI/addUserNewUI", "userForm", new UserController.Form());
    }
    public static class Form {
        private String username;
        private String password;
        private String fullName;
        private String email;
        private String address;
        private String[] roles = new String[]{"ROLE_USER"};

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
    public View create(Form form) throws IOException, UserNotFound, UserAlreadyExist {
        String username = uService.createUser(
                form.getUsername(),
                form.getPassword(),
                form.getFullName(),
                form.getEmail(),
                form.getAddress(),
                form.getRoles()
        );
        cService.createCartToUser(username);
        return new RedirectView("/register", true);
    }

    @GetMapping(value = {"", "/view/{username}"})
    public String view(ModelMap model,
                       @PathVariable("username") String username) throws UserNotFound {
        BookUser bookUser = uService.getUser(username);
        if (bookUser == null) throw new UserNotFound(username);
        model.addAttribute("user", bookUser);
        model.addAttribute("comments", bookUser.getComments());
        model.addAttribute("roles", bookUser.getRoles());
        return "NewUI/userDetailNewUI";
    }
    @GetMapping("/edit/{username}")
    public String edit(@PathVariable("username") String username,
                       ModelMap model) throws UserNotFound {
        BookUser bookUser = uService.getUser(username);
        if (bookUser == null) {
            return "redirect:/user/list";
        }
        model.addAttribute("username", username);
        model.addAttribute("user", bookUser);
        model.addAttribute("password",bookUser.getPassword().split("}")[1]);
        model.addAttribute("roles", bookUser.getRoles());
        // comments
        List<Comment> comments = bookUser.getComments();
        model.addAttribute("comments", comments);
        // form
        model.addAttribute("userForm", new Form());
        return "NewUI/editUserNewUI";
    }
    @PostMapping("/edit/{username}")
    public View edit(@PathVariable("username") String username, Form form) throws IOException,UserNotFound {
        uService.editUser(
            username,
            form.getPassword(),
            form.getFullName(),
            form.getEmail(),
            form.getAddress()
        );
        return new RedirectView("/user/view/" + username, true);
    }
    @GetMapping(value = {"", "/delete/{username}"})
    public View delete(ModelMap model,
                       @PathVariable("username") String username) throws UserNotFound {
        uService.delete(username);
        return new RedirectView("/user/list",true);
    }

    @GetMapping(value = {"/roleuser/create/{username}"})
    public View addRoleUser(@PathVariable("username") String username) throws UserNotFound{
        uService.addRole(username,"ROLE_USER");
        return new RedirectView("/user/view/"+ username,true);
    }

    @GetMapping(value = {"/roleadmin/create/{username}"})
    public View addRoleAdmin(@PathVariable("username") String username) throws UserNotFound{
        uService.addRole(username,"ROLE_ADMIN");
        return new RedirectView("/user/view/"+ username,true);
    }

    @GetMapping(value = {"/role/delete/{username}/{role}"})
    public View deleteRole(@PathVariable("username") String username,@PathVariable("role") int role) throws UserNotFound, UserRoleNotFound {
        BookUser user = uService.getUser(username);
        uService.deleteRole(role);
        return new RedirectView("/user/view/"+ username,true);
    }

    @GetMapping(value = {"/personal"})
    public String viewPersonal(ModelMap model, Principal principal) throws UserNotFound{
        BookUser bookUser = uService.getUser(principal.getName());
        model.addAttribute("user",bookUser);
        model.addAttribute("comments", bookUser.getComments());
        model.addAttribute("roles", bookUser.getRoles());
        return "NewUI/userDetailNewUI";
    }
    public static class BookItemQuantityForm {
        private int quantity;

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
    @GetMapping(value={"/cart"})
    public String viewCart(ModelMap model, Principal principal) throws CartNotFound{
        Cart cart = cService.getCart(principal.getName());
        List<Map<String,Object>> items = new ArrayList<>();
        for(BookItem item : cart.getBookItems()){
            Map newItem = new HashMap();
            newItem.put("book",item.getBook());
            newItem.put("item",item);
            items.add(newItem);
        }
        model.addAttribute("cartItems",items);
        model.addAttribute("cartForm",new BookItemQuantityForm());
        return "NewUI/cartNewUI";
    }

    @GetMapping(value={"/cart/add/{bookId}"})
    public View addToCart(Principal principal, @PathVariable("bookId") long bookId) throws BookNotFound, CartNotFound, CartItemExist {
        Cart cart = cService.getCart(principal.getName());
        cService.addItem(cart.getId(),bookId);
        return new RedirectView("/user/cart",true);
    }
    @GetMapping(value={"/cart/delete/{bookId}"})
    public View deleteFromCart(Principal principal, @PathVariable("bookId") long bookId) throws CartNotFound {
        Cart cart = cService.getCart(principal.getName());
        cService.deleteItem(cart.getId(),bookId);
        return new RedirectView("/user/cart",true);
    }
    @PostMapping ("/cart/edit/{bookId}")
    public View editQuantity(Principal principal,
                             @PathVariable("bookId") long bookId,
                             BookItemQuantityForm form) throws CartNotFound {
        Cart cart = cService.getCart(principal.getName());
        cService.editItemQuantity(cart.getId(),bookId, form.getQuantity());
        return new RedirectView("/user/cart",true);
    }
    @GetMapping(value = {"/cart/checkout"})
    public View checkout(ModelMap model, Principal principal) throws CartNotFound, UserNotFound, OrderNotFound, OutOfStockException, CartNull {
        Cart cart = cService.getCart(principal.getName());

        if (cart.getBookItems()!=null) {
            Order order = cService.addOrder(principal.getName());
            try {
                cService.transferCartToOrder(order.getId(), cart.getId());
            } catch (OutOfStockException e) {
                cService.deleteOrder(order.getId());
                throw e;
            }
            for (BookItem item : cart.getBookItems()) {
                cService.deleteItem(cart.getId(), item.getBookId());
            }
        }
        else {
            throw new CartNull();


        }
        return new RedirectView("/user/orders",true);
    }
    @GetMapping(value = {"/orders"})
    public String viewOrder(Principal principal, ModelMap model){
        List<Order> orders = cService.getOrders(principal.getName());
        model.addAttribute("orders",orders);
        return "NewUI/orderNewUI";
    }
    @GetMapping(value = {"/orders/view/{orderId}"})
    public String viewOrderDetail(ModelMap model, @PathVariable("orderId") UUID orderId) throws OrderNotFound {
        Order order = cService.getOrder(orderId);
        List<Map<String,Object>> items = new ArrayList<>();
        for(OrderItem item : order.getOrderItems()){
            Map newItem = new HashMap();
            newItem.put("book",item.getBook());
            newItem.put("item",item);
            items.add(newItem);
        }
        System.out.println("items:"+items.toString());
        model.addAttribute("orderId",orderId);
        model.addAttribute("orderItems",items);
        return "NewUI/orderDetailNewUI";
    }
    @ExceptionHandler(
            {
                    UserNotFound.class,
                    BookNotFound.class,
                    CartNotFound.class,
                    CartItemExist.class,
                    UserAlreadyExist.class,
                    OutOfStockException.class,
                    CartNull.class
            })
    public ModelAndView error(Exception e) {
        return new ModelAndView("NewUI/errorNewUI", "message", e.getMessage());
    }
}
