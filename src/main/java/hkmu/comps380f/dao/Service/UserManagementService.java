package hkmu.comps380f.dao.Service;

import hkmu.comps380f.dao.Repository.UserRoleRepository;
import hkmu.comps380f.dao.Service.ShoppingCartService;
import hkmu.comps380f.dao.Repository.BookUserRepository;
import hkmu.comps380f.exception.AttachmentNotFound;
import hkmu.comps380f.exception.BookNotFound;
import hkmu.comps380f.exception.UserNotFound;
import hkmu.comps380f.exception.UserRoleNotFound;
import hkmu.comps380f.model.*;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserManagementService {
    @Resource
    private BookUserRepository uRepo;
    @Resource
    private UserRoleRepository rRepo;
    //BookUser
    @Transactional
    public List<BookUser> getUsers() {
        return uRepo.findAll();
    }
    @Transactional
    public BookUser getUser(String username)
            throws UserNotFound {
        BookUser bookUser = uRepo.findById(username).orElse(null);
        if (bookUser == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found.");
        }
        return bookUser;
    }
    @Transactional(rollbackFor = UserNotFound.class)
    public void delete(String username) throws UserNotFound {
        BookUser deletedBookUser = uRepo.findById(username).orElse(null);
        if (deletedBookUser == null) {
            throw new UserNotFound(username);
        }
        uRepo.delete(deletedBookUser);
    }
    @Transactional
    public String createUser(String username, String password, String fullName, String email,
                             String address, String[] userRole)
            throws IOException {
        BookUser bookUser = new BookUser(username, password, userRole);
        bookUser.setFullName(fullName);
        bookUser.setEmail(email);
        bookUser.setAddress(address);
        BookUser savedBookUser = uRepo.save(bookUser);
        return savedBookUser.getUsername();
    }
    @Transactional
    public String editUser(String username, String password, String fullName, String email,
                           String address)
            throws IOException, UserNotFound {
        BookUser bookUser = uRepo.findById(username).orElse(null);
        bookUser.setPassword(password);
        bookUser.setFullName(fullName);
        bookUser.setEmail(email);
        bookUser.setAddress(address);
        BookUser savedUser = uRepo.save(bookUser);
        return savedUser.getUsername();
    }
    @Transactional
    public void addRole(String username,String role) throws UserNotFound{
        BookUser user = uRepo.findById(username).orElse(null);
        if (user == null) throw new UserNotFound("BookUser '" + username + "' not found.");
        List<UserRole> roles = user.getRoles();
        for(UserRole r : roles) if (r.getRole().equals(role)) return;
        roles.add(new UserRole(user,role));
        uRepo.save(user);
    }
    @Transactional
    public void deleteRole(int roleId) throws UserRoleNotFound {
        UserRole role = rRepo.findById(roleId).orElse(null);
        if (role == null) throw new UserRoleNotFound(roleId);
        BookUser user = role.getUser();
        user.getRoles().remove(role);
        System.out.println(user.getRoles().toString());
        uRepo.save(user);
        rRepo.delete(role);
    }
}
