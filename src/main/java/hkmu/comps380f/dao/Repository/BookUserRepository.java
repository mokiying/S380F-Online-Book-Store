package hkmu.comps380f.dao.Repository;

import hkmu.comps380f.model.BookUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookUserRepository extends JpaRepository<BookUser, String> {
}
