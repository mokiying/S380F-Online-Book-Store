package hkmu.comps380f.dao.Repository;

import hkmu.comps380f.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
