package go.fluent.exam.model;

import go.fluent.exam.entity.BasketItemEntity;

import java.math.BigDecimal;

public class BasketItem {

    private ShoppingBasket ShoppingBasket;

    private Product product;

    private Integer quantity;

    private Boolean active;

    private BigDecimal totalPrice;

    public BasketItem(ShoppingBasket shoppingBasket, Product product, Integer quantity, Boolean active) {
        ShoppingBasket = shoppingBasket;
        this.product = product;
        this.quantity = quantity;
        this.active = active;
        this.totalPrice = new BigDecimal(product.getUnitPrice()).multiply(new BigDecimal(quantity));
    }

    public BasketItem(BasketItemEntity entity) {
        ShoppingBasket = new ShoppingBasket(entity.getShoppingBasket());
        this.product = new Product(entity.getProduct());
        this.quantity = entity.getQuantity();
        this.active = entity.getActive();
        this.totalPrice = new BigDecimal(product.getUnitPrice()).multiply(new BigDecimal(quantity));
    }

    public go.fluent.exam.model.ShoppingBasket getShoppingBasket() {
        return ShoppingBasket;
    }

    public void setShoppingBasket(go.fluent.exam.model.ShoppingBasket shoppingBasket) {
        ShoppingBasket = shoppingBasket;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
