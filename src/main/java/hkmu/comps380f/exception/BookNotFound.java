package hkmu.comps380f.exception;

public class BookNotFound extends  Exception{
    public BookNotFound(long id) {
        super("Ticket " + id + " does not exist.");
    }
}
