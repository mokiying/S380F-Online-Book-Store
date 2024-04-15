package hkmu.comps380f.exception;

public class UserRoleNotFound extends Exception{
    public UserRoleNotFound(long id) { super("UserRole " + id + " does not exist.");}
}
