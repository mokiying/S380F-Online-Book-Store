package hkmu.comps380f.exception;

public class OutOfStockException extends Exception{
    public OutOfStockException(long id) {
        super("Book " + id + ":Requested quantity exceeds available stock.");
    }
}
