package go.fluent.exam.repository;

import go.fluent.exam.entity.CustomerEntity;
import go.fluent.exam.entity.ShoppingBasketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingBasketRepository extends JpaRepository<ShoppingBasketEntity, Long> {

    ShoppingBasketEntity findByCustomer(CustomerEntity customer);
}
