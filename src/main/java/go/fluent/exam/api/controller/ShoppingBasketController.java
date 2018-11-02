package go.fluent.exam.api.controller;

import static java.util.Objects.isNull;

import go.fluent.exam.api.exception.BadRequestException;
import go.fluent.exam.api.exception.NotFoundException;
import go.fluent.exam.model.ShoppingBasket;
import go.fluent.exam.service.ShoppingBasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/shopping-basket")
public class ShoppingBasketController {

    private ShoppingBasketService shoppingBasketService;

    @Autowired
    public ShoppingBasketController(ShoppingBasketService shoppingBasketService) {
        this.shoppingBasketService = shoppingBasketService;
    }

    @RequestMapping(path = "/items/list/{customerId}", method = {RequestMethod.GET})
    public ResponseEntity<?> getbasketItems(@PathVariable Long customerId) throws Exception {

        ShoppingBasket shoppingBasket = shoppingBasketService.get(customerId);

        if (isNull(shoppingBasket) || shoppingBasket.getBasketItems().size() == 0) {
            throw new NotFoundException("No basket items found.");
        }

        return new ResponseEntity<>(shoppingBasket.getBasketItems(), HttpStatus.OK);
    }

    @RequestMapping(path = "/items/add/{customerId}", method = {RequestMethod.GET})
    public ResponseEntity<?> addItem(@PathVariable Long customerId,
                                                 @RequestParam Long productId,
                                                 @RequestParam Integer quantity) throws Exception {

        try {
            shoppingBasketService.addItem(customerId, productId, quantity);
        } catch (Exception e) {
            throw new BadRequestException("Unable to add item.");
        }

        return new ResponseEntity<>("Item added.", HttpStatus.CREATED);
    }

    @RequestMapping(path = "/items/remove/{basketItemId}", method = {RequestMethod.GET})
    public ResponseEntity<?> removeItem(@PathVariable Long basketItemId) throws Exception {

        try {
            shoppingBasketService.removeItem(basketItemId);
        } catch (Exception e) {
            throw new BadRequestException("Unable to remove item.");
        }

        return new ResponseEntity<>("Item removed.", HttpStatus.ACCEPTED);
    }

    @RequestMapping(path = "/price/total/{customerId}", method = {RequestMethod.GET})
    public ResponseEntity<?> getbasketTotalPrice(@PathVariable Long customerId) throws Exception {

        ShoppingBasket shoppingBasket = shoppingBasketService.get(customerId);

        if (isNull(shoppingBasket) || shoppingBasket.getBasketItems().size() == 0) {
            throw new NotFoundException("No basket items found.");
        }

        BigDecimal total = shoppingBasketService.getBasketTotal(customerId);

        return new ResponseEntity<>(total, HttpStatus.OK);
    }


}
