package hkmu.comps380f.dao;

import hkmu.comps380f.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
