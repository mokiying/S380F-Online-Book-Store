package hkmu.comps380f.dao.Service;

import hkmu.comps380f.dao.Repository.BookUserRepository;
import hkmu.comps380f.exception.UserNotFound;
import hkmu.comps380f.model.BookUser;
import hkmu.comps380f.model.UserRole;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.List;

@Service
public class UserManagementService {
    @Resource
    private BookUserRepository uRepo;
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
                             String address, String userRole)
            throws IOException {
        BookUser bookUser = new BookUser();
        bookUser.setUsername(username);
        bookUser.setPassword(password);
        bookUser.setFullName(fullName);
        bookUser.setEmail(email);
        bookUser.setAddress(address);
        BookUser savedBookUser = uRepo.save(bookUser);
        return savedBookUser.getUsername();
    }
    @Transactional
    public void deleteUser(String username) throws UserNotFound{
        BookUser bookUser = uRepo.findById(username).orElse(null);
        if (bookUser == null) throw new UserNotFound("BookUser '" + username + "' not found.");
        for(UserRole role : bookUser.getRoles()){
            role.setUser(null);
        }
        bookUser.setRoles(null);
        uRepo.delete(bookUser);
    }
}
