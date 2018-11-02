package go.fluent.exam.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shopping_basket")
public class ShoppingBasketEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerEntity customer;

    @OneToMany(mappedBy = "shoppingBasket", cascade = CascadeType.ALL)
    private List<BasketItemEntity> basketItems;

    public ShoppingBasketEntity(CustomerEntity customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShoppingBasketEntity() {
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public List<BasketItemEntity> getBasketItems() {
        return basketItems;
    }

    public void setBasketItems(List<BasketItemEntity> basketItems) {
        this.basketItems = basketItems;
    }

}
