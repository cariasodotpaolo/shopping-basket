package go.fluent.exam.api.controller;


import static java.util.Objects.isNull;

import go.fluent.exam.api.exception.BadRequestException;
import go.fluent.exam.model.Customer;
import go.fluent.exam.service.CustomerService;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> get(Long id) throws Exception {

        if(isNull(id) || id <= 0) {
            logger.error("Missing custoemr ID.");
            throw new BadRequestException("Missing customer ID.");
        }

        Customer customer = customerService.get(id);

        return new ResponseEntity<>(customer, HttpStatus.OK);

    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<?> createNewCustomer(@RequestBody Customer customer) throws Exception {

        if(isNull(customer) || isNull(customer.getName()) || isNull(customer.getContactNumber())) {
            logger.error("Create new Customer request body or one of its fields is null.");
            throw new BadRequestException("Unable to create new customer at this time.");
        }

        Customer newCostumer = customerService.add(customer);

            return new ResponseEntity<>(newCostumer, HttpStatus.CREATED);

    }
}
