package hkmu.comps380f.dao;

import hkmu.comps380f.exception.BookNotFound;
import hkmu.comps380f.exception.TicketNotFound;
import hkmu.comps380f.model.Book;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
@Service
public class BookService {
    @Resource
    private BookRepository bRepo;
    @Transactional
    public List<Book> getBooks() {
        return bRepo.findAll();
    }
    @Transactional
    public Book getBook(long id)
            throws TicketNotFound {
        Book book = bRepo.findById(id).orElse(null);
        if (book == null) {
            throw new TicketNotFound(id);
        }
        return book;
    }
    // Get Comment
    @Transactional(rollbackFor = TicketNotFound.class)
    public void delete(long id) throws TicketNotFound {
        Book deletedBook = bRepo.findById(id).orElse(null);
        if (deletedBook == null) {
            throw new TicketNotFound(id);
        }
        bRepo.delete(deletedBook);
    }
    @Transactional
    public long createBook(String name, String author, double price, String description, boolean available)
            throws IOException {
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setPrice(price);
        book.setDescription(description);
        book.setAvailable(available);
        Book savedBook = bRepo.save(book);
        return savedBook.getId();
    }

    @Transactional(rollbackFor = TicketNotFound.class)
    public void updateBook(long id, String name, String author, double price, String description, boolean available)
            throws IOException, BookNotFound {
        Book updatedBook = bRepo.findById(id).orElse(null);
        if (updatedBook == null) {
            throw new BookNotFound(id);
        }
        updatedBook.setName(name);
        updatedBook.setAuthor(author);
        updatedBook.setPrice(price);
        updatedBook.setDescription(description);
        updatedBook.setAvailable(available);
        bRepo.save(updatedBook);
    }
}
