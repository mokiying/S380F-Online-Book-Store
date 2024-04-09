package hkmu.comps380f.dao;

import hkmu.comps380f.model.User;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserManagementService {
    @Resource
    private UserRepository tuRepo;

    @Transactional
    public List<User> getTicketUsers() {
        return tuRepo.findAll();
    }

    @Transactional
    public void delete(String username) {
        User user = tuRepo.findById(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found.");
        }
        tuRepo.delete(user);
    }

    @Transactional
    public void createTicketUser(String username, String password, String[] roles) {
        User user = new User(username, password, roles);
        tuRepo.save(user);
    }
}
