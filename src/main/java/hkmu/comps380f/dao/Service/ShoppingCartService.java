package hkmu.comps380f.dao.Service;


import hkmu.comps380f.exception.*;
import hkmu.comps380f.model.BookItem;
import hkmu.comps380f.model.BookUser;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import hkmu.comps380f.dao.Repository.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
    @Resource
    OrderRepository orderRepo;
    @Resource
    OrderItemRepository orderItemRepo;
    @Transactional
    public void createCartToUser(String username) throws UserNotFound{
        BookUser user = userRepo.findById(username).orElse(null);
        if(user == null) throw new UserNotFound(username);
        Cart newCart = new Cart();
        newCart.setUsername(username);
        newCart.setUser(user);
        userRepo.save(user);
        cartRepo.save(newCart);
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
    @Transactional
    public Order addOrder(String username) throws UserNotFound{
        Order newOrder = new Order();
        BookUser user = userRepo.findById(username).orElse(null);
        if(user == null) throw new UserNotFound(username);
        user.getOrders().add(newOrder);
        newOrder.setUsername(username);
        newOrder.setBookUser(user);
        newOrder.setDateTime(LocalDate.now());
        orderRepo.save(newOrder);
        return newOrder;
    }
    @Transactional
    public void transferCartToOrder(UUID orderId, long cartId) throws OutOfStockException {
        Order order = orderRepo.findById(orderId).orElse(null);
        Cart cart = cartRepo.findById(cartId).orElse(null);
        for (BookItem item : cart.getBookItems()){
            OrderItem newItem = new OrderItem();
            newItem.setOrderId(orderId);
            newItem.setOrder(order);
            newItem.setBookId(item.getBookId());
            newItem.setBook(item.getBook());
            newItem.setQuantity(item.getQuantity());
            order.getOrderItems().add(newItem);
            if(item.getQuantity() > item.getBook().getAvailability())
                throw new OutOfStockException(item.getId());
        }
        orderRepo.save(order);
    }
    @Transactional
    public void addOrder(long cartId) throws CartNotFound, OutOfStockException{
        Cart cart = cartRepo.findById(cartId).orElse(null);
        if(cart == null) throw new CartNotFound(cartId);
        Order newOrder = new Order();
        newOrder.setBookUser(cart.getUser());
        newOrder.setDateTime(LocalDate.now());

        System.out.println(newOrder.toString());
        orderRepo.save(newOrder);
        for (BookItem item : cart.getBookItems()){
            Book book = item.getBook();
            book.setAvailability(book.getAvailability()-item.getQuantity());
            bookRepo.save(book);
            item.setCart(null);
            itemRepo.delete(item);
            cart.getBookItems().remove(item);
        }
        cartRepo.save(cart);
    }
    @Transactional
    public List<Order> getOrders(String username){
        List<Order> orders = orderRepo.findByUsername(username);
        return orders;
    }
    @Transactional
    public Order getOrder(UUID orderId) throws OrderNotFound{
       Order order = orderRepo.findById(orderId).orElse(null);
        if(order == null) throw new OrderNotFound(orderId);
        return order;
    }
    @Transactional
    public void deleteOrder(UUID orderId) throws OrderNotFound{
        Order order = orderRepo.findById(orderId).orElse(null);
        if(order == null) throw new OrderNotFound(orderId);
        List<OrderItem> items = order.getOrderItems();
        if(items != null){
            for (OrderItem item : order.getOrderItems()){
                item.setBook(null);
                item.setOrder(null);
                order.getOrderItems().remove(item);
            }
        }
        order.setBookUser(null);
        orderRepo.delete(order);
    }
}
