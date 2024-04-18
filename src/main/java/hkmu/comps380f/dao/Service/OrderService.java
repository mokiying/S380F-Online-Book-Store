package hkmu.comps380f.dao.Service;

import hkmu.comps380f.dao.Repository.*;
import hkmu.comps380f.exception.BookNotFound;
import hkmu.comps380f.exception.CartItemExist;
import hkmu.comps380f.exception.CartNotFound;
import hkmu.comps380f.exception.OrderNotFound;
import hkmu.comps380f.model.Book;
import hkmu.comps380f.model.BookItem;
import hkmu.comps380f.model.Cart;
import hkmu.comps380f.model.Order;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    @Resource
    BookUserRepository userRepo;
   //@Resource
    //FavouriteRepository fReop;
    @Resource
    CartRepository cartRepo;
    @Resource
    BookRepository bookRepo;
    @Resource
    OrderRepository oRepo;
    @Transactional
    public Order getOrder(String username) throws OrderNotFound {
        Order order = oRepo.getOrderByUsername(username);
        if(order == null) throw new OrderNotFound(username);
        return order;
    }
    /*@Transactional
    public void addItem(long orderId, long cartId) throws CartNotFound, BookNotFound, CartItemExist, OrderNotFound {
        Cart cart = cartRepo.findById(cartId).orElse(null);
        if(cart == null) throw new CartNotFound(cartId);
        Order order = oRepo.findById(orderId).orElse(null);
        if(order == null) throw new OrderNotFound(orderId);
        List<BookItem> items = cart.getBookItems();
        order.setBookItems(items);
    }*/
}
