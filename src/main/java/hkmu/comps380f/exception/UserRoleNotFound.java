package hkmu.comps380f.exception;

public class UserRoleNotFound  extends  Exception {
    public UserRoleNotFound(long id) {
        super("User Role " + id + " does not exist.");
    }
    public UserRoleNotFound(String username, String role) {
        super("BookUser " + username +"'s role:" + role + " does not exist.");
    }
}