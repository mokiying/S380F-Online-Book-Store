package hkmu.comps380f.dao;

import hkmu.comps380f.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
