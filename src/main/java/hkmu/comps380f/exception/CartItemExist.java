package hkmu.comps380f.exception;

public class CartItemExist extends Exception{
    public CartItemExist(long id) {
        super("Book " + id + " is exist in Cart.");
    }
}
