package hkmu.comps380f.exception;

public class FavouriteNotFound extends Exception {
    public FavouriteNotFound(String username) {
        super("Favourite " + username + " does not exist.");
    }
}
