package go.fluent.exam.repository;

import go.fluent.exam.entity.BasketItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketItemRepository extends JpaRepository<BasketItemEntity, Long> {
}
