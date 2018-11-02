package go.fluent.exam.service;

import go.fluent.exam.entity.CustomerEntity;
import go.fluent.exam.model.Customer;
import go.fluent.exam.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {


    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer add(Customer customer) {

        CustomerEntity entity = new CustomerEntity();
        entity.setName(customer.getName());
        entity.setAddress(customer.getAddress());
        entity.setContactNumber(customer.getContactNumber());

        return new Customer(customerRepository.save(entity));
    }

    @Override
    public Customer get(Long id) {

        return new Customer(customerRepository.findOne(id));
    }

}
