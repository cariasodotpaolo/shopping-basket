package go.fluent.exam.service;

import go.fluent.exam.model.Customer;

import java.time.ZonedDateTime;
import java.util.Set;

public interface CustomerService {

    Customer add(Customer customer);

    Customer get(Long id);
}
