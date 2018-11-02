package go.fluent.exam.service;

import go.fluent.exam.model.ShoppingBasket;

import java.math.BigDecimal;

public interface ShoppingBasketService {

    ShoppingBasket create(Long customerId) throws Exception;

    ShoppingBasket get(Long customerId) throws Exception;

    BigDecimal getBasketTotal(Long customerId) throws Exception;

    void addItem(Long customerId, Long productId, Integer quantity) throws Exception;

    void removeItem(Long basketItemId) throws Exception;
}
