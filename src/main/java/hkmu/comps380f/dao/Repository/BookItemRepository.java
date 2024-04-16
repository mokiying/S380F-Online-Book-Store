package hkmu.comps380f.dao.Repository;

import hkmu.comps380f.model.BookItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookItemRepository extends JpaRepository<BookItem,Long> {
    public BookItem findByCartIdAndBookId(long id,long bookId);
}
