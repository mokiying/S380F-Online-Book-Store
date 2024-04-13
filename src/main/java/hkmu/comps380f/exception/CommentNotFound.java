package hkmu.comps380f.exception;

public class CommentNotFound extends Exception {
    public CommentNotFound(long id) {
        super("Comment " + id + " does not exist.");
    }
}
