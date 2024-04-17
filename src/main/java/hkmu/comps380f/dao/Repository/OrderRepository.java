package hkmu.comps380f.dao.Repository;

import hkmu.comps380f.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,String> {
    public Order getOrderByUsername(String username);
}
