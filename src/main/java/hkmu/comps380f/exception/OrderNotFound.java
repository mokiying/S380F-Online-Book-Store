package hkmu.comps380f.exception;

public class OrderNotFound extends Exception {
    public OrderNotFound(long id) {
        super("Order " + id + " does not exist.");
    }
    public OrderNotFound(String username) {
        super("Order " + username + " does not exist.");
    }
}
