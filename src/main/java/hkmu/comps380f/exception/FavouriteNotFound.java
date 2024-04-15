package hkmu.comps380f.exception;

public class FavouriteNotFound extends Exception {
    public FavouriteNotFound(long id) {
        super("Favourite " + id + " does not exist.");
    }
}
