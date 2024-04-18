package hkmu.comps380f.exception;

import java.util.UUID;

public class OrderNotFound extends  Exception{
    public OrderNotFound(UUID id) {
        super("Order " + id + " does not exist.");
    }
}