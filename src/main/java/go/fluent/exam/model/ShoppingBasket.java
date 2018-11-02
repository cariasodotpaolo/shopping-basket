package go.fluent.exam.model;

import go.fluent.exam.entity.ShoppingBasketEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingBasket {

    private Customer customer;
    private List<BasketItem> basketItems;
    private BigDecimal totalPrice;

    public ShoppingBasket() {
    }

    public ShoppingBasket(Customer customer, List<BasketItem> basketItems) {
        this.customer = customer;
        this.basketItems = basketItems;
    }

    public ShoppingBasket(ShoppingBasketEntity entity) {
        this.customer = new Customer(entity.getCustomer());
        this.basketItems = entity.getBasketItems().stream().map(e -> new BasketItem(e)).collect(Collectors.toList());
        this.totalPrice = getBasketItems().stream()
                .map(BasketItem::getTotalPrice).reduce(BigDecimal.TEN, BigDecimal::add);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public void setBasketItems(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
