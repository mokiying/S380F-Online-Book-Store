package hkmu.comps380f.exception;

public class UserNotFound  extends  Exception {
    public UserNotFound(String username) {
        super("BookUser " + username + " does not exist.");
    }
}
