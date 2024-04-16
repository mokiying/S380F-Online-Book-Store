package hkmu.comps380f.dao.Service;

import hkmu.comps380f.dao.Repository.*;
import hkmu.comps380f.exception.BookNotFound;
import hkmu.comps380f.exception.CartNotFound;
import hkmu.comps380f.exception.FavouriteNotFound;
import hkmu.comps380f.exception.UserNotFound;
import hkmu.comps380f.model.*;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class FavouriteService {
    @Resource
    BookUserRepository userRepo;
    @Resource
    FavouriteRepository fReop;
    @Resource
    BookRepository bookRepo;

    @Transactional
    public void createFavouriteToUser(String username) throws UserNotFound {
        BookUser user = userRepo.findById(username).orElse(null);
        if(user == null) throw new UserNotFound(username);
        Favourite newFav = new Favourite();
        newFav.setUsername(username);
        newFav.setBookUser(user);
        userRepo.save(user);
    }
    @Transactional
    public Favourite getFavourite(String username) throws FavouriteNotFound {
        Favourite favourite = fReop.getFavouriteByUsername(username);
        if(favourite == null) throw new FavouriteNotFound(username);
        return favourite;
    }
    @Transactional
    public void addFavourite(String username, long bookId) throws FavouriteNotFound, BookNotFound {
        Favourite favourite = fReop.getFavouriteByUsername(username);
        if(favourite == null) throw new FavouriteNotFound(username);
        Book book = bookRepo.findById(bookId).orElse(null);
        if (book == null) throw new BookNotFound(bookId);
        Book newBook = new Book();
        newBook.setId(bookId);
        newBook.setName(book.getName());
        newBook.setAuthor(book.getAuthor());
        newBook.setPrice(book.getPrice());
        newBook.setDescription(book.getDescription());
        newBook.setAvailability(book.getAvailability());
        newBook.setAttachments(book.getAttachments());
        newBook.setComments(book.getComments());
        newBook.setFavourites(book.getFavourites());
        favourite.getBook().add(newBook);
        fReop.save(favourite);
    }
    @Transactional
    public void deleteFavourite(String username, long bookId) throws FavouriteNotFound, BookNotFound {
        Favourite favourite = fReop.getFavouriteByUsername(username);
        if(favourite == null) throw new FavouriteNotFound(username);
        Book book = bookRepo.findById(bookId).orElse(null);
        if (book == null) throw new BookNotFound(bookId);
        favourite.getBook().remove(book);
        fReop.delete(favourite);
    }
}
