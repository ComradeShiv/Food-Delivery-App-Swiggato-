package com.example.Swiggato.Service.Impl;

import com.example.Swiggato.DTOs.RequestDTO.CustomerRequest;
import com.example.Swiggato.DTOs.ResponseDTO.CustomerResponse;
import com.example.Swiggato.Enum.Gender;
import com.example.Swiggato.Exception.CustomerNotFoundException;
import com.example.Swiggato.Exception.NoCustomerExistException;
import com.example.Swiggato.Model.Customer;
import com.example.Swiggato.Repository.CustomerRepository;
import com.example.Swiggato.Service.CustomerService;
import com.example.Swiggato.Transformer.CartTransformer;
import com.example.Swiggato.Transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{

    final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponse addCustomer(CustomerRequest customerRequest) {

        // dto -> model layer
        Customer customer = CustomerTransformer.customerRequestToCustomer(customerRequest);

        // allocate cart
        customer.setCart(CartTransformer.prepareCart(customer));

        // save both customer & cart
        Customer savedCustomer = customerRepository.save(customer);

        // customer -> responseDto & return
        return CustomerTransformer.customerToCustomerResponse(savedCustomer);

    }

    @Override
    public CustomerResponse getCustomer(String mobNo) {

        Customer customer = customerRepository.findByMobileNo(mobNo);
        if(customer == null)
            throw new CustomerNotFoundException("Customer not registered");

        return CustomerTransformer.customerToCustomerResponse(customer);
    }

    @Override
    public CustomerResponse findCustomerWithMostOrders() {
        List<Customer> customerList = customerRepository.findAll();

        if(customerList.isEmpty())
            throw new NoCustomerExistException("Customer Registry is empty");

        Customer maxOrdersCustomer = customerList.get(0);
        for(Customer customer: customerList) {
            if(maxOrdersCustomer.getOrder().size() < customer.getOrder().size())
                maxOrdersCustomer = customer;
        }

        return CustomerTransformer.customerToCustomerResponse(maxOrdersCustomer);
    }

    @Override
    public CustomerResponse femaleCustomerWithLeastOrders() {
        List<Customer> customerList = customerRepository.findByGender(Gender.FEMALE);

        if(customerList.isEmpty())
            throw new NoCustomerExistException("No Female Registrations Yet");

//        return customerList.stream().sorted( (a,b) -> { return a.getOrder().size() - b.getOrder().size()}).collect(Collectors.toList()).get(0);
        Customer minOrdersCustomer = customerList.get(0);
        for (Customer customer: customerList) {
            if(minOrdersCustomer.getOrder().size() > customer.getOrder().size())
                minOrdersCustomer = customer;
        }

        return CustomerTransformer.customerToCustomerResponse(minOrdersCustomer);
    }

}
