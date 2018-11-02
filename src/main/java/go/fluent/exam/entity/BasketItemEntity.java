package go.fluent.exam.entity;

import go.fluent.exam.model.Product;

import javax.persistence.*;

@Entity
@Table(name = "basket_item")
public class BasketItemEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name =  "shopping_basket_id", referencedColumnName = "id")
    private ShoppingBasketEntity ShoppingBasket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name =  "product_id", referencedColumnName = "id")
    private ProductEntity product;

    @Column(name = "quantity", length = 9)
    private Integer quantity;

    @Column(name = "active", length = 12)
    private Boolean active;

    public BasketItemEntity(ShoppingBasketEntity shoppingBasket, ProductEntity product, Integer quantity) {
        this.ShoppingBasket = shoppingBasket;
        this.product = product;
        this.quantity = quantity;
        this.active = true;
    }

    public BasketItemEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShoppingBasketEntity getShoppingBasket() {
        return ShoppingBasket;
    }

    public void setShoppingBasket(ShoppingBasketEntity shoppingBasket) {
        ShoppingBasket = shoppingBasket;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
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
}
