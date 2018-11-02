package go.fluent.exam.service;

import go.fluent.exam.entity.BasketItemEntity;
import go.fluent.exam.entity.CustomerEntity;
import go.fluent.exam.entity.ProductEntity;
import go.fluent.exam.entity.ShoppingBasketEntity;
import go.fluent.exam.model.BasketItem;
import go.fluent.exam.repository.BasketItemRepository;
import go.fluent.exam.repository.CustomerRepository;
import go.fluent.exam.model.ShoppingBasket;

import go.fluent.exam.repository.ProductRepository;
import go.fluent.exam.repository.ShoppingBasketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.util.Objects.isNull;

@Service
public class ShoppingBasketServiceImpl implements ShoppingBasketService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private CustomerRepository customerRepository;
    private ShoppingBasketRepository shoppingBasketRepository;
    private BasketItemRepository basketItemRepository;
    private ProductRepository productRepository;

    @Autowired
    public ShoppingBasketServiceImpl(CustomerRepository customerRepository,
                                     ShoppingBasketRepository shoppingBasketRepository,
                                     BasketItemRepository basketItemRepository,
                                     ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.shoppingBasketRepository = shoppingBasketRepository;
        this.basketItemRepository = basketItemRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ShoppingBasket create(Long customerId) throws Exception {

        CustomerEntity customerEntity = getCustomerEntity(customerId);

        ShoppingBasketEntity basketEntity = new ShoppingBasketEntity(customerEntity);

        if(isNull(basketEntity)) {
            logger.error("Unable to create Shopping Basket for customer ID {}.", customerId);
            //TODO: throw exception here
        }

        shoppingBasketRepository.save(basketEntity);

        return new ShoppingBasket(basketEntity);
    }

    @Override
    public ShoppingBasket get(Long customerId) throws Exception {

        CustomerEntity customerEntity = getCustomerEntity(customerId);

        return new ShoppingBasket(getShoppingBasketEntity(customerEntity));
    }

    @Override
    public BigDecimal getBasketTotal(Long customerId) throws Exception {

        CustomerEntity customerEntity = getCustomerEntity(customerId);

        ShoppingBasket shoppingBasket = new ShoppingBasket(getShoppingBasketEntity(customerEntity));


        BigDecimal total = shoppingBasket.getBasketItems().stream()
                            .map(BasketItem::getTotalPrice).reduce(BigDecimal.TEN, BigDecimal::add);

        return total;
    }

    @Override
    public void addItem(Long customerId, Long productId, Integer quantity) throws Exception {

        CustomerEntity customerEntity = getCustomerEntity(customerId);

        ShoppingBasketEntity shoppingBasketEntity = getShoppingBasketEntity(customerEntity);
        ProductEntity productEntity = productRepository.getOne(productId);

        BasketItemEntity itemEntity = new BasketItemEntity(shoppingBasketEntity, productEntity, quantity);

        try {
        basketItemRepository.save(itemEntity);
        } catch (Exception e) {
            logger.error("Unable to remove item from basket.");
            throw e;
        }
    }

    @Override
    public void removeItem(Long basketItemId) throws Exception {

        try {
            basketItemRepository.delete(basketItemId);
        } catch (Exception e) {
            logger.error("Unable to remove item from basket.");
            throw e;
        }
    }

    private CustomerEntity getCustomerEntity(Long customerId) throws Exception {

        CustomerEntity customerEntity = customerRepository.findOne(customerId);

        if(isNull(customerEntity) || isNull(customerEntity.getId())) {
            logger.error("Customer ID {} not found.", customerId);
            throw new Exception("Customer not found.");
        }

        return customerEntity;

    }

    private ShoppingBasketEntity getShoppingBasketEntity(CustomerEntity customerEntity) throws Exception {

        ShoppingBasketEntity basketEntity = shoppingBasketRepository.findByCustomer(customerEntity);

        if(isNull(basketEntity) || isNull(basketEntity.getId())) {
            logger.error("Basket for customer ID {} not found.", customerEntity.getId());
            throw new Exception("Basket not found.");
        }

        return basketEntity;
    }
}
