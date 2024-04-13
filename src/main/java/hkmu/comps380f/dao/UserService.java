package hkmu.comps380f.dao;

import hkmu.comps380f.exception.CommentNotFound;
import hkmu.comps380f.exception.UserNotFound;
import hkmu.comps380f.model.Comment;
import hkmu.comps380f.model.User;
import hkmu.comps380f.model.UserRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import jakarta.annotation.*;

import java.io.IOException;
import java.util.*;

@Service
public class UserService {
    @Resource
    private UserRepository uRepo;
    @Resource
    private CommentRepository comRepo;
    /*
    @Resource
    private CartRepository cartRepo;
    @Resource
    private OrderRepository oRepo;
    @Resource
    private FavouriteRepository fRepo;
    */

    //User
    @Transactional
    public List<User> getUsers() {
        return uRepo.findAll();
    }
    @Transactional
    public User getUser(String username)
            throws UserNotFound {
        User user = uRepo.findById(username).orElse(null);
        if (user == null) {
            throw new UserNotFound(username);
        }
        return user;
    }
    @Transactional(rollbackFor = UserNotFound.class)
    public void delete(String username) throws UserNotFound {
        User deletedUser = uRepo.findById(username).orElse(null);
        if (deletedUser == null) {
            throw new UserNotFound(username);
        }
        uRepo.delete(deletedUser);
    }
    @Transactional
    public String createUser(String username, String password, String fullName, String email,
                             String address, String userRole)
            throws IOException {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFullName(fullName);
        user.setEmail(email);
        user.setAddress(address);
        User savedUser = uRepo.save(user);
        return savedUser.getUsername();
    }
    @Transactional
    public void deleteUser(String username) throws UserNotFound{
        User user = uRepo.findById(username).orElse(null);
        if (user == null) throw new UserNotFound("User '" + username + "' not found.");
        for(UserRole role : user.getRoles()){
            role.setUser(null);
        }
        user.setRoles(null);
        uRepo.delete(user);
    }
}
