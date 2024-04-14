package hkmu.comps380f.exception;

public class CartNotFound extends  Exception{
    public CartNotFound(long id) {
        super("Cart " + id + " does not exist.");
    }
}
