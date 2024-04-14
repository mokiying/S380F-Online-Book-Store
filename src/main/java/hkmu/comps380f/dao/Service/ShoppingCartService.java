package hkmu.comps380f.dao.Service;


import hkmu.comps380f.exception.BookNotFound;
import hkmu.comps380f.exception.CartNotFound;
import hkmu.comps380f.exception.UserNotFound;
import hkmu.comps380f.model.BookItem;
import hkmu.comps380f.model.BookUser;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import hkmu.comps380f.dao.Repository.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import hkmu.comps380f.model.*;

@Service
public class ShoppingCartService {
    @Resource
    BookUserRepository userRepo;
    @Resource
    CartRepository cartRepo;
    @Resource
    BookItemRepository itemRepo;
    @Resource
    BookRepository bookRepo;
    @Transactional
    public void createCart(String username) throws UserNotFound{
        BookUser user = userRepo.findById(username).orElse(null);
        if(user == null) throw new UserNotFound(username);
        Cart newCart = new Cart();
        newCart.setUsername(username);
        newCart.setUser(user);
        user.setCart(newCart);
    }
    @Transactional
    public List<BookItem> getItems(long cartId) throws CartNotFound {
        Cart cart = cartRepo.findById(cartId).orElse(null);
        if(cart == null) throw new CartNotFound(cartId);
        List<BookItem> items = cart.getBookItems();
        return items;
    }
    @Transactional
    public void addItem(long cartId, long bookId) throws CartNotFound, BookNotFound {
        Cart cart = cartRepo.findById(cartId).orElse(null);
        if(cart == null) throw new CartNotFound(cartId);
        List<BookItem> items = cart.getBookItems();
        Book book = bookRepo.findById(bookId).orElse(null);
        if (book == null) throw new BookNotFound(bookId);
        BookItem newItem = new BookItem();
        newItem.setBookId(bookId);
        newItem.setBook(book);
        newItem.setCartId(cartId);
        newItem.setCart(cart);
        newItem.setQuantity(1);
        cart.getBookItems().add(newItem);
    }
}
