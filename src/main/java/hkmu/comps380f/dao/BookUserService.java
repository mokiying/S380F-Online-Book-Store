package hkmu.comps380f.dao;


import hkmu.comps380f.model.BookUser;
import hkmu.comps380f.model.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import jakarta.annotation.*;

import java.util.*;

@Service
public class BookUserService implements UserDetailsService {
    @Resource
    private BookUserRepository uRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BookUser bookUser = uRepo.findById(username).orElse(null);
        if (bookUser == null) {
            throw new UsernameNotFoundException("BookUser '" + username + "' not found.");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRole role : bookUser.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new User(bookUser.getUsername(), bookUser.getPassword(), authorities);
    }
}
