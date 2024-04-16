package hkmu.comps380f.dao.Service;


import hkmu.comps380f.exception.BookNotFound;
import hkmu.comps380f.exception.CartItemExist;
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
    public void createCartToUser(String username) throws UserNotFound{
        BookUser user = userRepo.findById(username).orElse(null);
        if(user == null) throw new UserNotFound(username);
        Cart newCart = new Cart();
        newCart.setUsername(username);
        newCart.setUser(user);
        userRepo.save(user);
    }
    @Transactional
    public Cart getCart(String username) throws CartNotFound {
        Cart cart = cartRepo.getCartByUsername(username);
        if(cart == null) throw new CartNotFound(username);
        return cart;
    }
    @Transactional
    public void addItem(long cartId, long bookId) throws CartNotFound, BookNotFound,CartItemExist {
        Cart cart = cartRepo.findById(cartId).orElse(null);
        if(cart == null) throw new CartNotFound(cartId);
        List<BookItem> items = cart.getBookItems();
        Book book = bookRepo.findById(bookId).orElse(null);
        if (book == null) throw new BookNotFound(bookId);
        for(BookItem i : items) if (i.getBookId() == book.getId()) throw new CartItemExist(bookId);
        BookItem newItem = new BookItem();
        newItem.setBookId(bookId);
        newItem.setBook(book);
        newItem.setCartId(cartId);
        newItem.setCart(cart);
        newItem.setQuantity(1);
        cart.getBookItems().add(newItem);
        cartRepo.save(cart);
    }
    @Transactional
    public void deleteItem(long cartId, long bookId) throws CartNotFound {
        Cart cart = cartRepo.findById(cartId).orElse(null);
        if(cart == null) throw new CartNotFound(cartId);
        List<BookItem> items = cart.getBookItems();
        BookItem item = itemRepo.findByCartIdAndBookId(cart.getId(),bookId);
        items.remove(item);
        item.setCart(null);
        item.setBook(null);
        itemRepo.delete(item);
    }
    @Transactional
    public void editItemQuantity(long cartId, long bookId, int quantity) throws CartNotFound {
        Cart cart = cartRepo.findById(cartId).orElse(null);
        if(cart == null) throw new CartNotFound(cartId);
        System.out.println("Cart ID: "+ cart.getId());
        System.out.println("Book ID: "+ bookId);
        BookItem item = itemRepo.findByCartIdAndBookId(cart.getId(),bookId);
        if(item == null) System.out.println("Error");;
        item.setQuantity(quantity);
        itemRepo.save(item);
    }
}
