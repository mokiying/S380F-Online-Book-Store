package hkmu.comps380f.exception;

public class UserAlreadyExist  extends Exception {
    public UserAlreadyExist(String username) {
        super("User " + username + " already exist.");
    }
}
